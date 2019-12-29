import { Stock } from './stock';

export class Transaction {
    id:number;
    soldItems={};
    date:Date = new Date();
    stockId:number;
}
