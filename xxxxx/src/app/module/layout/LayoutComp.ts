import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComp } from "./header/HeaderComp";
import { FooterComp } from "./footer/FooterComp";

@Component({
  selector: 'LayoutComp',
  templateUrl: './LayoutComp.html',
  styleUrls: ['./LayoutComp.scss'],
  standalone: true,
  imports: [CommonModule, RouterOutlet, HeaderComp, FooterComp],
  providers: [],
})
export class LayoutComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}
