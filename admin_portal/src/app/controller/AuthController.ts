import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {LoginInfoDto} from "../dto/LoginInfoDto";
import { AuthTokenDto } from "../dto/AuthTokenDto";

@Injectable({ providedIn: 'root' })
export class AuthController {

  constructor(private httpClient: HttpClient) { }

  customerLogin(loginInfoDto: LoginInfoDto): Observable<String> {
    return this.httpClient.post<String>(
      'http://localhost:9999/login/customer-login',
      loginInfoDto);
  }

  adminLogin(loginInfoDto: LoginInfoDto): Observable<AuthTokenDto> {
    return this.httpClient.post<AuthTokenDto>(
      'http://localhost:9999/login/admin-login',
      loginInfoDto);
  }
}
