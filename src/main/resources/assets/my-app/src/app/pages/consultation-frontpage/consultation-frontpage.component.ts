import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-consultation-frontpage',
  templateUrl: './consultation-frontpage.component.html',
  styleUrls: ['./consultation-frontpage.component.css']
})
export class ConsultationFrontpageComponent implements OnInit {

  constructor(private router: Router, private activatedRoute: ActivatedRoute) { }
  id : String;

  ngOnInit(): void {
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
  }

  start() : void{
    this.router.navigate(['consultationReport/' + this.id]);
  }

}
