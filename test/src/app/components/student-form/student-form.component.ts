import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import Student from 'src/app/entities/Student';
import { StudentService } from 'src/app/services/student.service';


@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.css']
})
export class StudentFormComponent {
  student: Student = {
    id: 0,
    name: '',
    cin: '',
    groupe: '',
    email: '',
    telephone: '',
    professors: [],
  };

  constructor(private studentService: StudentService) {}
  onSubmit(form: NgForm) {
    let newStudent: Student = Object.assign({}, this.student); // Create a new Student object with the form data
    this.studentService.add(newStudent).subscribe((data) => {
      console.log("data sent",data);
    });
    form.reset(); // Reset the form
  }

}
