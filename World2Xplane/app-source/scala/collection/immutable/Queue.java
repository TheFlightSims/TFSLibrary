/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Function3;
/*     */ import scala.Function4;
/*     */ import scala.Function5;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractSeq;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.LinearSeq;
/*     */ import scala.collection.LinearSeqLike;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenTraversableFactory;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParSeq;
/*     */ import scala.math.Integral;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Uf\001B\001\003\001%\021Q!U;fk\026T!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006\022'\031\0011bG\020'UA\031A\"D\b\016\003\021I!A\004\003\003\027\005\0237\017\036:bGR\034V-\035\t\003!Ea\001\001\002\004\023\001\021\025\ra\005\002\002\003F\021A\003\007\t\003+Yi\021AB\005\003/\031\021qAT8uQ&tw\r\005\002\0263%\021!D\002\002\004\003:L\bc\001\017\036\0375\t!!\003\002\037\005\tIA*\0338fCJ\034V-\035\t\005A\rzQ%D\001\"\025\t\021C!A\004hK:,'/[2\n\005\021\n#AG$f]\026\024\030n\031+sCZ,'o]1cY\026$V-\0349mCR,\007C\001\017\001!\021aqeD\025\n\005!\"!!\004'j]\026\f'oU3r\031&\\W\rE\002\035\001=\001\"!F\026\n\00512!\001D*fe&\fG.\033>bE2,\007\002\003\030\001\005\013\007I\021C\030\002\005%tW#\001\031\021\007q\tt\"\003\0023\005\t!A*[:u\021!!\004A!A!\002\023\001\024aA5oA!Aa\007\001BC\002\023Eq&A\002pkRD\001\002\017\001\003\002\003\006I\001M\001\005_V$\b\005C\003;\001\021E1(\001\004=S:LGO\020\013\004Sqj\004\"\002\030:\001\004\001\004\"\002\034:\001\004\001\004\"B \001\t\003\002\025!C2p[B\fg.[8o+\005\t\005c\001\021CK%\0211)\t\002\021\017\026tWM]5d\007>l\007/\0318j_:DQ!\022\001\005B\031\013Q!\0319qYf$\"aD$\t\013!#\005\031A%\002\0039\004\"!\006&\n\005-3!aA%oi\")Q\n\001C!\035\006A\021\016^3sCR|'/F\001P!\ra\001kD\005\003#\022\021\001\"\023;fe\006$xN\035\005\006'\002!\t\005V\001\bSN,U\016\035;z+\005)\006CA\013W\023\t9fAA\004C_>dW-\0318\t\013e\003A\021\t.\002\t!,\027\rZ\013\002\037!)A\f\001C!;\006!A/Y5m+\005I\003\"B0\001\t\003\002\027A\0027f]\036$\b.F\001J\021\025\021\007\001\"\001d\003\035)g.];fk\026,\"\001Z4\025\005\025T\007c\001\017\001MB\021\001c\032\003\006Q\006\024\r!\033\002\002\005F\021q\002\007\005\006W\006\004\rAZ\001\005K2,W\016C\003c\001\021\005Q.\006\002ocR\021qN\035\t\0049\001\001\bC\001\tr\t\025AGN1\001j\021\025\031H\0161\001u\003\021IG/\032:\021\007q)\b/\003\002w\005\tA\021\n^3sC\ndW\rC\003y\001\021\005\0210A\004eKF,X-^3\026\003i\004B!F>\020S%\021AP\002\002\007)V\004H.\032\032\t\013y\004A\021\001.\002\013\031\024xN\034;\t\017\005\005\001\001\"\021\002\004\005AAo\\*ue&tw\r\006\002\002\006A!\021qAA\007\035\r)\022\021B\005\004\003\0271\021A\002)sK\022,g-\003\003\002\020\005E!AB*ue&twMC\002\002\f\031AS\001AA\013\0037\0012!FA\f\023\r\tIB\002\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\002Bf\033dL_N;&i\004\b\003?\021\001\022AA\021\003\025\tV/Z;f!\ra\0221\005\004\007\003\tA\t!!\n\024\013\005\r\022q\005\026\021\t\001\nI#J\005\004\003W\t#AC*fc\032\0137\r^8ss\"9!(a\t\005\002\005=BCAA\021\021!\t\031$a\t\005\004\005U\022\001D2b]\n+\030\016\0343Ge>lW\003BA\034\003\023*\"!!\017\021\023\001\nY$a\020\002H\005-\023bAA\037C\ta1)\0318Ck&dGM\022:p[B!\021\021IA\"\033\t\t\031#C\002\002F\t\023AaQ8mYB\031\001#!\023\005\rI\t\tD1\001\024!\021a\002!a\022\t\021\005=\0231\005C\001\003#\n!B\\3x\005VLG\016Z3s+\021\t\031&a\031\026\005\005U\003\003CA,\003;\n\t'!\032\016\005\005e#bAA.\t\0059Q.\036;bE2,\027\002BA0\0033\022qAQ;jY\022,'\017E\002\021\003G\"aAEA'\005\004\031\002\003\002\017\001\003CB\001\"!\033\002$\021\005\0231N\001\006K6\004H/_\013\005\003[\n\031(\006\002\002pA!A\004AA9!\r\001\0221\017\003\007%\005\035$\031A\n\t\017\025\013\031\003\"\021\002xU!\021\021PA@)\021\tY(!!\021\tq\001\021Q\020\t\004!\005}DA\002\n\002v\t\0071\003\003\005\002\004\006U\004\031AAC\003\tA8\017E\003\026\003\017\013i(C\002\002\n\032\021!\002\020:fa\026\fG/\0323?\017!\ti)a\t\t\n\005=\025AC#naRL\030+^3vKB!\021\021IAI\r!\t\031*a\t\t\n\005U%AC#naRL\030+^3vKN!\021\021SAL!\ra\002\001\006\005\bu\005EE\021AAN)\t\ty\t\003\006\002 \006E\025\021!C\005\003C\0131B]3bIJ+7o\0347wKR\021\0211\025\t\005\003K\013y+\004\002\002(*!\021\021VAV\003\021a\027M\\4\013\005\0055\026\001\0026bm\006LA!!-\002(\n1qJ\0316fGRD!\"a(\002$\005\005I\021BAQ\001")
/*     */ public class Queue<A> extends AbstractSeq<A> implements LinearSeq<A>, GenericTraversableTemplate<A, Queue>, LinearSeqLike<A, Queue<A>>, Serializable {
/*     */   public static final long serialVersionUID = -7622936493364270175L;
/*     */   
/*     */   private final List<A> in;
/*     */   
/*     */   private final List<A> out;
/*     */   
/*     */   public LinearSeq<A> seq() {
/*  40 */     return LinearSeq$class.seq(this);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> thisCollection() {
/*  40 */     return LinearSeqLike.class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> toCollection(LinearSeqLike repr) {
/*  40 */     return LinearSeqLike.class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  40 */     return LinearSeqLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public final <B> boolean corresponds(GenSeq that, Function2 p) {
/*  40 */     return LinearSeqLike.class.corresponds(this, that, p);
/*     */   }
/*     */   
/*     */   public Seq<A> toSeq() {
/*  40 */     return Seq$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public Combiner<A, ParSeq<A>> parCombiner() {
/*  40 */     return Seq$class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public List<A> in() {
/*  40 */     return this.in;
/*     */   }
/*     */   
/*     */   public List<A> out() {
/*  40 */     return this.out;
/*     */   }
/*     */   
/*     */   public Queue(List<A> in, List<A> out) {
/*  40 */     Traversable$class.$init$(this);
/*  40 */     Iterable$class.$init$(this);
/*  40 */     Seq$class.$init$(this);
/*  40 */     LinearSeqLike.class.$init$(this);
/*  40 */     LinearSeq.class.$init$(this);
/*  40 */     LinearSeq$class.$init$(this);
/*     */   }
/*     */   
/*     */   public GenericCompanion<Queue> companion() {
/*  47 */     return (GenericCompanion<Queue>)Queue$.MODULE$;
/*     */   }
/*     */   
/*     */   public A apply(int n) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual out : ()Lscala/collection/immutable/List;
/*     */     //   4: invokevirtual length : ()I
/*     */     //   7: istore_2
/*     */     //   8: iload_1
/*     */     //   9: iload_2
/*     */     //   10: if_icmpge -> 24
/*     */     //   13: aload_0
/*     */     //   14: invokevirtual out : ()Lscala/collection/immutable/List;
/*     */     //   17: iload_1
/*     */     //   18: invokevirtual apply : (I)Ljava/lang/Object;
/*     */     //   21: goto -> 50
/*     */     //   24: iload_1
/*     */     //   25: iload_2
/*     */     //   26: isub
/*     */     //   27: istore_3
/*     */     //   28: iload_3
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual in : ()Lscala/collection/immutable/List;
/*     */     //   33: invokevirtual length : ()I
/*     */     //   36: if_icmpge -> 51
/*     */     //   39: aload_0
/*     */     //   40: invokevirtual in : ()Lscala/collection/immutable/List;
/*     */     //   43: invokevirtual reverse : ()Lscala/collection/immutable/List;
/*     */     //   46: iload_3
/*     */     //   47: invokevirtual apply : (I)Ljava/lang/Object;
/*     */     //   50: areturn
/*     */     //   51: new java/util/NoSuchElementException
/*     */     //   54: dup
/*     */     //   55: ldc 'index out of range'
/*     */     //   57: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   60: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #57	-> 0
/*     */     //   #58	-> 8
/*     */     //   #60	-> 24
/*     */     //   #61	-> 28
/*     */     //   #56	-> 50
/*     */     //   #62	-> 51
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	61	0	this	Lscala/collection/immutable/Queue;
/*     */     //   0	61	1	n	I
/*     */     //   8	53	2	len	I
/*     */     //   28	33	3	m	I
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/*  68 */     List<A> list = out();
/*  68 */     return in().reverse().$colon$colon$colon(list).iterator();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  74 */     return (in().isEmpty() && out().isEmpty());
/*     */   }
/*     */   
/*     */   public A head() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual out : ()Lscala/collection/immutable/List;
/*     */     //   4: invokevirtual nonEmpty : ()Z
/*     */     //   7: ifeq -> 20
/*     */     //   10: aload_0
/*     */     //   11: invokevirtual out : ()Lscala/collection/immutable/List;
/*     */     //   14: invokevirtual head : ()Ljava/lang/Object;
/*     */     //   17: goto -> 37
/*     */     //   20: aload_0
/*     */     //   21: invokevirtual in : ()Lscala/collection/immutable/List;
/*     */     //   24: invokevirtual nonEmpty : ()Z
/*     */     //   27: ifeq -> 38
/*     */     //   30: aload_0
/*     */     //   31: invokevirtual in : ()Lscala/collection/immutable/List;
/*     */     //   34: invokevirtual last : ()Ljava/lang/Object;
/*     */     //   37: areturn
/*     */     //   38: new java/util/NoSuchElementException
/*     */     //   41: dup
/*     */     //   42: ldc 'head on empty queue'
/*     */     //   44: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   47: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #77	-> 0
/*     */     //   #78	-> 20
/*     */     //   #77	-> 37
/*     */     //   #79	-> 38
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	48	0	this	Lscala/collection/immutable/Queue;
/*     */   }
/*     */   
/*     */   public Queue<A> tail() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual out : ()Lscala/collection/immutable/List;
/*     */     //   4: invokevirtual nonEmpty : ()Z
/*     */     //   7: ifeq -> 34
/*     */     //   10: new scala/collection/immutable/Queue
/*     */     //   13: dup
/*     */     //   14: aload_0
/*     */     //   15: invokevirtual in : ()Lscala/collection/immutable/List;
/*     */     //   18: aload_0
/*     */     //   19: invokevirtual out : ()Lscala/collection/immutable/List;
/*     */     //   22: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   25: checkcast scala/collection/immutable/List
/*     */     //   28: invokespecial <init> : (Lscala/collection/immutable/List;Lscala/collection/immutable/List;)V
/*     */     //   31: goto -> 67
/*     */     //   34: aload_0
/*     */     //   35: invokevirtual in : ()Lscala/collection/immutable/List;
/*     */     //   38: invokevirtual nonEmpty : ()Z
/*     */     //   41: ifeq -> 68
/*     */     //   44: new scala/collection/immutable/Queue
/*     */     //   47: dup
/*     */     //   48: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   51: aload_0
/*     */     //   52: invokevirtual in : ()Lscala/collection/immutable/List;
/*     */     //   55: invokevirtual reverse : ()Lscala/collection/immutable/List;
/*     */     //   58: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   61: checkcast scala/collection/immutable/List
/*     */     //   64: invokespecial <init> : (Lscala/collection/immutable/List;Lscala/collection/immutable/List;)V
/*     */     //   67: areturn
/*     */     //   68: new java/util/NoSuchElementException
/*     */     //   71: dup
/*     */     //   72: ldc 'tail on empty queue'
/*     */     //   74: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   77: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #82	-> 0
/*     */     //   #83	-> 34
/*     */     //   #82	-> 67
/*     */     //   #84	-> 68
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	78	0	this	Lscala/collection/immutable/Queue;
/*     */   }
/*     */   
/*     */   public int length() {
/*  88 */     return in().length() + out().length();
/*     */   }
/*     */   
/*     */   public <B> Queue<B> enqueue(Object elem) {
/*  95 */     return new Queue(in().$colon$colon((A)elem), out());
/*     */   }
/*     */   
/*     */   public <B> Queue<B> enqueue(Iterable iter) {
/* 106 */     List<A> list = iter.toList();
/* 106 */     return new Queue(in().reverse_$colon$colon$colon(list), out());
/*     */   }
/*     */   
/*     */   public Tuple2<A, Queue<A>> dequeue() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual out : ()Lscala/collection/immutable/List;
/*     */     //   4: astore_2
/*     */     //   5: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   8: dup
/*     */     //   9: ifnonnull -> 20
/*     */     //   12: pop
/*     */     //   13: aload_2
/*     */     //   14: ifnull -> 27
/*     */     //   17: goto -> 78
/*     */     //   20: aload_2
/*     */     //   21: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   24: ifeq -> 78
/*     */     //   27: aload_0
/*     */     //   28: invokevirtual in : ()Lscala/collection/immutable/List;
/*     */     //   31: invokevirtual isEmpty : ()Z
/*     */     //   34: ifne -> 78
/*     */     //   37: aload_0
/*     */     //   38: invokevirtual in : ()Lscala/collection/immutable/List;
/*     */     //   41: invokevirtual reverse : ()Lscala/collection/immutable/List;
/*     */     //   44: astore_1
/*     */     //   45: new scala/Tuple2
/*     */     //   48: dup
/*     */     //   49: aload_1
/*     */     //   50: invokevirtual head : ()Ljava/lang/Object;
/*     */     //   53: new scala/collection/immutable/Queue
/*     */     //   56: dup
/*     */     //   57: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   60: aload_1
/*     */     //   61: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   64: checkcast scala/collection/immutable/List
/*     */     //   67: invokespecial <init> : (Lscala/collection/immutable/List;Lscala/collection/immutable/List;)V
/*     */     //   70: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   73: astore #4
/*     */     //   75: goto -> 118
/*     */     //   78: aload_2
/*     */     //   79: instanceof scala/collection/immutable/$colon$colon
/*     */     //   82: ifeq -> 121
/*     */     //   85: aload_2
/*     */     //   86: checkcast scala/collection/immutable/$colon$colon
/*     */     //   89: astore_3
/*     */     //   90: new scala/Tuple2
/*     */     //   93: dup
/*     */     //   94: aload_3
/*     */     //   95: invokevirtual hd$1 : ()Ljava/lang/Object;
/*     */     //   98: new scala/collection/immutable/Queue
/*     */     //   101: dup
/*     */     //   102: aload_0
/*     */     //   103: invokevirtual in : ()Lscala/collection/immutable/List;
/*     */     //   106: aload_3
/*     */     //   107: invokevirtual tl$1 : ()Lscala/collection/immutable/List;
/*     */     //   110: invokespecial <init> : (Lscala/collection/immutable/List;Lscala/collection/immutable/List;)V
/*     */     //   113: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   116: astore #4
/*     */     //   118: aload #4
/*     */     //   120: areturn
/*     */     //   121: new java/util/NoSuchElementException
/*     */     //   124: dup
/*     */     //   125: ldc 'dequeue on empty queue'
/*     */     //   127: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   130: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #114	-> 0
/*     */     //   #115	-> 5
/*     */     //   #116	-> 78
/*     */     //   #114	-> 94
/*     */     //   #116	-> 95
/*     */     //   #114	-> 106
/*     */     //   #116	-> 107
/*     */     //   #114	-> 118
/*     */     //   #117	-> 121
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	131	0	this	Lscala/collection/immutable/Queue;
/*     */     //   45	28	1	rev	Lscala/collection/immutable/List;
/*     */   }
/*     */   
/*     */   public A front() {
/* 126 */     return head();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 130 */     return mkString("Queue(", ", ", ")");
/*     */   }
/*     */   
/*     */   public static <A> Queue<A> empty() {
/*     */     return Queue$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<Queue<?>, A, Queue<A>> canBuildFrom() {
/*     */     return Queue$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Some<Queue<A>> unapplySeq(Queue<A> paramQueue) {
/*     */     return Queue$.MODULE$.unapplySeq(paramQueue);
/*     */   }
/*     */   
/*     */   public static <A> Queue<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*     */     return (Queue<A>)Queue$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> Queue<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return (Queue<T>)Queue$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <T> Queue<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*     */     return (Queue<T>)Queue$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <A> Queue<Queue<Queue<Queue<Queue<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*     */     return (Queue<Queue<Queue<Queue<Queue<A>>>>>)Queue$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*     */   }
/*     */   
/*     */   public static <A> Queue<Queue<Queue<Queue<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*     */     return (Queue<Queue<Queue<Queue<A>>>>)Queue$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*     */   }
/*     */   
/*     */   public static <A> Queue<Queue<Queue<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*     */     return (Queue<Queue<Queue<A>>>)Queue$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*     */   }
/*     */   
/*     */   public static <A> Queue<Queue<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*     */     return (Queue<Queue<A>>)Queue$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> Queue<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*     */     return (Queue<A>)Queue$.MODULE$.tabulate(paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Queue<Queue<Queue<Queue<Queue<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*     */     return (Queue<Queue<Queue<Queue<Queue<A>>>>>)Queue$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Queue<Queue<Queue<Queue<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*     */     return (Queue<Queue<Queue<Queue<A>>>>)Queue$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Queue<Queue<Queue<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*     */     return (Queue<Queue<Queue<A>>>)Queue$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Queue<Queue<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*     */     return (Queue<Queue<A>>)Queue$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Queue<A> fill(int paramInt, Function0<A> paramFunction0) {
/*     */     return (Queue<A>)Queue$.MODULE$.fill(paramInt, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Queue<A> concat(Seq<Traversable<A>> paramSeq) {
/*     */     return (Queue<A>)Queue$.MODULE$.concat(paramSeq);
/*     */   }
/*     */   
/*     */   public static GenTraversableFactory<Queue>.GenericCanBuildFrom<scala.runtime.Nothing$> ReusableCBF() {
/*     */     return Queue$.MODULE$.ReusableCBF();
/*     */   }
/*     */   
/*     */   public static <A> Queue<A> empty() {
/*     */     return (Queue<A>)Queue$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public static class Queue$$anonfun$newBuilder$1 extends AbstractFunction1<List<A>, Queue<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Queue<A> apply(List<A> x) {
/* 140 */       return new Queue<A>(Nil$.MODULE$, x.toList());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class EmptyQueue$ extends Queue<scala.runtime.Nothing$> {
/*     */     public static final EmptyQueue$ MODULE$;
/*     */     
/*     */     private Object readResolve() {
/* 144 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public EmptyQueue$() {
/* 144 */       super(Nil$.MODULE$, Nil$.MODULE$);
/* 144 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Queue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */