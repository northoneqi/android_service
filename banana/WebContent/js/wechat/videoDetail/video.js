video=Ext.extend(videoUi,{
	initComponent: function() {
		 video.superclass.initComponent.call(this);
		 //对每一个组件加事件
		 /**左侧数点击事件**/
		 videoTree.on("click",function(node){
			 if(node.leaf){
				 document.getElementById("ss").innerHTML=node.text+"视频信息列表";
				 Ext.getCmp("videoId").setValue(node.id);
				 Ext.getCmp("videoCode").setValue(node.attributes.code);
				 Ext.getCmp("videoState").setValue(node.attributes.state);
				 /**加载视频明细**/
				 var grid=Ext.getCmp("videoGridId");
				 grid.getStore().baseParams={aid:node.id};
				 grid.getStore().load();
			 }
		 });
//		 Ext.getCmp("searchTreeButton").on("click",function(){
//			var key=Ext.getCmp("key").getValue(); 
//			var treePanel=Ext.getCmp("treePanel");
//			treePanel.root.baseParams={key:key};
//			treePanel.root.reload();
//			treePanel.expandAll();
//		 });
		 /**新建活动**/
		 Ext.getCmp("addButton").on("click",function(){
			var videoId=Ext.getCmp("videoId").getValue();
			var videoState=Ext.getCmp("videoState").getValue();
			if(videoId!=null&&videoId!=""){
				location.href=ctx+"/wechat/video/addDetailsPage.do?videoId="+videoId;
			}/*else if(videoState==3){
				Ext.Msg.alert("系统提示","该活动已结束!");
			}*/else{
				Ext.Msg.alert("系统提示","请选择一个活动！");
			} 
		 });
		 /**编辑活动**/
		 Ext.getCmp("editButton").on("click",function(){
			var grid=Ext.getCmp("videoGridId");
			var videoId=Ext.getCmp("videoId").getValue();
			var sm=grid.getSelectionModel();
			if(sm.getSelected()){
				var recs=sm.getSelections(); //把所有选中项放入数组 
				if(recs.length>1){
					Ext.MessageBox.alert("系统提示", "请选择一条活动编辑！"); 
				}else{
					location.href=ctx+"/wechat/video/addDetailsPage.do?videoId="+videoId+"&videoDetailsId="+recs[0].get("vid");
				}
			}else{
				Ext.MessageBox.alert("系统提示", "请选择需要编辑的活动！"); 
			}
		 });
		 /**删除视频事件**/
		 Ext.getCmp("deleteButton").on("click",function(){
			 var grid=Ext.getCmp("videoGridId");
			 var ids=new Array();
			 var sm=grid.getSelectionModel(); 
					if(sm.getSelected()){
						 var recs=sm.getSelections(); // 把所有选中项放入数组 
						 Ext.Msg.confirm("系统提示","确定删除这些视频？",function(btn){
						 if(btn=="yes"){
							var process=createProcess("正在删除,请稍后...");
						 	for(var i=0;i<recs.length;i++){ 
								 ids.push(recs[i].get("vid"));
						 	}
						 	/**异步调用将选中活动状态修改为已结束**/
						 	Ext.Ajax.request({
							 	 url:ctx+'/wechat/video/deleteVideoDetail.do',
							 	 success:function(){
							 		grid.getStore().reload();
							 		process.hide();
							 		Ext.Msg.alert("系统提示","删除成功!");
							 	 },
							 	 failure:function(){
							 		process.hide();
							 		Ext.Msg.alert("系统提示","操作失败!");
							 	 },
							 	 params:{
								 	ids:ids.join(),
								 	state:2
								 }
						 	});
						}
					  },this);
					}else{
   						Ext.MessageBox.alert("系统提示", "请选择需要结束的活动！"); 
					}
		 });
		 /**评价视频**/
		 Ext.getCmp("resultButton").on("click",function(){
				var grid=Ext.getCmp("videoGridId");
				var videoId=Ext.getCmp("videoId").getValue();
				var sm=grid.getSelectionModel();
				if(sm.getSelected()){
					var recs=sm.getSelections(); //把所有选中项放入数组 
					if(recs.length>1){
						Ext.MessageBox.alert("系统提示", "请选择一条活动评价！"); 
					}else{
						location.href=ctx+"/wechat/video/commentPage.do?videoDetailsId="+recs[0].get("vid")+"&zan="+recs[0].get("zan")+"&shi="+recs[0].get("shi")+"&zp="+recs[0].get("zp");
					}
				}else{
					Ext.MessageBox.alert("系统提示", "请选择需要评价的活动！"); 
				}
		});
		 
		 /**双击产生事件**/
		 var grid=Ext.getCmp("videoGridId");
		 grid.on("celldblclick",function(grid, rowIndex, columnIndex, e){
			 var record = grid.getStore().getAt(rowIndex); //Get the Record   
			 location.href=ctx+"/wechat/video/commentPage.do?videoDetailsId="+record.get("vid")+"&zan="+record.get("zan")+"&shi="+record.get("shi")+"&zp="+record.get("zp");
		 });
		 //查询
		 Ext.getCmp("searchDetailButton").on("click",function(){
			 var vid=Ext.getCmp("videoId").getValue();
			 var form=Ext.getCmp("searchFrom").getForm();
			 var title=form.findField("title").getValue();
			 var dzstar=form.findField("dzstar").getValue();
			 var dzend=form.findField("dzend").getValue();
			 var author=form.findField("author").getValue();
			 Ext.getCmp("videoGridId").getStore().baseParams={aid:vid,start: 0, limit: pageSize,title:title,dzend:dzend,dzstar:dzstar,author:author};
			 Ext.getCmp("videoGridId").getStore().load();
		 });
		//重置
		 Ext.getCmp("resetButton").on("click",function(){
			 Ext.getCmp('searchFrom').getForm().reset();
		 });
	}
});
