import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {IProjectTodo} from "../entity/project-todo";

@Injectable({
  providedIn: 'root'
})
export class ProjectTodoService {

  constructor(private http: HttpClient) { }

  addProjectTodo(projectId: number, todo: IProjectTodo) {
    return this.http.post('/api/todo/add/' + projectId, todo);
  }

  editProjectTodo(todo: IProjectTodo) {
    return this.http.post('/api/todo/update', todo);
  }

  getAllTodos() {
    return this.http.get('/api/todo/all');
  }

  getTodosOfProject(id: number) {
    return this.http.get('/api/todo/all/' + id);
  }

  getStatus() {
    return this.http.get('/api/values/todoStatus');
  }
}
