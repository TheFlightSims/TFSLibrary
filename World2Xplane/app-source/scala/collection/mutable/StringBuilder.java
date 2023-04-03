/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import java.util.regex.PatternSyntaxException;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
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
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.SeqView;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.immutable.StringLike;
/*     */ import scala.math.Ordered;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.matching.Regex;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r=a\001B\001\003\005%\021Qb\025;sS:<')^5mI\026\024(BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001\031r\001\001\006\0235u!c\006E\002\f\0319i\021AA\005\003\033\t\0211\"\0212tiJ\f7\r^*fcB\021q\002E\007\002\r%\021\021C\002\002\005\007\"\f'\017\005\002\02415\tAC\003\002\026-\005!A.\0318h\025\0059\022\001\0026bm\006L!!\007\013\003\031\rC\027M]*fcV,gnY3\021\007-Yb\"\003\002\035\005\tQ\021J\0343fq\026$7+Z9\021\007y\t3%D\001 \025\t\001C!A\005j[6,H/\0312mK&\021!e\b\002\013'R\024\030N\\4MS.,\007CA\006\001!\021YQED\024\n\005\031\022!a\002\"vS2$WM\035\t\003Q-r!aD\025\n\005)2\021A\002)sK\022,g-\003\002-[\t11\013\036:j]\036T!A\013\004\021\005=y\023B\001\031\007\0051\031VM]5bY&T\030M\0317f\021!\021\004A!b\001\n\023\031\024AC;oI\026\024H._5oOV\tA\007\005\002\024k%\021\021\001\006\005\to\001\021\t\021)A\005i\005YQO\0343fe2L\030N\\4!\021\025I\004\001\"\001;\003\031a\024N\\5u}Q\0211e\017\005\006ea\002\r\001\016\005\007{\001\001K\021\013 \002\035QD\027n]\"pY2,7\r^5p]V\t1\005\003\004A\001\001&\t&Q\001\ri>\034u\016\0347fGRLwN\034\013\003G\tCQaQ A\002\r\nAA]3qe\"1Q\t\001Q\005R\031\013!B\\3x\005VLG\016Z3s+\0059\005\003B\006I\035\rJ!!\023\002\003\035\035\023xn^5oO\n+\030\016\0343fe\")\021\b\001C\001\027R\0311\005T)\t\0135S\005\031\001(\002\031%t\027\016^\"ba\006\034\027\016^=\021\005=y\025B\001)\007\005\rIe\016\036\005\006%*\003\raJ\001\nS:LGOV1mk\026DQ!\017\001\005\002Q#\022a\t\005\006s\001!\tA\026\013\003G]CQ\001W+A\0029\013\001bY1qC\016LG/\037\005\006s\001!\tA\027\013\003GmCQ\001X-A\002\035\n1a\035;s\021\025q\006\001\"\001`\003\035!x.\021:sCf,\022\001\031\t\004\037\005t\021B\0012\007\005\025\t%O]1z\021\025!\007\001\"\021f\003\031aWM\\4uQV\ta\nC\003h\001\021\005\001.\001\006mK:<G\017[0%KF$\"!\0337\021\005=Q\027BA6\007\005\021)f.\033;\t\01354\007\031\001(\002\0039DQa\034\001\005\002A\fQa\0317fCJ$\022!\033\005\006e\002!\ta]\001\ng\026$H*\0328hi\"$\"!\033;\t\013U\f\b\031\001(\002\0071,g\016C\003Y\001\021\005Q\rC\003y\001\021\005\0210\001\bf]N,(/Z\"ba\006\034\027\016^=\025\005%T\b\"B>x\001\004q\025a\0038fo\016\013\007/Y2jifDQ! \001\005\002y\faa\0315be\006#HC\001\b\000\021\031\t\t\001 a\001\035\006)\021N\0343fq\"9\021Q\001\001\005B\005\035\021!B1qa2LHc\001\b\002\n!9\021\021AA\002\001\004q\005bBA\007\001\021\005\021qB\001\rI\026dW\r^3DQ\006\024\030\t\036\013\004G\005E\001bBA\001\003\027\001\rA\024\005\b\003+\001A\021AA\f\003%\031X\r^\"iCJ\fE\017F\003j\0033\tY\002C\004\002\002\005M\001\031\001(\t\017\005u\0211\003a\001\035\005\0211\r\033\005\b\003C\001A\021AA\022\003\031)\b\017Z1uKR)\021.!\n\002*!9\021qEA\020\001\004q\025!A5\t\017\005-\022q\004a\001\035\005\t1\rC\004\0020\001!\t!!\r\002\023M,(m\035;sS:<GcA\024\0024!9\021QGA\027\001\004q\025!B:uCJ$\bbBA\030\001\021\005\021\021\b\013\006O\005m\022Q\b\005\b\003k\t9\0041\001O\021\035\ty$a\016A\0029\0131!\0328e\021\035\t\031\005\001C\001\003\013\n1b];c'\026\fX/\0328dKR)!#a\022\002J!9\021QGA!\001\004q\005bBA \003\003\002\rA\024\005\b\003\033\002A\021AA(\003!!\003\017\\;tI\025\fH\003BA)\003'j\021\001\001\005\b\003+\nY\0051\001\017\003\005A\bbBA-\001\021\005\0211L\001\016IAdWo\035\023qYV\034H%Z9\025\t\005E\023Q\f\005\b\003?\n9\0061\001(\003\005\031\bbBA2\001\021\005\021QM\001\nCB\004XM\0343BY2$2aIA4\021\035\tI'!\031A\002\035\n!\001_:\t\017\0055\004\001\"\001\002p\005)A\005\0357vgR!\021\021KA9\021\035\t)&a\033A\0029Aq!!\036\001\t\003\t9(\001\004baB,g\016\032\013\004G\005e\004\002CA+\003g\002\r!a\037\021\007=\ti(C\002\002\000\031\0211!\0218z\021\035\t)\b\001C\001\003\007#2aIAC\021\035\ty&!!A\002\035Bq!!\036\001\t\003\tI\tF\002$\003\027Cq!!$\002\b\002\0071%\001\002tE\"9\0211\r\001\005\002\005EEcA\022\002\024\"A\021\021NAH\001\004\t)\nE\003\002\030\006ee\"D\001\005\023\r\tY\n\002\002\020)J\fg/\032:tC\ndWm\0248dK\"9\0211\r\001\005\002\005}EcA\022\002\"\"9\021\021NAO\001\004\001\007bBA2\001\021\005\021Q\025\013\bG\005\035\026\021VAW\021\035\tI'a)A\002\001Dq!a+\002$\002\007a*\001\004pM\032\034X\r\036\005\007k\006\r\006\031\001(\t\017\005U\004\001\"\001\0022R\0311%a-\t\021\005U\023q\026a\001\003k\0032aDA\\\023\r\tIL\002\002\b\005>|G.Z1o\021\035\t)\b\001C\001\003{#2aIA`\021!\t)&a/A\002\005\005\007cA\b\002D&\031\021Q\031\004\003\t\tKH/\032\005\b\003k\002A\021AAe)\r\031\0231\032\005\t\003+\n9\r1\001\002NB\031q\"a4\n\007\005EgAA\003TQ>\024H\017C\004\002v\001!\t!!6\025\007\r\n9\016C\004\002V\005M\007\031\001(\t\017\005U\004\001\"\001\002\\R\0311%!8\t\021\005U\023\021\034a\001\003?\0042aDAq\023\r\t\031O\002\002\005\031>tw\rC\004\002v\001!\t!a:\025\007\r\nI\017\003\005\002V\005\025\b\031AAv!\ry\021Q^\005\004\003_4!!\002$m_\006$\bbBA;\001\021\005\0211\037\013\004G\005U\b\002CA+\003c\004\r!a>\021\007=\tI0C\002\002|\032\021a\001R8vE2,\007bBA;\001\021\005\021q \013\004G\t\005\001bBA+\003{\004\rA\004\005\b\005\013\001A\021\001B\004\003\031!W\r\\3uKR)1E!\003\003\f!9\021Q\007B\002\001\004q\005bBA \005\007\001\rA\024\005\b\005\037\001A\021\001B\t\003\035\021X\r\0357bG\026$ra\tB\n\005+\0219\002C\004\0026\t5\001\031\001(\t\017\005}\"Q\002a\001\035\"1AL!\004A\002\035BqAa\007\001\t\003\021i\"A\005j]N,'\017^!mYRI1Ea\b\003\"\t\r\"Q\005\005\b\003\003\021I\0021\001O\021\031a&\021\004a\001A\"9\0211\026B\r\001\004q\005BB;\003\032\001\007a\nC\004\003*\001!\tAa\013\002\r%t7/\032:u)\025\031#Q\006B\030\021\035\t\tAa\nA\0029C\001\"!\026\003(\001\007\0211\020\005\b\005S\001A\021\001B\032)\025\031#Q\007B\034\021\035\t\tA!\rA\0029Cq!!\026\0032\001\007q\005C\004\003\034\001!\tAa\017\025\013\r\022iDa\020\t\017\005\005!\021\ba\001\035\"A\021\021\016B\035\001\004\t)\nC\004\003\034\001!\tAa\021\025\013\r\022)Ea\022\t\017\005\005!\021\ta\001\035\"9\021\021\016B!\001\004\001\007b\002B\025\001\021\005!1\n\013\006G\t5#q\n\005\b\003\003\021I\0051\001O\021!\t)F!\023A\002\005U\006b\002B\025\001\021\005!1\013\013\006G\tU#q\013\005\b\003\003\021\t\0061\001O\021!\t)F!\025A\002\005\005\007b\002B\025\001\021\005!1\f\013\006G\tu#q\f\005\b\003\003\021I\0061\001O\021!\t)F!\027A\002\0055\007b\002B\025\001\021\005!1\r\013\006G\t\025$q\r\005\b\003\003\021\t\0071\001O\021\035\t)F!\031A\0029CqA!\013\001\t\003\021Y\007F\003$\005[\022y\007C\004\002\002\t%\004\031\001(\t\021\005U#\021\016a\001\003?DqA!\013\001\t\003\021\031\bF\003$\005k\0229\bC\004\002\002\tE\004\031\001(\t\021\005U#\021\017a\001\003WDqA!\013\001\t\003\021Y\bF\003$\005{\022y\bC\004\002\002\te\004\031\001(\t\021\005U#\021\020a\001\003oDqA!\013\001\t\003\021\031\tF\003$\005\013\0239\tC\004\002\002\t\005\005\031\001(\t\017\005U#\021\021a\001\035!9!1\022\001\005\002\t5\025aB5oI\026DxJ\032\013\004\035\n=\005B\002/\003\n\002\007q\005C\004\003\f\002!\tAa%\025\0139\023)Ja&\t\rq\023\t\n1\001(\021\035\021IJ!%A\0029\013\021B\032:p[&sG-\032=\t\017\tu\005\001\"\001\003 \006YA.Y:u\023:$W\r_(g)\rq%\021\025\005\0079\nm\005\031A\024\t\017\tu\005\001\"\001\003&R)aJa*\003*\"1ALa)A\002\035BqA!'\003$\002\007a\n\003\004\003.\002!\tEP\001\be\0264XM]:fQ!\021YK!-\003>\n\005\007\003\002BZ\005sk!A!.\013\007\t]f!\001\006b]:|G/\031;j_:LAAa/\0036\nIQ.[4sCRLwN\\\021\003\005\013\021\017\031:fm\026\0248/\0321!e\026$XO\0358tA\005\004c.Z<!S:\034H/\0318dK:\002\003%V:fA\001\024XM^3sg\026\034uN\034;f]R\034\b\r\t;pAU\004H-\031;fA%t\007\005\0357bG\026\004\023M\0343!e\026$XO\0358!i\"\fG\017I*ue&twMQ;jY\022,'\017I5ug\026dgML\021\003\005\007\fQA\r\0309]ABaAa2\001\t\003\"\026!B2m_:,\007B\002Bf\001\021\005A+A\bsKZ,'o]3D_:$XM\034;t\021\035\021y\r\001C!\005#\f\001\002^8TiJLgn\032\013\003\005'\0042a\005Bk\023\taC\003C\004\003Z\002!\tEa7\002\0215\\7\013\036:j]\036,\"Aa5\t\017\t}\007\001\"\001\003b\0061!/Z:vYR$\022a\n\025\006\001\t\025(1\036\t\004\037\t\035\030b\001Bu\r\t\0012+\032:jC24VM]:j_:,\026\n\022\020\t\023?V\033a,lUd 9!q\036\002\t\002\tE\030!D*ue&twMQ;jY\022,'\017E\002\f\005g4a!\001\002\t\002\tU8#\002Bz\005ot\003cA\b\003z&\031!1 \004\003\r\005s\027PU3g\021\035I$1\037C\001\005$\"A!=\t\r\025\023\031\020\"\001?\021)\031)Aa=\002\002\023%1qA\001\fe\026\fGMU3t_24X\r\006\002\004\nA\0311ca\003\n\007\r5AC\001\004PE*,7\r\036")
/*     */ public final class StringBuilder extends AbstractSeq<Object> implements CharSequence, IndexedSeq<Object>, StringLike<StringBuilder>, Builder<Object, String>, Serializable {
/*     */   public static final long serialVersionUID = -8525408645367278351L;
/*     */   
/*     */   private final java.lang.StringBuilder underlying;
/*     */   
/*     */   public void sizeHint(int size) {
/*  28 */     Builder$class.sizeHint(this, size);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/*  28 */     Builder$class.sizeHint(this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/*  28 */     Builder$class.sizeHint(this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  28 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<Object, NewTo> mapResult(Function1 f) {
/*  28 */     return Builder$class.mapResult(this, f);
/*     */   }
/*     */   
/*     */   public Growable<Object> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  28 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Growable<Object> $plus$plus$eq(TraversableOnce xs) {
/*  28 */     return Growable.class.$plus$plus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public Object slice(int from, int until) {
/*  28 */     return StringLike.class.slice(this, from, until);
/*     */   }
/*     */   
/*     */   public String $times(int n) {
/*  28 */     return StringLike.class.$times(this, n);
/*     */   }
/*     */   
/*     */   public int compare(String other) {
/*  28 */     return StringLike.class.compare(this, other);
/*     */   }
/*     */   
/*     */   public String stripLineEnd() {
/*  28 */     return StringLike.class.stripLineEnd(this);
/*     */   }
/*     */   
/*     */   public Iterator<String> linesWithSeparators() {
/*  28 */     return StringLike.class.linesWithSeparators(this);
/*     */   }
/*     */   
/*     */   public Iterator<String> lines() {
/*  28 */     return StringLike.class.lines(this);
/*     */   }
/*     */   
/*     */   public Iterator<String> linesIterator() {
/*  28 */     return StringLike.class.linesIterator(this);
/*     */   }
/*     */   
/*     */   public String capitalize() {
/*  28 */     return StringLike.class.capitalize(this);
/*     */   }
/*     */   
/*     */   public String stripPrefix(String prefix) {
/*  28 */     return StringLike.class.stripPrefix(this, prefix);
/*     */   }
/*     */   
/*     */   public String stripSuffix(String suffix) {
/*  28 */     return StringLike.class.stripSuffix(this, suffix);
/*     */   }
/*     */   
/*     */   public String replaceAllLiterally(String literal, String replacement) {
/*  28 */     return StringLike.class.replaceAllLiterally(this, literal, replacement);
/*     */   }
/*     */   
/*     */   public String stripMargin(char marginChar) {
/*  28 */     return StringLike.class.stripMargin(this, marginChar);
/*     */   }
/*     */   
/*     */   public String stripMargin() {
/*  28 */     return StringLike.class.stripMargin(this);
/*     */   }
/*     */   
/*     */   public String[] split(char separator) throws PatternSyntaxException {
/*  28 */     return StringLike.class.split(this, separator);
/*     */   }
/*     */   
/*     */   public String[] split(char[] separators) throws PatternSyntaxException {
/*  28 */     return StringLike.class.split(this, separators);
/*     */   }
/*     */   
/*     */   public Regex r() {
/*  28 */     return StringLike.class.r(this);
/*     */   }
/*     */   
/*     */   public Regex r(Seq groupNames) {
/*  28 */     return StringLike.class.r(this, groupNames);
/*     */   }
/*     */   
/*     */   public boolean toBoolean() {
/*  28 */     return StringLike.class.toBoolean(this);
/*     */   }
/*     */   
/*     */   public byte toByte() {
/*  28 */     return StringLike.class.toByte(this);
/*     */   }
/*     */   
/*     */   public short toShort() {
/*  28 */     return StringLike.class.toShort(this);
/*     */   }
/*     */   
/*     */   public int toInt() {
/*  28 */     return StringLike.class.toInt(this);
/*     */   }
/*     */   
/*     */   public long toLong() {
/*  28 */     return StringLike.class.toLong(this);
/*     */   }
/*     */   
/*     */   public float toFloat() {
/*  28 */     return StringLike.class.toFloat(this);
/*     */   }
/*     */   
/*     */   public double toDouble() {
/*  28 */     return StringLike.class.toDouble(this);
/*     */   }
/*     */   
/*     */   public <B> Object toArray(ClassTag evidence$1) {
/*  28 */     return StringLike.class.toArray(this, evidence$1);
/*     */   }
/*     */   
/*     */   public String format(Seq args) {
/*  28 */     return StringLike.class.format(this, args);
/*     */   }
/*     */   
/*     */   public String formatLocal(Locale l, Seq args) {
/*  28 */     return StringLike.class.formatLocal(this, l, args);
/*     */   }
/*     */   
/*     */   public boolean $less(Object that) {
/*  28 */     return Ordered.class.$less((Ordered)this, that);
/*     */   }
/*     */   
/*     */   public boolean $greater(Object that) {
/*  28 */     return Ordered.class.$greater((Ordered)this, that);
/*     */   }
/*     */   
/*     */   public boolean $less$eq(Object that) {
/*  28 */     return Ordered.class.$less$eq((Ordered)this, that);
/*     */   }
/*     */   
/*     */   public boolean $greater$eq(Object that) {
/*  28 */     return Ordered.class.$greater$eq((Ordered)this, that);
/*     */   }
/*     */   
/*     */   public int compareTo(Object that) {
/*  28 */     return Ordered.class.compareTo((Ordered)this, that);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
/*  28 */     return TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
/*  28 */     return IterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
/*  28 */     return IterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$head() {
/*  28 */     return IterableLike.class.head(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$tail() {
/*  28 */     return TraversableLike.class.tail(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$last() {
/*  28 */     return TraversableLike.class.last(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$init() {
/*  28 */     return TraversableLike.class.init(this);
/*     */   }
/*     */   
/*     */   public boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
/*  28 */     return IterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
/*  28 */     return SeqLike.class.endsWith(this, that);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  28 */     return IndexedSeqOptimized.class.isEmpty((IndexedSeqOptimized)this);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  28 */     IndexedSeqOptimized.class.foreach((IndexedSeqOptimized)this, f);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/*  28 */     return IndexedSeqOptimized.class.forall((IndexedSeqOptimized)this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/*  28 */     return IndexedSeqOptimized.class.exists((IndexedSeqOptimized)this, p);
/*     */   }
/*     */   
/*     */   public Option<Object> find(Function1 p) {
/*  28 */     return IndexedSeqOptimized.class.find((IndexedSeqOptimized)this, p);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/*  28 */     return (B)IndexedSeqOptimized.class.foldLeft((IndexedSeqOptimized)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/*  28 */     return (B)IndexedSeqOptimized.class.foldRight((IndexedSeqOptimized)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/*  28 */     return (B)IndexedSeqOptimized.class.reduceLeft((IndexedSeqOptimized)this, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/*  28 */     return (B)IndexedSeqOptimized.class.reduceRight((IndexedSeqOptimized)this, op);
/*     */   }
/*     */   
/*     */   public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  28 */     return (That)IndexedSeqOptimized.class.zip((IndexedSeqOptimized)this, that, bf);
/*     */   }
/*     */   
/*     */   public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  28 */     return (That)IndexedSeqOptimized.class.zipWithIndex((IndexedSeqOptimized)this, bf);
/*     */   }
/*     */   
/*     */   public Object head() {
/*  28 */     return IndexedSeqOptimized.class.head((IndexedSeqOptimized)this);
/*     */   }
/*     */   
/*     */   public Object tail() {
/*  28 */     return IndexedSeqOptimized.class.tail((IndexedSeqOptimized)this);
/*     */   }
/*     */   
/*     */   public Object last() {
/*  28 */     return IndexedSeqOptimized.class.last((IndexedSeqOptimized)this);
/*     */   }
/*     */   
/*     */   public Object init() {
/*  28 */     return IndexedSeqOptimized.class.init((IndexedSeqOptimized)this);
/*     */   }
/*     */   
/*     */   public Object take(int n) {
/*  28 */     return IndexedSeqOptimized.class.take((IndexedSeqOptimized)this, n);
/*     */   }
/*     */   
/*     */   public Object drop(int n) {
/*  28 */     return IndexedSeqOptimized.class.drop((IndexedSeqOptimized)this, n);
/*     */   }
/*     */   
/*     */   public Object takeRight(int n) {
/*  28 */     return IndexedSeqOptimized.class.takeRight((IndexedSeqOptimized)this, n);
/*     */   }
/*     */   
/*     */   public Object dropRight(int n) {
/*  28 */     return IndexedSeqOptimized.class.dropRight((IndexedSeqOptimized)this, n);
/*     */   }
/*     */   
/*     */   public Tuple2<StringBuilder, StringBuilder> splitAt(int n) {
/*  28 */     return IndexedSeqOptimized.class.splitAt((IndexedSeqOptimized)this, n);
/*     */   }
/*     */   
/*     */   public StringBuilder takeWhile(Function1 p) {
/*  28 */     return (StringBuilder)IndexedSeqOptimized.class.takeWhile((IndexedSeqOptimized)this, p);
/*     */   }
/*     */   
/*     */   public StringBuilder dropWhile(Function1 p) {
/*  28 */     return (StringBuilder)IndexedSeqOptimized.class.dropWhile((IndexedSeqOptimized)this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<StringBuilder, StringBuilder> span(Function1 p) {
/*  28 */     return IndexedSeqOptimized.class.span((IndexedSeqOptimized)this, p);
/*     */   }
/*     */   
/*     */   public <B> boolean sameElements(GenIterable that) {
/*  28 */     return IndexedSeqOptimized.class.sameElements((IndexedSeqOptimized)this, that);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start, int len) {
/*  28 */     IndexedSeqOptimized.class.copyToArray((IndexedSeqOptimized)this, xs, start, len);
/*     */   }
/*     */   
/*     */   public int lengthCompare(int len) {
/*  28 */     return IndexedSeqOptimized.class.lengthCompare((IndexedSeqOptimized)this, len);
/*     */   }
/*     */   
/*     */   public int segmentLength(Function1 p, int from) {
/*  28 */     return IndexedSeqOptimized.class.segmentLength((IndexedSeqOptimized)this, p, from);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p, int from) {
/*  28 */     return IndexedSeqOptimized.class.indexWhere((IndexedSeqOptimized)this, p, from);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p, int end) {
/*  28 */     return IndexedSeqOptimized.class.lastIndexWhere((IndexedSeqOptimized)this, p, end);
/*     */   }
/*     */   
/*     */   public Iterator<Object> reverseIterator() {
/*  28 */     return IndexedSeqOptimized.class.reverseIterator((IndexedSeqOptimized)this);
/*     */   }
/*     */   
/*     */   public <B> boolean startsWith(GenSeq that, int offset) {
/*  28 */     return IndexedSeqOptimized.class.startsWith((IndexedSeqOptimized)this, that, offset);
/*     */   }
/*     */   
/*     */   public <B> boolean endsWith(GenSeq that) {
/*  28 */     return IndexedSeqOptimized.class.endsWith((IndexedSeqOptimized)this, that);
/*     */   }
/*     */   
/*     */   public GenericCompanion<IndexedSeq> companion() {
/*  28 */     return IndexedSeq$class.companion(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Object> seq() {
/*  28 */     return IndexedSeq$class.seq(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Object> toCollection(Object repr) {
/*  28 */     return IndexedSeqLike$class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  28 */     return IndexedSeqLike$class.view(this);
/*     */   }
/*     */   
/*     */   public IndexedSeqView<Object, IndexedSeq<Object>> view(int from, int until) {
/*  28 */     return IndexedSeqLike$class.view(this, from, until);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  28 */     return IndexedSeqLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public Iterator<Object> iterator() {
/*  28 */     return IndexedSeqLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public <A1> Buffer<A1> toBuffer() {
/*  28 */     return IndexedSeqLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   private java.lang.StringBuilder underlying() {
/*  28 */     return this.underlying;
/*     */   }
/*     */   
/*     */   public StringBuilder(java.lang.StringBuilder underlying) {
/*  28 */     IndexedSeqLike.class.$init$(this);
/*  28 */     IndexedSeq.class.$init$(this);
/*  28 */     IndexedSeqLike$class.$init$(this);
/*  28 */     IndexedSeq$class.$init$(this);
/*  28 */     IndexedSeqOptimized.class.$init$((IndexedSeqOptimized)this);
/*  28 */     Ordered.class.$init$((Ordered)this);
/*  28 */     StringLike.class.$init$(this);
/*  28 */     Growable.class.$init$(this);
/*  28 */     Builder$class.$init$(this);
/*     */   }
/*     */   
/*     */   public StringBuilder thisCollection() {
/*  36 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder toCollection(StringBuilder repr) {
/*  37 */     return repr;
/*     */   }
/*     */   
/*     */   public GrowingBuilder<Object, StringBuilder> newBuilder() {
/*  40 */     return new GrowingBuilder<Object, StringBuilder>(new StringBuilder());
/*     */   }
/*     */   
/*     */   public StringBuilder(int initCapacity, String initValue) {
/*  46 */     this((new java.lang.StringBuilder(initValue.length() + initCapacity)).append(initValue));
/*     */   }
/*     */   
/*     */   public StringBuilder() {
/*  51 */     this(16, "");
/*     */   }
/*     */   
/*     */   public StringBuilder(int capacity) {
/*  59 */     this(capacity, "");
/*     */   }
/*     */   
/*     */   public StringBuilder(String str) {
/*  64 */     this(16, str);
/*     */   }
/*     */   
/*     */   public char[] toArray() {
/*  67 */     char[] arr = new char[length()];
/*  68 */     underlying().getChars(0, length(), arr, 0);
/*  69 */     return arr;
/*     */   }
/*     */   
/*     */   public int length() {
/*  72 */     return underlying().length();
/*     */   }
/*     */   
/*     */   public void length_$eq(int n) {
/*  73 */     underlying().setLength(n);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  77 */     setLength(0);
/*     */   }
/*     */   
/*     */   public void setLength(int len) {
/*  86 */     underlying().setLength(len);
/*     */   }
/*     */   
/*     */   public int capacity() {
/*  93 */     return underlying().capacity();
/*     */   }
/*     */   
/*     */   public void ensureCapacity(int newCapacity) {
/* 102 */     underlying().ensureCapacity(newCapacity);
/*     */   }
/*     */   
/*     */   public char charAt(int index) {
/* 110 */     return underlying().charAt(index);
/*     */   }
/*     */   
/*     */   public char apply(int index) {
/* 114 */     return underlying().charAt(index);
/*     */   }
/*     */   
/*     */   public StringBuilder deleteCharAt(int index) {
/* 124 */     underlying().deleteCharAt(index);
/* 125 */     return this;
/*     */   }
/*     */   
/*     */   public void setCharAt(int index, char ch) {
/* 134 */     underlying().setCharAt(index, ch);
/*     */   }
/*     */   
/*     */   public void update(int i, char c) {
/* 138 */     setCharAt(i, c);
/*     */   }
/*     */   
/*     */   public String substring(int start) {
/* 149 */     return substring(start, length());
/*     */   }
/*     */   
/*     */   public String substring(int start, int end) {
/* 163 */     return underlying().substring(start, end);
/*     */   }
/*     */   
/*     */   public CharSequence subSequence(int start, int end) {
/* 168 */     return substring(start, end);
/*     */   }
/*     */   
/*     */   public StringBuilder $plus$eq(char x) {
/* 172 */     append(x);
/* 172 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder $plus$plus$eq(String s) {
/* 177 */     underlying().append(s);
/* 178 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder appendAll(String xs) {
/* 182 */     underlying().append(xs);
/* 183 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder $plus(char x) {
/* 188 */     $plus$eq(x);
/* 188 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder append(Object x) {
/* 197 */     underlying().append(String.valueOf(x));
/* 198 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder append(String s) {
/* 207 */     underlying().append(s);
/* 208 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder append(StringBuilder sb) {
/* 217 */     underlying().append(sb);
/* 218 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder appendAll(TraversableOnce xs) {
/* 226 */     return appendAll((char[])xs.toArray(ClassTag$.MODULE$.Char()));
/*     */   }
/*     */   
/*     */   public StringBuilder appendAll(char[] xs) {
/* 234 */     underlying().append(xs);
/* 235 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder appendAll(char[] xs, int offset, int len) {
/* 246 */     underlying().append(xs, offset, len);
/* 247 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder append(boolean x) {
/* 257 */     underlying().append(x);
/* 257 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder append(byte x) {
/* 258 */     underlying().append(x);
/* 258 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder append(short x) {
/* 259 */     underlying().append(x);
/* 259 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder append(int x) {
/* 260 */     underlying().append(x);
/* 260 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder append(long x) {
/* 261 */     underlying().append(x);
/* 261 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder append(float x) {
/* 262 */     underlying().append(x);
/* 262 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder append(double x) {
/* 263 */     underlying().append(x);
/* 263 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder append(char x) {
/* 264 */     underlying().append(x);
/* 264 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder delete(int start, int end) {
/* 276 */     underlying().delete(start, end);
/* 277 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder replace(int start, int end, String str) {
/* 290 */     underlying().replace(start, end, str);
/* 291 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder insertAll(int index, char[] str, int offset, int len) {
/* 307 */     underlying().insert(index, str, offset, len);
/* 308 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder insert(int index, Object x) {
/* 319 */     return insert(index, String.valueOf(x));
/*     */   }
/*     */   
/*     */   public StringBuilder insert(int index, String x) {
/* 329 */     underlying().insert(index, x);
/* 330 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder insertAll(int index, TraversableOnce xs) {
/* 340 */     return insertAll(index, (char[])xs.toArray(ClassTag$.MODULE$.Char()));
/*     */   }
/*     */   
/*     */   public StringBuilder insertAll(int index, char[] xs) {
/* 350 */     underlying().insert(index, xs);
/* 351 */     return this;
/*     */   }
/*     */   
/*     */   public StringBuilder insert(int index, boolean x) {
/* 361 */     return insert(index, String.valueOf(x));
/*     */   }
/*     */   
/*     */   public StringBuilder insert(int index, byte x) {
/* 362 */     return insert(index, String.valueOf(x));
/*     */   }
/*     */   
/*     */   public StringBuilder insert(int index, short x) {
/* 363 */     return insert(index, String.valueOf(x));
/*     */   }
/*     */   
/*     */   public StringBuilder insert(int index, int x) {
/* 364 */     return insert(index, String.valueOf(x));
/*     */   }
/*     */   
/*     */   public StringBuilder insert(int index, long x) {
/* 365 */     return insert(index, String.valueOf(x));
/*     */   }
/*     */   
/*     */   public StringBuilder insert(int index, float x) {
/* 366 */     return insert(index, String.valueOf(x));
/*     */   }
/*     */   
/*     */   public StringBuilder insert(int index, double x) {
/* 367 */     return insert(index, String.valueOf(x));
/*     */   }
/*     */   
/*     */   public StringBuilder insert(int index, char x) {
/* 368 */     return insert(index, String.valueOf(x));
/*     */   }
/*     */   
/*     */   public int indexOf(String str) {
/* 375 */     return underlying().indexOf(str);
/*     */   }
/*     */   
/*     */   public int indexOf(String str, int fromIndex) {
/* 383 */     return underlying().indexOf(str, fromIndex);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(String str) {
/* 390 */     return underlying().lastIndexOf(str);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(String str, int fromIndex) {
/* 398 */     return underlying().lastIndexOf(str, fromIndex);
/*     */   }
/*     */   
/*     */   public StringBuilder reverse() {
/* 407 */     return new StringBuilder((new java.lang.StringBuilder(underlying())).reverse());
/*     */   }
/*     */   
/*     */   public StringBuilder clone() {
/* 409 */     return new StringBuilder(new java.lang.StringBuilder(underlying()));
/*     */   }
/*     */   
/*     */   public StringBuilder reverseContents() {
/* 416 */     underlying().reverse();
/* 417 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 427 */     return underlying().toString();
/*     */   }
/*     */   
/*     */   public String mkString() {
/* 433 */     return toString();
/*     */   }
/*     */   
/*     */   public String result() {
/* 439 */     return toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\StringBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */