package com.ht.sys.util;

import java.sql.Types;  
  

import org.hibernate.dialect.SQLServerDialect;  
import org.hibernate.type.StandardBasicTypes;
  
public class SqlServer2008Dialect extends SQLServerDialect {  
  
   public SqlServer2008Dialect() {  
        super();  
        registerHibernateType(Types.CHAR, StandardBasicTypes.STRING.getName());  
        registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());  
        registerHibernateType(Types.LONGNVARCHAR, StandardBasicTypes.STRING.getName());  
        registerHibernateType(Types.DECIMAL, StandardBasicTypes.DOUBLE.getName());
        registerHibernateType(Types.CLOB, StandardBasicTypes.STRING.getName());
    }  
   } 
