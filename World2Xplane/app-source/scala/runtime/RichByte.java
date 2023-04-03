/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Proxy;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Numeric$ByteIsIntegral$;
/*    */ import scala.math.Ordered;
/*    */ import scala.math.Ordering;
/*    */ import scala.math.Ordering$Byte$;
/*    */ import scala.math.ScalaNumericAnyConversions;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001!4A!\001\002\003\017\tA!+[2i\005f$XM\003\002\004\t\0059!/\0368uS6,'\"A\003\002\013M\034\027\r\\1\004\001M\031\001\001\003\007\021\005%QQ\"\001\003\n\005-!!AB!osZ\013G\016E\002\016\035Ai\021AA\005\003\037\t\021QcU2bY\006<\006n\0347f\035Vl'-\032:Qe>D\030\020\005\002\n#%\021!\003\002\002\005\005f$X\r\003\005\025\001\t\025\r\021\"\001\026\003\021\031X\r\0344\026\003AA\001b\006\001\003\002\003\006I\001E\001\006g\026dg\r\t\005\0063\001!\tAG\001\007y%t\027\016\036 \025\005ma\002CA\007\001\021\025!\002\0041\001\021\021\025q\002\001\"\005 \003\rqW/\\\013\002A9\021\021%\f\b\003E)r!a\t\025\017\005\021:S\"A\023\013\005\0312\021A\002\037s_>$h(C\001\006\023\tIC!\001\003nCRD\027BA\026-\003\035qU/\\3sS\016T!!\013\003\n\0059z\023A\004\"zi\026L5/\0238uK\036\024\030\r\034\006\003W1BQ!\r\001\005\022I\n1a\034:e+\005\031dB\001\0338\035\t\021S'\003\0027Y\005AqJ\0353fe&tw-\003\0029s\005!!)\037;f\025\t1D\006C\004<\001\005\005I\021\t\037\002\021!\f7\017[\"pI\026$\022!\020\t\003\023yJ!a\020\003\003\007%sG\017C\004B\001\005\005I\021\t\"\002\r\025\fX/\0317t)\t\031e\t\005\002\n\t&\021Q\t\002\002\b\005>|G.Z1o\021\0359\005)!AA\002!\0131\001\037\0232!\tI\021*\003\002K\t\t\031\021I\\=\b\0171\023\021\021!E\001\033\006A!+[2i\005f$X\r\005\002\016\035\0329\021AAA\001\022\003y5C\001(Q!\tI\021+\003\002S\t\t1\021I\\=SK\032DQ!\007(\005\002Q#\022!\024\005\006-:#)aV\001\016]VlG%\032=uK:\034\030n\0348\025\005\001B\006\"B-V\001\004Y\022!\002\023uQ&\034\b\"B.O\t\013a\026!D8sI\022*\007\020^3og&|g\016\006\0024;\")\021L\027a\0017!9qLTA\001\n\013\001\027A\0055bg\"\034u\016Z3%Kb$XM\\:j_:$\"\001P1\t\013es\006\031A\016\t\017\rt\025\021!C\003I\006\001R-];bYN$S\r\037;f]NLwN\034\013\003K\036$\"a\0214\t\017\035\023\027\021!a\001\021\")\021L\031a\0017\001")
/*    */ public final class RichByte implements ScalaWholeNumberProxy<Object> {
/*    */   private final byte self;
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
/*    */   public boolean isValidByte() {
/* 11 */     return ScalaNumericAnyConversions.class.isValidByte(this);
/*    */   }
/*    */   
/*    */   public boolean isValidShort() {
/* 11 */     return ScalaNumericAnyConversions.class.isValidShort(this);
/*    */   }
/*    */   
/*    */   public boolean isValidInt() {
/* 11 */     return ScalaNumericAnyConversions.class.isValidInt(this);
/*    */   }
/*    */   
/*    */   public boolean isValidChar() {
/* 11 */     return ScalaNumericAnyConversions.class.isValidChar(this);
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
/*    */   public byte self() {
/* 11 */     return this.self;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 11 */     return RichByte$.MODULE$.hashCode$extension(self());
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/* 11 */     return RichByte$.MODULE$.equals$extension(self(), x$1);
/*    */   }
/*    */   
/*    */   public RichByte(byte self) {
/* 11 */     ScalaNumericAnyConversions.class.$init$(this);
/* 11 */     Proxy.class.$init$((Proxy)this);
/* 11 */     Ordered.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Numeric$ByteIsIntegral$ num() {
/* 12 */     return RichByte$.MODULE$.num$extension(self());
/*    */   }
/*    */   
/*    */   public Ordering$Byte$ ord() {
/* 13 */     return RichByte$.MODULE$.ord$extension(self());
/*    */   }
/*    */   
/*    */   public static boolean equals$extension(byte paramByte, Object paramObject) {
/*    */     return RichByte$.MODULE$.equals$extension(paramByte, paramObject);
/*    */   }
/*    */   
/*    */   public static int hashCode$extension(byte paramByte) {
/*    */     return RichByte$.MODULE$.hashCode$extension(paramByte);
/*    */   }
/*    */   
/*    */   public static Ordering$Byte$ ord$extension(byte paramByte) {
/*    */     return RichByte$.MODULE$.ord$extension(paramByte);
/*    */   }
/*    */   
/*    */   public static Numeric$ByteIsIntegral$ num$extension(byte paramByte) {
/*    */     return RichByte$.MODULE$.num$extension(paramByte);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichByte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */