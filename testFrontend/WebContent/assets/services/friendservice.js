app.factory('FriendService',function($http){
	
	var friendService={}
	
	friendService.getSuggestedUsers=function(){
var url="http://localhost:8081/testMiddleware/suggestedusers"
	return $http.get(url)
	}
	
	friendService.sendFriendRequest=function(toId){
		
		
		var url="http://localhost:8081/testMiddleware/friendrequest"
			return $http.post(url,toId)
	}
	
	friendService.getPendingRequests=function(){
		
		var url="http://localhost:8081/testMiddleware/pendingrequests"
		return $http.get(url)
	}
	
	
friendService.acceptRequest=function(pendingRequest){
		
		var url="http://localhost:8081/testMiddleware/acceptrequests"
		return $http.put(url,pendingRequest)
	}

friendService.deleteRequest=function(pendingRequest){
	
	var url="http://localhost:8081/testMiddleware/deleterequests"
	return $http.put(url,pendingRequest)
}

friendService.listOfFriends=function(){
	
	var url="http://localhost:8081/testMiddleware/listoffriends"
		return $http.get(url)
	
}

	
	return friendService;
	
	
	
})