export default interface User{
  email:string;
  password:string;
  firstName:string;
  lastName:string;
  role: 'PROF_MANAGER' | 'STUD_MANAGER';
}
