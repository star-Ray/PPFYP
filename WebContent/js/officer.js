var companyId = localStorage.getItem('companyId');
if (companyId == null) {
    window.location.replace('../pages/login.html');;
} else {
    $(document).ready(function() {
    	getOfficersByCompany();
    });
}

var OFFICERS = [];

function getOfficersByCompany() {
    var input = {};
    input.companyId = Number(companyId);

    var inputStr = JSON.stringify(input);
    //console.log(inputStr);
    inputStr = encodeURIComponent(inputStr);
    $.ajax({
        url: '../admin/GetOfficersByCompanyServlet?input=' + inputStr,
        method: 'GET',
        dataType: 'json',
        error: function(err) {
            console.log(err);
        },
        success: function(data) {
            console.log(data);
            var status = data.status;
            var message = data.message;
            if (status == 1) {
                var officers = message;
                //console.log(JSON.stringify(officers))
                OFFICERS = officers;
                var html = '';
                for (var o in officers) {
                    var officer = officers[o];

                    var officerId = officer.officerId;
                    var name = officer.name;
                    var username = officer.username;
                    var date = officer.createDate;
                    var status = officer.objStatus;
                    var objStatus = '';
                    switch (status) {
                        case 0:
                            objStatus = "Non-Active";
                            break;
                        case 1:
                            objStatus = "Active";
                            break;
                        default:
                            objStatus = "Undefined";
                            break;
                    }

                    html += '\
					<tr id="officer-' + officerId + '">\
						<td id="officerId-' + officerId + '">' + officerId + '</td>\
						<td id="name-' + officerId + '">' + name + '</td>\
						<td id="username-' + officerId + '">' + username + '</td>\
						<td>' + date.replace("T", "  ") + '</td>\
						<td id="objStatus-' + officerId + '">' + objStatus + '</td>\
						<td>\
							<button class="btn btn-sm btn-success fa fa-file-powerpoint-o" onclick="launchViewTask(' + officerId + ')" title="Tasks"></button>\
							<button class="btn btn-sm btn-warning fa fa-pencil" onclick="launchEditOfficer(' + officerId + ')" title="Edit"></button>\
							<button class="btn btn-sm btn-info fa fa-eraser" onclick="launchChangeOfficerPassword(' + officerId + ')" title="Change Password"></button>\
							<button class="btn btn-sm btn-danger fa fa-trash-o" onclick="launchDeleteOfficer(' + officerId + ')" title="Delete"></button>\
						</td>\
					</tr>';
                }

                $("#officers").html(html);

                $("#officerTable").DataTable({
                    destroy: true,
                    searching: true,
                    responsive: true
                });
            } else {
                console.log(message);
            }
        }
    });
}

function createOfficer() {
    $("#message").html('');
    $(".my-loading").removeClass('sr-only');
    // console.log("print out the companyId");
    var companyId = Number(localStorage.getItem("companyId"));
    // console.log(companyId);
    var name = $("#name").val();
    var username = $("#username").val();
    var password = $("#password").val();
    var password2 = $("#password2").val();
    var companyId = localStorage.getItem('companyId');
    if (password == password2) {
        var input = {};
        input.companyId = Number(companyId);
        input.name = name;
        input.username= username;
        input.password = password;

        var inputStr = JSON.stringify(input);
        inputStr = encodeURIComponent(inputStr);

        $.ajax({
                url: '../admin/CreateOfficerServlet?input=' + inputStr,
                method: 'POST',
                dataType: 'json',
                error: function(err) {
                    console.log(err);
                    $(".my-loading").addClass('sr-only');
                },
                success: function(data) {
                    console.log(data);
                    var status = data.status;
                    var message = data.message;
                    if (status == 1) {
                        $("#officerTable").DataTable().destroy();
                        var officer = message;
                        OFFICERS.push(officer);

                        var officerId = officer.officerId;
                        var name = officer.name;
                        var username = officer.username;
                        var date = officer.createDate;
                        //var companyId = officer.company.companyId;
                        var status = officer.objStatus;
                        var objStatus = '';
                        switch (status) {
                            case 0:
                                objStatus = "Non-Active";
                                break;
                            case 1:
                                objStatus = "Active";
                                break;
                            default:
                                objStatus = "Undefined";
                                break;
                        }

                        var html = '\
    					<tr id="officer-' + officerId + '">\
    						<td id="officerId-' + officerId + '">' + officerId + '</td>\
    						<td id="name-' + officerId + '">' + name + '</td>\
    						<td id="username-' + officerId + '">' + username + '</td>\
    						<td>' + date.replace("T", "  ") + '</td>\
    						<td id="objStatus-' + officerId + '">' + objStatus + '</td>\
    						<td>\
    							<button class="btn btn-sm btn-success fa fa-file-powerpoint-o" onclick="launchViewTask(' + officerId + ')" title="Tasks"></button>\
    							<button class="btn btn-sm btn-warning fa fa-pencil" onclick="launchEditOfficer(' + officerId + ')" title="Edit"></button>\
    							<button class="btn btn-sm btn-info fa fa-eraser" onclick="launchChangeOfficerPassword(' + officerId + ')" title="Change Password"></button>\
    							<button class="btn btn-sm btn-danger fa fa-trash-o" onclick="launchDeleteOfficer(' + officerId + ')" title="Delete"></button>\
    						</td>\
    					</tr>';

                        $("#officers").append(html);
                        $(".my-loading").addClass('sr-only');
                        $("#createOfficer").modal('hide');
                        $("#officerTable").DataTable({
                            responsive: true
                        });
                    } else {
                        console.log(message);
                        $("#message").html(message);
                        $(".my-loading").addClass('sr-only');
                    }
                }
            });
    } else {
        $("#message").html("Please confirm your password again!");
        $(".my-loading").addClass('sr-only');
    }
}

var to;

function passwordCheck(ms) {
    clearTimeout(to);
    to = setTimeout(function() {
        var password = document.getElementById("password").value;
        var password2 = document.getElementById("password2").value;
        if (password != password2) {
            $("#message").html("Please confirm your password again!");
        } else {
            $("#message").html("");
        }
    }, ms);
}

function launchEditOfficer(id) {
    var officer = {};
    for (var o in OFFICERS) {
        var tempEmp = OFFICERS[o];
        if (tempEmp.officerId == id) {
            officer = tempEmp;
            break;
        }
    }
    var name = officer.name;
    var contactNo = officer.contactNo;
    var username = officer.username;
    $("#editOfficerForm").attr('onsubmit','editOfficer(' + id + '); return false;');
    $("#nameEdit").val(name);
    $("#contactNoEdit").val(contactNo);
    $("#usernameEdit").val(username);
    $("#editOfficer").modal('show');
    
}

function editOfficer(id) {
    $("#messageEdit").html('');
    $(".my-loading").removeClass('sr-only');
    var input = {};

    var nameEdit = $("#nameEdit").val();
    var contactNoEdit = $("#contactNoEdit").val();
    var usernameEdit = $("#usernameEdit").val()
    
    input.officerId = Number(id);
    input.name = nameEdit;
    input.contactNo = contactNoEdit;
    input.username = usernameEdit;

    var inputStr = JSON.stringify(input);
    inputStr = encodeURIComponent(inputStr);

    $.ajax({
        url: '../admin/UpdateOfficerServlet?input=' + inputStr,
        method: 'GET',
        dataType: 'json',
        error: function(err) {
            console.log(err);
            $(".my-loading").addClass('sr-only');
        },
        success: function(data) {
            var status = data.status;
            var message = data.message;
            if (status == 1) {
                var tempCus = message;

                var name = tempCus.name;
                var username = tempCus.username;
                var contactNo = tempCus.contactNo;
                
                $("#name-" + id).html(name);
                $("#username-" + id).html(username);
                $("#contactNo-" + id).html(contactNo);
                for (var o in OFFICERS) {
                    var officer = OFFICERS[o];
                    if (officer.officerId == id) {
                        OFFICERS[o] = message;
                        break;
                    }
                }
                $("#editOfficer").modal('hide');
            } else {
                $("#messageEdit").html(message);
            }
            $(".my-loading").addClass('sr-only');
        }
    });
}

function launchChangeOfficerPassword(id) {
    $("#changeOfficerPasswordForm").attr('onsubmit',
        'changeOfficerPassword(' + id + '); return false;');
    $("#changeOfficerPassword").modal('show');
}

function passwordEditCheck(ms) {
    clearTimeout(to);
    to = setTimeout(function() {
        var password = document.getElementById("passwordEdit").value;
        var password2 = document.getElementById("password2Edit").value;
        if (password != password2) {
            $("#messageChangePassword").html(
                "Please confirm your password again!");
        } else {
            $("#messageChangePassword").html("");
        }
    }, ms);
}

function changeOfficerPassword(id) {
    $("#messageChangePassword").html('');
    $(".my-loading").removeClass('sr-only');
    var input = {};
    var password = $("#passwordEdit").val();
    var password2 = $("#password2Edit").val();

    if (password != password2) {
        $("#messageChangePassword").html("Please confirm your password again!");
        $(".my-loading").addClass('sr-only');
    } else {
        input.officerId = id;
        input.password = password;

        var inputStr = JSON.stringify(input);
        inputStr = encodeURIComponent(inputStr);

        $.ajax({
            url: '../admin/ChangeOfficerPasswordServlet?input=' + inputStr,
            method: 'GET',
            async: false,
            dataType: 'json',
            error: function(err) {
                console.log(err);
                $(".my-loading").addClass('sr-only');
            },
            success: function(data) {
                var status = data.status;
                var message = data.message;
                if (status == 1) {
                    $("#changeOfficerPassword").modal('hide');
                } else {
                    $("#messageChangePassword").html(message);
                }
                $(".my-loading").addClass('sr-only');
            }
        });
    }
}

function launchDeleteOfficer(id) {
    $("#deleteOfficerForm").attr('onsubmit',
        'deleteOfficer(' + id + '); return false;');
    $("#deleteOfficer").modal('show');
}

function deleteOfficer(id) {
    $("#messageDeleteOfficer").html('');
    $(".my-loading").removeClass('sr-only');
    var officer = {};
    for (var o in OFFICERS) {
        var tempSen = OFFICERS[o];
        if (tempSen.officerId == id) {
            officer = tempSen;
            break;
        }
    }
    var name = officer.name;
    var nameDelete = $("#nameDelete").val();
    var input = {};
    input.officerId = id;
    var inputStr = JSON.stringify(input);
    inputStr = encodeURIComponent(inputStr);
    if (nameDelete == name) {
        $.ajax({
            url: '../admin/DeleteOfficerServlet?input=' + inputStr,
            method: 'GET',
            async: false,
            dataType: 'json',
            error: function(err) {
                console.log(err);
                $(".my-loading").addClass('sr-only');
            },
            success: function(data) {
                var status = data.status;
                var message = data.message;
                if (status == 1) {
                    var tempCus = message;
                    var objStatus = tempCus.objStatus;
                    $("#officer-" + id).remove();
                    $("#deleteOfficer").modal('hide');
                } else {
                    $("#messageDeleteOfficer").html(message);
                }
                $(".my-loading").addClass('sr-only');
            }
        });
    } else {
        $("#messageDeleteOfficer").html('The officer name mis-match!');
        $(".my-loading").addClass('sr-only');
    }
}

function launchViewTask(id) {
    window.location.href = "managedeliverytask.html#" + id;
    var inputStr = JSON.stringify(id);
    localStorage.setItem('officerId', inputStr);
}

// upload image
//function uploadImage(id) {
//    var fd = new FormData(document.getElementById(id + "Form"));
//    $
//        .ajax({
//            url: '/Payroll/system/FileUploadServlet?input={"fileType":"image,jpeg,jpg,png","uploadDirectory":"dish","folderName":"image"}',
//            type: "POST",
//            data: fd,
//            dataType: 'json',
//            processData: false,
//            contentType: false,
//            error: function(err) {
//                console.log(err);
//                alert("uploadImage: check ajax!");
//            },
//            success: function(data) {
//                // console.log("after file has been uploaded, the next line
//                // is the file");
//                console.log(data);
//                var status = data["status"];
//                var message = data["message"];
//                if (status == 0) {
//                    alert(message);
//                } else {
//                    var fileUrl = data["fileUrl"];
//                    $("#" + id).val(fileUrl);
//                    $("#" + id + "Img").attr("src", fileUrl);
//                }
//            }
//        });
//}

function logOut(){
	localStorage.clear();
	window.location.href("./login.html");
}