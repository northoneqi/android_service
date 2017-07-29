videoUi=Ext.extend(Ext.Panel,{
	id:'videoUi',
	border:false,
	margins:'1 1 1 1',
	layout:'fit',
	initComponent: function() {
		 state=createCombo("活动状态", "state", [["-1","请选择"],["0","未开始"], ["1","进行中"], ["2","已结束"]]);
		 videoGrid=new videoGrid();
		 pageSize=20;
		 Ext.applyIf(this,{
			items:[{
				layout:'border',
				border:false,
				//frame:true,
				tbar:{height:30,items:['<span style="text-align:center;">' +
											'<img src="'+ctx+'/images/list.png"/>' +
									   '</span>',
									   '<span style="font-weight:bold;font-size:20px;">活动列表</span>',
									   				 '->',
													 //'-',{text:'高级查询',iconCls:'previewIcon',id:'searchButton'},
													 '-',{text:'新建活动',iconCls:'addIcon',id:'addButton'},
													 '-',{text:'编辑活动',iconCls:'edit1Icon',id:'editButton'},
													 '-',{text:'指定当前活动',iconCls:'wrenchIcon',id:'stopButton'},
													 '-']},
				items:[{
					region:'north',
					margins:'1 1 1 1',
					frame:true,
					border:false,
					height:40,
					layout:'fit',
					items:new searchFrom()
				},{
					region:'center',
					margins:'1 1 1 1',
					frame:true,
					layout:'fit',
					border:false,
					items:videoGrid,
					bbar: new Ext.PagingToolbar({
				            pageSize: pageSize,
				            store: videoGrid.getStore(),
				            displayInfo: true
				    })
				}]
			}]
		 });
		videoUi.superclass.initComponent.call(this);
	}
});