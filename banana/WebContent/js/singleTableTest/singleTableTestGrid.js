	var sm=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
	store = new Ext.data.Store({
		reader:new Ext.data.JsonReader({
			root:"root",//从数据库中读取的总记录数          
			totalProperty: 'total' ,
			fields: [{ name: 'singleTableTestId' }
,{ name: 'testCode' }
,{ name: 'testName' }
,{ name: 'remark' }

			]
		}),
		proxy: new Ext.data.HttpProxy ({ 
			url:ctx+"/auto/singleTableTest/queryDate.do"
		})      
	}); 
	var columns=[sm,
	new Ext.grid.RowNumberer({header:"序号",sortable : true,width:50,align:"center"}),
		 {header: "ID", dataIndex: 'singleTableTestId',hidden:true},
		 {header: "测试编号",dataIndex: 'testCode',sortable:true,renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "测试名称",dataIndex: 'testName',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "备注",dataIndex: 'remark',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}

    ];	
	grid=new Ext.grid.GridPanel({
	 	loadMask: {msg:'加载数据中,请等待......'},//显示等待数据加载（loading）图标
	 	autoScroll:true,
	 	columnLines: true,
	 	loadMask: true,
		displayInfo:true,
	 	border:false,
	 	margins:'2 2 0 0 ',
   		/**viewConfig : { forceFit : true //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。},
		anchor : '100%',*/
	 	cm:new Ext.grid.ColumnModel(columns),
	 	sm:sm,
	 	store:store,
	 	listeners:{  
            'rowdblclick' : function(grid, rowIndex, e){  
                 var rowid = getCheckRowId();
			     var c = rowid.split(",");
				 if(rowid!=""){
					 var win = new singleTableTestEditWindow();
					 win.show();
					 Ext.getCmp("singleTableTestId").setValue(c[0]);
					 loadForm(c[0]);//加载form
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
								url:ctx+"/auto/singleTableTest/update.do",
								params:formPanel.getValue,
								success:function(form,action){
									grid.getStore().load();
									Ext.Msg.alert("系统提示","修改成功");
									win.close();
								},
								failure:function(form,action){
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
             }
        }
	});
	grid.getStore().on({
    	"beforeload": function(store){
    		//往store添加baseParams
            Ext.apply(store.baseParams, { start: 0, limit: pageSize });
    	}
    });
