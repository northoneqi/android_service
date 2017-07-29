Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
Ext.QuickTips.init();
genCodeLog=Ext.extend(genCodeLogUi,{
	initComponent: function() {
		genCodeLog.superclass.initComponent.call(this);
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
			 var win = new genCodeLogEditWindow();
			 win.show();
			 /**右移按钮事件处理**/
			 var rightButton=Ext.getCmp("rightButton");
			 rightButton.on("click",function(){
				 var formPanel=Ext.getCmp("saveFormPanel").getForm();
				 var m = formPanel.findField('lmultiselect');
				 var value=m.getValue();
				 var store=m.store;
				 var rm = formPanel.findField('rmultiselect');
				 var rstore=rm.store;
				 store.each(function(record) {  
					 //alert(value+"-->"+record.data.value+"-->"+value.indexOf(record.data.value));
					 //if(value.indexOf(record.data.value)>=0){
					 if(value == record.data.value){
						 rstore.add(record);
						 store.remove(record);
					 }
				 }); 
			 });
			 /**全部右移按钮事件处理**/
			 var allRightButton=Ext.getCmp("allRightButton");
			 allRightButton.on("click",function(){
				 var formPanel=Ext.getCmp("saveFormPanel").getForm();
				 var m = formPanel.findField('lmultiselect');
				 var store=m.store;
				 var rm = formPanel.findField('rmultiselect');
				 var rstore=rm.store;
				 store.each(function(record) {  
					 rstore.add(record);
					 store.remove(record);
				 }); 
			 });
			 /**左移按钮事件处理**/
			 var leftButton=Ext.getCmp("leftButton");
			 leftButton.on("click",function(){
				 var formPanel=Ext.getCmp("saveFormPanel").getForm();
				 var m = formPanel.findField('rmultiselect');
				 var value=m.getValue();
				 var store=m.store;
				 var rm = formPanel.findField('lmultiselect');
				 var rstore=rm.store;
				 store.each(function(record) {  
					 //alert(value+"-->"+record.data.text+"-->"+value.indexOf(record.data.value));
					 //if(value.indexOf(record.data.text)>=0){
					 if(value == record.data.text){
						 rstore.add(record);
						 store.remove(record);
					 }
				 }); 
			 });
			 /**全部左移按钮事件处理**/
			 var allLeftButton=Ext.getCmp("allLeftButton");
			 allLeftButton.on("click",function(){
				 var formPanel=Ext.getCmp("saveFormPanel").getForm();
				 var m = formPanel.findField('rmultiselect');
				 var store=m.store;
				 var rm = formPanel.findField('lmultiselect');
				 var rstore=rm.store;
				 store.each(function(record) {  
					 rstore.add(record);
					 store.remove(record);
				 }); 
			 });
			 var saveButtion=Ext.getCmp("saveButton");
			 if(saveButtion){
				 /** 保存按钮点击事件 **/
				 saveButtion.on("click",function(){
					var formPanel=Ext.getCmp("saveFormPanel").getForm();
					var m = formPanel.findField('rmultiselect');
					var store=m.store;
					var tables = "";
					var tablesDes = "";
					store.each(function(record) {  
						tables+="'"+record.data.value+"',";
						tablesDes+=record.data.text+",";
					});
					if(tables.length>0){
						tables=tables.substring(0, tables.length-1);
						tablesDes=tablesDes.substring(0, tablesDes.length-1);
						if(formPanel.isValid()){
							Ext.Msg.confirm('系统提示', '系统将自动生成右侧您的选择，请确定?', function (opt) {
				                 if (opt == 'yes') {
				                	 formPanel.submit({
										waitMsg : '系统正在自动生成功能模块,请稍等...',
										waitTitle : '系统提示',// 标题
										method:'POST',
										url:ctx+"/auto/genCodeLog/save.do",
										params:{remark:tablesDes,operPerson:tables},
										success:function(form,action){
											grid.getStore().load();
											Ext.Msg.alert("系统提示","系统自动生成完成");
											win.close();
										},
										failure:function(form,action){
											Ext.Msg.alert("系统提示","系统自动生成失败");
										}
									});
				                }
				            });
						}
					}else{
						Ext.Msg.alert("系统提示","请从左侧选择您要生成的表");
					}
				 });
			 }
			 /**取得取消按钮 定义返回事件**/
			 var cencelButton = Ext.getCmp("cencelButton");
				cencelButton.on("click",function(){
					win.close();
			});
		});
	}
});
//加载表单数据
function loadForm(id) {
	var formPanel = Ext.getCmp("saveFormPanel").getForm();
	formPanel.load( {
		waitMsg : '正在加载数据请稍后',//提示信息
		waitTitle : '提示',//标题
		url:ctx+"/auto/genCodeLog/load.do",
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
			 rowId += recs[i].get("genCodeLogId");
			 if(i+1<recs.length)rowId +=",";
		 }
	 }
	 return rowId;
}
