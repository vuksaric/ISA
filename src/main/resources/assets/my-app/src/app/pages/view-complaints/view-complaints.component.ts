import { Component, OnInit } from '@angular/core';
import { ComplaintService } from 'src/app/services/complaint.service';
import { ToastrService } from 'ngx-toastr';
import jwt_decode from 'jwt-decode';
import { Complaint } from 'src/app/models/complaint';

@Component({
  selector: 'app-view-complaints',
  templateUrl: './view-complaints.component.html',
  styleUrls: ['./view-complaints.component.css']
})
export class ViewComplaintsComponent implements OnInit {

  listOfComplaints = [];
  complaint: any;
  complaint_answer: string;
  //listOfPharmacist = [];
  //listOfPharmacies = [];
  //complaint: any;
  //complaint_text: string;
  token: any;
  isVisible = false;

  
  constructor(private complaintService: ComplaintService) { }

  ngOnInit(): void {
    this.complaintService.getAll().subscribe(data=>{this.listOfComplaints=data; console.log(data)});
  }

  complaintVis(item){
      this.isVisible = true;
  }

  isDisabled(item): Boolean{
      if(item.answer != null){
        return true;
      }else return false;
  }

  handleOk(item): void {
    console.log('Button ok clicked!');
    const body = {
      id: item.id,
      objectId : item.idObject,
      patientId : item.idPatient,
      answer : this.complaint_answer,

    }
    console.log(body);
    this.complaintService.editComplaint(body).subscribe(data => {console.log(data);});
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
