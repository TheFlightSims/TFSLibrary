/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ import scala.xml.Utility$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Uc\001B\001\003\001&\021q\001R#G\003VcEK\003\002\004\t\005\031A\r\0363\013\005\0251\021a\001=nY*\tq!A\003tG\006d\027m\001\001\024\t\001QaB\005\t\003\0271i\021AA\005\003\033\t\0211\002R3gCVdG\017R3dYB\021q\002E\007\002\r%\021\021C\002\002\b!J|G-^2u!\ty1#\003\002\025\r\ta1+\032:jC2L'0\0312mK\"Aa\003\001BK\002\023\005q#A\003gSb,G-F\001\031!\ty\021$\003\002\033\r\t9!i\\8mK\006t\007\002\003\017\001\005#\005\013\021\002\r\002\r\031L\0070\0323!\021!q\002A!f\001\n\003y\022\001C1uiZ\013G.^3\026\003\001\002\"!\t\023\017\005=\021\023BA\022\007\003\031\001&/\0323fM&\021QE\n\002\007'R\024\030N\\4\013\005\r2\001\002\003\025\001\005#\005\013\021\002\021\002\023\005$HOV1mk\026\004\003\"\002\026\001\t\003Y\023A\002\037j]&$h\bF\002-[9\002\"a\003\001\t\013YI\003\031\001\r\t\013yI\003\031\001\021\t\013A\002A\021I\031\002\021Q|7\013\036:j]\036$\022\001\t\005\006g\001!\t\005N\001\fEVLG\016Z*ue&tw\r\006\0026\003B\021aG\020\b\003oqr!\001O\036\016\003eR!A\017\005\002\rq\022xn\034;?\023\0059\021BA\037\007\003\035\001\030mY6bO\026L!a\020!\003\033M#(/\0338h\005VLG\016Z3s\025\tid\001C\003Ce\001\007Q'\001\002tE\"9A\tAA\001\n\003)\025\001B2paf$2\001\f$H\021\03512\t%AA\002aAqAH\"\021\002\003\007\001\005C\004J\001E\005I\021\001&\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\t1J\013\002\031\031.\nQ\n\005\002O'6\tqJ\003\002Q#\006IQO\\2iK\016\\W\r\032\006\003%\032\t!\"\0318o_R\fG/[8o\023\t!vJA\tv]\016DWmY6fIZ\013'/[1oG\026DqA\026\001\022\002\023\005q+\001\bd_BLH\005Z3gCVdG\017\n\032\026\003aS#\001\t'\t\017i\003\021\021!C!7\006i\001O]8ek\016$\bK]3gSb,\022\001\030\t\003;\nl\021A\030\006\003?\002\fA\001\\1oO*\t\021-\001\003kCZ\f\027BA\023_\021\035!\007!!A\005\002\025\fA\002\035:pIV\034G/\021:jif,\022A\032\t\003\037\035L!\001\033\004\003\007%sG\017C\004k\001\005\005I\021A6\002\035A\024x\016Z;di\026cW-\\3oiR\021An\034\t\003\0375L!A\034\004\003\007\005s\027\020C\004qS\006\005\t\031\0014\002\007a$\023\007C\004s\001\005\005I\021I:\002\037A\024x\016Z;di&#XM]1u_J,\022\001\036\t\004kbdW\"\001<\013\005]4\021AC2pY2,7\r^5p]&\021\021P\036\002\t\023R,'/\031;pe\"91\020AA\001\n\003a\030\001C2b]\026\013X/\0317\025\005ai\bb\0029{\003\003\005\r\001\034\005\t\002\t\t\021\"\021\002\002\005A\001.Y:i\007>$W\rF\001g\021%\t)\001AA\001\n\003\n9!\001\004fcV\fGn\035\013\0041\005%\001\002\0039\002\004\005\005\t\031\0017\b\023\0055!!!A\t\002\005=\021a\002#F\r\006+F\n\026\t\004\027\005Ea\001C\001\003\003\003E\t!a\005\024\013\005E\021Q\003\n\021\017\005]\021Q\004\r!Y5\021\021\021\004\006\004\00371\021a\002:v]RLW.Z\005\005\003?\tIBA\tBEN$(/Y2u\rVt7\r^5p]JBqAKA\t\t\003\t\031\003\006\002\002\020!I\001'!\005\002\002\023\025\023q\005\013\0029\"Q\0211FA\t\003\003%\t)!\f\002\013\005\004\b\017\\=\025\0131\ny#!\r\t\rY\tI\0031\001\031\021\031q\022\021\006a\001A!Q\021QGA\t\003\003%\t)a\016\002\017Ut\027\r\0359msR!\021\021HA#!\025y\0211HA \023\r\tiD\002\002\007\037B$\030n\0348\021\013=\t\t\005\007\021\n\007\005\rcA\001\004UkBdWM\r\005\n\003\017\n\031$!AA\0021\n1\001\037\0231\021)\tY%!\005\002\002\023%\021QJ\001\fe\026\fGMU3t_24X\r\006\002\002PA\031Q,!\025\n\007\005McL\001\004PE*,7\r\036")
/*     */ public class DEFAULT extends DefaultDecl implements Product, Serializable {
/*     */   private final boolean fixed;
/*     */   
/*     */   private final String attValue;
/*     */   
/*     */   public boolean fixed() {
/* 150 */     return this.fixed;
/*     */   }
/*     */   
/*     */   public String attValue() {
/* 150 */     return this.attValue;
/*     */   }
/*     */   
/*     */   public DEFAULT copy(boolean fixed, String attValue) {
/* 150 */     return new DEFAULT(fixed, attValue);
/*     */   }
/*     */   
/*     */   public boolean copy$default$1() {
/* 150 */     return fixed();
/*     */   }
/*     */   
/*     */   public String copy$default$2() {
/* 150 */     return attValue();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 150 */     return "DEFAULT";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 150 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 150 */     switch (x$1) {
/*     */       default:
/* 150 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 150 */     return BoxesRunTime.boxToBoolean(fixed());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 150 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 150 */     return x$1 instanceof DEFAULT;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 150 */     return Statics.finalizeHash(Statics.mix(Statics.mix(-889275714, fixed() ? 1231 : 1237), Statics.anyHash(attValue())), 2);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 87
/*     */     //   5: aload_1
/*     */     //   6: instanceof scala/xml/dtd/DEFAULT
/*     */     //   9: ifeq -> 17
/*     */     //   12: iconst_1
/*     */     //   13: istore_2
/*     */     //   14: goto -> 19
/*     */     //   17: iconst_0
/*     */     //   18: istore_2
/*     */     //   19: iload_2
/*     */     //   20: ifeq -> 91
/*     */     //   23: aload_1
/*     */     //   24: checkcast scala/xml/dtd/DEFAULT
/*     */     //   27: astore #4
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual fixed : ()Z
/*     */     //   33: aload #4
/*     */     //   35: invokevirtual fixed : ()Z
/*     */     //   38: if_icmpne -> 83
/*     */     //   41: aload_0
/*     */     //   42: invokevirtual attValue : ()Ljava/lang/String;
/*     */     //   45: aload #4
/*     */     //   47: invokevirtual attValue : ()Ljava/lang/String;
/*     */     //   50: astore_3
/*     */     //   51: dup
/*     */     //   52: ifnonnull -> 63
/*     */     //   55: pop
/*     */     //   56: aload_3
/*     */     //   57: ifnull -> 70
/*     */     //   60: goto -> 83
/*     */     //   63: aload_3
/*     */     //   64: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   67: ifeq -> 83
/*     */     //   70: aload #4
/*     */     //   72: aload_0
/*     */     //   73: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   76: ifeq -> 83
/*     */     //   79: iconst_1
/*     */     //   80: goto -> 84
/*     */     //   83: iconst_0
/*     */     //   84: ifeq -> 91
/*     */     //   87: iconst_1
/*     */     //   88: goto -> 92
/*     */     //   91: iconst_0
/*     */     //   92: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #150	-> 0
/*     */     //   #236	-> 12
/*     */     //   #150	-> 19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	93	0	this	Lscala/xml/dtd/DEFAULT;
/*     */     //   0	93	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public DEFAULT(boolean fixed, String attValue) {
/* 150 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 151 */     return Utility$.MODULE$.sbToString((Function1)new DEFAULT$$anonfun$toString$2(this));
/*     */   }
/*     */   
/*     */   public class DEFAULT$$anonfun$toString$2 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(StringBuilder sb) {
/* 151 */       this.$outer.buildString(sb);
/*     */     }
/*     */     
/*     */     public DEFAULT$$anonfun$toString$2(DEFAULT $outer) {}
/*     */   }
/*     */   
/*     */   public StringBuilder buildString(StringBuilder sb) {
/* 153 */     fixed() ? sb.append("#FIXED ") : BoxedUnit.UNIT;
/* 154 */     return Utility$.MODULE$.appendEscapedQuoted(attValue(), sb);
/*     */   }
/*     */   
/*     */   public static Function1<Tuple2<Object, String>, DEFAULT> tupled() {
/*     */     return DEFAULT$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Object, Function1<String, DEFAULT>> curried() {
/*     */     return DEFAULT$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\DEFAULT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */