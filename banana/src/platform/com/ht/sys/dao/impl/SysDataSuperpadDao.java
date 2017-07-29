package com.ht.sys.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Repository;

import com.ht.sys.bean.SysDataSuperpad;

@Repository("sys_dataSuperpadDao")
public class SysDataSuperpadDao  extends BaseDaoImpl<SysDataSuperpad>{
	/**JDBC连接数据库**/
	public Connection testConnection(SysDataSuperpad sysDataSuperpad)throws Exception{
		String driverName ="";
		Connection connection=null;
		if("SQLServer".equals(sysDataSuperpad.getDataBaseType())){
			driverName="net.sourceforge.jtds.jdbc.Driver";
		}
		String dbURL="jdbc:jtds:sqlserver://"+sysDataSuperpad.getDataBaseAddress()+"/"+sysDataSuperpad.getDataBaseName()+";instance=";
		Class.forName(driverName);
		connection=DriverManager.getConnection(dbURL,sysDataSuperpad.getDataBaseUser(),sysDataSuperpad.getDataBasePassword());
		return connection;
	}
	/**根据不同表名 传输对应数据**/
	public boolean trantData(SysDataSuperpad sysDataSuperpad,String tables,String database)throws Exception{
		boolean res=false;
		String sql="";
		database=database+".";
		int i=0;
		for(String table:tables.split(",")){
			i++;
			/**部门数据同步**/
			if("sys_department".equals(table)){
				//sql+=" delete  from "+database+"."+table+" where f_united=0;";
				sql+=" insert into "+database+"."+table+" (f_id,f_version,f_code,f_name,f_state,f_unit_code,f_unit_name,f_united) " +
					 " select 'd"+i+"'+b.cDepCode,0,b.cDepCode,b.cDepName,'启用'," +
					 		" case when len(b.cDepCode)=3 then (select top 1 f_code from "+database+".sys_department where f_unit_code='root')" +
					 		" else (select cDepCode from Department where cDepCode = left(b.cDepcode,len(b.cDepcode)-3)) end pcode ," +
					 		" case when len(b.cDepCode)=3 then (select top 1 f_name from "+database+".sys_department where f_unit_code='root')" +
					 		" else (select cDepName from Department where cDepCode = left(b.cDepcode,len(b.cDepcode)-3))" +
					 		" end pname,0" +
					 " from Department b where b.cDepCode not in (select f_code from "+database+"."+table+");";
			}
			/**用户数据同步**/
			if("sys_user".equals(table)){
				//sql+=" delete from "+database+"."+table+" where f_user_name<>'admin';";
				sql+=" insert into "+database+"."+table+" (f_id,f_version,f_code,f_name,f_department_code,f_department_name,f_password,f_role_code," +
					 "f_role_name,f_state,f_user_name,f_data_auth,f_data_auth_Code )"+
					 " select 'u"+i+"'+cPersonCode,0,cPersonCode,cPersonName,cDepCode,(select top 1 f_name from "+database+".sys_department where f_code=cDepCode)," +
					 "'0000','1002','个人用户','启用',cPersonCode,'本人','1004' from person" +
					 " where cPersonCode not in (select f_code from "+database+"."+table+");";
			}
			/**供应商数据同步**/
			if("budget_supplier_record".equals(table)){
				//sql+=" delete from "+database+"."+table+";";
				sql+=" insert into "+database+"."+table+" (f_id,f_version,f_code,f_company_name,f_nature,f_status) "+
					 " select 's"+i+"'+cVencode,0,cVencode,cVenName,cVenAbbName,0 from vendor" +
					 " where cVencode not in (select f_code from "+database+"."+table+");";
			}
			/**客户档案同步**/
			if("budget_client_record".equals(table)){
				//sql+=" delete from "+database+"."+table+";";
				sql+=" insert into "+database+"."+table+" (f_id,f_version,f_code,f_company_name,f_shortened_form,f_status)" +
					 " select 'c"+i+"'+cCusCode,0,cCusCode,cCusName,cCusAbbName,0 from customer" +
					 " where cCusCode not in (select f_code from "+database+"."+table+");";
			}
			/**会计科目同步**/
			if("budget_accounting_subject".equals(table)){
				//sql+=" delete from "+database+"."+table+";";
				sql+=" insert into "+database+"."+table+" (f_id,f_version,f_adjust_accounts,f_code,f_count_unit,f_name,f_parent_code,f_parent_name,f_state) "+
				 " select top 1 'root1',0,'是','account','','会计科目','root','会计科目',null from code  where  'account' not in (select f_code from "+database+"."+table+")" +
				 " union select distinct 'a'"+"+cclass_engl,0,'是',cclass_engl,'',cclass,'account','会计科目','启用' from code where cclass_engl not in (select f_code from "+database+"."+table+")" +
				 " union select 'a"+i+"'+ccode,0,'是',ccode,'元',ccode_name,cclass_engl,cclass,'启用' from code  where ccode not in (select f_code from "+database+"."+table+");";
				
			}
			/**现金流量同步**/
			if("budget_flows".equals(table)){
				//sql+=" delete from "+database+"."+table+";";
				sql+=" insert into "+database+"."+table+" (f_id,f_version,f_direction,f_flows_name,f_flows_code,f_status)" +
					 " select 'f"+i+"'+citemcode,0,cDirection,citemname,citemcode,0 from fitemss98" +
					 " where citemcode not in (select f_flows_code from "+database+"."+table+");";
			}
			/**项目分类同步**/
			if("budget_project_subject".equals(table)){
				//sql+=" delete from "+database+"."+table+" where f_parent_code <>'root';";
				sql+=" insert into "+database+"."+table+" (f_id,f_version,f_code,f_name,f_parent_code,f_parent_name) " +
					 " select "+i+"+b.i_id,0,b.cItemCcode,b.cItemCname, " +
					 " case when len(b.cItemCcode)=3 then (select top 1 f_code from "+database+".budget_project_subject where f_parent_code='root')" +
					 " else left(b.cItemCcode,len(b.cItemCcode)-3) end pcode," +
					 " case when len(b.cItemCcode)=3 then (select top 1 f_name from "+database+".budget_project_subject where f_parent_code='root')" +
					 " else (select top 1 cItemCname from fitemss00class where cItemCcode=left(b.cItemCcode,len(b.cItemCcode)-3)) end pname " +
					 " from fitemss00class b where cItemCcode not in (select f_code from "+database+"."+table+");";
			}
			/**项目档案同步**/
			if("budget_item_archives".equals(table)){
				sql+="delete from "+database+".budget_spending;";
				sql+="delete from "+database+".budget_collection;";
				sql+="delete from "+database+"."+table+";";
				sql+=" insert into "+database+"."+table+"(f_id,f_code,f_name,f_version,f_category_code,f_category_name,f_project_status,f_sales)" +
					 " select 'a"+i+"'+a.citemcode,a.citemcode,a.citemname,0,b.citemCcode,b.citemCname ,case when a.bclose=0 then '立项' else '项目关闭' end,0.00 from fitemss00 a" +
					 " left join fitemss00class b on b.citemCcode=a.citemccode" +
					 " where a.citemccode not in (select f_code from "+database+"."+table+");";
			}
		}
		
		Connection conn=this.testConnection(sysDataSuperpad);
		conn.setAutoCommit(false);
		PreparedStatement psmt=conn.prepareStatement(sql);
		try{
			psmt.addBatch();
			psmt.executeBatch();
			conn.commit();
			res=true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}
		psmt.close();
		conn.close();
		return res;
	}
}
