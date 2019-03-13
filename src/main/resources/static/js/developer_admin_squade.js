

function loadMyAdmin() {
    console.log("loaded my admins");
    var adr="/api/developers_admin";

    $.get(adr, function(responseText) {
        // console.log(responseText);

        $("#myAdmins tbody tr").remove();//clear table

        for (var i=0;i<responseText.length;i++){
            // console.log(responseText[i]);

            if (responseText[i].role==="ADMIN") {
                $('#myAdmins > tbody:last-child').append('<tr class="bg-primary">' +
                    '<th scope="row">' + responseText[i].id + '</th>' +
                    '<th scope="row">' + responseText[i].email + '</th>' +
                    '<th scope="row">' + responseText[i].phone + '</th>' +
                    '<th scope="row">' + responseText[i].role + '</th>' +
                    '<th scope="row">' + responseText[i].name + '</th>' +
                    '<th scope="row">' + responseText[i].secondName + '</th>' +
                    '<th scope="row">' + '<button onclick="deleteFromMyAdminList('+responseText[i].id+')">Delete</button>' + '</th>' +
                    '</tr>');
            }
        }
    });

}

function deleteFromMyAdminList(userId) {
    // console.log("Owner want delete admin"+userId);

    $.ajax({
        type : "DELETE",
        url : "/api/developers_admin/"+userId,
        headers : {
            "Content-Type" : "application/json"
        },
        success : function(data) {
            loadMyAdmin();
        },
        error : function(data) {
            alert(data);
        }
    });

    loadMyAdmin();
}

function hireFromMyAdminList(newAdminId) {
    // console.log("Owner want hire new admin");

    $.ajax({
        type : "POST",
        url : "/api/developers_admin/"+newAdminId,
        headers : {
            "Content-Type" : "application/json"
        },
        success : function(data) {
            loadMyAdmin();
            search_admin_by_example();
        },
        error : function(data) {
            alert(data);
        }
    });

}

function search_admin_by_example() {
    // console.log("owner want search admin");

    var adr="/api/user-searcher"+
        "?email="+$("#search_email").val()
        +"&phone="+$("#search_phone").val()
        +"&name="+$("#search_name").val();

    // console.log(adr);

    $.get(adr, function(responseText) {
        // console.log(responseText);

        $("#admins_by_filter tbody tr").remove();//clear table

        for (var i=0;i<responseText.length;i++){
            // console.log(responseText[i]);

            if (responseText[i].role==="USER") {
                $('#admins_by_filter > tbody:last-child').append('<tr class="bg-primary">' +
                    '<th scope="row">' + responseText[i].id + '</th>' +
                    '<th scope="row">' + responseText[i].email + '</th>' +
                    '<th scope="row">' + responseText[i].phone + '</th>' +
                    '<th scope="row">' + responseText[i].role + '</th>' +
                    '<th scope="row">' + responseText[i].name + '</th>' +
                    '<th scope="row">' + responseText[i].secondName + '</th>' +
                    '<th scope="row">' + '<button onclick="hireFromMyAdminList('+responseText[i].id+')">Hire</button>' + '</th>' +
                    '</tr>');
            }
            else{
                $('#admins_by_filter > tbody:last-child').append('<tr class="bg-danger">' +
                    '<th scope="row">xxx</th>' +
                    '<th scope="row">' + responseText[i].email + '</th>' +
                    '<th scope="row">' + responseText[i].phone + '</th>' +
                    '<th scope="row">' + responseText[i].role + '</th>' +
                    '<th scope="row">hided</th>' +
                    '<th scope="row">hided</th>' +
                    '<th scope="row">hided</th>' +
                    '</tr>');
            }
        }
    });
}

