/**产生一个部门人员数**/
	function createEmployee(){
		 var dt1 = new Ext.tree.TreeLoader({dataUrl:'home/employer/getTreeData.do'});
		 var dtp = new Ext.tree.TreePanel({
	        	animate: true,
	        	frame: true,
	        	autoScroll: true,
	        	height: 270,
	        	width: 200,
	        	border: false,
	        	useArrows: false,
	        	trackMouseOver: false,
	        	rootVisible:false,
	        	lines: false,
	        	listeners: {
	            	'click': function (node) {
		 			 var ecode=(node.id);
		 			 if(ecode.substring(0,1)=='E'){
		 				 /**加载表单**/
		 				 Ext.Ajax.request({
		 					 url:'home/employer/loadFormPanel.do',
		 					 params:{ecode:ecode.substring(1)},
		 					 success:function(response,action){
		 						 var respText = Ext.util.JSON.decode(response.responseText);
		 						 Ext.getCmp("empsex").setValue(respText.sex);
		 						 Ext.getCmp("idCard").setValue(respText.idCard);
		 						 Ext.getCmp("birdthDay").setValue(respText.birthDay);
		 						 Ext.getCmp("propertyPeopleName").setValue(respText.departmentName);
		 						 Ext.getCmp("maritalStatus").setValue(respText.maritalStatus);
		 						 Ext.getCmp("jobPosition").setValue(respText.jobPosition);
		 						 Ext.getCmp("joinDate").setValue(respText.joinDate);
		 						 Ext.getCmp("professionalTitle").setValue(respText.professionalTitle);
		 						 departmentfield.setValue(respText.name);
		 						 Ext.getCmp('empCode').setValue(respText.code);
		 						 selectDepartmentMenu.hide();
		 					 }
		 				 });
		 			 }
	            }
	        },
	        loader: dt1
		 });
	  var droot = new Ext.tree.AsyncTreeNode({
	        id: '0',
	        text: 'root',
	        expanded: true
	    });
	  dtp.setRootNode(droot);

	  var selectDepartmentMenu = new Ext.menu.Menu({
	   	items: [dtp]
	  });
	/**部门下拉树(这个部门是单选部门)**/
	 var departmentfield = new Ext.form.TriggerField({
	        fieldLabel: '员工姓名',
	        name: 'name',
	        id:'departmentName',
	        onTriggerClick: function () {
	            if (this.menu == undefined) {
	                this.menu = selectDepartmentMenu;
	            }
	            this.menu.show(this.el, 'tl-bl?');//'tl-bl?'表示相对于下拉框显示的位置
	        }
	 });
	 return departmentfield;
}
	

	