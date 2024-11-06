import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from '@angular/router';
import {AuthenticationService} from './AuthenticationService';
import {FakeAuthenticationService} from './FakeAuthenticationService';

@Injectable({providedIn: 'root'})
export class AuthGuard {

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private fakeAuthenticationService: FakeAuthenticationService
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.fakeAuthenticationService.currentEmployeeValue;
    if (currentUser) {
      return true;
    }
    this.router.navigate(['/account/login'], {queryParams: {returnUrl: state.url}});
    return false;
  }
}
