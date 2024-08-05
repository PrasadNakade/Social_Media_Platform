import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  namea:string="";
  
dashboardToShow: number=0;
  constructor(public http: HttpClient, public app: AppComponent)
  {

  let url= this.app.baseurl+'get'+this.app.userid;
  this.app.http.get(url).subscribe((data:any)=>
  {
    this.namea=data[0];
    console.log(data[0]);
  })
 }

}