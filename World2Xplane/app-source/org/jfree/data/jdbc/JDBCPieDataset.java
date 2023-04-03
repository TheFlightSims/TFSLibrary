/*     */ package org.jfree.data.jdbc;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.sql.Timestamp;
/*     */ import org.jfree.data.general.DefaultPieDataset;
/*     */ 
/*     */ public class JDBCPieDataset extends DefaultPieDataset {
/*     */   private transient Connection connection;
/*     */   
/*     */   public JDBCPieDataset(String url, String driverName, String user, String password) throws SQLException, ClassNotFoundException {
/* 103 */     Class.forName(driverName);
/* 104 */     this.connection = DriverManager.getConnection(url, user, password);
/*     */   }
/*     */   
/*     */   public JDBCPieDataset(Connection con) {
/* 115 */     if (con == null)
/* 116 */       throw new NullPointerException("A connection must be supplied."); 
/* 118 */     this.connection = con;
/*     */   }
/*     */   
/*     */   public JDBCPieDataset(Connection con, String query) throws SQLException {
/* 133 */     this(con);
/* 134 */     executeQuery(query);
/*     */   }
/*     */   
/*     */   public void executeQuery(String query) throws SQLException {
/* 149 */     executeQuery(this.connection, query);
/*     */   }
/*     */   
/*     */   public void executeQuery(Connection con, String query) throws SQLException {
/* 166 */     Statement statement = null;
/* 167 */     ResultSet resultSet = null;
/*     */     try {
/* 170 */       statement = con.createStatement();
/* 171 */       resultSet = statement.executeQuery(query);
/* 172 */       ResultSetMetaData metaData = resultSet.getMetaData();
/* 174 */       int columnCount = metaData.getColumnCount();
/* 175 */       if (columnCount != 2)
/* 176 */         throw new SQLException("Invalid sql generated.  PieDataSet requires 2 columns only"); 
/* 181 */       int columnType = metaData.getColumnType(2);
/* 182 */       double value = Double.NaN;
/* 183 */       while (resultSet.next()) {
/*     */         Timestamp date;
/* 184 */         Comparable key = resultSet.getString(1);
/* 185 */         switch (columnType) {
/*     */           case -5:
/*     */           case 2:
/*     */           case 3:
/*     */           case 4:
/*     */           case 6:
/*     */           case 7:
/*     */           case 8:
/* 193 */             value = resultSet.getDouble(2);
/* 194 */             setValue(key, value);
/*     */             continue;
/*     */           case 91:
/*     */           case 92:
/*     */           case 93:
/* 200 */             date = resultSet.getTimestamp(2);
/* 201 */             value = date.getTime();
/* 202 */             setValue(key, value);
/*     */             continue;
/*     */         } 
/* 206 */         System.err.println("JDBCPieDataset - unknown data type");
/*     */       } 
/* 213 */       fireDatasetChanged();
/*     */     } finally {
/* 217 */       if (resultSet != null)
/*     */         try {
/* 219 */           resultSet.close();
/* 221 */         } catch (Exception e) {
/* 222 */           System.err.println("JDBCPieDataset: swallowing exception.");
/*     */         }  
/* 225 */       if (statement != null)
/*     */         try {
/* 227 */           statement.close();
/* 229 */         } catch (Exception e) {
/* 230 */           System.err.println("JDBCPieDataset: swallowing exception.");
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     try {
/* 242 */       this.connection.close();
/* 244 */     } catch (Exception e) {
/* 245 */       System.err.println("JdbcXYDataset: swallowing exception.");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\jdbc\JDBCPieDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */