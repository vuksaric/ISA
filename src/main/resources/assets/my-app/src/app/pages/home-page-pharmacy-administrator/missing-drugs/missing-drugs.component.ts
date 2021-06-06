import { MedicineNotificationService } from './../../../services/medicine-notification.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-missing-drugs',
  templateUrl: './missing-drugs.component.html',
  styleUrls: ['./missing-drugs.component.css']
})
export class MissingDrugsComponent implements OnInit {

  listOfNotifications : any[] = [];

  constructor(private medicineNotificationService: MedicineNotificationService) { }

  ngOnInit(): void {
    this.medicineNotificationService.getMedicineNotifications('1').subscribe(data => {
      this.listOfNotifications = data;
    })
  }

}
