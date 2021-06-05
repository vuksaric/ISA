import { Pipe, PipeTransform } from '@angular/core';
import { Offer } from 'src/app/models/offer';
import { Pharmacy } from 'src/app/models/pharmacy';

@Pipe({
  name: 'searchFilter'
})
export class SearchFilterPipe implements PipeTransform {

  transform(Offers: Offer[], searchValue: string) : Offer[] {
    if(!Offers || !searchValue){
      return Offers;
    }
    return Offers.filter(offer => offer.status.toLocaleLowerCase().includes(searchValue.toLocaleLowerCase()));
  }
}
