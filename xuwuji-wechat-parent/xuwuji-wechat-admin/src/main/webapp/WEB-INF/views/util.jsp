<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Util</title>
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
<link
	href="${pageContext.request.contextPath}/resources/DataTables/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/highchart/highcharts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/highchart/modules/funnel.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/highchart/modules/exporting.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/DataTables/js/jquery.dataTables.min.js"></script>

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
					<li><a href="${pageContext.request.contextPath}/order/index">订单管理</a></li>
					<li><a href="${pageContext.request.contextPath}/user/index">用户分析</a></li>
					<li class="active"><a href="">金融工具</a></li>
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
					<div class="col-xs-6 col-sm-3 placeholder" style="width: 230px;">
						<h4>
							<input type="text" class="form-control" id="currency_amount"
								placeholder="金额" />
						</h4>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder" style="width: 230px;">
						<h4>
							<div class="bfh-selectbox" data-name="selectbox3" data-value="1"
								data-filter="true" id="source_currency">
								<div data-value="1">美元USD</div>
								<div data-value="2">人民币CNY</div>
								<div data-value="3">日元JPY</div>
								<div data-value="4">英镑GBP</div>
								<div data-value="5">欧元EUR</div>
								<div data-value="6">韩元KRW</div>
							</div>

						</h4>

					</div>
					<div class="col-xs-6 col-sm-3 placeholder"
						style="width: 20px; padding-top: 7px; padding-left: 10px;">
						<h4>&rarr;</h4>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder" style="width: 230px;">
						<h4>
							<div class="bfh-selectbox" data-name="selectbox3" data-value="1"
								data-filter="true" id="target_currency">
								<div data-value="1">人民币CNY</div>
								<div data-value="2">美元USD</div>
								<div data-value="3">日元JPY</div>
								<div data-value="4">英镑GBP</div>
								<div data-value="5">欧元EUR</div>
								<div data-value="6">韩元KRW</div>
							</div>

						</h4>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder"
						style="padding-left: 0px; width: 115px;">
						<h4>
							<button type="button" class="btn btn-primary" onclick="exchange()"
								style="width: 76px;">查询</button>
						</h4>

					</div>
					<div class="col-xs-6 col-sm-3 placeholder" style="width: 200px;">
							<h4>
							<input type="text" class="form-control" id="currentcy_result"
								placeholder="结果" />
						</h4>
					</div>
				</div>
				<!--user trend high chart starts here.....-->
				<div id="user_trend_container"
					style="min-width: 300px; height: 300px; max-width: 600px; margin: 0 auto"></div>
				<!--user trend high chart ends here.....-->
				<hr
					style="height: 1px; border: none; border-top: 1px solid #555555;" />
				<!--search pie chart starts here.....-->
				<div id="search_pie_container"
					style="min-width: 300px; height: 300px; max-width: 600px; margin: 0 auto"></div>
				<!--search pie chart ends here.....-->
				<hr
					style="height: 1px; border: none; border-top: 1px solid #555555;" />
				<!--funnel chart starts here.....-->
				<div id="funnel_container"
					style="min-width: 300px; height: 300px; max-width: 600px; margin: 0 auto"></div>
				<!--funnel chart ends here.....-->


				<!--data tables start here-->
				<table id="example" class="display" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Name</th>
							<th>Position</th>
							<th>Office</th>
							<th>Extn.</th>
							<th>Start date</th>
							<th>Salary</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Name</th>
							<th>Position</th>
							<th>Office</th>
							<th>Extn.</th>
							<th>Start date</th>
							<th>Salary</th>
						</tr>
					</tfoot>
				</table>
				<!--data tables end here-->


			</div>
		</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		getUserTrend();
		groupBySearch();
		funnelSales();

	});

	function exchange(){
		var source=[];
		source[1]="USD";
		source[2]="CNY";
		source[3]="JPY";
		source[4]="GBP";
		source[5]="EUR";
		source[6]="KRW";
		var target=[];
		target[1]="CNY";
		target[2]="USD";
		target[3]="JPY";
		target[4]="GBP";
		target[5]="EUR";
		target[6]="KRW";
		var amount=$('#currency_amount').val();
		var source_code=source[$('#source_currency').val()];
		var target_code=target[$('#target_currency').val()];
		console.log(amount);
		console.log(source_code);
		console.log(target_code);

		$.getJSON("${pageContext.request.contextPath}/util/exchange?amount="+amount+'&source='+source_code+'&target='+target_code,  function(data) {
				$('#currentcy_result').val(data);
		});
	}

	function getUserTrend() {
		var yData = [];
		var xData = [];
		$.getJSON('${pageContext.request.contextPath}/user/trend',
				function(data) {
					for (key in data) {
						console.log(key);
						yData.push(key);
						xData.push(data[key]);
					}
					console.log(yData);
					console.log(xData);
				}).done(function() {
			$('#user_trend_container').highcharts({
				title : {
					text : '用户趋势',
					x : -20
				//center
				},
				subtitle : {
					text : '最近一月',
					x : -20
				},
				xAxis : {
					categories : yData
				},
				yAxis : {
					title : {
						text : '每日新加用户'
					},
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				tooltip : {
					valueSuffix : ''
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'middle',
					borderWidth : 0
				},
				series : [ {
					name : '',
					data : xData
				} ]
			});
		});

	}

	//funnel sales function starts here...
	function funnelSales() {
		$('#funnel_container')
				.highcharts(
						{
							chart : {
								type : 'funnel',
								marginRight : 100
							},
							title : {
								text : '购买转换率分析',
								x : -50
							},
							plotOptions : {
								series : {
									dataLabels : {
										enabled : true,
										format : '<b>{point.name}</b> ({point.y:,.0f})',
										color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
												|| 'black',
										softConnector : true
									},
									neckWidth : '30%',
									neckHeight : '25%'

								//-- Other available options
								// height: pixels or percent
								// width: pixels or percent
								}
							},
							legend : {
								enabled : false
							},
							series : [ {
								name : 'Unique users',
								data : [ [ '用户数', 15654 ], [ '用户操作', 4064 ],
										[ '查看商品', 1987 ], [ '下订单', 976 ],
										[ '付款', 846 ] ]
							} ]
						});
	}
	//funnel sales status function starts here...

	//group by search keyword function starts here...
	function groupBySearch() {
		//var startDate=$('#startDate').val();
		//var endDate=$('#endDate').val();
		var array = Array();
		$
				.getJSON(
						'${pageContext.request.contextPath}/user/search',
						function(cdata) {
							console.log(cdata);
							for (key in cdata) {
								array.push([ key, cdata[key] ])
							}

							$.each(cdata, function(i, point) {
								point.y = point.data;
							});
							$(function() {
								$('#search_pie_container')
										.highcharts(
												{
													chart : {
														plotBackgroundColor : null,
														plotBorderWidth : null,
														plotShadow : false,
														type : 'pie'
													},
													title : {
														text : '搜素关键词'
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
	//group by search keyword function ends here...

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
</script>
</html>