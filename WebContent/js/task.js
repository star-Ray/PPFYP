var companyId = localStorage.getItem('companyId');
if(companyId==null){
	window.location.replace('/Payroll/login.html');;
}else{
	$(document).ready(function(){
		getTasksByCompany();
	});
}

var EMPLOYEES = [];

function getTasksByCompany(){
	var input = {};
	input.companyId = companyId;
	
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	console.log("entered the get task function");
	$.ajax({
		url: '/Payroll/admin/GetTasksByCompanyServlet?input='+inputStr,
		method: 'GET',
		dataType: 'json',
		error: function(err){
			console.log(err);
			console.log("some error");
		},
		success: function(data){
			console.log(data);
			var status = data.status;
			var message = data.message;
			if(status == 1){
				var tasks = message;
				EMPLOYEES = tasks;
				var html = '';
				for(var o in tasks){
					var tmpTask = tasks[o];
					
					var taskId = tmpTask.taskId;
					var avata = tmpTask.avata;
					var email = tmpTask.email;
					var name = tmpTask.name;
					var workingType = tmpTask.workingType;
					var passType = tmpTask.passType;
					var passNo = tmpTask.passNo;
					var frequencyId = tmpTask.frequency.frequencyId;
					var date = tmpTask.createDate;
					var wType = '';
					var pType = '';
					switch (passType) {
						case 0: pType = "Local";
	                    	break;
						case 1: pType = "PR";
	                    	break;
						case 2: pType = "EP";
	                    	break;
						case 3: pType = "SP";
	                    	break;
						case 4: pType = "WP";
	                    	break;
			            default: passType = "Invalid pass";
                     		break;
					}
					if(workingType == 0){
						wType = "Full-Time";
					}else{
						wType = "Part-Time";
					}
					html += '\
						<tr id="task-'+taskId+'">\
							<td id="taskId-'+taskId+'">'+taskId+'</td>\
							<td><a id="avataA-'+taskId+'" href="'+avata+'" target="_blank">\
								<img id="avata-'+taskId+'" src="'+avata+'" class="img-responsive img-rounded my-img-xs" alt="avata">\
							</a></td>\
							<td id="email-'+taskId+'">'+email+'</td>\
							<td id="name-'+taskId+'">'+name+'</td>\
							<td id="workingType-'+taskId+'">'+pType+'</td>\
							<td id="passType-'+taskId+'">'+wType+'</td>\
							<td id="passNo-'+taskId+'">'+passNo+'</td>\
							<td id="frequencyId-'+taskId+'">'+frequencyId+'</td>\
							<td>'+date.replace("T","  ")+'</td>\
							<td id="objStatus-'+taskId+'">'+task.objStatus+'</td>\
							<td>\
								<button class="btn btn-sm btn-warning fa fa-pencil" onclick="launchEditTask('+taskId+')" title="Edit"></button>\
								<button class="btn btn-sm btn-info fa fa-eraser fa-1" onclick="launchChangeTaskPassword('+taskId+')" title="Change Password"></button>\
								<button class="btn btn-sm btn-danger fa fa-trash-o fa-lg" onclick="launchDeleteTask('+taskId+')" title="Delete"></button>\
								<button class="btn btn-sm btn-success fa fa-file-powerpoint-o" onclick="launchTaskPaySlip('+taskId+')" title="Payslips"></button>\
							</td>\
						</tr>';
				
				}
				
				$("#tasks").html(html);
				
				$("#taskTable").DataTable({
				    destroy: true,
				    searching: false,
					responsive: true
				});
			}else{
				console.log(message);
			}
		}
	});
}

function createTask(){
	$("#message").html('');
	$(".my-loading").removeClass('sr-only');
	var companyId = app.companyId;
	var name = $("#name").val();
	var email = $("#email").val();
	var avata = $("#avata").val();
	var password = $("#password").val();
	var password2 = $("#password2").val();
	var workingType = $("#workingType").val();
	var passType = $("#passType").val();
	var payslipId = $('#payslipId').val();
	if(workingType <0 || workingType >1){
		$("#message").html("Working type cannot be less than 0 and more than 1");
	}else if ( passType <0 || passType >4){
		$("#message").html("Pass type cannot be less than 0 and more than 4");
	} else {
		var passNo = $("#passNo").val();
		var passLength = passNo.length;
		var letters = /^[A-Za-z]+$/; 
		var checkFirstAlpa = passNo.charAt(0);
		var checkLastAlpa = passNo.charAt(8);
		var checkNumbers = Number((passNo).substring(1,8));	
		if(passLength != 9 || !(checkFirstAlpa.match(letters))|| !(checkLastAlpa.match(letters))){
			console.log("enter");
			$("#message").html("Please enter a valid IC");
		}else{
			var frequencyId = $("#frequencyId").val();
			if(password == password2){
				var input = {};
				input.companyId = Number(companyId);
				input.name = name;
				input.email = email;
				input.avata = avata;
				input.password = password;
				input.workingType = Number(workingType);
				input.passType = Number(passType);
				input.passNo = passNo;
				input.frequencyId = Number(frequencyId);
				input.payslipId = Number(payslipId);
				
				var inputStr = JSON.stringify(input);
				inputStr = encodeURIComponent(inputStr);
				
				$.ajax({
					url: '/Payroll/admin/CreateTaskServlet?input='+inputStr,
					method: 'GET',
					dataType: 'json',
					error: function(err){
						console.log(err);
						$(".my-loading").addClass('sr-only');
					},
					success: function(data){
						console.log(data);
						var status = data.status;
						var message = data.message;
						if(status == 1){
							$("#taskTable").DataTable().destroy();
							var task = message;
							
							var taskId = task.taskId;
							var companyId = task.company.companyId;
							var avata = task.avata;
							var email = task.email;
							var name = task.name;
							var workingType = task.workingType;
							var passType = task.passType;
							var passNo = task.passNo;
							var frequencyId = task.frequency.frequencyId;
							var date = task.createDate;
							EMPLOYEES.push(task);
							var taskId = task.taskId;
							var date = task.createDate;
							
							var wType = '';
							var pType = '';
							var pType = '';
							switch (passType) {
								case 0: pType = "Local";
			                    	break;
								case 1: pType = "PR";
			                    	break;
								case 2: pType = "EP";
			                    	break;
								case 3: pType = "SP";
			                    	break;
								case 4: pType = "WP";
			                    	break;
					            default: passType = "Invalid pass";
		                     		break;
							}
						if(workingType == 0){
							wType = "Full-Time";
						}else{
							wType = "Part-Time";
						}
							var html = '\
								<tr id="task-'+taskId+'">\
									<td id="taskId-'+taskId+'">'+taskId+'</td>\
									<td><a id="lalalA-'+taskId+'" href="'+avata+'" target="_blank"><img id="avata-'+taskId+'" src="'+avata+'" class="img-responsive img-rounded my-img-xs" alt="avata"></a></td>\
									<td id="email-'+taskId+'">'+email+'</td>\
									<td id="name-'+taskId+'">'+name+'</td>\
									<td id="workingType-'+taskId+'">'+pType+'</td>\
									<td id="passType-'+taskId+'">'+wType+'</td>\
									<td id="passNo-'+taskId+'">'+passNo+'</td>\
									<td id="frequencyId-'+taskId+'">'+frequencyId+'</td>\
									<td>'+date.replace("T","  ")+'</td>\
									<td>\
										<button class="btn btn-sm btn-warning fa fa-pencil" onclick="launchEditTask('+taskId+')" title="Edit"></button>\
										<button class="btn btn-sm btn-info fa fa-eraser fa-1" onclick="launchChangeTaskPassword('+taskId+')" title="Change Password"></button>\
										<button class="btn btn-sm btn-danger fa fa-trash-o fa-lg" onclick="launchDeleteTask('+taskId+')" title="Delete"></button>\
										<button class="btn btn-sm btn-success fa fa-file-powerpoint-o" onclick="launchTaskPaySlip('+taskId+')" title="Payslips"></button>\
								</td>\
								</tr>';
							$("#tasks").append(html);
							$(".my-loading").addClass('sr-only');
							$("#createTask").modal('hide');
							CloseModalBox();
							$("#taskTable").DataTable({
								responsive: true
							});
						}else{
							console.log(message);
							$("#message").html(message);
							$(".my-loading").addClass('sr-only');
						}
					}
				});
			}else{
				$("#message").html("Please confirm your password again!");
				$(".my-loading").addClass('sr-only');
			}
		}
	}
}

function launchEditTask(id){
	var form = $('<form id="editTaskForm" class="form-horizontal">'+
					'<div class="form-group">'+
						'<label for="nameEdit" class="col-sm-2 control-label">Name</label>'+
						'<div class="col-sm-10">'+
							'<input type="text" class="form-control" id="nameEdit" placeholder="Task Name" required>'+
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<label for="emailEdit" class="col-sm-2 control-label">Email</label>'+
						'<div class="col-sm-10">'+
							'<input type="email" class="form-control" id="emailEdit" placeholder="Email" required>'+
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<label for="avataEdit" class="col-sm-2 control-label">Avata</label>'+
						'<div class="col-sm-2">'+
							'<label for="avataEditUpload">'+
							'<img id="avataEditImg" class="img-responsive img-rounded clickable" alt="avata" src="/Payroll/asset/img/default.jpg">'+
							'</label>'+
							'<div class="text-center"><small>Size: 300px x 320px</small></div>'+
							'<input type="text" id="avataEdit" value="/Payroll/asset/img/default.jpg" class="form-control sr-only">'+
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<label for="workingTypeEdit" class="col-sm-2 control-label">Working Type</label>'+
						'<div class="col-sm-10">'+
							'<input type="number" min="0" max="1"class="form-control" id="workingTypeEdit" value=0 placeholder="0 - full time; 1 - part time;" required>'+
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<label for="passTypeEdit" class="col-sm-2 control-label">Pass Type</label>'+
						'<div class="col-sm-10">'+
							'<input type="number" min="0" max="4" class="form-control" id="passTypeEdit" value=0 placeholder="0 - local; 1 - PR; 2 - EP; 3 - SP; 4 - WP;" required>'+
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<label for="passNoEdit" class="col-sm-2 control-label">Pass No</label>'+
						'<div class="col-sm-10">'+
							'<input type="text" class="form-control" id="passNoEdit" placeholder="e.g. G0712805W" required>'+
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<label for="frequencyIdEdit" class="col-sm-2 control-label">FrequencyId</label>'+
						'<div class="col-sm-10">'+
							'<input type="number" min="0" class="form-control" id="frequencyIdEdit" placeholder="Frequency ID" required>'+
						'</div>'+
					'</div>'+
				'</form>');
	var buttons = $('<div class="form-group">'+
						'<div class="col-sm-offset-2">'+
							'<button type="submit" id="updateEmp" class="btn btn-xs btn-warning">Save</button>'+
							'<font color="red" id="messageEdit"></font>'+
							'<img src="/Payroll/asset/img/ajax-loader.gif" class="my-loading sr-only">'+
							'<button type="button" class="btn btn-xs btn-default pull-right" data-dismiss="modal">Close</button>'+
						'</div>'+
					'</div>');
	
	OpenModalBox('Update Task', form, buttons);
	$('#close').on('click', function(){
		CloseModalBox();
	});
	$('#updateEmp').on('click', function(){
		editTask(id);

	});
	
	var task = {};
	
	for(var o in EMPLOYEES){
		var tempEmp = EMPLOYEES[o];
		
		if(tempEmp.taskId == id){
			task = tempEmp;
			break;
		}
	}
	
	var taskId = task.taskId;
	var companyId = task.company.companyId;
	var avata = task.avata;
	var email = task.email;
	var name = task.name;
	var workingType = task.workingType;
	var passType = task.passType;
	var passNo = task.passNo;
	var frequencyId = task.frequency.frequencyId;
	
	$("#editTaskForm").attr('onsubmit', 'editTask('+id+'); return false;');
	$("#companyIdEdit").val(companyId);
	$("#emailEdit").val(email);
	$("#nameEdit").val(name);
	$("#workingTypeEdit").val(workingType);
	$("#passTypeEdit").val(passType);
	$("#nameEdit").val(name);
	$("#passNoEdit").val(passNo);
	$("#avataEditImg").attr('src', avata);
	$("#avataEdit").val(avata);
	$("#frequencyIdEdit").val(frequencyId);
	
	$("#editTask").modal('show');
}

function launchChangeTaskPassword(id){
	var form =$('<form class="form-horizontal" id="changeTaskPasswordForm" onsubmit="changeTaskPassword(0); return false;">'+
					'<div class="form-group">'+
							'<label for="passwordEdit" class="col-sm-2 control-label">Password</label>'+
							'<div class="col-sm-10">'+
								'<input type="password" class="form-control" id="passwordEdit" placeholder="Password" required>'+
							'</div>'+
						'</div>'+
						'<div class="form-group">'+
							'<label for="password2Edit" class="col-sm-2 control-label">Confirm Password</label>'+
							'<div class="col-sm-10">'+
								'<input type="password" class="form-control" id="password2Edit" placeholder="Confirm Password" required>'+
							'</div>'+
						'</div>'+
						'<div class="form-group">'+
							'<div class="col-sm-offset-2 col-sm-8 col-xs-10">'+
								'<font color="red" id="messageChangePassword"></font>'+
								'<img src="/Payroll/asset/img/ajax-loader.gif" class="my-loading sr-only">'+
							'</div>'+
						'</div>'+
					'</form>');
	var buttons = 	$('<div class="form-group">'+
			'<div class="col-sm-offset-2">'+
			'<button type="submit" id="changePass" class="btn btn-xs btn-danger">Change</button>'+
			'<font color="red" id="messageEdit"></font>'+
			'<img src="/Payroll/asset/img/ajax-loader.gif" class="my-loading sr-only">'+
			'<button type="button" id="close" class="btn btn-xs btn-default pull-right" data-dismiss="modal">Close</button>'+
		'</div>'+
	'</div>');

	OpenModalBox('Change Password', form, buttons);
	$('#close').on('click', function(){
		CloseModalBox();
	});
	$('#changePass').on('click', function(){
		changeTaskPassword(id);
	});
	
	$("#changeTaskPasswordForm").attr('onsubmit', 'changeTaskPassword('+id+'); return false;');
	$("#changeTaskPassword").modal('show');
}

function launchDeleteTask(id){
	var form = $('<form class="form-horizontal" id="deleteTaskForm" onsubmit="deleteTask(0); return false;">'+
						'<div class="form-group">'+
							'<label for="nameDelete" class="col-sm-2 control-label">Name</label>'+
							'<div class="col-sm-10">'+
								'<input type="text" class="form-control" id="nameDelete" placeholder="Please key in the task name to confirm delete!" required>'+
							'</div>'+
						'</div>'+
						'<div class="form-group">'+
							'<div class="col-sm-offset-2 col-sm-8 col-xs-10">'+
								'<font color="red" id="messageDeleteTask"></font>'+
								'<img src="/Payroll/asset/img/ajax-loader.gif" class="my-loading sr-only">'+
							'</div>'+
						'</div>'+
					'</form>');
	var buttons = 	$('<div class="form-group">'+
						'<div class="col-sm-offset-2">'+
							'<button type="submit" id="deleteEmp" class="btn btn-xs btn-danger">Delete</button>'+
							'<font color="red" id="messageEdit"></font>'+
							'<img src="/Payroll/asset/img/ajax-loader.gif" class="my-loading sr-only">'+
							'<button type="button" id="close" class="btn btn-xs btn-default pull-right" data-dismiss="modal">Close</button>'+
						'</div>'+
					'</div>');

	OpenModalBox('Delete Task', form, buttons);
	$('#close').on('click', function(){
		CloseModalBox();
	});
	$('#deleteEmp').on('click', function(){
		deleteTask(id);
	});
	
	$("#deleteTaskForm").attr('onsubmit', 'deleteTask('+id+'); return false;');
	$("#deleteTask").modal('show');
}

function editTask(id){
	$("#messageEdit").html('');
	$(".my-loading").removeClass('sr-only');
	var input = {};
	var companyIdEdit = app.companyId;
	var emailEdit = $("#emailEdit").val();
	var nameEdit = $("#nameEdit").val();
	var workingTypeEdit = $("#workingTypeEdit").val()
	var passTypeEdit = $("#passTypeEdit").val();
	if(workingTypeEdit <0 || workingTypeEdit >1){
		console.log("enter");
		$("#messageEdit").html("Working type cannot be less than 0 and more than 1");
	}else if ( passTypeEdit <0 || passTypeEdit >4){
		$("#messageEdit").html("Pass type cannot be less than 0 and more than 4");
	}else {
		var letters = /^[A-Za-z]+$/; 
		var passNoEdit = $("#passNoEdit").val();
		var passLength = passNoEdit.length;
		var checkFirstAlpa = passNoEdit.charAt(0);
		var checkLastAlpa = passNoEdit.charAt(8);
		var checkNumbers = Number((passNoEdit).substring(1,8));	
		if(passLength != 9 || !(checkFirstAlpa.match(letters))|| !(checkLastAlpa.match(letters))){
			$("#messageEdit").html("Please enter a valid IC");
		}else{
			var nameEdit = $("#nameEdit").val();
			var avataEdit = $("#avataEdit").val();
			var frequencyIdEdit = $("#frequencyIdEdit").val();
			
			input.taskId = Number(id);
			input.companyId = Number(companyIdEdit);
			input.name = nameEdit;
			input.email = emailEdit;
			input.avata = avataEdit;
			input.workingType = Number(workingTypeEdit);
			input.passType = Number(passTypeEdit);
			input.passNo = passNoEdit;
			input.frequencyId = Number(frequencyIdEdit);
			
			var inputStr = JSON.stringify(input);
			inputStr = encodeURIComponent(inputStr);
			
			$.ajax({
				url: '/Payroll/admin/UpdateTaskServlet?input='+inputStr,
				method: 'GET',
				async: false,
				dataType: 'json',
				error: function(err){
					console.log(err);
					$(".my-loading").addClass('sr-only');
				},
				success: function(data){
					console.log(data);
					var status = data.status;
					var message = data.message;
					if(status == 1){
						var tmpEmp = message;
						
						var taskId = tmpEmp.taskId;
						var companyId = tmpEmp.company.companyId;
						var avata = tmpEmp.avata;
						var email = tmpEmp.email;
						var name = tmpEmp.name;
						var workingType = tmpEmp.workingType;
						var passType = tmpEmp.passType;
						var passNo = tmpEmp.passNo;
						var frequencyId = tmpEmp.frequency.frequencyId;
						var date = tmpEmp.createDate;
						var wType = '';
						var pType = '';
						switch (passType) {
							case 0: pType = "Local";
		                    	break;
							case 1: pType = "PR";
		                    	break;
							case 2: pType = "EP";
		                    	break;
							case 3: pType = "SP";
		                    	break;
							case 4: pType = "WP";
		                    	break;
				            default: passType = "Invalid pass";
	                     		break;
						}
					if(workingType == 0){
						wType = "Full-Time";
					}else{
						wType = "Part-Time";
					}
						$("#avataA-"+id).attr('href', avata);
						$("#avata-"+id).attr('src', avata);
						$("#name-"+id).html(name);
						$("#email-"+id).html(email);
						$("#workingType-"+id).html(pType);
						$("#passType-"+id).html(wType);
						$("#passNo-"+id).html(passNo);
						$("#frequencyId-"+id).html(frequencyId);
						
						for(var o in EMPLOYEES){
							var tmpEmp = EMPLOYEES[o];
							if(tmpEmp.taskId == id){
								EMPLOYEES[o] = message;
								break;
							}
						}
						CloseModalBox();
						$("#editTask").modal('hide');
					}else{
						$("#messageEdit").html(message);
					}
					$(".my-loading").addClass('sr-only');
				}
			});
		}
	}
}

function changeTaskPassword(id){
	$("#messageChangePassword").html('');
	$(".my-loading").removeClass('sr-only');
	var input = {};
	var password = $("#passwordEdit").val();
	var password2 = $("#password2Edit").val();
	
	if(password != password2){
		$("#messageChangePassword").html("Please confirm your password again!");
		$(".my-loading").addClass('sr-only');
	}else{
		input.taskId = id;
		input.password = password;
		
		var inputStr = JSON.stringify(input);
		inputStr = encodeURIComponent(inputStr);
		
		$.ajax({
			url: '/Payroll/admin/ChangeTaskPassword?input='+inputStr,
			method: 'GET',
			async : false,
			dataType: 'json',
			error: function(err){
				console.log(err);
				$(".my-loading").addClass('sr-only');
			},
			success: function(data){
				var status = data.status;
				var message = data.message;
				if(status == 1){
					$("#changeTaskPassword").modal('hide');
					CloseModalBox();
				}else{
					$("#messageChangePassword").html(message);
				}
				$(".my-loading").addClass('sr-only');
			}
		});
	}
}

function deleteTask(id){
	$("#messageDeleteTask").html('');
	$(".my-loading").removeClass('sr-only');
	var task = {};
	for(var o in EMPLOYEES){
		var tempEmp = EMPLOYEES[o];
		
		if(tempEmp.taskId == id){
			task = tempEmp;
			break;
		}
	}
	
	var name = task.name;
	var nameDelete = $("#nameDelete").val();
	
	var input = {};
	input.taskId = id;
	
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	if(nameDelete == name){
		$.ajax({
			url: '/Payroll/admin/DeleteTaskServlet?input='+inputStr,
			method: 'GET',
			async :false,
			dataType: 'json',
			error: function(err){
				console.log(err);
				$(".my-loading").addClass('sr-only');
			},
			success: function(data){
				var status = data.status;
				var message = data.message;
				if(status == 1){
					var tmpEmp = message;
					var objStatus = tmpEmp.objStatus;
					$("#task-"+ id).remove();
					$("#deleteTask").modal('hide');
					CloseModalBox();
				}else{
					$("#messageDeleteTask").html(message);
				}
				$(".my-loading").addClass('sr-only');
			}
		});
	}else{
		$("#messageDeleteTask").html('The task name mis-match!');
		$(".my-loading").addClass('sr-only');
	}
}

function launchCreateTask(){
	var form = $('<form class="form-horizontal" onsubmit="createTask();return false;">'+
			'<div class="form-group">'+
				'<label for="name" class="col-sm-2 control-label">Name</label>'+
				'<div class="col-sm-10">'+
					'<input type="text" class="form-control" id="name" placeholder="Task Name" required>'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label for="email" class="col-sm-2 control-label">Email</label>'+
				'<div class="col-sm-10">'+
					'<input type="email" class="form-control" id="email" placeholder="Email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label for="avata" class="col-sm-2 control-label">Avata</label>'+
				'<div class="col-sm-2">'+
					'<label for="avataUpload">'+
						'<img id="avataImg" class="img-responsive img-rounded clickable" alt="avata" src="/Payroll/asset/img/default.jpg">'+
					'</label>'+
					'<div class="text-center"><small>Size: 300px x 320px</small></div>'+
					'<input type="text" id="avata" value="/Payroll/asset/img/default.jpg" class="form-control sr-only">'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label for="password" class="col-sm-2 control-label">Password</label>'+
				'<div class="col-sm-10">'+
					'<input type="password" class="form-control" id="password" placeholder="Password" required>'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label for="password2" class="col-sm-2 control-label">Confirm Password</label>'+
				'<div class="col-sm-10">'+
					'<input type="password" class="form-control" id="password2" placeholder="Confirm Password" required>'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label for="workingType" class="col-sm-2 control-label">Working Type</label>'+
				'<div class="col-sm-10">'+
					'<input type="number" min="0" max="1" class="form-control" id="workingType" value=0 placeholder="0 - full time; 1 - part time;" required>'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label for="passType" class="col-sm-2 control-label">Pass Type</label>'+
				'<div class="col-sm-10">'+
					'<input type="number" min="0" max="4" class="form-control" id="passType" value=0 placeholder="0 - local; 1 - PR; 2 - EP; 3 - SP; 4 - WP;" required>'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label for="passNo" class="col-sm-2 control-label">Pass No</label>'+
				'<div class="col-sm-10">'+
					'<input type="text" class="form-control" id="passNo" placeholder="e.g. G0712805W" required>'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label for="frequencyId" class="col-sm-2 control-label">FrequencyId</label>'+
				'<div class="col-sm-10">'+
					'<input type="number" min="0" class="form-control" id="frequencyId" placeholder="Frequency ID" required>'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label for="payslipId" class="col-sm-2 control-label">PayslipId</label>'+
				'<div class="col-sm-10">'+
					'<input type="number" min="1" class="form-control" id="payslipId" placeholder="Payslip ID" required>'+
				'</div>'+
			'</div>'+
		'</form>');
	
	var buttons = $('<div class="form-group">'+
						'<div class="col-sm-offset-2">'+
							'<button type="submit" id="CreateEmp" class="btn btn-xs btn-primary">Create</button>'+
							'<font color="red" id="message"></font>'+
							'<img src="/Payroll/asset/img/ajax-loader.gif" class="my-loading sr-only">'+
							'<button type="button" id="close" class="btn btn-xs btn-default pull-right" data-dismiss="modal">Close</button>'+
						'</div>'+
					'</div>');

		
	OpenModalBox('Create Task', form, buttons);
	$('#close').on('click', function(){
		CloseModalBox();
	});
	$('#CreateEmp').on('click', function(){
		createTask();
	});
}