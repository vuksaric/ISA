import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
//
import { ToastrService } from 'ngx-toastr';
import { Address } from 'src/app/models/address';
import { AddressService } from 'src/app/services/address.service';
import { PharmacyService } from 'src/app/services/pharmacy.service';

@Component({
  selector: 'app-registration-pharmacy',
  templateUrl: './registration-pharmacy.component.html',
  styleUrls: ['./registration-pharmacy.component.css']
})
export class RegistrationPharmacyComponent implements OnInit {

  validateForm!: FormGroup;
  name: string;
  street : string;
  town : string;
  state : string;
  description : string;
  adr : Address
  longitude : number;
  latitude : number;

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.name = this.validateForm.value.name;
    this.street = this.validateForm.value.street;
    this.town = this.validateForm.value.town;
    this.state = this.validateForm.value.state;
    this.description = this.validateForm.value.description;
    this.longitude = this.validateForm.value.longitude;
    this.latitude = this.validateForm.value.latitude;
    //this.mark = this.validateForm.value.mark;

    const address = {
      street : this.street,
      town : this.town,
      state : this.state,
      latitude : this.latitude,
      longitude : this.longitude
    }

    const body = {
      name: this.name,
      description : this.description,
      address : address
    }
    
    //this.pharmacyService.registerPharmacy(body).subscribe();
    //this.addressService.add(address).subscribe((adr : Address)=>{this.adr =adr})
    //this.addressService.add(address).subscribe(data => { console.log(data) })
    this.pharmacyService.registerPharmacy(body).subscribe(data => { console.log(data)
      //alert("You have successfully registered a pharmacy") })
      this.toastr.success("You have successfully registered a pharmacy!");
    })
    
  }

  /*updateConfirmValidator(): void {
    Promise.resolve().then(() => this.validateForm.controls.checkPassword.updateValueAndValidity());
  }*/

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    }
    return {};
  };

  constructor(private fb: FormBuilder,private pharmacyService: PharmacyService, private addressService: AddressService, private toastr : ToastrService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      street : [null, [Validators.required]],
      town : [null, [Validators.required]],
      state : [null, [Validators.required]],
      description : [null, [Validators.required]],
      latitude : [null, [Validators.required]],
      longitude : [null, [Validators.required]]
    });
  }
}
