import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ConsultationService } from 'src/app/services/consultation.service';

@Component({
  selector: 'app-new-consultation-pharmacist',
  templateUrl: './new-consultation-pharmacist.component.html',
  styleUrls: ['./new-consultation-pharmacist.component.css']
})
export class NewConsultationPharmacistComponent implements OnInit {
  date = null;
  time : Date;
  id : String;
  periods : any[];
  body : any;
  constructor(private activatedRoute: ActivatedRoute, private consultationService: ConsultationService) { }

  ngOnInit(): void {
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
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

}
