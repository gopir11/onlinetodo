import { Component, OnInit } from '@angular/core';
import { HttpClientService, AddToDo } from '../service/httpclient.service';

@Component({
  selector: 'app-addToDo',
  templateUrl: './addToDo.component.html',
  styleUrls: ['./addToDo.component.css']
})
export class AddToDoComponent implements OnInit {

  addToDo: AddToDo = new AddToDo("","");

  constructor(
    private httpClientService: HttpClientService
  ) { }

  ngOnInit() {
  }

  addToDoDetail(): void {
    this.httpClientService.addToDoDetail(this.addToDo)
        .subscribe( result => {
          alert("To Do added successfully.");
        },
        error => {
          alert("Error occurred");
        }
        );

  };

}