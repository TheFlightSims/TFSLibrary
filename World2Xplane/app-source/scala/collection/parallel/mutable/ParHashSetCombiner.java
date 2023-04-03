/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.FlatHashTable;
/*     */ import scala.collection.mutable.FlatHashTable$;
/*     */ import scala.collection.mutable.UnrolledBuffer;
/*     */ import scala.collection.mutable.UnrolledBuffer$;
/*     */ import scala.collection.parallel.BucketCombiner;
/*     */ import scala.collection.parallel.Task;
/*     */ import scala.collection.parallel.package$;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tmbAB\001\003\003\003\021!B\001\nQCJD\025m\0355TKR\034u.\0342j]\026\024(BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\021A\f'/\0317mK2T!a\002\005\002\025\r|G\016\\3di&|gNC\001\n\003\025\0318-\0317b+\tY!cE\002\001\031\t\002b!\004\b\021;i\tS\"\001\003\n\005=!!A\004\"vG.,GoQ8nE&tWM\035\t\003#Ia\001\001B\003\024\001\t\007QCA\001U\007\001\t\"A\006\016\021\005]AR\"\001\005\n\005eA!a\002(pi\"Lgn\032\t\003/mI!\001\b\005\003\007\005s\027\020E\002\037?Ai\021AA\005\003A\t\021!\002U1s\021\006\034\bnU3u!\rq\002\001\005\t\004GA\002bB\001\023.\035\t)CF\004\002'W9\021qEK\007\002Q)\021\021\006F\001\007yI|w\016\036 \n\003%I!a\002\005\n\005\r1\021B\001\0300\00351E.\031;ICNDG+\0312mK*\0211AB\005\003cI\022\021\002S1tQV#\030\016\\:\013\0059z\003\002\003\033\001\005\013\007I\021B\033\002\037Q\f'\r\\3M_\006$g)Y2u_J,\022A\016\t\003/]J!\001\017\005\003\007%sG\017\003\005;\001\t\005\t\025!\0037\003A!\030M\0317f\031>\fGMR1di>\024\b\005C\003=\001\021\005Q(\001\004=S:LGO\020\013\003CyBQ\001N\036A\002YBq\001\021\001A\002\023%Q'\001\003nCN\\\007b\002\"\001\001\004%IaQ\001\t[\006\0348n\030\023fcR\021Ai\022\t\003/\025K!A\022\005\003\tUs\027\016\036\005\b\021\006\013\t\0211\0017\003\rAH%\r\005\007\025\002\001\013\025\002\034\002\0135\f7o\033\021\t\0171\003\001\031!C\005k\005Qan\0348nCN\\G.\0328\t\0179\003\001\031!C\005\037\006qan\0348nCN\\G.\0328`I\025\fHC\001#Q\021\035AU*!AA\002YBaA\025\001!B\0231\024a\0038p]6\f7o\0337f]\002Bq\001\026\001A\002\023%Q'A\005tK\026$g/\0317vK\"9a\013\001a\001\n\0239\026!D:fK\0224\030\r\\;f?\022*\027\017\006\002E1\"9\001*VA\001\002\0041\004B\002.\001A\003&a'\001\006tK\026$g/\0317vK\002BQ\001\030\001\005\002u\013\001\002\n9mkN$S-\035\013\003=~k\021\001\001\005\006An\003\r\001E\001\005K2,W\016C\003c\001\021\0051-\001\004sKN,H\016\036\013\002;!)Q\r\001C\005M\006Y\001/\031:Q_B,H.\031;f+\0059\007c\0015k!9\021\021.L\007\002_%\0211N\r\002\t\007>tG/\0328ug\")Q\016\001C\005M\006Y1/Z9Q_B,H.\031;f\r\021y\007\001\0019\003'\005#G-\0338h\r2\fG\017S1tQR\013'\r\\3\024\0079\fH\017\005\002\030e&\0211\017\003\002\007\003:L(+\0324\021\007%,\b#\003\002w_\tia\t\\1u\021\006\034\b\016V1cY\026D\001\002\0378\003\002\003\006IAN\001\t]VlW\r\\3ng\"A!P\034B\001B\003%a'\001\002mM\"AAP\034B\001B\003%a'A\006j]N,W\r\032<bYV,\007\"\002\037o\t\003qHcB@\002\002\005\r\021Q\001\t\003=:DQ\001_?A\002YBQA_?A\002YBQ\001`?A\002YBq!!\003o\t\003\nY!\001\005u_N#(/\0338h)\t\ti\001\005\003\002\020\005UabA\f\002\022%\031\0211\003\005\002\rA\023X\rZ3g\023\021\t9\"!\007\003\rM#(/\0338h\025\r\t\031\002\003\005\007\003;qG\021A\033\002\027Q\f'\r\\3MK:<G\017\033\005\b\003CqG\021AA\022\003\035\031X\r^*ju\026$2\001RA\023\021\035\t9#a\bA\002Y\n!a\035>\t\017\005-b\016\"\001\002.\005Y\021N\\:feR,e\016\036:z)\0351\024qFA\032\003oAq!!\r\002*\001\007a'\001\005j]N,'\017^!u\021\035\t)$!\013A\002Y\n1bY8nKN\024UMZ8sK\"1\001-!\013A\002A1a!a\017\001\001\005u\"A\003$jY2\024En\\2lgN)\021\021H9\002@A9Q\"!\021\002F\005E\023bAA\"\t\t!A+Y:l!\0319\022q\t\034\002L%\031\021\021\n\005\003\rQ+\b\017\\33!\021I\027Q\n\016\n\007\005=sF\001\bV]J|G\016\\3e\005V4g-\032:\021\007y\013I\004C\006\002V\005e\"\021!Q\001\n\005]\023a\0022vG.,Go\035\t\006/\005e\0231J\005\004\0037B!!B!se\006L\bBCA0\003s\021\t\021)A\005\006)A/\0312mK\"Q\0211MA\035\005\013\007I\021A\033\002\r=4gm]3u\021)\t9'!\017\003\002\003\006IAN\001\b_\03247/\032;!\021)\tY'!\017\003\006\004%\t!N\001\bQ><X.\0318z\021)\ty'!\017\003\002\003\006IAN\001\tQ><X.\0318zA!9A(!\017\005\002\005MDCCA)\003k\n9(!\037\002|!A\021QKA9\001\004\t9\006C\004\002`\005E\004\031A@\t\017\005\r\024\021\017a\001m!9\0211NA9\001\0041\004\"\0032\002:\001\007I\021AA@+\t\t)\005\003\006\002\004\006e\002\031!C\001\003\013\013!B]3tk2$x\fJ3r)\r!\025q\021\005\n\021\006\005\025\021!a\001\003\013B\021\"a#\002:\001\006K!!\022\002\017I,7/\0367uA!A\021qRA\035\t\003\t\t*\001\003mK\0064Gc\001#\002\024\"A\021QSAG\001\004\t9*\001\003qe\0264\b#B\f\002\032\006\025\023bAAN\021\t1q\n\035;j_:D\021\"a(\002:\t\007I\021B\033\002\023\tdwnY6tSj,\007\002CAR\003s\001\013\021\002\034\002\025\tdwnY6tSj,\007\005\003\005\002(\006eB\021BAU\003)\021Gn\\2l'R\f'\017\036\013\004m\005-\006bBAW\003K\003\rAN\001\006E2|7m\033\005\t\003c\013I\004\"\003\0024\006qa.\032=u\0052|7m[*uCJ$Hc\001\034\0026\"9\021QVAX\001\0041\004\002CA]\003s!I!a/\002\023\031LG\016\034\"m_\016\\G\003CA#\003{\013y,a1\t\017\0055\026q\027a\001m!A\021\021YA\\\001\004\tY%A\003fY\026l7\017\003\005\002F\006]\006\031AA&\003%aWM\032;pm\026\0248\017\003\005\002J\006eB\021BAf\003%Ign]3si\006cG\016\006\005\002F\0055\027\021[Ak\021\035\ty-a2A\002Y\nQ!\031;Q_NDq!a5\002H\002\007a'A\005cK\032|'/\032)pg\"A\021\021YAd\001\004\tY\005\003\005\002Z\006eB\021AAn\003\025\031\b\017\\5u+\t\ti\016\005\004\002`\006\025\030\021K\007\003\003CT1!a9\007\003%IW.\\;uC\ndW-\003\003\002h\006\005(\001\002'jgRD\001\"a;\002:\021\005\023Q^\001\006[\026\024x-\032\013\004\t\006=\b\002CAy\003S\004\r!!\025\002\tQD\027\r\036\005\t\003k\fI\004\"\001\002x\006\0212\017[8vY\022\034\006\017\\5u\rV\024H\017[3s+\t\tI\020E\002\030\003wL1!!@\t\005\035\021un\0347fC:<\001B!\001\003\021\003!!1A\001\023!\006\024\b*Y:i'\026$8i\\7cS:,'\017E\002\037\005\0131q!\001\002\t\002\021\0219aE\002\003\006EDq\001\020B\003\t\003\021Y\001\006\002\003\004!Q!q\002B\003\005\004%\tAA\033\002!\021L7o\031:j[&t\027M\034;cSR\034\b\002\003B\n\005\013\001\013\021\002\034\002#\021L7o\031:j[&t\027M\034;cSR\034\b\005\003\006\003\030\t\025!\031!C\001\005U\n\021B\\;nE2|7m[:\t\021\tm!Q\001Q\001\nY\n!B\\;nE2|7m[:!\021)\021yB!\002C\002\023\005!!N\001\021I&\0348M]5nS:\fg\016^7bg.D\001Ba\t\003\006\001\006IAN\001\022I&\0348M]5nS:\fg\016^7bg.\004\003B\003B\024\005\013\021\r\021\"\001\003k\005ian\0348nCN\\G.\0328hi\"D\001Ba\013\003\006\001\006IAN\001\017]>tW.Y:lY\026tw\r\0365!\021!\021yC!\002\005\002\tE\022!B1qa2LX\003\002B\032\005s)\"A!\016\021\ty\001!q\007\t\004#\teBAB\n\003.\t\007Q\003")
/*     */ public abstract class ParHashSetCombiner<T> extends BucketCombiner<T, ParHashSet<T>, Object, ParHashSetCombiner<T>> implements FlatHashTable.HashUtils<T> {
/*     */   private final int tableLoadFactor;
/*     */   
/*     */   private int mask;
/*     */   
/*     */   private int nonmasklen;
/*     */   
/*     */   private int scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue;
/*     */   
/*     */   public final int sizeMapBucketBitSize() {
/* 119 */     return FlatHashTable.HashUtils.class.sizeMapBucketBitSize(this);
/*     */   }
/*     */   
/*     */   public final int sizeMapBucketSize() {
/* 119 */     return FlatHashTable.HashUtils.class.sizeMapBucketSize(this);
/*     */   }
/*     */   
/*     */   public int elemHashCode(Object elem) {
/* 119 */     return FlatHashTable.HashUtils.class.elemHashCode(this, elem);
/*     */   }
/*     */   
/*     */   public final int improve(int hcode, int seed) {
/* 119 */     return FlatHashTable.HashUtils.class.improve(this, hcode, seed);
/*     */   }
/*     */   
/*     */   private int tableLoadFactor() {
/* 119 */     return this.tableLoadFactor;
/*     */   }
/*     */   
/*     */   public ParHashSetCombiner(int tableLoadFactor) {
/* 119 */     super(
/* 120 */         ParHashSetCombiner$.MODULE$.numblocks());
/*     */     FlatHashTable.HashUtils.class.$init$(this);
/* 123 */     this.mask = ParHashSetCombiner$.MODULE$.discriminantmask();
/* 124 */     this.nonmasklen = ParHashSetCombiner$.MODULE$.nonmasklength();
/* 125 */     this.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue = 27;
/*     */   }
/*     */   
/*     */   private int mask() {
/*     */     return this.mask;
/*     */   }
/*     */   
/*     */   private void mask_$eq(int x$1) {
/*     */     this.mask = x$1;
/*     */   }
/*     */   
/*     */   private int nonmasklen() {
/*     */     return this.nonmasklen;
/*     */   }
/*     */   
/*     */   private void nonmasklen_$eq(int x$1) {
/*     */     this.nonmasklen = x$1;
/*     */   }
/*     */   
/*     */   public int scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue() {
/* 125 */     return this.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue;
/*     */   }
/*     */   
/*     */   private void scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue_$eq(int x$1) {
/* 125 */     this.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue = x$1;
/*     */   }
/*     */   
/*     */   public ParHashSetCombiner<T> $plus$eq(Object elem) {
/* 128 */     sz_$eq(sz() + 1);
/* 129 */     int hc = improve(elemHashCode((T)elem), scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue());
/* 130 */     int pos = hc >>> nonmasklen();
/* 131 */     if (buckets()[pos] == null)
/* 133 */       buckets()[pos] = new UnrolledBuffer(ClassTag$.MODULE$.Any()); 
/* 136 */     buckets()[pos].$plus$eq(elem);
/* 137 */     return this;
/*     */   }
/*     */   
/*     */   public ParHashSet<T> result() {
/* 141 */     FlatHashTable.Contents<T> contents = (size() >= ParHashSetCombiner$.MODULE$.numblocks() * sizeMapBucketSize()) ? parPopulate() : seqPopulate();
/* 142 */     return new ParHashSet<T>(contents);
/*     */   }
/*     */   
/*     */   private FlatHashTable.Contents<T> parPopulate() {
/* 147 */     AddingFlatHashTable table = new AddingFlatHashTable(this, size(), tableLoadFactor(), scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue());
/* 148 */     Tuple2 tuple2 = (Tuple2)combinerTaskSupport().executeAndWaitResult(new FillBlocks(this, buckets(), table, 0, (buckets()).length));
/* 148 */     if (tuple2 != null) {
/* 148 */       Tuple2 tuple21 = new Tuple2(BoxesRunTime.boxToInteger(tuple2._1$mcI$sp()), tuple2._2());
/* 148 */       int inserted = tuple21._1$mcI$sp();
/* 148 */       UnrolledBuffer leftovers = (UnrolledBuffer)tuple21._2();
/* 149 */       IntRef leftinserts = new IntRef(0);
/* 150 */       leftovers.foreach((Function1)new ParHashSetCombiner$$anonfun$parPopulate$1(this, table, (ParHashSetCombiner<T>)leftinserts));
/* 151 */       table.setSize(leftinserts.elem + inserted);
/* 152 */       return table.hashTableContents();
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public class ParHashSetCombiner$$anonfun$parPopulate$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ParHashSetCombiner.AddingFlatHashTable table$1;
/*     */     
/*     */     private final IntRef leftinserts$1;
/*     */     
/*     */     public final void apply(Object elem) {
/*     */       this.leftinserts$1.elem += this.table$1.insertEntry(0, this.table$1.tableLength(), (T)elem);
/*     */     }
/*     */     
/*     */     public ParHashSetCombiner$$anonfun$parPopulate$1(ParHashSetCombiner $outer, ParHashSetCombiner.AddingFlatHashTable table$1, IntRef leftinserts$1) {}
/*     */   }
/*     */   
/*     */   private FlatHashTable.Contents<T> seqPopulate() {
/* 158 */     FlatHashTable tbl = new ParHashSetCombiner$$anon$2(this);
/* 167 */     return tbl.hashTableContents();
/*     */   }
/*     */   
/*     */   public static <T> ParHashSetCombiner<T> apply() {
/*     */     return ParHashSetCombiner$.MODULE$.apply();
/*     */   }
/*     */   
/*     */   public class ParHashSetCombiner$$anon$2 implements FlatHashTable<T> {
/*     */     private transient int _loadFactor;
/*     */     
/*     */     private transient Object[] table;
/*     */     
/*     */     private transient int tableSize;
/*     */     
/*     */     private transient int threshold;
/*     */     
/*     */     private transient int[] sizemap;
/*     */     
/*     */     private transient int seedvalue;
/*     */     
/*     */     public int _loadFactor() {
/*     */       return this._loadFactor;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void _loadFactor_$eq(int x$1) {
/*     */       this._loadFactor = x$1;
/*     */     }
/*     */     
/*     */     public Object[] table() {
/*     */       return this.table;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void table_$eq(Object[] x$1) {
/*     */       this.table = x$1;
/*     */     }
/*     */     
/*     */     public int tableSize() {
/*     */       return this.tableSize;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void tableSize_$eq(int x$1) {
/*     */       this.tableSize = x$1;
/*     */     }
/*     */     
/*     */     public int threshold() {
/*     */       return this.threshold;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void threshold_$eq(int x$1) {
/*     */       this.threshold = x$1;
/*     */     }
/*     */     
/*     */     public int[] sizemap() {
/*     */       return this.sizemap;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void sizemap_$eq(int[] x$1) {
/*     */       this.sizemap = x$1;
/*     */     }
/*     */     
/*     */     public int seedvalue() {
/*     */       return this.seedvalue;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void seedvalue_$eq(int x$1) {
/*     */       this.seedvalue = x$1;
/*     */     }
/*     */     
/*     */     public int capacity(int expectedSize) {
/*     */       return FlatHashTable.class.capacity(this, expectedSize);
/*     */     }
/*     */     
/*     */     public int initialSize() {
/*     */       return FlatHashTable.class.initialSize(this);
/*     */     }
/*     */     
/*     */     public int randomSeed() {
/*     */       return FlatHashTable.class.randomSeed(this);
/*     */     }
/*     */     
/*     */     public int tableSizeSeed() {
/*     */       return FlatHashTable.class.tableSizeSeed(this);
/*     */     }
/*     */     
/*     */     public void init(ObjectInputStream in, Function1 f) {
/*     */       FlatHashTable.class.init(this, in, f);
/*     */     }
/*     */     
/*     */     public void serializeTo(ObjectOutputStream out) {
/*     */       FlatHashTable.class.serializeTo(this, out);
/*     */     }
/*     */     
/*     */     public Option<T> findEntry(Object elem) {
/*     */       return FlatHashTable.class.findEntry(this, elem);
/*     */     }
/*     */     
/*     */     public boolean containsEntry(Object elem) {
/*     */       return FlatHashTable.class.containsEntry(this, elem);
/*     */     }
/*     */     
/*     */     public boolean addEntry(Object elem) {
/*     */       return FlatHashTable.class.addEntry(this, elem);
/*     */     }
/*     */     
/*     */     public Option<T> removeEntry(Object elem) {
/*     */       return FlatHashTable.class.removeEntry(this, elem);
/*     */     }
/*     */     
/*     */     public Iterator<T> iterator() {
/*     */       return FlatHashTable.class.iterator(this);
/*     */     }
/*     */     
/*     */     public void nnSizeMapAdd(int h) {
/*     */       FlatHashTable.class.nnSizeMapAdd(this, h);
/*     */     }
/*     */     
/*     */     public void nnSizeMapRemove(int h) {
/*     */       FlatHashTable.class.nnSizeMapRemove(this, h);
/*     */     }
/*     */     
/*     */     public void nnSizeMapReset(int tableLength) {
/*     */       FlatHashTable.class.nnSizeMapReset(this, tableLength);
/*     */     }
/*     */     
/*     */     public final int totalSizeMapBuckets() {
/*     */       return FlatHashTable.class.totalSizeMapBuckets(this);
/*     */     }
/*     */     
/*     */     public int calcSizeMapSize(int tableLength) {
/*     */       return FlatHashTable.class.calcSizeMapSize(this, tableLength);
/*     */     }
/*     */     
/*     */     public void sizeMapInit(int tableLength) {
/*     */       FlatHashTable.class.sizeMapInit(this, tableLength);
/*     */     }
/*     */     
/*     */     public void sizeMapInitAndRebuild() {
/*     */       FlatHashTable.class.sizeMapInitAndRebuild(this);
/*     */     }
/*     */     
/*     */     public void printSizeMap() {
/*     */       FlatHashTable.class.printSizeMap(this);
/*     */     }
/*     */     
/*     */     public void printContents() {
/*     */       FlatHashTable.class.printContents(this);
/*     */     }
/*     */     
/*     */     public void sizeMapDisable() {
/*     */       FlatHashTable.class.sizeMapDisable(this);
/*     */     }
/*     */     
/*     */     public boolean isSizeMapDefined() {
/*     */       return FlatHashTable.class.isSizeMapDefined(this);
/*     */     }
/*     */     
/*     */     public boolean alwaysInitSizeMap() {
/*     */       return FlatHashTable.class.alwaysInitSizeMap(this);
/*     */     }
/*     */     
/*     */     public final int index(int hcode) {
/*     */       return FlatHashTable.class.index(this, hcode);
/*     */     }
/*     */     
/*     */     public void clearTable() {
/*     */       FlatHashTable.class.clearTable(this);
/*     */     }
/*     */     
/*     */     public FlatHashTable.Contents<T> hashTableContents() {
/*     */       return FlatHashTable.class.hashTableContents(this);
/*     */     }
/*     */     
/*     */     public void initWithContents(FlatHashTable.Contents c) {
/*     */       FlatHashTable.class.initWithContents(this, c);
/*     */     }
/*     */     
/*     */     public final int sizeMapBucketBitSize() {
/*     */       return FlatHashTable.HashUtils.class.sizeMapBucketBitSize((FlatHashTable.HashUtils)this);
/*     */     }
/*     */     
/*     */     public final int sizeMapBucketSize() {
/*     */       return FlatHashTable.HashUtils.class.sizeMapBucketSize((FlatHashTable.HashUtils)this);
/*     */     }
/*     */     
/*     */     public int elemHashCode(Object elem) {
/*     */       return FlatHashTable.HashUtils.class.elemHashCode((FlatHashTable.HashUtils)this, elem);
/*     */     }
/*     */     
/*     */     public final int improve(int hcode, int seed) {
/*     */       return FlatHashTable.HashUtils.class.improve((FlatHashTable.HashUtils)this, hcode, seed);
/*     */     }
/*     */     
/*     */     public ParHashSetCombiner$$anon$2(ParHashSetCombiner $outer) {
/*     */       FlatHashTable.HashUtils.class.$init$((FlatHashTable.HashUtils)this);
/*     */       FlatHashTable.class.$init$(this);
/*     */       sizeMapInit((table()).length);
/*     */       seedvalue_$eq($outer.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue());
/*     */       Predef$.MODULE$.refArrayOps((Object[])$outer.buckets()).withFilter((Function1)new ParHashSetCombiner$$anon$2$$anonfun$1(this)).foreach((Function1)new ParHashSetCombiner$$anon$2$$anonfun$2(this));
/*     */     }
/*     */     
/*     */     public class ParHashSetCombiner$$anon$2$$anonfun$1 extends AbstractFunction1<UnrolledBuffer<Object>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public ParHashSetCombiner$$anon$2$$anonfun$1(ParHashSetCombiner$$anon$2 $outer) {}
/*     */       
/*     */       public final boolean apply(UnrolledBuffer buffer) {
/*     */         return (buffer != null);
/*     */       }
/*     */     }
/*     */     
/*     */     public class ParHashSetCombiner$$anon$2$$anonfun$2 extends AbstractFunction1<UnrolledBuffer<Object>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public ParHashSetCombiner$$anon$2$$anonfun$2(ParHashSetCombiner$$anon$2 $outer) {}
/*     */       
/*     */       public final void apply(UnrolledBuffer buffer) {
/*     */         buffer.foreach((Function1)new ParHashSetCombiner$$anon$2$$anonfun$2$$anonfun$apply$1(this));
/*     */       }
/*     */       
/*     */       public class ParHashSetCombiner$$anon$2$$anonfun$2$$anonfun$apply$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public ParHashSetCombiner$$anon$2$$anonfun$2$$anonfun$apply$1(ParHashSetCombiner$$anon$2$$anonfun$2 $outer) {}
/*     */         
/*     */         public final boolean apply(Object elem) {
/*     */           return this.$outer.$outer.addEntry((T)elem);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class AddingFlatHashTable implements FlatHashTable<T> {
/*     */     private transient int _loadFactor;
/*     */     
/*     */     private transient Object[] table;
/*     */     
/*     */     private transient int tableSize;
/*     */     
/*     */     private transient int threshold;
/*     */     
/*     */     private transient int[] sizemap;
/*     */     
/*     */     private transient int seedvalue;
/*     */     
/*     */     public int _loadFactor() {
/* 177 */       return this._loadFactor;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void _loadFactor_$eq(int x$1) {
/* 177 */       this._loadFactor = x$1;
/*     */     }
/*     */     
/*     */     public Object[] table() {
/* 177 */       return this.table;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void table_$eq(Object[] x$1) {
/* 177 */       this.table = x$1;
/*     */     }
/*     */     
/*     */     public int tableSize() {
/* 177 */       return this.tableSize;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void tableSize_$eq(int x$1) {
/* 177 */       this.tableSize = x$1;
/*     */     }
/*     */     
/*     */     public int threshold() {
/* 177 */       return this.threshold;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void threshold_$eq(int x$1) {
/* 177 */       this.threshold = x$1;
/*     */     }
/*     */     
/*     */     public int[] sizemap() {
/* 177 */       return this.sizemap;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void sizemap_$eq(int[] x$1) {
/* 177 */       this.sizemap = x$1;
/*     */     }
/*     */     
/*     */     public int seedvalue() {
/* 177 */       return this.seedvalue;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void seedvalue_$eq(int x$1) {
/* 177 */       this.seedvalue = x$1;
/*     */     }
/*     */     
/*     */     public int capacity(int expectedSize) {
/* 177 */       return FlatHashTable.class.capacity(this, expectedSize);
/*     */     }
/*     */     
/*     */     public int initialSize() {
/* 177 */       return FlatHashTable.class.initialSize(this);
/*     */     }
/*     */     
/*     */     public int randomSeed() {
/* 177 */       return FlatHashTable.class.randomSeed(this);
/*     */     }
/*     */     
/*     */     public int tableSizeSeed() {
/* 177 */       return FlatHashTable.class.tableSizeSeed(this);
/*     */     }
/*     */     
/*     */     public void init(ObjectInputStream in, Function1 f) {
/* 177 */       FlatHashTable.class.init(this, in, f);
/*     */     }
/*     */     
/*     */     public void serializeTo(ObjectOutputStream out) {
/* 177 */       FlatHashTable.class.serializeTo(this, out);
/*     */     }
/*     */     
/*     */     public Option<T> findEntry(Object elem) {
/* 177 */       return FlatHashTable.class.findEntry(this, elem);
/*     */     }
/*     */     
/*     */     public boolean containsEntry(Object elem) {
/* 177 */       return FlatHashTable.class.containsEntry(this, elem);
/*     */     }
/*     */     
/*     */     public boolean addEntry(Object elem) {
/* 177 */       return FlatHashTable.class.addEntry(this, elem);
/*     */     }
/*     */     
/*     */     public Option<T> removeEntry(Object elem) {
/* 177 */       return FlatHashTable.class.removeEntry(this, elem);
/*     */     }
/*     */     
/*     */     public Iterator<T> iterator() {
/* 177 */       return FlatHashTable.class.iterator(this);
/*     */     }
/*     */     
/*     */     public void nnSizeMapAdd(int h) {
/* 177 */       FlatHashTable.class.nnSizeMapAdd(this, h);
/*     */     }
/*     */     
/*     */     public void nnSizeMapRemove(int h) {
/* 177 */       FlatHashTable.class.nnSizeMapRemove(this, h);
/*     */     }
/*     */     
/*     */     public void nnSizeMapReset(int tableLength) {
/* 177 */       FlatHashTable.class.nnSizeMapReset(this, tableLength);
/*     */     }
/*     */     
/*     */     public final int totalSizeMapBuckets() {
/* 177 */       return FlatHashTable.class.totalSizeMapBuckets(this);
/*     */     }
/*     */     
/*     */     public int calcSizeMapSize(int tableLength) {
/* 177 */       return FlatHashTable.class.calcSizeMapSize(this, tableLength);
/*     */     }
/*     */     
/*     */     public void sizeMapInit(int tableLength) {
/* 177 */       FlatHashTable.class.sizeMapInit(this, tableLength);
/*     */     }
/*     */     
/*     */     public void sizeMapInitAndRebuild() {
/* 177 */       FlatHashTable.class.sizeMapInitAndRebuild(this);
/*     */     }
/*     */     
/*     */     public void printSizeMap() {
/* 177 */       FlatHashTable.class.printSizeMap(this);
/*     */     }
/*     */     
/*     */     public void printContents() {
/* 177 */       FlatHashTable.class.printContents(this);
/*     */     }
/*     */     
/*     */     public void sizeMapDisable() {
/* 177 */       FlatHashTable.class.sizeMapDisable(this);
/*     */     }
/*     */     
/*     */     public boolean isSizeMapDefined() {
/* 177 */       return FlatHashTable.class.isSizeMapDefined(this);
/*     */     }
/*     */     
/*     */     public boolean alwaysInitSizeMap() {
/* 177 */       return FlatHashTable.class.alwaysInitSizeMap(this);
/*     */     }
/*     */     
/*     */     public final int index(int hcode) {
/* 177 */       return FlatHashTable.class.index(this, hcode);
/*     */     }
/*     */     
/*     */     public void clearTable() {
/* 177 */       FlatHashTable.class.clearTable(this);
/*     */     }
/*     */     
/*     */     public FlatHashTable.Contents<T> hashTableContents() {
/* 177 */       return FlatHashTable.class.hashTableContents(this);
/*     */     }
/*     */     
/*     */     public void initWithContents(FlatHashTable.Contents c) {
/* 177 */       FlatHashTable.class.initWithContents(this, c);
/*     */     }
/*     */     
/*     */     public final int sizeMapBucketBitSize() {
/* 177 */       return FlatHashTable.HashUtils.class.sizeMapBucketBitSize((FlatHashTable.HashUtils)this);
/*     */     }
/*     */     
/*     */     public final int sizeMapBucketSize() {
/* 177 */       return FlatHashTable.HashUtils.class.sizeMapBucketSize((FlatHashTable.HashUtils)this);
/*     */     }
/*     */     
/*     */     public int elemHashCode(Object elem) {
/* 177 */       return FlatHashTable.HashUtils.class.elemHashCode((FlatHashTable.HashUtils)this, elem);
/*     */     }
/*     */     
/*     */     public final int improve(int hcode, int seed) {
/* 177 */       return FlatHashTable.HashUtils.class.improve((FlatHashTable.HashUtils)this, hcode, seed);
/*     */     }
/*     */     
/*     */     public AddingFlatHashTable(ParHashSetCombiner $outer, int numelems, int lf, int inseedvalue) {
/* 177 */       FlatHashTable.HashUtils.class.$init$((FlatHashTable.HashUtils)this);
/* 177 */       FlatHashTable.class.$init$(this);
/* 178 */       _loadFactor_$eq(lf);
/* 179 */       table_$eq(new Object[capacity(FlatHashTable$.MODULE$.sizeForThreshold(numelems, _loadFactor()))]);
/* 180 */       tableSize_$eq(0);
/* 181 */       threshold_$eq(FlatHashTable$.MODULE$.newThreshold(_loadFactor(), (table()).length));
/* 182 */       seedvalue_$eq(inseedvalue);
/* 183 */       sizeMapInit((table()).length);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 185 */       Predef$ predef$ = Predef$.MODULE$;
/* 185 */       return (new StringOps("AFHT(%s)")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger((table()).length) }));
/*     */     }
/*     */     
/*     */     public int tableLength() {
/* 187 */       return (table()).length;
/*     */     }
/*     */     
/*     */     public void setSize(int sz) {
/* 189 */       tableSize_$eq(sz);
/*     */     }
/*     */     
/*     */     public int insertEntry(int insertAt, int comesBefore, Object elem) {
/* 210 */       int h = insertAt;
/* 211 */       if (insertAt == -1)
/* 211 */         h = index(elemHashCode((T)elem)); 
/* 212 */       Object entry = table()[h];
/*     */       while (true) {
/* 213 */         if (entry == null) {
/* 219 */           table()[h] = elem;
/* 229 */           nnSizeMapAdd(h);
/* 230 */           return 1;
/*     */         } 
/*     */         if ((entry == elem) ? true : ((entry == null) ? false : ((entry instanceof Number) ? BoxesRunTime.equalsNumObject((Number)entry, elem) : ((entry instanceof Character) ? BoxesRunTime.equalsCharObject((Character)entry, elem) : entry.equals(elem)))))
/*     */           return 0; 
/*     */         if (++h >= comesBefore)
/*     */           return -1; 
/*     */         entry = table()[h];
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class FillBlocks implements Task<Tuple2<Object, UnrolledBuffer<Object>>, FillBlocks> {
/*     */     private final UnrolledBuffer<Object>[] buckets;
/*     */     
/*     */     private final ParHashSetCombiner<T>.AddingFlatHashTable table;
/*     */     
/*     */     private final int offset;
/*     */     
/*     */     private final int howmany;
/*     */     
/*     */     private Tuple2<Object, UnrolledBuffer<Object>> result;
/*     */     
/*     */     private final int blocksize;
/*     */     
/*     */     private volatile Throwable throwable;
/*     */     
/*     */     public Throwable throwable() {
/* 236 */       return this.throwable;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void throwable_$eq(Throwable x$1) {
/* 236 */       this.throwable = x$1;
/*     */     }
/*     */     
/*     */     public FillBlocks repr() {
/* 236 */       return (FillBlocks)Task.class.repr(this);
/*     */     }
/*     */     
/*     */     public void forwardThrowable() {
/* 236 */       Task.class.forwardThrowable(this);
/*     */     }
/*     */     
/*     */     public void tryLeaf(Option lastres) {
/* 236 */       Task.class.tryLeaf(this, lastres);
/*     */     }
/*     */     
/*     */     public void tryMerge(Object t) {
/* 236 */       Task.class.tryMerge(this, t);
/*     */     }
/*     */     
/*     */     public void mergeThrowables(Task that) {
/* 236 */       Task.class.mergeThrowables(this, that);
/*     */     }
/*     */     
/*     */     public void signalAbort() {
/* 236 */       Task.class.signalAbort(this);
/*     */     }
/*     */     
/*     */     public int offset() {
/* 236 */       return this.offset;
/*     */     }
/*     */     
/*     */     public int howmany() {
/* 236 */       return this.howmany;
/*     */     }
/*     */     
/*     */     public FillBlocks(ParHashSetCombiner $outer, UnrolledBuffer[] buckets, ParHashSetCombiner.AddingFlatHashTable table, int offset, int howmany) {
/* 236 */       Task.class.$init$(this);
/* 238 */       this.result = new Tuple2(BoxesRunTime.boxToInteger(-2147483648), new UnrolledBuffer(ClassTag$.MODULE$.Any()));
/* 251 */       this.blocksize = table.tableLength() >> ParHashSetCombiner$.MODULE$.discriminantbits();
/*     */     }
/*     */     
/*     */     public Tuple2<Object, UnrolledBuffer<Object>> result() {
/*     */       return this.result;
/*     */     }
/*     */     
/*     */     public void result_$eq(Tuple2<Object, UnrolledBuffer<Object>> x$1) {
/*     */       this.result = x$1;
/*     */     }
/*     */     
/*     */     public void leaf(Option prev) {
/*     */       int i = offset();
/*     */       int totalinserts = 0;
/*     */       UnrolledBuffer<Object> leftover = new UnrolledBuffer(ClassTag$.MODULE$.Any());
/*     */       while (i < offset() + howmany()) {
/*     */         Tuple2<Object, UnrolledBuffer<Object>> tuple2 = fillBlock(i, this.buckets[i], leftover);
/*     */         if (tuple2 != null) {
/*     */           Tuple2 tuple21 = new Tuple2(BoxesRunTime.boxToInteger(tuple2._1$mcI$sp()), tuple2._2());
/*     */           int inserted = tuple21._1$mcI$sp();
/*     */           UnrolledBuffer<Object> intonextblock = (UnrolledBuffer)tuple21._2();
/*     */           totalinserts += inserted;
/*     */           leftover = intonextblock;
/*     */           i++;
/*     */           continue;
/*     */         } 
/*     */         throw new MatchError(tuple2);
/*     */       } 
/*     */       result_$eq(new Tuple2(BoxesRunTime.boxToInteger(totalinserts), leftover));
/*     */     }
/*     */     
/*     */     private int blocksize() {
/* 251 */       return this.blocksize;
/*     */     }
/*     */     
/*     */     private int blockStart(int block) {
/* 252 */       return block * blocksize();
/*     */     }
/*     */     
/*     */     private int nextBlockStart(int block) {
/* 253 */       return (block + 1) * blocksize();
/*     */     }
/*     */     
/*     */     private Tuple2<Object, UnrolledBuffer<Object>> fillBlock(int block, UnrolledBuffer<Object> elems, UnrolledBuffer<Object> leftovers) {
/* 255 */       int beforePos = nextBlockStart(block);
/* 258 */       Tuple2 tuple2 = (elems == null) ? new Tuple2(BoxesRunTime.boxToInteger(0), UnrolledBuffer$.MODULE$.apply((Seq)Nil$.MODULE$, ClassTag$.MODULE$.Any())) : insertAll(-1, beforePos, elems);
/* 258 */       if (tuple2 != null) {
/* 258 */         Tuple2 tuple21 = new Tuple2(BoxesRunTime.boxToInteger(tuple2._1$mcI$sp()), tuple2._2());
/* 258 */         int elemsIn = tuple21._1$mcI$sp();
/* 258 */         UnrolledBuffer elemsLeft = (UnrolledBuffer)tuple21._2();
/* 261 */         Tuple2<Object, UnrolledBuffer<Object>> tuple22 = insertAll(blockStart(block), beforePos, leftovers);
/* 261 */         if (tuple22 != null) {
/* 261 */           Tuple2 tuple23 = new Tuple2(BoxesRunTime.boxToInteger(tuple22._1$mcI$sp()), tuple22._2());
/* 261 */           int leftoversIn = tuple23._1$mcI$sp();
/* 261 */           UnrolledBuffer leftoversLeft = (UnrolledBuffer)tuple23._2();
/* 264 */           return new Tuple2(BoxesRunTime.boxToInteger(elemsIn + leftoversIn), elemsLeft.concat(leftoversLeft));
/*     */         } 
/*     */         throw new MatchError(tuple22);
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     private Tuple2<Object, UnrolledBuffer<Object>> insertAll(int atPos, int beforePos, UnrolledBuffer elems) {
/* 267 */       UnrolledBuffer leftovers = new UnrolledBuffer(ClassTag$.MODULE$.Any());
/* 268 */       int inserted = 0;
/* 270 */       UnrolledBuffer.Unrolled unrolled = elems.headPtr();
/* 271 */       int i = 0;
/* 272 */       ParHashSetCombiner.AddingFlatHashTable t = this.table;
/* 273 */       while (unrolled != null) {
/* 274 */         Object[] chunkarr = (Object[])unrolled.array();
/* 275 */         int chunksz = unrolled.size();
/* 276 */         while (i < chunksz) {
/* 277 */           Object elem = chunkarr[i];
/* 278 */           int res = t.insertEntry(atPos, beforePos, (T)elem);
/* 279 */           inserted += res;
/* 279 */           (res >= 0) ? BoxedUnit.UNIT : 
/* 280 */             leftovers.$plus$eq(elem);
/* 281 */           i++;
/*     */         } 
/* 283 */         i = 0;
/* 284 */         unrolled = unrolled.next();
/*     */       } 
/* 296 */       return new Tuple2(BoxesRunTime.boxToInteger(inserted), leftovers);
/*     */     }
/*     */     
/*     */     public List<FillBlocks> split() {
/* 299 */       int fp = howmany() / 2;
/* 300 */       (new FillBlocks[2])[0] = new FillBlocks(scala$collection$parallel$mutable$ParHashSetCombiner$FillBlocks$$$outer(), this.buckets, this.table, offset(), fp);
/* 300 */       (new FillBlocks[2])[1] = new FillBlocks(scala$collection$parallel$mutable$ParHashSetCombiner$FillBlocks$$$outer(), this.buckets, this.table, offset() + fp, howmany() - fp);
/* 300 */       return List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new FillBlocks[2]));
/*     */     }
/*     */     
/*     */     public void merge(FillBlocks that) {
/* 304 */       int atPos = blockStart(that.offset());
/* 305 */       int beforePos = blockStart(that.offset() + that.howmany());
/* 306 */       Tuple2<Object, UnrolledBuffer<Object>> tuple2 = insertAll(atPos, beforePos, (UnrolledBuffer<Object>)result()._2());
/* 306 */       if (tuple2 != null) {
/* 306 */         Tuple2 tuple21 = new Tuple2(BoxesRunTime.boxToInteger(tuple2._1$mcI$sp()), tuple2._2());
/* 306 */         int inserted = tuple21._1$mcI$sp();
/* 306 */         UnrolledBuffer remainingLeftovers = (UnrolledBuffer)tuple21._2();
/* 311 */         result_$eq(new Tuple2(BoxesRunTime.boxToInteger(result()._1$mcI$sp() + that.result()._1$mcI$sp() + inserted), remainingLeftovers.concat((UnrolledBuffer)that.result()._2())));
/*     */         return;
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public boolean shouldSplitFurther() {
/* 313 */       return (howmany() > package$.MODULE$.thresholdFromSize(ParHashMapCombiner$.MODULE$.numblocks(), scala$collection$parallel$mutable$ParHashSetCombiner$FillBlocks$$$outer().combinerTaskSupport().parallelismLevel()));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ParHashSetCombiner$$anon$1 extends ParHashSetCombiner<T> {
/*     */     public ParHashSetCombiner$$anon$1() {
/* 325 */       super(FlatHashTable$.MODULE$.defaultLoadFactor());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParHashSetCombiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */