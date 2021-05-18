import { Component, OnInit } from '@angular/core';
import { WorkdayPharmacist } from 'src/app/models/workday-pharmacist';
import { PharmacistService } from 'src/app/services/pharmacist.service';

@Component({
  selector: 'app-work-schedule',
  templateUrl: './work-schedule.component.html',
  styleUrls: ['./work-schedule.component.css']
})
export class WorkScheduleComponent implements OnInit {

  workdays = [] as unknown as WorkdayPharmacist;

  constructor(private pharmacistService: PharmacistService ) { }

  ngOnInit(): void {

    this.pharmacistService.getWorkdays(1).subscribe(data => { console.log(data); 
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

  ispis(consultation) : string
  {
      //console.log(consultation);
      return consultation.start[3]+":" + consultation.start[4] + '-' + consultation.end[3]+":" + consultation.end[4] + ' ' + consultation.pharmacyName;
  }

  ispismesec(consultation) : string
  {
    return consultation.start[2]+ ": " + consultation.start[3]+":" + consultation.start[4] + '-' + consultation.end[3]+":" + consultation.end[4] + ' ' + consultation.pharmacyName;
  }

  getEvents(index, event) {
    return event;
  }

}
