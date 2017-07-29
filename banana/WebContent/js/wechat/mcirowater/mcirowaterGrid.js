mcirowaterGrid=Ext.extend(Ext.grid.GridPanel,{
	id: 'mcirowaterGrid',
	//frame:true,
	loadMask: {msg:'加载数据中,请等待......'},//显示等待数据加载（loading）图标
	autoScroll:true,
	columnLines: true,
    loadMask: true,
    border:false,
    margins:'2 2 0 0 ',
	anchor : '100%',
    initComponent: function() {
		var sm=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
		store = new Ext.data.Store({
		   //读取数据源用json方法(三种方法1.读取json用JsonReader,2读取数组使用ArrayReader3.读取XML用XmlReader)     
		   reader:new Ext.data.JsonReader({
			   root:"root",//从数据库中读取的总记录数          
			   totalProperty: 'total',//要读取出来的字段         
			   fields:[	
			        {name: 'id',type: 'int'},
			        {name: 'activityId',type: 'int'},
			        {name: 'address',type: 'string'},
			        {name: 'examine',type: 'string'},
				    {name: 'name',type: 'string'}, 
	    			{name: 'openid',type: 'string'}, 
		    		{name: 'shequ',type: 'string'},
		    		{name: 'tel',type: 'string'},
		    		{name: 'crTime',type: 'string'},
		    		{name: 'veTime',type: 'string'}
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
		    proxy: new Ext.data.HttpProxy ({ 
				url:ctx+"/wechat/mcirowater/queryWaterPer.do"
			 })      
	 	});
		store.load({params: {start: 0, limit: pageSize}});
		var columns=[
	  	 	sm,
	  	 	new Ext.grid.RowNumberer({header:"<center><span style='font-weight:bold'>序号</span></center>",width:40, align:"center"}),
	        {header: "<center><span style='font-weight:bold'>申请者姓名</span></center>", align:'center',width: 80,height:40, sortable: true, dataIndex: 'name', renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }},
	        {header: "<center><span style='font-weight:bold'>电话号码</span></center>", align:'center',width: 80,height:40, sortable: true, dataIndex: 'tel', renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }},
	        {header: "<center><span style='font-weight:bold'>社区</span></center>", align:'center',width: 140,height:40, sortable: true, dataIndex: 'shequ', renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }},
	        {header: "<center><span style='font-weight:bold'>地址</span></center>", align:'center',width: 140,height:40, sortable: true, dataIndex: 'address', renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }},
	        {header: "<center><span style='font-weight:bold'>申请时间</span></center>", align:'center',width: 140,height:40, sortable: true, dataIndex: 'crTime', renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }},
//	        {header: "<center><span style='font-weight:bold'>审批者</span></center>", align:'center',width: 80,height:40, sortable: true, dataIndex: 'title', renderer:function(v){
//	      		if(v=='null'){
//	      			return '';
//	      		}else{
//	      			return v;
//	      		}
//	        }},
	        {header: "<center><span style='font-weight:bold'>审批时间</span></center>", align:'center',width: 140,height:40, sortable: true, dataIndex: 'veTime', renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }},
	        {header: "<center><span style='font-weight:bold'>申请状态</span></center>", align:'center',width: 80,height:40, sortable: true, dataIndex: 'examine', renderer:function(v){
	      		if(v=='2'){
	      			return "<span style='color:red'>审核不通过</span>";
	      		}else if(v=="1"){
	      			return "<span style='color:green'>审核通过</span>";
	      		}else{
	      			return "<span style='color:blue'>未审核</span>"
	      		}
	        }},
//	        {header: "<center><span style='font-weight:bold'>说明</span></center>", align:'center',width: 180,height:40, sortable: true, dataIndex: 'title', renderer:function(v){
//	      		if(v=='null'){
//	      			return '';
//	      		}else{
//	      			return v;
//	      		}
//	        }},
	        {header: "<center><span style='font-weight:bold'>操作</span></center>", align:'center',width: 120,height:40, sortable: true, dataIndex: 'id', renderer:function(v,cellmeta, record, rowIndex, columnIndex, store){
	        	if(record.get("examine")=="2"||record.get("examine")=="1"){
	        		return '<span style="font-weight:bold;color:#8E8E8E">通过</span>||<span style="font-weight:bold;color:#8E8E8E" >驳回</span>';
	        	}else{
	        		return '<a  style="font-weight:bold;color:green" onclick="shtg('+v+')">通过</a>||<a style="font-weight:bold;color:red" onclick="shbh('+v+')">驳回</a>';
	        	}
	        }}
	        
	    ];	
		this.colModel = new Ext.ux.grid.LockingColumnModel(columns);
		this.store=store;
		this.id = "mcirowaterGrid";
		this.sm=sm;
		this.view = new Ext.ux.grid.LockingGridView();
		mcirowaterGrid.superclass.initComponent.call(this);
	}
});