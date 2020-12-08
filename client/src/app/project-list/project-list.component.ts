import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../service/project.service';
import {NextObserver, Observable} from 'rxjs';
import {animate, state, style, transition, trigger} from "@angular/animations";
import {LogService} from "../service/log.service";
import {KeyValue} from "@angular/common";

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

  projectList$: Observable<any>;

  constructor(private projectService: ProjectService, private log: LogService) {
    /*
    this.projectList$ = projectService.getProjects();
    this.projectList$.subscribe(data => this.log.info("getProjects", data));
    */
    this.projectList$ = new Observable<any>();
  }

  columnsToDisplay = ['name', 'description', 'priority', 'startDate', 'plannedEndDate', 'status'];
  expandedElement: null;

  ngOnInit(): void {
    this.projectService.getProjects().subscribe(
      data => {
        let projectList: any[] = [];
        for (let key of Object.keys(data)) {

          // @ts-ignore
          for (let i = 0; i < data[key].length; i++) {
            // @ts-ignore
            projectList.push(data[key][i]);
          }
        }

        this.projectList$ = new Observable<any>((observer) => {
          observer.next(projectList);
          observer.complete();
        });
      }
    );
  }

  public orderByPriority(a: KeyValue<"key" | "value", unknown>, b: KeyValue<"key" | "value", unknown>) {
    //this.log.debug("orderProjectsByPriority", a, b);
    return 1;
  }

  public tmp(o: any) {
    this.log.debug("tmp", o);
  }

}
