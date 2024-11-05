import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'SidebarComp',
  templateUrl: './SidebarComp.html',
  styleUrls: ['./SidebarComp.scss'],
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  providers: [],
})
export class SidebarComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }
}
