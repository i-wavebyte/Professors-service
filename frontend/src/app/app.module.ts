import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfessorFormComponent } from './components/professor-form/professor-form.component';
import { ProfessorListComponent } from './components/professor-list/professor-list.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfessorFormComponent,
    ProfessorListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
