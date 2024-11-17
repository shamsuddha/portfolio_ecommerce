import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidebarComp } from "../layout/sidebar/SidebarComp";
import { MatTabsModule } from '@angular/material/tabs';

@Component({
  selector: 'SingleProductComp',
  templateUrl: './SingleProductComp.html',
  styleUrls: ['./SingleProductComp.scss'],
  standalone: true,
  imports: [CommonModule, SidebarComp, MatTabsModule],
  providers: [],
})
export class SingleProductComp implements OnInit {

  selectedSize: string | null = null;
  selectedColor: string | null = null;

  constructor() { }

  ngOnInit(): void { }

  // Array of image URLs
  images = [
    'assets/images/product/product-one.jpg',
    'assets/images/product/product-two.jpg',
    'assets/images/product/product-one.jpg',
  ];

  // Current selected image index
  currentIndex = 0;

  // Navigate to previous image
  prev() {
    this.currentIndex = (this.currentIndex > 0) ? this.currentIndex - 1 : this.images.length - 1;
  }

  // Navigate to next image
  next() {
    this.currentIndex = (this.currentIndex < this.images.length - 1) ? this.currentIndex + 1 : 0;
  }

  // Change the main image
  selectImage(index: number) {
    this.currentIndex = index;
  }
  selectSize(size: string) {
    this.selectedSize = size;
  }

  selectColor(color: string) {
    this.selectedColor = color;
  }
}
