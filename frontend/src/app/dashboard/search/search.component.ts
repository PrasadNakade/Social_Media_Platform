import { Component } from '@angular/core';
import { AppComponent } from '../../app.component';
import { DashboardComponent } from '../dashboard.component';
//import { window } from 'rxjs';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
  
export class SearchComponent {

  constructor(public app:AppComponent, public dash: DashboardComponent){}

    follow(userid2: number)
    {
      let obj={
        "userid1":this.app.userid,
        "userid2": userid2
        
      }
      console.log(obj);
    let url=this.app.baseurl+'follow';
    this.app.http.post(url,obj).subscribe((data:any)=>{
      if(data==-2)
      {
        window.alert('Blocked by the user');
      }
      if(data==3)
      {
        window.alert('Id is private request send');
      }
      if(data==4)
        window.alert(' Request directly accepted');
      if(data==5)
        window.alert('Id is private req sended new connection');
      if(data==6)
        window.alert('Direct accepted new connection');
      if(data==7)
        window.alert('You already sends req');
      if(data==-1)
        window.alert('Exception on server');
    });
  }
  
  uiname:string="";    // we get data from user with the help of ng model
  list : any;          //object storing of backend
  serach: string=""

  search(search : any){
      
    console.log(this.uiname)
    if(this.uiname=="")
    {
      window.alert('enter something')
    }
    else
    {
      let url=this.app.baseurl+'search'+this.uiname+ 'and' +this.app.userid;
      this.app.http.get(url).subscribe((data: any)=>{
      if(data==null)
        window.alert('exception on server');
      else
        this.list=data;  //all data comes from backend of serach method 
  })
  }
  }
}
