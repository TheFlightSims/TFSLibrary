/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Function3;
/*     */ import scala.Function4;
/*     */ import scala.Function5;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenTraversableFactory;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.math.Integral;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\rq!B\001\003\021\003I\021!B*uC\016\\'BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001\001\"AC\006\016\003\t1Q\001\004\002\t\0025\021Qa\025;bG.\0342a\003\b5!\ry!\003F\007\002!)\021\021\003B\001\bO\026tWM]5d\023\t\031\002C\001\006TKF4\025m\031;pef\004\"AC\013\007\t1\021\001AF\013\003/u\031r!\006\r(U9\nD\007E\002\0133mI!A\007\002\003\027\005\0237\017\036:bGR\034V-\035\t\0039ua\001\001B\003\037+\t\007qDA\001B#\t\001C\005\005\002\"E5\ta!\003\002$\r\t9aj\034;iS:<\007CA\021&\023\t1cAA\002B]f\0042A\003\025\034\023\tI#AA\002TKF\004BAC\026\034[%\021AF\001\002\b'\026\fH*[6f!\rQQc\007\t\005\037=ZB#\003\0021!\tQr)\0328fe&\034GK]1wKJ\034\030M\0317f)\026l\007\017\\1uKB\031!BM\027\n\005M\022!!C\"m_:,\027M\0317f!\t\tS'\003\0027\r\ta1+\032:jC2L'0\0312mK\"A\001(\006BA\002\023\005\021(A\003fY\026l7/F\001;!\rYdhG\007\002y)\021Q\bB\001\nS6lW\017^1cY\026L!a\020\037\003\t1K7\017\036\005\t\003V\021\t\031!C\001\005\006IQ\r\\3ng~#S-\035\013\003\007\032\003\"!\t#\n\005\0253!\001B+oSRDqa\022!\002\002\003\007!(A\002yIEB\001\"S\013\003\002\003\006KAO\001\007K2,Wn\035\021\t\013-+B\021\002'\002\rqJg.\033;?)\tiS\nC\0039\025\002\007!\bC\003L+\021\005q\nF\001.\021\025\tV\003\"\021S\003%\031w.\0349b]&|g.F\001T\035\tQ\001\001C\003V+\021\005c+A\004jg\026k\007\017^=\026\003]\003\"!\t-\n\005e3!a\002\"p_2,\027M\034\005\0067V!\t\005X\001\007Y\026tw\r\0365\026\003u\003\"!\t0\n\005}3!aA%oi\")\021-\006C!E\006)\021\r\0359msR\0211d\031\005\006I\002\004\r!X\001\006S:$W\r\037\005\006MV!\taZ\001\007kB$\027\r^3\025\007\rC'\016C\003jK\002\007Q,A\001o\021\025YW\r1\001\034\003\035qWm^3mK6DQ!\\\013\005\0029\fA\001];tQR\021q\016]\007\002+!)\021\017\034a\0017\005!Q\r\\3n\021\025iW\003\"\001t)\021yGO\036=\t\013U\024\b\031A\016\002\013\025dW-\\\031\t\013]\024\b\031A\016\002\013\025dW-\034\032\t\013a\022\b\031A=\021\007\005R8$\003\002|\r\tQAH]3qK\006$X\r\032 \t\013u,B\021\001@\002\017A,8\017[!mYR\021qn \005\b\003\003a\b\031AA\002\003\tA8\017E\003\002\006\005\0351$D\001\005\023\r\tI\001\002\002\020)J\fg/\032:tC\ndWm\0248dK\"9\021QB\013\005\002\005=\021a\001;paV\t1\004C\004\002\024U!\t!!\006\002\007A|\007\017F\001\034\021\035\tI\"\006C\001\0037\tQa\0317fCJ$\022a\021\005\b\003?)B\021IA\021\003!IG/\032:bi>\024XCAA\022!\025\t)!!\n\034\023\r\t9\003\002\002\t\023R,'/\031;pe\"B\021QDA\026\003o\tY\004\005\003\002.\005MRBAA\030\025\r\t\tDB\001\013C:tw\016^1uS>t\027\002BA\033\003_\021\021\"\\5he\006$\030n\0348\"\005\005e\022a\t1ji\026\024\030\r^8sA\002\"(/\031<feN,7\017I5oA\031Kei\024\021pe\022,'OL\021\003\003{\tQA\r\0309]ABa!!\021\026\t\003J\024A\002;p\031&\034H\017\013\005\002@\005-\022QIA\036C\t\t9%A\021ai>d\025n\035;aAQ\024\030M^3sg\026\034\b%\0338!\r&3u\nI8sI\026\024h\006C\004\002LU!\t%!\024\002\017\031|'/Z1dQV!\021qJA/)\r\031\025\021\013\005\t\003'\nI\0051\001\002V\005\ta\r\005\004\"\003/Z\0221L\005\004\00332!!\003$v]\016$\030n\03482!\ra\022Q\f\003\b\003?\nIE1\001 \005\005)\006\006CA%\003W\t\031'a\017\"\005\005\025\024A\t1g_J,\027m\0315aAQ\024\030M^3sg\026\034\b%\0338!\r&3u\nI8sI\026\024h\006\003\004\002jU!\teT\001\006G2|g.\032\005\007\027.!\t!!\034\025\003%1a!!\035\f\001\005M$\001D*uC\016\\')^5mI\026\024X\003BA;\003\013\033b!a\034\002x\005u\004cA\021\002z%\031\0211\020\004\003\r\005s\027PU3g!\035Q\021qPAB\003\017K1!!!\003\005\035\021U/\0337eKJ\0042\001HAC\t\031q\022q\016b\001?A!!\"FAB\021\035Y\025q\016C\001\003\027#\"!!$\021\r\005=\025qNAB\033\005Y\001BCAJ\003_\022\r\021\"\001\002\026\006)ANY;gMV\021\021q\023\t\006\025\005e\0251Q\005\004\0037\023!A\003'jgR\024UO\0324fe\"I\021qTA8A\003%\021qS\001\007Y\n,hM\032\021\t\021\005\r\026q\016C\001\003K\013\001\002\n9mkN$S-\035\013\005\003O\013I+\004\002\002p!9\021/!)A\002\005\r\005\002CA\r\003_\"\t!a\007\t\021\005=\026q\016C\001\003c\013aA]3tk2$HCAAD\021\035\t)l\003C\002\003o\013AbY1o\005VLG\016\032$s_6,B!!/\002NV\021\0211\030\t\n\037\005u\026\021YAf\003\037L1!a0\021\0051\031\025M\034\"vS2$gI]8n!\021\ty)a1\n\t\005\025\027q\031\002\005\007>dG.C\002\002JB\021\001cR3oKJL7mQ8na\006t\027n\0348\021\007q\ti\r\002\004\037\003g\023\ra\b\t\005\025U\tY\rC\004\002T.!\t!!6\002\0259,wOQ;jY\022,'/\006\003\002X\006uWCAAm!\035Q\021qPAn\003?\0042\001HAo\t\031q\022\021\033b\001?A!!\"FAn\021%\t\031o\003b\001\n\003\t)/A\003f[B$\0300\006\002\002hB\031!\"\006\021\t\021\005-8\002)A\005\003O\fa!Z7qif\004\003\"CAx\027\005\005I\021BAy\003-\021X-\0313SKN|GN^3\025\005\005M\b\003BA{\003l!!a>\013\t\005e\0301`\001\005Y\006twM\003\002\002~\006!!.\031<b\023\021\021\t!a>\003\r=\023'.Z2u\001")
/*     */ public class Stack<A> extends AbstractSeq<A> implements Seq<A>, SeqLike<A, Stack<A>>, GenericTraversableTemplate<A, Stack>, Cloneable<Stack<A>>, Serializable {
/*     */   private List<A> elems;
/*     */   
/*     */   public static class StackBuilder<A> implements Builder<A, Stack<A>> {
/*     */     private final ListBuffer<A> lbuff;
/*     */     
/*     */     public void sizeHint(int size) {
/*  26 */       Builder$class.sizeHint(this, size);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll) {
/*  26 */       Builder$class.sizeHint(this, coll);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll, int delta) {
/*  26 */       Builder$class.sizeHint(this, coll, delta);
/*     */     }
/*     */     
/*     */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  26 */       Builder$class.sizeHintBounded(this, size, boundingColl);
/*     */     }
/*     */     
/*     */     public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/*  26 */       return Builder$class.mapResult(this, f);
/*     */     }
/*     */     
/*     */     public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  26 */       return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public Growable<A> $plus$plus$eq(TraversableOnce xs) {
/*  26 */       return Growable.class.$plus$plus$eq(this, xs);
/*     */     }
/*     */     
/*     */     public StackBuilder() {
/*  26 */       Growable.class.$init$(this);
/*  26 */       Builder$class.$init$(this);
/*  27 */       this.lbuff = new ListBuffer<A>();
/*     */     }
/*     */     
/*     */     public ListBuffer<A> lbuff() {
/*  27 */       return this.lbuff;
/*     */     }
/*     */     
/*     */     public StackBuilder<A> $plus$eq(Object elem) {
/*  28 */       lbuff().$plus$eq((A)elem);
/*  28 */       return this;
/*     */     }
/*     */     
/*     */     public void clear() {
/*  29 */       lbuff().clear();
/*     */     }
/*     */     
/*     */     public Stack<A> result() {
/*  30 */       return new Stack<A>(lbuff().result());
/*     */     }
/*     */   }
/*     */   
/*     */   public List<A> elems() {
/*  56 */     return this.elems;
/*     */   }
/*     */   
/*     */   public void elems_$eq(List<A> x$1) {
/*  56 */     this.elems = x$1;
/*     */   }
/*     */   
/*     */   public Stack(List<A> elems) {}
/*     */   
/*     */   public Stack() {
/*  64 */     this((List<A>)Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public Stack$ companion() {
/*  66 */     return Stack$.MODULE$;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  72 */     return elems().isEmpty();
/*     */   }
/*     */   
/*     */   public int length() {
/*  75 */     return elems().length();
/*     */   }
/*     */   
/*     */   public A apply(int index) {
/*  85 */     return (A)elems().apply(index);
/*     */   }
/*     */   
/*     */   public void update(int n, Object newelem) {
/*  96 */     if (n < 0 || n >= length())
/*  96 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString()); 
/*  97 */     elems_$eq((List<A>)elems().take(n).$plus$plus((GenTraversableOnce)elems().drop(n + 1).$colon$colon(newelem), List$.MODULE$.canBuildFrom()));
/*     */   }
/*     */   
/*     */   public Stack<A> push(Object elem) {
/* 104 */     elems_$eq(elems().$colon$colon(elem));
/* 104 */     return this;
/*     */   }
/*     */   
/*     */   public Stack<A> push(Object elem1, Object elem2, Seq elems) {
/* 113 */     return push((A)elem1).push((A)elem2).pushAll((TraversableOnce<A>)elems);
/*     */   }
/*     */   
/*     */   public Stack<A> pushAll(TraversableOnce xs) {
/* 121 */     xs.seq().foreach((Function1)new Stack$$anonfun$pushAll$1(this));
/* 121 */     return this;
/*     */   }
/*     */   
/*     */   public class Stack$$anonfun$pushAll$1 extends AbstractFunction1<A, Stack<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Stack<A> apply(Object elem) {
/* 121 */       return this.$outer.push((A)elem);
/*     */     }
/*     */     
/*     */     public Stack$$anonfun$pushAll$1(Stack $outer) {}
/*     */   }
/*     */   
/*     */   public A top() {
/* 131 */     return (A)elems().head();
/*     */   }
/*     */   
/*     */   public A pop() {
/* 139 */     Object res = elems().head();
/* 140 */     elems_$eq((List<A>)elems().tail());
/* 141 */     return (A)res;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 148 */     elems_$eq((List<A>)Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/* 159 */     return elems().iterator();
/*     */   }
/*     */   
/*     */   public List<A> toList() {
/* 166 */     return elems();
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/* 169 */     IterableLike.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public Stack<A> clone() {
/* 175 */     return new Stack(elems());
/*     */   }
/*     */   
/*     */   public static Stack<Nothing$> empty() {
/*     */     return Stack$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<Stack<?>, A, Stack<A>> canBuildFrom() {
/*     */     return Stack$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Some<Stack<A>> unapplySeq(Stack<A> paramStack) {
/*     */     return Stack$.MODULE$.unapplySeq(paramStack);
/*     */   }
/*     */   
/*     */   public static <A> Stack<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*     */     return (Stack<A>)Stack$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> Stack<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return (Stack<T>)Stack$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <T> Stack<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*     */     return (Stack<T>)Stack$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<Stack<Stack<Stack<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*     */     return (Stack<Stack<Stack<Stack<Stack<A>>>>>)Stack$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<Stack<Stack<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*     */     return (Stack<Stack<Stack<Stack<A>>>>)Stack$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<Stack<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*     */     return (Stack<Stack<Stack<A>>>)Stack$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*     */     return (Stack<Stack<A>>)Stack$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> Stack<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*     */     return (Stack<A>)Stack$.MODULE$.tabulate(paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<Stack<Stack<Stack<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*     */     return (Stack<Stack<Stack<Stack<Stack<A>>>>>)Stack$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<Stack<Stack<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*     */     return (Stack<Stack<Stack<Stack<A>>>>)Stack$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<Stack<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*     */     return (Stack<Stack<Stack<A>>>)Stack$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*     */     return (Stack<Stack<A>>)Stack$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Stack<A> fill(int paramInt, Function0<A> paramFunction0) {
/*     */     return (Stack<A>)Stack$.MODULE$.fill(paramInt, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Stack<A> concat(Seq<Traversable<A>> paramSeq) {
/*     */     return (Stack<A>)Stack$.MODULE$.concat(paramSeq);
/*     */   }
/*     */   
/*     */   public static GenTraversableFactory<Stack>.GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*     */     return Stack$.MODULE$.ReusableCBF();
/*     */   }
/*     */   
/*     */   public static <A> Stack<A> empty() {
/*     */     return (Stack<A>)Stack$.MODULE$.empty();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Stack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */