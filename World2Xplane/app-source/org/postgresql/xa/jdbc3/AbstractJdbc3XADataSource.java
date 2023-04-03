/*    */ package org.postgresql.xa.jdbc3;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import javax.naming.Reference;
/*    */ import javax.naming.Referenceable;
/*    */ import javax.sql.XAConnection;
/*    */ import org.postgresql.Driver;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ import org.postgresql.ds.common.BaseDataSource;
/*    */ import org.postgresql.xa.PGXAConnection;
/*    */ import org.postgresql.xa.PGXADataSourceFactory;
/*    */ 
/*    */ public class AbstractJdbc3XADataSource extends BaseDataSource implements Referenceable {
/*    */   public XAConnection getXAConnection() throws SQLException {
/* 42 */     return getXAConnection(getUser(), getPassword());
/*    */   }
/*    */   
/*    */   public XAConnection getXAConnection(String user, String password) throws SQLException {
/* 57 */     Connection con = getConnection(user, password);
/* 58 */     return (XAConnection)new PGXAConnection((BaseConnection)con);
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 62 */     return "JDBC3 XA-enabled DataSource from " + Driver.getVersion();
/*    */   }
/*    */   
/*    */   protected Reference createReference() {
/* 69 */     return new Reference(getClass().getName(), PGXADataSourceFactory.class.getName(), null);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\xa\jdbc3\AbstractJdbc3XADataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */