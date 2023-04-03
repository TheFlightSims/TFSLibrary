/*    */ package org.postgresql.gss;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.security.auth.callback.Callback;
/*    */ import javax.security.auth.callback.CallbackHandler;
/*    */ import javax.security.auth.callback.NameCallback;
/*    */ import javax.security.auth.callback.PasswordCallback;
/*    */ import javax.security.auth.callback.TextOutputCallback;
/*    */ import javax.security.auth.callback.UnsupportedCallbackException;
/*    */ 
/*    */ public class GSSCallbackHandler implements CallbackHandler {
/*    */   private final String user;
/*    */   
/*    */   private final String password;
/*    */   
/*    */   public GSSCallbackHandler(String user, String password) {
/* 23 */     this.user = user;
/* 24 */     this.password = password;
/*    */   }
/*    */   
/*    */   public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
/* 29 */     for (int i = 0; i < callbacks.length; i++) {
/* 30 */       if (callbacks[i] instanceof TextOutputCallback) {
/* 31 */         TextOutputCallback toc = (TextOutputCallback)callbacks[i];
/* 32 */         switch (toc.getMessageType()) {
/*    */           case 0:
/* 34 */             System.out.println("INFO: " + toc.getMessage());
/*    */             break;
/*    */           case 2:
/* 37 */             System.out.println("ERROR: " + toc.getMessage());
/*    */             break;
/*    */           case 1:
/* 40 */             System.out.println("WARNING: " + toc.getMessage());
/*    */             break;
/*    */           default:
/* 43 */             throw new IOException("Unsupported message type: " + toc.getMessageType());
/*    */         } 
/* 45 */       } else if (callbacks[i] instanceof NameCallback) {
/* 46 */         NameCallback nc = (NameCallback)callbacks[i];
/* 47 */         nc.setName(this.user);
/* 48 */       } else if (callbacks[i] instanceof PasswordCallback) {
/* 49 */         PasswordCallback pc = (PasswordCallback)callbacks[i];
/* 50 */         if (this.password == null)
/* 51 */           throw new IOException("No cached kerberos ticket found and no password supplied."); 
/* 53 */         pc.setPassword(this.password.toCharArray());
/*    */       } else {
/* 55 */         throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\gss\GSSCallbackHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */