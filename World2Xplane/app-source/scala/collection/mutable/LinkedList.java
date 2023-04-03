/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Function3;
/*     */ import scala.Function4;
/*     */ import scala.Function5;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
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
/*     */ import scala.math.Integral;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001Q4A!\001\002\001\023\tQA*\0338lK\022d\025n\035;\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\003\025E\031b\001A\006\034=\025J\003c\001\007\016\0375\t!!\003\002\017\005\tY\021IY:ue\006\034GoU3r!\t\001\022\003\004\001\005\013I\001!\031A\n\003\003\005\013\"\001\006\r\021\005U1R\"\001\004\n\005]1!a\002(pi\"Lgn\032\t\003+eI!A\007\004\003\007\005s\027\020E\002\r9=I!!\b\002\003\0231Kg.Z1s'\026\f\b\003B\020#\037\021j\021\001\t\006\003C\021\tqaZ3oKJL7-\003\002$A\tQr)\0328fe&\034GK]1wKJ\034\030M\0317f)\026l\007\017\\1uKB\021A\002\001\t\005\031\031z\001&\003\002(\005\tqA*\0338lK\022d\025n\035;MS.,\007c\001\007\001\037A\021QCK\005\003W\031\021AbU3sS\006d\027N_1cY\026DQ!\f\001\005\0029\na\001P5oSRtD#\001\025\t\0135\002A\021\001\031\025\007!\n4\007C\0033_\001\007q\"\001\003fY\026l\007\"\002\0330\001\004A\023\001\0028fqRDQA\016\001\005B]\n\021bY8na\006t\027n\0348\026\003a\0022aH\035%\023\tQ\004E\001\tHK:,'/[2D_6\004\030M\\5p]\"\032\001\001P \021\005Ui\024B\001 \007\005A\031VM]5bYZ+'o]5p]VKEI\b\005\033('8\035\"%nR\017\025\t%\001#\001C\003)a\025N\\6fI2K7\017\036\t\003\031\r3Q!\001\002\t\002\021\0332aQ#*!\ryb\tJ\005\003\017\002\022!bU3r\r\006\034Go\034:z\021\025i3\t\"\001J)\005\021\005\"B&D\t\003b\025!B3naRLXCA'Q+\005q\005c\001\007\001\037B\021\001\003\025\003\006%)\023\ra\005\005\006%\016#\031aU\001\rG\006t')^5mI\032\023x.\\\013\003)v+\022!\026\t\006?YCFLX\005\003/\002\022AbQ1o\005VLG\016\032$s_6\004\"!\027.\016\003\rK!aW\035\003\t\r{G\016\034\t\003!u#QAE)C\002M\0012\001\004\001]\021\025\0017\t\"\001b\003)qWm\036\"vS2$WM]\013\003E\036,\022a\031\t\005\031\0214\007.\003\002f\005\t9!)^5mI\026\024\bC\001\th\t\025\021rL1\001\024!\ra\001A\032\005\bU\016\013\t\021\"\003l\003-\021X-\0313SKN|GN^3\025\0031\004\"!\034:\016\0039T!a\0349\002\t1\fgn\032\006\002c\006!!.\031<b\023\t\031hN\001\004PE*,7\r\036")
/*     */ public class LinkedList<A> extends AbstractSeq<A> implements LinearSeq<A>, GenericTraversableTemplate<A, LinkedList>, LinkedListLike<A, LinkedList<A>>, Serializable {
/*     */   public static final long serialVersionUID = -7308240733518833071L;
/*     */   
/*     */   private Object elem;
/*     */   
/*     */   private Seq next;
/*     */   
/*     */   public A elem() {
/*  78 */     return (A)this.elem;
/*     */   }
/*     */   
/*     */   public void elem_$eq(Object x$1) {
/*  78 */     this.elem = x$1;
/*     */   }
/*     */   
/*     */   public LinkedList<A> next() {
/*  78 */     return (LinkedList<A>)this.next;
/*     */   }
/*     */   
/*     */   public void next_$eq(Seq x$1) {
/*  78 */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  78 */     return LinkedListLike$class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public int length() {
/*  78 */     return LinkedListLike$class.length(this);
/*     */   }
/*     */   
/*     */   public A head() {
/*  78 */     return (A)LinkedListLike$class.head(this);
/*     */   }
/*     */   
/*     */   public LinkedList<A> tail() {
/*  78 */     return (LinkedList<A>)LinkedListLike$class.tail(this);
/*     */   }
/*     */   
/*     */   public LinkedList<A> append(Seq that) {
/*  78 */     return (LinkedList<A>)LinkedListLike$class.append(this, that);
/*     */   }
/*     */   
/*     */   public void insert(Seq that) {
/*  78 */     LinkedListLike$class.insert(this, that);
/*     */   }
/*     */   
/*     */   public LinkedList<A> drop(int n) {
/*  78 */     return (LinkedList<A>)LinkedListLike$class.drop(this, n);
/*     */   }
/*     */   
/*     */   public A apply(int n) {
/*  78 */     return (A)LinkedListLike$class.apply(this, n);
/*     */   }
/*     */   
/*     */   public void update(int n, Object x) {
/*  78 */     LinkedListLike$class.update(this, n, x);
/*     */   }
/*     */   
/*     */   public Option<A> get(int n) {
/*  78 */     return LinkedListLike$class.get(this, n);
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/*  78 */     return LinkedListLike$class.iterator(this);
/*     */   }
/*     */   
/*     */   public <B> void foreach(Function1 f) {
/*  78 */     LinkedListLike$class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public LinkedList<A> clone() {
/*  78 */     return (LinkedList<A>)LinkedListLike$class.clone(this);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> seq() {
/*  78 */     return LinearSeq$class.seq(this);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> thisCollection() {
/*  78 */     return LinearSeqLike.class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> toCollection(LinearSeqLike repr) {
/*  78 */     return LinearSeqLike.class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  78 */     return LinearSeqLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public final <B> boolean corresponds(GenSeq that, Function2 p) {
/*  78 */     return LinearSeqLike.class.corresponds(this, that, p);
/*     */   }
/*     */   
/*     */   public LinkedList() {
/*  78 */     LinearSeqLike.class.$init$(this);
/*  78 */     LinearSeq.class.$init$(this);
/*  78 */     LinearSeq$class.$init$(this);
/*  78 */     LinkedListLike$class.$init$(this);
/*  83 */     next_$eq(this);
/*     */   }
/*     */   
/*     */   public LinkedList(Object elem, LinkedList<A> next) {
/* 101 */     this();
/* 102 */     if (next != null) {
/* 103 */       elem_$eq((A)elem);
/* 104 */       next_$eq(next);
/*     */     } 
/*     */   }
/*     */   
/*     */   public GenericCompanion<LinkedList> companion() {
/* 108 */     return (GenericCompanion<LinkedList>)LinkedList$.MODULE$;
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<LinkedList<?>, A, LinkedList<A>> canBuildFrom() {
/*     */     return LinkedList$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<A> empty() {
/*     */     return LinkedList$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public static <A> Some<LinkedList<A>> unapplySeq(LinkedList<A> paramLinkedList) {
/*     */     return LinkedList$.MODULE$.unapplySeq(paramLinkedList);
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*     */     return (LinkedList<A>)LinkedList$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> LinkedList<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return (LinkedList<T>)LinkedList$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <T> LinkedList<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*     */     return (LinkedList<T>)LinkedList$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<LinkedList<LinkedList<LinkedList<LinkedList<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*     */     return (LinkedList<LinkedList<LinkedList<LinkedList<LinkedList<A>>>>>)LinkedList$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<LinkedList<LinkedList<LinkedList<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*     */     return (LinkedList<LinkedList<LinkedList<LinkedList<A>>>>)LinkedList$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<LinkedList<LinkedList<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*     */     return (LinkedList<LinkedList<LinkedList<A>>>)LinkedList$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<LinkedList<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*     */     return (LinkedList<LinkedList<A>>)LinkedList$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*     */     return (LinkedList<A>)LinkedList$.MODULE$.tabulate(paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<LinkedList<LinkedList<LinkedList<LinkedList<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*     */     return (LinkedList<LinkedList<LinkedList<LinkedList<LinkedList<A>>>>>)LinkedList$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<LinkedList<LinkedList<LinkedList<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*     */     return (LinkedList<LinkedList<LinkedList<LinkedList<A>>>>)LinkedList$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<LinkedList<LinkedList<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*     */     return (LinkedList<LinkedList<LinkedList<A>>>)LinkedList$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<LinkedList<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*     */     return (LinkedList<LinkedList<A>>)LinkedList$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<A> fill(int paramInt, Function0<A> paramFunction0) {
/*     */     return (LinkedList<A>)LinkedList$.MODULE$.fill(paramInt, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<A> concat(Seq<Traversable<A>> paramSeq) {
/*     */     return (LinkedList<A>)LinkedList$.MODULE$.concat(paramSeq);
/*     */   }
/*     */   
/*     */   public static GenTraversableFactory<LinkedList>.GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*     */     return LinkedList$.MODULE$.ReusableCBF();
/*     */   }
/*     */   
/*     */   public static <A> LinkedList<A> empty() {
/*     */     return (LinkedList<A>)LinkedList$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public static class LinkedList$$anonfun$newBuilder$1 extends AbstractFunction1<MutableList<A>, LinkedList<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final LinkedList<A> apply(MutableList<A> l) {
/* 120 */       return l.toLinkedList();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\LinkedList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */