import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import Professor from 'src/app/entities/Professor';
import Student from 'src/app/entities/Student';
import { ProfessorService } from 'src/app/services/professor.service';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit{

  student!:Student;
  professors!:Professor[];
  filtered!: Professor[];
  subjects!: string[];
  constructor(private studentService:StudentService,private route:ActivatedRoute, private professorService:ProfessorService){}
  ngOnInit(): void {
    console.log("initialized");
    console.log(this.route.snapshot.paramMap.get('id'));
    let id = parseInt(this.route.snapshot.paramMap.get('id')||"")
    this.studentService.getStudent(id).subscribe(data => {
      this.student = data;
      this.professorService.getProfessorsList(this.student.professors).subscribe(data=>{
        this.professors= data;
        console.log(data);
        this.filtered = this.professors;
      this.subjects = this.getUniqueSubjects();
      })
    })
  }

  handleFilterChange(searchValue: string, subjectFilter:string) {
    this.filtered = this.professors.filter(
      (professor) =>
        professor.name.toUpperCase().includes(searchValue.toUpperCase()) ||
        professor.email.toUpperCase().includes(searchValue.toUpperCase()) ||
        professor.cin.toUpperCase().includes(searchValue.toUpperCase()) ||
        professor.telephone.toUpperCase().includes(searchValue.toUpperCase())
    ).filter(professor => professor.subject.toUpperCase().includes(subjectFilter.toUpperCase()));


  }

  getUniqueSubjects(): string[] {
    let subjects = this.professors.map((professor) => professor.subject);
    return Array.from(new Set(subjects));
  }

}
