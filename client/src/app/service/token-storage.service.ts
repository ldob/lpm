import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

interface SaveUserParams {
  user: any;
}

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  logout(): void {
    window.sessionStorage.clear();
  }

  // @ts-ignore
  // TODO
  public saveToken(token): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    // @ts-ignore
    // TODO
    return sessionStorage.getItem(TOKEN_KEY);
  }

  // @ts-ignore
  // TODO
  public saveUser(data): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(data));
  }

  public getUser(): any {
    // @ts-ignore
    // TODO
    return JSON.parse(sessionStorage.getItem(USER_KEY));
  }
}
