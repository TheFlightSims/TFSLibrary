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
/*     */ @ScalaSignature(bytes = "\006\001a3Q!\001\002\003\t!\021Qa\025(pI\026T!a\001\003\002\025\r|gnY;se\026tGO\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006,2!C\n '\r\001!B\004\t\003\0271i\021AA\005\003\033\t\021\021BQ1tS\016tu\016Z3\021\t-y\021CH\005\003!\t\021aa\023,O_\022,\007C\001\n\024\031\001!Q\001\006\001C\002Y\021\021aS\002\001#\t92\004\005\002\03135\ta!\003\002\033\r\t9aj\034;iS:<\007C\001\r\035\023\tibAA\002B]f\004\"AE\020\005\013\001\002!\031\001\f\003\003YC\001B\t\001\003\006\004%)aI\001\002WV\t\021\003\003\005&\001\t\005\t\025!\004\022\003\tY\007\005\003\005(\001\t\025\r\021\"\002)\003\0051X#\001\020\t\021)\002!\021!Q\001\016y\t!A\036\021\t\0211\002!Q1A\005\0065\n!\001[2\026\0039\002\"\001G\030\n\005A2!aA%oi\"A!\007\001B\001B\0035a&A\002iG\002BQ\001\016\001\005\002U\na\001P5oSRtD\003\002\0348qe\002Ba\003\001\022=!)!e\ra\001#!)qe\ra\001=!)Af\ra\001]!)1\b\001C\003y\005!1m\0349z+\0051\004\"\002 \001\t\013y\024AC2paf$v.\0342fIV\t\001\t\005\003\f\003Fq\022B\001\"\003\005\025!fj\0343f\021\025!\005\001\"\002=\0031\031w\016]=V]R|WNY3e\021\0251\005\001\"\002H\003\031Yg\017U1jeV\t\001\n\005\003\031\023Fq\022B\001&\007\005\031!V\017\0357fe!)A\n\001C\003\033\00611\017\036:j]\036$\"A\024,\021\005=#V\"\001)\013\005E\023\026\001\0027b]\036T\021aU\001\005U\0064\030-\003\002V!\n11\013\036:j]\036DQaV&A\0029\n1\001\\3w\001")
/*     */ public final class SNode<K, V> extends BasicNode implements KVNode<K, V> {
/*     */   private final K k;
/*     */   
/*     */   private final V v;
/*     */   
/*     */   private final int hc;
/*     */   
/*     */   public final K k() {
/* 410 */     return this.k;
/*     */   }
/*     */   
/*     */   public final V v() {
/* 410 */     return this.v;
/*     */   }
/*     */   
/*     */   public final int hc() {
/* 410 */     return this.hc;
/*     */   }
/*     */   
/*     */   public SNode(Object k, Object v, int hc) {}
/*     */   
/*     */   public final SNode<K, V> copy() {
/* 412 */     return new SNode(k(), v(), hc());
/*     */   }
/*     */   
/*     */   public final TNode<K, V> copyTombed() {
/* 413 */     return new TNode<K, V>(k(), v(), hc());
/*     */   }
/*     */   
/*     */   public final SNode<K, V> copyUntombed() {
/* 414 */     return new SNode(k(), v(), hc());
/*     */   }
/*     */   
/*     */   public final Tuple2<K, V> kvPair() {
/* 415 */     return new Tuple2(k(), v());
/*     */   }
/*     */   
/*     */   public final String string(int lev) {
/* 416 */     Predef$ predef$1 = Predef$.MODULE$, predef$2 = Predef$.MODULE$;
/* 416 */     return (new StringBuilder()).append((new StringOps("  ")).$times(lev)).append((new StringOps("SNode(%s, %s, %x)")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { k(), v(), BoxesRunTime.boxToInteger(hc()) }))).toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\SNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */