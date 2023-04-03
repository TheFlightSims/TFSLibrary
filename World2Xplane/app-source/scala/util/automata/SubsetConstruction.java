/*     */ package scala.util.automata;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Predef$ArrowAssoc$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Map$;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.BitSet;
/*     */ import scala.collection.immutable.BitSet$;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.mutable.HashMap;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.Map$;
/*     */ import scala.collection.mutable.Stack;
/*     */ import scala.math.Ordering;
/*     */ import scala.math.Ordering$Int$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001!3A!\001\002\001\023\t\0212+\0362tKR\034uN\\:ueV\034G/[8o\025\t\031A!\001\005bkR|W.\031;b\025\t)a!\001\003vi&d'\"A\004\002\013M\034\027\r\\1\004\001U\021!\002G\n\003\001-\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g\021!\001\002A!b\001\n\003\t\022a\0018gCV\t!\003E\002\024)Yi\021AA\005\003+\t\021qBT8oI\026$xk\034:e\003V$x.\034\t\003/aa\001\001B\003\032\001\t\007!DA\001U#\tY2\002\005\002\r9%\021QD\002\002\b\035>$\b.\0338h\021!y\002A!A!\002\023\021\022\001\0028gC\002BQ!\t\001\005\002\t\na\001P5oSRtDCA\022%!\r\031\002A\006\005\006!\001\002\rA\005\005\006M\001!\taJ\001\ng\026dWm\031;UC\036$2\001K\0266!\ta\021&\003\002+\r\t\031\021J\034;\t\0131*\003\031A\027\002\003E\003\"AL\032\016\003=R!\001M\031\002\023%lW.\036;bE2,'B\001\032\007\003)\031w\016\0347fGRLwN\\\005\003i=\022aAQ5u'\026$\b\"\002\034&\001\0049\024A\0024j]\006d7\017E\002\rq!J!!\017\004\003\013\005\023(/Y=\t\013m\002A\021\001\037\002\027\021,G/\032:nS:L'0Z\013\002{A\0311C\020\f\n\005}\022!\001\004#fi^{'\017Z!vi>l\007\006\002\001B\t\032\003\"\001\004\"\n\005\r3!A\0033faJ,7-\031;fI\006\nQ)\001\016UQ&\034\be\0317bgN\004s/\0337mA\t,\007E]3n_Z,G-I\001H\003\031\021d&\r\031/a\001")
/*     */ public class SubsetConstruction<T> {
/*     */   private final NondetWordAutom<T> nfa;
/*     */   
/*     */   public NondetWordAutom<T> nfa() {
/*  14 */     return this.nfa;
/*     */   }
/*     */   
/*     */   public SubsetConstruction(NondetWordAutom<T> nfa) {}
/*     */   
/*     */   public int selectTag(BitSet Q, int[] finals) {
/*  18 */     return BoxesRunTime.unboxToInt(((TraversableOnce)((TraversableLike)Q.map((Function1)Predef$.MODULE$.wrapIntArray(finals), BitSet$.MODULE$.canBuildFrom())).filter((Function1)new SubsetConstruction$$anonfun$selectTag$1(this))).min((Ordering)Ordering$Int$.MODULE$));
/*     */   }
/*     */   
/*     */   public class SubsetConstruction$$anonfun$selectTag$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(int x$1) {
/*  18 */       return apply$mcZI$sp(x$1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int x$1) {
/*  18 */       return (x$1 > 0);
/*     */     }
/*     */     
/*     */     public SubsetConstruction$$anonfun$selectTag$1(SubsetConstruction $outer) {}
/*     */   }
/*     */   
/*     */   public DetWordAutom<T> determinize() {
/*  22 */     ObjectRef indexMap = new ObjectRef(Map$.MODULE$.apply((Seq)Nil$.MODULE$));
/*  23 */     Map invIndexMap = (Map)Map$.MODULE$.apply((Seq)Nil$.MODULE$);
/*  24 */     int ix = 0;
/*  27 */     BitSet q0 = (BitSet)BitSet$.MODULE$.apply((Seq)Predef$.MODULE$.wrapIntArray(new int[] { 0 }));
/*  28 */     BitSet sink = BitSet$.MODULE$.empty();
/*  30 */     (new BitSet[2])[0] = q0;
/*  30 */     (new BitSet[2])[1] = sink;
/*  30 */     ObjectRef states = new ObjectRef(Predef$.MODULE$.Set().apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new BitSet[2])));
/*  31 */     HashMap delta = new HashMap();
/*  32 */     Predef$ predef$1 = Predef$.MODULE$;
/*  32 */     Predef$ArrowAssoc$ predef$ArrowAssoc$1 = Predef$ArrowAssoc$.MODULE$;
/*  32 */     (new Tuple2[2])[0] = new Tuple2(q0, sink);
/*  32 */     Predef$ predef$2 = Predef$.MODULE$;
/*  32 */     Predef$ArrowAssoc$ predef$ArrowAssoc$2 = Predef$ArrowAssoc$.MODULE$;
/*  32 */     (new Tuple2[2])[1] = new Tuple2(sink, sink);
/*  32 */     ObjectRef deftrans = new ObjectRef(Map$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[2])));
/*  33 */     ObjectRef finals = new ObjectRef(Map$.MODULE$.apply((Seq)Nil$.MODULE$));
/*  34 */     Stack rest = new Stack();
/*  36 */     rest.push(sink, q0, (Seq)Predef$.MODULE$.wrapRefArray((Object[])new BitSet[0]));
/*  50 */     addFinal$1(q0, finals);
/*     */     while (true) {
/*  52 */       if (rest.isEmpty()) {
/*  76 */         int nstatesR = ((Set)states.elem).size();
/*  77 */         Map[] deltaR = new Map[nstatesR];
/*  78 */         int[] defaultR = new int[nstatesR];
/*  79 */         int[] finalsR = new int[nstatesR];
/*  81 */         ((Set)states.elem).foreach((Function1)new SubsetConstruction$$anonfun$determinize$2(this, indexMap, delta, deftrans, deltaR, (SubsetConstruction<T>)defaultR));
/*  98 */         ((Map)finals.elem).foreach((Function1)new SubsetConstruction$$anonfun$determinize$3(this, indexMap, (SubsetConstruction<T>)finalsR));
/* 100 */         return new SubsetConstruction$$anon$1(this, nstatesR, deltaR, defaultR, (SubsetConstruction<T>)finalsR);
/*     */       } 
/*     */       BitSet P = (BitSet)rest.pop();
/*     */       indexMap.elem = ((Map)indexMap.elem).updated(P, BoxesRunTime.boxToInteger(ix));
/*     */       invIndexMap = invIndexMap.updated(BoxesRunTime.boxToInteger(ix), P);
/*     */       ix++;
/*     */       HashMap Pdelta = new HashMap();
/*     */       delta.update(P, Pdelta);
/*     */       nfa().labels().foreach((Function1)new SubsetConstruction$$anonfun$determinize$1(this, states, finals, rest, P, (SubsetConstruction<T>)Pdelta));
/*     */       BitSet Pdef = nfa().nextDefault(P);
/*     */       deftrans.elem = ((Map)deftrans.elem).updated(P, Pdef);
/*     */       scala$util$automata$SubsetConstruction$$add$1(Pdef, states, finals, rest);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void addFinal$1(BitSet q, ObjectRef finals$1) {
/*     */     if (nfa().containsFinal(q))
/*     */       finals$1.elem = ((Map)finals$1.elem).updated(q, BoxesRunTime.boxToInteger(selectTag(q, nfa().finals()))); 
/*     */   }
/*     */   
/*     */   public final void scala$util$automata$SubsetConstruction$$add$1(BitSet Q, ObjectRef states$1, ObjectRef finals$1, Stack rest$1) {
/*     */     if (!((Set)states$1.elem).apply(Q)) {
/*     */       states$1.elem = ((Set)states$1.elem).$plus(Q);
/*     */       rest$1.push(Q);
/*     */       addFinal$1(Q, finals$1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public class SubsetConstruction$$anonfun$determinize$1 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef states$1;
/*     */     
/*     */     private final ObjectRef finals$1;
/*     */     
/*     */     private final Stack rest$1;
/*     */     
/*     */     private final BitSet P$1;
/*     */     
/*     */     private final HashMap Pdelta$1;
/*     */     
/*     */     public SubsetConstruction$$anonfun$determinize$1(SubsetConstruction $outer, ObjectRef states$1, ObjectRef finals$1, Stack rest$1, BitSet P$1, HashMap Pdelta$1) {}
/*     */     
/*     */     public final void apply(Object label) {
/*     */       BitSet Q = this.$outer.nfa().next(this.P$1, label);
/*     */       this.Pdelta$1.update(label, Q);
/*     */       this.$outer.scala$util$automata$SubsetConstruction$$add$1(Q, this.states$1, this.finals$1, this.rest$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SubsetConstruction$$anonfun$determinize$2 extends AbstractFunction1<BitSet, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ObjectRef indexMap$1;
/*     */     
/*     */     private final HashMap delta$1;
/*     */     
/*     */     private final ObjectRef deftrans$1;
/*     */     
/*     */     private final Map[] deltaR$1;
/*     */     
/*     */     private final int[] defaultR$1;
/*     */     
/*     */     public SubsetConstruction$$anonfun$determinize$2(SubsetConstruction $outer, ObjectRef indexMap$1, HashMap delta$1, ObjectRef deftrans$1, Map[] deltaR$1, int[] defaultR$1) {}
/*     */     
/*     */     public final void apply(BitSet Q) {
/*     */       int q = BoxesRunTime.unboxToInt(((Map)this.indexMap$1.elem).apply(Q));
/*     */       HashMap trans = (HashMap)this.delta$1.apply(Q);
/*     */       BitSet transDef = (BitSet)((Map)this.deftrans$1.elem).apply(Q);
/*     */       int qDef = BoxesRunTime.unboxToInt(((Map)this.indexMap$1.elem).apply(transDef));
/*     */       HashMap ntrans = new HashMap();
/*     */       trans.withFilter((Function1)new SubsetConstruction$$anonfun$determinize$2$$anonfun$apply$1(this)).foreach((Function1)new SubsetConstruction$$anonfun$determinize$2$$anonfun$apply$2(this, qDef, ($anonfun$determinize$2)ntrans));
/*     */       this.deltaR$1[q] = (Map)ntrans;
/*     */       this.defaultR$1[q] = qDef;
/*     */     }
/*     */     
/*     */     public class SubsetConstruction$$anonfun$determinize$2$$anonfun$apply$1 extends AbstractFunction1<Tuple2<T, BitSet>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 check$ifrefutable$1) {
/*     */         boolean bool;
/*     */         if (check$ifrefutable$1 != null) {
/*     */           bool = true;
/*     */         } else {
/*     */           bool = false;
/*     */         } 
/*     */         return bool;
/*     */       }
/*     */       
/*     */       public SubsetConstruction$$anonfun$determinize$2$$anonfun$apply$1(SubsetConstruction$$anonfun$determinize$2 $outer) {}
/*     */     }
/*     */     
/*     */     public class SubsetConstruction$$anonfun$determinize$2$$anonfun$apply$2 extends AbstractFunction1<Tuple2<T, BitSet>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int qDef$1;
/*     */       
/*     */       private final HashMap ntrans$1;
/*     */       
/*     */       public final void apply(Tuple2 x$2) {
/*     */         if (x$2 != null) {
/*     */           int p = BoxesRunTime.unboxToInt(((Map)this.$outer.indexMap$1.elem).apply(x$2._2()));
/*     */           if (p != this.qDef$1)
/*     */             this.ntrans$1.update(x$2._1(), BoxesRunTime.boxToInteger(p)); 
/*     */           return;
/*     */         } 
/*     */         throw new MatchError(x$2);
/*     */       }
/*     */       
/*     */       public SubsetConstruction$$anonfun$determinize$2$$anonfun$apply$2(SubsetConstruction$$anonfun$determinize$2 $outer, int qDef$1, HashMap ntrans$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class SubsetConstruction$$anonfun$determinize$3 extends AbstractFunction1<Tuple2<BitSet, Object>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef indexMap$1;
/*     */     
/*     */     private final int[] finalsR$1;
/*     */     
/*     */     public final void apply(Tuple2 x0$1) {
/*     */       if (x0$1 != null) {
/*     */         this.finalsR$1[BoxesRunTime.unboxToInt(((Map)this.indexMap$1.elem).apply(x0$1._1()))] = x0$1._2$mcI$sp();
/*     */         return;
/*     */       } 
/*     */       throw new MatchError(x0$1);
/*     */     }
/*     */     
/*     */     public SubsetConstruction$$anonfun$determinize$3(SubsetConstruction $outer, ObjectRef indexMap$1, int[] finalsR$1) {}
/*     */   }
/*     */   
/*     */   public class SubsetConstruction$$anon$1 extends DetWordAutom<T> {
/*     */     private final int nstates;
/*     */     
/*     */     private final Map<T, Object>[] delta;
/*     */     
/*     */     private final int[] default;
/*     */     
/*     */     private final int[] finals;
/*     */     
/*     */     public SubsetConstruction$$anon$1(SubsetConstruction $outer, int nstatesR$1, Map[] deltaR$1, int[] defaultR$1, int[] finalsR$1) {
/* 101 */       this.nstates = nstatesR$1;
/* 102 */       this.delta = (Map<T, Object>[])deltaR$1;
/* 103 */       this.default = defaultR$1;
/* 104 */       this.finals = finalsR$1;
/*     */     }
/*     */     
/*     */     public int nstates() {
/*     */       return this.nstates;
/*     */     }
/*     */     
/*     */     public Map<T, Object>[] delta() {
/*     */       return this.delta;
/*     */     }
/*     */     
/*     */     public int[] default() {
/*     */       return this.default;
/*     */     }
/*     */     
/*     */     public int[] finals() {
/* 104 */       return this.finals;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\automata\SubsetConstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */