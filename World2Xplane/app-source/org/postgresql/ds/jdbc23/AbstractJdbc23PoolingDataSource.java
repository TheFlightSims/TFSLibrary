/*     */ package org.postgresql.ds.jdbc23;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Stack;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.Reference;
/*     */ import javax.naming.StringRefAddr;
/*     */ import javax.sql.ConnectionEvent;
/*     */ import javax.sql.ConnectionEventListener;
/*     */ import javax.sql.PooledConnection;
/*     */ import org.postgresql.Driver;
/*     */ import org.postgresql.ds.PGConnectionPoolDataSource;
/*     */ import org.postgresql.ds.PGPooledConnection;
/*     */ import org.postgresql.ds.PGPoolingDataSource;
/*     */ import org.postgresql.ds.common.BaseDataSource;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public abstract class AbstractJdbc23PoolingDataSource extends BaseDataSource {
/*  54 */   protected static Map dataSources = new HashMap<Object, Object>();
/*     */   
/*     */   protected String dataSourceName;
/*     */   
/*     */   public static PGPoolingDataSource getDataSource(String name) {
/*  58 */     return (PGPoolingDataSource)dataSources.get(name);
/*     */   }
/*     */   
/*  63 */   private int initialConnections = 0;
/*     */   
/*  64 */   private int maxConnections = 0;
/*     */   
/*     */   private boolean initialized = false;
/*     */   
/*  67 */   private Stack available = new Stack();
/*     */   
/*  68 */   private Stack used = new Stack();
/*     */   
/*  69 */   private Object lock = new Object();
/*     */   
/*     */   private PGConnectionPoolDataSource source;
/*     */   
/*     */   public String getDescription() {
/*  78 */     return "Pooling DataSource '" + this.dataSourceName + " from " + Driver.getVersion();
/*     */   }
/*     */   
/*     */   public void setServerName(String serverName) {
/*  91 */     if (this.initialized)
/*  93 */       throw new IllegalStateException("Cannot set Data Source properties after DataSource has been used"); 
/*  95 */     super.setServerName(serverName);
/*     */   }
/*     */   
/*     */   public void setDatabaseName(String databaseName) {
/* 108 */     if (this.initialized)
/* 110 */       throw new IllegalStateException("Cannot set Data Source properties after DataSource has been used"); 
/* 112 */     super.setDatabaseName(databaseName);
/*     */   }
/*     */   
/*     */   public void setUser(String user) {
/* 125 */     if (this.initialized)
/* 127 */       throw new IllegalStateException("Cannot set Data Source properties after DataSource has been used"); 
/* 129 */     super.setUser(user);
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/* 142 */     if (this.initialized)
/* 144 */       throw new IllegalStateException("Cannot set Data Source properties after DataSource has been used"); 
/* 146 */     super.setPassword(password);
/*     */   }
/*     */   
/*     */   public void setPortNumber(int portNumber) {
/* 159 */     if (this.initialized)
/* 161 */       throw new IllegalStateException("Cannot set Data Source properties after DataSource has been used"); 
/* 163 */     super.setPortNumber(portNumber);
/*     */   }
/*     */   
/*     */   public int getInitialConnections() {
/* 173 */     return this.initialConnections;
/*     */   }
/*     */   
/*     */   public void setInitialConnections(int initialConnections) {
/* 187 */     if (this.initialized)
/* 189 */       throw new IllegalStateException("Cannot set Data Source properties after DataSource has been used"); 
/* 191 */     this.initialConnections = initialConnections;
/*     */   }
/*     */   
/*     */   public int getMaxConnections() {
/* 204 */     return this.maxConnections;
/*     */   }
/*     */   
/*     */   public void setMaxConnections(int maxConnections) {
/* 222 */     if (this.initialized)
/* 224 */       throw new IllegalStateException("Cannot set Data Source properties after DataSource has been used"); 
/* 226 */     this.maxConnections = maxConnections;
/*     */   }
/*     */   
/*     */   public String getDataSourceName() {
/* 235 */     return this.dataSourceName;
/*     */   }
/*     */   
/*     */   public void setDataSourceName(String dataSourceName) {
/* 252 */     if (this.initialized)
/* 254 */       throw new IllegalStateException("Cannot set Data Source properties after DataSource has been used"); 
/* 256 */     if (this.dataSourceName != null && dataSourceName != null && dataSourceName.equals(this.dataSourceName))
/*     */       return; 
/* 260 */     synchronized (dataSources) {
/* 262 */       if (getDataSource(dataSourceName) != null)
/* 264 */         throw new IllegalArgumentException("DataSource with name '" + dataSourceName + "' already exists!"); 
/* 266 */       if (this.dataSourceName != null)
/* 268 */         dataSources.remove(this.dataSourceName); 
/* 270 */       this.dataSourceName = dataSourceName;
/* 271 */       addDataSource(dataSourceName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initialize() throws SQLException {
/* 287 */     synchronized (this.lock) {
/* 289 */       this.source = createConnectionPool();
/*     */       try {
/* 291 */         this.source.initializeFrom(this);
/* 292 */       } catch (Exception e) {
/* 293 */         throw new PSQLException(GT.tr("Failed to setup DataSource."), PSQLState.UNEXPECTED_ERROR, e);
/*     */       } 
/* 297 */       while (this.available.size() < this.initialConnections)
/* 299 */         this.available.push(this.source.getPooledConnection()); 
/* 302 */       this.initialized = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isInitialized() {
/* 307 */     return this.initialized;
/*     */   }
/*     */   
/*     */   protected PGConnectionPoolDataSource createConnectionPool() {
/* 314 */     return new PGConnectionPoolDataSource();
/*     */   }
/*     */   
/*     */   public Connection getConnection(String user, String password) throws SQLException {
/* 329 */     if (user == null || (user.equals(getUser()) && ((password == null && getPassword() == null) || (password != null && password.equals(getPassword())))))
/* 332 */       return getConnection(); 
/* 335 */     if (!this.initialized)
/* 337 */       initialize(); 
/* 339 */     return super.getConnection(user, password);
/*     */   }
/*     */   
/*     */   public Connection getConnection() throws SQLException {
/* 352 */     if (!this.initialized)
/* 354 */       initialize(); 
/* 356 */     return getPooledConnection();
/*     */   }
/*     */   
/*     */   public void close() {
/* 364 */     synchronized (this.lock) {
/* 366 */       while (this.available.size() > 0) {
/* 368 */         PGPooledConnection pci = this.available.pop();
/*     */         try {
/* 371 */           pci.close();
/* 373 */         } catch (SQLException e) {}
/*     */       } 
/* 377 */       this.available = null;
/* 378 */       while (this.used.size() > 0) {
/* 380 */         PGPooledConnection pci = this.used.pop();
/* 381 */         pci.removeConnectionEventListener(this.connectionEventListener);
/*     */         try {
/* 384 */           pci.close();
/* 386 */         } catch (SQLException e) {}
/*     */       } 
/* 390 */       this.used = null;
/*     */     } 
/* 392 */     removeStoredDataSource();
/*     */   }
/*     */   
/*     */   protected void removeStoredDataSource() {
/* 396 */     synchronized (dataSources) {
/* 398 */       dataSources.remove(this.dataSourceName);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Connection getPooledConnection() throws SQLException {
/* 411 */     PooledConnection pc = null;
/* 412 */     synchronized (this.lock) {
/* 414 */       if (this.available == null)
/* 416 */         throw new PSQLException(GT.tr("DataSource has been closed."), PSQLState.CONNECTION_DOES_NOT_EXIST); 
/*     */       while (true) {
/* 421 */         if (this.available.size() > 0) {
/* 423 */           pc = this.available.pop();
/* 424 */           this.used.push(pc);
/*     */           break;
/*     */         } 
/* 427 */         if (this.maxConnections == 0 || this.used.size() < this.maxConnections) {
/* 429 */           pc = this.source.getPooledConnection();
/* 430 */           this.used.push(pc);
/*     */           break;
/*     */         } 
/*     */         try {
/* 438 */           this.lock.wait(1000L);
/* 440 */         } catch (InterruptedException e) {}
/*     */       } 
/*     */     } 
/* 446 */     pc.addConnectionEventListener(this.connectionEventListener);
/* 447 */     return pc.getConnection();
/*     */   }
/*     */   
/* 455 */   private ConnectionEventListener connectionEventListener = new ConnectionEventListener() {
/*     */       public void connectionClosed(ConnectionEvent event) {
/* 459 */         ((PooledConnection)event.getSource()).removeConnectionEventListener(this);
/* 460 */         synchronized (AbstractJdbc23PoolingDataSource.this.lock) {
/* 462 */           if (AbstractJdbc23PoolingDataSource.this.available == null)
/*     */             return; 
/* 466 */           boolean removed = AbstractJdbc23PoolingDataSource.this.used.remove(event.getSource());
/* 467 */           if (removed) {
/* 469 */             AbstractJdbc23PoolingDataSource.this.available.push(event.getSource());
/* 471 */             AbstractJdbc23PoolingDataSource.this.lock.notify();
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/*     */       public void connectionErrorOccurred(ConnectionEvent event) {
/* 486 */         ((PooledConnection)event.getSource()).removeConnectionEventListener(this);
/* 487 */         synchronized (AbstractJdbc23PoolingDataSource.this.lock) {
/* 489 */           if (AbstractJdbc23PoolingDataSource.this.available == null)
/*     */             return; 
/* 493 */           AbstractJdbc23PoolingDataSource.this.used.remove(event.getSource());
/* 495 */           AbstractJdbc23PoolingDataSource.this.lock.notify();
/*     */         } 
/*     */       }
/*     */     };
/*     */   
/*     */   public Reference getReference() throws NamingException {
/* 506 */     Reference ref = super.getReference();
/* 507 */     ref.add(new StringRefAddr("dataSourceName", this.dataSourceName));
/* 508 */     if (this.initialConnections > 0)
/* 510 */       ref.add(new StringRefAddr("initialConnections", Integer.toString(this.initialConnections))); 
/* 512 */     if (this.maxConnections > 0)
/* 514 */       ref.add(new StringRefAddr("maxConnections", Integer.toString(this.maxConnections))); 
/* 516 */     return ref;
/*     */   }
/*     */   
/*     */   protected abstract void addDataSource(String paramString);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ds\jdbc23\AbstractJdbc23PoolingDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */