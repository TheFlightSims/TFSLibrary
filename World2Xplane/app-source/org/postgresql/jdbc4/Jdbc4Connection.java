/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.sql.Array;
/*    */ import java.sql.Blob;
/*    */ import java.sql.CallableStatement;
/*    */ import java.sql.Clob;
/*    */ import java.sql.Connection;
/*    */ import java.sql.DatabaseMetaData;
/*    */ import java.sql.NClob;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.SQLClientInfoException;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.SQLFeatureNotSupportedException;
/*    */ import java.sql.SQLXML;
/*    */ import java.sql.Statement;
/*    */ import java.sql.Struct;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class Jdbc4Connection extends AbstractJdbc4Connection implements Connection {
/*    */   public Jdbc4Connection(String host, int port, String user, String database, Properties info, String url) throws SQLException {
/* 24 */     super(host, port, user, database, info, url);
/*    */   }
/*    */   
/*    */   public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/* 29 */     checkClosed();
/* 30 */     Jdbc4Statement s = new Jdbc4Statement(this, resultSetType, resultSetConcurrency, resultSetHoldability);
/* 31 */     s.setPrepareThreshold(getPrepareThreshold());
/* 32 */     return s;
/*    */   }
/*    */   
/*    */   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/* 38 */     checkClosed();
/* 39 */     Jdbc4PreparedStatement s = new Jdbc4PreparedStatement(this, sql, resultSetType, resultSetConcurrency, resultSetHoldability);
/* 40 */     s.setPrepareThreshold(getPrepareThreshold());
/* 41 */     return s;
/*    */   }
/*    */   
/*    */   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
/* 46 */     checkClosed();
/* 47 */     Jdbc4CallableStatement s = new Jdbc4CallableStatement(this, sql, resultSetType, resultSetConcurrency, resultSetHoldability);
/* 48 */     s.setPrepareThreshold(getPrepareThreshold());
/* 49 */     return s;
/*    */   }
/*    */   
/*    */   public DatabaseMetaData getMetaData() throws SQLException {
/* 54 */     checkClosed();
/* 55 */     if (this.metadata == null)
/* 56 */       this.metadata = new Jdbc4DatabaseMetaData(this); 
/* 57 */     return this.metadata;
/*    */   }
/*    */   
/*    */   public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
/* 62 */     setTypeMapImpl(map);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\Jdbc4Connection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */