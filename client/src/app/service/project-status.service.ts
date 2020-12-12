import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {IProjectStatus} from "../entity/project-status";

@Injectable({
  providedIn: 'root'
})
export class ProjectStatusService {

  constructor(private http: HttpClient) { }

  getProjectStatus(id: number) {
    return this.http.get('/api/project/' + id + '/status/all');
  }

  getLatestProjectStatus(id: number) {
    return this.http.get('/api/project/' + id + '/status/latest');
  }

  addProjectStatus(projectId: number, project: IProjectStatus) {
    return this.http.post('/api/project/' + projectId + '/status/add', project);
  }

  editProjectStatus(projectId: number, project: IProjectStatus) {
    return this.http.post('/api/project/' + projectId + '/status/update', project);
  }
}
