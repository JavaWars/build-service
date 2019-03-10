
function createDeveloper() {
    console.log("developer create");
    var developermodel = {
        adress: $("#address").val(),
        name: $("#name").val(),
        phone:$("#phone").val(),
        story: $("#story").val()
    };

    var requestJSON = JSON.stringify(developermodel);
    // console.log(requestJSON);
    $.ajax({
        type: "POST",
        url: "/api/developers",
        headers: {
            "Content-Type": "application/json"
        },
        data: requestJSON,
        success: function (data) {
            window.location("/developer_management");
        },
        error: function (data) {
            alert("error" + data);
        }
    });
    ;
}