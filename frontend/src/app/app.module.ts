import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ProfileComponent } from './dashboard/profile/profile.component';
import { SearchComponent } from './dashboard/search/search.component';
import { WaitingRequestsComponent } from './dashboard/waiting-requests/waiting-requests.component';
import { PostsComponent } from './dashboard/posts/posts.component';
import { ChatComponent } from './dashboard/chat/chat.component';
import { MypostsComponent } from './dashboard/myposts/myposts.component';
import { ReelsComponent } from './dashboard/reels/reels.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    ProfileComponent,
    SearchComponent,
    WaitingRequestsComponent,
    PostsComponent,
    ChatComponent,
    MypostsComponent,
    ReelsComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
