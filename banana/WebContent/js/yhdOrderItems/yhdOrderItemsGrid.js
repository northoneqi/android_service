function getProduct(){	
var sm2=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
	store2 = new Ext.data.Store({
		reader:new Ext.data.JsonReader({
			root:"root",//从数据库中读取的总记录数          
			totalProperty: 'total' ,
			fields: [{ name: 'yhdOrderItemsId' }
,{ name: 'jldw' }
,{ name: 'pdName' }
,{ name: 'yhdOrderIds' }

			]
		}),
		proxy: new Ext.data.HttpProxy ({ 
			url:ctx+"/auto/yhdOrderItems/queryDate.do"
		})      
	}); 
	var columns2=[sm2,
	new Ext.grid.RowNumberer({header:"序号",sortable : true,width:50,align:"center"}),
		 {header: "ID", dataIndex: 'yhdOrderItemsId',hidden:true},
		 {header: "计量单位",sortable:true,dataIndex: 'jldw',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "品种名称",sortable:true,dataIndex: 'pdName',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "yhdOrderIds",hidden:true,sortable:true,dataIndex: 'yhdOrderIds',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}

    ];	
	grid2=new Ext.grid.GridPanel({
	 	loadMask: {msg:'加载数据中,请等待......'},//显示等待数据加载（loading）图标
	 	autoScroll:true,
	 	columnLines: true,
	 	loadMask: true,
		displayInfo:true,
		id:'productGrid',
		height : 230,   
	 	border:false,
	 	margins:'2 2 0 0 ',
	 	cm:new Ext.grid.ColumnModel(columns2),
	 	sm:sm2,
	 	store:store2
	});
return grid2;	
}
