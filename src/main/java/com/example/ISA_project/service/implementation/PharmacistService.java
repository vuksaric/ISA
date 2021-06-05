package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.*;
import com.example.ISA_project.repository.ConsultationRepository;
import com.example.ISA_project.repository.PharmacistRepository;
import com.example.ISA_project.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacistService implements IPharmacistService {

    private final PharmacistRepository pharmacistRepository;
    private final IUserService userService;
    private final IReservationService reservationService;

    public PharmacistService(PharmacistRepository pharmacistRepository, IUserService userService, IReservationService reservationService) {
        this.pharmacistRepository = pharmacistRepository;
        this.userService = userService;
        this.reservationService = reservationService;
    }


    public ProfileDTO getProfile(int id) {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        return userService.getProfile(pharmacist.getUser().getId());
    }

    public List<WorkDayDTO> getWorkdays(int id) {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        List<WorkDayDTO> result = new ArrayList<>();
        for (WorkdayPharmacist workday : pharmacist.getWorkdays()) {
            List<AppointmentDTO> consultationDTOS = new ArrayList<>();
            for (Consultation consultation : workday.getConsultations())
                consultationDTOS.add(new AppointmentDTO(consultation));
            result.add(new WorkDayDTO(workday.getId(), workday.getDate(), consultationDTOS));
        }
        return result;
    }

    @Override
    public List<ReservationDTO> getReservations(int id) {

        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        return reservationService.getByPharmacy(pharmacist.getPharmacy().getName());
    }

    @Override
    public void newPharmacist(RegistrationDTO registrationDTO) {

        List<Pharmacist> pharmacists = pharmacistRepository.uniqueEmail(registrationDTO.getEmail());
        if(pharmacists.size() == 0) {
            Address address = new Address(registrationDTO.getAddress(), registrationDTO.getTown(), registrationDTO.getState());
            Gender gender;
            if (registrationDTO.getGender().equalsIgnoreCase("male"))
                gender = Gender.Male;
            else if (registrationDTO.getGender().equalsIgnoreCase("female"))
                gender = Gender.Female;
            else
                gender = Gender.NonBinary;
            User user = new User(registrationDTO.getName(), registrationDTO.getSurname(), registrationDTO.getEmail(), registrationDTO.getPassword(), registrationDTO.getPhone(), address, gender, registrationDTO.getBirthday(), UserType.Pharmacist);
            Pharmacist pharmacist = new Pharmacist();
            pharmacist.setUser(user);
            try {
                pharmacistRepository.save(pharmacist);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<PharmacistDTO> getAll() {
        ArrayList<Pharmacist> pharmacists = new ArrayList<>();
        ArrayList<PharmacistDTO> pharmacistDTOS = new ArrayList<>();
        try{
            pharmacists = (ArrayList<Pharmacist>) pharmacistRepository.findAll();
            for(Pharmacist p : pharmacists){
                pharmacistDTOS.add(new PharmacistDTO(p));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return pharmacistDTOS;
    }

    @Override
    public List<PharmacistDTO> search(String input) {
        List<Pharmacist> pharmacists = new ArrayList<>();
        ArrayList<PharmacistDTO> pharmacistDTOS = new ArrayList<>();
        try {
            if(input.contains(" ")){
                String[] inputs = input.split(" ");
                if(inputs.length == 1){
                    pharmacists = (ArrayList<Pharmacist>) pharmacistRepository.search(inputs[0]);

                }else{
                    pharmacists = (ArrayList<Pharmacist>) pharmacistRepository.search(inputs[0], inputs[1]);
                }
            }else {
                pharmacists = (ArrayList<Pharmacist>) pharmacistRepository.search(input);
            }
                for (Pharmacist p : pharmacists) {
                    pharmacistDTOS.add(new PharmacistDTO(p));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pharmacistDTOS;
    }

    public List<Period> getPeriods(WorkdayPharmacist workday, WorkingHours workingHours)
    {
        boolean check = false;
        List<Period> periods = new ArrayList<>();

        LocalDateTime start = LocalDateTime.of(workday.getDate(), workingHours.getStartTime());
        LocalDateTime end = LocalDateTime.of(workday.getDate(), workingHours.getEndTime());
        LocalDateTime end_period = start.plusMinutes(30);
        while (!check) {
            periods.add(new Period(start, start.plusMinutes(30)));
            start = start.plusMinutes(30);
            end_period = end_period.plusMinutes(30);
            check = end.isEqual(start);
        }
        return periods;
    }

    @Override
    public List<Period> freePeriods(int id, LocalDate date) {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        WorkdayPharmacist workday = null;
        List<Period> periods = new ArrayList<>();

        for (WorkdayPharmacist workdayPharmacist : pharmacist.getWorkdays()) {
            if (workdayPharmacist.getDate().equals(date)) {
                workday = workdayPharmacist;
                break;
            }
        }

        if (workday == null) {
            return periods;
        }

        periods = getPeriods(workday, pharmacist.getWorkingHours());

        for (Consultation consultation : workday.getConsultations()) {
            for (Period period : periods) {
                if (consultation.getPeriod().getStart_date().equals(period.getStart_date())) {
                    periods.remove(period);
                    break;
                }
            }
        }
        return periods;
    }

    @Override
    public void addVacation(Vacation vacation) {
        Pharmacist pharmacist = pharmacistRepository.getOne(vacation.getUser_id());
        List<Vacation> vacations = pharmacist.getVacation();
        vacations.add(vacation);
        pharmacist.setVacation(vacations);
        pharmacistRepository.save(pharmacist);
    }

    @Override
    public Pharmacist getById(int id){
        return pharmacistRepository.findOneById(id);
    }

    @Override
    public void delete(int id) {
        try{
            pharmacistRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void addNewConsultation(Consultation consultation) {
        Pharmacist pharmacist = pharmacistRepository.findOneById(consultation.getPharmacist().getId());
        for(WorkdayPharmacist workdayPharmacist : pharmacist.getWorkdays())
        {
            if(consultation.getPeriod().getStart_date().toLocalDate().equals(workdayPharmacist.getDate())) {
                workdayPharmacist.getConsultations().add(consultation);
                break;
            }
        }
        pharmacistRepository.save(pharmacist);
    }

    @Override
    public boolean checkVacation(CheckVacationRequest request) {
        Pharmacist pharmacist = pharmacistRepository.findOneById(request.getId());
        LocalDate date = request.getDate();
        for (Vacation vacation : pharmacist.getVacation()) {
            if (date.equals(vacation.getStart_date().toLocalDate()) || date.equals(vacation.getEnd_date().toLocalDate()))
                return false;

            if (date.isAfter(vacation.getStart_date().toLocalDate()) && date.isBefore(vacation.getEnd_date().toLocalDate()))
                return false;
        }
        return true;
    }

    public void cancelConsultation(Consultation consultation){
        Pharmacist pharmacist = pharmacistRepository.findOneById(consultation.getPharmacist().getId());
        for(WorkdayPharmacist workdayPharmacist : pharmacist.getWorkdays())
        {
            if(consultation.getPeriod().getStart_date().toLocalDate().equals(workdayPharmacist.getDate())) {
                workdayPharmacist.getConsultations().remove(consultation);
                break;
            }
        }
        pharmacistRepository.save(pharmacist);
    }

    @Override
    public Pharmacist findOneById(int id) {
        return pharmacistRepository.findOneById(id);
    }
}
