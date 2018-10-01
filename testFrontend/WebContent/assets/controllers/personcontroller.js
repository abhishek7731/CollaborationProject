app.controller('PersonCtrl',function($scope,PersonService,$location){///getting PersonService from factory PersonService will execute personservice.js and will be returned to this
	
	//getallpersons function in service
	//HttpResponse object as a result.
	//response- sucess or faliure
	//sucess- response[Array of persons as data , 200 OK]
	//faliure-response[errorClaszz as a data, 404 as a status code]
	 //backend mei select*from query chal rahe hogie
	function getAllPersons(){
	PersonService.getAllPersons().then(function(response){
		
		$scope.persons=response.data //response.data is Array of Person Object in JASON Format
	
     },function(response) {
    	 $scope.error=response.data //response.data is ErrorClazz object
	      console.log(response.data)
	      console.log(response.status)
	
})

	}
$scope.savePerson=function(person){
		PersonService.savePerson(person).then(function(response){
			alert('person details gets inserted sucessfully')
			$scope.person='' //add karna ka baad form ko khaale karna ka liya
			getAllPersons() 
		//$location.path('/getallpersons')
			},function(response){
				
				$scope.error=response.data
				
				
			})
		
		
		
	}
 $scope.deletePerson=function(id){
	 
	 PersonService.deletePerson(id).then(function(response){
		  getAllPersons()
	 },function(response){
		 
		 
		 $scope.error=response.data

		 
		 
		 
	 })
	 
	 
 }



getAllPersons()



	})
		
	//callService.then(function1,function2)
	//function1- sucess[200-299] if status code is the range
	//function2- faliure other status code is for the error


