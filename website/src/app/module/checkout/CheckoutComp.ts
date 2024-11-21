import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
 selector: 'CheckoutComp',
 templateUrl: './CheckoutComp.html',
 styleUrls: ['./CheckoutComp.scss'],
 standalone: true,
 imports: [CommonModule],
 providers: [],
})
export class CheckoutComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}
