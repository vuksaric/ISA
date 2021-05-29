import { Pipe, PipeTransform } from '@angular/core';
import { Allergy } from 'src/app/models/allergy';

@Pipe({
  name: 'filterAllergies'
})
export class FilterAllergiesPipe implements PipeTransform {

  transform(Allergies: Allergy[], value: String) :Allergy[] {
    if(!Allergies){
      return Allergies;
    }
    else if(!value){
      return null;
    }
    return Allergies.filter(allergy => allergy.name.toLocaleLowerCase().includes(value.toLocaleLowerCase()));
  }


}
