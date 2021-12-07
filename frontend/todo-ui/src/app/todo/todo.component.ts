import { Component, OnInit } from '@angular/core';
import { HttpClientService, ToDo } from '../service/httpclient.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class ToDoComponent implements OnInit {

  todos:ToDo[];
    
   
  constructor(
    private httpClientService:HttpClientService
  ) { }

  ngOnInit() {
     this.httpClientService.getTodos().subscribe(
      response =>this.handleSuccessfulResponse(response),
     );
  }

handleSuccessfulResponse(response)
{
    this.todos=response.data;
}

deleteTodo(todo: ToDo): void {
   this.httpClientService.deleteTodo(todo)
     .subscribe( data => {
      this.todos = this.todos.filter(u => u.id !== todo.id);
   })
};


}