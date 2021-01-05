import { Component, OnInit } from '@angular/core';
import {IProject, Project} from "../entity/project";
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectService} from "../service/project.service";
import {LogService} from "../service/log.service";
import {IProjectStatus, ProjectStatus} from "../entity/project-status";
import {IProjectTodo, ProjectTodo} from "../entity/project-todo";
import {ProjectStatusService} from "../service/project-status.service";
import {ProjectTodoService} from "../service/project-todo.service";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-project-status',
  templateUrl: './project-status.component.html',
  styleUrls: ['./project-status.component.css']
})
export class ProjectStatusComponent implements OnInit {

  id: number | null;
  project: IProject;
  projectStatusList$: MatTableDataSource<IProjectStatus>;
  statusList: Array<string>;
  todoList: Array<IProjectTodo>;
  todoStatusList: Array<string>;

  columnsToDisplay = ['date', 'action_post'];
  selectedProjectStatus: IProjectStatus | null;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private projectService: ProjectService, private projectStatusService: ProjectStatusService, private projectTodoService: ProjectTodoService, private log: LogService) {
    this.id = null;
    this.project = new Project();
    this.projectStatusList$ = new MatTableDataSource<IProjectStatus>();
    this.statusList = [];
    this.todoList = [];
    this.todoStatusList = [];
    this.selectedProjectStatus = null;
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      params => {
        this.id = parseInt(params['id'], 10);
        if(this.id) {
          this.projectService.getProject(this.id).subscribe(
            data => {
              this.project = data as IProject;
              this.log.debug("getProject", this.project);
            }
          );

          this.projectStatusService.getProjectStatus(this.id).subscribe(
            data => {
              this.projectStatusList$.data = data as IProjectStatus[];
              this.log.debug("getProjectStatus", this.projectStatusList$.data);

              if(this.projectStatusList$.data.length > 0) {
                this.setSelectedProjectStatus(this.projectStatusList$.data[0]);
              }
            }
          );
        }
        else {
          this.router.navigateByUrl('/projects');
        }
      }
    );

    this.projectStatusService.getStatus().subscribe(
      data => {
        this.statusList = data as Array<string>;
        this.log.debug("getStatus", this.statusList);
      }
    );

    if(this.id) {
      this.projectTodoService.getTodosOfProject(this.id).subscribe(
        data => {
          this.todoList = data as IProjectTodo[];
          this.log.debug("getProjectTodo", this.todoList);
        }
      );
    }

    this.projectTodoService.getStatus().subscribe(
      data => {
        this.todoStatusList = data as Array<string>;
        this.log.debug("getTodoStatus", this.todoStatusList);
      }
    );
  }

  setSelectedProjectStatus(projectStatus: IProjectStatus | null): void {
    if(projectStatus) {
      this.selectedProjectStatus = projectStatus;
    }
    else {
      this.selectedProjectStatus = new ProjectStatus();
      this.projectStatusService.getLatestProjectStatus(this.project.id).subscribe(
        data => {
          if(this.selectedProjectStatus != null && data != null) {
            let ps = data as IProjectStatus;
            this.selectedProjectStatus.status = ps.status;
            this.selectedProjectStatus.tweet = ps.tweet;
            this.selectedProjectStatus.nextSteps = ps.nextSteps;
            this.selectedProjectStatus.problems = ps.problems;
          }
          else {
            this.selectedProjectStatus = null;
          }
        }
      );
      this.selectedProjectStatus.date = new Date();

      const list = this.projectStatusList$.data;
      list.push(this.selectedProjectStatus);
      this.projectStatusList$.data = list;
    }
  }

  save(): void {
    if(this.id && this.selectedProjectStatus != null && this.selectedProjectStatus.id != null) {
      this.projectStatusService.editProjectStatus(this.id, this.selectedProjectStatus).subscribe(
        data => {
          this.log.debug('editProject', data);
          this.selectedProjectStatus = data as IProjectStatus;
        }
      );
    }
    else if(this.id && this.selectedProjectStatus != null) {
      this.projectStatusService.addProjectStatus(this.id, this.selectedProjectStatus).subscribe(
        data => {
          this.log.debug('addProject', data);
          this.selectedProjectStatus = data as IProjectStatus;
        }
      );
    }
  }

  addTodo(): void {
    var todo = new ProjectTodo();
    todo.edit = true;
    this.todoList.push(todo);
  }

  saveTodo(todo: IProjectTodo): void {
    if(this.id && todo.id != null) {
      this.projectTodoService.editProjectTodo(todo).subscribe(
        data => {
          todo = data as IProjectTodo;
          this.log.debug('editProjectTodo', todo);
        }
      );
    }
    else if(this.id) {
      this.projectTodoService.addProjectTodo(this.id, todo).subscribe(
        data => {
          todo = data as IProjectTodo;
          this.log.debug('addProjectTodo', todo);
        }
      );
    }
    todo.edit = false;
  }

}
