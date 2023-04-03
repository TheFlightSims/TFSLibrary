/*    */ package org.postgresql.jdbc3g;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.jdbc3.AbstractJdbc3Connection;
/*    */ import org.postgresql.jdbc3.AbstractJdbc3Statement;
/*    */ 
/*    */ public abstract class AbstractJdbc3gStatement extends AbstractJdbc3Statement {
/*    */   public AbstractJdbc3gStatement(AbstractJdbc3Connection c, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/* 23 */     super(c, rsType, rsConcurrency, rsHoldability);
/*    */   }
/*    */   
/*    */   public AbstractJdbc3gStatement(AbstractJdbc3Connection connection, String sql, boolean isCallable, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/* 28 */     super(connection, sql, isCallable, rsType, rsConcurrency, rsHoldability);
/*    */   }
/*    */   
/*    */   public void setObject(int parameterIndex, Object x) throws SQLException {
/* 33 */     if (x instanceof java.util.UUID && this.connection.haveMinimumServerVersion("8.3")) {
/* 35 */       setString(parameterIndex, x.toString(), 2950);
/*    */     } else {
/* 37 */       super.setObject(parameterIndex, x);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
/* 43 */     if (targetSqlType == 1111 && x instanceof java.util.UUID && this.connection.haveMinimumServerVersion("8.3")) {
/* 45 */       setString(parameterIndex, x.toString(), 2950);
/*    */     } else {
/* 47 */       super.setObject(parameterIndex, x, targetSqlType, scale);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc3g\AbstractJdbc3gStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */