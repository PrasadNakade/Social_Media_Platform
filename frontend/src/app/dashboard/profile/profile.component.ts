import { Component } from '@angular/core';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  followers: number=0;
  following: number=0;
  changeSta: number=0;
  status: String='';
  followerslist:any;
  followingList:any;
  wts:number=0
  postlist:any;
  

  constructor(public app: AppComponent){
//we are writing these code in consturctor because we are showing the data directly or the constructor executes first

  let url=this.app.baseurl+'follwersandfollowing'
  this.app.http.post(url, this.app.userid).subscribe((data:any)=>{      //we are passing array from backend these time ,(we always pass object)
   
      this.followers=data[0];
      this.following=data[1];
 
      if(data[2]==0)
        this.status='public';
      else
        this.status='private';
    })
  }
  changeStatus(){

    let url=this.app.baseurl+'updatestatus';
    this.app.http.post(url, this.app.userid).subscribe((data:any)=>{

      if(data==0)
        this.status='Public';
      else
      this.status='Private';
    })
   
  }
  getfollowerslist()
  {
    let url=this.app.baseurl+'getfollowerslist'+this.app.userid;
    this.app.http.get(url).subscribe((data:any)=>{
      if(data==null)
        window.alert('nothing to show')
      else
      this.followerslist=data;
  })

}

getfollowinglist()
{
  let url=this.app.baseurl+'getfollowinglist'+this.app.userid;
  this.app.http.get(url).subscribe((data:any)=>{
    if(data==null)
      window.alert('nothing to show')
    else
    this.followingList=data;
})

}

// myPosts(){

//   let url=this.app.baseurl+'getAllPost'+this.app.userid
//   this.app.http.get(url).subscribe((data:any)=>{
//     console.log(data)
//     if(data==null)
//       window.alert('nothing to show')
//     else
//     this.postlist=data;
//   })
// }



}
