/*    */ package ch.qos.logback.core.net.ssl;
/*    */ 
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.security.NoSuchProviderException;
/*    */ import javax.net.ssl.TrustManagerFactory;
/*    */ 
/*    */ public class TrustManagerFactoryFactoryBean {
/*    */   private String algorithm;
/*    */   
/*    */   private String provider;
/*    */   
/*    */   public TrustManagerFactory createTrustManagerFactory() throws NoSuchProviderException, NoSuchAlgorithmException {
/* 46 */     return (getProvider() != null) ? TrustManagerFactory.getInstance(getAlgorithm(), getProvider()) : TrustManagerFactory.getInstance(getAlgorithm());
/*    */   }
/*    */   
/*    */   public String getAlgorithm() {
/* 58 */     if (this.algorithm == null)
/* 59 */       return TrustManagerFactory.getDefaultAlgorithm(); 
/* 61 */     return this.algorithm;
/*    */   }
/*    */   
/*    */   public void setAlgorithm(String algorithm) {
/* 71 */     this.algorithm = algorithm;
/*    */   }
/*    */   
/*    */   public String getProvider() {
/* 79 */     return this.provider;
/*    */   }
/*    */   
/*    */   public void setProvider(String provider) {
/* 88 */     this.provider = provider;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\ssl\TrustManagerFactoryFactoryBean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */