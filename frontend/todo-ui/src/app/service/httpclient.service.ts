import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class User{
  constructor(
    public name:string,
    public email:string,
    public password:string,
  ) {}
}

export class ToDo{
  constructor(
    public id:number,
    public description:string,
    public targetDate:string,
    public done:string,
  ) {}
}

export class AddToDo{
  constructor(
    public description:string,
    public targetDate:string,
  ) {}
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(
    private httpClient:HttpClient
  ) { 
     }

    getTodos() {
      return this.httpClient.get<ToDo[]>("http://localhost:8080/todos");
    }
  
    public deleteTodo(todo) {
      return this.httpClient.delete<any>(
        "http://localhost:8080/todos" + "/" + todo.id
      );
    }

    public addToDoDetail(todo) {
      return this.httpClient.post<any>("http://localhost:8080/todos", todo);
    }

  public signUp(user) {
    return this.httpClient.post<User>("http://localhost:8080/users/signup", user);
  }
}