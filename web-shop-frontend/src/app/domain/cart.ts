import { itemDTO } from './itemDTO';

export class Cart {
    orderLines: itemDTO[];

    constructor() {
        this.orderLines = [];
    }
}