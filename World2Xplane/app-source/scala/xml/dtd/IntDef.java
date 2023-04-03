/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.xml.Utility$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005c\001B\001\003\001&\021a!\0238u\t\0264'BA\002\005\003\r!G\017\032\006\003\013\031\t1\001_7m\025\0059\021!B:dC2\f7\001A\n\005\001)q!\003\005\002\f\0315\t!!\003\002\016\005\tIQI\034;jif$UM\032\t\003\037Ai\021AB\005\003#\031\021q\001\025:pIV\034G\017\005\002\020'%\021AC\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\t-\001\021)\032!C\001/\005)a/\0317vKV\t\001\004\005\002\03299\021qBG\005\0037\031\ta\001\025:fI\0264\027BA\017\037\005\031\031FO]5oO*\0211D\002\005\tA\001\021\t\022)A\0051\0051a/\0317vK\002BQA\t\001\005\002\r\na\001P5oSRtDC\001\023&!\tY\001\001C\003\027C\001\007\001\004C\003(\001\021%\001&A\007wC2LG-\031;f-\006dW/\032\013\002SA\021qBK\005\003W\031\021A!\0268ji\")Q\006\001C!]\005Y!-^5mIN#(/\0338h)\ty3\b\005\0021q9\021\021G\016\b\003eUj\021a\r\006\003i!\ta\001\020:p_Rt\024\"A\004\n\005]2\021a\0029bG.\fw-Z\005\003si\022Qb\025;sS:<')^5mI\026\024(BA\034\007\021\025aD\0061\0010\003\t\031(\rC\004?\001\005\005I\021A \002\t\r|\007/\037\013\003I\001CqAF\037\021\002\003\007\001\004C\004C\001E\005I\021A\"\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\tAI\013\002\031\013.\na\t\005\002H\0316\t\001J\003\002J\025\006IQO\\2iK\016\\W\r\032\006\003\027\032\t!\"\0318o_R\fG/[8o\023\ti\005JA\tv]\016DWmY6fIZ\013'/[1oG\026Dqa\024\001\002\002\023\005\003+A\007qe>$Wo\031;Qe\0264\027\016_\013\002#B\021!kV\007\002'*\021A+V\001\005Y\006twMC\001W\003\021Q\027M^1\n\005u\031\006bB-\001\003\003%\tAW\001\raJ|G-^2u\003JLG/_\013\0027B\021q\002X\005\003;\032\0211!\0238u\021\035y\006!!A\005\002\001\fa\002\035:pIV\034G/\0227f[\026tG\017\006\002bIB\021qBY\005\003G\032\0211!\0218z\021\035)g,!AA\002m\0131\001\037\0232\021\0359\007!!A\005B!\fq\002\035:pIV\034G/\023;fe\006$xN]\013\002SB\031!.\\1\016\003-T!\001\034\004\002\025\r|G\016\\3di&|g.\003\002oW\nA\021\n^3sCR|'\017C\004q\001\005\005I\021A9\002\021\r\fg.R9vC2$\"A];\021\005=\031\030B\001;\007\005\035\021un\0347fC:Dq!Z8\002\002\003\007\021\rC\004x\001\005\005I\021\t=\002\021!\f7\017[\"pI\026$\022a\027\005\bu\002\t\t\021\"\021|\003!!xn\025;sS:<G#A)\t\017u\004\021\021!C!}\0061Q-];bYN$\"A]@\t\017\025d\030\021!a\001C\036I\0211\001\002\002\002#\005\021QA\001\007\023:$H)\0324\021\007-\t9A\002\005\002\005\005\005\t\022AA\005'\025\t9!a\003\023!\031\ti!a\005\031I5\021\021q\002\006\004\003#1\021a\002:v]RLW.Z\005\005\003+\tyAA\tBEN$(/Y2u\rVt7\r^5p]FBqAIA\004\t\003\tI\002\006\002\002\006!A!0a\002\002\002\023\0253\020\003\006\002 \005\035\021\021!CA\003C\tQ!\0319qYf$2\001JA\022\021\0311\022Q\004a\0011!Q\021qEA\004\003\003%\t)!\013\002\017Ut\027\r\0359msR!\0211FA\031!\021y\021Q\006\r\n\007\005=bA\001\004PaRLwN\034\005\n\003g\t)#!AA\002\021\n1\001\037\0231\021)\t9$a\002\002\002\023%\021\021H\001\fe\026\fGMU3t_24X\r\006\002\002<A\031!+!\020\n\007\005}2K\001\004PE*,7\r\036")
/*     */ public class IntDef extends EntityDef implements Product, Serializable {
/*     */   private final String value;
/*     */   
/*     */   public String value() {
/*  91 */     return this.value;
/*     */   }
/*     */   
/*     */   public IntDef copy(String value) {
/*  91 */     return new IntDef(value);
/*     */   }
/*     */   
/*     */   public String copy$default$1() {
/*  91 */     return value();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  91 */     return "IntDef";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*  91 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*  91 */     switch (x$1) {
/*     */       default:
/*  91 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/*  91 */     return value();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  91 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*  91 */     return x$1 instanceof IntDef;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  91 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  91 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 75
/*     */     //   5: aload_1
/*     */     //   6: instanceof scala/xml/dtd/IntDef
/*     */     //   9: ifeq -> 17
/*     */     //   12: iconst_1
/*     */     //   13: istore_2
/*     */     //   14: goto -> 19
/*     */     //   17: iconst_0
/*     */     //   18: istore_2
/*     */     //   19: iload_2
/*     */     //   20: ifeq -> 79
/*     */     //   23: aload_1
/*     */     //   24: checkcast scala/xml/dtd/IntDef
/*     */     //   27: astore #4
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual value : ()Ljava/lang/String;
/*     */     //   33: aload #4
/*     */     //   35: invokevirtual value : ()Ljava/lang/String;
/*     */     //   38: astore_3
/*     */     //   39: dup
/*     */     //   40: ifnonnull -> 51
/*     */     //   43: pop
/*     */     //   44: aload_3
/*     */     //   45: ifnull -> 58
/*     */     //   48: goto -> 71
/*     */     //   51: aload_3
/*     */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   55: ifeq -> 71
/*     */     //   58: aload #4
/*     */     //   60: aload_0
/*     */     //   61: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   64: ifeq -> 71
/*     */     //   67: iconst_1
/*     */     //   68: goto -> 72
/*     */     //   71: iconst_0
/*     */     //   72: ifeq -> 79
/*     */     //   75: iconst_1
/*     */     //   76: goto -> 80
/*     */     //   79: iconst_0
/*     */     //   80: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #91	-> 0
/*     */     //   #236	-> 12
/*     */     //   #91	-> 19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	81	0	this	Lscala/xml/dtd/IntDef;
/*     */     //   0	81	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public IntDef(String value) {
/*  91 */     Product.class.$init$(this);
/* 110 */     validateValue();
/*     */   }
/*     */   
/*     */   private void validateValue() {
/*     */     String tmp = value();
/*     */     int ix = tmp.indexOf('%');
/*     */     while (ix != -1) {
/*     */       int iz = tmp.indexOf(';', ix);
/*     */       if (iz == -1 && iz == ix + 1)
/*     */         throw new IllegalArgumentException("no % allowed in entity value, except for parameter-entity-references"); 
/*     */       String n = tmp.substring(ix, iz);
/*     */       if (Utility$.MODULE$.isName(n)) {
/*     */         ix = (tmp = tmp.substring(iz + 1, tmp.length())).indexOf('%');
/*     */         continue;
/*     */       } 
/*     */       throw new IllegalArgumentException((new StringBuilder()).append("internal entity def: \"").append(n).append("\" must be an XML Name").toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public StringBuilder buildString(StringBuilder sb) {
/* 113 */     return Utility$.MODULE$.appendQuoted(value(), sb);
/*     */   }
/*     */   
/*     */   public static <A> Function1<String, A> andThen(Function1<IntDef, A> paramFunction1) {
/*     */     return IntDef$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, IntDef> compose(Function1<A, String> paramFunction1) {
/*     */     return IntDef$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\IntDef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */