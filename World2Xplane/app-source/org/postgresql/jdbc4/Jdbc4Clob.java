/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.sql.Clob;
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ 
/*    */ public class Jdbc4Clob extends AbstractJdbc4Clob implements Clob {
/*    */   public Jdbc4Clob(BaseConnection conn, long oid) throws SQLException {
/* 18 */     super(conn, oid);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\Jdbc4Clob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */