/**项目预算单管理**/
orderForm = Ext.extend(Ext.form.FormPanel,{
	 id: 'orderForm',
	 xtype: 'form', 
	 autoScroll:true,
	 frame: true,
	 buttonAlign:'center',
	 initComponent:function(){
		Ext.applyIf(this,{
			border:false,
			items:[{
		        buttonAlign: 'center',
		        autoHeight:true,
	            labelAlign: 'right',
	            defaults: {width: 210},
	            defaultType: 'textfield',
	            layout:'form',
		        items: [{
		            	name: 'id',
		            	hidden: true
	            	},{
	                    fieldLabel: '订单号',
	                    name: 'sn',
	                    allowBlank:false
	                }, 
	                	Ext.ux.form.ComboBox({
	                		fieldLabel: '客户名称',
	                		url: ctx+'/sys/user/getCombo.do',
	                		fields: [{name: 'code'}, {name: 'name'}],
	                		name: 'customer',
	                		//hiddenName: 'customer',
	                		editable: false
	                }), {
	                    fieldLabel: '收货电话',
	                    name: 'phone',
	                    allowBlank: false
	                }, {
	                    fieldLabel: '收货地址',
	                    name: 'address',
	                    allowBlank: false
	                }, {
	                    fieldLabel: '订单金额',
	                    xtype:'numberfield',
	                    name: 'price',
	                    allowBlank: false
	                }, {
	                    fieldLabel: '订单数量',
	                     xtype:'numberfield',
	                    name: 'quantity',
	                    allowBlank: false
	                }
	             	, {
	                	fieldLabel:'备注',
	                    xtype:'textarea',
	                    name: 'note',
	                    height: 50,
	                    width: 210
	                }
	            ],
		        buttons: [{
	    			id: 'btn_form_save',
		            iconCls: 'saveIcon',
		            text: '保存'
		        }, {
		        	id: 'btn_form_update',
		            iconCls: 'edit1Icon',
		            text: '更新',
		            handler: function(){
		        		
		            	Ext.ux.Form.submitForm(userForm, "${basePath}/wechar/order/update.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			
		            			userGrid.getStore().load();
		            			userWin.hide();
		            			userForm.getForm().reset();
	            			}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_cancle',
		            iconCls: 'deleteIcon',
		            text: '取消',
		            handler: function(){
		            	userForm.getForm().reset();
		            	userWin.hide();
		            }
		        }]
			}]
		});
	 	orderForm.superclass.initComponent.call(this);
	 }
})