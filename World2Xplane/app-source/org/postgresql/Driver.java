/*     */ package org.postgresql;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.sql.Connection;
/*     */ import java.sql.Driver;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.DriverPropertyInfo;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLFeatureNotSupportedException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Properties;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.logging.Logger;
/*     */ import org.postgresql.core.Logger;
/*     */ import org.postgresql.jdbc4.Jdbc4Connection;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class Driver implements Driver {
/*     */   public static final int DEBUG = 2;
/*     */   
/*     */   public static final int INFO = 1;
/*     */   
/*  55 */   private static final Logger logger = new Logger();
/*     */   
/*     */   private static boolean logLevelSet = false;
/*     */   
/*     */   private Properties defaultProperties;
/*     */   
/*     */   static {
/*     */     try {
/*  66 */       DriverManager.registerDriver(new Driver());
/*  68 */     } catch (SQLException e) {
/*  70 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private synchronized Properties getDefaultProperties() throws IOException {
/*  78 */     if (this.defaultProperties != null)
/*  79 */       return this.defaultProperties; 
/*     */     try {
/*  85 */       this.defaultProperties = AccessController.<Properties>doPrivileged(new PrivilegedExceptionAction<Properties>() {
/*     */             public Object run() throws IOException {
/*  88 */               return Driver.this.loadDefaultProperties();
/*     */             }
/*     */           });
/*  92 */     } catch (PrivilegedActionException e) {
/*  94 */       throw (IOException)e.getException();
/*     */     } 
/* 100 */     synchronized (Driver.class) {
/* 101 */       if (!logLevelSet) {
/* 102 */         String driverLogLevel = this.defaultProperties.getProperty("loglevel");
/* 103 */         if (driverLogLevel != null)
/*     */           try {
/* 105 */             setLogLevel(Integer.parseInt(driverLogLevel));
/* 106 */           } catch (Exception l_e) {} 
/*     */       } 
/*     */     } 
/* 114 */     return this.defaultProperties;
/*     */   }
/*     */   
/*     */   private Properties loadDefaultProperties() throws IOException {
/* 118 */     Properties merged = new Properties();
/*     */     try {
/* 121 */       merged.setProperty("user", System.getProperty("user.name"));
/* 122 */     } catch (SecurityException se) {}
/* 135 */     ClassLoader cl = getClass().getClassLoader();
/* 136 */     if (cl == null)
/* 137 */       cl = ClassLoader.getSystemClassLoader(); 
/* 139 */     if (cl == null) {
/* 140 */       logger.debug("Can't find a classloader for the Driver; not loading driver configuration");
/* 141 */       return merged;
/*     */     } 
/* 144 */     logger.debug("Loading driver configuration via classloader " + cl);
/* 150 */     ArrayList<URL> urls = new ArrayList();
/* 151 */     Enumeration<URL> urlEnum = cl.getResources("org/postgresql/driverconfig.properties");
/* 152 */     while (urlEnum.hasMoreElements())
/* 154 */       urls.add(urlEnum.nextElement()); 
/* 157 */     for (int i = urls.size() - 1; i >= 0; i--) {
/* 158 */       URL url = urls.get(i);
/* 159 */       logger.debug("Loading driver configuration from: " + url);
/* 160 */       InputStream is = url.openStream();
/* 161 */       merged.load(is);
/* 162 */       is.close();
/*     */     } 
/* 165 */     return merged;
/*     */   }
/*     */   
/*     */   public Connection connect(String url, Properties info) throws SQLException {
/*     */     Properties defaults;
/*     */     try {
/* 231 */       defaults = getDefaultProperties();
/* 233 */     } catch (IOException ioe) {
/* 235 */       throw new PSQLException(GT.tr("Error loading default settings from driverconfig.properties"), PSQLState.UNEXPECTED_ERROR, ioe);
/*     */     } 
/* 240 */     Properties props = new Properties(defaults);
/* 241 */     for (Enumeration<?> e = info.propertyNames(); e.hasMoreElements(); ) {
/* 243 */       String propName = (String)e.nextElement();
/* 244 */       props.setProperty(propName, info.getProperty(propName));
/*     */     } 
/* 248 */     if ((props = parseURL(url, props)) == null) {
/* 250 */       logger.debug("Error in url: " + url);
/* 251 */       return null;
/*     */     } 
/*     */     try {
/* 255 */       logger.debug("Connecting with URL: " + url);
/* 265 */       long timeout = timeout(props);
/* 266 */       if (timeout <= 0L)
/* 267 */         return makeConnection(url, props); 
/* 269 */       ConnectThread ct = new ConnectThread(url, props);
/* 270 */       (new Thread(ct, "PostgreSQL JDBC driver connection thread")).start();
/* 271 */       return ct.getResult(timeout);
/* 273 */     } catch (PSQLException ex1) {
/* 275 */       logger.debug("Connection error:", (Throwable)ex1);
/* 278 */       throw ex1;
/* 280 */     } catch (AccessControlException ace) {
/* 282 */       throw new PSQLException(GT.tr("Your security policy has prevented the connection from being attempted.  You probably need to grant the connect java.net.SocketPermission to the database server host and port that you wish to connect to."), PSQLState.UNEXPECTED_ERROR, ace);
/* 284 */     } catch (Exception ex2) {
/* 286 */       logger.debug("Unexpected connection error:", ex2);
/* 287 */       throw new PSQLException(GT.tr("Something unusual has occured to cause the driver to fail. Please report this exception."), PSQLState.UNEXPECTED_ERROR, ex2);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static class ConnectThread implements Runnable {
/*     */     private final String url;
/*     */     
/*     */     private final Properties props;
/*     */     
/*     */     private Connection result;
/*     */     
/*     */     private Throwable resultException;
/*     */     
/*     */     private boolean abandoned;
/*     */     
/*     */     ConnectThread(String url, Properties props) {
/* 299 */       this.url = url;
/* 300 */       this.props = props;
/*     */     }
/*     */     
/*     */     public void run() {
/*     */       Connection connection;
/*     */       Throwable throwable;
/*     */       try {
/* 308 */         connection = Driver.makeConnection(this.url, this.props);
/* 309 */         throwable = null;
/* 310 */       } catch (Throwable t) {
/* 311 */         connection = null;
/* 312 */         throwable = t;
/*     */       } 
/* 315 */       synchronized (this) {
/* 316 */         if (this.abandoned) {
/* 317 */           if (connection != null)
/*     */             try {
/* 319 */               connection.close();
/* 320 */             } catch (SQLException e) {} 
/*     */         } else {
/* 323 */           this.result = connection;
/* 324 */           this.resultException = throwable;
/* 325 */           notify();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public Connection getResult(long timeout) throws SQLException {
/* 340 */       long expiry = System.currentTimeMillis() + timeout;
/* 341 */       synchronized (this) {
/*     */         while (true) {
/* 343 */           if (this.result != null)
/* 344 */             return this.result; 
/* 346 */           if (this.resultException != null) {
/* 347 */             if (this.resultException instanceof SQLException) {
/* 348 */               this.resultException.fillInStackTrace();
/* 349 */               throw (SQLException)this.resultException;
/*     */             } 
/* 351 */             throw new PSQLException(GT.tr("Something unusual has occured to cause the driver to fail. Please report this exception."), PSQLState.UNEXPECTED_ERROR, this.resultException);
/*     */           } 
/* 356 */           long delay = expiry - System.currentTimeMillis();
/* 357 */           if (delay <= 0L) {
/* 358 */             this.abandoned = true;
/* 359 */             throw new PSQLException(GT.tr("Connection attempt timed out."), PSQLState.CONNECTION_UNABLE_TO_CONNECT);
/*     */           } 
/*     */           try {
/* 364 */             wait(delay);
/* 365 */           } catch (InterruptedException ie) {
/* 366 */             this.abandoned = true;
/* 367 */             throw new PSQLException(GT.tr("Interrupted while attempting to connect."), PSQLState.CONNECTION_UNABLE_TO_CONNECT);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static Connection makeConnection(String url, Properties props) throws SQLException {
/* 393 */     return (Connection)new Jdbc4Connection(host(props), port(props), user(props), database(props), props, url);
/*     */   }
/*     */   
/*     */   public boolean acceptsURL(String url) throws SQLException {
/* 412 */     if (parseURL(url, null) == null)
/* 413 */       return false; 
/* 414 */     return true;
/*     */   }
/*     */   
/* 417 */   private static final Object[][] knownProperties = new Object[][] { 
/* 417 */       { "PGDBNAME", Boolean.TRUE, "Database name to connect to; may be specified directly in the JDBC URL." }, { "user", Boolean.TRUE, "Username to connect to the database as.", null }, { "PGHOST", Boolean.FALSE, "Hostname of the PostgreSQL server; may be specified directly in the JDBC URL." }, { "PGPORT", Boolean.FALSE, "Port number to connect to the PostgreSQL server on; may be specified directly in the JDBC URL." }, { "password", Boolean.FALSE, "Password to use when authenticating." }, { "protocolVersion", Boolean.FALSE, "Force use of a particular protocol version when connecting; if set, disables protocol version fallback." }, { "ssl", Boolean.FALSE, "Control use of SSL; any nonnull value causes SSL to be required." }, { "sslfactory", Boolean.FALSE, "Provide a SSLSocketFactory class when using SSL." }, { "sslfactoryarg", Boolean.FALSE, "Argument forwarded to constructor of SSLSocketFactory class." }, { "loglevel", Boolean.FALSE, "Control the driver's log verbosity: 0 is off, 1 is INFO, 2 is DEBUG.", { "0", "1", "2" } }, 
/* 417 */       { "allowEncodingChanges", Boolean.FALSE, "Allow the user to change the client_encoding variable." }, { "logUnclosedConnections", Boolean.FALSE, "When connections that are not explicitly closed are garbage collected, log the stacktrace from the opening of the connection to trace the leak source." }, { "prepareThreshold", Boolean.FALSE, "Default statement prepare threshold (numeric)." }, { "charSet", Boolean.FALSE, "When connecting to a pre-7.3 server, the database encoding to assume is in use." }, { "compatible", Boolean.FALSE, "Force compatibility of some features with an older version of the driver.", { "7.1", "7.2", "7.3", "7.4", "8.0", "8.1", "8.2" } }, { "loginTimeout", Boolean.FALSE, "The login timeout, in seconds; 0 means no timeout beyond the normal TCP connection timout." }, { "socketTimeout", Boolean.FALSE, "The timeout value for socket read operations, in seconds; 0 means no timeout." }, { "tcpKeepAlive", Boolean.FALSE, "Enable or disable TCP keep-alive probe." }, { "stringtype", Boolean.FALSE, "The type to bind String parameters as (usually 'varchar'; 'unspecified' allows implicit casting to other types)", { "varchar", "unspecified" } }, { "kerberosServerName", Boolean.FALSE, "The Kerberos service name to use when authenticating with GSSAPI.  This is equivalent to libpq's PGKRBSRVNAME environment variable." }, 
/* 417 */       { "jaasApplicationName", Boolean.FALSE, "Specifies the name of the JAAS system or application login configuration." } };
/*     */   
/*     */   public static final int MAJORVERSION = 9;
/*     */   
/*     */   public static final int MINORVERSION = 1;
/*     */   
/*     */   public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
/* 485 */     Properties copy = new Properties(info);
/* 486 */     parseURL(url, copy);
/* 488 */     DriverPropertyInfo[] props = new DriverPropertyInfo[knownProperties.length];
/* 489 */     for (int i = 0; i < knownProperties.length; i++) {
/* 491 */       String name = (String)knownProperties[i][0];
/* 492 */       props[i] = new DriverPropertyInfo(name, copy.getProperty(name));
/* 493 */       (props[i]).required = ((Boolean)knownProperties[i][1]).booleanValue();
/* 494 */       (props[i]).description = (String)knownProperties[i][2];
/* 495 */       if ((knownProperties[i]).length > 3)
/* 496 */         (props[i]).choices = (String[])knownProperties[i][3]; 
/*     */     } 
/* 499 */     return props;
/*     */   }
/*     */   
/*     */   public int getMajorVersion() {
/* 510 */     return 9;
/*     */   }
/*     */   
/*     */   public int getMinorVersion() {
/* 522 */     return 1;
/*     */   }
/*     */   
/*     */   public static String getVersion() {
/* 531 */     return "PostgreSQL 9.1 JDBC4 (build 901)";
/*     */   }
/*     */   
/*     */   public boolean jdbcCompliant() {
/* 546 */     return false;
/*     */   }
/*     */   
/* 549 */   private static String[] protocols = new String[] { "jdbc", "postgresql" };
/*     */   
/*     */   Properties parseURL(String url, Properties defaults) throws SQLException {
/* 561 */     int state = -1;
/* 562 */     Properties urlProps = new Properties(defaults);
/* 564 */     String l_urlServer = url;
/* 565 */     String l_urlArgs = "";
/* 567 */     int l_qPos = url.indexOf('?');
/* 568 */     if (l_qPos != -1) {
/* 570 */       l_urlServer = url.substring(0, l_qPos);
/* 571 */       l_urlArgs = url.substring(l_qPos + 1);
/*     */     } 
/* 577 */     int ipv6start = l_urlServer.indexOf("[");
/* 578 */     int ipv6end = l_urlServer.indexOf("]");
/* 579 */     String ipv6address = null;
/* 580 */     if (ipv6start != -1 && ipv6end > ipv6start) {
/* 582 */       ipv6address = l_urlServer.substring(ipv6start + 1, ipv6end);
/* 583 */       l_urlServer = l_urlServer.substring(0, ipv6start) + "ipv6host" + l_urlServer.substring(ipv6end + 1);
/*     */     } 
/* 587 */     StringTokenizer st = new StringTokenizer(l_urlServer, ":/", true);
/*     */     int count;
/* 589 */     for (count = 0; st.hasMoreTokens(); count++) {
/* 591 */       String token = st.nextToken();
/* 594 */       if (count <= 3) {
/* 596 */         if (count % 2 != 1 || !token.equals(":"))
/* 598 */           if (count % 2 == 0) {
/* 600 */             boolean found = (count == 0);
/* 601 */             for (int tmp = 0; tmp < protocols.length; tmp++) {
/* 603 */               if (token.equals(protocols[tmp]))
/* 607 */                 if (count == 2 && tmp > 0) {
/* 609 */                   urlProps.setProperty("Protocol", token);
/* 610 */                   found = true;
/*     */                 }  
/*     */             } 
/* 615 */             if (!found)
/* 616 */               return null; 
/*     */           } else {
/* 619 */             return null;
/*     */           }  
/* 621 */       } else if (count > 3) {
/* 623 */         if (count == 4 && token.equals("/")) {
/* 624 */           state = 0;
/* 625 */         } else if (count == 4) {
/* 627 */           urlProps.setProperty("PGDBNAME", token);
/* 628 */           state = -2;
/* 630 */         } else if (count == 5 && state == 0 && token.equals("/")) {
/* 631 */           state = 1;
/*     */         } else {
/* 632 */           if (count == 5 && state == 0)
/* 633 */             return null; 
/* 634 */           if (count == 6 && state == 1) {
/* 635 */             urlProps.setProperty("PGHOST", token);
/* 636 */           } else if (count == 7 && token.equals(":")) {
/* 637 */             state = 2;
/* 638 */           } else if (count == 8 && state == 2) {
/*     */             try {
/* 642 */               Integer portNumber = Integer.decode(token);
/* 643 */               urlProps.setProperty("PGPORT", portNumber.toString());
/* 645 */             } catch (Exception e) {
/* 647 */               return null;
/*     */             } 
/* 650 */           } else if ((count == 7 || count == 9) && (state == 1 || state == 2) && token.equals("/")) {
/* 652 */             state = -1;
/* 653 */           } else if (state == -1) {
/* 655 */             urlProps.setProperty("PGDBNAME", token);
/* 656 */             state = -2;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 660 */     if (count <= 1)
/* 662 */       return null; 
/* 666 */     if (ipv6address != null)
/* 667 */       urlProps.setProperty("PGHOST", ipv6address); 
/* 670 */     StringTokenizer qst = new StringTokenizer(l_urlArgs, "&");
/* 671 */     for (count = 0; qst.hasMoreTokens(); count++) {
/* 673 */       String token = qst.nextToken();
/* 674 */       int l_pos = token.indexOf('=');
/* 675 */       if (l_pos == -1) {
/* 677 */         urlProps.setProperty(token, "");
/*     */       } else {
/* 681 */         urlProps.setProperty(token.substring(0, l_pos), token.substring(l_pos + 1));
/*     */       } 
/*     */     } 
/* 685 */     return urlProps;
/*     */   }
/*     */   
/*     */   private static String host(Properties props) {
/* 693 */     return props.getProperty("PGHOST", "localhost");
/*     */   }
/*     */   
/*     */   private static int port(Properties props) {
/* 701 */     return Integer.parseInt(props.getProperty("PGPORT", "5432"));
/*     */   }
/*     */   
/*     */   private static String user(Properties props) {
/* 709 */     return props.getProperty("user", "");
/*     */   }
/*     */   
/*     */   private static String database(Properties props) {
/* 717 */     return props.getProperty("PGDBNAME", "");
/*     */   }
/*     */   
/*     */   private static long timeout(Properties props) {
/* 725 */     String timeout = props.getProperty("loginTimeout");
/* 726 */     if (timeout != null)
/*     */       try {
/* 728 */         return (long)(Float.parseFloat(timeout) * 1000.0F);
/* 729 */       } catch (NumberFormatException e) {
/* 732 */         logger.debug("Couldn't parse loginTimeout value: " + timeout);
/*     */       }  
/* 735 */     return (DriverManager.getLoginTimeout() * 1000);
/*     */   }
/*     */   
/*     */   public static SQLFeatureNotSupportedException notImplemented(Class callClass, String functionName) {
/* 753 */     return new SQLFeatureNotSupportedException(GT.tr("Method {0} is not yet implemented.", callClass.getName() + "." + functionName), PSQLState.NOT_IMPLEMENTED.getState());
/*     */   }
/*     */   
/*     */   public static void setLogLevel(int logLevel) {
/* 766 */     synchronized (Driver.class) {
/* 767 */       logger.setLogLevel(logLevel);
/* 768 */       logLevelSet = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int getLogLevel() {
/* 774 */     synchronized (Driver.class) {
/* 775 */       return logger.getLogLevel();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
/* 781 */     throw notImplemented(getClass(), "getParentLogger()");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\Driver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */