import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import Professor from '../entities/Professor';
import { Observable } from 'rxjs';
import User from '../entities/User';
import Login from '../entities/Login';
import Token from '../entities/Token';
@Injectable({
  providedIn: 'root',
})
export class ProfessorService {
  endpoint: string = 'http://localhost:8090/professorService';
  auth:string = 'http://localhost:8090/auth'
  headers = new HttpHeaders({
    'Authorization': 'Bearer ' + localStorage.getItem('token')
  });

  constructor(private http: HttpClient) {}

  getProfessors(): Observable<Professor[]> {
    return this.http.get<Professor[]>(`${this.endpoint}/get`, {headers:this.headers});
  }

  getProfessor(id: number): Observable<Professor> {
    return this.http.get<Professor>(`${this.endpoint}/get/${id}`, {headers:this.headers});
  }

  getProfessorsList(ids: number[]): Observable<Professor[]> {
    let params = new HttpParams().set('ids', ids.join(',')); // Construct the query parameter string
    return this.http.get<Professor[]>(`${this.endpoint}/get/list`, {
      params: params,
      headers:this.headers
    });
  }

  assignStudent(id: number, studentId: number): Observable<Professor> {
    return this.http.put<Professor>(`${this.endpoint}/assign/${id}`, studentId, {headers:this.headers});
  }

  assignStudents(id: number, studentIds: number[]): Observable<Professor> {
    return this.http.put<Professor>(`${this.endpoint}/assignAll/${id}`, studentIds, {headers:this.headers});
  }

  add(professor:Professor):Observable<Professor>{
    return this.http.post<Professor>(`${this.endpoint}/add`,professor, {headers:this.headers});
  }
  login(login:Login):Observable<Token>{
    return this.http.post<Token>(`${this.auth}/authenticate`,login);
  }
  regsiter(user:User):Observable<Token>{
    return this.http.post<Token>(`${this.auth}/register`,user);
  }
  validate(token:Token):Observable<boolean>{
    return this.http.post<boolean>(`${this.auth}/validate`,token);
  }
}
