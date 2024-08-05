import { Component } from '@angular/core';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-waiting-requests',
  templateUrl: './waiting-requests.component.html',
  styleUrl: './waiting-requests.component.css'
})
export class WaitingRequestsComponent {
 
 
  waitingreq:any;
  constructor(public app:AppComponent)

  {
    let url= this.app.baseurl+'watingreq';
    this.app.http.post(url,this.app.userid).subscribe((data:any)=>{
      this.waitingreq=data;
    });
  }

  
  setconnections(userid2:number ,setstatus:number)
  {
    let obj={
      userid1:this.app.userid,
      userid2:userid2,
      "connectionstatus":setstatus
    }
    let url= this.app.baseurl+'setconnections';
    this.app.http.post(url,obj).subscribe((data:any)=>{
      if(data<0)
        window.alert('exception on server')
      if(data==2)
        window.alert('Accept done')
      if(data==3)
        window.alert('Reject done')
      if(data==4)
        window.alert('Block done')
   
    })
  }


}
