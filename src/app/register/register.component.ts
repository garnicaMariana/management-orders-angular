import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Channel, Customer, Order, Subsidiary } from '../order';
import { OrderService } from '../order.service';

declare var swal: any;

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  order: Order = new Order();

  @ViewChild('customer', { static: true }) customerElement: ElementRef;
  @ViewChild('channel', { static: true }) channelElement: ElementRef;
  @ViewChild('subsidiary', { static: true }) subsidiaryElement: ElementRef;

  customer: string = "";
  channel: string = "";
  subsidiary: string = "";



  constructor(
    private orderService: OrderService, 
    private router: Router) {
  }

  ngOnInit(): void {

  }

  saveOrder() {
        
    this.orderService.getCustomerByNumber(this.customer).subscribe(data => {
      this.order.customer = data;
    },error => console.log(error));

    this.orderService.getChannelByName(this.channel).subscribe(data => {
      this.order.channel = data;
    },error => console.log(error));

    this.orderService.getSubsidiaryByName(this.subsidiary).subscribe(data => {
      this.order.subsidiary = data;
    },error => console.log(error));


    if(this.order.channel!=null &&  this.order.subsidiary!=null){
      this.orderService.registerOrder(this.order).subscribe(data => {
        this.goToOrdersList();
        swal("The user has been successfully created");
    }, error => console.log("Error", error));

    }
    
  }

  goToOrdersList(){
    this.router.navigate(["/orders"]);
  }

  onSubmit(): void {
    this.saveOrder();
  }

}
