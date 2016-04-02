var page = angular.module("hotspice", ['ngRoute']);

page.config(['$routeProvider',function($routeProvider){
	$routeProvider
    .when('/food', {
      templateUrl: 'food.html',
      controller:'HSController'
    })
    .when('/orders', {
      templateUrl: 'orders.html',
      controller:'OrdersController',
      
    })
    .when('/newDish', {
        templateUrl: 'add_dish.html',
        controller:'HSController'
      })
}]);

page.controller("HSController", function($scope, $http) {

	$scope.menu = null;
	$scope.menuItems = {};
	$scope.categories = {};
	$scope.types = {};
	
	
	if($scope.menu==null || $scope.menu==undefined){
		var menuResp = $http.get("menu", {}, {});
		menuResp.success(function(dataFromServer, status, headers, config) {
			console.log("menu");
			console.log(dataFromServer);
			$scope.menu = dataFromServer;
			$scope.menuItems = $scope.menu.menu;
			$scope.categories = dataFromServer.categories;
			$scope.types = dataFromServer.types;
			
		});
		menuResp.error(function(data, status, headers, config) {
	       alert("fetching menu failed!");
	    });
	}
	
	
	$scope.selectedType = "All";
	$scope.selectedCategory = "All";
	
	$scope.filterItem = {
	    category: $scope.menuItems
	  }

	$scope.selectedFoodItem = null;
	
	$scope.changeSelectedFood = function(food){
		$scope.selectedFoodItem = food;
	}
	
	$scope.searchFood   = '';
	
	$scope.typefilter = function(food){
		if($scope.selectedType == "All"){
			return true;
		}
		return food.type==$scope.selectedType;
	}
	
	$scope.categoryfilter = function(food){
		if($scope.selectedCategory == "All"){
			return true;
		}
		if(food.category.indexOf($scope.selectedCategory)>=0){
			return true;
		}
		return false;
	}
	
	$scope.sortMenu = function(sortField){
			if($scope.sortType==sortField){
				$scope.sortReverse=!$scope.sortReverse;
			}else{
				$scope.sortType =sortField;
				$scope.sortReverse=true;
			}
	}
	
	$scope.sortType="type";
	$scope.sortReverse=false;
	
	
    $scope.newDish = {};
    $scope.newDish.name = "";
    $scope.newDish.type = "";
    $scope.newDish.description = "";
    $scope.newDish.available = "";
    $scope.newDish.price = "";
    $scope.newDish.currency = "";
    $scope.newDish.imagePath = "";
    $scope.newDish.category = "";
    $scope.newDish.locations = "";
    
	
    $scope.newDish.add = function(item, event) {
        console.log("--> Submitting form");
        
        var dataObject = {name:$scope.newDish.name,
        		type:$scope.newDish.type,
        		code:"00",
        		category:$scope.newDish.category.split(','),
        		description:$scope.newDish.description,
        		available:$scope.newDish.available,
        		price:$scope.newDish.price,
        		currency:$scope.newDish.currency,
        		locations:$scope.newDish.locations.split(',')};

        console.log(dataObject);
        
        var responsePromise = $http.post("menu/item", dataObject, {});
        responsePromise.success(function(dataFromServer, status, headers, config) {
           console.log(dataFromServer.title);
           alert("Food Item successfully added");
        });
         responsePromise.error(function(data, status, headers, config) {
           alert("Failed to add food item");
        });
      }
    
    $scope.editFood = function(){
    	var responsePromise = $http.post("menu/dish", $scope.selectedFoodItem, {});
        responsePromise.success(function(dataFromServer, status, headers, config) {
        	$scope.foodEditedMessage = "Meal was edited successfully !!"
            $scope.foodEdited = true;
        	console.log("message "+$scope.foodEditedMessage + " status "+$scope.foodEdited );
        
        });
         responsePromise.error(function(data, status, headers, config) {
        	 $scope.foodEditedMessage = "OOPS!! something went wrong."
             $scope.foodEdited = false;
        	 console.log("message "+$scope.foodEditedMessage + " status "+$scope.foodEdited );
        });
        
    }
    
    
});

/** filter for making long string elliptical**/
angular.module('ng').filter('cut', function () {
    return function (value, wordwise, max, tail) {
        if (!value) return '';

        max = parseInt(max, 10);
        if (!max) return value;
        if (value.length <= max) return value;

        value = value.substr(0, max);
        if (wordwise) {
            var lastspace = value.lastIndexOf(' ');
            if (lastspace != -1) {
                value = value.substr(0, lastspace);
            }
        }

        return value + (tail || ' …');
    };
});

page.controller("OrdersController", function($scope, $http) {

	
	if($scope.orders==null || $scope.orders==undefined){
		var ordersResponse = $http.get("orders", {}, {});
		ordersResponse.success(function(dataFromServer, status, headers, config) {
			console.log(dataFromServer);
			$scope.orders = dataFromServer;
		});
		ordersResponse.error(function(data, status, headers, config) {
	       alert("fetching orders failed!");
	    });
	}
    
    $scope.orders = null;
    $scope.selectedOrder = null;
    $scope.showOrder = function(order){
    	$scope.selectedOrder = order;
    }
    
    $scope.selectedStatus = "All";
    
    $scope.statusfilter = function(order){
    	if($scope.selectedStatus=="All" || order.status==$scope.selectedStatus){
    		return true;
    	}
    	return false;
    }
    
    $scope.sortOrder = function(sortField){
		if($scope.sortType==sortField){
			$scope.sortReverse=!$scope.sortReverse;
		}else{
			$scope.sortType =sortField;
			$scope.sortReverse=true;
		}
	}
    
    $scope.searchText="";
	
	$scope.sortType="type";
	$scope.sortReverse=false;
	
	$scope.updateOrderStatus = function(order){
		var new_order = {};
		new_order.orderId = order.orderId;
		new_order.status = order.status;
		var ordersResponse = $http.post("order/status", new_order, {});
		ordersResponse.success(function(dataFromServer, status, headers, config) {
			console.log(dataFromServer);
			alert("status updated!");
		});
		ordersResponse.error(function(data, status, headers, config) {
	       alert("fetching orders failed!");
	    });
	}

    
});
