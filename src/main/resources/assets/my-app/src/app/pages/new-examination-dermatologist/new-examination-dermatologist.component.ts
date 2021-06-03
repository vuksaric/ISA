import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ExaminationService } from 'src/app/services/examination.service';

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
  validateForm: FormGroup;
  constructor(private router: Router,private activatedRoute: ActivatedRoute, private fb: FormBuilder, private examinationService: ExaminationService) { }

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

    this.examinationService.getFreePeriods(this.body).subscribe(data => { console.log(data); 
      
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
      this.examinationService.newDermatologist(this.body).subscribe(data => { console.log(data); 
      });
      alert("You have scheduled a follow up a examination!");
      this.router.navigate(['homePagePharmacist']);
    }
    else
    {
      alert("All fields must be filled");
    }
  }



}
