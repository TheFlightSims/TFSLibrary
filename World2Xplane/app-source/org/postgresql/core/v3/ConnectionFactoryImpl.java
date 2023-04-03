/*     */ package org.postgresql.core.v3;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.ConnectException;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLWarning;
/*     */ import java.util.Properties;
/*     */ import org.postgresql.core.ConnectionFactory;
/*     */ import org.postgresql.core.Encoding;
/*     */ import org.postgresql.core.Logger;
/*     */ import org.postgresql.core.PGStream;
/*     */ import org.postgresql.core.ProtocolConnection;
/*     */ import org.postgresql.core.SetupQueryRunner;
/*     */ import org.postgresql.core.Utils;
/*     */ import org.postgresql.gss.MakeGSS;
/*     */ import org.postgresql.ssl.MakeSSL;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.MD5Digest;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ import org.postgresql.util.PSQLWarning;
/*     */ import org.postgresql.util.ServerErrorMessage;
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
/*     */   private static final int AUTH_REQ_GSS = 7;
/*     */   
/*     */   private static final int AUTH_REQ_GSS_CONTINUE = 8;
/*     */   
/*     */   private static final int AUTH_REQ_SSPI = 9;
/*     */   
/*     */   private static class UnsupportedProtocolException extends IOException {
/*     */     private UnsupportedProtocolException() {}
/*     */   }
/*     */   
/*     */   public ProtocolConnection openConnectionImpl(String host, int port, String user, String database, Properties info, Logger logger) throws SQLException {
/*  53 */     boolean requireSSL = (info.getProperty("ssl") != null);
/*  54 */     boolean trySSL = requireSSL;
/*  57 */     boolean requireTCPKeepAlive = Boolean.valueOf(info.getProperty("tcpKeepAlive")).booleanValue();
/*  66 */     if (logger.logDebug())
/*  67 */       logger.debug("Trying to establish a protocol version 3 connection to " + host + ":" + port); 
/*  73 */     PGStream newStream = null;
/*     */     try {
/*  76 */       newStream = new PGStream(host, port);
/*  79 */       if (trySSL)
/*  80 */         newStream = enableSSL(newStream, requireSSL, info, logger); 
/*  83 */       String socketTimeoutProperty = info.getProperty("socketTimeout", "0");
/*     */       try {
/*  85 */         int socketTimeout = Integer.parseInt(socketTimeoutProperty);
/*  86 */         if (socketTimeout > 0)
/*  87 */           newStream.getSocket().setSoTimeout(socketTimeout * 1000); 
/*  89 */       } catch (NumberFormatException nfe) {
/*  90 */         logger.info("Couldn't parse socketTimeout value:" + socketTimeoutProperty);
/*     */       } 
/*  94 */       newStream.getSocket().setKeepAlive(requireTCPKeepAlive);
/*  97 */       String[][] params = { { "user", user }, { "database", database }, { "client_encoding", "UTF8" }, { "DateStyle", "ISO" }, { "extra_float_digits", "2" } };
/* 105 */       sendStartupPacket(newStream, params, logger);
/* 108 */       doAuthentication(newStream, host, user, info, logger);
/* 111 */       ProtocolConnectionImpl protoConnection = new ProtocolConnectionImpl(newStream, user, database, info, logger);
/* 112 */       readStartupMessages(newStream, protoConnection, logger);
/* 114 */       runInitialQueries(protoConnection, info, logger);
/* 117 */       return protoConnection;
/* 119 */     } catch (UnsupportedProtocolException upe) {
/* 122 */       if (logger.logDebug())
/* 123 */         logger.debug("Protocol not supported, abandoning connection."); 
/*     */       try {
/* 126 */         newStream.close();
/* 128 */       } catch (IOException e) {}
/* 131 */       return null;
/* 133 */     } catch (ConnectException cex) {
/* 138 */       throw new PSQLException(GT.tr("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections."), PSQLState.CONNECTION_UNABLE_TO_CONNECT, cex);
/* 140 */     } catch (IOException ioe) {
/* 142 */       if (newStream != null)
/*     */         try {
/* 146 */           newStream.close();
/* 148 */         } catch (IOException e) {} 
/* 152 */       throw new PSQLException(GT.tr("The connection attempt failed."), PSQLState.CONNECTION_UNABLE_TO_CONNECT, ioe);
/* 154 */     } catch (SQLException se) {
/* 156 */       if (newStream != null)
/*     */         try {
/* 160 */           newStream.close();
/* 162 */         } catch (IOException e) {} 
/* 166 */       throw se;
/*     */     } 
/*     */   }
/*     */   
/*     */   private PGStream enableSSL(PGStream pgStream, boolean requireSSL, Properties info, Logger logger) throws IOException, SQLException {
/* 171 */     if (logger.logDebug())
/* 172 */       logger.debug(" FE=> SSLRequest"); 
/* 175 */     pgStream.SendInteger4(8);
/* 176 */     pgStream.SendInteger2(1234);
/* 177 */     pgStream.SendInteger2(5679);
/* 178 */     pgStream.flush();
/* 181 */     int beresp = pgStream.ReceiveChar();
/* 182 */     switch (beresp) {
/*     */       case 69:
/* 185 */         if (logger.logDebug())
/* 186 */           logger.debug(" <=BE SSLError"); 
/* 189 */         if (requireSSL)
/* 190 */           throw new PSQLException(GT.tr("The server does not support SSL."), PSQLState.CONNECTION_REJECTED); 
/* 193 */         pgStream.close();
/* 194 */         return new PGStream(pgStream.getHost(), pgStream.getPort());
/*     */       case 78:
/* 197 */         if (logger.logDebug())
/* 198 */           logger.debug(" <=BE SSLRefused"); 
/* 201 */         if (requireSSL)
/* 202 */           throw new PSQLException(GT.tr("The server does not support SSL."), PSQLState.CONNECTION_REJECTED); 
/* 204 */         return pgStream;
/*     */       case 83:
/* 207 */         if (logger.logDebug())
/* 208 */           logger.debug(" <=BE SSLOk"); 
/* 211 */         MakeSSL.convert(pgStream, info, logger);
/* 212 */         return pgStream;
/*     */     } 
/* 215 */     throw new PSQLException(GT.tr("An error occured while setting up the SSL connection."), PSQLState.PROTOCOL_VIOLATION);
/*     */   }
/*     */   
/*     */   private void sendStartupPacket(PGStream pgStream, String[][] params, Logger logger) throws IOException {
/* 220 */     if (logger.logDebug()) {
/* 222 */       String details = "";
/* 223 */       for (int j = 0; j < params.length; j++) {
/* 225 */         if (j != 0)
/* 226 */           details = details + ", "; 
/* 227 */         details = details + params[j][0] + "=" + params[j][1];
/*     */       } 
/* 229 */       logger.debug(" FE=> StartupPacket(" + details + ")");
/*     */     } 
/* 235 */     int length = 8;
/* 236 */     byte[][] encodedParams = new byte[params.length * 2][];
/*     */     int i;
/* 237 */     for (i = 0; i < params.length; i++) {
/* 239 */       encodedParams[i * 2] = params[i][0].getBytes("UTF-8");
/* 240 */       encodedParams[i * 2 + 1] = params[i][1].getBytes("UTF-8");
/* 241 */       length += (encodedParams[i * 2]).length + 1 + (encodedParams[i * 2 + 1]).length + 1;
/*     */     } 
/* 244 */     length++;
/* 249 */     pgStream.SendInteger4(length);
/* 250 */     pgStream.SendInteger2(3);
/* 251 */     pgStream.SendInteger2(0);
/* 252 */     for (i = 0; i < encodedParams.length; i++) {
/* 254 */       pgStream.Send(encodedParams[i]);
/* 255 */       pgStream.SendChar(0);
/*     */     } 
/* 258 */     pgStream.SendChar(0);
/* 259 */     pgStream.flush();
/*     */   }
/*     */   
/*     */   private void doAuthentication(PGStream pgStream, String host, String user, Properties info, Logger logger) throws IOException, SQLException {
/* 267 */     String password = info.getProperty("password");
/*     */     while (true) {
/*     */       int l_elen;
/*     */       ServerErrorMessage errorMsg;
/*     */       int l_msgLen, areq;
/*     */       byte[] salt, md5Salt, encodedPassword, encodedResult, digest;
/* 271 */       int beresp = pgStream.ReceiveChar();
/* 273 */       switch (beresp) {
/*     */         case 69:
/* 282 */           l_elen = pgStream.ReceiveInteger4();
/* 283 */           if (l_elen > 30000)
/* 287 */             throw new UnsupportedProtocolException(); 
/* 290 */           errorMsg = new ServerErrorMessage(pgStream.ReceiveString(l_elen - 4), logger.getLogLevel());
/* 291 */           if (logger.logDebug())
/* 292 */             logger.debug(" <=BE ErrorMessage(" + errorMsg + ")"); 
/* 293 */           throw new PSQLException(errorMsg);
/*     */         case 82:
/* 298 */           l_msgLen = pgStream.ReceiveInteger4();
/* 301 */           areq = pgStream.ReceiveInteger4();
/* 304 */           switch (areq) {
/*     */             case 4:
/* 308 */               salt = pgStream.Receive(2);
/* 310 */               if (logger.logDebug())
/* 311 */                 logger.debug(" <=BE AuthenticationReqCrypt(salt='" + new String(salt, "US-ASCII") + "')"); 
/* 313 */               if (password == null)
/* 314 */                 throw new PSQLException(GT.tr("The server requested password-based authentication, but no password was provided."), PSQLState.CONNECTION_REJECTED); 
/* 316 */               encodedResult = UnixCrypt.crypt(salt, password.getBytes("UTF-8"));
/* 318 */               if (logger.logDebug())
/* 319 */                 logger.debug(" FE=> Password(crypt='" + new String(encodedResult, "US-ASCII") + "')"); 
/* 321 */               pgStream.SendChar(112);
/* 322 */               pgStream.SendInteger4(4 + encodedResult.length + 1);
/* 323 */               pgStream.Send(encodedResult);
/* 324 */               pgStream.SendChar(0);
/* 325 */               pgStream.flush();
/*     */               continue;
/*     */             case 5:
/* 332 */               md5Salt = pgStream.Receive(4);
/* 333 */               if (logger.logDebug())
/* 335 */                 logger.debug(" <=BE AuthenticationReqMD5(salt=" + Utils.toHexString(md5Salt) + ")"); 
/* 338 */               if (password == null)
/* 339 */                 throw new PSQLException(GT.tr("The server requested password-based authentication, but no password was provided."), PSQLState.CONNECTION_REJECTED); 
/* 341 */               digest = MD5Digest.encode(user.getBytes("UTF-8"), password.getBytes("UTF-8"), md5Salt);
/* 343 */               if (logger.logDebug())
/* 345 */                 logger.debug(" FE=> Password(md5digest=" + new String(digest, "US-ASCII") + ")"); 
/* 348 */               pgStream.SendChar(112);
/* 349 */               pgStream.SendInteger4(4 + digest.length + 1);
/* 350 */               pgStream.Send(digest);
/* 351 */               pgStream.SendChar(0);
/* 352 */               pgStream.flush();
/*     */               continue;
/*     */             case 3:
/* 359 */               if (logger.logDebug()) {
/* 361 */                 logger.debug(" <=BE AuthenticationReqPassword");
/* 362 */                 logger.debug(" FE=> Password(password=<not shown>)");
/*     */               } 
/* 365 */               if (password == null)
/* 366 */                 throw new PSQLException(GT.tr("The server requested password-based authentication, but no password was provided."), PSQLState.CONNECTION_REJECTED); 
/* 368 */               encodedPassword = password.getBytes("UTF-8");
/* 370 */               pgStream.SendChar(112);
/* 371 */               pgStream.SendInteger4(4 + encodedPassword.length + 1);
/* 372 */               pgStream.Send(encodedPassword);
/* 373 */               pgStream.SendChar(0);
/* 374 */               pgStream.flush();
/*     */               continue;
/*     */             case 7:
/* 380 */               MakeGSS.authenticate(pgStream, host, user, password, info.getProperty("jaasApplicationName"), info.getProperty("kerberosServerName"), logger);
/*     */               continue;
/*     */             case 9:
/* 388 */               if (logger.logDebug())
/* 389 */                 logger.debug(" <=BE AuthenticationReqSSPI"); 
/* 391 */               throw new PSQLException(GT.tr("SSPI authentication is not supported because it is not portable.  Try configuring the server to use GSSAPI instead."), PSQLState.CONNECTION_REJECTED);
/*     */             case 0:
/* 394 */               if (logger.logDebug())
/* 395 */                 logger.debug(" <=BE AuthenticationOk"); 
/*     */               return;
/*     */           } 
/* 400 */           if (logger.logDebug())
/* 401 */             logger.debug(" <=BE AuthenticationReq (unsupported type " + areq + ")"); 
/* 403 */           throw new PSQLException(GT.tr("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client''s IP address or subnet, and that it is using an authentication scheme supported by the driver.", new Integer(areq)), PSQLState.CONNECTION_REJECTED);
/*     */       } 
/*     */       break;
/*     */     } 
/* 409 */     throw new PSQLException(GT.tr("Protocol error.  Session setup failed."), PSQLState.PROTOCOL_VIOLATION);
/*     */   }
/*     */   
/*     */   private void readStartupMessages(PGStream pgStream, ProtocolConnectionImpl protoConnection, Logger logger) throws IOException, SQLException {
/*     */     int beresp;
/*     */     while (true) {
/*     */       char tStatus;
/*     */       int l_msgLen, pid, ckey, l_elen;
/*     */       ServerErrorMessage l_errorMsg;
/*     */       int l_nlen;
/*     */       ServerErrorMessage l_warnMsg;
/*     */       int l_len;
/*     */       String name, value;
/* 417 */       beresp = pgStream.ReceiveChar();
/* 418 */       switch (beresp) {
/*     */         case 90:
/* 422 */           if (pgStream.ReceiveInteger4() != 5)
/* 423 */             throw new IOException("unexpected length of ReadyForQuery packet"); 
/* 425 */           tStatus = (char)pgStream.ReceiveChar();
/* 426 */           if (logger.logDebug())
/* 427 */             logger.debug(" <=BE ReadyForQuery(" + tStatus + ")"); 
/* 430 */           switch (tStatus) {
/*     */             case 'I':
/* 433 */               protoConnection.setTransactionState(0);
/*     */               break;
/*     */             case 'T':
/* 436 */               protoConnection.setTransactionState(1);
/*     */               break;
/*     */             case 'E':
/* 439 */               protoConnection.setTransactionState(2);
/*     */               break;
/*     */           } 
/*     */           return;
/*     */         case 75:
/* 450 */           l_msgLen = pgStream.ReceiveInteger4();
/* 451 */           if (l_msgLen != 12)
/* 452 */             throw new PSQLException(GT.tr("Protocol error.  Session setup failed."), PSQLState.PROTOCOL_VIOLATION); 
/* 454 */           pid = pgStream.ReceiveInteger4();
/* 455 */           ckey = pgStream.ReceiveInteger4();
/* 457 */           if (logger.logDebug())
/* 458 */             logger.debug(" <=BE BackendKeyData(pid=" + pid + ",ckey=" + ckey + ")"); 
/* 460 */           protoConnection.setBackendKeyData(pid, ckey);
/*     */           continue;
/*     */         case 69:
/* 465 */           l_elen = pgStream.ReceiveInteger4();
/* 466 */           l_errorMsg = new ServerErrorMessage(pgStream.ReceiveString(l_elen - 4), logger.getLogLevel());
/* 468 */           if (logger.logDebug())
/* 469 */             logger.debug(" <=BE ErrorMessage(" + l_errorMsg + ")"); 
/* 471 */           throw new PSQLException(l_errorMsg);
/*     */         case 78:
/* 475 */           l_nlen = pgStream.ReceiveInteger4();
/* 476 */           l_warnMsg = new ServerErrorMessage(pgStream.ReceiveString(l_nlen - 4), logger.getLogLevel());
/* 478 */           if (logger.logDebug())
/* 479 */             logger.debug(" <=BE NoticeResponse(" + l_warnMsg + ")"); 
/* 481 */           protoConnection.addWarning((SQLWarning)new PSQLWarning(l_warnMsg));
/*     */           continue;
/*     */         case 83:
/* 486 */           l_len = pgStream.ReceiveInteger4();
/* 487 */           name = pgStream.ReceiveString();
/* 488 */           value = pgStream.ReceiveString();
/* 490 */           if (logger.logDebug())
/* 491 */             logger.debug(" <=BE ParameterStatus(" + name + " = " + value + ")"); 
/* 493 */           if (name.equals("server_version")) {
/* 494 */             protoConnection.setServerVersion(value);
/*     */             continue;
/*     */           } 
/* 495 */           if (name.equals("client_encoding")) {
/* 497 */             if (!value.equals("UTF8"))
/* 498 */               throw new PSQLException(GT.tr("Protocol error.  Session setup failed."), PSQLState.PROTOCOL_VIOLATION); 
/* 499 */             pgStream.setEncoding(Encoding.getDatabaseEncoding("UTF8"));
/*     */             continue;
/*     */           } 
/* 501 */           if (name.equals("standard_conforming_strings")) {
/* 503 */             if (value.equals("on")) {
/* 504 */               protoConnection.setStandardConformingStrings(true);
/*     */               continue;
/*     */             } 
/* 505 */             if (value.equals("off")) {
/* 506 */               protoConnection.setStandardConformingStrings(false);
/*     */               continue;
/*     */             } 
/* 508 */             throw new PSQLException(GT.tr("Protocol error.  Session setup failed."), PSQLState.PROTOCOL_VIOLATION);
/*     */           } 
/*     */           continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 514 */     if (logger.logDebug())
/* 515 */       logger.debug("invalid message type=" + (char)beresp); 
/* 516 */     throw new PSQLException(GT.tr("Protocol error.  Session setup failed."), PSQLState.PROTOCOL_VIOLATION);
/*     */   }
/*     */   
/*     */   private void runInitialQueries(ProtocolConnection protoConnection, Properties info, Logger logger) throws SQLException {
/* 523 */     String dbVersion = protoConnection.getServerVersion();
/* 525 */     if (dbVersion.compareTo("9.0") >= 0)
/* 526 */       SetupQueryRunner.run(protoConnection, "SET extra_float_digits = 3", false); 
/* 529 */     String appName = info.getProperty("ApplicationName");
/* 530 */     if (appName != null && dbVersion.compareTo("9.0") >= 0) {
/* 531 */       StringBuffer sql = new StringBuffer();
/* 532 */       sql.append("SET application_name = '");
/* 533 */       Utils.appendEscapedLiteral(sql, appName, protoConnection.getStandardConformingStrings());
/* 534 */       sql.append("'");
/* 535 */       SetupQueryRunner.run(protoConnection, sql.toString(), false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v3\ConnectionFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */