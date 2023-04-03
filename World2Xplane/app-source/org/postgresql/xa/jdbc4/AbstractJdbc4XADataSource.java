/*    */ package org.postgresql.xa.jdbc4;
/*    */ 
/*    */ import java.sql.SQLFeatureNotSupportedException;
/*    */ import java.util.logging.Logger;
/*    */ import org.postgresql.Driver;
/*    */ import org.postgresql.xa.jdbc3.AbstractJdbc3XADataSource;
/*    */ 
/*    */ public class AbstractJdbc4XADataSource extends AbstractJdbc3XADataSource {
/*    */   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
/* 22 */     throw Driver.notImplemented(getClass(), "getParentLogger()");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\xa\jdbc4\AbstractJdbc4XADataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */