import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ExaminationService } from 'src/app/services/examination.service';
import { differenceInCalendarDays, setHours } from 'date-fns';
import { DermatologistService } from 'src/app/services/dermatologist.service';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-new-examination-dermatologist',
  templateUrl: './new-examination-dermatologist.component.html',
  styleUrls: ['./new-examination-dermatologist.component.css']
})
export class NewExaminationDermatologistComponent implements OnInit {

  date = null;
  period : any;
  id : String;
  periods : any[];
  body : any;
  vacationBody : any;
  validateForm: FormGroup;
  today = new Date();
  check : boolean;
  dataToken : any;
  idDermatologist : number;
  disabledDate = (current: Date): boolean => {
    // Can not select days before today and today
    return differenceInCalendarDays(current, this.today) <= 0;
  };
  constructor(private authorizationService : AuthService,private router: Router,private activatedRoute: ActivatedRoute, private fb: FormBuilder, private examinationService: ExaminationService, 
    private dermatologistService : DermatologistService , private toastr: ToastrService) { }

  ngOnInit(): void {
    this.dataToken = this.authorizationService.getDataFromToken();
    this.idDermatologist = this.dataToken.id;
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

      id : this.idDermatologist,
      date : this.date,
      pharmacyId : this.id
    }
    if(result != null)
    {

      this.examinationService.checkVacation(this.vacationBody).subscribe(data => { console.log(data);
          this.check = data;
          console.log("Check : " + this.check);
          if(this.check)
          {
            this.examinationService.getFreePeriods(this.body).subscribe(data => { console.log(data); 
        
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
      this.examinationService.newDermatologist(this.body).subscribe(data => { console.log(data); 
      });
      this.toastr.success("You have scheduled a follow up a examination!");
      this.router.navigate(['homePageDermatologist']);
    }
    else
    {
      this.toastr.warning("All fields must be filled");
    }
  }



}
