import { Component, OnInit } from '@angular/core';
import { Pharmacy } from 'src/app/models/pharmacy';
import { PharmacyService } from 'src/app/services/pharmacy.service';
import { ToastrService } from 'ngx-toastr';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-view-subscribed-pharmacies',
  templateUrl: './view-subscribed-pharmacies.component.html',
  styleUrls: ['./view-subscribed-pharmacies.component.css']
})
export class ViewSubscribedPharmaciesComponent implements OnInit {
  token: any;
  id: number;
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
    },
    {
      title: 'Subscribe',
    }
  ];

  constructor(private toastr: ToastrService, private pharmacyService: PharmacyService) { }

  loadPharmacies(): void{
      this.pharmacyService.subscribedPharmacies(1).subscribe((pharmacies: Pharmacy[])=>{this.listOfData=pharmacies});  
  }

  ngOnInit(): void {
   
    
   this.loadPharmacies();
  }

  subscribeClick(id){
    this.token = this.getDecodedAccessToken(localStorage.getItem('token'));
    this.id = this.token.user_id;
    const body ={
      user_id : this.id,
      pharmacy_id : id
    }
    this.pharmacyService.deleteSubscription(body).subscribe(data =>{
      console.log(data);  
      if (data){
        this.toastr.success("You have successfully unsubscribed!");
        }else{
          this.toastr.error("You are already unsubscribed!");
        }
        
    });
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
