import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';
import { ConsultationService } from 'src/app/services/consultation.service';

@Component({
  selector: 'app-consultation-frontpage',
  templateUrl: './consultation-frontpage.component.html',
  styleUrls: ['./consultation-frontpage.component.css']
})
export class ConsultationFrontpageComponent implements OnInit {


  constructor(private router: Router, private activatedRoute: ActivatedRoute, private consultationService : ConsultationService, private authorizationService : AuthService, private toastr: ToastrService) { }

  id : String;

  ngOnInit(): void {
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
  }

  start() : void{
    this.router.navigate(['homePagePharmacist/consultationReport/' + this.id]);
  }

  return() : void{
    this.router.navigate(['homePagePharmacist/workSchedule']);
  }

  point()
  {
    this.consultationService.addPoint(this.id).subscribe(data => { console.log("Uspesno"); 
    this.toastr.success("You have added a point");
    this.router.navigate(['homePagePharmacist/workSchedule']);
    });
  }

  

}
