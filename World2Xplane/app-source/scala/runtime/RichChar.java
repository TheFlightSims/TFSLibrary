/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Proxy;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.NumericRange;
/*    */ import scala.math.Integral;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Numeric$CharIsIntegral$;
/*    */ import scala.math.Ordered;
/*    */ import scala.math.Ordering;
/*    */ import scala.math.Ordering$Char$;
/*    */ import scala.math.ScalaNumericAnyConversions;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005uh\001B\001\003\005\035\021\001BU5dQ\016C\027M\035\006\003\007\021\tqA];oi&lWMC\001\006\003\025\0318-\0317b\007\001\0312\001\001\005\r!\tI!\"D\001\005\023\tYAA\001\004B]f4\026\r\034\t\004\0339\001R\"\001\002\n\005=\021!!D%oi\026<'/\0317Qe>D\030\020\005\002\n#%\021!\003\002\002\005\007\"\f'\017\003\005\025\001\t\025\r\021\"\001\026\003\021\031X\r\0344\026\003AA\001b\006\001\003\002\003\006I\001E\001\006g\026dg\r\t\005\0063\001!\tAG\001\007y%t\027\016\036 \025\005ma\002CA\007\001\021\025!\002\0041\001\021\021\025q\002\001\"\005 \003\rqW/\\\013\002A9\021\021%\f\b\003E)r!a\t\025\017\005\021:S\"A\023\013\005\0312\021A\002\037s_>$h(C\001\006\023\tIC!\001\003nCRD\027BA\026-\003\035qU/\\3sS\016T!!\013\003\n\0059z\023AD\"iCJL5/\0238uK\036\024\030\r\034\006\003W1BQ!\r\001\005\022I\n1a\034:e+\005\031dB\001\0338\035\t\021S'\003\0027Y\005AqJ\0353fe&tw-\003\0029s\005!1\t[1s\025\t1D\006C\003<\001\021\005A(A\004bg\022Kw-\033;\026\003u\002\"!\003 \n\005}\"!aA%oi\")\021\t\001C\001\005\006I\021n]\"p]R\024x\016\\\013\002\007B\021\021\002R\005\003\013\022\021qAQ8pY\026\fg\016C\003H\001\021\005!)A\004jg\022Kw-\033;\t\013%\003A\021\001\"\002\021%\034H*\032;uKJDQa\023\001\005\002\t\013q\"[:MKR$XM](s\t&<\027\016\036\005\006\033\002!\tAQ\001\rSN<\006.\033;fgB\f7-\032\005\006\037\002!\tAQ\001\fSN\034\006/Y2f\007\"\f'\017C\003R\001\021\005!)A\bjg\"Kw\r[*veJ|w-\031;f\021\025\031\006\001\"\001C\0039I7\017T8x'V\024(o\\4bi\026DQ!\026\001\005\002\t\0131\"[:TkJ\024xnZ1uK\")q\013\001C\001\005\006A\022n]+oS\016|G-Z%eK:$\030NZ5feN#\030M\035;\t\013e\003A\021\001\"\002/%\034XK\\5d_\022,\027\nZ3oi&4\027.\032:QCJ$\b\"B.\001\t\003\021\025!F5t\023\022,g\016^5gS\026\024\030j\0328pe\006\024G.\032\005\006;\002!\tAQ\001\013SNl\025N\035:pe\026$\007\"B0\001\t\003\021\025aB5t\031><XM\035\005\006C\002!\tAQ\001\bSN,\006\017]3s\021\025\031\007\001\"\001C\003-I7\017V5uY\026\034\025m]3\t\013\025\004A\021A\013\002\017Q|Gj\\<fe\")q\r\001C\001+\0059Ao\\+qa\026\024\b\"B5\001\t\003)\022a\003;p)&$H.Z\"bg\026DQa\033\001\005\002q\nqaZ3u)f\004X\rC\003n\001\021\005A(A\bhKRtU/\\3sS\0164\026\r\\;f\021\025y\007\001\"\001q\003E9W\r\036#je\026\034G/[8oC2LG/_\013\002cB\021\021B]\005\003g\022\021AAQ=uK\")Q\017\001C\001+\005a!/\032<feN,')\037;fg\"9q\017AA\001\n\003B\030\001\0035bg\"\034u\016Z3\025\003uBqA\037\001\002\002\023\00530\001\004fcV\fGn\035\013\003\007rDq!`=\002\002\003\007a0A\002yIE\002\"!C@\n\007\005\005AAA\002B]f<\021\"!\002\003\003\003E\t!a\002\002\021IK7\r[\"iCJ\0042!DA\005\r!\t!!!A\t\002\005-1\003BA\005\003\033\0012!CA\b\023\r\t\t\002\002\002\007\003:L(+\0324\t\017e\tI\001\"\001\002\026Q\021\021q\001\005\t\0033\tI\001\"\002\002\034\005ia.^7%Kb$XM\\:j_:$2\001IA\017\021\035\ty\"a\006A\002m\tQ\001\n;iSND\001\"a\t\002\n\021\025\021QE\001\016_J$G%\032=uK:\034\030n\0348\025\007M\n9\003C\004\002 \005\005\002\031A\016\t\021\005-\022\021\002C\003\003[\t\021#Y:ES\036LG\017J3yi\026t7/[8o)\ri\024q\006\005\b\003?\tI\0031\001\034\021!\t\031$!\003\005\006\005U\022aE5t\007>tGO]8mI\025DH/\0328tS>tGcA\"\0028!9\021qDA\031\001\004Y\002\002CA\036\003\023!)!!\020\002#%\034H)[4ji\022*\007\020^3og&|g\016F\002D\003Aq!a\b\002:\001\0071\004\003\005\002D\005%AQAA#\003II7\017T3ui\026\024H%\032=uK:\034\030n\0348\025\007\r\0139\005C\004\002 \005\005\003\031A\016\t\021\005-\023\021\002C\003\003\033\n\021$[:MKR$XM](s\t&<\027\016\036\023fqR,gn]5p]R\0311)a\024\t\017\005}\021\021\na\0017!A\0211KA\005\t\013\t)&\001\fjg^C\027\016^3ta\006\034W\rJ3yi\026t7/[8o)\r\031\025q\013\005\b\003?\t\t\0061\001\034\021!\tY&!\003\005\006\005u\023!F5t'B\f7-Z\"iCJ$S\r\037;f]NLwN\034\013\004\007\006}\003bBA\020\0033\002\ra\007\005\t\003G\nI\001\"\002\002f\005I\022n\035%jO\"\034VO\035:pO\006$X\rJ3yi\026t7/[8o)\r\031\025q\r\005\b\003?\t\t\0071\001\034\021!\tY'!\003\005\006\0055\024\001G5t\031><8+\036:s_\036\fG/\032\023fqR,gn]5p]R\0311)a\034\t\017\005}\021\021\016a\0017!A\0211OA\005\t\013\t)(A\013jgN+(O]8hCR,G%\032=uK:\034\030n\0348\025\007\r\0139\bC\004\002 \005E\004\031A\016\t\021\005m\024\021\002C\003\003{\n!%[:V]&\034w\016Z3JI\026tG/\0334jKJ\034F/\031:uI\025DH/\0328tS>tGcA\"\002\000!9\021qDA=\001\004Y\002\002CAB\003\023!)!!\"\002C%\034XK\\5d_\022,\027\nZ3oi&4\027.\032:QCJ$H%\032=uK:\034\030n\0348\025\007\r\0139\tC\004\002 \005\005\005\031A\016\t\021\005-\025\021\002C\003\003\033\013q$[:JI\026tG/\0334jKJLuM\\8sC\ndW\rJ3yi\026t7/[8o)\r\031\025q\022\005\b\003?\tI\t1\001\034\021!\t\031*!\003\005\006\005U\025\001F5t\033&\024(o\034:fI\022*\007\020^3og&|g\016F\002D\003/Cq!a\b\002\022\002\0071\004\003\005\002\034\006%AQAAO\003EI7\017T8xKJ$S\r\037;f]NLwN\034\013\004\007\006}\005bBA\020\0033\003\ra\007\005\t\003G\013I\001\"\002\002&\006\t\022n]+qa\026\024H%\032=uK:\034\030n\0348\025\007\r\0139\013C\004\002 \005\005\006\031A\016\t\021\005-\026\021\002C\003\003[\013Q#[:USRdWmQ1tK\022*\007\020^3og&|g\016F\002D\003_Cq!a\b\002*\002\0071\004\003\005\0024\006%AQAA[\003E!x\016T8xKJ$S\r\037;f]NLwN\034\013\004!\005]\006bBA\020\003c\003\ra\007\005\t\003w\013I\001\"\002\002>\006\tBo\\+qa\026\024H%\032=uK:\034\030n\0348\025\007A\ty\fC\004\002 \005e\006\031A\016\t\021\005\r\027\021\002C\003\003\013\fQ\003^8USRdWmQ1tK\022*\007\020^3og&|g\016F\002\021\003\017Dq!a\b\002B\002\0071\004\003\005\002L\006%AQAAg\003E9W\r\036+za\026$S\r\037;f]NLwN\034\013\004{\005=\007bBA\020\003\023\004\ra\007\005\t\003'\fI\001\"\002\002V\006Ir-\032;Ok6,'/[2WC2,X\rJ3yi\026t7/[8o)\ri\024q\033\005\b\003?\t\t\0161\001\034\021!\tY.!\003\005\006\005u\027aG4fi\022K'/Z2uS>t\027\r\\5us\022*\007\020^3og&|g\016F\002r\003?Dq!a\b\002Z\002\0071\004\003\005\002d\006%AQAAs\003Y\021XM^3sg\026\024\025\020^3tI\025DH/\0328tS>tGc\001\t\002h\"9\021qDAq\001\004Y\002BCAv\003\023\t\t\021\"\002\002n\006\021\002.Y:i\007>$W\rJ3yi\026t7/[8o)\rA\030q\036\005\b\003?\tI\0171\001\034\021)\t\0310!\003\002\002\023\025\021Q_\001\021KF,\030\r\\:%Kb$XM\\:j_:$B!a>\002|R\0311)!?\t\021u\f\t0!AA\002yDq!a\b\002r\002\0071\004")
/*    */ public final class RichChar implements IntegralProxy<Object> {
/*    */   private final char self;
/*    */   
/*    */   public NumericRange.Exclusive<Object> until(Object end) {
/* 13 */     return IntegralProxy$class.until(this, end);
/*    */   }
/*    */   
/*    */   public NumericRange.Exclusive<Object> until(Object end, Object step) {
/* 13 */     return IntegralProxy$class.until(this, end, step);
/*    */   }
/*    */   
/*    */   public NumericRange.Inclusive<Object> to(Object end) {
/* 13 */     return IntegralProxy$class.to(this, end);
/*    */   }
/*    */   
/*    */   public NumericRange.Inclusive<Object> to(Object end, Object step) {
/* 13 */     return IntegralProxy$class.to(this, end, step);
/*    */   }
/*    */   
/*    */   public boolean isWhole() {
/* 13 */     return ScalaWholeNumberProxy$class.isWhole(this);
/*    */   }
/*    */   
/*    */   public Object underlying() {
/* 13 */     return ScalaNumberProxy$class.underlying(this);
/*    */   }
/*    */   
/*    */   public double doubleValue() {
/* 13 */     return ScalaNumberProxy$class.doubleValue(this);
/*    */   }
/*    */   
/*    */   public float floatValue() {
/* 13 */     return ScalaNumberProxy$class.floatValue(this);
/*    */   }
/*    */   
/*    */   public long longValue() {
/* 13 */     return ScalaNumberProxy$class.longValue(this);
/*    */   }
/*    */   
/*    */   public int intValue() {
/* 13 */     return ScalaNumberProxy$class.intValue(this);
/*    */   }
/*    */   
/*    */   public byte byteValue() {
/* 13 */     return ScalaNumberProxy$class.byteValue(this);
/*    */   }
/*    */   
/*    */   public short shortValue() {
/* 13 */     return ScalaNumberProxy$class.shortValue(this);
/*    */   }
/*    */   
/*    */   public Object min(Object that) {
/* 13 */     return ScalaNumberProxy$class.min(this, that);
/*    */   }
/*    */   
/*    */   public Object max(Object that) {
/* 13 */     return ScalaNumberProxy$class.max(this, that);
/*    */   }
/*    */   
/*    */   public Object abs() {
/* 13 */     return ScalaNumberProxy$class.abs(this);
/*    */   }
/*    */   
/*    */   public int signum() {
/* 13 */     return ScalaNumberProxy$class.signum(this);
/*    */   }
/*    */   
/*    */   public int compare(Object y) {
/* 13 */     return OrderedProxy$class.compare(this, y);
/*    */   }
/*    */   
/*    */   public boolean $less(Object that) {
/* 13 */     return Ordered.class.$less(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater(Object that) {
/* 13 */     return Ordered.class.$greater(this, that);
/*    */   }
/*    */   
/*    */   public boolean $less$eq(Object that) {
/* 13 */     return Ordered.class.$less$eq(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater$eq(Object that) {
/* 13 */     return Ordered.class.$greater$eq(this, that);
/*    */   }
/*    */   
/*    */   public int compareTo(Object that) {
/* 13 */     return Ordered.class.compareTo(this, that);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 13 */     return Proxy.class.toString((Proxy)this);
/*    */   }
/*    */   
/*    */   public char toChar() {
/* 13 */     return ScalaNumericAnyConversions.class.toChar(this);
/*    */   }
/*    */   
/*    */   public byte toByte() {
/* 13 */     return ScalaNumericAnyConversions.class.toByte(this);
/*    */   }
/*    */   
/*    */   public short toShort() {
/* 13 */     return ScalaNumericAnyConversions.class.toShort(this);
/*    */   }
/*    */   
/*    */   public int toInt() {
/* 13 */     return ScalaNumericAnyConversions.class.toInt(this);
/*    */   }
/*    */   
/*    */   public long toLong() {
/* 13 */     return ScalaNumericAnyConversions.class.toLong(this);
/*    */   }
/*    */   
/*    */   public float toFloat() {
/* 13 */     return ScalaNumericAnyConversions.class.toFloat(this);
/*    */   }
/*    */   
/*    */   public double toDouble() {
/* 13 */     return ScalaNumericAnyConversions.class.toDouble(this);
/*    */   }
/*    */   
/*    */   public boolean isValidByte() {
/* 13 */     return ScalaNumericAnyConversions.class.isValidByte(this);
/*    */   }
/*    */   
/*    */   public boolean isValidShort() {
/* 13 */     return ScalaNumericAnyConversions.class.isValidShort(this);
/*    */   }
/*    */   
/*    */   public boolean isValidInt() {
/* 13 */     return ScalaNumericAnyConversions.class.isValidInt(this);
/*    */   }
/*    */   
/*    */   public boolean isValidChar() {
/* 13 */     return ScalaNumericAnyConversions.class.isValidChar(this);
/*    */   }
/*    */   
/*    */   public int unifiedPrimitiveHashcode() {
/* 13 */     return ScalaNumericAnyConversions.class.unifiedPrimitiveHashcode(this);
/*    */   }
/*    */   
/*    */   public boolean unifiedPrimitiveEquals(Object x) {
/* 13 */     return ScalaNumericAnyConversions.class.unifiedPrimitiveEquals(this, x);
/*    */   }
/*    */   
/*    */   public char self() {
/* 13 */     return this.self;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 13 */     return RichChar$.MODULE$.hashCode$extension(self());
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/* 13 */     return RichChar$.MODULE$.equals$extension(self(), x$1);
/*    */   }
/*    */   
/*    */   public RichChar(char self) {
/* 13 */     ScalaNumericAnyConversions.class.$init$(this);
/* 13 */     Proxy.class.$init$((Proxy)this);
/* 13 */     Ordered.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Numeric$CharIsIntegral$ num() {
/* 14 */     return RichChar$.MODULE$.num$extension(self());
/*    */   }
/*    */   
/*    */   public Ordering$Char$ ord() {
/* 15 */     return RichChar$.MODULE$.ord$extension(self());
/*    */   }
/*    */   
/*    */   public int asDigit() {
/* 17 */     return RichChar$.MODULE$.asDigit$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isControl() {
/* 19 */     return RichChar$.MODULE$.isControl$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isDigit() {
/* 20 */     return RichChar$.MODULE$.isDigit$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isLetter() {
/* 21 */     return RichChar$.MODULE$.isLetter$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isLetterOrDigit() {
/* 22 */     return RichChar$.MODULE$.isLetterOrDigit$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isWhitespace() {
/* 23 */     return RichChar$.MODULE$.isWhitespace$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isSpaceChar() {
/* 24 */     return RichChar$.MODULE$.isSpaceChar$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isHighSurrogate() {
/* 25 */     return RichChar$.MODULE$.isHighSurrogate$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isLowSurrogate() {
/* 26 */     return RichChar$.MODULE$.isLowSurrogate$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isSurrogate() {
/* 27 */     return RichChar$.MODULE$.isSurrogate$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isUnicodeIdentifierStart() {
/* 28 */     return RichChar$.MODULE$.isUnicodeIdentifierStart$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isUnicodeIdentifierPart() {
/* 29 */     return RichChar$.MODULE$.isUnicodeIdentifierPart$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isIdentifierIgnorable() {
/* 30 */     return RichChar$.MODULE$.isIdentifierIgnorable$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isMirrored() {
/* 31 */     return RichChar$.MODULE$.isMirrored$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isLower() {
/* 33 */     return RichChar$.MODULE$.isLower$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isUpper() {
/* 34 */     return RichChar$.MODULE$.isUpper$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isTitleCase() {
/* 35 */     return RichChar$.MODULE$.isTitleCase$extension(self());
/*    */   }
/*    */   
/*    */   public char toLower() {
/* 37 */     return RichChar$.MODULE$.toLower$extension(self());
/*    */   }
/*    */   
/*    */   public char toUpper() {
/* 38 */     return RichChar$.MODULE$.toUpper$extension(self());
/*    */   }
/*    */   
/*    */   public char toTitleCase() {
/* 39 */     return RichChar$.MODULE$.toTitleCase$extension(self());
/*    */   }
/*    */   
/*    */   public int getType() {
/* 41 */     return RichChar$.MODULE$.getType$extension(self());
/*    */   }
/*    */   
/*    */   public int getNumericValue() {
/* 42 */     return RichChar$.MODULE$.getNumericValue$extension(self());
/*    */   }
/*    */   
/*    */   public byte getDirectionality() {
/* 43 */     return RichChar$.MODULE$.getDirectionality$extension(self());
/*    */   }
/*    */   
/*    */   public char reverseBytes() {
/* 44 */     return RichChar$.MODULE$.reverseBytes$extension(self());
/*    */   }
/*    */   
/*    */   public static boolean equals$extension(char paramChar, Object paramObject) {
/*    */     return RichChar$.MODULE$.equals$extension(paramChar, paramObject);
/*    */   }
/*    */   
/*    */   public static int hashCode$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.hashCode$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static char reverseBytes$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.reverseBytes$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static byte getDirectionality$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.getDirectionality$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static int getNumericValue$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.getNumericValue$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static int getType$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.getType$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static char toTitleCase$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.toTitleCase$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static char toUpper$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.toUpper$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static char toLower$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.toLower$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isTitleCase$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isTitleCase$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isUpper$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isUpper$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isLower$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isLower$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isMirrored$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isMirrored$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isIdentifierIgnorable$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isIdentifierIgnorable$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isUnicodeIdentifierPart$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isUnicodeIdentifierPart$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isUnicodeIdentifierStart$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isUnicodeIdentifierStart$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isSurrogate$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isSurrogate$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isLowSurrogate$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isLowSurrogate$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isHighSurrogate$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isHighSurrogate$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isSpaceChar$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isSpaceChar$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isWhitespace$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isWhitespace$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isLetterOrDigit$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isLetterOrDigit$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isLetter$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isLetter$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isDigit$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isDigit$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static boolean isControl$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.isControl$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static int asDigit$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.asDigit$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static Ordering$Char$ ord$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.ord$extension(paramChar);
/*    */   }
/*    */   
/*    */   public static Numeric$CharIsIntegral$ num$extension(char paramChar) {
/*    */     return RichChar$.MODULE$.num$extension(paramChar);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichChar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */