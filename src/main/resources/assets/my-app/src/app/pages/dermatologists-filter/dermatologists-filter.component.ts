import { Dermatologist } from './../../models/Dermatologist';
import { Component, OnInit } from '@angular/core';
import { PharmacyService } from 'src/app/services/pharmacy.service';
import { DermatologistService } from 'src/app/services/dermatologist.service';
import { ToastrService } from 'ngx-toastr';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NzTableFilterFn, NzTableFilterList, NzTableSortFn, NzTableSortOrder } from 'ng-zorro-antd/table';


interface DataItem {
  name: string;
  surname: string;
  mark: number;
  pharmacy: string;
}

interface ColumnItem {
  name: string;
  sortOrder: NzTableSortOrder | null;
  sortFn: NzTableSortFn | null;
  listOfFilter: NzTableFilterList;
  filterFn: NzTableFilterFn | null;
  filterMultiple: boolean;
  sortDirections: NzTableSortOrder[];
}

@Component({
  selector: 'app-dermatologists-filter',
  templateUrl: './dermatologists-filter.component.html',
  styleUrls: ['./dermatologists-filter.component.css']
})
export class DermatologistsFilterComponent implements OnInit {

  isVisible = false;
  search: string;
  dermatologistList: Dermatologist[] = [];
  nameFilter: any[] = [];
  surnameFilter : any[] = [];
  dermatologistId : number;
  dermatologistDifference : any[] = [];

  constructor(private modal: NzModalService, private toastr: ToastrService, private dermatologistService: DermatologistService, private pharmacyService : PharmacyService) { }

  ngOnInit(): void {
    this.getAllDermatologist();
  }

  searchDermatologist() {
    if (this.search === '') {
      this.getAllDermatologist();
    } else {
      this.dermatologistService.search(this.search).subscribe(result => {
        this.dermatologistList = result;
      });
    }
  }

  clearSearch() {
    this.search = '';
    this.getAllDermatologist();
  }

  getAllDermatologist() {
    this.dermatologistService.getAll().subscribe(result => {
      this.dermatologistList = result;

      for (let element of result) {
        let hasSame = false;
        for (let element2 of this.nameFilter) {
          if (element.name === element2.value) {
            hasSame = true;
            break;
          }
        }
        if (hasSame == false) {
          this.nameFilter.push({ text: element.name, value: element.name })
        }
      }

      for (let element of result) {
        let hasSameSurname = false;
        for (let element2 of this.surnameFilter) {
          if (element.surname === element2.value) {
            hasSameSurname = true;
            break;
          }
        }
        if (hasSameSurname == false) {
          this.surnameFilter.push({ text: element.surname, value: element.surname })
        }
      }
      this.initListOfColumns();
    }, () => { this.toastr.error("An error has occurred") });
  }

  listOfColumns: ColumnItem[] = [];

  initListOfColumns(){
    this.listOfColumns = [
      {
        name: 'Name',
        sortOrder: 'descend',
        sortFn: (a: DataItem, b: DataItem) => { return a.name.localeCompare(b.name)},
        sortDirections: ['ascend', 'descend', null],
        filterMultiple: true,
        listOfFilter: this.nameFilter /* [
          { text: 'Joe', value: 'Joe' },
          { text: 'Jim', value: 'Jim' }
        ]*/,
        filterFn: (list: string[], item: DataItem) => list.some(name => item.name.indexOf(name) !== -1)
      },
      {
        name: 'Surname',
        sortOrder: null,
        sortFn: (a: DataItem, b: DataItem) => a.name.localeCompare(b.name),
        sortDirections: ['ascend', 'descend', null],
        filterMultiple: true,
        listOfFilter: this.surnameFilter/*[
          { text: 'Joe', value: 'Joe' },
          { text: 'Jim', value: 'Jim' }
        ]*/,
        filterFn: (list: string[], item: DataItem) => list.some(name => item.name.indexOf(name) !== -1)
      },
      {
        name: 'Mark',
        sortOrder: 'descend',
        sortFn: (a: DataItem, b: DataItem) => { return a.mark - b.mark; },
        sortDirections: ['ascend', 'descend', null],
        filterMultiple: true,
        listOfFilter: [
          { text: '0-1', value: 1 },
          { text: '1-2', value: 2 },
          { text: '2-3', value: 3 },
          { text: '3-4', value: 4 },
          { text: '4-5', value: 5 },
          { text: '5-6', value: 6 },
          { text: '6-7', value: 7 },
          { text: '7-8', value: 8 },
          { text: '8-9', value: 9 },
          { text: '9-10', value: 10 }
        ],
        filterFn: (list: number[], item: DataItem) => list.some(mark => Number(item.mark) < mark && Number(item.mark) > mark - 1),
      },
      {
        name: 'Pharmacy',
        sortOrder: null,
        sortDirections: ['ascend', 'descend', null],
        sortFn: (a: DataItem, b: DataItem) => a.pharmacy.length - b.pharmacy.length,
        filterMultiple: false,
        listOfFilter: [
          { text: 'London', value: 'London' },
          { text: 'Sidney', value: 'Sidney' }
        ],
        filterFn: (address: string, item: DataItem) => item.pharmacy.indexOf(address) !== -1
      }
    ];
  }
}
