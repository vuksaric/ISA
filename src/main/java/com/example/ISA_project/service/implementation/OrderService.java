package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.AcceptOfferDTO;
import com.example.ISA_project.model.dto.MedicineOrderDTO;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.repository.OrderRepository;
import com.example.ISA_project.service.IOrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final MedicineQuantityService medicineQuantityService;
    private final MedicineService medicineService;
    private final UserService userService;
    private final EmailService emailService;
    private final PharmacyService pharmacyService;

    public OrderService(OrderRepository orderRepository, MedicineQuantityService medicineQuantityService, MedicineService medicineService, UserService userService, EmailService emailService, PharmacyService pharmacyService) {
        this.orderRepository = orderRepository;
        this.medicineQuantityService = medicineQuantityService;
        this.medicineService = medicineService;
        this.userService = userService;
        this.emailService = emailService;
        this.pharmacyService = pharmacyService;
    }

    @Override
    public void newOrder(MedicineOrderDTO order) {
        try{
            Medicine medicine = medicineService.findOneById(order.getMedicineId());
            MedicineQuantity medicineQuantity = new MedicineQuantity(medicine,order.getMedicineQuantity());
            LocalDateTime dateTime = LocalDateTime.parse(order.getDueDate().substring(0,19));
            OrderList orderList = new OrderList();
            orderList.setAdminId(1);
            orderList.setDueDate(dateTime);
            orderList.setMedicine(medicineQuantity);
            orderList.setStatus(OfferStatus.Waiting);
            orderList.setPharmacyId(order.getPharmacyId());
            orderRepository.save(orderList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        try{
            OrderList orderList = orderRepository.findById(id);
            if(orderList.getOffers().size() == 0){
                orderList.setStatus(OfferStatus.Rejected);
                orderRepository.save(orderList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<MedicineOrderDTO> getAll(int id) {
        List<MedicineOrderDTO> retVal = new ArrayList<>();
        try{
            for(OrderList ol : orderRepository.findAll()){
                if(ol.getPharmacyId() == id && ol.getStatus() == OfferStatus.Waiting){
                    retVal.add(new MedicineOrderDTO(ol));
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void acceptOffer(AcceptOfferDTO acceptOfferDTO) {
        try{
            OrderList orderList = orderRepository.findById(acceptOfferDTO.getOrderListId());
            Medicine m = orderList.getMedicine().getMedicine();
            for(Offer o : orderList.getOffers()){
                if(o.getId() == acceptOfferDTO.getOfferId()){
                    ProfileDTO profileDTO = userService.getProfile(o.getSupplier().getId());
                    emailService.acceptOffer(profileDTO,orderList);
                }else{
                    ProfileDTO profileDTO = userService.getProfile(o.getSupplier().getId());
                    emailService.rejectOffer(profileDTO,orderList);
                }
            }
            medicineQuantityService.addMedicineQuantity(m.getId(),acceptOfferDTO.getPharmacyId(),orderList.getMedicine().getQuantity());
            orderList.getOffers().clear();
            orderList.setPharmacyId(0);
            orderList.setStatus(OfferStatus.Accepted);
            orderRepository.save(orderList);
            orderRepository.delete(orderList);

        }catch(Exception e){

        }


    }
}
