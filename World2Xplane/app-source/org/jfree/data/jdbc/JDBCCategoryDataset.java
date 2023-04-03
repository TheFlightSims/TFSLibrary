/*     */ package org.jfree.data.jdbc;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.Date;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import org.jfree.data.category.DefaultCategoryDataset;
/*     */ 
/*     */ public class JDBCCategoryDataset extends DefaultCategoryDataset {
/*     */   private transient Connection connection;
/*     */   
/*     */   private boolean transpose = true;
/*     */   
/*     */   public JDBCCategoryDataset(String url, String driverName, String user, String passwd) throws ClassNotFoundException, SQLException {
/* 116 */     Class.forName(driverName);
/* 117 */     this.connection = DriverManager.getConnection(url, user, passwd);
/*     */   }
/*     */   
/*     */   public JDBCCategoryDataset(Connection connection) {
/* 126 */     if (connection == null)
/* 127 */       throw new NullPointerException("A connection must be supplied."); 
/* 129 */     this.connection = connection;
/*     */   }
/*     */   
/*     */   public JDBCCategoryDataset(Connection connection, String query) throws SQLException {
/* 143 */     this(connection);
/* 144 */     executeQuery(query);
/*     */   }
/*     */   
/*     */   public boolean getTranspose() {
/* 154 */     return this.transpose;
/*     */   }
/*     */   
/*     */   public void setTranspose(boolean transpose) {
/* 164 */     this.transpose = transpose;
/*     */   }
/*     */   
/*     */   public void executeQuery(String query) throws SQLException {
/* 180 */     executeQuery(this.connection, query);
/*     */   }
/*     */   
/*     */   public void executeQuery(Connection con, String query) throws SQLException {
/* 198 */     Statement statement = null;
/* 199 */     ResultSet resultSet = null;
/*     */     try {
/* 201 */       statement = con.createStatement();
/* 202 */       resultSet = statement.executeQuery(query);
/* 203 */       ResultSetMetaData metaData = resultSet.getMetaData();
/* 205 */       int columnCount = metaData.getColumnCount();
/* 207 */       if (columnCount < 2)
/* 208 */         throw new SQLException("JDBCCategoryDataset.executeQuery() : insufficient columns returned from the database."); 
/* 214 */       int i = getRowCount();
/* 215 */       for (; i > 0; i--)
/* 216 */         removeRow(i); 
/* 219 */       while (resultSet.next()) {
/* 221 */         Comparable rowKey = resultSet.getString(1);
/* 222 */         for (int column = 2; column <= columnCount; column++) {
/*     */           Number value;
/*     */           Date date;
/*     */           String string;
/*     */           Number number1;
/* 224 */           Comparable columnKey = metaData.getColumnName(column);
/* 225 */           int columnType = metaData.getColumnType(column);
/* 227 */           switch (columnType) {
/*     */             case -6:
/*     */             case -5:
/*     */             case 2:
/*     */             case 3:
/*     */             case 4:
/*     */             case 5:
/*     */             case 6:
/*     */             case 7:
/*     */             case 8:
/* 237 */               value = (Number)resultSet.getObject(column);
/* 238 */               if (this.transpose) {
/* 239 */                 setValue(value, columnKey, rowKey);
/*     */                 break;
/*     */               } 
/* 242 */               setValue(value, rowKey, columnKey);
/*     */               break;
/*     */             case 91:
/*     */             case 92:
/*     */             case 93:
/* 249 */               date = (Date)resultSet.getObject(column);
/* 250 */               number1 = new Long(date.getTime());
/* 251 */               if (this.transpose) {
/* 252 */                 setValue(number1, columnKey, rowKey);
/*     */                 break;
/*     */               } 
/* 255 */               setValue(number1, rowKey, columnKey);
/*     */               break;
/*     */             case -1:
/*     */             case 1:
/*     */             case 12:
/* 262 */               string = (String)resultSet.getObject(column);
/*     */               try {
/* 265 */                 number1 = Double.valueOf(string);
/* 266 */                 if (this.transpose) {
/* 267 */                   setValue(number1, columnKey, rowKey);
/*     */                   break;
/*     */                 } 
/* 270 */                 setValue(number1, rowKey, columnKey);
/* 273 */               } catch (NumberFormatException e) {}
/*     */               break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 285 */       fireDatasetChanged();
/*     */     } finally {
/* 288 */       if (resultSet != null)
/*     */         try {
/* 290 */           resultSet.close();
/* 292 */         } catch (Exception e) {} 
/* 296 */       if (statement != null)
/*     */         try {
/* 298 */           statement.close();
/* 300 */         } catch (Exception e) {} 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\jdbc\JDBCCategoryDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */