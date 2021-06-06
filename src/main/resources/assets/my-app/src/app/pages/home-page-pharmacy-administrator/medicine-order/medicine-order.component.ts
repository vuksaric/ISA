import { AuthService } from 'src/app/services/auth.service';
import { OrderService } from './../../../services/order.service';
import { MedicineService } from 'src/app/services/medicine.service';
import { Component, OnInit } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { differenceInCalendarDays } from 'date-fns';
import { getISOWeek } from 'date-fns';

interface Order {
  medicine: string;
  quantity: string;
  dueDate: string;
}

@Component({
  selector: 'app-medicine-order',
  templateUrl: './medicine-order.component.html',
  styleUrls: ['./medicine-order.component.css']
})
export class MedicineOrderComponent implements OnInit {

  isVisible = false;
  isVisible2 = false;
  selectedValue = null;
  medicineList : any[] = [];
  date = null;
  validateForm : FormGroup;
  today = new Date();
  listOfOrders : any[] = [];
  listOfOffers : any[] = [];
  currentOrder : any;
  pharmacyId : any;

  constructor(private modal: NzModalService, private toastr: ToastrService, private fb: FormBuilder, private medicineService : MedicineService,private orderService: OrderService, private authService : AuthService) { }

  ngOnInit(): void {
    
    let token = this.authService.getDataFromToken();
    this.pharmacyId = token.pharmacyId.toString(); 

    this.getAllOrders();

    this.medicineService.getAll().subscribe(result => {
      this.medicineList = result;
    })

    this.validateForm = this.fb.group({
      medicine: [null, [Validators.required]],
      quantity: [null, [Validators.required]],
      dueDate: [null, [Validators.required]],
    });
  }

  deleteOrder(data : any){
    this.orderService.deleteOrder(data.id,).subscribe(result =>{
      this.toastr.success("Succesfully deleted offer");
      this.getAllOrders();
    })
  }

  acceptOffer(data){
    console.log(data);
    const body = {
      orderListId:data.id,
      offerId: this.currentOrder.id,
      pharmacyId: 1,
      adminId : this.authService.getDataFromToken().id
    }
    console.log(body);
    this.orderService.acceptOffer(body).subscribe(result => {
      this.isVisible2 = false;
      this.getAllOrders();
    })
  }

  getAllOrders(){
    this.orderService.getAll(this.pharmacyId).subscribe(result => {
      for(var element of result){
        element.dueDate = element.dueDate.toString().split("T",2)[0];
      }
      this.listOfOrders = result;
    })
  }

  onChange(result: Date): void {
    console.log('onChange: ', result);
  }

  getWeek(result: Date): void {
    console.log('week: ', getISOWeek(result));
  }

  showModal(): void {
    this.isVisible = true;
  }

  showModal2(data : any): void {
    for(var element of data.offers){
      element.dueDate = element.dueDate.toString().split("T",2)[0];
    }
    this.currentOrder = data;
    console.log(this.currentOrder);
    this.isVisible2 = true;
    this.listOfOffers = data.offers;
  }

  handleOk2(): void {
    this.isVisible2 = false;
  }

  handleCancel2(): void {
    this.isVisible2 = false;
  }

  handleOk(): void {
    if(this.validateForm.valid){
      const body = {
        pharmacyId : this.pharmacyId,
        medicineId : this.validateForm.get('medicine').value,
        medicineQuantity : this.validateForm.get('quantity').value,
        dueDate : this.validateForm.get('dueDate').value.toISOString(),
        adminId : this.authService.getDataFromToken().id
      }
      this.orderService.newOrder(body).subscribe(data => {
        this.isVisible = false;
        this.getAllOrders();
        this.toastr.success("successfully ordered medicine");
      })
    }else{
      this.toastr.error("Input fields can not be empty");
    }
    
    
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  disabledDate = (current: Date): boolean => {
    return differenceInCalendarDays(current, this.today) < 0;
  };

  updateMedicineValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.medicine.updateValueAndValidity());
  }

  updateQuantityValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.quantity.updateValueAndValidity());
  }

  updateDueDateValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.dueDate.updateValueAndValidity());
  }

}
