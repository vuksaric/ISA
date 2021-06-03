import { VacationRequestService } from 'src/app/services/vacation-request.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { NzModalService } from 'ng-zorro-antd/modal';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { VacationRequest } from 'src/app/models/VacationRequest';

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
  vacationRequests: VacationRequest[] = [];
  request: VacationRequest;
  dataModal: any;

  constructor(private toastr: ToastrService, private modal: NzModalService, private fb: FormBuilder, private vacationService: VacationRequestService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      text: [null, [Validators.required]],
    });

    this.getAllRequests();

  }

  getAllRequests() {
    this.vacationService.getAllRequests().subscribe(data => {
      if (data === null) {
        this.toastr.error("There is no vacation requests");
      }
      else {
        data.forEach(function (request) {
          //request.start_date = request.start_date.split(' ')[0];
          //request.end_date = request.end_date.split(' ')[0];
        })
        this.vacationRequests = data;
      }
    })
  }

  showModal(data): void {
    this.isVisible = true;
    this.dataModal = data;
  }

  handleOk(): void {
    if (this.validateForm.valid) {
      const body = {
        requestId: this.dataModal.id,
        start_date: this.dataModal.start_date,
        end_date: this.dataModal.end_date,
        pharmacy_id: this.dataModal.pharmacy_id,
        user_id: this.dataModal.user_id,
        user_type: this.dataModal.user_type,
        rejectionNote: this.validateForm.get('text').value
      }
      this.vacationService.rejectVacation(body).subscribe(data => {
        this.toastr.success("Successfully rejected vacation");
        this.isVisible = false;
        this.getAllRequests();
      }
      );
    }
  }

  approve(data) {
    console.log(data);
    const body = {
      requestId: data.id,
      start_date: data.start_date,
      end_date: data.end_date,
      pharmacy_id: data.pharmacy_id,
      user_id: data.user_id,
      user_type: data.user_type
    }
    this.vacationService.approveVacation(body).subscribe(data => {
      this.toastr.success("Successfully approved vacation");
      this.getAllRequests();
    });
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
