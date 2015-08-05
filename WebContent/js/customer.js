var companyId = localStorage.getItem('companyId');
if (companyId == null) {
    window.location.replace('../pages/login.html');;
} else {
    $(document).ready(function() {
    	getCustomersByCompany();
    });
}

var SENDERS = [];

function getCustomersByCompany() {
    var input = {};
    input.companyId = Number(companyId);

    var inputStr = JSON.stringify(input);
    inputStr = encodeURIComponent(inputStr);
    $.ajax({
        url: '../admin/GetCustomersByCompanyServlet?input=' + inputStr,
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
                var senders = message;
                //console.log(JSON.stringify(senders))
                SENDERS = senders;
                var html = '';
                for (var o in senders) {
                    var sender = senders[o];

                    var senderId = sender.senderId;
                    var name = sender.name;
                    var contactNo = sender.contactNo;
                    var username = sender.username;
                    var date = sender.createDate;
                    var companyId = sender.company.companyId;
                    var status = sender.objStatus;
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
					<tr id="sender-' + senderId + '">\
						<td id="senderId-' + senderId + '">' + senderId + '</td>\
						<td id="name-' + senderId + '">' + name + '</td>\
						<td id="contactNo-' + senderId + '">' + contactNo + '</td>\
						<td id="username-' + senderId + '">' + username + '</td>\
						<td>' + date.replace("T", "  ") + '</td>\
						<td id="companyId-' + senderId + '">' + companyId + '</td>\
						<td id="objStatus-' + senderId + '">' + objStatus + '</td>\
						<td>\
							<button class="btn btn-sm btn-success fa fa-file-powerpoint-o" onclick="launchViewTask(' + senderId + ')" title="Tasks"></button>\
							<button class="btn btn-sm btn-warning fa fa-pencil" onclick="launchEditCustomer(' + senderId + ')" title="Edit"></button>\
							<button class="btn btn-sm btn-info fa fa-eraser" onclick="launchChangeCustomerPassword(' + senderId + ')" title="Change Password"></button>\
							<button class="btn btn-sm btn-danger fa fa-trash-o" onclick="launchDeleteCustomer(' + senderId + ')" title="Delete"></button>\
						</td>\
					</tr>';
                }

                $("#customers").html(html);

                $("#senderTable").DataTable({
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

function createCustomer() {
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
                url: '../admin/CreateSenderServlet?input=' + inputStr,
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
                        $("#senderTable").DataTable().destroy();
                        var sender = message;
                        SENDERS.push(sender);

                        var senderId = sender.senderId;
                        var name = sender.name;
                        var contactNo = sender.contactNo;
                        var username = sender.username;
                        var date = sender.createDate;
                        var companyId = sender.company.companyId;
                        var status = sender.objStatus;
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
    					<tr id="sender-' + senderId + '">\
    						<td id="senderId-' + senderId + '">' + senderId + '</td>\
    						<td id="name-' + senderId + '">' + name + '</td>\
    						<td id="contactNo-' + senderId + '">' + contactNo + '</td>\
    						<td id="username-' + senderId + '">' + username + '</td>\
    						<td>' + date.replace("T", "  ") + '</td>\
    						<td id="companyId-' + senderId + '">' + companyId + '</td>\
    						<td id="objStatus-' + senderId + '">' + objStatus + '</td>\
    						<td>\
    							<button class="btn btn-sm btn-success fa fa-file-powerpoint-o" onclick="launchViewTask(' + senderId + ')" title="Tasks"></button>\
    							<button class="btn btn-sm btn-warning fa fa-pencil" onclick="launchEditCustomer(' + senderId + ')" title="Edit"></button>\
    							<button class="btn btn-sm btn-info fa fa-eraser" onclick="launchChangeCustomerPassword(' + senderId + ')" title="Change Password"></button>\
    							<button class="btn btn-sm btn-danger fa fa-trash-o" onclick="launchDeleteCustomer(' + senderId + ')" title="Delete"></button>\
    						</td>\
    					</tr>';

                        $("#customers").append(html);
                        $(".my-loading").addClass('sr-only');
                        $("#createCustomer").modal('hide');
                        $("#senderTable").DataTable({
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

function launchEditCustomer(id) {
    var sender = {};
    for (var o in SENDERS) {
        var tempEmp = SENDERS[o];
        if (tempEmp.senderId == id) {
            sender = tempEmp;
            break;
        }
    }
    var name = sender.name;
    var contactNo = sender.contactNo;
    var username = sender.username;
    $("#editCustomerForm").attr('onsubmit','editCustomer(' + id + '); return false;');
    $("#nameEdit").val(name);
    $("#contactNoEdit").val(contactNo);
    $("#usernameEdit").val(username);
    $("#editCustomer").modal('show');
    
}

function editCustomer(id) {
    $("#messageEdit").html('');
    $(".my-loading").removeClass('sr-only');
    var input = {};

    var nameEdit = $("#nameEdit").val();
    var contactNoEdit = $("#contactNoEdit").val();
    var usernameEdit = $("#usernameEdit").val()
    
    input.senderId = Number(id);
    input.name = nameEdit;
    input.contactNo = contactNoEdit;
    input.username = usernameEdit;

    var inputStr = JSON.stringify(input);
    inputStr = encodeURIComponent(inputStr);

    $.ajax({
        url: '../admin/UpdateSenderServlet?input=' + inputStr,
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
                    var sender = SENDERS[o];
                    if (sender.senderId == id) {
                        SENDERS[o] = message;
                        break;
                    }
                }
                $("#editCustomer").modal('hide');
            } else {
                $("#messageEdit").html(message);
            }
            $(".my-loading").addClass('sr-only');
        }
    });
}

function launchChangeCustomerPassword(id) {
    $("#changeCustomerPasswordForm").attr('onsubmit',
        'changeCustomerPassword(' + id + '); return false;');
    $("#changeCustomerPassword").modal('show');
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

function changeCustomerPassword(id) {
    $("#messageChangePassword").html('');
    $(".my-loading").removeClass('sr-only');
    var input = {};
    var password = $("#passwordEdit").val();
    var password2 = $("#password2Edit").val();

    if (password != password2) {
        $("#messageChangePassword").html("Please confirm your password again!");
        $(".my-loading").addClass('sr-only');
    } else {
        input.senderId = id;
        input.password = password;

        var inputStr = JSON.stringify(input);
        inputStr = encodeURIComponent(inputStr);

        $.ajax({
            url: '/Payroll/admin/ChangeCustomerPassword?input=' + inputStr,
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
                    $("#changeCustomerPassword").modal('hide');
                } else {
                    $("#messageChangePassword").html(message);
                }
                $(".my-loading").addClass('sr-only');
            }
        });
    }
}

function launchDeleteCustomer(id) {
    $("#deleteCustomerForm").attr('onsubmit',
        'deleteCustomer(' + id + '); return false;');
    $("#deleteCustomer").modal('show');
}

function deleteCustomer(id) {
    $("#messageDeleteCustomer").html('');
    $(".my-loading").removeClass('sr-only');
    var sender = {};
    for (var o in SENDERS) {
        var tempSen = SENDERS[o];
        if (tempSen.senderId == id) {
            sender = tempSen;
            break;
        }
    }
    var name = sender.name;
    var nameDelete = $("#nameDelete").val();
    var input = {};
    input.senderId = id;
    var inputStr = JSON.stringify(input);
    inputStr = encodeURIComponent(inputStr);
    if (nameDelete == name) {
        $.ajax({
            url: '/Payroll/admin/DeleteCustomerServlet?input=' + inputStr,
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
                    $("#sender-" + id).remove();
                    $("#deleteCustomer").modal('hide');
                    CloseModalBox();
                } else {
                    $("#messageDeleteCustomer").html(message);
                }
                $(".my-loading").addClass('sr-only');
            }
        });
    } else {
        $("#messageDeleteCustomer").html('The sender name mis-match!');
        $(".my-loading").addClass('sr-only');
    }
}

function launchViewTask(id) {
    window.location.href = "managedeliverytask.html#" + id;
    var inputStr = JSON.stringify(id);
    localStorage.setItem('senderId', inputStr);
}

// upload image
function uploadImage(id) {
    var fd = new FormData(document.getElementById(id + "Form"));
    $
        .ajax({
            url: '/Payroll/system/FileUploadServlet?input={"fileType":"image,jpeg,jpg,png","uploadDirectory":"dish","folderName":"image"}',
            type: "POST",
            data: fd,
            dataType: 'json',
            processData: false,
            contentType: false,
            error: function(err) {
                console.log(err);
                alert("uploadImage: check ajax!");
            },
            success: function(data) {
                // console.log("after file has been uploaded, the next line
                // is the file");
                console.log(data);
                var status = data["status"];
                var message = data["message"];
                if (status == 0) {
                    alert(message);
                } else {
                    var fileUrl = data["fileUrl"];
                    $("#" + id).val(fileUrl);
                    $("#" + id + "Img").attr("src", fileUrl);
                }
            }
        });
}

function logOut(){
	localStorage.clear();
	window.location.href("./login.html");
}