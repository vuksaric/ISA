import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';
import { PatientChartService } from 'src/app/services/patient-chart.service';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-view-reviews',
  templateUrl: './view-reviews.component.html',
  styleUrls: ['./view-reviews.component.css']
})
export class ViewReviewsComponent implements OnInit {
  listOfDoctors = [];
  listOfPharmacist=[];
  listOfMedicine=[];
  listOfPharmacy=[];
  isVisible = false;
  dataFromToken : any;
  
  review : any;
  mark: number;
  
  constructor(private patientChartService : PatientChartService, private reviewService: ReviewService, 
    private toastr: ToastrService, private authorizationService : AuthService) { }

  ngOnInit(): void {
    this.dataFromToken = this.authorizationService.getDataFromToken();

    this.patientChartService.getPatientDoctors(this.dataFromToken.id).subscribe(data=>{this.listOfDoctors=data; console.log(data)});
    this.patientChartService.getPatientPharmacist(this.dataFromToken.id).subscribe(data=>{this.listOfPharmacist=data; console.log(data)});
    this.patientChartService.getPatientMedicine(this.dataFromToken.id).subscribe(data=>{this.listOfMedicine=data; console.log(data)});
    this.patientChartService.getPatientPharmacy(this.dataFromToken.id).subscribe(data=>{this.listOfPharmacy=data; console.log(data)})
  }

  viewGradeDoctor(item){
    this.isVisible = true;
    this.reviewService.findReview(this.dataFromToken.id, item.idObject, 'Dermatologist').subscribe(data=>{
      this.review=data;
      console.log(data);
     this.mark = data.mark;
    });
  }
  viewGradePharmacist(item){
    this.isVisible = true;
    this.reviewService.findReview(this.dataFromToken.id, item.idObject, 'Pharmacist').subscribe(data=>{
      this.review=data;
      console.log(data);
     this.mark = data.mark;
    });
  }
  viewGradeMedicine(item){
    this.isVisible = true;
    this.reviewService.findReview(this.dataFromToken.id, item.idObject, 'Medicine').subscribe(data=>{
      this.review=data;
      console.log(data);
     this.mark = data.mark;
    });
  }
  viewGradePharmacy(item){
    this.isVisible = true;
    this.reviewService.findReview(this.dataFromToken.id, item.idObject, 'Pharmacy').subscribe(data=>{
      this.review=data;
      console.log(data);
     this.mark = data.mark;
    });
  }

  handleOk(): void {
    console.log('Button ok clicked!');
    const body = {
      id : this.review.id,
      mark : this.mark,
      objectId : this.review.objectId,
      patientId : this.review.patientId,
      reviewType : this.review.reviewType
    }
    console.log(body);
    if(!this.review.reviewed){
      this.reviewService.saveReview(body).subscribe(data=>{
        console.log(data);
        this.toastr.success("You have successfully made a review!"); 
      });
    }
    else{
      this.reviewService.editReview(body).subscribe(data=>{
        console.log(data);
        this.toastr.success("You have successfully edited your review!"); 
      });
    }
    this.isVisible = false;
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }

}
