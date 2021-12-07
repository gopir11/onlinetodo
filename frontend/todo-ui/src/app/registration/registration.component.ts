import { Component, OnInit } from '@angular/core';
import { HttpClientService, User } from '../service/httpclient.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user: User = new User("","","");

  constructor(
    private httpClientService: HttpClientService
  ) { }

  ngOnInit() {
  }

  signUp(): void {
    this.httpClientService.signUp(this.user)
        .subscribe( data => {
          alert("User created successfully.");
        });

  };

}