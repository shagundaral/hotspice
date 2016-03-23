<html>


<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">

<style>
#header {
    background-color:black;
    color:white;
    text-align:center;
    padding:5px;
}
#nav {
    line-height:30px;
    background-color:#eeeeee;
    height:300px;
    width:100px;
    float:left;
    padding:5px;	      
}
#section {
    width:350px;
    float:left;
    padding:10px;	 	 
}
#footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
   padding:5px;	 	 
}



.left{
	float:left
}
.right{
	float:right
}




.solid {border-style: solid; border-width: 1px;}
.mix_veg {background-color:green; }
.mix_nonveg {background-color:red;}

</style>
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




	<div ng-controller="HSController">
	
	<div class="container">
		<div class="row">
			<a href="#food" class="col-lg-2 solid">Menu Items</a>
			<a href="#orders" class="col-lg-2 solid">Orders</a>
		</div>
		<div ng-view></div>
	</div>
	

</body>
<script type = "text/ng-template" id="food.html">

<div class="row">

<div>

	<button class="col-lg-2" ng-click="sortType='price'; sortReverse = !sortReverse">Sort</button>
	<select ng-model="selectedType" id="selectedType" name="selectedType">
      <option ng-repeat="option in menuItems" value="{{option.type}}">{{option.type}}</option>
    </select>
    <select ng-model="selectedCategory">
      <option ng-repeat="category in categories" value="{{category}}">{{category}}</option>
    </select>
	
	
	
</div>

<table>
	<td class="col-lg-8 solid">
		<div ng-repeat="food in menuItems | filter:typefilter | filter:categoryfilter">
			<div class="row">
				<div class="col-lg-1 solid">{{food.code}}</div>
				<div class="col-lg-2 solid">{{food.name}}</div>
				<div class="col-lg-2 solid">{{food.type}}</div>
				<div class="col-lg-2 solid">{{food.available}}</div>
				<div class="col-lg-2 solid">{{food.currency}}</div>
				<div class="col-lg-2 solid">{{food.price}}</div>
				<div class="col-lg-1 solid" ng-click="changeSelectedFood(food)">
					Edit</div>
			</div>
		</div>
	<td>
	<td class="col-lg-4 solid">
		<div ng-if="selectedFoodItem!=null"
			class="{solid : selectedFoodItem!=null}">
			<form action="">
				Food Code: {{selectedFoodItem.code}}<br> Food Name: <input
					type="text" value="{{selectedFoodItem.name}}"><br>
				Food Description: <input type="text"
					value="{{selectedFoodItem.description}}"><br> Food
				Currency: <input type="text" value="{{selectedFoodItem.currency}}"><br>
				Food Price: <input type="text" value="{{selectedFoodItem.price}}"><br>
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

<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/angular.js"></script>	
<script src="../resources/js/bootstrap.min.js"></script>
<script src = "../resources/js/angular-route.min.js"></script>
<script src="../resources/js/listing.js"></script>

</html>