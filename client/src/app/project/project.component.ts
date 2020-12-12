import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {ProjectService} from "../service/project.service";
import {LogService} from "../service/log.service";
import {IProject, Project} from "../entity/project";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  id: number | null;
  project: IProject;
  priorityList: Array<string>;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private projectService: ProjectService, private log: LogService) {
    this.id = null;
    this.project = new Project();
    this.priorityList = [];
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
        }
        else {
          this.project = new Project();
        }
      }
    );

    this.projectService.getPriorities().subscribe(
      data => {
        this.priorityList = data as Array<string>;
        this.log.debug("getPriorities", this.priorityList);
      }
    );
  }

  save(): void {
    if(this.id) {
      this.projectService.editProject(this.project).subscribe(
        data => {
          this.project = data as IProject;
          this.router.navigateByUrl('/projects');
        }
      );
    }
    else {
      this.projectService.addProject(this.project).subscribe(
        data => {
          let p = data as IProject;
          //this.router.navigate(['/project/', p.id]);
          this.router.navigateByUrl('/projects');
        }
      );
    }
  }

}
