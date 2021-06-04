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

  constructor(private modal: NzModalService, private toastr: ToastrService, private fb: FormBuilder, private medicineService : MedicineService,private orderService: OrderService) { }

  ngOnInit(): void {

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
    this.orderService.deleteOrder(data.id).subscribe(result =>{
      this.toastr.success("Succesfully deleted offer");
      this.getAllOrders();
    })
  }

  getAllOrders(){
    this.orderService.getAll().subscribe(result => {
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
    this.isVisible = false;
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

  listOfData: Order[] = [
    {
      medicine: '32',
      quantity: 'New York No. 1 Lake Park',
      dueDate: 'proba'
    },
    {
      medicine: '32',
      quantity: 'New York No. 1 Lake Park',
      dueDate: 'proba'
    },
    {
      medicine: '32',
      quantity: 'New York No. 1 Lake Park',
      dueDate: 'proba'
    }
  ];

}
