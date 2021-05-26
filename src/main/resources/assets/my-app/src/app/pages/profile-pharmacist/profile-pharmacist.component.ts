import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Profile } from 'src/app/models/profile';
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

  constructor(private pharmacistService: PharmacistService ) {}

  ngOnInit(): void {

    this.pharmacistService.getProfile(1).subscribe(data => { console.log(data); 
      this.data1 = [
      { title: 'Email', description: data.email },
      { title: 'Full name', description: data.name + ' ' + data.surname},
      { title: 'Date of birth', description: data.date[2] + '.' + data.date[1] + '.' + data.date[0]},
      { title: 'Adress', description: data.address + ', ' + data.town + ', ' + data.state },
      { title: 'Phone number', description: data.phone }
    ] });
    
    
     
  
  }

  
  edit() {
    alert("jej");
  }

}
