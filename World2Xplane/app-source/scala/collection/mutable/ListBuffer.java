/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Function3;
/*     */ import scala.Function4;
/*     */ import scala.Function5;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSeqLike;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
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
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.IterableForwarder;
/*     */ import scala.collection.generic.SeqForwarder;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.generic.TraversableForwarder;
/*     */ import scala.collection.immutable.;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.ListSerializeEnd$;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.math.Integral;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t]c\001B\001\003\005%\021!\002T5ti\n+hMZ3s\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)\t2\003\003\001\f7y)\023FM\033\021\0071iq\"D\001\003\023\tq!A\001\bBEN$(/Y2u\005V4g-\032:\021\005A\tB\002\001\003\006%\001\021\ra\005\002\002\003F\021A\003\007\t\003+Yi\021AB\005\003/\031\021qAT8uQ&tw\r\005\002\0263%\021!D\002\002\004\003:L\bc\001\007\035\037%\021QD\001\002\007\005V4g-\032:\021\t}\021s\002J\007\002A)\021\021\005B\001\bO\026tWM]5d\023\t\031\003E\001\016HK:,'/[2Ue\0064XM]:bE2,G+Z7qY\006$X\r\005\002\r\001A!ABJ\b)\023\t9#A\001\006Ck\0324WM\035'jW\026\0042\001\004\001\020!\021a!f\004\027\n\005-\022!a\002\"vS2$WM\035\t\004[AzQ\"\001\030\013\005=\"\021!C5n[V$\030M\0317f\023\t\tdF\001\003MSN$\bcA\0204\037%\021A\007\t\002\r'\026\fhi\034:xCJ$WM\035\t\003mmj\021a\016\006\003qe\n!![8\013\003i\nAA[1wC&\021Ah\016\002\r'\026\024\030.\0317ju\006\024G.\032\005\006}\001!\taP\001\007y%t\027\016\036 \025\003!BQ!\021\001\005B\t\013\021bY8na\006t\027n\0348\026\003\r\0032a\b#%\023\t)\005E\001\tHK:,'/[2D_6\004\030M\\5p]\"9q\t\001a\001\n\023A\025!B:uCJ$X#\001\027\t\017)\003\001\031!C\005\027\006I1\017^1si~#S-\035\013\003\031>\003\"!F'\n\00593!\001B+oSRDq\001U%\002\002\003\007A&A\002yIEBaA\025\001!B\023a\023AB:uCJ$\b\005C\005U\001\001\007\t\031!C\005+\006)A.Y:uaU\ta\013E\002./>I!\001\027\030\003\031\021\032w\016\\8oI\r|Gn\0348\t\023i\003\001\031!a\001\n\023Y\026!\0037bgR\004t\fJ3r)\taE\fC\004Q3\006\005\t\031\001,\t\ry\003\001\025)\003W\003\031a\027m\035;1A!9\001\r\001a\001\n\023\t\027\001C3ya>\024H/\0323\026\003\t\004\"!F2\n\005\0214!a\002\"p_2,\027M\034\005\bM\002\001\r\021\"\003h\0031)\007\020]8si\026$w\fJ3r)\ta\005\016C\004QK\006\005\t\031\0012\t\r)\004\001\025)\003c\003%)\007\020]8si\026$\007\005C\004m\001\001\007I\021B7\002\0071,g.F\001o!\t)r.\003\002q\r\t\031\021J\034;\t\017I\004\001\031!C\005g\0069A.\0328`I\025\fHC\001'u\021\035\001\026/!AA\0029DaA\036\001!B\023q\027\001\0027f]\002BQ\001\037\001\005\022e\f!\"\0368eKJd\0270\0338h+\005Q\bcA\027|\037%\021AP\f\002\004'\026\f\b\"\002@\001\t\023y\030aC<sSR,wJ\0316fGR$2\001TA\001\021\035\t\031! a\001\003\013\t1a\\;u!\r1\024qA\005\004\003\0239$AE(cU\026\034GoT;uaV$8\013\036:fC6Dq!!\004\001\t\023\ty!\001\006sK\006$wJ\0316fGR$2\001TA\t\021!\t\031\"a\003A\002\005U\021AA5o!\r1\024qC\005\004\00339$!E(cU\026\034G/\0238qkR\034FO]3b[\"1\021Q\004\001\005B5\fa\001\\3oORD\007BBA\021\001\021\005S.\001\003tSj,\007bBA\023\001\021\005\023qE\001\006CB\004H.\037\013\004\037\005%\002bBA\026\003G\001\rA\\\001\002]\"9\021q\006\001\005\002\005E\022AB;qI\006$X\rF\003M\003g\t)\004C\004\002,\0055\002\031\0018\t\017\005]\022Q\006a\001\037\005\t\001\020C\004\002<\001!\t!!\020\002\021\021\002H.^:%KF$B!a\020\002B5\t\001\001C\004\0028\005e\002\031A\b\t\017\005\025\003\001\"\021\002H\005iA\005\0357vg\022\002H.^:%KF$B!a\020\002J!A\0211JA\"\001\004\ti%\001\002ygB)\021qJA)\0375\tA!C\002\002T\021\021q\002\026:bm\026\0248/\0312mK>s7-\032\005\b\003/\002A\021IA-\003M!\003\017\\;tIAdWo\035\023fc\022\032w\016\\8o)\021\ty$a\027\t\021\005-\023Q\013a\001\003\033Bq!a\030\001\t\003\t\t'A\003dY\026\f'\017F\001M\021\035\t)\007\001C\001\003O\na\002\n9mkN$S-\035\023d_2|g\016\006\003\002@\005%\004bBA\034\003G\002\ra\004\005\b\003[\002A\021AA8\003%Ign]3si\006cG\016F\003M\003c\n\031\bC\004\002,\005-\004\031\0018\t\021\005U\0241\016a\001\003o\n1a]3r!\025\ty%!\037\020\023\r\tY\b\002\002\f)J\fg/\032:tC\ndW\rC\004\002\000\001!\t%!!\002\rI,Wn\034<f)\025a\0251QAC\021\035\tY#! A\0029Dq!a\"\002~\001\007a.A\003d_VtG\017\013\005\002~\005-\025qSAN!\021\ti)a%\016\005\005=%bAAI\r\005Q\021M\0348pi\006$\030n\0348\n\t\005U\025q\022\002\n[&<'/\031;j_:\f#!!'\002s%sg/\0317jI\002Jg\016];uAY\fG.^3tA]LG\016\034\021cK\002\022XM[3di\026$\007%\0338!MV$XO]3!e\026dW-Y:fg:\n#!!(\002\tIr\023'\r\005\b\003C\003A\021AAR\003\031\021Xm];miR\tA\006\003\004\002(\002!\t\005S\001\007i>d\025n\035;\t\017\005-\006\001\"\001\002.\006i\001O]3qK:$Gk\034'jgR$2\001LAX\021\035\tY%!+A\0021Bq!a \001\t\003\t\031\fF\002\020\003kCq!a\013\0022\002\007a\016C\004\002:\002!\t%a/\002\023\021j\027N\\;tI\025\fH\003BA \003{Cq!a0\0028\002\007q\"\001\003fY\026l\007bBAb\001\021\005\023QY\001\tSR,'/\031;peV\021\021q\031\t\006\003\037\nImD\005\004\003\027$!\001C%uKJ\fGo\034:\t\r\005=\007\001\"\021I\003!\021X-\0313P]2L\bbBAj\001\021%\021\021M\001\005G>\004\030\020C\004\002X\002!\t%!7\002\r\025\fX/\0317t)\r\021\0271\034\005\b\003;\f)\0161\001\031\003\021!\b.\031;\t\r\005\005\b\001\"\021@\003\025\031Gn\0348f\021\035\t)\017\001C!\003O\fAb\035;sS:<\007K]3gSb,\"!!;\021\t\005-\030\021\037\b\004+\0055\030bAAx\r\0051\001K]3eK\032LA!a=\002v\n11\013\036:j]\036T1!a<\007Q\025\001\021\021`A\000!\r)\0221`\005\004\003{4!\001E*fe&\fGNV3sg&|g.V%E=!y#\017>HQ\02444}a\002B\002\005!\005!QA\001\013\031&\034HOQ;gM\026\024\bc\001\007\003\b\0311\021A\001E\001\005\023\031bAa\002\003\f\tE\001\003B\020\003\016\021J1Aa\004!\005)\031V-\035$bGR|'/\037\t\004+\tM\021B\001\037\007\021\035q$q\001C\001\005/!\"A!\002\t\021\tm!q\001C\002\005;\tAbY1o\005VLG\016\032$s_6,BAa\b\0032U\021!\021\005\t\n?\t\r\"q\005B\030\005gI1A!\n!\0051\031\025M\034\"vS2$gI]8n!\021\021ICa\013\016\005\t\035\021b\001B\027\t\n!1i\0347m!\r\001\"\021\007\003\007%\te!\031A\n\021\t1\001!q\006\005\t\005o\0219\001\"\001\003:\005Qa.Z<Ck&dG-\032:\026\t\tm\"\021I\013\003\005{\001b\001\004\026\003@\t\r\003c\001\t\003B\0211!C!\016C\002M\001B\001\004\001\003@!Q!q\tB\004\003\003%IA!\023\002\027I,\027\r\032*fg>dg/\032\013\003\005\027\002BA!\024\003T5\021!q\n\006\004\005#J\024\001\0027b]\036LAA!\026\003P\t1qJ\0316fGR\004")
/*     */ public final class ListBuffer<A> extends AbstractBuffer<A> implements Buffer<A>, GenericTraversableTemplate<A, ListBuffer>, BufferLike<A, ListBuffer<A>>, Builder<A, List<A>>, SeqForwarder<A>, Serializable {
/*     */   public static final long serialVersionUID = 3419063961353022662L;
/*     */   
/*     */   private List<A> scala$collection$mutable$ListBuffer$$start;
/*     */   
/*     */   private .colon.colon<A> last0;
/*     */   
/*     */   private boolean exported;
/*     */   
/*     */   private int len;
/*     */   
/*     */   public int lengthCompare(int len) {
/*  45 */     return SeqForwarder.class.lengthCompare(this, len);
/*     */   }
/*     */   
/*     */   public boolean isDefinedAt(int x) {
/*  45 */     return SeqForwarder.class.isDefinedAt(this, x);
/*     */   }
/*     */   
/*     */   public int segmentLength(Function1 p, int from) {
/*  45 */     return SeqForwarder.class.segmentLength(this, p, from);
/*     */   }
/*     */   
/*     */   public int prefixLength(Function1 p) {
/*  45 */     return SeqForwarder.class.prefixLength(this, p);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p) {
/*  45 */     return SeqForwarder.class.indexWhere(this, p);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p, int from) {
/*  45 */     return SeqForwarder.class.indexWhere(this, p, from);
/*     */   }
/*     */   
/*     */   public <B> int indexOf(Object elem) {
/*  45 */     return SeqForwarder.class.indexOf(this, elem);
/*     */   }
/*     */   
/*     */   public <B> int indexOf(Object elem, int from) {
/*  45 */     return SeqForwarder.class.indexOf(this, elem, from);
/*     */   }
/*     */   
/*     */   public <B> int lastIndexOf(Object elem) {
/*  45 */     return SeqForwarder.class.lastIndexOf(this, elem);
/*     */   }
/*     */   
/*     */   public <B> int lastIndexOf(Object elem, int end) {
/*  45 */     return SeqForwarder.class.lastIndexOf(this, elem, end);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p) {
/*  45 */     return SeqForwarder.class.lastIndexWhere(this, p);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p, int end) {
/*  45 */     return SeqForwarder.class.lastIndexWhere(this, p, end);
/*     */   }
/*     */   
/*     */   public Iterator<A> reverseIterator() {
/*  45 */     return SeqForwarder.class.reverseIterator(this);
/*     */   }
/*     */   
/*     */   public <B> boolean startsWith(GenSeq that, int offset) {
/*  45 */     return SeqForwarder.class.startsWith(this, that, offset);
/*     */   }
/*     */   
/*     */   public <B> boolean startsWith(GenSeq that) {
/*  45 */     return SeqForwarder.class.startsWith(this, that);
/*     */   }
/*     */   
/*     */   public <B> boolean endsWith(GenSeq that) {
/*  45 */     return SeqForwarder.class.endsWith(this, that);
/*     */   }
/*     */   
/*     */   public <B> int indexOfSlice(GenSeq that) {
/*  45 */     return SeqForwarder.class.indexOfSlice(this, that);
/*     */   }
/*     */   
/*     */   public <B> int indexOfSlice(GenSeq that, int from) {
/*  45 */     return SeqForwarder.class.indexOfSlice(this, that, from);
/*     */   }
/*     */   
/*     */   public <B> int lastIndexOfSlice(GenSeq that) {
/*  45 */     return SeqForwarder.class.lastIndexOfSlice(this, that);
/*     */   }
/*     */   
/*     */   public <B> int lastIndexOfSlice(GenSeq that, int end) {
/*  45 */     return SeqForwarder.class.lastIndexOfSlice(this, that, end);
/*     */   }
/*     */   
/*     */   public <B> boolean containsSlice(GenSeq that) {
/*  45 */     return SeqForwarder.class.containsSlice(this, that);
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/*  45 */     return SeqForwarder.class.contains(this, elem);
/*     */   }
/*     */   
/*     */   public <B> boolean corresponds(GenSeq that, Function2 p) {
/*  45 */     return SeqForwarder.class.corresponds(this, that, p);
/*     */   }
/*     */   
/*     */   public Range indices() {
/*  45 */     return SeqForwarder.class.indices(this);
/*     */   }
/*     */   
/*     */   public <B> boolean sameElements(GenIterable that) {
/*  45 */     return IterableForwarder.class.sameElements((IterableForwarder)this, that);
/*     */   }
/*     */   
/*     */   public <B> void foreach(Function1 f) {
/*  45 */     TraversableForwarder.class.foreach((TraversableForwarder)this, f);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  45 */     return TraversableForwarder.class.isEmpty((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/*  45 */     return TraversableForwarder.class.nonEmpty((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/*  45 */     return TraversableForwarder.class.hasDefiniteSize((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/*  45 */     return TraversableForwarder.class.forall((TraversableForwarder)this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/*  45 */     return TraversableForwarder.class.exists((TraversableForwarder)this, p);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/*  45 */     return TraversableForwarder.class.count((TraversableForwarder)this, p);
/*     */   }
/*     */   
/*     */   public Option<A> find(Function1 p) {
/*  45 */     return TraversableForwarder.class.find((TraversableForwarder)this, p);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/*  45 */     return (B)TraversableForwarder.class.foldLeft((TraversableForwarder)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B $div$colon(Object z, Function2 op) {
/*  45 */     return (B)TraversableForwarder.class.$div$colon((TraversableForwarder)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/*  45 */     return (B)TraversableForwarder.class.foldRight((TraversableForwarder)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B $colon$bslash(Object z, Function2 op) {
/*  45 */     return (B)TraversableForwarder.class.$colon$bslash((TraversableForwarder)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/*  45 */     return (B)TraversableForwarder.class.reduceLeft((TraversableForwarder)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceLeftOption(Function2 op) {
/*  45 */     return TraversableForwarder.class.reduceLeftOption((TraversableForwarder)this, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/*  45 */     return (B)TraversableForwarder.class.reduceRight((TraversableForwarder)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceRightOption(Function2 op) {
/*  45 */     return TraversableForwarder.class.reduceRightOption((TraversableForwarder)this, op);
/*     */   }
/*     */   
/*     */   public <B> B sum(Numeric num) {
/*  45 */     return (B)TraversableForwarder.class.sum((TraversableForwarder)this, num);
/*     */   }
/*     */   
/*     */   public <B> B product(Numeric num) {
/*  45 */     return (B)TraversableForwarder.class.product((TraversableForwarder)this, num);
/*     */   }
/*     */   
/*     */   public <B> A min(Ordering cmp) {
/*  45 */     return (A)TraversableForwarder.class.min((TraversableForwarder)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> A max(Ordering cmp) {
/*  45 */     return (A)TraversableForwarder.class.max((TraversableForwarder)this, cmp);
/*     */   }
/*     */   
/*     */   public A head() {
/*  45 */     return (A)TraversableForwarder.class.head((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public Option<A> headOption() {
/*  45 */     return TraversableForwarder.class.headOption((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public A last() {
/*  45 */     return (A)TraversableForwarder.class.last((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public Option<A> lastOption() {
/*  45 */     return TraversableForwarder.class.lastOption((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public <B> void copyToBuffer(Buffer dest) {
/*  45 */     TraversableForwarder.class.copyToBuffer((TraversableForwarder)this, dest);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start, int len) {
/*  45 */     TraversableForwarder.class.copyToArray((TraversableForwarder)this, xs, start, len);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start) {
/*  45 */     TraversableForwarder.class.copyToArray((TraversableForwarder)this, xs, start);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs) {
/*  45 */     TraversableForwarder.class.copyToArray((TraversableForwarder)this, xs);
/*     */   }
/*     */   
/*     */   public <B> Object toArray(ClassTag evidence$1) {
/*  45 */     return TraversableForwarder.class.toArray((TraversableForwarder)this, evidence$1);
/*     */   }
/*     */   
/*     */   public Iterable<A> toIterable() {
/*  45 */     return TraversableForwarder.class.toIterable((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public Seq<A> toSeq() {
/*  45 */     return TraversableForwarder.class.toSeq((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> toIndexedSeq() {
/*  45 */     return TraversableForwarder.class.toIndexedSeq((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public <B> Buffer<B> toBuffer() {
/*  45 */     return TraversableForwarder.class.toBuffer((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public Stream<A> toStream() {
/*  45 */     return TraversableForwarder.class.toStream((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public <B> Set<B> toSet() {
/*  45 */     return TraversableForwarder.class.toSet((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  45 */     return TraversableForwarder.class.toMap((TraversableForwarder)this, ev);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/*  45 */     return TraversableForwarder.class.mkString((TraversableForwarder)this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/*  45 */     return TraversableForwarder.class.mkString((TraversableForwarder)this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/*  45 */     return TraversableForwarder.class.mkString((TraversableForwarder)this);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  45 */     return TraversableForwarder.class.addString((TraversableForwarder)this, b, start, sep, end);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String sep) {
/*  45 */     return TraversableForwarder.class.addString((TraversableForwarder)this, b, sep);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b) {
/*  45 */     return TraversableForwarder.class.addString((TraversableForwarder)this, b);
/*     */   }
/*     */   
/*     */   public void sizeHint(int size) {
/*  45 */     Builder$class.sizeHint(this, size);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/*  45 */     Builder$class.sizeHint(this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/*  45 */     Builder$class.sizeHint(this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  45 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/*  45 */     return Builder$class.mapResult(this, f);
/*     */   }
/*     */   
/*     */   public ListBuffer() {
/*  46 */     Builder$class.$init$(this);
/*  46 */     TraversableForwarder.class.$init$((TraversableForwarder)this);
/*  46 */     IterableForwarder.class.$init$((IterableForwarder)this);
/*  46 */     SeqForwarder.class.$init$(this);
/*  59 */     this.scala$collection$mutable$ListBuffer$$start = (List<A>)Nil$.MODULE$;
/*  61 */     this.exported = false;
/*  62 */     this.len = 0;
/*     */   }
/*     */   
/*     */   public GenericCompanion<ListBuffer> companion() {
/*     */     return (GenericCompanion<ListBuffer>)ListBuffer$.MODULE$;
/*     */   }
/*     */   
/*     */   public List<A> scala$collection$mutable$ListBuffer$$start() {
/*     */     return this.scala$collection$mutable$ListBuffer$$start;
/*     */   }
/*     */   
/*     */   private void scala$collection$mutable$ListBuffer$$start_$eq(List<A> x$1) {
/*     */     this.scala$collection$mutable$ListBuffer$$start = x$1;
/*     */   }
/*     */   
/*     */   private .colon.colon<A> last0() {
/*     */     return this.last0;
/*     */   }
/*     */   
/*     */   private void last0_$eq(.colon.colon<A> x$1) {
/*     */     this.last0 = x$1;
/*     */   }
/*     */   
/*     */   private boolean exported() {
/*     */     return this.exported;
/*     */   }
/*     */   
/*     */   private void exported_$eq(boolean x$1) {
/*     */     this.exported = x$1;
/*     */   }
/*     */   
/*     */   private int len() {
/*  62 */     return this.len;
/*     */   }
/*     */   
/*     */   private void len_$eq(int x$1) {
/*  62 */     this.len = x$1;
/*     */   }
/*     */   
/*     */   public Seq<A> underlying() {
/*  64 */     return (Seq<A>)scala$collection$mutable$ListBuffer$$start();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) {
/*  68 */     List<A> xs = scala$collection$mutable$ListBuffer$$start();
/*     */     while (true) {
/*  69 */       if (xs.isEmpty()) {
/*  70 */         out.writeObject(ListSerializeEnd$.MODULE$);
/*  75 */         out.writeBoolean(exported());
/*  78 */         out.writeInt(len());
/*     */         return;
/*     */       } 
/*     */       out.writeObject(xs.head());
/*     */       xs = (List<A>)xs.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokevirtual readObject : ()Ljava/lang/Object;
/*     */     //   4: astore_2
/*     */     //   5: aload_2
/*     */     //   6: getstatic scala/collection/immutable/ListSerializeEnd$.MODULE$ : Lscala/collection/immutable/ListSerializeEnd$;
/*     */     //   9: astore_3
/*     */     //   10: dup
/*     */     //   11: ifnonnull -> 22
/*     */     //   14: pop
/*     */     //   15: aload_3
/*     */     //   16: ifnull -> 29
/*     */     //   19: goto -> 44
/*     */     //   22: aload_3
/*     */     //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   26: ifeq -> 44
/*     */     //   29: aload_0
/*     */     //   30: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   33: invokespecial scala$collection$mutable$ListBuffer$$start_$eq : (Lscala/collection/immutable/List;)V
/*     */     //   36: aload_0
/*     */     //   37: aconst_null
/*     */     //   38: invokespecial last0_$eq : (Lscala/collection/immutable/$colon$colon;)V
/*     */     //   41: goto -> 101
/*     */     //   44: new scala/collection/immutable/$colon$colon
/*     */     //   47: dup
/*     */     //   48: aload_2
/*     */     //   49: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   52: invokespecial <init> : (Ljava/lang/Object;Lscala/collection/immutable/List;)V
/*     */     //   55: astore #4
/*     */     //   57: aload_0
/*     */     //   58: aload #4
/*     */     //   60: invokespecial scala$collection$mutable$ListBuffer$$start_$eq : (Lscala/collection/immutable/List;)V
/*     */     //   63: aload_1
/*     */     //   64: invokevirtual readObject : ()Ljava/lang/Object;
/*     */     //   67: astore_2
/*     */     //   68: aload_2
/*     */     //   69: getstatic scala/collection/immutable/ListSerializeEnd$.MODULE$ : Lscala/collection/immutable/ListSerializeEnd$;
/*     */     //   72: astore #5
/*     */     //   74: dup
/*     */     //   75: ifnonnull -> 87
/*     */     //   78: pop
/*     */     //   79: aload #5
/*     */     //   81: ifnull -> 95
/*     */     //   84: goto -> 118
/*     */     //   87: aload #5
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 118
/*     */     //   95: aload_0
/*     */     //   96: aload #4
/*     */     //   98: invokespecial last0_$eq : (Lscala/collection/immutable/$colon$colon;)V
/*     */     //   101: aload_0
/*     */     //   102: aload_1
/*     */     //   103: invokevirtual readBoolean : ()Z
/*     */     //   106: invokespecial exported_$eq : (Z)V
/*     */     //   109: aload_0
/*     */     //   110: aload_1
/*     */     //   111: invokevirtual readInt : ()I
/*     */     //   114: invokespecial len_$eq : (I)V
/*     */     //   117: return
/*     */     //   118: new scala/collection/immutable/$colon$colon
/*     */     //   121: dup
/*     */     //   122: aload_2
/*     */     //   123: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   126: invokespecial <init> : (Ljava/lang/Object;Lscala/collection/immutable/List;)V
/*     */     //   129: astore #6
/*     */     //   131: aload #4
/*     */     //   133: aload #6
/*     */     //   135: invokevirtual tl_$eq : (Lscala/collection/immutable/List;)V
/*     */     //   138: aload #6
/*     */     //   140: astore #4
/*     */     //   142: aload_1
/*     */     //   143: invokevirtual readObject : ()Ljava/lang/Object;
/*     */     //   146: astore_2
/*     */     //   147: goto -> 68
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #83	-> 0
/*     */     //   #84	-> 5
/*     */     //   #85	-> 29
/*     */     //   #86	-> 36
/*     */     //   #84	-> 41
/*     */     //   #88	-> 44
/*     */     //   #89	-> 57
/*     */     //   #90	-> 63
/*     */     //   #91	-> 68
/*     */     //   #97	-> 95
/*     */     //   #102	-> 101
/*     */     //   #98	-> 101
/*     */     //   #105	-> 109
/*     */     //   #92	-> 118
/*     */     //   #93	-> 131
/*     */     //   #94	-> 138
/*     */     //   #95	-> 142
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	150	0	this	Lscala/collection/mutable/ListBuffer;
/*     */     //   0	150	1	in	Ljava/io/ObjectInputStream;
/*     */     //   5	112	2	elem	Ljava/lang/Object;
/*     */     //   57	44	4	current	Lscala/collection/immutable/$colon$colon;
/*     */     //   131	16	6	list	Lscala/collection/immutable/$colon$colon;
/*     */   }
/*     */   
/*     */   public int length() {
/* 112 */     return len();
/*     */   }
/*     */   
/*     */   public int size() {
/* 115 */     return length();
/*     */   }
/*     */   
/*     */   public A apply(int n) {
/* 120 */     if (n < 0 || n >= len())
/* 120 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString()); 
/* 120 */     return 
/* 121 */       (A)SeqForwarder.class.apply(this, n);
/*     */   }
/*     */   
/*     */   public void update(int n, Object x) {
/* 133 */     if (n < 0 || n >= len())
/* 133 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString()); 
/* 134 */     if (exported())
/* 134 */       copy(); 
/* 135 */     if (n == 0) {
/* 136 */       .colon.colon<A> newElem = new .colon.colon(x, (List)scala$collection$mutable$ListBuffer$$start().tail());
/* 137 */       if (last0() == scala$collection$mutable$ListBuffer$$start())
/* 138 */         last0_$eq(newElem); 
/* 140 */       scala$collection$mutable$ListBuffer$$start_$eq((List<A>)newElem);
/*     */     } else {
/* 142 */       List<A> cursor = scala$collection$mutable$ListBuffer$$start();
/* 143 */       int i = 1;
/* 144 */       while (i < n) {
/* 145 */         cursor = (List<A>)cursor.tail();
/* 146 */         i++;
/*     */       } 
/* 148 */       .colon.colon<A> newElem = new .colon.colon(x, (List)((TraversableLike)cursor.tail()).tail());
/* 149 */       if (last0() == cursor.tail())
/* 150 */         last0_$eq(newElem); 
/* 152 */       ((.colon.colon)cursor).tl_$eq((List)newElem);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ListBuffer<A> $plus$eq(Object x) {
/* 162 */     if (exported())
/* 162 */       copy(); 
/* 163 */     if (scala$collection$mutable$ListBuffer$$start().isEmpty()) {
/* 164 */       last0_$eq(new .colon.colon(x, (List)Nil$.MODULE$));
/* 165 */       scala$collection$mutable$ListBuffer$$start_$eq((List<A>)last0());
/*     */     } else {
/* 167 */       .colon.colon<A> last1 = last0();
/* 168 */       last0_$eq(new .colon.colon(x, (List)Nil$.MODULE$));
/* 169 */       last1.tl_$eq((List)last0());
/*     */     } 
/* 171 */     len_$eq(len() + 1);
/* 172 */     return this;
/*     */   }
/*     */   
/*     */   public ListBuffer<A> $plus$plus$eq(TraversableOnce xs) {
/* 176 */     for (; xs == this; xs = (TraversableOnce)take(size()));
/* 176 */     return (ListBuffer<A>)Growable.class.$plus$plus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public ListBuffer<A> $plus$plus$eq$colon(TraversableOnce xs) {
/* 179 */     for (; xs == this; xs = (TraversableOnce)take(size()));
/* 179 */     return (ListBuffer<A>)BufferLike$class.$plus$plus$eq$colon(this, xs);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 184 */     scala$collection$mutable$ListBuffer$$start_$eq((List<A>)Nil$.MODULE$);
/* 185 */     exported_$eq(false);
/* 186 */     len_$eq(0);
/*     */   }
/*     */   
/*     */   public ListBuffer<A> $plus$eq$colon(Object x) {
/* 196 */     if (exported())
/* 196 */       copy(); 
/* 197 */     .colon.colon<A> newElem = new .colon.colon(x, scala$collection$mutable$ListBuffer$$start());
/* 198 */     if (scala$collection$mutable$ListBuffer$$start().isEmpty())
/* 198 */       last0_$eq(newElem); 
/* 199 */     scala$collection$mutable$ListBuffer$$start_$eq((List<A>)newElem);
/* 200 */     len_$eq(len() + 1);
/* 201 */     return this;
/*     */   }
/*     */   
/*     */   public void insertAll(int n, Traversable seq) {
/* 214 */     if (n < 0 || n > len())
/* 214 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString()); 
/* 215 */     if (exported())
/* 215 */       copy(); 
/* 216 */     List elems = seq.toList().reverse();
/* 217 */     len_$eq(len() + elems.length());
/* 218 */     if (n == 0) {
/* 219 */       while (!elems.isEmpty()) {
/* 220 */         .colon.colon<A> newElem = new .colon.colon(elems.head(), scala$collection$mutable$ListBuffer$$start());
/* 221 */         if (scala$collection$mutable$ListBuffer$$start().isEmpty())
/* 221 */           last0_$eq(newElem); 
/* 222 */         scala$collection$mutable$ListBuffer$$start_$eq((List<A>)newElem);
/* 223 */         elems = (List)elems.tail();
/*     */       } 
/*     */     } else {
/* 226 */       List<A> cursor = scala$collection$mutable$ListBuffer$$start();
/* 227 */       int i = 1;
/* 228 */       while (i < n) {
/* 229 */         cursor = (List<A>)cursor.tail();
/* 230 */         i++;
/*     */       } 
/*     */       while (true) {
/* 232 */         if (!elems.isEmpty()) {
/* 233 */           .colon.colon<A> newElem = new .colon.colon(elems.head(), (List)cursor.tail());
/* 234 */           if (((SeqLike)cursor.tail()).isEmpty())
/* 234 */             last0_$eq(newElem); 
/* 235 */           ((.colon.colon)cursor).tl_$eq((List)newElem);
/* 236 */           elems = (List)elems.tail();
/*     */           continue;
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove(int n, int count) {
/* 249 */     if (n >= len())
/*     */       return; 
/* 251 */     if (count < 0) {
/* 252 */       (new String[2])[0] = "removing negative number (";
/* 252 */       (new String[2])[1] = ") of elements";
/* 252 */       throw new IllegalArgumentException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(count) })));
/*     */     } 
/* 253 */     if (exported())
/* 253 */       copy(); 
/* 254 */     Predef$ predef$1 = Predef$.MODULE$;
/* 254 */     int n1 = RichInt$.MODULE$.max$extension(n, 0);
/* 255 */     Predef$ predef$2 = Predef$.MODULE$;
/* 255 */     int count1 = RichInt$.MODULE$.min$extension(count, len() - n1);
/* 256 */     scala$collection$mutable$ListBuffer$$start().head();
/* 257 */     if (n1 == 0) {
/* 258 */       int c = count1;
/* 259 */       while (c > 0) {
/* 260 */         scala$collection$mutable$ListBuffer$$start_$eq((List<A>)scala$collection$mutable$ListBuffer$$start().tail());
/* 261 */         c--;
/*     */       } 
/*     */     } else {
/* 264 */       List<A> cursor = scala$collection$mutable$ListBuffer$$start();
/* 265 */       int i = 1;
/* 266 */       while (i < n1) {
/* 267 */         cursor = (List<A>)cursor.tail();
/* 268 */         i++;
/*     */       } 
/* 270 */       int c = count1;
/* 271 */       while (c > 0) {
/* 272 */         if (last0() == cursor.tail())
/* 272 */           last0_$eq((.colon.colon<A>)cursor); 
/* 273 */         ((.colon.colon)cursor).tl_$eq((List)((TraversableLike)cursor.tail()).tail());
/* 274 */         c--;
/*     */       } 
/*     */     } 
/* 277 */     len_$eq(len() - count1);
/*     */   }
/*     */   
/*     */   public List<A> result() {
/* 282 */     return toList();
/*     */   }
/*     */   
/*     */   public List<A> toList() {
/* 288 */     exported_$eq(!scala$collection$mutable$ListBuffer$$start().isEmpty());
/* 289 */     return scala$collection$mutable$ListBuffer$$start();
/*     */   }
/*     */   
/*     */   public List<A> prependToList(List<A> xs) {
/* 301 */     if (exported())
/* 301 */       copy(); 
/* 302 */     last0().tl_$eq(xs);
/* 303 */     return scala$collection$mutable$ListBuffer$$start().isEmpty() ? xs : toList();
/*     */   }
/*     */   
/*     */   public A remove(int n) {
/* 318 */     if (n < 0 || n >= len())
/* 318 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString()); 
/* 319 */     if (exported())
/* 319 */       copy(); 
/* 320 */     Object old = scala$collection$mutable$ListBuffer$$start().head();
/* 321 */     if (n == 0) {
/* 322 */       scala$collection$mutable$ListBuffer$$start_$eq((List<A>)scala$collection$mutable$ListBuffer$$start().tail());
/*     */     } else {
/* 324 */       List<A> cursor = scala$collection$mutable$ListBuffer$$start();
/* 325 */       int i = 1;
/* 326 */       while (i < n) {
/* 327 */         cursor = (List<A>)cursor.tail();
/* 328 */         i++;
/*     */       } 
/* 330 */       old = ((IterableLike)cursor.tail()).head();
/* 331 */       if (last0() == cursor.tail())
/* 331 */         last0_$eq((.colon.colon<A>)cursor); 
/* 332 */       ((.colon.colon)cursor).tl_$eq((List)((TraversableLike)cursor.tail()).tail());
/*     */     } 
/* 334 */     len_$eq(len() - 1);
/* 335 */     return (A)old;
/*     */   }
/*     */   
/*     */   public ListBuffer<A> $minus$eq(Object elem) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial exported : ()Z
/*     */     //   4: ifeq -> 11
/*     */     //   7: aload_0
/*     */     //   8: invokespecial copy : ()V
/*     */     //   11: aload_0
/*     */     //   12: invokevirtual scala$collection$mutable$ListBuffer$$start : ()Lscala/collection/immutable/List;
/*     */     //   15: invokevirtual isEmpty : ()Z
/*     */     //   18: ifne -> 306
/*     */     //   21: aload_0
/*     */     //   22: invokevirtual scala$collection$mutable$ListBuffer$$start : ()Lscala/collection/immutable/List;
/*     */     //   25: invokevirtual head : ()Ljava/lang/Object;
/*     */     //   28: dup
/*     */     //   29: astore_2
/*     */     //   30: aload_1
/*     */     //   31: if_acmpne -> 38
/*     */     //   34: iconst_1
/*     */     //   35: goto -> 87
/*     */     //   38: aload_2
/*     */     //   39: ifnonnull -> 46
/*     */     //   42: iconst_0
/*     */     //   43: goto -> 87
/*     */     //   46: aload_2
/*     */     //   47: instanceof java/lang/Number
/*     */     //   50: ifeq -> 64
/*     */     //   53: aload_2
/*     */     //   54: checkcast java/lang/Number
/*     */     //   57: aload_1
/*     */     //   58: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   61: goto -> 87
/*     */     //   64: aload_2
/*     */     //   65: instanceof java/lang/Character
/*     */     //   68: ifeq -> 82
/*     */     //   71: aload_2
/*     */     //   72: checkcast java/lang/Character
/*     */     //   75: aload_1
/*     */     //   76: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   79: goto -> 87
/*     */     //   82: aload_2
/*     */     //   83: aload_1
/*     */     //   84: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   87: ifeq -> 117
/*     */     //   90: aload_0
/*     */     //   91: aload_0
/*     */     //   92: invokevirtual scala$collection$mutable$ListBuffer$$start : ()Lscala/collection/immutable/List;
/*     */     //   95: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   98: checkcast scala/collection/immutable/List
/*     */     //   101: invokespecial scala$collection$mutable$ListBuffer$$start_$eq : (Lscala/collection/immutable/List;)V
/*     */     //   104: aload_0
/*     */     //   105: aload_0
/*     */     //   106: invokespecial len : ()I
/*     */     //   109: iconst_1
/*     */     //   110: isub
/*     */     //   111: invokespecial len_$eq : (I)V
/*     */     //   114: goto -> 306
/*     */     //   117: aload_0
/*     */     //   118: invokevirtual scala$collection$mutable$ListBuffer$$start : ()Lscala/collection/immutable/List;
/*     */     //   121: astore #6
/*     */     //   123: aload #6
/*     */     //   125: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   128: checkcast scala/collection/SeqLike
/*     */     //   131: invokeinterface isEmpty : ()Z
/*     */     //   136: ifne -> 214
/*     */     //   139: aload #6
/*     */     //   141: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   144: checkcast scala/collection/IterableLike
/*     */     //   147: invokeinterface head : ()Ljava/lang/Object;
/*     */     //   152: dup
/*     */     //   153: astore_3
/*     */     //   154: aload_1
/*     */     //   155: if_acmpne -> 162
/*     */     //   158: iconst_1
/*     */     //   159: goto -> 211
/*     */     //   162: aload_3
/*     */     //   163: ifnonnull -> 170
/*     */     //   166: iconst_0
/*     */     //   167: goto -> 211
/*     */     //   170: aload_3
/*     */     //   171: instanceof java/lang/Number
/*     */     //   174: ifeq -> 188
/*     */     //   177: aload_3
/*     */     //   178: checkcast java/lang/Number
/*     */     //   181: aload_1
/*     */     //   182: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   185: goto -> 211
/*     */     //   188: aload_3
/*     */     //   189: instanceof java/lang/Character
/*     */     //   192: ifeq -> 206
/*     */     //   195: aload_3
/*     */     //   196: checkcast java/lang/Character
/*     */     //   199: aload_1
/*     */     //   200: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   203: goto -> 211
/*     */     //   206: aload_3
/*     */     //   207: aload_1
/*     */     //   208: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   211: ifeq -> 308
/*     */     //   214: aload #6
/*     */     //   216: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   219: checkcast scala/collection/SeqLike
/*     */     //   222: invokeinterface isEmpty : ()Z
/*     */     //   227: ifne -> 306
/*     */     //   230: aload #6
/*     */     //   232: checkcast scala/collection/immutable/$colon$colon
/*     */     //   235: astore #5
/*     */     //   237: aload #5
/*     */     //   239: invokevirtual tl : ()Lscala/collection/immutable/List;
/*     */     //   242: aload_0
/*     */     //   243: invokespecial last0 : ()Lscala/collection/immutable/$colon$colon;
/*     */     //   246: astore #4
/*     */     //   248: dup
/*     */     //   249: ifnonnull -> 261
/*     */     //   252: pop
/*     */     //   253: aload #4
/*     */     //   255: ifnull -> 269
/*     */     //   258: goto -> 275
/*     */     //   261: aload #4
/*     */     //   263: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   266: ifeq -> 275
/*     */     //   269: aload_0
/*     */     //   270: aload #5
/*     */     //   272: invokespecial last0_$eq : (Lscala/collection/immutable/$colon$colon;)V
/*     */     //   275: aload #5
/*     */     //   277: aload #6
/*     */     //   279: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   282: checkcast scala/collection/TraversableLike
/*     */     //   285: invokeinterface tail : ()Ljava/lang/Object;
/*     */     //   290: checkcast scala/collection/immutable/List
/*     */     //   293: invokevirtual tl_$eq : (Lscala/collection/immutable/List;)V
/*     */     //   296: aload_0
/*     */     //   297: aload_0
/*     */     //   298: invokespecial len : ()I
/*     */     //   301: iconst_1
/*     */     //   302: isub
/*     */     //   303: invokespecial len_$eq : (I)V
/*     */     //   306: aload_0
/*     */     //   307: areturn
/*     */     //   308: aload #6
/*     */     //   310: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   313: checkcast scala/collection/immutable/List
/*     */     //   316: astore #6
/*     */     //   318: goto -> 123
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #345	-> 0
/*     */     //   #346	-> 11
/*     */     //   #347	-> 21
/*     */     //   #348	-> 90
/*     */     //   #349	-> 104
/*     */     //   #351	-> 117
/*     */     //   #352	-> 123
/*     */     //   #355	-> 214
/*     */     //   #356	-> 230
/*     */     //   #357	-> 237
/*     */     //   #358	-> 269
/*     */     //   #359	-> 275
/*     */     //   #360	-> 296
/*     */     //   #363	-> 306
/*     */     //   #353	-> 308
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	321	0	this	Lscala/collection/mutable/ListBuffer;
/*     */     //   0	321	1	elem	Ljava/lang/Object;
/*     */     //   123	198	6	cursor	Lscala/collection/immutable/List;
/*     */     //   237	69	5	z	Lscala/collection/immutable/$colon$colon;
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/* 366 */     return (Iterator<A>)new ListBuffer$$anon$1(this);
/*     */   }
/*     */   
/*     */   public class ListBuffer$$anon$1 extends AbstractIterator<A> {
/* 374 */     private List<A> cursor = null;
/*     */     
/*     */     private List<A> cursor() {
/* 374 */       return this.cursor;
/*     */     }
/*     */     
/*     */     private void cursor_$eq(List<A> x$1) {
/* 374 */       this.cursor = x$1;
/*     */     }
/*     */     
/* 375 */     private int delivered = 0;
/*     */     
/*     */     private int delivered() {
/* 375 */       return this.delivered;
/*     */     }
/*     */     
/*     */     private void delivered_$eq(int x$1) {
/* 375 */       this.delivered = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 381 */       return (delivered() < this.$outer.length());
/*     */     }
/*     */     
/*     */     public A next() {
/* 383 */       if (hasNext()) {
/* 386 */         if (cursor() == null) {
/* 386 */           cursor_$eq(this.$outer.scala$collection$mutable$ListBuffer$$start());
/*     */         } else {
/* 387 */           cursor_$eq((List<A>)cursor().tail());
/*     */         } 
/* 388 */         delivered_$eq(delivered() + 1);
/* 389 */         return (A)cursor().head();
/*     */       } 
/*     */       throw new NoSuchElementException("next on empty Iterator");
/*     */     }
/*     */     
/*     */     public ListBuffer$$anon$1(ListBuffer $outer) {}
/*     */   }
/*     */   
/*     */   public List<A> readOnly() {
/* 394 */     return scala$collection$mutable$ListBuffer$$start();
/*     */   }
/*     */   
/*     */   private void copy() {
/* 400 */     List<A> cursor = scala$collection$mutable$ListBuffer$$start();
/* 401 */     List<A> limit = last0().tail();
/* 402 */     clear();
/* 403 */     while (cursor != limit) {
/* 404 */       $plus$eq((A)cursor.head());
/* 405 */       cursor = (List<A>)cursor.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*     */     boolean bool;
/* 409 */     if (that instanceof ListBuffer) {
/* 409 */       ListBuffer listBuffer = (ListBuffer)that;
/* 409 */       bool = readOnly().equals(listBuffer.readOnly());
/*     */     } else {
/* 411 */       bool = GenSeqLike.class.equals((GenSeqLike)this, that);
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public ListBuffer<A> clone() {
/* 418 */     return (new ListBuffer()).$plus$plus$eq((TraversableOnce<A>)this);
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/* 424 */     return "ListBuffer";
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<ListBuffer<?>, A, ListBuffer<A>> canBuildFrom() {
/*     */     return ListBuffer$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Some<ListBuffer<A>> unapplySeq(ListBuffer<A> paramListBuffer) {
/*     */     return ListBuffer$.MODULE$.unapplySeq(paramListBuffer);
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*     */     return (ListBuffer<A>)ListBuffer$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> ListBuffer<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return (ListBuffer<T>)ListBuffer$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <T> ListBuffer<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*     */     return (ListBuffer<T>)ListBuffer$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<ListBuffer<ListBuffer<ListBuffer<ListBuffer<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*     */     return (ListBuffer<ListBuffer<ListBuffer<ListBuffer<ListBuffer<A>>>>>)ListBuffer$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<ListBuffer<ListBuffer<ListBuffer<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*     */     return (ListBuffer<ListBuffer<ListBuffer<ListBuffer<A>>>>)ListBuffer$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<ListBuffer<ListBuffer<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*     */     return (ListBuffer<ListBuffer<ListBuffer<A>>>)ListBuffer$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<ListBuffer<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*     */     return (ListBuffer<ListBuffer<A>>)ListBuffer$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*     */     return (ListBuffer<A>)ListBuffer$.MODULE$.tabulate(paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<ListBuffer<ListBuffer<ListBuffer<ListBuffer<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*     */     return (ListBuffer<ListBuffer<ListBuffer<ListBuffer<ListBuffer<A>>>>>)ListBuffer$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<ListBuffer<ListBuffer<ListBuffer<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*     */     return (ListBuffer<ListBuffer<ListBuffer<ListBuffer<A>>>>)ListBuffer$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<ListBuffer<ListBuffer<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*     */     return (ListBuffer<ListBuffer<ListBuffer<A>>>)ListBuffer$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<ListBuffer<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*     */     return (ListBuffer<ListBuffer<A>>)ListBuffer$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<A> fill(int paramInt, Function0<A> paramFunction0) {
/*     */     return (ListBuffer<A>)ListBuffer$.MODULE$.fill(paramInt, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<A> concat(Seq<Traversable<A>> paramSeq) {
/*     */     return (ListBuffer<A>)ListBuffer$.MODULE$.concat(paramSeq);
/*     */   }
/*     */   
/*     */   public static GenTraversableFactory<ListBuffer>.GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*     */     return ListBuffer$.MODULE$.ReusableCBF();
/*     */   }
/*     */   
/*     */   public static <A> ListBuffer<A> empty() {
/*     */     return (ListBuffer<A>)ListBuffer$.MODULE$.empty();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ListBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */