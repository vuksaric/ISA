import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConsultationService } from 'src/app/services/consultation.service';

@Component({
  selector: 'app-consultation-frontpage',
  templateUrl: './consultation-frontpage.component.html',
  styleUrls: ['./consultation-frontpage.component.css']
})
export class ConsultationFrontpageComponent implements OnInit {

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private consultationService : ConsultationService) { }
  id : String;

  ngOnInit(): void {
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
  }

  start() : void{
    this.router.navigate(['consultationReport/' + this.id]);
  }

  return() : void{
    this.router.navigate(['workSchedule']);
  }

  point()
  {
    this.consultationService.addPoint(this.id).subscribe(data => { console.log("Uspesno"); 

    });
  }

}
