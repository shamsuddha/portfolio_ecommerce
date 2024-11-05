import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { SidebarComp } from "../layout/sidebar/SidebarComp";

@Component({
 selector: 'CategoryComp',
 templateUrl: './CategoryComp.html',
 styleUrls: ['./CategoryComp.scss'],
 standalone: true,
 imports: [CommonModule, SidebarComp],
 providers: [],
})
export class CategoryComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}
