import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConsultationService } from 'src/app/services/consultation.service';
import { MedicineService } from 'src/app/services/medicine.service';
import { ReportService } from 'src/app/services/report.service';


@Component({
  selector: 'app-consultation-report',
  templateUrl: './consultation-report.component.html',
  styleUrls: ['./consultation-report.component.css']
})
export class ConsultationReportComponent implements OnInit {

  medicines : any[];
  displayMedicine: any[];
  alternatives : any[];
  searchValue : String;
  days : number;
  id : String;
  medicineSpecification: any;
  isVisible = false;
  body : any;
  prescribed  = false;
  medicineId : number;
  information : String;
  prescribedMedicine : any;

  constructor(private activatedRoute: ActivatedRoute,private medicineService: MedicineService,private consultationService: ConsultationService, 
    private reportService : ReportService, private router: Router) { }

  ngOnInit(): void {
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
    this.consultationService.getMedicines(this.id).subscribe(data => { console.log(data); 
      this.medicines = data;
    });
  }

  specification(id : number) : void{
    console.log(id);
    this.medicineService.getById(id).subscribe(data => { console.log(data); 
      this.medicineSpecification = data;
    });
    this.isVisible = true;
  }

  search() : void{
    this.displayMedicine = this.medicines.filter((item: any) => item.name.toLowerCase().indexOf(this.searchValue.toLowerCase()) !== -1);
  }

  prescribe(medicine : any) : void{

    this.body = {
        idConsultation : this.id,
        idMedicine : medicine.id
    }

    this.consultationService.prescribeMedicine(this.body).subscribe(data => { console.log(data); 
      this.prescribed = data;
      console.log( "Prepisan je prvi: " + data);

      if(!this.prescribed)
      {
        alert("Medicine is currently out of stock, see replacements below");
        this.consultationService.getReplacements(this.body).subscribe(data => { console.log(data); 
          this.alternatives = data;
          console.log("Lista novih: " + data);
        });
      }
      else{
        alert("usao")
        this.prescribedMedicine = medicine;
        console.log(this.prescribedMedicine);
      }
    });



  }

  prescribeAlternative(medicine : any) : void
  {
    this.body = {
      idConsultation : this.id,
      idMedicine : medicine.id
    }

    this.consultationService.prescribeMedicine(this.body).subscribe(data => { console.log(data); 
      this.prescribed = data;
      console.log(data);
      this.prescribedMedicine = medicine
    console.log(this.prescribedMedicine);
    });

    
  }

  finish(){
    console.log(this.prescribedMedicine);
    this.body = {
      id : this.id,
      information : this.information,
      medicine : this.prescribedMedicine,
      duration : this.days
    }

    this.consultationService.finish(this.body).subscribe(data => { console.log(data) });
    this.router.navigate(['homePagePharmacist']);
    
  }

  return(){

    this.router.navigate(['consultationFrontpage/' + this.id]);

  }

  handleOk(): void {
    console.log('Button ok clicked!');
    this.isVisible = false;
  }
}
