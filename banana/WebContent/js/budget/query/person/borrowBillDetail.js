BorrowBillDetailGrid = Ext.extend(Ext.grid.GridPanel, {
	id: 'borrowBillDetailGrid',
	frame: false,
	loadMask: {msg:'加载数据中,请等待......'},//显示等待数据加载（loading）图标
	autoScroll:true,
	columnLines: true,
    loadMask: true,
    border: false,
    height: 240,
    margins:'2 2 0 0 ',
    region: 'center',
    viewConfig : {
      // forceFit : true //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。
    },
    initComponent: function() {
    	var BillDetail = Ext.data.Record.create([
            {name: 'id'},
         	{name: 'costCode',type: 'string'},
         	{name: 'costName',type: 'string'}, 
         	{name: 'cost',type: 'int'},
         	{name: 'digest',type: 'string'},
         	{name: 'remark',type: 'string'}
       ]);

       var billDetailStore = createStore(BillDetail, false, {}, "budget/borrowApplyDocumentDetail/getDetailsByPerson.do");
        
       billDetailStore.on({
			"beforeload": function(store){
				var baseParams = {pageSize:pageSize};
				if(!Ext.isEmpty(Ext.getCmp("searchFrom"))){
					baseParams = Ext.getCmp("searchFrom").getForm().getValues();
					//下拉树有bug
					var departmentCode = Ext.getCmp("searchFrom").getForm().findField("departmentCode").getValue();
					if(Ext.isEmpty(departmentCode)) {
						baseParams.departmentCode = "";
					}
					store.baseParams = baseParams;
				}
			}
	  });
       
	    this.store = billDetailStore;
	    this.view = new Ext.grid.GroupingView({
	        markDirty: false
	    });
	    
	    this.columns = [
	    new Ext.grid.RowNumberer(),
	    {
	        header: '费用项名称',
	        dataIndex: 'costName',
	        width: 120,
	        sortable: true,
	        align: 'center'
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>金额</center>',
	        dataIndex: 'cost',
	        width: 120,
	        sortable: true,
	        align: 'right',
	        format: '0.00'
	    }];
	    
	    BorrowBillDetailGrid.superclass.initComponent.call(this);
    }
});
