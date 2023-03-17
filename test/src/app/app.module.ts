import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HomeComponent } from './layouts/home/home.component';
import { ProfessorDetailsComponent } from './layouts/professor-details/professor-details.component';
import { StudentsComponent } from './layouts/students/students.component';
import { StudentDetailsComponent } from './layouts/student-details/student-details.component';

import { StudentFormComponent } from './components/student-form/student-form.component';
import { StudentListComponent } from './components/student-list/student-list.component';
import { ProfessorsListComponent } from './components/professors-list/professors-list.component';
import { StudDetailsComponent } from './components/stud-details/stud-details.component';
import { AssignStudentComponent } from './components/assign-student/assign-student.component';
import { ProfDetailsComponent } from './components/prof-details/prof-details.component';



@NgModule({
  declarations: [
    AppComponent,
    StudentFormComponent,
    StudentListComponent,
    HomeComponent,
    ProfessorDetailsComponent,
    StudentsComponent,
    StudentDetailsComponent,
    StudentFormComponent,
    StudentListComponent,
    ProfessorsListComponent,
    StudDetailsComponent,
    AssignStudentComponent,
    ProfDetailsComponent
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
