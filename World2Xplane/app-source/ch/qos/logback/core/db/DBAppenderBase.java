/*     */ package ch.qos.logback.core.db;
/*     */ 
/*     */ import ch.qos.logback.core.UnsynchronizedAppenderBase;
/*     */ import ch.qos.logback.core.db.dialect.DBUtil;
/*     */ import ch.qos.logback.core.db.dialect.SQLDialect;
/*     */ import ch.qos.logback.core.db.dialect.SQLDialectCode;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ 
/*     */ public abstract class DBAppenderBase<E> extends UnsynchronizedAppenderBase<E> {
/*     */   protected ConnectionSource connectionSource;
/*     */   
/*     */   protected boolean cnxSupportsGetGeneratedKeys = false;
/*     */   
/*     */   protected boolean cnxSupportsBatchUpdates = false;
/*     */   
/*     */   protected SQLDialect sqlDialect;
/*     */   
/*     */   protected abstract Method getGeneratedKeysMethod();
/*     */   
/*     */   protected abstract String getInsertSQL();
/*     */   
/*     */   public void start() {
/*  48 */     if (this.connectionSource == null)
/*  49 */       throw new IllegalStateException("DBAppender cannot function without a connection source"); 
/*  53 */     this.sqlDialect = DBUtil.getDialectFromCode(this.connectionSource.getSQLDialectCode());
/*  55 */     if (getGeneratedKeysMethod() != null) {
/*  56 */       this.cnxSupportsGetGeneratedKeys = this.connectionSource.supportsGetGeneratedKeys();
/*     */     } else {
/*  58 */       this.cnxSupportsGetGeneratedKeys = false;
/*     */     } 
/*  60 */     this.cnxSupportsBatchUpdates = this.connectionSource.supportsBatchUpdates();
/*  61 */     if (!this.cnxSupportsGetGeneratedKeys && this.sqlDialect == null)
/*  62 */       throw new IllegalStateException("DBAppender cannot function if the JDBC driver does not support getGeneratedKeys method *and* without a specific SQL dialect"); 
/*  67 */     super.start();
/*     */   }
/*     */   
/*     */   public ConnectionSource getConnectionSource() {
/*  74 */     return this.connectionSource;
/*     */   }
/*     */   
/*     */   public void setConnectionSource(ConnectionSource connectionSource) {
/*  82 */     this.connectionSource = connectionSource;
/*     */   }
/*     */   
/*     */   public void append(E eventObject) {
/*  87 */     Connection connection = null;
/*     */     try {
/*     */       PreparedStatement insertStatement;
/*     */       long eventId;
/*  89 */       connection = this.connectionSource.getConnection();
/*  90 */       connection.setAutoCommit(false);
/*  93 */       if (this.cnxSupportsGetGeneratedKeys) {
/*  94 */         String EVENT_ID_COL_NAME = "EVENT_ID";
/*  96 */         if (this.connectionSource.getSQLDialectCode() == SQLDialectCode.POSTGRES_DIALECT)
/*  97 */           EVENT_ID_COL_NAME = EVENT_ID_COL_NAME.toLowerCase(); 
/*  99 */         insertStatement = connection.prepareStatement(getInsertSQL(), new String[] { EVENT_ID_COL_NAME });
/*     */       } else {
/* 102 */         insertStatement = connection.prepareStatement(getInsertSQL());
/*     */       } 
/* 107 */       synchronized (this) {
/* 108 */         subAppend(eventObject, connection, insertStatement);
/* 109 */         eventId = selectEventId(insertStatement, connection);
/*     */       } 
/* 111 */       secondarySubAppend(eventObject, connection, eventId);
/* 114 */       close(insertStatement);
/* 116 */       connection.commit();
/* 117 */     } catch (Throwable sqle) {
/* 118 */       addError("problem appending event", sqle);
/*     */     } finally {
/* 120 */       DBHelper.closeConnection(connection);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void subAppend(E paramE, Connection paramConnection, PreparedStatement paramPreparedStatement) throws Throwable;
/*     */   
/*     */   protected abstract void secondarySubAppend(E paramE, Connection paramConnection, long paramLong) throws Throwable;
/*     */   
/*     */   protected long selectEventId(PreparedStatement insertStatement, Connection connection) throws SQLException, InvocationTargetException {
/* 132 */     ResultSet rs = null;
/* 133 */     Statement idStatement = null;
/* 134 */     boolean gotGeneratedKeys = false;
/* 135 */     if (this.cnxSupportsGetGeneratedKeys)
/*     */       try {
/* 137 */         rs = (ResultSet)getGeneratedKeysMethod().invoke(insertStatement, (Object[])null);
/* 139 */         gotGeneratedKeys = true;
/* 140 */       } catch (InvocationTargetException ex) {
/* 141 */         Throwable target = ex.getTargetException();
/* 142 */         if (target instanceof SQLException)
/* 143 */           throw (SQLException)target; 
/* 145 */         throw ex;
/* 146 */       } catch (IllegalAccessException ex) {
/* 147 */         addWarn("IllegalAccessException invoking PreparedStatement.getGeneratedKeys", ex);
/*     */       }  
/* 153 */     if (!gotGeneratedKeys) {
/* 154 */       idStatement = connection.createStatement();
/* 155 */       idStatement.setMaxRows(1);
/* 156 */       String selectInsertIdStr = this.sqlDialect.getSelectInsertId();
/* 157 */       rs = idStatement.executeQuery(selectInsertIdStr);
/*     */     } 
/* 162 */     rs.next();
/* 163 */     long eventId = rs.getLong(1);
/* 165 */     rs.close();
/* 167 */     close(idStatement);
/* 169 */     return eventId;
/*     */   }
/*     */   
/*     */   void close(Statement statement) throws SQLException {
/* 173 */     if (statement != null)
/* 174 */       statement.close(); 
/*     */   }
/*     */   
/*     */   public void stop() {
/* 180 */     super.stop();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\db\DBAppenderBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */