
$(document).ready(function () {

});

function deleteFromCart(productId) {
    console.log("deleteFromCart");

    $.ajax({
        type : "DELETE",
        url : "/cart?productId="+productId,
        headers : {
            "Content-Type" : "application/json"
        },
        success : function(response) {
            $.get("/current_cart_total_price",function (data) {
                $("#tatal_price").val(data);
            });
            $("#cart_table tbody #p_"+productId+" th").remove();
        },
        error : function(data) {
            console.log(data);
        }
    });

}

function changeProductCount(productId) {
    console.log("changeProductCount"+productId);

    var newVal=$("#order_product_price_"+productId).text()*$("#order_product_number_"+productId).val();
    $("#order_product_total_price_"+productId).text(newVal)

    $.ajax({
        type : "POST",
        url : "/cart?productId="+productId+
        "&count="+$("#order_product_number_"+productId).val(),
        headers : {
            "Content-Type" : "application/json"
        },
        success : function(response) {
            $.get("/current_cart_total_price",function (data) {
                $("#tatal_price").val(data);
            });
        },
        error : function(data) {
            console.log(data);
        }
    });

}

function orderProducts() {
    console.log("orderProducts");
    $("#tatal_price").val(0);
    window.location.href="/order_and_get_check";

}