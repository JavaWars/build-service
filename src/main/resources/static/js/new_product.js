
var productProperties=new Map();

var productImages;

var productId;

window.onload=new function () {
    productId=null;
};

function addPropToTable() {

    productProperties.set($("#newPropertyName").val(),$("#newPropertyValue").val());

    loadPropertiesToTable();
}

function loadPropertiesToTable() {
    // console.log(productProperties);
    $('#myProductProperties > tbody tr').remove();

    for (var [key, value] of productProperties) {
        $("#myProductProperties > tbody:last-child").append('<tr>' +
            '<th scope="col"></th>' +
            '<th scope="col">' + key + '</th>' +
            '<th scope="col">' + value + '</th>' +
            '<th scope="col"><button id="_btn_del_'+key+'">Delete</button></th>' +
            '</tr>');

        var id="#_btn_del_"+key;
        $(id).click(function(){
            deleteProperty(key);
        });
    }
}

function deleteProperty(PropName) {
    // console.log("delete property"+PropName+"from "+productProperties);

    productProperties.delete(PropName);

    loadPropertiesToTable();
}

function createProduct() {
    console.log("create product");

    var productmodel = {
        name: $("#name").val(),
        price: $("#price").val(),
        productDescription:$("#description").val(),
    };

    var requestJSON = JSON.stringify(productmodel);

    console.log(requestJSON);

    $.ajax({
        type : "POST",
        url : "/api/products",
        headers : {
            "Content-Type" : "application/json"
        },
        data : requestJSON,
        success : function(data) {
            console.log(data);
            productId=data;
            insertProperties();
        },
        error : function(data) {
        }
    });
}

function insertProperties() {
    console.log("inserting properties");
    if (productId>0 && productId!=null){

        var prop = {};
        for(var [key, value] of productProperties) {
            prop[key.toString()] = value;
        }
        var requestPropJSON = JSON.stringify(prop);
        console.log(requestPropJSON);
        $.ajax({
            type : "POST",
            url : "/api/properties/"+productId,
            headers : {
                "Content-Type" : "application/json"
            },
            data : requestPropJSON,
            success : function(data) {
                console.log("product with properties inserted");
            },
            error : function(data) {
            }
        });
    }
}

function deleteFromImgs() {
    console.log("delete from image");

}

function submitAllFiles() {
    console.log("submitAllFiles");

}

function addImageToTable() {
    console.log("addImageToTable");

}