import { Component, OnInit } from "@angular/core";
import { NgForm } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import Professor from "src/app/entities/Professor";
import Student from "src/app/entities/Student";
import { ProfessorService } from "src/app/services/professor.service";
import { StudentService } from "src/app/services/student.service";

@Component({
  selector: "app-assign-student",
  templateUrl: "./assign-student.component.html",
  styleUrls: ["./assign-student.component.css"],
})
export class AssignStudentComponent implements OnInit {
onSubmit(_t6: NgForm) {
  this.studentService.getStudent(parseInt(this.route.snapshot.paramMap.get('id')||"")).subscribe(data=>{
    console.log(data);
    console.log("student: ",parseInt(this.route.snapshot.paramMap.get('id')||""));
    
    this.professorService.assignStudent(this.professorId,parseInt(this.route.snapshot.paramMap.get('id')||"")).subscribe(prof =>{
      
      console.log("student assigned to professor");
      console.log("");
      
      this.studentService.assignProfessor(parseInt(this.route.snapshot.paramMap.get('id')||""),this.professorId).subscribe(d=>{
        console.log("professor assigned to student");
        
      })
    })
    console.log("professor: ",this.professorId);
    
  })
}


  professors!: Professor[];
  professorId!: number;
  professor!:Professor;
  student!:Student;
  constructor(private professorService: ProfessorService, private studentService:StudentService,private route:ActivatedRoute) {}
  ngOnInit(): void {
    this.professorService.getProfessors().subscribe((data) => {
      this.professors = data;
    });
  }

  studentForm(form:NgForm){
    
  }
}
