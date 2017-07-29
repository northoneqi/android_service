/**
 * 设置Cookie
 * 
 * @param {} name
 * @param {} value
 */
function setCookie(name, value, minuts) {
	var argv = setCookie.arguments;
	var argc = setCookie.arguments.length;
    var expiration = new Date((new Date()).getTime() + minuts * 60000 * 60);
	document.cookie = name
			+ "="
			+ escape(value)
			+ "; expires=" + expiration
					.toGMTString();
}

/**
 * 获取Cookie
 * 
 * @param {} Name
 * @return {}
 */
function getCookie(Name) {
	var search = Name + "="
	if (document.cookie.length > 0) {
		offset = document.cookie.indexOf(search)
		if (offset != -1) {
			offset += search.length
			end = document.cookie.indexOf(";", offset)
			if (end == -1)
				end = document.cookie.length
			return unescape(document.cookie.substring(offset, end))
		} else
			return ""
	}
}

/**
 * 从缓存中清除Cookie
 * 
 * @param {} name
 */
function clearCookie(name) {
	var expdate = new Date();
	expdate.setTime(expdate.getTime() - (86400 * 1000 * 1));
	setCookie(name, "", expdate);
}


function jsonToString(o){
    return JSON.stringify(o);
}

/**
*将json数据装换成form参数格式
*/
function jsonToParams(json){
	var t = "";
	for(x in json){
		t += x+"="+json[x]+"&";
	}
	
	if(t.length > 0){
		t = t.substr(0,t.length-1);
	}
	
	return t;
}


//打印预览
function printReport(ids, jsapName,condition){
	if(ids==''){
		 Ext.Msg.alert('警告', '请选择您要打印的单据!');
         return;
	}
	var url=('report/applet/toPrintPage.do?name='+jsapName+'&number='+ids);
	if(!Ext.isEmpty(condition)){
		url += condition;
	}
	var printWin = new top.Ext.Window({
		title : '打印预览',
		width : '100%',
		height:560,
		resizable:false,
		modal:true,
		maximized: true,
		layout : 'fit',
		maximizable:true,
		iconCls:'printer',
		plain : true,
		closeAction:'hide',
		html:'<iframe width="100%" align="center" height="100%" id="win" name="win" frameborder="0" scrolling="no" src="'+url+'"></iframe>',
        bodyStyle:'padding:3px;',
        buttonAlign:'right',
		buttons : [{
			text : '取消打印',
			handler:function(){
				printWin.hide();
			}
		},'->' ]
	}).show();
}