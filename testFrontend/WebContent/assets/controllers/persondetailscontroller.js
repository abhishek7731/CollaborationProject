
//Yeh controller tabhi load hoga jab getperson/22 koi id hogie saath mei
//routeparams tabhi use karta jab url ka saath kuch bind krna ho

app.controller('PersonDetailsCtrl',function($scope,PersonService,$routeParams,$location){
	var id=$routeParams.id
	
	PersonService.getPerson(id).then(function(response){
		
	$scope.person=response.data
	console.log(response.status)
	
	},function(response){
		
		console.log(response.status)
	})

$scope.updatePerson=function(person){
		
		PersonService.updatePerson(person).then(function(response){
			//redirect the user to list of person
			$location.path('/getperson/'+person.id)
			
		},function(response){
			
			//display error in updateform.html
			$scope.error=response.data
			
		})
		
		
		
	}
})