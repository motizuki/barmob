var host = "http://localhost:8080/barmob/restful";
setInterval(function() {
    updateTables()
}, 5000);
$(function() {
    $("#tabs").tabs();
});

function report(type) {
    var htmlReport = "<table style=\"width: 70%; margin-left:auto; margin-right:auto; \" id=\"users\" class=\"ui-widget ui-widget-content\"><thead><tr class=\"ui-widget-header \"><th>Name</th><th>Amount</th></tr></thead><tbody>";

    $.getJSON(host + '/order/status/' + type, function(data) {
          if(data.errCode == undefined){
            for (var key in data) {
                htmlReport += "<tr><td>" + data[key].menu.name + "</td><td>" + data[key].amount + "</td></tr>";
            }
          }else{
                htmlReport += "<tr><td COLSPAN=2>"+data.errCode+"</td></tr>";
          }
        htmlReport += "</tbody></table>";
        $("#reportDiv").html(htmlReport);
    });

}

function updateTables() {
    $.getJSON(host + '/table', function(data) {
        var htmlTable = '';
        for (var key in data) {
            var status = '';

            if (data[key].status == "closed") {
                status = "grey";
            } else if (data[key].status == "free") {
                status = "green";
            } else if (data[key].status == "busy") {
                status = "red";
            }
            htmlTable += "<div id=\"table" + data[key].tableNumber + "\" class=\"ui-widget-content\"style=\"width: 180px; height: 180px; padding: 0.5em; float:left;\" onClick = \"orderAction(" + data[key].id + "," + data[key].clientId + ");\"><center><h3>Table " + data[key].tableNumber + "<br> Password: " + data[key].password + "</h3><img class=\"imageTable\" src=\"../resources/images/" + status + ".png\"/></center><br><p style=\"text-align: center;\">" + "Alert messages TBD" + "</p><div class=\"preloader\">&nbsp;</div></div>";

        }
        $("#tableDiv").html(htmlTable);
    });
}

function tableStatus(table, status) {
    $.getJSON(host + '/table/' + table + '/' + status, function(data) {
        if (data.status) {
            alert("Table status was changed to " + data.status);
        } else {
            alert(data.errCode);
        }
    });
}


function processOrder(id, status) {
    $.getJSON(host + '/order/' + id + '/' + status, function(data) {
        if (data.status) {
            $.getJSON(host + '/order/' + id, function(data2) {
                orderAction(data2.table.id,data2.table.clientId);
            });
            alert("Order status changed");
        } else {
            alert(data.errCode);
        }
    });
}


function orderAction(table, clientId) {

    $("#dialog-confirm").dialog({
        resizable: false,
        height: 500,
        width: 810,
        modal: true,
        buttons: {
            "Start table": function() {
                tableStatus(table, 'start');
                $(this).dialog("close");
            },
            "Reopen table": function() {
                tableStatus(table, 'reopen');
                $(this).dialog("close");
            },
            "Reset table": function() {
                tableStatus(table, 'reset');
                $(this).dialog("close");
            },
            "Cancel": function() {
                $(this).dialog("close");
            }
        }
    });

    $.getJSON(host + '/order/client/' + clientId, function(data) {

        var htmlTableDetails = "";
        htmlTableDetails = "<center><h2>Client ID " + clientId + "</h2></center> <table style=\"width: 780px;\" id=\"orders\" class=\"ui-widget ui-widget-content\"> <thead> <tr class=\"ui-widget-header \"><th>Description</th><th>Status</th><th>Action</th></tr></thead><tbody>	 ";
        if (data.errCode == undefined){
            for (var key in data) {
                var name = data[key].menu.name;
                var amount = data[key].amount;
                var status = data[key].status;
                var statusAlerta = "";
                var statusBotao = "";
                var botaoPedido = "";

                if (status == "inprogress") {
                    statusAlerta = "In progress";
                    statusBotao = "Pick up the order";
                    botaoPedido = "<button id=\"orderAction\" onClick=\"processOrder(" + data[key].id + ",'inpreparation')\">" + statusBotao + "</button>";
                } else if (status == "inpreparation") {
                    statusAlerta = "Order is being prepared";
                    statusBotao = "Order completed";
                    botaoPedido = "<button id=\"orderAction\" onClick=\"processOrder(" + data[key].id + ",'ready')\">" + statusBotao + "</button>";
                } else {
                    statusAlerta = "Ready";
                }
                htmlTableDetails += "<tr><td><center>" + name + " x " + amount + "</center></td><td><center>" + statusAlerta + "</center></td><td><center>" + botaoPedido + "</center></td></tr>";
            }
        }

        htmlTableDetails += "  </tbody></table>";

        $("#users-contain").html(htmlTableDetails);

    });

}