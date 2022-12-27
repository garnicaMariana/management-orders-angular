import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Order } from '../order';
import { OrderService } from '../order.service';
import { ActivatedRoute, Router } from '@angular/router';

declare var swal: any;
@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})

export class UpdateComponent implements OnInit {
  
  id:number;
  order:Order = new Order();

  customer: string = "";
  channel: string = "";
  subsidiary: string = "";

  
  constructor(
    private orderService:OrderService,
    private router:Router,private route:ActivatedRoute) { }


    
  ngOnInit(): void {

    this.id = this.route.snapshot.params['id'];
    this.orderService.getOrderById(this.id).subscribe(data =>{
    this.customer = data.customer.numCustomer;
    this.channel = data.channel.name;
    this.subsidiary = data.subsidiary.name;
    
    this.order = data;
    this.order.channel.name = this.channel;
    this.order.subsidiary.name = this.subsidiary;

  },error => console.log(error));
  }


  goToListOrders(){
    this.router.navigate(['/orders']);
    swal("The user has been successfully updated");
  }

  onSubmit(){

    this.order.channel.name = this.channel;
    this.order.subsidiary.name = this.subsidiary;
    
    this.orderService.getChannelByName(this.channel).subscribe(data => {
      this.order.channel = data;
      this.orderService.getSubsidiaryByName(this.subsidiary).subscribe(data => {
        
        this.order.subsidiary = data;
        this.orderService.updateOrder(this.id,this.order).subscribe(data => {
          this.goToListOrders();
        },error => swal("The data are not valid"));
      },error => swal("The data are not valid"));
    },error => swal("The data are not valid"));

  
  }


  
}
