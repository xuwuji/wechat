<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Product</title>
<!-- Bootstrap core CSS -->
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>

<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/dashboard.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/DataTables/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/highchart/highcharts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/highchart/modules/exporting.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/DataTables/js/jquery.dataTables.min.js"></script>
<title>Insert title here</title>
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
					<li class="active"><a href="#">商品管理</a></li>
					<li><a href="${pageContext.request.contextPath}/order/index">订单管理</a></li>
					<li><a href="${pageContext.request.contextPath}/user/index">用户分析</a></li>
					<li><a href="${pageContext.request.contextPath}/util/index">金融工具</a></li>
					<li><a href="${pageContext.request.contextPath}/cost/index">成本控制</a></li>
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
							<button type="button" class="btn btn-primary"
								data-target='#add_new_Modal' data-toggle='modal'>新增商品</button>
						</h4>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<h4>
							<button type="button" class="btn btn-primary"
								data-target='#myModa2' data-toggle='modal'>上传图片</button>
						</h4>
						<span class="text-muted">预留位</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<h4>Label2</h4>
						<span class="text-muted">预留位</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<h4>Label3</h4>
						<span class="text-muted">预留位</span>
					</div>
				</div>
				<!--category pie chart starts here.....-->
				<div id="category_pie_container"
					style="min-width: 300px; height: 300px; max-width: 600px; margin: 0 auto"></div>
				<!--category pie chart ends here.....-->

				<!--price pie chart starts here.....-->
				<div id="price_pie_container"
					style="min-width: 300px; height: 300px; max-width: 600px; margin: 0 auto"></div>
				<!--price pie chart ends here.....-->


				<h2 class="sub-header" id="order_count"></h2>

				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>编号</th>
								<th>名称</th>
								<th>类别</th>
								<th>价格</th>
								<th>库存</th>
								<th>上架时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id='result_table'>
						</tbody>
					</table>
				</div>



				<!--data tables start here-->
				<table id="example" class="display" cellspacing="0" width="100%">
					<button type="button" class="btn btn-primary" id="delete_button">delete</button>
					<thead>
						<tr>
							<th>编号</th>
							<th>名称</th>
							<th>类别</th>
							<th>价格</th>
							<th>库存</th>
							<th>上架时间</th>

						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>编号</th>
							<th>名称</th>
							<th>类别</th>
							<th>价格</th>
							<th>库存</th>
							<th>上架时间</th>
						</tr>
					</tfoot>
				</table>
				<!--data tables end here-->







			</div>
		</div>
	</div>
	<!-- Add New Product start here....-->
	<div class="modal fade" id="add_new_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabe2">上传新的商品</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="add_product_form"
						enctype="multipart/form-data" method="POST"
						action="${pageContext.request.contextPath}/product/add">
						<div class="form-group">
							<label for="image_name" class="col-xs-2 control-label">名称</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="product_name"
									name="product_name" />
							</div>
						</div>
						<div class="form-group">
							<label for="image_name" class="col-xs-2 control-label">价格</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="product_price"
									name="product_price" />
							</div>
						</div>
						<div class="form-group">
							<label for="image_name" class="col-xs-2 control-label">库存</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="order_count"
									name="order_count" />
							</div>
						</div>
						<div class="form-group">
							<label for="image_name" class="col-xs-2 control-label">类别</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="product_category"
									name="product_category" />
							</div>
						</div>
						<div class="form-group">
							<label for="image_name" class="col-xs-2 control-label">商品描述</label>
							<div class="col-xs-4">
								<input id="product_description" name="product_description"
									placeholder="请添加商品详细描述..." />
							</div>
						</div>
						<div class="form-group">
							<label for="image" class="col-xs-2 control-label">图片</label>
							<div class="col-xs-4">
								<input type="file" id="product_image_url" name="file" />
							</div>
						</div>
						<div class="form-group">
							<label for="image" class="col-xs-2 control-label">商品地址</label>
							<div class="col-xs-4">
								<input type="text" id="product_url" name="product_url" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-primary" id="upload"
						onclick='add()'>确定</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Add New Product ends here....-->
</body>
<script type="text/javascript">
	$(document).ready(function() {
		groupByCategory();
		groupByPrice();

		dataTable();
		getProduct();

	});

	function dataTable() {
		$('#example').DataTable({
			"ajax" : {
				"url" : "${pageContext.request.contextPath}/product/get",
				"type" : "GET",
				"dataSrc" : ""
			},
			"columns" : [ {
				"data" : "id"
			}, {
				"data" : "title"
			}, {
				"data" : "category"
			}, {
				"data" : "price"
			}, {
				"data" : "count"
			}, {
				"data" : "time"
			} ]
		});

		var table = $('#example').DataTable();

		$('#example tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});
		$('#button').click(function() {
			table.row('.selected').remove().draw(false);
		});

		$('#delete_button').click(function() {


			table.row('.selected').remove();
				$('#example').DataTable({
			"ajax" : {
				"url" : "${pageContext.request.contextPath}/product/get",
				"type" : "GET",
				"dataSrc" : ""
			},
			"columns" : [ {
				"data" : "id"
			}, {
				"data" : "title"
			}, {
				"data" : "category"
			}, {
				"data" : "price"
			}, {
				"data" : "count"
			}, {
				"data" : "time"
			} ]
		});
			table.draw(false);
		});

	}

	//group by category function starts here...
	function groupByCategory() {
		var array = Array();
		$
				.getJSON(
						'${pageContext.request.contextPath}/product/category',
						function(cdata) {
							console.log(cdata);
							for (key in cdata) {
								array.push([ key, cdata[key] ])
							}

							$.each(cdata, function(i, point) {
								point.y = point.data;
							});
							$(function() {
								$('#category_pie_container')
										.highcharts(
												{
													chart : {
														plotBackgroundColor : null,
														plotBorderWidth : null,
														plotShadow : false,
														type : 'pie'
													},
													title : {
														text : 'Product Category'
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
	//group by category function starts here...

	//group by price function starts here...
	function groupByPrice() {
		var array = Array();
		$
				.getJSON(
						'${pageContext.request.contextPath}/product/price',
						function(cdata) {
							console.log(cdata);
							for (key in cdata) {
								array.push([ key, cdata[key] ])
							}

							$.each(cdata, function(i, point) {
								point.y = point.data;
							});
							$(function() {
								$('#price_pie_container')
										.highcharts(
												{
													chart : {
														plotBackgroundColor : null,
														plotBorderWidth : null,
														plotShadow : false,
														type : 'pie'
													},
													title : {
														text : 'Product Price'
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
	//group by price function starts here...

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

	//show the active products starts here...
	function getProduct() {
		var tabledata = [];
		$
				.getJSON(
						'${pageContext.request.contextPath}/product/get',
						function(data) {
							//dt.data = data;
							var length = data.length;
							var line = new StringBuffer();
							console.log(data);
							$
									.each(
											data,
											function(i, record) {
												var id = record.id;
												var title = record.title;
												var category = record.category;
												var price = record.price;
												var count = record.count;
												var time = record.time;
												//console.log(id);
												line
														.append('<tr><td>'
																+ id
																+ '</td><td>'
																+ title
																+ '</td><td>'
																+ category
																+ '</td><td>'
																+ price
																+ '</td><td>'
																+ count
																+ '</td><td>'
																+ time
																+ '</td><td><button type="button" class="btn btn-primary" onclick="deleteProduct('
																+ id
																+ ')">delete</button></td></tr>');
											});
							$('#order_count').html('');
							$('#order_count').append('已上架商品: ' + length + '件');
							var $table = $('#result_table');
							$table.append(line.toString());
							tabledata = data;
						}).done(function() {
					console.log(tabledata);

				});
	}
	//show the active products ends here...

	//add a new product function starts here...
	function add() {
		var $form = $('#add_product_form');
		$form.submit();
	}

	//delete an active product function starts here...
	function deleteProduct(productId) {
		$.getJSON('${pageContext.request.contextPath}/product/delete/'
				+ productId, function() {
		});
		var $table = $('#result_table');
		$table.html('');
		getProduct();
	}
</script>
</html>