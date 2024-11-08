import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'DashboardComp',
  templateUrl: 'DashboardComp.html',
  styleUrls: ['DashboardComp.scss'],
  standalone: true,
  imports: [CommonModule]
})

export class DashboardComp implements OnInit {

  constructor() { }

  ngOnInit() { }

}