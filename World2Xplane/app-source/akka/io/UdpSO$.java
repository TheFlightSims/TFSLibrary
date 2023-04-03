/*     */ package akka.io;
/*     */ 
/*     */ public final class UdpSO$ implements Inet.SoJavaFactories {
/*     */   public static final UdpSO$ MODULE$;
/*     */   
/*     */   public Inet.SO$.eceiveBufferSize receiveBufferSize(int size) {
/* 326 */     return Inet.SoJavaFactories$class.receiveBufferSize(this, size);
/*     */   }
/*     */   
/*     */   public Inet.SO$.euseAddress reuseAddress(boolean on) {
/* 326 */     return Inet.SoJavaFactories$class.reuseAddress(this, on);
/*     */   }
/*     */   
/*     */   public Inet.SO$.endBufferSize sendBufferSize(int size) {
/* 326 */     return Inet.SoJavaFactories$class.sendBufferSize(this, size);
/*     */   }
/*     */   
/*     */   public Inet.SO$.rafficClass trafficClass(int tc) {
/* 326 */     return Inet.SoJavaFactories$class.trafficClass(this, tc);
/*     */   }
/*     */   
/*     */   private UdpSO$() {
/* 326 */     MODULE$ = this;
/* 326 */     Inet.SoJavaFactories$class.$init$(this);
/*     */   }
/*     */   
/*     */   public Udp.SO$.roadcast broadcast(boolean on) {
/* 334 */     return new Udp.SO$.roadcast(on);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpSO$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */