BillDetailGrid = Ext.extend(Ext.grid.GridPanel, {
	id: 'billDetailGrid',
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
            {name: 'crowdfundingId'},
         	{name: 'crowdfundingCode',type: 'string'},
         	{name: 'payOpenId',type: 'string'}, 
         	{name: 'payName',type: 'string'},
         	{name: 'payMoney',type: 'string'},
         	{name: 'addTime',type: 'string'},
         	{name: 'outTradeNo',type: 'string'},
         	{name: 'transportFee',type: 'string'},
         	{name: 'tradeState',type: 'string'},
         	{name: 'tradeMode',type: 'string'},
         	{name: 'partner',type: 'string'},
         	{name: 'bankType',type: 'string'},
         	{name: 'bankBillno',type: 'string'},
         	{name: 'totalFee',type: 'string'},
         	{name: 'feeType',type: 'string'},
         	{name: 'notifyId',type: 'string'},
         	{name: 'transactionId',type: 'string'},
         	{name: 'timeEnd',type: 'string'},
         	{name: 'productFee',type: 'string'},
         	{name: 'discount',type: 'string'},
         	{name: 'refundStatus',type: 'string'},
         	{name: 'remark',type: 'string'}
       ]);

       var billDetailStore = createStore(BillDetail, false, {}, "admin/payinfoTwo/getDetails.do");
        
	    this.store = billDetailStore;
	    var summary = new Ext.ux.grid.GridSummary();
	    this.plugins = [summary];
	    this.view = new Ext.grid.GroupingView({
	        markDirty: false
	    });
	    
	    var renderSummary = function(o, cs, cm) {
	        return '合计：';
	    }
	    
	    this.columns = [
	    new Ext.grid.RowNumberer(),
	    {
	        header: '支付人姓名',
	        dataIndex: 'payName',
	        width: 100,
	        sortable: true,
	        align: 'center'
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>支付金额</center>',
	        dataIndex: 'payMoney',
	        width: 120,
	        sortable: true,
	        align: 'right',
	        format: '0.00',
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00') 
	    },
	    /*{
	        header: '交易状态',
	        dataIndex: 'tradeState',
	        width: 200,
	        align: 'center'
	    },*/
	    {
	        header: '备注',
	        dataIndex: 'remark',
	        align: 'center',
	        width: 200
	    }];
	    
	    //添加自动合计
//	    this.plugins = new Ext.ux.grid.GridSummary({
//	    	summaryTitleColumn: '合计：'
//	    });
	    BillDetailGrid.superclass.initComponent.call(this);
    }
});
