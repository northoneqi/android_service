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
       
        function setTotalPanel(){
	    	//Ext.getCmp("totalPanel").setText("合计金额:");
	    	//console.log(billDetailStore);
	    };
	    
        billDetailEditor.on("afteredit", function(roweditor, changes, record, rowIndex){
        	setTotalPanel();
        	var record = billDetailStore.getAt(rowIndex);
 			//console.log(record);
        });
        
	    this.store = billDetailStore;
	    var summary = new Ext.ux.grid.GridSummary();
	    this.plugins = [billDetailEditor, summary];
	    this.view = new Ext.grid.GroupingView({
	        markDirty: false
	    });
	    
	    var costItem = Ext.ux.form.ComboBox({
 			url: 'budget/costItemController/getCombo.do',
 			fields: [{name: 'costCode'}, {name: 'costName'}],
 			name: 'costName',
    		hiddenName: 'costCode',
    		listeners: [{
    			"change": function(field, value, oldValue){
		 			var record = billDetailStore.getAt(billDetailEditor.getEditingRow());
		 			record.data.costCode = value;
		 		}
    		}]
 		})
 		
 		costItem.on("change", function(field, value, oldValue){
 			//alert(value);
 			var record = billDetailStore.getAt(billDetailEditor.getEditingRow());
 			record.data.costCode = value;
 		});
 		   
	    this.tbar = [{
	        text: '增加',
	        iconCls:'addIcon',
	        handler: function(){
	            var e = new BillDetail({});
	            billDetailEditor.stopEditing();
	            billDetailStore.insert(billDetailStore.getCount(), e);
	            Ext.getCmp("billDetailGrid").getView().refresh();
	            Ext.getCmp("billDetailGrid").getSelectionModel().selectRow(billDetailStore.getCount());
	            billDetailEditor.startEditing(billDetailStore.getCount()-1);
	        }
	    }, '-', {
	        text: '删除',
	        iconCls:'deleteIcon',
	        handler: function(){
	            billDetailEditor.stopEditing();
	            var s = Ext.getCmp("billDetailGrid").getSelectionModel().getSelections();
	            for(var i = 0, r; r = s[i]; i++){
	            	billDetailStore.remove(r);
	            }
	        }
	    }, '-', '->', {
	    	id: 'totalPanel'
	    }];
	
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
	        editor:  costItem,
	        summaryRenderer: renderSummary
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>金额</center>',
	        dataIndex: 'cost',
	        width: 120,
	        sortable: true,
	        align: 'right',
	        format: '0.00',
	        editor: {
	        	xtype: 'numberfield',
	        	value: 0,
	            allowBlank: false,
	            submitValue: false
	        },
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00') 
	    },{
	        header: '摘要',
	        dataIndex: 'digest',
	        width: 200,
	        align: 'center',
	        editor: {
	        	xtype: 'textfield',
	        	submitValue: false,
	            allowBlank: true
	        }
	    },{
	        header: '备注',
	        dataIndex: 'remark',
	        align: 'center',
	        width: 200,
	        editor: {
	        	xtype: 'textfield',
	        	submitValue: false,
	            allowBlank: true
	        }
	    }];
	    
	    //添加自动合计
//	    this.plugins = new Ext.ux.grid.GridSummary({
//	    	summaryTitleColumn: '合计：'
//	    });
	    BillDetailGrid.superclass.initComponent.call(this);
    }
});
