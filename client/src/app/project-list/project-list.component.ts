import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ProjectService} from '../service/project.service';
import {animate, state, style, transition, trigger} from "@angular/animations";
import {LogService} from "../service/log.service";
import {IProject} from "../entity/project";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {IProjectStatus} from "../entity/project-status";
import {ProjectStatusService} from "../service/project-status.service";
import {CheckboxValue} from "../entity/checkbox-value";

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
export class ProjectListComponent implements OnInit, AfterViewInit {

  projectList$: MatTableDataSource<IProject>;

  columnsToDisplay = ['action_pre', 'priority', 'name', 'startDate', 'endDate', 'status', 'action_post'];
  selectedProject: IProject | null;
  selectedProjectLatestStatus: IProjectStatus | null;
  statusList: Array<CheckboxValue>;
  filterText: string;
  today: Date;

  @ViewChild(MatSort) sort: MatSort | null = null;
  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;

  constructor(private projectService: ProjectService, private projectStatusService: ProjectStatusService, private log: LogService) {
    this.projectList$ = new MatTableDataSource<IProject>();
    this.selectedProject = null;
    this.selectedProjectLatestStatus = null;
    this.statusList = [];
    this.filterText = "";
    this.today = new Date();
  }

  ngOnInit(): void {
    this.projectService.getProjects().subscribe(
      data => {
        let projectList: IProject[] = [];
        Object.entries(data).forEach(
          ([key, value]) => {
            for (let i = 0; i < value.length; i++) {
              projectList.push(value[i] as IProject);
            }
          }
        );

        this.projectList$.data = projectList as IProject[];
        this.log.debug("getProjects", this.projectList$.data);
      }
    );

    this.projectStatusService.getStatus().subscribe(
      data => {
        let list = data as Array<string>;
        list.forEach(item => {
          this.statusList.push(new CheckboxValue(item, false));
        });
        this.log.debug("getStatus", this.statusList);
      }
    );
  }

  ngAfterViewInit() {
    this.projectList$.sort = this.sort;
    this.projectList$.paginator = this.paginator;
  }

  setSelectedProject(project: IProject): void {
    this.selectedProject = this.selectedProject === project ? null : project;

    if(this.selectedProject != null) {
      this.projectStatusService.getLatestProjectStatus(this.selectedProject.id).subscribe(
        data => {
          this.selectedProjectLatestStatus = data as IProjectStatus;
          this.log.debug("getProjectStatus", this.selectedProjectLatestStatus);
        }
      );
    }
  }

  doFilter(): void {
    let filterStatus = '';
    this.statusList.forEach(status => {
      if(status.selected) {
        filterStatus += ' ' + status.name;
      }
    });
    this.projectList$.filter = this.filterText.trim().toLocaleLowerCase() + filterStatus;
  }

  getDateDiff(date1: Date, date2: Date) {
    date1 = new Date(date1);
    date2 = new Date(date2);
    return Math.ceil(Math.abs(date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
  }
}
