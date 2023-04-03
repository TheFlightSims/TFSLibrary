/*    */ package org.postgresql.ds.jdbc4;
/*    */ 
/*    */ import java.sql.SQLFeatureNotSupportedException;
/*    */ import java.util.logging.Logger;
/*    */ import org.postgresql.Driver;
/*    */ import org.postgresql.ds.jdbc23.AbstractJdbc23ConnectionPoolDataSource;
/*    */ 
/*    */ public class AbstractJdbc4ConnectionPoolDataSource extends AbstractJdbc23ConnectionPoolDataSource {
/*    */   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
/* 13 */     throw Driver.notImplemented(getClass(), "getParentLogger()");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ds\jdbc4\AbstractJdbc4ConnectionPoolDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */