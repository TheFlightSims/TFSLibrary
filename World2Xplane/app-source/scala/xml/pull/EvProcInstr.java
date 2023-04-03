/*    */ package scala.xml.pull;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Eb\001B\001\003\001&\0211\"\022<Qe>\034\027J\\:ue*\0211\001B\001\005aVdGN\003\002\006\r\005\031\0010\0347\013\003\035\tQa]2bY\006\034\001aE\003\001\0259\021R\003\005\002\f\0315\ta!\003\002\016\r\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\021akE*\022<f]R\004\"aC\n\n\005Q1!a\002)s_\022,8\r\036\t\003\027YI!a\006\004\003\031M+'/[1mSj\f'\r\\3\t\021e\001!Q3A\005\002i\ta\001^1sO\026$X#A\016\021\005qybBA\006\036\023\tqb!\001\004Qe\026$WMZ\005\003A\005\022aa\025;sS:<'B\001\020\007\021!\031\003A!E!\002\023Y\022a\002;be\036,G\017\t\005\tK\001\021)\032!C\0015\005!A/\032=u\021!9\003A!E!\002\023Y\022!\002;fqR\004\003\"B\025\001\t\003Q\023A\002\037j]&$h\bF\002,Y5\002\"a\004\001\t\013eA\003\031A\016\t\013\025B\003\031A\016\t\017=\002\021\021!C\001a\005!1m\0349z)\rY\023G\r\005\b39\002\n\0211\001\034\021\035)c\006%AA\002mAq\001\016\001\022\002\023\005Q'\001\bd_BLH\005Z3gCVdG\017J\031\026\003YR#aG\034,\003a\002\"!\017 \016\003iR!a\017\037\002\023Ut7\r[3dW\026$'BA\037\007\003)\tgN\\8uCRLwN\\\005\003i\022\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\035\t\005!%A\005\002U\nabY8qs\022\"WMZ1vYR$#\007C\004D\001\005\005I\021\t#\002\033A\024x\016Z;diB\023XMZ5y+\005)\005C\001$L\033\0059%B\001%J\003\021a\027M\\4\013\003)\013AA[1wC&\021\001e\022\005\b\033\002\t\t\021\"\001O\0031\001(o\0343vGR\f%/\033;z+\005y\005CA\006Q\023\t\tfAA\002J]RDqa\025\001\002\002\023\005A+\001\bqe>$Wo\031;FY\026lWM\034;\025\005UC\006CA\006W\023\t9fAA\002B]fDq!\027*\002\002\003\007q*A\002yIEBqa\027\001\002\002\023\005C,A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005i\006c\0010b+6\tqL\003\002a\r\005Q1m\0347mK\016$\030n\0348\n\005\t|&\001C%uKJ\fGo\034:\t\017\021\004\021\021!C\001K\006A1-\0318FcV\fG\016\006\002gSB\0211bZ\005\003Q\032\021qAQ8pY\026\fg\016C\004ZG\006\005\t\031A+\t\017-\004\021\021!C!Y\006A\001.Y:i\007>$W\rF\001P\021\035q\007!!A\005B=\f\001\002^8TiJLgn\032\013\002\013\"9\021\017AA\001\n\003\022\030AB3rk\006d7\017\006\002gg\"9\021\f]A\001\002\004)vaB;\003\003\003E\tA^\001\f\013Z\004&o\\2J]N$(\017\005\002\020o\0329\021AAA\001\022\003A8cA<z+A)!0`\016\034W5\t1P\003\002}\r\0059!/\0368uS6,\027B\001@|\005E\t%m\035;sC\016$h)\0368di&|gN\r\005\007S]$\t!!\001\025\003YDqA\\<\002\002\023\025s\016C\005\002\b]\f\t\021\"!\002\n\005)\021\r\0359msR)1&a\003\002\016!1\021$!\002A\002mAa!JA\003\001\004Y\002\"CA\to\006\005I\021QA\n\003\035)h.\0319qYf$B!!\006\002\"A)1\"a\006\002\034%\031\021\021\004\004\003\r=\003H/[8o!\025Y\021QD\016\034\023\r\tyB\002\002\007)V\004H.\032\032\t\023\005\r\022qBA\001\002\004Y\023a\001=%a!I\021qE<\002\002\023%\021\021F\001\fe\026\fGMU3t_24X\r\006\002\002,A\031a)!\f\n\007\005=rI\001\004PE*,7\r\036")
/*    */ public class EvProcInstr implements XMLEvent, Product, Serializable {
/*    */   private final String target;
/*    */   
/*    */   private final String text;
/*    */   
/*    */   public static Function1<Tuple2<String, String>, EvProcInstr> tupled() {
/*    */     return EvProcInstr$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<String, EvProcInstr>> curried() {
/*    */     return EvProcInstr$.MODULE$.curried();
/*    */   }
/*    */   
/*    */   public String target() {
/* 53 */     return this.target;
/*    */   }
/*    */   
/*    */   public String text() {
/* 53 */     return this.text;
/*    */   }
/*    */   
/*    */   public EvProcInstr copy(String target, String text) {
/* 53 */     return new EvProcInstr(target, text);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 53 */     return target();
/*    */   }
/*    */   
/*    */   public String copy$default$2() {
/* 53 */     return text();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 53 */     return "EvProcInstr";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 53 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 53 */     switch (x$1) {
/*    */       default:
/* 53 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 53 */     return target();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 53 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 53 */     return x$1 instanceof EvProcInstr;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 53 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 53 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 107
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/pull/EvProcInstr
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 111
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/pull/EvProcInstr
/*    */     //   27: astore #5
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual target : ()Ljava/lang/String;
/*    */     //   33: aload #5
/*    */     //   35: invokevirtual target : ()Ljava/lang/String;
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
/*    */     //   59: invokevirtual text : ()Ljava/lang/String;
/*    */     //   62: aload #5
/*    */     //   64: invokevirtual text : ()Ljava/lang/String;
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
/*    */     //   #53	-> 0
/*    */     //   #236	-> 12
/*    */     //   #53	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	113	0	this	Lscala/xml/pull/EvProcInstr;
/*    */     //   0	113	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public EvProcInstr(String target, String text) {
/* 53 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\EvProcInstr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */