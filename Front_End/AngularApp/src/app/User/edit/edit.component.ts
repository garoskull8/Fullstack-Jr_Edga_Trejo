import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/Service/service.service';
import { UserModel } from 'src/app/Model/user-model';


@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  user:UserModel=new UserModel();
  constructor(private router:Router, private service:ServiceService) { }

  ngOnInit() {
    this.editar();
  }

  editar(){
    let id=localStorage.getItem("id");
    this.service.getById(+id)
    .subscribe(data=>{
      this.user=data;
    })
  }

  actualizar(user:UserModel){
    this.service.update(user)
    .subscribe(data=>{
      this.user=data;
      alert("Actualizado!");
      this.router.navigate(["listar"]);
    })
  }

}
