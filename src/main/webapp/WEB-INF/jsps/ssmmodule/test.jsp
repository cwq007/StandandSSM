<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contentPath = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + 
			contentPath + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>基于词表自动标引测试API</title>
</head>
<body>
<script type="text/javascript" src="resource/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
function clean() {
	$("#title").val("");
	$("#keywords").val("");
	$("#summary").val("");
	$("#text").val("");
	$("#resultText").html("");
	$("#reResultText").html("");
	$("#resultDetailText").html("");
}


function tagging(index, title, keywords, summary) {
	/*
	var title = $("#title").val();
	var keywords = $("#keywords").val();
	var summary = $("#summary").val();
	if($.trim(title).length<1) {
		alert("标题不能为空！");
		return false;
	}
	*/
	var text = $("#text").val();
	var type = "en";
	$.ajax({
		type : "post",
		cache: false,
		async: true,
		url : "http://10.215.4.140:8091/nm-framework-web-api/tagging",
		data : {
			"title" : title,
			"keywords" : "",
			"summary" : summary,
			"text" : text,
			"type" : type
		},
		dataType : "json",
		success : function(r) {
			$.ajax({
				type: "post", 
				url: "<c:url value='/handlerdata/exportDataToExcel'/>", 
				async: true, 
				data: {index: index, data: JSON.stringify(r.data.reWordList)}, 
				success: function (data) {
					
				}
			});
			/*
			$("#resultText").html(JSON.stringify(r.data.wordList));
			$("#reResultText").html(JSON.stringify(r.data.reWordList));
			$("#resultDetailText").html(JSON.stringify(r.data.wordData));
			*/
		}
	}); 
}

// 开启一个空参请求
function startWork() {
	$.ajax({
		type : "get",
		url: "<c:url value='/handlerdata/getDataParams'/>", 
		data: {}, 
		success: function (data) {
			var titleKey = data['专利名称'];
			var keywordsKey = data['摘要'];
			var summaryKey = data['摘要'];
			$.each(titleKey, function (i, e) {
				tagging(i+1, titleKey[i], keywordsKey[i], summaryKey[i]);
			});
		}
	});
}

</script>
<h4>基于词表自动标引测试API在线测试</h4>
<p>标引类型：</p>
<select id="type" name="type">
  <option value="cn" selected="selected">中文标引</option>
  <option value="en">英文标引</option>
</select>
<p>标题：</p>
<input type="text" name="title" id="title" value="Reuse of spent FCC catalyst, waste serpentine and kiln rollers waste for synthesis of cordierite and cordierite-mullite ceramics" style="width: 80%">
<p>关键词：</p>
<input type="text" name="keywords" id="keywords" value="Cordierite;Cordierite-mullite;FCC catalyst;Kiln rollers waste;Waste serpentine" style="width: 80%">
<p>摘要：</p>
<textarea id="summary" name="summary" style="width: 80%;height: 80px;">Spent fluid catalytic cracking (FCC) was gathered from several petrochemical plants and calcined in a rotary furnace between 1000 and 1100 °C in order to remove sulphur and hydrocarbon based impurities. Calcining process on FCC led to formation of AlVO4ceramic phase, so converted the hazardous waste to non-hazardous applicable raw material. In this study, two ceramic bodies as cordierite and cordierite-mullite were synthesized with calcined spent FCC, waste serpentine, kiln rollers waste and high grade kaolin as raw materials. The XRD results showed that the cordierite and cordierite-mullite were synthesized successfully so that 96.4% of F1 (cordierite) sample fired at 1400 °C was cordierite phase and F2 (cordierite-mullite) sample fired at 1450 °C was completely cordierite and mullite phases. The synthesized cordierite and cordierite-mullite samples had lower porosity values and coefficient of thermal expansion (CTE) than similar industrial products. The negative CTE value that obtained from the cordierite sample up to 800 °C is favorable for some applications. The considerable results of the synthesized cordierite and cordierite-mullite from this work present cost reduction of the two ceramic bodies production and may help to solve the environmental problems with the use of three waste sources in large scales.</textarea>
<p>全文：</p>
<textarea id="text" name="text" style="width: 80%;height: 80px;"></textarea>
<p>
	<input type="button" value="自动标引测试" onclick="tagging();">
	&nbsp;
	<input type="button" value="清空内容" onclick="clean();">
</p>
<p>初步标引结果集：</p>
<p id="resultText">
</p>

<p>最终标引结果集（复数形式判断/是否正式主题词判断/中英文对照/用-Y关系对应）：</p>
<p id="reResultText">
</p>

<p>最终详细结果集：</p>
<p id="resultDetailText">
</p>
<button onclick="startWork();">新建文件</button>
</body>
</html>