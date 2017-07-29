function getProduct(){	
var sm2=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
	store2 = new Ext.data.Store({
		reader:new Ext.data.JsonReader({
			root:"root",//从数据库中读取的总记录数          
			totalProperty: 'total' ,
			fields: [{ name: 'providerLinkManId' }
,{ name: 'linkman' }
,{ name: 'position' }
,{ name: 'mobile' }
,{ name: 'telephone' }
,{ name: 'faxNumber' }
,{ name: 'qq' }
,{ name: 'email' }
,{ name: 'remark' }
,{ name: 'providerDocIds' }

			]
		}),
		proxy: new Ext.data.HttpProxy ({ 
			url:ctx+"/supplyChain/providerLinkMan/queryDate.do"
		})      
	}); 
	var columns2=[sm2,
	new Ext.grid.RowNumberer({header:"序号",sortable : true,width:50,align:"center"}),
		 {header: "ID", dataIndex: 'providerLinkManId',hidden:true},
		 {header: "联系人",sortable:true,dataIndex: 'linkman',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "职务",sortable:true,dataIndex: 'position',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "手机",sortable:true,dataIndex: 'mobile',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "联系电话",sortable:true,dataIndex: 'telephone',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "传真号码",sortable:true,dataIndex: 'faxNumber',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "QQ",sortable:true,dataIndex: 'qq',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "邮箱",sortable:true,dataIndex: 'email',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "备注",sortable:true,dataIndex: 'remark',renderer:function(value){
		if(value==null){return "";}else{return '<div ext:qtip='+value+'>'+ value +'</div>';}}}
,{header: "providerDocIds",hidden:true,sortable:true,dataIndex: 'providerDocIds',renderer:function(value){
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
