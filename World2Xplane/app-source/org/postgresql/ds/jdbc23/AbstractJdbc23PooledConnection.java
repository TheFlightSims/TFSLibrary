/*     */ package org.postgresql.ds.jdbc23;
/*     */ 
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.sql.CallableStatement;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import javax.sql.ConnectionEvent;
/*     */ import javax.sql.ConnectionEventListener;
/*     */ import org.postgresql.PGConnection;
/*     */ import org.postgresql.PGStatement;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public abstract class AbstractJdbc23PooledConnection {
/*  32 */   private List listeners = new LinkedList();
/*     */   
/*     */   private Connection con;
/*     */   
/*     */   private ConnectionHandler last;
/*     */   
/*     */   private final boolean autoCommit;
/*     */   
/*     */   private final boolean isXA;
/*     */   
/*     */   public AbstractJdbc23PooledConnection(Connection con, boolean autoCommit, boolean isXA) {
/*  44 */     this.con = con;
/*  45 */     this.autoCommit = autoCommit;
/*  46 */     this.isXA = isXA;
/*     */   }
/*     */   
/*     */   public void addConnectionEventListener(ConnectionEventListener connectionEventListener) {
/*  55 */     this.listeners.add(connectionEventListener);
/*     */   }
/*     */   
/*     */   public void removeConnectionEventListener(ConnectionEventListener connectionEventListener) {
/*  64 */     this.listeners.remove(connectionEventListener);
/*     */   }
/*     */   
/*     */   public void close() throws SQLException {
/*  74 */     if (this.last != null) {
/*  76 */       this.last.close();
/*  77 */       if (!this.con.getAutoCommit())
/*     */         try {
/*  81 */           this.con.rollback();
/*  83 */         } catch (SQLException e) {} 
/*     */     } 
/*     */     try {
/*  90 */       this.con.close();
/*     */     } finally {
/*  94 */       this.con = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Connection getConnection() throws SQLException {
/* 111 */     if (this.con == null) {
/* 114 */       PSQLException sqlException = new PSQLException(GT.tr("This PooledConnection has already been closed."), PSQLState.CONNECTION_DOES_NOT_EXIST);
/* 116 */       fireConnectionFatalError((SQLException)sqlException);
/* 117 */       throw sqlException;
/*     */     } 
/*     */     try {
/* 125 */       if (this.last != null) {
/* 127 */         this.last.close();
/* 128 */         if (!this.con.getAutoCommit())
/*     */           try {
/* 132 */             this.con.rollback();
/* 134 */           } catch (SQLException e) {} 
/* 138 */         this.con.clearWarnings();
/*     */       } 
/* 145 */       if (!this.isXA)
/* 146 */         this.con.setAutoCommit(this.autoCommit); 
/* 148 */     } catch (SQLException sqlException) {
/* 150 */       fireConnectionFatalError(sqlException);
/* 151 */       throw (SQLException)sqlException.fillInStackTrace();
/*     */     } 
/* 153 */     ConnectionHandler handler = new ConnectionHandler(this.con);
/* 154 */     this.last = handler;
/* 156 */     Connection proxyCon = (Connection)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { Connection.class, PGConnection.class }, handler);
/* 157 */     this.last.setProxy(proxyCon);
/* 158 */     return proxyCon;
/*     */   }
/*     */   
/*     */   void fireConnectionClosed() {
/* 166 */     ConnectionEvent evt = null;
/* 168 */     ConnectionEventListener[] local = (ConnectionEventListener[])this.listeners.toArray((Object[])new ConnectionEventListener[this.listeners.size()]);
/* 169 */     for (int i = 0; i < local.length; i++) {
/* 171 */       ConnectionEventListener listener = local[i];
/* 172 */       if (evt == null)
/* 174 */         evt = createConnectionEvent(null); 
/* 176 */       listener.connectionClosed(evt);
/*     */     } 
/*     */   }
/*     */   
/*     */   void fireConnectionFatalError(SQLException e) {
/* 185 */     ConnectionEvent evt = null;
/* 187 */     ConnectionEventListener[] local = (ConnectionEventListener[])this.listeners.toArray((Object[])new ConnectionEventListener[this.listeners.size()]);
/* 188 */     for (int i = 0; i < local.length; i++) {
/* 190 */       ConnectionEventListener listener = local[i];
/* 191 */       if (evt == null)
/* 193 */         evt = createConnectionEvent(e); 
/* 195 */       listener.connectionErrorOccurred(evt);
/*     */     } 
/*     */   }
/*     */   
/* 202 */   private static String[] fatalClasses = new String[] { "08", "53", "57P01", "57P02", "57P03", "58", "60", "99", "F0", "XX" };
/*     */   
/*     */   private static boolean isFatalState(String state) {
/* 219 */     if (state == null)
/* 220 */       return true; 
/* 221 */     if (state.length() < 2)
/* 222 */       return true; 
/* 224 */     for (int i = 0; i < fatalClasses.length; i++) {
/* 225 */       if (state.startsWith(fatalClasses[i]))
/* 226 */         return true; 
/*     */     } 
/* 228 */     return false;
/*     */   }
/*     */   
/*     */   private void fireConnectionError(SQLException e) {
/* 239 */     if (!isFatalState(e.getSQLState()))
/*     */       return; 
/* 242 */     fireConnectionFatalError(e);
/*     */   }
/*     */   
/*     */   protected abstract ConnectionEvent createConnectionEvent(SQLException paramSQLException);
/*     */   
/*     */   private class ConnectionHandler implements InvocationHandler {
/*     */     private Connection con;
/*     */     
/*     */     private Connection proxy;
/*     */     
/*     */     private boolean automatic = false;
/*     */     
/*     */     public ConnectionHandler(Connection con) {
/* 260 */       this.con = con;
/*     */     }
/*     */     
/*     */     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/* 267 */       if (method.getDeclaringClass().getName().equals("java.lang.Object")) {
/* 269 */         if (method.getName().equals("toString"))
/* 271 */           return "Pooled connection wrapping physical connection " + this.con; 
/* 273 */         if (method.getName().equals("equals"))
/* 275 */           return new Boolean((proxy == args[0])); 
/* 277 */         if (method.getName().equals("hashCode"))
/* 279 */           return new Integer(System.identityHashCode(proxy)); 
/*     */         try {
/* 283 */           return method.invoke(this.con, args);
/* 285 */         } catch (InvocationTargetException e) {
/* 287 */           throw e.getTargetException();
/*     */         } 
/*     */       } 
/* 291 */       if (method.getName().equals("isClosed"))
/* 293 */         return (this.con == null) ? Boolean.TRUE : Boolean.FALSE; 
/* 295 */       if (this.con == null && !method.getName().equals("close"))
/* 297 */         throw new PSQLException(this.automatic ? GT.tr("Connection has been closed automatically because a new connection was opened for the same PooledConnection or the PooledConnection has been closed.") : GT.tr("Connection has been closed."), PSQLState.CONNECTION_DOES_NOT_EXIST); 
/* 300 */       if (method.getName().equals("close")) {
/* 304 */         if (this.con == null)
/* 305 */           return null; 
/* 307 */         SQLException ex = null;
/* 308 */         if (!AbstractJdbc23PooledConnection.this.isXA && !this.con.getAutoCommit())
/*     */           try {
/* 312 */             this.con.rollback();
/* 314 */           } catch (SQLException e) {
/* 316 */             ex = e;
/*     */           }  
/* 319 */         this.con.clearWarnings();
/* 320 */         this.con = null;
/* 321 */         this.proxy = null;
/* 322 */         AbstractJdbc23PooledConnection.this.last = null;
/* 323 */         AbstractJdbc23PooledConnection.this.fireConnectionClosed();
/* 324 */         if (ex != null)
/* 326 */           throw ex; 
/* 328 */         return null;
/*     */       } 
/*     */       try {
/* 335 */         if (method.getName().equals("createStatement")) {
/* 337 */           Statement st = (Statement)method.invoke(this.con, args);
/* 338 */           return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { Statement.class, PGStatement.class }, new AbstractJdbc23PooledConnection.StatementHandler(this, st));
/*     */         } 
/* 340 */         if (method.getName().equals("prepareCall")) {
/* 342 */           Statement st = (Statement)method.invoke(this.con, args);
/* 343 */           return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { CallableStatement.class, PGStatement.class }, new AbstractJdbc23PooledConnection.StatementHandler(this, st));
/*     */         } 
/* 345 */         if (method.getName().equals("prepareStatement")) {
/* 347 */           Statement st = (Statement)method.invoke(this.con, args);
/* 348 */           return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { PreparedStatement.class, PGStatement.class }, new AbstractJdbc23PooledConnection.StatementHandler(this, st));
/*     */         } 
/* 352 */         return method.invoke(this.con, args);
/* 354 */       } catch (InvocationTargetException e) {
/* 355 */         Throwable te = e.getTargetException();
/* 356 */         if (te instanceof SQLException)
/* 357 */           AbstractJdbc23PooledConnection.this.fireConnectionError((SQLException)te); 
/* 358 */         throw te;
/*     */       } 
/*     */     }
/*     */     
/*     */     Connection getProxy() {
/* 363 */       return this.proxy;
/*     */     }
/*     */     
/*     */     void setProxy(Connection proxy) {
/* 367 */       this.proxy = proxy;
/*     */     }
/*     */     
/*     */     public void close() {
/* 372 */       if (this.con != null)
/* 374 */         this.automatic = true; 
/* 376 */       this.con = null;
/* 377 */       this.proxy = null;
/*     */     }
/*     */     
/*     */     public boolean isClosed() {
/* 382 */       return (this.con == null);
/*     */     }
/*     */   }
/*     */   
/*     */   private class StatementHandler implements InvocationHandler {
/*     */     private AbstractJdbc23PooledConnection.ConnectionHandler con;
/*     */     
/*     */     private Statement st;
/*     */     
/*     */     public StatementHandler(AbstractJdbc23PooledConnection.ConnectionHandler con, Statement st) {
/* 401 */       this.con = con;
/* 402 */       this.st = st;
/*     */     }
/*     */     
/*     */     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/* 408 */       if (method.getDeclaringClass().getName().equals("java.lang.Object")) {
/* 410 */         if (method.getName().equals("toString"))
/* 412 */           return "Pooled statement wrapping physical statement " + this.st; 
/* 414 */         if (method.getName().equals("hashCode"))
/* 416 */           return new Integer(System.identityHashCode(proxy)); 
/* 418 */         if (method.getName().equals("equals"))
/* 420 */           return new Boolean((proxy == args[0])); 
/* 422 */         return method.invoke(this.st, args);
/*     */       } 
/* 425 */       if (method.getName().equals("close")) {
/* 428 */         if (this.st == null || this.con.isClosed())
/* 429 */           return null; 
/*     */         try {
/* 433 */           this.st.close();
/*     */         } finally {
/* 437 */           this.con = null;
/* 438 */           this.st = null;
/*     */         } 
/* 440 */         return null;
/*     */       } 
/* 442 */       if (this.st == null || this.con.isClosed())
/* 444 */         throw new PSQLException(GT.tr("Statement has been closed."), PSQLState.OBJECT_NOT_IN_STATE); 
/* 448 */       if (method.getName().equals("getConnection"))
/* 450 */         return this.con.getProxy(); 
/*     */       try {
/* 455 */         return method.invoke(this.st, args);
/* 456 */       } catch (InvocationTargetException e) {
/* 457 */         Throwable te = e.getTargetException();
/* 458 */         if (te instanceof SQLException)
/* 459 */           AbstractJdbc23PooledConnection.this.fireConnectionError((SQLException)te); 
/* 460 */         throw te;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ds\jdbc23\AbstractJdbc23PooledConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */