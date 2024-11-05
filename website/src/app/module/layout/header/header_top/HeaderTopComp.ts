import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatMenuModule } from '@angular/material/menu';
import { RouterOutlet } from '@angular/router';

@Component({
 selector: 'HeaderTopComp',
 templateUrl: './HeaderTopComp.html',
 styleUrls: ['./HeaderTopComp.scss'],
 standalone: true,
 imports: [CommonModule, MatMenuModule],
 providers: [],
})
export class HeaderTopComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}
