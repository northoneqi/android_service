/**项目预算单管理**/
videoForm = Ext.extend(Ext.form.FormPanel,{
	 id: 'videoForm',
	 layout:'form',
	 labelAlign:'right',
	 initComponent:function(){
	 	var data=new Array();
	 	for(var i=2001;i<2050;i++){
	 		data.push({text:i+"年",value:i});
	 	}
		Ext.applyIf(this,{
			border:false,
			bodyStyle:'padding-left:60;padding-top:10',  
	        defaults : { allowBlank : false,height:30,xtype : 'textfield', msgTarget : 'side'},
	        layout:'form',
	        xtype:'form',
	        labelWidth:88,
	        items:[{xtype:'hidden',allowBlank:true,name:'id',value:viedoId},
	        	   {xtype:'hidden',allowBlank:true,name:'aid'},
	        	   {anchor:'-60',fieldLabel:'<span style="color:red">*</span>活动标题' ,name:'title',id:'title'},
	        	   {anchor:'-60',fieldLabel:'<span style="color:red">*</span>相关视频链接',name:'videoUil',id:'videoUil'},
	        	   {width:280,fieldLabel:'<span style="color:red">*</span>活动开始时间',xtype:'datefield',format:'Y-m-d',name:'bgTime',id:'bgTime'},
	        	   {width:280,fieldLabel:'<span style="color:red">*</span>活动结束时间',xtype:'datefield',format:'Y-m-d',name:'edTime',id:'edTime'},
	        	   {width:280,xtype:'combo',fieldLabel:'<span style="color:red">*</span>年度',name:'year',id:'year',displayField:'text',valueField:'value',triggerAction:'all',mode:"local",store:new Ext.data.Store({reader:new Ext.data.JsonReader({fields:[{name:"text"},{name:"value"}]}),data : data})},
	        	   {anchor:'-60',xtype:'htmleditor',height:260,fieldLabel:'内容结束',name:'content',id:'content'}
	        ]
		});
	 	videoForm.superclass.initComponent.call(this);
	 }
})