/*     */ package scala.math;
/*     */ 
/*     */ import java.math.MathContext;
/*     */ import scala.Enumeration;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.immutable.NumericRange;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021\005s!B\001\003\021\0039\021A\003\"jO\022+7-[7bY*\0211\001B\001\005[\006$\bNC\001\006\003\025\0318-\0317b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021!BQ5h\t\026\034\027.\\1m'\rIA\002\005\t\003\0339i\021\001B\005\003\037\021\021a!\0218z%\0264\007CA\007\022\023\t\021BA\001\007TKJL\027\r\\5{C\ndW\rC\003\025\023\021\005Q#\001\004=S:LGO\020\013\002\017!9q#\003b\001\n\023A\022!C7j]\016\0137\r[3e+\005I\002CA\007\033\023\tYBAA\002J]RDa!H\005!\002\023I\022AC7j]\016\0137\r[3eA!9q$\003b\001\n\023A\022!C7bq\016\0137\r[3e\021\031\t\023\002)A\0053\005QQ.\031=DC\016DW\r\032\021\t\017\rJ!\031!C\001I\005\021B-\0324bk2$X*\031;i\007>tG/\032=u+\005)\003C\001\024+\033\0059#BA\002)\025\005I\023\001\0026bm\006L!aK\024\003\0275\013G\017[\"p]R,\007\020\036\005\007[%\001\013\021B\023\002'\021,g-Y;mi6\013G\017[\"p]R,\007\020\036\021\t\017=J!\031!C\001a\0059Q*\0338M_:<W#A\031\021\005!\021d\001\002\006\003\001M\032BA\r\0338!A\021\001\"N\005\003m\t\0211bU2bY\006tU/\0342feB\021\001\002O\005\003s\t\021qcU2bY\006tU/\\3sS\016\034uN\034<feNLwN\\:\t\021m\022$Q1A\005\002q\n!BY5h\t\026\034\027.\\1m+\005i\004C\001\024?\023\tQq\005\003\005Ae\t\005\t\025!\003>\003-\021\027n\032#fG&l\027\r\034\021\t\021\t\023$Q1A\005\002\021\n!!\\2\t\021\021\023$\021!Q\001\n\025\n1!\\2!\021\025!\"\007\"\001G)\r\tt\t\023\005\006w\025\003\r!\020\005\006\005\026\003\r!\n\005\006)I\"\tA\023\013\003c-CQaO%A\002uBQ!\024\032\005\f9\013\021CY5hI\026\034'GQ5h\t\026\034\027.\\1m)\t\tt\nC\003Q\031\002\007Q(A\001y\021\025\021&\007\"\021T\003!A\027m\0355D_\022,G#A\r\t\013U\023D\021\t,\002\r\025\fX/\0317t)\t9&\f\005\002\0161&\021\021\f\002\002\b\005>|G.Z1o\021\025YF\0131\001]\003\021!\b.\031;\021\0055i\026B\0010\005\005\r\te.\037\005\006AJ\"\t%Y\001\fSN4\026\r\\5e\005f$X-F\001X\021\025\031'\007\"\021b\0031I7OV1mS\022\034\006n\034:u\021\025)'\007\"\021b\003-I7OV1mS\022\034\005.\031:\t\013\035\024D\021I1\002\025%\034h+\0317jI&sG\017C\003je\021\005\021-A\006jgZ\013G.\0333M_:<\007\"B63\t\003\t\027\001D5t-\006d\027\016\032$m_\006$\b\"B73\t\003\t\027!D5t-\006d\027\016\032#pk\ndW\rC\003pe\021%\001/A\013o_\006\023\030\016\0365nKRL7-\022=dKB$\030n\0348\025\005]\013\bB\002:o\t\003\0071/\001\003c_\022L\bcA\007um&\021Q\017\002\002\ty\tLh.Y7f}A\021Qb^\005\003q\022\021A!\0268ji\")!P\rC\001w\0069\021n],i_2,G#A,\t\013u\024D\021\001@\002\025UtG-\032:ms&tw\rF\001>\021\031)&\007\"\001\002\002Q\031q+a\001\t\013m{\b\031A\031\t\017\005\035!\007\"\001\002\n\00591m\\7qCJ,GcA\r\002\f!11,!\002A\002EBq!a\0043\t\003\t\t\"\001\005%Y\026\0348\017J3r)\r9\0261\003\005\0077\0065\001\031A\031\t\017\005]!\007\"\001\002\032\005YAe\032:fCR,'\017J3r)\r9\0261\004\005\0077\006U\001\031A\031\t\017\005}!\007\"\001\002\"\005)A\005\\3tgR\031q+a\t\t\rm\013i\0021\0012\021\035\t9C\rC\001\003S\t\001\002J4sK\006$XM\035\013\004/\006-\002BB.\002&\001\007\021\007C\004\0020I\"\t!!\r\002\013\021\002H.^:\025\007E\n\031\004\003\004\\\003[\001\r!\r\005\b\003o\021D\021AA\035\003\031!S.\0338vgR\031\021'a\017\t\rm\013)\0041\0012\021\035\tyD\rC\001\003\003\na\001\n;j[\026\034HcA\031\002D!11,!\020A\002EBq!a\0223\t\003\tI%\001\003%I&4HcA\031\002L!11,!\022A\002EBq!a\0243\t\003\t\t&\001\007%I&4H\005]3sG\026tG\017\006\003\002T\005e\003#B\007\002VE\n\024bAA,\t\t1A+\0369mKJBaaWA'\001\004\t\004bBA/e\021\005\021qL\001\005cV|G\017F\0022\003CBaaWA.\001\004\t\004bBA3e\021\005\021qM\001\004[&tGcA\031\002j!11,a\031A\002EBq!!\0343\t\003\ty'A\002nCb$2!MA9\021\031Y\0261\016a\001c!9\021Q\017\032\005\002\005]\024!\003:f[\006Lg\016Z3s)\r\t\024\021\020\005\0077\006M\004\031A\031\t\017\005u$\007\"\001\002\000\005AA\005]3sG\026tG\017F\0022\003\003CaaWA>\001\004\t\004bBACe\021\005\021qQ\001\004a><HcA\031\002\n\"9\0211RAB\001\004I\022!\0018\t\r\005=%\007\"\0011\0031)h.\031:z?\022j\027N\\;t\021\031\t\031J\rC\001a\005\031\021MY:\t\r\005]%\007\"\001\031\003\031\031\030n\0328v[\"1\0211\024\032\005\002a\t\021\002\035:fG&\034\030n\0348\t\017\005}%\007\"\001\002\"\006)!o\\;oIR\031\021'a)\t\r\t\013i\n1\001&\021\031\t9K\rC\0011\005)1oY1mK\"1\0211\026\032\005\002A\n1!\0367q\021\035\tyK\rC\001\003c\013Q!\0319qYf$2!MAZ\021\031\021\025Q\026a\001K!9\021q\027\032\005\002\005e\026\001C:fiN\033\027\r\\3\025\007E\nY\fC\004\002(\006U\006\031A\r\t\017\005]&\007\"\001\002@R)\021'!1\002D\"9\021qUA_\001\004I\002\002CAc\003{\003\r!a2\002\t5|G-\032\t\005\003\023\f)O\004\003\002L\0065gB\001\005\001\017\035\ty-\003E\001\003#\fABU8v]\022LgnZ'pI\026\004B!a5\002V6\t\021BB\004\002X&A\t!!7\003\031I{WO\0343j]\036lu\016Z3\024\t\005U\0271\034\t\004\033\005u\027bAAp\t\tYQI\\;nKJ\fG/[8o\021\035!\022Q\033C\001\003G$\"!!5\006\017\005]\027Q\033\001\002hB!\021\021^Av\033\t\t).\003\003\002n\006u'!\002,bYV,\007BCAy\003+\024\r\021\"\001\002t\006\021Q\013U\013\003\003OD\021\"a>\002V\002\006I!a:\002\007U\003\006\005\003\006\002|\006U'\031!C\001\003g\fA\001R(X\035\"I\021q`AkA\003%\021q]\001\006\t>;f\n\t\005\013\005\007\t)N1A\005\002\005M\030aB\"F\0232Kej\022\005\n\005\017\t)\016)A\005\003O\f\001bQ#J\031&su\t\t\005\013\005\027\t)N1A\005\002\005M\030!\002$M\037>\023\006\"\003B\b\003+\004\013\021BAt\003\0311EjT(SA!Q!1CAk\005\004%\t!a=\002\017!\013EJR0V!\"I!qCAkA\003%\021q]\001\t\021\006ceiX+QA!Q!1DAk\005\004%\t!a=\002\023!\013EJR0E\037^s\005\"\003B\020\003+\004\013\021BAt\003)A\025\t\024$`\t>;f\n\t\005\013\005G\t)N1A\005\002\005M\030!\003%B\031\032{VIV#O\021%\0219#!6!\002\023\t9/\001\006I\00323u,\022,F\035\002B!Ba\013\002V\n\007I\021AAz\003-)fJT#D\013N\033\026IU-\t\023\t=\022Q\033Q\001\n\005\035\030\001D+O\035\026\033UiU*B%f\003\003b\002B\032e\021\005#QG\001\nEf$XMV1mk\026$\"Aa\016\021\0075\021I$C\002\003<\021\021AAQ=uK\"9!q\b\032\005B\t\005\023AC:i_J$h+\0317vKR\021!1\t\t\004\033\t\025\023b\001B$\t\t)1\013[8si\"9!1\n\032\005\002\t5\023!C2iCJ4\026\r\\;f+\t\021y\005E\002\016\005#J1Aa\025\005\005\021\031\005.\031:\t\r\t]#\007\"\001T\003!Ig\016\036,bYV,\007b\002B.e\021\005!QL\001\nY>twMV1mk\026$\"Aa\030\021\0075\021\t'C\002\003d\021\021A\001T8oO\"9!q\r\032\005\002\t%\024A\0034m_\006$h+\0317vKR\021!1\016\t\004\033\t5\024b\001B8\t\t)a\t\\8bi\"9!1\017\032\005\002\tU\024a\0033pk\ndWMV1mk\026$\"Aa\036\021\0075\021I(C\002\003|\021\021a\001R8vE2,\007b\002B@e\021\005!\021Q\001\fi>\024\025\020^3Fq\006\034G/\006\002\0038!9!Q\021\032\005\002\t\035\025\001\004;p'\"|'\017^#yC\016$XC\001B\"\021\031\021YI\rC\0011\005QAo\\%oi\026C\030m\031;\t\017\t=%\007\"\001\003\022\006YAo\034'p]\036,\0050Y2u+\t\021y\006C\004\003\026J\"\tAa&\002\013UtG/\0337\025\t\te%\021\033\t\b\0057\023\031,\rBa\035\021\021iJ!,\017\t\t}%\021\026\b\005\005C\0239+\004\002\003$*\031!Q\025\004\002\rq\022xn\034;?\023\005)\021b\001BV\t\0059\001/Y2lC\036,\027\002\002BX\005c\013QAU1oO\026T1Aa+\005\023\021\021)La.\003\017A\013'\017^5bY*!!q\026B]\025\021\021YL!0\002\023%lW.\036;bE2,'b\001B`\t\005Q1m\0347mK\016$\030n\0348\021\013\t\r'1Z\031\017\t\t\025'qY\007\003\005sKAA!3\003:\006aa*^7fe&\034'+\0318hK&!!Q\032Bh\005%)\005p\0317vg&4XM\003\003\003J\ne\006b\002Bj\005'\003\r!M\001\004K:$\007b\002BKe\021\005!q\033\013\007\0053\024yN!9\021\r\t\r'1\032Bn!\021\021iJ!8\n\007)\021\t\fC\004\003T\nU\007\031A\031\t\017\t\r(Q\033a\001c\005!1\017^3q\021\035\0219O\rC\001\005S\f!\001^8\025\t\t-(1\037\t\b\0057\023\031,\rBw!\025\021\031Ma<2\023\021\021\tPa4\003\023%s7\r\\;tSZ,\007b\002Bj\005K\004\r!\r\005\b\005O\024D\021\001B|)\031\021IPa?\003~B1!1\031Bx\0057DqAa5\003v\002\007\021\007C\004\003d\nU\b\031A\031\t\017\r\005!\007\"\001\004\004\005AAo\034\"jO&sG\017\006\002\004\006A\031\001ba\002\n\007\r%!A\001\004CS\036Le\016\036\005\b\007\033\021D\021AB\b\0035!xNQ5h\023:$X\t_1diR\0211\021\003\t\006\033\rM1QA\005\004\007+!!AB(qi&|g\016C\004\004\032I\"\tea\007\002\021Q|7\013\036:j]\036$\"a!\b\021\t\r}1Q\005\b\004\033\r\005\022bAB\022\t\0051\001K]3eK\032LAaa\n\004*\t11\013\036:j]\036T1aa\t\005Q\035\0214QFB\032\007o\0012!DB\030\023\r\031\t\004\002\002\026I\026\004(/Z2bi\026$\027J\0345fe&$\030M\\2fC\t\031)$\001\020UQ&\034\be\0317bgN\004s/\0337mA\t,\007%\\1eK\0022\027N\\1m]\005\0221\021H\001\007e9\n\004G\f\031)\0179\032ida\021\004HA\031Qba\020\n\007\r\005CA\001\006eKB\024XmY1uK\022\f#a!\022\002#U\033X\r\t'p]\036tS*\0338WC2,X-\t\002\004J\005)!GL\035/a!91QJ\005!\002\023\t\024\001C'j]2{gn\032\021\t\021\rE\023B1A\005\002A\nq!T1y\031>tw\r\013\005\004P\ru2QKB$C\t\0319&A\tVg\026\004Cj\0348h]5\013\007PV1mk\026Dqaa\027\nA\003%\021'\001\005NCbduN\\4!\021)\031y&\003EC\002\023%1\021M\001\006G\006\034\007.Z\013\003\007G\002B!DB3c%\0311q\r\003\003\013\005\023(/Y=\t\025\r-\024\002#A!B\023\031\031'\001\004dC\016DW\r\t\005\b\007_JA\021AB9\003\0351\030\r\\;f\037\032$2!MB:\021!\031)h!\034A\002\t]\024!\0013\t\017\r=\024\002\"\001\004zQ)\021ga\037\004~!A1QOB<\001\004\0219\b\003\004C\007o\002\r!\n\005\b\003_KA\021ABA)\r\t41\021\005\b\007\013\033y\b1\001\032\003\005I\007bBAX\023\021\0051\021\022\013\006c\r-5Q\022\005\b\007\013\0339\t1\001\032\021\031\0215q\021a\001K!9\021qV\005\005\002\rEEcA\031\004\024\"A1QSBH\001\004\021y&A\001m\021\035\ty+\003C\001\0073#R!MBN\007;C\001b!&\004\030\002\007!q\f\005\007\005\016]\005\031A\023\t\017\005=\026\002\"\001\004\"R)\021ga)\004(\"A1QUBP\001\004\021y&A\006v]N\034\027\r\\3e-\006d\007bBAT\007?\003\r!\007\005\b\003_KA\021ABV)\035\t4QVBX\007cC\001b!*\004*\002\007!q\f\005\b\003O\033I\0131\001\032\021\031\0215\021\026a\001K!9\021qV\005\005\002\rUFcA\031\0048\"A1QOBZ\001\004\0219\bC\004\0020&!\taa/\025\013E\032ila0\t\021\rU4\021\030a\001\005oBaAQB]\001\004)\003bBAX\023\021\00511\031\013\004c\r\025\007b\002)\004B\002\0071q\031\t\006\033\r\025$q\n\005\b\003_KA\021ABf)\025\t4QZBh\021\035\0016\021\032a\001\007\017DaAQBe\001\004)\003bBAX\023\021\00511\033\013\004c\rU\007b\002)\004R\002\0071Q\004\005\b\003_KA\021ABm)\025\t41\\Bo\021\035\0016q\033a\001\007;AaAQBl\001\004)\003bBAX\023\021\0051\021\035\013\004c\r\r\bb\002)\004`\002\0071Q\001\005\b\003_KA\021ABt)\025\t4\021^Bv\021\035\0016Q\035a\001\007\013AaAQBs\001\004)\003bBAX\023\021\0051q\036\013\006c\rE81\037\005\t\007K\033i\0171\001\004\006!9\021qUBw\001\004I\002bBAX\023\021\0051q\037\013\bc\re81`B\021!\031)k!>A\002\r\025\001bBAT\007k\004\r!\007\005\007\005\016U\b\031A\023\t\017\005=\026\002\"\001\005\002Q\031\021\007b\001\t\017\021\0251q a\001{\005\021!\r\032\005\b\003_KA\021\001C\005)\025\tD1\002C\007\021\035!)\001b\002A\002uBaA\021C\004\001\004)\003b\002C\t\023\021\rA1C\001\017S:$(GY5h\t\026\034\027.\\1m)\r\tDQ\003\005\b\007\013#y\0011\001\032\021\035!I\"\003C\002\t7\tq\002\\8oOJ\022\027n\032#fG&l\027\r\034\013\004c\021u\001\002CBK\t/\001\rAa\030\t\017\021\005\022\002b\001\005$\005\tBm\\;cY\026\024$-[4EK\016LW.\0317\025\007E\")\003\003\005\004v\021}\001\031\001B<\021\035!I#\003C\002\tW\t\021D[1wC\nKw\rR3dS6\fGN\r2jO\022+7-[7bYR\031\021\007\"\f\t\rA#9\0031\001>\021%!\t$CA\001\n\023!\031$A\006sK\006$'+Z:pYZ,GC\001C\033!\021!9\004\"\020\016\005\021e\"b\001C\036Q\005!A.\0318h\023\021!y\004\"\017\003\r=\023'.Z2u\001")
/*     */ public class BigDecimal extends ScalaNumber implements ScalaNumericConversions, Serializable {
/*     */   private final java.math.BigDecimal bigDecimal;
/*     */   
/*     */   private final MathContext mc;
/*     */   
/*     */   public static class RoundingMode$ extends Enumeration {
/*     */     public static final RoundingMode$ MODULE$;
/*     */     
/*     */     private final Enumeration.Value UP;
/*     */     
/*     */     private final Enumeration.Value DOWN;
/*     */     
/*     */     private final Enumeration.Value CEILING;
/*     */     
/*     */     private final Enumeration.Value FLOOR;
/*     */     
/*     */     private final Enumeration.Value HALF_UP;
/*     */     
/*     */     private final Enumeration.Value HALF_DOWN;
/*     */     
/*     */     private final Enumeration.Value HALF_EVEN;
/*     */     
/*     */     private final Enumeration.Value UNNECESSARY;
/*     */     
/*     */     public RoundingMode$() {
/*  37 */       MODULE$ = this;
/*  41 */       this.UP = Value();
/*  41 */       this.DOWN = Value();
/*  41 */       this.CEILING = Value();
/*  41 */       this.FLOOR = Value();
/*  41 */       this.HALF_UP = Value();
/*  41 */       this.HALF_DOWN = Value();
/*  41 */       this.HALF_EVEN = Value();
/*  41 */       this.UNNECESSARY = Value();
/*     */     }
/*     */     
/*     */     public Enumeration.Value UNNECESSARY() {
/*  41 */       return this.UNNECESSARY;
/*     */     }
/*     */     
/*     */     public Enumeration.Value HALF_EVEN() {
/*  41 */       return this.HALF_EVEN;
/*     */     }
/*     */     
/*     */     public Enumeration.Value HALF_DOWN() {
/*  41 */       return this.HALF_DOWN;
/*     */     }
/*     */     
/*     */     public Enumeration.Value HALF_UP() {
/*  41 */       return this.HALF_UP;
/*     */     }
/*     */     
/*     */     public Enumeration.Value FLOOR() {
/*  41 */       return this.FLOOR;
/*     */     }
/*     */     
/*     */     public Enumeration.Value CEILING() {
/*  41 */       return this.CEILING;
/*     */     }
/*     */     
/*     */     public Enumeration.Value DOWN() {
/*  41 */       return this.DOWN;
/*     */     }
/*     */     
/*     */     public Enumeration.Value UP() {
/*  41 */       return this.UP;
/*     */     }
/*     */   }
/*     */   
/*     */   public char toChar() {
/* 163 */     return ScalaNumericAnyConversions$class.toChar(this);
/*     */   }
/*     */   
/*     */   public byte toByte() {
/* 163 */     return ScalaNumericAnyConversions$class.toByte(this);
/*     */   }
/*     */   
/*     */   public short toShort() {
/* 163 */     return ScalaNumericAnyConversions$class.toShort(this);
/*     */   }
/*     */   
/*     */   public int toInt() {
/* 163 */     return ScalaNumericAnyConversions$class.toInt(this);
/*     */   }
/*     */   
/*     */   public long toLong() {
/* 163 */     return ScalaNumericAnyConversions$class.toLong(this);
/*     */   }
/*     */   
/*     */   public float toFloat() {
/* 163 */     return ScalaNumericAnyConversions$class.toFloat(this);
/*     */   }
/*     */   
/*     */   public double toDouble() {
/* 163 */     return ScalaNumericAnyConversions$class.toDouble(this);
/*     */   }
/*     */   
/*     */   public int unifiedPrimitiveHashcode() {
/* 163 */     return ScalaNumericAnyConversions$class.unifiedPrimitiveHashcode(this);
/*     */   }
/*     */   
/*     */   public boolean unifiedPrimitiveEquals(Object x) {
/* 163 */     return ScalaNumericAnyConversions$class.unifiedPrimitiveEquals(this, x);
/*     */   }
/*     */   
/*     */   public java.math.BigDecimal bigDecimal() {
/* 164 */     return this.bigDecimal;
/*     */   }
/*     */   
/*     */   public BigDecimal(java.math.BigDecimal bigDecimal, MathContext mc) {
/*     */     ScalaNumericAnyConversions$class.$init$(this);
/*     */   }
/*     */   
/*     */   public MathContext mc() {
/* 165 */     return this.mc;
/*     */   }
/*     */   
/*     */   public BigDecimal(java.math.BigDecimal bigDecimal) {
/* 167 */     this(bigDecimal, BigDecimal$.MODULE$.defaultMathContext());
/*     */   }
/*     */   
/*     */   private BigDecimal bigdec2BigDecimal(java.math.BigDecimal x) {
/* 171 */     return new BigDecimal(x, mc());
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 180 */     return isWhole() ? unifiedPrimitiveHashcode() : scala.runtime.ScalaRunTime$.MODULE$
/* 181 */       .hash(doubleValue());
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: instanceof scala/math/BigDecimal
/*     */     //   4: ifeq -> 22
/*     */     //   7: aload_1
/*     */     //   8: checkcast scala/math/BigDecimal
/*     */     //   11: astore_2
/*     */     //   12: aload_0
/*     */     //   13: aload_2
/*     */     //   14: invokevirtual equals : (Lscala/math/BigDecimal;)Z
/*     */     //   17: istore #11
/*     */     //   19: goto -> 178
/*     */     //   22: aload_1
/*     */     //   23: instanceof scala/math/BigInt
/*     */     //   26: ifeq -> 76
/*     */     //   29: aload_1
/*     */     //   30: checkcast scala/math/BigInt
/*     */     //   33: astore #5
/*     */     //   35: aload_0
/*     */     //   36: invokevirtual toBigIntExact : ()Lscala/Option;
/*     */     //   39: dup
/*     */     //   40: astore_3
/*     */     //   41: invokevirtual isEmpty : ()Z
/*     */     //   44: ifne -> 70
/*     */     //   47: aload_3
/*     */     //   48: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   51: checkcast scala/math/BigInt
/*     */     //   54: astore #6
/*     */     //   56: aload #5
/*     */     //   58: aload #6
/*     */     //   60: invokevirtual equals : (Lscala/math/BigInt;)Z
/*     */     //   63: ifeq -> 70
/*     */     //   66: iconst_1
/*     */     //   67: goto -> 71
/*     */     //   70: iconst_0
/*     */     //   71: istore #11
/*     */     //   73: goto -> 178
/*     */     //   76: aload_1
/*     */     //   77: instanceof java/lang/Double
/*     */     //   80: ifeq -> 116
/*     */     //   83: aload_1
/*     */     //   84: invokestatic unboxToDouble : (Ljava/lang/Object;)D
/*     */     //   87: dstore #8
/*     */     //   89: aload_0
/*     */     //   90: invokevirtual isValidDouble : ()Z
/*     */     //   93: ifeq -> 110
/*     */     //   96: aload_0
/*     */     //   97: invokevirtual toDouble : ()D
/*     */     //   100: dload #8
/*     */     //   102: dcmpl
/*     */     //   103: ifne -> 110
/*     */     //   106: iconst_1
/*     */     //   107: goto -> 111
/*     */     //   110: iconst_0
/*     */     //   111: istore #11
/*     */     //   113: goto -> 178
/*     */     //   116: aload_1
/*     */     //   117: instanceof java/lang/Float
/*     */     //   120: ifeq -> 156
/*     */     //   123: aload_1
/*     */     //   124: invokestatic unboxToFloat : (Ljava/lang/Object;)F
/*     */     //   127: fstore #10
/*     */     //   129: aload_0
/*     */     //   130: invokevirtual isValidFloat : ()Z
/*     */     //   133: ifeq -> 150
/*     */     //   136: aload_0
/*     */     //   137: invokevirtual toFloat : ()F
/*     */     //   140: fload #10
/*     */     //   142: fcmpl
/*     */     //   143: ifne -> 150
/*     */     //   146: iconst_1
/*     */     //   147: goto -> 151
/*     */     //   150: iconst_0
/*     */     //   151: istore #11
/*     */     //   153: goto -> 178
/*     */     //   156: aload_0
/*     */     //   157: invokevirtual isValidLong : ()Z
/*     */     //   160: ifeq -> 175
/*     */     //   163: aload_0
/*     */     //   164: aload_1
/*     */     //   165: invokevirtual unifiedPrimitiveEquals : (Ljava/lang/Object;)Z
/*     */     //   168: ifeq -> 175
/*     */     //   171: iconst_1
/*     */     //   172: goto -> 176
/*     */     //   175: iconst_0
/*     */     //   176: istore #11
/*     */     //   178: iload #11
/*     */     //   180: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #186	-> 0
/*     */     //   #185	-> 0
/*     */     //   #187	-> 22
/*     */     //   #188	-> 76
/*     */     //   #189	-> 116
/*     */     //   #190	-> 156
/*     */     //   #185	-> 178
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	181	0	this	Lscala/math/BigDecimal;
/*     */     //   0	181	1	that	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public boolean isValidByte() {
/* 192 */     return noArithmeticException((Function0<BoxedUnit>)new BigDecimal$$anonfun$isValidByte$1(this));
/*     */   }
/*     */   
/*     */   public class BigDecimal$$anonfun$isValidByte$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/* 192 */       this.$outer.toByteExact();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 192 */       this.$outer.toByteExact();
/*     */     }
/*     */     
/*     */     public BigDecimal$$anonfun$isValidByte$1(BigDecimal $outer) {}
/*     */   }
/*     */   
/*     */   public boolean isValidShort() {
/* 193 */     return noArithmeticException((Function0<BoxedUnit>)new BigDecimal$$anonfun$isValidShort$1(this));
/*     */   }
/*     */   
/*     */   public class BigDecimal$$anonfun$isValidShort$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/* 193 */       this.$outer.toShortExact();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 193 */       this.$outer.toShortExact();
/*     */     }
/*     */     
/*     */     public BigDecimal$$anonfun$isValidShort$1(BigDecimal $outer) {}
/*     */   }
/*     */   
/*     */   public boolean isValidChar() {
/* 194 */     return (isValidInt() && toIntExact() >= 0 && toIntExact() <= 65535);
/*     */   }
/*     */   
/*     */   public boolean isValidInt() {
/* 195 */     return noArithmeticException((Function0<BoxedUnit>)new BigDecimal$$anonfun$isValidInt$1(this));
/*     */   }
/*     */   
/*     */   public class BigDecimal$$anonfun$isValidInt$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/* 195 */       this.$outer.toIntExact();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 195 */       this.$outer.toIntExact();
/*     */     }
/*     */     
/*     */     public BigDecimal$$anonfun$isValidInt$1(BigDecimal $outer) {}
/*     */   }
/*     */   
/*     */   public boolean isValidLong() {
/* 196 */     return noArithmeticException((Function0<BoxedUnit>)new BigDecimal$$anonfun$isValidLong$1(this));
/*     */   }
/*     */   
/*     */   public class BigDecimal$$anonfun$isValidLong$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/* 196 */       this.$outer.toLongExact();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 196 */       this.$outer.toLongExact();
/*     */     }
/*     */     
/*     */     public BigDecimal$$anonfun$isValidLong$1(BigDecimal $outer) {}
/*     */   }
/*     */   
/*     */   public boolean isValidFloat() {
/* 200 */     float f = toFloat();
/* 201 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 201 */     return (!scala.runtime.RichFloat$.MODULE$.isInfinity$extension(f) && bigDecimal().compareTo(new java.math.BigDecimal(f)) == 0);
/*     */   }
/*     */   
/*     */   public boolean isValidDouble() {
/* 206 */     double d = toDouble();
/* 207 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 207 */     return (!scala.runtime.RichDouble$.MODULE$.isInfinity$extension(d) && bigDecimal().compareTo(new java.math.BigDecimal(d)) == 0);
/*     */   }
/*     */   
/*     */   private boolean noArithmeticException(Function0 body) {
/*     */     try {
/* 211 */       body.apply$mcV$sp();
/* 211 */     } catch (ArithmeticException arithmeticException) {}
/* 211 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isWhole() {
/* 215 */     BigDecimal bigDecimal2 = BigDecimal$.MODULE$.apply(0);
/*     */     BigDecimal bigDecimal1;
/* 215 */     return (((bigDecimal1 = remainder(BigDecimal$.MODULE$.int2bigDecimal(1))) == bigDecimal2) ? true : ((bigDecimal1 == null) ? false : ((bigDecimal1 instanceof Number) ? BoxesRunTime.equalsNumObject(bigDecimal1, bigDecimal2) : ((bigDecimal1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)bigDecimal1, bigDecimal2) : bigDecimal1.equals(bigDecimal2)))));
/*     */   }
/*     */   
/*     */   public java.math.BigDecimal underlying() {
/* 216 */     return bigDecimal();
/*     */   }
/*     */   
/*     */   public boolean equals(BigDecimal that) {
/* 220 */     return (compare(that) == 0);
/*     */   }
/*     */   
/*     */   public int compare(BigDecimal that) {
/* 224 */     return bigDecimal().compareTo(that.bigDecimal());
/*     */   }
/*     */   
/*     */   public boolean $less$eq(BigDecimal that) {
/* 228 */     return (compare(that) <= 0);
/*     */   }
/*     */   
/*     */   public boolean $greater$eq(BigDecimal that) {
/* 232 */     return (compare(that) >= 0);
/*     */   }
/*     */   
/*     */   public boolean $less(BigDecimal that) {
/* 236 */     return (compare(that) < 0);
/*     */   }
/*     */   
/*     */   public boolean $greater(BigDecimal that) {
/* 240 */     return (compare(that) > 0);
/*     */   }
/*     */   
/*     */   public BigDecimal $plus(BigDecimal that) {
/* 244 */     return bigdec2BigDecimal(bigDecimal().add(that.bigDecimal()));
/*     */   }
/*     */   
/*     */   public BigDecimal $minus(BigDecimal that) {
/* 248 */     return bigdec2BigDecimal(bigDecimal().subtract(that.bigDecimal()));
/*     */   }
/*     */   
/*     */   public BigDecimal $times(BigDecimal that) {
/* 252 */     return bigdec2BigDecimal(bigDecimal().multiply(that.bigDecimal(), mc()));
/*     */   }
/*     */   
/*     */   public BigDecimal $div(BigDecimal that) {
/* 256 */     return bigdec2BigDecimal(bigDecimal().divide(that.bigDecimal(), mc()));
/*     */   }
/*     */   
/*     */   public Tuple2<BigDecimal, BigDecimal> $div$percent(BigDecimal that) {
/* 262 */     java.math.BigDecimal[] arrayOfBigDecimal = bigDecimal().divideAndRemainder(that.bigDecimal());
/* 263 */     Option option = scala.Array$.MODULE$.unapplySeq(arrayOfBigDecimal);
/* 263 */     if (!option.isEmpty() && option.get() != null && ((SeqLike)option.get()).lengthCompare(2) == 0);
/*     */     throw new MatchError(arrayOfBigDecimal);
/*     */   }
/*     */   
/*     */   public BigDecimal quot(BigDecimal that) {
/* 269 */     return bigdec2BigDecimal(bigDecimal().divideToIntegralValue(that.bigDecimal()));
/*     */   }
/*     */   
/*     */   public BigDecimal min(BigDecimal that) {
/* 273 */     return bigdec2BigDecimal(bigDecimal().min(that.bigDecimal()));
/*     */   }
/*     */   
/*     */   public BigDecimal max(BigDecimal that) {
/* 277 */     return bigdec2BigDecimal(bigDecimal().max(that.bigDecimal()));
/*     */   }
/*     */   
/*     */   public BigDecimal remainder(BigDecimal that) {
/* 281 */     return bigdec2BigDecimal(bigDecimal().remainder(that.bigDecimal()));
/*     */   }
/*     */   
/*     */   public BigDecimal $percent(BigDecimal that) {
/* 285 */     return remainder(that);
/*     */   }
/*     */   
/*     */   public BigDecimal pow(int n) {
/* 289 */     return bigdec2BigDecimal(bigDecimal().pow(n, mc()));
/*     */   }
/*     */   
/*     */   public BigDecimal unary_$minus() {
/* 293 */     return bigdec2BigDecimal(bigDecimal().negate());
/*     */   }
/*     */   
/*     */   public BigDecimal abs() {
/* 297 */     return bigdec2BigDecimal(bigDecimal().abs());
/*     */   }
/*     */   
/*     */   public int signum() {
/* 304 */     return bigDecimal().signum();
/*     */   }
/*     */   
/*     */   public int precision() {
/* 308 */     return bigDecimal().precision();
/*     */   }
/*     */   
/*     */   public BigDecimal round(MathContext mc) {
/* 312 */     return bigdec2BigDecimal(bigDecimal().round(mc));
/*     */   }
/*     */   
/*     */   public int scale() {
/* 316 */     return bigDecimal().scale();
/*     */   }
/*     */   
/*     */   public BigDecimal ulp() {
/* 320 */     return bigdec2BigDecimal(bigDecimal().ulp());
/*     */   }
/*     */   
/*     */   public BigDecimal apply(MathContext mc) {
/* 324 */     return BigDecimal$.MODULE$.apply(bigDecimal().toString(), mc);
/*     */   }
/*     */   
/*     */   public BigDecimal setScale(int scale) {
/* 329 */     return bigdec2BigDecimal(bigDecimal().setScale(scale));
/*     */   }
/*     */   
/*     */   public BigDecimal setScale(int scale, Enumeration.Value mode) {
/* 332 */     return bigdec2BigDecimal(bigDecimal().setScale(scale, mode.id()));
/*     */   }
/*     */   
/*     */   public byte byteValue() {
/* 339 */     return (byte)intValue();
/*     */   }
/*     */   
/*     */   public short shortValue() {
/* 346 */     return (short)intValue();
/*     */   }
/*     */   
/*     */   public char charValue() {
/* 353 */     return (char)intValue();
/*     */   }
/*     */   
/*     */   public int intValue() {
/* 361 */     return bigDecimal().intValue();
/*     */   }
/*     */   
/*     */   public long longValue() {
/* 369 */     return bigDecimal().longValue();
/*     */   }
/*     */   
/*     */   public float floatValue() {
/* 376 */     return bigDecimal().floatValue();
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 383 */     return bigDecimal().doubleValue();
/*     */   }
/*     */   
/*     */   public byte toByteExact() {
/* 390 */     return bigDecimal().byteValueExact();
/*     */   }
/*     */   
/*     */   public short toShortExact() {
/* 397 */     return bigDecimal().shortValueExact();
/*     */   }
/*     */   
/*     */   public int toIntExact() {
/* 404 */     return bigDecimal().intValueExact();
/*     */   }
/*     */   
/*     */   public long toLongExact() {
/* 411 */     return bigDecimal().longValueExact();
/*     */   }
/*     */   
/*     */   public Range.Partial<BigDecimal, NumericRange.Exclusive<BigDecimal>> until(BigDecimal end) {
/* 427 */     return new Range.Partial((Function1)new BigDecimal$$anonfun$until$1(this, end));
/*     */   }
/*     */   
/*     */   public class BigDecimal$$anonfun$until$1 extends AbstractFunction1<BigDecimal, NumericRange.Exclusive<BigDecimal>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BigDecimal end$1;
/*     */     
/*     */     public final NumericRange.Exclusive<BigDecimal> apply(BigDecimal x$2) {
/* 427 */       return this.$outer.until(this.end$1, x$2);
/*     */     }
/*     */     
/*     */     public BigDecimal$$anonfun$until$1(BigDecimal $outer, BigDecimal end$1) {}
/*     */   }
/*     */   
/*     */   public NumericRange.Exclusive<BigDecimal> until(BigDecimal end, BigDecimal step) {
/* 430 */     return scala.collection.immutable.Range$BigDecimal$.MODULE$.apply(this, end, step);
/*     */   }
/*     */   
/*     */   public Range.Partial<BigDecimal, NumericRange.Inclusive<BigDecimal>> to(BigDecimal end) {
/* 434 */     return new Range.Partial((Function1)new BigDecimal$$anonfun$to$1(this, end));
/*     */   }
/*     */   
/*     */   public class BigDecimal$$anonfun$to$1 extends AbstractFunction1<BigDecimal, NumericRange.Inclusive<BigDecimal>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BigDecimal end$2;
/*     */     
/*     */     public final NumericRange.Inclusive<BigDecimal> apply(BigDecimal x$3) {
/* 434 */       return this.$outer.to(this.end$2, x$3);
/*     */     }
/*     */     
/*     */     public BigDecimal$$anonfun$to$1(BigDecimal $outer, BigDecimal end$2) {}
/*     */   }
/*     */   
/*     */   public NumericRange.Inclusive<BigDecimal> to(BigDecimal end, BigDecimal step) {
/* 437 */     return scala.collection.immutable.Range$BigDecimal$.MODULE$.inclusive(this, end, step);
/*     */   }
/*     */   
/*     */   public BigInt toBigInt() {
/* 441 */     return new BigInt(bigDecimal().toBigInteger());
/*     */   }
/*     */   
/*     */   public Option<BigInt> toBigIntExact() {
/*     */     try {
/*     */     
/* 447 */     } catch (ArithmeticException arithmeticException) {}
/* 447 */     return 
/* 448 */       (Option<BigInt>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 452 */     return bigDecimal().toString();
/*     */   }
/*     */   
/*     */   public static BigDecimal javaBigDecimal2bigDecimal(java.math.BigDecimal paramBigDecimal) {
/*     */     return BigDecimal$.MODULE$.javaBigDecimal2bigDecimal(paramBigDecimal);
/*     */   }
/*     */   
/*     */   public static BigDecimal double2bigDecimal(double paramDouble) {
/*     */     return BigDecimal$.MODULE$.double2bigDecimal(paramDouble);
/*     */   }
/*     */   
/*     */   public static BigDecimal long2bigDecimal(long paramLong) {
/*     */     return BigDecimal$.MODULE$.long2bigDecimal(paramLong);
/*     */   }
/*     */   
/*     */   public static BigDecimal int2bigDecimal(int paramInt) {
/*     */     return BigDecimal$.MODULE$.int2bigDecimal(paramInt);
/*     */   }
/*     */   
/*     */   public static BigDecimal valueOf(double paramDouble, MathContext paramMathContext) {
/*     */     return BigDecimal$.MODULE$.valueOf(paramDouble, paramMathContext);
/*     */   }
/*     */   
/*     */   public static BigDecimal valueOf(double paramDouble) {
/*     */     return BigDecimal$.MODULE$.valueOf(paramDouble);
/*     */   }
/*     */   
/*     */   public static BigDecimal MaxLong() {
/*     */     return BigDecimal$.MODULE$.MaxLong();
/*     */   }
/*     */   
/*     */   public static BigDecimal MinLong() {
/*     */     return BigDecimal$.MODULE$.MinLong();
/*     */   }
/*     */   
/*     */   public static MathContext defaultMathContext() {
/*     */     return BigDecimal$.MODULE$.defaultMathContext();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\BigDecimal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */