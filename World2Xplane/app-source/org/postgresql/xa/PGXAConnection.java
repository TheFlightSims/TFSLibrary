/*     */ package org.postgresql.xa;
/*     */ 
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.LinkedList;
/*     */ import javax.sql.XAConnection;
/*     */ import javax.transaction.xa.XAException;
/*     */ import javax.transaction.xa.XAResource;
/*     */ import javax.transaction.xa.Xid;
/*     */ import org.postgresql.PGConnection;
/*     */ import org.postgresql.core.BaseConnection;
/*     */ import org.postgresql.core.Logger;
/*     */ import org.postgresql.ds.PGPooledConnection;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class PGXAConnection extends PGPooledConnection implements XAConnection, XAResource {
/*     */   private final BaseConnection conn;
/*     */   
/*     */   private final Logger logger;
/*     */   
/*     */   private Xid currentXid;
/*     */   
/*     */   private int state;
/*     */   
/*     */   private static final int STATE_IDLE = 0;
/*     */   
/*     */   private static final int STATE_ACTIVE = 1;
/*     */   
/*     */   private static final int STATE_ENDED = 2;
/*     */   
/*     */   private boolean localAutoCommitMode = true;
/*     */   
/*     */   private void debug(String s) {
/*  87 */     this.logger.debug("XAResource " + Integer.toHexString(hashCode()) + ": " + s);
/*     */   }
/*     */   
/*     */   public PGXAConnection(BaseConnection conn) throws SQLException {
/*  92 */     super((Connection)conn, true, true);
/*  93 */     this.conn = conn;
/*  94 */     this.state = 0;
/*  95 */     this.logger = conn.getLogger();
/*     */   }
/*     */   
/*     */   public Connection getConnection() throws SQLException {
/* 103 */     if (this.logger.logDebug())
/* 104 */       debug("PGXAConnection.getConnection called"); 
/* 106 */     Connection conn = super.getConnection();
/* 112 */     if (this.state == 0)
/* 113 */       conn.setAutoCommit(true); 
/* 119 */     ConnectionHandler handler = new ConnectionHandler(conn);
/* 120 */     return (Connection)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { Connection.class, PGConnection.class }, handler);
/*     */   }
/*     */   
/*     */   public XAResource getXAResource() {
/* 124 */     return this;
/*     */   }
/*     */   
/*     */   private class ConnectionHandler implements InvocationHandler {
/*     */     private Connection con;
/*     */     
/*     */     public ConnectionHandler(Connection con) {
/* 137 */       this.con = con;
/*     */     }
/*     */     
/*     */     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/* 143 */       if (PGXAConnection.this.state != 0) {
/* 145 */         String methodName = method.getName();
/* 146 */         if (methodName.equals("commit") || methodName.equals("rollback") || methodName.equals("setSavePoint") || (methodName.equals("setAutoCommit") && ((Boolean)args[0]).booleanValue()))
/* 151 */           throw new PSQLException(GT.tr("Transaction control methods setAutoCommit(true), commit, rollback and setSavePoint not allowed while an XA transaction is active."), PSQLState.OBJECT_NOT_IN_STATE); 
/*     */       } 
/*     */       try {
/* 156 */         return method.invoke(this.con, args);
/* 157 */       } catch (InvocationTargetException ex) {
/* 158 */         throw ex.getTargetException();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void start(Xid xid, int flags) throws XAException {
/* 184 */     if (this.logger.logDebug())
/* 185 */       debug("starting transaction xid = " + xid); 
/* 188 */     if (flags != 0 && flags != 134217728 && flags != 2097152)
/* 189 */       throw new PGXAException(GT.tr("Invalid flags"), -5); 
/* 191 */     if (xid == null)
/* 192 */       throw new PGXAException(GT.tr("xid must not be null"), -5); 
/* 194 */     if (this.state == 1)
/* 195 */       throw new PGXAException(GT.tr("Connection is busy with another transaction"), -6); 
/* 200 */     if (flags == 134217728)
/* 201 */       throw new PGXAException(GT.tr("suspend/resume not implemented"), -3); 
/* 204 */     if (flags == 2097152) {
/* 206 */       if (this.state != 2)
/* 207 */         throw new PGXAException(GT.tr("Transaction interleaving not implemented"), -3); 
/* 209 */       if (!xid.equals(this.currentXid))
/* 210 */         throw new PGXAException(GT.tr("Transaction interleaving not implemented"), -3); 
/* 211 */     } else if (this.state == 2) {
/* 212 */       throw new PGXAException(GT.tr("Transaction interleaving not implemented"), -3);
/*     */     } 
/*     */     try {
/* 216 */       this.localAutoCommitMode = this.conn.getAutoCommit();
/* 217 */       this.conn.setAutoCommit(false);
/* 219 */     } catch (SQLException ex) {
/* 221 */       throw new PGXAException(GT.tr("Error disabling autocommit"), ex, -3);
/*     */     } 
/* 225 */     this.state = 1;
/* 226 */     this.currentXid = xid;
/*     */   }
/*     */   
/*     */   public void end(Xid xid, int flags) throws XAException {
/* 242 */     if (this.logger.logDebug())
/* 243 */       debug("ending transaction xid = " + xid); 
/* 247 */     if (flags != 33554432 && flags != 536870912 && flags != 67108864)
/* 248 */       throw new PGXAException(GT.tr("Invalid flags"), -5); 
/* 250 */     if (xid == null)
/* 251 */       throw new PGXAException(GT.tr("xid must not be null"), -5); 
/* 253 */     if (this.state != 1 || !this.currentXid.equals(xid))
/* 254 */       throw new PGXAException(GT.tr("tried to call end without corresponding start call"), -6); 
/* 257 */     if (flags == 33554432)
/* 258 */       throw new PGXAException(GT.tr("suspend/resume not implemented"), -3); 
/* 264 */     this.state = 2;
/*     */   }
/*     */   
/*     */   public int prepare(Xid xid) throws XAException {
/* 279 */     if (this.logger.logDebug())
/* 280 */       debug("preparing transaction xid = " + xid); 
/* 283 */     if (!this.currentXid.equals(xid))
/* 285 */       throw new PGXAException(GT.tr("Not implemented: Prepare must be issued using the same connection that started the transaction"), -3); 
/* 288 */     if (this.state != 2)
/* 289 */       throw new PGXAException(GT.tr("Prepare called before end"), -5); 
/* 291 */     this.state = 0;
/* 292 */     this.currentXid = null;
/* 294 */     if (!this.conn.haveMinimumServerVersion("8.1"))
/* 295 */       throw new PGXAException(GT.tr("Server versions prior to 8.1 do not support two-phase commit."), -3); 
/*     */     try {
/* 299 */       String s = RecoveredXid.xidToString(xid);
/* 301 */       Statement stmt = this.conn.createStatement();
/*     */       try {
/* 304 */         stmt.executeUpdate("PREPARE TRANSACTION '" + s + "'");
/*     */       } finally {
/* 308 */         stmt.close();
/*     */       } 
/* 310 */       this.conn.setAutoCommit(this.localAutoCommitMode);
/* 312 */       return 0;
/* 314 */     } catch (SQLException ex) {
/* 316 */       throw new PGXAException(GT.tr("Error preparing transaction"), ex, -3);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Xid[] recover(int flag) throws XAException {
/* 330 */     if (flag != 16777216 && flag != 8388608 && flag != 0 && flag != 25165824)
/* 331 */       throw new PGXAException(GT.tr("Invalid flag"), -5); 
/* 338 */     if ((flag & 0x1000000) == 0)
/* 339 */       return new Xid[0]; 
/*     */     try {
/* 344 */       Statement stmt = this.conn.createStatement();
/*     */       try {
/* 352 */         ResultSet rs = stmt.executeQuery("SELECT gid FROM pg_prepared_xacts");
/* 353 */         LinkedList<Xid> l = new LinkedList();
/* 354 */         while (rs.next()) {
/* 356 */           Xid recoveredXid = RecoveredXid.stringToXid(rs.getString(1));
/* 357 */           if (recoveredXid != null)
/* 358 */             l.add(recoveredXid); 
/*     */         } 
/* 360 */         rs.close();
/* 362 */         return l.<Xid>toArray(new Xid[l.size()]);
/*     */       } finally {
/* 366 */         stmt.close();
/*     */       } 
/* 369 */     } catch (SQLException ex) {
/* 371 */       throw new PGXAException(GT.tr("Error during recover"), ex, -3);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void rollback(Xid xid) throws XAException {
/* 387 */     if (this.logger.logDebug())
/* 388 */       debug("rolling back xid = " + xid); 
/*     */     try {
/* 394 */       if (this.currentXid != null && xid.equals(this.currentXid)) {
/* 396 */         this.state = 0;
/* 397 */         this.currentXid = null;
/* 398 */         this.conn.rollback();
/* 399 */         this.conn.setAutoCommit(this.localAutoCommitMode);
/*     */       } else {
/* 403 */         String s = RecoveredXid.xidToString(xid);
/* 405 */         this.conn.setAutoCommit(true);
/* 406 */         Statement stmt = this.conn.createStatement();
/*     */         try {
/* 409 */           stmt.executeUpdate("ROLLBACK PREPARED '" + s + "'");
/*     */         } finally {
/* 413 */           stmt.close();
/*     */         } 
/*     */       } 
/* 417 */     } catch (SQLException ex) {
/* 419 */       throw new PGXAException(GT.tr("Error rolling back prepared transaction"), ex, -3);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void commit(Xid xid, boolean onePhase) throws XAException {
/* 424 */     if (this.logger.logDebug())
/* 425 */       debug("committing xid = " + xid + (onePhase ? " (one phase) " : " (two phase)")); 
/* 427 */     if (xid == null)
/* 428 */       throw new PGXAException(GT.tr("xid must not be null"), -5); 
/* 430 */     if (onePhase) {
/* 431 */       commitOnePhase(xid);
/*     */     } else {
/* 433 */       commitPrepared(xid);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void commitOnePhase(Xid xid) throws XAException {
/*     */     try {
/* 450 */       if (this.currentXid == null || !this.currentXid.equals(xid))
/* 454 */         throw new PGXAException(GT.tr("Not implemented: one-phase commit must be issued using the same connection that was used to start it"), -3); 
/* 457 */       if (this.state != 2)
/* 458 */         throw new PGXAException(GT.tr("commit called before end"), -6); 
/* 461 */       this.state = 0;
/* 462 */       this.currentXid = null;
/* 464 */       this.conn.commit();
/* 465 */       this.conn.setAutoCommit(this.localAutoCommitMode);
/* 467 */     } catch (SQLException ex) {
/* 469 */       throw new PGXAException(GT.tr("Error during one-phase commit"), ex, -3);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void commitPrepared(Xid xid) throws XAException {
/*     */     try {
/* 489 */       if (this.state != 0 || this.conn.getTransactionState() != 0)
/* 490 */         throw new PGXAException(GT.tr("Not implemented: 2nd phase commit must be issued using an idle connection"), -3); 
/* 493 */       String s = RecoveredXid.xidToString(xid);
/* 495 */       this.localAutoCommitMode = this.conn.getAutoCommit();
/* 496 */       this.conn.setAutoCommit(true);
/* 497 */       Statement stmt = this.conn.createStatement();
/*     */       try {
/* 500 */         stmt.executeUpdate("COMMIT PREPARED '" + s + "'");
/*     */       } finally {
/* 504 */         stmt.close();
/* 505 */         this.conn.setAutoCommit(this.localAutoCommitMode);
/*     */       } 
/* 508 */     } catch (SQLException ex) {
/* 510 */       throw new PGXAException(GT.tr("Error committing prepared transaction"), ex, -3);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isSameRM(XAResource xares) throws XAException {
/* 518 */     return (xares == this);
/*     */   }
/*     */   
/*     */   public void forget(Xid xid) throws XAException {
/* 525 */     throw new PGXAException(GT.tr("Heuristic commit/rollback not supported"), -4);
/*     */   }
/*     */   
/*     */   public int getTransactionTimeout() {
/* 532 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean setTransactionTimeout(int seconds) {
/* 539 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\xa\PGXAConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */