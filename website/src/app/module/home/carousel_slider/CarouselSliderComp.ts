import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'CarouselSliderComp',
    templateUrl: 'CarouselSliderComp.html',
    styleUrls: ['CarouselSliderComp.scss'],
    standalone: true,
    imports: [CommonModule]
})

export class CarouselSliderComp implements OnInit {

    currentSlide = 0;
   // autoSlideInterval: any;
    slides = [
        {
            image: 'assets/images/slider/slider-one.jpg',
        },
        {
            image: 'assets/images/slider/slider-two.jpg',
        },
        {
            image: 'assets/images/slider/slider-three.jpg',
        }
    ];

    constructor() { }

    ngOnInit() {
        // // Start auto-sliding with 1-second interval
        // this.autoSlideInterval = setInterval(() => {
        //     this.nextSlide();
        // }, 5000); // Change slide every 1 second
    }

    // ngOnDestroy() {
    //     // Clear the interval when the component is destroyed to prevent memory leaks
    //     if (this.autoSlideInterval) {
    //         clearInterval(this.autoSlideInterval);
    //     }
    // }

    // Get the CSS transform property for sliding effect
    getSlideTransform() {
        return `translateX(-${this.currentSlide * 100}%)`;
    }

    // Go to the next slide
    nextSlide() {
        this.currentSlide = (this.currentSlide + 1) % this.slides.length;
    }

    // Go to the previous slide
    prevSlide() {
        this.currentSlide = (this.currentSlide - 1 + this.slides.length) % this.slides.length;
    }

}