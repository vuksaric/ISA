import { Component, OnInit } from '@angular/core';
import { OfferService } from 'src/app/services/offer.service';
import { ToastrService } from 'ngx-toastr';
import jwt_decode from 'jwt-decode';
import { OrderList } from 'src/app/models/orderList';
import { OrderListService } from 'src/app/services/order-list.service';
import { MedicineQuantity } from 'src/app/models/medicineQuantity';
import { Medicine } from 'src/app/models/medicine';

@Component({
  selector: 'app-new-offer',
  templateUrl: './new-offer.component.html',
  styleUrls: ['./new-offer.component.css']
})
export class NewOfferComponent implements OnInit {

  listOfData : OrderList[] = [];
  token: any;
  medicines_array: "probaaaa";
  td: HTMLTableDataCellElement;

  listOfColumn = [
    {
      title: 'Id',
      compare: (a: OrderList, b: OrderList) => a.id - b.id,
      priority: false
    },
    {
      title: 'Date',
      compare: (a: OrderList, b: OrderList) => a.dueDate > b.dueDate,
      priority: false,
      //filterMultiple: true,
    },
    {
      title: 'Medicines',
      compare: (a: OrderList, b: OrderList) => a.medicines.length - b.medicines.length,
      priority: false,
      //filterMultiple: true,
    },

  ];

  /*toArray(medicines: MedicineQuantity[]) {
    let medicineQuantity: MedicineQuantity[];
    //medicineQuantity.push(this.listOfData.find((orderList) => orderList.medicines === medicines)) ;
    //return this.listOfData.find((orderList) => orderList.medicines === medicines);
    return Object.keys(medicines).map(key => medicines[key]);
  }*/


  constructor(private orderListService: OrderListService ,private offerService: OfferService) { }

  loadOrders(): void{
    this.token = this.getDecodedAccessToken(localStorage.getItem('token'));
    console.log(this.token.email);
    this.orderListService.getAll().subscribe((orders: OrderList[] ) => {
      this.listOfData = orders;
      //this.td = <HTMLTableDataCellElement>document.getElementById('proba');
      //this.td.innerHTML = "string";
      /*orders.forEach(order => {
        order['medicines'].forEach((quan) => order[quan['medicine_name']] == quan['medicine_name'] );
      });*/ 
      console.log(orders);
    });
  }
  ngOnInit(): void {
    this.loadOrders();
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    }
    catch (Error) {
      return null;
    }
  }

}
