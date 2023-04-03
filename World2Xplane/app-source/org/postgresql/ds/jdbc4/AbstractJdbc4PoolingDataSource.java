/*    */ package org.postgresql.ds.jdbc4;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.sql.SQLFeatureNotSupportedException;
/*    */ import java.util.logging.Logger;
/*    */ import org.postgresql.Driver;
/*    */ import org.postgresql.ds.jdbc23.AbstractJdbc23PoolingDataSource;
/*    */ 
/*    */ public abstract class AbstractJdbc4PoolingDataSource extends AbstractJdbc23PoolingDataSource {
/*    */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 22 */     throw Driver.notImplemented(getClass(), "isWrapperFor(Class<?>)");
/*    */   }
/*    */   
/*    */   public <T> T unwrap(Class<T> iface) throws SQLException {
/* 27 */     throw Driver.notImplemented(getClass(), "unwrap(Class<T>)");
/*    */   }
/*    */   
/*    */   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
/* 32 */     throw Driver.notImplemented(getClass(), "getParentLogger()");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ds\jdbc4\AbstractJdbc4PoolingDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */