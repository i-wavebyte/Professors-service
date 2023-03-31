import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import Login from 'src/app/entities/Login';
import Token from 'src/app/entities/Token';
import { ProfessorService } from 'src/app/services/professor.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit{


  loginInfo: Login = {
    username: '',
    password: '',
  };

  constructor(private professorService: ProfessorService, private router:Router) {}
  ngOnInit(): void {
    let token:Token = {token: window.localStorage.getItem("token") || ""}
      this.professorService.validate(token).subscribe(data=>{
        if(data==true)
        this.router.navigate(['']);
      })
  }

  handleLoginSubmit(form:NgForm) {
    let login: Login = Object.assign({}, this.loginInfo);
    console.log(login);

    this.professorService.login(login).subscribe((data) => {
      console.log(data);
      window.localStorage.setItem("token",data.token)
      this.professorService.validate(data).subscribe(data=>{
        if(data==true)
        this.router.navigate(['']);
        window.location.reload();
      })

    });
  }
}
