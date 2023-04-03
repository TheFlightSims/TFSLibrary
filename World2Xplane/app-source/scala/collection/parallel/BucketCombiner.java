/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.UnrolledBuffer;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005-aAB\001\003\003\003\021\001B\001\bCk\016\\W\r^\"p[\nLg.\032:\013\005\r!\021\001\0039be\006dG.\0327\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027-F\003\n)}q\023gE\002\001\0259\001\"a\003\007\016\003\031I!!\004\004\003\r\005s\027PU3g!\021y\001C\005\020\016\003\tI!!\005\002\003\021\r{WNY5oKJ\004\"a\005\013\r\001\0211Q\003\001EC\002]\021A!\0227f[\016\001\021C\001\r\034!\tY\021$\003\002\033\r\t9aj\034;iS:<\007CA\006\035\023\tibAA\002B]f\004\"aE\020\005\r\001\002AQ1\001\030\005\t!v\016\003\005#\001\t\025\r\021\"\003$\0031\021WoY6fi:,XNY3s+\005!\003CA\006&\023\t1cAA\002J]RD\001\002\013\001\003\002\003\006I\001J\001\016EV\0347.\032;ok6\024WM\035\021\t\013)\002A\021A\026\002\rqJg.\033;?)\taC\007\005\004\020\001IqR\006\r\t\003'9\"Qa\f\001C\002]\021AAQ;dWB\0211#\r\003\007e\001!)\031A\032\003\031\r{WNY5oKJ$\026\020]3\022\005aa\003\"\002\022*\001\004!\003b\002\034\001\001\004%\tbN\001\bEV\0347.\032;t+\005A$FA\035C!\rY!\bP\005\003w\031\021Q!\021:sCf\0042!\020!.\033\005q$BA \005\003\035iW\017^1cY\026L!!\021 \003\035Us'o\0347mK\022\024UO\0324fe.\n1\t\005\002E\0236\tQI\003\002G\017\006IQO\\2iK\016\\W\r\032\006\003\021\032\t!\"\0318o_R\fG/[8o\023\tQUIA\tv]\016DWmY6fIZ\013'/[1oG\026Dq\001\024\001A\002\023EQ*A\006ck\016\\W\r^:`I\025\fHC\001(R!\tYq*\003\002Q\r\t!QK\\5u\021\035\0216*!AA\002a\n1\001\037\0232\021\031!\006\001)Q\005q\005A!-^2lKR\034\b\005C\004W\001\001\007I\021C\022\002\005MT\bb\002-\001\001\004%\t\"W\001\007gj|F%Z9\025\0059S\006b\002*X\003\003\005\r\001\n\005\0079\002\001\013\025\002\023\002\007MT\b\005C\003_\001\021\0051%\001\003tSj,\007\"\0021\001\t\003\t\027!B2mK\006\024H#\001(\t\013\r\004A\021\0013\002\033\t,gm\034:f\007>l'-\0338f+\r)'N\034\013\003\035\032DQa\0322A\002!\fQa\034;iKJ\004Ba\004\tj[B\0211C\033\003\006W\n\024\r\001\034\002\002\035F\021\001D\005\t\003'9$Qa\0342C\002A\024QAT3x)>\f\"AH\016\t\013I\004A\021A:\002\031\0054G/\032:D_6\024\027N\\3\026\007QD(\020\006\002Ok\")q-\035a\001mB!q\002E<z!\t\031\002\020B\003lc\n\007A\016\005\002\024u\022)q.\035b\001a\")A\020\001C\001{\00691m\\7cS:,W#\002@\002\004\005\035AcA@\002\nA1q\002EA\001\003\013\0012aEA\002\t\025Y7P1\001m!\r\031\022q\001\003\006_n\024\r\001\035\005\006On\004\ra ")
/*     */ public abstract class BucketCombiner<Elem, To, Buck, CombinerType extends BucketCombiner<Elem, To, Buck, CombinerType>> implements Combiner<Elem, To> {
/*     */   private final int bucketnumber;
/*     */   
/*     */   private UnrolledBuffer<Buck>[] buckets;
/*     */   
/*     */   private int sz;
/*     */   
/*     */   private volatile transient TaskSupport _combinerTaskSupport;
/*     */   
/*     */   public TaskSupport _combinerTaskSupport() {
/* 205 */     return this._combinerTaskSupport;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void _combinerTaskSupport_$eq(TaskSupport x$1) {
/* 205 */     this._combinerTaskSupport = x$1;
/*     */   }
/*     */   
/*     */   public TaskSupport combinerTaskSupport() {
/* 205 */     return Combiner$class.combinerTaskSupport(this);
/*     */   }
/*     */   
/*     */   public void combinerTaskSupport_$eq(TaskSupport cts) {
/* 205 */     Combiner$class.combinerTaskSupport_$eq(this, cts);
/*     */   }
/*     */   
/*     */   public boolean canBeShared() {
/* 205 */     return Combiner$class.canBeShared(this);
/*     */   }
/*     */   
/*     */   public To resultWithTaskSupport() {
/* 205 */     return (To)Combiner$class.resultWithTaskSupport(this);
/*     */   }
/*     */   
/*     */   public void sizeHint(int size) {
/* 205 */     Builder.class.sizeHint(this, size);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/* 205 */     Builder.class.sizeHint(this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/* 205 */     Builder.class.sizeHint(this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 205 */     Builder.class.sizeHintBounded(this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<Elem, NewTo> mapResult(Function1 f) {
/* 205 */     return Builder.class.mapResult(this, f);
/*     */   }
/*     */   
/*     */   public Growable<Elem> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 205 */     return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Growable<Elem> $plus$plus$eq(TraversableOnce xs) {
/* 205 */     return Growable.class.$plus$plus$eq((Growable)this, xs);
/*     */   }
/*     */   
/*     */   private int bucketnumber() {
/* 206 */     return this.bucketnumber;
/*     */   }
/*     */   
/*     */   public BucketCombiner(int bucketnumber) {
/* 206 */     Growable.class.$init$((Growable)this);
/* 206 */     Builder.class.$init$(this);
/* 206 */     Combiner$class.$init$(this);
/* 209 */     this.buckets = (UnrolledBuffer<Buck>[])new UnrolledBuffer[bucketnumber];
/* 210 */     this.sz = 0;
/*     */   }
/*     */   
/*     */   public UnrolledBuffer<Buck>[] buckets() {
/*     */     return this.buckets;
/*     */   }
/*     */   
/*     */   public void buckets_$eq(UnrolledBuffer[] x$1) {
/*     */     this.buckets = (UnrolledBuffer<Buck>[])x$1;
/*     */   }
/*     */   
/*     */   public int sz() {
/* 210 */     return this.sz;
/*     */   }
/*     */   
/*     */   public void sz_$eq(int x$1) {
/* 210 */     this.sz = x$1;
/*     */   }
/*     */   
/*     */   public int size() {
/* 212 */     return sz();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 215 */     buckets_$eq((UnrolledBuffer<Buck>[])new UnrolledBuffer[bucketnumber()]);
/* 216 */     sz_$eq(0);
/*     */   }
/*     */   
/*     */   public <N extends Elem, NewTo> void beforeCombine(Combiner other) {}
/*     */   
/*     */   public <N extends Elem, NewTo> void afterCombine(Combiner other) {}
/*     */   
/*     */   public <N extends Elem, NewTo> Combiner<N, NewTo> combine(Combiner<Object, ?> other) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpne -> 9
/*     */     //   5: aload_0
/*     */     //   6: goto -> 120
/*     */     //   9: aload_1
/*     */     //   10: instanceof scala/collection/parallel/BucketCombiner
/*     */     //   13: ifeq -> 121
/*     */     //   16: aload_0
/*     */     //   17: aload_1
/*     */     //   18: invokevirtual beforeCombine : (Lscala/collection/parallel/Combiner;)V
/*     */     //   21: aload_1
/*     */     //   22: checkcast scala/collection/parallel/BucketCombiner
/*     */     //   25: astore_3
/*     */     //   26: iconst_0
/*     */     //   27: istore_2
/*     */     //   28: iload_2
/*     */     //   29: aload_0
/*     */     //   30: invokespecial bucketnumber : ()I
/*     */     //   33: if_icmpge -> 101
/*     */     //   36: aload_0
/*     */     //   37: invokevirtual buckets : ()[Lscala/collection/mutable/UnrolledBuffer;
/*     */     //   40: iload_2
/*     */     //   41: aaload
/*     */     //   42: ifnonnull -> 63
/*     */     //   45: aload_0
/*     */     //   46: invokevirtual buckets : ()[Lscala/collection/mutable/UnrolledBuffer;
/*     */     //   49: iload_2
/*     */     //   50: aload_3
/*     */     //   51: invokevirtual buckets : ()[Lscala/collection/mutable/UnrolledBuffer;
/*     */     //   54: iload_2
/*     */     //   55: aaload
/*     */     //   56: aastore
/*     */     //   57: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   60: goto -> 93
/*     */     //   63: aload_3
/*     */     //   64: invokevirtual buckets : ()[Lscala/collection/mutable/UnrolledBuffer;
/*     */     //   67: iload_2
/*     */     //   68: aaload
/*     */     //   69: ifnull -> 90
/*     */     //   72: aload_0
/*     */     //   73: invokevirtual buckets : ()[Lscala/collection/mutable/UnrolledBuffer;
/*     */     //   76: iload_2
/*     */     //   77: aaload
/*     */     //   78: aload_3
/*     */     //   79: invokevirtual buckets : ()[Lscala/collection/mutable/UnrolledBuffer;
/*     */     //   82: iload_2
/*     */     //   83: aaload
/*     */     //   84: invokevirtual concat : (Lscala/collection/mutable/UnrolledBuffer;)Lscala/collection/mutable/UnrolledBuffer;
/*     */     //   87: goto -> 93
/*     */     //   90: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   93: pop
/*     */     //   94: iload_2
/*     */     //   95: iconst_1
/*     */     //   96: iadd
/*     */     //   97: istore_2
/*     */     //   98: goto -> 28
/*     */     //   101: aload_0
/*     */     //   102: aload_0
/*     */     //   103: invokevirtual sz : ()I
/*     */     //   106: aload_3
/*     */     //   107: invokevirtual size : ()I
/*     */     //   110: iadd
/*     */     //   111: invokevirtual sz_$eq : (I)V
/*     */     //   114: aload_0
/*     */     //   115: aload_1
/*     */     //   116: invokevirtual afterCombine : (Lscala/collection/parallel/Combiner;)V
/*     */     //   119: aload_0
/*     */     //   120: areturn
/*     */     //   121: getstatic scala/sys/package$.MODULE$ : Lscala/sys/package$;
/*     */     //   124: ldc 'Unexpected combiner type.'
/*     */     //   126: invokevirtual error : (Ljava/lang/String;)Lscala/runtime/Nothing$;
/*     */     //   129: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #224	-> 0
/*     */     //   #226	-> 9
/*     */     //   #225	-> 9
/*     */     //   #227	-> 16
/*     */     //   #228	-> 21
/*     */     //   #230	-> 26
/*     */     //   #231	-> 28
/*     */     //   #232	-> 36
/*     */     //   #233	-> 45
/*     */     //   #234	-> 63
/*     */     //   #235	-> 72
/*     */     //   #234	-> 90
/*     */     //   #232	-> 93
/*     */     //   #237	-> 94
/*     */     //   #239	-> 101
/*     */     //   #240	-> 114
/*     */     //   #225	-> 119
/*     */     //   #241	-> 119
/*     */     //   #224	-> 120
/*     */     //   #243	-> 121
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	130	0	this	Lscala/collection/parallel/BucketCombiner;
/*     */     //   0	130	1	other	Lscala/collection/parallel/Combiner;
/*     */     //   26	93	3	that	Lscala/collection/parallel/BucketCombiner;
/*     */     //   28	91	2	i	I
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\BucketCombiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */