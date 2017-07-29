	var sm=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
	store = new Ext.data.Store({
		reader:new Ext.data.JsonReader({
			root:"root",//从数据库中读取的总记录数          
			totalProperty: 'total' ,
			fields: [{ name: 'sysAllTablesId' }
			,{ name: 'tableName' }
			,{ name: 'tableDesc' }
			,{ name: 'referencesShip' }
			,{ name: 'referencesTab' }
			,{ name: 'parentId' }
			,{ name: 'fieldCode' }
			,{ name: 'fieldName' }
			,{ name: 'fieldType' }
			,{ name: 'properyType' }
			,{ name: 'extType' }
			,{ name: 'isQuery' }
			,{ name: 'allowNull' }
			]
		}),
		proxy: new Ext.data.HttpProxy ({ 
			url:ctx+"/auto/sysAllTables/queryDate.do"
		})      
	}); 
	var columns=[sm,
	new Ext.grid.RowNumberer({header:"序号",sortable : true,width:40,align:"center"}),
		 {header: "ID", dataIndex: 'sysAllTablesId',hidden:true},
		 {header: "表名称英文",dataIndex: 'tableName',width:110,renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
		,{header: "表名称中文",dataIndex: 'tableDesc',renderer:function(value){
				if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
		,{header: "关联关系",dataIndex: 'referencesShip',width:60,renderer:function(value){
				if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
		,{header: "关联表",dataIndex: 'referencesTab',width:120,renderer:function(value){
				if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
		,{header: "父ID",dataIndex: 'parentId',hidden:true,width:40,renderer:function(value){
				if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
		,{header: "字段名称英文",dataIndex: 'fieldCode',width:120,renderer:function(value){
				if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
		,{header: "字段名称中文",dataIndex: 'fieldName',width:120,renderer:function(value){
				if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
		,{header: "字段类型",dataIndex: 'fieldType',renderer:function(value){
				if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
		,{header: "实体属性类型",dataIndex: 'properyType',width:81,renderer:function(value){
				if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
		,{header: "Ext表单类型",dataIndex: 'extType',renderer:function(value){
				if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
		,{header: "是否查询",dataIndex: 'isQuery',width:60,renderer:function(value){
				if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
		,{header: "是否为空",dataIndex: 'allowNull',width:60,renderer:function(value){
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
					 var win = new sysAllTablesEditWindow();
					 win.show();
					 Ext.getCmp("sysAllTablesId").setValue(c[0]);
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
								url:ctx+"/auto/sysAllTables/update.do",
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
	 	,
        viewConfig : { 
            getRowClass : function(record,rowIndex,rowParams,store){   
                //为表的时候将背景颜色设置成yellow
            	if(record.data.tableName!=""&&record.data.tableName!='null'){
            		return 'my_row_bgcolor';
            	}
            }   
        }
	});
	grid.getStore().on({
    	"beforeload": function(store){
    		//往store添加baseParams
            Ext.apply(store.baseParams, { start: 0, limit: pageSize });
    	}
    });