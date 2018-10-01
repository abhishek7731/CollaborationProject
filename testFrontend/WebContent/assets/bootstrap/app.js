 var app=angular.module("app",['ngRoute','ngCookies'])
app.config(function($routeProvider) {
	$routeProvider
	.when('/home',{controller:'HomeCtrl',templateUrl:'views/home.html'})
	.when('/getallpersons',{controller:'PersonCtrl',templateUrl:'views/listofpersons.html'})
	.when('/saveperson',{controller:'PersonCtrl',templateUrl:'views/personform.html'})
      .when('/getperson/:id',{controller:'PersonDetailsCtrl',templateUrl:'views/viewperson.html'})
      .when('/getupdateform/:id',{controller:'PersonDetailsCtrl',templateUrl:'views/updateform.html'})
      .when('/getupdateform/:id',{controller:'PersonDetailsCtrl',templateUrl:'views/updateform.html'})
       .when('/registration',{controller:'UserCtrl',templateUrl:'views/registrationform.html'})
       .when('/login',{controller:'UserCtrl',templateUrl:'views/login.html'})
       .when('/getalljobs',{controller:'UserCtrl',templateUrl:'views/getalljobs.html'})
      .when('/updateprofile',{controller:'UserCtrl',templateUrl:'views/updateprofile.html'})
      .when('/addjob',{controller:'JobCtrl',templateUrl:'views/jobform.html'})
       .when('/getalljobs',{controller:'JobCtrl',templateUrl:'views/listofjobs.html'})
        .when('/addblog',{controller:'BlogCtrl',templateUrl:'views/blogform.html'})
        
       .when('/getblogs',{controller:'BlogCtrl',templateUrl:'views/listofblogsapproved.html'})
       .when('/getblog/:id',{controller:'BlogInDetailCtrl',templateUrl:'views/blogindetail.html'})
       .when('/blogswaitingforapproval',{controller:'BlogCtrl',templateUrl:'views/listofblogswaitingforapproval.html'})
       .when('/getblogwaitingforapproval/:id',{controller:'BlogInDetailCtrl',templateUrl:'views/blogapprovalform.html'})
       .when('/getnotification/:id',{controller:'NotificationCtrl',templateUrl:'views/notificationdetails.html'})
       .when('/suggestedusers',{controller:'FriendCtrl',templateUrl:'views/suggesteduserslist.html'})
       .when('/pendingrequests',{controller:'FriendCtrl',templateUrl:'views/listofpendingrequest.html'})
       .when('/listoffriends',{controller:'FriendCtrl',templateUrl:'views/friendlist.html'})
       .when('/uploadprofilepic',{templateUrl:'views/uploadprofilepicture.html'})
       .otherwise({templateUrl:'views/home.html'})
}) 

//Angular module gets instantiated app.run() will get executed
//restore userdetails to the variable user $rootScope.user

app.run(function($rootScope,$cookieStore,UserService,$location){
	if($rootScope.user==undefined) //this will happen only when we refresh index.html
		$rootScope.user=$cookieStore.get('userDetails')
	    $rootScope.logout=function(){
		alert('Entering the function logout ')
		UserService.logout().then(function(reponse) {
			
			//3 cheeza karney hai 
			//remove user variable from root scope, 
			//cookies ko delete karna,
			//redirect the user to login page
			
			delete $rootScope.user
			$cookieStore.remove('userDetails')
			$location.path('/login')
		},function(response) {
			
			if($rootScope.user!=undefined)
			
			delete $rootScope.user
			$cookieStore.remove('userDetails')
			$location.path('/login')
			99
			
		})
		
	}
	
	
	
})