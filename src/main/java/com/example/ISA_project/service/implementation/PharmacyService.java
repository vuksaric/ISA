package com.example.ISA_project.service.implementation;


import com.example.ISA_project.model.*;
import com.example.ISA_project.model.Bill;
import com.example.ISA_project.model.MedicineQuantity;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.PharmacistDTO;
import com.example.ISA_project.model.dto.PharmacyDTO;
import com.example.ISA_project.repository.PharmacyRepository;
import com.example.ISA_project.model.dto.*;
import com.example.ISA_project.repository.*;
import com.example.ISA_project.service.IBillService;
import com.example.ISA_project.service.IMedicineNotificationService;
import com.example.ISA_project.service.IMedicineService;
import com.example.ISA_project.service.IPharmacyService;
import com.example.ISA_project.service.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PharmacyService implements IPharmacyService {
    private final PharmacyRepository pharmacyRepository;
    private final IMedicineService medicineService;
    private final IBillService billService;
    private final IMedicineNotificationService medicineNotificationService;
    private final PharmacistRepository pharmacistRepository;
    private final UserService userService;
    private final MedicineQuantityRepository medicineQuantityRepository;
    private final DermatologistService dermatologistService;
    private final WorkingHoursService workingHoursService;
    private final WorkdayDermatologistService workdayDermatologistService;
    private final ReservationRepository reservationRepository;

    public PharmacyService(PharmacyRepository pharmacyRepository, IMedicineService medicineService, IBillService billService, PharmacistRepository pharmacistRepository, UserService userService, MedicineQuantityRepository medicineQuantityRepository, DermatologistService dermatologistService, WorkingHoursService workingHoursService, WorkdayDermatologistService workdayDermatologistService, ReservationRepository reservationRepository, IMedicineNotificationService medicineNotificationService, IMedicineNotificationService medicineNotificationService1){
        this.pharmacyRepository=pharmacyRepository;
        this.medicineService = medicineService;
        this.billService = billService;
        this.pharmacistRepository = pharmacistRepository;
        this.userService = userService;
        this.medicineQuantityRepository = medicineQuantityRepository;
        this.dermatologistService = dermatologistService;
        this.workingHoursService = workingHoursService;
        this.workdayDermatologistService = workdayDermatologistService;
        this.reservationRepository = reservationRepository;
        this.medicineNotificationService = medicineNotificationService1;
    }
    @Override
    public List<PharmacyDTO> findAll() {
        List<PharmacyDTO> pharmacies = new ArrayList<PharmacyDTO>();
        for (Pharmacy pharmacy: pharmacyRepository.findAll()) {
            pharmacies.add(new PharmacyDTO(pharmacy));
        }
        return pharmacies;
    }

    @Override
    public List<PharmacyDTO> findAllDermatologist(int id) {
        List<PharmacyDTO> pharmacies = new ArrayList<PharmacyDTO>();
        for (Pharmacy pharmacy: pharmacyRepository.findAll()) {
            for(Dermatologist dermatologist : pharmacy.getDermatologist())
            {
                if(dermatologist.getId() == id) {
                    pharmacies.add(new PharmacyDTO(pharmacy));
                    break;
                }
            }
        }
        return pharmacies;
    }


    public Boolean registerPharmacy(Pharmacy pharmacy){
        Pharmacy ph = pharmacyRepository.save(pharmacy);
        if (ph != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean prescribeMedicine(int idPharmacy, int idMedicine) {
        Pharmacy pharmacy = pharmacyRepository.findOneById(idPharmacy);
        for(MedicineQuantity medicine : pharmacy.getMedicines())
        {
            if(medicine.getMedicine().getId() == idMedicine)
            {
                medicine.setQuantity(medicine.getQuantity()-1);
                pharmacyRepository.save(pharmacy);
                return true;
            }
        }
        return false;
    }



    @Override
    public List<MedicineDTO> getReplacements(int idPharmacy, int idMedicine) {

        Pharmacy pharmacy = pharmacyRepository.findOneById(idPharmacy);
        List<Integer> replacements = medicineService.getReplacementIds(idMedicine);
        List<MedicineDTO> result = new ArrayList<>();

        for(Integer replacement : replacements)
        {
            for(MedicineQuantity medicineQuantity : pharmacy.getMedicines())
            {
                if(medicineQuantity.getMedicine().getId() == replacement && medicineQuantity.getQuantity()>0)
                    result.add(new MedicineDTO(medicineQuantity.getMedicine()));
            }
        }
        return result;
    }

    @Override
    public boolean checkQuantity(int idPharmacy, int idMedicine) {
        Pharmacy pharmacy = pharmacyRepository.findOneById(idPharmacy);
        for (MedicineQuantity medicine : pharmacy.getMedicines()) {
            if (medicine.getMedicine().getId() == idMedicine) {
                if (medicine.getQuantity() > 0) {
                    return true;
                }
                else
                {
                    medicineNotificationService.saveNotification(new MedicineNotification(medicineService.findOneById(idMedicine),idPharmacy));
                }
            }
        }
        return false;
    }

    public Pharmacy findOneById(int id) {
        return pharmacyRepository.findOneById(id);
    }

    @Override
    public PharmacyDTO findOneByIdDTO(int id) {
        PharmacyDTO pharmacyDTO = null;
       try{
           Pharmacy pharmacy = pharmacyRepository.findOneById(id);
           pharmacyDTO = new PharmacyDTO(pharmacy);
       }catch(Exception e){
           e.printStackTrace();
       }

       return pharmacyDTO;
    }

    @Override
    public List<PharmacyDTO> findPharmacyByMedicineQuantity(int id) {
        List<PharmacyDTO> pharmacies = new ArrayList<PharmacyDTO>();
        for(Pharmacy pharmacy : pharmacyRepository.findAll()){
            for(MedicineQuantity medicineQuantity : pharmacy.getMedicines()){
                if(medicineQuantity.getMedicine().getId()==id && medicineQuantity.getQuantity()>0){
                    pharmacies.add(new PharmacyDTO(pharmacy));
                }
            }
        }
        return pharmacies;
    }
    @Override
    public Pharmacy subtractMedicineQuantity(int idMedicine, int idPharmacy){
        Pharmacy pharmacy = findOneById(idPharmacy);
        for(MedicineQuantity medicineQuantity : pharmacy.getMedicines()){
            if(medicineQuantity.getMedicine().getId()==idMedicine){
                medicineQuantity.setQuantity(medicineQuantity.getQuantity()-1);
                Bill bill = billService.newBill(medicineQuantity.getMedicine(), pharmacy);
            }
        }
        pharmacyRepository.save(pharmacy);

        return pharmacy;
    }

    @Override
    public Pharmacy addMedicineQuantity(int idMedicine, int idPharmacy) {
        Pharmacy pharmacy = findOneById(idPharmacy);
        for(MedicineQuantity medicineQuantity : pharmacy.getMedicines()){
            if(medicineQuantity.getMedicine().getId()==idMedicine){
                medicineQuantity.setQuantity(medicineQuantity.getQuantity()+1);
            }
        }
        pharmacyRepository.save(pharmacy);
        return pharmacy;
    }

    @Override
    public List<PharmacyDTO> subscribedPharmacies(int idPatient) {
        List<PharmacyDTO> pharmacies = new ArrayList<>();
        for(Pharmacy pharmacy : pharmacyRepository.findAll()){
            for(Patient patient : pharmacy.getSubscribers()){
                if(patient.getId()==idPatient){
                    pharmacies.add(new PharmacyDTO(pharmacy));
                }
            }
        }
        return pharmacies;
    }

    @Override
    public Pharmacy getByName(String name) {
        return pharmacyRepository.findOneByName(name);
    }

    @Override
    public Set<PharmacyDTO> getPharmaciesWithFreeAppointment(LocalDateTime start) {
        WorkdayPharmacist workdayPharmacist = null;
        boolean consultationExistance = false;
        List<PharmacyDTO> pharmacyDTOS = new ArrayList<>();

        for(Pharmacy pharmacy : pharmacyRepository.findAll()){
            for(Pharmacist pharmacist : pharmacy.getPharmacists()){
                if(checkIfPharmacistWorks(pharmacist, start)){
                    workdayPharmacist = findWorkday(start, pharmacist.getWorkdays());
                    if (workdayPharmacist == null) {
                        break;
                    }
                    consultationExistance = checkCnocultationExistance(workdayPharmacist.getConsultations(), start);
                    if(!consultationExistance){
                        pharmacyDTOS.add(new PharmacyDTO(pharmacy));
                    }
                }
            }
        }
        return new HashSet<>(pharmacyDTOS);
    }

    @Override
    public List<PharmacistDTO> getAvailablePharmacistsByPharmacy(int id, LocalDateTime start) {
        Pharmacy pharmacy = pharmacyRepository.findOneById(id);
        List<PharmacistDTO> pharmacistDTOS = new ArrayList<>();
        WorkdayPharmacist workdayPharmacist = null;
        boolean consultationExistance = false;

            for(Pharmacist pharmacist : pharmacy.getPharmacists()){
                if(checkIfPharmacistWorks(pharmacist, start)){
                    workdayPharmacist = findWorkday(start, pharmacist.getWorkdays());
                    if (workdayPharmacist == null) {
                        break;
                    }
                    consultationExistance = checkCnocultationExistance(workdayPharmacist.getConsultations(), start);
                    if(!consultationExistance){
                        pharmacistDTOS.add(new PharmacistDTO(pharmacist.getFullName(),pharmacist.getMark(),pharmacist.getId()));
                    }
                }
            }
        return pharmacistDTOS;
    }


    private boolean checkCnocultationExistance(List<Consultation> consultations, LocalDateTime start){
        boolean existance = false;
        for(Consultation consultation : consultations){
            if(consultation.getPeriod().getStart_date().toLocalTime().equals(start.toLocalTime())){
                existance = true;
                break;
            }
        }
        return existance;
    }

    private WorkdayPharmacist findWorkday(LocalDateTime date, List<WorkdayPharmacist> workdayPharmacists){
        WorkdayPharmacist workday = null;
        for(WorkdayPharmacist workdayPharmacist : workdayPharmacists){
            if(workdayPharmacist.getDate().equals(date.toLocalDate())){
                workday = workdayPharmacist;
                break;
            }
        }
        return workday;
    }

    private boolean checkIfPharmacistWorks(Pharmacist pharmacist, LocalDateTime date){
        WorkingHours workingHours = pharmacist.getWorkingHours();

        if(!checkIfPharmacistVacation(pharmacist, date)){
            return false;
        }
        else {
            if (date.toLocalTime().isAfter(workingHours.getStartTime()) &&
                    date.toLocalTime().plusMinutes(29).isBefore(workingHours.getEndTime()))
                return true;
            else
                return false;
        }
    }

    private boolean checkIfPharmacistVacation(Pharmacist pharmacist, LocalDateTime date){
        for (Vacation vacation : pharmacist.getVacation()) {
            if (date.equals(vacation.getStart_date()) || date.equals(vacation.getEnd_date()))
                return false;

            if (date.isAfter(vacation.getStart_date()) && date.isBefore(vacation.getEnd_date()))
                return false;
        }
        return true;
    }

    public List<PharmacistDTO> getAllPharmacists(int id) {
        List<Pharmacist> pharmacists;
        List<PharmacistDTO> pharmacistDTOS = new ArrayList<>();
        try{
            Pharmacy pharmacy = pharmacyRepository.findOneById(id);
            pharmacists = pharmacy.getPharmacists();
            for(Pharmacist p : pharmacists){
                pharmacistDTOS.add(new PharmacistDTO(p));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return pharmacistDTOS;
    }

    @Override
    public List<DermatologistDTO> getAllDermatologists(int id) {
        List<Dermatologist> dermatologists;
        List<DermatologistDTO> dermatologistDTOS = new ArrayList<>();
        try{
            Pharmacy pharmacy = pharmacyRepository.findOneById(id);
            dermatologists = pharmacy.getDermatologist();
            for(Dermatologist d : dermatologists){
                dermatologistDTOS.add(new DermatologistDTO(d,findAllPharmacies(d.getId())));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return dermatologistDTOS;
    }

    public List<PharmacyDTO> findAllPharmacies(int dermatologistId){
        List<PharmacyDTO> pharmacyDTOS = new ArrayList<>();
        try{
            List<Pharmacy> pharmacies = pharmacyRepository.findAll();
            for(Pharmacy p : pharmacies){
                for(Dermatologist d : p.getDermatologist()){
                    if(d.getId() == dermatologistId){
                        pharmacyDTOS.add(new PharmacyDTO(p));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return pharmacyDTOS;
    }

    @Override
    public List<MedicineQuantityDTO> getAllMedicines(int id) {
        List<MedicineQuantityDTO> medicineDTOS = new ArrayList<>();
        try{
            Pharmacy p = pharmacyRepository.findOneById(id);
            for( MedicineQuantity medicineQuantity : p.getMedicines()){
                if(medicineQuantity.getQuantity() != 0)
                    medicineDTOS.add(new MedicineQuantityDTO(medicineQuantity));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return medicineDTOS;
    }

    @Override
    public float getMark(int id) {
        float retVal = 0;
        try{
            Pharmacy p = pharmacyRepository.findOneById(id);
            retVal = p.getMark();
        }catch(Exception e){
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void newMedicineQuantity(int medicineId, int pharmacyId) {
        try{
            Pharmacy p = pharmacyRepository.findOneById(pharmacyId);
            MedicineQuantity mq = medicineQuantityRepository.findById(medicineId);
            p.getMedicines().add(mq);
            pharmacyRepository.save(p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<MedicineQuantityDTO> removeMedicineQuantity(int medicineId, int pharmacyId) {
        List<MedicineQuantity> medicineQuantities = new ArrayList<>();
        List<MedicineQuantityDTO> medicineQuantityDTOS = new ArrayList<>();
        try{
            Pharmacy p = pharmacyRepository.findOneById(pharmacyId);
            for(Reservation r : reservationRepository.findAll()){
                if(r.getMedicine().getId() == medicineId && r.getPharmacy().getId() == pharmacyId && !r.isCanceled() && !r.isIssued()){
                    return null;
                }
            }
            medicineQuantities = p.getMedicines();
            for(MedicineQuantity mq : medicineQuantities){
                if(mq.getMedicine().getId() == medicineId){
                    medicineQuantities.remove(mq);
                    break;
                }
            }
            p.setMedicines(medicineQuantities);
            pharmacyRepository.save(p);
            for( MedicineQuantity medicineQuantity : medicineQuantities) {
                medicineQuantityDTOS.add(new MedicineQuantityDTO(medicineQuantity));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return medicineQuantityDTOS;
    }

    public List<MedicineQuantityDTO> getAllMedicinesInPharmacy(int id) {
        List<MedicineQuantityDTO> medicineDTOS = new ArrayList<>();
        try{
            Pharmacy p = pharmacyRepository.findOneById(id);
            for( MedicineQuantity medicineQuantity : p.getMedicines()){
                medicineDTOS.add(new MedicineQuantityDTO(medicineQuantity));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return medicineDTOS;
    }

    @Override
    public List<PharmacistDTO> addPharmacistInPharmacy(RegistrationDTO registrationDTO, int pharmacyId) {
        List<PharmacistDTO> pharmacistDTOS = new ArrayList<>();
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
        LocalTime start = registrationDTO.getStartShift().toLocalTime();
        LocalTime end = registrationDTO.getEndShift().toLocalTime();
        try{
            Pharmacy pharmacy = pharmacyRepository.findOneById(pharmacyId);
            User user1 = userService.save(user);
            WorkingHours workingHours = workingHoursService.saveWorkingHours(new WorkingHours(start,end,pharmacy));
            pharmacist.setPharmacy(pharmacy);
            pharmacist.setWorkingHours(workingHours);
            pharmacist.setUser(user1);
            Pharmacist pharmacist1 = pharmacistRepository.save(pharmacist);
            boolean hasEmail = false;
            for(Pharmacist p : pharmacy.getPharmacists()){
                if(p.getUser().getEmail().equals(registrationDTO.getEmail()))
                    hasEmail = true;
            }
            if(!hasEmail){
                List<Pharmacist> pharmacists = pharmacy.getPharmacists();
                pharmacists.add(pharmacist1);
                pharmacy.setPharmacists(pharmacists);
                pharmacyRepository.save(pharmacy);
            }

            for(Pharmacist p : pharmacy.getPharmacists()){
                pharmacistDTOS.add(new PharmacistDTO(p,pharmacy));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return pharmacistDTOS;
    }

    @Override
    public List<PharmacistDTO> removePharmacistInPharmacy(int pharmacistId, int pharmacyId) {
        List<PharmacistDTO> pharmacistDTOS = new ArrayList<>();
        try{
            Pharmacy pharmacy = pharmacyRepository.findOneById(pharmacyId);
            List<Pharmacist> pharmacists = pharmacy.getPharmacists();
            for( Pharmacist p : pharmacists){
                if(p.getId() == pharmacistId){
                    for(WorkdayPharmacist wd : p.getWorkdays()){
                        for(Consultation c : wd.getConsultations()){
                            if(c.getPeriod().getStart_date().isAfter(LocalDateTime.now())){
                                return null;
                            }
                        }
                    }
                    pharmacists.remove(p);
                    break;
                }
            }
            pharmacy.setPharmacists(pharmacists);
            pharmacyRepository.save(pharmacy);
            for(Pharmacist p : pharmacy.getPharmacists()){
                pharmacistDTOS.add(new PharmacistDTO(p));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return pharmacistDTOS;
    }

    @Override
    public List<PharmacistDTO> searchPharmacistInPharmacy(String input, int pharmacyId) {
        List<PharmacistDTO> pharmacistDTOS = new ArrayList<>();
        try{
            Pharmacy pharmacy = pharmacyRepository.findOneById(pharmacyId);
            List<Pharmacist> pharmacists = pharmacy.getPharmacists();
            System.out.println(pharmacists.get(0).getFullName());
            for( Pharmacist p : pharmacists){
                if(p.getUser().getFullName().toLowerCase().contains(input.toLowerCase())){
                    pharmacistDTOS.add(new PharmacistDTO(p));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return pharmacistDTOS;
    }

    @Override
    public List<DermatologistDTO> addDermatologistInPharmacy(int dermatologistId, int pharmacyId, WorkingHoursDTO workingHoursDTO) {
        List<DermatologistDTO> dermatologistDTOS = new ArrayList<>();
        try{
            LocalTime start = workingHoursDTO.getStartTime().toLocalTime();
            LocalTime end = workingHoursDTO.getEndTime().toLocalTime();
            Pharmacy pharmacy = pharmacyRepository.findOneById(pharmacyId);
            WorkingHours workingHours = workingHoursService.saveWorkingHours(new WorkingHours(start,end,pharmacy));
            Dermatologist dermatologist = dermatologistService.getById(dermatologistId);
            dermatologist.getWorkingHours().add(workingHours);
            dermatologistService.save(dermatologist);
            pharmacy.getDermatologist().add(dermatologist);
            pharmacyRepository.save(pharmacy);

            for(Dermatologist d : pharmacy.getDermatologist()){
                dermatologistDTOS.add(new DermatologistDTO(d));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dermatologistDTOS;
    }

    @Override
    public List<DermatologistDTO> removeDermatologistInPharmacy(int dermatologistId, int pharmacyId) {
        List<DermatologistDTO> dermatologistDTOS = new ArrayList<>();
        try{
            Dermatologist dermatologist = dermatologistService.getById(dermatologistId);
            for(WorkdayDermatologist wd : dermatologist.getWorkdays()){
                for(Examination e : wd.getExaminations()){
                    if (e.getDate().getStart_date().isAfter(LocalDateTime.now())){
                        return null;
                    }
                }
            }
            Pharmacy pharmacy = pharmacyRepository.findOneById(pharmacyId);
            pharmacy.getDermatologist().remove(dermatologist);
            pharmacyRepository.save(pharmacy);

            for(Dermatologist d : pharmacy.getDermatologist()){
                dermatologistDTOS.add(new DermatologistDTO(d));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dermatologistDTOS;
    }

    @Override
    public List<DermatologistDTO> searchDermatologistInPharmacy(String input, int pharmacyId) {
        List<DermatologistDTO> dermatologistDTOS = new ArrayList<>();
        try{
            Pharmacy pharmacy = pharmacyRepository.findOneById(pharmacyId);
            for(Dermatologist d : pharmacy.getDermatologist()){
                if(d.getUser().getFullName().toLowerCase().contains(input.toLowerCase())){
                    dermatologistDTOS.add(new DermatologistDTO(d));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dermatologistDTOS;
    }

    @Override
    public List<DermatologistDTO> getDermatologistDifference(int pharmacyId) {
        List<DermatologistDTO> dermatologistDTOS = new ArrayList<>();
        try{
            List<Dermatologist> dermatologists = dermatologistService.findAll();
            Pharmacy p = pharmacyRepository.findOneById(pharmacyId);
            List<Dermatologist> dermatologists1 = p.getDermatologist();
            for(Dermatologist d : dermatologists){
                boolean hasSame = false;
                for(Dermatologist d2 : dermatologists1){
                    if(d.getId() == d2.getId()){
                        hasSame = true;
                    }
                }
                if(!hasSame){
                    dermatologistDTOS.add(new DermatologistDTO(d));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return  dermatologistDTOS;
    }

    @Override
    public List<WorkingHoursResponseDTO> getDermatologistShift(DermatologistFreePeriodsRequestDTO dto) {
        List<WorkingHoursResponseDTO> workingHoursResponseDTOS = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.parse(dto.getDate().substring(0,19));
        WorkingHours workingHours = new WorkingHours();
        try{
            Pharmacy p = pharmacyRepository.findOneById(dto.getPharmacyId());
            for( Dermatologist d : p.getDermatologist()){
                if(dto.getDermatologistId() == d.getId()){
                    for(Vacation v : d.getVacation()){
                        if(ChronoLocalDate.from(v.getStart_date()).isBefore(ChronoLocalDate.from(dateTime)) && ChronoLocalDate.from(v.getEnd_date()).isAfter(ChronoLocalDate.from(dateTime)) || ChronoLocalDate.from(v.getStart_date()).isEqual(ChronoLocalDate.from(dateTime)) || ChronoLocalDate.from(v.getEnd_date()).isEqual(ChronoLocalDate.from(dateTime))){
                            return null;
                        }
                    }

                    for(WorkingHours w : d.getWorkingHours()){
                        if(w.getPharmacy().getId() == dto.getPharmacyId()){
                            workingHours = w;
                        }
                    }

                    for(WorkdayDermatologist wd : d.getWorkdays()){
                        if(wd.getDate().isEqual(ChronoLocalDate.from(dateTime))){
                            LocalTime end = workingHours.getStartTime().plusMinutes(30);
                            LocalTime start = workingHours.getStartTime();
                            while(workingHours.getEndTime().isAfter(end)){
                                boolean hasSame = false;
                                for(Examination e : wd.getExaminations()){
                                    Period period = e.getDate();
                                    if((LocalTime.of(period.getStart_date().getHour(),period.getStart_date().getMinute()).isAfter(start) &&
                                            LocalTime.of(period.getStart_date().getHour(),period.getStart_date().getMinute()).isBefore(end)) ||
                                            (LocalTime.of(period.getEnd_date().getHour(),period.getEnd_date().getMinute()).isAfter(start) &&
                                            LocalTime.of(period.getEnd_date().getHour(),period.getEnd_date().getMinute()).isBefore(end)) ||
                                            LocalTime.of(period.getStart_date().getHour(),period.getStart_date().getMinute()).compareTo(start) == 0 ||
                                            LocalTime.of(period.getEnd_date().getHour(),period.getEnd_date().getMinute()).compareTo(end) == 0
                                    ){
                                        hasSame = true;
                                    }
                                }
                                if(!hasSame) {
                                    workingHoursResponseDTOS.add(new WorkingHoursResponseDTO(start, end));
                                }
                                start = start.plusMinutes(30);
                                end = end.plusMinutes(30);
                            }
                            return workingHoursResponseDTOS;
                            }
                        }
                    }
                }
            }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void makeReservation(NewExaminationDTO newExaminationDTO) {
        try{
            Pharmacy p = pharmacyRepository.findOneById(newExaminationDTO.getPharmacyId());
            List<Dermatologist> dermatologists = p.getDermatologist();
            LocalDateTime date = LocalDateTime.parse(newExaminationDTO.getDate().substring(0,19));
            LocalDate localDate = date.toLocalDate();
            for(Dermatologist d : dermatologists) {
                if(d.getId() == newExaminationDTO.getDermatologistId()){
                    boolean hasWorkday = false;
                    for(WorkdayDermatologist workdayDermatologist : d.getWorkdays()){
                        if(localDate.compareTo(workdayDermatologist.getDate()) == 0){
                            hasWorkday = true;
                            String start = newExaminationDTO.getDate() + 'T' + newExaminationDTO.getStart();
                            LocalDateTime dateTimeStart = LocalDateTime.parse(start.substring(0,10) + start.substring(24) + ":00");
                            String end = newExaminationDTO.getDate() + 'T' + newExaminationDTO.getEnd();
                            LocalDateTime dateTimeEnd = LocalDateTime.parse(end.substring(0,10) + end.substring(24) + ":00");
                            Period period = new Period(dateTimeStart,dateTimeEnd);
                            WorkdayDermatologist wd = workdayDermatologistService.findById(workdayDermatologist.getId());
                            wd.getExaminations().add(new Examination(period, newExaminationDTO.getPrice(),d,p));
                            workdayDermatologistService.save(wd);
                        }
                    }
                    if(!hasWorkday){
                        // pravimo workday
                    }
                }
            }

            }catch(Exception e){
                e.printStackTrace();
        }
    }

    @Override
    public void save(Pharmacy pharmacy) {
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public List<MedicineQuantityDTO> search(String input, int pharmacyId) {
        List<MedicineQuantityDTO> medicineDTOS = new ArrayList<>();
        try{
            Pharmacy p = pharmacyRepository.findOneById(pharmacyId);
            for( MedicineQuantity medicineQuantity : p.getMedicines()){
                if(medicineQuantity.getMedicine().getName().contains(input)) {
                    medicineDTOS.add(new MedicineQuantityDTO(medicineQuantity));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return medicineDTOS;
    }


}
