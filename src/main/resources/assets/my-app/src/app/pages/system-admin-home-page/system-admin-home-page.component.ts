import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-system-admin-home-page',
  templateUrl: './system-admin-home-page.component.html',
  styleUrls: ['./system-admin-home-page.component.css']
})
export class SystemAdminHomePageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  regPhClick = function(){
    this.router.navigate(['login']);
  }

  regPhAdminClick = function(){
    this.router.navigate(['login']);
  }

}
