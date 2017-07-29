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
       BillDetail = Ext.data.Record.create([
            {name: 'id'},
         	{name: 'budgetTargetCode',type: 'string'},
         	{name: 'budgetTargetName',type: 'string'}, 
         	{name: 'budgetMoney',type: 'int'},
         	{name: 'addMoney',type: 'int'},
         	{name: 'lastMoney',type: 'int'},
         	{name: 'grandTotalMoney',type: 'int'},
         	{name: 'remark',type: 'string'}
       ]);

       billDetailStore = createStore(BillDetail, false, {}, "budget/monthExpenseDocumentDetail/getDetails.do");
       billDetailEditor = new Ext.ux.grid.RowEditor({
             saveText: '保存',
             cancelText: '取消',
             commitChangesText: '您需要先保存或者取消',
             errorText: '错误'
        });
       
        function setTotalPanel(){
	    	//Ext.getCmp("totalPanel").setText("合计金额:");
	    	//console.log(billDetailStore);
	    };
	    
        billDetailEditor.on({
        	"beforeedit": function(roweditor, rowIndex){
        		//动态改变是否让某列是否可编辑
        		var columns = roweditor.grid.colModel.config;
        		
        		if(Ext.getCmp("billFormPanel").getForm().findField("documentTypeCombo").getValue() == "预算单"){
					columns[2].setEditor({
						xtype: 'numberfield',
			        	value: 0,
			            allowBlank: false,
			            submitValue: false
					});
					columns[3].setEditor(null);
        		}else{
        			
        			columns[2].setEditor(null);
					columns[3].setEditor({
						xtype: 'numberfield',
			        	value: 0,
			            allowBlank: false,
			            submitValue: false
					});
        		}
				
        		billDetailEditor.initFields();
        		billDetailEditor.refreshFields();
        	},
        	"afteredit": function(roweditor, changes, record12, rowIndex){
	        	setTotalPanel();
	        	var record = Ext.getCmp("billDetailGrid").getStore().getAt(billDetailEditor.getEditingRow());
	        	if(Ext.getCmp("billFormPanel").getForm().findField("documentTypeCombo").getValue() == "预算单"){
	        		record.data.lastMoney = record.data.budgetMoney;
	 				record.data.grandTotalMoney = record.data.budgetMoney;
	        	}else{
	        		record.data.lastMoney = record.data.lastMoney+record.data.addMoney;
	 				record.data.grandTotalMoney = record.data.budgetMoney+record.data.addMoney;
	        	}
	        	
	 			Ext.getCmp("billDetailGrid").getStore().commitChanges();
	 		}
        });
        
	    this.store = billDetailStore;
	    var summary = new Ext.ux.grid.GridSummary();
	    this.plugins = [billDetailEditor, summary];
	    this.view = new Ext.grid.GroupingView({
	        markDirty: false
	    });
	    
	    var budgetTarget = Ext.ux.form.ComboBox({
 			url: 'budget/budgetTargetController/getCombo.do',
 			fields: [{name: 'targetCode'}, {name: 'targetName'}],
 			name: 'budgetTargetName',
    		hiddenName: 'budgetTargetCode',
    		listeners: [{
    			"change": function(field, value, oldValue){
		 			var record = billDetailStore.getAt(billDetailEditor.getEditingRow());
		 			record.data.budgetTargetCode = value;
		 		}
    		}]
 		})
 		
 		budgetTarget.on("change", function(field, value, oldValue){
 			//alert(value);
 			var record = billDetailStore.getAt(billDetailEditor.getEditingRow());
 			record.data.budgetTargetCode = value;
 		});
 		   
	   /* this.tbar = [{
	        text: '增加',
	        iconCls:'addIcon',
	        handler: function(){
	            var e = new BillDetail({});
	            console.log(billDetailEditor);
	            //readOnly: true,
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
	    }];*/
	
	    var renderSummary = function(o, cs, cm) {
	        return '合计：';
	    }
	    
	    this.columns = [
	    new Ext.grid.RowNumberer(),
	    {
	        header: '预算指标',
	        dataIndex: 'budgetTargetName',
	        width: 100,
	        sortable: true,
	        align: 'center',
	        summaryRenderer: renderSummary
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>预算金额</center>',
	        dataIndex: 'budgetMoney',
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
	    	xtype: 'numbercolumn',
	        header: '<center>追加预算</center>',
	        dataIndex: 'addMoney',
	        width: 120,
	        sortable: true,
	        format: '0.00',
	        align: 'right',editor: {
	        	xtype: 'numberfield',
	        	value: 0,
	            allowBlank: false,
	            submitValue: false
	        },
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00') 
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>剩余预算</center>',
	        dataIndex: 'lastMoney',
	        width: 120,
	        sortable: true,
	        format: '0.00',
	        align: 'right',
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00') 
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>累计预算</center>',
	        dataIndex: 'grandTotalMoney',
	        width: 120,
	        sortable: true,
	        format: '0.00',
	        align: 'right',
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00')   
	    },{
	        header: '备注',
	        dataIndex: 'remark',
	        align: 'center',
	        width: 200,
	        align: 'center',
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
