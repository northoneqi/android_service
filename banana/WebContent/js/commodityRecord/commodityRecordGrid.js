	var sm=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
	store = new Ext.data.Store({
		reader:new Ext.data.JsonReader({
			root:"root",//从数据库中读取的总记录数          
			totalProperty: 'total' ,
			fields: [{ name: 'commodityRecordId' }
,{ name: 'goodsClassify' }
,{ name: 'isNotGift' }
,{ name: 'companySKU' }
,{ name: 'productionPlace' }
,{ name: 'commodityBrand' }
,{ name: 'keyword' }
,{ name: 'productCode' }
,{ name: 'isNotElectronicCode' }
,{ name: 'commodityCode' }
,{ name: 'commodityName' }
,{ name: 'commodityMnemoniccode' }
,{ name: 'format' }
,{ name: 'isNotOnLineSupply' }
,{ name: 'measurementCompany' }
,{ name: 'commodityState' }
,{ name: 'qualityNummberDay' }
,{ name: 'criticalPeriodNummberDay' }
,{ name: 'minimumQuantity' }
,{ name: 'isNotAllowReturnGoods' }
,{ name: 'safetyStock' }
,{ name: 'entryPerson' }
,{ name: 'entryDate' }
,{ name: 'remarks' }

			]
		}),
		proxy: new Ext.data.HttpProxy ({ 
			url:ctx+"/supplyChain/commodityRecord/queryDate.do"
		})      
	}); 
	var columns=[sm,
	new Ext.grid.RowNumberer({header:"序号",sortable : true,width:50,align:"center"}),
		 {header: "ID", dataIndex: 'commodityRecordId',hidden:true},
 {header: "商品名称",sortable:true,dataIndex: 'commodityName',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "商品类别",sortable:true,dataIndex: 'goodsClassify',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "是否是赠品",sortable:true,dataIndex: 'isNotGift',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "厂商货号",sortable:true,dataIndex: 'companySKU',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "产地",sortable:true,dataIndex: 'productionPlace',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "商品品牌",sortable:true,dataIndex: 'commodityBrand',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "关键字",sortable:true,dataIndex: 'keyword',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "商品条码",sortable:true,dataIndex: 'productCode',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "是否电子称码",sortable:true,dataIndex: 'isNotElectronicCode',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "商品编号",sortable:true,dataIndex: 'commodityCode',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "商品助记码",sortable:true,dataIndex: 'commodityMnemoniccode',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "规格",sortable:true,dataIndex: 'format',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "是否线上专供",sortable:true,dataIndex: 'isNotOnLineSupply',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "计量单位",sortable:true,dataIndex: 'measurementCompany',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "商品状态",sortable:true,dataIndex: 'commodityState',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "保质期天数",sortable:true,dataIndex: 'qualityNummberDay',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "临界期天数",sortable:true,dataIndex: 'criticalPeriodNummberDay',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "最少起量",sortable:true,dataIndex: 'minimumQuantity',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "是否允许退货",sortable:true,dataIndex: 'isNotAllowReturnGoods',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "安全库存",sortable:true,dataIndex: 'safetyStock',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "录入人",sortable:true,dataIndex: 'entryPerson',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "录入日期",sortable:true,dataIndex: 'entryDate',renderer:function(value){
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
					 var win = new commodityRecordEditWindow();
					 win.show();
					 Ext.getCmp("commodityRecordId").setValue(c[0]);
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
								url:ctx+"/supplyChain/commodityRecord/update.do",
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
