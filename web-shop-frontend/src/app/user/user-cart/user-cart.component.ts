import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { Cart } from 'src/app/domain/cart';

@Component({
  selector: 'app-user-cart',
  templateUrl: './user-cart.component.html',
  styleUrls: ['./user-cart.component.css']
})
export class UserCartComponent implements OnInit {

  cart: Cart;

  constructor(
    private cartService: CartService
  ) { }

  ngOnInit() {
    this.cart = JSON.parse(localStorage.getItem('cart'));

    if (this.cart != null) {
      this.cartService.sendCart(this.cart).subscribe(
        response => {
          console.log(response);
        }
      )
    }
  }

}
