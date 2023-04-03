/*    */ package scala.text;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005=a\001B\001\003\001\036\021q\001R8d)\026DHO\003\002\004\t\005!A/\032=u\025\005)\021!B:dC2\f7\001A\n\005\001!a\001\003\005\002\n\0255\t!!\003\002\f\005\tAAi\\2v[\026tG\017\005\002\016\0355\tA!\003\002\020\t\t9\001K]8ek\016$\bCA\007\022\023\t\021BA\001\007TKJL\027\r\\5{C\ndW\r\003\005\025\001\tU\r\021\"\001\026\003\r!\b\020^\013\002-A\021qC\007\b\003\033aI!!\007\003\002\rA\023X\rZ3g\023\tYBD\001\004TiJLgn\032\006\0033\021A\001B\b\001\003\022\003\006IAF\001\005ib$\b\005C\003!\001\021\005\021%\001\004=S:LGO\020\013\003E\r\002\"!\003\001\t\013Qy\002\031\001\f\t\017\025\002\021\021!C\001M\005!1m\0349z)\t\021s\005C\004\025IA\005\t\031\001\f\t\017%\002\021\023!C\001U\005q1m\0349zI\021,g-Y;mi\022\nT#A\026+\005Ya3&A\027\021\0059\032T\"A\030\013\005A\n\024!C;oG\",7m[3e\025\t\021D!\001\006b]:|G/\031;j_:L!\001N\030\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\0047\001\005\005I\021I\034\002\033A\024x\016Z;diB\023XMZ5y+\005A\004CA\035?\033\005Q$BA\036=\003\021a\027M\\4\013\003u\nAA[1wC&\0211D\017\005\b\001\002\t\t\021\"\001B\0031\001(o\0343vGR\f%/\033;z+\005\021\005CA\007D\023\t!EAA\002J]RDqA\022\001\002\002\023\005q)\001\bqe>$Wo\031;FY\026lWM\034;\025\005![\005CA\007J\023\tQEAA\002B]fDq\001T#\002\002\003\007!)A\002yIEBqA\024\001\002\002\023\005s*A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005\001\006cA)U\0216\t!K\003\002T\t\005Q1m\0347mK\016$\030n\0348\n\005U\023&\001C%uKJ\fGo\034:\t\017]\003\021\021!C\0011\006A1-\0318FcV\fG\016\006\002Z9B\021QBW\005\0037\022\021qAQ8pY\026\fg\016C\004M-\006\005\t\031\001%\t\017y\003\021\021!C!?\006A\001.Y:i\007>$W\rF\001C\021\035\t\007!!A\005B\t\f\001\002^8TiJLgn\032\013\002q!9A\rAA\001\n\003*\027AB3rk\006d7\017\006\002ZM\"9AjYA\001\002\004Aua\0025\003\003\003E\t![\001\b\t>\034G+\032=u!\tI!NB\004\002\005\005\005\t\022A6\024\007)d\007\003\005\003naZ\021S\"\0018\013\005=$\021a\002:v]RLW.Z\005\003c:\024\021#\0212tiJ\f7\r\036$v]\016$\030n\03482\021\025\001#\016\"\001t)\005I\007bB1k\003\003%)E\031\005\bm*\f\t\021\"!x\003\025\t\007\017\0357z)\t\021\003\020C\003\025k\002\007a\003C\004{U\006\005I\021Q>\002\017Ut\027\r\0359msR\021Ap \t\004\033u4\022B\001@\005\005\031y\005\017^5p]\"A\021\021A=\002\002\003\007!%A\002yIAB\021\"!\002k\003\003%I!a\002\002\027I,\027\r\032*fg>dg/\032\013\003\003\023\0012!OA\006\023\r\tiA\017\002\007\037\nTWm\031;")
/*    */ public class DocText extends Document implements Product, Serializable {
/*    */   private final String txt;
/*    */   
/*    */   public static <A> Function1<String, A> andThen(Function1<DocText, A> paramFunction1) {
/*    */     return DocText$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, DocText> compose(Function1<A, String> paramFunction1) {
/*    */     return DocText$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public String txt() {
/* 15 */     return this.txt;
/*    */   }
/*    */   
/*    */   public DocText copy(String txt) {
/* 15 */     return new DocText(txt);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 15 */     return txt();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 15 */     return "DocText";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 15 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 15 */     switch (x$1) {
/*    */       default:
/* 15 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 15 */     return txt();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 15 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 15 */     return x$1 instanceof DocText;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 15 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 15 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/text/DocText
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/text/DocText
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual txt : ()Ljava/lang/String;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual txt : ()Ljava/lang/String;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 71
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 71
/*    */     //   58: aload #4
/*    */     //   60: aload_0
/*    */     //   61: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   64: ifeq -> 71
/*    */     //   67: iconst_1
/*    */     //   68: goto -> 72
/*    */     //   71: iconst_0
/*    */     //   72: ifeq -> 79
/*    */     //   75: iconst_1
/*    */     //   76: goto -> 80
/*    */     //   79: iconst_0
/*    */     //   80: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #15	-> 0
/*    */     //   #236	-> 12
/*    */     //   #15	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	this	Lscala/text/DocText;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public DocText(String txt) {
/* 15 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\text\DocText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */