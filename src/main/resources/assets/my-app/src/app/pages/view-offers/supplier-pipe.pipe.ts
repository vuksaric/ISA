import { Pipe, PipeTransform } from '@angular/core';
import { Offer } from 'src/app/models/offer';

@Pipe({
  name: 'supplierPipe'
})
export class SupplierPipePipe implements PipeTransform {

  transform(Offers: Offer[], searchValue2: string) : Offer[] {
    if(!Offers || !searchValue2){
      return Offers;
    }
    return Offers.filter(offer => offer.status.toLocaleLowerCase().includes(searchValue2.toLocaleLowerCase()));
  }

}
