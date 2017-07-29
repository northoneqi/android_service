videoAddUi=Ext.extend(Ext.Panel,{
	id:'videoAddUi',
	border:false,
	margins:'1 1 1 1',
	layout:'fit',
	initComponent: function() {
		 videoForm=new videoForm();
		 var title="新建活动";
		 if(viedoId!=null){
			 title="编辑活动";
		 }
		 Ext.applyIf(this,{
			//bodyStyle:'padding-right:80', 
			items:[{
				layout:'fit',
				border:false,
				//frame:true,
				tbar:{height:30,items:['<span style="text-align:center;">' +
											'<img src="'+ctx+'/images/list.png"/>' +
									   '</span>',
									   '<span id="formPanelTitle" style="font-weight:bold;font-size:20px;">'+title+'</span>',
									   				 '->',
													 '-',{text:'保存',iconCls:'saveIcon',id:'saveButton'},
													 '-',{text:'取消',iconCls:'closeIcon',id:'cancleButton'},
													 '-',{text:'预览',iconCls:'application_view_listIcon',id:'printerIcon'},
													 '-']},
				items:[{
					margins:'1 1 1 1',
					//frame:true,
					layout:'form',
					border:false,
					items:videoForm
				}]
			}]
		 });
		videoAddUi.superclass.initComponent.call(this);
	}
});