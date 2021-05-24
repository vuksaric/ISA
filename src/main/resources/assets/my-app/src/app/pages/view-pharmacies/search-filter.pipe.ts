import { Pipe, PipeTransform } from '@angular/core';
import { Pharmacy } from 'src/app/models/pharmacy';

@Pipe({
  name: 'searchFilter'
})
export class SearchFilterPipe implements PipeTransform {

  transform(Pharmacies: Pharmacy[], searchValue: string) : Pharmacy[] {
    if(!Pharmacies || !searchValue){
      return Pharmacies;
    }
    return Pharmacies.filter(pharmacy => pharmacy.name.toLocaleLowerCase().includes(searchValue.toLocaleLowerCase()) ||
    pharmacy.town.toLocaleLowerCase().includes(searchValue.toLocaleLowerCase()));
  }
}
