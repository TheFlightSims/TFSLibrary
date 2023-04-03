/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Function3;
/*     */ import scala.Function4;
/*     */ import scala.Function5;
/*     */ import scala.MatchError;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.AbstractSeq;
/*     */ import scala.collection.CustomParallelizable;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.IndexedSeqLike;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Iterator$;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenTraversableFactory;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParVector;
/*     */ import scala.compat.Platform$;
/*     */ import scala.math.Integral;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r5t!B\001\003\021\003I\021A\002,fGR|'O\003\002\004\t\005I\021.\\7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001\001\t\003\025-i\021A\001\004\006\031\tA\t!\004\002\007-\026\034Go\034:\024\007-qq\bE\002\020%Qi\021\001\005\006\003#\021\tqaZ3oKJL7-\003\002\024!\tQ1+Z9GC\016$xN]=\021\005))b\001\002\007\003\005Y)\"a\006\020\024\021UA\002f\013\0303\t\0032!\007\016\035\033\005!\021BA\016\005\005-\t%m\035;sC\016$8+Z9\021\005uqB\002\001\003\007?U!)\031\001\021\003\003\005\013\"!I\023\021\005\t\032S\"\001\004\n\005\0212!a\002(pi\"Lgn\032\t\003E\031J!a\n\004\003\007\005s\027\020E\002\013SqI!A\013\002\003\025%sG-\032=fIN+\027\017\005\003\020Yq!\022BA\027\021\005i9UM\\3sS\016$&/\031<feN\f'\r\\3UK6\004H.\031;f!\021Ir\006H\031\n\005A\"!AD%oI\026DX\rZ*fc2K7.\032\t\004\025Ua\002c\001\0064k%\021AG\001\002\016-\026\034Go\034:Q_&tG/\032:+\005q14&A\034\021\005ajT\"A\035\013\005iZ\024!C;oG\",7m[3e\025\tad!\001\006b]:|G/\031;j_:L!AP\035\003#Ut7\r[3dW\026$g+\031:jC:\034W\r\005\002#\001&\021\021I\002\002\r'\026\024\030.\0317ju\006\024G.\032\t\0053\rcR)\003\002E\t\t!2)^:u_6\004\026M]1mY\026d\027N_1cY\026\0042A\022&\035\033\0059%BA\002I\025\tIE!\001\005qCJ\fG\016\\3m\023\tYuIA\005QCJ4Vm\031;pe\"IQ*\006BC\002\023\005AAT\001\013gR\f'\017^%oI\026DX#A(\021\005\t\002\026BA)\007\005\rIe\016\036\005\t'V\021\t\021)A\005\037\006Y1\017^1si&sG-\032=!\021%)VC!b\001\n\003!a*\001\005f]\022Le\016Z3y\021!9VC!A!\002\023y\025!C3oI&sG-\032=!\021!IVC!A!\002\023y\025!\0024pGV\034\b\"B.\026\t\003a\026A\002\037j]&$h\b\006\0032;z{\006\"B'[\001\004y\005\"B+[\001\004y\005\"B-[\001\004y\005\"B1\026\t\003\022\027!C2p[B\fg.[8o+\005\031\007cA\be)%\021Q\r\005\002\021\017\026tWM]5d\007>l\007/\0318j_:D\001bZ\013A\002\023\005!\001[\001\006I&\024H/_\013\002SB\021!E[\005\003W\032\021qAQ8pY\026\fg\016\003\005n+\001\007I\021\001\002o\003%!\027N\035;z?\022*\027\017\006\002peB\021!\005]\005\003c\032\021A!\0268ji\"91\017\\A\001\002\004I\027a\001=%c!1Q/\006Q!\n%\fa\001Z5sif\004\003\"B<\026\t\003q\025A\0027f]\036$\b\016C\003z+\021\005#0A\002qCJ,\022!\022\005\006yV!\t%`\001\ti>4Vm\031;peV\t\021\007\003\004\000+\021\005\023\021A\001\016Y\026tw\r\0365D_6\004\030M]3\025\007=\013\031\001\003\004\002\006y\004\raT\001\004Y\026t\007\002CA\005+\021\025A!a\003\002\031%t\027\016^%uKJ\fGo\034:\026\t\0055\0211\004\013\004_\006=\001\002CA\t\003\017\001\r!a\005\002\003M\004RACA\013\0033I1!a\006\003\00591Vm\031;pe&#XM]1u_J\0042!HA\016\t!\ti\"a\002C\002\005}!!\001\"\022\005q)\003bBA\022+\021\005\023QE\001\tSR,'/\031;peV\021\021q\005\t\005\025\005UA\004C\004\002,U!\t%!\f\002\037I,g/\032:tK&#XM]1u_J,\"!a\f\021\te\t\t\004H\005\004\003g!!\001C%uKJ\fGo\034:\t\017\005]R\003\"\001\002:\005)\021\r\0359msR\031A$a\017\t\017\005u\022Q\007a\001\037\006)\021N\0343fq\"9\021\021I\013\005\n\005\r\023!E2iK\016\\'+\0318hK\016{gN^3siR\031q*!\022\t\017\005u\022q\ba\001\037\"9\021\021J\013\005B\005-\023aB;qI\006$X\rZ\013\007\003\033\n\031'a\025\025\r\005=\023QMA4)\021\t\t&a\026\021\007u\t\031\006B\004\002V\005\035#\031\001\021\003\tQC\027\r\036\005\t\0033\n9\005q\001\002\\\005\021!M\032\t\t\037\005u\023'!\031\002R%\031\021q\f\t\003\031\r\013gNQ;jY\0224%o\\7\021\007u\t\031\007\002\005\002\036\005\035#\031AA\020\021\035\ti$a\022A\002=C\001\"!\033\002H\001\007\021\021M\001\005K2,W\016C\004\002nU!\t%a\034\002\027\021\002H.^:%G>dwN\\\013\007\003c\ny(a\036\025\t\005M\024\021\021\013\005\003k\nI\bE\002\036\003o\"q!!\026\002l\t\007\001\005\003\005\002Z\005-\0049AA>!!y\021QL\031\002~\005U\004cA\017\002\000\021A\021QDA6\005\004\ty\002\003\005\002j\005-\004\031AA?\021\035\t))\006C!\003\017\0131\002J2pY>tG\005\0357vgV1\021\021RAL\003\037#B!a#\002\032R!\021QRAI!\ri\022q\022\003\b\003+\n\031I1\001!\021!\tI&a!A\004\005M\005\003C\b\002^E\n)*!$\021\007u\t9\n\002\005\002\036\005\r%\031AA\020\021!\tI'a!A\002\005U\005bBAO+\021\005\023qT\001\005i\006\\W\rF\0022\003CCq!a)\002\034\002\007q*A\001o\021\035\t9+\006C!\003S\013A\001\032:paR\031\021'a+\t\017\005\r\026Q\025a\001\037\"9\021qV\013\005B\005E\026!\003;bW\026\024\026n\0325u)\r\t\0241\027\005\b\003G\013i\0131\001P\021\035\t9,\006C!\003s\013\021\002\032:paJKw\r\033;\025\007E\nY\fC\004\002$\006U\006\031A(\t\017\005}V\003\"\021\002B\006!\001.Z1e+\005a\002BBAc+\021\005S0\001\003uC&d\007bBAe+\021\005\023\021Y\001\005Y\006\034H\017\003\004\002NV!\t%`\001\005S:LG\017C\004\002RV!\t%a5\002\013Md\027nY3\025\013E\n).!7\t\017\005]\027q\032a\001\037\006!aM]8n\021\035\tY.a4A\002=\013Q!\0368uS2Dq!a8\026\t\003\n\t/A\004ta2LG/\021;\025\t\005\r\030\021\036\t\006E\005\025\030'M\005\004\003O4!A\002+va2,'\007C\004\002$\006u\007\031A(\t\017\0055X\003\"\021\002p\006QA\005\0357vg\022\002H.^:\026\r\005E\030q`A|)\021\t\031P!\001\025\t\005U\030\021 \t\004;\005]HaBA+\003W\024\r\001\t\005\t\0033\nY\017q\001\002|BAq\"!\0302\003{\f)\020E\002\036\003$\001\"!\b\002l\n\007\021q\004\005\t\005\007\tY\0171\001\003\006\005!A\017[1u!\025I\"qAA\023\r\021I\001\002\002\023\017\026tGK]1wKJ\034\030M\0317f\037:\034W\r\003\005\003\016U!\tA\001B\b\003!)\b\017Z1uK\006#X\003\002B\t\005/!bAa\005\003\032\tm\001\003\002\006\026\005+\0012!\bB\f\t!\tiBa\003C\002\005}\001bBA\037\005\027\001\ra\024\005\t\003S\022Y\0011\001\003\026!9!qD\013\005\n\t\005\022aD4pi>\004vn],sSR\f'\r\\3\025\017=\024\031Ca\n\003,!9!Q\005B\017\001\004y\025\001C8mI&sG-\032=\t\017\t%\"Q\004a\001\037\006Aa.Z<J]\022,\007\020C\004\003.\tu\001\031A(\002\007a|'\017C\004\0032U!IAa\r\002)\035|Go\034$sKND\007k\\:Xe&$\030M\0317f)\035y'Q\007B\034\005sAqA!\n\0030\001\007q\nC\004\003*\t=\002\031A(\t\017\t5\"q\006a\001\037\"A!QH\013\005\002\t\021y$A\006baB,g\016\032$s_:$X\003\002B!\005\017\"BAa\021\003JA!!\"\006B#!\ri\"q\t\003\t\003;\021YD1\001\002 !A!1\nB\036\001\004\021)%A\003wC2,X\r\003\005\003PU!\tA\001B)\003)\t\007\017]3oI\n\0137m[\013\005\005'\022I\006\006\003\003V\tm\003\003\002\006\026\005/\0022!\bB-\t!\tiB!\024C\002\005}\001\002\003B&\005\033\002\rAa\026\t\017\t}S\003\"\003\003b\005i1\017[5giR{\007\017T3wK2$Ra\034B2\005OBqA!\032\003^\001\007q*A\004pY\022dUM\032;\t\017\t%$Q\fa\001\037\0069a.Z<MK\032$\bb\002B7+\021%!qN\001\tu\026\024x\016T3giR)qN!\035\003\002\"A!1\017B6\001\004\021)(A\003beJ\f\027\020E\003#\005o\022Y(C\002\003z\031\021Q!\021:sCf\0042A\tB?\023\r\021yH\002\002\007\003:L(+\0324\t\017\005u\"1\016a\001\037\"9!QQ\013\005\n\t\035\025!\003>fe>\024\026n\0325u)\025y'\021\022BF\021!\021\031Ha!A\002\tU\004bBA\037\005\007\003\ra\024\005\b\005\037+B\021\002BI\003!\031w\016]=MK\032$HC\002B;\005'\023)\n\003\005\003t\t5\005\031\001B;\021\035\0219J!$A\002=\013QA]5hQRDqAa'\026\t\023\021i*A\005d_BL(+[4iiR1!Q\017BP\005CC\001Ba\035\003\032\002\007!Q\017\005\b\005G\023I\n1\001P\003\021aWM\032;\t\017\t\035V\003\"\003\003*\006A\001O]3DY\026\fg\016F\002p\005WCqA!,\003&\002\007q*A\003eKB$\b\016C\004\0032V!IAa-\002\033\rdW-\0318MK\032$X\tZ4f)\ry'Q\027\005\b\005o\023y\0131\001P\003!\031W\017^%oI\026D\bb\002B^+\021%!QX\001\017G2,\027M\034*jO\"$X\tZ4f)\ry'q\030\005\b\005o\023I\f1\001P\021\035\021\031-\006C\005\005\013\fQB]3rk&\024X\r\032#faRDGcA(\003H\"9!Q\006Ba\001\004y\005b\002Bf+\021%!QZ\001\013IJ|\007O\022:p]R\004DcA\031\003P\"9!q\027Be\001\004y\005b\002Bj+\021%!Q[\001\nIJ|\007OQ1dWB\"2!\rBl\021\035\0219L!5A\002=CaaW\006\005\002\tmG#A\005\007\017\t}7\002\001\003\003b\n\tb+Z2u_J\024V-^:bE2,7I\021$\024\t\tu'1\035\t\006\005K\0249/I\007\002\027%!!\021\036Bv\005M9UM\\3sS\016\034\025M\034\"vS2$gI]8n\023\r\021i\017\005\002\026\017\026tGK]1wKJ\034\030M\0317f\r\006\034Go\034:z\021\035Y&Q\034C\001\005c$\"Aa=\021\t\t\025(Q\034\005\t\003o\021i\016\"\021\003xR\021!\021 \t\b\005w\034\t!IB\003\033\t\021iPC\002\003\000\022\tq!\\;uC\ndW-\003\003\004\004\tu(a\002\"vS2$WM\035\t\004\025U\t\003\"CB\005\027\t\007I\021BB\006\003E1Vm\031;peJ+Wo]1cY\026\034%IR\013\003\005GD\001ba\004\fA\003%!1]\001\023-\026\034Go\034:SKV\034\030M\0317f\007\n3\005\005\003\006\004\024-A)\031!C!\007\027\t1BU3vg\006\024G.Z\"C\r\"Q1qC\006\t\002\003\006KAa9\002\031I+Wo]1cY\026\034%I\022\021\t\017\rm1\002\"\001\004\036\005Qa.Z<Ck&dG-\032:\026\t\r}1QE\013\003\007C\001\002Ba?\004\002\r\r2q\005\t\004;\r\025BAB\020\004\032\t\007\001\005\005\003\013+\r\r\002bBB\026\027\021\r1QF\001\rG\006t')^5mI\032\023x.\\\013\005\007_\031Y$\006\002\0042AIq\"!\030\0044\re2Q\b\t\005\005K\034)$C\002\0048\021\024AaQ8mYB\031Qda\017\005\r}\031IC1\001!!\021QQc!\017\t\025\r\0053B1A\005\002\t\031\031%A\002O\0232+\"a!\002\t\021\r\0353\002)A\005\007\013\tAAT%MA!911J\006\005B\r5\023!B3naRLX\003BB(\007+*\"a!\025\021\t))21\013\t\004;\rUCAB\020\004J\t\007\001\005C\005\004Z-\t\t\021\"\003\004\\\005Y!/Z1e%\026\034x\016\034<f)\t\031i\006\005\003\004`\r%TBAB1\025\021\031\031g!\032\002\t1\fgn\032\006\003\007O\nAA[1wC&!11NB1\005\031y%M[3di\002")
/*     */ public final class Vector<A> extends AbstractSeq<A> implements IndexedSeq<A>, GenericTraversableTemplate<A, Vector>, IndexedSeqLike<A, Vector<A>>, VectorPointer<A>, Serializable, CustomParallelizable<A, ParVector<A>> {
/*     */   private final int startIndex;
/*     */   
/*     */   private final int endIndex;
/*     */   
/*     */   private final int focus;
/*     */   
/*     */   private boolean dirty;
/*     */   
/*     */   private int depth;
/*     */   
/*     */   private Object[] display0;
/*     */   
/*     */   private Object[] display1;
/*     */   
/*     */   private Object[] display2;
/*     */   
/*     */   private Object[] display3;
/*     */   
/*     */   private Object[] display4;
/*     */   
/*     */   private Object[] display5;
/*     */   
/*     */   public static class VectorReusableCBF extends GenTraversableFactory<Vector>.GenericCanBuildFrom<Nothing$> {
/*     */     public VectorReusableCBF() {
/*  23 */       super((GenTraversableFactory)Vector$.MODULE$);
/*     */     }
/*     */     
/*     */     public Builder<Nothing$, Vector<Nothing$>> apply() {
/*  24 */       return Vector$.MODULE$.newBuilder();
/*     */     }
/*     */   }
/*     */   
/*     */   public Combiner<A, ParVector<A>> parCombiner() {
/*  67 */     return CustomParallelizable.class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public int depth() {
/*  67 */     return this.depth;
/*     */   }
/*     */   
/*     */   public void depth_$eq(int x$1) {
/*  67 */     this.depth = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display0() {
/*  67 */     return this.display0;
/*     */   }
/*     */   
/*     */   public void display0_$eq(Object[] x$1) {
/*  67 */     this.display0 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display1() {
/*  67 */     return this.display1;
/*     */   }
/*     */   
/*     */   public void display1_$eq(Object[] x$1) {
/*  67 */     this.display1 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display2() {
/*  67 */     return this.display2;
/*     */   }
/*     */   
/*     */   public void display2_$eq(Object[] x$1) {
/*  67 */     this.display2 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display3() {
/*  67 */     return this.display3;
/*     */   }
/*     */   
/*     */   public void display3_$eq(Object[] x$1) {
/*  67 */     this.display3 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display4() {
/*  67 */     return this.display4;
/*     */   }
/*     */   
/*     */   public void display4_$eq(Object[] x$1) {
/*  67 */     this.display4 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display5() {
/*  67 */     return this.display5;
/*     */   }
/*     */   
/*     */   public void display5_$eq(Object[] x$1) {
/*  67 */     this.display5 = x$1;
/*     */   }
/*     */   
/*     */   public final <U> void initFrom(VectorPointer that) {
/*  67 */     VectorPointer$class.initFrom(this, that);
/*     */   }
/*     */   
/*     */   public final <U> void initFrom(VectorPointer that, int depth) {
/*  67 */     VectorPointer$class.initFrom(this, that, depth);
/*     */   }
/*     */   
/*     */   public final A getElem(int index, int xor) {
/*  67 */     return (A)VectorPointer$class.getElem(this, index, xor);
/*     */   }
/*     */   
/*     */   public final void gotoPos(int index, int xor) {
/*  67 */     VectorPointer$class.gotoPos(this, index, xor);
/*     */   }
/*     */   
/*     */   public final void gotoNextBlockStart(int index, int xor) {
/*  67 */     VectorPointer$class.gotoNextBlockStart(this, index, xor);
/*     */   }
/*     */   
/*     */   public final void gotoNextBlockStartWritable(int index, int xor) {
/*  67 */     VectorPointer$class.gotoNextBlockStartWritable(this, index, xor);
/*     */   }
/*     */   
/*     */   public final Object[] copyOf(Object[] a) {
/*  67 */     return VectorPointer$class.copyOf(this, a);
/*     */   }
/*     */   
/*     */   public final Object[] nullSlotAndCopy(Object[] array, int index) {
/*  67 */     return VectorPointer$class.nullSlotAndCopy(this, array, index);
/*     */   }
/*     */   
/*     */   public final void stabilize(int index) {
/*  67 */     VectorPointer$class.stabilize(this, index);
/*     */   }
/*     */   
/*     */   public final void gotoPosWritable0(int newIndex, int xor) {
/*  67 */     VectorPointer$class.gotoPosWritable0(this, newIndex, xor);
/*     */   }
/*     */   
/*     */   public final void gotoPosWritable1(int oldIndex, int newIndex, int xor) {
/*  67 */     VectorPointer$class.gotoPosWritable1(this, oldIndex, newIndex, xor);
/*     */   }
/*     */   
/*     */   public final Object[] copyRange(Object[] array, int oldLeft, int newLeft) {
/*  67 */     return VectorPointer$class.copyRange(this, array, oldLeft, newLeft);
/*     */   }
/*     */   
/*     */   public final void gotoFreshPosWritable0(int oldIndex, int newIndex, int xor) {
/*  67 */     VectorPointer$class.gotoFreshPosWritable0(this, oldIndex, newIndex, xor);
/*     */   }
/*     */   
/*     */   public final void gotoFreshPosWritable1(int oldIndex, int newIndex, int xor) {
/*  67 */     VectorPointer$class.gotoFreshPosWritable1(this, oldIndex, newIndex, xor);
/*     */   }
/*     */   
/*     */   public void debug() {
/*  67 */     VectorPointer$class.debug(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> toIndexedSeq() {
/*  67 */     return IndexedSeq$class.toIndexedSeq(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> seq() {
/*  67 */     return IndexedSeq$class.seq(this);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  67 */     return IndexedSeqLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> thisCollection() {
/*  67 */     return IndexedSeqLike.class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> toCollection(Object repr) {
/*  67 */     return IndexedSeqLike.class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public <A1> Buffer<A1> toBuffer() {
/*  67 */     return IndexedSeqLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public Seq<A> toSeq() {
/*  67 */     return Seq$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public int startIndex() {
/*  67 */     return this.startIndex;
/*     */   }
/*     */   
/*     */   public int endIndex() {
/*  67 */     return this.endIndex;
/*     */   }
/*     */   
/*     */   public Vector(int startIndex, int endIndex, int focus) {
/*  67 */     Traversable$class.$init$(this);
/*  67 */     Iterable$class.$init$(this);
/*  67 */     Seq$class.$init$(this);
/*  67 */     IndexedSeqLike.class.$init$(this);
/*  67 */     IndexedSeq.class.$init$(this);
/*  67 */     IndexedSeq$class.$init$(this);
/*  67 */     VectorPointer$class.$init$(this);
/*  67 */     CustomParallelizable.class.$init$(this);
/*  84 */     this.dirty = false;
/*     */   }
/*     */   
/*     */   public GenericCompanion<Vector> companion() {
/*     */     return (GenericCompanion<Vector>)Vector$.MODULE$;
/*     */   }
/*     */   
/*     */   public boolean dirty() {
/*  84 */     return this.dirty;
/*     */   }
/*     */   
/*     */   public void dirty_$eq(boolean x$1) {
/*  84 */     this.dirty = x$1;
/*     */   }
/*     */   
/*     */   public int length() {
/*  86 */     return endIndex() - startIndex();
/*     */   }
/*     */   
/*     */   public ParVector<A> par() {
/*  88 */     return new ParVector(this);
/*     */   }
/*     */   
/*     */   public Vector<A> toVector() {
/*  90 */     return this;
/*     */   }
/*     */   
/*     */   public int lengthCompare(int len) {
/*  92 */     return length() - len;
/*     */   }
/*     */   
/*     */   public final <B> void initIterator(VectorIterator s) {
/*  95 */     s.initFrom(this);
/*  96 */     if (dirty())
/*  96 */       s.stabilize(this.focus); 
/*  97 */     if (s.depth() > 1)
/*  97 */       s.gotoPos(startIndex(), startIndex() ^ this.focus); 
/*     */   }
/*     */   
/*     */   public VectorIterator<A> iterator() {
/* 101 */     VectorIterator<?> s = new VectorIterator(startIndex(), endIndex());
/* 102 */     initIterator(s);
/* 103 */     return (VectorIterator)s;
/*     */   }
/*     */   
/*     */   public Iterator<A> reverseIterator() {
/* 109 */     return (Iterator<A>)new Vector$$anon$1(this);
/*     */   }
/*     */   
/*     */   public class Vector$$anon$1 extends AbstractIterator<A> {
/*     */     private int i;
/*     */     
/*     */     public Vector$$anon$1(Vector $outer) {
/* 110 */       this.i = $outer.length();
/*     */     }
/*     */     
/*     */     private int i() {
/* 110 */       return this.i;
/*     */     }
/*     */     
/*     */     private void i_$eq(int x$1) {
/* 110 */       this.i = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 111 */       return (0 < i());
/*     */     }
/*     */     
/*     */     public A next() {
/* 114 */       i_$eq(i() - 1);
/* 115 */       return (0 < i()) ? this.$outer.apply(i()) : 
/* 116 */         (A)Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public A apply(int index) {
/* 127 */     int idx = checkRangeConvert(index);
/* 129 */     return getElem(idx, idx ^ this.focus);
/*     */   }
/*     */   
/*     */   private int checkRangeConvert(int index) {
/* 133 */     int idx = index + startIndex();
/* 134 */     if (0 <= index && idx < endIndex())
/* 135 */       return idx; 
/* 137 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(index).toString());
/*     */   }
/*     */   
/*     */   public <B, That> That updated(int index, Object elem, CanBuildFrom bf) {
/* 144 */     return (bf == IndexedSeq$.MODULE$.ReusableCBF()) ? (That)updateAt(index, elem) : 
/* 145 */       (That)SeqLike.class.updated(this, index, elem, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/* 148 */     return (bf == IndexedSeq$.MODULE$.ReusableCBF()) ? (That)appendFront(elem) : 
/* 149 */       (That)SeqLike.class.$plus$colon(this, elem, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/* 152 */     return (bf == IndexedSeq$.MODULE$.ReusableCBF()) ? (That)appendBack(elem) : 
/* 153 */       (That)SeqLike.class.$colon$plus(this, elem, bf);
/*     */   }
/*     */   
/*     */   public Vector<A> take(int n) {
/* 156 */     return (n <= 0) ? 
/* 157 */       Vector$.MODULE$.<A>empty() : (
/* 158 */       (startIndex() + n < endIndex()) ? 
/* 159 */       dropBack0(startIndex() + n) : 
/*     */       
/* 161 */       this);
/*     */   }
/*     */   
/*     */   public Vector<A> drop(int n) {
/* 165 */     return (n <= 0) ? 
/* 166 */       this : (
/* 167 */       (startIndex() + n < endIndex()) ? 
/* 168 */       dropFront0(startIndex() + n) : 
/*     */       
/* 170 */       Vector$.MODULE$.<A>empty());
/*     */   }
/*     */   
/*     */   public Vector<A> takeRight(int n) {
/* 174 */     return (n <= 0) ? 
/* 175 */       Vector$.MODULE$.<A>empty() : (
/* 176 */       (endIndex() - n > startIndex()) ? 
/* 177 */       dropFront0(endIndex() - n) : 
/*     */       
/* 179 */       this);
/*     */   }
/*     */   
/*     */   public Vector<A> dropRight(int n) {
/* 183 */     return (n <= 0) ? 
/* 184 */       this : (
/* 185 */       (endIndex() - n > startIndex()) ? 
/* 186 */       dropBack0(endIndex() - n) : 
/*     */       
/* 188 */       Vector$.MODULE$.<A>empty());
/*     */   }
/*     */   
/*     */   public A head() {
/* 192 */     if (isEmpty())
/* 192 */       throw new UnsupportedOperationException("empty.head"); 
/* 193 */     return apply(0);
/*     */   }
/*     */   
/*     */   public Vector<A> tail() {
/* 197 */     if (isEmpty())
/* 197 */       throw new UnsupportedOperationException("empty.tail"); 
/* 198 */     return drop(1);
/*     */   }
/*     */   
/*     */   public A last() {
/* 202 */     if (isEmpty())
/* 202 */       throw new UnsupportedOperationException("empty.last"); 
/* 203 */     return apply(length() - 1);
/*     */   }
/*     */   
/*     */   public Vector<A> init() {
/* 207 */     if (isEmpty())
/* 207 */       throw new UnsupportedOperationException("empty.init"); 
/* 208 */     return dropRight(1);
/*     */   }
/*     */   
/*     */   public Vector<A> slice(int from, int until) {
/* 212 */     return take(until).drop(from);
/*     */   }
/*     */   
/*     */   public Tuple2<Vector<A>, Vector<A>> splitAt(int n) {
/* 214 */     return new Tuple2(take(n), drop(n));
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/* 220 */     return (That)TraversableLike.class.$plus$plus(this, (GenTraversableOnce)that.seq(), bf);
/*     */   }
/*     */   
/*     */   public <B> Vector<B> updateAt(int index, Object elem) {
/* 228 */     int idx = checkRangeConvert(index);
/* 229 */     Vector<B> s = new Vector(startIndex(), endIndex(), idx);
/* 230 */     s.initFrom(this);
/* 231 */     s.dirty_$eq(dirty());
/* 232 */     s.gotoPosWritable(this.focus, idx, this.focus ^ idx);
/* 233 */     s.display0()[idx & 0x1F] = elem;
/* 234 */     return s;
/*     */   }
/*     */   
/*     */   private void gotoPosWritable(int oldIndex, int newIndex, int xor) {
/* 238 */     if (dirty()) {
/* 239 */       gotoPosWritable1(oldIndex, newIndex, xor);
/*     */     } else {
/* 241 */       gotoPosWritable0(newIndex, xor);
/* 242 */       dirty_$eq(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void gotoFreshPosWritable(int oldIndex, int newIndex, int xor) {
/* 245 */     if (dirty()) {
/* 246 */       gotoFreshPosWritable1(oldIndex, newIndex, xor);
/*     */     } else {
/* 248 */       gotoFreshPosWritable0(oldIndex, newIndex, xor);
/* 249 */       dirty_$eq(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public <B> Vector<B> appendFront(Object value) {
/* 254 */     int blockIndex = startIndex() - 1 & (0x1F ^ 0xFFFFFFFF);
/* 255 */     int lo = startIndex() - 1 & 0x1F;
/* 258 */     Vector<B> s = new Vector(startIndex() - 1, endIndex(), blockIndex);
/* 259 */     s.initFrom(this);
/* 260 */     s.dirty_$eq(dirty());
/* 261 */     s.gotoPosWritable(this.focus, blockIndex, this.focus ^ blockIndex);
/* 262 */     s.display0()[lo] = value;
/* 266 */     int freeSpace = (1 << 5 * depth()) - endIndex();
/* 267 */     int shift = freeSpace & ((1 << 5 * (depth() - 1)) - 1 ^ 0xFFFFFFFF);
/* 268 */     int shiftBlocks = freeSpace >>> 5 * (depth() - 1);
/* 273 */     debug();
/* 277 */     int newBlockIndex = blockIndex + shift;
/* 278 */     int newFocus = this.focus + shift;
/* 279 */     Vector<B> vector1 = new Vector(startIndex() - 1 + shift, endIndex() + shift, newBlockIndex);
/* 280 */     vector1.initFrom(this);
/* 281 */     vector1.dirty_$eq(dirty());
/* 282 */     vector1.shiftTopLevel(0, shiftBlocks);
/* 283 */     vector1.debug();
/* 284 */     vector1.gotoFreshPosWritable(newFocus, newBlockIndex, newFocus ^ newBlockIndex);
/* 285 */     vector1.display0()[lo] = value;
/* 289 */     int j = blockIndex + 32;
/* 290 */     int i = this.focus;
/* 295 */     Vector<B> vector2 = new Vector(startIndex() - 1 + shift, endIndex() + shift, j);
/* 296 */     vector2.initFrom(this);
/* 297 */     vector2.dirty_$eq(dirty());
/* 298 */     vector2.shiftTopLevel(0, shiftBlocks);
/* 299 */     vector2.gotoPosWritable(i, j, i ^ j);
/* 300 */     vector2.display0()[shift - 1] = value;
/* 301 */     vector2.debug();
/* 306 */     int move = (1 << 5 * (depth() + 1)) - (1 << 5 * depth());
/* 309 */     int m = blockIndex + move;
/* 310 */     int k = this.focus + move;
/* 313 */     Vector<B> vector3 = new Vector(startIndex() - 1 + move, endIndex() + move, m);
/* 314 */     vector3.initFrom(this);
/* 315 */     vector3.dirty_$eq(dirty());
/* 316 */     vector3.debug();
/* 317 */     vector3.gotoFreshPosWritable(k, m, k ^ m);
/* 318 */     vector3.display0()[lo] = value;
/* 319 */     vector3.debug();
/* 323 */     int n = this.focus;
/* 326 */     Vector<B> vector4 = new Vector(startIndex() - 1, endIndex(), blockIndex);
/* 327 */     vector4.initFrom(this);
/* 328 */     vector4.dirty_$eq(dirty());
/* 329 */     vector4.gotoFreshPosWritable(n, blockIndex, n ^ blockIndex);
/* 330 */     vector4.display0()[lo] = value;
/* 338 */     Object[] elems = new Object[32];
/* 339 */     elems[31] = value;
/* 340 */     Vector<B> vector5 = new Vector(31, 32, 0);
/* 341 */     vector5.depth_$eq(1);
/* 342 */     vector5.display0_$eq(elems);
/* 343 */     return (endIndex() != startIndex()) ? ((startIndex() != blockIndex + 32) ? s : ((shift != 0) ? ((depth() > 1) ? vector1 : vector2) : ((blockIndex < 0) ? vector3 : vector4))) : vector5;
/*     */   }
/*     */   
/*     */   public <B> Vector<B> appendBack(Object value) {
/* 351 */     int blockIndex = endIndex() & (0x1F ^ 0xFFFFFFFF);
/* 352 */     int lo = endIndex() & 0x1F;
/* 356 */     Vector<B> s = new Vector(startIndex(), endIndex() + 1, blockIndex);
/* 357 */     s.initFrom(this);
/* 358 */     s.dirty_$eq(dirty());
/* 359 */     s.gotoPosWritable(this.focus, blockIndex, this.focus ^ blockIndex);
/* 360 */     s.display0()[lo] = value;
/* 363 */     int shift = startIndex() & ((1 << 5 * (depth() - 1)) - 1 ^ 0xFFFFFFFF);
/* 364 */     int shiftBlocks = startIndex() >>> 5 * (depth() - 1);
/* 369 */     debug();
/* 372 */     int newBlockIndex = blockIndex - shift;
/* 373 */     int newFocus = this.focus - shift;
/* 374 */     Vector<B> vector1 = new Vector(startIndex() - shift, endIndex() + 1 - shift, newBlockIndex);
/* 375 */     vector1.initFrom(this);
/* 376 */     vector1.dirty_$eq(dirty());
/* 377 */     vector1.shiftTopLevel(shiftBlocks, 0);
/* 378 */     vector1.debug();
/* 379 */     vector1.gotoFreshPosWritable(newFocus, newBlockIndex, newFocus ^ newBlockIndex);
/* 380 */     vector1.display0()[lo] = value;
/* 381 */     vector1.debug();
/* 385 */     int j = blockIndex - 32;
/* 386 */     int i = this.focus;
/* 391 */     Vector<B> vector2 = new Vector(startIndex() - shift, endIndex() + 1 - shift, j);
/* 392 */     vector2.initFrom(this);
/* 393 */     vector2.dirty_$eq(dirty());
/* 394 */     vector2.shiftTopLevel(shiftBlocks, 0);
/* 395 */     vector2.gotoPosWritable(i, j, i ^ j);
/* 396 */     vector2.display0()[32 - shift] = value;
/* 397 */     vector2.debug();
/* 401 */     int k = this.focus;
/* 404 */     Vector<B> vector3 = new Vector(startIndex(), endIndex() + 1, blockIndex);
/* 405 */     vector3.initFrom(this);
/* 406 */     vector3.dirty_$eq(dirty());
/* 407 */     vector3.gotoFreshPosWritable(k, blockIndex, k ^ blockIndex);
/* 408 */     vector3.display0()[lo] = value;
/* 410 */     if (vector3.depth() == depth() + 1)
/* 412 */       vector3.debug(); 
/* 418 */     Object[] elems = new Object[32];
/* 419 */     elems[0] = value;
/* 420 */     Vector<B> vector4 = new Vector(0, 1, 0);
/* 421 */     vector4.depth_$eq(1);
/* 422 */     vector4.display0_$eq(elems);
/* 423 */     return (endIndex() != startIndex()) ? ((endIndex() != blockIndex) ? s : ((shift != 0) ? ((depth() > 1) ? vector1 : vector2) : vector3)) : vector4;
/*     */   }
/*     */   
/*     */   private void shiftTopLevel(int oldLeft, int newLeft) {
/* 430 */     int i = depth() - 1;
/* 430 */     switch (i) {
/*     */       default:
/* 430 */         throw new MatchError(BoxesRunTime.boxToInteger(i));
/*     */       case 5:
/* 442 */         display5_$eq(copyRange(display5(), oldLeft, newLeft));
/*     */         return;
/*     */       case 4:
/*     */         display4_$eq(copyRange(display4(), oldLeft, newLeft));
/*     */         return;
/*     */       case 3:
/*     */         display3_$eq(copyRange(display3(), oldLeft, newLeft));
/*     */         return;
/*     */       case 2:
/*     */         display2_$eq(copyRange(display2(), oldLeft, newLeft));
/*     */         return;
/*     */       case 1:
/*     */         display1_$eq(copyRange(display1(), oldLeft, newLeft));
/*     */         return;
/*     */       case 0:
/*     */         break;
/*     */     } 
/*     */     display0_$eq(copyRange(display0(), oldLeft, newLeft));
/*     */   }
/*     */   
/*     */   private void zeroLeft(Object[] array, int index) {
/* 446 */     for (int i = 0; i < index; ) {
/* 446 */       array[i] = null;
/* 446 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void zeroRight(Object[] array, int index) {
/* 450 */     for (int i = index; i < array.length; ) {
/* 450 */       array[i] = null;
/* 450 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Object[] copyLeft(Object[] array, int right) {
/* 456 */     Object[] a2 = new Object[array.length];
/* 457 */     Platform$ platform$ = Platform$.MODULE$;
/* 457 */     System.arraycopy(array, 0, a2, 0, right);
/* 458 */     return a2;
/*     */   }
/*     */   
/*     */   private Object[] copyRight(Object[] array, int left) {
/* 461 */     Object[] a2 = new Object[array.length];
/* 462 */     int i = a2.length - left;
/* 462 */     Platform$ platform$ = Platform$.MODULE$;
/* 462 */     System.arraycopy(array, left, a2, left, i);
/* 463 */     return a2;
/*     */   }
/*     */   
/*     */   private void preClean(int depth) {
/* 467 */     depth_$eq(depth);
/* 468 */     int i = depth - 1;
/* 468 */     switch (i) {
/*     */       default:
/* 468 */         throw new MatchError(BoxesRunTime.boxToInteger(i));
/*     */       case 4:
/* 488 */         display5_$eq((Object[])null);
/*     */         break;
/*     */       case 3:
/*     */         display4_$eq((Object[])null);
/*     */         display5_$eq((Object[])null);
/*     */         break;
/*     */       case 2:
/*     */         display3_$eq((Object[])null);
/*     */         display4_$eq((Object[])null);
/*     */         display5_$eq((Object[])null);
/*     */         break;
/*     */       case 1:
/*     */         display2_$eq((Object[])null);
/*     */         display3_$eq((Object[])null);
/*     */         display4_$eq((Object[])null);
/*     */         display5_$eq((Object[])null);
/*     */         break;
/*     */       case 0:
/*     */         display1_$eq((Object[])null);
/*     */         display2_$eq((Object[])null);
/*     */         display3_$eq((Object[])null);
/*     */         display4_$eq((Object[])null);
/*     */         display5_$eq((Object[])null);
/*     */         break;
/*     */       case 5:
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void cleanLeftEdge(int cutIndex) {
/* 495 */     if (cutIndex < 32) {
/* 496 */       zeroLeft(display0(), cutIndex);
/* 498 */     } else if (cutIndex < 1024) {
/* 499 */       zeroLeft(display0(), cutIndex & 0x1F);
/* 500 */       display1_$eq(copyRight(display1(), cutIndex >>> 5));
/* 502 */     } else if (cutIndex < 32768) {
/* 503 */       zeroLeft(display0(), cutIndex & 0x1F);
/* 504 */       display1_$eq(copyRight(display1(), cutIndex >>> 5 & 0x1F));
/* 505 */       display2_$eq(copyRight(display2(), cutIndex >>> 10));
/* 507 */     } else if (cutIndex < 1048576) {
/* 508 */       zeroLeft(display0(), cutIndex & 0x1F);
/* 509 */       display1_$eq(copyRight(display1(), cutIndex >>> 5 & 0x1F));
/* 510 */       display2_$eq(copyRight(display2(), cutIndex >>> 10 & 0x1F));
/* 511 */       display3_$eq(copyRight(display3(), cutIndex >>> 15));
/* 513 */     } else if (cutIndex < 33554432) {
/* 514 */       zeroLeft(display0(), cutIndex & 0x1F);
/* 515 */       display1_$eq(copyRight(display1(), cutIndex >>> 5 & 0x1F));
/* 516 */       display2_$eq(copyRight(display2(), cutIndex >>> 10 & 0x1F));
/* 517 */       display3_$eq(copyRight(display3(), cutIndex >>> 15 & 0x1F));
/* 518 */       display4_$eq(copyRight(display4(), cutIndex >>> 20));
/*     */     } else {
/* 520 */       if (cutIndex < 1073741824) {
/* 521 */         zeroLeft(display0(), cutIndex & 0x1F);
/* 522 */         display1_$eq(copyRight(display1(), cutIndex >>> 5 & 0x1F));
/* 523 */         display2_$eq(copyRight(display2(), cutIndex >>> 10 & 0x1F));
/* 524 */         display3_$eq(copyRight(display3(), cutIndex >>> 15 & 0x1F));
/* 525 */         display4_$eq(copyRight(display4(), cutIndex >>> 20 & 0x1F));
/* 526 */         display5_$eq(copyRight(display5(), cutIndex >>> 25));
/*     */         return;
/*     */       } 
/* 528 */       throw new IllegalArgumentException();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void cleanRightEdge(int cutIndex) {
/* 538 */     if (cutIndex <= 32) {
/* 539 */       zeroRight(display0(), cutIndex);
/* 541 */     } else if (cutIndex <= 1024) {
/* 542 */       zeroRight(display0(), (cutIndex - 1 & 0x1F) + 1);
/* 543 */       display1_$eq(copyLeft(display1(), cutIndex >>> 5));
/* 545 */     } else if (cutIndex <= 32768) {
/* 546 */       zeroRight(display0(), (cutIndex - 1 & 0x1F) + 1);
/* 547 */       display1_$eq(copyLeft(display1(), (cutIndex - 1 >>> 5 & 0x1F) + 1));
/* 548 */       display2_$eq(copyLeft(display2(), cutIndex >>> 10));
/* 550 */     } else if (cutIndex <= 1048576) {
/* 551 */       zeroRight(display0(), (cutIndex - 1 & 0x1F) + 1);
/* 552 */       display1_$eq(copyLeft(display1(), (cutIndex - 1 >>> 5 & 0x1F) + 1));
/* 553 */       display2_$eq(copyLeft(display2(), (cutIndex - 1 >>> 10 & 0x1F) + 1));
/* 554 */       display3_$eq(copyLeft(display3(), cutIndex >>> 15));
/* 556 */     } else if (cutIndex <= 33554432) {
/* 557 */       zeroRight(display0(), (cutIndex - 1 & 0x1F) + 1);
/* 558 */       display1_$eq(copyLeft(display1(), (cutIndex - 1 >>> 5 & 0x1F) + 1));
/* 559 */       display2_$eq(copyLeft(display2(), (cutIndex - 1 >>> 10 & 0x1F) + 1));
/* 560 */       display3_$eq(copyLeft(display3(), (cutIndex - 1 >>> 15 & 0x1F) + 1));
/* 561 */       display4_$eq(copyLeft(display4(), cutIndex >>> 20));
/*     */     } else {
/* 563 */       if (cutIndex <= 1073741824) {
/* 564 */         zeroRight(display0(), (cutIndex - 1 & 0x1F) + 1);
/* 565 */         display1_$eq(copyLeft(display1(), (cutIndex - 1 >>> 5 & 0x1F) + 1));
/* 566 */         display2_$eq(copyLeft(display2(), (cutIndex - 1 >>> 10 & 0x1F) + 1));
/* 567 */         display3_$eq(copyLeft(display3(), (cutIndex - 1 >>> 15 & 0x1F) + 1));
/* 568 */         display4_$eq(copyLeft(display4(), (cutIndex - 1 >>> 20 & 0x1F) + 1));
/* 569 */         display5_$eq(copyLeft(display5(), cutIndex >>> 25));
/*     */         return;
/*     */       } 
/* 571 */       throw new IllegalArgumentException();
/*     */     } 
/*     */   }
/*     */   
/*     */   private int requiredDepth(int xor) {
/* 580 */     if (xor < 33554432) {
/*     */     
/*     */     } else {
/* 581 */       if (xor < 1073741824)
/* 581 */         return 6; 
/* 582 */       throw new IllegalArgumentException();
/*     */     } 
/*     */     return (xor < 32) ? 1 : ((xor < 1024) ? 2 : ((xor < 32768) ? 3 : ((xor < 1048576) ? 4 : "JD-Core does not support Kotlin")));
/*     */   }
/*     */   
/*     */   private Vector<A> dropFront0(int cutIndex) {
/* 586 */     int blockIndex = cutIndex & (0x1F ^ 0xFFFFFFFF);
/* 587 */     int xor = cutIndex ^ endIndex() - 1;
/* 590 */     int d = requiredDepth(xor);
/* 591 */     int shift = cutIndex & ((1 << 5 * d) - 1 ^ 0xFFFFFFFF);
/* 608 */     Vector<A> s = new Vector(cutIndex - shift, endIndex() - shift, blockIndex - shift);
/* 609 */     s.initFrom(this);
/* 610 */     s.dirty_$eq(dirty());
/* 611 */     s.gotoPosWritable(this.focus, blockIndex, this.focus ^ blockIndex);
/* 612 */     s.preClean(d);
/* 613 */     s.cleanLeftEdge(cutIndex - shift);
/* 614 */     return s;
/*     */   }
/*     */   
/*     */   private Vector<A> dropBack0(int cutIndex) {
/* 618 */     int blockIndex = cutIndex - 1 & (0x1F ^ 0xFFFFFFFF);
/* 619 */     int xor = startIndex() ^ cutIndex - 1;
/* 622 */     int d = requiredDepth(xor);
/* 623 */     int shift = startIndex() & ((1 << 5 * d) - 1 ^ 0xFFFFFFFF);
/* 630 */     Vector<A> s = new Vector(startIndex() - shift, cutIndex - shift, blockIndex - shift);
/* 631 */     s.initFrom(this);
/* 632 */     s.dirty_$eq(dirty());
/* 633 */     s.gotoPosWritable(this.focus, blockIndex, this.focus ^ blockIndex);
/* 634 */     s.preClean(d);
/* 635 */     s.cleanRightEdge(cutIndex - shift);
/* 636 */     return s;
/*     */   }
/*     */   
/*     */   public static <A> Vector<A> empty() {
/*     */     return Vector$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<Vector<?>, A, Vector<A>> canBuildFrom() {
/*     */     return Vector$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static GenTraversableFactory<Vector>.GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*     */     return Vector$.MODULE$.ReusableCBF();
/*     */   }
/*     */   
/*     */   public static <A> Some<Vector<A>> unapplySeq(Vector<A> paramVector) {
/*     */     return Vector$.MODULE$.unapplySeq(paramVector);
/*     */   }
/*     */   
/*     */   public static <A> Vector<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*     */     return (Vector<A>)Vector$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> Vector<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return (Vector<T>)Vector$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <T> Vector<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*     */     return (Vector<T>)Vector$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <A> Vector<Vector<Vector<Vector<Vector<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*     */     return (Vector<Vector<Vector<Vector<Vector<A>>>>>)Vector$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*     */   }
/*     */   
/*     */   public static <A> Vector<Vector<Vector<Vector<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*     */     return (Vector<Vector<Vector<Vector<A>>>>)Vector$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*     */   }
/*     */   
/*     */   public static <A> Vector<Vector<Vector<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*     */     return (Vector<Vector<Vector<A>>>)Vector$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*     */   }
/*     */   
/*     */   public static <A> Vector<Vector<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*     */     return (Vector<Vector<A>>)Vector$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> Vector<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*     */     return (Vector<A>)Vector$.MODULE$.tabulate(paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Vector<Vector<Vector<Vector<Vector<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*     */     return (Vector<Vector<Vector<Vector<Vector<A>>>>>)Vector$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Vector<Vector<Vector<Vector<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*     */     return (Vector<Vector<Vector<Vector<A>>>>)Vector$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Vector<Vector<Vector<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*     */     return (Vector<Vector<Vector<A>>>)Vector$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Vector<Vector<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*     */     return (Vector<Vector<A>>)Vector$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Vector<A> fill(int paramInt, Function0<A> paramFunction0) {
/*     */     return (Vector<A>)Vector$.MODULE$.fill(paramInt, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Vector<A> concat(Seq<Traversable<A>> paramSeq) {
/*     */     return (Vector<A>)Vector$.MODULE$.concat(paramSeq);
/*     */   }
/*     */   
/*     */   public static <A> Vector<A> empty() {
/*     */     return (Vector<A>)Vector$.MODULE$.empty();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */