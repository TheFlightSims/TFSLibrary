/*     */ package akka.util;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Predef$ArrowAssoc$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.SetLike;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Seq$;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Set$;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.immutable.Vector$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005uxAB\001\003\021\003!a!\001\nTk\n\034G.Y:tS\032LW\rZ%oI\026D(BA\002\005\003\021)H/\0337\013\003\025\tA!Y6lCB\021q\001C\007\002\005\0311\021B\001E\001\t)\021!cU;cG2\f7o]5gS\026$\027J\0343fqN\021\001b\003\t\003\031=i\021!\004\006\002\035\005)1oY1mC&\021\001#\004\002\007\003:L(+\0324\t\013IAA\021\001\013\002\rqJg.\033;?\007\001!\022A\002\004\005-!\001qCA\004O_:\024xn\034;\026\013a\t\031)a\"\024\005UI\002CB\004\033\003\003\013)IB\003\n\005\001!1$F\002\035\005&\032\"AG\006\t\021yQ\"\0211A\005\022}\taA^1mk\026\034X#\001\021\021\007\005\"sE\004\002\rE%\0211%D\001\007!J,G-\0324\n\005\0252#aA*fi*\0211%\004\t\003Q%b\001\001B\003+5\t\0071FA\001W#\tas\006\005\002\r[%\021a&\004\002\b\035>$\b.\0338h!\ta\001'\003\0022\033\t\031\021I\\=\t\021MR\"\0211A\005\022Q\n!B^1mk\026\034x\fJ3r)\t)\004\b\005\002\rm%\021q'\004\002\005+:LG\017C\004:e\005\005\t\031\001\021\002\007a$\023\007\003\005<5\t\005\t\025)\003!\003\0351\030\r\\;fg\002B\001\"\020\016\003\002\003\006YAP\001\003g\016\0042aB B\023\t\001%AA\tTk\n\034G.Y:tS\032L7-\031;j_:\004\"\001\013\"\005\013\rS\"\031A\026\003\003-CQA\005\016\005\n\025#\"AR%\025\005\035C\005\003B\004\033\003\036BQ!\020#A\004yBQA\b#A\002\001*Aa\023\016\001\031\n91\t[1oO\026\034\bcA'S)6\taJ\003\002P!\006I\021.\\7vi\006\024G.\032\006\003#6\t!bY8mY\026\034G/[8o\023\t\031fJA\002TKF\004B\001D+BA%\021a+\004\002\007)V\004H.\032\032\t\017aS\002\031!C\t3\00691/\0362lKf\034X#\001.\021\0075[V,\003\002]\035\n1a+Z2u_J\004BAX\013BO9\021q\001\001\005\bAj\001\r\021\"\005b\003-\031XOY6fsN|F%Z9\025\005U\022\007bB\035`\003\003\005\rA\027\005\007Ij\001\013\025\002.\002\021M,(m[3zg\002BQA\005\016\005\002\031$\022a\032\013\003\017\"DQ!P3A\004yBqA\033\016C\002\023E1.\001\003s_>$X#A$\t\r5T\002\025!\003H\003\025\021xn\034;!\021\025y'\004\"\001q\003\031\tG\rZ&fsR\021\021o\035\t\003e*k\021A\007\005\006i:\004\r!Q\001\004W\026L\b\"\002<\033\t#9\030aC5o]\026\024\030\t\0323LKf$\"!\035=\t\013Q,\b\031A!\t\013iTB\021A>\002\021\005$GMV1mk\026$2!\035?~\021\025!\030\0201\001B\021\025q\030\0201\001(\003\0251\030\r\\;f\021\035\t\tA\007C\t\003\007\tQ\"\0338oKJ\fE\r\032,bYV,G#B9\002\006\005\035\001\"\002;\000\001\004\t\005\"\002@\000\001\0049\003bBA\0065\021\005\021QB\001\fe\026lwN^3WC2,X\rF\003r\003\037\t\t\002\003\004u\003\023\001\r!\021\005\007}\006%\001\031A\024\t\017\005U!\004\"\005\002\030\005\001\022N\0348feJ+Wn\034<f-\006dW/\032\013\006c\006e\0211\004\005\007i\006M\001\031A!\t\ry\f\031\0021\001(\021\035\tYA\007C\001\003?!2!]A\021\021\031q\030Q\004a\001O!9\021Q\005\016\005\026\005\035\022A\0034j]\0224\026\r\\;fgR\031\001%!\013\t\rQ\f\031\0031\001B\021\035\tiC\007C\t\003_\tq\"\0338oKJ4\025N\0343WC2,Xm\035\013\004A\005E\002B\002;\002,\001\007\021\tC\004\0026i!)\"a\016\002#\031Lg\016Z*vE.+\027p]#yG\026\004H\017\006\004\002:\005m\022Q\b\t\004C\021\n\005B\002;\0024\001\007\021\t\003\005\002@\005M\002\031AA!\003\031)\007pY3qiB)\0211IA*;:!\021QIA(\035\021\t9%!\024\016\005\005%#bAA&'\0051AH]8pizJ\021AD\005\004\003#j\021a\0029bG.\fw-Z\005\0049\006U#bAA)\033!9\021\021\f\016\005\022\005m\023\001E5o]\026\024h)\0338e'V\0247*Z=t)\031\tI$!\030\002`!1A/a\026A\002\005C\001\"a\020\002X\001\007\021\021\t\005\b\003GRB\021IA3\003!!xn\025;sS:<GCAA4!\r\t\023\021N\005\004\003W2#AB*ue&tw\rC\004\002pi!I!!\035\002\023%tG/Z4sCR,GcA9\002t!9\021QOA7\001\004i\026!\0018\t\017\005e$\004\"\003\002|\005\tR.\032:hK\016C\027M\\4fg\nK8*Z=\025\007E\fi\bC\004\002\000\005]\004\031A9\002\017\rD\027M\\4fgB\031\001&a!\005\013\r+\"\031A\026\021\007!\n9\tB\003++\t\0071\006C\005k+\t\025\r\021\"\021\002\fV\t\021\004\003\005n+\t\005\t\025!\003\032\021%!XC!b\001\n\003\t\t*\006\002\002\002\"Q\021QS\013\003\002\003\006I!!!\002\t-,\027\020\t\005\013\0033+\"\021!Q\001\n\005m\025aB0wC2,Xm\035\t\005C\021\n)\tC\005>+\t\005\t\025a\003\002 B!qaPAA\021\031\021R\003\"\001\002$RA\021QUAW\003_\013\t\f\006\003\002(\006-\006cBAU+\005\005\025QQ\007\002\021!9Q(!)A\004\005}\005B\0026\002\"\002\007\021\004C\004u\003C\003\r!!!\t\021\005e\025\021\025a\001\0037Cq!!\001\026\t\003\n)\f\006\004\0028\006m\026Q\030\t\004\003sSU\"A\013\t\017Q\f\031\f1\001\002\002\"9a0a-A\002\005\025\005B\002>\026\t\023\t\t\r\006\003\0028\006\r\007b\002@\002@\002\007\021Q\021\005\b\003+)B\021IAd)\031\t9,!3\002L\"9A/!2A\002\005\005\005b\002@\002F\002\007\021Q\021\005\b\003\027)B\021IAh)\021\t9,!5\t\017y\fi\r1\001\002\006\"9\021QF\013\005B\005UG\003BAN\003/Dq\001^Aj\001\004\t\t\tC\004\002dU!\t%!\032\t\021\005u\007\002\"\001\t\003?\fQ\"Z7qiflUM]4f\033\006\004XCBAq\003W\f\t0\006\002\002dB9\021%!:\002j\0065\030bAAtM\t\031Q*\0319\021\007!\nY\017\002\004D\0037\024\ra\013\t\005C\021\ny\017E\002)\003c$aAKAn\005\004Y\003\002CA{\021\001\006I!a>\002+%tG/\032:oC2,U\016\035;z\033\026\024x-Z'baB1Q*!?\f\003wL1!a:O!\r\tCe\003")
/*     */ public class SubclassifiedIndex<K, V> {
/*     */   private Set<V> values;
/*     */   
/*     */   public final Subclassification<K> akka$util$SubclassifiedIndex$$sc;
/*     */   
/*     */   public static class Nonroot<K, V> extends SubclassifiedIndex<K, V> {
/*     */     private final SubclassifiedIndex<K, V> root;
/*     */     
/*     */     private final K key;
/*     */     
/*     */     private final Subclassification<K> sc;
/*     */     
/*     */     public SubclassifiedIndex<K, V> root() {
/*  24 */       return this.root;
/*     */     }
/*     */     
/*     */     public K key() {
/*  24 */       return this.key;
/*     */     }
/*     */     
/*     */     public Nonroot(SubclassifiedIndex<K, V> root, Object key, Set<V> _values, Subclassification<K> sc) {
/*  24 */       super(_values, sc);
/*     */     }
/*     */     
/*     */     public Seq<Tuple2<K, Set<V>>> innerAddValue(Object key, Object value) {
/*  28 */       return this.sc.isEqual((K)key, key()) ? akka$util$SubclassifiedIndex$Nonroot$$addValue((V)value) : super.innerAddValue((K)key, (V)value);
/*     */     }
/*     */     
/*     */     public Seq<Tuple2<K, Set<V>>> akka$util$SubclassifiedIndex$Nonroot$$addValue(Object value) {
/*  32 */       Vector kids = (Vector)subkeys().flatMap((Function1)new SubclassifiedIndex$Nonroot$$anonfun$1(this, (Nonroot<K, V>)value), Vector$.MODULE$.canBuildFrom());
/*  34 */       values_$eq((Set<V>)values().$plus(value));
/*  36 */       return values().contains(value) ? (Seq<Tuple2<K, Set<V>>>)kids : (Seq<Tuple2<K, Set<V>>>)kids.$colon$plus(new Tuple2(key(), Predef$.MODULE$.Set().apply((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { value }))), Vector$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public class SubclassifiedIndex$Nonroot$$anonfun$1 extends AbstractFunction1<Nonroot<K, V>, Seq<Tuple2<K, Set<V>>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object value$3;
/*     */       
/*     */       public final Seq<Tuple2<K, Set<V>>> apply(SubclassifiedIndex.Nonroot<K, V> x$1) {
/*     */         return x$1.akka$util$SubclassifiedIndex$Nonroot$$addValue((V)this.value$3);
/*     */       }
/*     */       
/*     */       public SubclassifiedIndex$Nonroot$$anonfun$1(SubclassifiedIndex.Nonroot $outer, Object value$3) {}
/*     */     }
/*     */     
/*     */     public Seq<Tuple2<K, Set<V>>> innerRemoveValue(Object key, Object value) {
/*  42 */       return this.sc.isEqual((K)key, key()) ? removeValue((V)value) : super.innerRemoveValue((K)key, (V)value);
/*     */     }
/*     */     
/*     */     public Seq<Tuple2<K, Set<V>>> removeValue(Object value) {
/*  46 */       Vector kids = (Vector)subkeys().flatMap((Function1)new SubclassifiedIndex$Nonroot$$anonfun$2(this, (Nonroot<K, V>)value), Vector$.MODULE$.canBuildFrom());
/*  48 */       values_$eq((Set<V>)values().$minus(value));
/*  49 */       return values().contains(value) ? (Seq<Tuple2<K, Set<V>>>)kids.$colon$plus(new Tuple2(key(), Predef$.MODULE$.Set().apply((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { value }))), Vector$.MODULE$.canBuildFrom()) : 
/*  50 */         (Seq<Tuple2<K, Set<V>>>)kids;
/*     */     }
/*     */     
/*     */     public class SubclassifiedIndex$Nonroot$$anonfun$2 extends AbstractFunction1<Nonroot<K, V>, Seq<Tuple2<K, Set<V>>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object value$5;
/*     */       
/*     */       public final Seq<Tuple2<K, Set<V>>> apply(SubclassifiedIndex.Nonroot<K, V> x$2) {
/*     */         return x$2.removeValue((V)this.value$5);
/*     */       }
/*     */       
/*     */       public SubclassifiedIndex$Nonroot$$anonfun$2(SubclassifiedIndex.Nonroot $outer, Object value$5) {}
/*     */     }
/*     */     
/*     */     public Set<V> innerFindValues(Object key) {
/*  54 */       return this.sc.isEqual((K)key, key()) ? values() : super.innerFindValues((K)key);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  56 */       return subkeys().mkString((new StringBuilder()).append("Nonroot(").append(key()).append(", ").append(values()).append(",\n").toString(), ",\n", ")");
/*     */     }
/*     */   }
/*     */   
/*     */   public Set<V> values() {
/*  75 */     return this.values;
/*     */   }
/*     */   
/*     */   public void values_$eq(Set<V> x$1) {
/*  75 */     this.values = x$1;
/*     */   }
/*     */   
/*  81 */   private Vector<Nonroot<K, V>> subkeys = package$.MODULE$.Vector().empty();
/*     */   
/*     */   public Vector<Nonroot<K, V>> subkeys() {
/*  81 */     return this.subkeys;
/*     */   }
/*     */   
/*     */   public void subkeys_$eq(Vector<Nonroot<K, V>> x$1) {
/*  81 */     this.subkeys = x$1;
/*     */   }
/*     */   
/*     */   public SubclassifiedIndex(Subclassification<K> sc) {
/*  83 */     this(Predef$.MODULE$.Set().empty(), sc);
/*     */   }
/*     */   
/*  85 */   private final SubclassifiedIndex<K, V> root = this;
/*     */   
/*     */   public SubclassifiedIndex<K, V> root() {
/*  85 */     return this.root;
/*     */   }
/*     */   
/*     */   public Seq<Tuple2<K, Set<V>>> addKey(Object key) {
/*  93 */     return mergeChangesByKey(innerAddKey((K)key));
/*     */   }
/*     */   
/*     */   public Seq<Tuple2<K, Set<V>>> innerAddKey(Object key) {
/*  96 */     BooleanRef found = new BooleanRef(false);
/*  97 */     Vector ch = (Vector)subkeys().flatMap((Function1)new SubclassifiedIndex$$anonfun$3(this, key, (SubclassifiedIndex<K, V>)found), Vector$.MODULE$.canBuildFrom());
/* 106 */     return found.elem ? 
/*     */       
/* 108 */       (Seq<Tuple2<K, Set<V>>>)ch : (Seq<Tuple2<K, Set<V>>>)integrate(new Nonroot<K, V>(root(), (K)key, values(), this.akka$util$SubclassifiedIndex$$sc)).$colon$plus(new Tuple2(key, values()), Seq$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$3 extends AbstractFunction1<Nonroot<K, V>, Seq<Tuple2<K, Set<V>>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object key$4;
/*     */     
/*     */     private final BooleanRef found$1;
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$3(SubclassifiedIndex $outer, Object key$4, BooleanRef found$1) {}
/*     */     
/*     */     public final Seq<Tuple2<K, Set<V>>> apply(SubclassifiedIndex.Nonroot<K, V> n) {
/*     */       this.found$1.elem = true;
/*     */       this.found$1.elem = true;
/*     */       return this.$outer.akka$util$SubclassifiedIndex$$sc.isEqual(this.key$4, n.key()) ? (Seq<Tuple2<K, Set<V>>>)Nil$.MODULE$ : (this.$outer.akka$util$SubclassifiedIndex$$sc.isSubclass(this.key$4, n.key()) ? n.innerAddKey((K)this.key$4) : (Seq<Tuple2<K, Set<V>>>)Nil$.MODULE$);
/*     */     }
/*     */   }
/*     */   
/*     */   public Seq<Tuple2<K, Set<V>>> addValue(Object key, Object value) {
/* 117 */     return mergeChangesByKey(innerAddValue((K)key, (V)value));
/*     */   }
/*     */   
/*     */   public Seq<Tuple2<K, Set<V>>> innerAddValue(Object key, Object value) {
/* 120 */     BooleanRef found = new BooleanRef(false);
/* 121 */     Vector ch = (Vector)subkeys().flatMap((Function1)new SubclassifiedIndex$$anonfun$4(this, key, value, (SubclassifiedIndex<K, V>)found), Vector$.MODULE$.canBuildFrom());
/* 128 */     Set<V> v = (Set)values().$plus(value);
/* 129 */     Nonroot<K, V> n = new Nonroot<K, V>(root(), (K)key, v, this.akka$util$SubclassifiedIndex$$sc);
/* 131 */     return found.elem ? (Seq<Tuple2<K, Set<V>>>)ch : (Seq<Tuple2<K, Set<V>>>)((SeqLike)integrate(n).$plus$plus((GenTraversableOnce)n.innerAddValue((K)key, (V)value), Seq$.MODULE$.canBuildFrom())).$colon$plus(Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.any2ArrowAssoc(key), v), Seq$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$4 extends AbstractFunction1<Nonroot<K, V>, Seq<Tuple2<K, Set<V>>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object key$1;
/*     */     
/*     */     private final Object value$2;
/*     */     
/*     */     private final BooleanRef found$2;
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$4(SubclassifiedIndex $outer, Object key$1, Object value$2, BooleanRef found$2) {}
/*     */     
/*     */     public final Seq<Tuple2<K, Set<V>>> apply(SubclassifiedIndex.Nonroot<K, V> n) {
/*     */       this.found$2.elem = true;
/*     */       return this.$outer.akka$util$SubclassifiedIndex$$sc.isSubclass(this.key$1, n.key()) ? n.innerAddValue((K)this.key$1, (V)this.value$2) : (Seq<Tuple2<K, Set<V>>>)Nil$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public Seq<Tuple2<K, Set<V>>> removeValue(Object key, Object value) {
/* 143 */     return (Seq<Tuple2<K, Set<V>>>)mergeChangesByKey(innerRemoveValue((K)key, (V)value)).map((Function1)new SubclassifiedIndex$$anonfun$removeValue$1(this), Seq$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$removeValue$1 extends AbstractFunction1<Tuple2<K, Set<V>>, Tuple2<K, Set<V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<K, Set<V>> apply(Tuple2 x0$1) {
/* 143 */       Tuple2 tuple2 = x0$1;
/* 143 */       if (tuple2 != null) {
/* 144 */         Object k = tuple2._1();
/* 144 */         return new Tuple2(k, this.$outer.findValues(k));
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$removeValue$1(SubclassifiedIndex $outer) {}
/*     */   }
/*     */   
/*     */   public Seq<Tuple2<K, Set<V>>> innerRemoveValue(Object key, Object value) {
/* 149 */     BooleanRef found = new BooleanRef(false);
/* 150 */     Vector ch = (Vector)subkeys().flatMap((Function1)new SubclassifiedIndex$$anonfun$5(this, key, value, (SubclassifiedIndex<K, V>)found), Vector$.MODULE$.canBuildFrom());
/* 157 */     Nonroot<K, V> n = new Nonroot<K, V>(root(), (K)key, values(), this.akka$util$SubclassifiedIndex$$sc);
/* 159 */     return found.elem ? (Seq<Tuple2<K, Set<V>>>)ch : (Seq<Tuple2<K, Set<V>>>)integrate(n).$plus$plus((GenTraversableOnce)n.removeValue((V)value), Seq$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$5 extends AbstractFunction1<Nonroot<K, V>, Seq<Tuple2<K, Set<V>>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object key$2;
/*     */     
/*     */     private final Object value$4;
/*     */     
/*     */     private final BooleanRef found$3;
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$5(SubclassifiedIndex $outer, Object key$2, Object value$4, BooleanRef found$3) {}
/*     */     
/*     */     public final Seq<Tuple2<K, Set<V>>> apply(SubclassifiedIndex.Nonroot<K, V> n) {
/*     */       this.found$3.elem = true;
/*     */       return this.$outer.akka$util$SubclassifiedIndex$$sc.isSubclass(this.key$2, n.key()) ? n.innerRemoveValue((K)this.key$2, (V)this.value$4) : (Seq<Tuple2<K, Set<V>>>)Nil$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public Seq<Tuple2<K, Set<V>>> removeValue(Object value) {
/* 167 */     return mergeChangesByKey((Seq<Tuple2<K, Set<V>>>)subkeys().flatMap((Function1)new SubclassifiedIndex$$anonfun$removeValue$2(this, (SubclassifiedIndex<K, V>)value), Vector$.MODULE$.canBuildFrom()));
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$removeValue$2 extends AbstractFunction1<Nonroot<K, V>, Seq<Tuple2<K, Set<V>>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object value$1;
/*     */     
/*     */     public final Seq<Tuple2<K, Set<V>>> apply(SubclassifiedIndex.Nonroot<K, V> x$3) {
/* 167 */       return x$3.removeValue((V)this.value$1);
/*     */     }
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$removeValue$2(SubclassifiedIndex $outer, Object value$1) {}
/*     */   }
/*     */   
/*     */   public final Set<V> findValues(Object key) {
/* 172 */     return root().innerFindValues((K)key);
/*     */   }
/*     */   
/*     */   public Set<V> innerFindValues(Object key) {
/* 174 */     Set set = Predef$.MODULE$.Set().empty();
/* 174 */     return (Set<V>)subkeys().$div$colon(set, (Function2)new SubclassifiedIndex$$anonfun$innerFindValues$1(this, (SubclassifiedIndex<K, V>)key));
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$innerFindValues$1 extends AbstractFunction2<Set<V>, Nonroot<K, V>, Set<V>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object key$3;
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$innerFindValues$1(SubclassifiedIndex $outer, Object key$3) {}
/*     */     
/*     */     public final Set<V> apply(Set<V> s, SubclassifiedIndex.Nonroot n) {
/* 175 */       return this.$outer.akka$util$SubclassifiedIndex$$sc.isSubclass(this.key$3, n.key()) ? 
/* 176 */         (Set<V>)s.$plus$plus((GenTraversableOnce)n.innerFindValues(this.key$3)) : 
/*     */         
/* 178 */         s;
/*     */     }
/*     */   }
/*     */   
/*     */   public final Set<K> findSubKeysExcept(Object key, Vector<Nonroot<K, V>> except) {
/* 184 */     return root().innerFindSubKeys((K)key, except);
/*     */   }
/*     */   
/*     */   public Set<K> innerFindSubKeys(Object key, Vector except) {
/* 186 */     Set set = Predef$.MODULE$.Set().empty();
/* 186 */     return (Set<K>)subkeys().$div$colon(set, (Function2)new SubclassifiedIndex$$anonfun$innerFindSubKeys$1(this, key, (SubclassifiedIndex<K, V>)except));
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$innerFindSubKeys$1 extends AbstractFunction2<Set<K>, Nonroot<K, V>, Set<K>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Object key$5;
/*     */     
/*     */     private final Vector except$1;
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$innerFindSubKeys$1(SubclassifiedIndex $outer, Object key$5, Vector except$1) {}
/*     */     
/*     */     public final Set<K> apply(Set<K> s, SubclassifiedIndex.Nonroot n) {
/* 187 */       return this.$outer.akka$util$SubclassifiedIndex$$sc.isEqual(this.key$5, n.key()) ? s : 
/* 188 */         (Set<K>)n.innerFindSubKeys(this.key$5, this.except$1).$plus$plus((
/* 189 */           this.$outer.akka$util$SubclassifiedIndex$$sc.isSubclass(n.key(), this.key$5) && !this.except$1.exists((Function1)new SubclassifiedIndex$$anonfun$innerFindSubKeys$1$$anonfun$apply$1(this))) ? 
/* 190 */           (GenTraversableOnce)s.$plus(n.key()) : 
/*     */           
/* 192 */           (GenTraversableOnce)s);
/*     */     }
/*     */     
/*     */     public class SubclassifiedIndex$$anonfun$innerFindSubKeys$1$$anonfun$apply$1 extends AbstractFunction1<SubclassifiedIndex.Nonroot<K, V>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(SubclassifiedIndex.Nonroot e) {
/*     */         return (this.$outer.akka$util$SubclassifiedIndex$$anonfun$$$outer()).akka$util$SubclassifiedIndex$$sc.isEqual(this.$outer.key$5, e.key());
/*     */       }
/*     */       
/*     */       public SubclassifiedIndex$$anonfun$innerFindSubKeys$1$$anonfun$apply$1(SubclassifiedIndex$$anonfun$innerFindSubKeys$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString() {
/* 196 */     return subkeys().mkString((new StringBuilder()).append("SubclassifiedIndex(").append(values()).append(",\n").toString(), ",\n", ")");
/*     */   }
/*     */   
/*     */   private Seq<Tuple2<K, Set<V>>> integrate(Nonroot<K, V> n) {
/* 203 */     Tuple2 tuple2 = subkeys().partition((Function1)new $anonfun$6(this, n));
/* 203 */     if (tuple2 != null) {
/* 203 */       Vector subsub = (Vector)tuple2._1(), sub = (Vector)tuple2._2();
/* 203 */       Tuple2 tuple22 = new Tuple2(subsub, sub), tuple21 = tuple22;
/* 203 */       Vector<Nonroot<K, V>> vector = (Vector)tuple21._1();
/* 203 */       Vector vector1 = (Vector)tuple21._2();
/* 204 */       subkeys_$eq((Vector<Nonroot<K, V>>)vector1.$colon$plus(n, Vector$.MODULE$.canBuildFrom()));
/* 205 */       n.subkeys_$eq(vector.nonEmpty() ? vector : n.subkeys());
/* 206 */       n.subkeys_$eq((Vector<Nonroot<K, V>>)n.subkeys().$plus$plus((GenTraversableOnce)findSubKeysExcept(n.key(), n.subkeys()).map((Function1)new SubclassifiedIndex$$anonfun$integrate$1(this), Set$.MODULE$.canBuildFrom()), Vector$.MODULE$.canBuildFrom()));
/* 207 */       return (Seq<Tuple2<K, Set<V>>>)n.subkeys().map((Function1)new SubclassifiedIndex$$anonfun$integrate$2(this), Vector$.MODULE$.canBuildFrom());
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public class $anonfun$6 extends AbstractFunction1<Nonroot<K, V>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final SubclassifiedIndex.Nonroot n$1;
/*     */     
/*     */     public final boolean apply(SubclassifiedIndex.Nonroot k) {
/*     */       return this.$outer.akka$util$SubclassifiedIndex$$sc.isSubclass(k.key(), this.n$1.key());
/*     */     }
/*     */     
/*     */     public $anonfun$6(SubclassifiedIndex $outer, SubclassifiedIndex.Nonroot n$1) {}
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$integrate$1 extends AbstractFunction1<K, Nonroot<K, V>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final SubclassifiedIndex.Nonroot<K, V> apply(Object k) {
/*     */       return new SubclassifiedIndex.Nonroot<K, V>(this.$outer.root(), (K)k, this.$outer.values(), this.$outer.akka$util$SubclassifiedIndex$$sc);
/*     */     }
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$integrate$1(SubclassifiedIndex $outer) {}
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$integrate$2 extends AbstractFunction1<Nonroot<K, V>, Tuple2<K, Set<V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<K, Set<V>> apply(SubclassifiedIndex.Nonroot n) {
/* 207 */       return new Tuple2(n.key(), n.values().toSet());
/*     */     }
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$integrate$2(SubclassifiedIndex $outer) {}
/*     */   }
/*     */   
/*     */   private Seq<Tuple2<K, Set<V>>> mergeChangesByKey(Seq changes) {
/* 211 */     Map<?, Set<?>> map = SubclassifiedIndex$.MODULE$.emptyMergeMap();
/* 211 */     return (Seq<Tuple2<K, Set<V>>>)((TraversableLike)changes.$div$colon(map, (Function2)new SubclassifiedIndex$$anonfun$mergeChangesByKey$1(this)))
/*     */       
/* 213 */       .to(Predef$.MODULE$.fallbackStringCanBuildFrom());
/*     */   }
/*     */   
/*     */   public SubclassifiedIndex(Set<V> values, Subclassification<K> sc) {}
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$mergeChangesByKey$1 extends AbstractFunction2<Map<K, Set<V>>, Tuple2<K, Set<V>>, Map<K, Set<V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Map<K, Set<V>> apply(Map x0$2, Tuple2 x1$1) {
/*     */       Tuple2 tuple2 = new Tuple2(x0$2, x1$1);
/*     */       if (tuple2 != null) {
/*     */         Map m = (Map)tuple2._1();
/*     */         Tuple2 tuple21 = (Tuple2)tuple2._2();
/*     */         if (tuple21 != null) {
/*     */           Object k = tuple21._1();
/*     */           Set s = (Set)tuple21._2();
/*     */           return m.updated(k, ((SetLike)m.apply(k)).$plus$plus((GenTraversableOnce)s));
/*     */         } 
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$mergeChangesByKey$1(SubclassifiedIndex $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\SubclassifiedIndex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */