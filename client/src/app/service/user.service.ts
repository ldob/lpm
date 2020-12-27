import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {IProject} from "../entity/project";
import {IProjectStatus} from "../entity/project-status";
import {IUser} from "../entity/user";

const API_URL = 'http://localhost:8080/api/test/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUserList() {
    return this.http.get('/api/user/all');
  }

  getUser(id: number) {
    return this.http.get('/api/user/' + id);
  }

  addUser(user: IUser) {
    return this.http.post('/api/user/add', user);
  }

  editUser(user: IUser) {
    return this.http.post('/api/user/update', user);
  }

  getRoles() {
    return this.http.get('/api/values/roles');
  }
}
