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
            {name: 'id'},
            {name: 'costCode',type: 'string'},
         	{name: 'costName',type: 'string'},
         	{name: 'digest',type: 'string'}, 
         	{name: 'cost',type: 'int'},
         	{name: 'clearingSubject',type: 'string'},
         	{name: 'partySubject',type: 'string'},
         	{name: 'billNo',type: 'string'},
         	{name: 'cashFlow',type: 'string'},
         	{name: 'remark',type: 'string'}
       ]);

       var billDetailStore = createStore(BillDetail, false, {}, "budget/paymentDocumentDetail/getDetails.do");
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
	        header: '<center>费用项名称</center>',
	        dataIndex: 'costName',
	        width: 120,
	        align: 'center',
	        summaryRenderer: renderSummary
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>金额</center>',
	        dataIndex: 'cost',
	        width: 120,
	        sortable: true,
	        format: '0.00',
	        align: 'right',
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00')
	    },{
	        header: '<center>摘要</center>',
	        dataIndex: 'digest',
	        width: 120,
	        sortable: true,
	        align: 'center'
	    },{
	        header: '<center>结算科目</center>',
	        dataIndex: 'clearingSubject',
	        width: 120,
	        sortable: true,
	        align: 'center'
	    },{
	        header: '<center>对方科目</center>',
	        dataIndex: 'partySubject',
	        width: 120,
	        sortable: true,
	        align: 'center'
	    },{
	        header: '<center>票据号</center>',
	        dataIndex: 'billNo',
	        width: 120,
	        sortable: true,
	        align: 'center'
	    },{
	        header: '<center>现金流量</center>',
	        dataIndex: 'cashFlow',
	        width: 120,
	        sortable: true,
	        align: 'right'
	    },{
	        header: '备注',
	        dataIndex: 'remark',
	        width: 200,
	        align: 'center'
	    }];
	    
	    BillDetailGrid.superclass.initComponent.call(this);
    }
});
