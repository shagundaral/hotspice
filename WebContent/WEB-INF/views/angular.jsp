
<html>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.5/angular.min.js"></script>
<script src="js/main_test.js"></script>
<style>
.green {
    color: green;
}

.red {
    color: red;
}
</style>
<script type="text/javascript">
	var message =  ${message};
	var emps = ${employees};
	
</script>

<body ng-app="main_test">
		<div ng-controller="FirstController">
		      <label>Filter: <input ng-model="searchText"></label>
		      <button ng-click="swapsort();" >Sort</button>
		      <p ng-click="removeEmp(employee);">delete</p>
		      <p ng-click="addEmp();">ADD</p>
		      
		      <div ng-if='sort'>
			      <div ng-repeat="employee in emp_list | filter:searchText |orderBy : employee.name ">
			      		<h3 ng-class="{red:$even,green:$odd}">{{employee.name}}
			      		{{employee.id}}
			      		<p ng-click="deleteEmp($index);">delete</p> </h3>
			      		</br>
			      </div>
		      </div>
		</div> 	 	
	</body>
</html>