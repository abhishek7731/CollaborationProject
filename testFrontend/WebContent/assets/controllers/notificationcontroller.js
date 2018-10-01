app.controller('NotificationCtrl',function($scope,NotificationService,$routeParams,$location){
	
	var id=$routeParams.id
	
	
	NotificationService.getNotification(id).then(function(response){
		
		$scope.notification=response.data//select*from notification where id=?
		
	},function(response){
		
		if(response.data==401)
			$location.path('/login')
			
	})
	NotificationService.updateNotification(id).then(function(response){
		
		getNotificationNotViewed()
	
	},function(response){
		
		
		if(response.data==401)
			$location.path('/login')
	})
	
	function getNotificationNotViewed(){
		NotificationService.getNotificationNotViewed().then(function(response){
			//update the value of variable
			//reponse.data will be list of notification not yet viewed by user
			//update ho jaana ka baad humko dropdaown mei notification show nhi hona chaiya
			$rootScope.notifications=reponse.data
			$rootScope.notificationCount=$rootScope.notifications.length
		},function(response){
			
			if(response.data==401)
				$location.path('/login')
			
		})
		
	}
	
})