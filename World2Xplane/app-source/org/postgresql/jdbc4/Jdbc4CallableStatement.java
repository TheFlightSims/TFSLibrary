/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.sql.CallableStatement;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Map;
/*    */ 
/*    */ class Jdbc4CallableStatement extends Jdbc4PreparedStatement implements CallableStatement {
/*    */   Jdbc4CallableStatement(Jdbc4Connection connection, String sql, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/* 19 */     super(connection, sql, true, rsType, rsConcurrency, rsHoldability);
/* 20 */     if (!connection.haveMinimumServerVersion("8.1") || connection.getProtocolVersion() == 2)
/* 24 */       this.adjustIndex = this.outParmBeforeFunc; 
/*    */   }
/*    */   
/*    */   public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
/* 30 */     return getObjectImpl(i, map);
/*    */   }
/*    */   
/*    */   public Object getObject(String s, Map<String, Class<?>> map) throws SQLException {
/* 35 */     return getObjectImpl(s, map);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\Jdbc4CallableStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */