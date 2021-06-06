import { PromotionsService } from './../../../services/promotions.service';
import { AuthService } from 'src/app/services/auth.service';
import { Promotion } from './../../../models/Promotion';
import { Component, OnInit } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import { ToastrService } from 'ngx-toastr';
import { getISOWeek } from 'date-fns';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { resourceUsage } from 'process';


@Component({
  selector: 'app-promotions',
  templateUrl: './promotions.component.html',
  styleUrls: ['./promotions.component.css']
})
export class PromotionsComponent implements OnInit {

  isVisible = false;
  validateForm!: FormGroup;
  date = null;
  start : Date;
  end : Date;
  listOfPromotions : Promotion[] = [];
  pharmacyId : any;

  constructor(private modal: NzModalService, private toastr: ToastrService, private fb: FormBuilder, private promotionService: PromotionsService, private authService : AuthService) { }

  ngOnInit(): void {
    let token = this.authService.getDataFromToken();
    this.pharmacyId = token.pharmacyId.toString(); 

    this.validateForm = this.fb.group({
      text: [null, [Validators.required]],
      duration: [null, [Validators.required]],
    });
  }

  showModal(): void {
    this.isVisible = true;
  }

  getAllPromotions(){
    this.promotionService.getAll().subscribe(result => {
      for(let el of result){
          let startSplitted = el.startDate.toString().split(",",3);
          el.startDate = startSplitted[2] + '.' + startSplitted[1] + '.' + startSplitted[0]; 
          let endSplitted = el.endDate.toString().split(",",3);
          el.endDate = endSplitted[2] + '.' + endSplitted[1] + '.' + endSplitted[0]; 
      }
      this.listOfPromotions = result;
    })
  }

  handleOk(): void {
    if(this.validateForm.valid){
      const body = {
        startDate: this.start,
        endDate: this.end,
        text: this.validateForm.get('text').value,
        pharmacyId : this.pharmacyId
      }
      this.promotionService.newPromotion(body).subscribe( result => {
        this.getAllPromotions();
        this.toastr.success('Successfully registered!');
        this.isVisible = false;
      })
    }
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  onChange(result: Date[]): void {
    console.log('onChange: ', result);
    Promise.resolve().then(() => this.validateForm.controls.duration.updateValueAndValidity());
    this.date = result;
    this.start = result[0];
    this.end = result[1];
    console.log(this.start + ' ' + this.end);
  }

  getWeek(result: Date[]): void {
    console.log('week: ', result.map(getISOWeek));
  }

  updateTextValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.text.updateValueAndValidity());
  }


}
