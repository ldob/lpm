import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {HomeComponent} from './home/home.component';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {ProfileComponent} from "./profile/profile.component";
import {ProjectListComponent} from './project-list/project-list.component';
import {NotAllowedComponent} from "./not-allowed/not-allowed.component";
import {ProjectComponent} from "./project/project.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'projects', component: ProjectListComponent },
  { path: 'project', component: ProjectComponent },
  { path: 'project/:id', component: ProjectComponent },
  { path: 'not_allowed', component: NotAllowedComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', redirectTo: 'home' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
