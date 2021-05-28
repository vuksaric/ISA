package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.*;
import com.example.ISA_project.repository.ConsultationRepository;
import com.example.ISA_project.repository.PharmacistRepository;
import com.example.ISA_project.service.IPharmacistService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacistService implements IPharmacistService {

    private final PharmacistRepository pharmacistRepository;
    private final ConsultationRepository consultationRepository;
    private final UserService userService;
    private final ReservationService reservationService;


    public PharmacistService(PharmacistRepository pharmacistRepository, ConsultationRepository consultationRepository, UserService userService, ReservationService reservationService) {
        this.pharmacistRepository = pharmacistRepository;
        this.consultationRepository = consultationRepository;
        this.userService = userService;
        this.reservationService = reservationService;
    }


    public ProfileDTO getProfile(int id)
    {
        return userService.getProfile(id);
    }

    public List<WorkDayDTO> getWorkdays(int id)
    {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        List<WorkDayDTO> result = new ArrayList<>();
        for(WorkdayPharmacist workday : pharmacist.getWorkdays())
        {
            List<AppointmentDTO> consultationDTOS = new ArrayList<>();
            for(Consultation consultation : workday.getConsultations())
                consultationDTOS.add(new AppointmentDTO(consultation.getId(),consultation.getPeriod().getStart_date(),consultation.getPeriod().getEnd_date(),consultation.getPharmacy().getName(),consultation.getPatient().getUser().getFullName()));
            result.add(new WorkDayDTO(workday.getId(),workday.getPeriod().getStart_date(),consultationDTOS));
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
        ArrayList<Pharmacist> pharmacists = new ArrayList<>();
        ArrayList<PharmacistDTO> pharmacistDTOS = new ArrayList<>();
        try{
            pharmacists = (ArrayList<Pharmacist>) pharmacistRepository.search(input);
            for(Pharmacist p : pharmacists){
                pharmacistDTOS.add(new PharmacistDTO(p));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return pharmacistDTOS;
    }

}
