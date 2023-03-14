import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import Professor from '../entities/Professor';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class ProfessorService {
  endpoint: string = 'http://localhost:8090/professorService';

  constructor(private http: HttpClient) {}

  getProfessors(): Observable<Professor[]> {
    return this.http.get<Professor[]>(`${this.endpoint}/get`);
  }

  getProfessor(id: number): Observable<Professor> {
    return this.http.get<Professor>(`${this.endpoint}/get/${id}`);
  }

  getProfessorsList(ids: number[]): Observable<Professor[]> {
    let params = new HttpParams().set('ids', ids.join(',')); // Construct the query parameter string
    return this.http.get<Professor[]>(`${this.endpoint}/get/list`, {
      params: params,
    });
  }

  assignStudent(id: number, studentId: number): Observable<Professor> {
    return this.http.put<Professor>(`${this.endpoint}/assign/${id}`, studentId);
  }

  assignStudents(id: number, studentIds: number[]): Observable<Professor> {
    return this.http.put<Professor>(`${this.endpoint}/assignAll/${id}`, studentIds);
  }
}
