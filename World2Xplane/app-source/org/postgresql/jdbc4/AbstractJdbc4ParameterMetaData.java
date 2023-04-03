/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.Driver;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ import org.postgresql.jdbc3.AbstractJdbc3ParameterMetaData;
/*    */ 
/*    */ public abstract class AbstractJdbc4ParameterMetaData extends AbstractJdbc3ParameterMetaData {
/*    */   public AbstractJdbc4ParameterMetaData(BaseConnection connection, int[] oids) {
/* 20 */     super(connection, oids);
/*    */   }
/*    */   
/*    */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 25 */     throw Driver.notImplemented(getClass(), "isWrapperFor(Class<?>)");
/*    */   }
/*    */   
/*    */   public <T> T unwrap(Class<T> iface) throws SQLException {
/* 30 */     throw Driver.notImplemented(getClass(), "unwrap(Class<T>)");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\AbstractJdbc4ParameterMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */