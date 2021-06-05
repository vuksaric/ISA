import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PharmacistService } from 'src/app/services/pharmacist.service';
import { VacationRequestService } from 'src/app/services/vacation-request.service';
import { differenceInCalendarDays, setHours } from 'date-fns';
import { PharmacyService } from 'src/app/services/pharmacy.service';
import { Pharmacy } from 'src/app/models/pharmacy';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-vacation-request',
  templateUrl: './vacation-request.component.html',
  styleUrls: ['./vacation-request.component.css']
})
export class VacationRequestComponent implements OnInit {


  validateForm: FormGroup;
  today = new Date();
  name : String;
  type : String;
  pharmacy_id : number;
  pharmacies : Pharmacy[];
  data: any
  dataToken : any;
  id : number;
  disabledStartDate = (current: Date): boolean => {
    // Can not select days before today and today
    return differenceInCalendarDays(current, this.today) <= 0;
  };

  disabledEndDate = (current: Date): boolean => {
    // Can not select days before today and today
    return differenceInCalendarDays(current, this.dateStart) > 0;
  };

  constructor(private router: Router,private vacationRequestService : VacationRequestService, private fb: FormBuilder, private pharmacyService : PharmacyService, private authorizationService : AuthService
    , private toastr: ToastrService) { 

  }

  ngOnInit(): void {
    this.dataToken = this.authorizationService.getDataFromToken();
    this.type = this.dataToken.type;
    this.id = this.dataToken.id;
    this.pharmacyService.getPharmaciesDermatologist(this.id).subscribe((pharmacies: Pharmacy[]) => {
      this.pharmacies = pharmacies;
      console.log(this.pharmacies)
    });
   
    if(this.type.toLowerCase() != "pharmacist")
    {
      this.validateForm = this.fb.group({
        dateStart: ['', [Validators.required]],
        dateEnd: ['', [Validators.required]],
        name: ['', [Validators.required]],
      })
    }
    else
    {
      this.validateForm = this.fb.group({
        dateStart: ['', [Validators.required]],
        dateEnd: ['', [Validators.required]],
      })
    }
  }

  dateStart = null;
  dateEnd = null;

  onChangeStart(result: Date): void {
    this.dateStart = result;
    console.log('onChange: ', result);

    this.disabledEndDate = (current: Date): boolean => {
      // Can not select days before today and today
      return differenceInCalendarDays(current, this.dateStart) < 0;
    };
  }

  onChangeEnd(result: Date): void {
    this.dateEnd = result;
    console.log('onChange: ', result);
  }



  submit() : void
  {

    for (const key in this.validateForm.controls) {
      this.validateForm.controls[key].markAsDirty();
      this.validateForm.controls[key].updateValueAndValidity();
    }

    const body = {
      start_date: this.dateStart,
      end_date: this.dateEnd,
      user_id: this.id,
      pharmacy_id : Number(this.pharmacy_id),
      user_type : this.type
    }

    console.log(body);
    if(this.validateForm.valid)
    {
      this.vacationRequestService.sendVacationRequest(body).subscribe(data=>{console.log("usao")});
      this.toastr.warning("You have successfully sent a vacation request!");
      this.router.navigate(['homePagePharmacist']);
    }

    else
    this.toastr.warning("All fields are required");
    
  }

  visibility() : boolean
  {
    if(this.type.toLowerCase() == "pharmacist")
    {
      return true;
    }
      
    else
      return false;
  }
 

}


