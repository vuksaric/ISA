import { AuthService } from 'src/app/services/auth.service';
import { BillService } from './../../../services/bill.service';
import { ExaminationService } from 'src/app/services/examination.service';
import { Component, OnInit, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { PharmacyService } from 'src/app/services/pharmacy.service';
import { NgxChartsModule } from '@swimlane/ngx-charts';


@Component({
  selector: 'app-pharmacy-report',
  templateUrl: './pharmacy-report.component.html',
  styleUrls: ['./pharmacy-report.component.css']
})
export class PharmacyReportComponent implements OnInit {

  mark: number;
  listOfWorkers: Worker[];
  list2: Worker[] = [];
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Month';
  showYAxisLabel = true;
  yAxisLabel = 'Count';
  chart: any[] = [];
  chart2: any[] = [];
  loaded = false;
  pharmacyId : any;

  view: any[] = [550, 300];

  xAxisLabel2 = 'Quarter';
  yAxisLabel2 = 'Count';
  chart3: any[] = [];
  chart4: any[] = [];

  xAxisLabel3 = 'Year';
  yAxisLabel3 = 'Count';
  chart5: any[] = [];
  chart6: any[] = [];


  loaded2 = false;
  chart7: any[] = [];
  chart8: any[] = [];

  chart9: any[] = [];
  chart10: any[] = [];

  chart11: any[] = [];
  chart12: any[] = [];

  loaded3 = false;
  chart13: any[] = [];
  chart14: any[] = [];

  chart15: any[] = [];
  chart16: any[] = [];

  chart17: any[] = [];
  chart18: any[] = [];

  colorScheme = {
    domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA', '#009900', '#0099ff', '#ff3300', '#ffff00', '#ff00ff', '#000099', '#996633', '#666699']
  };

  onSelect(event) {
    console.log(event);
  }

  constructor(private pharmacyService: PharmacyService, private examinationService: ExaminationService, private billService: BillService, private authService : AuthService) { }

  ngOnInit(): void {
    let token = this.authService.getDataFromToken();
    this.pharmacyId = token.pharmacyId.toString(); 
    this.getExaminationReport('12');
    this.getBillReport();
    this.getIncomeReport();
    this.getMark();
    this.getAllPharmacists();
  }

  getMark() {
    this.pharmacyService.getPharmacyMark(this.pharmacyId).subscribe(data => {
      this.mark = data;
    })
  }

  getAllPharmacists() {
    this.pharmacyService.getPharmacistsFromPharmacy(this.pharmacyId).subscribe(data => {
      for (let element of data) {
        this.list2.push(element);
      }
      this.getAllDermatologists();
    })
  }

  getAllDermatologists() {
    this.pharmacyService.getDermatologistsFromPharmacy(this.pharmacyId).subscribe(data => {
      for (let element of data) {
        this.list2.push(element);
      }
      this.listOfWorkers = this.list2;
    })
  }

  getExaminationReport(mode) {
    this.examinationService.getAllExaminationByMonth(this.pharmacyId, mode).subscribe(data => {
      console.log(data);
      this.chart2.push({ "name": "January", "value": data[0] })
      this.chart2.push({ "name": "February", "value": data[1] })
      this.chart2.push({ "name": "March", "value": data[2] })
      this.chart2.push({ "name": "April", "value": data[3] })
      this.chart2.push({ "name": "May", "value": data[4] })
      this.chart2.push({ "name": "Jun", "value": data[5] })
      this.chart2.push({ "name": "Jul", "value": data[6] })
      this.chart2.push({ "name": "August", "value": data[7] })
      this.chart2.push({ "name": "September", "value": data[8] })
      this.chart2.push({ "name": "October", "value": data[9] })
      this.chart2.push({ "name": "November", "value": data[10] })
      this.chart2.push({ "name": "December", "value": data[11] })
      this.chart = this.chart2;

      this.chart3.push({ "name": "1", "value": data[0] + data[1] + data[2] })
      this.chart3.push({ "name": "2", "value": data[3] + data[4] + data[5] })
      this.chart3.push({ "name": "3", "value": data[6] + data[7] + data[8] })
      this.chart3.push({ "name": "4", "value": data[9] + data[10] + data[11] })
      this.chart4 = this.chart3;

      this.chart5.push({ "name": "2021", "value": data[0] + data[1] + data[2] + data[3] + data[4] + data[5] + data[6] + data[7] + data[8] + data[9] + data[10] + data[11] })
      this.chart6 = this.chart5;

      this.loaded = true;
      console.log(this.chart);
      console.log(this.chart2);
    })
  }

  getBillReport() {
    this.billService.getBillReport(this.pharmacyId).subscribe(data => {
      this.chart7.push({ "name": "January", "value": data[0] })
      this.chart7.push({ "name": "February", "value": data[1] })
      this.chart7.push({ "name": "March", "value": data[2] })
      this.chart7.push({ "name": "April", "value": data[3] })
      this.chart7.push({ "name": "May", "value": data[4] })
      this.chart7.push({ "name": "Jun", "value": data[5] })
      this.chart7.push({ "name": "Jul", "value": data[6] })
      this.chart7.push({ "name": "August", "value": data[7] })
      this.chart7.push({ "name": "September", "value": data[8] })
      this.chart7.push({ "name": "October", "value": data[9] })
      this.chart7.push({ "name": "November", "value": data[10] })
      this.chart7.push({ "name": "December", "value": data[11] })
      this.chart8 = this.chart7;

      this.chart9.push({ "name": "1", "value": data[0] + data[1] + data[2] })
      this.chart9.push({ "name": "2", "value": data[3] + data[4] + data[5] })
      this.chart9.push({ "name": "3", "value": data[6] + data[7] + data[8] })
      this.chart9.push({ "name": "4", "value": data[9] + data[10] + data[11] })
      this.chart10 = this.chart9;

      this.chart11.push({ "name": "2021", "value": data[0] + data[1] + data[2] + data[3] + data[4] + data[5] + data[6] + data[7] + data[8] + data[9] + data[10] + data[11] })
      this.chart12 = this.chart11;

      this.loaded2 = true;
    })
  }

  getIncomeReport(){
    this.billService.getIncomeReport(this.pharmacyId).subscribe(data=> { 
      this.chart13.push({ "name": "January", "value": data[0] })
      this.chart13.push({ "name": "February", "value": data[1] })
      this.chart13.push({ "name": "March", "value": data[2] })
      this.chart13.push({ "name": "April", "value": data[3] })
      this.chart13.push({ "name": "May", "value": data[4] })
      this.chart13.push({ "name": "Jun", "value": data[5] })
      this.chart13.push({ "name": "Jul", "value": data[6] })
      this.chart13.push({ "name": "August", "value": data[7] })
      this.chart13.push({ "name": "September", "value": data[8] })
      this.chart13.push({ "name": "October", "value": data[9] })
      this.chart13.push({ "name": "November", "value": data[10] })
      this.chart13.push({ "name": "December", "value": data[11] })
      this.chart14 = this.chart13;

      this.chart15.push({ "name": "1", "value": data[0] + data[1] + data[2] })
      this.chart15.push({ "name": "2", "value": data[3] + data[4] + data[5] })
      this.chart15.push({ "name": "3", "value": data[6] + data[7] + data[8] })
      this.chart15.push({ "name": "4", "value": data[9] + data[10] + data[11] })
      this.chart16 = this.chart15;

      this.chart17.push({ "name": "2021", "value": data[0] + data[1] + data[2] + data[3] + data[4] + data[5] + data[6] + data[7] + data[8] + data[9] + data[10] + data[11] })
      this.chart18 = this.chart17;

      this.loaded3 = true;
    })
  }

}
