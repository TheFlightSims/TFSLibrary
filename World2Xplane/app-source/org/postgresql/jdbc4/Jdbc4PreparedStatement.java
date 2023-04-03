/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ class Jdbc4PreparedStatement extends Jdbc4Statement implements PreparedStatement {
/*    */   Jdbc4PreparedStatement(Jdbc4Connection connection, String sql, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/* 18 */     this(connection, sql, false, rsType, rsConcurrency, rsHoldability);
/*    */   }
/*    */   
/*    */   protected Jdbc4PreparedStatement(Jdbc4Connection connection, String sql, boolean isCallable, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/* 23 */     super(connection, sql, isCallable, rsType, rsConcurrency, rsHoldability);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\Jdbc4PreparedStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */