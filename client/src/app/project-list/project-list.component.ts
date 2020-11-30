import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../service/project.service';
import {Observable} from 'rxjs';
import {animate, state, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ProjectListComponent implements OnInit {

  projects$: Observable<any>;

  constructor(private projectData: ProjectService) {
    this.projects$ = this.projectData.getProjects();
  }

  columnsToDisplay = ['name', 'description'];
  displayName : any = {
    'name': 'Projekt',
    'description': 'Beschreibung'
  };
  expandedElement: null;

  ngOnInit(): void {
  }

}
