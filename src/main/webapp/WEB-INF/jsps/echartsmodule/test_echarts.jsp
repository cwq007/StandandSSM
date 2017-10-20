<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String contentPath = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 
			request.getServerPort() + "/" + contentPath + "/";
%>
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试echarts</title>
<script type="text/javascript" src="resource/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resource/js/plugins/echarts.js"></script>
<script type="text/javascript">
	require.config({
		paths: {
			echarts: "<%=basePath%>resource/js/plugins/echarts.js",
		}		
	});
	require(
		["echarts", "echarts/chart/bar"], 
		function (ec) {
			var myecharts = ex.init($("#main"));
			
			var options = {
				tooltip: {
					show: true,
				}, 
				legend: {
					data: ["销量"],
				}, 
				xAxis: [
			    	{
			    		type: "category", 
			    		data: ["衬衫", "羊毛衫", "鞋子", "高跟鞋", "袜子"], 
			    	}
			    ],
			    yAxis: [
					{
						type: "value"
					}
	            ], 
	            series: [
                	{
                		name: "销量", 
                		type: "bar", 
                		data: [5, 20, 40, 10, 10, 20]
                	}
                ]
			};
			myecharts.setOption(option);
		}
	);
</script>
</head>
<body>
	
	<div id="main" style="height: 400px;"></div>
	
</body>
</html>