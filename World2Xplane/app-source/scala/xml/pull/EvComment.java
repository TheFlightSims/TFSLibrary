/*    */ package scala.xml.pull;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ea\001B\001\003\001&\021\021\"\022<D_6lWM\034;\013\005\r!\021\001\0029vY2T!!\002\004\002\007alGNC\001\b\003\025\0318-\0317b\007\001\031R\001\001\006\017%U\001\"a\003\007\016\003\031I!!\004\004\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"A\001\005Y\0332+e/\0328u!\tY1#\003\002\025\r\t9\001K]8ek\016$\bCA\006\027\023\t9bA\001\007TKJL\027\r\\5{C\ndW\r\003\005\032\001\tU\r\021\"\001\033\003\021!X\r\037;\026\003m\001\"\001H\020\017\005-i\022B\001\020\007\003\031\001&/\0323fM&\021\001%\t\002\007'R\024\030N\\4\013\005y1\001\002C\022\001\005#\005\013\021B\016\002\013Q,\007\020\036\021\t\013\025\002A\021\001\024\002\rqJg.\033;?)\t9\003\006\005\002\020\001!)\021\004\na\0017!9!\006AA\001\n\003Y\023\001B2paf$\"a\n\027\t\017eI\003\023!a\0017!9a\006AI\001\n\003y\023AD2paf$C-\0324bk2$H%M\013\002a)\0221$M\026\002eA\0211\007O\007\002i)\021QGN\001\nk:\034\007.Z2lK\022T!a\016\004\002\025\005tgn\034;bi&|g.\003\002:i\t\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017m\002\021\021!C!y\005i\001O]8ek\016$\bK]3gSb,\022!\020\t\003}\rk\021a\020\006\003\001\006\013A\001\\1oO*\t!)\001\003kCZ\f\027B\001\021@\021\035)\005!!A\005\002\031\013A\002\035:pIV\034G/\021:jif,\022a\022\t\003\027!K!!\023\004\003\007%sG\017C\004L\001\005\005I\021\001'\002\035A\024x\016Z;di\026cW-\\3oiR\021Q\n\025\t\003\0279K!a\024\004\003\007\005s\027\020C\004R\025\006\005\t\031A$\002\007a$\023\007C\004T\001\005\005I\021\t+\002\037A\024x\016Z;di&#XM]1u_J,\022!\026\t\004-fkU\"A,\013\005a3\021AC2pY2,7\r^5p]&\021!l\026\002\t\023R,'/\031;pe\"9A\fAA\001\n\003i\026\001C2b]\026\013X/\0317\025\005y\013\007CA\006`\023\t\001gAA\004C_>dW-\0318\t\017E[\026\021!a\001\033\"91\rAA\001\n\003\"\027\001\0035bg\"\034u\016Z3\025\003\035CqA\032\001\002\002\023\005s-\001\005u_N#(/\0338h)\005i\004bB5\001\003\003%\tE[\001\007KF,\030\r\\:\025\005y[\007bB)i\003\003\005\r!T\004\b[\n\t\t\021#\001o\003%)eoQ8n[\026tG\017\005\002\020_\0329\021AAA\001\022\003\0018cA8r+A!!/^\016(\033\005\031(B\001;\007\003\035\021XO\034;j[\026L!A^:\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007C\003&_\022\005\001\020F\001o\021\0351w.!A\005F\035Dqa_8\002\002\023\005E0A\003baBd\027\020\006\002({\")\021D\037a\0017!Aqp\\A\001\n\003\013\t!A\004v]\006\004\b\017\\=\025\t\005\r\021\021\002\t\005\027\005\0251$C\002\002\b\031\021aa\0249uS>t\007\002CA\006}\006\005\t\031A\024\002\007a$\003\007C\005\002\020=\f\t\021\"\003\002\022\005Y!/Z1e%\026\034x\016\034<f)\t\t\031\002E\002?\003+I1!a\006@\005\031y%M[3di\002")
/*    */ public class EvComment implements XMLEvent, Product, Serializable {
/*    */   private final String text;
/*    */   
/*    */   public static <A> Function1<String, A> andThen(Function1<EvComment, A> paramFunction1) {
/*    */     return EvComment$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, EvComment> compose(Function1<A, String> paramFunction1) {
/*    */     return EvComment$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public String text() {
/* 59 */     return this.text;
/*    */   }
/*    */   
/*    */   public EvComment copy(String text) {
/* 59 */     return new EvComment(text);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 59 */     return text();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 59 */     return "EvComment";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 59 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 59 */     switch (x$1) {
/*    */       default:
/* 59 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 59 */     return text();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 59 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 59 */     return x$1 instanceof EvComment;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 59 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 59 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/pull/EvComment
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/pull/EvComment
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual text : ()Ljava/lang/String;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual text : ()Ljava/lang/String;
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
/*    */     //   #59	-> 0
/*    */     //   #236	-> 12
/*    */     //   #59	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	this	Lscala/xml/pull/EvComment;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public EvComment(String text) {
/* 59 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\EvComment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */