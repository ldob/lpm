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

  columnsToDisplay = ['action_pre', 'priority', 'project', 'startDate', 'endDate', 'status', 'action_post'];
  selectedProject: IProject | null;
  selectedProjectLatestStatus: IProjectStatus | null;

  @ViewChild(MatSort) sort: MatSort | null = null;
  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;

  constructor(private projectService: ProjectService, private projectStatusService: ProjectStatusService, private log: LogService) {
    // TODO refactor
    /*
    this.projectList$ = projectService.getProjects();
    this.projectList$.subscribe(data => this.log.info("getProjects", data));
    */
    this.projectList$ = new MatTableDataSource<IProject>();
    this.selectedProject = null;
    this.selectedProjectLatestStatus = null;
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
  }

  ngAfterViewInit() {
    this.projectList$.sort = this.sort;
    this.projectList$.paginator = this.paginator;
  }

  public doFilter = (target: EventTarget | null) => {
    let element = target as HTMLInputElement;
    this.projectList$.filter = element.value.trim().toLocaleLowerCase();
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
}
