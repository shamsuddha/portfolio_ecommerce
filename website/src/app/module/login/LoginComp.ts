import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
 selector: 'LoginComp',
 templateUrl: './LoginComp.html',
 styleUrls: ['./LoginComp.scss'],
 standalone: true,
 imports: [CommonModule, RouterOutlet],
 providers: [],
})
export class LoginComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}
