import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidebarComp } from "../layout/sidebar/SidebarComp";

@Component({
 selector: 'ShopComp',
 templateUrl: './ShopComp.html',
 styleUrls: ['./ShopComp.scss'],
 standalone: true,
 imports: [CommonModule, RouterOutlet, SidebarComp],
 providers: [],
})
export class ShopComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}
