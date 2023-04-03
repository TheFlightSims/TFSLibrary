/*     */ package scala.collection.concurrent;
/*     */ 
/*     */ import scala.Predef$;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001}2Q!\001\002\003\005!\021!BR1jY\026$gj\0343f\025\t\031A!\001\006d_:\034WO\035:f]RT!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b+\rI\001\003H\n\003\001)\001Ba\003\007\01775\t!!\003\002\016\005\tAQ*Y5o\035>$W\r\005\002\020!1\001A!B\t\001\005\004\031\"!A&\004\001E\021A\003\007\t\003+Yi\021AB\005\003/\031\021qAT8uQ&tw\r\005\002\0263%\021!D\002\002\004\003:L\bCA\b\035\t\025i\002A1\001\024\005\0051\006\002C\020\001\005\003\005\013\021\002\006\002\003ADQ!\t\001\005\002\t\na\001P5oSRtDCA\022%!\021Y\001AD\016\t\013}\001\003\031\001\006\t\013\031\002A\021A\024\002\rM$(/\0338h)\t!\002\006C\003*K\001\007!&A\002mKZ\004\"!F\026\n\00512!aA%oi\")a\006\001C\001_\005Q1-Y2iK\022\034\026N_3\025\005)\002\004\"B\031.\001\004\021\024AA2u!\t)2'\003\0025\r\t1\021I\\=SK\032DQA\016\001\005B]\n\001\002^8TiJLgn\032\013\002qA\021\021\b\020\b\003+iJ!a\017\004\002\rA\023X\rZ3g\023\tidH\001\004TiJLgn\032\006\003w\031\001")
/*     */ public final class FailedNode<K, V> extends MainNode<K, V> {
/*     */   private final MainNode<K, V> p;
/*     */   
/*     */   public FailedNode(MainNode<K, V> p) {
/* 395 */     WRITE_PREV(p);
/*     */   }
/*     */   
/*     */   public Nothing$ string(int lev) {
/* 397 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int cachedSize(Object ct) {
/* 399 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 401 */     Predef$ predef$ = Predef$.MODULE$;
/* 401 */     return (new StringOps("FailedNode(%s)")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { this.p }));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\FailedNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */