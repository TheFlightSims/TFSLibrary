/*     */ package scala.collection.parallel.immutable;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.immutable.HashSet;
/*     */ import scala.collection.immutable.HashSet$;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.UnrolledBuffer;
/*     */ import scala.collection.parallel.BucketCombiner;
/*     */ import scala.collection.parallel.Task;
/*     */ import scala.collection.parallel.package$;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005-cAB\001\003\003\003\021!BA\bICND7+\032;D_6\024\027N\\3s\025\t\031A!A\005j[6,H/\0312mK*\021QAB\001\ta\006\024\030\r\0347fY*\021q\001C\001\013G>dG.Z2uS>t'\"A\005\002\013M\034\027\r\\1\026\005-\0212C\001\001\r!\031ia\002E\017\033C5\tA!\003\002\020\t\tq!)^2lKR\034u.\0342j]\026\024\bCA\t\023\031\001!Qa\005\001C\002U\021\021\001V\002\001#\t1\"\004\005\002\03015\t\001\"\003\002\032\021\t9aj\034;iS:<\007CA\f\034\023\ta\002BA\002B]f\0042AH\020\021\033\005\021\021B\001\021\003\005)\001\026M\035%bg\"\034V\r\036\t\004=\001\001\002\"B\022\001\t\003!\023A\002\037j]&$h\bF\001\"\021\0351\003A1A\005\002\035\n\021\"Z7qif$&/[3\026\003!\0022!K\026\021\033\005Q#BA\002\007\023\ta#FA\004ICND7+\032;\t\r9\002\001\025!\003)\003))W\016\035;z)JLW\r\t\005\006a\001!\t!M\001\tIAdWo\035\023fcR\021!gM\007\002\001!)Ag\fa\001!\005!Q\r\\3n\021\0251\004\001\"\0018\003\031\021Xm];miR\tQD\002\003:\001\001Q$AC\"sK\006$X\r\026:jKN\031\001h\017 \021\005]a\024BA\037\t\005\031\te.\037*fMB!QbP!E\023\t\001EA\001\003UCN\\\007CA\fC\023\t\031\005B\001\003V]&$\bC\001\0329\021!1\005H!A!\002\0239\025!\0022vG.\034\bcA\fI\025&\021\021\n\003\002\006\003J\024\030-\037\t\004\027fSbB\001'W\035\tiEK\004\002O':\021qJU\007\002!*\021\021\013F\001\007yI|w\016\036 \n\003%I!a\002\005\n\005U3\021aB7vi\006\024G.Z\005\003/b\013a\"\0268s_2dW\r\032\"vM\032,'O\003\002V\r%\021!l\027\002\t+:\024x\016\0347fI*\021q\013\027\005\t;b\022\t\021)A\005=\006!!o\\8u!\r9\002\n\013\005\tAb\022\t\021)A\005C\0061qN\0324tKR\004\"a\0062\n\005\rD!aA%oi\"AQ\r\017B\001B\003%\021-A\004i_^l\027M\\=\t\013\rBD\021A4\025\013\021C\027N[6\t\013\0313\007\031A$\t\013u3\007\031\0010\t\013\0014\007\031A1\t\013\0254\007\031A1\t\017YB\004\031!C\001[V\t\021\tC\004pq\001\007I\021\0019\002\025I,7/\0367u?\022*\027\017\006\002Bc\"9!O\\A\001\002\004\t\025a\001=%c!1A\017\017Q!\n\005\013qA]3tk2$\b\005C\003wq\021\005q/\001\003mK\0064GCA!y\021\025IX\0171\001{\003\021\001(/\032<\021\007]Y\030)\003\002}\021\t1q\n\035;j_:DQA \035\005\n}\f!b\031:fCR,GK]5f)\rA\023\021\001\005\007\003\007i\b\031\001&\002\013\025dW-\\:\t\017\005\035\001\b\"\001\002\n\005)1\017\0357jiV\021\0211\002\t\005S\0055A)C\002\002\020)\022A\001T5ti\"9\0211\003\035\005\002\005U\021AE:i_VdGm\0259mSR4UO\035;iKJ,\"!a\006\021\007]\tI\"C\002\002\034!\021qAQ8pY\026\fgnB\004\002 \tA\t!!\t\002\037!\0137\017[*fi\016{WNY5oKJ\0042AHA\022\r\031\t!\001#\001\002&M\031\0211E\036\t\017\r\n\031\003\"\001\002*Q\021\021\021\005\005\t\003[\t\031\003\"\001\0020\005)\021\r\0359msV!\021\021GA\034+\t\t\031\004\005\003\037\001\005U\002cA\t\0028\02111#a\013C\002UA1\"a\017\002$\t\007I\021\001\002\002>\005A!o\\8uE&$8/F\001b\021!\t\t%a\t!\002\023\t\027!\003:p_R\024\027\016^:!\021-\t)%a\tC\002\023\005!!!\020\002\021I|w\016^:ju\026D\001\"!\023\002$\001\006I!Y\001\ne>|Go]5{K\002\002")
/*     */ public abstract class HashSetCombiner<T> extends BucketCombiner<T, ParHashSet<T>, Object, HashSetCombiner<T>> {
/*     */   private final HashSet<T> emptyTrie;
/*     */   
/*     */   public HashSetCombiner() {
/* 135 */     super(HashSetCombiner$.MODULE$.rootsize());
/* 138 */     this.emptyTrie = HashSet$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public HashSet<T> emptyTrie() {
/* 138 */     return this.emptyTrie;
/*     */   }
/*     */   
/*     */   public HashSetCombiner<T> $plus$eq(Object elem) {
/* 141 */     sz_$eq(sz() + 1);
/* 142 */     int hc = emptyTrie().computeHash(elem);
/* 143 */     int pos = hc & 0x1F;
/* 144 */     if (buckets()[pos] == null)
/* 146 */       buckets()[pos] = new UnrolledBuffer(ClassTag$.MODULE$.Any()); 
/* 149 */     buckets()[pos].$plus$eq(elem);
/* 150 */     return this;
/*     */   }
/*     */   
/*     */   public ParHashSet<T> result() {
/* 154 */     UnrolledBuffer.Unrolled[] bucks = (UnrolledBuffer.Unrolled[])Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])buckets()).filter((Function1)new HashSetCombiner$$anonfun$1(this))).map((Function1)new HashSetCombiner$$anonfun$2(this), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(UnrolledBuffer.Unrolled.class)));
/* 155 */     HashSet[] root = new HashSet[bucks.length];
/* 157 */     combinerTaskSupport().executeAndWaitResult(new CreateTrie(this, bucks, (HashSet<T>[])root, 0, bucks.length));
/* 159 */     int bitmap = 0;
/* 160 */     int i = 0;
/* 161 */     while (i < HashSetCombiner$.MODULE$.rootsize()) {
/* 162 */       if (buckets()[i] != null)
/* 162 */         bitmap |= 1 << i; 
/* 163 */       i++;
/*     */     } 
/* 165 */     int sz = BoxesRunTime.unboxToInt(Predef$.MODULE$.refArrayOps((Object[])root).foldLeft(BoxesRunTime.boxToInteger(0), (Function2)new HashSetCombiner$$anonfun$3(this)));
/* 170 */     HashSet.HashTrieSet trie = new HashSet.HashTrieSet(bitmap, root, sz);
/* 171 */     return (sz == 0) ? new ParHashSet<T>() : ((sz == 1) ? new ParHashSet<T>(root[0]) : new ParHashSet<T>((HashSet<T>)trie));
/*     */   }
/*     */   
/*     */   public static <T> HashSetCombiner<T> apply() {
/*     */     return HashSetCombiner$.MODULE$.apply();
/*     */   }
/*     */   
/*     */   public class HashSetCombiner$$anonfun$1 extends AbstractFunction1<UnrolledBuffer<Object>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(UnrolledBuffer x$3) {
/*     */       return !(x$3 == null);
/*     */     }
/*     */     
/*     */     public HashSetCombiner$$anonfun$1(HashSetCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashSetCombiner$$anonfun$2 extends AbstractFunction1<UnrolledBuffer<Object>, UnrolledBuffer.Unrolled<Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final UnrolledBuffer.Unrolled<Object> apply(UnrolledBuffer x$4) {
/*     */       return x$4.headPtr();
/*     */     }
/*     */     
/*     */     public HashSetCombiner$$anonfun$2(HashSetCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashSetCombiner$$anonfun$3 extends AbstractFunction2<Object, HashSet<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int x$5, HashSet x$6) {
/*     */       return x$5 + x$6.size();
/*     */     }
/*     */     
/*     */     public HashSetCombiner$$anonfun$3(HashSetCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class CreateTrie implements Task<BoxedUnit, CreateTrie> {
/*     */     private final UnrolledBuffer.Unrolled<Object>[] bucks;
/*     */     
/*     */     private final HashSet<T>[] root;
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
/* 177 */       return this.throwable;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void throwable_$eq(Throwable x$1) {
/* 177 */       this.throwable = x$1;
/*     */     }
/*     */     
/*     */     public CreateTrie repr() {
/* 177 */       return (CreateTrie)Task.class.repr(this);
/*     */     }
/*     */     
/*     */     public void merge(Object that) {
/* 177 */       Task.class.merge(this, that);
/*     */     }
/*     */     
/*     */     public void forwardThrowable() {
/* 177 */       Task.class.forwardThrowable(this);
/*     */     }
/*     */     
/*     */     public void tryLeaf(Option lastres) {
/* 177 */       Task.class.tryLeaf(this, lastres);
/*     */     }
/*     */     
/*     */     public void tryMerge(Object t) {
/* 177 */       Task.class.tryMerge(this, t);
/*     */     }
/*     */     
/*     */     public void mergeThrowables(Task that) {
/* 177 */       Task.class.mergeThrowables(this, that);
/*     */     }
/*     */     
/*     */     public void signalAbort() {
/* 177 */       Task.class.signalAbort(this);
/*     */     }
/*     */     
/*     */     public CreateTrie(HashSetCombiner $outer, UnrolledBuffer.Unrolled[] bucks, HashSet[] root, int offset, int howmany) {
/* 177 */       Task.class.$init$(this);
/* 179 */       this.result = BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public void result() {}
/*     */     
/*     */     public void result_$eq(BoxedUnit x$1) {
/* 179 */       this.result = x$1;
/*     */     }
/*     */     
/*     */     public void leaf(Option prev) {
/* 181 */       int i = this.offset;
/* 182 */       int until = this.offset + this.howmany;
/* 183 */       while (i < until) {
/* 184 */         this.root[i] = createTrie(this.bucks[i]);
/* 185 */         i++;
/*     */       } 
/*     */     }
/*     */     
/*     */     private HashSet<T> createTrie(UnrolledBuffer.Unrolled elems) {
/* 189 */       HashSet<T> trie = new HashSet();
/* 191 */       UnrolledBuffer.Unrolled unrolled = elems;
/* 192 */       int i = 0;
/* 193 */       while (unrolled != null) {
/* 194 */         Object[] chunkarr = (Object[])unrolled.array();
/* 195 */         int chunksz = unrolled.size();
/* 196 */         while (i < chunksz) {
/* 197 */           Object v = chunkarr[i];
/* 198 */           int hc = trie.computeHash(v);
/* 199 */           trie = trie.updated0(v, hc, HashSetCombiner$.MODULE$.rootbits());
/* 200 */           i++;
/*     */         } 
/* 202 */         i = 0;
/* 203 */         unrolled = unrolled.next();
/*     */       } 
/* 206 */       return trie;
/*     */     }
/*     */     
/*     */     public List<CreateTrie> split() {
/* 209 */       int fp = this.howmany / 2;
/* 210 */       (new CreateTrie[2])[0] = new CreateTrie(scala$collection$parallel$immutable$HashSetCombiner$CreateTrie$$$outer(), this.bucks, this.root, this.offset, fp);
/* 210 */       (new CreateTrie[2])[1] = new CreateTrie(scala$collection$parallel$immutable$HashSetCombiner$CreateTrie$$$outer(), this.bucks, this.root, this.offset + fp, this.howmany - fp);
/* 210 */       return List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new CreateTrie[2]));
/*     */     }
/*     */     
/*     */     public boolean shouldSplitFurther() {
/* 212 */       return (this.howmany > package$.MODULE$.thresholdFromSize(this.root.length, scala$collection$parallel$immutable$HashSetCombiner$CreateTrie$$$outer().combinerTaskSupport().parallelismLevel()));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HashSetCombiner$$anon$1 extends HashSetCombiner<T> {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\HashSetCombiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */