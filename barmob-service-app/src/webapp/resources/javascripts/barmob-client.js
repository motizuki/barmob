jo.load();

//host
var host = "http://localhost:8080/barmob/restful";

//caches variables
var metadata = 0;
var clientId;
var mesaid;
var drinksDS = [];
var entranceDS = [];
var mainDishesDS = [];
var dessertDS = [];
var promotionDS = [];


function refreshData(){
	
        drinksDS = [];
        entranceDS = [];
        mainDishesDS = [];
        dessertDS = [];
        promotionDS = [];
        billDS = [["Description","Amount","Price"]];

		if(clientId != undefined){
			$.getJSON(host+'/order/client/'+clientId, function(data) {
				var total = 0;
				for (var key in data) {
						total += data[key].amount * data[key].menu.price;
						billDS.push([data[key].menu.name,data[key].amount,"$ "+data[key].menu.price]);
					}
				billDS.push(["Total","------","$ "+total]);
			});
		}
        $.getJSON(host+'/menu/type/drink', function(data) {
            for (var key in data) {
                if(data[key].availability == true) {drinksDS.push({title: data[key].name, id: data[key].id});}
                }
        });

        $.getJSON(host+'/menu/type/dessert', function(data) {
            for (var key in data) {
                if(data[key].availability == true) {dessertDS.push({title: data[key].name, id: data[key].id});}
                }
        });

        $.getJSON(host+'/menu/type/main', function(data) {
            for (var key in data) {
                if(data[key].availability == true) {mainDishesDS.push({title: data[key].name, id: data[key].id});}
                }

        });
        $.getJSON(host+'/menu/type/entrance', function(data) {
            for (var key in data) {
                if(data[key].availability == true) {entranceDS.push({title: data[key].name, id: data[key].id});}
                }
        });

        $.getJSON(host+'/menu/type/promotion', function(data) {
            for (var key in data) {
                if(data[key].availability == true) {promotionDS.push({title: data[key].name, id: data[key].id});}
                }

        });
		joCache.set("menu", function(v, stack, scn) {
			var back;
			var card = new joCard([
				list = new joMenu([
					{ title: "Drinks", id: "drinks" },
					{ title: "Entrances", id: "entrances" },
					{ title: "Main Dishes", id: "maindishes" },
					{ title: "Desserts", id: "desserts" },
                    { title: "Promotions", id: "promotions" }
				])
			]).setTitle("Menu");

		card.activate = function() {
			list.deselect();
		};

		list.selectEvent.subscribe(function(id) {
			if (id == "drinks")
				stack.push(joCache.get(id, stack, scn));
			else if (id == "entrances")
				stack.push(joCache.get(id, stack, scn));
			else if (id == "maindishes")
				stack.push(joCache.get(id, stack, scn));
		    else if (id == "desserts")
       			stack.push(joCache.get(id, stack, scn));
			else if (id == "promotions")
				stack.push(joCache.get(id, stack, scn));
		}, this);
		
			return card;
		});

		joCache.set("drinks", function(v, stack, scn) {
			var back;
			var card = new joCard([
				list = new joMenu(drinksDS)
			]).setTitle("Menu - Drinks");

		card.activate = function() {
			list.deselect();
		};

		list.selectEvent.subscribe(function(id) {
			$.getJSON(host+ '/menu/'+id, function(data) {
				stack.push(joCache.get("makeorder",data, stack, scn));
				refreshData();
			});
			
		}, this);
		
			return card;
		});
		joCache.set("entrances", function(v, stack, scn) {
			var back;
			var card = new joCard([
				list = new joMenu(entranceDS)
			]).setTitle("Menu - Main Dishes");

		card.activate = function() {
			list.deselect();
		};

		list.selectEvent.subscribe(function(id) {
			$.getJSON(host+'/menu/'+id, function(data) {
				stack.push(joCache.get("makeorder",data, stack, scn));
				refreshData();
			});
		}, this);
		
			return card;
		});

		joCache.set("maindishes", function(v, stack, scn) {
			var back;
			var card = new joCard([
				list = new joMenu(mainDishesDS)
			]).setTitle("Menu - Main Dishes");

		card.activate = function() {
			list.deselect();
		};

		list.selectEvent.subscribe(function(id) {
			$.getJSON(host+'/menu/'+id, function(data) {
				stack.push(joCache.get("makeorder",data, stack, scn));
				refreshData();
			});
		}, this);
		
			return card;
		});

		joCache.set("desserts", function(v, stack, scn) {
			var back;
			var card = new joCard([
				list = new joMenu(dessertDS)
			]).setTitle("Menu - Desserts");

		card.activate = function() {
			list.deselect();
		};

		list.selectEvent.subscribe(function(id) {
			$.getJSON(host+'/menu/'+id, function(data) {
				stack.push(joCache.get("makeorder",data, stack, scn));
				refreshData();
			});
		}, this);
		
			return card;
		});

		joCache.set("promotions", function(v, stack, scn) {
			var back;
			var card = new joCard([
				list = new joMenu(promotionDS)
			]).setTitle("Menu - Promotion");

		card.activate = function() {
			list.deselect();
		};

		list.selectEvent.subscribe(function(id) {
			$.getJSON(host+'/menu/'+id, function(data) {
				stack.push(joCache.get("makeorder",data, stack, scn));
				refreshData();
			});
		}, this);

			return card;
		});

		joCache.set("makeorder", function(v, data, stack, scn) {
			var details = data.details;
			var price = data.price;
			var name = data.name;
			var menuId = data.id;

			var select;

			var orderButton = new joButton("Make order");

			orderButton.selectEvent.subscribe(function() {
				var qnt = parseInt(select.getValue())+parseInt(1);
				$.getJSON(host+'/order/create?table_id='+mesaid+'&menu_id='+menuId+'&amount='+qnt+'&observation=N/I', function(data) {
					if(data.id != undefined ){
						scn.alert("Order has been done!");
						stack.pop();
					}else if (data.errCode == "TODO TABLE IS NOT PREPARED TO RECEIVE ORDERS"){
						scn.alert("Your bill has been closed, you cannot make more orders.");

					}else{
						scn.alert("Order not processed, please try again.");
					}
				});
			});

			var card = new joCard([
			new joLabel("Item name: "+name),
			new joLabel("Item description: "+details),
			new joLabel("Price : $"+price),
			,new joGroup([
				new joLabel("Amount: "),
				select = new joSelect([ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" ],0)
			]),
			new joDivider(),
			orderButton
			]).setTitle("Make Order");

		card.activate = function() {
			list.deselect();
		};
		
			return card;
		});

		

		joCache.set("bill", function(v, stack, scn) {
			var closeBill = new joButton("Close Bill");
			var card = new joCard([
				new joGroup(
					
					new joTable(billDS).selectEvent.subscribe(function(index, table) {
						joLog("table cell:", table.getRow(), table.getCol());
					}, this).setStyle({width: "100%"})
				),
				new joDivider(),
				closeBill
			]).setTitle("Bill");

			closeBill.selectEvent.subscribe(function() {
				
				$.getJSON(host+'/table/'+mesaid+'/close', function(data) {
					if(data.status == "closed"){
						scn.alert("Your bill will be closed. Please wait");
						stack.pop();
					}else{
						scn.alert("Your bill is already closed, wait a waiter come to your table.");
					}
				});
				
				
			});

			return card;
		});

}

var App = (function() {
	var stack;
	var scn;
	var button;
	var backbutton;
	var option;
	var select;
	var urldata;
	var list;
	var menu;
	var cssnode;
	var testds;
	var login;

	function init() {
		setInterval("refreshData()",5000);		
		cssnode = joDOM.applyCSS(".htmlgroup { background: #fff; }");
		


		var toolbar;
		var nav;
		
		scn = new joScreen(
			new joContainer([
				new joFlexcol([
					nav = new joNavbar(),
					stack = new joStackScroller()
				]),
				toolbar = new joToolbar("Barmob simple example app")
			]).setStyle({position: "absolute", top: "0", left: "0", bottom: "0", right: "0"})
		);
		
		nav.setStack(stack);
		
		var ex;
		testds = new joRecord({
			uid: "",
			pwd: ""
		}).setAutoSave(true);

		var loginButton = new joButton("Login");
		login = new joCard([new joGroup([
		                                new joTitle("Welcome to the Barmob app!"),
		                                new joDivider(),
		                 				new joLabel("Table number:"),
		                				new joFlexrow(nameinput = new joInput(testds.link("uid"))),
		                				new joLabel("Password:"),
		                				new joFlexrow(new joPasswordInput(testds.link("pwd")))
		                 				])
		,new joDivider(),loginButton,null]).setTitle("Login Barmob");
		loginButton.selectEvent.subscribe(function() {

			refreshData();
			$.getJSON(host+'/table/'+testds.getProperty("uid"), function(data) {
		        if(testds.getProperty("pwd") == data.password){
                    mesaid = testds.getProperty("uid");
                    clientId = data.clientId;
                    stack.push(menu);
				}else {
				    scn.alert("Invalid password");
				}
			});
			
			
		});
		menu = new joCard([
			list = new joMenu([
				{ title: "Menu", id: "menu" },
				{ title: "Bill", id: "bill" },
				{ title: "Call waiter", id: "callwaiter" }
			])
		]).setTitle("Barmob Client");
		menu.activate = function() {
			list.deselect();
		};

		list.selectEvent.subscribe(function(id) {
			if (id == "menu")
				stack.push(joCache.get(id, stack, scn));
			else if (id == "bill")
				stack.push(joCache.get(id, stack, scn));
			else if (id == "callwaiter")
				scn.alert("Call waiter", "A waiter is comming, please wait.", function() { list.deselect(); });
		}, this);
		

		joGesture.forwardEvent.subscribe(stack.forward, stack);
		joGesture.backEvent.subscribe(stack.pop, stack);
		
		document.body.addEventListener('touchmove', function(e) {
		    e.preventDefault();
			joEvent.stop(e);
		}, false);

		stack.push(login);
	}
		
	return {
		"init": init,
		"getData": function() { return testds; },
		"getStack": function() { return stack; },
		"getButton": function() { return button; },
		"getSelect": function() { return select; },
		"getOption": function() { return option; }
	}
}());
