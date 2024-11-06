import {Injectable} from "@angular/core";
import {MatSnackBar} from "@angular/material/snack-bar";
import {LoginInfoDto} from "../../dto/LoginInfoDto";

@Injectable({providedIn: 'root'})
export class AdminLoginService {

  validation(loginInfoDto:LoginInfoDto, snackBar: MatSnackBar):boolean{
    if(loginInfoDto.username == null || loginInfoDto.username == ""){
      snackBar.open('username can not be empty', '', {
        horizontalPosition: 'end',
        verticalPosition: 'top',
        duration: 5000,
        panelClass: 'app-notification-error'
      });
      return false;
    }
    if(loginInfoDto.password == null || loginInfoDto.password == ""){
      snackBar.open('password can not be empty', '', {
        horizontalPosition: 'end',
        verticalPosition: 'top',
        duration: 5000,
        panelClass: 'app-notification-error'
      });
      return false;
    }
    return true;
  }
}
