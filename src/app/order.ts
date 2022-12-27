
export class Order {
    id: number;
    channel: Channel;
    subsidiary: Subsidiary;
    customer: Customer
    orderDate: Date;
    transaction: String;

}

export class Customer {
    id: number;
    numCustomer:string;
}

export class Channel {
    id: number;
    name:string;
}

export class Subsidiary {
    id: number;
    name:string;
}
