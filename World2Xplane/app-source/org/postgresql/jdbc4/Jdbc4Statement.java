/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.sql.ParameterMetaData;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import java.util.Vector;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ import org.postgresql.core.BaseStatement;
/*    */ import org.postgresql.core.Field;
/*    */ import org.postgresql.core.Query;
/*    */ import org.postgresql.core.ResultCursor;
/*    */ 
/*    */ class Jdbc4Statement extends AbstractJdbc4Statement implements Statement {
/*    */   Jdbc4Statement(Jdbc4Connection c, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/* 25 */     super(c, rsType, rsConcurrency, rsHoldability);
/*    */   }
/*    */   
/*    */   protected Jdbc4Statement(Jdbc4Connection connection, String sql, boolean isCallable, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/* 30 */     super(connection, sql, isCallable, rsType, rsConcurrency, rsHoldability);
/*    */   }
/*    */   
/*    */   public ResultSet createResultSet(Query originalQuery, Field[] fields, Vector tuples, ResultCursor cursor) throws SQLException {
/* 36 */     Jdbc4ResultSet newResult = new Jdbc4ResultSet(originalQuery, (BaseStatement)this, fields, tuples, cursor, getMaxRows(), getMaxFieldSize(), getResultSetType(), getResultSetConcurrency(), getResultSetHoldability());
/* 39 */     newResult.setFetchSize(getFetchSize());
/* 40 */     newResult.setFetchDirection(getFetchDirection());
/* 41 */     return newResult;
/*    */   }
/*    */   
/*    */   public ParameterMetaData createParameterMetaData(BaseConnection conn, int[] oids) throws SQLException {
/* 46 */     return new Jdbc4ParameterMetaData(conn, oids);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\Jdbc4Statement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */