import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Observable} from 'rxjs';
import {ProjectService} from '../service/project.service';
import {LogService} from '../service/log.service';
import {IProject, Project} from '../entity/project';
import {ActivatedRoute, Router} from '@angular/router';
import {MatTableDataSource} from '@angular/material/table';
import {IMember} from '../entity/member';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MemberInputDialogComponent } from '../member-input-dialog/member-input-dialog.component';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  id: number | null;
  project: IProject;
  priorityList: Array<string>;

  memberManageList$: MatTableDataSource<IMember>;
  memberCollaborateList$: MatTableDataSource<IMember>;
  memberObserveList$: MatTableDataSource<IMember>;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private dialog: MatDialog, private projectService: ProjectService, private log: LogService) {
    this.id = null;
    this.project = new Project();
    this.priorityList = [];

    this.memberManageList$ = new MatTableDataSource<IMember>();
    this.memberCollaborateList$ = new MatTableDataSource<IMember>();
    this.memberObserveList$ = new MatTableDataSource<IMember>();
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      params => {
        this.id = parseInt(params.id, 10);
        if (this.id) {
          this.projectService.getProject(this.id).subscribe(
            data => {
              this.project = data as IProject;
              this.log.debug('getProject', this.project);
            }
          );

          this.projectService.getProjectMember(this.id, 'MANAGE').subscribe(
            data => {
              this.memberManageList$.data = data as IMember[];
              this.log.debug('getMemberManage', this.memberManageList$.data);
            }
          );

          this.projectService.getProjectMember(this.id, 'COLLABORATE').subscribe(
            data => {
              this.memberCollaborateList$.data = data as IMember[];
              this.log.debug('getMemberCollaborate', this.memberCollaborateList$.data);
            }
          );

          this.projectService.getProjectMember(this.id, 'OBSERVE').subscribe(
            data => {
              this.memberObserveList$.data = data as IMember[];
              this.log.debug('getMemberObserve', this.memberObserveList$.data);
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
        this.log.debug('getPriorities', this.priorityList);
      }
    );
  }

  save(): void {
    if (this.id) {
      this.projectService.editProject(this.project).subscribe(
        data => {
          this.project = data as IProject;
        }
      );
    }
    else {
      this.projectService.addProject(this.project).subscribe(
        data => {
          this.project = data as IProject;
        }
      );
    }
  }

  saveAndClose(): void {
    this.save();
    this.router.navigateByUrl('/projects');
  }

  // ***** MEMBERS *****
  addAssignedMember(role: string, list: MatTableDataSource<IMember>): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
      title: role + ' hinzufügen',
      close: 'Schließen',
      save: 'Speichern'
    }

    const dialogRef = this.dialog.open(MemberInputDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      username => {
        this.log.debug('dialogOutput', username);
        if (username) {
          this.projectService.addProjectMember(this.project.id, username, role).subscribe(
            data => {
              list.data = data as IMember[];
              this.log.debug('addMember', list.data);
            }
          );
        }
      }
    );
  }

  removeAssignedMember(member: IMember, role: string, list: MatTableDataSource<IMember>): void {
    this.projectService.removeProjectMember(this.project.id, member.username, role).subscribe(
      data => {
        list.data = data as IMember[];
        this.log.debug('removeMember', list.data);
      }
    );
  }

}
