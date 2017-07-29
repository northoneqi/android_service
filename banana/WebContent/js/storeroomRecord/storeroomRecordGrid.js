	var sm=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
	store = new Ext.data.Store({
		reader:new Ext.data.JsonReader({
			root:"root",//从数据库中读取的总记录数          
			totalProperty: 'total' ,
			fields: [{ name: 'storeroomRecordId' }
,{ name: 'storeroomCode' }
,{ name: 'storeroomName' }
,{ name: 'storeroomArea' }
,{ name: 'storeroomlongWidth' }
,{ name: 'storeroomAreaCode' }
,{ name: 'storeroomAreaName' }
,{ name: 'storeroomArealongWidth' }
,{ name: 'goodsPlaceCode' }
,{ name: 'goodsPlaceName' }
,{ name: 'goodsPlacelongWidth' }
,{ name: 'remarks' }

			]
		}),
		proxy: new Ext.data.HttpProxy ({ 
			url:ctx+"/supplyChain/storeroomRecord/queryDate.do"
		})      
	}); 
	var columns=[sm,
	new Ext.grid.RowNumberer({header:"序号",sortable : true,width:50,align:"center"}),
		 {header: "ID", dataIndex: 'storeroomRecordId',hidden:true},
		 {header: "库房编号",sortable:true,dataIndex: 'storeroomCode',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "库房名称",sortable:true,dataIndex: 'storeroomName',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "库房面积",sortable:true,dataIndex: 'storeroomArea',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "库房长宽高",sortable:true,dataIndex: 'storeroomlongWidth',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "库区编号",sortable:true,dataIndex: 'storeroomAreaCode',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "库区名称",sortable:true,dataIndex: 'storeroomAreaName',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "库区长宽高",sortable:true,dataIndex: 'storeroomArealongWidth',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "货位编号",sortable:true,dataIndex: 'goodsPlaceCode',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "货位名称",sortable:true,dataIndex: 'goodsPlaceName',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "货位长宽高",sortable:true,dataIndex: 'goodsPlacelongWidth',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "备注",sortable:true,dataIndex: 'remarks',renderer:function(value){
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
					 var win = new storeroomRecordEditWindow();
					 win.show();
					 Ext.getCmp("storeroomRecordId").setValue(c[0]);
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
								url:ctx+"/supplyChain/storeroomRecord/update.do",
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
