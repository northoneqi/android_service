videoAdd=Ext.extend(videoAddUi,{
	initComponent: function() {
		 videoAdd.superclass.initComponent.call(this);
		 if(videoDetailsId!=null&&videoDetailsId!=""){
			//需要加载form表单
			var form=Ext.getCmp("videoForm").getForm();
			form.load({
				url:ctx+'/wechat/video/loadDetail.do',
				params:{id:videoDetailsId}
			});
		 }
		 //对每一个组件加事件
		 /**保存事件**/
		 Ext.getCmp("saveButton").on("click",function(){
			var form=Ext.getCmp("videoForm").getForm();
			if(form.isValid()){
				form.submit({
					url:ctx+"/wechat/video/saveVideoDetail.do",
					method:'POST',
					waitMsg : '正在保存,请稍等...',
					waitTitle : '系统提示',// 标题
					params:form.getValue,
					success:function(form,action){
						if(videoDetailsId!=null&&videoDetailsId!=""){
							Ext.Msg.alert("系统提示","更新成功");
							window.location.href=ctx+"/wechat/video/listDetailPage.do";
						}else{
							Ext.Msg.confirm('系统提示','添加成功,是否继续添加？',
								function(btn){
									if(btn=='yes'){
										form.reset();
									}else{       
										window.location.href=ctx+"/wechat/video/listDetailPage.do";
							}},this);
						}
					},
					failure:function(form,action){
						Ext.Msg.alert("系统提示","操作失败！");
					}
				});
			}
		 });
		 /**取消事件**/
		 Ext.getCmp("cancleButton").on("click",function(){
			location.href=ctx+"/wechat/video/listDetailPage.do";
		 });
	}
});
