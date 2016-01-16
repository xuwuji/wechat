<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order</title>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/bootstrapformhelpers/js/bootstrap-formhelpers.js"></script>
<link
	href="${pageContext.request.contextPath}/resources/bootstrapformhelpers/css/bootstrap-formhelpers.css"
	rel="stylesheet" media="screen">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/dashboard.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/highchart/highcharts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/highchart/modules/exporting.js"></script>

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">微信电商平台</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/logout">退出</a></li>
				<li><a href="#">Settings</a></li>
				<li><a href="#">Profile</a></li>
				<li><a href="#">Help</a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>
	</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="#">Overview <span class="sr-only">(current)</span></a></li>
					<li><a href="#">Reports</a></li>
					<li><a href="#">Analytics</a></li>
					<li><a href="#">Export</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="${pageContext.request.contextPath}/index">商品管理</a></li>
					<li class="active"><a href="">订单管理</a></li>
					<li><a href="${pageContext.request.contextPath}/user/index">用户分析</a></li>
					<li><a href="">预留位</a></li>
					<li><a href="">预留位</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">预留位</a></li>
					<li><a href="">预留位</a></li>
					<li><a href="">预留位</a></li>
					<li><a href="">预留位</a></li>
				</ul>
			</div>





			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row placeholders">
					<div class="col-xs-6 col-sm-3 placeholder">
						<h4>
							<div class="bfh-datepicker" data-format="y-m-d" data-date="today"
								id="startDate"></div>
						</h4>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<h4>
							<div class="bfh-datepicker" data-format="y-m-d" data-date="today"
								id="endDate"></div>
						</h4>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder" style="padding-left: 0px;width: 115px;">
						<h4>
							<button type="button" class="btn btn-primary"
								data-target='#myModa2' data-toggle='modal' onclick="getOrder()" style="width: 76px;">查询</button>
						</h4>

					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<h4>Label3</h4>
						<span class="text-muted">预留位</span>
					</div>
				</div>
				<!--status pie chart starts here.....-->
				<div id="status_pie_container"
					style="min-width: 300px; height: 300px; max-width: 600px; margin: 0 auto"></div>
				<!--status pie chart ends here.....-->

				<!--province pie chart starts here.....-->
				<div id="province_pie_container"
					style="min-width: 300px; height: 300px; max-width: 600px; margin: 0 auto"></div>
				<!--province pie chart ends here.....-->


				<h2 class="sub-header" id="order_count"></h2>

				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>编号</th>
								<th>下单时间</th>
								<th>订单状态</th>
								<th>收获地址</th>
								<th>＃</th>
								<th>＃</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id='result_table'>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div> 


	<!-- Order Detail Model start here....-->
	<div class="modal fade" id="order_detail_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabe2">订单详情</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="add_product_form"
						enctype="multipart/form-data" method="POST"
						action="${pageContext.request.contextPath}/product/add">
						<div class="form-group">
							<label for="image_name" class="col-xs-2 control-label">1</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="product_name"
									name="product_name" placeholder="根据需求预留信息显示....."/>
							</div>
						</div>
						<div class="form-group">
							<label for="image_name" class="col-xs-2 control-label">2</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="product_name"
									name="product_name" placeholder="根据需求预留信息显示....."/>
							</div>
						</div>
						<div class="form-group">
							<label for="image_name" class="col-xs-2 control-label">3</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="product_name"
									name="product_name" placeholder="根据需求预留信息显示....."/>
							</div>
						</div>
						<div class="form-group">
							<label for="image_name" class="col-xs-2 control-label">4</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="product_name"
									name="product_name" placeholder="根据需求预留信息显示....."/>
							</div>
						</div>
						<div class="form-group">
							<label for="image_name" class="col-xs-2 control-label">5</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="product_name"
									name="product_name" placeholder="根据需求预留信息显示....."/>
							</div>
						</div>
						<div class="form-group">
							<label for="image" class="col-xs-2 control-label">6</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="product_name"
									name="product_name" placeholder="根据需求预留信息显示....."/>
							</div>
						</div>
						<div class="form-group">
							<label for="image" class="col-xs-2 control-label">7</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="product_name"
									name="product_name" placeholder="根据需求预留信息显示....."/>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Add New Product ends here....-->
</body>
<script type="text/javascript">
	$(document).ready(function() {
		//groupByCategory();
		//groupByPrice();
		//getProduct();
		//groupByProvince();
		getOrder();
	});

	//group by order status function starts here...
	function groupByStatus() {
		var startDate=$('#startDate').val();
		var endDate=$('#endDate').val();
		var array = Array();
		$
				.getJSON(
						'${pageContext.request.contextPath}/order/status?startDate='+startDate+'&endDate='+endDate,
						function(cdata) {
							console.log(cdata);
							for (key in cdata) {
								array.push([ key, cdata[key] ])
							}

							$.each(cdata, function(i, point) {
								point.y = point.data;
							});
							$(function() {
								$('#status_pie_container')
										.highcharts(
												{
													chart : {
														plotBackgroundColor : null,
														plotBorderWidth : null,
														plotShadow : false,
														type : 'pie'
													},
													title : {
														text : '订单状态'
													},
													tooltip : {
														pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
													},
													plotOptions : {
														pie : {
															allowPointSelect : true,
															cursor : 'pointer',
															dataLabels : {
																enabled : true,
																format : '<b>{point.name}</b>: {point.percentage:.1f} %',
																style : {
																	color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
																			|| 'black'
																}
															}
														}
													},
													series : [ {
														name : 'Percentage',
														colorByPoint : true,
														data : array
													} ]
												});
							});
						}).done(function(){
							groupByProvince();
						});

	}
	//group by order status function starts here...

	//group by province function starts here...
	function groupByProvince() {
		var startDate=$('#startDate').val();
		var endDate=$('#endDate').val();
		var array = Array();
		$
				.getJSON(
						'${pageContext.request.contextPath}/order/province?startDate='+startDate+'&endDate='+endDate,
						function(cdata) {
							console.log(cdata);
							for (key in cdata) {
								array.push([ key, cdata[key] ])
							}

							$.each(cdata, function(i, point) {
								point.y = point.data;
							});
							$(function() {
								$('#province_pie_container')
										.highcharts(
												{
													chart : {
														plotBackgroundColor : null,
														plotBorderWidth : null,
														plotShadow : false,
														type : 'pie'
													},
													title : {
														text : '购买省份'
													},
													tooltip : {
														pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
													},
													plotOptions : {
														pie : {
															allowPointSelect : true,
															cursor : 'pointer',
															dataLabels : {
																enabled : true,
																format : '<b>{point.name}</b>: {point.percentage:.1f} %',
																style : {
																	color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
																			|| 'black'
																}
															}
														}
													},
													series : [ {
														name : 'Percentage',
														colorByPoint : true,
														data : array
													} ]
												});
							});
						});
	}
	//group by province function ends here...

	//String buffer function starts here...
	function StringBuffer() {
		this.__strings__ = [];
	};
	StringBuffer.prototype.append = function(str) {
		this.__strings__.push(str);
	};
	StringBuffer.prototype.toString = function() {
		return this.__strings__.join('');
	};
	//String buffer function ends here...



	//show the orders starts here...
	function getOrder() {

		var startDate=$('#startDate').val();
		var endDate=$('#endDate').val();
		$
				.getJSON(
						'${pageContext.request.contextPath}/order/get?startDate='+startDate+'&endDate='+endDate,
						function(data) {

							var length = data.length;
							var line = new StringBuffer();
							$
									.each(
											data,
											function(i, record) {
												var order_id = record.order_id;
												//var title = record.time;
												var img_url = record.img_url;
												var address = record.address;
												var status = record.status;
												var time = record.time;
												//console.log(id);
												line
														.append('<tr><td>'
																+ order_id
																+ '</td><td>'
																+ time
																+ '</td><td>'
																+ status
																+ '</td><td>'
																+ address
																+ '</td><td>'
																+ '<img src=\''+img_url+'\' style="width: 30px;"></img>'
																+ '</td><td>'
																+ '#'
																+ '</td><td><button type="button" class="btn btn-primary" type="button" data-target="#order_detail_Modal" data-toggle="modal">详细信息</button></td></tr>');
											});
							$('#order_count').html('');
							$('#order_count')
									.append('已查询到的订单: ' + length + '条');
							var $table = $('#result_table');
							$table.html('');
							$table.append(line.toString());
						}).done(function(){
							//alert("done");
							groupByStatus();
							
						});
						
						
	//
	
	}
	//show the active products ends here...


	//show detail of an order function starts here...
	function detailOrder(orderId) {
		$.getJSON('${pageContext.request.contextPath}/product/delete/'
				+ productId, function() {
		});
		var $table = $('#result_table');
		$table.html('');
		getProduct();
	}
</script>
</html>