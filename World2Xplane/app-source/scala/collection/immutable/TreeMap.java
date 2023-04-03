/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function0;
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
/*     */ import scala.collection.GenMapLike;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.MapLike;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.SortedMap;
/*     */ import scala.collection.SortedMapLike;
/*     */ import scala.collection.SortedSet;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.FilterMonadic;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.Sorted;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParMap;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tex!B\001\003\021\003I\021a\002+sK\026l\025\r\035\006\003\007\021\t\021\"[7nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\021\005)YQ\"\001\002\007\0131\021\001\022A\007\003\017Q\023X-Z'baN\0311BD\033\021\007=\021B#D\001\021\025\t\tB!A\004hK:,'/[2\n\005M\001\"!G%n[V$\030M\0317f'>\024H/\0323NCB4\025m\031;pef\004\"AC\013\007\t1\021\001AF\013\004/\005Z3CB\013\03195\022T\007\005\002\03255\ta!\003\002\034\r\t1\021I\\=SK\032\004BAC\017 U%\021aD\001\002\n'>\024H/\0323NCB\004\"\001I\021\r\001\021)!%\006b\001G\t\t\021)\005\002%OA\021\021$J\005\003M\031\021qAT8uQ&tw\r\005\002\032Q%\021\021F\002\002\004\003:L\bC\001\021,\t\031aS\003\"b\001G\t\t!\tE\003/_}Q\023'D\001\005\023\t\001DAA\007T_J$X\rZ'ba2K7.\032\t\005\025Uy\"\006E\003\013g}Q\023'\003\0025\005\t9Q*\0319MS.,\007CA\r7\023\t9dA\001\007TKJL\027\r\\5{C\ndW\r\003\005:+\t\005\t\025!\003;\003\021!(/Z3\021\tmrtD\013\b\003\025qJ!!\020\002\002\031I+GM\0217bG.$&/Z3\n\005}\002%\001\002+sK\026T!!\020\002\t\021\t+\"Q1A\005\004\r\013\001b\034:eKJLgnZ\013\002\tB\031Q)T\020\017\005\031[eBA$K\033\005A%BA%\t\003\031a$o\\8u}%\tq!\003\002M\r\0059\001/Y2lC\036,\027B\001(P\005!y%\017Z3sS:<'B\001'\007\021!\tVC!A!\002\023!\025!C8sI\026\024\030N\\4!\021\025\031V\003\"\003U\003\031a\024N\\5u}Q\021Qk\026\013\003cYCQA\021*A\004\021CQ!\017*A\002iBQ!W\013\005\002i\013\021\"[:T[\006dG.\032:\025\007ms\006\r\005\002\0329&\021QL\002\002\b\005>|G.Z1o\021\025y\006\f1\001 \003\005A\b\"B1Y\001\004y\022!A=)\ta\033g\r\033\t\0033\021L!!\032\004\003\025\021,\007O]3dCR,G-I\001h\003e)8/\032\021a_J$WM]5oO:bG\017\031\021j]N$X-\0313\"\003%\faA\r\0302a9\002\004BB6\026A\023EC.\001\006oK^\024U/\0337eKJ,\022!\034\t\005]F\034\030'D\001p\025\t\001H!A\004nkR\f'\r\\3\n\005I|'a\002\"vS2$WM\035\t\0053Q|\"&\003\002v\r\t1A+\0369mKJBQa^\013\005Ba\fAa]5{KV\t\021\020\005\002\032u&\0211P\002\002\004\023:$\b\"B*\026\t\003iH#\001@\025\005Ez\b\"\002\"}\001\b!\005bBA\002+\021\005\023QA\001\ne\006tw-Z%na2$R!MA\004\003#A\001\"!\003\002\002\001\007\0211B\001\005MJ|W\016\005\003\032\003\033y\022bAA\b\r\t1q\n\035;j_:D\001\"a\005\002\002\001\007\0211B\001\006k:$\030\016\034\005\b\003/)B\021IA\r\003\025\021\030M\\4f)\025\t\0241DA\017\021\035\tI!!\006A\002}Aq!a\005\002\026\001\007q\004C\004\002\nU!\t%!\t\025\007E\n\031\003C\004\002\n\005}\001\031A\020\t\017\005\035R\003\"\021\002*\005\021Ao\034\013\004c\005-\002bBA\024\003K\001\ra\b\005\b\003')B\021IA\030)\r\t\024\021\007\005\b\003'\ti\0031\001 \021\035\t)$\006C!\003o\t\001BZ5sgR\\U-_\013\002?!9\0211H\013\005B\005]\022a\0027bgR\\U-\037\005\b\003)B\021IA!\003\035\031w.\0349be\026$R!_A\"\003\017Bq!!\022\002>\001\007q$\001\002la!9\021\021JA\037\001\004y\022AA62\021\035\ti%\006C!\003\037\nA\001[3bIV\t1\017C\004\002TU!\t%!\026\002\025!,\027\rZ(qi&|g.\006\002\002XA!\021$!\004t\021\035\tY&\006C!\003\037\nA\001\\1ti\"9\021qL\013\005B\005U\023A\0037bgR|\005\017^5p]\"9\0211M\013\005B\005\025\024\001\002;bS2,\022!\r\005\b\003S*B\021IA3\003\021Ig.\033;\t\017\0055T\003\"\021\002p\005!AM]8q)\r\t\024\021\017\005\b\003g\nY\0071\001z\003\005q\007bBA<+\021\005\023\021P\001\005i\006\\W\rF\0022\003wBq!a\035\002v\001\007\021\020C\004\002\000U!\t%!!\002\013Md\027nY3\025\013E\n\031)!\"\t\017\005%\021Q\020a\001s\"9\0211CA?\001\004I\bbBAE+\021\005\0231R\001\nIJ|\007OU5hQR$2!MAG\021\035\t\031(a\"A\002eDq!!%\026\t\003\n\031*A\005uC.,'+[4iiR\031\021'!&\t\017\005M\024q\022a\001s\"9\021\021T\013\005B\005m\025aB:qY&$\030\t\036\013\005\003;\013y\n\005\003\032iF\n\004bBA:\003/\003\r!\037\005\t\003G+\002\025\"\003\002&\006Q1m\\;oi^C\027\016\\3\025\007e\f9\013\003\005\002*\006\005\006\031AAV\003\005\001\b#B\r\002.N\\\026bAAX\r\tIa)\0368di&|g.\r\005\b\003g+B\021IA[\003%!'o\0349XQ&dW\rF\0022\003oC\001\"!+\0022\002\007\0211\026\005\b\003w+B\021IA_\003%!\030m[3XQ&dW\rF\0022\003C\001\"!+\002:\002\007\0211\026\005\b\003\007,B\021IAc\003\021\031\b/\0318\025\t\005u\025q\031\005\t\003S\013\t\r1\001\002,\"9\0211Z\013\005B\005\025\024!B3naRL\bbBAh+\021\005\023\021[\001\bkB$\027\r^3e+\021\t\031.!7\025\r\005U\027q\\Ar!\025QQcHAl!\r\001\023\021\034\003\t\0037\fiM1\001\002^\n\021!)M\t\003U\035Bq!!9\002N\002\007q$A\002lKfD\001\"!:\002N\002\007\021q[\001\006m\006dW/\032\005\b\003S,B\021IAv\003\025!\003\017\\;t+\021\ti/a=\025\t\005=\030Q\037\t\006\025Uy\022\021\037\t\004A\005MH\001CAn\003O\024\r!!8\t\021\005]\030q\035a\001\003s\f!a\033<\021\013e!x$!=\t\017\005%X\003\"\021\002~V!\021q B\003)!\021\tAa\002\003\016\tE\001#\002\006\026?\t\r\001c\001\021\003\006\021A\0211\\A~\005\004\ti\016\003\005\003\n\005m\b\031\001B\006\003\025)G.Z72!\025IBo\bB\002\021!\021y!a?A\002\t-\021!B3mK6\024\004\002\003B\n\003w\004\rA!\006\002\013\025dW-\\:\021\013e\0219Ba\003\n\007\teaA\001\006=e\026\004X-\031;fIzBqA!\b\026\t\003\022y\"\001\006%a2,8\017\n9mkN,BA!\t\003(Q!!1\005B\025!\025QQc\bB\023!\r\001#q\005\003\t\0037\024YB1\001\002^\"A!1\006B\016\001\004\021i#\001\002ygB)aFa\f\0034%\031!\021\007\003\003%\035+g\016\026:bm\026\0248/\0312mK>s7-\032\t\0063Q|\"Q\005\005\b\005o)B\021\001B\035\003\031Ign]3siV!!1\bB!)\031\021iDa\021\003FA)!\"F\020\003@A\031\001E!\021\005\021\005m'Q\007b\001\003;Dq!!9\0036\001\007q\004\003\005\002f\nU\002\031\001B \021\035\021I%\006C\001\005\027\na\001J7j]V\034HcA\031\003N!9\021\021\035B$\001\004y\002b\002B)+\021\005#1K\001\004O\026$H\003\002B+\005/\002B!GA\007U!9\021\021\035B(\001\004y\002b\002B.+\021\005#QL\001\tSR,'/\031;peV\021!q\f\t\005]\t\0054/C\002\003d\021\021\001\"\023;fe\006$xN\035\005\b\005O*B\021\tB5\0031YW-_:Ji\026\024\030\r^8s+\t\021Y\007\005\003/\005Cz\002b\002B8+\021\005#\021O\001\017m\006dW/Z:Ji\026\024\030\r^8s+\t\021\031\b\005\003/\005CR\003b\002B<+\021\005#\021P\001\tG>tG/Y5ogR\0311La\037\t\017\005\005(Q\017a\001?!9!qP\013\005B\t\005\025aC5t\t\0264\027N\\3e\003R$2a\027BB\021\035\t\tO! A\002}AqAa\"\026\t\003\022I)A\004g_J,\027m\0315\026\t\t-%1\024\013\005\005\033\023\031\nE\002\032\005\037K1A!%\007\005\021)f.\033;\t\021\tU%Q\021a\001\005/\013\021A\032\t\0073\00556O!'\021\007\001\022Y\nB\004\003\036\n\025%\031A\022\003\003UCaaU\006\005\002\t\005F#A\005\t\017\005-7\002\"\001\003&V1!q\025BW\005c#BA!+\0034B1!\"\006BV\005_\0032\001\tBW\t\031\021#1\025b\001GA\031\001E!-\005\r1\022\031K1\001$\021!\021)La)A\004\t]\026aA8sIB!Q)\024BV\021\035\021Yl\003C\002\005{\013AbY1o\005VLG\016\032$s_6,bAa0\003X\nmG\003\002Ba\005?\004\022b\004Bb\005\017\024\031N!8\n\007\t\025\007C\001\007DC:\024U/\0337e\rJ|W\016\005\003\003J\n-W\"A\006\n\t\t5'q\032\002\005\007>dG.C\002\003RB\021\001cU8si\026$W*\0319GC\016$xN]=\021\re!(Q\033Bm!\r\001#q\033\003\007E\te&\031A\022\021\007\001\022Y\016\002\004-\005s\023\ra\t\t\007\025U\021)N!7\t\021\tU&\021\030a\002\005C\004B!R'\003V\"I!Q]\006\002\002\023%!q]\001\fe\026\fGMU3t_24X\r\006\002\003jB!!1\036B{\033\t\021iO\003\003\003p\nE\030\001\0027b]\036T!Aa=\002\t)\fg/Y\005\005\005o\024iO\001\004PE*,7\r\036")
/*     */ public class TreeMap<A, B> implements SortedMap<A, B>, SortedMapLike<A, B, TreeMap<A, B>>, MapLike<A, B, TreeMap<A, B>>, Serializable {
/*     */   private final RedBlackTree.Tree<A, B> tree;
/*     */   
/*     */   private final Ordering<A> ordering;
/*     */   
/*     */   public SortedSet<A> keySet() {
/*  48 */     return SortedMap$class.keySet(this);
/*     */   }
/*     */   
/*     */   public SortedMap<A, B> filterKeys(Function1 p) {
/*  48 */     return SortedMap$class.filterKeys(this, p);
/*     */   }
/*     */   
/*     */   public <C> SortedMap<A, C> mapValues(Function1 f) {
/*  48 */     return SortedMap$class.mapValues(this, f);
/*     */   }
/*     */   
/*     */   public boolean hasAll(Iterator j) {
/*  48 */     return Sorted.class.hasAll((Sorted)this, j);
/*     */   }
/*     */   
/*     */   public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  48 */     return Map$class.toMap(this, ev);
/*     */   }
/*     */   
/*     */   public Map<A, B> seq() {
/*  48 */     return Map$class.seq(this);
/*     */   }
/*     */   
/*     */   public <B1> Map<A, B1> withDefault(Function1 d) {
/*  48 */     return Map$class.withDefault(this, d);
/*     */   }
/*     */   
/*     */   public <B1> Map<A, B1> withDefaultValue(Object d) {
/*  48 */     return Map$class.withDefaultValue(this, d);
/*     */   }
/*     */   
/*     */   public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
/*  48 */     return MapLike$class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public <C, That> That transform(Function2 f, CanBuildFrom bf) {
/*  48 */     return (That)MapLike$class.transform(this, f, bf);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  48 */     return MapLike.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public <B1> B1 getOrElse(Object key, Function0 default) {
/*  48 */     return (B1)MapLike.class.getOrElse(this, key, default);
/*     */   }
/*     */   
/*     */   public B apply(Object key) {
/*  48 */     return (B)MapLike.class.apply(this, key);
/*     */   }
/*     */   
/*     */   public Iterable<A> keys() {
/*  48 */     return MapLike.class.keys(this);
/*     */   }
/*     */   
/*     */   public Iterable<B> values() {
/*  48 */     return MapLike.class.values(this);
/*     */   }
/*     */   
/*     */   public B default(Object key) {
/*  48 */     return (B)MapLike.class.default(this, key);
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> filterNot(Function1 p) {
/*  48 */     return (TreeMap<A, B>)MapLike.class.filterNot(this, p);
/*     */   }
/*     */   
/*     */   public Seq<Tuple2<A, B>> toSeq() {
/*  48 */     return MapLike.class.toSeq(this);
/*     */   }
/*     */   
/*     */   public <C> Buffer<C> toBuffer() {
/*  48 */     return MapLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  48 */     return MapLike.class.addString(this, b, start, sep, end);
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/*  48 */     return MapLike.class.stringPrefix(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  48 */     return MapLike.class.toString(this);
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> $minus(Object elem1, Object elem2, Seq elems) {
/*  48 */     return (TreeMap<A, B>)Subtractable.class.$minus((Subtractable)this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> $minus$minus(GenTraversableOnce xs) {
/*  48 */     return (TreeMap<A, B>)Subtractable.class.$minus$minus((Subtractable)this, xs);
/*     */   }
/*     */   
/*     */   public <A1 extends A, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/*  48 */     return PartialFunction.class.orElse((PartialFunction)this, that);
/*     */   }
/*     */   
/*     */   public <C> PartialFunction<A, C> andThen(Function1 k) {
/*  48 */     return PartialFunction.class.andThen((PartialFunction)this, k);
/*     */   }
/*     */   
/*     */   public Function1<A, Option<B>> lift() {
/*  48 */     return PartialFunction.class.lift((PartialFunction)this);
/*     */   }
/*     */   
/*     */   public <A1 extends A, B1> B1 applyOrElse(Object x, Function1 default) {
/*  48 */     return (B1)PartialFunction.class.applyOrElse((PartialFunction)this, x, default);
/*     */   }
/*     */   
/*     */   public <U> Function1<A, Object> runWith(Function1 action) {
/*  48 */     return PartialFunction.class.runWith((PartialFunction)this, action);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZD$sp(double v1) {
/*  48 */     return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDD$sp(double v1) {
/*  48 */     return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFD$sp(double v1) {
/*  48 */     return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcID$sp(double v1) {
/*  48 */     return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJD$sp(double v1) {
/*  48 */     return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVD$sp(double v1) {
/*  48 */     Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZF$sp(float v1) {
/*  48 */     return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDF$sp(float v1) {
/*  48 */     return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFF$sp(float v1) {
/*  48 */     return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIF$sp(float v1) {
/*  48 */     return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJF$sp(float v1) {
/*  48 */     return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVF$sp(float v1) {
/*  48 */     Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZI$sp(int v1) {
/*  48 */     return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDI$sp(int v1) {
/*  48 */     return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFI$sp(int v1) {
/*  48 */     return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcII$sp(int v1) {
/*  48 */     return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJI$sp(int v1) {
/*  48 */     return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVI$sp(int v1) {
/*  48 */     Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZJ$sp(long v1) {
/*  48 */     return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDJ$sp(long v1) {
/*  48 */     return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFJ$sp(long v1) {
/*  48 */     return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIJ$sp(long v1) {
/*  48 */     return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJJ$sp(long v1) {
/*  48 */     return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVJ$sp(long v1) {
/*  48 */     Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, B> compose(Function1 g) {
/*  48 */     return Function1.class.compose((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  48 */     return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  48 */     return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  48 */     return GenMapLike.class.hashCode((GenMapLike)this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  48 */     return GenMapLike.class.equals((GenMapLike)this, that);
/*     */   }
/*     */   
/*     */   public GenericCompanion<Iterable> companion() {
/*  48 */     return Iterable$class.companion(this);
/*     */   }
/*     */   
/*     */   public Iterable<Tuple2<A, B>> thisCollection() {
/*  48 */     return IterableLike.class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public Iterable<Tuple2<A, B>> toCollection(Object repr) {
/*  48 */     return IterableLike.class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/*  48 */     return IterableLike.class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/*  48 */     return IterableLike.class.exists(this, p);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<A, B>> find(Function1 p) {
/*  48 */     return IterableLike.class.find(this, p);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/*  48 */     return (B)IterableLike.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/*  48 */     return (B)IterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public Iterable<Tuple2<A, B>> toIterable() {
/*  48 */     return IterableLike.class.toIterable(this);
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<A, B>> toIterator() {
/*  48 */     return IterableLike.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public Iterator<TreeMap<A, B>> grouped(int size) {
/*  48 */     return IterableLike.class.grouped(this, size);
/*     */   }
/*     */   
/*     */   public Iterator<TreeMap<A, B>> sliding(int size) {
/*  48 */     return IterableLike.class.sliding(this, size);
/*     */   }
/*     */   
/*     */   public Iterator<TreeMap<A, B>> sliding(int size, int step) {
/*  48 */     return IterableLike.class.sliding(this, size, step);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start, int len) {
/*  48 */     IterableLike.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  48 */     return (That)IterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  48 */     return (That)IterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*     */   }
/*     */   
/*     */   public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  48 */     return (That)IterableLike.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public <B> boolean sameElements(GenIterable that) {
/*  48 */     return IterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public Stream<Tuple2<A, B>> toStream() {
/*  48 */     return IterableLike.class.toStream(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object that) {
/*  48 */     return IterableLike.class.canEqual(this, that);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  48 */     return IterableLike.class.view(this);
/*     */   }
/*     */   
/*     */   public IterableView<Tuple2<A, B>, TreeMap<A, B>> view(int from, int until) {
/*  48 */     return IterableLike.class.view(this, from, until);
/*     */   }
/*     */   
/*     */   public <B> Builder<B, Iterable<B>> genericBuilder() {
/*  48 */     return GenericTraversableTemplate.class.genericBuilder(this);
/*     */   }
/*     */   
/*     */   public <A1, A2> Tuple2<Iterable<A1>, Iterable<A2>> unzip(Function1 asPair) {
/*  48 */     return GenericTraversableTemplate.class.unzip(this, asPair);
/*     */   }
/*     */   
/*     */   public <A1, A2, A3> Tuple3<Iterable<A1>, Iterable<A2>, Iterable<A3>> unzip3(Function1 asTriple) {
/*  48 */     return GenericTraversableTemplate.class.unzip3(this, asTriple);
/*     */   }
/*     */   
/*     */   public <B> Iterable<B> flatten(Function1 asTraversable) {
/*  48 */     return (Iterable<B>)GenericTraversableTemplate.class.flatten(this, asTraversable);
/*     */   }
/*     */   
/*     */   public <B> Iterable<Iterable<B>> transpose(Function1 asTraversable) {
/*  48 */     return (Iterable<Iterable<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> repr() {
/*  48 */     return (TreeMap<A, B>)TraversableLike.class.repr(this);
/*     */   }
/*     */   
/*     */   public final boolean isTraversableAgain() {
/*  48 */     return TraversableLike.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/*  48 */     return TraversableLike.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  48 */     return (That)TraversableLike.class.$plus$plus(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/*  48 */     return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/*  48 */     return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That map(Function1 f, CanBuildFrom bf) {
/*  48 */     return (That)TraversableLike.class.map(this, f, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  48 */     return (That)TraversableLike.class.flatMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> filter(Function1 p) {
/*  48 */     return (TreeMap<A, B>)TraversableLike.class.filter(this, p);
/*     */   }
/*     */   
/*     */   public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  48 */     return (That)TraversableLike.class.collect(this, pf, bf);
/*     */   }
/*     */   
/*     */   public Tuple2<TreeMap<A, B>, TreeMap<A, B>> partition(Function1 p) {
/*  48 */     return TraversableLike.class.partition(this, p);
/*     */   }
/*     */   
/*     */   public <K> Map<K, TreeMap<A, B>> groupBy(Function1 f) {
/*  48 */     return TraversableLike.class.groupBy(this, f);
/*     */   }
/*     */   
/*     */   public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/*  48 */     return (That)TraversableLike.class.scan(this, z, op, cbf);
/*     */   }
/*     */   
/*     */   public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  48 */     return (That)TraversableLike.class.scanLeft(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  48 */     return (That)TraversableLike.class.scanRight(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> sliceWithKnownDelta(int from, int until, int delta) {
/*  48 */     return (TreeMap<A, B>)TraversableLike.class.sliceWithKnownDelta(this, from, until, delta);
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> sliceWithKnownBound(int from, int until) {
/*  48 */     return (TreeMap<A, B>)TraversableLike.class.sliceWithKnownBound(this, from, until);
/*     */   }
/*     */   
/*     */   public Iterator<TreeMap<A, B>> tails() {
/*  48 */     return TraversableLike.class.tails(this);
/*     */   }
/*     */   
/*     */   public Iterator<TreeMap<A, B>> inits() {
/*  48 */     return TraversableLike.class.inits(this);
/*     */   }
/*     */   
/*     */   public Traversable<Tuple2<A, B>> toTraversable() {
/*  48 */     return TraversableLike.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/*  48 */     return (Col)TraversableLike.class.to(this, cbf);
/*     */   }
/*     */   
/*     */   public FilterMonadic<Tuple2<A, B>, TreeMap<A, B>> withFilter(Function1 p) {
/*  48 */     return TraversableLike.class.withFilter(this, p);
/*     */   }
/*     */   
/*     */   public ParMap<A, B> par() {
/*  48 */     return (ParMap<A, B>)Parallelizable.class.par(this);
/*     */   }
/*     */   
/*     */   public List<Tuple2<A, B>> reversed() {
/*  48 */     return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/*  48 */     return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/*  48 */     return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */   }
/*     */   
/*     */   public <B> Option<B> collectFirst(PartialFunction pf) {
/*  48 */     return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */   }
/*     */   
/*     */   public <B> B $div$colon(Object z, Function2 op) {
/*  48 */     return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B $colon$bslash(Object z, Function2 op) {
/*  48 */     return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/*  48 */     return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/*  48 */     return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceLeftOption(Function2 op) {
/*  48 */     return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceRightOption(Function2 op) {
/*  48 */     return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 reduce(Function2 op) {
/*  48 */     return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> Option<A1> reduceOption(Function2 op) {
/*  48 */     return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 fold(Object z, Function2 op) {
/*  48 */     return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  48 */     return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <B> B sum(Numeric num) {
/*  48 */     return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */   }
/*     */   
/*     */   public <B> B product(Numeric num) {
/*  48 */     return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */   }
/*     */   
/*     */   public <B> Tuple2<A, B> min(Ordering cmp) {
/*  48 */     return (Tuple2<A, B>)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> Tuple2<A, B> max(Ordering cmp) {
/*  48 */     return (Tuple2<A, B>)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> Tuple2<A, B> maxBy(Function1 f, Ordering cmp) {
/*  48 */     return (Tuple2<A, B>)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> Tuple2<A, B> minBy(Function1 f, Ordering cmp) {
/*  48 */     return (Tuple2<A, B>)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> void copyToBuffer(Buffer dest) {
/*  48 */     TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start) {
/*  48 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs) {
/*  48 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */   }
/*     */   
/*     */   public <B> Object toArray(ClassTag evidence$1) {
/*  48 */     return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<Tuple2<A, B>> toList() {
/*  48 */     return TraversableOnce.class.toList((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Tuple2<A, B>> toIndexedSeq() {
/*  48 */     return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <B> Set<B> toSet() {
/*  48 */     return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Vector<Tuple2<A, B>> toVector() {
/*  48 */     return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/*  48 */     return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/*  48 */     return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/*  48 */     return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String sep) {
/*  48 */     return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b) {
/*  48 */     return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  48 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public Ordering<A> ordering() {
/*  48 */     return this.ordering;
/*     */   }
/*     */   
/*     */   private TreeMap(RedBlackTree.Tree<A, B> tree, Ordering<A> ordering) {
/*  48 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  48 */     TraversableOnce.class.$init$((TraversableOnce)this);
/*  48 */     Parallelizable.class.$init$(this);
/*  48 */     TraversableLike.class.$init$(this);
/*  48 */     GenericTraversableTemplate.class.$init$(this);
/*  48 */     GenTraversable.class.$init$((GenTraversable)this);
/*  48 */     Traversable.class.$init$(this);
/*  48 */     Traversable$class.$init$(this);
/*  48 */     GenIterable.class.$init$((GenIterable)this);
/*  48 */     IterableLike.class.$init$(this);
/*  48 */     Iterable.class.$init$(this);
/*  48 */     Iterable$class.$init$(this);
/*  48 */     GenMapLike.class.$init$((GenMapLike)this);
/*  48 */     Function1.class.$init$((Function1)this);
/*  48 */     PartialFunction.class.$init$((PartialFunction)this);
/*  48 */     Subtractable.class.$init$((Subtractable)this);
/*  48 */     MapLike.class.$init$(this);
/*  48 */     Map.class.$init$(this);
/*  48 */     MapLike$class.$init$(this);
/*  48 */     Map$class.$init$(this);
/*  48 */     Sorted.class.$init$((Sorted)this);
/*  48 */     SortedMapLike.class.$init$(this);
/*  48 */     SortedMap.class.$init$(this);
/*  48 */     SortedMap$class.$init$(this);
/*     */   }
/*     */   
/*     */   public boolean isSmaller(Object x, Object y) {
/*  55 */     return ordering().lt(x, y);
/*     */   }
/*     */   
/*     */   public Builder<Tuple2<A, B>, TreeMap<A, B>> newBuilder() {
/*  58 */     return TreeMap$.MODULE$.newBuilder(ordering());
/*     */   }
/*     */   
/*     */   public int size() {
/*  60 */     return RedBlackTree$.MODULE$.count(this.tree);
/*     */   }
/*     */   
/*     */   public TreeMap(Ordering<A> ordering) {
/*  62 */     this(null, ordering);
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> rangeImpl(Option<A> from, Option<A> until) {
/*  64 */     return new TreeMap(RedBlackTree$.MODULE$.rangeImpl(this.tree, from, until, ordering()), ordering());
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> range(Object from, Object until) {
/*  65 */     return new TreeMap(RedBlackTree$.MODULE$.range(this.tree, (A)from, (A)until, ordering()), ordering());
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> from(Object from) {
/*  66 */     return new TreeMap(RedBlackTree$.MODULE$.from(this.tree, (A)from, ordering()), ordering());
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> to(Object to) {
/*  67 */     return new TreeMap(RedBlackTree$.MODULE$.to(this.tree, (A)to, ordering()), ordering());
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> until(Object until) {
/*  68 */     return new TreeMap(RedBlackTree$.MODULE$.until(this.tree, (A)until, ordering()), ordering());
/*     */   }
/*     */   
/*     */   public A firstKey() {
/*  70 */     return (A)RedBlackTree$.MODULE$.<A, B>smallest(this.tree).key();
/*     */   }
/*     */   
/*     */   public A lastKey() {
/*  71 */     return (A)RedBlackTree$.MODULE$.<A, B>greatest(this.tree).key();
/*     */   }
/*     */   
/*     */   public int compare(Object k0, Object k1) {
/*  72 */     return ordering().compare(k0, k1);
/*     */   }
/*     */   
/*     */   public Tuple2<A, B> head() {
/*  75 */     RedBlackTree.Tree<A, B> smallest = RedBlackTree$.MODULE$.smallest(this.tree);
/*  76 */     return new Tuple2(smallest.key(), smallest.value());
/*     */   }
/*     */   
/*     */   public Option<Tuple2<A, B>> headOption() {
/*  78 */     return RedBlackTree$.MODULE$.isEmpty(this.tree) ? (Option<Tuple2<A, B>>)None$.MODULE$ : (Option<Tuple2<A, B>>)new Some(head());
/*     */   }
/*     */   
/*     */   public Tuple2<A, B> last() {
/*  80 */     RedBlackTree.Tree<A, B> greatest = RedBlackTree$.MODULE$.greatest(this.tree);
/*  81 */     return new Tuple2(greatest.key(), greatest.value());
/*     */   }
/*     */   
/*     */   public Option<Tuple2<A, B>> lastOption() {
/*  83 */     return RedBlackTree$.MODULE$.isEmpty(this.tree) ? (Option<Tuple2<A, B>>)None$.MODULE$ : (Option<Tuple2<A, B>>)new Some(last());
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> tail() {
/*  85 */     return new TreeMap(RedBlackTree$.MODULE$.delete(this.tree, firstKey(), ordering()), ordering());
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> init() {
/*  86 */     return new TreeMap(RedBlackTree$.MODULE$.delete(this.tree, lastKey(), ordering()), ordering());
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> drop(int n) {
/*  89 */     return (n <= 0) ? this : (
/*  90 */       (n >= size()) ? empty() : 
/*  91 */       new TreeMap(RedBlackTree$.MODULE$.drop(this.tree, n, ordering()), ordering()));
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> take(int n) {
/*  95 */     return (n <= 0) ? empty() : (
/*  96 */       (n >= size()) ? this : 
/*  97 */       new TreeMap(RedBlackTree$.MODULE$.take(this.tree, n, ordering()), ordering()));
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> slice(int from, int until) {
/* 101 */     return (until <= from) ? empty() : (
/* 102 */       (from <= 0) ? take(until) : (
/* 103 */       (until >= size()) ? drop(from) : 
/* 104 */       new TreeMap(RedBlackTree$.MODULE$.slice(this.tree, from, until, ordering()), ordering())));
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> dropRight(int n) {
/* 107 */     return take(size() - n);
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> takeRight(int n) {
/* 108 */     return drop(size() - n);
/*     */   }
/*     */   
/*     */   public Tuple2<TreeMap<A, B>, TreeMap<A, B>> splitAt(int n) {
/* 109 */     return new Tuple2(take(n), drop(n));
/*     */   }
/*     */   
/*     */   private int countWhile(Function1 p) {
/* 112 */     int result = 0;
/* 113 */     Iterator<Tuple2<A, B>> it = iterator();
/* 114 */     for (; it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(it.next())); result++);
/* 115 */     return result;
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> dropWhile(Function1<Tuple2<A, B>, Object> p) {
/* 117 */     return drop(countWhile(p));
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> takeWhile(Function1<Tuple2<A, B>, Object> p) {
/* 118 */     return take(countWhile(p));
/*     */   }
/*     */   
/*     */   public Tuple2<TreeMap<A, B>, TreeMap<A, B>> span(Function1<Tuple2<A, B>, Object> p) {
/* 119 */     return splitAt(countWhile(p));
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> empty() {
/* 123 */     return TreeMap$.MODULE$.empty(ordering());
/*     */   }
/*     */   
/*     */   public <B1> TreeMap<A, B1> updated(Object key, Object value) {
/* 134 */     return new TreeMap(RedBlackTree$.MODULE$.update(this.tree, (A)key, (B)value, true, ordering()), ordering());
/*     */   }
/*     */   
/*     */   public <B1> TreeMap<A, B1> $plus(Tuple2 kv) {
/* 141 */     return updated((A)kv._1(), (B1)kv._2());
/*     */   }
/*     */   
/*     */   public <B1> TreeMap<A, B1> $plus(Tuple2<?, ?> elem1, Tuple2 elem2, Seq elems) {
/* 154 */     return $plus(elem1).$plus(elem2).$plus$plus((GenTraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public <B1> TreeMap<A, B1> $plus$plus(GenTraversableOnce xs) {
/* 162 */     TreeMap<A, B> treeMap = repr();
/* 162 */     return (TreeMap<A, B1>)xs.seq().$div$colon(treeMap, (Function2)new TreeMap$$anonfun$$plus$plus$1(this));
/*     */   }
/*     */   
/*     */   public class TreeMap$$anonfun$$plus$plus$1 extends AbstractFunction2<TreeMap<A, B1>, Tuple2<A, B1>, TreeMap<A, B1>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final TreeMap<A, B1> apply(TreeMap x$2, Tuple2 x$3) {
/* 162 */       return x$2.$plus(x$3);
/*     */     }
/*     */     
/*     */     public TreeMap$$anonfun$$plus$plus$1(TreeMap $outer) {}
/*     */   }
/*     */   
/*     */   public <B1> TreeMap<A, B1> insert(Object key, Object value) {
/* 173 */     Predef$.MODULE$.assert(!RedBlackTree$.MODULE$.contains(this.tree, (A)key, ordering()));
/* 174 */     return new TreeMap(RedBlackTree$.MODULE$.update(this.tree, (A)key, (B)value, true, ordering()), ordering());
/*     */   }
/*     */   
/*     */   public TreeMap<A, B> $minus(Object key) {
/* 178 */     return RedBlackTree$.MODULE$.contains(this.tree, (A)key, ordering()) ? 
/* 179 */       new TreeMap(RedBlackTree$.MODULE$.delete(this.tree, (A)key, ordering()), ordering()) : this;
/*     */   }
/*     */   
/*     */   public Option<B> get(Object key) {
/* 187 */     return RedBlackTree$.MODULE$.get(this.tree, (A)key, ordering());
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<A, B>> iterator() {
/* 194 */     return RedBlackTree$.MODULE$.iterator(this.tree);
/*     */   }
/*     */   
/*     */   public Iterator<A> keysIterator() {
/* 196 */     return RedBlackTree$.MODULE$.keysIterator(this.tree);
/*     */   }
/*     */   
/*     */   public Iterator<B> valuesIterator() {
/* 197 */     return RedBlackTree$.MODULE$.valuesIterator(this.tree);
/*     */   }
/*     */   
/*     */   public boolean contains(Object key) {
/* 199 */     return RedBlackTree$.MODULE$.contains(this.tree, (A)key, ordering());
/*     */   }
/*     */   
/*     */   public boolean isDefinedAt(Object key) {
/* 200 */     return RedBlackTree$.MODULE$.contains(this.tree, (A)key, ordering());
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1<Tuple2<A, B>, ?> f) {
/* 202 */     RedBlackTree$.MODULE$.foreach(this.tree, f);
/*     */   }
/*     */   
/*     */   public static <A, B> CanBuildFrom<TreeMap<?, ?>, Tuple2<A, B>, TreeMap<A, B>> canBuildFrom(Ordering<A> paramOrdering) {
/*     */     return TreeMap$.MODULE$.canBuildFrom(paramOrdering);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\TreeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */