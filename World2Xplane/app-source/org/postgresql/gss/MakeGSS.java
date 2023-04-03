/*    */ package org.postgresql.gss;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.security.PrivilegedAction;
/*    */ import java.sql.SQLException;
/*    */ import javax.security.auth.Subject;
/*    */ import javax.security.auth.login.LoginContext;
/*    */ import org.postgresql.core.Logger;
/*    */ import org.postgresql.core.PGStream;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class MakeGSS {
/*    */   public static void authenticate(PGStream pgStream, String host, String user, String password, String jaasApplicationName, String kerberosServerName, Logger logger) throws IOException, SQLException {
/* 31 */     if (logger.logDebug())
/* 32 */       logger.debug(" <=BE AuthenticationReqGSS"); 
/* 34 */     Object result = null;
/* 36 */     if (jaasApplicationName == null)
/* 37 */       jaasApplicationName = "pgjdbc"; 
/* 38 */     if (kerberosServerName == null)
/* 39 */       kerberosServerName = "postgres"; 
/*    */     try {
/* 42 */       LoginContext lc = new LoginContext(jaasApplicationName, new GSSCallbackHandler(user, password));
/* 43 */       lc.login();
/* 45 */       Subject sub = lc.getSubject();
/* 46 */       PrivilegedAction<?> action = new GssAction(pgStream, host, user, password, kerberosServerName, logger);
/* 47 */       result = Subject.doAs(sub, action);
/* 48 */     } catch (Exception e) {
/* 49 */       throw new PSQLException(GT.tr("GSS Authentication failed"), PSQLState.CONNECTION_FAILURE, e);
/*    */     } 
/* 52 */     if (result instanceof IOException)
/* 53 */       throw (IOException)result; 
/* 54 */     if (result instanceof SQLException)
/* 55 */       throw (SQLException)result; 
/* 56 */     if (result != null)
/* 57 */       throw new PSQLException(GT.tr("GSS Authentication failed"), PSQLState.CONNECTION_FAILURE, (Exception)result); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\gss\MakeGSS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */