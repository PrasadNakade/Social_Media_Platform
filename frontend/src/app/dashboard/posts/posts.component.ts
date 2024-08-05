import { Component } from '@angular/core';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrl: './posts.component.css'
})
export class PostsComponent {

  postslist:any;
  wts=0;
  comment:string="";
  commentlist:any;
  constructor(public app:AppComponent)
  {
     let url= this.app.baseurl+'getPostsFromMyConnections'+this.app.userid;
     this.app.http.get(url).subscribe((data:any)=>{
      this.postslist=data;
     });

  }

  likes(post:any)
  {
    let url=this.app.baseurl+'likescount'+this.app.userid+'and'+post.id;
    this.app.http.get(url).subscribe((data:any)=>{
      post.likecount=data;
    })
  }

  addcomment(post:any)
  {
    let abc :String=post.comment;
    console.log(abc)
    let url=this.app.baseurl+'addComments'+this.app.userid+'and'+post.id;
    this.app.http.post(url,abc).subscribe((data:any)=>
    {
    //  console.log(this.comment);
      this.commentlist.push(data);
    });
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


// import { Component } from '@angular/core';
// import { AppComponent } from '../../app.component';

// @Component({
//   selector: 'app-posts',
//   templateUrl: './posts.component.html',
//   styleUrl: './posts.component.css'
// })
// export class PostsComponent {

//   postlist:any;
//   wts=0;
//   comment: String="";
//   commentlist:any;
//   constructor(public app:AppComponent){

//     let url=this.app.baseurl+'getPostsFromMyConnections'+this.app.userid
//     this.app.http.get(url).subscribe((data:any)=>{
//       console.log(data)
//       this.postlist=data;
//     });
//   }

// likes(post: any)
//   {
//     let url=this.app.baseurl+'likescount'+this.app.userid+'and'+post.id;
//     this.app.http.get(url).subscribe((data:any)=>{
//       console.log(data)
//       post.likecount=data;

//     });
//   }

//   comments(post:any)
//   {
//     let url= this.app.baseurl+'getComments'+post.id
//     this.app.http.get(url).subscribe((data:any)=>{

      

//       if(data.length==0)
//         window.alert('data nothing to show')
//       else{
//         post.commentlist=data;
//         this.wts=1;
//       }

//     });
//   }

//   addcomment(post:any)
//   {
//     let url=this.app.baseurl+'addComments'+post.id+'and'+this.app.userid;
//     this.app.http.post(url, this.comment).subscribe((data:any)=>
//       {
//         this.commentlist.push(data)
//       })
//     }

//   }

