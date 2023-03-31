import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProfessorService } from 'src/app/services/professor.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor(private professorService:ProfessorService,private router:Router){}
  handleLogout(){
    window.localStorage.setItem("token","");
    this.router.navigate(['/login']);
  }
}
