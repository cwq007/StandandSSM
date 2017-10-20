<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String contentPath = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + 
			contentPath + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试跨域请求</title>
<script type="text/javascript" src="<%=basePath %>resource/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	function get_weather() {
		/* 会发生跨域安全问题
		var queryUrl = "http://op.juhe.cn/onebox/weather/query";
		$("div").load(queryUrl, {key: "1bf47c326664bd7db4a7b3f9d5d167b2", cityname: city});
		*/
		
		var city = $("input").val();
		
		$.ajax({
		    type:"POST",    //请求方式
		    async:true,    //是否异步
		    url:"http://op.juhe.cn/onebox/weather/query",
		    dataType:"jsonp",    //跨域json请求一定是jsonp
		    jsonp: "callback",    //跨域请求的参数名，默认是callback
		        //jsonpCallback:"successCallback",    //自定义跨域参数值，回调函数名也是一样，默认为jQuery自动生成的字符串
		    data:{key: "1bf47c326664bd7db4a7b3f9d5d167b2", cityname: city},    //请求参数

		    success: function(data) {
		    	var realtime = data.result.data.realtime;
		    	var city = realtime.city_name;
		    	var weatherTemp = realtime.weather.temperature;
		    	var weatherInfo = realtime.weather.info;
		        //请求成功处理，和本地回调完全一样
		        $("div").html("温度：" + weatherTemp + "; 信息：" + weatherInfo);
		    },
		    
			/*
		    beforeSend: function() {
		        //请求前的处理
		    },
		    complete: function() {
		        //请求完成的处理
		    },

		    error: function() {
		        //请求出错处理
		    }
		    */
		});
	}
</script>
</head>
<body>
	<input type="text"/>
	<button onclick="get_weather();">查询</button>
	
	<div style="width: 20%; height: 500px; border: 1px solid gray;"></div>
</body>
</html>