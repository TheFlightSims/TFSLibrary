/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenSetLike;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.SetLike;
/*     */ import scala.collection.SortedSet;
/*     */ import scala.collection.SortedSetLike;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.FilterMonadic;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericSetTemplate;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.generic.Sorted;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.mutable.ParSet;
/*     */ import scala.collection.script.Message;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%w!B\001\003\021\003I\021a\002+sK\026\034V\r\036\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001\001\005\002\013\0275\t!AB\003\r\005!\005QBA\004Ue\026,7+\032;\024\007-qQ\007E\002\020%Qi\021\001\005\006\003#\021\tqaZ3oKJL7-\003\002\024!\t9R*\036;bE2,7k\034:uK\022\034V\r\036$bGR|'/\037\t\003\025U1A\001\004\002\001-U\021q#I\n\b+aa\"F\f\0326!\tI\"$D\001\007\023\tYbA\001\004B]f\024VM\032\t\004\025uy\022B\001\020\003\005%\031vN\035;fIN+G\017\005\002!C1\001A!\002\022\026\005\004\031#!A!\022\005\021:\003CA\r&\023\t1cAA\004O_RD\027N\\4\021\005eA\023BA\025\007\005\r\te.\037\t\005\025-zR&\003\002-\005\t91+\032;MS.,\007c\001\006\026?A!q\006M\020.\033\005!\021BA\031\005\0055\031vN\035;fIN+G\017T5lKB\031!bM\020\n\005Q\022!aA*fiB\021\021DN\005\003o\031\021AbU3sS\006d\027N_1cY\026D\001\"O\013\003\006\004%\031AO\001\t_J$WM]5oOV\t1\bE\002=\t~q!!\020\"\017\005y\nU\"A \013\005\001C\021A\002\037s_>$h(C\001\b\023\t\031e!A\004qC\016\\\027mZ3\n\005\0253%\001C(sI\026\024\030N\\4\013\005\r3\001\002\003%\026\005\003\005\013\021B\036\002\023=\024H-\032:j]\036\004\003\"\002&\026\t\003Y\025A\002\037j]&$h\bF\001M)\tiS\nC\003:\023\002\0171\bC\003K+\021%q\n\006\003Q%^SFCA\027R\021\025Id\nq\001<\021\025\031f\n1\001U\003\021\021\027m]3\021\007e)V&\003\002W\r\t1q\n\035;j_:DQ\001\027(A\002e\013AA\032:p[B\031\021$V\020\t\013ms\005\031A-\002\013UtG/\0337\t\017M+\002\031!C\005;V\tA\013C\004`+\001\007I\021\0021\002\021\t\f7/Z0%KF$\"!\0313\021\005e\021\027BA2\007\005\021)f.\033;\t\017\025t\026\021!a\001)\006\031\001\020J\031\t\r\035,\002\025)\003U\003\025\021\027m]3!\021\035AV\0031A\005\n%,\022!\027\005\bWV\001\r\021\"\003m\003!1'o\\7`I\025\fHCA1n\021\035)'.!AA\002eCaa\\\013!B\023I\026!\0024s_6\004\003bB.\026\001\004%I!\033\005\beV\001\r\021\"\003t\003%)h\016^5m?\022*\027\017\006\002bi\"9Q-]A\001\002\004I\006B\002<\026A\003&\021,\001\004v]RLG\016\t\005\bqV\001\r\021\"\003z\003\r\tg\017\\\013\002uB\031!b_\020\n\005q\024!aB!W\031R\023X-\032\005\b}V\001\r\021\"\003\000\003\035\tg\017\\0%KF$2!YA\001\021\035)W0!AA\002iDq!!\002\026A\003&!0\001\003bm2\004\003\"CA\005+\001\007I\021BA\006\003-\031\027M\0353j]\006d\027\016^=\026\005\0055\001cA\r\002\020%\031\021\021\003\004\003\007%sG\017C\005\002\026U\001\r\021\"\003\002\030\005y1-\031:eS:\fG.\033;z?\022*\027\017F\002b\0033A\021\"ZA\n\003\003\005\r!!\004\t\021\005uQ\003)Q\005\003\033\tAbY1sI&t\027\r\\5us\002Bq!!\t\026\t\003\t\031#A\004sKN|GN^3\026\0035Bq!a\n\026\t\023\tI#\001\tjg2+g\r^!dG\026\004H/\0312mKR1\0211FA\034\003s!B!!\f\0024A\031\021$a\f\n\007\005EbAA\004C_>dW-\0318\t\017\005U\022Q\005a\001?\005\t\021\r\003\004Y\003K\001\r!\027\005\007s\005\025\002\031A\036\t\017\005uR\003\"\003\002@\005\t\022n\035*jO\"$\030iY2faR\f'\r\\3\025\r\005\005\023QIA$)\021\ti#a\021\t\017\005U\0221\ba\001?!11,a\017A\002eCa!OA\036\001\004Y\004bBA&+\021\005\0231B\001\005g&TX\rC\004\002PU!\t%!\025\002\031M$(/\0338h!J,g-\033=\026\005\005M\003\003BA+\003?j!!a\026\013\t\005e\0231L\001\005Y\006twM\003\002\002^\005!!.\031<b\023\021\t\t'a\026\003\rM#(/\0338h\021\035\t)'\006C!\003G\tQ!Z7qifDq!!\033\026\t\003\nY'A\005sC:<W-S7qYR)Q&!\034\002p!1\001,a\032A\002eCaaWA4\001\004I\006bBA:+\021\005\023QO\001\nI5Lg.^:%KF$B!a\036\002z5\tQ\003C\004\002|\005E\004\031A\020\002\t\025dW-\034\005\b\003*B\021IAA\003!!\003\017\\;tI\025\fH\003BA<\003\007Cq!a\037\002~\001\007q\004C\004\002\bV!\t%!#\002\013\rdwN\\3\025\0035Bq!!$\026\t\003\ny)\001\005d_:$\030-\0338t)\021\ti#!%\t\017\005m\0241\022a\001?!9\021QS\013\005B\005]\025\001C5uKJ\fGo\034:\026\005\005e\005\003B\030\002\034~I1!!(\005\005!IE/\032:bi>\024\b\002DAQ+\005\005\t\021\"\003\002\f\005\r\026AC:va\026\024He]5{K&!\0211JAS\023\r\t9\013\002\002\020)J\fg/\032:tC\ndWm\0248dK\"1!j\003C\001\003W#\022!\003\005\b\003KZA\021AAX+\021\t\t,a.\025\t\005M\026\021\030\t\005\025U\t)\fE\002!\003o#aAIAW\005\004\031\003bB\035\002.\002\017\0211\030\t\005y\021\013)\fC\005\002@.\t\t\021\"\003\002B\006Y!/Z1e%\026\034x\016\034<f)\t\t\031\r\005\003\002V\005\025\027\002BAd\003/\022aa\0242kK\016$\b")
/*     */ public class TreeSet<A> implements SortedSet<A>, SetLike<A, TreeSet<A>>, SortedSetLike<A, TreeSet<A>>, Set<A>, Serializable {
/*     */   private final Ordering<A> ordering;
/*     */   
/*     */   private Option<TreeSet<A>> base;
/*     */   
/*     */   private Option<A> scala$collection$mutable$TreeSet$$from;
/*     */   
/*     */   private Option<A> scala$collection$mutable$TreeSet$$until;
/*     */   
/*     */   private AVLTree<A> avl;
/*     */   
/*     */   private int scala$collection$mutable$TreeSet$$cardinality;
/*     */   
/*     */   public GenericCompanion<Set> companion() {
/*  37 */     return Set$class.companion(this);
/*     */   }
/*     */   
/*     */   public Set<A> seq() {
/*  37 */     return Set$class.seq(this);
/*     */   }
/*     */   
/*     */   public Builder<A, TreeSet<A>> newBuilder() {
/*  37 */     return SetLike$class.newBuilder(this);
/*     */   }
/*     */   
/*     */   public Combiner<A, ParSet<A>> parCombiner() {
/*  37 */     return SetLike$class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public boolean add(Object elem) {
/*  37 */     return SetLike$class.add(this, elem);
/*     */   }
/*     */   
/*     */   public boolean remove(Object elem) {
/*  37 */     return SetLike$class.remove(this, elem);
/*     */   }
/*     */   
/*     */   public void update(Object elem, boolean included) {
/*  37 */     SetLike$class.update(this, elem, included);
/*     */   }
/*     */   
/*     */   public void retain(Function1 p) {
/*  37 */     SetLike$class.retain(this, p);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  37 */     SetLike$class.clear(this);
/*     */   }
/*     */   
/*     */   public TreeSet<A> result() {
/*  37 */     return (TreeSet<A>)SetLike$class.result(this);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $plus(Object elem) {
/*  37 */     return (TreeSet<A>)SetLike$class.$plus(this, elem);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $plus(Object elem1, Object elem2, Seq elems) {
/*  37 */     return (TreeSet<A>)SetLike$class.$plus(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $plus$plus(GenTraversableOnce xs) {
/*  37 */     return (TreeSet<A>)SetLike$class.$plus$plus(this, xs);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $minus(Object elem) {
/*  37 */     return (TreeSet<A>)SetLike$class.$minus(this, elem);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $minus(Object elem1, Object elem2, Seq elems) {
/*  37 */     return (TreeSet<A>)SetLike$class.$minus(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $minus$minus(GenTraversableOnce xs) {
/*  37 */     return (TreeSet<A>)SetLike$class.$minus$minus(this, xs);
/*     */   }
/*     */   
/*     */   public void $less$less(Message cmd) {
/*  37 */     SetLike$class.$less$less(this, cmd);
/*     */   }
/*     */   
/*     */   public Object scala$collection$mutable$Cloneable$$super$clone() {
/*  37 */     return super.clone();
/*     */   }
/*     */   
/*     */   public Shrinkable<A> $minus$eq(Object elem1, Object elem2, Seq elems) {
/*  37 */     return Shrinkable.class.$minus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Shrinkable<A> $minus$minus$eq(TraversableOnce xs) {
/*  37 */     return Shrinkable.class.$minus$minus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public void sizeHint(int size) {
/*  37 */     Builder$class.sizeHint(this, size);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/*  37 */     Builder$class.sizeHint(this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/*  37 */     Builder$class.sizeHint(this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  37 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/*  37 */     return Builder$class.mapResult(this, f);
/*     */   }
/*     */   
/*     */   public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  37 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Growable<A> $plus$plus$eq(TraversableOnce xs) {
/*  37 */     return Growable.class.$plus$plus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public boolean scala$collection$SortedSetLike$$super$subsetOf(GenSet that) {
/*  37 */     return GenSetLike.class.subsetOf((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public TreeSet<A> keySet() {
/*  37 */     return (TreeSet<A>)SortedSetLike.class.keySet(this);
/*     */   }
/*     */   
/*     */   public A firstKey() {
/*  37 */     return (A)SortedSetLike.class.firstKey(this);
/*     */   }
/*     */   
/*     */   public A lastKey() {
/*  37 */     return (A)SortedSetLike.class.lastKey(this);
/*     */   }
/*     */   
/*     */   public TreeSet<A> from(Object from) {
/*  37 */     return (TreeSet<A>)SortedSetLike.class.from(this, from);
/*     */   }
/*     */   
/*     */   public TreeSet<A> until(Object until) {
/*  37 */     return (TreeSet<A>)SortedSetLike.class.until(this, until);
/*     */   }
/*     */   
/*     */   public TreeSet<A> range(Object from, Object until) {
/*  37 */     return (TreeSet<A>)SortedSetLike.class.range(this, from, until);
/*     */   }
/*     */   
/*     */   public boolean subsetOf(GenSet that) {
/*  37 */     return SortedSetLike.class.subsetOf(this, that);
/*     */   }
/*     */   
/*     */   public int compare(Object k0, Object k1) {
/*  37 */     return Sorted.class.compare((Sorted)this, k0, k1);
/*     */   }
/*     */   
/*     */   public TreeSet<A> to(Object to) {
/*  37 */     return (TreeSet<A>)Sorted.class.to((Sorted)this, to);
/*     */   }
/*     */   
/*     */   public boolean hasAll(Iterator j) {
/*  37 */     return Sorted.class.hasAll((Sorted)this, j);
/*     */   }
/*     */   
/*     */   public Object scala$collection$SetLike$$super$map(Function1 f, CanBuildFrom bf) {
/*  37 */     return TraversableLike.class.map(this, f, bf);
/*     */   }
/*     */   
/*     */   public Seq<A> toSeq() {
/*  37 */     return SetLike.class.toSeq(this);
/*     */   }
/*     */   
/*     */   public <A1> Buffer<A1> toBuffer() {
/*  37 */     return SetLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public <B, That> That map(Function1 f, CanBuildFrom bf) {
/*  37 */     return (That)SetLike.class.map(this, f, bf);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  37 */     return SetLike.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public TreeSet<A> union(GenSet that) {
/*  37 */     return (TreeSet<A>)SetLike.class.union(this, that);
/*     */   }
/*     */   
/*     */   public TreeSet<A> diff(GenSet that) {
/*  37 */     return (TreeSet<A>)SetLike.class.diff(this, that);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> subsets(int len) {
/*  37 */     return SetLike.class.subsets(this, len);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> subsets() {
/*  37 */     return SetLike.class.subsets(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  37 */     return SetLike.class.toString(this);
/*     */   }
/*     */   
/*     */   public boolean apply(Object elem) {
/*  37 */     return GenSetLike.class.apply((GenSetLike)this, elem);
/*     */   }
/*     */   
/*     */   public TreeSet<A> intersect(GenSet that) {
/*  37 */     return (TreeSet<A>)GenSetLike.class.intersect((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $amp(GenSet that) {
/*  37 */     return (TreeSet<A>)GenSetLike.class.$amp((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $bar(GenSet that) {
/*  37 */     return (TreeSet<A>)GenSetLike.class.$bar((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $amp$tilde(GenSet that) {
/*  37 */     return (TreeSet<A>)GenSetLike.class.$amp$tilde((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  37 */     return GenSetLike.class.equals((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  37 */     return GenSetLike.class.hashCode((GenSetLike)this);
/*     */   }
/*     */   
/*     */   public Iterable<A> thisCollection() {
/*  37 */     return IterableLike.class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public Iterable<A> toCollection(Object repr) {
/*  37 */     return IterableLike.class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  37 */     IterableLike.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/*  37 */     return IterableLike.class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/*  37 */     return IterableLike.class.exists(this, p);
/*     */   }
/*     */   
/*     */   public Option<A> find(Function1 p) {
/*  37 */     return IterableLike.class.find(this, p);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/*  37 */     return (B)IterableLike.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/*  37 */     return (B)IterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public Iterable<A> toIterable() {
/*  37 */     return IterableLike.class.toIterable(this);
/*     */   }
/*     */   
/*     */   public Iterator<A> toIterator() {
/*  37 */     return IterableLike.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public A head() {
/*  37 */     return (A)IterableLike.class.head(this);
/*     */   }
/*     */   
/*     */   public TreeSet<A> slice(int from, int until) {
/*  37 */     return (TreeSet<A>)IterableLike.class.slice(this, from, until);
/*     */   }
/*     */   
/*     */   public TreeSet<A> take(int n) {
/*  37 */     return (TreeSet<A>)IterableLike.class.take(this, n);
/*     */   }
/*     */   
/*     */   public TreeSet<A> drop(int n) {
/*  37 */     return (TreeSet<A>)IterableLike.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public TreeSet<A> takeWhile(Function1 p) {
/*  37 */     return (TreeSet<A>)IterableLike.class.takeWhile(this, p);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> grouped(int size) {
/*  37 */     return IterableLike.class.grouped(this, size);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> sliding(int size) {
/*  37 */     return IterableLike.class.sliding(this, size);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> sliding(int size, int step) {
/*  37 */     return IterableLike.class.sliding(this, size, step);
/*     */   }
/*     */   
/*     */   public TreeSet<A> takeRight(int n) {
/*  37 */     return (TreeSet<A>)IterableLike.class.takeRight(this, n);
/*     */   }
/*     */   
/*     */   public TreeSet<A> dropRight(int n) {
/*  37 */     return (TreeSet<A>)IterableLike.class.dropRight(this, n);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start, int len) {
/*  37 */     IterableLike.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  37 */     return (That)IterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  37 */     return (That)IterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*     */   }
/*     */   
/*     */   public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  37 */     return (That)IterableLike.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public <B> boolean sameElements(GenIterable that) {
/*  37 */     return IterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public Stream<A> toStream() {
/*  37 */     return IterableLike.class.toStream(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object that) {
/*  37 */     return IterableLike.class.canEqual(this, that);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  37 */     return IterableLike.class.view(this);
/*     */   }
/*     */   
/*     */   public IterableView<A, TreeSet<A>> view(int from, int until) {
/*  37 */     return IterableLike.class.view(this, from, until);
/*     */   }
/*     */   
/*     */   public <B> Builder<B, Set<B>> genericBuilder() {
/*  37 */     return GenericTraversableTemplate.class.genericBuilder(this);
/*     */   }
/*     */   
/*     */   public <A1, A2> Tuple2<Set<A1>, Set<A2>> unzip(Function1 asPair) {
/*  37 */     return GenericTraversableTemplate.class.unzip(this, asPair);
/*     */   }
/*     */   
/*     */   public <A1, A2, A3> Tuple3<Set<A1>, Set<A2>, Set<A3>> unzip3(Function1 asTriple) {
/*  37 */     return GenericTraversableTemplate.class.unzip3(this, asTriple);
/*     */   }
/*     */   
/*     */   public <B> Set<B> flatten(Function1 asTraversable) {
/*  37 */     return (Set<B>)GenericTraversableTemplate.class.flatten(this, asTraversable);
/*     */   }
/*     */   
/*     */   public <B> Set<Set<B>> transpose(Function1 asTraversable) {
/*  37 */     return (Set<Set<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*     */   }
/*     */   
/*     */   public TreeSet<A> repr() {
/*  37 */     return (TreeSet<A>)TraversableLike.class.repr(this);
/*     */   }
/*     */   
/*     */   public final boolean isTraversableAgain() {
/*  37 */     return TraversableLike.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/*  37 */     return TraversableLike.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  37 */     return (That)TraversableLike.class.$plus$plus(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/*  37 */     return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/*  37 */     return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  37 */     return (That)TraversableLike.class.flatMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public TreeSet<A> filter(Function1 p) {
/*  37 */     return (TreeSet<A>)TraversableLike.class.filter(this, p);
/*     */   }
/*     */   
/*     */   public TreeSet<A> filterNot(Function1 p) {
/*  37 */     return (TreeSet<A>)TraversableLike.class.filterNot(this, p);
/*     */   }
/*     */   
/*     */   public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  37 */     return (That)TraversableLike.class.collect(this, pf, bf);
/*     */   }
/*     */   
/*     */   public Tuple2<TreeSet<A>, TreeSet<A>> partition(Function1 p) {
/*  37 */     return TraversableLike.class.partition(this, p);
/*     */   }
/*     */   
/*     */   public <K> Map<K, TreeSet<A>> groupBy(Function1 f) {
/*  37 */     return TraversableLike.class.groupBy(this, f);
/*     */   }
/*     */   
/*     */   public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/*  37 */     return (That)TraversableLike.class.scan(this, z, op, cbf);
/*     */   }
/*     */   
/*     */   public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  37 */     return (That)TraversableLike.class.scanLeft(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  37 */     return (That)TraversableLike.class.scanRight(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public Option<A> headOption() {
/*  37 */     return TraversableLike.class.headOption(this);
/*     */   }
/*     */   
/*     */   public TreeSet<A> tail() {
/*  37 */     return (TreeSet<A>)TraversableLike.class.tail(this);
/*     */   }
/*     */   
/*     */   public A last() {
/*  37 */     return (A)TraversableLike.class.last(this);
/*     */   }
/*     */   
/*     */   public Option<A> lastOption() {
/*  37 */     return TraversableLike.class.lastOption(this);
/*     */   }
/*     */   
/*     */   public TreeSet<A> init() {
/*  37 */     return (TreeSet<A>)TraversableLike.class.init(this);
/*     */   }
/*     */   
/*     */   public TreeSet<A> sliceWithKnownDelta(int from, int until, int delta) {
/*  37 */     return (TreeSet<A>)TraversableLike.class.sliceWithKnownDelta(this, from, until, delta);
/*     */   }
/*     */   
/*     */   public TreeSet<A> sliceWithKnownBound(int from, int until) {
/*  37 */     return (TreeSet<A>)TraversableLike.class.sliceWithKnownBound(this, from, until);
/*     */   }
/*     */   
/*     */   public TreeSet<A> dropWhile(Function1 p) {
/*  37 */     return (TreeSet<A>)TraversableLike.class.dropWhile(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<TreeSet<A>, TreeSet<A>> span(Function1 p) {
/*  37 */     return TraversableLike.class.span(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<TreeSet<A>, TreeSet<A>> splitAt(int n) {
/*  37 */     return TraversableLike.class.splitAt(this, n);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> tails() {
/*  37 */     return TraversableLike.class.tails(this);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> inits() {
/*  37 */     return TraversableLike.class.inits(this);
/*     */   }
/*     */   
/*     */   public Traversable<A> toTraversable() {
/*  37 */     return TraversableLike.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/*  37 */     return (Col)TraversableLike.class.to(this, cbf);
/*     */   }
/*     */   
/*     */   public FilterMonadic<A, TreeSet<A>> withFilter(Function1 p) {
/*  37 */     return TraversableLike.class.withFilter(this, p);
/*     */   }
/*     */   
/*     */   public ParSet<A> par() {
/*  37 */     return (ParSet<A>)Parallelizable.class.par(this);
/*     */   }
/*     */   
/*     */   public List<A> reversed() {
/*  37 */     return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/*  37 */     return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/*  37 */     return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */   }
/*     */   
/*     */   public <B> Option<B> collectFirst(PartialFunction pf) {
/*  37 */     return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */   }
/*     */   
/*     */   public <B> B $div$colon(Object z, Function2 op) {
/*  37 */     return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B $colon$bslash(Object z, Function2 op) {
/*  37 */     return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/*  37 */     return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/*  37 */     return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceLeftOption(Function2 op) {
/*  37 */     return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceRightOption(Function2 op) {
/*  37 */     return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 reduce(Function2 op) {
/*  37 */     return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> Option<A1> reduceOption(Function2 op) {
/*  37 */     return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 fold(Object z, Function2 op) {
/*  37 */     return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  37 */     return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <B> B sum(Numeric num) {
/*  37 */     return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */   }
/*     */   
/*     */   public <B> B product(Numeric num) {
/*  37 */     return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */   }
/*     */   
/*     */   public <B> A min(Ordering cmp) {
/*  37 */     return (A)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> A max(Ordering cmp) {
/*  37 */     return (A)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> A maxBy(Function1 f, Ordering cmp) {
/*  37 */     return (A)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> A minBy(Function1 f, Ordering cmp) {
/*  37 */     return (A)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> void copyToBuffer(Buffer dest) {
/*  37 */     TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start) {
/*  37 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs) {
/*  37 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */   }
/*     */   
/*     */   public <B> Object toArray(ClassTag evidence$1) {
/*  37 */     return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<A> toList() {
/*  37 */     return TraversableOnce.class.toList((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> toIndexedSeq() {
/*  37 */     return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <B> Set<B> toSet() {
/*  37 */     return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Vector<A> toVector() {
/*  37 */     return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  37 */     return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/*  37 */     return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/*  37 */     return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/*  37 */     return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  37 */     return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String sep) {
/*  37 */     return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b) {
/*  37 */     return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  37 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZD$sp(double v1) {
/*  37 */     return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDD$sp(double v1) {
/*  37 */     return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFD$sp(double v1) {
/*  37 */     return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcID$sp(double v1) {
/*  37 */     return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJD$sp(double v1) {
/*  37 */     return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVD$sp(double v1) {
/*  37 */     Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZF$sp(float v1) {
/*  37 */     return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDF$sp(float v1) {
/*  37 */     return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFF$sp(float v1) {
/*  37 */     return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIF$sp(float v1) {
/*  37 */     return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJF$sp(float v1) {
/*  37 */     return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVF$sp(float v1) {
/*  37 */     Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZI$sp(int v1) {
/*  37 */     return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDI$sp(int v1) {
/*  37 */     return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFI$sp(int v1) {
/*  37 */     return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcII$sp(int v1) {
/*  37 */     return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJI$sp(int v1) {
/*  37 */     return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVI$sp(int v1) {
/*  37 */     Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZJ$sp(long v1) {
/*  37 */     return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDJ$sp(long v1) {
/*  37 */     return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFJ$sp(long v1) {
/*  37 */     return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIJ$sp(long v1) {
/*  37 */     return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJJ$sp(long v1) {
/*  37 */     return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVJ$sp(long v1) {
/*  37 */     Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose(Function1 g) {
/*  37 */     return Function1.class.compose((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  37 */     return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, A> andThen(Function1 g) {
/*  37 */     return Function1.class.andThen((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  37 */     return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public Ordering<A> ordering() {
/*  37 */     return this.ordering;
/*     */   }
/*     */   
/*     */   public TreeSet(Ordering<A> ordering) {
/*  37 */     Function1.class.$init$((Function1)this);
/*  37 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  37 */     TraversableOnce.class.$init$((TraversableOnce)this);
/*  37 */     Parallelizable.class.$init$(this);
/*  37 */     TraversableLike.class.$init$(this);
/*  37 */     GenericTraversableTemplate.class.$init$(this);
/*  37 */     GenTraversable.class.$init$((GenTraversable)this);
/*  37 */     Traversable.class.$init$(this);
/*  37 */     GenIterable.class.$init$((GenIterable)this);
/*  37 */     IterableLike.class.$init$(this);
/*  37 */     Iterable.class.$init$(this);
/*  37 */     GenSetLike.class.$init$((GenSetLike)this);
/*  37 */     GenericSetTemplate.class.$init$(this);
/*  37 */     GenSet.class.$init$((GenSet)this);
/*  37 */     Subtractable.class.$init$((Subtractable)this);
/*  37 */     SetLike.class.$init$(this);
/*  37 */     Set.class.$init$(this);
/*  37 */     Sorted.class.$init$((Sorted)this);
/*  37 */     SortedSetLike.class.$init$(this);
/*  37 */     SortedSet.class.$init$(this);
/*  37 */     Traversable$class.$init$(this);
/*  37 */     Iterable$class.$init$(this);
/*  37 */     Growable.class.$init$(this);
/*  37 */     Builder$class.$init$(this);
/*  37 */     Shrinkable.class.$init$(this);
/*  37 */     Cloneable$class.$init$(this);
/*  37 */     SetLike$class.$init$(this);
/*  37 */     Set$class.$init$(this);
/*  37 */     SortedSet$class.$init$(this);
/*  48 */     this.base = (Option<TreeSet<A>>)None$.MODULE$;
/*  50 */     this.scala$collection$mutable$TreeSet$$from = (Option<A>)None$.MODULE$;
/*  52 */     this.scala$collection$mutable$TreeSet$$until = (Option<A>)None$.MODULE$;
/*  54 */     this.avl = Leaf$.MODULE$;
/*  56 */     this.scala$collection$mutable$TreeSet$$cardinality = 0;
/*     */   }
/*     */   
/*     */   private TreeSet(Option<TreeSet<A>> base, Option<A> from, Option<A> until, Ordering<A> ordering) {
/*     */     this(ordering);
/*     */     base_$eq(base);
/*     */     scala$collection$mutable$TreeSet$$from_$eq(from);
/*     */     scala$collection$mutable$TreeSet$$until_$eq(until);
/*     */   }
/*     */   
/*     */   private Option<TreeSet<A>> base() {
/*     */     return this.base;
/*     */   }
/*     */   
/*     */   private void base_$eq(Option<TreeSet<A>> x$1) {
/*     */     this.base = x$1;
/*     */   }
/*     */   
/*     */   public Option<A> scala$collection$mutable$TreeSet$$from() {
/*     */     return this.scala$collection$mutable$TreeSet$$from;
/*     */   }
/*     */   
/*     */   private void scala$collection$mutable$TreeSet$$from_$eq(Option<A> x$1) {
/*     */     this.scala$collection$mutable$TreeSet$$from = x$1;
/*     */   }
/*     */   
/*     */   public Option<A> scala$collection$mutable$TreeSet$$until() {
/*     */     return this.scala$collection$mutable$TreeSet$$until;
/*     */   }
/*     */   
/*     */   private void scala$collection$mutable$TreeSet$$until_$eq(Option<A> x$1) {
/*     */     this.scala$collection$mutable$TreeSet$$until = x$1;
/*     */   }
/*     */   
/*     */   private AVLTree<A> avl() {
/*     */     return this.avl;
/*     */   }
/*     */   
/*     */   private void avl_$eq(AVLTree<A> x$1) {
/*     */     this.avl = x$1;
/*     */   }
/*     */   
/*     */   public int scala$collection$mutable$TreeSet$$cardinality() {
/*  56 */     return this.scala$collection$mutable$TreeSet$$cardinality;
/*     */   }
/*     */   
/*     */   private void scala$collection$mutable$TreeSet$$cardinality_$eq(int x$1) {
/*  56 */     this.scala$collection$mutable$TreeSet$$cardinality = x$1;
/*     */   }
/*     */   
/*     */   public TreeSet<A> resolve() {
/*     */     Option<TreeSet<A>> option;
/*  58 */     return (option = base()).isEmpty() ? this : (TreeSet<A>)option.get();
/*     */   }
/*     */   
/*     */   public boolean scala$collection$mutable$TreeSet$$isLeftAcceptable(Option from, Ordering ordering, Object a) {
/*  61 */     Object object = from.get();
/*     */     Option option;
/*  61 */     return BoxesRunTime.unboxToBoolean((option = (Option)(from.isEmpty() ? None$.MODULE$ : new Some(BoxesRunTime.boxToBoolean(ordering.gteq(a, object))))).isEmpty() ? BoxesRunTime.boxToBoolean(true) : option.get());
/*     */   }
/*     */   
/*     */   public boolean scala$collection$mutable$TreeSet$$isRightAcceptable(Option until, Ordering ordering, Object a) {
/*  64 */     Object object = until.get();
/*     */     Option option;
/*  64 */     return BoxesRunTime.unboxToBoolean((option = (Option)(until.isEmpty() ? None$.MODULE$ : new Some(BoxesRunTime.boxToBoolean(ordering.lt(a, object))))).isEmpty() ? BoxesRunTime.boxToBoolean(true) : option.get());
/*     */   }
/*     */   
/*     */   public int scala$collection$mutable$TreeSet$$super$size() {
/*  72 */     return TraversableOnce.class.size((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public int size() {
/*  72 */     option.get();
/*     */     Option<TreeSet<A>> option;
/*     */     Option option1;
/*  72 */     return BoxesRunTime.unboxToInt((option1 = (Option)((option = base()).isEmpty() ? None$.MODULE$ : new Some(BoxesRunTime.boxToInteger(TraversableOnce.class.size((TraversableOnce)this))))).isEmpty() ? BoxesRunTime.boxToInteger(scala$collection$mutable$TreeSet$$cardinality()) : option1.get());
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/*  74 */     return "TreeSet";
/*     */   }
/*     */   
/*     */   public TreeSet<A> empty() {
/*  76 */     return TreeSet$.MODULE$.empty(ordering());
/*     */   }
/*     */   
/*     */   public TreeSet<A> rangeImpl(Option<A> from, Option<A> until) {
/*  78 */     return new TreeSet((Option<TreeSet<A>>)new Some(this), from, until, ordering());
/*     */   }
/*     */   
/*     */   public TreeSet<A> $minus$eq(Object elem) {
/*     */     try {
/*  82 */       resolve().avl_$eq(resolve().avl().remove(elem, ordering()));
/*  83 */       resolve().scala$collection$mutable$TreeSet$$cardinality_$eq(resolve().scala$collection$mutable$TreeSet$$cardinality() - 1);
/*     */     } catch (NoSuchElementException noSuchElementException) {}
/*  85 */     return this;
/*     */   }
/*     */   
/*     */   public TreeSet<A> $plus$eq(Object elem) {
/*     */     try {
/*  92 */       resolve().avl_$eq(resolve().avl().insert(elem, ordering()));
/*  93 */       resolve().scala$collection$mutable$TreeSet$$cardinality_$eq(resolve().scala$collection$mutable$TreeSet$$cardinality() + 1);
/*     */     } catch (IllegalArgumentException illegalArgumentException) {}
/*  95 */     return this;
/*     */   }
/*     */   
/*     */   public TreeSet<A> clone() {
/* 107 */     TreeSet<A> clone = new TreeSet(base(), scala$collection$mutable$TreeSet$$from(), scala$collection$mutable$TreeSet$$until(), ordering());
/* 108 */     clone.avl_$eq(resolve().avl());
/* 109 */     clone.scala$collection$mutable$TreeSet$$cardinality_$eq(resolve().scala$collection$mutable$TreeSet$$cardinality());
/* 110 */     return clone;
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/* 114 */     return (scala$collection$mutable$TreeSet$$isLeftAcceptable(scala$collection$mutable$TreeSet$$from(), ordering(), (A)elem) && 
/* 115 */       scala$collection$mutable$TreeSet$$isRightAcceptable(scala$collection$mutable$TreeSet$$until(), ordering(), (A)elem) && 
/* 116 */       resolve().avl().contains(elem, ordering()));
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/* 121 */     return resolve().avl().iterator().dropWhile((Function1)new TreeSet$$anonfun$iterator$1(this)).takeWhile((Function1)new TreeSet$$anonfun$iterator$2(this));
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<TreeSet<?>, A, TreeSet<A>> newCanBuildFrom(Ordering<A> paramOrdering) {
/*     */     return TreeSet$.MODULE$.newCanBuildFrom(paramOrdering);
/*     */   }
/*     */   
/*     */   public class TreeSet$$anonfun$iterator$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Object e) {
/*     */       return !this.$outer.scala$collection$mutable$TreeSet$$isLeftAcceptable(this.$outer.scala$collection$mutable$TreeSet$$from(), this.$outer.ordering(), e);
/*     */     }
/*     */     
/*     */     public TreeSet$$anonfun$iterator$1(TreeSet $outer) {}
/*     */   }
/*     */   
/*     */   public class TreeSet$$anonfun$iterator$2 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Object e) {
/* 121 */       return this.$outer.scala$collection$mutable$TreeSet$$isRightAcceptable(this.$outer.scala$collection$mutable$TreeSet$$until(), this.$outer.ordering(), e);
/*     */     }
/*     */     
/*     */     public TreeSet$$anonfun$iterator$2(TreeSet $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\TreeSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */