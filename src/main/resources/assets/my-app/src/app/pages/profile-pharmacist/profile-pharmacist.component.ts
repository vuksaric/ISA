import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Profile } from 'src/app/models/profile';
import { AuthService } from 'src/app/services/auth.service';
import { DermatologistService } from 'src/app/services/dermatologist.service';
import { PharmacistService } from 'src/app/services/pharmacist.service';

@Component({
  selector: 'app-profile-pharmacist',
  templateUrl: './profile-pharmacist.component.html',
  styleUrls: ['./profile-pharmacist.component.css']
})
export class ProfilePharmacistComponent implements OnInit {

  profile: Profile;
  data1: any;
  data: any
  dataToken : any;
  type : String;

  constructor(private pharmacistService: PharmacistService, private router: Router, private authorizationService : AuthService, private dermatologistService : DermatologistService ) {}

  ngOnInit(): void {

    this.dataToken = this.authorizationService.getDataFromToken();
    this.type = this.dataToken.type;
    console.log(this.type);
    if(this.type == "Pharmacist")
    {
      this.pharmacistService.getProfile(1).subscribe(data => { console.log(data); 
        this.data1 = [
        { title: 'Email', description: data.email },
        { title: 'Full name', description: data.name + ' ' + data.surname},
        { title: 'Date of birth', description: data.date[2] + '.' + data.date[1] + '.' + data.date[0]},
        { title: 'Adress', description: data.address + ', ' + data.town + ', ' + data.state },
        { title: 'Phone number', description: data.phone }
      ] });
    }
    else
    {
      this.dermatologistService.getProfile(1).subscribe(data => { console.log(data); 
        this.data1 = [
        { title: 'Email', description: data.email },
        { title: 'Full name', description: data.name + ' ' + data.surname},
        { title: 'Date of birth', description: data.date[2] + '.' + data.date[1] + '.' + data.date[0]},
        { title: 'Adress', description: data.address + ', ' + data.town + ', ' + data.state },
        { title: 'Phone number', description: data.phone }
      ] });
    }
  }

  
  edit() {
    if(this.type == "Pharmacist")
    {
    this.router.navigate(['homePagePharmacist/userProfile']);
    }
    else{
      this.router.navigate(['homePageDermatologist/userProfile']);
    }
  }

}
