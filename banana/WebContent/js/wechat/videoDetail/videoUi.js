videoUi=Ext.extend(Ext.Panel,{
	id:'videoUi',
	border:false,
	margins:'1 1 1 1',
	layout:'fit',
	initComponent: function() {
		 //state=createCombo("活动状态", "state", [["-1","请选择"],["0","未开始"], ["1","进行中"], ["2","已结束"]]);
		 videoTree = createTreePanel("活动数", ctx+"/wechat/video/queryTree.do",{},true);
		 videoGrid=new videoGrid();
		 pageSize=20;
		 Ext.applyIf(this,{
			items:[{
				layout:'border',
				border:false,
				items:[{
					region:'west',
					margins:'1 1 1 1',
					tbar:{height:34,items:['<span style="text-align:center;">' +
											'<img src="'+ctx+'/images/list.png"/></span>' +
											'<span style="font-weight:bold;font-size:20px;">活动导航</span>']},
					border:false,
					width:260,
					layout:'fit',
					//frame:true,
					items:videoTree
				},{
					region:'center',
					margins:'1 1 1 1',
					layout:'border',
					tbar:{height:34,items:['<span style="text-align:center;">' +
											'<img src="'+ctx+'/images/list.png"/></span>' +
											'<span style="font-weight:bold;font-size:20px;" id="ss">视频信息列表</span>',
													 '->',{xtype:'hidden',id:'videoId'},{xtype:'hidden',id:'videoState'},{xtype:'hidden',id:'videoCode'},
													// '-',{text:'高级查询',iconCls:'previewIcon',id:'searchButton'},
													 '-',{text:'添加',iconCls:'addIcon',id:'addButton'},
													 '-',{text:'编辑',iconCls:'edit1Icon',id:'editButton'},
													 '-',{text:'删除',iconCls:'deleteIcon',id:'deleteButton'},
													 '-',{text:'评委点评',iconCls:'submitIcon',id:'resultButton'},
													 '-']},
					border:false,
					items:[{
						region:'north',
						height:40,
						border:false,
						frame:true,
						items:new searchFrom()
					},{
						region:'center',
						border:false,
						layout:'fit',
						items:videoGrid,
						bbar: new Ext.PagingToolbar({
				            pageSize: pageSize,
				            store: videoGrid.getStore(),
				            displayInfo: true
				    	})
					}]
				}]
			}]
		 });
		videoUi.superclass.initComponent.call(this);
	}
});