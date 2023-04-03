/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.CustomParallelizable;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.IndexedSeqLike;
/*     */ import scala.collection.IndexedSeqOptimized;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.SeqView;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.mutable.ParArray;
/*     */ import scala.collection.parallel.mutable.ParArray$;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\ref!B\001\003\003\003I!\001D,sCB\004X\rZ!se\006L(BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)\"AC\t\024\013\001Y1D\b\022\021\0071iq\"D\001\003\023\tq!AA\006BEN$(/Y2u'\026\f\bC\001\t\022\031\001!QA\005\001C\002M\021\021\001V\t\003)a\001\"!\006\f\016\003\031I!a\006\004\003\0179{G\017[5oOB\021Q#G\005\0035\031\0211!\0218z!\raAdD\005\003;\t\021!\"\0238eKb,GmU3r!\021aqdD\021\n\005\001\022!!C!se\006LH*[6f!\ra\001a\004\t\005G\021za%D\001\005\023\t)CA\001\013DkN$x.\034)be\006dG.\0327ju\006\024G.\032\t\004O-zQ\"\001\025\013\005\rI#B\001\026\005\003!\001\030M]1mY\026d\027B\001\027)\005!\001\026M]!se\006L\b\"\002\030\001\t\003y\023A\002\037j]&$h\bF\001\"\021\031\t\004\001)C)e\005qA\017[5t\007>dG.Z2uS>tW#A\021\t\rQ\002\001\025\"\0256\0031!xnQ8mY\026\034G/[8o)\t\tc\007C\0038g\001\007\021%\001\003sKB\024\b\"B\035\001\r\003Q\024aB3mK6$\026mZ\013\002wA\031AhP\b\016\003uR!A\020\004\002\017I,g\r\\3di&\021\001)\020\002\t\0072\f7o\035+bO\")!\t\001C\001\007\006aQ\r\\3n\033\006t\027NZ3tiV\tA\tE\002F\021>q!!\006$\n\005\0353\021A\002)sK\022,g-\003\002J\025\ni1\t\\1tg6\013g.\0334fgRT!a\022\004)\t\005cu*\025\t\003+5K!A\024\004\003\025\021,\007O]3dCR,G-I\001Q\003M)8/\032\021fY\026lG+Y4!S:\034H/Z1eC\005\021\026A\002\032/cAr\003\007C\003U\001\031\005Q+\001\004mK:<G\017[\013\002-B\021QcV\005\0031\032\0211!\0238u\021\025Q\006A\"\001\\\003\025\t\007\017\0357z)\tyA\fC\003^3\002\007a+A\003j]\022,\007\020C\003`\001\031\005\001-\001\004va\022\fG/\032\013\004C\022,\007CA\013c\023\t\031gA\001\003V]&$\b\"B/_\001\0041\006\"\0024_\001\004y\021\001B3mK6DQ\001\033\001\007\002%\fQ!\031:sCf,\022A\033\t\004+-|\021B\0017\007\005\025\t%O]1z\021\025q\007\001\"\021p\003\r\001\030M]\013\002M!)\021\017\001C\005e\006aQ\r\\3nK:$8\t\\1tgV\t1\017\r\002uqB\031Q)^<\n\005YT%!B\"mCN\034\bC\001\ty\t%I\b/!A\001\002\013\0051CA\002`IEBQa\037\001\005Bq\fq\001^8BeJ\f\0270F\002~\003\003!2A`A\004!\r)2n \t\004!\005\005AaBA\002u\n\007\021Q\001\002\002+F\021q\002\007\005\n\003\023Q\030\021!a\002\003\027\t!\"\032<jI\026t7-\032\0232!\rath \005\b\003\037\001A\021IA\t\0031\031HO]5oOB\023XMZ5y+\t\t\031\002\005\003\002\026\005}QBAA\f\025\021\tI\"a\007\002\t1\fgn\032\006\003\003;\tAA[1wC&!\021\021EA\f\005\031\031FO]5oO\"1\021Q\005\001\005B=\nQa\0317p]\026D\001\"!\013\001A\023E\0231F\001\013]\026<()^5mI\026\024XCAA\027!\025a\021qF\b\"\023\r\t\tD\001\002\b\005VLG\016Z3s\017\035\t)D\001E\001\003o\tAb\026:baB,G-\021:sCf\0042\001DA\035\r\031\t!\001#\001\002<M!\021\021HA\037!\r)\022qH\005\004\003\0032!AB!osJ+g\rC\004/\003s!\t!!\022\025\005\005]\002BCA%\003s\021\r\021\"\003\002L\005\tR)\0349us^\023\030\r\0359fI\006\023(/Y=\026\005\0055\003CBA(\003#\ni$\004\002\002:\0319\0211KA\035\005\005U#!B8g%\0264W\003BA,\003;\032b!!\025\002Z\005\005\004\003\002\007\001\0037\0022\001EA/\t\035\021\022\021\013b\001\003?\n2\001FA\037!\r)\0221M\005\004\003K2!\001D*fe&\fG.\033>bE2,\007B\0035\002R\t\025\r\021\"\001\002jU\021\0211\016\t\005+-\fY\006C\006\002p\005E#\021!Q\001\n\005-\024AB1se\006L\b\005C\004/\003#\"\t!a\035\025\t\005U\024q\017\t\007\003\037\n\t&a\027\t\017!\f\t\b1\001\002l!Q\021(!\025\t\006\004%\t!a\037\026\005\005u\004\003\002\037@\0037B1\"!!\002R!\005\t\025)\003\002~\005AQ\r\\3n)\006<\007\005\003\004U\003#\"\t!\026\005\b5\006EC\021AAD)\021\tY&!#\t\ru\013)\t1\001W\021\035y\026\021\013C\001\003\033#R!YAH\003#Ca!XAF\001\0041\006b\0024\002\f\002\007\0211\f\005\n\003+\013I\004)A\005\003\033\n!#R7qif<&/\0319qK\022\f%O]1zA!A\021\021TA\035\t\003\tY*A\003f[B$\0300\006\003\002\036\006\rVCAAP!\021a\001!!)\021\007A\t\031\013B\004\023\003/\023\r!a\030\t\021\005\035\026\021\bC\001\003S\013A!\\1lKV!\0211VAY)\021\ti+a-\021\t1\001\021q\026\t\004!\005EFA\002\n\002&\n\0071\003\003\005\0026\006\025\006\031AA\037\003\005A\b\002CA]\003s!\031!a/\002\031\r\fgNQ;jY\0224%o\\7\026\t\005u\026q\033\013\005\003\013Y\016\005\006\002B\006\035\0271ZAk\0033l!!a1\013\007\005\025G!A\004hK:,'/[2\n\t\005%\0271\031\002\r\007\006t')^5mI\032\023x.\034\031\005\003\033\f\t\016\005\003\r\001\005=\007c\001\t\002R\022Y\0211[A\\\003\003\005\tQ!\001\024\005\ryFE\r\t\004!\005]GA\002\n\0028\n\0071\003\005\003\r\001\005U\007\002CAo\003o\003\035!a8\002\0035\004B\001P \002V\"A\021\021FA\035\t\003\t\031/\006\003\002f\006-XCAAt!\035a\021qFAu\003_\0042\001EAv\t\035\ti/!9C\002M\021\021!\021\t\005\031q\tIOB\004\002t\006e\"!!>\003\r=4')\037;f'\031\t\t0a>\002bA!A\002AA}!\r)\0221`\005\004\003{4!\001\002\"zi\026D!\002[Ay\005\013\007I\021\001B\001+\t\021\031\001\005\003\026W\006e\bbCA8\003c\024\t\021)A\005\005\007AqALAy\t\003\021I\001\006\003\003\f\t5\001\003BA(\003cDq\001\033B\004\001\004\021\031\001C\004:\003c$\tA!\005\026\005\tM\001\003\002\037@\003sDa\001VAy\t\003)\006b\002.\002r\022\005!\021\004\013\005\003s\024Y\002\003\004^\005/\001\rA\026\005\b?\006EH\021\001B\020)\025\t'\021\005B\022\021\031i&Q\004a\001-\"9aM!\bA\002\005eha\002B\024\003s\021!\021\006\002\b_\032\034\006n\034:u'\031\021)Ca\013\002bA!A\002\001B\027!\r)\"qF\005\004\005c1!!B*i_J$\bB\0035\003&\t\025\r\021\"\001\0036U\021!q\007\t\005+-\024i\003C\006\002p\t\025\"\021!Q\001\n\t]\002b\002\030\003&\021\005!Q\b\013\005\005\021\t\005\005\003\002P\t\025\002b\0025\003<\001\007!q\007\005\bs\t\025B\021\001B#+\t\0219\005\005\003=\t5\002B\002+\003&\021\005Q\013C\004[\005K!\tA!\024\025\t\t5\"q\n\005\007;\n-\003\031\001,\t\017}\023)\003\"\001\003TQ)\021M!\026\003X!1QL!\025A\002YCqA\032B)\001\004\021iCB\004\003\\\005e\"A!\030\003\r=47\t[1s'\031\021IFa\030\002bA!A\002\001B1!\r)\"1M\005\004\005K2!\001B\"iCJD!\002\033B-\005\013\007I\021\001B5+\t\021Y\007\005\003\026W\n\005\004bCA8\0053\022\t\021)A\005\005WBqA\fB-\t\003\021\t\b\006\003\003t\tU\004\003BA(\0053Bq\001\033B8\001\004\021Y\007C\004:\0053\"\tA!\037\026\005\tm\004\003\002\037@\005CBa\001\026B-\t\003)\006b\002.\003Z\021\005!\021\021\013\005\005C\022\031\t\003\004^\005\002\rA\026\005\b?\neC\021\001BD)\025\t'\021\022BF\021\031i&Q\021a\001-\"9aM!\"A\002\t\005da\002BH\003s\021!\021\023\002\006_\032Le\016^\n\007\005\033\023\031*!\031\021\0071\001a\013\003\006i\005\033\023)\031!C\001\005/+\"A!'\021\007UYg\013C\006\002p\t5%\021!Q\001\n\te\005b\002\030\003\016\022\005!q\024\013\005\005C\023\031\013\005\003\002P\t5\005b\0025\003\036\002\007!\021\024\005\bs\t5E\021\001BT+\t\021I\013E\002=YCa\001\026BG\t\003)\006b\002.\003\016\022\005!q\026\013\004-\nE\006BB/\003.\002\007a\013C\004`\005\033#\tA!.\025\013\005\0249L!/\t\ru\023\031\f1\001W\021\0311'1\027a\001-\0329!QXA\035\005\t}&AB8g\031>twm\005\004\003<\n\005\027\021\r\t\005\031\001\021\031\rE\002\026\005\013L1Aa2\007\005\021auN\\4\t\025!\024YL!b\001\n\003\021Y-\006\002\003NB!Qc\033Bb\021-\tyGa/\003\002\003\006IA!4\t\0179\022Y\f\"\001\003TR!!Q\033Bl!\021\tyEa/\t\017!\024\t\0161\001\003N\"9\021Ha/\005\002\tmWC\001Bo!\021atHa1\t\rQ\023Y\f\"\001V\021\035Q&1\030C\001\005G$BAa1\003f\"1QL!9A\002YCqa\030B^\t\003\021I\017F\003b\005W\024i\017\003\004^\005O\004\rA\026\005\bM\n\035\b\031\001Bb\r\035\021\t0!\017\003\005g\024qa\0344GY>\fGo\005\004\003p\nU\030\021\r\t\005\031\001\0219\020E\002\026\005sL1Aa?\007\005\0251En\\1u\021)A'q\036BC\002\023\005!q`\013\003\007\003\001B!F6\003x\"Y\021q\016Bx\005\003\005\013\021BB\001\021\035q#q\036C\001\007\017!Ba!\003\004\fA!\021q\nBx\021\035A7Q\001a\001\007\003Aq!\017Bx\t\003\031y!\006\002\004\022A!Ah\020B|\021\031!&q\036C\001+\"9!La<\005\002\r]A\003\002B|\0073Aa!XB\013\001\0041\006bB0\003p\022\0051Q\004\013\006C\016}1\021\005\005\007;\016m\001\031\001,\t\017\031\034Y\0021\001\003x\03291QEA\035\005\r\035\"\001C8g\t>,(\r\\3\024\r\r\r2\021FA1!\021a\001aa\013\021\007U\031i#C\002\0040\031\021a\001R8vE2,\007B\0035\004$\t\025\r\021\"\001\0044U\0211Q\007\t\005+-\034Y\003C\006\002p\r\r\"\021!Q\001\n\rU\002b\002\030\004$\021\00511\b\013\005\007{\031y\004\005\003\002P\r\r\002b\0025\004:\001\0071Q\007\005\bs\r\rB\021AB\"+\t\031)\005\005\003=\r-\002B\002+\004$\021\005Q\013C\004[\007G!\taa\023\025\t\r-2Q\n\005\007;\016%\003\031\001,\t\017}\033\031\003\"\001\004RQ)\021ma\025\004V!1Qla\024A\002YCqAZB(\001\004\031YCB\004\004Z\005e\"aa\027\003\023=4'i\\8mK\006t7CBB,\007;\n\t\007\005\003\r\001\r}\003cA\013\004b%\03111\r\004\003\017\t{w\016\\3b]\"Q\001na\026\003\006\004%\taa\032\026\005\r%\004\003B\013l\007?B1\"a\034\004X\t\005\t\025!\003\004j!9afa\026\005\002\r=D\003BB9\007g\002B!a\024\004X!9\001n!\034A\002\r%\004bB\035\004X\021\0051qO\013\003\007s\002B\001P \004`!1Aka\026\005\002UCqAWB,\t\003\031y\b\006\003\004`\r\005\005BB/\004~\001\007a\013C\004`\007/\"\ta!\"\025\013\005\0349i!#\t\ru\033\031\t1\001W\021\035171\021a\001\007?2qa!$\002:\t\031yI\001\004pMVs\027\016^\n\007\007\027\033\t*!\031\021\0071\001\021\r\003\006i\007\027\023)\031!C\001\007++\"aa&\021\007UY\027\rC\006\002p\r-%\021!Q\001\n\r]\005b\002\030\004\f\022\0051Q\024\013\005\007?\033\t\013\005\003\002P\r-\005b\0025\004\034\002\0071q\023\005\bs\r-E\021ABS+\t\0319\013E\002=\005Da\001VBF\t\003)\006b\002.\004\f\022\0051Q\026\013\004C\016=\006BB/\004,\002\007a\013C\004`\007\027#\taa-\025\013\005\034)la.\t\ru\033\t\f1\001W\021\03117\021\027a\001C\002")
/*     */ public abstract class WrappedArray<T> extends AbstractSeq<T> implements IndexedSeq<T>, ArrayLike<T, WrappedArray<T>>, CustomParallelizable<T, ParArray<T>> {
/*     */   public Combiner<T, ParArray<T>> parCombiner() {
/*  34 */     return CustomParallelizable.class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Object> deep() {
/*  34 */     return ArrayLike$class.deep(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
/*  34 */     return TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
/*  34 */     return IterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
/*  34 */     return IterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$head() {
/*  34 */     return IterableLike.class.head(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$tail() {
/*  34 */     return TraversableLike.class.tail(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$last() {
/*  34 */     return TraversableLike.class.last(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$init() {
/*  34 */     return TraversableLike.class.init(this);
/*     */   }
/*     */   
/*     */   public boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
/*  34 */     return IterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
/*  34 */     return SeqLike.class.endsWith(this, that);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  34 */     return IndexedSeqOptimized.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  34 */     IndexedSeqOptimized.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/*  34 */     return IndexedSeqOptimized.class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/*  34 */     return IndexedSeqOptimized.class.exists(this, p);
/*     */   }
/*     */   
/*     */   public Option<T> find(Function1 p) {
/*  34 */     return IndexedSeqOptimized.class.find(this, p);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/*  34 */     return (B)IndexedSeqOptimized.class.foldLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/*  34 */     return (B)IndexedSeqOptimized.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/*  34 */     return (B)IndexedSeqOptimized.class.reduceLeft(this, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/*  34 */     return (B)IndexedSeqOptimized.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  34 */     return (That)IndexedSeqOptimized.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  34 */     return (That)IndexedSeqOptimized.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public WrappedArray<T> slice(int from, int until) {
/*  34 */     return (WrappedArray<T>)IndexedSeqOptimized.class.slice(this, from, until);
/*     */   }
/*     */   
/*     */   public T head() {
/*  34 */     return (T)IndexedSeqOptimized.class.head(this);
/*     */   }
/*     */   
/*     */   public WrappedArray<T> tail() {
/*  34 */     return (WrappedArray<T>)IndexedSeqOptimized.class.tail(this);
/*     */   }
/*     */   
/*     */   public T last() {
/*  34 */     return (T)IndexedSeqOptimized.class.last(this);
/*     */   }
/*     */   
/*     */   public WrappedArray<T> init() {
/*  34 */     return (WrappedArray<T>)IndexedSeqOptimized.class.init(this);
/*     */   }
/*     */   
/*     */   public WrappedArray<T> take(int n) {
/*  34 */     return (WrappedArray<T>)IndexedSeqOptimized.class.take(this, n);
/*     */   }
/*     */   
/*     */   public WrappedArray<T> drop(int n) {
/*  34 */     return (WrappedArray<T>)IndexedSeqOptimized.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public WrappedArray<T> takeRight(int n) {
/*  34 */     return (WrappedArray<T>)IndexedSeqOptimized.class.takeRight(this, n);
/*     */   }
/*     */   
/*     */   public WrappedArray<T> dropRight(int n) {
/*  34 */     return (WrappedArray<T>)IndexedSeqOptimized.class.dropRight(this, n);
/*     */   }
/*     */   
/*     */   public Tuple2<WrappedArray<T>, WrappedArray<T>> splitAt(int n) {
/*  34 */     return IndexedSeqOptimized.class.splitAt(this, n);
/*     */   }
/*     */   
/*     */   public WrappedArray<T> takeWhile(Function1 p) {
/*  34 */     return (WrappedArray<T>)IndexedSeqOptimized.class.takeWhile(this, p);
/*     */   }
/*     */   
/*     */   public WrappedArray<T> dropWhile(Function1 p) {
/*  34 */     return (WrappedArray<T>)IndexedSeqOptimized.class.dropWhile(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<WrappedArray<T>, WrappedArray<T>> span(Function1 p) {
/*  34 */     return IndexedSeqOptimized.class.span(this, p);
/*     */   }
/*     */   
/*     */   public <B> boolean sameElements(GenIterable that) {
/*  34 */     return IndexedSeqOptimized.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start, int len) {
/*  34 */     IndexedSeqOptimized.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public int lengthCompare(int len) {
/*  34 */     return IndexedSeqOptimized.class.lengthCompare(this, len);
/*     */   }
/*     */   
/*     */   public int segmentLength(Function1 p, int from) {
/*  34 */     return IndexedSeqOptimized.class.segmentLength(this, p, from);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p, int from) {
/*  34 */     return IndexedSeqOptimized.class.indexWhere(this, p, from);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p, int end) {
/*  34 */     return IndexedSeqOptimized.class.lastIndexWhere(this, p, end);
/*     */   }
/*     */   
/*     */   public WrappedArray<T> reverse() {
/*  34 */     return (WrappedArray<T>)IndexedSeqOptimized.class.reverse(this);
/*     */   }
/*     */   
/*     */   public Iterator<T> reverseIterator() {
/*  34 */     return IndexedSeqOptimized.class.reverseIterator(this);
/*     */   }
/*     */   
/*     */   public <B> boolean startsWith(GenSeq that, int offset) {
/*  34 */     return IndexedSeqOptimized.class.startsWith(this, that, offset);
/*     */   }
/*     */   
/*     */   public <B> boolean endsWith(GenSeq that) {
/*  34 */     return IndexedSeqOptimized.class.endsWith(this, that);
/*     */   }
/*     */   
/*     */   public GenericCompanion<IndexedSeq> companion() {
/*  34 */     return IndexedSeq$class.companion(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<T> seq() {
/*  34 */     return IndexedSeq$class.seq(this);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  34 */     return IndexedSeqLike$class.view(this);
/*     */   }
/*     */   
/*     */   public IndexedSeqView<T, WrappedArray<T>> view(int from, int until) {
/*  34 */     return IndexedSeqLike$class.view(this, from, until);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  34 */     return IndexedSeqLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public Iterator<T> iterator() {
/*  34 */     return IndexedSeqLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public <A1> Buffer<A1> toBuffer() {
/*  34 */     return IndexedSeqLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public WrappedArray() {
/*  35 */     IndexedSeqLike.class.$init$(this);
/*  35 */     IndexedSeq.class.$init$(this);
/*  35 */     IndexedSeqLike$class.$init$(this);
/*  35 */     IndexedSeq$class.$init$(this);
/*  35 */     IndexedSeqOptimized.class.$init$(this);
/*  35 */     ArrayLike$class.$init$(this);
/*  35 */     CustomParallelizable.class.$init$(this);
/*     */   }
/*     */   
/*     */   public WrappedArray<T> thisCollection() {
/*  41 */     return this;
/*     */   }
/*     */   
/*     */   public WrappedArray<T> toCollection(WrappedArray<T> repr) {
/*  42 */     return repr;
/*     */   }
/*     */   
/*     */   public ClassTag<T> elemManifest() {
/*  48 */     return Predef$.MODULE$.ClassManifest().fromClass(ScalaRunTime$.MODULE$.arrayElementClass(elemTag()));
/*     */   }
/*     */   
/*     */   public ParArray<T> par() {
/*  62 */     return ParArray$.MODULE$.handoff(array());
/*     */   }
/*     */   
/*     */   private Class<?> elementClass() {
/*  65 */     return ScalaRunTime$.MODULE$.arrayElementClass(array().getClass());
/*     */   }
/*     */   
/*     */   public <U> Object toArray(ClassTag evidence$1) {
/*  68 */     Predef$ predef$ = Predef$.MODULE$;
/*  68 */     Class<?> thatElementClass = ScalaRunTime$.MODULE$.arrayElementClass(evidence$1);
/*  69 */     return (elementClass() == thatElementClass) ? 
/*  70 */       array() : 
/*     */       
/*  72 */       TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/*  75 */     return "WrappedArray";
/*     */   }
/*     */   
/*     */   public WrappedArray<T> clone() {
/*  78 */     return WrappedArray$.MODULE$.make(ScalaRunTime$.MODULE$.array_clone(array()));
/*     */   }
/*     */   
/*     */   public Builder<T, WrappedArray<T>> newBuilder() {
/*  83 */     return new WrappedArrayBuilder<T>(elemTag());
/*     */   }
/*     */   
/*     */   public static <T> CanBuildFrom<WrappedArray<?>, T, WrappedArray<T>> canBuildFrom(ClassTag<T> paramClassTag) {
/*     */     return WrappedArray$.MODULE$.canBuildFrom(paramClassTag);
/*     */   }
/*     */   
/*     */   public static <T> WrappedArray<T> make(Object paramObject) {
/*     */     return WrappedArray$.MODULE$.make(paramObject);
/*     */   }
/*     */   
/*     */   public static <T> WrappedArray<T> empty() {
/*     */     return WrappedArray$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public abstract ClassTag<T> elemTag();
/*     */   
/*     */   public abstract int length();
/*     */   
/*     */   public abstract T apply(int paramInt);
/*     */   
/*     */   public abstract void update(int paramInt, T paramT);
/*     */   
/*     */   public abstract Object array();
/*     */   
/*     */   public static class WrappedArray$$anon$1 implements CanBuildFrom<WrappedArray<?>, T, WrappedArray<T>> {
/*     */     private final ClassTag m$1;
/*     */     
/*     */     public WrappedArray$$anon$1(ClassTag m$1) {}
/*     */     
/*     */     public Builder<T, WrappedArray<T>> apply(WrappedArray from) {
/* 116 */       return ArrayBuilder$.MODULE$.<T>make(this.m$1).mapResult((Function1<Object, WrappedArray<T>>)new WrappedArray$$anon$1$$anonfun$apply$1(this));
/*     */     }
/*     */     
/*     */     public class WrappedArray$$anon$1$$anonfun$apply$1 extends AbstractFunction1<Object, WrappedArray<T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final WrappedArray<T> apply(Object x) {
/* 116 */         return WrappedArray$.MODULE$.make(x);
/*     */       }
/*     */       
/*     */       public WrappedArray$$anon$1$$anonfun$apply$1(WrappedArray$$anon$1 $outer) {}
/*     */     }
/*     */     
/*     */     public Builder<T, WrappedArray<T>> apply() {
/* 118 */       return ArrayBuilder$.MODULE$.<T>make(this.m$1).mapResult((Function1<Object, WrappedArray<T>>)new WrappedArray$$anon$1$$anonfun$apply$2(this));
/*     */     }
/*     */     
/*     */     public class WrappedArray$$anon$1$$anonfun$apply$2 extends AbstractFunction1<Object, WrappedArray<T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final WrappedArray<T> apply(Object x) {
/* 118 */         return WrappedArray$.MODULE$.make(x);
/*     */       }
/*     */       
/*     */       public WrappedArray$$anon$1$$anonfun$apply$2(WrappedArray$$anon$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofRef<T> extends WrappedArray<T> implements Serializable {
/*     */     private final T[] array;
/*     */     
/*     */     private ClassTag<T> elemTag;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public T[] array() {
/* 123 */       return this.array;
/*     */     }
/*     */     
/*     */     public ofRef(Object[] array) {}
/*     */     
/*     */     private ClassTag elemTag$lzycompute() {
/* 124 */       synchronized (this) {
/* 124 */         if (!this.bitmap$0) {
/* 124 */           this.elemTag = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayElementClass(array().getClass()));
/* 124 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/mutable/WrappedArray}.Lscala/collection/mutable/WrappedArray$ofRef;}} */
/* 124 */         return this.elemTag;
/*     */       } 
/*     */     }
/*     */     
/*     */     public ClassTag<T> elemTag() {
/* 124 */       return this.bitmap$0 ? this.elemTag : elemTag$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/* 125 */       return (array()).length;
/*     */     }
/*     */     
/*     */     public T apply(int index) {
/* 126 */       return array()[index];
/*     */     }
/*     */     
/*     */     public void update(int index, Object elem) {
/* 127 */       array()[index] = (T)elem;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofByte extends WrappedArray<Object> implements Serializable {
/*     */     private final byte[] array;
/*     */     
/*     */     public byte[] array() {
/* 130 */       return this.array;
/*     */     }
/*     */     
/*     */     public ofByte(byte[] array) {}
/*     */     
/*     */     public ClassTag<Object> elemTag() {
/* 131 */       return ClassTag$.MODULE$.Byte();
/*     */     }
/*     */     
/*     */     public int length() {
/* 132 */       return (array()).length;
/*     */     }
/*     */     
/*     */     public byte apply(int index) {
/* 133 */       return array()[index];
/*     */     }
/*     */     
/*     */     public void update(int index, byte elem) {
/* 134 */       array()[index] = elem;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofShort extends WrappedArray<Object> implements Serializable {
/*     */     private final short[] array;
/*     */     
/*     */     public short[] array() {
/* 137 */       return this.array;
/*     */     }
/*     */     
/*     */     public ofShort(short[] array) {}
/*     */     
/*     */     public ClassTag<Object> elemTag() {
/* 138 */       return ClassTag$.MODULE$.Short();
/*     */     }
/*     */     
/*     */     public int length() {
/* 139 */       return (array()).length;
/*     */     }
/*     */     
/*     */     public short apply(int index) {
/* 140 */       return array()[index];
/*     */     }
/*     */     
/*     */     public void update(int index, short elem) {
/* 141 */       array()[index] = elem;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofChar extends WrappedArray<Object> implements Serializable {
/*     */     private final char[] array;
/*     */     
/*     */     public char[] array() {
/* 144 */       return this.array;
/*     */     }
/*     */     
/*     */     public ofChar(char[] array) {}
/*     */     
/*     */     public ClassTag<Object> elemTag() {
/* 145 */       return ClassTag$.MODULE$.Char();
/*     */     }
/*     */     
/*     */     public int length() {
/* 146 */       return (array()).length;
/*     */     }
/*     */     
/*     */     public char apply(int index) {
/* 147 */       return array()[index];
/*     */     }
/*     */     
/*     */     public void update(int index, char elem) {
/* 148 */       array()[index] = elem;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofInt extends WrappedArray<Object> implements Serializable {
/*     */     private final int[] array;
/*     */     
/*     */     public int[] array() {
/* 151 */       return this.array;
/*     */     }
/*     */     
/*     */     public ofInt(int[] array) {}
/*     */     
/*     */     public ClassTag<Object> elemTag() {
/* 152 */       return ClassTag$.MODULE$.Int();
/*     */     }
/*     */     
/*     */     public int length() {
/* 153 */       return (array()).length;
/*     */     }
/*     */     
/*     */     public int apply(int index) {
/* 154 */       return apply$mcII$sp(index);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int index) {
/* 154 */       return array()[index];
/*     */     }
/*     */     
/*     */     public void update(int index, int elem) {
/* 155 */       array()[index] = elem;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofLong extends WrappedArray<Object> implements Serializable {
/*     */     private final long[] array;
/*     */     
/*     */     public long[] array() {
/* 158 */       return this.array;
/*     */     }
/*     */     
/*     */     public ofLong(long[] array) {}
/*     */     
/*     */     public ClassTag<Object> elemTag() {
/* 159 */       return ClassTag$.MODULE$.Long();
/*     */     }
/*     */     
/*     */     public int length() {
/* 160 */       return (array()).length;
/*     */     }
/*     */     
/*     */     public long apply(int index) {
/* 161 */       return apply$mcJI$sp(index);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int index) {
/* 161 */       return array()[index];
/*     */     }
/*     */     
/*     */     public void update(int index, long elem) {
/* 162 */       array()[index] = elem;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofFloat extends WrappedArray<Object> implements Serializable {
/*     */     private final float[] array;
/*     */     
/*     */     public float[] array() {
/* 165 */       return this.array;
/*     */     }
/*     */     
/*     */     public ofFloat(float[] array) {}
/*     */     
/*     */     public ClassTag<Object> elemTag() {
/* 166 */       return ClassTag$.MODULE$.Float();
/*     */     }
/*     */     
/*     */     public int length() {
/* 167 */       return (array()).length;
/*     */     }
/*     */     
/*     */     public float apply(int index) {
/* 168 */       return apply$mcFI$sp(index);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int index) {
/* 168 */       return array()[index];
/*     */     }
/*     */     
/*     */     public void update(int index, float elem) {
/* 169 */       array()[index] = elem;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofDouble extends WrappedArray<Object> implements Serializable {
/*     */     private final double[] array;
/*     */     
/*     */     public double[] array() {
/* 172 */       return this.array;
/*     */     }
/*     */     
/*     */     public ofDouble(double[] array) {}
/*     */     
/*     */     public ClassTag<Object> elemTag() {
/* 173 */       return ClassTag$.MODULE$.Double();
/*     */     }
/*     */     
/*     */     public int length() {
/* 174 */       return (array()).length;
/*     */     }
/*     */     
/*     */     public double apply(int index) {
/* 175 */       return apply$mcDI$sp(index);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int index) {
/* 175 */       return array()[index];
/*     */     }
/*     */     
/*     */     public void update(int index, double elem) {
/* 176 */       array()[index] = elem;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofBoolean extends WrappedArray<Object> implements Serializable {
/*     */     private final boolean[] array;
/*     */     
/*     */     public boolean[] array() {
/* 179 */       return this.array;
/*     */     }
/*     */     
/*     */     public ofBoolean(boolean[] array) {}
/*     */     
/*     */     public ClassTag<Object> elemTag() {
/* 180 */       return ClassTag$.MODULE$.Boolean();
/*     */     }
/*     */     
/*     */     public int length() {
/* 181 */       return (array()).length;
/*     */     }
/*     */     
/*     */     public boolean apply(int index) {
/* 182 */       return apply$mcZI$sp(index);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int index) {
/* 182 */       return array()[index];
/*     */     }
/*     */     
/*     */     public void update(int index, boolean elem) {
/* 183 */       array()[index] = elem;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofUnit extends WrappedArray<BoxedUnit> implements Serializable {
/*     */     private final BoxedUnit[] array;
/*     */     
/*     */     public BoxedUnit[] array() {
/* 186 */       return this.array;
/*     */     }
/*     */     
/*     */     public ofUnit(BoxedUnit[] array) {}
/*     */     
/*     */     public ClassTag<BoxedUnit> elemTag() {
/* 187 */       return ClassTag$.MODULE$.Unit();
/*     */     }
/*     */     
/*     */     public int length() {
/* 188 */       return (array()).length;
/*     */     }
/*     */     
/*     */     public void apply(int index) {
/* 189 */       apply$mcVI$sp(index);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int index) {
/* 189 */       array()[index];
/*     */     }
/*     */     
/*     */     public void update(int index, BoxedUnit elem) {
/* 190 */       array()[index] = elem;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\WrappedArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */