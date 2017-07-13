<html>
<head></head>
<body>
<h3>Simple Calculator</h3>
<br/>
<style>
#calc{width:300px;height:250px;}
.btn{width:100%;height:40px;font-size:20px;background-color: selver}
</style>
<form Name="calc">
<table id="calc" border=2>
<tr>
<td colspan=5><input class="btn" id="btnn" name="display" onkeypress="return event.charCode >= 48 && event.charCode <= 57" type="text"></td>
<td style="display:none"><input name="M" type="number"></td>
</tr>
<tr>
<td><input class="btn" type=button value="" OnClick=""></td>
<td><input class="btn" type=button value="0"  OnClick="calc.display.value+='0'"></td>
<td><input class="btn" type=button value="1" OnClick="calc.display.value+='1'"></td>
<td><input class="btn" type=button value="2" OnClick="calc.display.value+='2'"></td>
<td><input class="btn" type=button value="+" OnClick="calc.display.value+='+'"></td>
</tr>
<tr>
<td><input class="btn" type=button value=""  OnClick=""></td>
<td><input class="btn" type=button value="3" OnClick="calc.display.value+='3'"></td>
<td><input class="btn" type=button value="4" OnClick="calc.display.value+='4'"></td>
<td><input class="btn" type=button value="5" OnClick="calc.display.value+='5'"></td>
<td><input class="btn" type=button value="-" OnClick="calc.display.value+='-'"></td>
</tr>
<tr>
<td><input class="btn" type=button value="" OnClick=""></td>
<td><input class="btn" type=button value="6" OnClick="calc.display.value+='6'"></td>
<td><input class="btn" type=button value="7" OnClick="calc.display.value+='7'"></td>
<td><input class="btn" type=button value="8" OnClick="calc.display.value+='8'"></td>
<td><input class="btn" type=button value="x" OnClick="calc.display.value+='*'"></td>
</tr>
<tr>
<td><input class="btn" type=button value="" OnClick=""></td>
<td><input class="btn" type=button value="9" OnClick="calc.display.value+='9'"></td>
<td><input class="btn" type=button value="" OnClick="">
</td>
<td><input class="btn" type=button value="=" OnClick="doSomething()"></td>
<td><input class="btn" type=button value="/" OnClick="calc.display.value+='/'"></td>
</tr>
<tr>
<td><input class="btn" type=button value="" OnClick=""></td>
<td><input class="btn" type=button value="." OnClick="calc.display.value+='.'"></td>
<td><input class="btn" type=button value="" OnClick=""></td>
<td><input class="btn" type=button value="root" OnClick="calc.display.value+='√'"></td>
<td><input class="btn" type=button value="C" OnClick="calc.display.value=''"></td>
</tr>
</table>
</form>

<script type="text/javascript">

function doSomething(){
	
var url = "http://localhost:8080/calc/webapi/mycalc/qus/";
myReq = new XMLHttpRequest();

var myParam = document.getElementById("btnn").value;
myReq.onreadystatechange = function() {
	if(this.readyState == 4 && this.status == 200){
		//calc.display.value= myReq.responseText;
		document.getElementById("btnn").value = myReq.responseText;
		
	}
};
myReq.open("GET",url+myParam,true);
myReq.send();
}
</script>
</body>
</html>