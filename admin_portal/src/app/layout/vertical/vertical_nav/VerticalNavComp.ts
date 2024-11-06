import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import MetisMenu from 'metismenujs';

class MenuItem {

  id: string = '0';
  label: string = '';
  icon: string = '';
  link: string = '/';
  subMenuItemList: Array<any> = [];

  constructor(id: string, label: string) {
    this.id = id;
    this.label = label;
  }
}

@Component({
  selector: 'VerticalNavComp',
  templateUrl: './VerticalNavComp.html',
  styleUrls: ['./VerticalNavComp.scss']
})
export class VerticalNavComp implements OnInit {

  //@ViewChild('sideMenu') sideMenu!: ElementRef;
  menu: any;
  menuItemList: Array<any> = [];

  constructor(private router: Router) {
    router.events.forEach((event) => {
      if (event instanceof NavigationEnd) {
        this._activateMenuDropdown();
      }
    });
  }

  ngOnInit(): void {
    this.menuItemList = [
      { id: '2', label: 'Dashboard', icon: 'fa-solid fa-house', link: '/authenticated/dashboard' },
      {
        id: '3', label: 'Multi nav', icon: 'fa-solid fa-house', subMenuItemList: [
          { id: '31', label: 'Level 1.1', link: '/about-us' },
          {
            id: '32', label: 'Level 1.2', subMenuItemList: [
              { id: '321', label: 'Level 2.1', link: '/contact-us' },
              { id: '323', label: 'Level 2.2', link: '/help' }
            ]
          },
        ]
      },

      { id: '4', label: 'Floor setup', icon: 'fa-solid fa-house', link: '/authenticated/floor-setup' },
      { id: '5', label: 'Bed setup', icon: 'fa-solid fa-house', link: '/authenticated/bed-setup' },

      { id: '6', label: 'Role setup', icon: 'fa-solid fa-house', link: '/role-setup' },
      
    ];
  }

  ngAfterViewInit() {
    this.menu = new MetisMenu('#side-menu');
    this._activateMenuDropdown();
  }

  hasItems(item: any): boolean {
    return item.subMenuItemList && item.subMenuItemList.length > 0;
  }

  _removeAllClass(className: any) {
    const els = document.getElementsByClassName(className);
    while (els[0]) {
      els[0].classList.remove(className);
    }
  }

  _activateMenuDropdown() {
    this._removeAllClass('mm-active');
    this._removeAllClass('mm-show');
    const links: any = document.getElementsByClassName('side-nav-link-ref');
    let menuItemEl = null;
    const paths = [];
    for (let i = 0; i < links.length; i++) {
      paths.push(links[i]['pathname']);
    }
    var itemIndex = paths.indexOf(window.location.pathname);
    if (itemIndex === -1) {
      const strIndex = window.location.pathname.lastIndexOf('/');
      const item = window.location.pathname.substr(0, strIndex).toString();
      menuItemEl = links[paths.indexOf(item)];
    } else {
      menuItemEl = links[itemIndex];
    }
    if (menuItemEl) {
      menuItemEl.classList.add('active');
      const parentEl = menuItemEl.parentElement;
      if (parentEl) {
        parentEl.classList.add('mm-active');
        const parent2El = parentEl.parentElement.closest('ul');
        if (parent2El && parent2El.id !== 'side-menu') {
          parent2El.classList.add('mm-show');
          const parent3El = parent2El.parentElement;
          if (parent3El && parent3El.id !== 'side-menu') {
            parent3El.classList.add('mm-active');
            const childAnchor = parent3El.querySelector('.has-arrow');
            const childDropdown = parent3El.querySelector('.has-dropdown');
            if (childAnchor) {
              childAnchor.classList.add('mm-active');
            }
            if (childDropdown) {
              childDropdown.classList.add('mm-active');
            }
            const parent4El = parent3El.parentElement;
            if (parent4El && parent4El.id !== 'side-menu') {
              parent4El.classList.add('mm-show');
              const parent5El = parent4El.parentElement;
              if (parent5El && parent5El.id !== 'side-menu') {
                parent5El.classList.add('mm-active');
                const childanchor = parent5El.querySelector('.is-parent');
                if (childanchor && parent5El.id !== 'side-menu') {
                  childanchor.classList.add('mm-active');
                }
              }
            }
          }
        }
      }
    }
  }
}
