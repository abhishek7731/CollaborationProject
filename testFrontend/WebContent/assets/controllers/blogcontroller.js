app.controller('BlogCtrl',function($scope,$location,BlogService,$rootScope){
	
	$scope.addBlog=function(blog){
		
		
		BlogService.addBlog(blog).then(function(response){
			
			
			alert('BlogPost INserted sucessfully and it is waiting for approval......')
			$location.path('/home')
		},function(response){
			
			//Not authenticated
			if (response.data.errorCode == 5)
				$location.path('/login')
				$scope.error = response.data
			
			//Authenticated but while inserting threw an exception
			
			
			
		})
		
	}
	
    function getBlogsApproved(){
	
	BlogService.getBlogsApproved().then(function(response){
		//response.data is list of blogs approved
		$scope.getBlogsApproved=response.data
		
	},function(response){
		
		if(response.status==401)
			$location.path('/login')
		
	})
    }
    
 function getBlogsWaitingForApproval(){
    	
	 BlogService.getBlogsWaitingForApproval().then(function(response){
		 
		$scope.blogsWaitingForApproval=response.data 
		 
	 },function(response){
		if(response.status==401) 
		 $location.path('/login')
	
	 
	 })
    	
    }
    getBlogsApproved() // yeh nhi likha hoga to upar vala function execute nhi hoga
   
    //call this function only for admin role
    if($rootScope.user.role=='ADMIN')
    getBlogsWaitingForApproval()
    
})