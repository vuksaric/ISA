import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
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
  isVisibleNew = false;
  body : any;
  prescribed  = false;
  medicineId : number;
  information : String;
  prescribedMedicine : any;
  validateForm: FormGroup;

  constructor(private activatedRoute: ActivatedRoute,private medicineService: MedicineService,private consultationService: ConsultationService, 
    private reportService : ReportService, private router: Router, private fb: FormBuilder, private toastr: ToastrService) { 

      this.validateForm = this.fb.group({
        information: ['', [Validators.required]],
        days: ['', [Validators.required]]
      })
    }

  ngOnInit(): void {
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
    this.consultationService.getMedicines(this.id).subscribe(data => { console.log(data); 
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

    this.consultationService.prescribeMedicine(this.body).subscribe(data => { console.log(data); 
      this.prescribed = data;
      console.log( "Prepisan je prvi: " + data);

      if(!this.prescribed)
      {
        this.toastr.warning("Medicine is currently out of stock, see replacements below");
        this.consultationService.getReplacements(this.body).subscribe(data => { console.log(data); 
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

    for (const key in this.validateForm.controls) {
      this.validateForm.controls[key].markAsDirty();
      this.validateForm.controls[key].updateValueAndValidity();
    }

    if(this.validateForm.valid)
    {
      this.consultationService.finish(this.body).subscribe(data => { console.log(data) });
      this.router.navigate(['homePagePharmacist']);
    }
    else
    this.toastr.warning("All fields are required");

    
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
    this.router.navigate(['newConsultationPharmacist/' + this.id]);
  }

  
}
