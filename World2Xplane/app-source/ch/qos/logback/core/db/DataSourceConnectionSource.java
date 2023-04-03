/*    */ package ch.qos.logback.core.db;
/*    */ 
/*    */ import ch.qos.logback.core.db.dialect.SQLDialectCode;
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import javax.sql.DataSource;
/*    */ 
/*    */ public class DataSourceConnectionSource extends ConnectionSourceBase {
/*    */   private DataSource dataSource;
/*    */   
/*    */   public void start() {
/* 41 */     if (this.dataSource == null) {
/* 42 */       addWarn("WARNING: No data source specified");
/*    */     } else {
/* 44 */       Connection connection = null;
/*    */       try {
/* 46 */         connection = getConnection();
/* 47 */       } catch (SQLException se) {
/* 48 */         addWarn("Could not get a connection to discover the dialect to use.", se);
/*    */       } 
/* 51 */       if (connection != null)
/* 52 */         discoverConnectionProperties(); 
/* 54 */       if (!supportsGetGeneratedKeys() && getSQLDialectCode() == SQLDialectCode.UNKNOWN_DIALECT)
/* 56 */         addWarn("Connection does not support GetGeneratedKey method and could not discover the dialect."); 
/*    */     } 
/* 59 */     super.start();
/*    */   }
/*    */   
/*    */   public Connection getConnection() throws SQLException {
/* 66 */     if (this.dataSource == null) {
/* 67 */       addError("WARNING: No data source specified");
/* 68 */       return null;
/*    */     } 
/* 71 */     if (getUser() == null)
/* 72 */       return this.dataSource.getConnection(); 
/* 74 */     return this.dataSource.getConnection(getUser(), getPassword());
/*    */   }
/*    */   
/*    */   public DataSource getDataSource() {
/* 79 */     return this.dataSource;
/*    */   }
/*    */   
/*    */   public void setDataSource(DataSource dataSource) {
/* 83 */     this.dataSource = dataSource;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\db\DataSourceConnectionSource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */