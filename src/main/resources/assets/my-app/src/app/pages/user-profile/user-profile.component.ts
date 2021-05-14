import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  validateForm!: FormGroup;

  constructor(private fb: FormBuilder) { }


  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }

  ngOnInit(): void {
     this.validateForm = this.fb.group({
      email: {value: null, disabled: true }, //email ne sme da se menja!
      name: [null, [Validators.required]],
      surname: [null, [Validators.required]],
      phoneNumberPrefix: ['+381'],
      phoneNumber: [null, [Validators.required]],
      address: [null, [Validators.required]]
  
    });
  }
}
