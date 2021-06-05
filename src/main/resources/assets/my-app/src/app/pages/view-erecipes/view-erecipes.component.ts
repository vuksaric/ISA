import { Component, OnInit } from '@angular/core';
import { NzTableSortOrder, NzTableFilterList, NzTableFilterFn, NzTableSortFn } from 'ng-zorro-antd/table';
import { AuthService } from 'src/app/services/auth.service';
import { PatientChartService } from 'src/app/services/patient-chart.service';



@Component({
  selector: 'app-view-erecipes',
  templateUrl: './view-erecipes.component.html',
  styleUrls: ['./view-erecipes.component.css']
})
export class ViewErecipesComponent implements OnInit {
  isVisible = false;
  dataFromToken : any;
  listOfData =[];
  dataSet=[];
  listOfColumn  = [
    {
      title: 'ERecipe date',
      sortOrder: null,
      sortFn: (a: any, b: any) => Date.parse(a.date) - Date.parse(b.date),
     
    }];
    listOfColumn1  = [
    {
      title: 'Status',
      listOfFilter: [
        { text: 'New', value: 'New' },
        { text: 'Processed', value: 'Processed' },
        { text: 'Rejected', value: 'Rejected' }

      ],
      filterFn: (list: string[], item: any) => list.some(status => item.status.indexOf(status) !== -1)
    }
  ];

  constructor(private patientChartService: PatientChartService, private authorizationService : AuthService) { }

  ngOnInit(): void {
    this.dataFromToken = this.authorizationService.getDataFromToken();

    this.patientChartService.getPatientERecipes(this.dataFromToken.id).subscribe(data=> {this.listOfData=data; console.log(data)})
  }
  medicines(data): void{
    this.isVisible = true;
    this.dataSet = data.medicines;
  }

 

  handleOk(): void {
    console.log('Button ok clicked!');
    this.isVisible = false;
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }
 
}
