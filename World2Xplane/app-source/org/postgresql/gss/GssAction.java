/*     */ package org.postgresql.gss;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.security.PrivilegedAction;
/*     */ import org.ietf.jgss.GSSContext;
/*     */ import org.ietf.jgss.GSSCredential;
/*     */ import org.ietf.jgss.GSSException;
/*     */ import org.ietf.jgss.GSSManager;
/*     */ import org.ietf.jgss.GSSName;
/*     */ import org.ietf.jgss.Oid;
/*     */ import org.postgresql.core.Logger;
/*     */ import org.postgresql.core.PGStream;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ import org.postgresql.util.ServerErrorMessage;
/*     */ 
/*     */ class GssAction implements PrivilegedAction {
/*     */   private final PGStream pgStream;
/*     */   
/*     */   private final String host;
/*     */   
/*     */   private final String user;
/*     */   
/*     */   private final String password;
/*     */   
/*     */   private final String kerberosServerName;
/*     */   
/*     */   private final Logger logger;
/*     */   
/*     */   public GssAction(PGStream pgStream, String host, String user, String password, String kerberosServerName, Logger logger) {
/*  74 */     this.pgStream = pgStream;
/*  75 */     this.host = host;
/*  76 */     this.user = user;
/*  77 */     this.password = password;
/*  78 */     this.kerberosServerName = kerberosServerName;
/*  79 */     this.logger = logger;
/*     */   }
/*     */   
/*     */   public Object run() {
/*     */     try {
/*  86 */       Oid[] desiredMechs = new Oid[1];
/*  87 */       desiredMechs[0] = new Oid("1.2.840.113554.1.2.2");
/*  90 */       GSSManager manager = GSSManager.getInstance();
/*  92 */       GSSName clientName = manager.createName(this.user, GSSName.NT_USER_NAME);
/*  93 */       GSSCredential clientCreds = manager.createCredential(clientName, 28800, desiredMechs, 1);
/*  95 */       GSSName serverName = manager.createName(this.kerberosServerName + "@" + this.host, GSSName.NT_HOSTBASED_SERVICE);
/*  97 */       GSSContext secContext = manager.createContext(serverName, desiredMechs[0], clientCreds, 0);
/*  98 */       secContext.requestMutualAuth(true);
/* 100 */       byte[] inToken = new byte[0];
/* 101 */       byte[] outToken = null;
/* 103 */       boolean established = false;
/* 104 */       while (!established) {
/* 105 */         outToken = secContext.initSecContext(inToken, 0, inToken.length);
/* 108 */         if (outToken != null) {
/* 109 */           if (this.logger.logDebug())
/* 110 */             this.logger.debug(" FE=> Password(GSS Authentication Token)"); 
/* 112 */           this.pgStream.SendChar(112);
/* 113 */           this.pgStream.SendInteger4(4 + outToken.length);
/* 114 */           this.pgStream.Send(outToken);
/* 115 */           this.pgStream.flush();
/*     */         } 
/* 118 */         if (!secContext.isEstablished()) {
/* 119 */           int response = this.pgStream.ReceiveChar();
/* 121 */           if (response == 69) {
/* 122 */             int l_elen = this.pgStream.ReceiveInteger4();
/* 123 */             ServerErrorMessage l_errorMsg = new ServerErrorMessage(this.pgStream.ReceiveString(l_elen - 4), this.logger.getLogLevel());
/* 125 */             if (this.logger.logDebug())
/* 126 */               this.logger.debug(" <=BE ErrorMessage(" + l_errorMsg + ")"); 
/* 128 */             return new PSQLException(l_errorMsg);
/*     */           } 
/* 130 */           if (response == 82) {
/* 132 */             if (this.logger.logDebug())
/* 133 */               this.logger.debug(" <=BE AuthenticationGSSContinue"); 
/* 135 */             int len = this.pgStream.ReceiveInteger4();
/* 136 */             int type = this.pgStream.ReceiveInteger4();
/* 138 */             inToken = this.pgStream.Receive(len - 8);
/*     */             continue;
/*     */           } 
/* 141 */           return new PSQLException(GT.tr("Protocol error.  Session setup failed."), PSQLState.CONNECTION_UNABLE_TO_CONNECT);
/*     */         } 
/* 144 */         established = true;
/*     */       } 
/* 148 */     } catch (IOException e) {
/* 149 */       return e;
/* 150 */     } catch (GSSException gsse) {
/* 151 */       return new PSQLException(GT.tr("GSS Authentication failed"), PSQLState.CONNECTION_FAILURE, gsse);
/*     */     } 
/* 154 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\gss\GssAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */