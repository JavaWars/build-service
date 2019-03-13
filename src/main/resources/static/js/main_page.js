

function goToProducts() {

    var url="/products?";

    url+="page="+1;

    url+="&name="+$("#serchProductName").val();

    alert(url);

    window.location.href=url;

}