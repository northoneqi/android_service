/**项目预算单管理**/
videoView = Ext.extend(Ext.form.FormPanel,{
	 id: 'videoView',
	 layout:'form',
	 labelAlign:'right',
	 initComponent:function(){
		Ext.applyIf(this,{
			border:false,
			bodyStyle:'padding-top:10',  
	        defaults : { readOnly: true,height:30,xtype : 'textfield', msgTarget : 'side'},
	        layout:'form',
	        xtype:'form',
	        labelWidth:88,
	        items:[{anchor:'-60',fieldLabel:'活动标题' ,name:'title',id:'title'},
	        	   {anchor:'-60',fieldLabel:'相关视频链接',name:'videoUil',id:'videoUil'},
	        	   {anchor:'-60',fieldLabel:'活动开始时间',name:'bgTime',id:'bgTime'},
	        	   {anchor:'-60',fieldLabel:'活动结束时间',name:'edTime',id:'edTime'},
	        	   {anchor:'-60',fieldLabel:'年度',name:'year',id:'year'},
	        	   {anchor:'-60',xtype:'htmleditor',height:200,fieldLabel:'内容结束',name:'content',id:'content'}
	        ]
		});
		videoView.superclass.initComponent.call(this);
	 }
})