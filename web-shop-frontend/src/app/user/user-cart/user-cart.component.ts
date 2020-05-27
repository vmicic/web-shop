import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { Cart } from 'src/app/domain/cart';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-user-cart',
  templateUrl: './user-cart.component.html',
  styleUrls: ['./user-cart.component.css']
})
export class UserCartComponent implements OnInit {

  cart: Cart;

  order: any;

  orderFinal: any;
  orderConfirmed: boolean = false;

  bonusPointsForm: FormGroup;

  constructor(
    private cartService: CartService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.bonusPointsForm = this.formBuilder.group({
      bonusPoints: ['']
    });

    this.cart = JSON.parse(localStorage.getItem('cart'));

    if (this.cart != null) {
      this.cartService.sendCart(this.cart).subscribe(
        response => {
          console.log(response);
          this.order = response.body;
          console.log(this.order);
        }
      )
    }
  }

  onSubmit() {
    console.log("submiting form");
    let awardPoints;
    if(this.bonusPointsForm.controls['bonusPoints'].value == null) {
      awardPoints = 0;
    } else {
      awardPoints = this.bonusPointsForm.controls['bonusPoints'].value;
    }
    this.orderConfirmed = true;
    this.cartService.sendAwardPoints(this.order.orderId, awardPoints).subscribe(
      response => {
        this.orderFinal = response.body;
      }
    )
  }

}
