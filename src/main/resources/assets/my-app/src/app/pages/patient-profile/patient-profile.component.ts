import { Component, OnInit } from '@angular/core';
import { NzModalRef, NzModalService } from 'ng-zorro-antd/modal';
import { Allergy } from 'src/app/models/allergy';
import { Profile } from 'src/app/models/profile';
import { MedicineService } from 'src/app/services/medicine.service';
import { PatientChartService } from 'src/app/services/patient-chart.service';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-patient-profile',
  templateUrl: './patient-profile.component.html',
  styleUrls: ['./patient-profile.component.css']
})
export class PatientProfileComponent implements OnInit {
  confirmModal?: NzModalRef;

  listOfData : Allergy[] = [];
  listOfOptions : Allergy[] =[];
 
  
  profile: Profile;
  data1: any;
  allergies: any;
  isVisible = false;
  nzVisible = false;
  value : String;
  
  constructor(private patientService: PatientService, private patientChartService: PatientChartService,
    private medicineService : MedicineService, private modal: NzModalService) { }

  ngOnInit(): void {
    
    this.patientService.getProfile(1).subscribe(data => { console.log(data); 
      this.data1 = [
      { title: 'Email', description: data.email },
      { title: 'Full name', description: data.name + ' ' + data.surname},
      { title: 'Adress', description: data.address + ', ' + data.town + ', ' + data.state },
      { title: 'Phone number', description: data.phone },
      { title: 'User type', description: data.type }
    ] });



    this.medicineService.getMedicines().subscribe(data => { console.log(data); this.listOfOptions=data; });


    this.patientChartService.getPatientAllergy(1).subscribe(data => {console.log(data); 
      var names : String = "";
        data.forEach(element => {
        names = names + element.name + "; ";
      });
      this.allergies=[
        {title : 'Allergies', description: names}
      ];
    })
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
    this.patientChartService.addPatientAllergy(body, 1).subscribe(data => { console.log(data);
      this.medicineService.getMedicines().subscribe(data => { console.log(data); this.listOfOptions=data; });
    });
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
    location.reload();
  }

  edit(){
    alert("izmeni profil");
  }

}
