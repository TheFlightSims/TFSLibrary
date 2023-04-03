/*    */ package ch.qos.logback.classic.net.server;
/*    */ 
/*    */ import ch.qos.logback.core.net.ssl.ConfigurableSSLServerSocketFactory;
/*    */ import ch.qos.logback.core.net.ssl.SSLComponent;
/*    */ import ch.qos.logback.core.net.ssl.SSLConfiguration;
/*    */ import ch.qos.logback.core.net.ssl.SSLParametersConfiguration;
/*    */ import ch.qos.logback.core.spi.ContextAware;
/*    */ import javax.net.ServerSocketFactory;
/*    */ import javax.net.ssl.SSLContext;
/*    */ 
/*    */ public class SSLServerSocketReceiver extends ServerSocketReceiver implements SSLComponent {
/*    */   private SSLConfiguration ssl;
/*    */   
/*    */   private ServerSocketFactory socketFactory;
/*    */   
/*    */   protected ServerSocketFactory getServerSocketFactory() throws Exception {
/* 40 */     if (this.socketFactory == null) {
/* 41 */       SSLContext sslContext = getSsl().createContext((ContextAware)this);
/* 42 */       SSLParametersConfiguration parameters = getSsl().getParameters();
/* 43 */       parameters.setContext(getContext());
/* 44 */       this.socketFactory = (ServerSocketFactory)new ConfigurableSSLServerSocketFactory(parameters, sslContext.getServerSocketFactory());
/*    */     } 
/* 47 */     return this.socketFactory;
/*    */   }
/*    */   
/*    */   public SSLConfiguration getSsl() {
/* 56 */     if (this.ssl == null)
/* 57 */       this.ssl = new SSLConfiguration(); 
/* 59 */     return this.ssl;
/*    */   }
/*    */   
/*    */   public void setSsl(SSLConfiguration ssl) {
/* 67 */     this.ssl = ssl;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\net\server\SSLServerSocketReceiver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */