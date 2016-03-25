<html>


<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../resources/css/base-style.css">


<script>
	var menuItems = ${menu};
</script>
<body ng-app="hotspice">
<div id="header">Hot Spice</div>
 <div id="nav">
   		<div class="row">
			<a href="#food">Menu Items</a>
			<a href="#orders">Orders</a>
		</div>
   </div>

	<div ng-controller="HSController" id="section">
	
	<div class="container">
		<div ng-view></div>
	</div>
	</div>
	

</body>
<script type = "text/ng-template" id="food.html">

<div class="row">

<div>

	<select ng-model="selectedType" id="selectedType" name="selectedType">
      <option ng-repeat="option in menuItems " value="{{option.type}}">{{option.type}}</option>
    </select>
    
    <select ng-model="selectedCategory">
      <option ng-repeat="category in categories" value="{{category}}">{{category}}</option>
    </select>
    
    <button ng-click="addFoodItem()"><a href="#newDish">Add new Dish</a></button>
	
	
</div>
<br>
<table class="table table-bordered table-striped">
	<td class="col-lg-8 solid left">
	
			<div class="row">
				<div class="col-lg-1 solid" ng-click="sortMenu('code'); ">code</div>
				<div class="col-lg-2 solid" ng-click="sortMenu('name');">name</div>
				<div class="col-lg-2 solid" ng-click="sortMenu('type');">type</div>
				<div class="col-lg-2 solid" ng-click="sortMenu('available');">available</div>
				<div class="col-lg-2 solid">currency</div>
				<div class="col-lg-2 solid" ng-click="sortMenu('price');">price</div>
			</div>
	
		<div ng-repeat="food in menuItems | filter:typefilter:true | filter:categoryfilter | orderBy:sortType:sortReverse">
			<div class="row" class="tr">
				<div class="col-lg-1 solid">{{food.code}}</div>
				<div class="col-lg-2 solid">{{food.name | cut:true:10:' ...'}}</div>
				<div class="col-lg-2 solid">{{food.type}}</div>
				<div class="col-lg-2 solid">{{food.available}}</div>
				<div class="col-lg-2 solid">{{food.currency}}</div>
				<div class="col-lg-2 solid">{{food.price}}</div>
				<div class="col-lg-1 solid" ng-click="changeSelectedFood(food)">
					Edit</div>
			</div>
		</div>
	<td>
	<td class="col-lg-4 solid right">
		<div ng-if="selectedFoodItem!=null" class="{solid : selectedFoodItem!=null} textBarsContainer1">
			<form action="">
				Food Code: {{selectedFoodItem.code}}<br> 
				Food Name: <input type="text" class="textBars1" value="{{selectedFoodItem.name}}"><br>
				Food Description: <input type="text" class="textBars1" value="{{selectedFoodItem.description}}"><br> 
				Food Currency: <input type="text" class="textBars1" value="{{selectedFoodItem.currency}}"><br>
				Food Price: <input type="text" class="textBars1" value="{{selectedFoodItem.price}}"><br>
				Food Category: <input type="text" class="textBars1" value="{{selectedFoodItem.category}}"><br>
				<button>Edit</button>
			</form>

		</div>
	</td>

</table>

</div>

</script>

<script type = "text/ng-template" id = "orders.html">
<div>
orders page goes here
</div>
</script>

<script type = "text/ng-template" id = "add_dish.html">
<div class="textBarsContainer">
<form>
	Food Name: <input type="text" class="textBars" name="dishName" ng-model="newDish.name"><br>
	Food Description: <input class="textBars" type="text" ng-model="newDish.description"><br> 
	Food Currency: <input type="text" class="textBars" ng-model="newDish.currency"><br>
	Food Price: <input type="text" class="textBars" ng-model="newDish.price"><br>
	Food Category: <input type="text" class="textBars" ng-model="newDish.category"><br>
	Location: <input type="text" class="textBars" ng-model="newDish.locations"><br>
	Image path: <input type="text" class="textBars" ng-model="newDish.imagePath"><br>
	Available : <input type="radio" class="radioBar" ng-model="newDish.available" value="Yes"> Yes  
				<input type="radio" class="radioBar" ng-model="newDish.available" value="No"> No <br>
	<div class="formSubmit" ng-click="newDish.add()">Save</div>
</form>
</div>
</script>

<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/angular.js"></script>	
<script src="../resources/js/bootstrap.min.js"></script>
<script src = "../resources/js/angular-route.min.js"></script>
<script src="../resources/js/listing.js"></script>

</html>