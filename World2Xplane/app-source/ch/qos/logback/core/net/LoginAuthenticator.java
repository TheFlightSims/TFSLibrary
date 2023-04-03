/*    */ package ch.qos.logback.core.net;
/*    */ 
/*    */ import javax.mail.Authenticator;
/*    */ import javax.mail.PasswordAuthentication;
/*    */ 
/*    */ public class LoginAuthenticator extends Authenticator {
/*    */   String username;
/*    */   
/*    */   String password;
/*    */   
/*    */   LoginAuthenticator(String username, String password) {
/* 28 */     this.username = username;
/* 29 */     this.password = password;
/*    */   }
/*    */   
/*    */   public PasswordAuthentication getPasswordAuthentication() {
/* 33 */     return new PasswordAuthentication(this.username, this.password);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\LoginAuthenticator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */