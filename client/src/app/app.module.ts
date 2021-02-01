import {AppComponent} from './app.component';

import {LOCALE_ID, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpClientModule} from "@angular/common/http";

import { authInterceptorProviders } from './helper/lpm-http-interceptor.service';

import {AppRoutingModule} from "./app-routing.module";

import {MatToolbarModule} from "@angular/material/toolbar";
import {MatInputModule} from "@angular/material/input";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatTableModule} from "@angular/material/table";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatDividerModule} from "@angular/material/divider";
import {MatSelectModule} from "@angular/material/select";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatMenuModule} from "@angular/material/menu";
import {MatIconModule} from "@angular/material/icon";
import {MatNativeDateModule, MatOptionModule} from "@angular/material/core";
import {MatListModule} from "@angular/material/list";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatProgressBarModule} from "@angular/material/progress-bar";

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import {ProjectComponent} from './project/project.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { ProjectStatusComponent } from './project-status/project-status.component';
import { LoadingOverlayComponent } from './loading-overlay/loading-overlay.component';
import { ErrorComponent } from './error/error.component';
import {LogService} from "./service/log.service";


import {registerLocaleData} from "@angular/common";

import localeDe from '@angular/common/locales/de';
import { UserComponent } from './user/user.component';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatChipsModule} from "@angular/material/chips";
import { MatDialogModule } from '@angular/material/dialog';
import { MemberInputDialogComponent } from './member-input-dialog/member-input-dialog.component';
registerLocaleData(localeDe);



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    ProjectListComponent,
    ProjectComponent,
    ProjectStatusComponent,
    ErrorComponent,
    LoadingOverlayComponent,
    UserComponent,
    MemberInputDialogComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,

    FormsModule,
    ReactiveFormsModule,

    AppRoutingModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,

    MatToolbarModule,
    MatInputModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatCheckboxModule,
    MatDividerModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatOptionModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    MatPaginatorModule,
    MatSortModule,
    MatListModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatAutocompleteModule,
    MatChipsModule,
    MatDialogModule
  ],
  providers: [
    authInterceptorProviders,
    { provide: LOCALE_ID, useValue: 'de-AT'},
    LogService
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    MemberInputDialogComponent
  ]
})
export class AppModule { }
