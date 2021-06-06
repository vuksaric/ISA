import { AuthService } from 'src/app/services/auth.service';
import { PharmacyService } from 'src/app/services/pharmacy.service';
import { Dermatologist } from './../../../models/Dermatologist';
import { DermatologistService } from 'src/app/services/dermatologist.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NzTableFilterFn, NzTableFilterList, NzTableSortFn, NzTableSortOrder } from 'ng-zorro-antd/table';

interface Person {
  key: string;
  name: string;
  surname: string;
  phone: string;
  gender: string;
  birthday: string;
}

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
  selector: 'app-dermatologist-list',
  templateUrl: './dermatologist-list.component.html',
  styleUrls: ['./dermatologist-list.component.css']
})
export class DermatologistListComponent implements OnInit {

  isVisible = false;
  isVisible2 = false;
  search: string;
  dermatologistList: Dermatologist[] = [];
  nameFilter: any[] = [];
  surnameFilter: any[] = [];
  dermatologistId: number;
  dermatologistDifference: any[] = [];
  start = new Date();
  end = new Date();
  pharmacyList : any[] = [];
  pharmacyId : any;

  constructor(private modal: NzModalService, private toastr: ToastrService, private dermatologistService: DermatologistService, private pharmacyService: PharmacyService, private authService : AuthService) { }

  ngOnInit(): void {
    let token = this.authService.getDataFromToken();
    this.pharmacyId = token.pharmacyId.toString(); 

    this.getAllDermatologist();
    this.getDifference();
  }

  searchDermatologist() {
    if (this.search === '') {
      this.getAllDermatologist();
    } else {
      this.pharmacyService.searchDermatologistInPharmacy(this.search, this.pharmacyId).subscribe(result => {
        this.dermatologistList = result;
      });
    }
  }

  getDifference() {
    this.pharmacyService.getDifferenceDermatologist(this.pharmacyId).subscribe(data => {
      this.dermatologistDifference = data;
    })
  }

  clearSearch() {
    this.search = '';
    this.getAllDermatologist();
  }

  addDermatologist() {
    if (this.dermatologistId != undefined) {
      const body = {
        startTime: this.start,
        endTime: this.end,
        pharmacyId: 1 
      }
      this.pharmacyService.addDermatologistInPharmacy(this.pharmacyId, this.dermatologistId, body).subscribe(data => {
        console.log(this.start);
        console.log(this.end);
        this.getAllDermatologist();
        this.toastr.success("Successfully added!");
        this.isVisible = false;
        this.getDifference();
      })
    }
  }

  getAllDermatologist() {
    this.pharmacyService.getDermatologistsFromPharmacy(this.pharmacyId).subscribe(result => {
      console.log(result);
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

  showPharmaciesDialog(data){
    this.pharmacyList = [];
    this.pharmacyList.join(data.pharmacyDTOList);
    console.log(data);
    for(var e of data.pharmacyDTOList){
      this.pharmacyList.push(e);
    }
    console.log(this.pharmacyList);
    this.isVisible2 = true;
  }

  onOk(){
    this.isVisible2 = false;
  }

  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    this.isVisible = false;
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  showDeleteConfirm(data): void {
    this.modal.confirm({
      nzTitle: 'Are you sure delete this pharmacist?',
      nzOkText: 'Yes',
      nzOkType: 'primary',
      nzOkDanger: true,
      nzOnOk: () => this.deleteDermatologist(data.id),
      nzCancelText: 'No',
      nzOnCancel: () => console.log('Cancel')
    });
  }

  deleteDermatologist(id: number) {
    this.pharmacyService.removeDermatologistInPharmacy(id, this.pharmacyId).subscribe(data => {
      this.getAllDermatologist();
      this.getDifference();
      this.toastr.success("Successfully deleted!");
    })
  }

  listOfColumns: ColumnItem[] = [];

  initListOfColumns() {
    this.listOfColumns = [
      {
        name: 'Name',
        sortOrder: 'descend',
        sortFn: (a: DataItem, b: DataItem) => { return a.name.localeCompare(b.name) },
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
        filterFn: (list: number[], item: DataItem) => list.some(mark => Number(item.mark) <= mark && Number(item.mark) > mark - 1),
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
