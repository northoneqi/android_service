//核销明细
VeriBillDetailGrid = Ext.extend(Ext.grid.GridPanel, {
	id: 'veriBillDetailGrid',
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
            {name: 'borrowId'},
            {name: 'borrowDocumentNum'},
            {name: 'borrowDate'},
            {name: 'borrowPeopleCode'},
            {name: 'borrowPeopleName'},
         	{name: 'costCode',type: 'string'},
         	{name: 'costName',type: 'string'}, 
         	{name: 'borrowMoney',type: 'int'},
         	{name: 'returnMoney',type: 'int'},
         	{name: 'veriMoney',type: 'int'},
         	{name: 'digest',type: 'string'},
         	{name: 'remark',type: 'string'}
       ]);

       var billDetailStore = createStore(BillDetail, false, {}, "budget/paymentDocument/getBorrowedDetails.do");
       var billDetailEditor = new Ext.ux.grid.RowEditor({
             saveText: '保存',
             cancelText: '取消',
             commitChangesText: '您需要先保存或者取消',
             errorText: '错误'
        });
       
	    this.store = billDetailStore;
	    this.plugins = [billDetailEditor];
	    this.view = new Ext.grid.GroupingView({
	        markDirty: false
	    });
	
	    this.columns = [
	    new Ext.grid.RowNumberer(),
	    {
	        header: '借款单号',
	        dataIndex: 'borrowDocumentNum',
	        width: 100,
	        sortable: true,
	        align: 'center'
	    },{
	        header: '借款日期',
	        dataIndex: 'borrowDate',
	        width: 100,
	        sortable: true,
	        align: 'center'
	    },{
	        header: '费用项名称',
	        dataIndex: 'costName',
	        width: 100,
	        sortable: true,
	        align: 'center'
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>借用金额</center>',
	        dataIndex: 'borrowMoney',
	        width: 100,
	        sortable: true,
	        align: 'right',
	        format: '0.00'
	    },{
	        header: '摘要',
	        dataIndex: 'digest',
	        width: 150,
	        align: 'center'
	    },{
	        header: '备注',
	        dataIndex: 'remark',
	        align: 'center',
	        width: 150
	    },{
	    	xtype: 'numbercolumn',
	        header: "<span style='color=red;'>已核销金额",
	        dataIndex: 'returnMoney',
	        width: 100,
	        align: 'right',
	        format: '0.00'
	    },{
	    	xtype: 'numbercolumn',
	        header: "<span style='color=red;'>本次核销金额",
	        dataIndex: 'veriMoney',
	        align: 'right',
	        format: '0.00',
	        width: 100
	    }];
	    
	    VeriBillDetailGrid.superclass.initComponent.call(this);
    }
});
