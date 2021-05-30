import { Component, OnInit } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import { ToastrService } from 'ngx-toastr';
import { getISOWeek } from 'date-fns';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

interface Promotion {
  start: string;
  end: string;
  note: string;
}

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

  constructor(private modal: NzModalService, private toastr: ToastrService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      text: [null, [Validators.required]],
      duration: [null, [Validators.required]],
    });
  }

  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    if(this.validateForm.valid){
      this.toastr.success('Successfully registered!');
      this.isVisible = false;
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

  listOfData: Promotion[] = [
    {
      start: '32',
      end: 'New York No. 1 Lake Park',
      note: 'proba'
    },
    {
      start: '32',
      end: 'New York No. 1 Lake Park',
      note: 'proba'
    },
    {
      start: '32',
      end: 'New York No. 1 Lake Park',
      note: 'proba'
    }
  ];

}
