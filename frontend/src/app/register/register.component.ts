import { Component } from '@angular/core';
import { AppComponent } from '../app.component';
import { DashboardComponent } from '../dashboard/dashboard.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  constructor(public app:AppComponent){}

add()
{
  let obj=
  {
    "username": this.app.username,
    "email": this.app.email,
    "password": this.app.password,
    "mobile" : this.app.mobile
  } 

  let url=this.app.baseurl+'register'
  this.app.http.post(url,obj).subscribe((data:any)=>{
    console.log(data)
   if(data==-4)
    alert('email is not valid');
   if(data==-3)
    alert('mobile no is already exits')
   if(data==-2)
    alert('username already exits')
  if(data==-5)
    alert('In valid mobile number')
  if(data==0)
    alert('something went wrong')

  if(data==1)
    alert('user added successfully');
  
  });
  }
}
