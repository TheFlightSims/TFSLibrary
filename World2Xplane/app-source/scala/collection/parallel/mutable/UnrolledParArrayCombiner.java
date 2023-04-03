/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.mutable.UnrolledBuffer;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.Task;
/*     */ import scala.collection.parallel.TaskSupport;
/*     */ import scala.collection.parallel.package$;
/*     */ import scala.math.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005=daB\001\003!\003\r\ta\003\002\031+:\024x\016\0347fIB\013'/\021:sCf\034u.\0342j]\026\024(BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\021A\f'/\0317mK2T!a\002\005\002\025\r|G\016\\3di&|gNC\001\n\003\025\0318-\0317b\007\001)\"\001D\f\024\007\001i\021\003\005\002\017\0375\t\001\"\003\002\021\021\t1\021I\\=SK\032\004BAE\n\026A5\tA!\003\002\025\t\tA1i\\7cS:,'\017\005\002\027/1\001A!\002\r\001\005\004I\"!\001+\022\005ii\002C\001\b\034\023\ta\002BA\004O_RD\027N\\4\021\0059q\022BA\020\t\005\r\te.\037\t\004C\t*R\"\001\002\n\005\r\022!\001\003)be\006\023(/Y=\t\013\025\002A\021\001\024\002\r\021Jg.\033;%)\0059\003C\001\b)\023\tI\003B\001\003V]&$\bbB\026\001\005\004%\t\001L\001\005EV4g-F\001.!\r\tc&H\005\003_\t\021a\003R8vE2LgnZ+oe>dG.\0323Ck\0324WM\035\005\007c\001\001\013\021B\027\002\013\t,hM\032\021\t\013M\002A\021\001\033\002\021\021\002H.^:%KF$\"!\016\034\016\003\001AQa\016\032A\002U\tA!\0327f[\")\021\b\001C\001u\0051!/Z:vYR$\022\001\t\005\006y\001!\tAJ\001\006G2,\027M\035\005\006}\001!\teP\001\tg&TX\rS5oiR\021q\005\021\005\006\003v\002\rAQ\001\003gj\004\"AD\"\n\005\021C!aA%oi\")a\t\001C\001\017\00691m\\7cS:,Wc\001%L\037R\021\021J\025\t\005%MQe\n\005\002\027\027\022)A*\022b\001\033\n\ta*\005\002\033+A\021ac\024\003\006!\026\023\r!\025\002\006\035\026<Hk\\\t\003AuAQaU#A\002%\013Qa\034;iKJDQ!\026\001\005\002Y\013Aa]5{KV\t!I\002\003Y\001\001I&aE\"paf,fN]8mY\026$Gk\\!se\006L8cA,\0165B!!cW\024^\023\taFA\001\003UCN\\\007CA\033X\021!yvK!A!\002\023\001\027!B1se\006L\bc\001\bb;%\021!\r\003\002\006\003J\024\030-\037\005\tI^\023\t\021)A\005\005\0061qN\0324tKRD\001BZ,\003\002\003\006IAQ\001\bQ><X.\0318z\021\025Aw\013\"\001j\003\031a\024N\\5u}Q!QL[6m\021\025yv\r1\001a\021\025!w\r1\001C\021\0251w\r1\001C\021\035It\0131A\005\0029,\022a\n\005\ba^\003\r\021\"\001r\003)\021Xm];mi~#S-\035\013\003OIDqa]8\002\002\003\007q%A\002yIEBa!^,!B\0239\023a\002:fgVdG\017\t\005\006o^#\t\001_\001\005Y\026\fg\r\006\002(s\")!P\036a\001w\006!\001O]3w!\rqApJ\005\003{\"\021aa\0249uS>t\007BB@X\t\023\t\t!A\005gS:$7\013^1siR!\0211AA\016!\031q\021QAA\005\005&\031\021q\001\005\003\rQ+\b\017\\33!\025\tY!!\006\036\035\021\ti!!\005\016\005\005=!BA\002\007\023\021\t\031\"a\004\002\035Us'o\0347mK\022\024UO\0324fe&!\021qCA\r\005!)fN]8mY\026$'\002BA\n\003\037Aa!!\b\001\004\021\025a\0019pg\"9\021\021E,\005\002\005\r\022!B:qY&$XCAA\023!\025\t9#!\f^\033\t\tICC\002\002,\031\t\021\"[7nkR\f'\r\\3\n\t\005=\022\021\006\002\005\031&\034H\017C\004\0024]#\t!!\016\002%MDw.\0367e'Bd\027\016\036$veRDWM]\013\003\003o\0012ADA\035\023\r\tY\004\003\002\b\005>|G.Z1o\021\035\tyd\026C!\003\003\n\001\002^8TiJLgn\032\013\003\003\007\002B!!\022\002P5\021\021q\t\006\005\003\023\nY%\001\003mC:<'BAA'\003\021Q\027M^1\n\t\005E\023q\t\002\007'R\024\030N\\4\b\017\005U#\001#\001\002X\005ARK\034:pY2,G\rU1s\003J\024\030-_\"p[\nLg.\032:\021\007\005\nIF\002\004\002\005!\005\0211L\n\004\0033j\001b\0025\002Z\021\005\021q\f\013\003\003/B\001\"a\031\002Z\021\005\021QM\001\006CB\004H._\013\005\003O\ni\007\006\002\002jA!\021\005AA6!\r1\022Q\016\003\0071\005\005$\031A\r")
/*     */ public interface UnrolledParArrayCombiner<T> extends Combiner<T, ParArray<T>> {
/*     */   void scala$collection$parallel$mutable$UnrolledParArrayCombiner$_setter_$buff_$eq(DoublingUnrolledBuffer paramDoublingUnrolledBuffer);
/*     */   
/*     */   DoublingUnrolledBuffer<Object> buff();
/*     */   
/*     */   UnrolledParArrayCombiner<T> $plus$eq(T paramT);
/*     */   
/*     */   ParArray<T> result();
/*     */   
/*     */   void clear();
/*     */   
/*     */   void sizeHint(int paramInt);
/*     */   
/*     */   <N extends T, NewTo> Combiner<N, NewTo> combine(Combiner<N, NewTo> paramCombiner);
/*     */   
/*     */   int size();
/*     */   
/*     */   public class CopyUnrolledToArray implements Task<BoxedUnit, CopyUnrolledToArray> {
/*     */     private final Object[] array;
/*     */     
/*     */     private final int offset;
/*     */     
/*     */     private final int howmany;
/*     */     
/*     */     private BoxedUnit result;
/*     */     
/*     */     private volatile Throwable throwable;
/*     */     
/*     */     public Throwable throwable() {
/*  70 */       return this.throwable;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void throwable_$eq(Throwable x$1) {
/*  70 */       this.throwable = x$1;
/*     */     }
/*     */     
/*     */     public CopyUnrolledToArray repr() {
/*  70 */       return (CopyUnrolledToArray)Task.class.repr(this);
/*     */     }
/*     */     
/*     */     public void merge(Object that) {
/*  70 */       Task.class.merge(this, that);
/*     */     }
/*     */     
/*     */     public void forwardThrowable() {
/*  70 */       Task.class.forwardThrowable(this);
/*     */     }
/*     */     
/*     */     public void tryLeaf(Option lastres) {
/*  70 */       Task.class.tryLeaf(this, lastres);
/*     */     }
/*     */     
/*     */     public void tryMerge(Object t) {
/*  70 */       Task.class.tryMerge(this, t);
/*     */     }
/*     */     
/*     */     public void mergeThrowables(Task that) {
/*  70 */       Task.class.mergeThrowables(this, that);
/*     */     }
/*     */     
/*     */     public void signalAbort() {
/*  70 */       Task.class.signalAbort(this);
/*     */     }
/*     */     
/*     */     public CopyUnrolledToArray(UnrolledParArrayCombiner $outer, Object[] array, int offset, int howmany) {
/*  70 */       Task.class.$init$(this);
/*  72 */       this.result = BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public void result() {}
/*     */     
/*     */     public void result_$eq(BoxedUnit x$1) {
/*  72 */       this.result = x$1;
/*     */     }
/*     */     
/*     */     public void leaf(Option prev) {
/*  73 */       if (this.howmany > 0) {
/*  74 */         int totalleft = this.howmany;
/*  75 */         Tuple2<UnrolledBuffer.Unrolled<Object>, Object> tuple2 = findStart(this.offset);
/*  75 */         if (tuple2 != null) {
/*  75 */           Tuple2 tuple21 = new Tuple2(tuple2._1(), BoxesRunTime.boxToInteger(tuple2._2$mcI$sp()));
/*  75 */           UnrolledBuffer.Unrolled startnode = (UnrolledBuffer.Unrolled)tuple21._1();
/*  75 */           int startpos = tuple21._2$mcI$sp();
/*  76 */           UnrolledBuffer.Unrolled curr = startnode;
/*  77 */           int pos = startpos;
/*  78 */           int arroffset = this.offset;
/*  79 */           while (totalleft > 0) {
/*  80 */             int lefthere = package$.MODULE$.min(totalleft, curr.size() - pos);
/*  81 */             Array$.MODULE$.copy(curr.array(), pos, this.array, arroffset, lefthere);
/*  83 */             totalleft -= lefthere;
/*  84 */             arroffset += lefthere;
/*  85 */             pos = 0;
/*  86 */             curr = curr.next();
/*     */           } 
/*     */         } else {
/*     */           throw new MatchError(tuple2);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private Tuple2<UnrolledBuffer.Unrolled<Object>, Object> findStart(int pos) {
/*  90 */       int left = pos;
/*  91 */       UnrolledBuffer.Unrolled node = scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer().buff().headPtr();
/*  92 */       while (left - node.size() >= 0) {
/*  93 */         left -= node.size();
/*  94 */         node = node.next();
/*     */       } 
/*  96 */       return new Tuple2(node, BoxesRunTime.boxToInteger(left));
/*     */     }
/*     */     
/*     */     public List<CopyUnrolledToArray> split() {
/*  99 */       int fp = this.howmany / 2;
/* 100 */       (new CopyUnrolledToArray[2])[0] = new CopyUnrolledToArray(scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer(), this.array, this.offset, fp);
/* 100 */       (new CopyUnrolledToArray[2])[1] = new CopyUnrolledToArray(scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer(), this.array, this.offset + fp, this.howmany - fp);
/* 100 */       return List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new CopyUnrolledToArray[2]));
/*     */     }
/*     */     
/*     */     public boolean shouldSplitFurther() {
/* 102 */       return (this.howmany > package$.MODULE$.thresholdFromSize(scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer().size(), scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer().combinerTaskSupport().parallelismLevel()));
/*     */     }
/*     */     
/*     */     public String toString() {
/* 103 */       return (new StringBuilder()).append("CopyUnrolledToArray(").append(BoxesRunTime.boxToInteger(this.offset)).append(", ").append(BoxesRunTime.boxToInteger(this.howmany)).append(")").toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class UnrolledParArrayCombiner$$anon$1 implements UnrolledParArrayCombiner<T> {
/*     */     private final DoublingUnrolledBuffer<Object> buff;
/*     */     
/*     */     private volatile transient TaskSupport _combinerTaskSupport;
/*     */     
/*     */     public DoublingUnrolledBuffer<Object> buff() {
/* 108 */       return this.buff;
/*     */     }
/*     */     
/*     */     public void scala$collection$parallel$mutable$UnrolledParArrayCombiner$_setter_$buff_$eq(DoublingUnrolledBuffer<Object> x$1) {
/* 108 */       this.buff = x$1;
/*     */     }
/*     */     
/*     */     public UnrolledParArrayCombiner<T> $plus$eq(Object elem) {
/* 108 */       return UnrolledParArrayCombiner$class.$plus$eq(this, elem);
/*     */     }
/*     */     
/*     */     public ParArray<T> result() {
/* 108 */       return UnrolledParArrayCombiner$class.result(this);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 108 */       UnrolledParArrayCombiner$class.clear(this);
/*     */     }
/*     */     
/*     */     public void sizeHint(int sz) {
/* 108 */       UnrolledParArrayCombiner$class.sizeHint(this, sz);
/*     */     }
/*     */     
/*     */     public <N extends T, NewTo> Combiner<N, NewTo> combine(Combiner other) {
/* 108 */       return UnrolledParArrayCombiner$class.combine(this, other);
/*     */     }
/*     */     
/*     */     public int size() {
/* 108 */       return UnrolledParArrayCombiner$class.size(this);
/*     */     }
/*     */     
/*     */     public TaskSupport _combinerTaskSupport() {
/* 108 */       return this._combinerTaskSupport;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void _combinerTaskSupport_$eq(TaskSupport x$1) {
/* 108 */       this._combinerTaskSupport = x$1;
/*     */     }
/*     */     
/*     */     public TaskSupport combinerTaskSupport() {
/* 108 */       return Combiner.class.combinerTaskSupport(this);
/*     */     }
/*     */     
/*     */     public void combinerTaskSupport_$eq(TaskSupport cts) {
/* 108 */       Combiner.class.combinerTaskSupport_$eq(this, cts);
/*     */     }
/*     */     
/*     */     public boolean canBeShared() {
/* 108 */       return Combiner.class.canBeShared(this);
/*     */     }
/*     */     
/*     */     public ParArray<T> resultWithTaskSupport() {
/* 108 */       return (ParArray<T>)Combiner.class.resultWithTaskSupport(this);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll) {
/* 108 */       Builder.class.sizeHint((Builder)this, coll);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll, int delta) {
/* 108 */       Builder.class.sizeHint((Builder)this, coll, delta);
/*     */     }
/*     */     
/*     */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 108 */       Builder.class.sizeHintBounded((Builder)this, size, boundingColl);
/*     */     }
/*     */     
/*     */     public <NewTo> Builder<T, NewTo> mapResult(Function1 f) {
/* 108 */       return Builder.class.mapResult((Builder)this, f);
/*     */     }
/*     */     
/*     */     public Growable<T> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 108 */       return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public Growable<T> $plus$plus$eq(TraversableOnce xs) {
/* 108 */       return Growable.class.$plus$plus$eq((Growable)this, xs);
/*     */     }
/*     */     
/*     */     public UnrolledParArrayCombiner$$anon$1() {
/* 108 */       Growable.class.$init$((Growable)this);
/* 108 */       Builder.class.$init$((Builder)this);
/* 108 */       Combiner.class.$init$(this);
/* 108 */       UnrolledParArrayCombiner$class.$init$(this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\UnrolledParArrayCombiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */