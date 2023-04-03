/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.IndexedSeqOptimized;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.mutable.ArrayOps;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Numeric$IntIsIntegral$;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%eAB\001\003\003\003!\001B\001\007Ue&,\027\n^3sCR|'O\003\002\004\t\005I\021.\\7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\fWCA\005\021'\t\001!\002E\002\f\0319i\021\001B\005\003\033\021\021\001#\0212tiJ\f7\r^%uKJ\fGo\034:\021\005=\001B\002\001\003\007#\001!)\031A\n\003\003Q\033\001!\005\002\0251A\021QCF\007\002\r%\021qC\002\002\b\035>$\b.\0338h!\t)\022$\003\002\033\r\t\031\021I\\=\t\021q\001!\021!Q\001\nu\tQ!\0327f[N\0042!\006\020!\023\tybAA\003BeJ\f\027\020E\002\"E9i\021AA\005\003G\t\021\001\"\023;fe\006\024G.\032\005\006K\001!\tAJ\001\007y%t\027\016\036 \025\005\035B\003cA\021\001\035!)A\004\na\001;!1!\006\001D\001\005-\nqaZ3u\0132,W\016\006\002\017Y!)Q&\013a\001]\005\t\001\020\005\002\026_%\021\001G\002\002\007\003:L(+\0324\t\013I\002A\021A\032\002\023%t\027\016\036#faRDW#\001\033\021\005U)\024B\001\034\007\005\rIe\016\036\005\006q\001!\t!O\001\017S:LG/\021:sCf\034F/Y2l+\005Q\004cA\013\037wA\031QC\b\037\021\007\005\022SH\013\002\017}-\nq\b\005\002A\0136\t\021I\003\002C\007\006IQO\\2iK\016\\W\r\032\006\003\t\032\t!\"\0318o_R\fG/[8o\023\t1\025IA\tv]\016DWmY6fIZ\013'/[1oG\026DQ\001\023\001\005\002%\013A\"\0338jiB{7o\025;bG.,\022A\023\t\004+y!\004\"\002'\001\t\003i\025AC5oSR\f%O]1z\tV\t1\bC\003P\001\021\0051'\001\005j]&$\bk\\:E\021\025\t\006\001\"\001S\003-Ig.\033;Tk\nLE/\032:\026\003M\0032a\003+\017\023\t)FA\001\005Ji\026\024\030\r^8s\021\0319\006\001)Q\005i\005)A-\0329uQ\"1\021\f\001Q!\ni\n!\"\031:sCf\034F/Y2l\021\031Y\006\001)Q\005\025\006A\001o\\:Ti\006\0347\016\003\004^\001\001\006KaO\001\007CJ\024\030-\037#\t\r}\003\001\025)\0035\003\021\001xn\035#\t\r\005\004\001\025)\003T\003\035\031XOY%uKJDaa\031\001!\n\023!\027\001C4fi\026cW-\\:\025\005u)\007\"B\027c\001\004\001\003BB4\001A\023%\001.\001\td_2d\027n]5p]R{\027I\035:bsR\021Q$\033\005\006[\031\004\r\001I\003\007W\002\001\013\021\0027\003\035M\003H.\033;Ji\026\024\030\r^8sgB!Q#\\8T\023\tqgA\001\004UkBdWM\r\t\005+5\034F\007C\003r\001\021%!/\001\004jgR\023\030.\032\013\003gZ\004\"!\006;\n\005U4!a\002\"p_2,\027M\034\005\006[A\004\rA\f\005\006q\002!I!_\001\fSN\034uN\034;bS:,'\017\006\002tu\")Qf\036a\001]\031!A\020\001\002~\005-!U\017]%uKJ\fGo\034:\024\005m<\003b\002\032|\005\004%\te\r\005\013\003\003YH\021!A!\002\023!\024AC5oSR$U\r\035;iA!9\001h\037b\001\n\003J\004BCA\004w\022\005\t\021)A\005u\005y\021N\\5u\003J\024\030-_*uC\016\\\007\005C\004Iw\n\007I\021I%\t\025\00551\020\"A\001B\003%!*A\007j]&$\bk\\:Ti\006\0347\016\t\005\b\031n\024\r\021\"\021N\021)\t\031b\037C\001\002\003\006IaO\001\fS:LG/\021:sCf$\005\005C\004Pw\n\007I\021I\032\t\025\005e1\020\"A\001B\003%A'A\005j]&$\bk\\:EA!9\021k\037b\001\n\003\022\006BCA\020w\022\005\t\021)A\005'\006a\021N\\5u'V\024\027\n^3sA!I\0211E>\003\002\003\006I!H\001\003qNDa!J>\005\002\005\035B\003BA\025\003[\0012!a\013|\033\005\001\001bBA\022\003K\001\r!\b\005\007Um$)%!\r\025\0079\t\031\004\003\004.\003_\001\rA\f\005\b\003o\001A\021AA\035\003-!W\017]%uKJ\fGo\034:\026\003\035B\001\"!\020\001A\023%\021qH\001\f]\026<\030\n^3sCR|'\017F\002(\003\003Bq!a\t\002<\001\007Q\004\003\005\002F\001\001K\021BA$\003AIG/\032:bi>\024x+\033;i'&TX\rF\002p\003\023Bq!a\023\002D\001\007Q$A\002beJD\001\"a\024\001A\023%\021\021K\001\021CJ\024\030-\037+p\023R,'/\031;peN$B!a\025\002VA\031\0211\0066\t\017\005-\023Q\na\001;!A\021\021\f\001!\n\023\tY&\001\006ta2LG/\021:sCf$B!a\025\002^!9\021qLA,\001\004i\022AA1e\021\035\t\031\007\001C\001\003K\nq\001[1t\035\026DH/F\001t\021\035\tI\007\001C\001\003W\nAA\\3yiR\ta\002\003\005\002p\001\001K\021BA9\003\025qW\r\037;1)\025q\0211OA;\021\031a\022Q\016a\001;!9\021qOA7\001\004!\024!A5)\t\0055\0241\020\t\005\003{\ny(D\001D\023\r\t\ti\021\002\bi\006LGN]3d\021\035\t)\t\001C\001\003\017\013Qa\0359mSR,\"!a\025")
/*     */ public abstract class TrieIterator<T> extends AbstractIterator<T> {
/*     */   private final Iterable<T>[] elems;
/*     */   
/*     */   public abstract T getElem(Object paramObject);
/*     */   
/*     */   public int initDepth() {
/*  25 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<T>[][] initArrayStack() {
/*  26 */     return (Iterable<T>[][])new Iterable[6][];
/*     */   }
/*     */   
/*     */   public int[] initPosStack() {
/*  27 */     return new int[6];
/*     */   }
/*     */   
/*     */   public Iterable<T>[] initArrayD() {
/*  28 */     return this.elems;
/*     */   }
/*     */   
/*     */   public int initPosD() {
/*  29 */     return 0;
/*     */   }
/*     */   
/*  32 */   public int scala$collection$immutable$TrieIterator$$depth = initDepth();
/*     */   
/*  33 */   public Iterable<T>[][] scala$collection$immutable$TrieIterator$$arrayStack = initArrayStack();
/*     */   
/*  34 */   public int[] scala$collection$immutable$TrieIterator$$posStack = initPosStack();
/*     */   
/*  35 */   public Iterable<T>[] scala$collection$immutable$TrieIterator$$arrayD = initArrayD();
/*     */   
/*  36 */   public int scala$collection$immutable$TrieIterator$$posD = initPosD();
/*     */   
/*  37 */   public Iterator<T> scala$collection$immutable$TrieIterator$$subIter = initSubIter();
/*     */   
/*     */   public Iterator<T> initSubIter() {
/*     */     return null;
/*     */   }
/*     */   
/*     */   private Iterable<T>[] getElems(Iterable x) {
/*     */     AbstractIterable[] arrayOfAbstractIterable;
/*  39 */     if (x instanceof HashMap.HashTrieMap) {
/*  39 */       HashMap.HashTrieMap hashTrieMap = (HashMap.HashTrieMap)x;
/*  39 */       arrayOfAbstractIterable = (AbstractIterable[])hashTrieMap.elems();
/*     */     } else {
/*  41 */       if (x instanceof HashSet.HashTrieSet) {
/*  41 */         HashSet.HashTrieSet hashTrieSet = (HashSet.HashTrieSet)x;
/*  41 */         arrayOfAbstractIterable = (AbstractIterable[])hashTrieSet.elems();
/*     */         return (Iterable<T>[])arrayOfAbstractIterable;
/*     */       } 
/*     */       throw new MatchError(x);
/*     */     } 
/*     */     return (Iterable<T>[])arrayOfAbstractIterable;
/*     */   }
/*     */   
/*     */   private Iterable<T>[] collisionToArray(Iterable x) {
/*     */     AbstractIterable[] arrayOfAbstractIterable;
/*  44 */     if (x instanceof HashMap.HashMapCollision1) {
/*  44 */       HashMap.HashMapCollision1 hashMapCollision1 = (HashMap.HashMapCollision1)x;
/*  44 */       arrayOfAbstractIterable = (AbstractIterable[])((TraversableOnce)hashMapCollision1.kvs().map((Function1)new TrieIterator$$anonfun$collisionToArray$1(this), Iterable$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.apply(HashMap.class));
/*     */     } else {
/*  46 */       if (x instanceof HashSet.HashSetCollision1) {
/*  46 */         HashSet.HashSetCollision1 hashSetCollision1 = (HashSet.HashSetCollision1)x;
/*  46 */         arrayOfAbstractIterable = (AbstractIterable[])((TraversableOnce)hashSetCollision1.ks().map((Function1)new TrieIterator$$anonfun$collisionToArray$2(this), ListSet$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.apply(HashSet.class));
/*     */         return (Iterable<T>[])arrayOfAbstractIterable;
/*     */       } 
/*     */       throw new MatchError(x);
/*     */     } 
/*     */     return (Iterable<T>[])arrayOfAbstractIterable;
/*     */   }
/*     */   
/*     */   public class TrieIterator$$anonfun$collisionToArray$1 extends AbstractFunction1<Tuple2<Object, Object>, HashMap<Object, Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final HashMap<Object, Object> apply(Tuple2 x) {
/*     */       (new Tuple2[1])[0] = x;
/*     */       return (HashMap<Object, Object>)HashMap$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]));
/*     */     }
/*     */     
/*     */     public TrieIterator$$anonfun$collisionToArray$1(TrieIterator $outer) {}
/*     */   }
/*     */   
/*     */   public class TrieIterator$$anonfun$collisionToArray$2 extends AbstractFunction1<Object, HashSet<Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final HashSet<Object> apply(Object x) {
/*  46 */       return (HashSet<Object>)HashSet$.MODULE$.apply((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { x }));
/*     */     }
/*     */     
/*     */     public TrieIterator$$anonfun$collisionToArray$2(TrieIterator $outer) {}
/*     */   }
/*     */   
/*     */   private boolean isTrie(Object x) {
/*     */     boolean bool1;
/*     */     boolean bool2;
/*  51 */     if (x instanceof HashMap.HashTrieMap) {
/*  51 */       bool1 = true;
/*  51 */     } else if (x instanceof HashSet.HashTrieSet) {
/*  51 */       bool1 = true;
/*     */     } else {
/*  51 */       bool1 = false;
/*     */     } 
/*  51 */     if (bool1) {
/*  51 */       bool2 = true;
/*     */     } else {
/*  53 */       bool2 = false;
/*     */     } 
/*     */     return bool2;
/*     */   }
/*     */   
/*     */   private boolean isContainer(Object x) {
/*     */     boolean bool1;
/*     */     boolean bool2;
/*  55 */     if (x instanceof HashMap.HashMap1) {
/*  55 */       bool1 = true;
/*  55 */     } else if (x instanceof HashSet.HashSet1) {
/*  55 */       bool1 = true;
/*     */     } else {
/*  55 */       bool1 = false;
/*     */     } 
/*  55 */     if (bool1) {
/*  55 */       bool2 = true;
/*     */     } else {
/*  57 */       bool2 = false;
/*     */     } 
/*     */     return bool2;
/*     */   }
/*     */   
/*     */   public class DupIterator extends TrieIterator<T> {
/*     */     private final int initDepth;
/*     */     
/*     */     private final Iterable<T>[][] initArrayStack;
/*     */     
/*     */     private final int[] initPosStack;
/*     */     
/*     */     private final Iterable<T>[] initArrayD;
/*     */     
/*     */     private final int initPosD;
/*     */     
/*     */     private final Iterator<T> initSubIter;
/*     */     
/*     */     public DupIterator(TrieIterator $outer, Iterable[] xs) {
/*  60 */       super(
/*     */           
/*  67 */           (Iterable<T>[])xs);
/*     */     }
/*     */     
/*     */     public int initDepth() {
/*     */       return this.initDepth;
/*     */     }
/*     */     
/*     */     public Iterable<T>[][] initArrayStack() {
/*     */       return this.initArrayStack;
/*     */     }
/*     */     
/*     */     public int[] initPosStack() {
/*     */       return this.initPosStack;
/*     */     }
/*     */     
/*     */     public Iterable<T>[] initArrayD() {
/*     */       return this.initArrayD;
/*     */     }
/*     */     
/*     */     public int initPosD() {
/*     */       return this.initPosD;
/*     */     }
/*     */     
/*     */     public Iterator<T> initSubIter() {
/*     */       return this.initSubIter;
/*     */     }
/*     */     
/*     */     public final T getElem(Object x) {
/*  68 */       return this.$outer.getElem(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public TrieIterator<T> dupIterator() {
/*  71 */     return new DupIterator(this, this.elems);
/*     */   }
/*     */   
/*     */   private TrieIterator<T> newIterator(Iterable[] xs) {
/*  73 */     return new TrieIterator$$anon$1(this, (TrieIterator<T>)xs);
/*     */   }
/*     */   
/*     */   public class TrieIterator$$anon$1 extends TrieIterator<T> {
/*     */     public TrieIterator$$anon$1(TrieIterator $outer, Iterable[] xs$1) {
/*  73 */       super((Iterable<T>[])xs$1);
/*     */     }
/*     */     
/*     */     public final T getElem(Object x) {
/*  74 */       return this.$outer.getElem(x);
/*     */     }
/*     */   }
/*     */   
/*     */   private Tuple2<Iterator<T>, Object> iteratorWithSize(Iterable[] arr) {
/*  78 */     return new Tuple2(newIterator((Iterable<T>[])arr), Predef$.MODULE$.intArrayOps((int[])Predef$.MODULE$.refArrayOps((Object[])arr).map((Function1)new TrieIterator$$anonfun$iteratorWithSize$1(this), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.Int()))).sum((Numeric)Numeric$IntIsIntegral$.MODULE$));
/*     */   }
/*     */   
/*     */   public class TrieIterator$$anonfun$iteratorWithSize$1 extends AbstractFunction1<Iterable<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(Iterable x$1) {
/*  78 */       return x$1.size();
/*     */     }
/*     */     
/*     */     public TrieIterator$$anonfun$iteratorWithSize$1(TrieIterator $outer) {}
/*     */   }
/*     */   
/*     */   private Tuple2<Tuple2<Iterator<T>, Object>, Iterator<T>> arrayToIterators(Iterable[] arr) {
/*  81 */     Tuple2 tuple2 = Predef$.MODULE$.refArrayOps((Object[])arr).splitAt(arr.length / 2);
/*  81 */     if (tuple2 != null) {
/*  81 */       Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/*  81 */       Iterable[] fst = (Iterable[])tuple21._1(), snd = (Iterable[])tuple21._2();
/*  83 */       return new Tuple2(iteratorWithSize((Iterable<T>[])snd), newIterator((Iterable<T>[])fst));
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   private Tuple2<Tuple2<Iterator<T>, Object>, Iterator<T>> splitArray(Iterable[] ad) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: arraylength
/*     */     //   2: iconst_1
/*     */     //   3: if_icmple -> 14
/*     */     //   6: aload_0
/*     */     //   7: aload_1
/*     */     //   8: invokespecial arrayToIterators : ([Lscala/collection/immutable/Iterable;)Lscala/Tuple2;
/*     */     //   11: goto -> 59
/*     */     //   14: aload_1
/*     */     //   15: iconst_0
/*     */     //   16: aaload
/*     */     //   17: astore_2
/*     */     //   18: aload_2
/*     */     //   19: instanceof scala/collection/immutable/HashMap$HashMapCollision1
/*     */     //   22: ifeq -> 30
/*     */     //   25: iconst_1
/*     */     //   26: istore_3
/*     */     //   27: goto -> 44
/*     */     //   30: aload_2
/*     */     //   31: instanceof scala/collection/immutable/HashSet$HashSetCollision1
/*     */     //   34: ifeq -> 42
/*     */     //   37: iconst_1
/*     */     //   38: istore_3
/*     */     //   39: goto -> 44
/*     */     //   42: iconst_0
/*     */     //   43: istore_3
/*     */     //   44: iload_3
/*     */     //   45: ifeq -> 60
/*     */     //   48: aload_0
/*     */     //   49: aload_0
/*     */     //   50: aload_1
/*     */     //   51: iconst_0
/*     */     //   52: aaload
/*     */     //   53: invokespecial collisionToArray : (Lscala/collection/immutable/Iterable;)[Lscala/collection/immutable/Iterable;
/*     */     //   56: invokespecial arrayToIterators : ([Lscala/collection/immutable/Iterable;)Lscala/Tuple2;
/*     */     //   59: areturn
/*     */     //   60: aload_0
/*     */     //   61: aload_1
/*     */     //   62: iconst_0
/*     */     //   63: aaload
/*     */     //   64: invokespecial getElems : (Lscala/collection/immutable/Iterable;)[Lscala/collection/immutable/Iterable;
/*     */     //   67: astore_1
/*     */     //   68: goto -> 0
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #86	-> 0
/*     */     //   #87	-> 14
/*     */     //   #88	-> 18
/*     */     //   #89	-> 48
/*     */     //   #85	-> 59
/*     */     //   #87	-> 59
/*     */     //   #91	-> 60
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	71	0	this	Lscala/collection/immutable/TrieIterator;
/*     */     //   0	71	1	ad	[Lscala/collection/immutable/Iterable;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  94 */     return (this.scala$collection$immutable$TrieIterator$$subIter != null || this.scala$collection$immutable$TrieIterator$$depth >= 0);
/*     */   }
/*     */   
/*     */   public T next() {
/*  97 */     Object el = this.scala$collection$immutable$TrieIterator$$subIter.next();
/*  98 */     if (!this.scala$collection$immutable$TrieIterator$$subIter.hasNext())
/*  99 */       this.scala$collection$immutable$TrieIterator$$subIter = null; 
/* 100 */     return (this.scala$collection$immutable$TrieIterator$$subIter != null) ? (T)el : 
/*     */       
/* 102 */       next0(this.scala$collection$immutable$TrieIterator$$arrayD, this.scala$collection$immutable$TrieIterator$$posD);
/*     */   }
/*     */   
/*     */   private T next0(Iterable[] elems, int i) {
/*     */     // Byte code:
/*     */     //   0: iload_2
/*     */     //   1: aload_1
/*     */     //   2: arraylength
/*     */     //   3: iconst_1
/*     */     //   4: isub
/*     */     //   5: if_icmpne -> 78
/*     */     //   8: aload_0
/*     */     //   9: aload_0
/*     */     //   10: getfield scala$collection$immutable$TrieIterator$$depth : I
/*     */     //   13: iconst_1
/*     */     //   14: isub
/*     */     //   15: putfield scala$collection$immutable$TrieIterator$$depth : I
/*     */     //   18: aload_0
/*     */     //   19: getfield scala$collection$immutable$TrieIterator$$depth : I
/*     */     //   22: iconst_0
/*     */     //   23: if_icmplt -> 65
/*     */     //   26: aload_0
/*     */     //   27: aload_0
/*     */     //   28: getfield scala$collection$immutable$TrieIterator$$arrayStack : [[Lscala/collection/immutable/Iterable;
/*     */     //   31: aload_0
/*     */     //   32: getfield scala$collection$immutable$TrieIterator$$depth : I
/*     */     //   35: aaload
/*     */     //   36: putfield scala$collection$immutable$TrieIterator$$arrayD : [Lscala/collection/immutable/Iterable;
/*     */     //   39: aload_0
/*     */     //   40: aload_0
/*     */     //   41: getfield scala$collection$immutable$TrieIterator$$posStack : [I
/*     */     //   44: aload_0
/*     */     //   45: getfield scala$collection$immutable$TrieIterator$$depth : I
/*     */     //   48: iaload
/*     */     //   49: putfield scala$collection$immutable$TrieIterator$$posD : I
/*     */     //   52: aload_0
/*     */     //   53: getfield scala$collection$immutable$TrieIterator$$arrayStack : [[Lscala/collection/immutable/Iterable;
/*     */     //   56: aload_0
/*     */     //   57: getfield scala$collection$immutable$TrieIterator$$depth : I
/*     */     //   60: aconst_null
/*     */     //   61: aastore
/*     */     //   62: goto -> 88
/*     */     //   65: aload_0
/*     */     //   66: aconst_null
/*     */     //   67: putfield scala$collection$immutable$TrieIterator$$arrayD : [Lscala/collection/immutable/Iterable;
/*     */     //   70: aload_0
/*     */     //   71: iconst_0
/*     */     //   72: putfield scala$collection$immutable$TrieIterator$$posD : I
/*     */     //   75: goto -> 88
/*     */     //   78: aload_0
/*     */     //   79: aload_0
/*     */     //   80: getfield scala$collection$immutable$TrieIterator$$posD : I
/*     */     //   83: iconst_1
/*     */     //   84: iadd
/*     */     //   85: putfield scala$collection$immutable$TrieIterator$$posD : I
/*     */     //   88: aload_1
/*     */     //   89: iload_2
/*     */     //   90: aaload
/*     */     //   91: astore_3
/*     */     //   92: aload_0
/*     */     //   93: aload_3
/*     */     //   94: invokespecial isContainer : (Ljava/lang/Object;)Z
/*     */     //   97: ifeq -> 108
/*     */     //   100: aload_0
/*     */     //   101: aload_3
/*     */     //   102: invokevirtual getElem : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   105: goto -> 199
/*     */     //   108: aload_0
/*     */     //   109: aload_3
/*     */     //   110: invokespecial isTrie : (Ljava/lang/Object;)Z
/*     */     //   113: ifeq -> 185
/*     */     //   116: aload_0
/*     */     //   117: getfield scala$collection$immutable$TrieIterator$$depth : I
/*     */     //   120: iconst_0
/*     */     //   121: if_icmplt -> 150
/*     */     //   124: aload_0
/*     */     //   125: getfield scala$collection$immutable$TrieIterator$$arrayStack : [[Lscala/collection/immutable/Iterable;
/*     */     //   128: aload_0
/*     */     //   129: getfield scala$collection$immutable$TrieIterator$$depth : I
/*     */     //   132: aload_0
/*     */     //   133: getfield scala$collection$immutable$TrieIterator$$arrayD : [Lscala/collection/immutable/Iterable;
/*     */     //   136: aastore
/*     */     //   137: aload_0
/*     */     //   138: getfield scala$collection$immutable$TrieIterator$$posStack : [I
/*     */     //   141: aload_0
/*     */     //   142: getfield scala$collection$immutable$TrieIterator$$depth : I
/*     */     //   145: aload_0
/*     */     //   146: getfield scala$collection$immutable$TrieIterator$$posD : I
/*     */     //   149: iastore
/*     */     //   150: aload_0
/*     */     //   151: aload_0
/*     */     //   152: getfield scala$collection$immutable$TrieIterator$$depth : I
/*     */     //   155: iconst_1
/*     */     //   156: iadd
/*     */     //   157: putfield scala$collection$immutable$TrieIterator$$depth : I
/*     */     //   160: aload_0
/*     */     //   161: aload_0
/*     */     //   162: aload_3
/*     */     //   163: invokespecial getElems : (Lscala/collection/immutable/Iterable;)[Lscala/collection/immutable/Iterable;
/*     */     //   166: putfield scala$collection$immutable$TrieIterator$$arrayD : [Lscala/collection/immutable/Iterable;
/*     */     //   169: aload_0
/*     */     //   170: iconst_0
/*     */     //   171: putfield scala$collection$immutable$TrieIterator$$posD : I
/*     */     //   174: aload_0
/*     */     //   175: aload_3
/*     */     //   176: invokespecial getElems : (Lscala/collection/immutable/Iterable;)[Lscala/collection/immutable/Iterable;
/*     */     //   179: iconst_0
/*     */     //   180: istore_2
/*     */     //   181: astore_1
/*     */     //   182: goto -> 0
/*     */     //   185: aload_0
/*     */     //   186: aload_3
/*     */     //   187: invokeinterface iterator : ()Lscala/collection/Iterator;
/*     */     //   192: putfield scala$collection$immutable$TrieIterator$$subIter : Lscala/collection/Iterator;
/*     */     //   195: aload_0
/*     */     //   196: invokevirtual next : ()Ljava/lang/Object;
/*     */     //   199: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #106	-> 0
/*     */     //   #107	-> 8
/*     */     //   #108	-> 18
/*     */     //   #109	-> 26
/*     */     //   #110	-> 39
/*     */     //   #111	-> 52
/*     */     //   #113	-> 65
/*     */     //   #114	-> 70
/*     */     //   #117	-> 78
/*     */     //   #119	-> 88
/*     */     //   #124	-> 92
/*     */     //   #125	-> 100
/*     */     //   #126	-> 108
/*     */     //   #127	-> 116
/*     */     //   #128	-> 124
/*     */     //   #129	-> 137
/*     */     //   #131	-> 150
/*     */     //   #132	-> 160
/*     */     //   #133	-> 169
/*     */     //   #134	-> 174
/*     */     //   #137	-> 185
/*     */     //   #138	-> 195
/*     */     //   #105	-> 199
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	200	0	this	Lscala/collection/immutable/TrieIterator;
/*     */     //   0	200	1	elems	[Lscala/collection/immutable/Iterable;
/*     */     //   0	200	2	i	I
/*     */     //   92	108	3	m	Lscala/collection/immutable/Iterable;
/*     */   }
/*     */   
/*     */   public Tuple2<Tuple2<Iterator<T>, Object>, Iterator<T>> split() {
/* 165 */     if (this.scala$collection$immutable$TrieIterator$$arrayD != null && this.scala$collection$immutable$TrieIterator$$depth == 0 && this.scala$collection$immutable$TrieIterator$$posD == 0)
/* 166 */       return splitArray(this.scala$collection$immutable$TrieIterator$$arrayD); 
/* 171 */     Buffer buff = this.scala$collection$immutable$TrieIterator$$subIter.toBuffer();
/* 172 */     this.scala$collection$immutable$TrieIterator$$subIter = null;
/* 180 */     this.scala$collection$immutable$TrieIterator$$arrayStack[0];
/* 184 */     Object[] arrayOfObject1 = (Object[])this.scala$collection$immutable$TrieIterator$$arrayStack[0];
/* 184 */     Predef$ predef$1 = Predef$.MODULE$;
/* 184 */     (new Iterable[1])[0] = (Iterable)IndexedSeqOptimized.class.last((IndexedSeqOptimized)new ArrayOps.ofRef(arrayOfObject1));
/* 184 */     Iterable[] snd = new Iterable[1];
/* 185 */     int szsnd = snd[0].size();
/* 187 */     this.scala$collection$immutable$TrieIterator$$depth--;
/* 188 */     Predef$ predef$2 = Predef$.MODULE$;
/* 188 */     int i = this.scala$collection$immutable$TrieIterator$$arrayStack.length;
/* 188 */     Range$ range$ = Range$.MODULE$;
/* 188 */     TrieIterator$$anonfun$split$1 trieIterator$$anonfun$split$1 = new TrieIterator$$anonfun$split$1(this);
/*     */     Range range;
/* 188 */     if ((range = new Range(1, i, 1)).validateRangeBoundaries((Function1<Object, Object>)trieIterator$$anonfun$split$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 188 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 188 */         this.scala$collection$immutable$TrieIterator$$arrayStack[i1 - 1] = this.scala$collection$immutable$TrieIterator$$arrayStack[i1];
/* 188 */         i1 += step1;
/*     */       } 
/*     */     } 
/* 189 */     (new Iterable[1])[0] = null;
/* 189 */     this.scala$collection$immutable$TrieIterator$$arrayStack[this.scala$collection$immutable$TrieIterator$$arrayStack.length - 1] = (Iterable<T>[])new Iterable[1];
/* 190 */     int[] arrayOfInt1 = this.scala$collection$immutable$TrieIterator$$posStack;
/* 190 */     Predef$ predef$3 = Predef$.MODULE$;
/* 190 */     int[] arrayOfInt2 = (int[])IndexedSeqOptimized.class.tail((IndexedSeqOptimized)new ArrayOps.ofInt(arrayOfInt1));
/* 190 */     Predef$ predef$4 = Predef$.MODULE$;
/* 190 */     int[] arrayOfInt3 = (int[])Array$.MODULE$.apply((Seq)Predef$.MODULE$.wrapIntArray(new int[] { 0 }, ), ClassTag$.MODULE$.Int());
/* 190 */     Predef$ predef$5 = Predef$.MODULE$;
/* 190 */     ClassTag classTag = ClassTag$.MODULE$.Int();
/* 190 */     Array$ array$ = Array$.MODULE$;
/* 190 */     this.scala$collection$immutable$TrieIterator$$posStack = (int[])TraversableLike.class.$plus$plus((TraversableLike)new ArrayOps.ofInt(arrayOfInt2), (GenTraversableOnce)new ArrayOps.ofInt(arrayOfInt3), (CanBuildFrom)new Object(classTag));
/* 195 */     Object[] arrayOfObject2 = (Object[])this.scala$collection$immutable$TrieIterator$$arrayStack[0];
/* 195 */     Predef$ predef$6 = Predef$.MODULE$;
/* 195 */     Tuple2 tuple2 = IndexedSeqOptimized.class.splitAt((IndexedSeqOptimized)new ArrayOps.ofRef(arrayOfObject2), (this.scala$collection$immutable$TrieIterator$$arrayStack[0]).length - ((this.scala$collection$immutable$TrieIterator$$arrayStack[0]).length - this.scala$collection$immutable$TrieIterator$$posStack[0] + 1) / 2);
/* 195 */     if (tuple2 != null) {
/* 195 */       Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 195 */       Iterable[] fst = (Iterable[])tuple21._1(), arrayOfIterable1 = (Iterable[])tuple21._2();
/* 196 */       this.scala$collection$immutable$TrieIterator$$arrayStack[0] = (Iterable<T>[])fst;
/*     */     } else {
/*     */       throw new MatchError(tuple2);
/*     */     } 
/* 202 */     if (this.scala$collection$immutable$TrieIterator$$posD == this.scala$collection$immutable$TrieIterator$$arrayD.length - 1) {
/* 204 */       Iterable<T> m = this.scala$collection$immutable$TrieIterator$$arrayD[this.scala$collection$immutable$TrieIterator$$posD];
/*     */     } else {
/* 212 */       Object[] arrayOfObject = (Object[])this.scala$collection$immutable$TrieIterator$$arrayD;
/* 212 */       Predef$ predef$ = Predef$.MODULE$;
/* 212 */       Tuple2 tuple21 = IndexedSeqOptimized.class.splitAt((IndexedSeqOptimized)new ArrayOps.ofRef(arrayOfObject), this.scala$collection$immutable$TrieIterator$$arrayD.length - (this.scala$collection$immutable$TrieIterator$$arrayD.length - this.scala$collection$immutable$TrieIterator$$posD + 1) / 2);
/* 212 */       if (tuple21 != null) {
/* 212 */         Tuple2 tuple22 = new Tuple2(tuple21._1(), tuple21._2());
/* 212 */         Iterable[] fst = (Iterable[])tuple22._1(), arrayOfIterable1 = (Iterable[])tuple22._2();
/* 213 */         this.scala$collection$immutable$TrieIterator$$arrayD = (Iterable<T>[])fst;
/* 214 */         return new Tuple2(iteratorWithSize((Iterable<T>[])arrayOfIterable1), this);
/*     */       } 
/*     */       throw new MatchError(tuple21);
/*     */     } 
/*     */     return (this.scala$collection$immutable$TrieIterator$$subIter != null) ? new Tuple2(new Tuple2(buff.iterator(), BoxesRunTime.boxToInteger(buff.length())), this) : ((this.scala$collection$immutable$TrieIterator$$depth > 0) ? ((this.scala$collection$immutable$TrieIterator$$posStack[0] == (this.scala$collection$immutable$TrieIterator$$arrayStack[0]).length - 1) ? new Tuple2(new Tuple2(new TrieIterator$$anon$1(this, (TrieIterator<T>)snd), BoxesRunTime.boxToInteger(szsnd)), this) : (Tuple2<Tuple2<Iterator<T>, Object>, Iterator<T>>)"JD-Core does not support Kotlin") : (Tuple2<Tuple2<Iterator<T>, Object>, Iterator<T>>)"JD-Core does not support Kotlin");
/*     */   }
/*     */   
/*     */   public TrieIterator(Iterable[] elems) {}
/*     */   
/*     */   public class TrieIterator$$anonfun$split$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(int i) {
/*     */       apply$mcVI$sp(i);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int i) {
/*     */       this.$outer.scala$collection$immutable$TrieIterator$$arrayStack[i - 1] = this.$outer.scala$collection$immutable$TrieIterator$$arrayStack[i];
/*     */     }
/*     */     
/*     */     public TrieIterator$$anonfun$split$1(TrieIterator $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\TrieIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */