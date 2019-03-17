
function addToCart() {

    if ($("#productId").val()!=="")

        $.ajax({
            type: "POST",
            url: "/cart?count=1&productId=" + $('#productId').val(),
            headers: {
                "Content-Type": "application/json"
            },
            success: function (data) {
                alert(data);
            },
            error: function (data) {
            }
        });

}