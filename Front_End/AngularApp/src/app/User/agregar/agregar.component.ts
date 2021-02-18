import { Component, OnInit } from '@angular/core';
import { UserModel } from 'src/app/Model/user-model';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/Service/service.service';

@Component({
  selector: 'app-agregar',
  templateUrl: './agregar.component.html',
  styleUrls: ['./agregar.component.css']
})
export class AgregarComponent implements OnInit {

  user:UserModel= new UserModel();
  constructor(private router:Router, private service:ServiceService) { }

  ngOnInit() {
  }
guardar(){
  this.service.createAlumno(this.user)
  .subscribe(data=>{
    alert("Se agrego con exito.");
    this.router.navigate(["listar"]);
  })
}

}
