import {Component, Inject, OnInit} from '@angular/core';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {first} from 'rxjs/operators';
import {CommonModule, DOCUMENT} from '@angular/common';
import {RouterOutlet} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FakeAuthenticationService} from "../../service/FakeAuthenticationService";
import {AuthenticationService} from "../../service/AuthenticationService";
import {LAYOUT_MODE} from "../../layout/layouts.model";
import {LoginInfoDto} from "../../dto/LoginInfoDto";
import {AdminLoginService} from "./AdminLoginService";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AuthController} from "../../controller/AuthController";
import {LoginDataStorage} from "../../service/LoginDataStorage";

@Component({
  selector: 'LoginComp',
  imports: [
    CommonModule,
    RouterOutlet,
    ReactiveFormsModule,
    FormsModule,
  ],
  standalone: true,
  templateUrl: './AdminLoginComp.html',
  styleUrls: ['./AdminLoginComp.scss']
})
export class AdminLoginComp implements OnInit {

  year: number = new Date().getFullYear();

  loginForm: UntypedFormGroup = this.fb.group({
    username: [null, [Validators.required]],
    password: [null, [Validators.required]],
  });
  error = '';
  layout_mode!: string;
  fieldTextType!: boolean;

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private fb: UntypedFormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private adminLoginService: AdminLoginService,
    private authController: AuthController,
    private snackBar: MatSnackBar,
    public loginDataStorage: LoginDataStorage,
    private fakeAuthenticationService: FakeAuthenticationService) {
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit(): void {
    console.log(document);
    this.layout_mode = LAYOUT_MODE;
    if (this.layout_mode === 'dark') {
      document.body.setAttribute("data-bs-theme", "dark");
    }
    document.body.setAttribute('data-layout', 'vertical');
  }


  adminLogin() {
    console.log(this.loginForm.value)
    const loginInfoDto = new LoginInfoDto(this.loginForm.value);
    if (!this.adminLoginService.validation(loginInfoDto, this.snackBar)) {
      return;
    }
    this.authController.adminLogin(loginInfoDto)
      .subscribe((response) => {
        console.log(response);        
        this.snackBar.open('Login Successful.', '', {
          horizontalPosition: 'end',
          verticalPosition: 'top',
          duration: 5000,
          panelClass: 'app-notification-success'
        });
        this.loginDataStorage.setItem('admin_auth', JSON.stringify(response));
        this.router.navigate(['/authenticated/dashboard'], { relativeTo: this.route });
      }, (error) => {
        console.log(error);        
        this.snackBar.open('Login Failed.', '', {
          horizontalPosition: 'end',
          verticalPosition: 'top',
          duration: 5000,
          panelClass: 'app-notification-error'
        });
      });

      /*this.fakeAuthenticationService.login('', '')
        .pipe(first())
        .subscribe((data) => {
          this.router.navigate(['/']);
        }, (error) => {
          this.error = error ? error : '';
        });*/

  }

  togglePasswordFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }

}
