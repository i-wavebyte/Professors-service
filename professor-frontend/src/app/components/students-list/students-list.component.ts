import { Component, OnInit } from '@angular/core';
import Student from 'src/app/entities/Student';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.css']
})
export class StudentsListComponent implements OnInit{
  students!:Student[];

  constructor(private studentService:StudentService){}

  filtered!: Student[];
  groups!:string[]


  handleFilterChange(searchValue: string, subjectFilter:string) {
    this.filtered = this.students.filter(
      (student) =>
        student.name.toUpperCase().includes(searchValue.toUpperCase()) ||
        student.email.toUpperCase().includes(searchValue.toUpperCase()) ||
        student.cin.toUpperCase().includes(searchValue.toUpperCase()) ||
        student.telephone.toUpperCase().includes(searchValue.toUpperCase())
    ).filter(student => student.groupe.toUpperCase().includes(subjectFilter.toUpperCase()));


  }
  ngOnInit(): void {
    this.studentService.getStudents().subscribe((data) => {
      this.students = data;
      this.filtered = this.students;
      this.groups = this.getUniqueGroups();
    });
  }


  getUniqueGroups(): string[] {
    let subjects = this.students.map((professor) => professor.groupe);
    return Array.from(new Set(subjects));
  }


}
