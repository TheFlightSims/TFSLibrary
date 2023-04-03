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
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.Iterable;
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
/*     */ import scala.math.Integral;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.sys.package$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t%r!B\001\003\021\003I\021AC!se\006L8\013^1dW*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001!\tQ1\"D\001\003\r\025a!\001#\001\016\005)\t%O]1z'R\f7m[\n\004\02799\004cA\b\023)5\t\001C\003\002\022\t\0059q-\0328fe&\034\027BA\n\021\005)\031V-\035$bGR|'/\037\t\003\025U1A\001\004\002\001-U\021q#H\n\t+a9#FL\0315oA\031!\"G\016\n\005i\021!aC!cgR\024\030m\031;TKF\004\"\001H\017\r\001\021)a$\006b\001?\t\tA+\005\002!IA\021\021EI\007\002\r%\0211E\002\002\b\035>$\b.\0338h!\t\tS%\003\002'\r\t\031\021I\\=\021\007)A3$\003\002*\005\t\0311+Z9\021\t)Y3$L\005\003Y\t\021qaU3r\031&\\W\rE\002\013+m\001BaD\030\034)%\021\001\007\005\002\033\017\026tWM]5d)J\fg/\032:tC\ndW\rV3na2\fG/\032\t\004\025Ij\023BA\032\003\005%\031En\0348fC\ndW\r\005\003\013kmi\023B\001\034\003\005\035\021U/\0337eKJ\004\"!\t\035\n\005e2!\001D*fe&\fG.\033>bE2,\007\002C\036\026\005\003\007I\021\002\037\002\013Q\f'\r\\3\026\003u\0022!\t A\023\tydAA\003BeJ\f\027\020\005\002\"\003&\021!I\002\002\007\003:L(+\0324\t\021\021+\"\0211A\005\n\025\013\021\002^1cY\026|F%Z9\025\005\031K\005CA\021H\023\tAeA\001\003V]&$\bb\002&D\003\003\005\r!P\001\004q\022\n\004\002\003'\026\005\003\005\013\025B\037\002\rQ\f'\r\\3!\021!qUC!a\001\n\023y\025!B5oI\026DX#\001)\021\005\005\n\026B\001*\007\005\rIe\016\036\005\t)V\021\t\031!C\005+\006I\021N\0343fq~#S-\035\013\003\rZCqAS*\002\002\003\007\001\013\003\005Y+\t\005\t\025)\003Q\003\031Ig\016Z3yA!)!,\006C\0057\0061A(\0338jiz\"2!\f/^\021\025Y\024\f1\001>\021\025q\025\f1\001Q\021\025QV\003\"\001`)\005i\003\"B1\026\t\003\021\027!B1qa2LHCA\016d\021\025!\007\r1\001Q\003\005q\007\"\0024\026\t\003y\025A\0027f]\036$\b\016C\003i+\021\005\023.A\005d_6\004\030M\\5p]V\t!N\004\002\013\001!)A.\006C\001[\0061Q\017\0353bi\026$2A\0228p\021\025!7\0161\001Q\021\025\0018\0161\001\034\003\035qWm^3mK6DQA]\013\005\002M\fA\001];tQR\021a\t\036\005\006kF\004\raG\001\002q\")q/\006C\001q\006\031\001o\0349\025\003mAQA_\013\005\002m\f1\001^8q+\005Y\002\"B?\026\t\003q\030a\0013vaR\ta\t\003\004\002\002U!\tA`\001\006G2,\027M\035\005\b\003\013)B\021AA\004\003\025!'/Y5o)\r1\025\021\002\005\t\003\027\t\031\0011\001\002\016\005\ta\rE\003\"\003\037Yb)C\002\002\022\031\021\021BR;oGRLwN\\\031\t\017\005UQ\003\"\021\002\030\005iA\005\0357vg\022\002H.^:%KF$B!!\007\002\0345\tQ\003\003\005\002\036\005M\001\031AA\020\003\tA8\017E\003\002\"\005\r2$D\001\005\023\r\t)\003\002\002\020)J\fg/\032:tC\ndWm\0248dK\"9\021\021F\013\005\002\005-\022\001\003\023qYV\034H%Z9\025\t\005e\021Q\006\005\007k\006\035\002\031A\016\t\r\005ER\003\"\001`\003\031\021Xm];mi\"1\021QG\013\005\ny\fAB]3wKJ\034X\rV1cY\026Dq!!\017\026\t\003\tY$A\004d_6\024\027N\\3\025\007\031\013i\004\003\005\002\f\005]\002\031AA !\031\t\023\021I\016\0347%\031\0211\t\004\003\023\031+hn\031;j_:\024\004bBA$+\021\005\021\021J\001\013e\026$WoY3XSRDGc\001$\002L!A\0211BA#\001\004\ty\004\003\004\002PU!\teT\001\005g&TX\rC\004\002TU!\t!!\026\002\025A\024Xm]3sm&tw-\006\003\002X\005mC\003BA-\003;\0022\001HA.\t\031q\022\021\013b\001?!I\021qLA)\t\003\007\021\021M\001\007C\016$\030n\0348\021\013\005\n\031'!\027\n\007\005\025dA\001\005=Eft\027-\\3?\021\035\tI'\006C!\003W\nq![:F[B$\0300\006\002\002nA\031\021%a\034\n\007\005EdAA\004C_>dW-\0318\t\017\005UT\003\"\001\002x\005A\021\016^3sCR|'/\006\002\002zA)\021\021EA>7%\031\021Q\020\003\003\021%#XM]1u_JDq!!!\026\t\003\n\031)A\004g_J,\027m\0315\026\t\005\025\025Q\022\013\004\r\006\035\005\002CA\006\003\002\r!!#\021\r\005\nyaGAF!\ra\022Q\022\003\b\003\037\013yH1\001 \005\005)\006BBAJ+\021\005s,A\003dY>tW\rK\003\026\003/\013i\nE\002\"\0033K1!a'\007\005A\031VM]5bYZ+'o]5p]VKEI\b\005w;\0307H\034(Y_\021\031Q6\002\"\001\002\"R\t\021\002C\004\002&.!\031!a*\002\031\r\fgNQ;jY\0224%o\\7\026\t\005%\026qX\013\003\003W\003\022bDAW\003c\013i,a1\n\007\005=\006C\001\007DC:\024U/\0337e\rJ|W\016\005\003\0024\006UV\"A\006\n\t\005]\026\021\030\002\005\007>dG.C\002\002<B\021\001cR3oKJL7mQ8na\006t\027n\0348\021\007q\ty\fB\004\002B\006\r&\031A\020\003\003\005\003BAC\013\002>\"9\021qY\006\005\002\005%\027A\0038fo\n+\030\016\0343feV!\0211ZAi+\t\ti\r\005\004\013k\005=\0271\033\t\0049\005EGaBAa\003\013\024\ra\b\t\005\025U\ty\rC\004\002X.!\t!!7\002\013\025l\007\017^=\026\005\005m\007c\001\006\026A!1\021m\003C\001\003?,B!!9\002jR!\0211]A~)\021\t)/a;\021\t))\022q\035\t\0049\005%HaBAa\003;\024\ra\b\005\013\003[\fi.!AA\004\005=\030AC3wS\022,gnY3%cA1\021\021_A|\003Ol!!a=\013\007\005Uh!A\004sK\032dWm\031;\n\t\005e\0301\037\002\t\0072\f7o\035+bO\"A\021Q`Ao\001\004\ty0A\003fY\026l7\017E\003\"\005\003\t9/C\002\003\004\031\021!\002\020:fa\026\fG/\0323?\021!\0219a\003C\001\005\t%\021!C4s_^\f%O]1z)\ri$1\002\005\007k\n\025\001\031A\037\t\021\005M5\002\"\001\003\005\037!2!\020B\t\021\031)(Q\002a\001{!I!QC\006\002\002\023%!qC\001\fe\026\fGMU3t_24X\r\006\002\003\032A!!1\004B\023\033\t\021iB\003\003\003 \t\005\022\001\0027b]\036T!Aa\t\002\t)\fg/Y\005\005\005O\021iB\001\004PE*,7\r\036")
/*     */ public class ArrayStack<T> extends AbstractSeq<T> implements Seq<T>, SeqLike<T, ArrayStack<T>>, GenericTraversableTemplate<T, ArrayStack>, Cloneable<ArrayStack<T>>, Builder<T, ArrayStack<T>>, Serializable {
/*     */   public static final long serialVersionUID = 8565219180626620510L;
/*     */   
/*     */   private Object[] scala$collection$mutable$ArrayStack$$table;
/*     */   
/*     */   private int scala$collection$mutable$ArrayStack$$index;
/*     */   
/*     */   public static class ArrayStack$$anonfun$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Object apply(Object x$1) {
/*  27 */       return x$1;
/*     */     }
/*     */   }
/*     */   
/*     */   public void sizeHint(int size) {
/*  64 */     Builder$class.sizeHint(this, size);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/*  64 */     Builder$class.sizeHint(this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/*  64 */     Builder$class.sizeHint(this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  64 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<T, NewTo> mapResult(Function1 f) {
/*  64 */     return Builder$class.mapResult(this, f);
/*     */   }
/*     */   
/*     */   public Growable<T> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  64 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Object[] scala$collection$mutable$ArrayStack$$table() {
/*  64 */     return this.scala$collection$mutable$ArrayStack$$table;
/*     */   }
/*     */   
/*     */   private void scala$collection$mutable$ArrayStack$$table_$eq(Object[] x$1) {
/*  64 */     this.scala$collection$mutable$ArrayStack$$table = x$1;
/*     */   }
/*     */   
/*     */   public ArrayStack(Object[] table, int index) {
/*  64 */     Growable.class.$init$(this);
/*  64 */     Builder$class.$init$(this);
/*     */   }
/*     */   
/*     */   public int scala$collection$mutable$ArrayStack$$index() {
/*  65 */     return this.scala$collection$mutable$ArrayStack$$index;
/*     */   }
/*     */   
/*     */   private void scala$collection$mutable$ArrayStack$$index_$eq(int x$1) {
/*  65 */     this.scala$collection$mutable$ArrayStack$$index = x$1;
/*     */   }
/*     */   
/*     */   public ArrayStack() {
/*  74 */     this(new Object[1], 0);
/*     */   }
/*     */   
/*     */   public T apply(int n) {
/*  85 */     return (T)scala$collection$mutable$ArrayStack$$table()[scala$collection$mutable$ArrayStack$$index() - 1 - n];
/*     */   }
/*     */   
/*     */   public int length() {
/*  88 */     return scala$collection$mutable$ArrayStack$$index();
/*     */   }
/*     */   
/*     */   public ArrayStack$ companion() {
/*  90 */     return ArrayStack$.MODULE$;
/*     */   }
/*     */   
/*     */   public void update(int n, Object newelem) {
/* 101 */     scala$collection$mutable$ArrayStack$$table()[scala$collection$mutable$ArrayStack$$index() - 1 - n] = newelem;
/*     */   }
/*     */   
/*     */   public void push(Object x) {
/* 108 */     if (scala$collection$mutable$ArrayStack$$index() == (scala$collection$mutable$ArrayStack$$table()).length)
/* 108 */       scala$collection$mutable$ArrayStack$$table_$eq(ArrayStack$.MODULE$.growArray(scala$collection$mutable$ArrayStack$$table())); 
/* 109 */     scala$collection$mutable$ArrayStack$$table()[scala$collection$mutable$ArrayStack$$index()] = x;
/* 110 */     scala$collection$mutable$ArrayStack$$index_$eq(scala$collection$mutable$ArrayStack$$index() + 1);
/*     */   }
/*     */   
/*     */   public T pop() {
/* 118 */     if (scala$collection$mutable$ArrayStack$$index() == 0)
/* 118 */       throw package$.MODULE$.error("Stack empty"); 
/* 119 */     scala$collection$mutable$ArrayStack$$index_$eq(scala$collection$mutable$ArrayStack$$index() - 1);
/* 120 */     Object x = scala$collection$mutable$ArrayStack$$table()[scala$collection$mutable$ArrayStack$$index()];
/* 121 */     scala$collection$mutable$ArrayStack$$table()[scala$collection$mutable$ArrayStack$$index()] = null;
/* 122 */     return (T)x;
/*     */   }
/*     */   
/*     */   public T top() {
/* 132 */     return (T)scala$collection$mutable$ArrayStack$$table()[scala$collection$mutable$ArrayStack$$index() - 1];
/*     */   }
/*     */   
/*     */   public void dup() {
/* 140 */     push(top());
/*     */   }
/*     */   
/*     */   public void clear() {
/* 144 */     scala$collection$mutable$ArrayStack$$index_$eq(0);
/* 145 */     scala$collection$mutable$ArrayStack$$table_$eq(new Object[1]);
/*     */   }
/*     */   
/*     */   public void drain(Function1 f) {
/*     */     while (true) {
/* 153 */       if (isEmpty())
/*     */         return; 
/* 153 */       f.apply(pop());
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayStack<T> $plus$plus$eq(TraversableOnce xs) {
/* 160 */     xs.seq().foreach((Function1)new ArrayStack$$anonfun$$plus$plus$eq$1(this));
/* 160 */     return this;
/*     */   }
/*     */   
/*     */   public class ArrayStack$$anonfun$$plus$plus$eq$1 extends AbstractFunction1<T, ArrayStack<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ArrayStack<T> apply(Object x) {
/* 160 */       return this.$outer.$plus$eq((T)x);
/*     */     }
/*     */     
/*     */     public ArrayStack$$anonfun$$plus$plus$eq$1(ArrayStack $outer) {}
/*     */   }
/*     */   
/*     */   public ArrayStack<T> $plus$eq(Object x) {
/* 167 */     push((T)x);
/* 167 */     return this;
/*     */   }
/*     */   
/*     */   public ArrayStack<T> result() {
/* 170 */     reverseTable();
/* 171 */     return this;
/*     */   }
/*     */   
/*     */   private void reverseTable() {
/* 175 */     int i = 0;
/* 176 */     int until = scala$collection$mutable$ArrayStack$$index() / 2;
/* 177 */     while (i < until) {
/* 178 */       int revi = scala$collection$mutable$ArrayStack$$index() - i - 1;
/* 179 */       Object tmp = scala$collection$mutable$ArrayStack$$table()[i];
/* 180 */       scala$collection$mutable$ArrayStack$$table()[i] = scala$collection$mutable$ArrayStack$$table()[revi];
/* 181 */       scala$collection$mutable$ArrayStack$$table()[revi] = tmp;
/* 182 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void combine(Function2 f) {
/* 193 */     push((T)f.apply(pop(), pop()));
/*     */   }
/*     */   
/*     */   public void reduceWith(Function2<T, T, T> f) {
/* 200 */     for (; size() > 1; combine(f));
/*     */   }
/*     */   
/*     */   public int size() {
/* 202 */     return scala$collection$mutable$ArrayStack$$index();
/*     */   }
/*     */   
/*     */   public <T> T preserving(Function0 action) {
/* 211 */     int oldIndex = scala$collection$mutable$ArrayStack$$index();
/* 212 */     Object[] oldTable = ArrayStack$.MODULE$.clone(scala$collection$mutable$ArrayStack$$table());
/*     */     try {
/* 215 */       return (T)action.apply();
/*     */     } finally {
/* 217 */       scala$collection$mutable$ArrayStack$$index_$eq(oldIndex);
/* 218 */       scala$collection$mutable$ArrayStack$$table_$eq(oldTable);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 222 */     return (scala$collection$mutable$ArrayStack$$index() == 0);
/*     */   }
/*     */   
/*     */   public Iterator<T> iterator() {
/* 227 */     return (Iterator<T>)new ArrayStack$$anon$1(this);
/*     */   }
/*     */   
/*     */   public class ArrayStack$$anon$1 extends AbstractIterator<T> {
/*     */     private int currentIndex;
/*     */     
/*     */     public ArrayStack$$anon$1(ArrayStack $outer) {
/* 228 */       this.currentIndex = $outer.scala$collection$mutable$ArrayStack$$index();
/*     */     }
/*     */     
/*     */     private int currentIndex() {
/* 228 */       return this.currentIndex;
/*     */     }
/*     */     
/*     */     private void currentIndex_$eq(int x$1) {
/* 228 */       this.currentIndex = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 229 */       return (currentIndex() > 0);
/*     */     }
/*     */     
/*     */     public T next() {
/* 231 */       currentIndex_$eq(currentIndex() - 1);
/* 232 */       return (T)this.$outer.scala$collection$mutable$ArrayStack$$table()[currentIndex()];
/*     */     }
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/* 237 */     int currentIndex = scala$collection$mutable$ArrayStack$$index();
/* 238 */     while (currentIndex > 0) {
/* 239 */       currentIndex--;
/* 240 */       f.apply(scala$collection$mutable$ArrayStack$$table()[currentIndex]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayStack<T> clone() {
/* 244 */     return new ArrayStack(ArrayStack$.MODULE$.clone(scala$collection$mutable$ArrayStack$$table()), scala$collection$mutable$ArrayStack$$index());
/*     */   }
/*     */   
/*     */   public static ArrayStack<Nothing$> empty() {
/*     */     return ArrayStack$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<ArrayStack<?>, A, ArrayStack<A>> canBuildFrom() {
/*     */     return ArrayStack$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Some<ArrayStack<A>> unapplySeq(ArrayStack<A> paramArrayStack) {
/*     */     return ArrayStack$.MODULE$.unapplySeq(paramArrayStack);
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*     */     return (ArrayStack<A>)ArrayStack$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> ArrayStack<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return (ArrayStack<T>)ArrayStack$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <T> ArrayStack<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*     */     return (ArrayStack<T>)ArrayStack$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<ArrayStack<ArrayStack<ArrayStack<ArrayStack<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*     */     return (ArrayStack<ArrayStack<ArrayStack<ArrayStack<ArrayStack<A>>>>>)ArrayStack$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<ArrayStack<ArrayStack<ArrayStack<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*     */     return (ArrayStack<ArrayStack<ArrayStack<ArrayStack<A>>>>)ArrayStack$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<ArrayStack<ArrayStack<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*     */     return (ArrayStack<ArrayStack<ArrayStack<A>>>)ArrayStack$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<ArrayStack<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*     */     return (ArrayStack<ArrayStack<A>>)ArrayStack$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*     */     return (ArrayStack<A>)ArrayStack$.MODULE$.tabulate(paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<ArrayStack<ArrayStack<ArrayStack<ArrayStack<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*     */     return (ArrayStack<ArrayStack<ArrayStack<ArrayStack<ArrayStack<A>>>>>)ArrayStack$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<ArrayStack<ArrayStack<ArrayStack<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*     */     return (ArrayStack<ArrayStack<ArrayStack<ArrayStack<A>>>>)ArrayStack$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<ArrayStack<ArrayStack<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*     */     return (ArrayStack<ArrayStack<ArrayStack<A>>>)ArrayStack$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<ArrayStack<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*     */     return (ArrayStack<ArrayStack<A>>)ArrayStack$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<A> fill(int paramInt, Function0<A> paramFunction0) {
/*     */     return (ArrayStack<A>)ArrayStack$.MODULE$.fill(paramInt, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<A> concat(Seq<Traversable<A>> paramSeq) {
/*     */     return (ArrayStack<A>)ArrayStack$.MODULE$.concat(paramSeq);
/*     */   }
/*     */   
/*     */   public static GenTraversableFactory<ArrayStack>.GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*     */     return ArrayStack$.MODULE$.ReusableCBF();
/*     */   }
/*     */   
/*     */   public static <A> ArrayStack<A> empty() {
/*     */     return (ArrayStack<A>)ArrayStack$.MODULE$.empty();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ArrayStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */