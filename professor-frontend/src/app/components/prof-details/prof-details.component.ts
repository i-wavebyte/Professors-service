import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import Professor from 'src/app/entities/Professor';
import { ProfessorService } from 'src/app/services/professor.service';

@Component({
  selector: 'app-prof-details',
  templateUrl: './prof-details.component.html',
  styleUrls: ['./prof-details.component.css']
})
export class ProfDetailsComponent  implements OnInit{

  professor!:Professor;
  constructor(private professorService:ProfessorService,private route:ActivatedRoute){}
  ngOnInit(): void {
    console.log("initialized");
    console.log(this.route.snapshot.paramMap.get('id'));
    let id = parseInt(this.route.snapshot.paramMap.get('id')||"")
    this.professorService.getProfessor(id).subscribe(data => {
      this.professor = data;
      console.log(data);
    })
  }

}
