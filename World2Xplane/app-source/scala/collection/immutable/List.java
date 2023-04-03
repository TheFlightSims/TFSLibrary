/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Function3;
/*     */ import scala.Function4;
/*     */ import scala.Function5;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractSeq;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.LinearSeq;
/*     */ import scala.collection.LinearSeqLike;
/*     */ import scala.collection.LinearSeqOptimized;
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
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.ListBuffer;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParSeq;
/*     */ import scala.math.Integral;
/*     */ import scala.math.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.util.Either;
/*     */ import scala.util.Left;
/*     */ import scala.util.Right;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021Ec!B\001\003\003CI!\001\002'jgRT!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006\022'\031\0011bG\020#SA\031A\"D\b\016\003\021I!A\004\003\003\027\005\0237\017\036:bGR\034V-\035\t\003!Ea\001\001\002\004\023\001\021\025\ra\005\002\002\003F\021A\003\007\t\003+Yi\021AB\005\003/\031\021qAT8uQ&tw\r\005\002\0263%\021!D\002\002\004\003:L\bc\001\017\036\0375\t!!\003\002\037\005\tIA*\0338fCJ\034V-\035\t\003+\001J!!\t\004\003\017A\023x\016Z;diB!1EJ\b)\033\005!#BA\023\005\003\0359WM\\3sS\016L!a\n\023\0035\035+g.\032:jGR\023\030M^3sg\006\024G.\032+f[Bd\027\r^3\021\005q\001\001\003\002\007+\0371J!a\013\003\003%1Kg.Z1s'\026\fx\n\035;j[&TX\r\032\t\0049\001y\001\"\002\030\001\t\003y\023A\002\037j]&$h\bF\001-\021\025\t\004\001\"\0213\003%\031w.\0349b]&|g.F\0014!\r\031C\007K\005\003k\021\022\001cR3oKJL7mQ8na\006t\027n\0348\t\013]\002a\021\001\035\002\017%\034X)\0349usV\t\021\b\005\002\026u%\0211H\002\002\b\005>|G.Z1o\021\025i\004A\"\001?\003\021AW-\0313\026\003=AQ\001\021\001\007\002\005\013A\001^1jYV\tA\006C\003D\001\021\005A)\001\007%G>dwN\034\023d_2|g.\006\002F\021R\021ai\023\t\0049\0019\005C\001\tI\t\025I%I1\001K\005\005\021\025CA\b\031\021\025a%\t1\001H\003\005A\b\"\002(\001\t\003y\025A\005\023d_2|g\016J2pY>tGeY8m_:,\"\001U*\025\005E#\006c\001\017\001%B\021\001c\025\003\006\0236\023\rA\023\005\006+6\003\r!U\001\007aJ,g-\033=\t\013]\003A\021\001-\0025I,g/\032:tK~#3m\0347p]\022\032w\016\\8oI\r|Gn\0348\026\005ecFC\001.^!\ra\002a\027\t\003!q#Q!\023,C\002)CQ!\026,A\002iCQa\030\001\005\002\001\f1\"\\1q\007>t7/\032:wKV\021\021\r\032\013\003E&\0042\001\b\001d!\t\001B\rB\003J=\n\007Q-\005\002\020MB\021QcZ\005\003Q\032\021a!\0218z%\0264\007\"\0026_\001\004Y\027!\0014\021\tUawbY\005\003[\032\021\021BR;oGRLwN\\\031\t\013=\004A\021\t9\002\025\021\002H.^:%a2,8/F\002ryR$\"A]?\025\005M4\bC\001\tu\t\025)hN1\001\024\005\021!\006.\031;\t\013]t\0079\001=\002\005\t4\007#B\022zYm\034\030B\001>%\0051\031\025M\034\"vS2$gI]8n!\t\001B\020B\003J]\n\007!\nC\003]\002\007q0\001\003uQ\006$\b\003\002\007\002\002mL1!a\001\005\005I9UM\034+sCZ,'o]1cY\026|enY3\t\017\005\035\001\001\"\021\002\n\005YA\005\0357vg\022\032w\016\\8o+\031\tY!!\007\002\022Q!\021QBA\016)\021\ty!a\005\021\007A\t\t\002\002\004v\003\013\021\ra\005\005\bo\006\025\0019AA\013!\035\031\023\020LA\f\003\037\0012\001EA\r\t\031I\025Q\001b\001\025\"A\021QDA\003\001\004\t9\"\001\003fY\026l\007BBA\021\001\021\005\023)\001\004u_2K7\017\036\005\b\003K\001A\021IA\024\003\021!\030m[3\025\0071\nI\003\003\005\002,\005\r\002\031AA\027\003\005q\007cA\013\0020%\031\021\021\007\004\003\007%sG\017C\004\0026\001!\t%a\016\002\t\021\024x\016\035\013\004Y\005e\002\002CA\026\003g\001\r!!\f\t\017\005u\002\001\"\021\002@\005)1\017\\5dKR)A&!\021\002F!A\0211IA\036\001\004\ti#\001\003ge>l\007\002CA$\003w\001\r!!\f\002\013UtG/\0337\t\017\005-\003\001\"\021\002N\005IA/Y6f%&<\007\016\036\013\004Y\005=\003\002CA\026\003\023\002\r!!\f\t\017\005M\003\001\"\021\002V\00591\017\0357ji\006#H\003BA,\003;\002R!FA-Y1J1!a\027\007\005\031!V\017\0357fe!A\0211FA)\001\004\ti\003C\004\002b\001!\t%a\031\002\023Q\f7.Z,iS2,Gc\001\027\002f!A\021qMA0\001\004\tI'A\001q!\021)BnD\035\t\017\0055\004\001\"\021\002p\005IAM]8q/\"LG.\032\013\004Y\005E\004\002CA4\003W\002\r!!\033\t\017\005U\004\001\"\021\002x\005!1\017]1o)\021\t9&!\037\t\021\005\035\0241\017a\001\003SBa!! \001\t\003\n\025a\002:fm\026\0248/\032\005\b\003\003\003A\021IAB\003%1w\016\0343SS\036DG/\006\003\002\006\006-E\003BAD\003/#B!!#\002\016B\031\001#a#\005\r%\013yH1\001\024\021!\ty)a A\002\005E\025AA8q!!)\0221S\b\002\n\006%\025bAAK\r\tIa)\0368di&|gN\r\005\t\0033\013y\b1\001\002\n\006\t!\020C\004\002\036\002!\t%a(\002\031M$(/\0338h!J,g-\033=\026\005\005\005\006\003BAR\003[k!!!*\013\t\005\035\026\021V\001\005Y\006twM\003\002\002,\006!!.\031<b\023\021\ty+!*\003\rM#(/\0338h\021\035\t\031\f\001C!\003k\013\001\002^8TiJ,\027-\\\013\003\003o\003B\001HA]\037%\031\0211\030\002\003\rM#(/Z1n\021\035\ty\f\001C#\003\003\fqAZ8sK\006\034\007.\006\003\002D\006EG\003BAc\003\027\0042!FAd\023\r\tIM\002\002\005+:LG\017C\004k\003{\003\r!!4\021\013Uaw\"a4\021\007A\t\t\016\002\004J\003{\023\ra\005\025\005\003{\013)\016E\002\026\003/L1!!7\007\005\031Ig\016\\5oK\"1\021Q\034\001\005\002\005\013\001C]3n_Z,G)\0369mS\016\fG/Z:)\021\005m\027\021]At\003W\0042!FAr\023\r\t)O\002\002\013I\026\004(/Z2bi\026$\027EAAu\003Y)8/\032\021aI&\034H/\0338di\002\004\023N\\:uK\006$\027EAAw\003\025\021d\006\017\0301S\025\001\021\021_A{\023\r\t\031P\001\002\rI\r|Gn\0348%G>dwN\034\006\004\003o\024\021a\001(jY\0369\0211 \002\t\002\005u\030\001\002'jgR\0042\001HA\000\r\031\t!\001#\001\003\002M!\021q B\002!\021\031#Q\001\025\n\007\t\035AE\001\006TKF4\025m\031;pefDqALA\000\t\003\021Y\001\006\002\002~\"A!qBA\000\t\007\021\t\"\001\007dC:\024U/\0337e\rJ|W.\006\003\003\024\t\005RC\001B\013!!\031\023Pa\006\003 \t\r\002\003\002B\r\0057i!!a@\n\007\tuAG\001\003D_2d\007c\001\t\003\"\0211!C!\004C\002M\001B\001\b\001\003 !A!qEA\000\t\003\021I#\001\006oK^\024U/\0337eKJ,BAa\013\003<U\021!Q\006\t\t\005_\021)D!\017\003>5\021!\021\007\006\004\005g!\021aB7vi\006\024G.Z\005\005\005o\021\tDA\004Ck&dG-\032:\021\007A\021Y\004\002\004\023\005K\021\ra\005\t\0059\001\021I\004\003\005\003B\005}H\021\tB\"\003\025)W\016\035;z+\021\021)Ea\023\026\005\t\035\003\003\002\017\001\005\023\0022\001\005B&\t\031\021\"q\bb\001'!A!qJA\000\t\003\022\t&A\003baBd\0270\006\003\003T\teC\003\002B+\0057\002B\001\b\001\003XA\031\001C!\027\005\rI\021iE1\001\024\021!\021iF!\024A\002\t}\023A\001=t!\025)\"\021\rB,\023\r\021\031G\002\002\013yI,\007/Z1uK\022t\004\002\003B4\003$\tA!\033\002\013I\fgnZ3\025\021\t-$Q\016B9\005k\002B\001\b\001\002.!A!q\016B3\001\004\ti#A\003ti\006\024H\017\003\005\003t\t\025\004\031AA\027\003\r)g\016\032\005\t\005o\022)\0071\001\003z\005!1\017^3q!\031)B.!\f\002.!B!QMAq\005{\nY/\t\002\003\000\005)Ro]3!A&$XM]1uK\002\004\023N\\:uK\006$\007\002\003BB\003$\tA!\"\002\t5\f7.Z\013\005\005\017\023i\t\006\004\003\n\n=%\021\023\t\0059\001\021Y\tE\002\021\005\033#aA\005BA\005\004\031\002\002CA\026\005\003\003\r!!\f\t\021\005u!\021\021a\001\005\027C\003B!!\002b\nU\0251^\021\003\005/\013!#^:fA\0014\027\016\0347aA%t7\017^3bI\"A!1TA\000\t\003\021i*A\004gY\006$H/\0328\026\t\t}%Q\025\013\005\005C\0239\013\005\003\035\001\t\r\006c\001\t\003&\0221!C!'C\002MA\001B!+\003\032\002\007!1V\001\004qN\034\b\003\002\017\001\005CC\003B!'\002b\n=\0261^\021\003\005c\013\001'^:fA\001D8o\035\030gY\006$H/\0328aA%t7\017^3bI\002zg\r\t1MSN$hF\0327biR,g\016\013=tg&\002\007\002\003B[\003$\tAa.\002\013Ut'0\0339\026\r\te&\021\031Bd)\021\021YL!3\021\017U\tIF!0\003DB!A\004\001B`!\r\001\"\021\031\003\007%\tM&\031A\n\021\tq\001!Q\031\t\004!\t\035GAB%\0034\n\0071\003\003\005\003^\tM\006\031\001Bf!\021a\002A!4\021\017U\tIFa0\003F\"B!1WAq\005#\fY/\t\002\003T\006QSo]3!Ab\034h&\0368{SB\004\007%\0338ti\026\fG\rI8gA\001d\025n\035;/k:T\030\016\035\025yg&\002\007\002\003B[\003$\tAa6\026\r\te'\021\035Bt)\021\021YN!;\021\017U\tIF!8\003dB!A\004\001Bp!\r\001\"\021\035\003\007%\tU'\031A\n\021\tq\001!Q\035\t\004!\t\035HAB%\003V\n\0071\003\003\005\003^\tU\007\031\001Bv!\025a!Q\036By\023\r\021y\017\002\002\t\023R,'/\0312mKB9Q#!\027\003`\n\025\b\006\003Bk\003C\024\t.a;\t\021\t]\030q C\001\005s\fQ\001\\3giN,bAa?\004\002\r\rB\003\002B\007\007\001B\001\b\001\003\000B\031\001c!\001\005\rI\021)P1\001\024\021!\031)A!>A\002\r\035\021AA3t!\025a!Q^B\005!!\031Yaa\007\003\000\016\005b\002BB\007\007/qAaa\004\004\0265\0211\021\003\006\004\007'A\021A\002\037s_>$h(C\001\b\023\r\031IBB\001\ba\006\0347.Y4f\023\021\031iba\b\003\r\025KG\017[3s\025\r\031IB\002\t\004!\r\rBAB%\003v\n\0071\003\013\005\003v\006\0058qEAvC\t\031I#A#vg\026\004\003\r_:!G>dG.Z2uAm\0043-Y:fA1+g\r\036\025yu\001\n\025\006I\037?Aa\004S\020\031\021j]N$X-\0313!_\032\004\003\rT5ti:bWM\032;tQa\034\030\006\031\005\t\007[\ty\020\"\001\0040\0051!/[4iiN,ba!\r\004B\r]B\003BB\032\007s\001B\001\b\001\0046A\031\001ca\016\005\r%\033YC1\001\024\021!\031)aa\013A\002\rm\002#\002\007\003n\016u\002\003CB\006\0077\031yd!\016\021\007A\031\t\005\002\004\023\007W\021\ra\005\025\t\007W\t\to!\022\002l\006\0221qI\001HkN,\007\005\031=tA\r|G\016\\3di\002Z\beY1tK\002\022\026n\0325uQaT\004EQ\025!{y\002\003\020I?aA%t7\017^3bI\002zg\r\t1MSN$hF]5hQR\034\b\006_:*A\"A11JA\000\t\003\031i%\001\005tKB\f'/\031;f+\031\031yea\026\004^Q!1\021KB0!\035)\022\021LB*\0073\002B\001\b\001\004VA\031\001ca\026\005\rI\031IE1\001\024!\021a\002aa\027\021\007A\031i\006\002\004J\007\023\022\ra\005\005\t\007\013\031I\0051\001\004bA)AB!<\004dAA11BB\016\007+\032Y\006\013\005\004J\005\0058qMAvC\t\031I'A%vg\026\004\003\r\0134pe\002BC*\0324uQaL\003\005P\027!KNL\003%_5fY\022\004\003\020\f\021g_J\004\003FU5hQRD\0030\013\021=[\001*7/\013\021zS\026dG\r\t=*A\002Jgn\035;fC\022D\001b!\034\002\000\022\0051qN\001\rMJ|W.\023;fe\006$xN]\013\005\007c\0329\b\006\003\004t\re\004\003\002\017\001\007k\0022\001EB<\t\031\02121\016b\001'!A11PB6\001\004\031i(\001\002jiB)Aba \004v%\0311\021\021\003\003\021%#XM]1u_JD\003ba\033\002b\016\025\0251^\021\003\007\017\013A&^:fA\001LGO\f;p\031&\034H\017\031\021j]N$X-\0313!_\032\004\003\rT5ti:\"x\016T5ti\"JG/\0131\t\021\r-\025q C\001\007\033\013\021B\032:p[\006\023(/Y=\026\t\r=5Q\023\013\005\007#\0339\n\005\003\035\001\rM\005c\001\t\004\026\0221!c!#C\002MA\001b!'\004\n\002\00711T\001\004CJ\024\b#B\013\004\036\016M\025bABP\r\t)\021I\035:bs\"B1\021RAq\007G\013Y/\t\002\004&\006)To]3!A\006\024(/Y=/i>d\025n\035;aA%t7\017^3bI\002zg\r\t1MSN$hF\032:p[\006\023(/Y=)CJ\024\030-_\025a\021!\031Y)a@\005\002\r%V\003BBV\007c#\002b!,\0044\016]6\021\030\t\0059\001\031y\013E\002\021\007c#aAEBT\005\004\031\002\002CBM\007O\003\ra!.\021\013U\031ija,\t\021\t=4q\025a\001\003[A\001ba/\004(\002\007\021QF\001\004Y\026t\007\006CBT\003C\034y,a;\"\005\r\005\027AU;tK\002\002\027M\035:bs:2\030.Z<)gR\f'\017\036\027!K:$\027F\f;p\031&\034H\017\031\021j]N$X-\0313!_\032\004\003\rT5ti:2'o\\7BeJ\f\027\020K1se\006LH\006I:uCJ$H\006I3oI&\002\007\002CBc\003$\taa2\002\t5\f\007OM\013\t\007\023\034Yna8\004RR111ZBq\007K$Ba!4\004VB!A\004ABh!\r\0012\021\033\003\b\007'\034\031M1\001\024\005\005\031\005b\0026\004D\002\0071q\033\t\n+\005M5\021\\Bo\007\037\0042\001EBn\t\031\02121\031b\001'A\031\001ca8\005\r%\033\031M1\001\024\021!\021ifa1A\002\r\r\b\003\002\017\001\0073D\001ba:\004D\002\0071\021^\001\003sN\004B\001\b\001\004^\"B11YAq\007[\fY/\t\002\004p\006qTo]3!A\"B8\017\f\021zg&r#0\0339qK\022tS.\0319)M&\002\007%\0338ti\026\fG\rI8gA\001d\025n\035;/[\006\004(\007\013=tY\001J8/\013\025gS\001D\001ba=\002\000\022\0051Q_\001\bM>\024\030\r\03473+\031\0319\020\"\001\005\006Q11\021 C\004\t\027!2!OB~\021\035Q7\021\037a\001\007{\004\002\"FAJ\007$\031!\017\t\004!\021\005AA\002\n\004r\n\0071\003E\002\021\t\013!a!SBy\005\004\031\002\002\003B/\007c\004\r\001\"\003\021\tq\0011q \005\t\007O\034\t\0201\001\005\016A!A\004\001C\002Q!\031\t0!9\005\022\005-\030E\001C\n\003\021+8/\032\021aQa\034H\006I=tS9R\030\016\0359fI:2wN]1mY\"2\027\006\031\021j]N$X-\0313!_\032\004\003\rT5ti:2wN]1mYJB\003p\035\027!sNL\003FZ\025a\021!!9\"a@\005\002\021e\021aB3ySN$8OM\013\007\t7!)\003\"\013\025\r\021uA1\006C\030)\rIDq\004\005\bU\022U\001\031\001C\021!!)\0221\023C\022\tOI\004c\001\t\005&\0211!\003\"\006C\002M\0012\001\005C\025\t\031IEQ\003b\001'!A!Q\fC\013\001\004!i\003\005\003\035\001\021\r\002\002CBt\t+\001\r\001\"\r\021\tq\001Aq\005\025\t\t+\t\t\017\"\016\002l\006\022AqG\001EkN,\007\005\031\025yg2\002\023p]\025/u&\004\b/\0323/KbL7\017^:)M&\002\007%\0338ti\026\fG\rI8gA\001d\025n\035;/KbL7\017^:3Qa\034H\006I=tS!2\027\006\031\005\t\tw\ty\020\"\001\005>\005IAO]1ogB|7/Z\013\005\t!9\005\006\003\005B\021%\003\003\002\017\001\t\007\002B\001\b\001\005FA\031\001\003b\022\005\rI!ID1\001\024\021!\021I\013\"\017A\002\021\005\003\006\003C\035\003C$i%a;\"\005\021=\023\001N;tK\002\002\007p]:/iJ\fgn\0359pg\026\004\007%\0338ti\026\fG\rI8gA\001d\025n\035;/iJ\fgn\0359pg\026D\003p]:*A\002")
/*     */ public abstract class List<A> extends AbstractSeq<A> implements LinearSeq<A>, Product, GenericTraversableTemplate<A, List>, LinearSeqOptimized<A, List<A>> {
/*     */   public boolean scala$collection$LinearSeqOptimized$$super$sameElements(GenIterable that) {
/*  84 */     return IterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public int length() {
/*  84 */     return LinearSeqOptimized.class.length(this);
/*     */   }
/*     */   
/*     */   public A apply(int n) {
/*  84 */     return (A)LinearSeqOptimized.class.apply(this, n);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/*  84 */     return LinearSeqOptimized.class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/*  84 */     return LinearSeqOptimized.class.exists(this, p);
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/*  84 */     return LinearSeqOptimized.class.contains(this, elem);
/*     */   }
/*     */   
/*     */   public Option<A> find(Function1 p) {
/*  84 */     return LinearSeqOptimized.class.find(this, p);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 f) {
/*  84 */     return (B)LinearSeqOptimized.class.foldLeft(this, z, f);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 f) {
/*  84 */     return (B)LinearSeqOptimized.class.reduceLeft(this, f);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/*  84 */     return (B)LinearSeqOptimized.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public A last() {
/*  84 */     return (A)LinearSeqOptimized.class.last(this);
/*     */   }
/*     */   
/*     */   public List<A> dropRight(int n) {
/*  84 */     return (List<A>)LinearSeqOptimized.class.dropRight(this, n);
/*     */   }
/*     */   
/*     */   public <B> boolean sameElements(GenIterable that) {
/*  84 */     return LinearSeqOptimized.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public int lengthCompare(int len) {
/*  84 */     return LinearSeqOptimized.class.lengthCompare(this, len);
/*     */   }
/*     */   
/*     */   public boolean isDefinedAt(int x) {
/*  84 */     return LinearSeqOptimized.class.isDefinedAt(this, x);
/*     */   }
/*     */   
/*     */   public int segmentLength(Function1 p, int from) {
/*  84 */     return LinearSeqOptimized.class.segmentLength(this, p, from);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p, int from) {
/*  84 */     return LinearSeqOptimized.class.indexWhere(this, p, from);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p, int end) {
/*  84 */     return LinearSeqOptimized.class.lastIndexWhere(this, p, end);
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  84 */     return Product.class.productIterator(this);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  84 */     return Product.class.productPrefix(this);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> seq() {
/*  84 */     return LinearSeq$class.seq(this);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> thisCollection() {
/*  84 */     return LinearSeqLike.class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> toCollection(LinearSeqLike repr) {
/*  84 */     return LinearSeqLike.class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  84 */     return LinearSeqLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/*  84 */     return LinearSeqLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public final <B> boolean corresponds(GenSeq that, Function2 p) {
/*  84 */     return LinearSeqLike.class.corresponds(this, that, p);
/*     */   }
/*     */   
/*     */   public Seq<A> toSeq() {
/*  84 */     return Seq$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public Combiner<A, ParSeq<A>> parCombiner() {
/*  84 */     return Seq$class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public List() {
/*  84 */     Traversable$class.$init$(this);
/*  84 */     Iterable$class.$init$(this);
/*  84 */     Seq$class.$init$(this);
/*  84 */     LinearSeqLike.class.$init$(this);
/*  84 */     LinearSeq.class.$init$(this);
/*  84 */     LinearSeq$class.$init$(this);
/*  84 */     Product.class.$init$(this);
/*  84 */     LinearSeqOptimized.class.$init$(this);
/*     */   }
/*     */   
/*     */   public GenericCompanion<List> companion() {
/*  89 */     return (GenericCompanion<List>)List$.MODULE$;
/*     */   }
/*     */   
/*     */   public <B> List<B> $colon$colon(Object x) {
/* 111 */     return new $colon$colon<B>((B)x, this);
/*     */   }
/*     */   
/*     */   public <B> List<B> $colon$colon$colon(List<B> prefix) {
/* 125 */     return isEmpty() ? prefix : (
/* 126 */       prefix.isEmpty() ? this : (
/* 127 */       new ListBuffer()).$plus$plus$eq((TraversableOnce)prefix).prependToList(this));
/*     */   }
/*     */   
/*     */   public <B> List<B> reverse_$colon$colon$colon(List<Object> prefix) {
/* 140 */     List<B> these = this;
/* 141 */     List<Object> pres = prefix;
/*     */     while (true) {
/* 142 */       if (pres.isEmpty())
/* 146 */         return these; 
/*     */       B b = (B)pres.head();
/*     */       these = these.$colon$colon(b);
/*     */       pres = (List<Object>)pres.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   private final List loop$1(ListBuffer mapped, List unchanged, List pending, Function1 f$1) {
/*     */     while (true) {
/* 164 */       if (pending.isEmpty())
/* 165 */         return (mapped == null) ? unchanged : 
/* 166 */           mapped.prependToList(unchanged); 
/* 169 */       Object head0 = pending.head();
/* 170 */       Object head1 = f$1.apply(head0);
/* 172 */       if (head1 == head0) {
/* 173 */         pending = (List)pending.tail();
/* 173 */         unchanged = unchanged;
/* 173 */         mapped = mapped;
/*     */         continue;
/*     */       } 
/* 175 */       ListBuffer b = (mapped == null) ? new ListBuffer() : mapped;
/* 176 */       List xc = unchanged;
/* 177 */       while (xc != pending) {
/* 178 */         b.$plus$eq(xc.head());
/* 179 */         xc = (List)xc.tail();
/*     */       } 
/* 181 */       b.$plus$eq(head1);
/* 182 */       List tail0 = (List)pending.tail();
/* 183 */       pending = tail0;
/* 183 */       unchanged = tail0;
/* 183 */       mapped = b;
/*     */     } 
/*     */   }
/*     */   
/*     */   public <B> List<B> mapConserve(Function1 f) {
/* 186 */     return loop$1((ListBuffer)null, this, this, f);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/* 192 */     Builder b = bf.apply(this);
/* 193 */     return (b instanceof ListBuffer) ? (That)that.seq().toList().$colon$colon$colon(this) : 
/* 194 */       (That)TraversableLike.class.$plus$plus(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/*     */     Object object;
/* 197 */     if (bf instanceof GenTraversableFactory.GenericCanBuildFrom) {
/* 197 */       object = $colon$colon(elem);
/*     */     } else {
/* 199 */       object = SeqLike.class.$plus$colon(this, elem, bf);
/*     */     } 
/*     */     return (That)object;
/*     */   }
/*     */   
/*     */   public List<A> toList() {
/* 202 */     return this;
/*     */   }
/*     */   
/*     */   public List<A> take(int n) {
/* 205 */     ListBuffer b = new ListBuffer();
/* 206 */     int i = 0;
/* 207 */     List these = this;
/* 208 */     while (!these.isEmpty() && i < n) {
/* 209 */       i++;
/* 210 */       b.$plus$eq(these.head());
/* 211 */       these = (List)these.tail();
/*     */     } 
/* 213 */     return these.isEmpty() ? this : 
/* 214 */       b.toList();
/*     */   }
/*     */   
/*     */   public List<A> drop(int n) {
/* 218 */     List<A> these = this;
/* 219 */     int count = n;
/* 220 */     while (!these.isEmpty() && count > 0) {
/* 221 */       these = (List)these.tail();
/* 222 */       count--;
/*     */     } 
/* 224 */     return these;
/*     */   }
/*     */   
/*     */   public List<A> slice(int from, int until) {
/* 238 */     int lo = package$.MODULE$.max(from, 0);
/* 239 */     return (until <= lo || isEmpty()) ? Nil$.MODULE$ : 
/* 240 */       drop(lo).take(until - lo);
/*     */   }
/*     */   
/*     */   private final List loop$2(List lead, List lag) {
/*     */     // Byte code:
/*     */     //   0: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   3: dup
/*     */     //   4: ifnonnull -> 15
/*     */     //   7: pop
/*     */     //   8: aload_1
/*     */     //   9: ifnull -> 22
/*     */     //   12: goto -> 24
/*     */     //   15: aload_1
/*     */     //   16: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   19: ifeq -> 24
/*     */     //   22: aload_2
/*     */     //   23: areturn
/*     */     //   24: aload_1
/*     */     //   25: instanceof scala/collection/immutable/$colon$colon
/*     */     //   28: ifeq -> 52
/*     */     //   31: aload_1
/*     */     //   32: checkcast scala/collection/immutable/$colon$colon
/*     */     //   35: astore_3
/*     */     //   36: aload_3
/*     */     //   37: invokevirtual tl$1 : ()Lscala/collection/immutable/List;
/*     */     //   40: aload_2
/*     */     //   41: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   44: checkcast scala/collection/immutable/List
/*     */     //   47: astore_2
/*     */     //   48: astore_1
/*     */     //   49: goto -> 0
/*     */     //   52: new scala/MatchError
/*     */     //   55: dup
/*     */     //   56: aload_1
/*     */     //   57: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   60: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #246	-> 0
/*     */     //   #245	-> 0
/*     */     //   #245	-> 22
/*     */     //   #247	-> 24
/*     */     //   #245	-> 36
/*     */     //   #247	-> 37
/*     */     //   #245	-> 52
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	61	0	this	Lscala/collection/immutable/List;
/*     */     //   0	61	1	lead	Lscala/collection/immutable/List;
/*     */     //   0	61	2	lag	Lscala/collection/immutable/List;
/*     */   }
/*     */   
/*     */   public List<A> takeRight(int n) {
/* 249 */     return loop$2(drop(n), this);
/*     */   }
/*     */   
/*     */   public Tuple2<List<A>, List<A>> splitAt(int n) {
/* 255 */     ListBuffer b = new ListBuffer();
/* 256 */     int i = 0;
/* 257 */     List these = this;
/* 258 */     while (!these.isEmpty() && i < n) {
/* 259 */       i++;
/* 260 */       b.$plus$eq(these.head());
/* 261 */       these = (List)these.tail();
/*     */     } 
/* 263 */     return new Tuple2(b.toList(), these);
/*     */   }
/*     */   
/*     */   public List<A> takeWhile(Function1 p) {
/* 267 */     ListBuffer b = new ListBuffer();
/* 268 */     List these = this;
/* 269 */     while (!these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
/* 270 */       b.$plus$eq(these.head());
/* 271 */       these = (List)these.tail();
/*     */     } 
/* 273 */     return b.toList();
/*     */   }
/*     */   
/*     */   private final List loop$3(List xs, Function1 p$1) {
/* 279 */     while (!xs.isEmpty() && BoxesRunTime.unboxToBoolean(p$1.apply(xs.head())))
/* 280 */       xs = (List)xs.tail(); 
/*     */     return xs;
/*     */   }
/*     */   
/*     */   public List<A> dropWhile(Function1 p) {
/* 282 */     return loop$3(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<List<A>, List<A>> span(Function1 p) {
/* 286 */     ListBuffer b = new ListBuffer();
/* 287 */     List these = this;
/* 288 */     while (!these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
/* 289 */       b.$plus$eq(these.head());
/* 290 */       these = (List)these.tail();
/*     */     } 
/* 292 */     return new Tuple2(b.toList(), these);
/*     */   }
/*     */   
/*     */   public List<A> reverse() {
/* 296 */     List<A> result = Nil$.MODULE$;
/* 297 */     List<Object> these = this;
/*     */     while (true) {
/* 298 */       if (these.isEmpty())
/* 302 */         return result; 
/*     */       A a = (A)these.head();
/*     */       result = result.$colon$colon(a);
/*     */       these = (List<Object>)these.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/* 306 */     return reverse().foldLeft((B)z, (Function2<B, A, B>)new List$$anonfun$foldRight$1(this, (List<A>)op));
/*     */   }
/*     */   
/*     */   public class List$$anonfun$foldRight$1 extends AbstractFunction2<B, A, B> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 op$1;
/*     */     
/*     */     public final B apply(Object right, Object left) {
/* 306 */       return (B)this.op$1.apply(left, right);
/*     */     }
/*     */     
/*     */     public List$$anonfun$foldRight$1(List $outer, Function2 op$1) {}
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/* 308 */     return "List";
/*     */   }
/*     */   
/*     */   public Stream<A> toStream() {
/* 311 */     return isEmpty() ? Stream.Empty$.MODULE$ : 
/* 312 */       new Stream.Cons<A>(head(), (Function0<Stream<A>>)new List$$anonfun$toStream$1(this));
/*     */   }
/*     */   
/*     */   public class List$$anonfun$toStream$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Stream<A> apply() {
/* 312 */       return ((List<A>)this.$outer.tail()).toStream();
/*     */     }
/*     */     
/*     */     public List$$anonfun$toStream$1(List $outer) {}
/*     */   }
/*     */   
/*     */   public final <B> void foreach(Function1 f) {
/* 316 */     List these = this;
/*     */     while (true) {
/* 317 */       if (these.isEmpty())
/*     */         return; 
/* 318 */       f.apply(these.head());
/* 319 */       these = (List)these.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<A> removeDuplicates() {
/* 324 */     return (List<A>)distinct();
/*     */   }
/*     */   
/*     */   public static <A, B> boolean exists2(List<A> paramList, List<B> paramList1, Function2<A, B, Object> paramFunction2) {
/*     */     return List$.MODULE$.exists2(paramList, paramList1, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A, B> boolean forall2(List<A> paramList, List<B> paramList1, Function2<A, B, Object> paramFunction2) {
/*     */     return List$.MODULE$.forall2(paramList, paramList1, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A, B, C> List<C> map2(List<A> paramList, List<B> paramList1, Function2<A, B, C> paramFunction2) {
/*     */     return List$.MODULE$.map2(paramList, paramList1, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> List<A> fromArray(Object paramObject, int paramInt1, int paramInt2) {
/*     */     return List$.MODULE$.fromArray(paramObject, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */   public static <A> List<A> fromArray(Object paramObject) {
/*     */     return List$.MODULE$.fromArray(paramObject);
/*     */   }
/*     */   
/*     */   public static <A> List<A> fromIterator(Iterator<A> paramIterator) {
/*     */     return List$.MODULE$.fromIterator(paramIterator);
/*     */   }
/*     */   
/*     */   public static <A, B> Tuple2<List<A>, List<B>> separate(Iterable<Either<A, B>> paramIterable) {
/*     */     return List$.MODULE$.separate(paramIterable);
/*     */   }
/*     */   
/*     */   public static <A, B> List<B> rights(Iterable<Either<A, B>> paramIterable) {
/*     */     return List$.MODULE$.rights(paramIterable);
/*     */   }
/*     */   
/*     */   public static <A, B> List<A> lefts(Iterable<Either<A, B>> paramIterable) {
/*     */     return List$.MODULE$.lefts(paramIterable);
/*     */   }
/*     */   
/*     */   public static <A> List<A> make(int paramInt, A paramA) {
/*     */     return List$.MODULE$.make(paramInt, paramA);
/*     */   }
/*     */   
/*     */   public static List<Object> range(int paramInt1, int paramInt2, Function1<Object, Object> paramFunction1) {
/*     */     return List$.MODULE$.range(paramInt1, paramInt2, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> List<A> empty() {
/*     */     return List$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<List<?>, A, List<A>> canBuildFrom() {
/*     */     return List$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Some<List<A>> unapplySeq(List<A> paramList) {
/*     */     return List$.MODULE$.unapplySeq(paramList);
/*     */   }
/*     */   
/*     */   public static <A> List<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*     */     return (List<A>)List$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> List<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return (List<T>)List$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <T> List<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*     */     return (List<T>)List$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <A> List<List<List<List<List<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*     */     return (List<List<List<List<List<A>>>>>)List$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*     */   }
/*     */   
/*     */   public static <A> List<List<List<List<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*     */     return (List<List<List<List<A>>>>)List$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*     */   }
/*     */   
/*     */   public static <A> List<List<List<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*     */     return (List<List<List<A>>>)List$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*     */   }
/*     */   
/*     */   public static <A> List<List<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*     */     return (List<List<A>>)List$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> List<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*     */     return (List<A>)List$.MODULE$.tabulate(paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> List<List<List<List<List<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*     */     return (List<List<List<List<List<A>>>>>)List$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> List<List<List<List<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*     */     return (List<List<List<List<A>>>>)List$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> List<List<List<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*     */     return (List<List<List<A>>>)List$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> List<List<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*     */     return (List<List<A>>)List$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> List<A> fill(int paramInt, Function0<A> paramFunction0) {
/*     */     return (List<A>)List$.MODULE$.fill(paramInt, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> List<A> concat(Seq<Traversable<A>> paramSeq) {
/*     */     return (List<A>)List$.MODULE$.concat(paramSeq);
/*     */   }
/*     */   
/*     */   public static GenTraversableFactory<List>.GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*     */     return List$.MODULE$.ReusableCBF();
/*     */   }
/*     */   
/*     */   public static <A> List<A> empty() {
/*     */     return (List<A>)List$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public abstract boolean isEmpty();
/*     */   
/*     */   public abstract A head();
/*     */   
/*     */   public static class List$$anonfun$unzip$1 extends AbstractFunction2<Tuple2<A, B>, Tuple2<List<A>, List<B>>, Tuple2<List<A>, List<B>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<List<A>, List<B>> apply(Tuple2 x0$1, Tuple2 x1$1) {
/* 489 */       Tuple2 tuple2 = new Tuple2(x0$1, x1$1);
/* 489 */       if (tuple2 != null && tuple2._1() != null && tuple2._2() != null) {
/* 490 */         Object object1 = ((Tuple2)tuple2._1())._1(), object2 = ((Tuple2)tuple2._1())._2();
/* 490 */         return new Tuple2(((List)((Tuple2)tuple2._2())._1()).$colon$colon(object1), ((List)((Tuple2)tuple2._2())._2()).$colon$colon(object2));
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class List$$anonfun$lefts$1 extends AbstractFunction2<Either<A, B>, List<A>, List<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<A> apply(Either e, List<A> as) {
/* 498 */       if (e instanceof Left) {
/* 498 */         Left left = (Left)e;
/* 499 */         Object object = left.a();
/* 499 */         List list = as.$colon$colon(object);
/*     */       } else {
/* 500 */         if (e instanceof Right)
/* 500 */           return as; 
/*     */         throw new MatchError(e);
/*     */       } 
/*     */       return (List<A>)SYNTHETIC_LOCAL_VARIABLE_5;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class List$$anonfun$rights$1 extends AbstractFunction2<Either<A, B>, List<B>, List<B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<B> apply(Either e, List bs) {
/*     */       List<B> list;
/* 508 */       if (e instanceof Left) {
/* 508 */         list = bs;
/*     */       } else {
/* 510 */         if (e instanceof Right) {
/* 510 */           Right right = (Right)e;
/* 510 */           Object object = right.b();
/* 510 */           list = bs.$colon$colon(object);
/*     */         } 
/*     */         throw new MatchError(e);
/*     */       } 
/*     */       return list;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class List$$anonfun$separate$1 extends AbstractFunction2<Either<A, B>, Tuple2<List<A>, List<B>>, Tuple2<List<A>, List<B>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<List<A>, List<B>> apply(Either x0$2, Tuple2 x1$2) {
/* 520 */       Tuple2 tuple2 = new Tuple2(x0$2, x1$2);
/* 520 */       if (tuple2 != null && tuple2
/* 521 */         ._1() instanceof Left) {
/* 521 */         Left left = (Left)tuple2._1();
/* 521 */         if (tuple2._2() != null) {
/* 521 */           Object object = left.a();
/* 521 */           return new Tuple2(((List)((Tuple2)tuple2._2())._1()).$colon$colon(object), ((Tuple2)tuple2._2())._2());
/*     */         } 
/*     */       } 
/* 522 */       if (tuple2 != null && tuple2._1() instanceof Right) {
/* 522 */         Right right = (Right)tuple2._1();
/* 522 */         if (tuple2._2() != null);
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class List$$anonfun$transpose$1 extends AbstractFunction1<List<A>, A> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final A apply(List<A> x$12) {
/* 638 */       return x$12.head();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class List$$anonfun$transpose$2 extends AbstractFunction1<List<A>, List<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<A> apply(List x$13) {
/* 639 */       return (List<A>)x$13.tail();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\List.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */