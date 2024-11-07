import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatMenuModule } from '@angular/material/menu';

@Component({
  selector: 'MenuComp',
  templateUrl: './MenuComp.html',
  styleUrls: ['./MenuComp.scss'],
  standalone: true,
  imports: [CommonModule, MatMenuModule],
  providers: [],
})
export class MenuComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}
