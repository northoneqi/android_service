video=Ext.extend(videoUi,{
	initComponent: function() {
		 video.superclass.initComponent.call(this);
		 //对每一个组件加事件
		 /**新建活动**/
		 Ext.getCmp("addButton").on("click",function(){
			location.href=ctx+"/wechat/video/addPage.do";
		 });
		 /**编辑活动**/
		 Ext.getCmp("editButton").on("click",function(){
			
			var grid=Ext.getCmp("videoGridId");
			var sm=grid.getSelectionModel();
			if(sm.getSelected()){
				var recs=sm.getSelections(); // 把所有选中项放入数组 
				var state=recs[0].get("state");
				
				if(recs.length>1){
					Ext.MessageBox.alert("系统提示", "请选择一条活动编辑！"); 
				}else{
					location.href=ctx+"/wechat/video/addPage.do?viedoId="+recs[0].get("aid");
				}
			}else{
				Ext.MessageBox.alert("系统提示", "请选择需要编辑的活动！"); 
			}
		 });
		 /**结束活动事件**/
		 Ext.getCmp("stopButton").on("click",function(){
			 var grid=Ext.getCmp("videoGridId");
			 var ids=new Array();
			 var sm=grid.getSelectionModel(); 
					if(sm.getSelected()){
						 var process=createProcess("正在结束,请稍后...");
						 var recs=sm.getSelections(); // 把所有选中项放入数组 
						 if(recs.length>1){
							 Ext.MessageBox.alert("系统提示", "请选择一条活动！"); 
						 }else {
						 Ext.Msg.confirm("系统提示","确定指定当前活动？",function(btn){
						 if(btn=="yes"){
							//process.open();
						 	for(var i=0;i<recs.length;i++){ 
								 ids.push(recs[i].get("aid"));
						 	}
						 	/**异步调用将选中活动状态修改为已结束**/
						 	Ext.Ajax.request({
							 	 url:ctx+'/wechat/video/updateVideo.do',
							 	 success:function(){
							 		grid.getStore().reload();
							 		process.hide();
							 		Ext.Msg.alert("系统提示","所选活动已更新为当前进行!");
							 	 },
							 	 failure:function(){
							 		process.hide();
							 		Ext.Msg.alert("系统提示","操作失败!");
							 	 },
							 	 params:{
								 	ids:ids.join(),
								 	activeflag:'1'
								 }
						 	});
						}
					  },this);
						 }
					}else{
   						Ext.MessageBox.alert("系统提示", "请选择需要指定的活动！"); 
					}
		 });
		 
		 /**双击产生事件**/
		 var grid=Ext.getCmp("videoGridId");
		 grid.on("celldblclick",function(grid, rowIndex, columnIndex, e){
			 var record = grid.getStore().getAt(rowIndex); //Get the Record   
			 var form=new videoView();
			 form.load({
					url:ctx+'/wechat/video/load.do',
					params:{id:record.get("aid")}
			 });
			 var window=new Ext.Window({
				 layout:'border',
				 width:900,
				 height:450,
				 modal:true, 
				 frame:true,
				 boder:false,
				 items:[{
					 region:'west',
					 width:'45%',
					 layout:'fit',
					 boder:false,
					 items:form
				 },{
					 region:'center',
					 boder:false,
					 html:'<object id="video" classid="clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA"   width="500">'+'<param name="AudioStream" value="-1">'+
					      '<param name="AudioSize" value="-1">'+'<param name="AutoStart" value="1">'+'<param name="FileName" value="/white.avi">'+'<embed width="500" height="450"  filename="/white.avi" src="'+record.get("videoUil")+'">'+'</embed>'+'</object>'
				 }]
			 });
			 window.show();
		 });
		 /**查询实现**/
		 Ext.getCmp("searchButton").on("click",function(){
			 var form=Ext.getCmp("searchFrom").getForm();
			 var title=form.findField("title").getValue();
			 var startTime=form.findField("startTime").getValue();
			 var endTime=form.findField("endTime").getValue();
			 var state=form.findField("state").getValue();
			 Ext.getCmp("videoGridId").getStore().baseParams={start: 0, limit: pageSize,title:title,startTime:startTime,endTime:endTime,state:state};
			 Ext.getCmp("videoGridId").getStore().load();
		 });
		//重置
		 Ext.getCmp("resetButton").on("click",function(){
			 Ext.getCmp('searchFrom').getForm().reset();
		 });
	}
});
