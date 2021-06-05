import { Component, OnInit } from '@angular/core';
import { PatientChartService } from 'src/app/services/patient-chart.service';
import { ToastrService } from 'ngx-toastr';
import jwt_decode from 'jwt-decode';
import { ComplaintService } from 'src/app/services/complaint.service';
import { isTemplateRef } from 'ng-zorro-antd/core/util';

@Component({
  selector: 'app-new-complaint',
  templateUrl: './new-complaint.component.html',
  styleUrls: ['./new-complaint.component.css']
})
export class NewComplaintComponent implements OnInit {

  listOfDermatologist = [];
  listOfPharmacist = [];
  listOfPharmacies = [];
  complaint: any;
  complaint_text: string;
  token: any;
  isVisible = false;

  constructor(private patientChartService : PatientChartService, private complaintService: ComplaintService) { }

  ngOnInit(): void {
    this.token = this.getDecodedAccessToken(localStorage.getItem('token'));
    this.patientChartService.getPatientDoctors(this.token.user_id).subscribe(data=>{this.listOfDermatologist=data; console.log(data)});
    this.patientChartService.getPatientPharmacist(this.token.user_id).subscribe(data=>{this.listOfPharmacist=data; console.log(data)});
    this.patientChartService.getPatientPharmacy(this.token.user_id).subscribe(data=>{this.listOfPharmacies=data; console.log(data)})
  }

  complaintVis(item){
      this.isVisible = true;
  }

  handleOkDerm(item): void {
    console.log('Button ok clicked!');
    const body = {
      text : this.complaint_text,
      objectId : item.idObject,
      patientId : this.token.user_id,
      type : 'Dermatologist'
    }
    console.log(body);
    this.complaintService.newComplaint(body).subscribe(data => {console.log(data);});
    this.isVisible = false;
  }

  handleOkPharm(item): void {
    console.log('Button ok clicked!');
    const body = {
      text : this.complaint_text,
      objectId : item.idObject,
      patientId : this.token.user_id,
      type : 'Pharmacist'
    }
    console.log(body);
    this.complaintService.newComplaint(body).subscribe(data => {console.log(data);});
    this.isVisible = false;
  }

  handleOkPharmy(item): void {
    console.log('Button ok clicked!');
    const body = {
      text : this.complaint_text,
      objectId : item.idObject,
      patientId : this.token.user_id,
      type : 'Pharmacy'
    }
    console.log(body);
    this.complaintService.newComplaint(body).subscribe(data => {console.log(data);});
    this.isVisible = false;
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    }
    catch (Error) {
      return null;
    }
  }

}
