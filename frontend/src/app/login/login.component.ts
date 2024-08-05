import { Component } from '@angular/core';
import { AppComponent } from '../app.component';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(public app:AppComponent){}

  login()
  {
  let obj={
    "mobile": this.app.mobile,
    "password": this.app.password
  };
  
    let url=this.app.baseurl+'login';
   
    this.app.http.post(url,obj).subscribe((data:any)=>{

      if(data==-1)
        window.alert("wrong mobile number");
      else if(data==-3)
        window.alert("wrong password");
      else if(data==-2)
        window.alert("user not found");
      else if(data<1)
        window.alert("exception on server");
      else
      {
        // window.alert("login sucessful");
        this.app.WhatToShow=3;
        this.app.userid=data;      //userid is store for later use   //not needed for login
        this.app.mobile="";
        this.app.password="";
      }
    });
    
  }
}

