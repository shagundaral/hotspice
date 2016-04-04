var page = angular.module("hotspice", ['ngRoute']);

page.config(['$routeProvider',function($routeProvider){
	$routeProvider
    .when('/food', {
      templateUrl: 'resources/static/view/food.html',
      controller:'HSController'
    })
    .when('/orders', {
      templateUrl: 'resources/static/view/orders.html',
      controller:'OrdersController',
      
    })
    .when('/newDish', {
        templateUrl: 'resources/static/view/add_dish.html',
        controller:'HSController'
      })
      .otherwise({
          redirectTo: '/food'
      });
}]);

page.controller("HSController", function($scope, $http) {

	$scope.menu = null;
	$scope.menuItems = {};
	$scope.categories = {};
	$scope.types = {};
	
	
	if($scope.menu==null || $scope.menu==undefined){
		var menuResp = $http.get("menu", {}, {});
		menuResp.success(function(dataFromServer, status, headers, config) {
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
        
    	if($scope.newDish.name == "" || $scope.newDish.type == ""){
    		
    		alert("Please fill required fields.");
    		
    	}else{
    	
    		var dataObject = {
        		name:$scope.newDish.name,
        		type:$scope.newDish.type,
        		code:"00",
        		category:$scope.newDish.category.split(','),
        		description:$scope.newDish.description,
        		available:$scope.newDish.available,
        		price:$scope.newDish.price,
        		currency:$scope.newDish.currency,
        		locations:$scope.newDish.locations.split(',')};

        
	        var responsePromise = $http.post("menu/item", dataObject, {});
	        responsePromise.success(function(dataFromServer, status, headers, config) {
	            $scope.addDishMessage = "Food Item successfully added";
	            $scope.dishAdded = true;
	        	//alert("Food Item successfully added");
	        });
	         responsePromise.error(function(data, status, headers, config) {
	        	 $scope.addDishMessage = "Failed to add food item";
		         $scope.dishAdded = false;
	           //alert("Failed to add food item");
	        });
    	}
      }
    
    $scope.editFood = function(){
    	var responsePromise = $http.post("menu/dish", $scope.selectedFoodItem, {});
        responsePromise.success(function(dataFromServer, status, headers, config) {
        	$scope.foodEditedMessage = "Meal was edited successfully !!"
            $scope.foodEdited = true;
        
        });
         responsePromise.error(function(data, status, headers, config) {
        	 $scope.foodEditedMessage = "OOPS!! something went wrong."
             $scope.foodEdited = false;
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
			$scope.orders = dataFromServer;
		});
		ordersResponse.error(function(data, status, headers, config) {
			$scope.orderPageMessage = "Fetching orders failed";
			$scope.orderEdited = false;
			//alert("fetching orders failed!");
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
			$scope.orderPageMessage = "Status updated successfully";
			$scope.orderEdited = true;
			//alert("status updated!");
		});
		ordersResponse.error(function(data, status, headers, config) {
			$scope.orderPageMessage = "Failed to update status";
			$scope.orderEdited = true;
	       //alert("fetching orders failed!");
	    });
	}

    
});
