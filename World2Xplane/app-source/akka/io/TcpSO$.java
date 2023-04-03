/*     */ package akka.io;
/*     */ 
/*     */ public final class TcpSO$ implements Inet.SoJavaFactories {
/*     */   public static final TcpSO$ MODULE$;
/*     */   
/*     */   public Inet.SO$.eceiveBufferSize receiveBufferSize(int size) {
/* 576 */     return Inet.SoJavaFactories$class.receiveBufferSize(this, size);
/*     */   }
/*     */   
/*     */   public Inet.SO$.euseAddress reuseAddress(boolean on) {
/* 576 */     return Inet.SoJavaFactories$class.reuseAddress(this, on);
/*     */   }
/*     */   
/*     */   public Inet.SO$.endBufferSize sendBufferSize(int size) {
/* 576 */     return Inet.SoJavaFactories$class.sendBufferSize(this, size);
/*     */   }
/*     */   
/*     */   public Inet.SO$.rafficClass trafficClass(int tc) {
/* 576 */     return Inet.SoJavaFactories$class.trafficClass(this, tc);
/*     */   }
/*     */   
/*     */   private TcpSO$() {
/* 576 */     MODULE$ = this;
/* 576 */     Inet.SoJavaFactories$class.$init$(this);
/*     */   }
/*     */   
/*     */   public Tcp.SO$.eepAlive keepAlive(boolean on) {
/* 584 */     return new Tcp.SO$.eepAlive(on);
/*     */   }
/*     */   
/*     */   public Tcp.SO$.OBInline oobInline(boolean on) {
/* 593 */     return new Tcp.SO$.OBInline(on);
/*     */   }
/*     */   
/*     */   public Tcp.SO$.cpNoDelay tcpNoDelay(boolean on) {
/* 603 */     return new Tcp.SO$.cpNoDelay(on);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\TcpSO$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */