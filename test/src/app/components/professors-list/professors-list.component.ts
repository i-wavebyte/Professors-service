import { Component, OnInit } from '@angular/core';
import Professor from 'src/app/entities/Professor';
import Student from 'src/app/entities/Student';
import { ProfessorService } from 'src/app/services/professor.service';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-professors-list',
  templateUrl: './professors-list.component.html',
  styleUrls: ['./professors-list.component.css']
})
export class ProfessorsListComponent implements OnInit{

  professors!:Professor[];

  constructor(private professorService:ProfessorService){}

  filtered!: Professor[];
  subjects!:string[]


  handleFilterChange(searchValue: string, groupeFilter:string) {
    this.filtered = this.professors.filter(
      (professor) =>
        professor.name.toUpperCase().includes(searchValue.toUpperCase()) ||
        professor.email.toUpperCase().includes(searchValue.toUpperCase()) ||
        professor.cin.toUpperCase().includes(searchValue.toUpperCase()) ||
        professor.telephone.toUpperCase().includes(searchValue.toUpperCase())
    ).filter(professor => professor.subject.toUpperCase().includes(groupeFilter.toUpperCase()));


  }
  ngOnInit(): void {
    this.professorService.getProfessors().subscribe((data) => {
      this.professors = data;
      this.filtered = this.professors;
      this.subjects = this.getUniqueSubjects();
    });
  }


  getUniqueSubjects(): string[] {
    let subjects = this.professors.map((professor) => professor.subject);
    return Array.from(new Set(subjects));
  }


}
