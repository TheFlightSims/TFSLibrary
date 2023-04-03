/*     */ package org.postgresql.core.v2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.ConnectException;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLWarning;
/*     */ import java.util.Properties;
/*     */ import java.util.StringTokenizer;
/*     */ import org.postgresql.core.ConnectionFactory;
/*     */ import org.postgresql.core.Encoding;
/*     */ import org.postgresql.core.Logger;
/*     */ import org.postgresql.core.PGStream;
/*     */ import org.postgresql.core.ProtocolConnection;
/*     */ import org.postgresql.core.SetupQueryRunner;
/*     */ import org.postgresql.core.Utils;
/*     */ import org.postgresql.ssl.MakeSSL;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.MD5Digest;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ import org.postgresql.util.UnixCrypt;
/*     */ 
/*     */ public class ConnectionFactoryImpl extends ConnectionFactory {
/*     */   private static final int AUTH_REQ_OK = 0;
/*     */   
/*     */   private static final int AUTH_REQ_KRB4 = 1;
/*     */   
/*     */   private static final int AUTH_REQ_KRB5 = 2;
/*     */   
/*     */   private static final int AUTH_REQ_PASSWORD = 3;
/*     */   
/*     */   private static final int AUTH_REQ_CRYPT = 4;
/*     */   
/*     */   private static final int AUTH_REQ_MD5 = 5;
/*     */   
/*     */   private static final int AUTH_REQ_SCM = 6;
/*     */   
/*     */   public ProtocolConnection openConnectionImpl(String host, int port, String user, String database, Properties info, Logger logger) throws SQLException {
/*  45 */     boolean requireSSL = (info.getProperty("ssl") != null);
/*  46 */     boolean trySSL = requireSSL;
/*  49 */     boolean requireTCPKeepAlive = Boolean.valueOf(info.getProperty("tcpKeepAlive")).booleanValue();
/*  51 */     if (logger.logDebug())
/*  52 */       logger.debug("Trying to establish a protocol version 2 connection to " + host + ":" + port); 
/*  58 */     PGStream newStream = null;
/*     */     try {
/*  61 */       newStream = new PGStream(host, port);
/*  64 */       if (trySSL)
/*  65 */         newStream = enableSSL(newStream, requireSSL, info, logger); 
/*  69 */       String socketTimeoutProperty = info.getProperty("socketTimeout", "0");
/*     */       try {
/*  71 */         int socketTimeout = Integer.parseInt(socketTimeoutProperty);
/*  72 */         if (socketTimeout > 0)
/*  73 */           newStream.getSocket().setSoTimeout(socketTimeout * 1000); 
/*  75 */       } catch (NumberFormatException nfe) {
/*  76 */         logger.info("Couldn't parse socketTimeout value:" + socketTimeoutProperty);
/*     */       } 
/*  81 */       newStream.getSocket().setKeepAlive(requireTCPKeepAlive);
/*  84 */       sendStartupPacket(newStream, user, database, logger);
/*  87 */       doAuthentication(newStream, user, info.getProperty("password"), logger);
/*  90 */       ProtocolConnectionImpl protoConnection = new ProtocolConnectionImpl(newStream, user, database, logger);
/*  91 */       readStartupMessages(newStream, protoConnection, logger);
/*  94 */       runInitialQueries(protoConnection, info, logger);
/*  97 */       return protoConnection;
/*  99 */     } catch (ConnectException cex) {
/* 104 */       throw new PSQLException(GT.tr("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections."), PSQLState.CONNECTION_UNABLE_TO_CONNECT, cex);
/* 106 */     } catch (IOException ioe) {
/* 108 */       if (newStream != null)
/*     */         try {
/* 112 */           newStream.close();
/* 114 */         } catch (IOException e) {} 
/* 119 */       throw new PSQLException(GT.tr("The connection attempt failed."), PSQLState.CONNECTION_UNABLE_TO_CONNECT, ioe);
/* 121 */     } catch (SQLException se) {
/* 123 */       if (newStream != null)
/*     */         try {
/* 127 */           newStream.close();
/* 129 */         } catch (IOException e) {} 
/* 134 */       throw se;
/*     */     } 
/*     */   }
/*     */   
/*     */   private PGStream enableSSL(PGStream pgStream, boolean requireSSL, Properties info, Logger logger) throws IOException, SQLException {
/* 139 */     if (logger.logDebug())
/* 140 */       logger.debug(" FE=> SSLRequest"); 
/* 143 */     pgStream.SendInteger4(8);
/* 144 */     pgStream.SendInteger2(1234);
/* 145 */     pgStream.SendInteger2(5679);
/* 146 */     pgStream.flush();
/* 149 */     int beresp = pgStream.ReceiveChar();
/* 150 */     switch (beresp) {
/*     */       case 69:
/* 153 */         if (logger.logDebug())
/* 154 */           logger.debug(" <=BE SSLError"); 
/* 157 */         if (requireSSL)
/* 158 */           throw new PSQLException(GT.tr("The server does not support SSL."), PSQLState.CONNECTION_REJECTED); 
/* 161 */         pgStream.close();
/* 162 */         return new PGStream(pgStream.getHost(), pgStream.getPort());
/*     */       case 78:
/* 165 */         if (logger.logDebug())
/* 166 */           logger.debug(" <=BE SSLRefused"); 
/* 169 */         if (requireSSL)
/* 170 */           throw new PSQLException(GT.tr("The server does not support SSL."), PSQLState.CONNECTION_REJECTED); 
/* 172 */         return pgStream;
/*     */       case 83:
/* 175 */         if (logger.logDebug())
/* 176 */           logger.debug(" <=BE SSLOk"); 
/* 179 */         MakeSSL.convert(pgStream, info, logger);
/* 180 */         return pgStream;
/*     */     } 
/* 183 */     throw new PSQLException(GT.tr("An error occured while setting up the SSL connection."), PSQLState.PROTOCOL_VIOLATION);
/*     */   }
/*     */   
/*     */   private void sendStartupPacket(PGStream pgStream, String user, String database, Logger logger) throws IOException {
/* 197 */     if (logger.logDebug())
/* 198 */       logger.debug(" FE=> StartupPacket(user=" + user + ",database=" + database + ")"); 
/* 200 */     pgStream.SendInteger4(296);
/* 201 */     pgStream.SendInteger2(2);
/* 202 */     pgStream.SendInteger2(0);
/* 203 */     pgStream.Send(database.getBytes("UTF-8"), 64);
/* 204 */     pgStream.Send(user.getBytes("UTF-8"), 32);
/* 205 */     pgStream.Send(new byte[64]);
/* 206 */     pgStream.Send(new byte[64]);
/* 207 */     pgStream.Send(new byte[64]);
/* 208 */     pgStream.flush();
/*     */   }
/*     */   
/*     */   private void doAuthentication(PGStream pgStream, String user, String password, Logger logger) throws IOException, SQLException {
/*     */     while (true) {
/*     */       String errorMsg;
/*     */       int areq;
/*     */       byte[] salt, md5Salt, encodedPassword, encodedResult, digest;
/* 218 */       int beresp = pgStream.ReceiveChar();
/* 220 */       switch (beresp) {
/*     */         case 69:
/* 229 */           errorMsg = pgStream.ReceiveString();
/* 230 */           if (logger.logDebug())
/* 231 */             logger.debug(" <=BE ErrorMessage(" + errorMsg + ")"); 
/* 232 */           throw new PSQLException(GT.tr("Connection rejected: {0}.", errorMsg), PSQLState.CONNECTION_REJECTED);
/*     */         case 82:
/* 237 */           areq = pgStream.ReceiveInteger4();
/* 240 */           switch (areq) {
/*     */             case 4:
/* 244 */               salt = pgStream.Receive(2);
/* 246 */               if (logger.logDebug())
/* 247 */                 logger.debug(" <=BE AuthenticationReqCrypt(salt='" + new String(salt, "US-ASCII") + "')"); 
/* 249 */               if (password == null)
/* 250 */                 throw new PSQLException(GT.tr("The server requested password-based authentication, but no password was provided."), PSQLState.CONNECTION_REJECTED); 
/* 252 */               encodedResult = UnixCrypt.crypt(salt, password.getBytes("UTF-8"));
/* 254 */               if (logger.logDebug())
/* 255 */                 logger.debug(" FE=> Password(crypt='" + new String(encodedResult, "US-ASCII") + "')"); 
/* 257 */               pgStream.SendInteger4(4 + encodedResult.length + 1);
/* 258 */               pgStream.Send(encodedResult);
/* 259 */               pgStream.SendChar(0);
/* 260 */               pgStream.flush();
/*     */               continue;
/*     */             case 5:
/* 267 */               md5Salt = pgStream.Receive(4);
/* 268 */               if (logger.logDebug())
/* 269 */                 logger.debug(" <=BE AuthenticationReqMD5(salt=" + Utils.toHexString(md5Salt) + ")"); 
/* 271 */               if (password == null)
/* 272 */                 throw new PSQLException(GT.tr("The server requested password-based authentication, but no password was provided."), PSQLState.CONNECTION_REJECTED); 
/* 274 */               digest = MD5Digest.encode(user.getBytes("UTF-8"), password.getBytes("UTF-8"), md5Salt);
/* 275 */               if (logger.logDebug())
/* 276 */                 logger.debug(" FE=> Password(md5digest=" + new String(digest, "US-ASCII") + ")"); 
/* 278 */               pgStream.SendInteger4(4 + digest.length + 1);
/* 279 */               pgStream.Send(digest);
/* 280 */               pgStream.SendChar(0);
/* 281 */               pgStream.flush();
/*     */               continue;
/*     */             case 3:
/* 288 */               if (logger.logDebug())
/* 289 */                 logger.debug(" <=BE AuthenticationReqPassword"); 
/* 291 */               if (password == null)
/* 292 */                 throw new PSQLException(GT.tr("The server requested password-based authentication, but no password was provided."), PSQLState.CONNECTION_REJECTED); 
/* 294 */               if (logger.logDebug())
/* 295 */                 logger.debug(" FE=> Password(password=<not shown>)"); 
/* 297 */               encodedPassword = password.getBytes("UTF-8");
/* 298 */               pgStream.SendInteger4(4 + encodedPassword.length + 1);
/* 299 */               pgStream.Send(encodedPassword);
/* 300 */               pgStream.SendChar(0);
/* 301 */               pgStream.flush();
/*     */               continue;
/*     */             case 0:
/* 307 */               if (logger.logDebug())
/* 308 */                 logger.debug(" <=BE AuthenticationOk"); 
/*     */               return;
/*     */           } 
/* 313 */           if (logger.logDebug())
/* 314 */             logger.debug(" <=BE AuthenticationReq (unsupported type " + areq + ")"); 
/* 316 */           throw new PSQLException(GT.tr("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client''s IP address or subnet, and that it is using an authentication scheme supported by the driver.", new Integer(areq)), PSQLState.CONNECTION_REJECTED);
/*     */       } 
/*     */       break;
/*     */     } 
/* 322 */     throw new PSQLException(GT.tr("Protocol error.  Session setup failed."), PSQLState.PROTOCOL_VIOLATION);
/*     */   }
/*     */   
/*     */   private void readStartupMessages(PGStream pgStream, ProtocolConnectionImpl protoConnection, Logger logger) throws IOException, SQLException {
/*     */     while (true) {
/*     */       int pid, ckey;
/*     */       String errorMsg, warnMsg;
/* 330 */       int beresp = pgStream.ReceiveChar();
/* 331 */       switch (beresp) {
/*     */         case 90:
/* 334 */           if (logger.logDebug())
/* 335 */             logger.debug(" <=BE ReadyForQuery"); 
/*     */           return;
/*     */         case 75:
/* 339 */           pid = pgStream.ReceiveInteger4();
/* 340 */           ckey = pgStream.ReceiveInteger4();
/* 341 */           if (logger.logDebug())
/* 342 */             logger.debug(" <=BE BackendKeyData(pid=" + pid + ",ckey=" + ckey + ")"); 
/* 343 */           protoConnection.setBackendKeyData(pid, ckey);
/*     */           continue;
/*     */         case 69:
/* 347 */           errorMsg = pgStream.ReceiveString();
/* 348 */           if (logger.logDebug())
/* 349 */             logger.debug(" <=BE ErrorResponse(" + errorMsg + ")"); 
/* 350 */           throw new PSQLException(GT.tr("Backend start-up failed: {0}.", errorMsg), PSQLState.CONNECTION_UNABLE_TO_CONNECT);
/*     */         case 78:
/* 353 */           warnMsg = pgStream.ReceiveString();
/* 354 */           if (logger.logDebug())
/* 355 */             logger.debug(" <=BE NoticeResponse(" + warnMsg + ")"); 
/* 356 */           protoConnection.addWarning(new SQLWarning(warnMsg));
/*     */           continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 360 */     throw new PSQLException(GT.tr("Protocol error.  Session setup failed."), PSQLState.PROTOCOL_VIOLATION);
/*     */   }
/*     */   
/*     */   private void runInitialQueries(ProtocolConnectionImpl protoConnection, Properties info, Logger logger) throws SQLException, IOException {
/* 366 */     byte[][] results = SetupQueryRunner.run(protoConnection, "set datestyle = 'ISO'; select version(), case when pg_encoding_to_char(1) = 'SQL_ASCII' then 'UNKNOWN' else getdatabaseencoding() end", true);
/* 368 */     String rawDbVersion = protoConnection.getEncoding().decode(results[0]);
/* 369 */     StringTokenizer versionParts = new StringTokenizer(rawDbVersion);
/* 370 */     versionParts.nextToken();
/* 371 */     String dbVersion = versionParts.nextToken();
/* 373 */     protoConnection.setServerVersion(dbVersion);
/* 375 */     if (dbVersion.compareTo("7.3") >= 0) {
/* 382 */       if (logger.logDebug())
/* 383 */         logger.debug("Switching to UTF8 client_encoding"); 
/* 385 */       String sql = "begin; set autocommit = on; set client_encoding = 'UTF8'; ";
/* 386 */       if (dbVersion.compareTo("9.0") >= 0) {
/* 387 */         sql = sql + "SET extra_float_digits=3; ";
/* 388 */       } else if (dbVersion.compareTo("7.4") >= 0) {
/* 389 */         sql = sql + "SET extra_float_digits=2; ";
/*     */       } 
/* 391 */       sql = sql + "commit";
/* 393 */       SetupQueryRunner.run(protoConnection, sql, false);
/* 394 */       protoConnection.setEncoding(Encoding.getDatabaseEncoding("UTF8"));
/*     */     } else {
/* 398 */       String charSet = info.getProperty("charSet");
/* 399 */       String dbEncoding = (results[1] == null) ? null : protoConnection.getEncoding().decode(results[1]);
/* 400 */       if (logger.logDebug()) {
/* 402 */         logger.debug("Specified charset:  " + charSet);
/* 403 */         logger.debug("Database encoding: " + dbEncoding);
/*     */       } 
/* 406 */       if (charSet != null) {
/* 409 */         protoConnection.setEncoding(Encoding.getJVMEncoding(charSet));
/* 411 */       } else if (dbEncoding != null) {
/* 414 */         protoConnection.setEncoding(Encoding.getDatabaseEncoding(dbEncoding));
/*     */       } else {
/* 420 */         protoConnection.setEncoding(Encoding.defaultEncoding());
/*     */       } 
/*     */     } 
/* 424 */     if (logger.logDebug())
/* 425 */       logger.debug("Connection encoding (using JVM's nomenclature): " + protoConnection.getEncoding()); 
/* 427 */     if (dbVersion.compareTo("8.1") >= 0) {
/* 430 */       results = SetupQueryRunner.run(protoConnection, "select current_setting('standard_conforming_strings')", true);
/* 431 */       String value = protoConnection.getEncoding().decode(results[0]);
/* 432 */       protoConnection.setStandardConformingStrings(value.equalsIgnoreCase("on"));
/*     */     } else {
/* 436 */       protoConnection.setStandardConformingStrings(false);
/*     */     } 
/* 439 */     String appName = info.getProperty("ApplicationName");
/* 440 */     if (appName != null && dbVersion.compareTo("9.0") >= 0) {
/* 442 */       StringBuffer sb = new StringBuffer("SET application_name = '");
/* 443 */       Utils.appendEscapedLiteral(sb, appName, protoConnection.getStandardConformingStrings());
/* 444 */       sb.append("'");
/* 445 */       SetupQueryRunner.run(protoConnection, sb.toString(), false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v2\ConnectionFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */