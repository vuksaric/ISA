package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.PricelistMedicine;
import com.example.ISA_project.model.dto.PriceListMedicineDTO;
import com.example.ISA_project.repository.PriceListMedicineRepository;
import com.example.ISA_project.service.IPriceListMedicineService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PriceListMedicineService implements IPriceListMedicineService {
    private final PriceListMedicineRepository priceListMedicineRepository;

    public PriceListMedicineService(PriceListMedicineRepository priceListMedicineRepository){
        this.priceListMedicineRepository = priceListMedicineRepository;
    }
    @Override
    public PricelistMedicine find(int medicineId, int pharmacyId)
    {
        return priceListMedicineRepository.find(medicineId, pharmacyId);
    }

    public List<PriceListMedicineDTO> getAll(int pharmacyId){
        List<PriceListMedicineDTO> priceListMedicineDTOS = new ArrayList<>();
        try{
            List<PricelistMedicine> pricelistMedicines = priceListMedicineRepository.findAll();
            for(PricelistMedicine p : pricelistMedicines){
                if(p.getPharmacy().getId() == pharmacyId){
                    priceListMedicineDTOS.add(new PriceListMedicineDTO(p));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return priceListMedicineDTOS;
    }

    @Override
    public void edit(PriceListMedicineDTO priceListMedicineDTO) {
        try{
            List<PricelistMedicine> pricelistMedicines = priceListMedicineRepository.findAll();
            for(PricelistMedicine pm : pricelistMedicines){
                if(priceListMedicineDTO.getId() == pm.getId()){
                    pm.setPrice(priceListMedicineDTO.getPrice());
                    pm.getValidity().setStart_date(LocalDateTime.now());
                    pm.getValidity().setEnd_date(LocalDateTime.now().plusDays(Integer.parseInt(priceListMedicineDTO.getDuration())));
                    priceListMedicineRepository.save(pm);
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
