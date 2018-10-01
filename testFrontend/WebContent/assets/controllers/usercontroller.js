app.controller('UserCtrl', function($scope, UserService, $location,$rootScope,$cookieStore) {
	// function for registration
	$scope.registration = function(user) {
		console.log(user.firstname);

		UserService.registration(user).then(function() {

			alert('Registered sucessfully.... please login again')
			$location.path('/login')

		}, function(response) {

			$scope.error = response.data // ErrorClazz object in JASON fmt
 
		})

	}

	$scope.login = function(user) {
		UserService.login(user).then(function(response) {
        $cookieStore.put('userDetails',response.data)
			$rootScope.user=response.data
           alert('sucessfully logged in...')
			$location.path('/home')

		}, function(response) {

			$scope.error = response.data

		})

	}

	UserService.getAllJobs().then(function(response) {

	}, function(response) {
		$location.path('/login')
	})

$scope.updateProfile=function(user){
		
		UserService.updateProfile(user).then(function(response){
			
			$rootScope.user=response.data
			$cookieStore.put('userDetails',response.data)
			alert('Updated user profile sucessfully....')
			$location.path('/login')
			
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
		})
		
		
		
		
		
	}


})
