import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Replacements } from 'src/app/models/replacements';
import { MedicineService } from 'src/app/services/medicine.service';

@Component({
  selector: 'app-add-medicine',
  templateUrl: './add-medicine.component.html',
  styleUrls: ['./add-medicine.component.css']
})
export class AddMedicineComponent implements OnInit {

  containerElement : any;
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
  selectReplacements: HTMLSelectElement;
  optionReplacements: HTMLOptionElement;

  onSearchChange(searchValue: string): void {  
    console.log("tiiiiiiip");
    console.log(searchValue);
    this.medicineservice.getByType(searchValue).subscribe(data => { this.replacements = data; console.log(this.replacements) });
    this.selectReplacements = <HTMLSelectElement>document.getElementById("selectId");
    //this.optionReplacements = <HTMLOptionElement>this.replacements;
    for(const i in this.replacements){
      this.optionReplacements = <HTMLOptionElement><unknown>this.replacements[i].name;
      this.selectReplacements.appendChild(this.optionReplacements);
    }
    //this.selectReplacements.options = this.replacements;
    //this.replacements.
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
    
    if(this.validateForm.valid){
    this.ingredients_array = this.ingredients.split(",");

    const body = {
      name: this.name,
      type: this.type,
      shape: this.shape,
      ingredients: this.ingredients_array,
      manufacturer: this.manufacturer,
      issuingMode: this.selectedValueIssuingMode,
      replacements: this.replacements,
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
    });
  }

}
