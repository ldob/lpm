import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public isAuthenticated = new BehaviorSubject<boolean>(false);

  constructor(private router: Router) {
  }

  async checkAuthenticated() {
    return false;
  }

  async login(username: string, password: string) {
    //TODO
  }

  async logout(redirect: string) {
    //TODO
  }
}
