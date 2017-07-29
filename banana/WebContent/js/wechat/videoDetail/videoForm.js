/**项目预算单管理**/
videoForm = Ext.extend(Ext.form.FormPanel,{
	 id: 'videoForm',
	 layout:'form',
	 labelAlign:'right',
	 enctype: 'multipart/form-data', 
     fileUpload : true,
	 initComponent:function(){
		Ext.applyIf(this,{
			border:false,
			bodyStyle:'padding-left:60;padding-top:10',  
	        defaults : {allowBlank : false,height:30,xtype : 'textfield', msgTarget : 'side'},
	        layout:'form',
	        xtype:'form',
	        enctype: 'multipart/form-data', 
	        fileUpload : true,
	        labelWidth:88,
	        items:[{xtype:'hidden',allowBlank:true,name:'hostId',value:videoDetailsId},
	        	   {xtype:'hidden',allowBlank:true,name:'videoID',value:videoId},
	        	   {xtype:'hidden',name:'aid'},
	        	   {xtype:'hidden',name:'vid'},
	        	   {xtype:'hidden',name:'createPerson'},
	        	   {xtype:'hidden',name:'state'},
	        	   {xtype:'hidden',name:'ctTime'},
	        	   {anchor:'-60', fieldLabel:'<span style="color:red">*</span>视频标题' ,name:'title',id:'title'},
	        	   {anchor:'-60', fieldLabel:'<span style="color:red">*</span>视频链接',name:'videoUrl',id:'videoUrl'},
	        	   {width:300,xtype: 'fileuploadfield', fieldLabel:'<span style="color:red">*</span>封面',name:'imageFile',id:'imageFile',buttonText: '上传封面'},
	        	   {fieldLabel:'<span style="color:red">*</span>作者',width:300,name:'author',id:'author'},
	        	   {fieldLabel:'<span style="color:red">*</span>联系电话',width:300,name:'tel',id:'tel'},
	        	   {anchor:'-60', xtype:'htmleditor',height:280,fieldLabel:'视频介绍',name:'content',id:'content'}
	        ]
		});
	 	videoForm.superclass.initComponent.call(this);
	 }
})