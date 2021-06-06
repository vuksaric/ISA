import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzModalRef, NzModalService } from 'ng-zorro-antd/modal';
import { ToastrService } from 'ngx-toastr';
import { Allergy } from 'src/app/models/allergy';
import { Profile } from 'src/app/models/profile';
import { AuthService } from 'src/app/services/auth.service';
import { MedicineService } from 'src/app/services/medicine.service';
import { PatientChartService } from 'src/app/services/patient-chart.service';
import { PatientService } from 'src/app/services/patient.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-patient-profile',
  templateUrl: './patient-profile.component.html',
  styleUrls: ['./patient-profile.component.css']
})
export class PatientProfileComponent implements OnInit {
  confirmModal?: NzModalRef;
  listOfPoints = [];
  listOfData : Allergy[] = [];
  listOfOptions : Allergy[] =[];
  isVisiblePoint = false;
  dataFromToken : any;
 
  validateForm2 : FormGroup;
  profile: Profile;
  data1: any;
  allergies: any;
  isVisible = false;
  nzVisible = false;
  isVisible2 = false;
  value : String;
  points : number;
  
  constructor(private patientService: PatientService, private patientChartService: PatientChartService,
    private medicineService : MedicineService, private modal: NzModalService, private router: Router,
    private toastr: ToastrService, private fb: FormBuilder, private userService: UserService,
    private authorizationService : AuthService) { }

  ngOnInit(): void {
    this.dataFromToken = this.authorizationService.getDataFromToken();
    
    this.patientService.getProfile(this.dataFromToken.id).subscribe(data => { console.log(data); 
      this.data1 = [
      { title: 'Email', description: data.email },
      { title: 'Full name', description: data.name + ' ' + data.surname},
      { title: 'Adress', description: data.address + ', ' + data.town + ', ' + data.state },
      { title: 'Phone number', description: data.phone },
      { title: 'User type', description: data.type }
    ] });

    this.validateForm2 = this.fb.group({
      oldPassword:[null,[Validators.required]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]]
    })


    this.patientChartService.getNotPatientAllergy(this.dataFromToken.id).subscribe(data => { console.log(data); this.listOfOptions=data; });


    this.patientChartService.getPatientAllergy(this.dataFromToken.id).subscribe(data => {console.log(data); 
      var names : String = "";
        data.forEach(element => {
        names = names + element.name + "; ";
      });
      this.allergies=[
        {title : 'Allergies', description: names}
      ];
    })

    this.patientService.getPenaltyPoints(this.dataFromToken.id).subscribe(data=>{this.listOfPoints=data; this.points= this.listOfPoints.length});
  }
  
  add(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    console.log('Button ok clicked!');
    console.log(this.value);
    this.modal.success({
      nzTitle: 'This is a success message',
      nzContent: 'Successfully added allergies',
      nzOnOk: () =>{
        location.reload();
        this.isVisible=false;
      }   
    });
  }

  addAllergy(item){
    const body={
      name : item.name
    }
    this.patientChartService.addPatientAllergy(body, this.dataFromToken.id).subscribe(data => { console.log(data);
      this.toastr.success("You have successfully made a new allergy!");
      this.patientChartService.getNotPatientAllergy(this.dataFromToken.id).subscribe(data => { console.log(data); this.listOfOptions=data; });
    });
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
    location.reload();
  }

  edit(){
    this.router.navigate(['homepage/userProfile']);
  }
  editPassword(){
    this.isVisible2 = true;
  }

  submitForm2(){
    if(this.validateForm2.valid){
      const body = {
        oldPassword: this.validateForm2.controls['oldPassword'].value,
        password: this.validateForm2.controls['password'].value,
        checkPassword: this.validateForm2.controls['checkPassword'].value,
      }
      this.userService.changePassword(body).subscribe(result => {
        this.handleCancel();
        this.toastr.success("Successfully changed");
      })
    }
  }

  handleCancel2(): void {
    this.isVisible2 = false;
  }

  viewPoints(){
    this.isVisiblePoint=true;
  }
  handlePoint(): void {
    this.isVisiblePoint=false;
  }

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm2.controls.password.value) {
      return { confirm: true, error: true };
    }
    return {};
  };



}
