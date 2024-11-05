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

  constructor() { }

  ngOnInit(): void { }

  imageObject = [
    {
      image: 'https://via.placeholder.com/800x600', // Full-size image
      thumbImage: 'https://via.placeholder.com/400x300', // Thumbnail image
      title: 'Image 1'
    },
    {
      image: 'https://via.placeholder.com/800x600',
      thumbImage: 'https://via.placeholder.com/400x300',
      title: 'Image 2'
    },
    {
      image: 'https://via.placeholder.com/800x600',
      thumbImage: 'https://via.placeholder.com/400x300',
      title: 'Image 3'
    }
  ];
}
