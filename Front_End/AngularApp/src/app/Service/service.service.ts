import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserModel } from '../Model/user-model';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  Url='http://localhost:8082/users';
  constructor(private http:HttpClient) { }

  createAlumno(user:UserModel){
    return this.http.post<UserModel>(this.Url,user);
  }
}
