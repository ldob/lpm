import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IProject} from "../entity/project";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) { }

  getProjects() {
    return this.http.get('/api/project/all');
  }

  getProject(id: number) {
    return this.http.get('/api/project/' + id);
  }

  addProject(project: IProject) {
    return this.http.post('/api/project/add', project);
  }

  editProject(project: IProject) {
    return this.http.post('/api/project/update', project);
  }

  getPriorities() {
    return this.http.get('/api/values/priorities');
  }
}
