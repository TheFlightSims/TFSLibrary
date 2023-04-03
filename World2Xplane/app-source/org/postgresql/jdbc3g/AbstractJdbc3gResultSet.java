/*    */ package org.postgresql.jdbc3g;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.util.UUID;
/*    */ import java.util.Vector;
/*    */ import org.postgresql.core.BaseStatement;
/*    */ import org.postgresql.core.Field;
/*    */ import org.postgresql.core.Query;
/*    */ import org.postgresql.core.ResultCursor;
/*    */ import org.postgresql.jdbc3.AbstractJdbc3ResultSet;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public abstract class AbstractJdbc3gResultSet extends AbstractJdbc3ResultSet {
/*    */   public AbstractJdbc3gResultSet(Query originalQuery, BaseStatement statement, Field[] fields, Vector tuples, ResultCursor cursor, int maxRows, int maxFieldSize, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/* 28 */     super(originalQuery, statement, fields, tuples, cursor, maxRows, maxFieldSize, rsType, rsConcurrency, rsHoldability);
/*    */   }
/*    */   
/*    */   protected Object getUUID(String data) throws SQLException {
/*    */     UUID uuid;
/*    */     try {
/* 36 */       uuid = UUID.fromString(data);
/* 37 */     } catch (IllegalArgumentException iae) {
/* 38 */       throw new PSQLException(GT.tr("Invalid UUID data."), PSQLState.INVALID_PARAMETER_VALUE, iae);
/*    */     } 
/* 41 */     return uuid;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc3g\AbstractJdbc3gResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */