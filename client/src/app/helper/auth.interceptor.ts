import {HTTP_INTERCEPTORS, HttpErrorResponse, HttpEvent} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { TokenStorageService } from '../service/token-storage.service';
import { Observable } from 'rxjs';
import {catchError} from "rxjs/operators";
import {Router} from "@angular/router";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService, private router: Router) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authRequest = request;
    const token = this.token.getToken();
    if (token != null) {
      authRequest = request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    }
    return next.handle(authRequest)
      .pipe(catchError(error => new Observable<HttpEvent<any>>(observer => {
        if(error instanceof HttpErrorResponse) {
          const errorResponse = <HttpErrorResponse>error;
          console.log(errorResponse.status);
          if(errorResponse.status === 401) {
            this.token.logout();
            this.router.navigate(['login']);
          }
          if(errorResponse.status === 403) {
            this.router.navigate(['not_allowed']);
          }
          observer.error(error);
          observer.complete();
        }
      })));
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
