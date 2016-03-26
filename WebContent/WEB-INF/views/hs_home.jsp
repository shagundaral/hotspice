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

Foot Type:
	<select ng-model="selectedType" id="selectedType" name="selectedType">
      <option ng-repeat="option in types" value="{{option}}">{{option}}</option>
    </select>
    &nbsp;&nbsp;&nbsp;
Food Category:
    <select ng-model="selectedCategory">
      <option ng-repeat="category in categories" value="{{category}}">{{category}}</option>
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <button ng-click="addFoodItem()"><a href="#newDish">Add new Dish</a></button>
	
	
</div>
<br>

<form>
    <div class="form-group">
      <div class="input-group">
        <div class="input-group-addon"><i class="fa fa-search"></i></div>
        <input type="text" class="form-control" placeholder="Search Food Item" ng-model="searchFood">
      </div>      
    </div>
  </form>

			<div class="row">
				<div class="col-lg-1 col-md-6 solid highlightText" ng-click="sortMenu('code');">Code</div>
				<div class="col-lg-2 col-md-6 solid highlightText" ng-click="sortMenu('name');">Name</div>
				<div class="col-lg-2 solid highlightText" ng-click="sortMenu('type');">Type</div>
				<div class="col-lg-2 solid highlightText" ng-click="sortMenu('available');">Available</div>
				<div class="col-lg-2 solid highlightText">Currency</div>
				<div class="col-lg-2 solid highlightText" ng-click="sortMenu('price');">Price</div>
			</div>
	
		<div ng-repeat="food in menuItems | filter:typefilter:true | filter:categoryfilter | filter:searchFood | orderBy:sortType:sortReverse">
			<div class="row" class="tr">
				<div class="col-lg-1 solid" class="btn btn-info btn-lg" data-toggle="modal" data-target="#FoodItemModal" ng-click="changeSelectedFood(food)">{{food.code}}</div>
				<div class="col-lg-2 solid">{{food.name | cut:true:20:' ...'}}</div>
				<div class="col-lg-2 solid">{{food.type}}</div>
				<div class="col-lg-2 solid">{{food.available}}</div>
				<div class="col-lg-2 solid">{{food.currency}}</div>
				<div class="col-lg-2 solid">{{food.price}}</div>
				
			</div>
		</div>


</div>


<div id="FoodItemModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">{{selectedFoodItem.code}}</h4>
      </div>
      <div class="modal-body">
        <div ng-if="selectedFoodItem!=null" class="{solid : selectedFoodItem!=null} textBarsContainer1">
			<form action="">
				Food Name: <input type="text" class="textBars1" value="{{selectedFoodItem.name}}" ng-model="selectedFoodItem.name"><br>
				Food Description: <input type="text" class="textBars1" value="{{selectedFoodItem.description}}" ng-model="selectedFoodItem.description"><br> 
				Food Currency: <input type="text" class="textBars1" value="{{selectedFoodItem.currency}}" ng-model="selectedFoodItem.currency"><br>
				Food Price: <input type="text" class="textBars1" value="{{selectedFoodItem.price}}" ng-model="selectedFoodItem.price"><br>
				Food Category: <input type="text" class="textBars1" value="{{selectedFoodItem.category}}" ng-model="selectedFoodItem.category"><br>
				<button ng-click="editFood()">Edit</button>
			</form>

		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>



</script>

<script type = "text/ng-template" id = "orders.html">
<div>

	<p ng-show="orders.orderByStatusCount.CONFIRMED!=null" style="display: inline; " ng-click="selectedStatus='CONFIRMED'">Confirmed: {{orders.orderByStatusCount.CONFIRMED}} </p>
	<p ng-show="orders.orderByStatusCount.DISPATCHED!=null" style="display: inline; highlightText" ng-click="selectedStatus='DISPATCHED'"> | Dispatched: {{orders.orderByStatusCount.DISPATCHED}} </p>
	<p ng-show="orders.orderByStatusCount.DELIVERED!=null" style="display: inline; highlightText" ng-click="selectedStatus='DELIVERED'"> | Delivered: {{orders.orderByStatusCount.DELIVERED}} </p>
	<p ng-show="orders.orderByStatusCount.CANCELLED!=null" style="display: inline; highlightText" ng-click="selectedStatus='CANCELLED'"> | Cancelled: {{orders.orderByStatusCount.CANCELLED}}</p>
	<br><br>

<form>
    <div class="form-group">
      <div class="input-group">
        <div class="input-group-addon"><i class="fa fa-search"></i></div>
        <input type="text" class="form-control" placeholder="Search" ng-model="searchText">
      </div>      
    </div>
  </form>

		
			<div class="row">
				<div class="col-lg-2 solid highlightText" ng-click="sortOrder('orderId'); ">Order Id</div>
				<div class="col-lg-2 solid highlightText" >Customer Id</div>
				<div class="col-lg-4 solid highlightText" ng-click="sortOrder('timeOfOrder');">Time Of Order</div>
				<div class="col-lg-2 solid highlightText" ng-click="sortOrder('status');">Status</div>
				<div class="col-lg-2 solid highlightText" ng-click="sortOrder('totalAmount');">Total Amount</div>
			</div>
	
		<div ng-repeat="order in orders.orders | filter:statusfilter | filter:searchText | orderBy:sortType:sortReverse ">
			<div class="row" class="tr">
				<div class="col-lg-2 solid" class="btn btn-info btn-lg" data-toggle="modal" data-target="#orderModal" ng-click="showOrder(order)">{{order.orderId}}</div>
				<div class="col-lg-2 solid" ng-click="showCustomerData(order.customer)">{{order.customer.id}}</div>
				<div class="col-lg-4 solid">{{order.timeOfOrder}}</div>
				<div class="col-lg-2 solid">
				<select ng-model="order.status" ng-change="updateOrderStatus(order)">
			      <option value="CONFIRMED">CONFIRMED</option>
			      <option value="DISPATCHED">DISPATCHED</option>
			      <option value="DELIVERED">DELIVERED</option>
			      <option value="CANCELLED">CANCELLED</option>
			    </select>
				</div>
				<div class="col-lg-2 solid">{{order.totalAmount}}</div>
				<!--<div class="col-lg-1 solid" ng-click="showOrder(order)">View</div>-->
			</div>
		</div>

</div>

<div id="orderModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">{{selectedOrder.orderId}} | {{selectedOrder.status}}</h4>
      </div>
      <div class="modal-body">
        <form>
				
				City: {{selectedOrder.city}}<br>
				Time of Order: {{selectedOrder.timeOfOrder}}<br> 
				Time of delivery: {{selectedOrder.timeOfDelivery}}<br>
				Order Amount: {{selectedOrder.totalAmount}}<br>
				Delivery Address: {{selectedOrder.address}}<br>
				Customer contact : {{selectedOrder.customer.phoneNumber}}<br>
				Customer email : {{selectedOrder.customer.emailId}}<br>
				Order:<p ng-repeat="dish in selectedOrder.foodItems" style="display: inline;">{{dish.name}},</p>
				drop down for change status
				
			</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>


</script>

<script type = "text/ng-template" id = "add_dish.html">
<div ng-controller="HSController" id="section">
	<div class="textBarsContainer">
	<form>
		Food Name: <input type="text" class="textBars" name="dishName" ng-model="newDish.name"><br>
		Food Description: <input class="textBars" type="text" ng-model="newDish.description"><br>
		Food Type: <input class="textBars" type="text" ng-model="newDish.type"><br> 
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
</div>
</script>

<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/angular.js"></script>	
<script src="../resources/js/bootstrap.min.js"></script>
<script src = "../resources/js/angular-route.min.js"></script>
<script src="../resources/js/listing.js"></script>

</html>