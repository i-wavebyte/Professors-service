import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './layouts/home/home.component';
import { ProfessorDetailsComponent } from './layouts/professor-details/professor-details.component';
import { StudentDetailsComponent } from './layouts/student-details/student-details.component';
import { StudentsComponent } from './layouts/students/students.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'details/:id', component: ProfessorDetailsComponent },
  { path: 'student/:id', component: StudentDetailsComponent },
  { path: 'students', component:  StudentsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
