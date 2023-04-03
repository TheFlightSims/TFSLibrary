/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Function3;
/*    */ import scala.Function4;
/*    */ import scala.Function5;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableView;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.LinearSeq;
/*    */ import scala.collection.LinearSeqLike;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericTraversableTemplate;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.math.Integral;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001=4A!\001\002\001\023\t\001Bi\\;cY\026d\025N\\6fI2K7\017\036\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013#M1\001aC\016\037K%\0022\001D\007\020\033\005\021\021B\001\b\003\005-\t%m\035;sC\016$8+Z9\021\005A\tB\002\001\003\006%\001\021\ra\005\002\002\003F\021A\003\007\t\003+Yi\021AB\005\003/\031\021qAT8uQ&tw\r\005\002\0263%\021!D\002\002\004\003:L\bc\001\007\035\037%\021QD\001\002\n\031&tW-\031:TKF\004Ba\b\022\020I5\t\001E\003\002\"\t\0059q-\0328fe&\034\027BA\022!\005i9UM\\3sS\016$&/\031<feN\f'\r\\3UK6\004H.\031;f!\ta\001\001\005\003\rM=A\023BA\024\003\005Q!u.\0362mK2Kgn[3e\031&\034H\017T5lKB\031A\002A\b\021\005UQ\023BA\026\007\0051\031VM]5bY&T\030M\0317f\021\025i\003\001\"\001/\003\031a\024N\\5u}Q\t\001\006C\003.\001\021\005\001\007F\002)cMBQAM\030A\002=\tA!\0327f[\")Ag\fa\001Q\005!a.\032=u\021\0251\004\001\"\0218\003%\031w.\0349b]&|g.F\0019!\ry\022\bJ\005\003u\001\022\001cR3oKJL7mQ8na\006t\027n\0348\t\013q\002A\021\t\030\002\013\rdwN\\3)\007\001q\024\t\005\002\026%\021\001I\002\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\002Bd|-W+1U8U\004\006\007\nA\t\001R\001\021\t>,(\r\\3MS:\\W\r\032'jgR\004\"\001D#\007\013\005\021\001\022\001$\024\007\025;\025\006E\002 \021\022J!!\023\021\003\025M+\027OR1di>\024\030\020C\003.\013\022\0051\nF\001E\021\025iU\tb\001O\0031\031\027M\034\"vS2$gI]8n+\ty\005,F\001Q!\025y\022kU,Z\023\t\021\006E\001\007DC:\024U/\0337e\rJ|W\016\005\002U+6\tQ)\003\002Ws\t!1i\0347m!\t\001\002\fB\003\023\031\n\0071\003E\002\r\001]CQaW#\005\002q\013!B\\3x\005VLG\016Z3s+\ti&-F\001_!\021aq,Y2\n\005\001\024!a\002\"vS2$WM\035\t\003!\t$QA\005.C\002M\0012\001\004\001b\021\035)W)!A\005\n\031\f1B]3bIJ+7o\0347wKR\tq\r\005\002i[6\t\021N\003\002kW\006!A.\0318h\025\005a\027\001\0026bm\006L!A\\5\003\r=\023'.Z2u\001")
/*    */ public class DoubleLinkedList<A> extends AbstractSeq<A> implements LinearSeq<A>, GenericTraversableTemplate<A, DoubleLinkedList>, DoubleLinkedListLike<A, DoubleLinkedList<A>>, Serializable {
/*    */   public static final long serialVersionUID = -8144992287952814767L;
/*    */   
/*    */   private Seq prev;
/*    */   
/*    */   private Object elem;
/*    */   
/*    */   private Seq next;
/*    */   
/*    */   public DoubleLinkedList<A> prev() {
/* 44 */     return (DoubleLinkedList<A>)this.prev;
/*    */   }
/*    */   
/*    */   public void prev_$eq(Seq x$1) {
/* 44 */     this.prev = x$1;
/*    */   }
/*    */   
/*    */   public void scala$collection$mutable$DoubleLinkedListLike$$super$insert(Seq that) {
/* 44 */     LinkedListLike$class.insert(this, that);
/*    */   }
/*    */   
/*    */   public DoubleLinkedList<A> append(Seq that) {
/* 44 */     return (DoubleLinkedList<A>)DoubleLinkedListLike$class.append(this, that);
/*    */   }
/*    */   
/*    */   public void insert(Seq that) {
/* 44 */     DoubleLinkedListLike$class.insert(this, that);
/*    */   }
/*    */   
/*    */   public void remove() {
/* 44 */     DoubleLinkedListLike$class.remove(this);
/*    */   }
/*    */   
/*    */   public DoubleLinkedList<A> drop(int n) {
/* 44 */     return (DoubleLinkedList<A>)DoubleLinkedListLike$class.drop(this, n);
/*    */   }
/*    */   
/*    */   public DoubleLinkedList<A> tail() {
/* 44 */     return (DoubleLinkedList<A>)DoubleLinkedListLike$class.tail(this);
/*    */   }
/*    */   
/*    */   public A apply(int n) {
/* 44 */     return (A)DoubleLinkedListLike$class.apply(this, n);
/*    */   }
/*    */   
/*    */   public void update(int n, Object x) {
/* 44 */     DoubleLinkedListLike$class.update(this, n, x);
/*    */   }
/*    */   
/*    */   public Option<A> get(int n) {
/* 44 */     return DoubleLinkedListLike$class.get(this, n);
/*    */   }
/*    */   
/*    */   public A elem() {
/* 44 */     return (A)this.elem;
/*    */   }
/*    */   
/*    */   public void elem_$eq(Object x$1) {
/* 44 */     this.elem = x$1;
/*    */   }
/*    */   
/*    */   public DoubleLinkedList<A> next() {
/* 44 */     return (DoubleLinkedList<A>)this.next;
/*    */   }
/*    */   
/*    */   public void next_$eq(Seq x$1) {
/* 44 */     this.next = x$1;
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 44 */     return LinkedListLike$class.isEmpty(this);
/*    */   }
/*    */   
/*    */   public int length() {
/* 44 */     return LinkedListLike$class.length(this);
/*    */   }
/*    */   
/*    */   public A head() {
/* 44 */     return (A)LinkedListLike$class.head(this);
/*    */   }
/*    */   
/*    */   public Iterator<A> iterator() {
/* 44 */     return LinkedListLike$class.iterator(this);
/*    */   }
/*    */   
/*    */   public <B> void foreach(Function1 f) {
/* 44 */     LinkedListLike$class.foreach(this, f);
/*    */   }
/*    */   
/*    */   public LinearSeq<A> seq() {
/* 44 */     return LinearSeq$class.seq(this);
/*    */   }
/*    */   
/*    */   public LinearSeq<A> thisCollection() {
/* 44 */     return LinearSeqLike.class.thisCollection(this);
/*    */   }
/*    */   
/*    */   public LinearSeq<A> toCollection(LinearSeqLike repr) {
/* 44 */     return LinearSeqLike.class.toCollection(this, repr);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 44 */     return LinearSeqLike.class.hashCode(this);
/*    */   }
/*    */   
/*    */   public final <B> boolean corresponds(GenSeq that, Function2 p) {
/* 44 */     return LinearSeqLike.class.corresponds(this, that, p);
/*    */   }
/*    */   
/*    */   public DoubleLinkedList() {
/* 44 */     LinearSeqLike.class.$init$(this);
/* 44 */     LinearSeq.class.$init$(this);
/* 44 */     LinearSeq$class.$init$(this);
/* 44 */     LinkedListLike$class.$init$(this);
/* 44 */     DoubleLinkedListLike$class.$init$(this);
/* 49 */     next_$eq(this);
/*    */   }
/*    */   
/*    */   public DoubleLinkedList(Object elem, DoubleLinkedList<A> next) {
/* 57 */     this();
/* 58 */     if (next != null) {
/* 59 */       elem_$eq((A)elem);
/* 60 */       next_$eq(next);
/* 61 */       next().prev_$eq(this);
/*    */     } 
/*    */   }
/*    */   
/*    */   public GenericCompanion<DoubleLinkedList> companion() {
/* 65 */     return (GenericCompanion<DoubleLinkedList>)DoubleLinkedList$.MODULE$;
/*    */   }
/*    */   
/*    */   public DoubleLinkedList<A> clone() {
/* 69 */     Builder builder = newBuilder();
/* 70 */     builder.$plus$plus$eq((TraversableOnce)this);
/* 71 */     return (DoubleLinkedList<A>)builder.result();
/*    */   }
/*    */   
/*    */   public static <A> CanBuildFrom<DoubleLinkedList<?>, A, DoubleLinkedList<A>> canBuildFrom() {
/*    */     return DoubleLinkedList$.MODULE$.canBuildFrom();
/*    */   }
/*    */   
/*    */   public static <A> Some<DoubleLinkedList<A>> unapplySeq(DoubleLinkedList<A> paramDoubleLinkedList) {
/*    */     return DoubleLinkedList$.MODULE$.unapplySeq(paramDoubleLinkedList);
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*    */     return (DoubleLinkedList<A>)DoubleLinkedList$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*    */   }
/*    */   
/*    */   public static <T> DoubleLinkedList<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*    */     return (DoubleLinkedList<T>)DoubleLinkedList$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*    */   }
/*    */   
/*    */   public static <T> DoubleLinkedList<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*    */     return (DoubleLinkedList<T>)DoubleLinkedList$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*    */     return (DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<A>>>>>)DoubleLinkedList$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*    */     return (DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<A>>>>)DoubleLinkedList$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*    */     return (DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<A>>>)DoubleLinkedList$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<DoubleLinkedList<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*    */     return (DoubleLinkedList<DoubleLinkedList<A>>)DoubleLinkedList$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*    */     return (DoubleLinkedList<A>)DoubleLinkedList$.MODULE$.tabulate(paramInt, paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*    */     return (DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<A>>>>>)DoubleLinkedList$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*    */     return (DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<A>>>>)DoubleLinkedList$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*    */     return (DoubleLinkedList<DoubleLinkedList<DoubleLinkedList<A>>>)DoubleLinkedList$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<DoubleLinkedList<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*    */     return (DoubleLinkedList<DoubleLinkedList<A>>)DoubleLinkedList$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<A> fill(int paramInt, Function0<A> paramFunction0) {
/*    */     return (DoubleLinkedList<A>)DoubleLinkedList$.MODULE$.fill(paramInt, paramFunction0);
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<A> concat(Seq<Traversable<A>> paramSeq) {
/*    */     return (DoubleLinkedList<A>)DoubleLinkedList$.MODULE$.concat(paramSeq);
/*    */   }
/*    */   
/*    */   public static GenTraversableFactory<DoubleLinkedList>.GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*    */     return DoubleLinkedList$.MODULE$.ReusableCBF();
/*    */   }
/*    */   
/*    */   public static <A> DoubleLinkedList<A> empty() {
/*    */     return (DoubleLinkedList<A>)DoubleLinkedList$.MODULE$.empty();
/*    */   }
/*    */   
/*    */   public static class DoubleLinkedList$$anon$1 implements Builder<A, DoubleLinkedList<A>> {
/*    */     private DoubleLinkedList<A> current;
/*    */     
/*    */     public void sizeHint(int size) {
/* 84 */       Builder$class.sizeHint(this, size);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll) {
/* 84 */       Builder$class.sizeHint(this, coll);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll, int delta) {
/* 84 */       Builder$class.sizeHint(this, coll, delta);
/*    */     }
/*    */     
/*    */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 84 */       Builder$class.sizeHintBounded(this, size, boundingColl);
/*    */     }
/*    */     
/*    */     public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/* 84 */       return Builder$class.mapResult(this, f);
/*    */     }
/*    */     
/*    */     public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 84 */       return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Growable<A> $plus$plus$eq(TraversableOnce xs) {
/* 84 */       return Growable.class.$plus$plus$eq(this, xs);
/*    */     }
/*    */     
/*    */     public DoubleLinkedList$$anon$1() {
/* 84 */       Growable.class.$init$(this);
/* 84 */       Builder$class.$init$(this);
/* 86 */       this.current = emptyList();
/*    */     }
/*    */     
/*    */     private DoubleLinkedList<A> emptyList() {
/*    */       return new DoubleLinkedList<A>();
/*    */     }
/*    */     
/*    */     private DoubleLinkedList<A> current() {
/* 86 */       return this.current;
/*    */     }
/*    */     
/*    */     private void current_$eq(DoubleLinkedList<A> x$1) {
/* 86 */       this.current = x$1;
/*    */     }
/*    */     
/*    */     public DoubleLinkedList$$anon$1 $plus$eq(Object elem) {
/* 90 */       current_$eq(new DoubleLinkedList<A>((A)elem, emptyList()));
/* 90 */       current().isEmpty() ? BoxedUnit.UNIT : 
/*    */         
/* 92 */         current().append(new DoubleLinkedList<A>((A)elem, emptyList()));
/* 94 */       return this;
/*    */     }
/*    */     
/*    */     public void clear() {
/* 97 */       current_$eq(emptyList());
/*    */     }
/*    */     
/*    */     public DoubleLinkedList<A> result() {
/* 98 */       return current();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\DoubleLinkedList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */