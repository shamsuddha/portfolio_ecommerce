import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
 selector: 'SearchComp',
 templateUrl: './SearchComp.html',
 styleUrls: ['./SearchComp.scss'],
 standalone: true,
 imports: [CommonModule, RouterOutlet],
 providers: [],
})
export class SearchComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}
