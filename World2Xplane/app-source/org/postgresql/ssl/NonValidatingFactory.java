/*    */ package org.postgresql.ssl;
/*    */ 
/*    */ import java.security.GeneralSecurityException;
/*    */ import java.security.cert.X509Certificate;
/*    */ import javax.net.ssl.SSLContext;
/*    */ import javax.net.ssl.TrustManager;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ 
/*    */ public class NonValidatingFactory extends WrappedFactory {
/*    */   public NonValidatingFactory(String arg) throws GeneralSecurityException {
/* 33 */     SSLContext ctx = SSLContext.getInstance("TLS");
/* 35 */     ctx.init(null, new TrustManager[] { new NonValidatingTM() }, null);
/* 39 */     this._factory = ctx.getSocketFactory();
/*    */   }
/*    */   
/*    */   static class NonValidatingTM implements X509TrustManager {
/*    */     public X509Certificate[] getAcceptedIssuers() {
/* 45 */       return new X509Certificate[0];
/*    */     }
/*    */     
/*    */     public void checkClientTrusted(X509Certificate[] certs, String authType) {}
/*    */     
/*    */     public void checkServerTrusted(X509Certificate[] certs, String authType) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ssl\NonValidatingFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */