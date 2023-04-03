/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Proxy;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.NumericRange;
/*    */ import scala.math.Integral;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Numeric$LongIsIntegral$;
/*    */ import scala.math.Ordered;
/*    */ import scala.math.Ordering;
/*    */ import scala.math.Ordering$Long$;
/*    */ import scala.math.ScalaNumericAnyConversions;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005]b\001B\001\003\005\035\021\001BU5dQ2{gn\032\006\003\007\021\tqA];oi&lWMC\001\006\003\025\0318-\0317b\007\001\0312\001\001\005\r!\tI!\"D\001\005\023\tYAA\001\004B]f4\026\r\034\t\004\0339\001R\"\001\002\n\005=\021!!D%oi\026<'/\0317Qe>D\030\020\005\002\n#%\021!\003\002\002\005\031>tw\r\003\005\025\001\t\025\r\021\"\001\026\003\021\031X\r\0344\026\003AA\001b\006\001\003\002\003\006I\001E\001\006g\026dg\r\t\005\0063\001!\tAG\001\007y%t\027\016\036 \025\005ma\002CA\007\001\021\025!\002\0041\001\021\021\025q\002\001\"\005 \003\rqW/\\\013\002A9\021\021%\f\b\003E)r!a\t\025\017\005\021:S\"A\023\013\005\0312\021A\002\037s_>$h(C\001\006\023\tIC!\001\003nCRD\027BA\026-\003\035qU/\\3sS\016T!!\013\003\n\0059z\023A\004'p]\036L5/\0238uK\036\024\030\r\034\006\003W1BQ!\r\001\005\022I\n1a\034:e+\005\031dB\001\0338\035\t\021S'\003\0027Y\005AqJ\0353fe&tw-\003\0029s\005!Aj\0348h\025\t1D\006C\003<\001\021\005A(\001\bu_\nKg.\031:z'R\024\030N\\4\026\003u\002\"AP!\017\005%y\024B\001!\005\003\031\001&/\0323fM&\021!i\021\002\007'R\024\030N\\4\013\005\001#\001\"B#\001\t\003a\024a\003;p\021\026D8\013\036:j]\036DQa\022\001\005\002q\nQ\002^8PGR\fGn\025;sS:<\007\"B%\001\t\003R\025aC5t-\006d\027\016\032\"zi\026,\022a\023\t\003\0231K!!\024\003\003\017\t{w\016\\3b]\")q\n\001C!\025\006a\021n\035,bY&$7\013[8si\")\021\013\001C!\025\006Y\021n\035,bY&$7\t[1s\021\025\031\006\001\"\021K\003)I7OV1mS\022Le\016\036\005\b+\002\t\t\021\"\021W\003!A\027m\0355D_\022,G#A,\021\005%A\026BA-\005\005\rIe\016\036\005\b7\002\t\t\021\"\021]\003\031)\027/^1mgR\0211*\030\005\b=j\013\t\0211\001`\003\rAH%\r\t\003\023\001L!!\031\003\003\007\005s\027pB\004d\005\005\005\t\022\0013\002\021IK7\r\033'p]\036\004\"!D3\007\017\005\021\021\021!E\001MN\021Qm\032\t\003\023!L!!\033\003\003\r\005s\027PU3g\021\025IR\r\"\001l)\005!\007\"B7f\t\013q\027!\0048v[\022*\007\020^3og&|g\016\006\002!_\")\001\017\034a\0017\005)A\005\0365jg\")!/\032C\003g\006iqN\0353%Kb$XM\\:j_:$\"a\r;\t\013A\f\b\031A\016\t\013Y,GQA<\0021Q|')\0338bef\034FO]5oO\022*\007\020^3og&|g\016\006\002>q\")\001/\036a\0017!)!0\032C\003w\006)Bo\034%fqN#(/\0338hI\025DH/\0328tS>tGCA\037}\021\025\001\030\0201\001\034\021\025qX\r\"\002\000\003]!xnT2uC2\034FO]5oO\022*\007\020^3og&|g\016F\002>\003\003AQ\001]?A\002mAq!!\002f\t\013\t9!A\013jgZ\013G.\0333CsR,G%\032=uK:\034\030n\0348\025\007-\013I\001\003\004q\003\007\001\ra\007\005\b\003\033)GQAA\b\003YI7OV1mS\022\034\006n\034:uI\025DH/\0328tS>tGcA&\002\022!1\001/a\003A\002mAq!!\006f\t\013\t9\"A\013jgZ\013G.\0333DQ\006\024H%\032=uK:\034\030n\0348\025\007-\013I\002\003\004q\003'\001\ra\007\005\b\003;)GQAA\020\003QI7OV1mS\022Le\016\036\023fqR,gn]5p]R\0311*!\t\t\rA\fY\0021\001\034\021%\t)#ZA\001\n\013\t9#\001\niCND7i\0343fI\025DH/\0328tS>tGc\001,\002*!1\001/a\tA\002mA\021\"!\ff\003\003%)!a\f\002!\025\fX/\0317tI\025DH/\0328tS>tG\003BA\031\003k!2aSA\032\021!q\0261FA\001\002\004y\006B\0029\002,\001\0071\004")
/*    */ public final class RichLong implements IntegralProxy<Object> {
/*    */   private final long self;
/*    */   
/*    */   public NumericRange.Exclusive<Object> until(Object end) {
/* 11 */     return IntegralProxy$class.until(this, end);
/*    */   }
/*    */   
/*    */   public NumericRange.Exclusive<Object> until(Object end, Object step) {
/* 11 */     return IntegralProxy$class.until(this, end, step);
/*    */   }
/*    */   
/*    */   public NumericRange.Inclusive<Object> to(Object end) {
/* 11 */     return IntegralProxy$class.to(this, end);
/*    */   }
/*    */   
/*    */   public NumericRange.Inclusive<Object> to(Object end, Object step) {
/* 11 */     return IntegralProxy$class.to(this, end, step);
/*    */   }
/*    */   
/*    */   public boolean isWhole() {
/* 11 */     return ScalaWholeNumberProxy$class.isWhole(this);
/*    */   }
/*    */   
/*    */   public Object underlying() {
/* 11 */     return ScalaNumberProxy$class.underlying(this);
/*    */   }
/*    */   
/*    */   public double doubleValue() {
/* 11 */     return ScalaNumberProxy$class.doubleValue(this);
/*    */   }
/*    */   
/*    */   public float floatValue() {
/* 11 */     return ScalaNumberProxy$class.floatValue(this);
/*    */   }
/*    */   
/*    */   public long longValue() {
/* 11 */     return ScalaNumberProxy$class.longValue(this);
/*    */   }
/*    */   
/*    */   public int intValue() {
/* 11 */     return ScalaNumberProxy$class.intValue(this);
/*    */   }
/*    */   
/*    */   public byte byteValue() {
/* 11 */     return ScalaNumberProxy$class.byteValue(this);
/*    */   }
/*    */   
/*    */   public short shortValue() {
/* 11 */     return ScalaNumberProxy$class.shortValue(this);
/*    */   }
/*    */   
/*    */   public Object min(Object that) {
/* 11 */     return ScalaNumberProxy$class.min(this, that);
/*    */   }
/*    */   
/*    */   public Object max(Object that) {
/* 11 */     return ScalaNumberProxy$class.max(this, that);
/*    */   }
/*    */   
/*    */   public Object abs() {
/* 11 */     return ScalaNumberProxy$class.abs(this);
/*    */   }
/*    */   
/*    */   public int signum() {
/* 11 */     return ScalaNumberProxy$class.signum(this);
/*    */   }
/*    */   
/*    */   public int compare(Object y) {
/* 11 */     return OrderedProxy$class.compare(this, y);
/*    */   }
/*    */   
/*    */   public boolean $less(Object that) {
/* 11 */     return Ordered.class.$less(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater(Object that) {
/* 11 */     return Ordered.class.$greater(this, that);
/*    */   }
/*    */   
/*    */   public boolean $less$eq(Object that) {
/* 11 */     return Ordered.class.$less$eq(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater$eq(Object that) {
/* 11 */     return Ordered.class.$greater$eq(this, that);
/*    */   }
/*    */   
/*    */   public int compareTo(Object that) {
/* 11 */     return Ordered.class.compareTo(this, that);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 11 */     return Proxy.class.toString((Proxy)this);
/*    */   }
/*    */   
/*    */   public char toChar() {
/* 11 */     return ScalaNumericAnyConversions.class.toChar(this);
/*    */   }
/*    */   
/*    */   public byte toByte() {
/* 11 */     return ScalaNumericAnyConversions.class.toByte(this);
/*    */   }
/*    */   
/*    */   public short toShort() {
/* 11 */     return ScalaNumericAnyConversions.class.toShort(this);
/*    */   }
/*    */   
/*    */   public int toInt() {
/* 11 */     return ScalaNumericAnyConversions.class.toInt(this);
/*    */   }
/*    */   
/*    */   public long toLong() {
/* 11 */     return ScalaNumericAnyConversions.class.toLong(this);
/*    */   }
/*    */   
/*    */   public float toFloat() {
/* 11 */     return ScalaNumericAnyConversions.class.toFloat(this);
/*    */   }
/*    */   
/*    */   public double toDouble() {
/* 11 */     return ScalaNumericAnyConversions.class.toDouble(this);
/*    */   }
/*    */   
/*    */   public int unifiedPrimitiveHashcode() {
/* 11 */     return ScalaNumericAnyConversions.class.unifiedPrimitiveHashcode(this);
/*    */   }
/*    */   
/*    */   public boolean unifiedPrimitiveEquals(Object x) {
/* 11 */     return ScalaNumericAnyConversions.class.unifiedPrimitiveEquals(this, x);
/*    */   }
/*    */   
/*    */   public long self() {
/* 11 */     return this.self;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 11 */     return RichLong$.MODULE$.hashCode$extension(self());
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/* 11 */     return RichLong$.MODULE$.equals$extension(self(), x$1);
/*    */   }
/*    */   
/*    */   public RichLong(long self) {
/* 11 */     ScalaNumericAnyConversions.class.$init$(this);
/* 11 */     Proxy.class.$init$((Proxy)this);
/* 11 */     Ordered.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Numeric$LongIsIntegral$ num() {
/* 12 */     return RichLong$.MODULE$.num$extension(self());
/*    */   }
/*    */   
/*    */   public Ordering$Long$ ord() {
/* 13 */     return RichLong$.MODULE$.ord$extension(self());
/*    */   }
/*    */   
/*    */   public String toBinaryString() {
/* 15 */     return RichLong$.MODULE$.toBinaryString$extension(self());
/*    */   }
/*    */   
/*    */   public String toHexString() {
/* 16 */     return RichLong$.MODULE$.toHexString$extension(self());
/*    */   }
/*    */   
/*    */   public String toOctalString() {
/* 17 */     return RichLong$.MODULE$.toOctalString$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isValidByte() {
/* 19 */     return RichLong$.MODULE$.isValidByte$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isValidShort() {
/* 20 */     return RichLong$.MODULE$.isValidShort$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isValidChar() {
/* 21 */     return RichLong$.MODULE$.isValidChar$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isValidInt() {
/* 22 */     return RichLong$.MODULE$.isValidInt$extension(self());
/*    */   }
/*    */   
/*    */   public static boolean equals$extension(long paramLong, Object paramObject) {
/*    */     return RichLong$.MODULE$.equals$extension(paramLong, paramObject);
/*    */   }
/*    */   
/*    */   public static int hashCode$extension(long paramLong) {
/*    */     return RichLong$.MODULE$.hashCode$extension(paramLong);
/*    */   }
/*    */   
/*    */   public static boolean isValidInt$extension(long paramLong) {
/*    */     return RichLong$.MODULE$.isValidInt$extension(paramLong);
/*    */   }
/*    */   
/*    */   public static boolean isValidChar$extension(long paramLong) {
/*    */     return RichLong$.MODULE$.isValidChar$extension(paramLong);
/*    */   }
/*    */   
/*    */   public static boolean isValidShort$extension(long paramLong) {
/*    */     return RichLong$.MODULE$.isValidShort$extension(paramLong);
/*    */   }
/*    */   
/*    */   public static boolean isValidByte$extension(long paramLong) {
/*    */     return RichLong$.MODULE$.isValidByte$extension(paramLong);
/*    */   }
/*    */   
/*    */   public static String toOctalString$extension(long paramLong) {
/*    */     return RichLong$.MODULE$.toOctalString$extension(paramLong);
/*    */   }
/*    */   
/*    */   public static String toHexString$extension(long paramLong) {
/*    */     return RichLong$.MODULE$.toHexString$extension(paramLong);
/*    */   }
/*    */   
/*    */   public static String toBinaryString$extension(long paramLong) {
/*    */     return RichLong$.MODULE$.toBinaryString$extension(paramLong);
/*    */   }
/*    */   
/*    */   public static Ordering$Long$ ord$extension(long paramLong) {
/*    */     return RichLong$.MODULE$.ord$extension(paramLong);
/*    */   }
/*    */   
/*    */   public static Numeric$LongIsIntegral$ num$extension(long paramLong) {
/*    */     return RichLong$.MODULE$.num$extension(paramLong);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichLong.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */