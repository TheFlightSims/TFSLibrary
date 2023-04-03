/*     */ package org.postgresql.jdbc3;
/*     */ 
/*     */ import java.sql.CallableStatement;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Savepoint;
/*     */ import java.sql.Statement;
/*     */ import java.util.Properties;
/*     */ import org.postgresql.core.BaseConnection;
/*     */ import org.postgresql.jdbc2.AbstractJdbc2Connection;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public abstract class AbstractJdbc3Connection extends AbstractJdbc2Connection {
/*  26 */   private int rsHoldability = 2;
/*     */   
/*  27 */   private int savepointId = 0;
/*     */   
/*     */   protected AbstractJdbc3Connection(String host, int port, String user, String database, Properties info, String url) throws SQLException {
/*  30 */     super(host, port, user, database, info, url);
/*     */   }
/*     */   
/*     */   public void setHoldability(int holdability) throws SQLException {
/*  50 */     checkClosed();
/*  52 */     switch (holdability) {
/*     */       case 2:
/*  55 */         this.rsHoldability = holdability;
/*     */         return;
/*     */       case 1:
/*  58 */         this.rsHoldability = holdability;
/*     */         return;
/*     */     } 
/*  61 */     throw new PSQLException(GT.tr("Unknown ResultSet holdability setting: {0}.", new Integer(holdability)), PSQLState.INVALID_PARAMETER_VALUE);
/*     */   }
/*     */   
/*     */   public int getHoldability() throws SQLException {
/*  80 */     checkClosed();
/*  81 */     return this.rsHoldability;
/*     */   }
/*     */   
/*     */   public Savepoint setSavepoint() throws SQLException {
/*  97 */     checkClosed();
/*  98 */     if (!haveMinimumServerVersion("8.0"))
/*  99 */       throw new PSQLException(GT.tr("Server versions prior to 8.0 do not support savepoints."), PSQLState.NOT_IMPLEMENTED); 
/* 100 */     if (getAutoCommit())
/* 101 */       throw new PSQLException(GT.tr("Cannot establish a savepoint in auto-commit mode."), PSQLState.NO_ACTIVE_SQL_TRANSACTION); 
/* 104 */     PSQLSavepoint savepoint = new PSQLSavepoint(this.savepointId++);
/* 108 */     Statement stmt = createStatement();
/* 109 */     stmt.executeUpdate("SAVEPOINT " + savepoint.getPGName());
/* 110 */     stmt.close();
/* 112 */     return savepoint;
/*     */   }
/*     */   
/*     */   public Savepoint setSavepoint(String name) throws SQLException {
/* 129 */     checkClosed();
/* 130 */     if (!haveMinimumServerVersion("8.0"))
/* 131 */       throw new PSQLException(GT.tr("Server versions prior to 8.0 do not support savepoints."), PSQLState.NOT_IMPLEMENTED); 
/* 132 */     if (getAutoCommit())
/* 133 */       throw new PSQLException(GT.tr("Cannot establish a savepoint in auto-commit mode."), PSQLState.NO_ACTIVE_SQL_TRANSACTION); 
/* 136 */     PSQLSavepoint savepoint = new PSQLSavepoint(name);
/* 140 */     Statement stmt = createStatement();
/* 141 */     stmt.executeUpdate("SAVEPOINT " + savepoint.getPGName());
/* 142 */     stmt.close();
/* 144 */     return savepoint;
/*     */   }
/*     */   
/*     */   public void rollback(Savepoint savepoint) throws SQLException {
/* 164 */     checkClosed();
/* 165 */     if (!haveMinimumServerVersion("8.0"))
/* 166 */       throw new PSQLException(GT.tr("Server versions prior to 8.0 do not support savepoints."), PSQLState.NOT_IMPLEMENTED); 
/* 168 */     PSQLSavepoint pgSavepoint = (PSQLSavepoint)savepoint;
/* 169 */     execSQLUpdate("ROLLBACK TO SAVEPOINT " + pgSavepoint.getPGName());
/*     */   }
/*     */   
/*     */   public void releaseSavepoint(Savepoint savepoint) throws SQLException {
/* 186 */     checkClosed();
/* 187 */     if (!haveMinimumServerVersion("8.0"))
/* 188 */       throw new PSQLException(GT.tr("Server versions prior to 8.0 do not support savepoints."), PSQLState.NOT_IMPLEMENTED); 
/* 190 */     PSQLSavepoint pgSavepoint = (PSQLSavepoint)savepoint;
/* 191 */     execSQLUpdate("RELEASE SAVEPOINT " + pgSavepoint.getPGName());
/* 192 */     pgSavepoint.invalidate();
/*     */   }
/*     */   
/*     */   public abstract Statement createStatement(int paramInt1, int paramInt2, int paramInt3) throws SQLException;
/*     */   
/*     */   public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
/* 230 */     checkClosed();
/* 231 */     return createStatement(resultSetType, resultSetConcurrency, getHoldability());
/*     */   }
/*     */   
/*     */   public abstract PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2, int paramInt3) throws SQLException;
/*     */   
/*     */   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/* 274 */     checkClosed();
/* 275 */     return prepareStatement(sql, resultSetType, resultSetConcurrency, getHoldability());
/*     */   }
/*     */   
/*     */   public abstract CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2, int paramInt3) throws SQLException;
/*     */   
/*     */   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
/* 317 */     checkClosed();
/* 318 */     return prepareCall(sql, resultSetType, resultSetConcurrency, getHoldability());
/*     */   }
/*     */   
/*     */   public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
/* 361 */     checkClosed();
/* 362 */     if (autoGeneratedKeys != 2)
/* 363 */       sql = AbstractJdbc3Statement.addReturning((BaseConnection)this, sql, new String[] { "*" }, false); 
/* 365 */     PreparedStatement ps = prepareStatement(sql);
/* 367 */     if (autoGeneratedKeys != 2)
/* 368 */       ((AbstractJdbc3Statement)ps).wantsGeneratedKeysAlways = true; 
/* 370 */     return ps;
/*     */   }
/*     */   
/*     */   public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
/* 416 */     if (columnIndexes == null || columnIndexes.length == 0)
/* 417 */       return prepareStatement(sql); 
/* 419 */     checkClosed();
/* 420 */     throw new PSQLException(GT.tr("Returning autogenerated keys is not supported."), PSQLState.NOT_IMPLEMENTED);
/*     */   }
/*     */   
/*     */   public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
/* 466 */     if (columnNames != null && columnNames.length != 0)
/* 467 */       sql = AbstractJdbc3Statement.addReturning((BaseConnection)this, sql, columnNames, true); 
/* 469 */     PreparedStatement ps = prepareStatement(sql);
/* 471 */     if (columnNames != null && columnNames.length != 0)
/* 472 */       ((AbstractJdbc3Statement)ps).wantsGeneratedKeysAlways = true; 
/* 474 */     return ps;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc3\AbstractJdbc3Connection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */