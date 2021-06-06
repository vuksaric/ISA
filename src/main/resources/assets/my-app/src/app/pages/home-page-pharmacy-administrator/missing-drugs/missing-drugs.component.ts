import { AuthService } from 'src/app/services/auth.service';
import { MedicineNotificationService } from './../../../services/medicine-notification.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-missing-drugs',
  templateUrl: './missing-drugs.component.html',
  styleUrls: ['./missing-drugs.component.css']
})
export class MissingDrugsComponent implements OnInit {

  listOfNotifications : any[] = [];
  pharmacyId : any;

  constructor(private medicineNotificationService: MedicineNotificationService, private authService : AuthService) { }

  ngOnInit(): void {
    let token = this.authService.getDataFromToken();
    this.pharmacyId = token.pharmacyId.toString(); 

    this.medicineNotificationService.getMedicineNotifications(this.pharmacyId).subscribe(data => {
      this.listOfNotifications = data;
    })
  }

}
