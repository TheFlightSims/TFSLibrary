/*    */ package org.postgresql.core;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.sql.SQLWarning;
/*    */ import java.util.Vector;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class SetupQueryRunner {
/*    */   private static class SimpleResultHandler implements ResultHandler {
/*    */     private SQLException error;
/*    */     
/*    */     private Vector tuples;
/*    */     
/*    */     private final ProtocolConnection protoConnection;
/*    */     
/*    */     SimpleResultHandler(ProtocolConnection protoConnection) {
/* 34 */       this.protoConnection = protoConnection;
/*    */     }
/*    */     
/*    */     Vector getResults() {
/* 38 */       return this.tuples;
/*    */     }
/*    */     
/*    */     public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {
/* 42 */       this.tuples = tuples;
/*    */     }
/*    */     
/*    */     public void handleCommandStatus(String status, int updateCount, long insertOID) {}
/*    */     
/*    */     public void handleWarning(SQLWarning warning) {}
/*    */     
/*    */     public void handleError(SQLException newError) {
/* 54 */       if (this.error == null) {
/* 55 */         this.error = newError;
/*    */       } else {
/* 57 */         this.error.setNextException(newError);
/*    */       } 
/*    */     }
/*    */     
/*    */     public void handleCompletion() throws SQLException {
/* 61 */       if (this.error != null)
/* 62 */         throw this.error; 
/*    */     }
/*    */   }
/*    */   
/*    */   public static byte[][] run(ProtocolConnection protoConnection, String queryString, boolean wantResults) throws SQLException {
/* 67 */     QueryExecutor executor = protoConnection.getQueryExecutor();
/* 68 */     Query query = executor.createSimpleQuery(queryString);
/* 69 */     SimpleResultHandler handler = new SimpleResultHandler(protoConnection);
/* 71 */     int flags = 17;
/* 72 */     if (!wantResults)
/* 73 */       flags |= 0x6; 
/*    */     try {
/* 77 */       executor.execute(query, (ParameterList)null, handler, 0, 0, flags);
/*    */     } finally {
/* 81 */       query.close();
/*    */     } 
/* 84 */     if (!wantResults)
/* 85 */       return (byte[][])null; 
/* 87 */     Vector<byte[][]> tuples = handler.getResults();
/* 88 */     if (tuples == null || tuples.size() != 1)
/* 89 */       throw new PSQLException(GT.tr("An unexpected result was returned by a query."), PSQLState.CONNECTION_UNABLE_TO_CONNECT); 
/* 91 */     return tuples.elementAt(0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\SetupQueryRunner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */