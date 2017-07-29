Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
Ext.QuickTips.init();
excelWin = Ext.extend(Ext.Window, {
	title : '系统业务表导入窗口',
	iconCls:'icon-window',
	width : 630,
	height : 255,
	layout : 'fit',
	modal : true,
	buttonAlign : 'center',
	initComponent : function() {
		Ext.applyIf(this, {
			xtype : 'form',
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'form',
				layout : 'form',
				height : 185,
				width : 620,
				id : 'uploadFormPanel',
				fileUpload: true,
				//			    autoScroll:true,
				border : false,
				buttonAlign : 'center',
				items : [ {
					layout : 'form',
					frame : true,
					border : false,
					buttonAlign : 'center',
					items : [ {
						xtype : 'fieldset',
						collapseFirst : false,
						header : true,
						animCollapse : true,
						frame : true,
						width : 600,
						height : 181,
						layout : 'form',
						labelAlign : 'right',
						frame : true,
						items : [ {
							layout : 'column', // 定义该元素为布局为列布局方式
							//collapsible:true, //是否隐藏
							items : [{
								columnWidth : .9, // 该列占用的宽度，标识为30％
								layout : 'form',
								border : false,
								labelAlign : 'right',
								bodyStyle:'padding-top:70px;',
								items : [ { // 这里可以为多个Item，表现出来是该列的多行
									xtype : 'textfield',
									fieldLabel : '选择文件',
									msgTarget : 'side',
									name : 'myfiles',
									id:'myfiles',
									allowBlank : false,
									inputType: 'file', 
									anchor : '90%'
								},{
									html:'<a style="font-size:12px;padding-left:320px;" href="'+ctx+'/SWFUpload/bizDatebaseDes.xlsx">模板下载</a>'
								}]
							}]
						} ]
					} ]
				} ]
			} ],
			buttons : [ {
				text : '开始导入',
				iconCls:'icon-disk',
				id : 'beginUploadButton'
			}, {
				text : '取消',
				iconCls:'icon-cencel',
				id : 'cencelButton'
			} ]
		});
		excelWin.superclass.initComponent.call(this);
	}
});
