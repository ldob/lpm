import { Component, OnInit } from '@angular/core';
import {ProjectDataService} from '../project-data.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects$: Observable<any>;

  constructor(private projectData: ProjectDataService) {
    this.projects$ = this.projectData.getProjects();
  }

  displayedProjects: string[] = ['name', 'description'];

  ngOnInit(): void {
  }

}
