import {HTTP_INTERCEPTORS, HttpErrorResponse, HttpEvent} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { TokenStorageService } from '../service/token-storage.service';
import { Observable } from 'rxjs';
import {catchError, finalize} from "rxjs/operators";
import {Router} from "@angular/router";
import {LogService} from "../service/log.service";
import {LoadingService} from "../service/loading.service";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class LpmHttpInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService, private loadingScreen: LoadingService, private log: LogService, private router: Router) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.loadingScreen.show();

    let authRequest = request;
    const token = this.token.getToken();
    if (token != null) {
      authRequest = request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    }
    return next.handle(authRequest)
      .pipe(catchError(error => new Observable<HttpEvent<any>>(observer => {
        if(error instanceof HttpErrorResponse) {
          const errorResponse = <HttpErrorResponse>error;
          this.log.warn("http status error", errorResponse.status);
          if(errorResponse.status === 401) {
            this.token.logout();
            this.router.navigate(['error', errorResponse.status]);
          }
          else {
            this.router.navigate(['error', errorResponse.status]);
          }
          observer.error(error);
          observer.complete();
        }
      })))
      .pipe(finalize(() => {
        this.loadingScreen.hide();
      }));
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: LpmHttpInterceptor, multi: true }
];
