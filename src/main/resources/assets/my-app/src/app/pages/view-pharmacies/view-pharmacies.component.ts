import { Component, OnInit } from '@angular/core';
import { Pharmacy } from 'src/app/models/pharmacy';
import { PharmacyService } from 'src/app/services/pharmacy.service';

@Component({
  selector: 'app-view-pharmacies',
  templateUrl: './view-pharmacies.component.html',
  styleUrls: ['./view-pharmacies.component.css']
})

export class ViewPharmaciesComponent implements OnInit {
  searchValue: string;
  listOfData : Pharmacy[] = [];
  listOfColumn = [
    {
      title: 'Name',
      compare: (a: Pharmacy, b: Pharmacy) => a.name.localeCompare(b.name),
      priority: false
    },
    {
      title: 'Street',
      compare: (a: Pharmacy, b: Pharmacy) => a.street.localeCompare(b.street),
      priority: 3
    },
    {
      title: 'Town',
      compare: (a: Pharmacy, b: Pharmacy) => a.town.localeCompare(b.town),
      priority: 1,
      filterMultiple: true,
      listOfFilter: [
        { text: 'Beograd', value: 'Beograd' },
        { text: 'Novi Sad', value: 'Novi Sad' }
      ],
      filterFn: (list: string[], item: Pharmacy) => list.some(town => item.town.indexOf(town) !== -1)
    },
    {
      title: 'Grade',
      compare: (a: Pharmacy, b: Pharmacy) => a.mark - b.mark,
      priority: 2,
      filterMultiple: true,
      listOfFilter: [
        { text: '1', value: '1' },
        { text: '2', value: '2' },
        { text: '3', value: '3' },
        { text: '4', value: '4' },
        { text: '5', value: '5' },
        { text: '6', value: '6' },
        { text: '7', value: '7' },
        { text: '8', value: '8' },
        { text: '9', value: '9' },
        { text: '10', value: '10' },
      ],
      filterFn: (list: string[], item: Pharmacy) => list.some(mark  => (item.mark>=Number(mark) && item.mark < (Number(mark)+1)))
    }
  ];

  constructor( private pharmacyService: PharmacyService) { }

  loadPharmacies(): void{
    this.pharmacyService.getPharmacies().subscribe((pharmacies: Pharmacy[]) => {
      this.listOfData = pharmacies;
      /*pharmacies.forEach(element => {
        var num = this.listOfFilterTown.push({text:element.town, value:element.town});
        console.log(num);
        const found = this.listOfColumn.find(item=> item.title=="Town");
        console.log(found);
        found.listOfFilter=this.listOfFilterTown;
        console.log(this.listOfColumn);
      });*/
    });
  }

  ngOnInit(): void {
   this.loadPharmacies();
  }

}
