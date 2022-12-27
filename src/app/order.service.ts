import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Channel, Customer, Order, Subsidiary } from './order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseURL ="http://localhost:8080/api/v1/orders";
  private baseURLChannel ="http://localhost:8080/api/v1";

  
  constructor(private httpClient: HttpClient) { }

  getAllOrders():Observable<Order[]>{    
    return this.httpClient.get<Order[]>(`${this.baseURL}`);
  }

  getAllOrdersByDate(startDate:Date, endDate:Date):Observable<Order[]>{    
    return this.httpClient.get<Order[]>(`${this.baseURL}`+`/time/`+startDate+"/"+endDate);
  }

  registerOrder(order:Order):Observable<Object>{  
    return this.httpClient.post(`${this.baseURL}`, order);

  }

  updateOrder(id:number, order:Order):Observable<Object>{    
    return this.httpClient.put(`${this.baseURL}/${id}`, order);
  }

  deleteOrder(id:number):Observable<Object>{    
    return this.httpClient.delete(`${this.baseURL}/delete/${id}`);
  }

  getOrderById(id:number):Observable<Order>{
    return this.httpClient.get<Order>(`${this.baseURL}/${id}`);
  }

  registerOrders(orders: Array<Object>): Observable<Object> {
    console.log(orders)
    return this.httpClient.post(`${this.baseURL}`+`/load`, orders).pipe(map((data: any) => {
      return data;
    }));
  }

  getChannelByName(name:string):Observable<Channel>{
    return this.httpClient.get<Channel>(`${this.baseURLChannel}/getChannel/${name}`);
  }

  getSubsidiaryByName(name:string):Observable<Subsidiary>{
    return this.httpClient.get<Subsidiary>(`${this.baseURLChannel}/getSubsidiary/${name}`);
  }

  getCustomerByNumber(number:string):Observable<Customer>{
    return this.httpClient.get<Customer>(`${this.baseURLChannel}/getCustomer/${number}`);
  }

}
