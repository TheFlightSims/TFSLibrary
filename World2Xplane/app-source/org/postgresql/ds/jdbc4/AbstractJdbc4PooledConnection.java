/*    */ package org.postgresql.ds.jdbc4;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLFeatureNotSupportedException;
/*    */ import java.util.logging.Logger;
/*    */ import javax.sql.StatementEventListener;
/*    */ import org.postgresql.Driver;
/*    */ import org.postgresql.ds.jdbc23.AbstractJdbc23PooledConnection;
/*    */ 
/*    */ public abstract class AbstractJdbc4PooledConnection extends AbstractJdbc23PooledConnection {
/*    */   public AbstractJdbc4PooledConnection(Connection con, boolean autoCommit, boolean isXA) {
/* 23 */     super(con, autoCommit, isXA);
/*    */   }
/*    */   
/*    */   public void removeStatementEventListener(StatementEventListener listener) {}
/*    */   
/*    */   public void addStatementEventListener(StatementEventListener listener) {}
/*    */   
/*    */   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
/* 36 */     throw Driver.notImplemented(getClass(), "getParentLogger()");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ds\jdbc4\AbstractJdbc4PooledConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */