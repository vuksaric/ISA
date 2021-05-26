import { Component, OnInit } from '@angular/core';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-serach-patients',
  templateUrl: './serach-patients.component.html',
  styleUrls: ['./serach-patients.component.css']
})
export class SerachPatientsComponent implements OnInit {

  constructor(private patientService: PatientService) { }

  names : String[];
  listOfDisplayData : String[];
  searchValue : String;

  ngOnInit(): void {

    this.patientService.getNames().subscribe(data => { console.log(data); 
      this.names = data;
      this.listOfDisplayData = this.names;
    });

  }

  search() : void
  {
    this.listOfDisplayData = this.names.filter((item: String) => item.toLowerCase().indexOf(this.searchValue.toLowerCase()) !== -1);
  }

}
