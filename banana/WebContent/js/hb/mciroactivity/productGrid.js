ProductGrid=Ext.extend(Ext.grid.GridPanel,{
	id: 'productGrid',
	frame:true,
	region: 'center',
	loadMask: {msg:'加载数据中,请等待......'},//显示等待数据加载（loading）图标
	autoScroll:true,
	columnLines: true,
    loadMask: true,
    border:false,
    margins:'2 2 0 0 ',
	anchor : '100%',
    initComponent: function() {
		var sm=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
		var productStore = new Ext.data.Store({
		   //读取数据源用json方法(三种方法1.读取json用JsonReader,2读取数组使用ArrayReader3.读取XML用XmlReader)     
		   reader:new Ext.data.JsonReader({
			   root:"root",//从数据库中读取的总记录数          
			   totalProperty: 'total',//要读取出来的字段         
			   fields:[	
			       {name: 'ProSKUId'},
			       {name: 'SKU'},
			       {name: 'productName'},
			       {name: 'MarketPrice'},
			       {name: 'SellPrice'},
			       {name: 'IsShow'}
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
			 proxy: new Ext.data.HttpProxy ({ 
				url:'admin/productbasicskuinfo/list.do'
			 })      
	 	});
		productStore.on({
			"beforeload": function(myStore){
				var baseParams = {pageSize:pageSize};
				if(!Ext.isEmpty(Ext.getCmp("productSearchFrom"))){
					baseParams = Ext.getCmp("productSearchFrom").getForm().getValues();
					myStore.baseParams = baseParams;
				}
			}
		});
		productStore.load({params: {start: 0, limit: pageSize}});
		
		
		var columns=[
	  	 	sm,
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50, sortable : true, align:"center"}),
	        {header: "<center>商品SKU</center>", width: 100, sortable: true, dataIndex: 'SKU', renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>商品名称</center>", width: 80, sortable: true, dataIndex: 'productName',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else {
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>市场价</center>", width: 80, sortable: true, dataIndex: 'MarketPrice',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>销售价</center>", width: 80, sortable: true, dataIndex: 'SellPrice',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>是否上架</center>", width: 80, sortable: true, dataIndex: 'IsShow',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else if(v == true){
	      			return '是';
	      		}else {
	      			return '否';
	      		}
	        }, align:"center"}
	    ];	
		this.colModel =new Ext.grid.ColumnModel(columns);
		this.store = productStore;
		this.id = "queryProductId";
		this.sm=sm;
		//this.view = new Ext.ux.grid.LockingGridView();
		ProductGrid.superclass.initComponent.call(this);
	}
});