package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.hibernate.WrongClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;import org.springframework.web.service.annotation.PatchExchange;

import jakarta.persistence.Id;

@RestController
@CrossOrigin
public class MyController {
	
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	ConnectionRepo connectionRepo;
	@Autowired
	PostRepo postrepo;
	@Autowired
	LikeDataRepo likedatarepo;
	@Autowired
	CommentsRepo commentsrepo;
	
	
	@RequestMapping("addComments{userid}and{postid}")
	public Comments addComments(@PathVariable int userid, @PathVariable int postid, @RequestBody String comment)
	{
		Comments obj=new Comments();
		obj.comment=comment;
		obj.postid=postid;
		obj.date=new Date();
		
		User user=userRepo.findById(userid).get();
		obj.username=user.username;
		
	  return commentsrepo.save(obj);
		
	}
	
	@RequestMapping("getComments{postid}")
	public List<Comments> getComments(@PathVariable int postid)
	{
		return commentsrepo.findByPostidOrderByIdDesc(postid);
	}
	
	
	@RequestMapping("likescount{userid}and{postid}")
	public int likescount(@PathVariable int userid, @PathVariable int postid)
	{
		Posts post=postrepo.findById(postid).get();
		int count=likedatarepo.countByUseridAndPostid(userid, postid);
		if(count>0)
			return post.likecount;
		
		post.likecount++;
		postrepo.save(post);
		LikeData likedata=new LikeData();
		
		likedata.date=new Date();
		likedata.postid=postid;
		likedata.userid=userid;
		likedatarepo.save(likedata);	
		return post.likecount;
	
}
	
	
	@RequestMapping("getPostsFromMyConnections{id}")
	public List<PostsData> getPostsFromMyConnections(@PathVariable int id){
	return postrepo.getPostsFromMyConnections(id);
	}
	
	@RequestMapping("addnewpost{userid1}")
	public Posts addnewpost(@PathVariable int userid1, @RequestBody String content)
	{
		Posts post= new Posts();
		post.content=content;
		post.setDate(new Date());
		post.userid=userid1;
		return postrepo.save(post);
	}
	
	
	//displaying all posts
	@RequestMapping("getAllPost{userid1}")
	public List<Posts> getAllPost(@PathVariable int userid1){
		return postrepo.findByUseridOrderByIdDesc(userid1);
	}

	
	
	@RequestMapping("getfollowerslist{userid1}")
	public List<User> getfollowerslist(@PathVariable int userid1)
	{
		try {
            List<Integer> list =connectionRepo.findUserIdsByUserId2AndConnectionStatusis2(userid1);
           return userRepo.findByIdIn(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
	}

	@RequestMapping("getfollowinglist{userid1}")
	public List<User> getfollowinglist(@PathVariable int userid1)
	{
		try {
            List<Integer> list =connectionRepo.findUserIdsByUserId1AndConnectionStatusIs2(userid1);
           return userRepo.findByIdIn(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
	}
	 
	 @RequestMapping("setconnections")
	 public  int set(@RequestBody Connection connection)
	 {
		 try {
			 System.out.println(connection);
			 
			Connection allconnection=	connectionRepo.findByUserid1AndUserid2(connection.userid2,connection.userid1);
			//System.out.println(allconnection);
			if(allconnection!=null)
			{
			 allconnection.setConnectionstatus(connection.connectionstatus);
			 connectionRepo.save(allconnection);
			 return connection.connectionstatus;
			}
			return -3;
			
		} catch (Exception e) {
			   e.printStackTrace();
			   return -1;
		}
		 
	 }
	
	
	 @RequestMapping("watingreq")
	 public List<User> watingreq(@RequestBody int userid2)
	 {
//		  List<Connection> a=connectionRepo.findByUserid2AndConnectionstatus(userid2, 1);//conn stats 1 only displayed
//		  
//		  List<User> aList=new ArrayList();
//		  for(Connection data:a)
//		  {
//			//System.out.println(data);
////			  data.getUserid1();
////			  String user= userRepo.findById(data.getUserid1()).get().getUsername();
//			  
//			  aList.add(userRepo.findById(data.getUserid1()).get());
//	    	}
////		  System.out.println(a);
//			 return aList;
		 
	
		 try {
				List<Integer> list=  connectionRepo.findUserIdsByUserId2AndConnectionStatus(userid2);
				return userRepo.findByIdIn(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	 }

	@RequestMapping("follow")
	public int follow(@RequestBody  Connection  connection)
	{
		try {
			Connection aConnection=	connectionRepo.findByUserid1AndUserid2(connection.userid1,connection.userid2);
		
	
			if(aConnection!=null)
			{  
				if (aConnection.connectionstatus==1) {
					return 7; // allready sended
					
				}
				if(aConnection.connectionstatus==4)
					return -2; //  use blocked by the user
				
					int psc=  userRepo.findById(connection.userid2).get().getStatus();   //private status connection
					     
					if(psc==1)
					{
							aConnection.connectionstatus=1;
							connectionRepo.save(aConnection);
							return 3; // id is privete requst send
					}
					else {
						aConnection.connectionstatus=2;
						connectionRepo.save(aConnection);
						return 4; // requst dircte acepeted id not private and follwed 
					}
						
			}
			else 
			{
						Connection newConnection=new Connection();
						newConnection.setUserid1(connection.userid1);
						newConnection.setUserid2(connection.userid2);
						int psc=  userRepo.findById(connection.userid2).get().getStatus();
								if(psc==1)
								{
										newConnection.connectionstatus=1;
										connectionRepo.save(newConnection);
										return  5; // id is privete requst send
								}
								else {
									newConnection.connectionstatus=2;
									 connectionRepo.save(newConnection);
									return 6; // requst dircte acepeted id not private and follwed 
								}
			}
			
			
		} catch (Exception e) {
			 e.printStackTrace();
			 return -1;
		}
	
	}
	
  @RequestMapping("updatestatus")//updateStatus
  public  int updatestatus(@RequestBody int userid)
  {
	try {
		Optional<User> user=userRepo.findById(userid);
		if(user.isPresent())
		{
		User u=user.get();
		
		
		if(u.getStatus()==0)
		{
			u.setStatus(1);	
			userRepo.save(u);
			return 1;  
		}
		else
		{
			u.setStatus(0);
			userRepo.save(u);
			return 0;
		}
		}
		return -1;
		
	} catch (Exception e) {
		 e.printStackTrace();
		 return -1;
	}
  }


	@RequestMapping("follwersandfollowing")//followersAndfollowingcount
	public int[] abc(@RequestBody int uid)
	{
		   try {
					int[]a=new int[3];
					a[0]=connectionRepo.findUserIdsByUserId2AndConnectionStatusiscount(uid);
					a[1]=connectionRepo.findUserIdsByUserId1AndConnectionStatusIs2count(uid);
					Optional<User> user=userRepo.findById(uid);
					if(user.isPresent())
					{
					User b=user.get();
					a[2]=b.getStatus();
                     }
					
					return a;
				} catch (Exception e) 
				{
					e.printStackTrace();
					return null;
				}
	}
	
	@RequestMapping("search{un}and{luid}")
	public List<User> ser(@PathVariable String un, @PathVariable int luid)
	{
		try {
			List<User> a=userRepo.findTop20ByUsernameStartsWith(un);
			//sanket follow sanket issue
			User user = userRepo.findById(luid).orElse(null);
			
			if(user!=null)
			{
				Iterator<User> iterator= a.iterator();
				while(iterator.hasNext())
				{
					User user2=iterator.next();
					if(user2.getUsername().equals(user.getUsername()))
					{
						iterator.remove();
					}
					
				}
			}
			//System.out.println(a);
			if(a.isEmpty())
				return null;
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			 return null;
		}
	}

	 @RequestMapping("get{id}")
	 public String[] a(@PathVariable int id)
	 {
		 String[] a=new String[1];
		Optional<User> b=userRepo.findById(id);
		if(b.isPresent())
		{
		User c=b.get();
		a[0]=c.getUsername();
		}
		return a;
	 }
	@RequestMapping("login")
	public int login(@RequestBody User userui)
	{
		try {
			if(userui.password==null)
				return -41;
				int count= userRepo.countByMobile(userui.mobile);
				if(count<0)
				return -2; // wrong mnumber
			 
				if(userRepo.countByPassword(userui.password)!=1)
				{
					return -3; //Wrongpasswrd
				}
				
				User userdb=userRepo.findByMobile(userui.mobile);
				userdb.password.equals(userui.password);
					return userdb.id;// 
				
			
		} catch (Exception e) {
			e.printStackTrace();	
			return -1;
		}
		
	}

//	add method
	@RequestMapping("register")
	public int register(@RequestBody User user) {
		
	if(user.email==null || !user.email.contains("@gmail.com"))
		return -4; //email duplicate
	
	if(userRepo.countByMobile(user.mobile)>0)
		return -3;   //mobile duplicate
	
//	if( user.mobile < 1000000000L || user.mobile > 9999999999L)
//		return -5;
	
	if(userRepo.countByUsername(user.username)>0)
		return -2;   //username validation
	
	userRepo.save(user);
	return 1;		//add user
	}
}
		


//@RequestMapping("register")
//public int re(@RequestBody User user)
//{
//	try{
//		if(userRepo.countByUsername(user.username)>0 ) // 1
//		return -4;// username duplicate
//		
//		if(userRepo.countByMobile(user.mobile)>0 )
//			return -3 ; //mobile duplicate
//		
////		if(user.mobile < 1000000000L || user.mobile > 9999999999l)
////			return -6;
//		
//		if(userRepo.countByEmail(user.email)>0 )
//			return -5; //duplicate email
//	
//		if(!user.email.contains("@gmail.com"))
//		{
//		return -2;
//
//		}
//		//user.setDate(new Date());
//		userRepo.save(user);
//		return 1;// susses
//		
//		//if(userRepo.countByPassword(null))
//		
//	} catch (Exception e) {
//		e.printStackTrace();
//		return -1;
//	}
//}
//
//
//}



//package com.example.demo;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@CrossOrigin
//public class MyController {
//
//@Autowired
//UserRepo userrepo;
//
//@Autowired
//ConnectionRepo connectionrepo;
//
//
//
//@RequestMapping("getfollowerslist{userid1}")
//public List<User> getfollowerslist(@PathVariable int userid1)
//{
//	try {
//        List<Integer> list =connectionrepo.findUserIdsByUserId2AndConnectionStatusis2(userid1);
//       return userrepo.findByIdIn(list);
//		
//	} catch (Exception e) {
//		e.printStackTrace();
//		return null;
//	
//	}
//	
//}
//
//@RequestMapping("getfollowinglist{userid1}")
//public List<User> getfollowinglist(@PathVariable int userid1)
//{
//	try {
//        List<Integer> list =connectionrepo.findUserIdsByUserId1AndConnectionStatusIs2(userid1);
//       return userrepo.findByIdIn(list);
//		
//	} catch (Exception e) {
//		e.printStackTrace();
//		return null;
//	
//	}
//	
//}
// 	
//
//@RequestMapping("setconnections")
//public int set(@RequestBody Connection connection)
//{
//	try {
//		Connection allconnection= connectionrepo.findByUserid1AndUserid2(connection.userid2, connection.userid1);
//		if(allconnection!=null)
//		{
//			allconnection.setConnectionStatus(connection.connectionStatus);
//			connectionrepo.save(allconnection);
//			return connection.connectionStatus;
//		}
//		return -3;
//	} catch (Exception e) {
//		e.printStackTrace();
//		return -1;
//	}
//}
//
//
//
//@RequestMapping("waitingrequest")
//public List<User> waitingReq(@RequestBody int userid2) {
//try {
//	List<Integer> list=connectionrepo.findUserIdsByUserId2AndConnectionStatusis2(userid2);
//	return userrepo.findAllById(list);
//} catch (Exception e) {
//	e.printStackTrace();
//	return null;
//}	
//}
//
//@RequestMapping("follow")
//public int follow(@RequestBody Connection connection) {
//try 
//{
//	Connection aConnection= connectionrepo.findByUserid1AndUserid2(connection.userid1, connection.userid2);
//	if(aConnection!=null)
//	{ System.out.println(aConnection);
//		if(aConnection.connectionStatus==1)
//		{
//			return 7; //already registered
//		}
//		if(aConnection.connectionStatus==4)
//		{
//			return -2;
//		}
//			int psc=userrepo.findById(connection.userid2).get().getStatus(); //private status connection
//			
//			if(psc==1)
//			{
//				aConnection.connectionStatus=1;
//				connectionrepo.save(aConnection);
//				return 3;    //id is private req send
//			}
//			else
//			{
//				aConnection.connectionStatus=2;
//				connectionrepo.save(aConnection);
//				return 4;	//req direct accpeted id not private and followed
//			}
//	}
//	else
//	{
//		Connection newConnection=new Connection();
//		newConnection.setUserid1(connection.userid1);
//		newConnection.setUserid2(connection.userid2);
//		int psc=userrepo.findById(connection.userid2).get().getStatus(); //private status connection
//		System.out.println("abnd"+newConnection);
//		if(psc==1)
//		{System.out.println(psc);
//			aConnection.connectionStatus=1;
//			connectionrepo.save(aConnection);
//			return 5;    //id is private req send
//  		}
//		else
//		{
//			aConnection.connectionStatus=2;
//			System.out.println(aConnection);
//			connectionrepo.save(aConnection);
//			
//			return 6;  //req direct accepted id not private and followed
//		}
//		
//	}
//	
//} 	catch (Exception e) 
//	{
//	return -1;
//	}
//}
//
//@RequestMapping("updateStatus")
//public int uS(@RequestBody int userid)
//{
//	try {
//		Optional<User> user= userrepo.findById(userid);
//		User u =user.get();             //user all propepty stored
//		int s=u.getStatus();
//		
//		if(s==0)
//		{
//			u.setStatus(1);
//			userrepo.save(u);
//			return 1;
//		}
//		else
//		{
//			u.setStatus(0);
//			userrepo.save(u);
//			return 0;
//		}
//			
//	} catch (Exception e) {
//		e.printStackTrace();
//		return -1;
//	}
//}
//
//
//@RequestMapping("followersAndfollowingcount")
//public int[] abc(@RequestBody int userid) {
//
// 	
//try {
//
//	int[] a=new int[3];
//	a[0]=78;   //followers
//	a[1]=12;	//following
//	
//	Optional<User> user= userrepo.findById(userid);
//	if(user.isPresent())     
//	{
//		User b=user.get();
//		a[2]=b.getStatus();
//	}
//	return a;
//
//} catch (Exception e) {
//	e.printStackTrace();
//	return null;
//}
//
//}
//
//
//
//
////seraching user 
//@RequestMapping("search{n}")
//public List<User> search(@PathVariable String n)
//{
//	try 
//	{
//		return userrepo.findTop20ByUsernameStartsWith(n);
//	
//	} catch (Exception e) {
//		e.printStackTrace();
//		return null;
//	}
//}
//
//
//@RequestMapping("get{userid}")
//public String[] a(@PathVariable int userid) 
//{
//	String[] a=new String[1];
//	Optional<User> b=userrepo.findById(userid);
//	User c=b.get();
//	a[0]=c.getUsername();
//	return a;
//}
//
//
//
////login method
//@RequestMapping("login")
//public int login(@RequestBody User userui)
//{
//
//	try {
//		if(userui.password==null)
//			return -4; //null data
//		int count=userrepo.countByMobile(userui.mobile);
//		
//		if(count<0)
//			return -2; //wrong number
//		
//		if(userrepo.countByPassword(userui.password)!=1)
//			return -3; //wrong password
//		
//		User userdb=userrepo.findByMobile(userui.mobile);
//		if(userdb.password.equals(userui.password));
//		  return userdb.id;//user id send to ui
//					
//	} catch (Exception e) {
//		e.printStackTrace();
//		return -1;
//	}
//}
//		
//
//
//}
//}
