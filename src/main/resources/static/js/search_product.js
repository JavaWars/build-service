var page;

var searchDeveloper=null;

$(document).ready(function () {

    if ($("#developerId").val()!=="") {
        searchDeveloper = $("#developerId").val();

        $("#placeForDeveloperAlert").append('<div class="alert alert-primary navbar fixed-top" role="alert"  >\n' +
            '    <div>Products for developer </div><span th:text="${developer.name}"></span> displayed <button onclick="searchClearDeveloper()">Show all</button>\n' +
            '</div>');
    }

    if ($("#search_page").val()>0)
        page=$("#search_page").val();
    else{page=1;}

    $("#pageID").text(page);
});

function searchProduct() {

    console.log("searchProduct");

    var url="/products?";

    url+="page="+page;

    if ($("#search_by_name").val()!==""){url+="&name="+$("#search_by_name").val();}

    if ($("#search_min_price").val()!==""){url+="&minPrice="+$("#search_min_price").val();}

    if ($("#search_max_price").val()!==""){url+="&maxPrice="+$("#search_max_price").val();}

    if(searchDeveloper!==null){url+="&developer="+searchDeveloper;}

    window.location.href=url;
}

function searchClearDeveloper() {

    console.log("searchClearDeveloper");

    searchDeveloper=null;
    $("#developerId").val("");
    $("#placeForDeveloperAlert").remove();

    searchProduct();
}

function incPage() {
    if (page>0){
        page++;
        searchProduct();
    }
}

function decPage() {
    if (page>0) {
        page--;
        searchProduct();
    }
}