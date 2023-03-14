import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './layouts/home/home.component';
import { ProfessorDetailsComponent } from './layouts/professor-details/professor-details.component';

const routes: Routes = [{path: '', component: HomeComponent},{path: 'details/:id', component: ProfessorDetailsComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
