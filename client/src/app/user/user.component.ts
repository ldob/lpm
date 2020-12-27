import { Component, OnInit } from '@angular/core';
import {IProject, Project} from "../entity/project";
import {MatTableDataSource} from "@angular/material/table";
import {IProjectStatus, ProjectStatus} from "../entity/project-status";
import {ActivatedRoute, Router} from "@angular/router";
import {LogService} from "../service/log.service";
import {IUser, User} from "../entity/user";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  initUserId: number | null;
  user: IUser;
  userList$: MatTableDataSource<IUser>;
  roleList: Array<string>;

  columnsToDisplay = ['username', 'action_post'];
  selectedUser: IUser | null;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private userService: UserService, private log: LogService) {
    this.initUserId = null;
    this.user = new User();
    this.userList$ = new MatTableDataSource<IUser>();
    this.roleList = [];
    this.selectedUser = null;
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      params => {
        this.initUserId = parseInt(params['id'], 10);
      }
    );

    this.userService.getUserList().subscribe(
      data => {
        this.userList$.data = data as IUser[];
        this.log.debug("getUser", this.userList$.data);

        let user;
        this.userList$.data.forEach(u => {
          if (this.initUserId === u.id) {
            user = u;
          }
        });

        if(user) {
          this.setSelectedUser(user);
        }
      }
    );

    this.userService.getRoles().subscribe(
      data => {
        this.roleList = data as Array<string>;
        this.log.debug("getRoles", this.roleList);
      }
    );
  }

  setSelectedUser(user: IUser | null): void {
    if(user) {
      this.selectedUser = user;
    }
    else {
      this.selectedUser = new User();

      const list = this.userList$.data;
      list.push(this.selectedUser);
      this.userList$.data = list;
    }
  }

  save(): void {
    if(this.selectedUser != null && this.selectedUser.id != null) {
      this.userService.editUser(this.selectedUser).subscribe(
        data => {
          this.log.debug('editUser', data);
          this.selectedUser = data as IUser;
        }
      );
    }
    else if(this.selectedUser != null) {
      this.userService.addUser(this.selectedUser).subscribe(
        data => {
          this.log.debug('addUser', data);
          this.selectedUser = data as IUser;
        }
      );
    }
  }

}
