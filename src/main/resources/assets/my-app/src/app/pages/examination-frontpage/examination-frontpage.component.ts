import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ExaminationService } from 'src/app/services/examination.service';

@Component({
  selector: 'app-examination-frontpage',
  templateUrl: './examination-frontpage.component.html',
  styleUrls: ['./examination-frontpage.component.css']
})
export class ExaminationFrontpageComponent implements OnInit {

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private examinationService : ExaminationService) { }
  id : String;

  ngOnInit(): void {
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
  }

  start() : void{
    this.router.navigate(['examinationReport/' + this.id]);
  }

  return() : void{
    this.router.navigate(['workScheduleDermatologist']);
  }

  point()
  {
    this.examinationService.addPoint(this.id).subscribe(data => { console.log("Uspesno"); 

    });
  }
}
