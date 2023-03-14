import { Component, OnInit } from '@angular/core';
import Professor from 'src/app/entities/Professor';
import { ProfessorService } from 'src/app/services/professor.service';

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css'],
})
export class ProfessorListComponent implements OnInit {
  professors!: Professor[];
  filtered!: Professor[];
  subjects!:string[]

  constructor(private professorService: ProfessorService) {}

  handleFilterChange(searchValue: string, subjectFilter:string) {
    this.filtered = this.professors.filter(
      (prof) =>
        prof.name.toUpperCase().includes(searchValue.toUpperCase()) ||
        prof.email.toUpperCase().includes(searchValue.toUpperCase()) ||
        prof.cin.toUpperCase().includes(searchValue.toUpperCase()) ||
        prof.telephone.toUpperCase().includes(searchValue.toUpperCase())
    ).filter(prof => prof.subject.toUpperCase().includes(subjectFilter.toUpperCase()));


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
