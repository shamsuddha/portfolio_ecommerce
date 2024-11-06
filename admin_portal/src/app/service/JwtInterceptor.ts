import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest,} from '@angular/common/http';
import {Observable} from 'rxjs';

import {AuthenticationService} from './AuthenticationService';
import {FakeAuthenticationService} from './FakeAuthenticationService';


@Injectable({providedIn: 'root'})
export class JwtInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService,
              private fakeAuthenticationService: FakeAuthenticationService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const currentEmployee = this.fakeAuthenticationService.currentEmployeeValue;
    if (currentEmployee) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer 123Abc`,
        },
      });
    }
    return next.handle(request);
  }
}
