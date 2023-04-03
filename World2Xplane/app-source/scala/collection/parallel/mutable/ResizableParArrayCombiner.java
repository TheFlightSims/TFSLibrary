/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.Array$;
/*    */ import scala.Function1;
/*    */ import scala.MatchError;
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.SeqLike;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.List$;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.Task;
/*    */ import scala.collection.parallel.TaskSupport;
/*    */ import scala.collection.parallel.package$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\035daB\001\003!\003\r\ta\003\002\032%\026\034\030N_1cY\026\004\026M]!se\006L8i\\7cS:,'O\003\002\004\t\0059Q.\036;bE2,'BA\003\007\003!\001\030M]1mY\026d'BA\004\t\003)\031w\016\0347fGRLwN\034\006\002\023\005)1oY1mC\016\001QC\001\007\030'\r\001Q\"\005\t\003\035=i\021\001C\005\003!!\021a!\0218z%\0264\007#\002\n\024+\001\032S\"\001\002\n\005Q\021!\001\004'buf\034u.\0342j]\026\024\bC\001\f\030\031\001!Q\001\007\001C\002e\021\021\001V\t\0035u\001\"AD\016\n\005qA!a\002(pi\"Lgn\032\t\003\035yI!a\b\005\003\007\005s\027\020E\002\023CUI!A\t\002\003\021A\013'/\021:sCf\0042A\005\023\026\023\t)#A\001\nFqB|7/\0323BeJ\f\027PQ;gM\026\024\b\"B\024\001\t\003A\023A\002\023j]&$H\005F\001*!\tq!&\003\002,\021\t!QK\\5u\021\025i\003\001\"\021/\003!\031\030N_3IS:$HCA\0250\021\025\001D\0061\0012\003\t\031(\020\005\002\017e%\0211\007\003\002\004\023:$\b\"B\033\001\t\0031\024a\0048fo2\013'0_\"p[\nLg.\032:\025\005]B\004c\001\n\001+!)\021\b\016a\001u\005\t1\rE\002<{\rj\021\001\020\006\003\007\031I!A\020\037\003\027\005\023(/Y=Ck\0324WM\035\005\006\001\002!\t!Q\001\020C2dwnY1uK\006sGmQ8qsV\t\001\005C\003D\001\021\005C)\001\005u_N#(/\0338h)\005)\005C\001$L\033\0059%B\001%J\003\021a\027M\\4\013\003)\013AA[1wC&\021Aj\022\002\007'R\024\030N\\4\007\t9\003\001a\024\002\021\007>\004\030p\0215bS:$v.\021:sCf\0342!T\007Q!\021\t&+\013+\016\003\021I!a\025\003\003\tQ\0137o\033\t\003+6k\021\001\001\005\t/6\023\t\021)A\0051\006)\021M\035:bsB\031a\"W\017\n\005iC!!B!se\006L\b\002\003/N\005\003\005\013\021B\031\002\r=4gm]3u\021!qVJ!A!\002\023\t\024a\0025po6\fg.\037\005\006A6#\t!Y\001\007y%t\027\016\036 \025\tQ\0237\r\032\005\006/~\003\r\001\027\005\0069~\003\r!\r\005\006=~\003\r!\r\005\bM6\003\r\021\"\001h\003\031\021Xm];miV\t\021\006C\004j\033\002\007I\021\0016\002\025I,7/\0367u?\022*\027\017\006\002*W\"9A\016[A\001\002\004I\023a\001=%c!1a.\024Q!\n%\nqA]3tk2$\b\005C\003q\033\022\005\021/\001\003mK\0064GCA\025s\021\025\031x\0161\001u\003\021\001(/\032<\021\0079)\030&\003\002w\021\t1q\n\035;j_:DQ\001_'\005\ne\f\021bY8qs\016CWO\\6\025\021%RXp`A\002\003\017AQa_<A\002q\fqAY;gM\006\024(\017E\002\01736AQA`<A\002E\n\021BY;gMN#\030M\035;\t\r\005\005q\0171\001Y\003\t\021\030\r\003\004\002\006]\004\r!M\001\013CJ\024\030-_*uCJ$\bBBA\005o\002\007\021'A\003v]RLG\016C\004\002\0165#I!a\004\002\023\031Lg\016Z*uCJ$H\003BA\t\003/\001RADA\ncEJ1!!\006\t\005\031!V\017\0357fe!9\021\021DA\006\001\004\t\024a\0019pg\"9\021QD'\005\002\005}\021!B:qY&$XCAA\021!\025\t\031#!\013U\033\t\t)CC\002\002(\031\t\021\"[7nkR\f'\r\\3\n\t\005-\022Q\005\002\005\031&\034H\017C\004\00205#\t!!\r\002%MDw.\0367e'Bd\027\016\036$veRDWM]\013\003\003g\0012ADA\033\023\r\t9\004\003\002\b\005>|G.Z1o\017\035\tYD\001E\001\003{\t\021DU3tSj\f'\r\\3QCJ\f%O]1z\007>l'-\0338feB\031!#a\020\007\r\005\021\001\022AA!'\r\ty$\004\005\bA\006}B\021AA#)\t\ti\004\003\005\002J\005}B\021AA&\003\025\t\007\017\0357z+\021\ti%a\025\025\t\005=\023Q\013\t\005%\001\t\t\006E\002\027\003'\"a\001GA$\005\004I\002bB\035\002H\001\007\021q\013\t\005wu\nI\006\005\003\023I\005E\003\002CA%\003!\t!!\030\026\t\005}\023Q\r\013\003\003C\002BA\005\001\002dA\031a#!\032\005\ra\tYF1\001\032\001")
/*    */ public interface ResizableParArrayCombiner<T> extends LazyCombiner<T, ParArray<T>, ExposedArrayBuffer<T>> {
/*    */   void sizeHint(int paramInt);
/*    */   
/*    */   ResizableParArrayCombiner<T> newLazyCombiner(ArrayBuffer<ExposedArrayBuffer<T>> paramArrayBuffer);
/*    */   
/*    */   ParArray<T> allocateAndCopy();
/*    */   
/*    */   String toString();
/*    */   
/*    */   public class CopyChainToArray implements Task<BoxedUnit, CopyChainToArray> {
/*    */     private final Object[] array;
/*    */     
/*    */     private final int offset;
/*    */     
/*    */     private final int howmany;
/*    */     
/*    */     private BoxedUnit result;
/*    */     
/*    */     private volatile Throwable throwable;
/*    */     
/*    */     public Throwable throwable() {
/* 46 */       return this.throwable;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void throwable_$eq(Throwable x$1) {
/* 46 */       this.throwable = x$1;
/*    */     }
/*    */     
/*    */     public CopyChainToArray repr() {
/* 46 */       return (CopyChainToArray)Task.class.repr(this);
/*    */     }
/*    */     
/*    */     public void merge(Object that) {
/* 46 */       Task.class.merge(this, that);
/*    */     }
/*    */     
/*    */     public void forwardThrowable() {
/* 46 */       Task.class.forwardThrowable(this);
/*    */     }
/*    */     
/*    */     public void tryLeaf(Option lastres) {
/* 46 */       Task.class.tryLeaf(this, lastres);
/*    */     }
/*    */     
/*    */     public void tryMerge(Object t) {
/* 46 */       Task.class.tryMerge(this, t);
/*    */     }
/*    */     
/*    */     public void mergeThrowables(Task that) {
/* 46 */       Task.class.mergeThrowables(this, that);
/*    */     }
/*    */     
/*    */     public void signalAbort() {
/* 46 */       Task.class.signalAbort(this);
/*    */     }
/*    */     
/*    */     public CopyChainToArray(ResizableParArrayCombiner $outer, Object[] array, int offset, int howmany) {
/* 46 */       Task.class.$init$(this);
/* 47 */       this.result = BoxedUnit.UNIT;
/*    */     }
/*    */     
/*    */     public void result() {}
/*    */     
/*    */     public void result_$eq(BoxedUnit x$1) {
/* 47 */       this.result = x$1;
/*    */     }
/*    */     
/*    */     public void leaf(Option prev) {
/* 48 */       if (this.howmany > 0) {
/* 49 */         int totalleft = this.howmany;
/* 50 */         Tuple2<Object, Object> tuple2 = findStart(this.offset);
/* 50 */         if (tuple2 != null) {
/* 50 */           Tuple2.mcII.sp sp = new Tuple2.mcII.sp(tuple2._1$mcI$sp(), tuple2._2$mcI$sp());
/* 50 */           int stbuff = sp._1$mcI$sp(), stind = sp._2$mcI$sp();
/* 51 */           int buffind = stbuff;
/* 52 */           int ind = stind;
/* 53 */           int arrayIndex = this.offset;
/* 54 */           while (totalleft > 0) {
/* 55 */             ExposedArrayBuffer currbuff = (ExposedArrayBuffer)scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer().chain().apply(buffind);
/* 56 */             int chunksize = (totalleft < currbuff.size() - ind) ? totalleft : (currbuff.size() - ind);
/* 57 */             int until = ind + chunksize;
/* 59 */             copyChunk(currbuff.internalArray(), ind, this.array, arrayIndex, until);
/* 60 */             arrayIndex += chunksize;
/* 63 */             totalleft -= chunksize;
/* 64 */             buffind++;
/* 65 */             ind = 0;
/*    */           } 
/*    */         } else {
/*    */           throw new MatchError(tuple2);
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/*    */     private void copyChunk(Object[] buffarr, int buffStart, Object[] ra, int arrayStart, int until) {
/* 69 */       Array$.MODULE$.copy(buffarr, buffStart, ra, arrayStart, until - buffStart);
/*    */     }
/*    */     
/*    */     private Tuple2<Object, Object> findStart(int pos) {
/* 72 */       int left = pos;
/* 73 */       int buffind = 0;
/* 74 */       while (left >= ((SeqLike)scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer().chain().apply(buffind)).size()) {
/* 75 */         left -= ((SeqLike)scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer().chain().apply(buffind)).size();
/* 76 */         buffind++;
/*    */       } 
/* 78 */       return (Tuple2<Object, Object>)new Tuple2.mcII.sp(buffind, left);
/*    */     }
/*    */     
/*    */     public List<CopyChainToArray> split() {
/* 81 */       int fp = this.howmany / 2;
/* 82 */       (new CopyChainToArray[2])[0] = new CopyChainToArray(scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer(), this.array, this.offset, fp);
/* 82 */       (new CopyChainToArray[2])[1] = new CopyChainToArray(scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer(), this.array, this.offset + fp, this.howmany - fp);
/* 82 */       return List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new CopyChainToArray[2]));
/*    */     }
/*    */     
/*    */     public boolean shouldSplitFurther() {
/* 84 */       return (this.howmany > package$.MODULE$.thresholdFromSize(scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer().size(), scala$collection$parallel$mutable$ResizableParArrayCombiner$CopyChainToArray$$$outer().combinerTaskSupport().parallelismLevel()));
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ResizableParArrayCombiner$$anon$1 implements ResizableParArrayCombiner<T> {
/*    */     private final ArrayBuffer<ExposedArrayBuffer<T>> chain;
/*    */     
/*    */     private final Growable lastbuff;
/*    */     
/*    */     private volatile transient TaskSupport _combinerTaskSupport;
/*    */     
/*    */     public void sizeHint(int sz) {
/* 90 */       ResizableParArrayCombiner$class.sizeHint(this, sz);
/*    */     }
/*    */     
/*    */     public ResizableParArrayCombiner<T> newLazyCombiner(ArrayBuffer c) {
/* 90 */       return ResizableParArrayCombiner$class.newLazyCombiner(this, c);
/*    */     }
/*    */     
/*    */     public ParArray<T> allocateAndCopy() {
/* 90 */       return ResizableParArrayCombiner$class.allocateAndCopy(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 90 */       return ResizableParArrayCombiner$class.toString(this);
/*    */     }
/*    */     
/*    */     public ExposedArrayBuffer<T> lastbuff() {
/* 90 */       return (ExposedArrayBuffer<T>)this.lastbuff;
/*    */     }
/*    */     
/*    */     public void scala$collection$parallel$mutable$LazyCombiner$_setter_$lastbuff_$eq(Growable x$1) {
/* 90 */       this.lastbuff = x$1;
/*    */     }
/*    */     
/*    */     public LazyCombiner<T, ParArray<T>, ExposedArrayBuffer<T>> $plus$eq(Object elem) {
/* 90 */       return LazyCombiner$class.$plus$eq(this, elem);
/*    */     }
/*    */     
/*    */     public ParArray<T> result() {
/* 90 */       return (ParArray<T>)LazyCombiner$class.result(this);
/*    */     }
/*    */     
/*    */     public void clear() {
/* 90 */       LazyCombiner$class.clear(this);
/*    */     }
/*    */     
/*    */     public <N extends T, NewTo> Combiner<N, NewTo> combine(Combiner other) {
/* 90 */       return LazyCombiner$class.combine(this, other);
/*    */     }
/*    */     
/*    */     public int size() {
/* 90 */       return LazyCombiner$class.size(this);
/*    */     }
/*    */     
/*    */     public TaskSupport _combinerTaskSupport() {
/* 90 */       return this._combinerTaskSupport;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void _combinerTaskSupport_$eq(TaskSupport x$1) {
/* 90 */       this._combinerTaskSupport = x$1;
/*    */     }
/*    */     
/*    */     public TaskSupport combinerTaskSupport() {
/* 90 */       return Combiner.class.combinerTaskSupport(this);
/*    */     }
/*    */     
/*    */     public void combinerTaskSupport_$eq(TaskSupport cts) {
/* 90 */       Combiner.class.combinerTaskSupport_$eq(this, cts);
/*    */     }
/*    */     
/*    */     public boolean canBeShared() {
/* 90 */       return Combiner.class.canBeShared(this);
/*    */     }
/*    */     
/*    */     public ParArray<T> resultWithTaskSupport() {
/* 90 */       return (ParArray<T>)Combiner.class.resultWithTaskSupport(this);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll) {
/* 90 */       Builder.class.sizeHint((Builder)this, coll);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll, int delta) {
/* 90 */       Builder.class.sizeHint((Builder)this, coll, delta);
/*    */     }
/*    */     
/*    */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 90 */       Builder.class.sizeHintBounded((Builder)this, size, boundingColl);
/*    */     }
/*    */     
/*    */     public <NewTo> Builder<T, NewTo> mapResult(Function1 f) {
/* 90 */       return Builder.class.mapResult((Builder)this, f);
/*    */     }
/*    */     
/*    */     public Growable<T> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 90 */       return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Growable<T> $plus$plus$eq(TraversableOnce xs) {
/* 90 */       return Growable.class.$plus$plus$eq((Growable)this, xs);
/*    */     }
/*    */     
/*    */     public ArrayBuffer<ExposedArrayBuffer<T>> chain() {
/* 90 */       return this.chain;
/*    */     }
/*    */     
/*    */     public ResizableParArrayCombiner$$anon$1(ArrayBuffer<ExposedArrayBuffer<T>> c$1) {
/* 90 */       Growable.class.$init$((Growable)this);
/* 90 */       Builder.class.$init$((Builder)this);
/* 90 */       Combiner.class.$init$(this);
/* 90 */       LazyCombiner$class.$init$(this);
/* 90 */       ResizableParArrayCombiner$class.$init$(this);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ResizableParArrayCombiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */