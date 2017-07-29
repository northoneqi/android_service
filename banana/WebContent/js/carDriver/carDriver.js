Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
Ext.QuickTips.init();
carDriver=Ext.extend(carDriverUi,{
	initComponent: function() {
		carDriver.superclass.initComponent.call(this);
		var form=Ext.getCmp("queryForm").getForm();
		/** 重置按钮点击事件 **/
		Ext.getCmp("resetButton").on("click",function(){
			form.reset();
		});
		/**查询按钮点击事件**/
		Ext.getCmp("queryButton").on("click",function(){
			var formPanel = Ext.getCmp("queryForm").getForm();
			//console.log(formPanel.getFieldValues());
			grid.getStore().baseParams=formPanel.getFieldValues();
			grid.getStore().load();
		});
		/** 增加按钮点击事件 **/
		Ext.getCmp("add_button").on("click",function(){
			 var win = new carDriverEditWindow();
			 win.show();
			 var saveButtion=Ext.getCmp("saveButton");
			 if(saveButtion){
				 /** 保存按钮点击事件 **/
				 saveButtion.on("click",function(){
					var formPanel=Ext.getCmp("saveFormPanel").getForm();
					//alert(formPanel.getFieldValues() )
					if(formPanel.isValid()){
						formPanel.submit({
							waitMsg : '正在保存数据,请稍等...',
							waitTitle : '系统提示',// 标题
							method:'POST',
							url:ctx+"/supplyChain/carDriver/save.do",
							params:formPanel.getValue,
							success:function(form,action){
								grid.getStore().load();
								Ext.Msg.alert("系统提示","添加成功");
								win.close();
							},
							failure:function(form,action){
								Ext.Msg.alert("系统提示","添加失败");
							}
						});
					}
				 });
			 }
			 /**取得取消按钮 定义返回事件**/
			 var cencelButton = Ext.getCmp("cencelButton");
				cencelButton.on("click",function(){
					win.close();
			});
		});
		/** 修改按钮点击事件 **/
		Ext.getCmp("edit_button").on("click",function(){
			 var win = new carDriverEditWindow();
			 var rowid = getCheckRowId();
			 var c = rowid.split(",");
			 if(rowid!="" && c.length == 1){
				 win.show();
				 Ext.getCmp("carDriverId").setValue(rowid);
				 loadForm(rowid);//加载form
			 }else if(rowid!=""){
				 Ext.Msg.alert("系统提示","请选择您要修改的记录");
			 }else {
				 Ext.Msg.alert("系统提示","一次只能修改一条记录");
			 }
			 var saveButtion=Ext.getCmp("saveButton");
			 if(saveButtion){
				 saveButtion.on("click",function(){
					var formPanel=Ext.getCmp("saveFormPanel").getForm();
					//alert(formPanel.getFieldValues() )
					if(formPanel.isValid()){
						formPanel.submit({
							waitMsg : '正在修改数据,请稍等...',
							waitTitle : '系统提示',// 标题
							method:'POST',
							url:ctx+"/supplyChain/carDriver/update.do",
							params:formPanel.getValue,
							success:function(form,action){
								grid.getStore().load();
								Ext.Msg.alert("系统提示","修改成功");
								win.close();
							},
							failure:function(form,action){
								MsgTip.msg('系统提示', '修改失败',true);
								Ext.Msg.alert("系统提示","修改失败");
							}
						});
					}
				 });
			 }
			 /**取得取消按钮 定义返回事件**/
			 var cencelButton = Ext.getCmp("cencelButton");
				cencelButton.on("click",function(){
					win.close();
			});
		});
		/**删除按钮点击事件**/
		Ext.getCmp("delete_button").on("click",function(){
			 var rowid = getCheckRowId();
			 if(rowid!=""){
				 Ext.Msg.confirm('系统提示', '您确定要删除吗?', function (opt) {
	                 if (opt == 'yes') {
	                    Ext.Ajax.request({
							 url:ctx+"/supplyChain/carDriver/delete.do",
							 params:{ids:rowid},
							 success:function(response){
								 grid.getStore().load();
								 Ext.Msg.alert("系统提示","删除成功");
							 }
						});
	                }
	             });
			 }else {
				 Ext.Msg.alert('系统提示', '请选择您要删除的记录');
			 }
		});
	}
});
//加载表单数据
function loadForm(id) {
	var formPanel = Ext.getCmp("saveFormPanel").getForm();
	formPanel.load( {
		waitMsg : '正在加载数据请稍后',//提示信息
		waitTitle : '提示',//标题
		url:ctx+"/supplyChain/carDriver/load.do",
		params:formPanel.getFieldValues(),
		method : 'POST',//请求方式
		failure : function(form, action) {//加载失败的处理函数
			Ext.Msg.alert('系统提示', '数据加载失败');
		}
	});
}
//获得Grid选择行的主键ID
function getCheckRowId(){
	 var sm=grid.getSelectionModel(); 
	 var rowId = "";
	 if(sm.getSelected()){
		 var recs=sm.getSelections(); // 把所有选中项放入数组 
		 for(var i = 0 ; i < recs.length ; i++){
			 rowId += recs[i].get("carDriverId");
			 if(i+1<recs.length)rowId +=",";
		 }
	 }
	 return rowId;
}
