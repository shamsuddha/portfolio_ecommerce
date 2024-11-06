import {Component} from '@angular/core';
import {RouterModule} from '@angular/router';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {catchError} from 'rxjs/operators';
import {toResponseBody, uploadProgress} from "./util/FileUtil";
import {formatErrors} from "./util/ErrorUtil";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {

  constructor(private http: HttpClient) {
  }

}