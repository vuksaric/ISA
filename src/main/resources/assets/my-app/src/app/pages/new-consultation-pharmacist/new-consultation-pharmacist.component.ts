import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ConsultationService } from 'src/app/services/consultation.service';

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
  constructor(private router: Router,private activatedRoute: ActivatedRoute, private fb: FormBuilder, private consultationService: ConsultationService) { }

  ngOnInit(): void {
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

    this.consultationService.getFreePeriods(this.body).subscribe(data => { console.log(data); 
      
      if(data.length != 0)
      {
        this.periods = data;
      }
      else
      {
        alert("There are no free terms for this date, please choose another");
      }
        
    });
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
      alert("You have scheduled a follow up a consultation!");
      this.router.navigate(['homePagePharmacist']);
    }
    else
    {
      alert("All fields must be filled");
    }
  }


}
