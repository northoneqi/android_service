Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
Ext.QuickTips.init();
errorMsgWin = Ext.extend(Ext.Window, {
	title : 'EXCEL导入时数据验证失败',
	iconCls:'icon-window',
	width : 790,
	height : 455,
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
				height : 385,
				width :780,
				id : 'errorFormPanel',
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
						autoScroll:true,
						frame : true,
						width : 760,
						height : 381,
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
								bodyStyle:'padding-top:1px;',
								items : [{
									html:'<div id="errorMsg"></div>'
								}]
							}]
						} ]
					} ]
				} ]
			} ],
			buttons : [ {
				text : '关闭',
				iconCls:'icon-cencel',
				id : 'cencelButton'
			} ]
		});
		errorMsgWin.superclass.initComponent.call(this);
	}
});
