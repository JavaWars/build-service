
function editProduct(productId) {
    // console.log("edit product"+productId);

    window.location.href="/product_config?productIdForConfiguration="+productId;
}

function deleteProduct(productId) {
    console.log("delete product"+productId);

    $.ajax({
        type : "DELETE",
        url : "/api/products/"+productId,
        headers : {
            "Content-Type" : "application/json"
        },
        success : function(data) {

        },
        error : function(data) {
            // alert(data);
        }
    });
    loadProductForDeveloper();
}

function loadProductForDeveloper() {
    var developerId;
    jQuery.ajax({
        url: '/api/developer/',
        success: function (result) {
            developerId=result;
        },
        async: false
    });

    jQuery.ajax({
        url: '/api/products/'+developerId,
        success: function (result) {
            setProducts(result);
        },
        async: false
    });


}

function setProducts(response) {

    $("#ourProducts tbody tr").remove();//clear table

    for (var i = 0; i < response.length; i++) {

        $('#ourProducts > tbody:last-child').append('<tr class="" >' +
            '<th>' + response[i].id + '</th>' +
            '<th>' + response[i].name + '</th>' +
            '<th><img width="100" height="100" src="/api/product_root_img/'+ response[i].id +'"/></th>' +
            '<th><a href="/product/' + response[i].id + '">On Product Page</a></th>' +
            '<th><button class="button btn-warning" onclick="editProduct(' + response[i].id + ')">Edit</button></th>' +
            '<th><button class="button btn-danger" onclick="deleteProduct(' + response[i].id + ')">Delete</button></th>' +
            '</tr>')

    }
}