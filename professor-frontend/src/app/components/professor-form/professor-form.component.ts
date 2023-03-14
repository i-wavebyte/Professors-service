import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import Professor from 'src/app/entities/Professor';
import { ProfessorService } from 'src/app/services/professor.service';

@Component({
  selector: 'app-professor-form',
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.css'],
})
export class ProfessorFormComponent {
  professor: Professor = {
    id: 0,
    name: '',
    cin: '',
    subject: '',
    email: '',
    telephone: '',
    etudiants: [],
  };

  constructor(private professorService: ProfessorService) {}
  onSubmit(form: NgForm) {
    let newProfessor: Professor = Object.assign({}, this.professor); // Create a new Student object with the form data
    this.professorService.add(newProfessor).subscribe((data) => {
      console.log("data sent",data);
    });
    form.reset(); // Reset the form
  }
}
