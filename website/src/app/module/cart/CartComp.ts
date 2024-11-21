import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'CartComp',
  templateUrl: './CartComp.html',
  styleUrls: ['./CartComp.scss'],
  standalone: true,
  imports: [CommonModule],
  providers: [],
})
export class CartComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}
