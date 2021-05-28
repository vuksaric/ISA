import { Pharmaist } from './../../../models/pharmacist';
import { PharmacistService } from 'src/app/services/pharmacist.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NzModalService } from 'ng-zorro-antd/modal';
import { differenceInCalendarDays } from 'date-fns';
import { ToastrService } from 'ngx-toastr';


interface Person {
  key: string;
  name: string;
  age: number;
  address: string;
}

@Component({
  selector: 'app-pharmacist-list',
  templateUrl: './pharmacist-list.component.html',
  styleUrls: ['./pharmacist-list.component.css']
})
export class PharmacistListComponent implements OnInit {

  isVisible = false;
  isVisible2 = false;
  date = null;
  today = new Date();
  validateForm!: FormGroup;
  pharmacistList: Pharmaist[];
  search : string;

  constructor(private modal: NzModalService, private fb: FormBuilder, private pharmacistService: PharmacistService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.getAllPharmacist();

    this.validateForm = this.fb.group({
      name: [null, [Validators.required, this.validateName]],
      surname: [null, [Validators.required, this.validateSurname]],
      gender: [null, [Validators.required]],
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]],
      phone: [null, [Validators.required, this.validatePhone]],
      birthday: [null, [Validators.required]],
      street: [null, [Validators.required]],
      town: [null, [Validators.required]],
      state: [null, [Validators.required]]
    });
  }

  getAllPharmacist(){
    this.pharmacistService.getAll().subscribe(result => { this.pharmacistList = result }, () => { this.toastr.error("An error has occurred") });
  }

  searchPharmacist(){
    if(this.search === '' || this.search === null || this.search === undefined){
      this.toastr.error("Please input search field");
    }else{
      this.pharmacistService.search(this.search).subscribe(result => {
        this.toastr.success("GREAT SUCCESS");
        this.pharmacistList = result;
      });
    }
  }

  clearSearch(){
    this.search = '';
    this.getAllPharmacist();
  }

  validateName = (group: FormControl): { [s: string]: boolean } => {
    let phone = group.value;
    let regex = /^[a-zA-Z ]+$/;
    if (!regex.test(phone)) {
      return { name: true }
    }
  }

  validateSurname = (group: FormControl): { [s: string]: boolean } => {
    let phone = group.value;
    let regex = /^[a-zA-Z ]+$/;
    if (!regex.test(phone)) {
      return { surname: true }
    }
  }

  validatePhone = (group: FormControl): { [s: string]: boolean } => {
    let phone = group.value;
    let regex = /^[0-9]*$/;
    if (!regex.test(phone)) {
      return { numbers: true }
    }
  }


  disabledDate = (current: Date): boolean => {
    return differenceInCalendarDays(current, this.today) > 0;
  };


  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm.controls.password.value) {
      return { confirm: true, error: true };
    }
    return {};
  };

  submitForm(): void {
    if (this.validateForm.valid) {
      const body = {
        name: this.validateForm.controls['name'].value,
        surname: this.validateForm.controls['surname'].value,
        password: this.validateForm.controls['password'].value,
        email: this.validateForm.controls['email'].value,
        gender: this.validateForm.controls['gender'].value,
        address: this.validateForm.controls['street'].value,
        town: this.validateForm.controls['town'].value,
        state: this.validateForm.controls['state'].value,
        birthday: this.validateForm.controls['birthday'].value,
        phone: this.validateForm.controls['phone'].value
      }

      this.pharmacistService.newPharmacist(body).subscribe(data => {
        this.toastr.success("Successfully registered!");
        this.getAllPharmacist();
        this.isVisible = false;
      }, error => {
        this.toastr.error("Failed to register");
      })
    }

    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }

  updateConfirmValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.checkPassword.updateValueAndValidity());
  }

  updateNameValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.name.updateValueAndValidity());
  }

  updateSurnameValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.surname.updateValueAndValidity());
  }

  updateGenderValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.gender.updateValueAndValidity());
  }

  updateEmailValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.email.updateValueAndValidity());
  }

  updatePhoneValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.phone.updateValueAndValidity());
  }

  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    this.isVisible = false;
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  showConfirm(): void {
    this.modal.confirm({
      nzTitle: '<i>Do you Want to delete these items?</i>',
      nzContent: '<b>Some descriptions</b>',
      nzOnOk: () => console.log('OK')
    });
  }

  showDeleteConfirm(): void {
    this.modal.confirm({
      nzTitle: 'Are you sure delete this pharmacist?',
      nzOkText: 'Yes',
      nzOkType: 'primary',
      nzOkDanger: true,
      nzOnOk: () => console.log('OK'),
      nzCancelText: 'No',
      nzOnCancel: () => console.log('Cancel')
    });
  }

  listOfData: Person[] = [
    {
      key: '1',
      name: 'John Brown',
      age: 32,
      address: 'New York No. 1 Lake Park'
    },
    {
      key: '2',
      name: 'Jim Green',
      age: 42,
      address: 'London No. 1 Lake Park'
    },
    {
      key: '3',
      name: 'Joe Black',
      age: 32,
      address: 'Sidney No. 1 Lake Park'
    }
  ];

}
