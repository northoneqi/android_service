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
		store = new Ext.data.Store({
		   //读取数据源用json方法(三种方法1.读取json用JsonReader,2读取数组使用ArrayReader3.读取XML用XmlReader)     
		   reader:new Ext.data.JsonReader({
			   root:"root",//从数据库中读取的总记录数          
			   totalProperty: 'total',//要读取出来的字段         
			   fields:[	
			       {name: 'ordercode'},
			       {name: 'ordertype'},
			       {name: 'addtime'},
			       {name: 'Consignee'},
			       {name: 'telephone'},
			       {name: 'province'},
			       {name: 'city'},
			       {name: 'area'},
			       {name: 'address'},
			       {name: 'SKUName'},
			       {name: 'BuyPrice'},
			       {name: 'Subtotal'},
			       {name: 'BuyPrice'}
			      
			    ]
			}),
			pageSize: pageSize,
			
			 //获取数据源(该方法返回一个json格式的数据源)
			 proxy: new Ext.data.HttpProxy ({ 
				
				url:'admin/mciroactivity/redList.do'
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
	        {header: "<center>红包订单数</center>", width: 120, sortable: true, dataIndex: 'ordercode', renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		 }else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>订单状态</center>", width: 80, sortable: true, dataIndex: 'ordertype',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>下单时间</center>", width: 80, sortable: true, dataIndex: 'addtime',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>姓名</center>", width: 80, sortable: true, dataIndex: 'Consignee',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v ;
	      		}
	        }, align:"center"},
	        {header: "<center>电话</center>", width: 80, sortable: true, dataIndex: 'telephone',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>省</center>", width: 80, sortable: true, dataIndex: 'province',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else if(v=='1'){
	       			return '北京市';
	       		}
	        }, align:"center"},
	        {header: "<center>市</center>", width: 80, sortable: true, dataIndex: 'city',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else if(v=='1'){
	       			return '北京市';
	       		}
	        }, align:"center"},
	        {header: "<center>区</center>", width: 80, sortable: true, dataIndex: 'area',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else if(v=='3'){
	       			return '朝阳区';
	       		}else if(v=='4'){
	       			return '东城区';
	       		}else if(v=='5'){
	       			return '西城区';
	       		}else if(v=='6'){
	       			return '丰台区';
	       		}else if(v=='7'){
	       			return '石景山区';
	       		}else if(v=='8'){
	       			return '海淀区';       			
	       		}else if(v=='9'){
	       			return '门头沟区';
	       		}else if(v=='10'){
	       			return '房山区';
	       		}else if(v=='11'){
	       			return '通州区';
	       		}else if(v=='12'){
	       			return '顺义区';
	       		}else if(v=='13'){
	       			return '昌平区';
	       		}else if(v=='14'){
	       			return '大兴区';
	       		}else if(v=='15'){
	       			return '怀柔区';
	       		}else if(v=='16'){
	       			return '平谷区';
	       		}
	        }, align:"center"},
	        {header: "<center>地址</center>", width: 80, sortable: true, dataIndex: 'address',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>商品名称</center>", width: 80, sortable: true, dataIndex: 'SKUName',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>商品单价</center>", width: 80, sortable: true, dataIndex: 'BuyPrice',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>红包金额</center>", width: 80, sortable: true, dataIndex: 'Subtotal',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>支付金额</center>", width: 80, sortable: true, dataIndex: 'BuyPrice',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"}
	    ];	
		this.colModel = new Ext.ux.grid.LockingColumnModel(columns);
		this.store=store;
		this.id = "queryId";
		this.sm=sm;
		this.view = new Ext.ux.grid.LockingGridView();
		mainGrid.superclass.initComponent.call(this);
	}
});