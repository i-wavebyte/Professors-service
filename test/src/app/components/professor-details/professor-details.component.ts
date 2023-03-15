import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import Professor from 'src/app/entities/Professor';
import Student from 'src/app/entities/Student';
import { ProfessorService } from 'src/app/services/professor.service';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-professor-details',
  templateUrl: './professor-details.component.html',
  styleUrls: ['./professor-details.component.css']
})
export class ProfessorDetailsComponent implements OnInit {

  professor!:Professor;
  students!:Student[];
  filtered!: Student[];
  subjects!: string[];
  constructor(private studentService:StudentService,private route:ActivatedRoute, private professorService:ProfessorService){}
  ngOnInit(): void {
    console.log("initialized");
    console.log(this.route.snapshot.paramMap.get('id'));
    let id = parseInt(this.route.snapshot.paramMap.get('id')||"")
    this.professorService.getProfessor(id).subscribe(data => {
      this.professor = data;
      this.studentService.getStudentsList(this.professor.etudiants).subscribe(data=>{
        this.students= data;
        console.log(data);
        this.filtered = this.students;
      this.subjects = this.getUniqueSubjects();
      })
    })
  }

  handleFilterChange(searchValue: string, groupeFilter:string) {
    this.filtered = this.students.filter(
      (student) =>
        student.name.toUpperCase().includes(searchValue.toUpperCase()) ||
        student.email.toUpperCase().includes(searchValue.toUpperCase()) ||
        student.cin.toUpperCase().includes(searchValue.toUpperCase()) ||
        student.telephone.toUpperCase().includes(searchValue.toUpperCase())
    ).filter(student => student.groupe.toUpperCase().includes(groupeFilter.toUpperCase()));


  }

  getUniqueSubjects(): string[] {
    let subjects = this.students.map((professor) => professor.groupe);
    return Array.from(new Set(subjects));
  }

}

