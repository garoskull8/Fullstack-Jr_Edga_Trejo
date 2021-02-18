import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserModel } from '../Model/user-model';
import { identifierModuleUrl } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  Url='http://localhost:8082/users';
  constructor(private http:HttpClient) { }

  getAll(){
    return this.http.get<UserModel[]>(this.Url);
  }

  getById(id:number){
    return this.http.get<UserModel>(this.Url+"/"+id);

  }
  createUser(user:UserModel){
    return this.http.post<UserModel>(this.Url,user);
  }
  delete(user:UserModel){
    return this.http.delete<UserModel>(this.Url+"/"+user.id_user);
  }
  update(user:UserModel){
    return this.http.put<UserModel>(this.Url+"/"+user.id_user,user);
  }
}
