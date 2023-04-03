/*     */ package scala.collection.generic;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001E2A!\001\002\001\023\t\tB)\0324bk2$8+[4oC2d\027N\\4\013\005\r!\021aB4f]\026\024\030n\031\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\n\005\001)q!\003\005\002\f\0315\ta!\003\002\016\r\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\025MKwM\\1mY&tw\r\005\002\020'%\021AC\001\002\016->d\027\r^5mK\006\023wN\035;\t\013Y\001A\021A\f\002\rqJg.\033;?)\005A\002CA\b\001\021\025Q\002\001\"\001\034\003%Ig\016Z3y\r2\fw-F\001\035!\tYQ$\003\002\037\r\t\031\021J\034;\t\013\001\002A\021A\021\002\031M,G/\0238eKb4E.Y4\025\005\t*\003CA\006$\023\t!cA\001\003V]&$\b\"\002\024 \001\004a\022!\0014\t\013!\002A\021A\025\002+M,G/\0238eKb4E.Y4JM\036\023X-\031;feR\021!E\013\005\006M\035\002\r\001\b\005\006Y\001!\t!L\001\025g\026$\030J\0343fq\032c\027mZ%g\031\026\0348/\032:\025\005\tr\003\"\002\024,\001\004a\002\"\002\031\001\t\003Y\022a\001;bO\002")
/*     */ public class DefaultSignalling implements VolatileAbort {
/*     */   private volatile boolean scala$collection$generic$VolatileAbort$$abortflag;
/*     */   
/*     */   public boolean scala$collection$generic$VolatileAbort$$abortflag() {
/* 102 */     return this.scala$collection$generic$VolatileAbort$$abortflag;
/*     */   }
/*     */   
/*     */   public void scala$collection$generic$VolatileAbort$$abortflag_$eq(boolean x$1) {
/* 102 */     this.scala$collection$generic$VolatileAbort$$abortflag = x$1;
/*     */   }
/*     */   
/*     */   public boolean isAborted() {
/* 102 */     return VolatileAbort$class.isAborted(this);
/*     */   }
/*     */   
/*     */   public void abort() {
/* 102 */     VolatileAbort$class.abort(this);
/*     */   }
/*     */   
/*     */   public DefaultSignalling() {
/* 102 */     VolatileAbort$class.$init$(this);
/*     */   }
/*     */   
/*     */   public int indexFlag() {
/* 103 */     return -1;
/*     */   }
/*     */   
/*     */   public void setIndexFlag(int f) {}
/*     */   
/*     */   public void setIndexFlagIfGreater(int f) {}
/*     */   
/*     */   public void setIndexFlagIfLesser(int f) {}
/*     */   
/*     */   public int tag() {
/* 108 */     return -1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\DefaultSignalling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */