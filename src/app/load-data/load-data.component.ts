import { Component } from '@angular/core';
import { read, utils, writeFile } from 'xlsx';
import { OrderService } from '../order.service';
import { Router } from '@angular/router';
import { Channel, Customer, Order, Subsidiary } from '../order';



@Component({
  selector: 'app-load-data',
  templateUrl: './load-data.component.html',
  styleUrls: ['./load-data.component.css']
})
export class LoadDataComponent {

  rows: any[] = [];
  elements: any[] = [];
  orders: Order[] = [];


  alertify: any;

  constructor(
    private orderService: OrderService, 
    private router: Router) {

  }


  loadFile($event: any) {
    const files = $event.target.files;
    if (files.length) {
      const file = files[0];
      const reader = new FileReader();
      reader.onload = (event: any) => {
        const wb = read(event.target.result);
        const sheets = wb.SheetNames;

        if (sheets.length) {
          const rows = utils.sheet_to_json(wb.Sheets[sheets[0]]);
          let i =0;
          this.rows = rows;
   
          rows.forEach(element => {

            let elementObject = <any> element;
            let order = new Order();
            
            this.orderService.getCustomerByNumber(elementObject.customer).subscribe(data => {
              order.customer = data;
              this.orderService.getChannelByName(elementObject.channel).subscribe(data => {
                order.channel = data;
                this.orderService.getSubsidiaryByName(elementObject.subsidiary).subscribe(data => {
                  console.log(data);
                  order.subsidiary = data;

                  console.log(order.customer);
                  console.log(order.channel);
                  console.log(order.subsidiary);

                  this.orders.push(order);

                  if(i==rows.length-1){
                    this.orderService.registerOrders(this.orders).subscribe(data => {
                      this.goToOrdersList();
                   }, error => console.log("Error", error));
                  }
                  i++;
                

                },error => console.log(error));
              },error => console.log(error));
            },error => console.log(error));
                
          });        
        }
      }
      reader.readAsArrayBuffer(file);
    }
  }

  
  save() {
    /*this.orderServicio.registerOrders(this.elements).subscribe(data => {
      this.goToOrdersList();
    }, error => console.log("Error", error));
*/
  }

  goToOrdersList(){
    this.router.navigate(["/orders"]);
  }

}
