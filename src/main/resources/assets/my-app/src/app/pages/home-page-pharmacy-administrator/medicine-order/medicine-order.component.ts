import { Component, OnInit } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

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

  constructor(private modal: NzModalService, private toastr: ToastrService, private fb: FormBuilder) { }

  ngOnInit(): void {
  }

  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    this.isVisible = false;
  }

  handleCancel(): void {
    this.isVisible = false;
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
