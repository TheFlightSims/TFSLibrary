/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Proxy;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.NumericRange;
/*    */ import scala.collection.immutable.Range;
/*    */ import scala.math.Fractional;
/*    */ import scala.math.Integral;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Numeric$FloatAsIfIntegral$;
/*    */ import scala.math.Numeric$FloatIsFractional$;
/*    */ import scala.math.Ordered;
/*    */ import scala.math.Ordering;
/*    */ import scala.math.Ordering$Float$;
/*    */ import scala.math.ScalaNumericAnyConversions;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\035e\001B\001\003\005\035\021\021BU5dQ\032cw.\031;\013\005\r!\021a\002:v]RLW.\032\006\002\013\005)1oY1mC\016\0011c\001\001\t\031A\021\021BC\007\002\t%\0211\002\002\002\007\003:Lh+\0317\021\0075q\001#D\001\003\023\ty!AA\bGe\006\034G/[8oC2\004&o\034=z!\tI\021#\003\002\023\t\t)a\t\\8bi\"AA\003\001BC\002\023\005Q#\001\003tK24W#\001\t\t\021]\001!\021!Q\001\nA\tQa]3mM\002BQ!\007\001\005\002i\ta\001P5oSRtDCA\016\035!\ti\001\001C\003\0251\001\007\001\003C\003\037\001\021Eq$A\002ok6,\022\001\t\b\003C5r!A\t\026\017\005\rBcB\001\023(\033\005)#B\001\024\007\003\031a$o\\8u}%\tQ!\003\002*\t\005!Q.\031;i\023\tYC&A\004Ok6,'/[2\013\005%\"\021B\001\0300\003E1En\\1u\023N4%/Y2uS>t\027\r\034\006\003W1BQ!\r\001\005\022I\n1a\034:e+\005\031dB\001\0338\035\t\021S'\003\0027Y\005AqJ\0353fe&tw-\003\0029s\005)a\t\\8bi*\021a\007\f\005\006w\001!\t\002P\001\fS:$Xm\032:bY:+X.F\001>\035\t\tc(\003\002@_\005\tb\t\\8bi\006\033\030JZ%oi\026<'/\0317\t\013\005\003A\021\001\"\002\013I|WO\0343\026\003\r\003\"!\003#\n\005\025#!aA%oi\")q\t\001C\001+\005!1-Z5m\021\025I\005\001\"\001\026\003\0251Gn\\8s\021\025Y\005\001\"\001\026\003%!xNU1eS\006t7\017C\003N\001\021\005Q#A\005u_\022+wM]3fg\")q\n\001C\001!\006Q\021n]%oM&t\027\016^=\026\003E\003\"!\003*\n\005M#!a\002\"p_2,\027M\034\005\006+\002!\t\001U\001\016SN\004vn]%oM&t\027\016^=\t\013]\003A\021\001)\002\033%\034h*Z4J]\032Lg.\033;z\021\025I\006\001\"\021Q\003-I7OV1mS\022\024\025\020^3\t\013m\003A\021\t)\002\031%\034h+\0317jINCwN\035;\t\013u\003A\021\t)\002\027%\034h+\0317jI\016C\027M\035\005\006?\002!\t\005U\001\013SN4\026\r\\5e\023:$\b\"B1\001\t\003\022\027aB5t/\"|G.\032\013\002#\"9A\rAA\001\n\003*\027\001\0035bg\"\034u\016Z3\025\003\rCqa\032\001\002\002\023\005\003.\001\004fcV\fGn\035\013\003#&DqA\0334\002\002\003\0071.A\002yIE\002\"!\0037\n\0055$!aA!os\0369qNAA\001\022\003\001\030!\003*jG\"4En\\1u!\ti\021OB\004\002\005\005\005\t\022\001:\024\005E\034\bCA\005u\023\t)HA\001\004B]f\024VM\032\005\0063E$\ta\036\013\002a\")\0210\035C\003u\006ia.^7%Kb$XM\\:j_:$\"\001I>\t\013qD\b\031A\016\002\013\021\"\b.[:\t\013y\fHQA@\002\033=\024H\rJ3yi\026t7/[8o)\r\031\024\021\001\005\006yv\004\ra\007\005\b\003\013\tHQAA\004\003UIg\016^3he\006dg*^7%Kb$XM\\:j_:$2!PA\005\021\031a\0301\001a\0017!9\021QB9\005\006\005=\021a\004:pk:$G%\032=uK:\034\030n\0348\025\007\r\013\t\002\003\004}\003\027\001\ra\007\005\b\003+\tHQAA\f\0039\031W-\0337%Kb$XM\\:j_:$2\001EA\r\021\031a\0301\003a\0017!9\021QD9\005\006\005}\021a\0044m_>\024H%\032=uK:\034\030n\0348\025\007A\t\t\003\003\004}\0037\001\ra\007\005\b\003K\tHQAA\024\003M!xNU1eS\006t7\017J3yi\026t7/[8o)\r\001\022\021\006\005\007y\006\r\002\031A\016\t\017\0055\022\017\"\002\0020\005\031Bo\034#fOJ,Wm\035\023fqR,gn]5p]R\031\001#!\r\t\rq\fY\0031\001\034\021\035\t)$\035C\003\003o\tA#[:J]\032Lg.\033;zI\025DH/\0328tS>tGcA)\002:!1A0a\rA\002mAq!!\020r\t\013\ty$A\fjgB{7/\0238gS:LG/\037\023fqR,gn]5p]R\031\021+!\021\t\rq\fY\0041\001\034\021\035\t)%\035C\003\003\017\nq#[:OK\036LeNZ5oSRLH%\032=uK:\034\030n\0348\025\007E\013I\005\003\004}\003\007\002\ra\007\005\b\003\033\nHQAA(\003UI7OV1mS\022\024\025\020^3%Kb$XM\\:j_:$2!UA)\021\031a\0301\na\0017!9\021QK9\005\006\005]\023AF5t-\006d\027\016Z*i_J$H%\032=uK:\034\030n\0348\025\007E\013I\006\003\004}\003'\002\ra\007\005\b\003;\nHQAA0\003UI7OV1mS\022\034\005.\031:%Kb$XM\\:j_:$2!UA1\021\031a\0301\fa\0017!9\021QM9\005\006\005\035\024\001F5t-\006d\027\016Z%oi\022*\007\020^3og&|g\016F\002R\003SBa\001`A2\001\004Y\002bBA7c\022\025\021qN\001\022SN<\006n\0347fI\025DH/\0328tS>tGc\0012\002r!1A0a\033A\002mA\021\"!\036r\003\003%)!a\036\002%!\f7\017[\"pI\026$S\r\037;f]NLwN\034\013\004K\006e\004B\002?\002t\001\0071\004C\005\002~E\f\t\021\"\002\002\000\005\001R-];bYN$S\r\037;f]NLwN\034\013\005\003\003\013)\tF\002R\003\007C\001B[A>\003\003\005\ra\033\005\007y\006m\004\031A\016")
/*    */ public final class RichFloat implements FractionalProxy<Object> {
/*    */   private final float self;
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
/*    */   public float self() {
/* 12 */     return this.self;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 12 */     return RichFloat$.MODULE$.hashCode$extension(self());
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/* 12 */     return RichFloat$.MODULE$.equals$extension(self(), x$1);
/*    */   }
/*    */   
/*    */   public RichFloat(float self) {
/* 12 */     ScalaNumericAnyConversions.class.$init$(this);
/* 12 */     Proxy.class.$init$((Proxy)this);
/* 12 */     Ordered.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Numeric$FloatIsFractional$ num() {
/* 13 */     return RichFloat$.MODULE$.num$extension(self());
/*    */   }
/*    */   
/*    */   public Ordering$Float$ ord() {
/* 14 */     return RichFloat$.MODULE$.ord$extension(self());
/*    */   }
/*    */   
/*    */   public Numeric$FloatAsIfIntegral$ integralNum() {
/* 15 */     return RichFloat$.MODULE$.integralNum$extension(self());
/*    */   }
/*    */   
/*    */   public int round() {
/* 17 */     return RichFloat$.MODULE$.round$extension(self());
/*    */   }
/*    */   
/*    */   public float ceil() {
/* 18 */     return RichFloat$.MODULE$.ceil$extension(self());
/*    */   }
/*    */   
/*    */   public float floor() {
/* 19 */     return RichFloat$.MODULE$.floor$extension(self());
/*    */   }
/*    */   
/*    */   public float toRadians() {
/* 26 */     return RichFloat$.MODULE$.toRadians$extension(self());
/*    */   }
/*    */   
/*    */   public float toDegrees() {
/* 33 */     return RichFloat$.MODULE$.toDegrees$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isInfinity() {
/* 37 */     return RichFloat$.MODULE$.isInfinity$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isPosInfinity() {
/* 38 */     return RichFloat$.MODULE$.isPosInfinity$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isNegInfinity() {
/* 39 */     return RichFloat$.MODULE$.isNegInfinity$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isValidByte() {
/* 41 */     return RichFloat$.MODULE$.isValidByte$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isValidShort() {
/* 42 */     return RichFloat$.MODULE$.isValidShort$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isValidChar() {
/* 43 */     return RichFloat$.MODULE$.isValidChar$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isValidInt() {
/* 44 */     return RichFloat$.MODULE$.isValidInt$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isWhole() {
/* 48 */     return RichFloat$.MODULE$.isWhole$extension(self());
/*    */   }
/*    */   
/*    */   public static boolean equals$extension(float paramFloat, Object paramObject) {
/*    */     return RichFloat$.MODULE$.equals$extension(paramFloat, paramObject);
/*    */   }
/*    */   
/*    */   public static int hashCode$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.hashCode$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static boolean isWhole$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.isWhole$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static boolean isValidInt$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.isValidInt$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static boolean isValidChar$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.isValidChar$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static boolean isValidShort$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.isValidShort$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static boolean isValidByte$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.isValidByte$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static boolean isNegInfinity$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.isNegInfinity$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static boolean isPosInfinity$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.isPosInfinity$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static boolean isInfinity$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.isInfinity$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static float toDegrees$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.toDegrees$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static float toRadians$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.toRadians$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static float floor$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.floor$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static float ceil$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.ceil$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static int round$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.round$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static Numeric$FloatAsIfIntegral$ integralNum$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.integralNum$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static Ordering$Float$ ord$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.ord$extension(paramFloat);
/*    */   }
/*    */   
/*    */   public static Numeric$FloatIsFractional$ num$extension(float paramFloat) {
/*    */     return RichFloat$.MODULE$.num$extension(paramFloat);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichFloat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */