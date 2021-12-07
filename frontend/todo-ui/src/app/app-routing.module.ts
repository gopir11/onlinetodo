import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ToDoComponent } from './todo/todo.component';
import { AddToDoComponent } from './addToDo/addToDo.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGaurdService } from './service/auth-gaurd.service';

const routes: Routes = [
  { path: '', component: ToDoComponent,canActivate:[AuthGaurdService] },
  { path: 'addToDo', component: AddToDoComponent,canActivate:[AuthGaurdService] },
  { path: 'addUser', component: RegistrationComponent},
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent,canActivate:[AuthGaurdService] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
