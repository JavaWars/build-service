
var productProperties=new Map();

var productImages;

var productId;
$(document).ready(function () {
    productId=null;

    // console.log("new product loaded product id is"+$("#productLoadedId").val());

    if ($("#productLoadedId").val()!=""){
        productId=$("#productLoadedId").val();
        loadDataFromServerToFormForProduct();
    }
});

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

    if (productId>0){
        productmodel["id"]=productId;
    }

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
                alert("success");
                console.log("product with properties inserted");
            },
            error : function(data) {
                alert("Error "+data);
            }
        });
    }
}

// var multipleFileUploadInput = document.querySelector('#multipleFileUploadInput');
var multipleFileUploadError = document.querySelector('#multipleFileUploadError');

function uploadMultipleFiles() {

    var files = document.getElementById("multipleFileUploadInput").files;
    if (files.length === 0) {
        multipleFileUploadError.innerHTML = "Please select at least one file or insert product before create image";
        multipleFileUploadError.style.display = "block";
    }
    else {

        var formData = new FormData();
        for (var index = 0; index < files.length; index++) {
            formData.append("files", files[index]);
        }

        var myUrl = "/api/product_img/" + productId;
        var xhr = new XMLHttpRequest();
        xhr.open("POST", myUrl);

        xhr.onload = function() {
            console.log(xhr.responseText);
            var response = JSON.parse(xhr.responseText);
            if(xhr.status == 200) {
                multipleFileUploadError.style.display = "none";
                var content = "<p>All Files Uploaded Successfully</p>";

                multipleFileUploadSuccess.innerHTML = content;
            } else {
                multipleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
            }
        }
        xhr.send(formData);
    }
}

function deleteFromImgs(imgId) {
    console.log("delete from image "+imgId);

}

function loadDataFromServerToFormForProduct() {

    console.log("todo load data from server for product (updating purpose)")

    $.get("/api/product/"+productId, function(responseText) {

        console.log(responseText);
        $("#name").val(responseText['name']);
        $("#price").val(responseText['price']);
        $("#description").val(responseText['productDescription']);

        $.get("/api/properties/"+productId, function(prodProp) {
            // console.log(prodProp);
            for (const [key, value] of Object.entries(prodProp)) {
                console.log(key, value);
                productProperties.set(key,value);
            }
            loadPropertiesToTable();
        });

        //todo  load images

    });


}


