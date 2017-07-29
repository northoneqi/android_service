function getProduct(){	
var sm2=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
	store2 = new Ext.data.Store({
		reader:new Ext.data.JsonReader({
			root:"root",//从数据库中读取的总记录数          
			totalProperty: 'total' ,
			fields: [{ name: 'contractProductDetailId' }
,{ name: 'productName' }
,{ name: 'productCode' }
,{ name: 'specifications' }
,{ name: 'unit' }
,{ name: 'buyPrice' }
,{ name: 'quantity' }
,{ name: 'subtotal' }
,{ name: 'remark' }
,{ name: 'providerContractIds' }

			]
		}),
		proxy: new Ext.data.HttpProxy ({ 
			url:ctx+"/supplyChain/contractProductDetail/queryDate.do"
		})      
	}); 
	var columns2=[sm2,
	new Ext.grid.RowNumberer({header:"序号",sortable : true,width:50,align:"center"}),
		 {header: "ID", dataIndex: 'contractProductDetailId',hidden:true},
		 {header: "商品名称",sortable:true,dataIndex: 'productName',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "商品编号",sortable:true,dataIndex: 'productCode',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "规格",sortable:true,dataIndex: 'specifications',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "计量单位",sortable:true,dataIndex: 'unit',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "单价",sortable:true,dataIndex: 'buyPrice',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "数量",sortable:true,dataIndex: 'quantity',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "金额",sortable:true,dataIndex: 'subtotal',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "备注",sortable:true,dataIndex: 'remark',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "providerContractIds",hidden:true,sortable:true,dataIndex: 'providerContractIds',renderer:function(value){
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
