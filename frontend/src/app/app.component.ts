import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'mayproject';

  constructor(public http:HttpClient){}

  username: String="";  
  email:String="";
  mobile:any="";
  password:String="";
  userid:number=0;

  WhatToShow: number=0

  baseurl="http://15.207.221.225:8080/mayproject/";
  
}

