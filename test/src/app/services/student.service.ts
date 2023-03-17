import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Professor from '../entities/Professor';
import Student from '../entities/Student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  endpoint: string = 'http://localhost:8091/studentService';

  constructor(private http: HttpClient) {}

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.endpoint}/all`);
  }

  getStudent(id: number): Observable<Student> {
    return this.http.get<Student>(`${this.endpoint}/find/${id}`);
  }

  getStudentsList(ids: number[]): Observable<Student[]> {
    let params = new HttpParams().set('studentIds', ids.join(',')); // Construct the query parameter string
    return this.http.get<Student[]>(`${this.endpoint}/get/list`, {
      params: params,
    });
  }

  assignProfessor(id: number, studentId: number): Observable<Student> {
    let params = new HttpParams().set('profId', studentId);
    return this.http.put<Student>(`${this.endpoint}/assign/${id}`,{}, {params:params});
  }

  add(student:Student):Observable<Student>{
    return this.http.post<Student>(`${this.endpoint}/add`,student);
  }
}
