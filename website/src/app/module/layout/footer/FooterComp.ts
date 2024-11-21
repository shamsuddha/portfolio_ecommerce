import { CommonModule } from '@angular/common';
import { Component, HostListener, OnInit } from '@angular/core';
import { MatBadgeModule } from '@angular/material/badge';

@Component({
  selector: 'FooterComp',
  templateUrl: './FooterComp.html',
  styleUrls: ['./FooterComp.scss'],
  standalone: true,
  imports: [CommonModule, MatBadgeModule],
  providers: [],
})
export class FooterComp implements OnInit {

  constructor() { }

  ngOnInit(): void { }

  // Show the button only when the user scrolls down
  @HostListener('window:scroll', [])
  onWindowScroll() {
    const goTopBtn = document.getElementById('goTopBtn') as HTMLElement;
    if (window.pageYOffset > 100) { // 100px from top
      goTopBtn.style.display = 'block';
    } else {
      goTopBtn.style.display = 'none';
    }
  }

  // Scroll to the top of the page when the button is clicked
  goToTop(): void {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
}