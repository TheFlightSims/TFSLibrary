/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericClassTagCompanion;
/*     */ import scala.collection.generic.GenericClassTagTraversableTemplate;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r}e\001B\001\003\001%\021a\"\0268s_2dW\r\032\"vM\032,'O\003\002\004\t\0059Q.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006\022'\035\0011b\007\020#S1\0022\001D\007\020\033\005\021\021B\001\b\003\0059\t%m\035;sC\016$()\0364gKJ\004\"\001E\t\r\001\021)!\003\001b\001'\t\tA+\005\002\0251A\021QCF\007\002\r%\021qC\002\002\b\035>$\b.\0338h!\t)\022$\003\002\033\r\t\031\021I\\=\021\0071ar\"\003\002\036\005\t1!)\0364gKJ\004B\001D\020\020C%\021\001E\001\002\013\005V4g-\032:MS.,\007c\001\007\001\037A!1EJ\b)\033\005!#BA\023\005\003\0359WM\\3sS\016L!a\n\023\003E\035+g.\032:jG\016c\027m]:UC\036$&/\031<feN\f'\r\\3UK6\004H.\031;f!\ta\001\001\005\003\rU=\t\023BA\026\003\005\035\021U/\0337eKJ\004\"!F\027\n\00592!\001D*fe&\fG.\033>bE2,\007\002\003\031\001\005\013\007I1A\031\002\007Q\fw-F\0013!\r\031dgD\007\002i)\021QGB\001\be\0264G.Z2u\023\t9DG\001\005DY\006\0348\017V1h\021!I\004A!A!\002\023\021\024\001\002;bO\002BQa\017\001\005\002q\na\001P5oSRtD#A\037\025\005\005r\004\"\002\031;\001\b\021\004b\002!\001\001\004%I!Q\001\bQ\026\fG\r\035;s+\005\021\005cA\"|\0379\021A\002R\004\006\013\nA\tAR\001\017+:\024x\016\0347fI\n+hMZ3s!\taqIB\003\002\005!\005\001jE\002H\0232\0022a\t&)\023\tYEE\001\016DY\006\0348\017V1h)J\fg/\032:tC\ndWMR1di>\024\030\020C\003<\017\022\005Q\nF\001G\021\025yu\tb\001Q\0031\031\027M\034\"vS2$gI]8n+\t\tF\f\006\002S=B)1eU+\\;&\021A\013\n\002\r\007\006t')^5mI\032\023x.\034\t\003-^k\021aR\005\0031f\023AaQ8mY&\021!\f\n\002\031\017\026tWM]5d\0072\f7o\035+bO\016{W\016]1oS>t\007C\001\t]\t\025\021bJ1\001\024!\ra\001a\027\005\006?:\003\035\001Y\001\002iB\0311GN.\t\013\t<E\021A2\002\0259,wOQ;jY\022,'/\006\002eOR\021Q-\033\t\005\031)2\007\016\005\002\021O\022)!#\031b\001'A\031A\002\0014\t\013}\013\0079\0016\021\007M2d\rC\004m\017\n\007I\021A7\002\023]\fG/\032:mS:,W#\0018\021\005Uy\027B\0019\007\005\rIe\016\036\005\007e\036\003\013\021\0028\002\025]\fG/\032:mS:,\007\005C\004u\017\n\007I\021A7\002\035]\fG/\032:mS:,G)\0327j[\"1ao\022Q\001\n9\fqb^1uKJd\027N\\3EK2LW\016\t\005\tq\036\023\r\021\"\001\005[\006qQO\034:pY2,G\r\\3oORD\007B\002>HA\003%a.A\bv]J|G\016\\3eY\026tw\r\0365!\r\021ax\tA?\003\021Us'o\0347mK\022,2A`A\026'\tYx\020E\002\026\003\003I1!a\001\007\005\031\te.\037*fM\"I\021qA>\003\002\004%\t!\\\001\005g&TX\r\003\006\002\fm\024\t\031!C\001\003\033\t\001b]5{K~#S-\035\013\005\003\037\t)\002E\002\026\003#I1!a\005\007\005\021)f.\033;\t\023\005]\021\021BA\001\002\004q\027a\001=%c!I\0211D>\003\002\003\006KA\\\001\006g&TX\r\t\005\013\003?Y(\0211A\005\002\005\005\022!B1se\006LXCAA\022!\025)\022QEA\025\023\r\t9C\002\002\006\003J\024\030-\037\t\004!\005-B!\002\n|\005\004\031\002BCA\030w\n\005\r\021\"\001\0022\005I\021M\035:bs~#S-\035\013\005\003\037\t\031\004\003\006\002\030\0055\022\021!a\001\003GA!\"a\016|\005\003\005\013\025BA\022\003\031\t'O]1zA!Q\0211H>\003\002\004%\t!!\020\002\t9,\007\020^\013\003\003\001BAV>\002*!Q\0211I>\003\002\004%\t!!\022\002\0219,\007\020^0%KF$B!a\004\002H!Q\021qCA!\003\003\005\r!a\020\t\025\005-3P!A!B\023\ty$A\003oKb$\b\005\003\006\002Pm\024)\031!C\001\003#\nAAY;gMV\021\0211\013\t\005\031\001\tI\003\003\006\002Xm\024\t\021)A\005\003'\nQAY;gM\002B!\"a\027|\005\007\005\0131BA/\003))g/\0333f]\016,G%\r\t\005gY\nI\003C\004<w\022\005A!!\031\025\025\005\r\024qMA5\003W\ni\007\006\003\002@\005\025\004\002CA.\003?\002\035!!\030\t\017\005\035\021q\fa\001]\"A\021qDA0\001\004\t\031\003\003\005\002<\005}\003\031AA \021)\ty%a\030\021\002\003\007\0211\013\005\bwm$\t\001BA9)\t\t\031\b\006\003\002@\005U\004BCA<\003_\n\t\021q\001\002^\005QQM^5eK:\034W\r\n\032\t\017mZH\021\001\003\002|Q!\021QPAB)\021\ty$a \t\025\005\005\025\021PA\001\002\b\ti&\001\006fm&$WM\\2fIMB\001\"!\"\002z\001\007\0211K\001\002E\"1\021\021R>\005\n5\f!B\\3yi2,gn\032;i\021\035\tii\037C\003\003\037\013a!\0319qK:$G\003BA \003#C\001\"a%\002\f\002\007\021\021F\001\005K2,W\016\013\003\002\f\006]\005\003BAM\003?k!!a'\013\007\005ue!\001\006b]:|G/\031;j_:LA!!)\002\034\n9A/Y5me\026\034\007bBASw\022\005\021qU\001\bM>\024X-Y2i+\021\tI+a.\025\t\005=\0211\026\005\t\003[\013\031\0131\001\0020\006\ta\rE\004\026\003c\013I#!.\n\007\005MfAA\005Gk:\034G/[8ocA\031\001#a.\005\017\005e\0261\025b\001'\t\tQ\013C\004\002>n$)!a0\002\013\005\004\b\017\\=\025\t\005%\022\021\031\005\b\003\007\fY\f1\001o\003\rIG\r\037\025\005\003w\0139\nC\004\002Jn$)!a3\002\rU\004H-\031;f)\031\ty!!4\002P\"9\0211YAd\001\004q\007\002CAi\003\017\004\r!!\013\002\0179,w/\0327f[\"\"\021qYAL\021\035\t9n\037C\003\0033\fa\001\\8dCR,G\003BA \0037Dq!a1\002V\002\007a\016\013\003\002V\006]\005bBAqw\022\005\0211]\001\baJ,\007/\0328e)\021\ty$!:\t\021\005M\025q\034a\001\003SAq!!;|\t\023\tY/\001\006tQ&4GO]5hQR$\"!a\004\t\017\005=8\020\"\002\002r\0061!/Z7pm\026$b!!\013\002t\006U\bbBAb\003[\004\rA\034\005\t\003o\fi\0171\001\002T\0051!-\0364gKJDC!!<\002\030\"9\021Q`>\005\n\005}\030!C:iS\032$H.\0324u)\021\tyA!\001\t\017\t\r\0211 a\001]\006)A.\0324uE\"9!qA>\005\022\t%\021\001\005;ss6+'oZ3XSRDg*\032=u)\t\021Y\001E\002\026\005\033I1Aa\004\007\005\035\021un\0347fC:DqAa\005|\t\013\021)\"A\005j]N,'\017^!mYRA\021q\002B\f\0053\021\031\003C\004\002D\nE\001\031\0018\t\017}\023\t\0021\001\003\034A1!Q\004B\020\003Si\021\001B\005\004\005C!!a\003+sCZ,'o]1cY\026D\001\"a>\003\022\001\007\0211\013\025\005\005#\t9\nC\004\003*m$IAa\013\002\0179,H\016\\8viR1\021q\002B\027\005cAqAa\f\003(\001\007a.\001\003ge>l\007b\002B\032\005O\001\rA\\\001\006k:$\030\016\034\005\b\005oYH\021\001B\035\003\021\021\027N\0343\025\t\t-!1\b\005\t\005{\021)\0041\001\002@\005AA\017[1uQ\026\fG\rC\004\003Bm$\tEa\021\002\021Q|7\013\036:j]\036$\"A!\022\021\t\t\035#\021K\007\003\005\023RAAa\023\003N\005!A.\0318h\025\t\021y%\001\003kCZ\f\027\002\002B*\005\023\022aa\025;sS:<w!\003B,\017\006\005\t\022\001B-\003!)fN]8mY\026$\007c\001,\003\\\031AApRA\001\022\003\021ifE\002\003\\}Dqa\017B.\t\003\021\t\007\006\002\003Z!Q!Q\rB.#\003%\tAa\032\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0235+\021\021IG!!\026\005\t-$\006\002B7\005g\0022!\006B8\023\r\021\tH\002\002\005\035VdGn\013\002\003vA!!q\017B?\033\t\021IH\003\003\003|\005m\025!C;oG\",7m[3e\023\021\021yH!\037\003#Ut7\r[3dW\026$g+\031:jC:\034W\r\002\004\023\005G\022\ra\005\005\n\005\013;\025\021!C\005\005\017\0131B]3bIJ+7o\0347wKR\021!\021\022\t\005\005\017\022Y)\003\003\003\016\n%#AB(cU\026\034G\017C\005\003\022\002\001\r\021\"\003\003\024\006Y\001.Z1eaR\024x\fJ3r)\021\tyA!&\t\023\005]!qRA\001\002\004\021\005b\002BM\001\001\006KAQ\001\tQ\026\fG\r\035;sA!\"!q\023BO!\r)\"qT\005\004\005C3!!\003;sC:\034\030.\0328u\021!\021)\013\001a\001\n\023\t\025a\0027bgR\004HO\035\005\n\005S\003\001\031!C\005\005W\0131\002\\1tiB$(o\030\023fcR!\021q\002BW\021%\t9Ba*\002\002\003\007!\tC\004\0032\002\001\013\025\002\"\002\0211\f7\017\0369ue\002BCAa,\003\036\"A!q\027\001A\002\023%Q.\001\002tu\"I!1\030\001A\002\023%!QX\001\007gj|F%Z9\025\t\005=!q\030\005\n\003/\021I,!AA\0029DqAa1\001A\003&a.A\002tu\002BCA!1\003\036\"9!\021\032\001\005\002\021\t\025a\0025fC\022\004FO\035\005\t\005\033\004A\021\001\003\003P\006Y\001.Z1e!R\024x\fJ3r)\021\tyA!5\t\017\tM'1\032a\001\005\006!\001.Z1e\021\035\0219\016\001C\001\t\005\013q\001\\1tiB#(\017\003\005\003\\\002!\t\001\002Bo\003-a\027m\035;QiJ|F%Z9\025\t\005=!q\034\005\b\005C\024I\0161\001C\003\021a\027m\035;\t\021\005-\001\001\"\001\005\005K$B!a\004\003h\"9!\021\036Br\001\004q\027!A:\t\017\t\004\001\025\"\025\003nV\t\021\005\003\004\003r\002!\t\"Q\001\f]\026<XK\034:pY2,G\r\003\005\003v\002!\t\001\002B|\0039\031\027\r\\2OKb$H*\0328hi\"$2A\034B}\021\035\0219La=A\0029DqA!@\001\t\003\021y0A\tdY\006\0348\017V1h\007>l\007/\0318j_:,\022a\021\005\b\007\007\001A\021AB\003\003\031\031wN\\2biR\031\021ea\002\t\017\r%1\021\001a\001C\005!A\017[1u\021\035\031i\001\001C\001\007\037\t\001\002\n9mkN$S-\035\013\005\007#\031\031\"D\001\001\021\035\t\031ja\003A\002=Aqaa\006\001\t\003\tY/A\003dY\026\f'\017C\004\004\034\001!\ta!\b\002\021%$XM]1u_J,\"aa\b\021\013\tu1\021E\b\n\007\r\rBA\001\005Ji\026\024\030\r^8s\021\035\t)\013\001C!\007O)Ba!\013\0042Q!\021qBB\026\021!\tik!\nA\002\r5\002CB\013\0022>\031y\003E\002\021\007c!q!!/\004&\t\0071\003C\004\0046\001!\taa\016\002\rI,7/\0367u)\005\t\003BBB\036\001\021\005Q.\001\004mK:<G\017\033\005\b\003{\003A\021AB )\ry1\021\t\005\b\003\007\034i\0041\001o\021\035\tI\r\001C\001\007\013\"b!a\004\004H\r%\003bBAb\007\007\002\rA\034\005\b\003#\034\031\0051\001\020\021\035\ty\017\001C\001\007\033\"2aDB(\021\035\t\031ma\023A\0029Dqaa\025\001\t\003\031)&\001\b%a2,8\017J3rI\r|Gn\0348\025\t\rE1q\013\005\b\003'\033\t\0061\001\020\021\035\021\031\002\001C\001\0077\"b!a\004\004^\r}\003bBAb\0073\002\rA\034\005\t\007C\032I\0061\001\004d\005)Q\r\\3ngB)!Q\004B\020\037!91q\r\001\005\n\r%\024aC<sSR,wJ\0316fGR$B!a\004\004l!A1QNB3\001\004\031y'A\002pkR\004Ba!\035\004x5\02111\017\006\005\007k\022i%\001\002j_&!1\021PB:\005Iy%M[3di>+H\017];u'R\024X-Y7\t\017\ru\004\001\"\003\004\000\005Q!/Z1e\037\nTWm\031;\025\t\005=1\021\021\005\t\007\007\033Y\b1\001\004\006\006\021\021N\034\t\005\007c\0329)\003\003\004\n\016M$!E(cU\026\034G/\0238qkR\034FO]3b[\"91Q\022\001\005B\r]\022!B2m_:,\007bBBI\001\021\00531S\001\rgR\024\030N\\4Qe\0264\027\016_\013\003\005\013BS\001ABL\007;\0032!FBM\023\r\031YJ\002\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!\001")
/*     */ public class UnrolledBuffer<T> extends AbstractBuffer<T> implements Buffer<T>, BufferLike<T, UnrolledBuffer<T>>, GenericClassTagTraversableTemplate<T, UnrolledBuffer>, Builder<T, UnrolledBuffer<T>>, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ClassTag<T> tag;
/*     */   
/*     */   private transient Unrolled<T> scala$collection$mutable$UnrolledBuffer$$headptr;
/*     */   
/*     */   private transient Unrolled<T> lastptr;
/*     */   
/*     */   private transient int sz;
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
/*     */   public <NewTo> Builder<T, NewTo> mapResult(Function1 f) {
/*  45 */     return Builder$class.mapResult(this, f);
/*     */   }
/*     */   
/*     */   public <B> Builder<B, UnrolledBuffer<B>> genericClassTagBuilder(ClassTag tag) {
/*  45 */     return GenericClassTagTraversableTemplate.class.genericClassTagBuilder(this, tag);
/*     */   }
/*     */   
/*     */   public GenericClassTagCompanion<UnrolledBuffer> classManifestCompanion() {
/*  45 */     return GenericClassTagTraversableTemplate.class.classManifestCompanion(this);
/*     */   }
/*     */   
/*     */   public <B> Builder<B, UnrolledBuffer<B>> genericClassManifestBuilder(ClassTag manifest) {
/*  45 */     return GenericClassTagTraversableTemplate.class.genericClassManifestBuilder(this, manifest);
/*     */   }
/*     */   
/*     */   public ClassTag<T> tag() {
/*  45 */     return this.tag;
/*     */   }
/*     */   
/*     */   public UnrolledBuffer(ClassTag<T> tag) {
/*  45 */     GenericClassTagTraversableTemplate.class.$init$(this);
/*  45 */     Builder$class.$init$(this);
/*  55 */     this.scala$collection$mutable$UnrolledBuffer$$headptr = newUnrolled();
/*  56 */     this.lastptr = scala$collection$mutable$UnrolledBuffer$$headptr();
/*  57 */     this.sz = 0;
/*     */   }
/*     */   
/*     */   public Unrolled<T> scala$collection$mutable$UnrolledBuffer$$headptr() {
/*     */     return this.scala$collection$mutable$UnrolledBuffer$$headptr;
/*     */   }
/*     */   
/*     */   private void scala$collection$mutable$UnrolledBuffer$$headptr_$eq(Unrolled<T> x$1) {
/*     */     this.scala$collection$mutable$UnrolledBuffer$$headptr = x$1;
/*     */   }
/*     */   
/*     */   private Unrolled<T> lastptr() {
/*     */     return this.lastptr;
/*     */   }
/*     */   
/*     */   private void lastptr_$eq(Unrolled<T> x$1) {
/*     */     this.lastptr = x$1;
/*     */   }
/*     */   
/*     */   private int sz() {
/*  57 */     return this.sz;
/*     */   }
/*     */   
/*     */   private void sz_$eq(int x$1) {
/*  57 */     this.sz = x$1;
/*     */   }
/*     */   
/*     */   public Unrolled<T> headPtr() {
/*  59 */     return scala$collection$mutable$UnrolledBuffer$$headptr();
/*     */   }
/*     */   
/*     */   public void headPtr_$eq(Unrolled<T> head) {
/*  60 */     scala$collection$mutable$UnrolledBuffer$$headptr_$eq(head);
/*     */   }
/*     */   
/*     */   public Unrolled<T> lastPtr() {
/*  61 */     return lastptr();
/*     */   }
/*     */   
/*     */   public void lastPtr_$eq(Unrolled<T> last) {
/*  62 */     lastptr_$eq(last);
/*     */   }
/*     */   
/*     */   public void size_$eq(int s) {
/*  63 */     sz_$eq(s);
/*     */   }
/*     */   
/*     */   public UnrolledBuffer<T> newBuilder() {
/*  65 */     return new UnrolledBuffer(tag());
/*     */   }
/*     */   
/*     */   public Unrolled<T> newUnrolled() {
/*  67 */     return new Unrolled<T>(this, tag());
/*     */   }
/*     */   
/*     */   public int calcNextLength(int sz) {
/*  69 */     return sz;
/*     */   }
/*     */   
/*     */   public UnrolledBuffer$ classTagCompanion() {
/*  71 */     return UnrolledBuffer$.MODULE$;
/*     */   }
/*     */   
/*     */   public UnrolledBuffer<T> concat(UnrolledBuffer<T> that) {
/*  82 */     if (!lastptr().bind(that.scala$collection$mutable$UnrolledBuffer$$headptr()))
/*  82 */       lastptr_$eq(that.lastPtr()); 
/*  85 */     sz_$eq(sz() + that.sz());
/*  90 */     that.clear();
/*  93 */     return this;
/*     */   }
/*     */   
/*     */   public UnrolledBuffer<T> $plus$eq(Object elem) {
/*  97 */     lastptr_$eq(lastptr().append((T)elem));
/*  98 */     sz_$eq(sz() + 1);
/*  99 */     return this;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 103 */     scala$collection$mutable$UnrolledBuffer$$headptr_$eq(newUnrolled());
/* 104 */     lastptr_$eq(scala$collection$mutable$UnrolledBuffer$$headptr());
/* 105 */     sz_$eq(0);
/*     */   }
/*     */   
/*     */   public Iterator<T> iterator() {
/* 108 */     return (Iterator<T>)new UnrolledBuffer$$anon$1(this);
/*     */   }
/*     */   
/*     */   public class UnrolledBuffer$$anon$1 extends AbstractIterator<T> {
/*     */     private int pos;
/*     */     
/*     */     private UnrolledBuffer.Unrolled<T> node;
/*     */     
/*     */     public UnrolledBuffer$$anon$1(UnrolledBuffer<T> $outer) {
/* 109 */       this.pos = -1;
/* 110 */       this.node = $outer.scala$collection$mutable$UnrolledBuffer$$headptr();
/* 111 */       scan();
/*     */     }
/*     */     
/*     */     private int pos() {
/*     */       return this.pos;
/*     */     }
/*     */     
/*     */     private void pos_$eq(int x$1) {
/*     */       this.pos = x$1;
/*     */     }
/*     */     
/*     */     private UnrolledBuffer.Unrolled<T> node() {
/*     */       return this.node;
/*     */     }
/*     */     
/*     */     private void node_$eq(UnrolledBuffer.Unrolled<T> x$1) {
/*     */       this.node = x$1;
/*     */     }
/*     */     
/*     */     private void scan() {
/* 114 */       pos_$eq(pos() + 1);
/* 115 */       while (pos() >= node().size()) {
/* 116 */         pos_$eq(0);
/* 117 */         node_$eq(node().next());
/* 118 */         if (node() == null)
/*     */           return; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 121 */       return (node() != null);
/*     */     }
/*     */     
/*     */     public T next() {
/* 123 */       Object r = scala.runtime.ScalaRunTime$.MODULE$.array_apply(node().array(), pos());
/* 124 */       scan();
/* 125 */       return hasNext() ? (T)r : 
/* 126 */         (T)scala.collection.Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1<T, ?> f) {
/* 130 */     scala$collection$mutable$UnrolledBuffer$$headptr().foreach(f);
/*     */   }
/*     */   
/*     */   public UnrolledBuffer<T> result() {
/* 132 */     return this;
/*     */   }
/*     */   
/*     */   public int length() {
/* 134 */     return sz();
/*     */   }
/*     */   
/*     */   public T apply(int idx) {
/* 137 */     if (idx >= 0 && idx < sz())
/* 137 */       return scala$collection$mutable$UnrolledBuffer$$headptr().apply(idx); 
/* 138 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString());
/*     */   }
/*     */   
/*     */   public void update(int idx, Object newelem) {
/* 141 */     if (idx >= 0 && idx < sz()) {
/* 141 */       scala$collection$mutable$UnrolledBuffer$$headptr().update(idx, (T)newelem);
/*     */       return;
/*     */     } 
/* 142 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString());
/*     */   }
/*     */   
/*     */   public T remove(int idx) {
/* 145 */     if (idx >= 0 && idx < sz()) {
/* 146 */       sz_$eq(sz() - 1);
/* 147 */       return scala$collection$mutable$UnrolledBuffer$$headptr().remove(idx, this);
/*     */     } 
/* 148 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString());
/*     */   }
/*     */   
/*     */   public UnrolledBuffer<T> $plus$eq$colon(Object elem) {
/* 151 */     scala$collection$mutable$UnrolledBuffer$$headptr_$eq(scala$collection$mutable$UnrolledBuffer$$headptr().prepend((T)elem));
/* 152 */     sz_$eq(sz() + 1);
/* 153 */     return this;
/*     */   }
/*     */   
/*     */   public void insertAll(int idx, Traversable<T> elems) {
/* 157 */     if (idx >= 0 && idx <= sz()) {
/* 158 */       scala$collection$mutable$UnrolledBuffer$$headptr().insertAll(idx, elems, this);
/* 159 */       sz_$eq(sz() + elems.size());
/*     */       return;
/*     */     } 
/* 160 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString());
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) {
/* 163 */     out.defaultWriteObject();
/* 164 */     out.writeInt(sz());
/* 165 */     foreach((Function1<?, ?>)new UnrolledBuffer$$anonfun$writeObject$1(this, (UnrolledBuffer<T>)out));
/*     */   }
/*     */   
/*     */   public class UnrolledBuffer$$anonfun$writeObject$1 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public final void apply(Object elem) {
/* 165 */       this.out$1.writeObject(elem);
/*     */     }
/*     */     
/*     */     public UnrolledBuffer$$anonfun$writeObject$1(UnrolledBuffer $outer, ObjectOutputStream out$1) {}
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) {
/* 169 */     in.defaultReadObject();
/* 171 */     int num = in.readInt();
/* 173 */     headPtr_$eq(newUnrolled());
/* 174 */     lastPtr_$eq(headPtr());
/* 175 */     sz_$eq(0);
/* 176 */     int i = 0;
/* 177 */     while (i < num) {
/* 178 */       $plus$eq((T)in.readObject());
/* 179 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public UnrolledBuffer<T> clone() {
/* 183 */     return (UnrolledBuffer<T>)(new UnrolledBuffer(tag())).$plus$plus$eq((TraversableOnce<?>)this);
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/* 185 */     return "UnrolledBuffer";
/*     */   }
/*     */   
/*     */   public static int waterlineDelim() {
/*     */     return UnrolledBuffer$.MODULE$.waterlineDelim();
/*     */   }
/*     */   
/*     */   public static int waterline() {
/*     */     return UnrolledBuffer$.MODULE$.waterline();
/*     */   }
/*     */   
/*     */   public static <T> CanBuildFrom<UnrolledBuffer<?>, T, UnrolledBuffer<T>> canBuildFrom(ClassTag<T> paramClassTag) {
/*     */     return UnrolledBuffer$.MODULE$.canBuildFrom(paramClassTag);
/*     */   }
/*     */   
/*     */   public static <A> UnrolledBuffer<A> empty(ClassTag<A> paramClassTag) {
/*     */     return (UnrolledBuffer<A>)UnrolledBuffer$.MODULE$.empty(paramClassTag);
/*     */   }
/*     */   
/*     */   public static class Unrolled<T> {
/*     */     private int size;
/*     */     
/*     */     private Object array;
/*     */     
/*     */     private Unrolled<T> next;
/*     */     
/*     */     private final UnrolledBuffer<T> buff;
/*     */     
/*     */     private final ClassTag<T> evidence$1;
/*     */     
/*     */     public int size() {
/* 201 */       return this.size;
/*     */     }
/*     */     
/*     */     public void size_$eq(int x$1) {
/* 201 */       this.size = x$1;
/*     */     }
/*     */     
/*     */     public Object array() {
/* 201 */       return this.array;
/*     */     }
/*     */     
/*     */     public void array_$eq(Object x$1) {
/* 201 */       this.array = x$1;
/*     */     }
/*     */     
/*     */     public Unrolled<T> next() {
/* 201 */       return this.next;
/*     */     }
/*     */     
/*     */     public void next_$eq(Unrolled<T> x$1) {
/* 201 */       this.next = x$1;
/*     */     }
/*     */     
/*     */     public UnrolledBuffer<T> buff() {
/* 201 */       return this.buff;
/*     */     }
/*     */     
/*     */     public Unrolled(int size, Object array, Unrolled<T> next, UnrolledBuffer<T> buff, ClassTag<T> evidence$1) {}
/*     */     
/*     */     public Unrolled(ClassTag<T> evidence$2) {
/* 202 */       this(0, evidence$2.newArray(UnrolledBuffer$.MODULE$.unrolledlength()), null, null, evidence$2);
/*     */     }
/*     */     
/*     */     public Unrolled(UnrolledBuffer<T> b, ClassTag<T> evidence$3) {
/* 203 */       this(0, evidence$3.newArray(UnrolledBuffer$.MODULE$.unrolledlength()), null, b, evidence$3);
/*     */     }
/*     */     
/*     */     private int nextlength() {
/* 205 */       return (buff() == null) ? UnrolledBuffer$.MODULE$.unrolledlength() : buff().calcNextLength(scala.runtime.ScalaRunTime$.MODULE$.array_length(array()));
/*     */     }
/*     */     
/*     */     public final Unrolled<T> append(Object elem) {
/*     */       while (true) {
/* 208 */         if (size() < scala.runtime.ScalaRunTime$.MODULE$.array_length(array())) {
/* 209 */           scala.runtime.ScalaRunTime$.MODULE$.array_update(array(), size(), elem);
/* 210 */           size_$eq(size() + 1);
/* 211 */           return this;
/*     */         } 
/* 213 */         next_$eq(new Unrolled(0, this.evidence$1.newArray(nextlength()), null, buff(), this.evidence$1));
/* 214 */         this = next();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 217 */       Unrolled unrolled = this;
/* 218 */       int i = 0;
/* 219 */       while (unrolled != null) {
/* 220 */         Object chunkarr = unrolled.array();
/* 221 */         int chunksz = unrolled.size();
/* 222 */         while (i < chunksz) {
/* 223 */           Object elem = scala.runtime.ScalaRunTime$.MODULE$.array_apply(chunkarr, i);
/* 224 */           f.apply(elem);
/* 225 */           i++;
/*     */         } 
/* 227 */         i = 0;
/* 228 */         unrolled = unrolled.next();
/*     */       } 
/*     */     }
/*     */     
/*     */     public final T apply(int idx) {
/*     */       while (true) {
/* 232 */         if (idx < size())
/* 232 */           return (T)scala.runtime.ScalaRunTime$.MODULE$.array_apply(array(), idx); 
/* 232 */         idx -= size();
/* 232 */         this = next();
/*     */       } 
/*     */     }
/*     */     
/*     */     public final void update(int idx, Object newelem) {
/*     */       while (true) {
/* 234 */         if (idx < size()) {
/* 234 */           scala.runtime.ScalaRunTime$.MODULE$.array_update(array(), idx, newelem);
/*     */           return;
/*     */         } 
/* 234 */         idx -= size();
/* 234 */         this = next();
/*     */       } 
/*     */     }
/*     */     
/*     */     public final Unrolled<T> locate(int idx) {
/*     */       while (true) {
/* 236 */         if (idx < size())
/* 236 */           return this; 
/* 236 */         idx -= size();
/* 236 */         this = next();
/*     */       } 
/*     */     }
/*     */     
/*     */     public Unrolled<T> prepend(Object elem) {
/* 240 */       shiftright();
/* 241 */       scala.runtime.ScalaRunTime$.MODULE$.array_update(array(), 0, elem);
/* 242 */       size_$eq(size() + 1);
/* 247 */       Unrolled<Object> newhead = new Unrolled(buff(), this.evidence$1);
/* 248 */       newhead.append(elem);
/* 249 */       newhead.next_$eq(this);
/* 250 */       return (size() < scala.runtime.ScalaRunTime$.MODULE$.array_length(array())) ? this : (Unrolled)newhead;
/*     */     }
/*     */     
/*     */     private void shiftright() {
/* 254 */       int i = size() - 1;
/* 255 */       while (i >= 0) {
/* 256 */         scala.runtime.ScalaRunTime$.MODULE$.array_update(array(), i + 1, scala.runtime.ScalaRunTime$.MODULE$.array_apply(array(), i));
/* 257 */         i--;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final T remove(int idx, UnrolledBuffer buffer) {
/*     */       while (true) {
/* 262 */         if (idx < size()) {
/* 265 */           Object r = scala.runtime.ScalaRunTime$.MODULE$.array_apply(array(), idx);
/* 266 */           shiftleft(idx);
/* 267 */           size_$eq(size() - 1);
/* 268 */           if (tryMergeWithNext())
/* 268 */             buffer.lastPtr_$eq(this); 
/* 269 */           return (T)r;
/*     */         } 
/* 270 */         idx -= size();
/* 270 */         this = next();
/*     */       } 
/*     */     }
/*     */     
/*     */     private void shiftleft(int leftb) {
/* 273 */       int i = leftb;
/* 274 */       while (i < size() - 1) {
/* 275 */         scala.runtime.ScalaRunTime$.MODULE$.array_update(array(), i, scala.runtime.ScalaRunTime$.MODULE$.array_apply(array(), i + 1));
/* 276 */         i++;
/*     */       } 
/* 278 */       nullout(i, i + 1);
/*     */     }
/*     */     
/*     */     public boolean tryMergeWithNext() {
/* 282 */       scala.Array$.MODULE$.copy(next().array(), 0, array(), size(), next().size());
/* 283 */       size_$eq(size() + next().size());
/* 284 */       next_$eq(next().next());
/* 285 */       return (next() != null && size() + next().size() < scala.runtime.ScalaRunTime$.MODULE$.array_length(array()) * UnrolledBuffer$.MODULE$.waterline() / UnrolledBuffer$.MODULE$.waterlineDelim()) ? ((next() == null)) : false;
/*     */     }
/*     */     
/*     */     public final void insertAll(int idx, Traversable t, UnrolledBuffer buffer) {
/*     */       while (true) {
/* 288 */         if (idx < size()) {
/* 291 */           Unrolled<T> newnextnode = new Unrolled(0, this.evidence$1.newArray(scala.runtime.ScalaRunTime$.MODULE$.array_length(array())), null, buff(), this.evidence$1);
/* 292 */           scala.Array$.MODULE$.copy(array(), idx, newnextnode.array(), 0, size() - idx);
/* 293 */           newnextnode.size_$eq(size() - idx);
/* 294 */           newnextnode.next_$eq(next());
/* 297 */           nullout(idx, size());
/* 298 */           size_$eq(idx);
/* 299 */           next_$eq(null);
/* 302 */           ObjectRef curr = new ObjectRef(this);
/* 303 */           t.foreach((Function1)new UnrolledBuffer$Unrolled$$anonfun$insertAll$1(this, (Unrolled<T>)curr));
/* 304 */           ((Unrolled<T>)curr.elem).next_$eq(newnextnode);
/* 307 */           if (((Unrolled)curr.elem).tryMergeWithNext())
/* 307 */             buffer.lastPtr_$eq((Unrolled)curr.elem); 
/*     */           return;
/*     */         } 
/* 308 */         idx -= size();
/*     */       } 
/*     */     }
/*     */     
/*     */     public class UnrolledBuffer$Unrolled$$anonfun$insertAll$1 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ObjectRef curr$1;
/*     */       
/*     */       public final void apply(Object elem) {
/*     */         this.curr$1.elem = ((UnrolledBuffer.Unrolled<Object>)this.curr$1.elem).append(elem);
/*     */       }
/*     */       
/*     */       public UnrolledBuffer$Unrolled$$anonfun$insertAll$1(UnrolledBuffer.Unrolled $outer, ObjectRef curr$1) {}
/*     */     }
/*     */     
/*     */     private void nullout(int from, int until) {
/* 310 */       int idx = from;
/* 311 */       while (idx < until) {
/* 312 */         scala.runtime.ScalaRunTime$.MODULE$.array_update(array(), idx, null);
/* 313 */         idx++;
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean bind(Unrolled<T> thathead) {
/* 321 */       scala.Predef$.MODULE$.assert((next() == null));
/* 322 */       next_$eq(thathead);
/* 323 */       return tryMergeWithNext();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 326 */       return (new StringBuilder()).append(scala.Predef$.MODULE$.genericArrayOps(scala.Predef$.MODULE$.genericArrayOps(array()).take(size())).mkString((new StringBuilder()).append("Unrolled[").append(BoxesRunTime.boxToInteger(scala.runtime.ScalaRunTime$.MODULE$.array_length(array()))).append("](").toString(), ", ", ")")).append(" -> ").append((next() != null) ? next().toString() : "").toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Unrolled$ {
/*     */     public static final Unrolled$ MODULE$;
/*     */     
/*     */     public <T> scala.runtime.Null$ $lessinit$greater$default$4() {
/*     */       return null;
/*     */     }
/*     */     
/*     */     public Unrolled$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public class UnrolledBuffer$Unrolled$$anonfun$insertAll$1 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ObjectRef curr$1;
/*     */       
/*     */       public final void apply(Object elem) {
/*     */         this.curr$1.elem = ((UnrolledBuffer.Unrolled<Object>)this.curr$1.elem).append(elem);
/*     */       }
/*     */       
/*     */       public UnrolledBuffer$Unrolled$$anonfun$insertAll$1(UnrolledBuffer.Unrolled $outer, ObjectRef curr$1) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\UnrolledBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */