/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.Driver;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ import org.postgresql.core.Field;
/*    */ import org.postgresql.jdbc2.AbstractJdbc2ResultSetMetaData;
/*    */ 
/*    */ abstract class AbstractJdbc4ResultSetMetaData extends AbstractJdbc2ResultSetMetaData {
/*    */   public AbstractJdbc4ResultSetMetaData(BaseConnection connection, Field[] fields) {
/* 21 */     super(connection, fields);
/*    */   }
/*    */   
/*    */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 26 */     throw Driver.notImplemented(getClass(), "isWrapperFor(Class<?>)");
/*    */   }
/*    */   
/*    */   public <T> T unwrap(Class<T> iface) throws SQLException {
/* 31 */     throw Driver.notImplemented(getClass(), "unwrap(Class<T>)");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\AbstractJdbc4ResultSetMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */