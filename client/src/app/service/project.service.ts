import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IProject} from '../entity/project';

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

  getProjectMember(id: number, role: string | undefined) {
    if (role) {
      return this.http.get('/api/project/' + id + '/member/' + role);
    }
    else {
      return this.http.get('/api/project/' + id + '/member');
    }
  }

  addProjectMember(projectId: number, member: string, role: string) {
    return this.http.post('/api/project/' + projectId + '/addMember/' + member, role);
  }

  removeProjectMember(projectId: number, member: string, role: string) {
    return this.http.post('/api/project/' + projectId + '/removeMember/' + member, role);
  }

  getPriorities() {
    return this.http.get('/api/values/priorities');
  }
}
