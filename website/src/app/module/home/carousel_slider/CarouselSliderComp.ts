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
    autoSlideInterval: any;
    slides = [
        {
            image: 'https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg?text=Slide+1',
            title: 'Save 30%',
            description: 'Special Price from bichitrobazar'
        },
        {
            image: 'https://gratisography.com/wp-content/uploads/2024/10/gratisography-cool-cat-800x525.jpg?text=Slide+2',
            title: 'Save 50%',
            description: 'Limited Time Offer to get this offer'
        },
        {
            image: 'https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg?text=Slide+3',
            title: 'New Collection',
            description: 'Discover Now our new collection'
        }
    ];

    constructor() { }

    ngOnInit() {
        // Start auto-sliding with 1-second interval
        this.autoSlideInterval = setInterval(() => {
            this.nextSlide();
        }, 5000); // Change slide every 1 second
    }

    ngOnDestroy() {
        // Clear the interval when the component is destroyed to prevent memory leaks
        if (this.autoSlideInterval) {
            clearInterval(this.autoSlideInterval);
        }
    }

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