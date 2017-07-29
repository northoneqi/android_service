Ext.BLANK_IMAGE_URL = ctx + "/ext/resources/images/default/s.gif";
var fatherId = 0;
var page_size = 20;
providerContractEditWindow = Ext
		.extend(
				Ext.Window,
				{
					title : '供应商合同',
					width : '90%',
					height : 432,
					layout : 'fit',
					modal : true,
					autoScroll : true,
					iconCls : 'icon-window',
					constrainHeader : true,// 是否能移出界面
					resizable : true,// 窗口是否能改变大小
					buttonAlign : 'center',
					initComponent : function() {
						Ext
								.applyIf(
										this,
										{
											layout : 'form',
											border : false,
											items : [ {
												xtype : 'form',
												layout : 'form',
												id : 'saveFormPanel',
												autoScroll : true,
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
														autoScroll : true,
														animCollapse : true,
														frame : true,
														width : 550,
														height : 340,
														layout : 'form',
														bodyStyle : 'padding-top:6px;',
														labelAlign : 'right',
														frame : true,
														anchor : '99%',
														items : [ {
															layout : 'column', // 定义该元素为布局为列布局方式
															items : [
																	{
																		xtype : 'hidden',
																		id : 'providerContractId',
																		name : 'providerContractId'
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '<span style="color:red">*</span>合同编号',
																			name : 'contractCode',
																			emptyText : '系统自动产生',
																			style : 'background-color: #F6F6F6; background-image: none;',
																			readOnly : true,
																			allowBlank : true
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '<span style="color:red">*</span>合同名称',
																			allowBlank : false,
																			name : 'contractName',
																			xtype : 'textfield'
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '<span style="color:red">*</span>签订日期',
																			allowBlank : false,
																			name : 'signed',
																			xtype : 'datefield',
																			format : 'Y-m-d'
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '<span style="color:red">*</span>供应商名称',
																			allowBlank : false,
																			name : 'providerName',
																			xtype : 'textfield'
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '业务员',
																			allowBlank : true,
																			name : 'salesman',
																			xtype : 'textfield'
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '录入人',
																			allowBlank : true,
																			name : 'recordPeople',
																			xtype : 'textfield'
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '<span style="color:red">*</span>生效日期',
																			allowBlank : false,
																			name : 'effectiveDate',
																			id : 'effectiveDateField',
																			xtype : 'datefield',
																			format : 'Y-m-d',
																			listeners : {
																				change : function(
																						field,
																						val,
																						oldVal) {
																					var failDate = Ext
																							.getCmp(
																									"failDateField")
																							.getValue();
																					if (!Ext
																							.isEmpty(failDate)) {
																						if (failDate > val) {
																							Ext
																									.getCmp(
																											"remainingDaysField")
																									.setValue(
																											failDate
																													.getElapsed(val)
																													/ (24 * 3600 * 1000));
																						} else {
																							Ext
																									.getCmp(
																											"effectiveDateField")
																									.setValue(
																											failDate);
																							Ext
																									.getCmp(
																											"remainingDaysField")
																									.setValue(
																											1);
																						}
																					}

																				}
																			}
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ Ext.ux.form
																				.ComboBox({
																					editable : false,
																					allowBlank : false,
																					id : 'pickTypeCombo',
																					hiddenName : 'pickType',
																					fieldLabel : '<span style="color:red">*</span>提货方式',
																					fields : [
																							{
																								name : 'name'
																							},
																							{
																								name : 'name'
																							} ],
																					root : 'data',
																					autoLoad : true,
																					url : 'sys/dict/getDictByCode.do?code=1013'
																				}) ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ Ext.ux.form
																				.ComboBox({
																					editable : false,
																					allowBlank : false,
																					id : 'payTypeCombo',
																					hiddenName : 'payType',
																					fieldLabel : '<span style="color:red">*</span>付款方式',
																					fields : [
																							{
																								name : 'name'
																							},
																							{
																								name : 'name'
																							} ],
																					root : 'data',
																					autoLoad : true,
																					url : 'sys/dict/getDictByCode.do?code=1012'
																				}) ]
																	},

																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '<span style="color:red">*</span>失效日期',
																			allowBlank : false,
																			name : 'failDate',
																			id : 'failDateField',
																			xtype : 'datefield',
																			format : 'Y-m-d',
																			listeners : {
																				change : function(
																						field,
																						val,
																						oldVal) {
																					var effectiveDateField = Ext
																							.getCmp(
																									"effectiveDateField")
																							.getValue();
																					if (!Ext
																							.isEmpty(effectiveDateField)) {
																						if (effectiveDateField < val) {
																							Ext
																									.getCmp(
																											"remainingDaysField")
																									.setValue(
																											val
																													.getElapsed(effectiveDateField)
																													/ (24 * 3600 * 1000));
																						} else {
																							// alert("您输入的日期不符合");
																							Ext
																									.getCmp(
																											"failDateField")
																									.setValue(
																											effectiveDateField);
																							Ext
																									.getCmp(
																											"remainingDaysField")
																									.setValue(
																											1);
																						}
																					}
																				}
																			}
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ Ext.ux.form
																				.ComboBox({
																					editable : false,
																					allowBlank : false,
																					id : 'settleAccordCombo',
																					hiddenName : 'settleAccord',
																					fieldLabel : '<span style="color:red">*</span>结算依据',
																					fields : [
																							{
																								name : 'name'
																							},
																							{
																								name : 'name'
																							} ],
																					root : 'data',
																					autoLoad : true,
																					url : 'sys/dict/getDictByCode.do?code=1014'
																				}) ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ Ext.ux.form
																				.ComboBox({
																					editable : false,
																					allowBlank : false,
																					id : 'prepaymentTypeCombo',
																					hiddenName : 'prepaymentType',
																					fieldLabel : '<span style="color:red">*</span>预付款方式',
																					fields : [
																							{
																								name : 'name'
																							},
																							{
																								name : 'name'
																							} ],
																					root : 'data',
																					autoLoad : true,
																					url : 'sys/dict/getDictByCode.do?code=1015'
																				}) ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '<span style="color:red">*</span>预付款',
																			allowBlank : false,
																			name : 'prepayment',
																			xtype : 'textfield'
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ Ext.ux.form
																				.ComboBox({
																					editable : false,
																					allowBlank : false,
																					id : 'settleTypeCombo',
																					hiddenName : 'settleType',
																					fieldLabel : '<span style="color:red">*</span>结算方式',
																					fields : [
																							{
																								name : 'name'
																							},
																							{
																								name : 'name'
																							} ],
																					root : 'data',
																					autoLoad : true,
																					url : 'sys/dict/getDictByCode.do?code=1016'
																				}) ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ Ext.ux.form
																				.ComboBox({
																					editable : false,
																					allowBlank : false,
																					id : 'arrivalTypeCombo',
																					hiddenName : 'arrivalType',
																					fieldLabel : '<span style="color:red">*</span>到货方式',
																					fields : [
																							{
																								name : 'name'
																							},
																							{
																								name : 'name'
																							} ],
																					root : 'data',
																					autoLoad : true,
																					url : 'sys/dict/getDictByCode.do?code=1017'
																				}) ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '<span style="color:red">*</span>到货天数',
																			allowBlank : false,
																			name : 'arrivalDays',
																			xtype : 'textfield'
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '<span style="color:red">*</span>订货周期',
																			allowBlank : false,
																			name : 'orderCycle',
																			xtype : 'textfield'
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '<span style="color:red">*</span>预警天数',
																			allowBlank : false,
																			name : 'warningDays',
																			xtype : 'textfield'
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '<span style="color:red">*</span>剩余天数',
																			allowBlank : false,
																			name : 'remainingDays',
																			emptyText : '系统自动算出',
																			style : 'background-color: #F6F6F6; background-image: none;',
																			id : 'remainingDaysField',
																			readOnly : true,
																			xtype : 'textfield',
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ Ext.ux.form
																				.ComboBox({
																					editable : false,
																					allowBlank : false,
																					id : 'operationTypeCombo',
																					hiddenName : 'operationType',
																					fieldLabel : '<span style="color:red">*</span>经营方式',
																					fields : [
																							{
																								name : 'name'
																							},
																							{
																								name : 'name'
																							} ],
																					root : 'data',
																					autoLoad : true,
																					url : 'sys/dict/getDictByCode.do?code=1018'
																				}) ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		border : false,
																		defaults : {
																			anchor : '-10',
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 60
																		},
																		labelAlign : 'right',
																		items : [ {
																			fieldLabel : '<span style="color:red">*</span>合同扫描件',
																			allowBlank : false,
																			name : 'contractScan',
																			xtype : 'textfield'
																		} ]
																	},
																	{
																		columnWidth : .33,
																		layout : 'form',
																		columnWidth : 1,
																		xtype : 'container',
																		defaults : {
																			allowBlank : true,
																			xtype : 'textfield',
																			msgTarget : 'side',
																			labelWidth : 80,
																			anchor : '98%'
																		},
																		items : [ {
																			xtype : 'textarea',
																			name : 'remarks',
																			fieldLabel : '备注',
																			height : 30
																		} ]
																	},
																	{
																		columnWidth : .99,
																		layout : 'form',
																		border : false,
																		labelAlign : 'right',
																		bodyStyle : 'padding-top:6px;',
																		items : [ {
																			tbar : [
																					{
																						text : '增加',
																						iconCls : 'addIcon',
																						handler : function() {
																							if (Ext
																									.getCmp(
																											"providerContractId")
																									.getValue() != null)
																								fatherId = Ext
																										.getCmp(
																												"providerContractId")
																										.getValue();
																							var win2 = new contractProductDetailEditWindow();
																							win2
																									.show();
																							var saveButtion = Ext
																									.getCmp("saveButtonCh");
																							if (saveButtion) {
																								/** 保存按钮点击事件 * */
																								saveButtion
																										.on(
																												"click",
																												function() {
																													var formPanel = Ext
																															.getCmp(
																																	"saveFormPanelCh")
																															.getForm();
																													formPanel
																															.findField(
																																	"providerContractIds")
																															.setValue(
																																	fatherId);
																													if (formPanel
																															.isValid()) {
																														formPanel
																																.submit({
																																	waitMsg : '正在保存数据,请稍等...',
																																	waitTitle : '系统提示',// 标题
																																	method : 'POST',
																																	url : ctx
																																			+ "/supplyChain/contractProductDetail/save.do",
																																	params : formPanel.getValue,
																																	success : function(
																																			form,
																																			action) {
																																		var mid = action.result.msg;
																																		if (mid != null) {
																																			fatherId = mid;
																																			win2
																																					.close();
																																			grid2
																																					.getStore()
																																					.setBaseParam(
																																							"paraentId",
																																							mid);
																																			grid2
																																					.getStore()
																																					.load();
																																			var formPanelP = Ext
																																					.getCmp(
																																							"saveFormPanel")
																																					.getForm();
																																			formPanelP
																																					.findField(
																																							"providerContractId")
																																					.setValue(
																																							mid);
																																			grid
																																					.getStore()
																																					.load();
																																		} else {
																																			win2
																																					.close();
																																			grid2
																																					.getStore()
																																					.load();
																																		}
																																	},
																																	failure : function(
																																			form,
																																			action) {
																																		Ext.Msg
																																				.alert(
																																						"系统提示",
																																						"添加失败");
																																	}
																																});
																													}
																												});
																							}
																							/**
																							 * 取得取消按钮
																							 * 定义返回事件*
																							 */
																							var cencelButton = Ext
																									.getCmp("cencelButtonCh");
																							cencelButton
																									.on(
																											"click",
																											function() {
																												win2
																														.close();
																											});
																						}
																					},
																					'-',
																					{
																						text : '修改',
																						iconCls : 'edit1Icon',
																						handler : function() {
																							var win2 = new contractProductDetailEditWindow();
																							var rowid = getCheckRowId2();
																							var c = rowid
																									.split(",");
																							if (rowid != ""
																									&& c.length == 1) {
																								win2
																										.show();
																								Ext
																										.getCmp(
																												"contractProductDetailId")
																										.setValue(
																												rowid);
																								loadFormCh(rowid);// 加载form
																							} else if (rowid != "") {
																								Ext.Msg
																										.alert(
																												"系统提示",
																												"请选择您要修改的记录");
																							} else {
																								Ext.Msg
																										.alert(
																												"系统提示",
																												"一次只能修改一条记录");
																							}
																							var saveButtion = Ext
																									.getCmp("saveButtonCh");
																							if (saveButtion) {
																								saveButtion
																										.on(
																												"click",
																												function() {
																													var formPanel = Ext
																															.getCmp(
																																	"saveFormPanelCh")
																															.getForm();
																													// alert(formPanel.getFieldValues()
																													// )
																													if (formPanel
																															.isValid()) {
																														formPanel
																																.submit({
																																	waitMsg : '正在修改数据,请稍等...',
																																	waitTitle : '系统提示',// 标题
																																	method : 'POST',
																																	url : ctx
																																			+ "/supplyChain/contractProductDetail/update.do",
																																	params : formPanel.getValue,
																																	success : function(
																																			form,
																																			action) {
																																		grid2
																																				.getStore()
																																				.load();
																																		Ext.Msg
																																				.alert(
																																						"系统提示",
																																						"修改成功");
																																		win2
																																				.close();
																																	},
																																	failure : function(
																																			form,
																																			action) {
																																		MsgTip
																																				.msg(
																																						'系统提示',
																																						'修改失败',
																																						true);
																																		Ext.Msg
																																				.alert(
																																						"系统提示",
																																						"修改失败");
																																	}
																																});
																													}
																												});
																							}
																							/**
																							 * 取得取消按钮
																							 * 定义返回事件*
																							 */
																							var cencelButton = Ext
																									.getCmp("cencelButtonCh");
																							cencelButton
																									.on(
																											"click",
																											function() {
																												win2
																														.close();
																											});
																						}
																					},
																					'-',
																					{
																						text : '删除',
																						iconCls : 'deleteIcon',
																						handler : function() {
																							var rowid = getCheckRowId2();
																							if (rowid != "") {
																								Ext.Msg
																										.confirm(
																												'系统提示',
																												'您确定要删除吗?',
																												function(
																														opt) {
																													if (opt == 'yes') {
																														Ext.Ajax
																																.request({
																																	url : ctx
																																			+ "/supplyChain/contractProductDetail/delete.do",
																																	params : {
																																		ids : rowid
																																	},
																																	success : function(
																																			response) {
																																		grid2
																																				.getStore()
																																				.load();
																																		Ext.Msg
																																				.alert(
																																						"系统提示",
																																						"删除成功");
																																	}
																																});
																													}
																												});
																							} else {
																								Ext.Msg
																										.alert(
																												'系统提示',
																												'请选择您要删除的记录');
																							}
																						}
																					} ],
																			region : 'center',
																			layout : 'fit',
																			items : grid2,
																			bbar : new Ext.PagingToolbar(
																					{
																						pageSize : page_size,
																						store : grid2
																								.getStore(),
																						displayInfo : true,
																						displayMsg : '每页显示'
																								+ page_size
																								+ '条记录，当前显示{0}到{1}条，共{2}条。',
																						emptyMsg : '没有记录'
																					})
																		} ]
																	}

															]
														} ]
													} ]
												} ],
												buttons : [ {
													text : '保存',
													id : 'saveButton',
													iconCls : 'icon-disk'
												}, {
													text : '取消',
													id : 'cencelButton',
													iconCls : 'icon-cencel'
												} ]
											} ]
										});

						providerContractEditWindow.superclass.initComponent
								.call(this);
					}
				});
