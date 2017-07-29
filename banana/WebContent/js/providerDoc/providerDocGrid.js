	var sm=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
	store = new Ext.data.Store({
		reader:new Ext.data.JsonReader({
			root:"root",//从数据库中读取的总记录数          
			totalProperty: 'total' ,
			fields: [{ name: 'providerDocId' }
,{ name: 'cooperationType' }
,{ name: 'theproviderCode' }
,{ name: 'theOriginalCode' }
,{ name: 'providerName' }
,{ name: 'providerMnemonicCode' }
,{ name: 'theScopeOfBusiness' }
,{ name: 'providerRegisteredAddress' }
,{ name: 'theRegisteredCapitalOf' }
,{ name: 'theproviderManagement' }
,{ name: 'theLegalRepresentative' }
,{ name: 'theEnterpriseWebsite' }
,{ name: 'tariff' }
,{ name: 'theOpeningBank' }
,{ name: 'account' }
,{ name: 'contactTelephoneNumber' }
,{ name: 'theFaxNumber' }
,{ name: 'companyMailbox' }
,{ name: 'cooperationStartDate' }
,{ name: 'state' }
,{ name: 'saveName' }
,{ name: 'createDate' }
,{ name: 'remarks' }

			]
		}),
		proxy: new Ext.data.HttpProxy ({ 
			url:ctx+"/supplyChain/providerDoc/queryDate.do"
		})      
	}); 
	var columns=[sm,
	new Ext.grid.RowNumberer({header:"序号",sortable : true,width:50,align:"center"}),
		 {header: "ID", dataIndex: 'providerDocId',hidden:true},
		 {header: "合作类型",sortable:true,dataIndex: 'cooperationType',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "供应商编码",sortable:true,dataIndex: 'theproviderCode',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "原编码",sortable:true,dataIndex: 'theOriginalCode',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "供应商名称",sortable:true,dataIndex: 'providerName',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "供应商助记码",sortable:true,dataIndex: 'providerMnemonicCode',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "经营范围",sortable:true,dataIndex: 'theScopeOfBusiness',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "供应商注册地址",sortable:true,dataIndex: 'providerRegisteredAddress',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "注册资本",sortable:true,dataIndex: 'theRegisteredCapitalOf',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "供应商经营地址",sortable:true,dataIndex: 'theproviderManagement',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "法人代表",sortable:true,dataIndex: 'theLegalRepresentative',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "企业网站",sortable:true,dataIndex: 'theEnterpriseWebsite',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "税号",sortable:true,dataIndex: 'tariff',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "开户行",sortable:true,dataIndex: 'theOpeningBank',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "开户账号",sortable:true,dataIndex: 'account',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "联系电话",sortable:true,dataIndex: 'contactTelephoneNumber',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "传真号码",sortable:true,dataIndex: 'theFaxNumber',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "公司邮箱",sortable:true,dataIndex: 'companyMailbox',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "合作开始日期",sortable:true,dataIndex: 'cooperationStartDate',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "状态",sortable:true,dataIndex: 'state',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "填单人",sortable:true,dataIndex: 'saveName',renderer:function(value){
	if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "填单时间",sortable:true,dataIndex: 'createDate',renderer:function(value){
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
				     grid2 =getProduct();
					 var win = new providerDocEditWindow();
					 win.show();
					 Ext.getCmp("providerDocId").setValue(c[0]);
					 loadForm(c[0]);//加载form
					 grid2.getStore().baseParams={paraentId:c[0]};
					 grid2.getStore().load();
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
								url:ctx+"/supplyChain/providerDoc/update.do",
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
