import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';
import { ExaminationService } from 'src/app/services/examination.service';

@Component({
  selector: 'app-examination-frontpage',
  templateUrl: './examination-frontpage.component.html',
  styleUrls: ['./examination-frontpage.component.css']
})
export class ExaminationFrontpageComponent implements OnInit {

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private examinationService : ExaminationService, private authorizationService : AuthService, private toastr: ToastrService) { }
  id : String;

  ngOnInit(): void {
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
  }

  start() : void{
    this.router.navigate(['homePageDermatologist/examinationReport/' + this.id]);
  }

  return() : void{
    this.router.navigate(['homePageDermatologist/workScheduleDermatologist']);
  }

  point()
  {
    this.examinationService.addPoint(this.id).subscribe(data => { console.log("Uspesno"); 
    this.toastr.success("You have added a point");
    this.router.navigate(['homePageDermatologist/workScheduleDermatologist']);

    });
  }
}
