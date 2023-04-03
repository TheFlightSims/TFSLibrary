/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Proxy;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.NumericRange;
/*    */ import scala.collection.immutable.Range;
/*    */ import scala.math.Fractional;
/*    */ import scala.math.Integral;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Numeric$DoubleAsIfIntegral$;
/*    */ import scala.math.Numeric$DoubleIsFractional$;
/*    */ import scala.math.Ordered;
/*    */ import scala.math.Ordering;
/*    */ import scala.math.Ordering$Double$;
/*    */ import scala.math.ScalaNumericAnyConversions;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055e\001B\001\003\005\035\021!BU5dQ\022{WO\0317f\025\t\031A!A\004sk:$\030.\\3\013\003\025\tQa]2bY\006\034\001aE\002\001\0211\001\"!\003\006\016\003\021I!a\003\003\003\r\005s\027PV1m!\ria\002E\007\002\005%\021qB\001\002\020\rJ\f7\r^5p]\006d\007K]8ysB\021\021\"E\005\003%\021\021a\001R8vE2,\007\002\003\013\001\005\013\007I\021A\013\002\tM,GNZ\013\002!!Aq\003\001B\001B\003%\001#A\003tK24\007\005C\003\032\001\021\005!$\001\004=S:LGO\020\013\0037q\001\"!\004\001\t\013QA\002\031\001\t\t\013y\001A\021C\020\002\0079,X.F\001!\035\t\tSF\004\002#U9\0211\005\013\b\003I\035j\021!\n\006\003M\031\ta\001\020:p_Rt\024\"A\003\n\005%\"\021\001B7bi\"L!a\013\027\002\0179+X.\032:jG*\021\021\006B\005\003]=\n!\003R8vE2,\027j\035$sC\016$\030n\0348bY*\0211\006\f\005\006c\001!\tBM\001\004_J$W#A\032\017\005Q:dB\001\0226\023\t1D&\001\005Pe\022,'/\0338h\023\tA\024(\001\004E_V\024G.\032\006\003m1BQa\017\001\005\022q\n1\"\0338uK\036\024\030\r\034(v[V\tQH\004\002\"}%\021qhL\001\023\t>,(\r\\3Bg&3\027J\034;fOJ\fG\016C\003B\001\021\005!)A\003s_VtG-F\001D!\tIA)\003\002F\t\t!Aj\0348h\021\0259\005\001\"\001\026\003\021\031W-\0337\t\013%\003A\021A\013\002\013\031dwn\034:\t\013-\003A\021A\013\002\023Q|'+\0313jC:\034\b\"B'\001\t\003)\022!\003;p\t\026<'/Z3t\021\025y\005\001\"\001Q\003)I7/\0238gS:LG/_\013\002#B\021\021BU\005\003'\022\021qAQ8pY\026\fg\016C\003V\001\021\005\001+A\007jgB{7/\0238gS:LG/\037\005\006/\002!\t\001U\001\016SNtUmZ%oM&t\027\016^=\t\013e\003A\021\t)\002\027%\034h+\0317jI\nKH/\032\005\0067\002!\t\005U\001\rSN4\026\r\\5e'\"|'\017\036\005\006;\002!\t\005U\001\fSN4\026\r\\5e\007\"\f'\017C\003`\001\021\005\003+\001\006jgZ\013G.\0333J]RDQ!\031\001\005B\t\fq![:XQ>dW\rF\001R\021\035!\007!!A\005B\025\f\001\002[1tQ\016{G-\032\013\002MB\021\021bZ\005\003Q\022\0211!\0238u\021\035Q\007!!A\005B-\fa!Z9vC2\034HCA)m\021\035i\027.!AA\0029\f1\001\037\0232!\tIq.\003\002q\t\t\031\021I\\=\b\017I\024\021\021!E\001g\006Q!+[2i\t>,(\r\\3\021\0055!haB\001\003\003\003E\t!^\n\003iZ\004\"!C<\n\005a$!AB!osJ+g\rC\003\032i\022\005!\020F\001t\021\025aH\017\"\002~\0035qW/\034\023fqR,gn]5p]R\021\001E \005\006n\004\raG\001\006IQD\027n\035\005\b\003\007!HQAA\003\0035y'\017\032\023fqR,gn]5p]R\0311'a\002\t\r}\f\t\0011\001\034\021\035\tY\001\036C\003\003\033\tQ#\0338uK\036\024\030\r\034(v[\022*\007\020^3og&|g\016F\002>\003\037Aaa`A\005\001\004Y\002bBA\ni\022\025\021QC\001\020e>,h\016\032\023fqR,gn]5p]R\0311)a\006\t\r}\f\t\0021\001\034\021\035\tY\002\036C\003\003;\tabY3jY\022*\007\020^3og&|g\016F\002\021\003?Aaa`A\r\001\004Y\002bBA\022i\022\025\021QE\001\020M2|wN\035\023fqR,gn]5p]R\031\001#a\n\t\r}\f\t\0031\001\034\021\035\tY\003\036C\003\003[\t1\003^8SC\022L\027M\\:%Kb$XM\\:j_:$2\001EA\030\021\031y\030\021\006a\0017!9\0211\007;\005\006\005U\022a\005;p\t\026<'/Z3tI\025DH/\0328tS>tGc\001\t\0028!1q0!\rA\002mAq!a\017u\t\013\ti$\001\013jg&sg-\0338jif$S\r\037;f]NLwN\034\013\004#\006}\002BB@\002:\001\0071\004C\004\002DQ$)!!\022\002/%\034\bk\\:J]\032Lg.\033;zI\025DH/\0328tS>tGcA)\002H!1q0!\021A\002mAq!a\023u\t\013\ti%A\fjg:+w-\0238gS:LG/\037\023fqR,gn]5p]R\031\021+a\024\t\r}\fI\0051\001\034\021\035\t\031\006\036C\003\003+\nQ#[:WC2LGMQ=uK\022*\007\020^3og&|g\016F\002R\003/Baa`A)\001\004Y\002bBA.i\022\025\021QL\001\027SN4\026\r\\5e'\"|'\017\036\023fqR,gn]5p]R\031\021+a\030\t\r}\fI\0061\001\034\021\035\t\031\007\036C\003\003K\nQ#[:WC2LGm\0215be\022*\007\020^3og&|g\016F\002R\003OBaa`A1\001\004Y\002bBA6i\022\025\021QN\001\025SN4\026\r\\5e\023:$H%\032=uK:\034\030n\0348\025\007E\013y\007\003\004\000\003S\002\ra\007\005\b\003g\"HQAA;\003EI7o\0265pY\026$S\r\037;f]NLwN\034\013\004E\006]\004BB@\002r\001\0071\004C\005\002|Q\f\t\021\"\002\002~\005\021\002.Y:i\007>$W\rJ3yi\026t7/[8o)\r)\027q\020\005\007\006e\004\031A\016\t\023\005\rE/!A\005\006\005\025\025\001E3rk\006d7\017J3yi\026t7/[8o)\021\t9)a#\025\007E\013I\t\003\005n\003\003\013\t\0211\001o\021\031y\030\021\021a\0017\001")
/*    */ public final class RichDouble implements FractionalProxy<Object> {
/*    */   private final double self;
/*    */   
/*    */   public Range.Partial<Object, NumericRange<Object>> until(Object end) {
/* 12 */     return FractionalProxy$class.until(this, end);
/*    */   }
/*    */   
/*    */   public NumericRange.Exclusive<Object> until(Object end, Object step) {
/* 12 */     return FractionalProxy$class.until(this, end, step);
/*    */   }
/*    */   
/*    */   public Range.Partial<Object, NumericRange<Object>> to(Object end) {
/* 12 */     return FractionalProxy$class.to(this, end);
/*    */   }
/*    */   
/*    */   public NumericRange.Inclusive<Object> to(Object end, Object step) {
/* 12 */     return FractionalProxy$class.to(this, end, step);
/*    */   }
/*    */   
/*    */   public Object underlying() {
/* 12 */     return ScalaNumberProxy$class.underlying(this);
/*    */   }
/*    */   
/*    */   public double doubleValue() {
/* 12 */     return ScalaNumberProxy$class.doubleValue(this);
/*    */   }
/*    */   
/*    */   public float floatValue() {
/* 12 */     return ScalaNumberProxy$class.floatValue(this);
/*    */   }
/*    */   
/*    */   public long longValue() {
/* 12 */     return ScalaNumberProxy$class.longValue(this);
/*    */   }
/*    */   
/*    */   public int intValue() {
/* 12 */     return ScalaNumberProxy$class.intValue(this);
/*    */   }
/*    */   
/*    */   public byte byteValue() {
/* 12 */     return ScalaNumberProxy$class.byteValue(this);
/*    */   }
/*    */   
/*    */   public short shortValue() {
/* 12 */     return ScalaNumberProxy$class.shortValue(this);
/*    */   }
/*    */   
/*    */   public Object min(Object that) {
/* 12 */     return ScalaNumberProxy$class.min(this, that);
/*    */   }
/*    */   
/*    */   public Object max(Object that) {
/* 12 */     return ScalaNumberProxy$class.max(this, that);
/*    */   }
/*    */   
/*    */   public Object abs() {
/* 12 */     return ScalaNumberProxy$class.abs(this);
/*    */   }
/*    */   
/*    */   public int signum() {
/* 12 */     return ScalaNumberProxy$class.signum(this);
/*    */   }
/*    */   
/*    */   public int compare(Object y) {
/* 12 */     return OrderedProxy$class.compare(this, y);
/*    */   }
/*    */   
/*    */   public boolean $less(Object that) {
/* 12 */     return Ordered.class.$less(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater(Object that) {
/* 12 */     return Ordered.class.$greater(this, that);
/*    */   }
/*    */   
/*    */   public boolean $less$eq(Object that) {
/* 12 */     return Ordered.class.$less$eq(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater$eq(Object that) {
/* 12 */     return Ordered.class.$greater$eq(this, that);
/*    */   }
/*    */   
/*    */   public int compareTo(Object that) {
/* 12 */     return Ordered.class.compareTo(this, that);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return Proxy.class.toString((Proxy)this);
/*    */   }
/*    */   
/*    */   public char toChar() {
/* 12 */     return ScalaNumericAnyConversions.class.toChar(this);
/*    */   }
/*    */   
/*    */   public byte toByte() {
/* 12 */     return ScalaNumericAnyConversions.class.toByte(this);
/*    */   }
/*    */   
/*    */   public short toShort() {
/* 12 */     return ScalaNumericAnyConversions.class.toShort(this);
/*    */   }
/*    */   
/*    */   public int toInt() {
/* 12 */     return ScalaNumericAnyConversions.class.toInt(this);
/*    */   }
/*    */   
/*    */   public long toLong() {
/* 12 */     return ScalaNumericAnyConversions.class.toLong(this);
/*    */   }
/*    */   
/*    */   public float toFloat() {
/* 12 */     return ScalaNumericAnyConversions.class.toFloat(this);
/*    */   }
/*    */   
/*    */   public double toDouble() {
/* 12 */     return ScalaNumericAnyConversions.class.toDouble(this);
/*    */   }
/*    */   
/*    */   public int unifiedPrimitiveHashcode() {
/* 12 */     return ScalaNumericAnyConversions.class.unifiedPrimitiveHashcode(this);
/*    */   }
/*    */   
/*    */   public boolean unifiedPrimitiveEquals(Object x) {
/* 12 */     return ScalaNumericAnyConversions.class.unifiedPrimitiveEquals(this, x);
/*    */   }
/*    */   
/*    */   public double self() {
/* 12 */     return this.self;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 12 */     return RichDouble$.MODULE$.hashCode$extension(self());
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/* 12 */     return RichDouble$.MODULE$.equals$extension(self(), x$1);
/*    */   }
/*    */   
/*    */   public RichDouble(double self) {
/* 12 */     ScalaNumericAnyConversions.class.$init$(this);
/* 12 */     Proxy.class.$init$((Proxy)this);
/* 12 */     Ordered.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Numeric$DoubleIsFractional$ num() {
/* 13 */     return RichDouble$.MODULE$.num$extension(self());
/*    */   }
/*    */   
/*    */   public Ordering$Double$ ord() {
/* 14 */     return RichDouble$.MODULE$.ord$extension(self());
/*    */   }
/*    */   
/*    */   public Numeric$DoubleAsIfIntegral$ integralNum() {
/* 15 */     return RichDouble$.MODULE$.integralNum$extension(self());
/*    */   }
/*    */   
/*    */   public long round() {
/* 17 */     return RichDouble$.MODULE$.round$extension(self());
/*    */   }
/*    */   
/*    */   public double ceil() {
/* 18 */     return RichDouble$.MODULE$.ceil$extension(self());
/*    */   }
/*    */   
/*    */   public double floor() {
/* 19 */     return RichDouble$.MODULE$.floor$extension(self());
/*    */   }
/*    */   
/*    */   public double toRadians() {
/* 26 */     return RichDouble$.MODULE$.toRadians$extension(self());
/*    */   }
/*    */   
/*    */   public double toDegrees() {
/* 32 */     return RichDouble$.MODULE$.toDegrees$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isInfinity() {
/* 36 */     return RichDouble$.MODULE$.isInfinity$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isPosInfinity() {
/* 37 */     return RichDouble$.MODULE$.isPosInfinity$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isNegInfinity() {
/* 38 */     return RichDouble$.MODULE$.isNegInfinity$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isValidByte() {
/* 40 */     return RichDouble$.MODULE$.isValidByte$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isValidShort() {
/* 41 */     return RichDouble$.MODULE$.isValidShort$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isValidChar() {
/* 42 */     return RichDouble$.MODULE$.isValidChar$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isValidInt() {
/* 43 */     return RichDouble$.MODULE$.isValidInt$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isWhole() {
/* 47 */     return RichDouble$.MODULE$.isWhole$extension(self());
/*    */   }
/*    */   
/*    */   public static boolean equals$extension(double paramDouble, Object paramObject) {
/*    */     return RichDouble$.MODULE$.equals$extension(paramDouble, paramObject);
/*    */   }
/*    */   
/*    */   public static int hashCode$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.hashCode$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static boolean isWhole$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.isWhole$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static boolean isValidInt$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.isValidInt$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static boolean isValidChar$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.isValidChar$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static boolean isValidShort$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.isValidShort$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static boolean isValidByte$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.isValidByte$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static boolean isNegInfinity$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.isNegInfinity$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static boolean isPosInfinity$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.isPosInfinity$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static boolean isInfinity$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.isInfinity$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static double toDegrees$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.toDegrees$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static double toRadians$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.toRadians$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static double floor$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.floor$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static double ceil$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.ceil$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static long round$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.round$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static Numeric$DoubleAsIfIntegral$ integralNum$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.integralNum$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static Ordering$Double$ ord$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.ord$extension(paramDouble);
/*    */   }
/*    */   
/*    */   public static Numeric$DoubleIsFractional$ num$extension(double paramDouble) {
/*    */     return RichDouble$.MODULE$.num$extension(paramDouble);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichDouble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */