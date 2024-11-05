import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderTopComp } from './header_top/HeaderTopComp';
import { MenuComp } from "./menu/MenuComp";
import { MatMenuModule } from '@angular/material/menu';

@Component({
  selector: 'HeaderComp',
  templateUrl: './HeaderComp.html',
  styleUrls: ['./HeaderComp.scss'],
  standalone: true,
  imports: [CommonModule, RouterOutlet, MatMenuModule, HeaderTopComp, MenuComp],
  providers: [],
})
export class HeaderComp implements OnInit {

  constructor() { }

  ngOnInit(): void { };


}
