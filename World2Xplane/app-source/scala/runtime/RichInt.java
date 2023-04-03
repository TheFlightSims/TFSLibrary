/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Proxy;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.Range;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Numeric$IntIsIntegral$;
/*    */ import scala.math.Ordered;
/*    */ import scala.math.Ordering;
/*    */ import scala.math.Ordering$Int$;
/*    */ import scala.math.ScalaNumericAnyConversions;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\rg\001B\001\003\005\035\021qAU5dQ&sGO\003\002\004\t\0059!/\0368uS6,'\"A\003\002\013M\034\027\r\\1\004\001M!\001\001\003\007\024!\tI!\"D\001\005\023\tYAA\001\004B]f4\026\r\034\t\004\0339\001R\"\001\002\n\005=\021!\001E*dC2\fg*^7cKJ\004&o\034=z!\tI\021#\003\002\023\t\t\031\021J\034;\021\0075!\002#\003\002\026\005\tY!+\0318hK\022\004&o\034=z\021!9\002A!b\001\n\003A\022\001B:fY\032,\022\001\005\005\t5\001\021\t\021)A\005!\005)1/\0327gA!)A\004\001C\001;\0051A(\0338jiz\"\"AH\020\021\0055\001\001\"B\f\034\001\004\001\002\"B\021\001\t#\021\023a\0018v[V\t1E\004\002%a9\021Q%\f\b\003M-r!a\n\026\016\003!R!!\013\004\002\rq\022xn\034;?\023\005)\021B\001\027\005\003\021i\027\r\0365\n\0059z\023a\002(v[\026\024\030n\031\006\003Y\021I!!\r\032\002\033%sG/S:J]R,wM]1m\025\tqs\006C\0035\001\021EQ'A\002pe\022,\022A\016\b\003oir!!\n\035\n\005ez\023\001C(sI\026\024\030N\\4\n\005mb\024aA%oi*\021\021hL\003\005}\001\001qHA\tSKN,H\016^,ji\"|W\017^*uKB\004\"\001Q#\016\003\005S!AQ\"\002\023%lW.\036;bE2,'B\001#\005\003)\031w\016\0347fGRLwN\\\005\003\r\006\023QAU1oO\026DQ\001\023\001\005\002%\013q![:XQ>dW\rF\001K!\tI1*\003\002M\t\t9!i\\8mK\006t\007\"\002(\001\t\003y\025!B;oi&dGCA Q\021\025\tV\n1\001\021\003\r)g\016\032\005\006\035\002!\ta\025\013\004Q+\006\"B)S\001\004\001\002\"\002,S\001\004\001\022\001B:uKBDQ\001\027\001\005\002e\013!\001^8\025\005i\013\007CA._\035\t\001E,\003\002^\003\006)!+\0318hK&\021q\f\031\002\n\023:\034G.^:jm\026T!!X!\t\013E;\006\031\001\t\t\013a\003A\021A2\025\007i#W\rC\003RE\002\007\001\003C\003WE\002\007\001\003C\003h\001\021\005\003.A\002nS:$\"\001E5\t\013)4\007\031\001\t\002\tQD\027\r\036\005\006Y\002!\t%\\\001\004[\006DHC\001\to\021\025Q7\0161\001\021\021\025\001\b\001\"\021\031\003\r\t'm\035\005\006e\002!\ta]\001\017i>\024\025N\\1ssN#(/\0338h+\005!\bCA;y\035\tIa/\003\002x\t\0051\001K]3eK\032L!!\037>\003\rM#(/\0338h\025\t9H\001C\003}\001\021\0051/A\006u_\"+\007p\025;sS:<\007\"\002@\001\t\003\031\030!\004;p\037\016$\030\r\\*ue&tw\rC\005\002\002\001\t\t\021\"\021\002\004\005A\001.Y:i\007>$W\rF\001\021\021%\t9\001AA\001\n\003\nI!\001\004fcV\fGn\035\013\004\025\006-\001BCA\007\003\013\t\t\0211\001\002\020\005\031\001\020J\031\021\007%\t\t\"C\002\002\024\021\0211!\0218z\017%\t9BAA\001\022\003\tI\"A\004SS\016D\027J\034;\021\0075\tYB\002\005\002\005\005\005\t\022AA\017'\021\tY\"a\b\021\007%\t\t#C\002\002$\021\021a!\0218z%\0264\007b\002\017\002\034\021\005\021q\005\013\003\0033A\001\"a\013\002\034\021\025\021QF\001\016]VlG%\032=uK:\034\030n\0348\025\007\r\ny\003C\004\0022\005%\002\031\001\020\002\013\021\"\b.[:\t\021\005U\0221\004C\003\003o\tQb\034:eI\025DH/\0328tS>tGc\001\034\002:!9\021\021GA\032\001\004q\002\002CA\037\0037!)!a\020\002#%\034x\013[8mK\022*\007\020^3og&|g\016F\002J\003\003Bq!!\r\002<\001\007a\004\003\005\002F\005mAQAA$\003A)h\016^5mI\025DH/\0328tS>t\007\007\006\003\002J\0055CcA \002L!1\021+a\021A\002AAq!!\r\002D\001\007a\004\003\005\002R\005mAQAA*\003A)h\016^5mI\025DH/\0328tS>t\027\007\006\003\002V\005mC#B \002X\005e\003BB)\002P\001\007\001\003\003\004W\003\037\002\r\001\005\005\b\003c\ty\0051\001\037\021!\ty&a\007\005\006\005\005\024!\004;pI\025DH/\0328tS>t\007\007\006\003\002d\005\035Dc\001.\002f!1\021+!\030A\002AAq!!\r\002^\001\007a\004\003\005\002l\005mAQAA7\0035!x\016J3yi\026t7/[8ocQ!\021qNA;)\025Q\026\021OA:\021\031\t\026\021\016a\001!!1a+!\033A\002AAq!!\r\002j\001\007a\004\003\005\002z\005mAQAA>\0035i\027N\034\023fqR,gn]5p]R!\021QPAA)\r\001\022q\020\005\007U\006]\004\031\001\t\t\017\005E\022q\017a\001=!A\021QQA\016\t\013\t9)A\007nCb$S\r\037;f]NLwN\034\013\005\003\023\013i\tF\002\021\003\027CaA[AB\001\004\001\002bBA\031\003\007\003\rA\b\005\t\003#\013Y\002\"\002\002\024\006i\021MY:%Kb$XM\\:j_:$2\001EAK\021\035\t\t$a$A\002yA\001\"!'\002\034\021\025\0211T\001\031i>\024\025N\\1ssN#(/\0338hI\025DH/\0328tS>tGc\001;\002\036\"9\021\021GAL\001\004q\002\002CAQ\0037!)!a)\002+Q|\007*\032=TiJLgn\032\023fqR,gn]5p]R\031A/!*\t\017\005E\022q\024a\001=!A\021\021VA\016\t\013\tY+A\fu_>\033G/\0317TiJLgn\032\023fqR,gn]5p]R\031A/!,\t\017\005E\022q\025a\001=!Q\021\021WA\016\003\003%)!a-\002%!\f7\017[\"pI\026$S\r\037;f]NLwN\034\013\005\003\007\t)\fC\004\0022\005=\006\031\001\020\t\025\005e\0261DA\001\n\013\tY,\001\tfcV\fGn\035\023fqR,gn]5p]R!\021QXAa)\rQ\025q\030\005\013\003\033\t9,!AA\002\005=\001bBA\031\003o\003\rA\b")
/*    */ public final class RichInt implements ScalaNumberProxy<Object>, RangedProxy<Object> {
/*    */   private final int self;
/*    */   
/*    */   public Object underlying() {
/* 15 */     return ScalaNumberProxy$class.underlying(this);
/*    */   }
/*    */   
/*    */   public double doubleValue() {
/* 15 */     return ScalaNumberProxy$class.doubleValue(this);
/*    */   }
/*    */   
/*    */   public float floatValue() {
/* 15 */     return ScalaNumberProxy$class.floatValue(this);
/*    */   }
/*    */   
/*    */   public long longValue() {
/* 15 */     return ScalaNumberProxy$class.longValue(this);
/*    */   }
/*    */   
/*    */   public int intValue() {
/* 15 */     return ScalaNumberProxy$class.intValue(this);
/*    */   }
/*    */   
/*    */   public byte byteValue() {
/* 15 */     return ScalaNumberProxy$class.byteValue(this);
/*    */   }
/*    */   
/*    */   public short shortValue() {
/* 15 */     return ScalaNumberProxy$class.shortValue(this);
/*    */   }
/*    */   
/*    */   public int signum() {
/* 15 */     return ScalaNumberProxy$class.signum(this);
/*    */   }
/*    */   
/*    */   public int compare(Object y) {
/* 15 */     return OrderedProxy$class.compare(this, y);
/*    */   }
/*    */   
/*    */   public boolean $less(Object that) {
/* 15 */     return Ordered.class.$less(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater(Object that) {
/* 15 */     return Ordered.class.$greater(this, that);
/*    */   }
/*    */   
/*    */   public boolean $less$eq(Object that) {
/* 15 */     return Ordered.class.$less$eq(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater$eq(Object that) {
/* 15 */     return Ordered.class.$greater$eq(this, that);
/*    */   }
/*    */   
/*    */   public int compareTo(Object that) {
/* 15 */     return Ordered.class.compareTo(this, that);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 15 */     return Proxy.class.toString((Proxy)this);
/*    */   }
/*    */   
/*    */   public char toChar() {
/* 15 */     return ScalaNumericAnyConversions.class.toChar(this);
/*    */   }
/*    */   
/*    */   public byte toByte() {
/* 15 */     return ScalaNumericAnyConversions.class.toByte(this);
/*    */   }
/*    */   
/*    */   public short toShort() {
/* 15 */     return ScalaNumericAnyConversions.class.toShort(this);
/*    */   }
/*    */   
/*    */   public int toInt() {
/* 15 */     return ScalaNumericAnyConversions.class.toInt(this);
/*    */   }
/*    */   
/*    */   public long toLong() {
/* 15 */     return ScalaNumericAnyConversions.class.toLong(this);
/*    */   }
/*    */   
/*    */   public float toFloat() {
/* 15 */     return ScalaNumericAnyConversions.class.toFloat(this);
/*    */   }
/*    */   
/*    */   public double toDouble() {
/* 15 */     return ScalaNumericAnyConversions.class.toDouble(this);
/*    */   }
/*    */   
/*    */   public boolean isValidByte() {
/* 15 */     return ScalaNumericAnyConversions.class.isValidByte(this);
/*    */   }
/*    */   
/*    */   public boolean isValidShort() {
/* 15 */     return ScalaNumericAnyConversions.class.isValidShort(this);
/*    */   }
/*    */   
/*    */   public boolean isValidInt() {
/* 15 */     return ScalaNumericAnyConversions.class.isValidInt(this);
/*    */   }
/*    */   
/*    */   public boolean isValidChar() {
/* 15 */     return ScalaNumericAnyConversions.class.isValidChar(this);
/*    */   }
/*    */   
/*    */   public int unifiedPrimitiveHashcode() {
/* 15 */     return ScalaNumericAnyConversions.class.unifiedPrimitiveHashcode(this);
/*    */   }
/*    */   
/*    */   public boolean unifiedPrimitiveEquals(Object x) {
/* 15 */     return ScalaNumericAnyConversions.class.unifiedPrimitiveEquals(this, x);
/*    */   }
/*    */   
/*    */   public int self() {
/* 15 */     return this.self;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 15 */     return RichInt$.MODULE$.hashCode$extension(self());
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/* 15 */     return RichInt$.MODULE$.equals$extension(self(), x$1);
/*    */   }
/*    */   
/*    */   public RichInt(int self) {
/* 15 */     ScalaNumericAnyConversions.class.$init$(this);
/* 15 */     Proxy.class.$init$((Proxy)this);
/* 15 */     Ordered.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Numeric$IntIsIntegral$ num() {
/* 16 */     return RichInt$.MODULE$.num$extension(self());
/*    */   }
/*    */   
/*    */   public Ordering$Int$ ord() {
/* 17 */     return RichInt$.MODULE$.ord$extension(self());
/*    */   }
/*    */   
/*    */   public boolean isWhole() {
/* 24 */     return RichInt$.MODULE$.isWhole$extension(self());
/*    */   }
/*    */   
/*    */   public Range until(int end) {
/* 31 */     return RichInt$.MODULE$.until$extension0(self(), end);
/*    */   }
/*    */   
/*    */   public Range until(int end, int step) {
/* 39 */     return RichInt$.MODULE$.until$extension1(self(), end, step);
/*    */   }
/*    */   
/*    */   public Range.Inclusive to(int end) {
/* 47 */     return RichInt$.MODULE$.to$extension0(self(), end);
/*    */   }
/*    */   
/*    */   public Range.Inclusive to(int end, int step) {
/* 55 */     return RichInt$.MODULE$.to$extension1(self(), end, step);
/*    */   }
/*    */   
/*    */   public int min(int that) {
/* 60 */     return RichInt$.MODULE$.min$extension(self(), that);
/*    */   }
/*    */   
/*    */   public int max(int that) {
/* 65 */     return RichInt$.MODULE$.max$extension(self(), that);
/*    */   }
/*    */   
/*    */   public int abs() {
/* 70 */     return RichInt$.MODULE$.abs$extension(self());
/*    */   }
/*    */   
/*    */   public String toBinaryString() {
/* 72 */     return RichInt$.MODULE$.toBinaryString$extension(self());
/*    */   }
/*    */   
/*    */   public String toHexString() {
/* 73 */     return RichInt$.MODULE$.toHexString$extension(self());
/*    */   }
/*    */   
/*    */   public String toOctalString() {
/* 74 */     return RichInt$.MODULE$.toOctalString$extension(self());
/*    */   }
/*    */   
/*    */   public static boolean equals$extension(int paramInt, Object paramObject) {
/*    */     return RichInt$.MODULE$.equals$extension(paramInt, paramObject);
/*    */   }
/*    */   
/*    */   public static int hashCode$extension(int paramInt) {
/*    */     return RichInt$.MODULE$.hashCode$extension(paramInt);
/*    */   }
/*    */   
/*    */   public static String toOctalString$extension(int paramInt) {
/*    */     return RichInt$.MODULE$.toOctalString$extension(paramInt);
/*    */   }
/*    */   
/*    */   public static String toHexString$extension(int paramInt) {
/*    */     return RichInt$.MODULE$.toHexString$extension(paramInt);
/*    */   }
/*    */   
/*    */   public static String toBinaryString$extension(int paramInt) {
/*    */     return RichInt$.MODULE$.toBinaryString$extension(paramInt);
/*    */   }
/*    */   
/*    */   public static int abs$extension(int paramInt) {
/*    */     return RichInt$.MODULE$.abs$extension(paramInt);
/*    */   }
/*    */   
/*    */   public static int max$extension(int paramInt1, int paramInt2) {
/*    */     return RichInt$.MODULE$.max$extension(paramInt1, paramInt2);
/*    */   }
/*    */   
/*    */   public static int min$extension(int paramInt1, int paramInt2) {
/*    */     return RichInt$.MODULE$.min$extension(paramInt1, paramInt2);
/*    */   }
/*    */   
/*    */   public static Range.Inclusive to$extension1(int paramInt1, int paramInt2, int paramInt3) {
/*    */     return RichInt$.MODULE$.to$extension1(paramInt1, paramInt2, paramInt3);
/*    */   }
/*    */   
/*    */   public static Range.Inclusive to$extension0(int paramInt1, int paramInt2) {
/*    */     return RichInt$.MODULE$.to$extension0(paramInt1, paramInt2);
/*    */   }
/*    */   
/*    */   public static Range until$extension1(int paramInt1, int paramInt2, int paramInt3) {
/*    */     return RichInt$.MODULE$.until$extension1(paramInt1, paramInt2, paramInt3);
/*    */   }
/*    */   
/*    */   public static Range until$extension0(int paramInt1, int paramInt2) {
/*    */     return RichInt$.MODULE$.until$extension0(paramInt1, paramInt2);
/*    */   }
/*    */   
/*    */   public static boolean isWhole$extension(int paramInt) {
/*    */     return RichInt$.MODULE$.isWhole$extension(paramInt);
/*    */   }
/*    */   
/*    */   public static Ordering$Int$ ord$extension(int paramInt) {
/*    */     return RichInt$.MODULE$.ord$extension(paramInt);
/*    */   }
/*    */   
/*    */   public static Numeric$IntIsIntegral$ num$extension(int paramInt) {
/*    */     return RichInt$.MODULE$.num$extension(paramInt);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichInt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */