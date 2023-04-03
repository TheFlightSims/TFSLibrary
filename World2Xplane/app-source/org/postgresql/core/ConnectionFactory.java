/*    */ package org.postgresql.core;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.util.Properties;
/*    */ import org.postgresql.core.v2.ConnectionFactoryImpl;
/*    */ import org.postgresql.core.v3.ConnectionFactoryImpl;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public abstract class ConnectionFactory {
/* 32 */   private static final Object[][] versions = new Object[][] { { "3", new ConnectionFactoryImpl() }, { "2", new ConnectionFactoryImpl() } };
/*    */   
/*    */   public static ProtocolConnection openConnection(String host, int port, String user, String database, Properties info, Logger logger) throws SQLException {
/* 57 */     String protoName = info.getProperty("protocolVersion");
/* 59 */     for (int i = 0; i < versions.length; i++) {
/* 61 */       String versionProtoName = (String)versions[i][0];
/* 62 */       if (protoName == null || protoName.equals(versionProtoName)) {
/* 65 */         ConnectionFactory factory = (ConnectionFactory)versions[i][1];
/* 66 */         ProtocolConnection connection = factory.openConnectionImpl(host, port, user, database, info, logger);
/* 67 */         if (connection != null)
/* 68 */           return connection; 
/*    */       } 
/*    */     } 
/* 71 */     throw new PSQLException(GT.tr("A connection could not be made using the requested protocol {0}.", protoName), PSQLState.CONNECTION_UNABLE_TO_CONNECT);
/*    */   }
/*    */   
/*    */   public abstract ProtocolConnection openConnectionImpl(String paramString1, int paramInt, String paramString2, String paramString3, Properties paramProperties, Logger paramLogger) throws SQLException;
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\ConnectionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */