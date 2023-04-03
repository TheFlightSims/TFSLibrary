/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractSeq;
/*     */ import scala.collection.CustomParallelizable;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSeqLike;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.IndexedSeqLike;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParRange;
/*     */ import scala.math.BigDecimal;
/*     */ import scala.math.BigInt;
/*     */ import scala.math.Integral;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021=b\001B\001\003\001%\021QAU1oO\026T!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\0011#\002\001\013%Y\001\003cA\006\r\0355\tA!\003\002\016\t\tY\021IY:ue\006\034GoU3r!\ty\001#D\001\007\023\t\tbAA\002J]R\0042a\005\013\017\033\005\021\021BA\013\003\005)Ie\016Z3yK\022\034V-\035\t\005\027]q\021$\003\002\031\t\t!2)^:u_6\004\026M]1mY\026d\027N_1cY\026\004\"A\007\020\016\003mQ!a\001\017\013\005u!\021\001\0039be\006dG.\0327\n\005}Y\"\001\003)beJ\013gnZ3\021\005=\t\023B\001\022\007\0051\031VM]5bY&T\030M\0317f\021!!\003A!b\001\n\003)\023!B:uCJ$X#\001\b\t\021\035\002!\021!Q\001\n9\taa\035;beR\004\003\002C\025\001\005\013\007I\021A\023\002\007\025tG\r\003\005,\001\t\005\t\025!\003\017\003\021)g\016\032\021\t\0215\002!Q1A\005\002\025\nAa\035;fa\"Aq\006\001B\001B\003%a\"A\003ti\026\004\b\005C\0032\001\021\005!'\001\004=S:LGO\020\013\005gQ*d\007\005\002\024\001!)A\005\ra\001\035!)\021\006\ra\001\035!)Q\006\ra\001\035!)\001\b\001C!s\005\031\001/\031:\026\003eAQa\017\001\005\nq\n1aZ1q+\005i\004CA\b?\023\tydA\001\003M_:<\007\"B!\001\t\023\021\025aB5t\013b\f7\r^\013\002\007B\021q\002R\005\003\013\032\021qAQ8pY\026\fg\016C\003H\001\021%!)A\004iCN\034F/\0362\t\013%\003A\021\002\037\002\0251|gn\032'f]\036$\b\016C\004L\001\t\007IQ\t\"\002\017%\034X)\0349us\"1Q\n\001Q\001\016\r\013\001\"[:F[B$\030\020\t\005\b\037\002\021\r\021\"\002&\003AqW/\034*b]\036,W\t\\3nK:$8\017\003\004R\001\001\006iAD\001\022]Vl'+\0318hK\026cW-\\3oiN\004\003bB*\001\005\004%)!J\001\fY\006\034H/\0227f[\026tG\017\003\004V\001\001\006iAD\001\rY\006\034H/\0227f[\026tG\017\t\005\b/\002\021\r\021\"\002&\003=!XM]7j]\006dW\t\\3nK:$\bBB-\001A\0035a\"\001\tuKJl\027N\\1m\0132,W.\0328uA!)1\f\001C!K\005!A.Y:u\021\025i\006\001\"\021_\003\ri\027N\\\013\003?B$\"A\0041\t\013\005d\0069\0012\002\007=\024H\rE\002dW:t!\001Z5\017\005\025DW\"\0014\013\005\035D\021A\002\037s_>$h(C\001\b\023\tQg!A\004qC\016\\\027mZ3\n\0051l'\001C(sI\026\024\030N\\4\013\005)4\001CA8q\031\001!Q!\035/C\002I\024!!Q\031\022\0059\031\bCA\bu\023\t)hAA\002B]fDQa\036\001\005Ba\f1!\\1y+\tIX\020\006\002\017u\")\021M\036a\002wB\0311m\033?\021\005=lH!B9w\005\004\021\bBB@\001\t#\t\t!\001\003d_BLHcB\032\002\004\005\025\021q\001\005\006Iy\004\rA\004\005\006Sy\004\rA\004\005\006[y\004\rA\004\005\b\003\027\001A\021AA\007\003\t\021\027\020F\0024\003\037Aa!LA\005\001\004q\001BBA\n\001\021\005!)A\006jg&s7\r\\;tSZ,\007BBA\f\001\021\005S%\001\003tSj,\007BBA\016\001\021\005S%\001\004mK:<G\017\033\005\b\003?\001A\021BA\021\003-!Wm]2sSB$\030n\0348\026\005\005\r\002\003BA\023\003Wq1aDA\024\023\r\tICB\001\007!J,G-\0324\n\t\0055\022q\006\002\007'R\024\030N\\4\013\007\005%b\001C\004\0024\001!I!!\016\002\t\031\f\027\016\034\013\003\003o\0012aDA\035\023\r\tYD\002\002\b\035>$\b.\0338h\021\035\ty\004\001C\005\003\003\n\021C^1mS\022\fG/Z'bq2+gn\032;i)\t\t\031\005E\002\020\003\013J1!a\022\007\005\021)f.\033;\t\017\005-\003\001\"\001\002N\0059b/\0317jI\006$XMU1oO\026\024u.\0368eCJLWm\035\013\004\007\006=\003\002CA)\003\023\002\r!a\025\002\003\031\004RaDA+\035ML1!a\026\007\005%1UO\\2uS>t\027\007C\004\002\\\001!)!!\030\002\013\005\004\b\017\\=\025\0079\ty\006C\004\002b\005e\003\031\001\b\002\007%$\007\020C\004\002f\001!)%a\032\002\017\031|'/Z1dQV!\021\021NA9)\021\t\031%a\033\t\021\005E\0231\ra\001\003[\002baDA+\035\005=\004cA8\002r\021a\0211OA2A\003\005\tQ1\001\002v\t\tQ+E\002\0028MDc!!\035\002z\005}\004cA\b\002|%\031\021Q\020\004\003\027M\004XmY5bY&TX\rZ\031\nG\005\005\0251QAD\003\013s1aDAB\023\r\t)IB\001\005+:LG/\r\003%I\"<\001\006BA2\003\027\0032aDAG\023\r\tyI\002\002\007S:d\027N\\3\t\017\005M\005\001\"\022\002\026\006!A/Y6f)\r\031\024q\023\005\b\0033\013\t\n1\001\017\003\005q\007bBAO\001\021\025\023qT\001\005IJ|\007\017F\0024\003CCq!!'\002\034\002\007a\002C\004\002&\002!)%a*\002\t%t\027\016^\013\002g!9\0211\026\001\005F\005\035\026\001\002;bS2Dq!a,\001\t\023\t\t,A\005tW&\0048i\\;oiR\031a\"a-\t\021\005U\026Q\026a\001\003o\013\021\001\035\t\006\037\005Ucb\021\005\b\003w\003A\021BA_\003II7oV5uQ&t'i\\;oI\006\024\030.Z:\025\007\r\013y\fC\004\002B\006e\006\031\001\b\002\t\025dW-\034\005\b\003\013\004A\021BAd\0039awnY1uS>t\027I\032;fe:#2ADAe\021\035\tI*a1A\0029Aq!!4\001\t\023\ty-A\007oK^,U\016\035;z%\006tw-\032\013\004g\005E\007bBAj\003\027\004\rAD\001\006m\006dW/\032\005\b\003/\004AQIAm\003%!\030m[3XQ&dW\rF\0024\0037D\001\"!.\002V\002\007\021q\027\005\b\003?\004AQIAq\003%!'o\0349XQ&dW\rF\0024\003GD\001\"!.\002^\002\007\021q\027\005\b\003O\004AQIAu\003\021\031\b/\0318\025\t\005-\030\021\037\t\006\037\00558gM\005\004\003_4!A\002+va2,'\007\003\005\0026\006\025\b\031AA\\\021\035\t)\020\001C#\003o\fqa\0359mSR\fE\017\006\003\002l\006e\bbBAM\003g\004\rA\004\005\b\003{\004AQIA\000\003%!\030m[3SS\036DG\017F\0024\005\003Aq!!'\002|\002\007a\002C\004\003\006\001!)Ea\002\002\023\021\024x\016\035*jO\"$HcA\032\003\n!9\021\021\024B\002\001\004q\001b\002B\007\001\021\025\023qU\001\be\0264XM]:f\021\035\021\t\002\001C\001\003O\013\021\"\0338dYV\034\030N^3\t\017\tU\001\001\"\002\003\030\005A1m\0348uC&t7\017F\002D\0053AqAa\007\003\024\001\007a\"A\001y\021\035\021y\002\001C#\005C\t1a];n+\021\021\031C!\r\025\0079\021)\003\003\005\003(\tu\0019\001B\025\003\rqW/\034\t\006G\n-\"qF\005\004\005[i'a\002(v[\026\024\030n\031\t\004_\nEBa\002B\032\005;\021\rA\035\002\002\005\"9!q\007\001\005B\005\035\026A\003;p\023R,'/\0312mK\"9!1\b\001\005B\005\035\026!\002;p'\026\f\bb\002B \001\021\005#\021I\001\007KF,\030\r\\:\025\007\r\023\031\005C\004\003F\tu\002\031A:\002\013=$\b.\032:\t\017\t%\003\001\"\021\003L\005AAo\\*ue&tw\r\006\002\002$!*\001Aa\024\003VA\031qB!\025\n\007\tMcA\001\tTKJL\027\r\034,feNLwN\\+J\tzA\021noRUWW\021TbB\004\003Z\tA\tAa\027\002\013I\013gnZ3\021\007M\021iF\002\004\002\005!\005!qL\n\006\005;\022\t\007\t\t\004\037\t\r\024b\001B3\r\t1\021I\\=SK\032Dq!\rB/\t\003\021I\007\006\002\003\\!Q!Q\016B/\005\004%\tAA\023\002\0235\013\005l\030)S\023:#\006\002\003B9\005;\002\013\021\002\b\002\0255\013\005l\030)S\023:#\006\005\003\005\003v\tuC\021\001B<\003\025\031w.\0368u)%q!\021\020B>\005{\022y\b\003\004%\005g\002\rA\004\005\007S\tM\004\031\001\b\t\r5\022\031\b1\001\017\021\035\t\031Ba\035A\002\rC\001B!\036\003^\021\005!1\021\013\b\035\t\025%q\021BE\021\031!#\021\021a\001\035!1\021F!!A\0029Aa!\fBA\001\004qaa\002BG\005;\002!q\022\002\n\023:\034G.^:jm\026\0342Aa#4\021)!#1\022B\001B\003%ab\t\005\013S\t-%\021!Q\001\n9A\003BC\027\003\f\n\005\t\025!\003\017Y!9\021Ga#\005\002\teE\003\003BN\005?\023\tKa)\021\t\tu%1R\007\003\005;Ba\001\nBL\001\004q\001BB\025\003\030\002\007a\002\003\004.\005/\003\rA\004\005\b\003'\021Y\t\"\021C\021\035y(1\022C)\005S#ra\rBV\005[\023y\013\003\004%\005O\003\rA\004\005\007S\t\035\006\031\001\b\t\r5\0229\0131\001\017\021!\tYF!\030\005\002\tMFcB\032\0036\n]&\021\030\005\007I\tE\006\031\001\b\t\r%\022\t\f1\001\017\021\031i#\021\027a\001\035!A\0211\fB/\t\003\021i\fF\0034\005\023\t\r\003\004%\005w\003\rA\004\005\007S\tm\006\031\001\b\t\021\tE!Q\fC\001\005\013$\002Ba2\003L\n5'q\032\t\005\005\023\024YID\002\024\005/Ba\001\nBb\001\004q\001BB\025\003D\002\007a\002\003\004.\005\007\004\rA\004\005\t\005#\021i\006\"\001\003TR1!q\031Bk\005/Da\001\nBi\001\004q\001BB\025\003R\002\007ab\002\005\003\\\nu\003\022\001Bo\003\031\021\025nZ%oiB!!Q\024Bp\r!\021\tO!\030\t\002\t\r(A\002\"jO&sGo\005\003\003`\n\005\004bB\031\003`\022\005!q\035\013\003\005;D\001\"a\027\003`\022\005!1\036\013\t\005[\024yp!\001\004\004A1!q\036B{\005wt1a\005By\023\r\021\031PA\001\r\035VlWM]5d%\006tw-Z\005\005\005o\024IPA\005Fq\016dWo]5wK*\031!1\037\002\021\007\r\024i0C\002\003b6Dq\001\nBu\001\004\021Y\020C\004*\005S\004\rAa?\t\0175\022I\0171\001\003|\"A!\021\003Bp\t\003\0319\001\006\005\004\n\r51qBB\t!\031\021yoa\003\003|&!!Q\022B}\021\035!3Q\001a\001\005wDq!KB\003\001\004\021Y\020C\004.\007\013\001\rAa?\b\021\rU!Q\fE\001\007/\tA\001T8oOB!!QTB\r\r\035y$Q\fE\001\0077\031Ba!\007\003b!9\021g!\007\005\002\r}ACAB\f\021!\tYf!\007\005\002\r\rB\003CB\023\007O\031Ica\013\021\013\t=(Q_\037\t\r\021\032\t\0031\001>\021\031I3\021\005a\001{!1Qf!\tA\002uB\001B!\005\004\032\021\0051q\006\013\t\007c\031\031d!\016\0048A)!q^B\006{!1Ae!\fA\002uBa!KB\027\001\004i\004BB\027\004.\001\007Qh\002\005\004<\tu\003\022AB\037\003)\021\025n\032#fG&l\027\r\034\t\005\005;\033yD\002\005\004B\tu\003\022AB\"\005)\021\025n\032#fG&l\027\r\\\n\005\007\021\t\007C\0042\007!\taa\022\025\005\ru\002BCB&\007\021\r\021b\001\004N\005\001\"-[4EK\016\f5/\0238uK\036\024\030\r\\\013\003\007\037rAa!\025\004`9!11KB-\035\r!7QK\005\004\007/2\021\001B7bi\"LAaa\027\004^\0059a*^7fe&\034'bAB,\r%!1\021MB2\003Y\021\025n\032#fG&l\027\r\\!t\023\032Le\016^3he\006d'\002BB.\007;B\021ba\032\004@\001\006Iaa\024\002#\tLw\rR3d\003NLe\016^3he\006d\007\005\003\005\002\\\r}B\021AB6)!\031iga\035\004v\r]\004C\002Bx\005k\034y\007E\002d\007cJ1a!\021n\021\035!3\021\016a\001\007_Bq!KB5\001\004\031y\007C\004.\007S\002\raa\034\t\021\tE1q\bC\001\007w\"\002b! \004\000\r\00551\021\t\007\005_\034Yaa\034\t\017\021\032I\b1\001\004p!9\021f!\037A\002\r=\004bB\027\004z\001\0071qN\004\t\007\017\023i\006#\001\004\n\0061Ai\\;cY\026\004BA!(\004\f\032A1Q\022B/\021\003\031yI\001\004E_V\024G.Z\n\005\007\027\023\t\007C\0042\007\027#\taa%\025\005\r%\005BCB&\007\027\023\r\021b\001\004N!I1qMBFA\003%1q\n\005\013\0077\033YI1A\005\004\ru\025\001\0053pk\ndW-Q:J]R,wM]1m+\t\031yJ\004\003\004R\r\005\026\002BBR\007G\n!\003R8vE2,\027i]%g\023:$Xm\032:bY\"I1qUBFA\003%1qT\001\022I>,(\r\\3Bg&sG/Z4sC2\004\003\002CBV\007\027#\ta!,\002\tQ|'\t\022\013\005\007_\032y\013\003\005\003\034\r%\006\031ABY!\ry11W\005\004\007\0333\001\002CA.\007\027#\taa.\025\021\re6qXBa\007\007\004RaEB^\007cK1a!0\003\0051qU/\\3sS\016\024\026M\\4f\021\035!3Q\027a\001\007cCq!KB[\001\004\031\t\fC\004.\007k\003\ra!-\t\021\tE11\022C\001\007\017$\002b!/\004J\016-7Q\032\005\bI\r\025\007\031ABY\021\035I3Q\031a\001\007cCq!LBc\001\004\031\tLB\004\004R\nu\003aa5\003\017A\013'\017^5bYV11Q[Bo\007G\034Baa4\003b!Y\021\021KBh\005\003\005\013\021BBm!\035y\021QKBn\007C\0042a\\Bo\t!\031yna4C\002\005U$!\001+\021\007=\034\031\017\002\005\002t\r='\031AA;\021\035\t4q\032C\001\007O$Ba!;\004lBA!QTBh\0077\034\t\017\003\005\002R\r\025\b\031ABm\021!\tYaa4\005\002\r=H\003BBq\007cD\001Ba\007\004n\002\00711\\\004\t\007k\024i\006#\001\004x\006\031\021J\034;\021\t\tu5\021 \004\b#\tu\003\022AB~'\021\031IP!\031\t\017E\032I\020\"\001\004\000R\0211q\037\005\t\0037\032I\020\"\001\005\004QAAQ\001C\004\t\023!Y\001E\003\003p\nUh\002\003\004%\t\003\001\rA\004\005\007S\021\005\001\031\001\b\t\r5\"\t\0011\001\017\021!\021\tb!?\005\002\021=A\003\003C\t\t'!)\002b\006\021\013\t=81\002\b\t\r\021\"i\0011\001\017\021\031ICQ\002a\001\035!1Q\006\"\004A\0029A!\002b\007\003^\005\005I\021\002C\017\003-\021X-\0313SKN|GN^3\025\005\021}\001\003\002C\021\tWi!\001b\t\013\t\021\025BqE\001\005Y\006twM\003\002\005*\005!!.\031<b\023\021!i\003b\t\003\r=\023'.Z2u\001")
/*     */ public class Range extends AbstractSeq<Object> implements IndexedSeq<Object>, CustomParallelizable<Object, ParRange>, Serializable {
/*     */   public static final long serialVersionUID = 7618862778670199309L;
/*     */   
/*     */   private final int start;
/*     */   
/*     */   private final int end;
/*     */   
/*     */   private final int step;
/*     */   
/*     */   private final boolean isEmpty;
/*     */   
/*     */   private final int numRangeElements;
/*     */   
/*     */   private final int lastElement;
/*     */   
/*     */   private final int terminalElement;
/*     */   
/*     */   public Combiner<Object, ParRange> parCombiner() {
/*  44 */     return CustomParallelizable.class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public GenericCompanion<IndexedSeq> companion() {
/*  44 */     return IndexedSeq$class.companion(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Object> toIndexedSeq() {
/*  44 */     return IndexedSeq$class.toIndexedSeq(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Object> seq() {
/*  44 */     return IndexedSeq$class.seq(this);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  44 */     return IndexedSeqLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Object> thisCollection() {
/*  44 */     return IndexedSeqLike.class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Object> toCollection(Object repr) {
/*  44 */     return IndexedSeqLike.class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public Iterator<Object> iterator() {
/*  44 */     return IndexedSeqLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public <A1> Buffer<A1> toBuffer() {
/*  44 */     return IndexedSeqLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public int start() {
/*  44 */     return this.start;
/*     */   }
/*     */   
/*     */   public int end() {
/*  44 */     return this.end;
/*     */   }
/*     */   
/*     */   public int step() {
/*  44 */     return this.step;
/*     */   }
/*     */   
/*     */   public Range(int start, int end, int step) {
/*  44 */     Traversable$class.$init$(this);
/*  44 */     Iterable$class.$init$(this);
/*  44 */     Seq$class.$init$(this);
/*  44 */     IndexedSeqLike.class.$init$(this);
/*  44 */     IndexedSeq.class.$init$(this);
/*  44 */     IndexedSeq$class.$init$(this);
/*  44 */     CustomParallelizable.class.$init$(this);
/*  62 */     this.isEmpty = ((
/*  63 */       start > end && step > 0) || (
/*  64 */       start < end && step < 0) || (
/*  65 */       start == end && !isInclusive()));
/*  68 */     if (step == 0)
/*  68 */       throw new IllegalArgumentException("step cannot be 0."); 
/*  71 */     long len = longLength();
/*  72 */     this.numRangeElements = isEmpty() ? 0 : ((len > 2147483647L) ? -1 : 
/*  73 */       (int)len);
/*  76 */     this.lastElement = start + (numRangeElements() - 1) * step;
/*  77 */     this.terminalElement = start + numRangeElements() * step;
/*     */   }
/*     */   
/*     */   public ParRange par() {
/*     */     return new ParRange(this);
/*     */   }
/*     */   
/*     */   private long gap() {
/*     */     return end() - start();
/*     */   }
/*     */   
/*     */   private boolean isExact() {
/*     */     return (gap() % step() == 0L);
/*     */   }
/*     */   
/*     */   private boolean hasStub() {
/*     */     return !(!isInclusive() && isExact());
/*     */   }
/*     */   
/*     */   private long longLength() {
/*     */     return gap() / step() + (hasStub() ? 1L : 0L);
/*     */   }
/*     */   
/*     */   public final boolean isEmpty() {
/*     */     return this.isEmpty;
/*     */   }
/*     */   
/*     */   public final int numRangeElements() {
/*     */     return this.numRangeElements;
/*     */   }
/*     */   
/*     */   public final int lastElement() {
/*     */     return this.lastElement;
/*     */   }
/*     */   
/*     */   public final int terminalElement() {
/*  77 */     return this.terminalElement;
/*     */   }
/*     */   
/*     */   public int last() {
/*  79 */     return isEmpty() ? BoxesRunTime.unboxToInt(Nil$.MODULE$.last()) : lastElement();
/*     */   }
/*     */   
/*     */   public <A1> int min(Ordering ord) {
/*  82 */     return (ord == scala.math.Ordering$Int$.MODULE$) ? (
/*  83 */       (step() > 0) ? start() : 
/*  84 */       last()) : 
/*  85 */       BoxesRunTime.unboxToInt(TraversableOnce.class.min((TraversableOnce)this, ord));
/*     */   }
/*     */   
/*     */   public <A1> int max(Ordering ord) {
/*  88 */     return (ord == scala.math.Ordering$Int$.MODULE$) ? (
/*  89 */       (step() > 0) ? last() : 
/*  90 */       start()) : 
/*  91 */       BoxesRunTime.unboxToInt(TraversableOnce.class.max((TraversableOnce)this, ord));
/*     */   }
/*     */   
/*     */   public Range copy(int start, int end, int step) {
/*  93 */     return new Range(start, end, step);
/*     */   }
/*     */   
/*     */   public Range by(int step) {
/* 100 */     return copy(start(), end(), step);
/*     */   }
/*     */   
/*     */   public boolean isInclusive() {
/* 102 */     return false;
/*     */   }
/*     */   
/*     */   public int size() {
/* 104 */     return length();
/*     */   }
/*     */   
/*     */   public int length() {
/* 105 */     if (numRangeElements() < 0)
/* 105 */       throw fail(); 
/* 105 */     return numRangeElements();
/*     */   }
/*     */   
/*     */   private String description() {
/* 107 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 107 */     return (new StringOps("%d %s %d by %s")).format((Seq<Object>)scala.Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(start()), isInclusive() ? "to" : "until", BoxesRunTime.boxToInteger(end()), BoxesRunTime.boxToInteger(step()) }));
/*     */   }
/*     */   
/*     */   private scala.runtime.Nothing$ fail() {
/* 108 */     throw new IllegalArgumentException((new StringBuilder()).append(description()).append(": seqs cannot contain more than Int.MaxValue elements.").toString());
/*     */   }
/*     */   
/*     */   public void scala$collection$immutable$Range$$validateMaxLength() {
/* 110 */     if (numRangeElements() < 0)
/* 111 */       throw fail(); 
/*     */   }
/*     */   
/*     */   public boolean validateRangeBoundaries(Function1 f) {
/* 115 */     scala$collection$immutable$Range$$validateMaxLength();
/* 117 */     if (start() == Integer.MIN_VALUE && end() == Integer.MIN_VALUE) {
/* 118 */       int count = 0;
/* 119 */       int num = start();
/* 120 */       while (count < numRangeElements()) {
/* 121 */         f.apply(BoxesRunTime.boxToInteger(num));
/* 122 */         count++;
/* 123 */         num += step();
/*     */       } 
/*     */       if (false);
/*     */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final int apply(int idx) {
/* 129 */     return apply$mcII$sp(idx);
/*     */   }
/*     */   
/*     */   public int apply$mcII$sp(int idx) {
/* 130 */     scala$collection$immutable$Range$$validateMaxLength();
/* 131 */     if (idx < 0 || idx >= numRangeElements())
/* 131 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString()); 
/* 132 */     return start() + step() * idx;
/*     */   }
/*     */   
/*     */   public final <U> void foreach(Function1<Object, Object> f) {
/* 136 */     if (validateRangeBoundaries(f)) {
/* 137 */       int i = start();
/* 138 */       int terminal = terminalElement();
/* 139 */       int step = step();
/* 140 */       while (i != terminal) {
/* 141 */         f.apply(BoxesRunTime.boxToInteger(i));
/* 142 */         i += step;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void foreach$mVc$sp(Function1<Object, Object> f) {
/*     */     if (validateRangeBoundaries(f)) {
/*     */       int i = start();
/*     */       int terminal = terminalElement();
/*     */       int step = step();
/*     */       while (i != terminal) {
/*     */         f.apply$mcVI$sp(i);
/* 142 */         i += step;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final Range take(int n) {
/* 155 */     return (n <= 0 || isEmpty()) ? newEmptyRange(start()) : (
/* 156 */       (n >= numRangeElements()) ? this : 
/* 157 */       new Inclusive(start(), locationAfterN(n - 1), step()));
/*     */   }
/*     */   
/*     */   public final Range drop(int n) {
/* 168 */     return (n <= 0 || isEmpty()) ? this : (
/* 169 */       (n >= numRangeElements()) ? newEmptyRange(end()) : 
/* 170 */       copy(locationAfterN(n), end(), step()));
/*     */   }
/*     */   
/*     */   public final Range init() {
/* 180 */     isEmpty() ? 
/* 181 */       Nil$.MODULE$.init() : BoxedUnit.UNIT;
/* 183 */     return dropRight(1);
/*     */   }
/*     */   
/*     */   public final Range tail() {
/* 193 */     isEmpty() ? 
/* 194 */       Nil$.MODULE$.tail() : BoxedUnit.UNIT;
/* 196 */     return drop(1);
/*     */   }
/*     */   
/*     */   private int skipCount(Function1 p) {
/* 201 */     int current = start();
/* 202 */     int counted = 0;
/* 204 */     while (counted < numRangeElements() && p.apply$mcZI$sp(current)) {
/* 205 */       counted++;
/* 206 */       current += step();
/*     */     } 
/* 208 */     return counted;
/*     */   }
/*     */   
/*     */   private boolean isWithinBoundaries(int elem) {
/* 212 */     return (!isEmpty() && ((
/* 213 */       step() > 0 && start() <= elem && elem <= last()) || (
/* 214 */       step() < 0 && last() <= elem && elem <= start())));
/*     */   }
/*     */   
/*     */   private int locationAfterN(int n) {
/* 218 */     return start() + step() * n;
/*     */   }
/*     */   
/*     */   private Range newEmptyRange(int value) {
/* 224 */     return new Range(value, value, step());
/*     */   }
/*     */   
/*     */   public final Range takeWhile(Function1<Object, Object> p) {
/* 226 */     return take(skipCount(p));
/*     */   }
/*     */   
/*     */   public final Range dropWhile(Function1<Object, Object> p) {
/* 227 */     return drop(skipCount(p));
/*     */   }
/*     */   
/*     */   public final Tuple2<Range, Range> span(Function1<Object, Object> p) {
/* 228 */     return splitAt(skipCount(p));
/*     */   }
/*     */   
/*     */   public final Tuple2<Range, Range> splitAt(int n) {
/* 235 */     return new Tuple2(take(n), drop(n));
/*     */   }
/*     */   
/*     */   public final Range takeRight(int n) {
/* 241 */     return drop(numRangeElements() - n);
/*     */   }
/*     */   
/*     */   public final Range dropRight(int n) {
/* 247 */     return take(numRangeElements() - n);
/*     */   }
/*     */   
/*     */   public final Range reverse() {
/* 254 */     return isEmpty() ? this : 
/* 255 */       new Inclusive(last(), start(), -step());
/*     */   }
/*     */   
/*     */   public Range inclusive() {
/* 260 */     return isInclusive() ? this : 
/* 261 */       new Inclusive(start(), end(), step());
/*     */   }
/*     */   
/*     */   public final boolean contains(int x) {
/* 263 */     return (isWithinBoundaries(x) && (x - start()) % step() == 0);
/*     */   }
/*     */   
/*     */   public final <B> int sum(Numeric num) {
/* 266 */     return isEmpty() ? 0 : (
/* 267 */       (numRangeElements() == 1) ? BoxesRunTime.unboxToInt(head()) : 
/* 268 */       (int)(numRangeElements() * (BoxesRunTime.unboxToInt(head()) + last()) / 2L));
/*     */   }
/*     */   
/*     */   public Range toIterable() {
/* 271 */     return this;
/*     */   }
/*     */   
/*     */   public Range toSeq() {
/* 273 */     return this;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/*     */     boolean bool;
/* 275 */     if (other instanceof Range) {
/* 275 */       Range range = (Range)other;
/* 277 */       bool = (range.canEqual(this) && length() == range.length() && (
/* 278 */         isEmpty() || (
/* 279 */         start() == range.start() && last() == range.last())));
/*     */     } else {
/* 282 */       bool = GenSeqLike.class.equals((GenSeqLike)this, other);
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 289 */     String endStr = (numRangeElements() > Range$.MODULE$.MAX_PRINT()) ? ", ... )" : ")";
/* 290 */     return take(Range$.MODULE$.MAX_PRINT()).mkString("Range(", ", ", endStr);
/*     */   }
/*     */   
/*     */   public static class Inclusive extends Range {
/*     */     public Inclusive(int start, int end, int step) {
/* 330 */       super(start, end, step);
/*     */     }
/*     */     
/*     */     public boolean isInclusive() {
/* 332 */       return true;
/*     */     }
/*     */     
/*     */     public Range copy(int start, int end, int step) {
/* 333 */       return new Inclusive(start, end, step);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BigInt$ {
/*     */     public static final BigInt$ MODULE$;
/*     */     
/*     */     public BigInt$() {
/* 355 */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public NumericRange.Exclusive<BigInt> apply(BigInt start, BigInt end, BigInt step) {
/* 356 */       return NumericRange$.MODULE$.apply(start, end, step, (Integral<BigInt>)scala.math.Numeric$BigIntIsIntegral$.MODULE$);
/*     */     }
/*     */     
/*     */     public NumericRange.Inclusive<BigInt> inclusive(BigInt start, BigInt end, BigInt step) {
/* 357 */       return NumericRange$.MODULE$.inclusive(start, end, step, (Integral<BigInt>)scala.math.Numeric$BigIntIsIntegral$.MODULE$);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Long$ {
/*     */     public static final Long$ MODULE$;
/*     */     
/*     */     public Long$() {
/* 360 */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public NumericRange.Exclusive<Object> apply(long start, long end, long step) {
/* 361 */       return NumericRange$.MODULE$.apply(BoxesRunTime.boxToLong(start), BoxesRunTime.boxToLong(end), BoxesRunTime.boxToLong(step), (Integral)scala.math.Numeric$LongIsIntegral$.MODULE$);
/*     */     }
/*     */     
/*     */     public NumericRange.Inclusive<Object> inclusive(long start, long end, long step) {
/* 362 */       return NumericRange$.MODULE$.inclusive(BoxesRunTime.boxToLong(start), BoxesRunTime.boxToLong(end), BoxesRunTime.boxToLong(step), (Integral)scala.math.Numeric$LongIsIntegral$.MODULE$);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BigDecimal$ {
/*     */     public static final BigDecimal$ MODULE$;
/*     */     
/*     */     private final scala.math.Numeric$BigDecimalAsIfIntegral$ bigDecAsIntegral;
/*     */     
/*     */     public BigDecimal$() {
/* 370 */       MODULE$ = this;
/* 371 */       this.bigDecAsIntegral = scala.math.Numeric$BigDecimalAsIfIntegral$.MODULE$;
/*     */     }
/*     */     
/*     */     public scala.math.Numeric$BigDecimalAsIfIntegral$ bigDecAsIntegral() {
/* 371 */       return this.bigDecAsIntegral;
/*     */     }
/*     */     
/*     */     public NumericRange.Exclusive<BigDecimal> apply(BigDecimal start, BigDecimal end, BigDecimal step) {
/* 374 */       return NumericRange$.MODULE$.apply(start, end, step, (Integral<BigDecimal>)bigDecAsIntegral());
/*     */     }
/*     */     
/*     */     public NumericRange.Inclusive<BigDecimal> inclusive(BigDecimal start, BigDecimal end, BigDecimal step) {
/* 376 */       return NumericRange$.MODULE$.inclusive(start, end, step, (Integral<BigDecimal>)bigDecAsIntegral());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Double$ {
/*     */     public static final Double$ MODULE$;
/*     */     
/*     */     private final scala.math.Numeric$BigDecimalAsIfIntegral$ bigDecAsIntegral;
/*     */     
/*     */     private final scala.math.Numeric$DoubleAsIfIntegral$ doubleAsIntegral;
/*     */     
/*     */     public Double$() {
/* 385 */       MODULE$ = this;
/* 386 */       this.bigDecAsIntegral = scala.math.Numeric$BigDecimalAsIfIntegral$.MODULE$;
/* 387 */       this.doubleAsIntegral = scala.math.Numeric$DoubleAsIfIntegral$.MODULE$;
/*     */     }
/*     */     
/*     */     public scala.math.Numeric$BigDecimalAsIfIntegral$ bigDecAsIntegral() {
/*     */       return this.bigDecAsIntegral;
/*     */     }
/*     */     
/*     */     public scala.math.Numeric$DoubleAsIfIntegral$ doubleAsIntegral() {
/* 387 */       return this.doubleAsIntegral;
/*     */     }
/*     */     
/*     */     public BigDecimal toBD(double x) {
/* 388 */       return scala.math.BigDecimal$.MODULE$.valueOf(x);
/*     */     }
/*     */     
/*     */     public NumericRange<Object> apply(double start, double end, double step) {
/* 391 */       return Range.BigDecimal$.MODULE$.apply(toBD(start), toBD(end), toBD(step)).mapRange((Function1)new Range$Double$$anonfun$apply$1(), (Integral)doubleAsIntegral());
/*     */     }
/*     */     
/*     */     public static class Range$Double$$anonfun$apply$1 extends AbstractFunction1<BigDecimal, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final double apply(BigDecimal x$1) {
/* 391 */         return x$1.doubleValue();
/*     */       }
/*     */     }
/*     */     
/*     */     public NumericRange<Object> inclusive(double start, double end, double step) {
/* 394 */       return Range.BigDecimal$.MODULE$.inclusive(toBD(start), toBD(end), toBD(step)).mapRange((Function1)new Range$Double$$anonfun$inclusive$1(), (Integral)doubleAsIntegral());
/*     */     }
/*     */     
/*     */     public static class Range$Double$$anonfun$inclusive$1 extends AbstractFunction1<BigDecimal, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final double apply(BigDecimal x$2) {
/* 394 */         return x$2.doubleValue();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Partial<T, U> {
/*     */     private final Function1<T, U> f;
/*     */     
/*     */     public Partial(Function1<T, U> f) {}
/*     */     
/*     */     public U by(Object x) {
/* 400 */       return (U)this.f.apply(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Int$ {
/*     */     public static final Int$ MODULE$;
/*     */     
/*     */     public Int$() {
/* 407 */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public NumericRange.Exclusive<Object> apply(int start, int end, int step) {
/* 408 */       return NumericRange$.MODULE$.apply(BoxesRunTime.boxToInteger(start), BoxesRunTime.boxToInteger(end), BoxesRunTime.boxToInteger(step), (Integral)scala.math.Numeric$IntIsIntegral$.MODULE$);
/*     */     }
/*     */     
/*     */     public NumericRange.Inclusive<Object> inclusive(int start, int end, int step) {
/* 409 */       return NumericRange$.MODULE$.inclusive(BoxesRunTime.boxToInteger(start), BoxesRunTime.boxToInteger(end), BoxesRunTime.boxToInteger(step), (Integral)scala.math.Numeric$IntIsIntegral$.MODULE$);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Range.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */