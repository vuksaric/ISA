import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Consultation } from 'src/app/models/consultation';
import { ConsultationService } from 'src/app/services/consultation.service';
import { ExaminationService } from 'src/app/services/examination.service';



interface DataItem {
  id : number;
  name: string;
  surname: string;
  address: string;
  date: string
  pharmacy: string
}

@Component({
  selector: 'app-previous-examinations',
  templateUrl: './previous-examinations.component.html',
  styleUrls: ['./previous-examinations.component.css']
})
export class PreviousExaminationsComponent implements OnInit {

  searchValueName = '';
  searchValueSurname = ''; 
  searchValueAddress = '';
  searchValueDate = '';
  searchValuePharmacy = '';
  visibleName = false;
  visibleSurname= false;
  visibleAddress= false;
  visibleDate = false;
  visiblePharmacy = false;
  examinations = [] as DataItem[];
  listOfDisplayData = [] as DataItem[];

  constructor(private router: Router,private examinationService: ExaminationService ) { }

  ngOnInit(): void {

    this.examinationService.getPreviousByDermatologist(1).subscribe(data => { console.log(data); 
      this.examinations = data;
      this.listOfDisplayData = this.examinations;
    });
  }

  

  resetName(): void {
    this.searchValueName = '';
    this.searchName();
  }

  searchName(): void {
    this.visibleName = false;
    this.listOfDisplayData = this.examinations.filter((item: DataItem) => item.name.toLowerCase().indexOf(this.searchValueName.toLowerCase()) !== -1);
    console.log(this.listOfDisplayData);
  }

  resetSurname(): void {
    this.searchValueSurname = '';
    this.searchSurname();
  }

  searchSurname(): void {
    this.visibleSurname = false;
    this.listOfDisplayData = this.examinations.filter((item: DataItem) => item.surname.toLowerCase().indexOf(this.searchValueSurname.toLowerCase()) !== -1);
  }

  
  resetAddress(): void {
    this.searchValueAddress = '';
    this.searchAddress();
  }

  searchAddress(): void {
    this.visibleAddress = false;
    this.listOfDisplayData = this.examinations.filter((item: DataItem) => item.address.toLowerCase().indexOf(this.searchValueAddress.toLowerCase()) !== -1);
  }

  
  resetDate(): void {
    this.searchValueDate = '';
    this.searchDate();
  }

  searchDate(): void {
    this.visibleDate = false;
    this.listOfDisplayData = this.examinations.filter((item: DataItem) => item.date.toLowerCase().indexOf(this.searchValueDate.toLowerCase()) !== -1);
  }

  resetPharmacy(): void {
    this.searchValuePharmacy = '';
    this.searchDate();
  }

  searchPharmacy(): void {
    this.visiblePharmacy = false;
    this.listOfDisplayData = this.examinations.filter((item: DataItem) => item.pharmacy.toLowerCase().indexOf(this.searchValuePharmacy.toLowerCase()) !== -1);
  }

  viewProfile(id : number) : void
  {
    this.router.navigate(['homePageDermatologist/patientProfileDoctor/' + id]);
  }

}
