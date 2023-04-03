/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005}c\001B\001\003\001&\0211\"\021;u\031&\034H\017R3dY*\0211\001B\001\004IR$'BA\003\007\003\rAX\016\034\006\002\017\005)1oY1mC\016\0011\003\002\001\013\035I\001\"a\003\007\016\003\tI!!\004\002\003\0255\013'o[;q\t\026\034G\016\005\002\020!5\ta!\003\002\022\r\t9\001K]8ek\016$\bCA\b\024\023\t!bA\001\007TKJL\027\r\\5{C\ndW\r\003\005\027\001\tU\r\021\"\001\030\003\021q\027-\\3\026\003a\001\"!\007\017\017\005=Q\022BA\016\007\003\031\001&/\0323fM&\021QD\b\002\007'R\024\030N\\4\013\005m1\001\002\003\021\001\005#\005\013\021\002\r\002\0139\fW.\032\021\t\021\t\002!Q3A\005\002\r\nQ!\031;ueN,\022\001\n\t\004K5\002dB\001\024,\035\t9#&D\001)\025\tI\003\"\001\004=e>|GOP\005\002\017%\021AFB\001\ba\006\0347.Y4f\023\tqsF\001\003MSN$(B\001\027\007!\tY\021'\003\0023\005\tA\021\t\036;s\t\026\034G\016\003\0055\001\tE\t\025!\003%\003\031\tG\017\036:tA!)a\007\001C\001o\0051A(\0338jiz\"2\001O\035;!\tY\001\001C\003\027k\001\007\001\004C\003#k\001\007A\005C\003=\001\021\005S(A\006ck&dGm\025;sS:<GC\001 B!\t)s(\003\002A_\ti1\013\036:j]\036\024U/\0337eKJDQAQ\036A\002y\n!a\0352\t\017\021\003\021\021!C\001\013\006!1m\0349z)\rAdi\022\005\b-\r\003\n\0211\001\031\021\035\0213\t%AA\002\021Bq!\023\001\022\002\023\005!*\001\bd_BLH\005Z3gCVdG\017J\031\026\003-S#\001\007',\0035\003\"AT*\016\003=S!\001U)\002\023Ut7\r[3dW\026$'B\001*\007\003)\tgN\\8uCRLwN\\\005\003)>\023\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\0351\006!%A\005\002]\013abY8qs\022\"WMZ1vYR$#'F\001YU\t!C\nC\004[\001\005\005I\021I.\002\033A\024x\016Z;diB\023XMZ5y+\005a\006CA/c\033\005q&BA0a\003\021a\027M\\4\013\003\005\fAA[1wC&\021QD\030\005\bI\002\t\t\021\"\001f\0031\001(o\0343vGR\f%/\033;z+\0051\007CA\bh\023\tAgAA\002J]RDqA\033\001\002\002\023\0051.\001\bqe>$Wo\031;FY\026lWM\034;\025\0051|\007CA\bn\023\tqgAA\002B]fDq\001]5\002\002\003\007a-A\002yIEBqA\035\001\002\002\023\0053/A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005!\bcA;yY6\taO\003\002x\r\005Q1m\0347mK\016$\030n\0348\n\005e4(\001C%uKJ\fGo\034:\t\017m\004\021\021!C\001y\006A1-\0318FcV\fG\016F\002~\003\003\001\"a\004@\n\005}4!a\002\"p_2,\027M\034\005\baj\f\t\0211\001m\021%\t)\001AA\001\n\003\n9!\001\005iCND7i\0343f)\0051\007\"CA\006\001\005\005I\021IA\007\003!!xn\025;sS:<G#\001/\t\023\005E\001!!A\005B\005M\021AB3rk\006d7\017F\002~\003+A\001\002]A\b\003\003\005\r\001\\\004\n\0033\021\021\021!E\001\0037\t1\"\021;u\031&\034H\017R3dYB\0311\"!\b\007\021\005\021\021\021!E\001\003?\031R!!\b\002\"I\001r!a\t\002*a!\003(\004\002\002&)\031\021q\005\004\002\017I,h\016^5nK&!\0211FA\023\005E\t%m\035;sC\016$h)\0368di&|gN\r\005\bm\005uA\021AA\030)\t\tY\002\003\006\002\f\005u\021\021!C#\003\033A!\"!\016\002\036\005\005I\021QA\034\003\025\t\007\017\0357z)\025A\024\021HA\036\021\0311\0221\007a\0011!1!%a\rA\002\021B!\"a\020\002\036\005\005I\021QA!\003\035)h.\0319qYf$B!a\021\002PA)q\"!\022\002J%\031\021q\t\004\003\r=\003H/[8o!\025y\0211\n\r%\023\r\tiE\002\002\007)V\004H.\032\032\t\023\005E\023QHA\001\002\004A\024a\001=%a!Q\021QKA\017\003\003%I!a\026\002\027I,\027\r\032*fg>dg/\032\013\003\0033\0022!XA.\023\r\tiF\030\002\007\037\nTWm\031;")
/*    */ public class AttListDecl extends MarkupDecl implements Product, Serializable {
/*    */   private final String name;
/*    */   
/*    */   private final List<AttrDecl> attrs;
/*    */   
/*    */   public String name() {
/* 32 */     return this.name;
/*    */   }
/*    */   
/*    */   public List<AttrDecl> attrs() {
/* 32 */     return this.attrs;
/*    */   }
/*    */   
/*    */   public AttListDecl copy(String name, List<AttrDecl> attrs) {
/* 32 */     return new AttListDecl(name, attrs);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 32 */     return name();
/*    */   }
/*    */   
/*    */   public List<AttrDecl> copy$default$2() {
/* 32 */     return attrs();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 32 */     return "AttListDecl";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 32 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 32 */     switch (x$1) {
/*    */       default:
/* 32 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 32 */     return name();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 32 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 32 */     return x$1 instanceof AttListDecl;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 32 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 32 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 107
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/dtd/AttListDecl
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 111
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/dtd/AttListDecl
/*    */     //   27: astore #5
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual name : ()Ljava/lang/String;
/*    */     //   33: aload #5
/*    */     //   35: invokevirtual name : ()Ljava/lang/String;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 103
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 103
/*    */     //   58: aload_0
/*    */     //   59: invokevirtual attrs : ()Lscala/collection/immutable/List;
/*    */     //   62: aload #5
/*    */     //   64: invokevirtual attrs : ()Lscala/collection/immutable/List;
/*    */     //   67: astore #4
/*    */     //   69: dup
/*    */     //   70: ifnonnull -> 82
/*    */     //   73: pop
/*    */     //   74: aload #4
/*    */     //   76: ifnull -> 90
/*    */     //   79: goto -> 103
/*    */     //   82: aload #4
/*    */     //   84: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   87: ifeq -> 103
/*    */     //   90: aload #5
/*    */     //   92: aload_0
/*    */     //   93: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   96: ifeq -> 103
/*    */     //   99: iconst_1
/*    */     //   100: goto -> 104
/*    */     //   103: iconst_0
/*    */     //   104: ifeq -> 111
/*    */     //   107: iconst_1
/*    */     //   108: goto -> 112
/*    */     //   111: iconst_0
/*    */     //   112: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #32	-> 0
/*    */     //   #236	-> 12
/*    */     //   #32	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	113	0	this	Lscala/xml/dtd/AttListDecl;
/*    */     //   0	113	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public AttListDecl(String name, List<AttrDecl> attrs) {
/* 32 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 35 */     return sb.append("<!ATTLIST ").append(name()).append('\n').append(attrs().mkString("", "\n", ">"));
/*    */   }
/*    */   
/*    */   public static Function1<Tuple2<String, List<AttrDecl>>, AttListDecl> tupled() {
/*    */     return AttListDecl$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<List<AttrDecl>, AttListDecl>> curried() {
/*    */     return AttListDecl$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\AttListDecl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */