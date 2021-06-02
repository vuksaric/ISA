import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ExaminationService } from 'src/app/services/examination.service';
import { MedicineService } from 'src/app/services/medicine.service';
import { ReportService } from 'src/app/services/report.service';

@Component({
  selector: 'app-examination-report',
  templateUrl: './examination-report.component.html',
  styleUrls: ['./examination-report.component.css']
})
export class ExaminationReportComponent implements OnInit {

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
  diagnosis : String;
  validateForm: FormGroup;


  constructor(private activatedRoute: ActivatedRoute,private medicineService: MedicineService,private examinationService: ExaminationService, 
    private reportService : ReportService, private router: Router, private fb: FormBuilder) { 

      this.validateForm = this.fb.group({
        information: ['', [Validators.required]],
        diagnosis: ['', [Validators.required]],
        days: ['', [Validators.required]]
      })
    }

  ngOnInit(): void {
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
    this.examinationService.getMedicines(this.id).subscribe(data => { console.log(data); 
      this.medicines = data;
    });
    this.days = 0;
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

    this.examinationService.prescribeMedicine(this.body).subscribe(data => { console.log(data); 
      this.prescribed = data;
      console.log( "Prepisan je prvi: " + data);

      if(!this.prescribed)
      {
        alert("Medicine is currently out of stock, see replacements below");
        this.examinationService.getReplacements(this.body).subscribe(data => { console.log(data); 
          this.alternatives = data;
          console.log("Lista novih: " + data);
        });
      }
      else{
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

    this.examinationService.prescribeMedicine(this.body).subscribe(data => { console.log(data); 
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
      duration : this.days,
      diagnosis : this.diagnosis
    }

    for (const key in this.validateForm.controls) {
      this.validateForm.controls[key].markAsDirty();
      this.validateForm.controls[key].updateValueAndValidity();
    }

    if(this.validateForm.valid)
    {
      this.examinationService.finish(this.body).subscribe(data => { console.log(data) });
      this.router.navigate(['homePageDermatologist']);
    }
    else
      alert("All fields are required");

    
  }

  return(){

    this.router.navigate(['consultationFrontpage/' + this.id]);

  }

  handleOk(): void {
    console.log('Button ok clicked!');
    this.isVisible = false;
  }

  new()
  {
    this.finish();
    this.router.navigate(['newExaminationDermatologist/' + this.id]);
  }


}
