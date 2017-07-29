function getProduct(){	
var sm2=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
	store2 = new Ext.data.Store({
		reader:new Ext.data.JsonReader({
			root:"root",//从数据库中读取的总记录数          
			totalProperty: 'total' ,
			fields: [{ name: 'commodityChangePriceTwoId' }
,{ name: 'goodsClassify' }
,{ name: 'commercialBarCode' }
,{ name: 'itemNumber' }
,{ name: 'nameCommodity' }
,{ name: 'specifications' }
,{ name: 'company' }
,{ name: 'theOriginalSalesChannels' }
,{ name: 'theSalesChannel' }
,{ name: 'theOriginalPurchasePrice' }
,{ name: 'theOriginalSalesPrice' }
,{ name: 'nowThePurchasePrice' }
,{ name: 'nowTheSalesPrice' }
,{ name: 'remarks' }
,{ name: 'commodityChangePriceIds' }

			]
		}),
		proxy: new Ext.data.HttpProxy ({ 
			url:ctx+"/supplyChain/commodityChangePriceTwo/queryDate.do"
		})      
	}); 
	var columns2=[sm2,
	new Ext.grid.RowNumberer({header:"序号",sortable : true,width:50,align:"center"}),
		 {header: "ID", dataIndex: 'commodityChangePriceTwoId',hidden:true},
		 {header: "商品类别",sortable:true,dataIndex: 'goodsClassify',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "商品条码",sortable:true,dataIndex: 'commercialBarCode',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "商品编号",sortable:true,dataIndex: 'itemNumber',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "商品名称",sortable:true,dataIndex: 'nameCommodity',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "规格",sortable:true,dataIndex: 'specifications',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "单位",sortable:true,dataIndex: 'company',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "原销售渠道",sortable:true,dataIndex: 'theOriginalSalesChannels',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "现销售渠道",sortable:true,dataIndex: 'theSalesChannel',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "原进货单价",sortable:true,dataIndex: 'theOriginalPurchasePrice',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "原销售单价",sortable:true,dataIndex: 'theOriginalSalesPrice',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "现进货单价",sortable:true,dataIndex: 'nowThePurchasePrice',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "现销售单价",sortable:true,dataIndex: 'nowTheSalesPrice',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "备注",sortable:true,dataIndex: 'remarks',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "commodityChangePriceIds",hidden:true,sortable:true,dataIndex: 'commodityChangePriceIds',renderer:function(value){
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
