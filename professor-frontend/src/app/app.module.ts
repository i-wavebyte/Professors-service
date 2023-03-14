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

@NgModule({
  declarations: [
    AppComponent,
    ProfessorFormComponent,
    ProfessorListComponent,
    HomeComponent,
    ProfessorDetailsComponent,
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
