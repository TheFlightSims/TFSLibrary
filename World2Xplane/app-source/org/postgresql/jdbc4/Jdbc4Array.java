/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.sql.Array;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Map;
/*    */ import org.postgresql.Driver;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ import org.postgresql.jdbc2.AbstractJdbc2Array;
/*    */ 
/*    */ public class Jdbc4Array extends AbstractJdbc2Array implements Array {
/*    */   public Jdbc4Array(BaseConnection conn, int oid, String fieldString) throws SQLException {
/* 21 */     super(conn, oid, fieldString);
/*    */   }
/*    */   
/*    */   public Object getArray(Map<String, Class<?>> map) throws SQLException {
/* 26 */     return getArrayImpl(map);
/*    */   }
/*    */   
/*    */   public Object getArray(long index, int count, Map<String, Class<?>> map) throws SQLException {
/* 31 */     return getArrayImpl(index, count, map);
/*    */   }
/*    */   
/*    */   public ResultSet getResultSet(Map<String, Class<?>> map) throws SQLException {
/* 36 */     return getResultSetImpl(map);
/*    */   }
/*    */   
/*    */   public ResultSet getResultSet(long index, int count, Map<String, Class<?>> map) throws SQLException {
/* 41 */     return getResultSetImpl(index, count, map);
/*    */   }
/*    */   
/*    */   public void free() throws SQLException {
/* 46 */     throw Driver.notImplemented(getClass(), "free()");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\Jdbc4Array.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */