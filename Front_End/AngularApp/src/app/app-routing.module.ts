import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AgregarComponent } from './User/agregar/agregar.component';
import { EditComponent } from './User/edit/edit.component';
import { ListarComponent } from './User/listar/listar.component';


const routes: Routes = [
  {path:'agregar',component:AgregarComponent},
  {path:'edit',component:EditComponent},
  {path:'listar',component:ListarComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents=[AgregarComponent,EditComponent,ListarComponent];
