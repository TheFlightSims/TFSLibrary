/*     */ package org.postgresql.ds.common;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.SQLException;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.Reference;
/*     */ import javax.naming.Referenceable;
/*     */ import javax.naming.StringRefAddr;
/*     */ 
/*     */ public abstract class BaseDataSource implements Referenceable {
/*     */   private transient PrintWriter logger;
/*     */   
/*     */   static {
/*     */     try {
/*  35 */       Class.forName("org.postgresql.Driver");
/*  37 */     } catch (ClassNotFoundException e) {
/*  39 */       System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
/*     */     } 
/*     */   }
/*     */   
/*  47 */   private String serverName = "localhost";
/*     */   
/*     */   private String databaseName;
/*     */   
/*     */   private String user;
/*     */   
/*     */   private String password;
/*     */   
/*  51 */   private int portNumber = 0;
/*     */   
/*  52 */   private int prepareThreshold = 5;
/*     */   
/*  53 */   private int unknownLength = Integer.MAX_VALUE;
/*     */   
/*  54 */   private int loginTimeout = 0;
/*     */   
/*  55 */   private int socketTimeout = 0;
/*     */   
/*     */   private boolean ssl = false;
/*     */   
/*     */   private String sslfactory;
/*     */   
/*     */   private boolean tcpKeepAlive = false;
/*     */   
/*     */   private String compatible;
/*     */   
/*  60 */   private int logLevel = 0;
/*     */   
/*  61 */   private int protocolVersion = 0;
/*     */   
/*     */   private String applicationName;
/*     */   
/*     */   public Connection getConnection() throws SQLException {
/*  75 */     return getConnection(this.user, this.password);
/*     */   }
/*     */   
/*     */   public Connection getConnection(String user, String password) throws SQLException {
/*     */     try {
/*  92 */       Connection con = DriverManager.getConnection(getUrl(), user, password);
/*  93 */       if (this.logger != null)
/*  95 */         this.logger.println("Created a non-pooled connection for " + user + " at " + getUrl()); 
/*  97 */       return con;
/*  99 */     } catch (SQLException e) {
/* 101 */       if (this.logger != null)
/* 103 */         this.logger.println("Failed to create a non-pooled connection for " + user + " at " + getUrl() + ": " + e); 
/* 105 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getLoginTimeout() throws SQLException {
/* 114 */     return this.loginTimeout;
/*     */   }
/*     */   
/*     */   public void setLoginTimeout(int i) throws SQLException {
/* 122 */     this.loginTimeout = i;
/*     */   }
/*     */   
/*     */   public PrintWriter getLogWriter() throws SQLException {
/* 130 */     return this.logger;
/*     */   }
/*     */   
/*     */   public void setLogWriter(PrintWriter printWriter) throws SQLException {
/* 138 */     this.logger = printWriter;
/*     */   }
/*     */   
/*     */   public String getServerName() {
/* 146 */     return this.serverName;
/*     */   }
/*     */   
/*     */   public void setServerName(String serverName) {
/* 156 */     if (serverName == null || serverName.equals("")) {
/* 158 */       this.serverName = "localhost";
/*     */     } else {
/* 162 */       this.serverName = serverName;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getCompatible() {
/* 168 */     return this.compatible;
/*     */   }
/*     */   
/*     */   public void setCompatible(String compatible) {
/* 173 */     this.compatible = compatible;
/*     */   }
/*     */   
/*     */   public int getLogLevel() {
/* 178 */     return this.logLevel;
/*     */   }
/*     */   
/*     */   public void setLogLevel(int logLevel) {
/* 183 */     this.logLevel = logLevel;
/*     */   }
/*     */   
/*     */   public int getProtocolVersion() {
/* 188 */     return this.protocolVersion;
/*     */   }
/*     */   
/*     */   public void setProtocolVersion(int protocolversion) {
/* 193 */     this.protocolVersion = this.protocolVersion;
/*     */   }
/*     */   
/*     */   public String getDatabaseName() {
/* 202 */     return this.databaseName;
/*     */   }
/*     */   
/*     */   public void setDatabaseName(String databaseName) {
/* 212 */     this.databaseName = databaseName;
/*     */   }
/*     */   
/*     */   public String getUser() {
/* 227 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(String user) {
/* 237 */     this.user = user;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/* 247 */     return this.password;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/* 258 */     this.password = password;
/*     */   }
/*     */   
/*     */   public int getPortNumber() {
/* 269 */     return this.portNumber;
/*     */   }
/*     */   
/*     */   public void setPortNumber(int portNumber) {
/* 279 */     this.portNumber = portNumber;
/*     */   }
/*     */   
/*     */   public void setPrepareThreshold(int count) {
/* 291 */     this.prepareThreshold = count;
/*     */   }
/*     */   
/*     */   public int getPrepareThreshold() {
/* 301 */     return this.prepareThreshold;
/*     */   }
/*     */   
/*     */   public void setUnknownLength(int unknownLength) {
/* 306 */     this.unknownLength = unknownLength;
/*     */   }
/*     */   
/*     */   public int getUnknownLength() {
/* 311 */     return this.unknownLength;
/*     */   }
/*     */   
/*     */   public void setSocketTimeout(int seconds) {
/* 319 */     this.socketTimeout = seconds;
/*     */   }
/*     */   
/*     */   public int getSocketTimeout() {
/* 327 */     return this.socketTimeout;
/*     */   }
/*     */   
/*     */   public void setSsl(boolean enabled) {
/* 338 */     this.ssl = enabled;
/*     */   }
/*     */   
/*     */   public boolean getSsl() {
/* 348 */     return this.ssl;
/*     */   }
/*     */   
/*     */   public void setSslfactory(String classname) {
/* 359 */     this.sslfactory = classname;
/*     */   }
/*     */   
/*     */   public String getSslfactory() {
/* 369 */     return this.sslfactory;
/*     */   }
/*     */   
/*     */   public void setApplicationName(String applicationName) {
/* 374 */     this.applicationName = applicationName;
/*     */   }
/*     */   
/*     */   public String getApplicationName() {
/* 379 */     return this.applicationName;
/*     */   }
/*     */   
/*     */   public void setTcpKeepAlive(boolean enabled) {
/* 384 */     this.tcpKeepAlive = enabled;
/*     */   }
/*     */   
/*     */   public boolean getTcpKeepAlive() {
/* 389 */     return this.tcpKeepAlive;
/*     */   }
/*     */   
/*     */   private String getUrl() {
/* 397 */     StringBuffer sb = new StringBuffer(100);
/* 398 */     sb.append("jdbc:postgresql://");
/* 399 */     sb.append(this.serverName);
/* 400 */     if (this.portNumber != 0)
/* 401 */       sb.append(":").append(this.portNumber); 
/* 403 */     sb.append("/").append(this.databaseName);
/* 404 */     sb.append("?loginTimeout=").append(this.loginTimeout);
/* 405 */     sb.append("&socketTimeout=").append(this.socketTimeout);
/* 406 */     sb.append("&prepareThreshold=").append(this.prepareThreshold);
/* 407 */     sb.append("&unknownLength=").append(this.unknownLength);
/* 408 */     sb.append("&loglevel=").append(this.logLevel);
/* 409 */     if (this.protocolVersion != 0)
/* 410 */       sb.append("&protocolVersion=").append(this.protocolVersion); 
/* 412 */     if (this.ssl) {
/* 413 */       sb.append("&ssl=true");
/* 414 */       if (this.sslfactory != null)
/* 415 */         sb.append("&sslfactory=").append(this.sslfactory); 
/*     */     } 
/* 418 */     sb.append("&tcpkeepalive=").append(this.tcpKeepAlive);
/* 419 */     if (this.compatible != null)
/* 420 */       sb.append("&compatible=" + this.compatible); 
/* 422 */     if (this.applicationName != null) {
/* 423 */       sb.append("&ApplicationName=");
/* 424 */       sb.append(this.applicationName);
/*     */     } 
/* 427 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected Reference createReference() {
/* 434 */     return new Reference(getClass().getName(), PGObjectFactory.class.getName(), null);
/*     */   }
/*     */   
/*     */   public Reference getReference() throws NamingException {
/* 442 */     Reference ref = createReference();
/* 443 */     ref.add(new StringRefAddr("serverName", this.serverName));
/* 444 */     if (this.portNumber != 0)
/* 446 */       ref.add(new StringRefAddr("portNumber", Integer.toString(this.portNumber))); 
/* 448 */     ref.add(new StringRefAddr("databaseName", this.databaseName));
/* 449 */     if (this.user != null)
/* 451 */       ref.add(new StringRefAddr("user", this.user)); 
/* 453 */     if (this.password != null)
/* 455 */       ref.add(new StringRefAddr("password", this.password)); 
/* 458 */     ref.add(new StringRefAddr("prepareThreshold", Integer.toString(this.prepareThreshold)));
/* 459 */     ref.add(new StringRefAddr("unknownLength", Integer.toString(this.unknownLength)));
/* 460 */     ref.add(new StringRefAddr("loginTimeout", Integer.toString(this.loginTimeout)));
/* 461 */     ref.add(new StringRefAddr("socketTimeout", Integer.toString(this.socketTimeout)));
/* 463 */     ref.add(new StringRefAddr("ssl", Boolean.toString(this.ssl)));
/* 464 */     ref.add(new StringRefAddr("sslfactory", this.sslfactory));
/* 466 */     ref.add(new StringRefAddr("tcpKeepAlive", Boolean.toString(this.tcpKeepAlive)));
/* 467 */     if (this.compatible != null)
/* 469 */       ref.add(new StringRefAddr("compatible", this.compatible)); 
/* 472 */     ref.add(new StringRefAddr("logLevel", Integer.toString(this.logLevel)));
/* 473 */     ref.add(new StringRefAddr("protocolVersion", Integer.toString(this.protocolVersion)));
/* 474 */     ref.add(new StringRefAddr("ApplicationName", this.applicationName));
/* 476 */     return ref;
/*     */   }
/*     */   
/*     */   protected void writeBaseObject(ObjectOutputStream out) throws IOException {
/* 481 */     out.writeObject(this.serverName);
/* 482 */     out.writeObject(this.databaseName);
/* 483 */     out.writeObject(this.user);
/* 484 */     out.writeObject(this.password);
/* 485 */     out.writeInt(this.portNumber);
/* 486 */     out.writeInt(this.prepareThreshold);
/* 487 */     out.writeInt(this.unknownLength);
/* 488 */     out.writeInt(this.loginTimeout);
/* 489 */     out.writeInt(this.socketTimeout);
/* 490 */     out.writeBoolean(this.ssl);
/* 491 */     out.writeObject(this.sslfactory);
/* 492 */     out.writeBoolean(this.tcpKeepAlive);
/* 493 */     out.writeObject(this.compatible);
/* 494 */     out.writeInt(this.logLevel);
/* 495 */     out.writeInt(this.protocolVersion);
/* 496 */     out.writeObject(this.applicationName);
/*     */   }
/*     */   
/*     */   protected void readBaseObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 501 */     this.serverName = (String)in.readObject();
/* 502 */     this.databaseName = (String)in.readObject();
/* 503 */     this.user = (String)in.readObject();
/* 504 */     this.password = (String)in.readObject();
/* 505 */     this.portNumber = in.readInt();
/* 506 */     this.prepareThreshold = in.readInt();
/* 507 */     this.unknownLength = in.readInt();
/* 508 */     this.loginTimeout = in.readInt();
/* 509 */     this.socketTimeout = in.readInt();
/* 510 */     this.ssl = in.readBoolean();
/* 511 */     this.sslfactory = (String)in.readObject();
/* 512 */     this.tcpKeepAlive = in.readBoolean();
/* 513 */     this.compatible = (String)in.readObject();
/* 514 */     this.logLevel = in.readInt();
/* 515 */     this.protocolVersion = in.readInt();
/* 516 */     this.applicationName = (String)in.readObject();
/*     */   }
/*     */   
/*     */   public void initializeFrom(BaseDataSource source) throws IOException, ClassNotFoundException {
/* 520 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 521 */     ObjectOutputStream oos = new ObjectOutputStream(baos);
/* 522 */     source.writeBaseObject(oos);
/* 523 */     oos.close();
/* 524 */     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
/* 525 */     ObjectInputStream ois = new ObjectInputStream(bais);
/* 526 */     readBaseObject(ois);
/*     */   }
/*     */   
/*     */   public abstract String getDescription();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ds\common\BaseDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */