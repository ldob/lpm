import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Observable} from "rxjs";
import {ProjectService} from "../service/project.service";
import {LogService} from "../service/log.service";
import {IProject, Project} from "../entity/project";
import {ActivatedRoute, Router} from "@angular/router";
import {MatAutocomplete, MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";
import {MatChipInputEvent} from "@angular/material/chips";
import {FormControl} from "@angular/forms";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {map, startWith} from "rxjs/operators";
import {IMember, Member} from "../entity/member";

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

    this.filteredMembers = this.memberManageCtrl.valueChanges.pipe(
      startWith(null),
      map((member: IMember | null) => member ? this._filterMember(member) : this.allMembers.slice())
    );
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

  // ***** MEMBERS *****
  separatorKeysCodes: number[] = [ENTER, COMMA];
  memberManageCtrl = new FormControl();
  filteredMembers: Observable<IMember[]>;
  members: IMember[] = [new Member(1, 'LDO', 'Lucas Dobler')];
  allMembers: IMember[] = [new Member(1, 'LDO', 'Lucas Dobler'), new Member(2, 'LAM', 'Alexander Lampret')];

  // @ts-ignore
  @ViewChild('memberManageInput') memberManageInput: ElementRef<HTMLInputElement>;
  // @ts-ignore
  @ViewChild('auto') matAutocomplete: MatAutocomplete;

  addMember(event: MatChipInputEvent, type: string): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      let member = this._getMemberByUsername(value.trim());
      if(member) {
        this.members.push(member);
      }
    }

    if (input) {
      input.value = '';
    }

    this.memberManageCtrl.setValue(null);
  }

  removeMember(member: IMember, type: string): void {
    const index = this.members.indexOf(member);
    if (index >= 0) {
      this.members.splice(index, 1);
    }
  }

  selectedMember(event: MatAutocompleteSelectedEvent, type: string): void {
    let member = this._getMemberByUsername(event.option.viewValue);
    if(member) {
      this.members.push(member);
      this.memberManageInput.nativeElement.value = '';
      this.memberManageCtrl.setValue(null);
    }
  }

  private _getMemberByUsername(value: string): IMember | null {
    let member = null;
    this.allMembers.forEach(m => {
      if(m.username === value.trim()) {
        member = m;
      }
    });

    return member;
  }

  private _filterMember(value: IMember): IMember[] {
    const filterValue = value.username.toLowerCase();
    return this.allMembers.filter(member => member.username.toLowerCase().indexOf(filterValue) === 0);
  }
}
