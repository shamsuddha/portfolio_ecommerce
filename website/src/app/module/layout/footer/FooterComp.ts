import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'FooterComp',
  templateUrl: './FooterComp.html',
  styleUrls: ['./FooterComp.scss'],
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  providers: [],
})
export class FooterComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}