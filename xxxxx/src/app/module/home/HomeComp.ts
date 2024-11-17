import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { CarouselSliderComp } from "./carousel_slider/CarouselSliderComp";

export interface PhotosApi {
  albumId?: number;
  id?: number;
  title?: string;
  url?: string;
  thumbnailUrl?: string;
}

@Component({
  selector: 'HomeComp',
  templateUrl: './HomeComp.html',
  styleUrls: ['./HomeComp.scss'],
  standalone: true,
  imports: [CommonModule, MatTabsModule, CarouselSliderComp],
  providers: [],
})
export class HomeComp implements OnInit {

  constructor() { }

  ngOnInit(): void {

  }
}
