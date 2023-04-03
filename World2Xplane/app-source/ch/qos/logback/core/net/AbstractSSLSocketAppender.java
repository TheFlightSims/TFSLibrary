/*     */ package ch.qos.logback.core.net;
/*     */ 
/*     */ import ch.qos.logback.core.net.ssl.ConfigurableSSLSocketFactory;
/*     */ import ch.qos.logback.core.net.ssl.SSLComponent;
/*     */ import ch.qos.logback.core.net.ssl.SSLConfiguration;
/*     */ import ch.qos.logback.core.net.ssl.SSLParametersConfiguration;
/*     */ import ch.qos.logback.core.spi.ContextAware;
/*     */ import javax.net.SocketFactory;
/*     */ import javax.net.ssl.SSLContext;
/*     */ 
/*     */ public abstract class AbstractSSLSocketAppender<E> extends AbstractSocketAppender<E> implements SSLComponent {
/*     */   private SSLConfiguration ssl;
/*     */   
/*     */   private SocketFactory socketFactory;
/*     */   
/*     */   protected AbstractSSLSocketAppender() {}
/*     */   
/*     */   @Deprecated
/*     */   protected AbstractSSLSocketAppender(String remoteHost, int port) {
/*  55 */     super(remoteHost, port);
/*     */   }
/*     */   
/*     */   protected SocketFactory getSocketFactory() {
/*  65 */     return this.socketFactory;
/*     */   }
/*     */   
/*     */   public void start() {
/*     */     try {
/*  74 */       SSLContext sslContext = getSsl().createContext((ContextAware)this);
/*  75 */       SSLParametersConfiguration parameters = getSsl().getParameters();
/*  76 */       parameters.setContext(getContext());
/*  77 */       this.socketFactory = (SocketFactory)new ConfigurableSSLSocketFactory(parameters, sslContext.getSocketFactory());
/*  79 */       super.start();
/*  81 */     } catch (Exception ex) {
/*  82 */       addError(ex.getMessage(), ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SSLConfiguration getSsl() {
/*  92 */     if (this.ssl == null)
/*  93 */       this.ssl = new SSLConfiguration(); 
/*  95 */     return this.ssl;
/*     */   }
/*     */   
/*     */   public void setSsl(SSLConfiguration ssl) {
/* 103 */     this.ssl = ssl;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\AbstractSSLSocketAppender.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */