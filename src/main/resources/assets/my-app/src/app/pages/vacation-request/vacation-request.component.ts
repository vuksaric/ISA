import { Component, OnInit } from '@angular/core';
import { PharmacistService } from 'src/app/services/pharmacist.service';
import { VacationRequestService } from 'src/app/services/vacation-request.service';

@Component({
  selector: 'app-vacation-request',
  templateUrl: './vacation-request.component.html',
  styleUrls: ['./vacation-request.component.css']
})
export class VacationRequestComponent implements OnInit {

  constructor(private vacationRequestService : VacationRequestService) { }

  ngOnInit(): void {
  }

  dateStart = null;
  dateEnd = null;

  onChangeStart(result: Date): void {
    this.dateStart = result;
    console.log('onChange: ', result);
  }

  onChangeEnd(result: Date): void {
    this.dateEnd = result;
    console.log('onChange: ', result);
  }

  submit() : void
  {
    const body = {
      start_date: this.dateStart,
      end_date: this.dateEnd,
      user_id: 1
    }

    console.log(body);
    this.vacationRequestService.sendVacationRequest(body).subscribe(data=>{console.log("usao")});
    
  }
 

}
