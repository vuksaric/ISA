import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  
  review : any;
  mark: number;
  
  constructor(private patientChartService : PatientChartService, private reviewService: ReviewService) { }

  ngOnInit(): void {
    this.patientChartService.getPatientDoctors(1).subscribe(data=>{this.listOfDoctors=data; console.log(data)});
    this.patientChartService.getPatientPharmacist(1).subscribe(data=>{this.listOfPharmacist=data; console.log(data)});
    this.patientChartService.getPatientMedicine(1).subscribe(data=>{this.listOfMedicine=data; console.log(data)});
    this.patientChartService.getPatientPharmacy(1).subscribe(data=>{this.listOfPharmacy=data; console.log(data)})
  }

  viewGradeDoctor(item){
    this.isVisible = true;
    this.reviewService.findReview(1, item.idObject, 'Dermatologist').subscribe(data=>{
      this.review=data;
      console.log(data);
     this.mark = data.mark;
    });
  }
  viewGradePharmacist(item){
    this.isVisible = true;
    this.reviewService.findReview(1, item.idObject, 'Pharmacist').subscribe(data=>{
      this.review=data;
      console.log(data);
     this.mark = data.mark;
    });
  }
  viewGradeMedicine(item){
    this.isVisible = true;
    this.reviewService.findReview(1, item.idObject, 'Medicine').subscribe(data=>{
      this.review=data;
      console.log(data);
     this.mark = data.mark;
    });
  }
  viewGradePharmacy(item){
    this.isVisible = true;
    this.reviewService.findReview(1, item.idObject, 'Pharmacy').subscribe(data=>{
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
      this.reviewService.saveReview(body).subscribe(data=>{console.log(data); });
    }
    else{
      this.reviewService.editReview(body).subscribe(data=>{console.log(data); });
    }
    this.isVisible = false;
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }

}
