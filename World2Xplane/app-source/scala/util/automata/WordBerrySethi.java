/*     */ package scala.util.automata;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Predef$ArrowAssoc$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.BitSet;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.MapLike;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.BitSet;
/*     */ import scala.collection.immutable.BitSet$;
/*     */ import scala.collection.immutable.IndexedSeq$;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Map$;
/*     */ import scala.collection.immutable.Map$EmptyMap$;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Range$;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.HashMap;
/*     */ import scala.collection.mutable.HashMap$;
/*     */ import scala.collection.mutable.HashSet;
/*     */ import scala.collection.mutable.HashSet$;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.util.regexp.Base;
/*     */ import scala.util.regexp.WordExp;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005-e!B\001\003\003\003I!AD,pe\022\024UM\035:z'\026$\b.\033\006\003\007\021\t\001\"Y;u_6\fG/\031\006\003\013\031\tA!\036;jY*\tq!A\003tG\006d\027m\001\001\024\005\001Q\001CA\006\r\033\005\021\021BA\007\003\0059\021\025m]3CKJ\024\030pU3uQ&DQa\004\001\005\002A\ta\001P5oSRtD#A\t\021\005-\001\001bB\n\001\005\0045\t\005F\001\005Y\006tw-F\001\026!\t1\022$D\001\030\025\tAB!\001\004sK\036,\007\020]\005\0035]\021qaV8sI\026C\b\017C\005\035\001\001\007\t\031!C\t;\0051A.\0312fYN,\022A\b\t\004?\0212S\"\001\021\013\005\005\022\023aB7vi\006\024G.\032\006\003G\031\t!bY8mY\026\034G/[8o\023\t)\003EA\004ICND7+\032;\021\005\035JcB\001\025\023\033\005\001\021B\001\026\032\005\035yF.\0312fYRC\021\002\f\001A\002\003\007I\021C\027\002\0251\f'-\0327t?\022*\027\017\006\002/eA\021q\006M\007\002\r%\021\021G\002\002\005+:LG\017C\0044W\005\005\t\031\001\020\002\007a$\023\007\003\0046\001\001\006KAH\001\bY\006\024W\r\\:!\021%9\004\0011AA\002\023E\001(A\004mC\n,G.\021;\026\003e\002BAO\037AM9\021qfO\005\003y\031\ta\001\025:fI\0264\027B\001 @\005\ri\025\r\035\006\003y\031\001\"aL!\n\005\t3!aA%oi\"IA\t\001a\001\002\004%\t\"R\001\fY\006\024W\r\\!u?\022*\027\017\006\002/\r\"91gQA\001\002\004I\004B\002%\001A\003&\021(\001\005mC\n,G.\021;!\021%Q\005\0011AA\002\023E1*\001\004eK2$\030-]\013\002\031B\031q&T(\n\00593!!B!se\006L\b\003B\020QMIK!!\025\021\003\017!\0137\017['baB\0311k\027!\017\005QKfBA+Y\033\0051&BA,\t\003\031a$o\\8u}%\tq!\003\002[\r\0059\001/Y2lC\036,\027B\001/^\005\021a\025n\035;\013\005i3\001\"C0\001\001\004\005\r\021\"\005a\003)!W\r\034;bc~#S-\035\013\003]\005Dqa\r0\002\002\003\007A\n\003\004d\001\001\006K\001T\001\bI\026dG/Y9!\021%)\007\0011AA\002\023Ea-\001\005eK\032\fW\017\034;r+\0059\007cA\030N%\"I\021\016\001a\001\002\004%\tB[\001\rI\0264\027-\0367uc~#S-\035\013\003]-Dqa\r5\002\002\003\007q\r\003\004n\001\001\006KaZ\001\nI\0264\027-\0367uc\002B\021b\034\001A\002\003\007I\021\0039\002\021%t\027\016^5bYN,\022!\035\t\004uI\004\025BA:@\005\r\031V\r\036\005\nk\002\001\r\0211A\005\022Y\fA\"\0338ji&\fGn]0%KF$\"AL<\t\017M\"\030\021!a\001c\"1\021\020\001Q!\nE\f\021\"\0338ji&\fGn\035\021\t\013m\004A\021\013?\002\023\r|W\016\035$jeN$HCA9~\021\025q(\0201\001\000\003\005\021\bcA\024\002\002%!\0211AA\003\005\031\021VmZ#ya&\031\021qA\f\003\t\t\0137/\032\005\b\003\027\001A\021KA\007\003!\031w.\0349MCN$HcA9\002\020!1a0!\003A\002}Dq!a\005\001\t#\n)\"A\006d_6\004hi\0347m_^\fD#B9\002\030\005m\001bBA\r\003#\001\r!]\001\005M>d\027\007\003\004\003#\001\ra \005\b\003?\001A\021CA\021\003%\031X-\0328MC\n,G\016F\004/\003G\t)#!\013\t\ry\fi\0021\001\000\021\035\t9#!\bA\002\001\013\021!\033\005\b\003W\ti\0021\001'\003\025a\027MY3m\021\035\ty\002\001C\t\003_!R\001QA\031\003gAaA`A\027\001\004y\bbBA\026\003[\001\rA\n\005\b\003o\001A\021IA\035\003!!(/\031<feN,Gc\001\030\002<!1a0!\016A\002}Dq!a\020\001\t#\t\t%\001\bnC.,GK]1og&$\030n\0348\025\0179\n\031%a\022\002L!9\021QIA\037\001\004\001\025aA:sG\"9\021\021JA\037\001\004\001\025\001\0023fgRDq!a\013\002>\001\007a\005C\004\002P\001!\t\"!\025\002\025%t\027\016^5bY&TX\rF\002/\003'B\001\"!\026\002N\001\007\021qK\001\bgV\024W\r\0379s!\021\031\026\021L@\n\007\005mSLA\002TKFDq!a\030\001\t#\t\t'A\bj]&$\030.\0317ju\026\fU\017^8n)\005q\003bBA3\001\021E\021\021M\001\023G>dG.Z2u)J\fgn]5uS>t7\017C\004\002j\001!\t!a\033\002\033\005,Ho\\7bi>tgI]8n)\031\ti'a\035\002xA!1\"a\034'\023\r\t\tH\001\002\020\035>tG-\032;X_J$\027)\036;p[\"9\021QOA4\001\004y\030a\0019bi\"9\021\021PA4\001\004\001\025\001\0034j]\006dG+Y4)\017\001\ti(a!\002\bB\031q&a \n\007\005\005eA\001\006eKB\024XmY1uK\022\f#!!\"\0025QC\027n\035\021dY\006\0348\017I<jY2\004#-\032\021sK6|g/\0323\"\005\005%\025A\002\032/cAr\003\007")
/*     */ public abstract class WordBerrySethi extends BaseBerrySethi {
/*     */   private HashSet<WordExp.Label> labels;
/*     */   
/*     */   private Map<Object, WordExp.Label> labelAt;
/*     */   
/*     */   private HashMap<WordExp.Label, List<Object>>[] deltaq;
/*     */   
/*     */   private List<Object>[] defaultq;
/*     */   
/*     */   private Set<Object> initials;
/*     */   
/*     */   public abstract WordExp lang();
/*     */   
/*     */   public HashSet<WordExp.Label> labels() {
/*  26 */     return this.labels;
/*     */   }
/*     */   
/*     */   public void labels_$eq(HashSet<WordExp.Label> x$1) {
/*  26 */     this.labels = x$1;
/*     */   }
/*     */   
/*     */   public Map<Object, WordExp.Label> labelAt() {
/*  28 */     return this.labelAt;
/*     */   }
/*     */   
/*     */   public void labelAt_$eq(Map<Object, WordExp.Label> x$1) {
/*  28 */     this.labelAt = x$1;
/*     */   }
/*     */   
/*     */   public HashMap<WordExp.Label, List<Object>>[] deltaq() {
/*  29 */     return this.deltaq;
/*     */   }
/*     */   
/*     */   public void deltaq_$eq(HashMap[] x$1) {
/*  29 */     this.deltaq = (HashMap<WordExp.Label, List<Object>>[])x$1;
/*     */   }
/*     */   
/*     */   public List<Object>[] defaultq() {
/*  30 */     return this.defaultq;
/*     */   }
/*     */   
/*     */   public void defaultq_$eq(List[] x$1) {
/*  30 */     this.defaultq = (List<Object>[])x$1;
/*     */   }
/*     */   
/*     */   public Set<Object> initials() {
/*  31 */     return this.initials;
/*     */   }
/*     */   
/*     */   public void initials_$eq(Set<Object> x$1) {
/*  31 */     this.initials = x$1;
/*     */   }
/*     */   
/*     */   public Set<Object> compFirst(Base.RegExp r) {
/*     */     Set<Object> set;
/*  38 */     if (r instanceof WordExp.Letter) {
/*  38 */       WordExp.Letter letter = (WordExp.Letter)r;
/*  38 */       set = (Set)Predef$.MODULE$.Set().apply((Seq)Predef$.MODULE$.wrapIntArray(new int[] { letter.pos() }));
/*     */     } else {
/*  40 */       set = super.compFirst(r);
/*     */     } 
/*     */     return set;
/*     */   }
/*     */   
/*     */   public Set<Object> compLast(Base.RegExp r) {
/*     */     Set<Object> set;
/*  48 */     if (r instanceof WordExp.Letter) {
/*  48 */       WordExp.Letter letter = (WordExp.Letter)r;
/*  48 */       set = (Set)Predef$.MODULE$.Set().apply((Seq)Predef$.MODULE$.wrapIntArray(new int[] { letter.pos() }));
/*     */     } else {
/*  50 */       set = super.compLast(r);
/*     */     } 
/*     */     return set;
/*     */   }
/*     */   
/*     */   public Set<Object> compFollow1(Set<Object> fol1, Base.RegExp r) {
/*     */     Set<Object> set;
/*  59 */     if (r instanceof WordExp.Letter) {
/*  59 */       WordExp.Letter letter = (WordExp.Letter)r;
/*  59 */       follow().update(BoxesRunTime.boxToInteger(letter.pos()), fol1);
/*  59 */       set = (Set)Predef$.MODULE$.Set().apply((Seq)Predef$.MODULE$.wrapIntArray(new int[] { letter.pos() }));
/*     */     } else {
/*  61 */       if (lang().Eps() == null) {
/*  61 */         lang().Eps();
/*  61 */         if (r != null)
/*  62 */           Set<Object> set1 = super.compFollow1(fol1, r); 
/*     */       } else {
/*     */         if (lang().Eps().equals(r))
/*     */           Set<Object> set2 = emptySet(); 
/*  62 */         Set<Object> set1 = super.compFollow1(fol1, r);
/*     */       } 
/*     */       set = emptySet();
/*     */     } 
/*     */     return set;
/*     */   }
/*     */   
/*     */   public void seenLabel(Base.RegExp r, int i, WordExp.Label label) {
/*  71 */     labelAt_$eq(labelAt().updated(BoxesRunTime.boxToInteger(i), label));
/*  72 */     labels().$plus$eq(label);
/*     */   }
/*     */   
/*     */   public int seenLabel(Base.RegExp r, WordExp.Label label) {
/*  77 */     pos_$eq(pos() + 1);
/*  78 */     seenLabel(r, pos(), label);
/*  79 */     return pos();
/*     */   }
/*     */   
/*     */   public void traverse(Base.RegExp r) {
/*  83 */     if (r instanceof WordExp.Letter) {
/*  83 */       WordExp.Letter letter = (WordExp.Letter)r;
/*  83 */       letter
/*  84 */         .pos_$eq(seenLabel(r, letter.a()));
/*  85 */     } else if (lang().Eps() == null) {
/*  85 */       lang().Eps();
/*  85 */       if (r != null) {
/*  86 */         super.traverse(r);
/*     */         return;
/*     */       } 
/*     */     } else if (!lang().Eps().equals(r)) {
/*  86 */       super.traverse(r);
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void makeTransition(int src, int dest, WordExp.Label label) {
/*  91 */     HashMap<WordExp.Label, List<Object>> q = deltaq()[src];
/*  92 */     q.update(label, ((List)q.getOrElse(label, (Function0)new WordBerrySethi$$anonfun$makeTransition$1(this))).$colon$colon(BoxesRunTime.boxToInteger(dest)));
/*     */   }
/*     */   
/*     */   public class WordBerrySethi$$anonfun$makeTransition$1 extends AbstractFunction0<Nil$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Nil$ apply() {
/*  92 */       return Nil$.MODULE$;
/*     */     }
/*     */     
/*     */     public WordBerrySethi$$anonfun$makeTransition$1(WordBerrySethi $outer) {}
/*     */   }
/*     */   
/*     */   public void initialize(Seq subexpr) {
/*  96 */     labelAt_$eq((Map<Object, WordExp.Label>)Map$.MODULE$.apply((Seq)Nil$.MODULE$));
/*  97 */     follow_$eq((HashMap<Object, Set<Object>>)HashMap$.MODULE$.apply((Seq)Nil$.MODULE$));
/*  98 */     labels_$eq((HashSet<WordExp.Label>)HashSet$.MODULE$.apply((Seq)Nil$.MODULE$));
/*  99 */     pos_$eq(0);
/* 102 */     subexpr.foreach((Function1)new WordBerrySethi$$anonfun$initialize$1(this));
/* 104 */     initials_$eq((Set<Object>)Predef$.MODULE$.Set().apply((Seq)Predef$.MODULE$.wrapIntArray(new int[] { 0 })));
/*     */   }
/*     */   
/*     */   public class WordBerrySethi$$anonfun$initialize$1 extends AbstractFunction1<Base.RegExp, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Base.RegExp r) {
/*     */       this.$outer.traverse(r);
/*     */     }
/*     */     
/*     */     public WordBerrySethi$$anonfun$initialize$1(WordBerrySethi $outer) {}
/*     */   }
/*     */   
/*     */   public void initializeAutom() {
/* 108 */     Map$ map$ = Map$.MODULE$;
/* 108 */     finals_$eq((Map<Object, Object>)Map$EmptyMap$.MODULE$);
/* 109 */     deltaq_$eq((HashMap<WordExp.Label, List<Object>>[])new HashMap[pos()]);
/* 110 */     defaultq_$eq((List<Object>[])new List[pos()]);
/* 112 */     Predef$ predef$ = Predef$.MODULE$;
/* 112 */     int i = pos();
/* 112 */     Range$ range$ = Range$.MODULE$;
/* 112 */     WordBerrySethi$$anonfun$initializeAutom$1 wordBerrySethi$$anonfun$initializeAutom$1 = new WordBerrySethi$$anonfun$initializeAutom$1(this);
/*     */     Range range;
/* 112 */     if ((range = new Range(0, i, 1)).validateRangeBoundaries((Function1)wordBerrySethi$$anonfun$initializeAutom$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 112 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 112 */         Nil$ nil$ = Nil$.MODULE$;
/* 112 */         HashMap$ hashMap$ = HashMap$.MODULE$;
/* 112 */         deltaq()[i1] = (HashMap<WordExp.Label, List<Object>>)((Builder)((Builder)new HashMap()).$plus$plus$eq((TraversableOnce)nil$)).result();
/* 112 */         defaultq()[i1] = (List<Object>)Nil$.MODULE$;
/* 112 */         i1 += step1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public class WordBerrySethi$$anonfun$initializeAutom$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(int j) {
/* 112 */       apply$mcVI$sp(j);
/*     */     }
/*     */     
/*     */     public WordBerrySethi$$anonfun$initializeAutom$1(WordBerrySethi $outer) {}
/*     */     
/*     */     public void apply$mcVI$sp(int j) {
/* 113 */       this.$outer.deltaq()[j] = (HashMap<WordExp.Label, List<Object>>)HashMap$.MODULE$.apply((Seq)Nil$.MODULE$);
/* 114 */       this.$outer.defaultq()[j] = (List<Object>)Nil$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public void collectTransitions() {
/* 119 */     Predef$ predef$ = Predef$.MODULE$;
/* 119 */     ((IterableLike)RichInt$.MODULE$.until$extension0(0, pos()).map((Function1)new WordBerrySethi$$anonfun$collectTransitions$1(this), IndexedSeq$.MODULE$.canBuildFrom())).foreach((Function1)new WordBerrySethi$$anonfun$collectTransitions$2(this));
/*     */   }
/*     */   
/*     */   public class WordBerrySethi$$anonfun$collectTransitions$1 extends AbstractFunction1<Object, Tuple2<Object, Set<Object>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<Object, Set<Object>> apply(int j) {
/* 119 */       Set fol = (Set)this.$outer.follow().apply(BoxesRunTime.boxToInteger(j));
/* 119 */       return new Tuple2(BoxesRunTime.boxToInteger(j), fol);
/*     */     }
/*     */     
/*     */     public WordBerrySethi$$anonfun$collectTransitions$1(WordBerrySethi $outer) {}
/*     */   }
/*     */   
/*     */   public class WordBerrySethi$$anonfun$collectTransitions$2 extends AbstractFunction1<Tuple2<Object, Set<Object>>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Tuple2 x$2) {
/* 119 */       if (x$2 != null) {
/* 119 */         ((IterableLike)x$2._2()).foreach((Function1)new WordBerrySethi$$anonfun$collectTransitions$2$$anonfun$apply$1(this, x$2));
/*     */         return;
/*     */       } 
/* 119 */       throw new MatchError(x$2);
/*     */     }
/*     */     
/*     */     public WordBerrySethi$$anonfun$collectTransitions$2(WordBerrySethi $outer) {}
/*     */     
/*     */     public class WordBerrySethi$$anonfun$collectTransitions$2$$anonfun$apply$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Tuple2 x1$1;
/*     */       
/*     */       public final void apply(int k) {
/* 119 */         apply$mcVI$sp(k);
/*     */       }
/*     */       
/*     */       public WordBerrySethi$$anonfun$collectTransitions$2$$anonfun$apply$1(WordBerrySethi$$anonfun$collectTransitions$2 $outer, Tuple2 x1$1) {}
/*     */       
/*     */       public void apply$mcVI$sp(int k) {
/* 120 */         if (this.$outer.$outer.pos() == k)
/* 120 */           this.$outer.$outer.finals_$eq(this.$outer.scala$util$automata$WordBerrySethi$$anonfun$$$outer().finals().updated(BoxesRunTime.boxToInteger(this.x1$1._1$mcI$sp()), BoxesRunTime.boxToInteger(this.$outer.scala$util$automata$WordBerrySethi$$anonfun$$$outer().finalTag()))); 
/* 121 */         this.$outer.$outer.makeTransition(this.x1$1._1$mcI$sp(), k, (WordExp.Label)this.$outer.scala$util$automata$WordBerrySethi$$anonfun$$$outer().labelAt().apply(BoxesRunTime.boxToInteger(k)));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public NondetWordAutom<WordExp.Label> automatonFrom(Base.RegExp pat, int finalTag) {
/*     */     NondetWordAutom<WordExp.Label> nondetWordAutom;
/* 125 */     finalTag_$eq(finalTag);
/* 127 */     if (pat instanceof Base.Sequ) {
/* 127 */       Base.Sequ sequ = (Base.Sequ)pat;
/* 130 */       initialize(sequ.rs());
/* 131 */       pos_$eq(pos() + 1);
/* 132 */       compFollow(sequ.rs());
/* 135 */       initializeAutom();
/* 136 */       collectTransitions();
/* 138 */       if (sequ.isNullable())
/* 139 */         finals_$eq(finals().updated(BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(finalTag))); 
/* 141 */       Map delta1 = (Map)Map$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])deltaq()).zipWithIndex(Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(Tuple2.class)))).map((Function1)new WordBerrySethi$$anonfun$2(this), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(Tuple2.class)))));
/* 142 */       Predef$ predef$1 = Predef$.MODULE$;
/* 142 */       int[] finalsArr = (int[])((TraversableOnce)RichInt$.MODULE$.until$extension0(0, pos()).map((Function1)new WordBerrySethi$$anonfun$1(this), IndexedSeq$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.Int());
/* 143 */       int[] initialsArr = (int[])initials().toArray(ClassTag$.MODULE$.Int());
/* 146 */       Predef$ predef$2 = Predef$.MODULE$;
/* 146 */       Map[] deltaArr = (Map[])((TraversableOnce)RichInt$.MODULE$.until$extension0(0, pos()).map((Function1)new WordBerrySethi$$anonfun$3(this, delta1), IndexedSeq$.MODULE$.canBuildFrom()))
/*     */         
/* 148 */         .toArray(ClassTag$.MODULE$.apply(Map.class));
/* 150 */       Predef$ predef$3 = Predef$.MODULE$;
/* 150 */       BitSet[] defaultArr = (BitSet[])((TraversableOnce)RichInt$.MODULE$.until$extension0(0, pos()).map((Function1)new WordBerrySethi$$anonfun$4(this), IndexedSeq$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.apply(BitSet.class));
/* 152 */       nondetWordAutom = new WordBerrySethi$$anon$1(this, finalsArr, initialsArr, deltaArr, defaultArr);
/*     */     } else {
/* 161 */       (new Base.RegExp[1])[0] = pat;
/* 161 */       nondetWordAutom = automatonFrom(lang().Sequ().apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Base.RegExp[1])), finalTag);
/*     */     } 
/*     */     return nondetWordAutom;
/*     */   }
/*     */   
/*     */   public class WordBerrySethi$$anonfun$2 extends AbstractFunction1<Tuple2<HashMap<WordExp.Label, List<Object>>, Object>, Tuple2<Object, HashMap<WordExp.Label, List<Object>>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<Object, HashMap<WordExp.Label, List<Object>>> apply(Tuple2 x$3) {
/*     */       return x$3.swap();
/*     */     }
/*     */     
/*     */     public WordBerrySethi$$anonfun$2(WordBerrySethi $outer) {}
/*     */   }
/*     */   
/*     */   public class WordBerrySethi$$anonfun$1 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int k) {
/*     */       return apply$mcII$sp(k);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int k) {
/*     */       return BoxesRunTime.unboxToInt(this.$outer.finals().getOrElse(BoxesRunTime.boxToInteger(k), (Function0)new WordBerrySethi$$anonfun$1$$anonfun$apply$mcII$sp$1(this)));
/*     */     }
/*     */     
/*     */     public WordBerrySethi$$anonfun$1(WordBerrySethi $outer) {}
/*     */     
/*     */     public class WordBerrySethi$$anonfun$1$$anonfun$apply$mcII$sp$1 extends AbstractFunction0.mcI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply() {
/*     */         return 0;
/*     */       }
/*     */       
/*     */       public int apply$mcI$sp() {
/*     */         return 0;
/*     */       }
/*     */       
/*     */       public WordBerrySethi$$anonfun$1$$anonfun$apply$mcII$sp$1(WordBerrySethi$$anonfun$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class WordBerrySethi$$anonfun$3 extends AbstractFunction1<Object, HashMap<WordExp.Label, BitSet>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Map delta1$1;
/*     */     
/*     */     public WordBerrySethi$$anonfun$3(WordBerrySethi $outer, Map delta1$1) {}
/*     */     
/*     */     public final HashMap<WordExp.Label, BitSet> apply(int x) {
/*     */       return (HashMap<WordExp.Label, BitSet>)HashMap$.MODULE$.apply((Seq)((MapLike)this.delta1$1.apply(BoxesRunTime.boxToInteger(x))).toSeq().map((Function1)new WordBerrySethi$$anonfun$3$$anonfun$apply$2(this), Seq$.MODULE$.canBuildFrom()));
/*     */     }
/*     */     
/*     */     public class WordBerrySethi$$anonfun$3$$anonfun$apply$2 extends AbstractFunction1<Tuple2<WordExp.Label, List<Object>>, Tuple2<WordExp.Label, BitSet>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Tuple2<WordExp.Label, BitSet> apply(Tuple2 x0$1) {
/*     */         if (x0$1 != null) {
/*     */           Object object = x0$1._1();
/*     */           Predef$ predef$ = Predef$.MODULE$;
/*     */           BitSet bitSet = BitSet$.MODULE$.apply((Seq)x0$1._2());
/*     */           Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
/*     */           return new Tuple2(object, bitSet);
/*     */         } 
/*     */         throw new MatchError(x0$1);
/*     */       }
/*     */       
/*     */       public WordBerrySethi$$anonfun$3$$anonfun$apply$2(WordBerrySethi$$anonfun$3 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class WordBerrySethi$$anonfun$4 extends AbstractFunction1<Object, BitSet> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final BitSet apply(int k) {
/*     */       return (BitSet)BitSet$.MODULE$.apply((Seq)this.$outer.defaultq()[k]);
/*     */     }
/*     */     
/*     */     public WordBerrySethi$$anonfun$4(WordBerrySethi $outer) {}
/*     */   }
/*     */   
/*     */   public class WordBerrySethi$$anon$1 extends NondetWordAutom<WordExp.Label> {
/*     */     private final int nstates;
/*     */     
/*     */     private final List<WordExp.Label> labels;
/*     */     
/*     */     private final int[] initials;
/*     */     
/*     */     private final int[] finals;
/*     */     
/*     */     private final Map<WordExp.Label, BitSet>[] delta;
/*     */     
/*     */     private final BitSet[] default;
/*     */     
/*     */     public WordBerrySethi$$anon$1(WordBerrySethi $outer, int[] finalsArr$1, int[] initialsArr$1, Map[] deltaArr$1, BitSet[] defaultArr$1) {
/*     */       this.nstates = $outer.pos();
/*     */       this.labels = $outer.labels().toList();
/*     */       this.initials = initialsArr$1;
/*     */       this.finals = finalsArr$1;
/*     */       this.delta = (Map<WordExp.Label, BitSet>[])deltaArr$1;
/*     */       this.default = defaultArr$1;
/*     */     }
/*     */     
/*     */     public int nstates() {
/*     */       return this.nstates;
/*     */     }
/*     */     
/*     */     public List<WordExp.Label> labels() {
/*     */       return this.labels;
/*     */     }
/*     */     
/*     */     private int[] initials() {
/*     */       return this.initials;
/*     */     }
/*     */     
/*     */     public int[] finals() {
/*     */       return this.finals;
/*     */     }
/*     */     
/*     */     public Map<WordExp.Label, BitSet>[] delta() {
/*     */       return this.delta;
/*     */     }
/*     */     
/*     */     public BitSet[] default() {
/*     */       return this.default;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\automata\WordBerrySethi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */