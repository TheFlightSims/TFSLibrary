/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.sql.DataSource;
/*     */ import org.apache.commons.collections.CollectionUtils;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class DatabaseConfiguration extends AbstractConfiguration {
/*     */   private DataSource datasource;
/*     */   
/*     */   private String table;
/*     */   
/*     */   private String nameColumn;
/*     */   
/*     */   private String keyColumn;
/*     */   
/*     */   private String valueColumn;
/*     */   
/*     */   private String name;
/*     */   
/*     */   private final boolean doCommits;
/*     */   
/*     */   public DatabaseConfiguration(DataSource datasource, String table, String nameColumn, String keyColumn, String valueColumn, String name) {
/* 126 */     this(datasource, table, nameColumn, keyColumn, valueColumn, name, false);
/*     */   }
/*     */   
/*     */   public DatabaseConfiguration(DataSource datasource, String table, String nameColumn, String keyColumn, String valueColumn, String name, boolean commits) {
/* 146 */     this.datasource = datasource;
/* 147 */     this.table = table;
/* 148 */     this.nameColumn = nameColumn;
/* 149 */     this.keyColumn = keyColumn;
/* 150 */     this.valueColumn = valueColumn;
/* 151 */     this.name = name;
/* 152 */     this.doCommits = commits;
/* 153 */     setLogger(LogFactory.getLog(getClass()));
/* 154 */     addErrorLogListener();
/*     */   }
/*     */   
/*     */   public DatabaseConfiguration(DataSource datasource, String table, String keyColumn, String valueColumn) {
/* 167 */     this(datasource, table, (String)null, keyColumn, valueColumn, (String)null);
/*     */   }
/*     */   
/*     */   public DatabaseConfiguration(DataSource datasource, String table, String keyColumn, String valueColumn, boolean commits) {
/* 184 */     this(datasource, table, (String)null, keyColumn, valueColumn, (String)null, commits);
/*     */   }
/*     */   
/*     */   public boolean isDoCommits() {
/* 195 */     return this.doCommits;
/*     */   }
/*     */   
/*     */   public Object getProperty(String key) {
/* 210 */     Object result = null;
/* 213 */     StringBuffer query = new StringBuffer("SELECT * FROM ");
/* 214 */     query.append(this.table).append(" WHERE ");
/* 215 */     query.append(this.keyColumn).append("=?");
/* 216 */     if (this.nameColumn != null)
/* 218 */       query.append(" AND " + this.nameColumn + "=?"); 
/* 221 */     Connection conn = null;
/* 222 */     PreparedStatement pstmt = null;
/*     */     try {
/* 226 */       conn = getConnection();
/* 229 */       pstmt = conn.prepareStatement(query.toString());
/* 230 */       pstmt.setString(1, key);
/* 231 */       if (this.nameColumn != null)
/* 233 */         pstmt.setString(2, this.name); 
/* 236 */       ResultSet rs = pstmt.executeQuery();
/* 238 */       List results = new ArrayList();
/* 239 */       while (rs.next()) {
/* 241 */         Object value = rs.getObject(this.valueColumn);
/* 242 */         if (isDelimiterParsingDisabled()) {
/* 244 */           results.add(value);
/*     */           continue;
/*     */         } 
/* 249 */         CollectionUtils.addAll(results, PropertyConverter.toIterator(value, getListDelimiter()));
/*     */       } 
/* 253 */       if (!results.isEmpty())
/* 255 */         result = (results.size() > 1) ? results : results.get(0); 
/* 258 */     } catch (SQLException e) {
/* 260 */       fireError(5, key, null, e);
/*     */     } finally {
/* 264 */       close(conn, pstmt);
/*     */     } 
/* 267 */     return result;
/*     */   }
/*     */   
/*     */   protected void addPropertyDirect(String key, Object obj) {
/* 283 */     StringBuffer query = new StringBuffer("INSERT INTO " + this.table);
/* 284 */     if (this.nameColumn != null) {
/* 286 */       query.append(" (" + this.nameColumn + ", " + this.keyColumn + ", " + this.valueColumn + ") VALUES (?, ?, ?)");
/*     */     } else {
/* 290 */       query.append(" (" + this.keyColumn + ", " + this.valueColumn + ") VALUES (?, ?)");
/*     */     } 
/* 293 */     Connection conn = null;
/* 294 */     PreparedStatement pstmt = null;
/*     */     try {
/* 298 */       conn = getConnection();
/* 301 */       pstmt = conn.prepareStatement(query.toString());
/* 302 */       int index = 1;
/* 303 */       if (this.nameColumn != null)
/* 305 */         pstmt.setString(index++, this.name); 
/* 307 */       pstmt.setString(index++, key);
/* 308 */       pstmt.setString(index++, String.valueOf(obj));
/* 310 */       pstmt.executeUpdate();
/* 311 */       commitIfRequired(conn);
/* 313 */     } catch (SQLException e) {
/* 315 */       fireError(1, key, obj, e);
/*     */     } finally {
/* 320 */       close(conn, pstmt);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addProperty(String key, Object value) {
/* 338 */     boolean parsingFlag = isDelimiterParsingDisabled();
/*     */     try {
/* 341 */       if (value instanceof String)
/* 344 */         setDelimiterParsingDisabled(true); 
/* 346 */       super.addProperty(key, value);
/*     */     } finally {
/* 350 */       setDelimiterParsingDisabled(parsingFlag);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 364 */     boolean empty = true;
/* 367 */     StringBuffer query = new StringBuffer("SELECT count(*) FROM " + this.table);
/* 368 */     if (this.nameColumn != null)
/* 370 */       query.append(" WHERE " + this.nameColumn + "=?"); 
/* 373 */     Connection conn = null;
/* 374 */     PreparedStatement pstmt = null;
/*     */     try {
/* 378 */       conn = getConnection();
/* 381 */       pstmt = conn.prepareStatement(query.toString());
/* 382 */       if (this.nameColumn != null)
/* 384 */         pstmt.setString(1, this.name); 
/* 387 */       ResultSet rs = pstmt.executeQuery();
/* 389 */       if (rs.next())
/* 391 */         empty = (rs.getInt(1) == 0); 
/* 394 */     } catch (SQLException e) {
/* 396 */       fireError(5, null, null, e);
/*     */     } finally {
/* 401 */       close(conn, pstmt);
/*     */     } 
/* 404 */     return empty;
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/* 419 */     boolean found = false;
/* 422 */     StringBuffer query = new StringBuffer("SELECT * FROM " + this.table + " WHERE " + this.keyColumn + "=?");
/* 423 */     if (this.nameColumn != null)
/* 425 */       query.append(" AND " + this.nameColumn + "=?"); 
/* 428 */     Connection conn = null;
/* 429 */     PreparedStatement pstmt = null;
/*     */     try {
/* 433 */       conn = getConnection();
/* 436 */       pstmt = conn.prepareStatement(query.toString());
/* 437 */       pstmt.setString(1, key);
/* 438 */       if (this.nameColumn != null)
/* 440 */         pstmt.setString(2, this.name); 
/* 443 */       ResultSet rs = pstmt.executeQuery();
/* 445 */       found = rs.next();
/* 447 */     } catch (SQLException e) {
/* 449 */       fireError(5, key, null, e);
/*     */     } finally {
/* 454 */       close(conn, pstmt);
/*     */     } 
/* 457 */     return found;
/*     */   }
/*     */   
/*     */   protected void clearPropertyDirect(String key) {
/* 472 */     StringBuffer query = new StringBuffer("DELETE FROM " + this.table + " WHERE " + this.keyColumn + "=?");
/* 473 */     if (this.nameColumn != null)
/* 475 */       query.append(" AND " + this.nameColumn + "=?"); 
/* 478 */     Connection conn = null;
/* 479 */     PreparedStatement pstmt = null;
/*     */     try {
/* 483 */       conn = getConnection();
/* 486 */       pstmt = conn.prepareStatement(query.toString());
/* 487 */       pstmt.setString(1, key);
/* 488 */       if (this.nameColumn != null)
/* 490 */         pstmt.setString(2, this.name); 
/* 493 */       pstmt.executeUpdate();
/* 494 */       commitIfRequired(conn);
/* 496 */     } catch (SQLException e) {
/* 498 */       fireError(2, key, null, e);
/*     */     } finally {
/* 503 */       close(conn, pstmt);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 516 */     fireEvent(4, null, null, true);
/* 518 */     StringBuffer query = new StringBuffer("DELETE FROM " + this.table);
/* 519 */     if (this.nameColumn != null)
/* 521 */       query.append(" WHERE " + this.nameColumn + "=?"); 
/* 524 */     Connection conn = null;
/* 525 */     PreparedStatement pstmt = null;
/*     */     try {
/* 529 */       conn = getConnection();
/* 532 */       pstmt = conn.prepareStatement(query.toString());
/* 533 */       if (this.nameColumn != null)
/* 535 */         pstmt.setString(1, this.name); 
/* 538 */       pstmt.executeUpdate();
/* 539 */       commitIfRequired(conn);
/* 541 */     } catch (SQLException e) {
/* 543 */       fireError(4, null, null, e);
/*     */     } finally {
/* 548 */       close(conn, pstmt);
/*     */     } 
/* 550 */     fireEvent(4, null, null, false);
/*     */   }
/*     */   
/*     */   public Iterator getKeys() {
/* 565 */     Collection keys = new ArrayList();
/* 568 */     StringBuffer query = new StringBuffer("SELECT DISTINCT " + this.keyColumn + " FROM " + this.table);
/* 569 */     if (this.nameColumn != null)
/* 571 */       query.append(" WHERE " + this.nameColumn + "=?"); 
/* 574 */     Connection conn = null;
/* 575 */     PreparedStatement pstmt = null;
/*     */     try {
/* 579 */       conn = getConnection();
/* 582 */       pstmt = conn.prepareStatement(query.toString());
/* 583 */       if (this.nameColumn != null)
/* 585 */         pstmt.setString(1, this.name); 
/* 588 */       ResultSet rs = pstmt.executeQuery();
/* 590 */       while (rs.next())
/* 592 */         keys.add(rs.getString(1)); 
/* 595 */     } catch (SQLException e) {
/* 597 */       fireError(5, null, null, e);
/*     */     } finally {
/* 602 */       close(conn, pstmt);
/*     */     } 
/* 605 */     return keys.iterator();
/*     */   }
/*     */   
/*     */   public DataSource getDatasource() {
/* 616 */     return this.datasource;
/*     */   }
/*     */   
/*     */   protected Connection getConnection() throws SQLException {
/* 632 */     return getDatasource().getConnection();
/*     */   }
/*     */   
/*     */   private void close(Connection conn, Statement stmt) {
/*     */     try {
/* 646 */       if (stmt != null)
/* 648 */         stmt.close(); 
/* 651 */     } catch (SQLException e) {
/* 653 */       getLogger().error("An error occured on closing the statement", e);
/*     */     } 
/*     */     try {
/* 658 */       if (conn != null)
/* 660 */         conn.close(); 
/* 663 */     } catch (SQLException e) {
/* 665 */       getLogger().error("An error occured on closing the connection", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void commitIfRequired(Connection conn) throws SQLException {
/* 679 */     if (isDoCommits())
/* 681 */       conn.commit(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\DatabaseConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */