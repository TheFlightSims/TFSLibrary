/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.sql.Blob;
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ 
/*    */ public class Jdbc4Blob extends AbstractJdbc4Blob implements Blob {
/*    */   public Jdbc4Blob(BaseConnection conn, long oid) throws SQLException {
/* 20 */     super(conn, oid);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\Jdbc4Blob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */