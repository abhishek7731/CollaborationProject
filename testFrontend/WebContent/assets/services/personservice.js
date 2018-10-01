
//$http is a service in angular.js
//$http is used to make http request
//name of service is PersonService 
app.factory('PersonService',function($http){
	var personService={} //INTIALIZING  
	
	 personService.getAllPersons=function(){
		//make rest call or call middleware using $http service object
	 // return the response to the controller
		 var url="http://localhost:8081/testMiddleware/getallpersons";
		 return $http.get(url);
		
	}
	
	personService.savePerson=function(person){
		 var url="http://localhost:8081/testMiddleware/saveperson";
		return $http.post(url,person)
		
	} 
	
	personService.deletePerson=function(id){
		 
		var url="http://localhost:8081/testMiddleware/deleteperson/"+id 	
		return $http['delete'](url)
		
	}
	personService.getPerson=function(id){
		var url="http://localhost:8081/testMiddleware/getperson/"+id 
		return $http.get(url)
		
	}
	personService.updatePerson=function(person){
		
		 var url="http://localhost:8081/testMiddleware/updateperson";
		 return $http.put(url,person)

		
	}
	
	
	return personService;
})