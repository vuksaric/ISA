import { Pipe, PipeTransform } from '@angular/core';
import { Medicine } from 'src/app/models/medicine';

@Pipe({
  name: 'searchMedicine'
})
export class SearchMedicinePipe implements PipeTransform {

  transform(Medicines: Medicine[], searchValue: string) : Medicine[] {
    if(!Medicines || !searchValue){
      return Medicines;
    }
    return Medicines.filter(medicine => medicine.name.toLocaleLowerCase().includes(searchValue.toLocaleLowerCase()) ||
    medicine.shape.toLocaleLowerCase().includes(searchValue.toLocaleLowerCase()) ||
    medicine.type.toLocaleLowerCase().includes(searchValue.toLocaleLowerCase()));
  }

}
