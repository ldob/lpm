import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProjectDataService {

  constructor(private http: HttpClient) { }

  getProjects() {
    return this.http.get('/api/project/all');
  }
}
