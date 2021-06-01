import { VacationRequestService } from 'src/app/services/vacation-request.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { NzModalService } from 'ng-zorro-antd/modal';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

interface Vacation {
  name: string;
  surname: string;
  type: string;
  start: string;
  end: string;
}

@Component({
  selector: 'app-vacation-approval',
  templateUrl: './vacation-approval.component.html',
  styleUrls: ['./vacation-approval.component.css']
})
export class VacationApprovalComponent implements OnInit {

  validateForm!: FormGroup;
  isVisible = false;

  constructor(private toastr: ToastrService, private modal: NzModalService, private fb: FormBuilder, private vacationService: VacationRequestService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      text: [null, [Validators.required]],
    });
  }

  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    if (this.validateForm.valid) {
      const body = {

      }
      this.vacationService.rejectVacation(body);
      this.toastr.success("Successfully rejected vacation");
      this.isVisible = false;
    }
  }

  approve(data) {
    console.log(data);
    const body = {

    }
    this.vacationService.approveVacation(body);
    this.toastr.success("Successfully approved vacation");
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  updateTextValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.text.updateValueAndValidity());
  }

  listOfData: Vacation[] = [
    {
      name: 'Vuk',
      surname: 'Saric',
      type: 'Dermatologist',
      start: '32',
      end: 'New York No. 1 Lake Park',
    },
    {
      name: 'Vuk',
      surname: 'Saric',
      type: 'Dermatologist',
      start: '32',
      end: 'New York No. 1 Lake Park',
    },
    {
      name: 'Vuk',
      surname: 'Saric',
      type: 'Dermatologist',
      start: '32',
      end: 'New York No. 1 Lake Park',
    }
  ];

}
