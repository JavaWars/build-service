
function cancelOrder(orderId) {
    // console.log("cancelOrder"+orderId);

    $.ajax({
        type : "PUT",
        url : "/api/cancel_order/"+orderId,
        headers : {
            "Content-Type" : "application/json"
        },
        success : function(data) {
            alert(data);
        },
        error : function(data) {
        }
    });
}


function sentOrder(orderId) {
    // console.log("sentOrder"+orderId);

    $.ajax({
        type : "PUT",
        url : "/api/dev_sent_order/"+orderId,
        headers : {
            "Content-Type" : "application/json"
        },
        success : function(data) {
            alert(data);
        },
        error : function(data) {
        }
    });}