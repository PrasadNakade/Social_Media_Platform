import { Component } from '@angular/core';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-myposts',
  templateUrl: './myposts.component.html',
  styleUrl: './myposts.component.css'
})
export class MypostsComponent {

  commentlist : any;
  wts: number=0;
  postlist: any;
  posts: String='';
  constructor(public app:AppComponent){

    let url=this.app.baseurl+'getAllPost'+this.app.userid
    this.app.http.get(url).subscribe((data:any)=>{
      console.log(data)
      if(data==null)
        window.alert('nothing to show')
      else
      this.postlist=data;
    })
  }

post(){
  let url=this.app.baseurl+'addnewpost'+this.app.userid;
  this.app.http.post(url,this.posts).subscribe((data:any)=>
  {
    if(window.confirm("are you sure!!"))
      {
        this.postlist.push(data);
        this.posts="";  
      }

  })
}

comments(post:any)
{
  let url=this.app.baseurl+'getComments'+post.id;
  this.app.http.get(url).subscribe((data:any)=>
  {
    
    if(data.length==0)
    {
        window.alert('nothing to show')
    }
    else
    {
        this.commentlist=data;
        this.wts=1;
    }
  });
}

}
  

