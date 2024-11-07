import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidebarComp } from "../layout/sidebar/SidebarComp";

@Component({
 selector: 'SingleProductComp',
 templateUrl: './SingleProductComp.html',
 styleUrls: ['./SingleProductComp.scss'],
 standalone: true,
 imports: [ CommonModule,  RouterOutlet,  SidebarComp],
 providers: [],
})
export class SingleProductComp implements OnInit {

  selectedSize: string | null = null;
  selectedColor: string | null = null;

  constructor() { }

  ngOnInit(): void { }

  // Array of image URLs
  images = [
    'https://plus.unsplash.com/premium_photo-1674641194949-e154719cdc02?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8c2xpZGVyfGVufDB8fDB8fHww',
    'https://www.w3rack.com/wp-content/uploads/2017/05/slider-img1.jpg',
    'https://soliloquywp.com/wp-content/uploads/2019/04/nb_esc_cover.jpg',
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
  selectSize(size: string){
    this.selectedSize = size;
  }

  selectColor(color: string){
    this.selectedColor = color;
  }
}
