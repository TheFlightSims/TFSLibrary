/*    */ package org.geotools.referencing.factory.epsg;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.SQLFeatureNotSupportedException;
/*    */ import java.util.logging.Logger;
/*    */ import javax.sql.DataSource;
/*    */ 
/*    */ class SingleConnectionDataSource implements DataSource {
/*    */   Connection connection;
/*    */   
/*    */   public SingleConnectionDataSource(Connection connection) {
/* 39 */     this.connection = connection;
/*    */   }
/*    */   
/*    */   public Connection getConnection() throws SQLException {
/* 43 */     return this.connection;
/*    */   }
/*    */   
/*    */   public Connection getConnection(String username, String password) throws SQLException {
/* 47 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public PrintWriter getLogWriter() throws SQLException {
/* 51 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public int getLoginTimeout() throws SQLException {
/* 55 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public void setLogWriter(PrintWriter out) throws SQLException {
/* 59 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public void setLoginTimeout(int seconds) throws SQLException {
/* 63 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 67 */     return false;
/*    */   }
/*    */   
/*    */   public <T> T unwrap(Class<T> iface) throws SQLException {
/* 71 */     throw new SQLException("Not wrapping an object implementing " + iface.getClass().getName());
/*    */   }
/*    */   
/*    */   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
/* 75 */     throw new SQLFeatureNotSupportedException();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\SingleConnectionDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */