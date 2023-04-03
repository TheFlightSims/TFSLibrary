/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005eeaB\001\003!\003\r\ta\002\002\013\005&$8+\032;MS.,'BA\002\005\003)\031w\016\0347fGRLwN\034\006\002\013\005)1oY1mC\016\001QC\001\005\027'\r\001\021\"\004\t\003\025-i\021\001B\005\003\031\021\021a!\0218z%\0264\007\003\002\b\020#Qi\021AA\005\003!\t\021QbU8si\026$7+\032;MS.,\007C\001\006\023\023\t\031BAA\002J]R\004\"!\006\f\r\001\0211q\003\001CC\002a\021A\001\0265jgF\021\021\004\b\t\003\025iI!a\007\003\003\0179{G\017[5oOJ\031Qd\b\021\007\ty\001\001\001\b\002\ryI,g-\0338f[\026tGO\020\t\004\035\001!\002c\001\b\"#%\021!E\001\002\n'>\024H/\0323TKRDQ\001\n\001\005\002\025\na\001J5oSR$C#\001\024\021\005)9\023B\001\025\005\005\021)f.\033;\t\013)\002a\021A\026\002\013\025l\007\017^=\026\003QAQ!\f\001\007\0229\naA\\<pe\022\034X#A\t\t\013A\002a\021C\031\002\t]|'\017\032\013\003eU\002\"AC\032\n\005Q\"!\001\002'p]\036DQAN\030A\002E\t1!\0333y\021\025A\004A\"\005:\003E1'o\\7CSRl\025m]6O_\016{\007/\037\013\003)iBQaO\034A\002q\nQ!\0327f[N\0042AC\0373\023\tqDAA\003BeJ\f\027\020C\003A\001\021\005\021)A\005u_\nKG/T1tWV\tA\bC\003D\001\021\005c&\001\003tSj,\007\"B#\001\t\0071\025\001C8sI\026\024\030N\\4\026\003\035\0032\001\023)\022\035\tIeJ\004\002K\0336\t1J\003\002M\r\0051AH]8pizJ\021!B\005\003\037\022\tq\001]1dW\006<W-\003\002R%\nAqJ\0353fe&twM\003\002P\t!)A\013\001C\001+\006I!/\0318hK&k\007\017\034\013\004)Y[\006\"B,T\001\004A\026\001\0024s_6\0042AC-\022\023\tQFA\001\004PaRLwN\034\005\0069N\003\r\001W\001\006k:$\030\016\034\005\006=\002!\taX\001\tSR,'/\031;peV\t\001\rE\002\017CFI!A\031\002\003\021%#XM]1u_JDQ\001\032\001\005B\025\fqAZ8sK\006\034\007.\006\002g[R\021ae\032\005\006Q\016\004\r![\001\002MB!!B[\tm\023\tYGAA\005Gk:\034G/[8ocA\021Q#\034\003\006]\016\024\ra\034\002\002\005F\021\021\004\035\t\003\025EL!A\035\003\003\007\005s\027\020C\003u\001\021\005Q/\001\003%E\006\024HC\001\013w\021\02598\0171\001y\003\025yG\017[3s!\tq\0210\003\002{\005\t1!)\033;TKRDQ\001 \001\005\002u\fA\001J1naR\021AC \005\006on\004\r\001\037\005\b\003\003\001A\021AA\002\003)!\023-\0349%i&dG-\032\013\004)\005\025\001\"B<\000\001\004A\bbBA\005\001\021\005\0211B\001\004IU\004Hc\001\013\002\016!1q/a\002A\002aDq!!\005\001\t\003\t\031\"\001\005d_:$\030-\0338t)\021\t)\"a\007\021\007)\t9\"C\002\002\032\021\021qAQ8pY\026\fg\016C\004\002\036\005=\001\031A\t\002\t\025dW-\034\005\b\003C\001A\021AA\022\003!\031XOY:fi>3G\003BA\013\003KAaa^A\020\001\004A\bbBA\025\001\021\005\0231F\001\nC\022$7\013\036:j]\036$\"\"!\f\002:\005u\022qJA*!\021\ty#!\016\016\005\005E\"bAA\032\005\0059Q.\036;bE2,\027\002BA\034\003c\021Qb\025;sS:<')^5mI\026\024\b\002CA\036\003O\001\r!!\f\002\005M\024\007\002CA \003O\001\r!!\021\002\013M$\030M\035;\021\t\005\r\023\021\n\b\004\025\005\025\023bAA$\t\0051\001K]3eK\032LA!a\023\002N\t11\013\036:j]\036T1!a\022\005\021!\t\t&a\nA\002\005\005\023aA:fa\"A\021QKA\024\001\004\t\t%A\002f]\022Dq!!\027\001\t\003\nY&\001\007tiJLgn\032)sK\032L\0070\006\002\002^A!\021qLA5\033\t\t\tG\003\003\002d\005\025\024\001\0027b]\036T!!a\032\002\t)\fg/Y\005\005\003\027\n\tgB\004\002n\tA\t!a\034\002\025\tKGoU3u\031&\\W\rE\002\017\003c2a!\001\002\t\002\005M4cAA9\023!A\021qOA9\t\003\tI(\001\004=S:LGO\020\013\003\003_B!\"! \002r\t\007I\021\001\002/\003\025aunZ,M\021!\t\t)!\035!\002\023\t\022A\002'pO^c\005\005C\005\002\006\006E$\031!C\005]\005Qqk\034:e\031\026tw\r\0365\t\021\005%\025\021\017Q\001\nE\t1bV8sI2+gn\032;iA!I\021QRA9\t\003\021\021qR\001\fkB$\027\r^3BeJ\f\027\020F\004=\003#\013\031*!&\t\rm\nY\t1\001=\021\0311\0241\022a\001#!9\021qSAF\001\004\021\024!A<")
/*     */ public interface BitSetLike<This extends BitSetLike<This> & SortedSet<Object>> extends SortedSetLike<Object, This> {
/*     */   This empty();
/*     */   
/*     */   int nwords();
/*     */   
/*     */   long word(int paramInt);
/*     */   
/*     */   This fromBitMaskNoCopy(long[] paramArrayOflong);
/*     */   
/*     */   long[] toBitMask();
/*     */   
/*     */   int size();
/*     */   
/*     */   Ordering<Object> ordering();
/*     */   
/*     */   This rangeImpl(Option<Object> paramOption1, Option<Object> paramOption2);
/*     */   
/*     */   Iterator<Object> iterator();
/*     */   
/*     */   <B> void foreach(Function1<Object, B> paramFunction1);
/*     */   
/*     */   This $bar(BitSet paramBitSet);
/*     */   
/*     */   This $amp(BitSet paramBitSet);
/*     */   
/*     */   This $amp$tilde(BitSet paramBitSet);
/*     */   
/*     */   This $up(BitSet paramBitSet);
/*     */   
/*     */   boolean contains(int paramInt);
/*     */   
/*     */   boolean subsetOf(BitSet paramBitSet);
/*     */   
/*     */   StringBuilder addString(StringBuilder paramStringBuilder, String paramString1, String paramString2, String paramString3);
/*     */   
/*     */   String stringPrefix();
/*     */   
/*     */   public class BitSetLike$$anon$1 extends AbstractIterator<Object> {
/*     */     private int current;
/*     */     
/*     */     private final int end;
/*     */     
/*     */     public BitSetLike$$anon$1(BitSetLike $outer) {
/* 103 */       this.current = 0;
/* 104 */       this.end = $outer.nwords() * BitSetLike$.MODULE$.scala$collection$BitSetLike$$WordLength();
/*     */     }
/*     */     
/*     */     private int current() {
/*     */       return this.current;
/*     */     }
/*     */     
/*     */     private void current_$eq(int x$1) {
/*     */       this.current = x$1;
/*     */     }
/*     */     
/*     */     private int end() {
/* 104 */       return this.end;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 106 */       for (; current() < end() && !this.$outer.contains(current()); current_$eq(current() + 1));
/* 107 */       return (current() < end());
/*     */     }
/*     */     
/*     */     public int next() {
/* 110 */       int r = current();
/* 110 */       current_$eq(current() + 1);
/* 110 */       return hasNext() ? r : 
/* 111 */         BoxesRunTime.unboxToInt(Iterator$.MODULE$.empty().next());
/*     */     }
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$foreach$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Function1 f$1;
/*     */     
/*     */     public final void apply(int i) {
/* 115 */       apply$mcVI$sp(i);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$foreach$1(BitSetLike $outer, Function1 f$1) {}
/*     */     
/*     */     public void apply$mcVI$sp(int i) {
/* 116 */       long w = this.$outer.word(i);
/* 117 */       int j = i * BitSetLike$.MODULE$.scala$collection$BitSetLike$$WordLength();
/* 117 */       Predef$ predef$ = Predef$.MODULE$;
/* 117 */       BitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1 bitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1 = new BitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1(this, w);
/*     */       Range range;
/* 117 */       if ((range = RichInt$.MODULE$.until$extension0(j, (i + 1) * BitSetLike$.MODULE$.scala$collection$BitSetLike$$WordLength())).validateRangeBoundaries((Function1)bitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1)) {
/*     */         int terminal1;
/*     */         int step1;
/*     */         int i1;
/* 117 */         for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 117 */           int k = i1;
/* 117 */           bitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1.apply(k);
/* 117 */           i1 += step1;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public class BitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final long w$1;
/*     */       
/*     */       public BitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1(BitSetLike$$anonfun$foreach$1 $outer, long w$1) {}
/*     */       
/*     */       public final Object apply(int j) {
/* 118 */         return ((this.w$1 & 1L << j) != 0L) ? this.$outer.f$1.apply(BoxesRunTime.boxToInteger(j)) : BoxedUnit.UNIT;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$$bar$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final long[] words$1;
/*     */     
/*     */     public final BitSet other$1;
/*     */     
/*     */     public final void apply(int idx) {
/* 133 */       apply$mcVI$sp(idx);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$$bar$1(BitSetLike $outer, long[] words$1, BitSet other$1) {}
/*     */     
/*     */     public void apply$mcVI$sp(int idx) {
/* 134 */       this.words$1[idx] = this.$outer.word(idx) | this.other$1.word(idx);
/*     */     }
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$$amp$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final long[] words$2;
/*     */     
/*     */     public final BitSet other$2;
/*     */     
/*     */     public final void apply(int idx) {
/* 147 */       apply$mcVI$sp(idx);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$$amp$1(BitSetLike $outer, long[] words$2, BitSet other$2) {}
/*     */     
/*     */     public void apply$mcVI$sp(int idx) {
/* 148 */       this.words$2[idx] = this.$outer.word(idx) & this.other$2.word(idx);
/*     */     }
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$$amp$tilde$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final long[] words$3;
/*     */     
/*     */     public final BitSet other$3;
/*     */     
/*     */     public final void apply(int idx) {
/* 162 */       apply$mcVI$sp(idx);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$$amp$tilde$1(BitSetLike $outer, long[] words$3, BitSet other$3) {}
/*     */     
/*     */     public void apply$mcVI$sp(int idx) {
/* 163 */       this.words$3[idx] = this.$outer.word(idx) & (this.other$3.word(idx) ^ 0xFFFFFFFFFFFFFFFFL);
/*     */     }
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$$up$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final long[] words$4;
/*     */     
/*     */     public final BitSet other$4;
/*     */     
/*     */     public final void apply(int idx) {
/* 177 */       apply$mcVI$sp(idx);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$$up$1(BitSetLike $outer, long[] words$4, BitSet other$4) {}
/*     */     
/*     */     public void apply$mcVI$sp(int idx) {
/* 178 */       this.words$4[idx] = this.$outer.word(idx) ^ this.other$4.word(idx);
/*     */     }
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$subsetOf$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BitSet other$5;
/*     */     
/*     */     public final boolean apply(int idx) {
/* 192 */       return apply$mcZI$sp(idx);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int idx) {
/* 192 */       return ((this.$outer.word(idx) & (this.other$5.word(idx) ^ 0xFFFFFFFFFFFFFFFFL)) == 0L);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$subsetOf$1(BitSetLike $outer, BitSet other$5) {}
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$addString$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ObjectRef pre$1;
/*     */     
/*     */     public final StringBuilder sb$1;
/*     */     
/*     */     public final String sep$1;
/*     */     
/*     */     public final void apply(int i) {
/* 197 */       apply$mcVI$sp(i);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$addString$1(BitSetLike $outer, ObjectRef pre$1, StringBuilder sb$1, String sep$1) {}
/*     */     
/*     */     public void apply$mcVI$sp(int i) {
/* 198 */       if (this.$outer.contains(i)) {
/* 199 */         this.sb$1.append((String)this.pre$1.elem).append(i);
/* 200 */         this.pre$1.elem = this.sep$1;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\BitSetLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */