mainGrid=Ext.extend(Ext.grid.GridPanel,{
	id: 'mainGrid',
	frame:true,
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
			       {name: 'crowdfundingCode'},
			       {name: 'activityId'},
			       {name: 'isPay'},
			       {name: 'addTime'},
			       {name: 'endTime'},
			       {name: 'orderStatus'},
			       {name: 'payurl'},
			       {name: 'sendDate'},
			       {name: 'sendTimes'},
			       {name: 'paymenTtime'},
			       {name: 'playNum'},
			       {name: 'wxOrdercode'},
			       {name: 'openId'},
			       {name: 'name'},
			       {name: 'tel'},
			       {name: 'province'},
			       {name: 'city'},
			       {name: 'area'},
			       {name: 'address'},
			       {name: 'sku'},
			       {name: 'buyNumber'},
			       {name: 'skuName'},
			       {name: 'goodPrice'},
			       {name: 'remark'}
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
			 proxy: new Ext.data.HttpProxy ({ 
				url:'admin/orderbasicTwo/list.do'
			 })      
	 	});
		store.on({
			"beforeload": function(store){
				var baseParams = {pageSize:pageSize};
				if(!Ext.isEmpty(Ext.getCmp("searchFrom"))){
					baseParams = Ext.getCmp("searchFrom").getForm().getValues();
					store.baseParams = baseParams;
				}
			}
		});
		store.load({params: {start: 0, limit: pageSize}});
		
		var columns=[
	  	 	sm,
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50,sortable : true,  align:"center"}),
	        {header: "<center>众筹订单编号</center>", width: 120, sortable: true, dataIndex: 'crowdfundingCode', renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>订单状态</center>", width: 80, sortable: true, dataIndex: 'orderStatus',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			if(v=='0'){
	      				return '进行中'; 
	      			}
	      			if(v=='1'){
	      				return '已完成'; 
	      			}
	      			if(v=='2'){
	      				return '已取消'; 
	      			}
	      		}
	        }, align:"center"},
	        {header: "<center>是否在支付</center>", width: 80, sortable: true, dataIndex: 'orderStatus',renderer:function(v){
		      	if(v=='null'){
		      			return '';
		      		}else{
		      			if(v=='0'){
		      				return '是'; 
		      			}
		      			if(v=='1'){
		      				return '否'; 
		      			}
		      			if(v=='2'){
		      				return '否'; 
		      			}
		      		}
	        }, align:"center"},
	        {header: "<center>添加时间</center>", width: 80, sortable: true, dataIndex: 'addTime',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v ;
	      		}
	        }, align:"center"},
	        {header: "<center>结束时间</center>", width: 80, sortable: true, dataIndex: 'endTime',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>发送日期</center>", width: 80, sortable: true, dataIndex: 'sendDate',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>发送时间段</center>", width: 100, sortable: true, dataIndex: 'sendTimes',renderer:function(v){
	        	if(v=='null'){
	       			return '';
	       		}if(v==1){
	       			return '9:00-11:30';
	       		}if(v==2){
	       			return '14:00-18:00';
	       		}if(v==3){
	       			return '18:00-20:00';
	       		}if(v==4){
	       			return '任意时间';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>支付时间</center>", width: 100, sortable: true, dataIndex: 'paymenTtime',renderer:function(v){
	    		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	       }, align:"center"},
	        {header: "<center>众筹人份数</center>", width: 100, sortable: true, dataIndex: 'playNum',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>微信订单编号</center>", width: 100, sortable: true, dataIndex: 'wxOrdercode',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center> 发起人姓名</center>", width: 100, sortable: true, dataIndex: 'name',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center> 发起人电话</center>", width: 100, sortable: true, dataIndex: 'tel',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center> 省</center>", width: 100, sortable: true, dataIndex: 'province',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center> 市</center>", width: 100, sortable: true, dataIndex: 'city',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>区</center>", width: 100, sortable: true, dataIndex: 'area',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>发起人地址</center>", width: 100, sortable: true, dataIndex: 'address',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>购买份数</center>", width: 100, sortable: true, dataIndex: 'buyNumber',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>商品名称</center>", width: 100, sortable: true, dataIndex: 'skuName',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>商品单价</center>", width: 100, sortable: true, dataIndex: 'goodPrice',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"}, 
	       {header: "<center>备注</center>", width: 135, sortable: true, dataIndex: 'remark',renderer:function(v){
	     		if(v=='null' || v == ""){
	     			return '';
	     		}else{
	     			return v;
	     		}
	       }, align:"center"}
	    ];	
		this.colModel = new Ext.grid.ColumnModel(columns);
		this.store=store;
		this.id = "queryId";
		this.sm=sm;
		//this.view = new Ext.ux.grid.LockingGridView();
		mainGrid.superclass.initComponent.call(this);
	}
});