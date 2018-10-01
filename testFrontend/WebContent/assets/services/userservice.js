app.factory('UserService', function($http) {

	var userService = {}

	userService.registration = function(user) {
		console.log(user.firstname);
		var url = "http://localhost:8081/testMiddleware/register"
		return $http.post(url, user)
	}

	userService.login = function(user) {

		var url = "http://localhost:8081/testMiddleware/login"
		return $http.put(url, user)

	}

	userService.getAllJobs = function() {

		var url = "http://localhost:8081/testMiddleware/getalljobs"
		return $http.get(url)

	}

	userService.logout = function() {

		var url = "http://localhost:8081/testMiddleware/logout"
		return $http.put(url)

	}

	userService.updateProfile = function(user) {

		var url = "http://localhost:8081/testMiddleware/updateprofile"
		return $http.put(url, user)

	}

	return userService;

})
