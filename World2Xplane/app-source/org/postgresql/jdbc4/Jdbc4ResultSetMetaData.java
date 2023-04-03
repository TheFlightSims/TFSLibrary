/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.sql.ResultSetMetaData;
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ import org.postgresql.core.Field;
/*    */ 
/*    */ public class Jdbc4ResultSetMetaData extends AbstractJdbc4ResultSetMetaData implements ResultSetMetaData {
/*    */   public Jdbc4ResultSetMetaData(BaseConnection connection, Field[] fields) {
/* 19 */     super(connection, fields);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\Jdbc4ResultSetMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */