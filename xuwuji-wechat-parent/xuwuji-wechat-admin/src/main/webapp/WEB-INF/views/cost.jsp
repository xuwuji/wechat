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
	src="${pageContext.request.contextPath}/resources/js/highchart/highcharts-3d.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/highchart/themes/dark-unica.js"></script>
<script type="text/javascript"
	src="http://cdn.hcharts.cn/highcharts/highcharts-more.js"></script>
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
					<li><a href="${pageContext.request.contextPath}/util/index">金融工具</a></li>
					<li class="active"><a href="">成本控制</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">预留位</a></li>
					<li><a href="">预留位</a></li>
					<li><a href="">预留位</a></li>
					<li><a href="">预留位</a></li>
				</ul>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<!--3d cost chart starts here-->
				<div id="cost_container" style="min-width: 700px; height: 400px"></div>
				<!--3d cost chart ends here-->
				<hr
					style="height: 1px; border: none; border-top: 1px solid #555555;" />

				<!--stock starts here-->
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
					<div class="col-xs-6 col-sm-3 placeholder"
						style="padding-left: 0px; width: 115px;">
						<h4>
							<button type="button" class="btn btn-primary"
								data-target='#myModa2' data-toggle='modal'
								onclick="stock_apply()" style="width: 76px;">查询</button>
						</h4>

					</div>

				</div>
				<div id="stock_container" style="height: 400px; min-width: 310px"></div>
				<!--stock ends here-->

				<hr
					style="height: 1px; border: none; border-top: 1px solid #555555;" />
				<!--spider chart starts here.....-->
				<div id="spider_container" style="min-width: 700px; height: 400px"></div>
				<!--spider chart ends here.....-->
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
		stock("2016-01-25", "2016-01-25");
		cost3d();
		spider();
	});

	function stock_apply() {
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		stock(startDate, endDate);
	}

	function stock(startDate, endDate) {
		var yData = [];
		var maxData = [];
		var minData = [];
		var openData = [];
		var closeData = [];
		var xData = [];
		$.getJSON(
				'${pageContext.request.contextPath}/util/stock?startDate='
						+ startDate + '&endDate=' + endDate, function(data) {
					var obj = data;
					for (var i = 0; i < obj.length; i++) {
						var stock = obj[i];
						var date = stock.date;
						var max = stock.max;
						var min = stock.min;
						var open = stock.open;
						var close = stock.close;
						yData.push(date);
						maxData.push(max);
						minData.push(min);
						openData.push(open);
						closeData.push(close);
					}
				}).done(function() {
			var chart = new Highcharts.Chart({
				chart : {
					renderTo : 'stock_container',

					options3d : {
						enabled : false,
					}
				},
				title : {
					text : '上证综指',
					x : -20
				//center
				},
				subtitle : {
					text : '默认显示最近一月',
					x : -20
				},
				xAxis : {
					categories : yData
				},
				yAxis : {
					title : {
						text : '指数'
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
					name : '最高点',
					data : maxData
				}, {
					name : '最低点',
					data : minData
				}, {
					name : '开盘',
					data : openData
				}, {
					name : '收市',
					data : closeData
				} ]
			})
		});
	}

	function spider() {

			$('#spider_container')
					.highcharts(
							{

								chart : {
									polar : true,
									type : 'line'
								},

								title : {
									text : '预算 vs 开销',
									x : -80
								},

								pane : {
									size : '80%'
								},

								xAxis : {
									categories : [ '推广', '人工',
											'产品成本', '维护',
											'促销',
											'其他' ],
									tickmarkPlacement : 'on',
									lineWidth : 0
								},

								yAxis : {
									gridLineInterpolation : 'polygon',
									lineWidth : 0,
									min : 0
								},

								tooltip : {
									shared : true,
								},

								legend : {
									align : 'right',
									verticalAlign : 'top',
									y : 70,
									layout : 'vertical'
								},

								series : [
										{
											name : '预算',
											data : [ 43000, 19000, 60000,
													35000, 17000, 10000 ],
											pointPlacement : 'on'
										},
										{
											name : '实际开销',
											data : [ 50000, 39000, 42000,
													31000, 26000, 14000 ],
											pointPlacement : 'on'
										} ]

							});
	}

	function cost3d() {

		// Give the points a 3D feel by adding a radial gradient
		Highcharts.getOptions().colors = $.map(Highcharts.getOptions().colors,
				function(color) {
					return {
						radialGradient : {
							cx : 0.4,
							cy : 0.3,
							r : 0.5
						},
						stops : [
								[ 0, color ],
								[
										1,
										Highcharts.Color(color).brighten(-0.2)
												.get('rgb') ] ]
					};
				});

		// Set up the chart
		var chart = new Highcharts.Chart({
			chart : {
				renderTo : 'cost_container',
				margin : 100,
				type : 'scatter',
				options3d : {
					enabled : true,
					alpha : 10,
					beta : 30,
					depth : 250,
					viewDistance : 5,

					frame : {
						bottom : {
							size : 1,
							color : 'rgba(0,0,0,0.02)'
						},
						back : {
							size : 1,
							color : 'rgba(0,0,0,0.04)'
						},
						side : {
							size : 1,
							color : 'rgba(0,0,0,0.06)'
						}
					}
				}
			},
			tooltip : {
				// pointFormat:'{point.x}{point.y}{point.z}'
				formatter : function() {
					console.log(this);
					return '<b>' + this.series.name + '</b><br><b>广度:</b>'
							+ this.x + '<br><b>高度:</b>' + this.y
							+ '<br><b>深度:</b>' + this.point.options.z;
				}
			},
			title : {
				text : '成本关联系数'
			},
			subtitle : {
				text : '点击并拖拽以更好地使用'
			},
			plotOptions : {
				scatter : {
					width : 10,
					height : 10,
					depth : 10
				}
			},
			yAxis : {
				min : 0,
				max : 10,
				title : null
			},
			xAxis : {
				min : 0,
				max : 10,
				gridLineWidth : 1
			},
			zAxis : {
				min : 0,
				max : 10
			},
			legend : {
				enabled : false
			},
			series : [ {
				name : '关联',
				colorByPoint : true,
				data : [ [ 1, 6, 5 ], [ 8, 7, 9 ], [ 1, 3, 4 ], [ 4, 6, 8 ],
						[ 5, 7, 7 ], [ 6, 9, 6 ], [ 7, 0, 5 ], [ 2, 3, 3 ],
						[ 3, 9, 8 ], [ 3, 6, 5 ], [ 4, 9, 4 ], [ 2, 3, 3 ],
						[ 6, 9, 9 ], [ 0, 7, 0 ], [ 7, 7, 9 ], [ 7, 2, 9 ],
						[ 0, 6, 2 ], [ 4, 6, 7 ], [ 3, 7, 7 ], [ 0, 1, 7 ],
						[ 2, 8, 6 ], [ 2, 3, 7 ], [ 6, 4, 8 ], [ 3, 5, 9 ],
						[ 7, 9, 5 ], [ 3, 1, 7 ], [ 4, 4, 2 ], [ 3, 6, 2 ],
						[ 3, 1, 6 ], [ 6, 8, 5 ], [ 6, 6, 7 ], [ 4, 1, 1 ],
						[ 7, 2, 7 ], [ 7, 7, 0 ], [ 8, 8, 9 ], [ 9, 4, 1 ],
						[ 8, 3, 4 ], [ 9, 8, 9 ], [ 3, 5, 3 ], [ 0, 2, 4 ],
						[ 6, 0, 2 ], [ 2, 1, 3 ], [ 5, 8, 9 ], [ 2, 1, 1 ],
						[ 9, 7, 6 ], [ 3, 0, 2 ], [ 9, 9, 0 ], [ 3, 4, 8 ],
						[ 2, 6, 1 ], [ 8, 9, 2 ], [ 7, 6, 5 ], [ 6, 3, 1 ],
						[ 9, 3, 1 ], [ 8, 9, 3 ], [ 9, 1, 0 ], [ 3, 8, 7 ],
						[ 8, 0, 0 ], [ 4, 9, 7 ], [ 8, 6, 2 ], [ 4, 3, 0 ],
						[ 2, 3, 5 ], [ 9, 1, 4 ], [ 1, 1, 4 ], [ 6, 0, 2 ],
						[ 6, 1, 6 ], [ 3, 8, 8 ], [ 8, 8, 7 ], [ 5, 5, 0 ],
						[ 3, 9, 6 ], [ 5, 4, 3 ], [ 6, 8, 3 ], [ 0, 1, 5 ],
						[ 6, 7, 3 ], [ 8, 3, 2 ], [ 3, 8, 3 ], [ 2, 1, 6 ],
						[ 4, 6, 7 ], [ 8, 9, 9 ], [ 5, 4, 2 ], [ 6, 1, 3 ],
						[ 6, 9, 5 ], [ 4, 8, 2 ], [ 9, 7, 4 ], [ 5, 4, 2 ],
						[ 9, 6, 1 ], [ 2, 7, 3 ], [ 4, 5, 4 ], [ 6, 8, 1 ],
						[ 3, 4, 0 ], [ 2, 2, 6 ], [ 5, 1, 2 ], [ 9, 9, 7 ],
						[ 6, 9, 9 ], [ 8, 4, 3 ], [ 4, 1, 7 ], [ 6, 2, 5 ],
						[ 0, 4, 9 ], [ 3, 5, 9 ], [ 6, 9, 1 ], [ 1, 9, 2 ] ]
			} ]
		});

		// Add mouse events for rotation
		$(chart.container)
				.bind(
						'mousedown.hc touchstart.hc',
						function(e) {
							e = chart.pointer.normalize(e);

							var posX = e.pageX, posY = e.pageY, alpha = chart.options.chart.options3d.alpha, beta = chart.options.chart.options3d.beta, newAlpha, newBeta, sensitivity = 5; // lower is more sensitive

							$(document)
									.bind(
											{
												'mousemove.hc touchdrag.hc' : function(
														e) {
													// Run beta
													newBeta = beta
															+ (posX - e.pageX)
															/ sensitivity;
													newBeta = Math.min(100,
															Math.max(-100,
																	newBeta));
													chart.options.chart.options3d.beta = newBeta;

													// Run alpha
													newAlpha = alpha
															+ (e.pageY - posY)
															/ sensitivity;
													newAlpha = Math.min(100,
															Math.max(-100,
																	newAlpha));
													chart.options.chart.options3d.alpha = newAlpha;

													chart.redraw(false);
												},
												'mouseup touchend' : function() {
													$(document).unbind('.hc');
												}
											});
						});
	}

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