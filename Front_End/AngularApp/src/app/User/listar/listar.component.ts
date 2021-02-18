import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/Service/service.service';
import { Router } from '@angular/router';
import { UserModel } from 'src/app/Model/user-model';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {
  users:UserModel[];
  constructor(private service:ServiceService,private router:Router) { }

  ngOnInit() {
    this.service.getAll()
    .subscribe(data=>{
      this.users=data;
    })
  }

}
