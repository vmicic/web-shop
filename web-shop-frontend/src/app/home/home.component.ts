import { Component, OnInit } from '@angular/core';
import { AuthService } from '../shared/auth/auth.service';
import { Router } from '@angular/router';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {

    const role = this.authService.getUserRole();

    if(role === "ROLE_USER") {
      this.router.navigate(['home']);
    } else if(role === "ROLE_SELLER"){
      this.router.navigate(['seller']);
    } else if(role === "ROLE_SALES_MANAGER") {
      this.router.navigate(['manager'])
    }

  }

}
