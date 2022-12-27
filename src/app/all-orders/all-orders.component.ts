import {Order} from './../order';
import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { OrderService } from '../order.service';
import { Router } from '@angular/router';
import { combineLatest, map, Subject } from 'rxjs';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { DataTableDirective } from 'angular-datatables';


declare var swal: any;
const moment = require('moment');

@Component({
  selector: 'app-all-orders',
  templateUrl: './all-orders.component.html',
  styleUrls: ['./all-orders.component.css']
  
})

export class AllOrdersComponent implements OnInit{
  
  @ViewChild(DataTableDirective, {static: false})
  dtElement: DataTableDirective;

  orders: Order[];
  dtTrigger: Subject<any> = new Subject<any>();
  dtOptions: DataTables.Settings = {};

  dateRange: any;
  range: any;

  constructor(
    private orderService:OrderService, 
    private router:Router){}

    startDatePicker = new Subject<MatDatepickerInputEvent<any>>();
    endDatePicker = new Subject<MatDatepickerInputEvent<any>>();

  ngOnInit(): void {
    this.getAllOrders();
    const dateChange$ = combineLatest([this.startDatePicker, this.endDatePicker]).pipe(
      map(([a$, b$]) => ({
        start: a$,
        end: b$
      }))
    )

    dateChange$.subscribe((data: { start: { value: any; }; end: { value: any; }; }) => {
      if (data.start.value && data.end.value) {
        //this.router.navigate(['orders/time', moment(data.start.value).format('YYYY-MM-DD'), moment(data.end.value).format('YYYY-MM-DD')]);
        this.orderService.getAllOrdersByDate( moment(data.start.value).format('YYYY-MM-DD'),moment(data.end.value).format('YYYY-MM-DD') ).subscribe(data=>{
          this.orders=data;
          this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
            dtInstance.destroy();
            this.dtTrigger.next(this.orders);
          });
        })
      }
    });

  }

  updateOrder(id:number){
    this.router.navigate(['update-order', id]);
  }

  deleteOrder(id:number){
    this.orderService.deleteOrder(id).subscribe(data => {
      this.router.navigate(['/orders']);
      swal("The user has been successfully deleted");
    },error => swal("The item cannot be deleted"));

  }

  private getAllOrders(){
    this.orderService.getAllOrders().subscribe(data=>{
      this.orders=data;  
      this.dtTrigger.next(this.orders);
    })
  }

}
