app.factory('BlogService',function($http){
	
	var blogService={} //object hai
	
	blogService.addBlog=function(blog){
		
		
		var url="http://localhost:8081/testMiddleware/addblog"
			return $http.post(url,blog)
	}
	
	
	//select *from blog where approvalstatus==true
	blogService.getBlogsApproved=function(){
		
		var url="http://localhost:8081/testMiddleware/approvedblogs"
			return $http.get(url)
		
	}
	blogService.getBlog=function(id){
		var url="http://localhost:8081/testMiddleware/getblog/"+id 
		return $http.get(url)
	}
	blogService.getBlogsWaitingForApproval=function(){
		
		var url="http://localhost:8081/testMiddleware/blogswaitingforapproval"
			return $http.get(url)
	}
	blogService.approveBlogPost=function(blogPost){
		var url="http://localhost:8081/testMiddleware/approveblogpost"
			return $http.put(url,blogPost)
		
		
	}
	blogService.rejectBlogPost=function(blogPost,rejectionReason){
		var url="http://localhost:8081/testMiddleware/rejectblogpost?rejectionReason="+rejectionReason
			return $http.put(url,blogPost)
		
	}
	
	blogService.getNotificationNotViewed=function(){
		var url="http://localhost:8081/testMiddleware/notifications"
			return $http.get(url)
	}
	blogService.addBlogComment=function(blogPost,commentTxt){
	var url="http://localhost:8081/testMiddleware/addcomment?commentTxt="+commentTxt	
		return $http.post(url,blogPost)
		
	}
	blogService.getBlogComments=function(blogPostId){
		var url="http://localhost:8081/testMiddleware/getcomments/"+blogPostId
		return $http.get(url)
	}
	
	return blogService;
})
