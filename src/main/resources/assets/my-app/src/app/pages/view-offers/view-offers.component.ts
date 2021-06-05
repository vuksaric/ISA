import { Component, OnInit } from '@angular/core';
import { Offer } from 'src/app/models/offer';
import { OfferService } from 'src/app/services/offer.service';
import { ToastrService } from 'ngx-toastr';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-view-offers',
  templateUrl: './view-offers.component.html',
  styleUrls: ['./view-offers.component.css']
})
export class ViewOffersComponent implements OnInit {

  searchValue2: string;
  listOfData : Offer[] = [];
  email: string;
  token: any;
  listOfColumn = [
    {
      title: 'Status',
      compare: (a: Offer, b: Offer) => a.status.localeCompare(b.status),
      priority: false
    },
    {
      title: 'Price',
      compare: (a: Offer, b: Offer) => a.price - b.price,
      priority: false,
      //filterMultiple: true,
    },
    {
      title: 'Date',
      compare: (a: Offer, b: Offer) => a.dueDate > b.dueDate,
      priority: false,
      //filterMultiple: true,
    },
  ];

  constructor(private offerService: OfferService) { }

  loadOffers(): void{
    this.token = this.getDecodedAccessToken(localStorage.getItem('token'));
    console.log(this.token.email);
    this.offerService.getAllBySupplier(this.token.email).subscribe((offers: Offer[] ) => {
      this.listOfData = offers;
      console.log(offers);
    });
  }

  ngOnInit(): void {
    this.loadOffers();
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
