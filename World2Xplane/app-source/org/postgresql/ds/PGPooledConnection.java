/*    */ package org.postgresql.ds;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import javax.sql.ConnectionEvent;
/*    */ import javax.sql.PooledConnection;
/*    */ import org.postgresql.ds.jdbc4.AbstractJdbc4PooledConnection;
/*    */ 
/*    */ public class PGPooledConnection extends AbstractJdbc4PooledConnection implements PooledConnection {
/*    */   public PGPooledConnection(Connection con, boolean autoCommit, boolean isXA) {
/* 33 */     super(con, autoCommit, isXA);
/*    */   }
/*    */   
/*    */   public PGPooledConnection(Connection con, boolean autoCommit) {
/* 38 */     this(con, autoCommit, false);
/*    */   }
/*    */   
/*    */   protected ConnectionEvent createConnectionEvent(SQLException sqle) {
/* 43 */     return new ConnectionEvent(this, sqle);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ds\PGPooledConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */