import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ConsultationService } from 'src/app/services/consultation.service';
import { PharmacistService } from 'src/app/services/pharmacist.service';
import { differenceInCalendarDays, setHours } from 'date-fns';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-new-consultation-pharmacist',
  templateUrl: './new-consultation-pharmacist.component.html',
  styleUrls: ['./new-consultation-pharmacist.component.css']
})
export class NewConsultationPharmacistComponent implements OnInit {
  date = null;
  period : any;
  id : String;
  periods : any[];
  body : any;
  validateForm: FormGroup;
  vacationBody : any;
  check : boolean;
  today = new Date();
  dataToken : any;
  idPharmacist : number;
  disabledDate = (current: Date): boolean => {
    // Can not select days before today and today
    return differenceInCalendarDays(current, this.today) <= 0;
  };
  constructor(private authorizationService : AuthService, private router: Router,private activatedRoute: ActivatedRoute, private fb: FormBuilder, private consultationService: ConsultationService, 
    private pharmacistService : PharmacistService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.dataToken = this.authorizationService.getDataFromToken();
    this.idPharmacist = this.dataToken.id;
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
    this.validateForm = this.fb.group({
      date: ['', [Validators.required]],
      period: ['', [Validators.required]],
    })
  }


  onChange(result: Date): void {
    console.log('onChange: ', result);
    this.body = {
      id : this.id,
      date : this.date
    }

    
    this.vacationBody = {

      id : this.idPharmacist,
      date : this.date,
      pharmacyId : 1
    }
    if(result != null)
    {
      this.pharmacistService.checkVacation(this.vacationBody).subscribe(data => { console.log(data);
          this.check = data;
          console.log("Check : " + this.check);
          if(this.check)
          {
            this.consultationService.getFreePeriods(this.body).subscribe(data => { console.log(data); 
        
              if(data.length != 0)
              {
                this.periods = data;
              }
              else
              {
                this.toastr.warning("There are no free terms for this date, please choose another");
              }
                
            });
          }
          else
          {
            this.toastr.warning("You are on vacation that day");
          }
      });
    }
    
  }

  submit()
  {
    this.body = {
      period : this.period,
      consultationId : this.id
    }


    for (const key in this.validateForm.controls) {
      this.validateForm.controls[key].markAsDirty();
      this.validateForm.controls[key].updateValueAndValidity();
    }

    if(this.validateForm.valid)
    {


      this.consultationService.newPharmacist(this.body).subscribe(data => { console.log(data); 
      });
      this.toastr.success("You have scheduled a follow up a consultation!");
      this.router.navigate(['homePagePharmacist']);
    }
    else
    {
      this.toastr.warning("All fields must be filled");
    }
  }


}
