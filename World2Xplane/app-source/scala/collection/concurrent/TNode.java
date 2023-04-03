/*     */ package scala.collection.concurrent;
/*     */ 
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0014Q!\001\002\003\t!\021Q\001\026(pI\026T!a\001\003\002\025\r|gnY;se\026tGO\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006,2!\003\t\035'\r\001!B\b\t\005\0271q1$D\001\003\023\ti!A\001\005NC&tgj\0343f!\ty\001\003\004\001\005\013E\001!\031A\n\003\003-\033\001!\005\002\0251A\021QCF\007\002\r%\021qC\002\002\b\035>$\b.\0338h!\t)\022$\003\002\033\r\t\031\021I\\=\021\005=aB!B\017\001\005\004\031\"!\001,\021\t-ybbG\005\003A\t\021aa\023,O_\022,\007\002\003\022\001\005\013\007IQA\022\002\003-,\022A\004\005\tK\001\021\t\021)A\007\035\005\0211\016\t\005\tO\001\021)\031!C\003Q\005\ta/F\001\034\021!Q\003A!A!\002\033Y\022A\001<!\021!a\003A!b\001\n\013i\023A\0015d+\005q\003CA\0130\023\t\001dAA\002J]RD\001B\r\001\003\002\003\006iAL\001\004Q\016\004\003\"\002\033\001\t\003)\024A\002\037j]&$h\b\006\0037oaJ\004\003B\006\001\035mAQAI\032A\0029AQaJ\032A\002mAQ\001L\032A\0029BQa\017\001\005\006q\nAaY8qsV\ta\007C\003?\001\021\025A(\001\006d_BLHk\\7cK\022DQ\001\021\001\005\006\005\013AbY8qsVsGo\\7cK\022,\022A\021\t\005\027\rs1$\003\002E\005\t)1KT8eK\")a\t\001C\003\017\00611N\036)bSJ,\022\001\023\t\005+%s1$\003\002K\r\t1A+\0369mKJBQ\001\024\001\005\0065\013!bY1dQ\026$7+\033>f)\tqc\nC\003P\027\002\007\001+\001\002diB\021Q#U\005\003%\032\021a!\0218z%\0264\007\"\002+\001\t\013)\026AB:ue&tw\r\006\002W=B\021q\013X\007\0021*\021\021LW\001\005Y\006twMC\001\\\003\021Q\027M^1\n\005uC&AB*ue&tw\rC\003`'\002\007a&A\002mKZ\004")
/*     */ public final class TNode<K, V> extends MainNode<K, V> implements KVNode<K, V> {
/*     */   private final K k;
/*     */   
/*     */   private final V v;
/*     */   
/*     */   private final int hc;
/*     */   
/*     */   public final K k() {
/* 420 */     return this.k;
/*     */   }
/*     */   
/*     */   public final V v() {
/* 420 */     return this.v;
/*     */   }
/*     */   
/*     */   public final int hc() {
/* 420 */     return this.hc;
/*     */   }
/*     */   
/*     */   public TNode(Object k, Object v, int hc) {}
/*     */   
/*     */   public final TNode<K, V> copy() {
/* 422 */     return new TNode(k(), v(), hc());
/*     */   }
/*     */   
/*     */   public final TNode<K, V> copyTombed() {
/* 423 */     return new TNode(k(), v(), hc());
/*     */   }
/*     */   
/*     */   public final SNode<K, V> copyUntombed() {
/* 424 */     return new SNode<K, V>(k(), v(), hc());
/*     */   }
/*     */   
/*     */   public final Tuple2<K, V> kvPair() {
/* 425 */     return new Tuple2(k(), v());
/*     */   }
/*     */   
/*     */   public final int cachedSize(Object ct) {
/* 426 */     return 1;
/*     */   }
/*     */   
/*     */   public final String string(int lev) {
/* 427 */     Predef$ predef$1 = Predef$.MODULE$, predef$2 = Predef$.MODULE$;
/* 427 */     return (new StringBuilder()).append((new StringOps("  ")).$times(lev)).append((new StringOps("TNode(%s, %s, %x, !)")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { k(), v(), BoxesRunTime.boxToInteger(hc()) }))).toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\TNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */