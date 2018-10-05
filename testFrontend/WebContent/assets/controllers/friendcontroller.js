 app.controller('FriendCtrl', function($scope, $location,$rootScope, FriendService) {

	
	function getSuggestedUsers(){
	FriendService.getSuggestedUsers().then(function(response) {

		$scope.suggestedUsers = response.data

	}, function(response) {
		if (response.status == 401)
			$location.path('/login')

	})

	}
	$scope.sendFriendRequest=function(friendRequestToId){ //toId is an User object,friend.setToId(toId)
		
		FriendService.sendFriendRequest(friendRequestToId).then(function(response){
			
		alert('Friend Request Has Been Sent Sucessfully')
		getSuggestedUsers()
		
		},function(response){
			
			if (response.status == 401)
				$location.path('/login')

			
		
	  })	
	
	}
	function getPendingRequests(){
		FriendService.getPendingRequests().then(function(response){
			
		$scope.pendingRequests=response.data //List Of Pending Requests(select from Friend where f.toId.email=:email and status='P'")
			
		},function(response){
			
			
			if (response.status == 401)
				$location.path('/login')
			
		})
		
	}
	
	
	$scope.acceptRequest=function(pendingRequest){ //pendingRequests objject of type friend
		
		FriendService.acceptRequest(pendingRequest).then(function(response){
			
			getPendingRequests()
		
		},function(response){
			
			if (response.status == 401)
				$location.path('/login')
		})
		
	}
	
	
$scope.deleteRequest=function(pendingRequests){ //pendingRequests objject of type friend
		
		FriendService.deleteRequest(pendingRequests).then(function(response){
			
			getPendingRequests()
		
		},function(response){
			
			if (response.status == 401)
				$location.path('/login')
		})
		
	}
FriendService.listOfFriends().then(function(response){
	
$scope.friendDetails=response.data //it is of type user coz we are selecting fromId and ToId

},function(response){
	
	if (response.status == 401)
		$location.path('/login')
})

$scope.startChat=function(){
	$location.path('/chat')

	$rootScope.email=email;
	
	
}


	getSuggestedUsers()
	getPendingRequests()
	
})