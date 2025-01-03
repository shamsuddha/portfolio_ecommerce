import {AfterViewInit, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import {HorizontalNav} from './HorizontalNav';
import {LAYOUT_MODE} from "../../layouts.model";
import {EventService} from "../../../service/EventService";
import {AuthenticationService} from "../../../service/AuthenticationService";

@Component({
  selector: 'HorizontalNavComp',
  templateUrl: './HorizontalNavComp.html',
  styleUrls: ['./HorizontalNavComp.scss'],
})
export class HorizontalNavComp implements OnInit, AfterViewInit {

  mode: string | undefined;
  layoutMode!: string;
  menuItems: Array<any> = [];
  element: any;
  flagvalue: any;
  cookieValue: any;
  countryName: any;
  valueset: any;
  listLang = [
    {text: 'English', flag: 'assets/images/flags/us.jpg', lang: 'en'},
    {text: 'Spanish', flag: 'assets/images/flags/spain.jpg', lang: 'es'},
    {text: 'German', flag: 'assets/images/flags/germany.jpg', lang: 'de'},
    {text: 'Italian', flag: 'assets/images/flags/italy.jpg', lang: 'it'},
    {text: 'Russian', flag: 'assets/images/flags/russia.jpg', lang: 'ru'},
  ];
  @Output() settingsButtonClicked = new EventEmitter();
  @Output() mobileMenuButtonClicked = new EventEmitter();

  constructor(
    private router: Router,
    private eventService: EventService,
    private authService: AuthenticationService,
  ) {
    router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.activateMenu();
      }
    });
  }

  setLanguage(text: string, lang: string, flag: string) {
    this.countryName = text;
    this.flagvalue = flag;
    this.cookieValue = lang;
  }

  ngOnInit(): void {
    this.element = document.documentElement;
    this.layoutMode = LAYOUT_MODE;
    this.initialize();
    this.cookieValue = 'en';
    const val = this.listLang.filter(x => x.lang === this.cookieValue);
    this.countryName = val.map(element => element.text);
    if (val.length === 0) {
      if (this.flagvalue === undefined) {
        this.valueset = 'assets/images/flags/us.jpg';
      }
    } else {
      this.flagvalue = val.map(element => element.flag);
    }
  }

  initialize(): void {
    this.menuItems = HorizontalNav;
  }

  /**
   * Returns true or false if given menu item has child or not
   * @param item menuItem
   */
  hasItems(item: any) {
    return item.subItems !== undefined ? item.subItems.length > 0 : false;
  }

  /**
   * on settings button clicked from topbar
   */
  onSettingsButtonClicked() {
    document.body.classList.toggle('right-bar-enabled');
  }

  /**
   * On menu click
   */
  onMenuClick(event: any) {
    const nextEl = event.target.nextElementSibling;
    if (nextEl) {
      const parentEl = event.target.parentNode;
      if (parentEl) {
        parentEl.classList.remove('show');
      }
      nextEl.classList.toggle('show');
    }
    return false;
  }

  ngAfterViewInit() {
    this.activateMenu();
  }

  /**
   * remove active and mm-active class
   */
  _removeAllClass(className: any) {
    const els = document.getElementsByClassName(className);
    while (els[0]) {
      els[0].classList.remove(className);
    }
  }

  /**
   * Topbar Light-Dark Mode Change
   */
  changeMode(mode: string) {
    this.mode = mode;
    this.layoutMode = mode;
    this.eventService.broadcast('changeMode', mode);
  }

  /**
   * Toggle the menu bar when having mobile screen
   */
  toggleMobileMenu(event: any) {
    event.preventDefault();
    this.mobileMenuButtonClicked.emit();
  }

  /**
   * Toggles the right sidebar
   */
  toggleRightSidebar() {
    this.settingsButtonClicked.emit();
  }

  /**
   * Activates the menu
   */
  private activateMenu() {
    const resetParent = (el: any) => {
      const parent = el.parentElement;
      if (parent) {
        parent.classList.remove('active');
        const parent2 = parent.parentElement;
        this._removeAllClass('mm-active');
        this._removeAllClass('mm-show');
        if (parent2) {
          parent2.classList.remove('active');
          const parent3 = parent2.parentElement;
          if (parent3) {
            parent3.classList.remove('active');
            const parent4 = parent3.parentElement;
            if (parent4) {
              parent4.classList.remove('active');
              const parent5 = parent4.parentElement;
              if (parent5) {
                parent5.classList.remove('active');
                const menuelement = document.getElementById(
                  'topnav-menu-content'
                );
                if (menuelement !== null)
                  if (menuelement.classList.contains('show'))
                    document
                      .getElementById('topnav-menu-content')!
                      .classList.remove('show');
              }
            }
          }
        }
      }
    };

    // activate menu item based on location
    const links: any = document.getElementsByClassName('side-nav-link-ref');
    let matchingMenuItem = null;
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < links.length; i++) {
      // reset menu
      resetParent(links[i]);
    }
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < links.length; i++) {
      // tslint:disable-next-line: no-string-literal
      if (location.pathname === links[i]['pathname']) {
        matchingMenuItem = links[i];
        break;
      }
    }

    if (matchingMenuItem) {
      const parent = matchingMenuItem.parentElement;
      /**
       * TODO: This is hard coded way of expading/activating parent menu dropdown and working till level 3.
       * We should come up with non hard coded approach
       */
      if (parent) {
        parent.classList.add('active');
        const parent2 = parent.parentElement;
        if (parent2) {
          parent2.classList.add('active');
          const parent3 = parent2.parentElement;
          if (parent3) {
            parent3.classList.add('active');
            const parent4 = parent3.parentElement;
            if (parent4) {
              parent4.classList.add('active');
              const parent5 = parent4.parentElement;
              if (parent5) {
                parent5.classList.add('active');
              }
            }
          }
        }
      }
    }
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/account/login']);
  }


}
