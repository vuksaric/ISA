import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzOptionComponent, NzSelectComponent } from 'ng-zorro-antd/select';
import { Replacements } from 'src/app/models/replacements';
import { MedicineService } from 'src/app/services/medicine.service';

@Component({
  selector: 'app-add-medicine',
  templateUrl: './add-medicine.component.html',
  styleUrls: ['./add-medicine.component.css']
})
export class AddMedicineComponent implements OnInit {

  validateForm!: FormGroup;
  selectedValueIssuingMode: "WithRecipe";
  selectedValueTherapy: "1";
  selectedValueReplacements: null;
  name: string;
  type: string;
  shape: string;
  ingredients_array: string[];
  ingredients: string;
  manufacturer: string;
  contraindications: string;
  replacements: Replacements[];
  selectReplacements: NzSelectComponent;
  optionReplacements: NzOptionComponent;
  ReplacementsOptions : NzOptionComponent[];
  items : Replacements[];
  ids: number[];

  onSearchChange(searchValue: string): void {  
    this.selectedValueReplacements = null;
    this.medicineservice.getByType(searchValue).subscribe(data => { this.items = data;});
  }

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.name = this.validateForm.value.name;
    this.type = this.validateForm.value.type;
    this.name = this.validateForm.value.name;
    this.shape = this.validateForm.value.shape;
    this.ingredients = this.validateForm.value.ingredients;
    this.manufacturer = this.validateForm.value.manufacturer;
    this.contraindications = this.validateForm.value.contraindications;
    this.items = this.validateForm.value.items;
    
    if(this.validateForm.valid){
    this.ingredients_array = this.ingredients.split(",");

    this.ids = this.selectedValueReplacements;

    const body = {
      name: this.name,
      type: this.type,
      shape: this.shape,
      ingredients: this.ingredients_array,
      manufacturer: this.manufacturer,
      issuingMode: this.selectedValueIssuingMode,
      replacements: this.ids,
      notes: this.contraindications,
      therapyPerDay: this.selectedValueTherapy
    }
    console.log(body);


      this.medicineservice.addMedicine(body).subscribe(data => { console.log(data) })
    }
  }

  constructor(private fb: FormBuilder, private medicineservice: MedicineService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      type: [null, [Validators.required]],
      shape: [null, [Validators.required]],
      ingredients: [null, [Validators.required]],
      manufacturer: [null, [Validators.required]],
      //replacements: [null, [Validators.required]],
      contraindications: [null, [Validators.required]],
      //issuingMode: [null,[Validators.required]]
      items: [null,[Validators.required]]
    });
  }

}
