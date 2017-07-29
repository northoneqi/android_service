videoCommen=Ext.extend(videoCommentUi,{
	initComponent: function() {
		videoCommen.superclass.initComponent.call(this);
		
		if(commentTime!=null && commentTime.length>0){
			Ext.getCmp("fabiaopinglun").setValue(commentNode+"【"+commentTime+"】");
			Ext.getCmp("comments").setVisible(false);
			Ext.getCmp("fabiaopinglun").setReadOnly(true);
			//Ext.getCmp("saveButton").setVisible(false);
		}
		
//		 /**评委点评 **/
//		 Ext.getCmp("comments").on("click",function(){
//			var comment=Ext.getCmp('fabiaopinglun').getValue();
//			Ext.getCmp("dpnr").setValue(comment);
//		 });
		Ext.getCmp("comments").on("click",function(){
			var comment=Ext.getCmp('fabiaopinglun').getValue();
			Ext.getCmp("dpnr").setValue(comment);
			var form=Ext.getCmp("dpFormPanel").getForm();
			if(form.isValid()){
				form.submit({
					url:ctx+"/wechat/video/saveVideoComment.do",
					method:'POST',
					waitMsg : '正在保存,请稍等...',
					waitTitle : '系统提示',// 标题
					params:form.getValue,
					success:function(form,action){
							Ext.Msg.alert("系统提示","更新成功");
							Ext.getCmp("comments").setVisible(false);
							Ext.getCmp("fabiaopinglun").setReadOnly(true);
							//Ext.getCmp("saveButton").setVisible(false);
					},
					failure:function(form,action){
						Ext.Msg.alert("系统提示","操作失败！");
					}
				});
			}
		});
		Ext.getCmp("cancleButton").on("click",function(){
			window.location.href=ctx+"/wechat/video/listDetailPage.do";
		}); 
		
	}
});
