import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
 selector: 'RegistrationComp',
 templateUrl: './RegistrationComp.html',
 styleUrls: ['./RegistrationComp.scss'],
 standalone: true,
 imports: [CommonModule, RouterOutlet],
 providers: [],
})
export class RegistrationComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}
