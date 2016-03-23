var angular = angular.module("hotspice", ['ngRoute']);

angular.config(['$routeProvider',function($routeProvider){
	$routeProvider
    .when('/food', {
      templateUrl: 'food.html',
      controller:'HSController'
    })
    .when('/orders', {
      templateUrl: 'orders.html',
      controller:'HSController'
    }).
    otherwise({
       redirectTo: '/food'
    });
}]);

angular.controller("HSController", function($scope) {

	$scope.menu = window.menuItems;
	$scope.menuItems = window.menuItems.menu;
	$scope.categories = window.menuItems.categories;
	$scope.selectedType = $scope.menuItems[0].type;
	$scope.selectedCategory = "All";
	
	$scope.filterItem = {
	    category: window.menuItems.menu
	  }

	$scope.selectedFoodItem = null;
	
	$scope.changeSelectedFood = function(food){
		$scope.selectedFoodItem = food;
	}
	
	
	$scope.typefilter = function(food){
		return food.type==$scope.selectedType;
	}
	
	$scope.typefilter = function(food){
		if(food.category.indexOf($scope.selectedCategory)>=0){
			return true;
		}
		return false;
	}
	
	$scope.openTab="beverage";
	$scope.switchTab = function(choice){
		$scope.openTab=choice;
	}
	
	$scope.sortType="name";
	$scope.sortReverse=false;
	
	$("button").click(function(){
		$.ajax({url: "demo_test.txt", success: function(result){
			$("#div1").html(result);
		}});
	});

});

