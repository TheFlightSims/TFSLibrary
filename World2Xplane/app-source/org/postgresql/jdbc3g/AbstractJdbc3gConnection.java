/*    */ package org.postgresql.jdbc3g;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.util.Properties;
/*    */ import org.postgresql.core.TypeInfo;
/*    */ import org.postgresql.jdbc3.AbstractJdbc3Connection;
/*    */ 
/*    */ public abstract class AbstractJdbc3gConnection extends AbstractJdbc3Connection {
/*    */   public AbstractJdbc3gConnection(String host, int port, String user, String database, Properties info, String url) throws SQLException {
/* 22 */     super(host, port, user, database, info, url);
/* 24 */     TypeInfo types = getTypeInfo();
/* 25 */     if (haveMinimumServerVersion("8.3"))
/* 26 */       types.addCoreType("uuid", Integer.valueOf(2950), Integer.valueOf(1111), "java.util.UUID", Integer.valueOf(2951)); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc3g\AbstractJdbc3gConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */