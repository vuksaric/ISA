import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DermatologistService } from 'src/app/services/dermatologist.service';
import { WorkdayDermatologistService } from 'src/app/services/workday-dermatologist.service';
import { WorkdayPharmacistService } from 'src/app/services/workday-pharmacist.service';

@Component({
  selector: 'app-work-schedule-dermatologist',
  templateUrl: './work-schedule-dermatologist.component.html',
  styleUrls: ['./work-schedule-dermatologist.component.css']
})
export class WorkScheduleDermatologistComponent implements OnInit {

  workdays = [] as any;
  result : String;
  selectedValue : any;
  consultations = [] as any;
  visible = false;
  displayData = [] as any;

  constructor(private dermatologistService: DermatologistService, private workdayDermatologistService : WorkdayDermatologistService, private router: Router ) { }

  ngOnInit(): void {

    this.dermatologistService.getWorkdays(1).subscribe(data => { console.log(data); 
      this.workdays = data;
    });
  
  }

  compareDate(currentDate, date) : boolean{

    //console.log("Trenutna godina " + currentDate.getFullYear() + " Trenutni dan " + currentDate.getDate() + " Trenutni mesec je " + currentDate.getMonth() +  " Godina " + date[0] + " Datum " + date[2])
    return currentDate.getFullYear() == date[0] && currentDate.getMonth()+1 == date[1] && currentDate.getDate() == date[2]
  }

  compareMonth(currentMonth, date)
  {
    //console.log("Trenutni mesec je " + currentMonth.getMonth() + "Mesec " + date[1]);
    return currentMonth.getFullYear() == date[0] && currentMonth.getMonth()+1 == date[1] ;
  }

  ispis(consultation) : String
  {
      //console.log(consultation);
      this.result = consultation.start[3]+":" + consultation.start[4] + '-' + consultation.end[3]+":" + consultation.end[4] +  ' ' + consultation.fullName + ', ' + consultation.pharmacyName;

      return this.result;
  }

  ispisMesec(consultation) : String
  {
      //console.log(consultation);
      this.result =  consultation.start[2]+ ": " + consultation.start[3]+":" + consultation.start[4] + '-' + consultation.end[3]+":" + consultation.end[4] +  ' ' + consultation.fullName + ', ' + consultation.pharmacyName;

      return this.result;
  }

  selectChange(select: Date): void {
    this.workdays.forEach(element => {
      if(this.compareDate(select,element.date))
      {
        this.workdayDermatologistService.getExaminations(element.id).subscribe(data => { console.log(data); 
          this.consultations = data;
        });
        
      }
      else
      this.consultations = [];
    });

  }

  examination(id : number): void {
    this.router.navigate(['examinationFrontpage/' + id]);
  }

  viewProfile(id : number) : void
  {
    this.router.navigate(['patientProfileDoctor/' + id]);
  }
}
