/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Proxy;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Numeric$ShortIsIntegral$;
/*    */ import scala.math.Ordered;
/*    */ import scala.math.Ordering;
/*    */ import scala.math.Ordering$Short$;
/*    */ import scala.math.ScalaNumericAnyConversions;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001!4A!\001\002\003\017\tI!+[2i'\"|'\017\036\006\003\007\021\tqA];oi&lWMC\001\006\003\025\0318-\0317b\007\001\0312\001\001\005\r!\tI!\"D\001\005\023\tYAA\001\004B]f4\026\r\034\t\004\0339\001R\"\001\002\n\005=\021!!F*dC2\fw\013[8mK:+XNY3s!J|\0070\037\t\003\023EI!A\005\003\003\013MCwN\035;\t\021Q\001!Q1A\005\002U\tAa]3mMV\t\001\003\003\005\030\001\t\005\t\025!\003\021\003\025\031X\r\0344!\021\025I\002\001\"\001\033\003\031a\024N\\5u}Q\0211\004\b\t\003\033\001AQ\001\006\rA\002AAQA\b\001\005\022}\t1A\\;n+\005\001cBA\021.\035\t\021#F\004\002$Q9\021AeJ\007\002K)\021aEB\001\007yI|w\016\036 \n\003\025I!!\013\003\002\t5\fG\017[\005\003W1\nqAT;nKJL7M\003\002*\t%\021afL\001\020'\"|'\017^%t\023:$Xm\032:bY*\0211\006\f\005\006c\001!\tBM\001\004_J$W#A\032\017\005Q:dB\001\0226\023\t1D&\001\005Pe\022,'/\0338h\023\tA\024(A\003TQ>\024HO\003\0027Y!91\bAA\001\n\003b\024\001\0035bg\"\034u\016Z3\025\003u\002\"!\003 \n\005}\"!aA%oi\"9\021\tAA\001\n\003\022\025AB3rk\006d7\017\006\002D\rB\021\021\002R\005\003\013\022\021qAQ8pY\026\fg\016C\004H\001\006\005\t\031\001%\002\007a$\023\007\005\002\n\023&\021!\n\002\002\004\003:Lxa\002'\003\003\003E\t!T\001\n%&\034\007n\0255peR\004\"!\004(\007\017\005\021\021\021!E\001\037N\021a\n\025\t\003\023EK!A\025\003\003\r\005s\027PU3g\021\025Ib\n\"\001U)\005i\005\"\002,O\t\0139\026!\0048v[\022*\007\020^3og&|g\016\006\002!1\")\021,\026a\0017\005)A\005\0365jg\")1L\024C\0039\006iqN\0353%Kb$XM\\:j_:$\"aM/\t\013eS\006\031A\016\t\017}s\025\021!C\003A\006\021\002.Y:i\007>$W\rJ3yi\026t7/[8o)\ta\024\rC\003Z=\002\0071\004C\004d\035\006\005IQ\0013\002!\025\fX/\0317tI\025DH/\0328tS>tGCA3h)\t\031e\rC\004HE\006\005\t\031\001%\t\013e\023\007\031A\016")
/*    */ public final class RichShort implements ScalaWholeNumberProxy<Object> {
/*    */   private final short self;
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
/*    */   public short self() {
/* 11 */     return this.self;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 11 */     return RichShort$.MODULE$.hashCode$extension(self());
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/* 11 */     return RichShort$.MODULE$.equals$extension(self(), x$1);
/*    */   }
/*    */   
/*    */   public RichShort(short self) {
/* 11 */     ScalaNumericAnyConversions.class.$init$(this);
/* 11 */     Proxy.class.$init$((Proxy)this);
/* 11 */     Ordered.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Numeric$ShortIsIntegral$ num() {
/* 12 */     return RichShort$.MODULE$.num$extension(self());
/*    */   }
/*    */   
/*    */   public Ordering$Short$ ord() {
/* 13 */     return RichShort$.MODULE$.ord$extension(self());
/*    */   }
/*    */   
/*    */   public static boolean equals$extension(short paramShort, Object paramObject) {
/*    */     return RichShort$.MODULE$.equals$extension(paramShort, paramObject);
/*    */   }
/*    */   
/*    */   public static int hashCode$extension(short paramShort) {
/*    */     return RichShort$.MODULE$.hashCode$extension(paramShort);
/*    */   }
/*    */   
/*    */   public static Ordering$Short$ ord$extension(short paramShort) {
/*    */     return RichShort$.MODULE$.ord$extension(paramShort);
/*    */   }
/*    */   
/*    */   public static Numeric$ShortIsIntegral$ num$extension(short paramShort) {
/*    */     return RichShort$.MODULE$.num$extension(paramShort);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichShort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */