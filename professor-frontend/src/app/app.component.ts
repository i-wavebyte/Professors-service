import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter, first } from 'rxjs';
import { ProfessorService } from './services/professor.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  constructor(
    private professorService: ProfessorService,
    private router: Router
  ) {}
  ngOnInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        const currentRoute = event.url;
        if (currentRoute !== '/login') {
            this.professorService
              .validate({ token: localStorage.getItem('token') || '' })
              .subscribe((data) => {

                console.log(localStorage.getItem('token'));

                if(data == false) this.router.navigate(['/login'])
              });
        }
      }
    });
  }
  title = 'Professors Manager';
}
