import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { Profile } from 'src/app/models/profile';
import { PatientService } from 'src/app/services/patient.service';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  validateForm!: FormGroup;
  profile:  Observable<Profile>;
  name: string;
  surname: string;
  address: string;
  town: string;
  state: string;
  phone: string;
  email : string;

  constructor(private fb: FormBuilder, private patientService: PatientService, private userService : UserService,
    private toastr: ToastrService) { }


  submitForm(): void {
    
      for (const i in this.validateForm.controls) {
        this.validateForm.controls[i].markAsDirty();
        this.validateForm.controls[i].updateValueAndValidity();
      }
  
      this.name = this.validateForm.value.name_user;
      this.surname = this.validateForm.value.surname;
      this.address = this.validateForm.value.address;
      this.town = this.validateForm.value.town;
      this.state = this.validateForm.value.state;
      this.phone = this.validateForm.value.phoneNumber;
      

      const body = {
        name: this.name,
        surname: this.surname,
        address: this.address,
        town: this.town,
        state: this.state,
        phone: this.phone,
        email: this.email
      }
      console.log(body);

      if(this.validateForm.valid){
        this.patientService.editProfile(body).subscribe(data => { 
          console.log(data);
          this.toastr.success("You have successfully edited your user profile!");
         })
      }
  }

  ngOnInit(): void {
    
    this.userService.getUserProfile("andja.ptrvc@gmail.com").subscribe(data => { console.log(data);
      this.email = data.email;
    
      console.log(data);
     this.validateForm = this.fb.group({
        email:[{value :data.email,  disabled: true }], 
        name_user: [data.name, [Validators.required]],
        surname: [data.surname, [Validators.required]],
        phoneNumber: [data.phone, [Validators.required]],
        address: [data.address, [Validators.required]],
        town: [data.town, [Validators.required]],
        state: [data.state, [Validators.required]],
      })
    });
  }
}
