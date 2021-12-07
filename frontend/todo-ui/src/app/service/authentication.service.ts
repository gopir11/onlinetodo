import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

export class Login{
  constructor(
    public email:string,
    public password:string,
  ) {}
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private httpClient:HttpClient
  ) { 
     }

     authenticate(login) {
      return this.httpClient
      .post<any>("http://localhost:8080/users/login", login)
      .pipe(
        map(userData => {
          console.log('User Data : ' + JSON.stringify(userData));
          console.log('User Data : ' + JSON.stringify(userData.data.token));
          sessionStorage.setItem("username", login.email);
          let tokenStr = "Bearer " + userData.data.token;
          sessionStorage.setItem("token", tokenStr);
          return userData;
        })
      );
    }
  

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username');
    let token = sessionStorage.getItem('token');
    return !(user === null && token === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }
}