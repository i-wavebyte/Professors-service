import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfessorFormComponent } from './components/professor-form/professor-form.component';
import { ProfessorListComponent } from './components/professor-list/professor-list.component';
import { HomeComponent } from './layouts/home/home.component';
import { ProfessorDetailsComponent } from './layouts/professor-details/professor-details.component';
import { ProfDetailsComponent } from './components/prof-details/prof-details.component';
import { StudentsListComponent } from './components/students-list/students-list.component';
import { StudentsComponent } from './layouts/students/students.component';
import { StudentDetailsComponent } from './layouts/student-details/student-details.component';
import { StudDetailsComponent } from './components/stud-details/stud-details.component';
import { LoginComponent } from './layouts/login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    ProfessorFormComponent,
    ProfessorListComponent,
    HomeComponent,
    ProfessorDetailsComponent,
    ProfDetailsComponent,
    StudentsListComponent,
    StudentsComponent,
    StudentDetailsComponent,
    StudDetailsComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
