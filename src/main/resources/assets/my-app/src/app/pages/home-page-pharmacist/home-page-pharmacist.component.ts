import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page-pharmacist',
  templateUrl: './home-page-pharmacist.component.html',
  styleUrls: ['./home-page-pharmacist.component.css']
})
export class HomePagePharmacistComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  profile()
  {
    this.router.navigate(['profilePharmacist']);
  }
}



