import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SupplierService } from 'src/app/services/supplier.service';
import jwt_decode from 'jwt-decode';
import { Profile } from 'src/app/models/profile';
import { UserService } from 'src/app/services/user.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-supplier-profile',
  templateUrl: './supplier-profile.component.html',
  styleUrls: ['./supplier-profile.component.css']
})
export class SupplierProfileComponent implements OnInit {

  validateForm!: FormGroup;
  validateForm2!: FormGroup;
  name: string;
  lastname : string;
  email : string;
  street : string;
  town : string;
  state : string;
  phone : string;
  token : any;
  supplier_info: Profile;

  oldPassword: string;
  password: string;
  checkPassword: string;

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.name = this.validateForm.value.name;
    this.lastname = this.validateForm.value.surname;
    this.email = this.validateForm.value.email;
    this.street = this.validateForm.value.street;
    this.town = this.validateForm.value.town;
    this.state = this.validateForm.value.state;
    this.phone = this.validateForm.value.phone;

    const body = {
      name: this.name,
      surname: this.lastname,
      email : this.email,
      address : this.street,
      town : this.town,
      state : this.state,
      phone : this.phone,
    }
    if(this.validateForm.valid){
      this.userService.editProfile(body).subscribe(data => { console.log(data) })
    }
  }

  submitForm2(): void {
    for (const i in this.validateForm2.controls) {
      this.validateForm2.controls[i].markAsDirty();
      this.validateForm2.controls[i].updateValueAndValidity();
    }

    this.password = this.validateForm2.value.password;
    this.oldPassword = this.validateForm2.value.oldPassword;
    this.checkPassword = this.validateForm2.value.checkPassword;

    const body = {
      oldPassword: this.oldPassword,
      password: this.password,
      checkPassword: this.checkPassword,
    }

    if(this.validateForm.valid){
        this.userService.changePassword(body).subscribe(data => {
        this.toastr.success("Successfully changed password");
      })
    }
  }

  updateConfirmValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm2.controls.checkPassword.updateValueAndValidity());
  }

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm2.controls.password.value) {
      return { confirm: true, error: true };
    }
    return {};
  };

  constructor(private toastr: ToastrService, private userService: UserService, private fb: FormBuilder, private supplierService: SupplierService) { }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    }
    catch (Error) {
      return null;
    }
  }

  profileInfoFields(){
    this.validateForm = this.fb.group({
      name: [this.supplier_info.name, [Validators.required]],
      surname: [this.supplier_info.surname, [Validators.required]],
      email: [this.supplier_info.email, [Validators.required]],
      street : [this.supplier_info.address, [Validators.required]],
      town : [this.supplier_info.town, [Validators.required]],
      state : [this.supplier_info.state, [Validators.required]],
      phone: [this.supplier_info.phone, [Validators.required]]
    });
  }

  ngOnInit(): void {
    //this.token = localStorage.getItem('token');
    //console.log(this.token.email);

    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      surname: [null, [Validators.required]],
      email: [null, [Validators.required]],
      street : [null, [Validators.required]],
      town : [null, [Validators.required]],
      state : [null, [Validators.required]],
      phone: [null, [Validators.required]],
    });

    this.validateForm2 = this.fb.group({
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]],
      oldPassword: [null, [Validators.required]],
    });

    this.token = this.getDecodedAccessToken(localStorage.getItem('token'));
    console.log(this.token.email);
    this.supplierService.getProfileInfo(this.token.email).subscribe(data =>{
      this.supplier_info = data;
      console.log(this.supplier_info.name);
      this.profileInfoFields();
    });



  }

}


