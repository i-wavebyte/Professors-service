import { Component, OnInit } from '@angular/core';
import Professor from 'src/app/entities/Professor';
import Student from 'src/app/entities/Student';
import { ProfessorService } from 'src/app/services/professor.service';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit{

  students!: Student[];
  filtered!: Student[];
  groups!:string[]

  constructor(private studentService: StudentService) {}

  handleFilterChange(searchValue: string, groupeFilter:string) {
    this.filtered = this.students.filter(
      (student) =>
        student.name.toUpperCase().includes(searchValue.toUpperCase()) ||
        student.email.toUpperCase().includes(searchValue.toUpperCase()) ||
        student.cin.toUpperCase().includes(searchValue.toUpperCase()) ||
        student.telephone.toUpperCase().includes(searchValue.toUpperCase())
    ).filter(student => student.groupe.toUpperCase().includes(groupeFilter.toUpperCase()));


  }
  ngOnInit(): void {
    this.studentService.getStudents().subscribe((data) => {
      this.students = data;
      this.filtered = this.students;
      this.groups = this.getUniqueGroups();
    });
  }


  getUniqueGroups(): string[] {
    let groups = this.students.map((student) => student.groupe);
    return Array.from(new Set(groups));
  }
}
