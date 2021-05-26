import { Component, OnInit } from '@angular/core';
import { ɵNAMESPACE_URIS } from '@angular/platform-browser';
import { element } from 'protractor';
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
  listOfData : Allergy[] = [];
  listOfMedicines : Allergy[] = [];
  
  profile: Profile;
  data1: any;
  allergies: any;
  isVisible = false;
  value : String;
  
  constructor(private patientService: PatientService, private patientChartService: PatientChartService,
    private medicineService : MedicineService) { }

  ngOnInit(): void {
    
    this.patientService.getProfile(1).subscribe(data => { console.log(data); 
      this.data1 = [
      { title: 'Username', description: data.username },
      { title: 'Email', description: data.email },
      { title: 'Full name', description: data.name + ' ' + data.surname},
      { title: 'Adress', description: data.address + ', ' + data.town + ', ' + data.state },
      { title: 'Phone number', description: data.phone },
      { title: 'User type', description: data.type }
    ] });


    this.medicineService.getDistinctMedicine().subscribe( data => {console.log(data);
      this.listOfMedicines = data;
    });

    this.patientChartService.getPatientAllergy(1).subscribe(data => {console.log(data); 
      var names : String = "";
        data.forEach(element => {
        names = names + element.name + "; ";
      });
      this.allergies=[
        {title : 'Allergies', description: names}
      ];
     /*this.listOfMedicines.forEach(element=>{
      alert(data.includes(element));
     });
     console.log(this.listOfData);*/
    
    })

      

  }

  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    console.log('Button ok clicked!');
    
      const body ={
        name: "Adderall"
      }
      this.patientChartService.addPatientAllergy(body, 1).subscribe(data => { console.log(data) });
      this.isVisible = false;
    location.reload();
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }

  add(){
    this.showModal();
    
  }
  edit(){
    alert("izmeni profil");
  }

}
