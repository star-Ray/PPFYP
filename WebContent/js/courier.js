var companyId = localStorage.getItem('companyId');
if (companyId == null) {
    window.location.replace('../pages/login.html');;
} else {
    $(document).ready(function() {
    	getCouriersByCompany();
    });
}

var SENDERS = [];

function getCouriersByCompany() {
    var input = {};
    input.companyId = Number(companyId);

    var inputStr = JSON.stringify(input);
    inputStr = encodeURIComponent(inputStr);
    $.ajax({
        url: '../admin/GetCouriersByCompanyServlet?input=' + inputStr,
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
                var couriers = message;
                //console.log(JSON.stringify(couriers))
                SENDERS = couriers;
                var html = '';
                for (var o in couriers) {
                    var courier = couriers[o];

                    var courierId = courier.courierId;
                    var name = courier.name;
                    var contactNo = courier.contactNo;
                    var username = courier.username;
                    var date = courier.createDate;
                    var status = courier.objStatus;
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
					<tr id="courier-' + courierId + '">\
						<td id="courierId-' + courierId + '">' + courierId + '</td>\
						<td id="name-' + courierId + '">' + name + '</td>\
						<td id="contactNo-' + courierId + '">' + contactNo + '</td>\
						<td id="username-' + courierId + '">' + username + '</td>\
						<td id="location-' + courierId + '"></td>\
						<td>' + date.replace("T", "  ") + '</td>\
						<td id="objStatus-' + courierId + '">' + objStatus + '</td>\
						<td>\
							<button class="btn btn-sm btn-success fa fa-file-powerpoint-o" onclick="launchViewTask(' + courierId + ')" title="Tasks"></button>\
							<button class="btn btn-sm btn-warning fa fa-pencil" onclick="launchEditCourier(' + courierId + ')" title="Edit"></button>\
							<button class="btn btn-sm btn-info fa fa-eraser" onclick="launchChangeCourierPassword(' + courierId + ')" title="Change Password"></button>\
							<button class="btn btn-sm btn-danger fa fa-trash-o" onclick="launchDeleteCourier(' + courierId + ')" title="Delete"></button>\
						</td>\
					</tr>';
                }

                $("#couriers").html(html);

                $("#courierTable").DataTable({
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

function createCourier() {
    $("#message").html('');
    $(".my-loading").removeClass('sr-only');
    // console.log("print out the companyId");
    var companyId = Number(localStorage.getItem("companyId"));
    // console.log(companyId);
    var name = $("#name").val();
    var username = $("#username").val();
    var password = $("#password").val();
    var password2 = $("#password2").val();
    var contactNo = $("#contactNo").val();
    var companyId = localStorage.getItem('companyId');
    if (password == password2) {
        var input = {};
        input.companyId = Number(companyId);
        input.name = name;
        input.contactNo = contactNo;
        input.username= username;
        input.password = password;

        var inputStr = JSON.stringify(input);
        inputStr = encodeURIComponent(inputStr);

        $.ajax({
                url: '../admin/CreateCourierServlet?input=' + inputStr,
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
                        $("#courierTable").DataTable().destroy();
                        var courier = message;
                        SENDERS.push(courier);

                        var courierId = courier.courierId;
                        var name = courier.name;
                        var contactNo = courier.contactNo;
                        var username = courier.username;
                        var date = courier.createDate;
                        var companyId = courier.company.companyId;
                        var status = courier.objStatus;
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
    					<tr id="courier-' + courierId + '">\
    						<td id="courierId-' + courierId + '">' + courierId + '</td>\
    						<td id="name-' + courierId + '">' + name + '</td>\
    						<td id="contactNo-' + courierId + '">' + contactNo + '</td>\
    						<td id="username-' + courierId + '">' + username + '</td>\
    						<td>' + date.replace("T", "  ") + '</td>\
    						<td id="companyId-' + courierId + '">' + companyId + '</td>\
    						<td id="objStatus-' + courierId + '">' + objStatus + '</td>\
    						<td>\
    							<button class="btn btn-sm btn-success fa fa-file-powerpoint-o" onclick="launchViewTask(' + courierId + ')" title="Tasks"></button>\
    							<button class="btn btn-sm btn-warning fa fa-pencil" onclick="launchEditCourier(' + courierId + ')" title="Edit"></button>\
    							<button class="btn btn-sm btn-info fa fa-eraser" onclick="launchChangeCourierPassword(' + courierId + ')" title="Change Password"></button>\
    							<button class="btn btn-sm btn-danger fa fa-trash-o" onclick="launchDeleteCourier(' + courierId + ')" title="Delete"></button>\
    						</td>\
    					</tr>';

                        $("#couriers").append(html);
                        $(".my-loading").addClass('sr-only');
                        $("#createCourier").modal('hide');
                        $("#courierTable").DataTable({
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

function launchEditCourier(id) {
    var courier = {};
    for (var o in SENDERS) {
        var tempEmp = SENDERS[o];
        if (tempEmp.courierId == id) {
            courier = tempEmp;
            break;
        }
    }
    var name = courier.name;
    var contactNo = courier.contactNo;
    var username = courier.username;
    $("#editCourierForm").attr('onsubmit','editCourier(' + id + '); return false;');
    $("#nameEdit").val(name);
    $("#contactNoEdit").val(contactNo);
    $("#usernameEdit").val(username);
    $("#editCourier").modal('show');
    
}

function editCourier(id) {
    $("#messageEdit").html('');
    $(".my-loading").removeClass('sr-only');
    var input = {};

    var nameEdit = $("#nameEdit").val();
    var contactNoEdit = $("#contactNoEdit").val();
    var usernameEdit = $("#usernameEdit").val()
    
    input.courierId = Number(id);
    input.name = nameEdit;
    input.contactNo = contactNoEdit;
    input.username = usernameEdit;

    var inputStr = JSON.stringify(input);
    inputStr = encodeURIComponent(inputStr);

    $.ajax({
        url: '../admin/UpdateCourierServlet?input=' + inputStr,
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
                for (var o in SENDERS) {
                    var courier = SENDERS[o];
                    if (courier.courierId == id) {
                        SENDERS[o] = message;
                        break;
                    }
                }
                $("#editCourier").modal('hide');
            } else {
                $("#messageEdit").html(message);
            }
            $(".my-loading").addClass('sr-only');
        }
    });
}

function launchChangeCourierPassword(id) {
    $("#changeCourierPasswordForm").attr('onsubmit',
        'changeCourierPassword(' + id + '); return false;');
    $("#changeCourierPassword").modal('show');
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

function changeCourierPassword(id) {
    $("#messageChangePassword").html('');
    $(".my-loading").removeClass('sr-only');
    var input = {};
    var password = $("#passwordEdit").val();
    var password2 = $("#password2Edit").val();

    if (password != password2) {
        $("#messageChangePassword").html("Please confirm your password again!");
        $(".my-loading").addClass('sr-only');
    } else {
        input.courierId = id;
        input.password = password;

        var inputStr = JSON.stringify(input);
        inputStr = encodeURIComponent(inputStr);

        $.ajax({
            url: '../admin/ChangeCourierPasswordServlet?input=' + inputStr,
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
                    $("#changeCourierPassword").modal('hide');
                } else {
                    $("#messageChangePassword").html(message);
                }
                $(".my-loading").addClass('sr-only');
            }
        });
    }
}

function launchDeleteCourier(id) {
    $("#deleteCourierForm").attr('onsubmit',
        'deleteCourier(' + id + '); return false;');
    $("#deleteCourier").modal('show');
}

function deleteCourier(id) {
    $("#messageDeleteCourier").html('');
    $(".my-loading").removeClass('sr-only');
    var courier = {};
    for (var o in SENDERS) {
        var tempSen = SENDERS[o];
        if (tempSen.courierId == id) {
            courier = tempSen;
            break;
        }
    }
    var name = courier.name;
    var nameDelete = $("#nameDelete").val();
    var input = {};
    input.courierId = id;
    var inputStr = JSON.stringify(input);
    inputStr = encodeURIComponent(inputStr);
    if (nameDelete == name) {
        $.ajax({
            url: '../admin/DeleteCourierServlet?input=' + inputStr,
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
                    $("#courier-" + id).remove();
                    $("#deleteCourier").modal('hide');
                } else {
                    $("#messageDeleteCourier").html(message);
                }
                $(".my-loading").addClass('sr-only');
            }
        });
    } else {
        $("#messageDeleteCourier").html('The courier name mis-match!');
        $(".my-loading").addClass('sr-only');
    }
}

function launchViewTask(id) {
    window.location.href = "managedeliverytask.html#" + id;
    var inputStr = JSON.stringify(id);
    localStorage.setItem('courierId', inputStr);
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