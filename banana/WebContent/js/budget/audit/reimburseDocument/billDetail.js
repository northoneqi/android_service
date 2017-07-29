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
         	{name: 'cost',type: 'int'},
         	{name: 'digest',type: 'string'},
         	{name: 'remark',type: 'string'}
       ]);

       var billDetailStore = createStore(BillDetail, false, {}, "budget/reimburseDocumentDetail/getDetails.do");
       var billDetailEditor = new Ext.ux.grid.RowEditor({
             saveText: '保存',
             cancelText: '取消',
             commitChangesText: '您需要先保存或者取消',
             errorText: '错误'
        });
       
        
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
	        header: '费用项名称',
	        dataIndex: 'costName',
	        width: 100,
	        sortable: true,
	        align: 'center',
	        summaryRenderer: renderSummary
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>金额</center>',
	        dataIndex: 'cost',
	        width: 120,
	        sortable: true,
	        align: 'right',
	        format: '0.00',
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00')
	    },{
	        header: '摘要',
	        dataIndex: 'digest',
	        width: 200,
	        align: 'center'
	    },{
	        header: '备注',
	        dataIndex: 'remark',
	        align: 'center',
	        width: 200
	    }];
	    
	    BillDetailGrid.superclass.initComponent.call(this);
    }
});
