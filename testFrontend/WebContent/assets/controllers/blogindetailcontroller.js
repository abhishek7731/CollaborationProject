app.controller('BlogInDetailCtrl', function($scope, $location, BlogService,
		$routeParams,$sce) {

	var id=$routeParams.id
	$scope.isRejected=false
	
	BlogService.getBlog(id).then(function(response){
		
		
		$scope.blogPost=response.data // result of the query select*from blog where id=?
		$scope.content=$sce.trustAsHtml($scope.blogPost.blogcontent)
	},function(response){
		
		
		if(response.status==401)
			$location.path('/login')
		
		
	})
	
	$scope.approveBlogPost=function(blogPost){
		
		
		 BlogService.approveBlogPost(blogPost).then(function(response){
			 
			 $location.path('/blogswaitingforapproval')
			 
		 },function(response){
			 
			 
				if(response.status==401)
					$location.path('/login')
			 
		 })
		
	}
	
	
	$scope.rejectBlogPost=function(blogPost){
		 BlogService.rejectBlogPost(blogPost,$scope.rejectionReason).then(function(response){
			
			 
			 //after rejecting admin redirect the page to blogswaitingforapproval.html
			 $location.path('/blogswaitingforapproval')
		 
		 
		 },function(response){
		 
		
				if(response.status==401)
					$location.path('/login')

		 })
		 }
	$scope.showTextArea=function(){
		
		$scope.isRejected=!$scope.isRejected
		
	}

$scope.addComment=function(blogPost,commentTxt)
{
	if(commentTxt==undefined || commentTxt=="")
		$scope.error='Please enter comment...'
		else
	
	BlogService.addBlogComment(blogPost,commentTxt).then(function(response){
		
	alert('posted sucessfully')
	$scope.commentTxt=""
		$scope.error=""
			$scope.blogComment=response.data
	
	},function(response){
		

		if(response.status==401)
			$location.path('/login')
		
	})
	
}

$scope.getBlogComments=function(blogPostId){
	alert('nhi chal raha')
	BlogService.getBlogComments(blogPostId).then(function(response){
		
	$scope.blogComments=response.data //result of query select*from blogcomment where blogPostId=?
	
	},function(response){
		
		if(response.status==401)
			$location.path('/login')
		
	})
	
}

})