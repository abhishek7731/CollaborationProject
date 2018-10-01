app.factory('NotificationService',function($http){
	
	var notificationService={}
	
	notificationService.getNotification=function(id){
		
		 var url="http://localhost:8081/testMiddleware/notification/"+id 
		return $http.get(url,id)
	}
	
	notificationService.updateNotification=function(id){
		var url="http://localhost:8081/testMiddleware/updatenotification/"+id
	     return $http.put(url,id)
	}
	
	notificationService.getNotificationNotViewed=function()
	{
		var url="http://localhost:8081/testMiddleware/notifications"
		return $http.get(url)
	}
	
	return notificationService;
	
	
})