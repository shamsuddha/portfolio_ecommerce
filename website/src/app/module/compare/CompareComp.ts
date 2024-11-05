import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
 selector: 'CompareComp',
 templateUrl: './CompareComp.html',
 styleUrls: ['./CompareComp.scss'],
 standalone: true,
 imports: [CommonModule],
 providers: [],
})
export class CompareComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}
