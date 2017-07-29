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
		var store = new Ext.data.Store({
		   //读取数据源用json方法(三种方法1.读取json用JsonReader,2读取数组使用ArrayReader3.读取XML用XmlReader)     
		   reader:new Ext.data.JsonReader({
			   root:"root",//从数据库中读取的总记录数          
			   totalProperty: 'total',//要读取出来的字段    
			   fields:[	
			       {name: 'id'},
			       {name: 'activityNum'},
			       {name: 'activityStatus'},
			       {name: 'activityInfo'},
			       {name: 'playNum'},
			       {name: 'isCanal'},
			       {name: 'isPayall'},
			       {name: 'minPayNumber'},
			       {name: 'maxPlayMore'},
			       {name: 'maxPayMore'},
			       {name: 'activityBeginTime'},
			       {name: 'activityEndTime'},
			       {name: 'activityAuto'},
			       {name: 'activityAddTime'},
			       {name: 'remark'}
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
			 proxy: new Ext.data.HttpProxy ({ 
				url:'admin/backinfoTwo/list.do'
			 })      
	 	});
		store.on({
			"beforeload": function(myStore){
				var baseParams = {pageSize:pageSize};
				if(!Ext.isEmpty(Ext.getCmp("searchFrom"))){
					baseParams = Ext.getCmp("searchFrom").getForm().getValues();
					if(Ext.getCmp("searchFrom").getForm().findField("activityStatus").getValue() == ""){
						baseParams.activityStatus = "";
					}
					myStore.baseParams = baseParams;
				}
			}
		});
		store.load({params: {start: 0, limit: pageSize}});
		
		
		var columns=[
	  	 	sm,
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50, sortable : true, align:"center"}),
	        {header: "<center>活动期数</center>", width: 100, sortable: true, dataIndex: 'activityNum', renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>活动状态</center>", width: 80, sortable: true, dataIndex: 'activityStatus',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else if(v == 0){
	      			return '未开始';
	      		}else if(v == 1) {
	      			return "进行中";
	      		}else if(v == 2) {
	      			return "暂停";
	      		}else if(v == 3){
	      			return "结束";
	      		}
	        }, align:"center"},
	        {header: "<center>活动介绍</center>", width: 80, sortable: true, dataIndex: 'activityInfo',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>众筹人份数</center>", width: 80, sortable: true, dataIndex: 'playNum',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>未完成是否可以取消</center>", width: 80, sortable: true, dataIndex: 'isCanal',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else if(v == true){
	      			return '是';
	      		}else {
	      			return '否';
	      		}
	        }, align:"center"},
	        {header: "<center>是否可以自己支付</center>", width: 80, sortable: true, dataIndex: 'isPayall',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else if(v == true){
	      			return '是';
	      		}else {
	      			return '否';
	      		}
	        }, align:"center"},
	        {header: "<center>最少允许支付剩余金额的份数</center>", width: 100, sortable: true, dataIndex: 'minPayNumber',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>最多发起次数</center>", width: 100, sortable: true, dataIndex: 'maxPlayMore',renderer:function(v){
	    		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	       }, align:"center"},
	        {header: "<center>最多支付次数</center>", width: 100, sortable: true, dataIndex: 'maxPayMore',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>活动开始时间</center>", width: 100, sortable: true, dataIndex: 'activityBeginTime',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>活动结束时间</center>", width: 100, sortable: true, dataIndex: 'activityEndTime',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>活动计划经办人</center>", width: 100, sortable: true, dataIndex: 'activityAuto',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>活动计划时间</center>", width: 100, sortable: true, dataIndex: 'activityAddTime',renderer:function(v){
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
		this.store = store;
		this.id = "queryId";
		this.sm=sm;
		//this.view = new Ext.ux.grid.LockingGridView();
		mainGrid.superclass.initComponent.call(this);
	}
});