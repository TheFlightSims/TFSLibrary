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
/*     */ @ScalaSignature(bytes = "\006\001\005Ub\001B\001\003\001&\0211\002U#SK\032,'/\0328dK*\0211\001B\001\004IR$'BA\003\007\003\rAX\016\034\006\002\017\005)1oY1mC\016\0011\003\002\001\013\035I\001\"a\003\007\016\003\tI!!\004\002\003\0255\013'o[;q\t\026\034G\016\005\002\020!5\ta!\003\002\022\r\t9\001K]8ek\016$\bCA\b\024\023\t!bA\001\007TKJL\027\r\\5{C\ndW\r\003\005\027\001\tU\r\021\"\001\030\003\r)g\016^\013\0021A\021\021\004\b\b\003\037iI!a\007\004\002\rA\023X\rZ3g\023\tibD\001\004TiJLgn\032\006\0037\031A\001\002\t\001\003\022\003\006I\001G\001\005K:$\b\005C\003#\001\021\0051%\001\004=S:LGO\020\013\003I\025\002\"a\003\001\t\013Y\t\003\031\001\r\t\013\035\002A\021\t\025\002\027\t,\030\016\0343TiJLgn\032\013\003SU\002\"A\013\032\017\005-\002dB\001\0270\033\005i#B\001\030\t\003\031a$o\\8u}%\tq!\003\0022\r\0059\001/Y2lC\036,\027BA\0325\0055\031FO]5oO\n+\030\016\0343fe*\021\021G\002\005\006m\031\002\r!K\001\003g\nDq\001\017\001\002\002\023\005\021(\001\003d_BLHC\001\023;\021\0351r\007%AA\002aAq\001\020\001\022\002\023\005Q(\001\bd_BLH\005Z3gCVdG\017J\031\026\003yR#\001G ,\003\001\003\"!\021$\016\003\tS!a\021#\002\023Ut7\r[3dW\026$'BA#\007\003)\tgN\\8uCRLwN\\\005\003\017\n\023\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\035I\005!!A\005B)\013Q\002\035:pIV\034G\017\025:fM&DX#A&\021\0051\013V\"A'\013\0059{\025\001\0027b]\036T\021\001U\001\005U\0064\030-\003\002\036\033\"91\013AA\001\n\003!\026\001\0049s_\022,8\r^!sSRLX#A+\021\005=1\026BA,\007\005\rIe\016\036\005\b3\002\t\t\021\"\001[\0039\001(o\0343vGR,E.Z7f]R$\"a\0270\021\005=a\026BA/\007\005\r\te.\037\005\b?b\013\t\0211\001V\003\rAH%\r\005\bC\002\t\t\021\"\021c\003=\001(o\0343vGRLE/\032:bi>\024X#A2\021\007\021<7,D\001f\025\t1g!\001\006d_2dWm\031;j_:L!\001[3\003\021%#XM]1u_JDqA\033\001\002\002\023\0051.\001\005dC:,\025/^1m)\taw\016\005\002\020[&\021aN\002\002\b\005>|G.Z1o\021\035y\026.!AA\002mCq!\035\001\002\002\023\005#/\001\005iCND7i\0343f)\005)\006b\002;\001\003\003%\t%^\001\ti>\034FO]5oOR\t1\nC\004x\001\005\005I\021\t=\002\r\025\fX/\0317t)\ta\027\020C\004`m\006\005\t\031A.\b\017m\024\021\021!E\001y\006Y\001+\022*fM\026\024XM\\2f!\tYQPB\004\002\005\005\005\t\022\001@\024\007u|(\003\005\004\002\002\005\035\001\004J\007\003\003\007Q1!!\002\007\003\035\021XO\034;j[\026LA!!\003\002\004\t\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\r\tjH\021AA\007)\005a\bb\002;~\003\003%)%\036\005\n\003'i\030\021!CA\003+\tQ!\0319qYf$2\001JA\f\021\0311\022\021\003a\0011!I\0211D?\002\002\023\005\025QD\001\bk:\f\007\017\0357z)\021\ty\"!\n\021\t=\t\t\003G\005\004\003G1!AB(qi&|g\016C\005\002(\005e\021\021!a\001I\005\031\001\020\n\031\t\023\005-R0!A\005\n\0055\022a\003:fC\022\024Vm]8mm\026$\"!a\f\021\0071\013\t$C\002\00245\023aa\0242kK\016$\b")
/*     */ public class PEReference extends MarkupDecl implements Product, Serializable {
/*     */   private final String ent;
/*     */   
/*     */   public String ent() {
/* 124 */     return this.ent;
/*     */   }
/*     */   
/*     */   public PEReference copy(String ent) {
/* 124 */     return new PEReference(ent);
/*     */   }
/*     */   
/*     */   public String copy$default$1() {
/* 124 */     return ent();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 124 */     return "PEReference";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 124 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 124 */     switch (x$1) {
/*     */       default:
/* 124 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 124 */     return ent();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 124 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 124 */     return x$1 instanceof PEReference;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 124 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 124 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 75
/*     */     //   5: aload_1
/*     */     //   6: instanceof scala/xml/dtd/PEReference
/*     */     //   9: ifeq -> 17
/*     */     //   12: iconst_1
/*     */     //   13: istore_2
/*     */     //   14: goto -> 19
/*     */     //   17: iconst_0
/*     */     //   18: istore_2
/*     */     //   19: iload_2
/*     */     //   20: ifeq -> 79
/*     */     //   23: aload_1
/*     */     //   24: checkcast scala/xml/dtd/PEReference
/*     */     //   27: astore #4
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual ent : ()Ljava/lang/String;
/*     */     //   33: aload #4
/*     */     //   35: invokevirtual ent : ()Ljava/lang/String;
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
/*     */     //   #124	-> 0
/*     */     //   #236	-> 12
/*     */     //   #124	-> 19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	81	0	this	Lscala/xml/dtd/PEReference;
/*     */     //   0	81	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public PEReference(String ent) {
/* 124 */     Product.class.$init$(this);
/* 125 */     if (Utility$.MODULE$.isName(ent))
/*     */       return; 
/* 126 */     throw new IllegalArgumentException("ent must be an XML Name");
/*     */   }
/*     */   
/*     */   public StringBuilder buildString(StringBuilder sb) {
/* 129 */     return sb.append('%').append(ent()).append(';');
/*     */   }
/*     */   
/*     */   public static <A> Function1<String, A> andThen(Function1<PEReference, A> paramFunction1) {
/*     */     return PEReference$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, PEReference> compose(Function1<A, String> paramFunction1) {
/*     */     return PEReference$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\PEReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */