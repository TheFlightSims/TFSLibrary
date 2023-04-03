/*    */ package scala.util.parsing.combinator.token;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005EdaB\001\003!\003\r\t!\004\002\007)>\\WM\\:\013\005\r!\021!\002;pW\026t'BA\003\007\003)\031w.\0342j]\006$xN\035\006\003\017!\tq\001]1sg&twM\003\002\n\025\005!Q\017^5m\025\005Y\021!B:dC2\f7\001A\n\003\0019\001\"a\004\t\016\003)I!!\005\006\003\r\005s\027PU3g\021\025\031\002\001\"\001\025\003\031!\023N\\5uIQ\tQ\003\005\002\020-%\021qC\003\002\005+:LGOB\003\032\001\005\005!DA\003U_.,gn\005\002\031\035!)A\004\007C\001;\0051A(\0338jiz\"\022A\b\t\003?ai\021\001\001\005\006Ca1\tAI\001\006G\"\f'o]\013\002GA\021Ae\n\b\003\037\025J!A\n\006\002\rA\023X\rZ3g\023\tA\023F\001\004TiJLgn\032\006\003M)1Aa\013\001AY\tQQI\035:peR{7.\0328\024\t)rR\006\r\t\003\0379J!a\f\006\003\017A\023x\016Z;diB\021q\"M\005\003e)\021AbU3sS\006d\027N_1cY\026D\001\002\016\026\003\026\004%\tAI\001\004[N<\007\002\003\034+\005#\005\013\021B\022\002\t5\034x\r\t\005\0069)\"\t\001\017\013\003si\002\"a\b\026\t\013Q:\004\031A\022\t\013\005RC\021\001\037\026\003u\002\"AP\"\016\003}R!\001Q!\002\t1\fgn\032\006\002\005\006!!.\031<b\023\tAs\bC\004FU\005\005I\021\001$\002\t\r|\007/\037\013\003s\035Cq\001\016#\021\002\003\0071\005C\004JUE\005I\021\001&\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\t1J\013\002$\031.\nQ\n\005\002O'6\tqJ\003\002Q#\006IQO\\2iK\016\\W\r\032\006\003%*\t!\"\0318o_R\fG/[8o\023\t!vJA\tv]\016DWmY6fIZ\013'/[1oG\026DqA\026\026\002\002\023\005C(A\007qe>$Wo\031;Qe\0264\027\016\037\005\b1*\n\t\021\"\001Z\0031\001(o\0343vGR\f%/\033;z+\005Q\006CA\b\\\023\ta&BA\002J]RDqA\030\026\002\002\023\005q,\001\bqe>$Wo\031;FY\026lWM\034;\025\005\001\034\007CA\bb\023\t\021'BA\002B]fDq\001Z/\002\002\003\007!,A\002yIEBqA\032\026\002\002\023\005s-A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005A\007cA5mA6\t!N\003\002l\025\005Q1m\0347mK\016$\030n\0348\n\0055T'\001C%uKJ\fGo\034:\t\017=T\023\021!C\001a\006A1-\0318FcV\fG\016\006\002riB\021qB]\005\003g*\021qAQ8pY\026\fg\016C\004e]\006\005\t\031\0011\t\017YT\023\021!C!o\006A\001.Y:i\007>$W\rF\001[\021\035I(&!A\005Bi\f\001\002^8TiJLgn\032\013\002{!9APKA\001\n\003j\030AB3rk\006d7\017\006\002r}\"9Am_A\001\002\004\001w!CA\001\001\005\005\t\022AA\002\003))%O]8s)>\\WM\034\t\004?\005\025a\001C\026\001\003\003E\t!a\002\024\013\005\025\021\021\002\031\021\r\005-\021\021C\022:\033\t\tiAC\002\002\020)\tqA];oi&lW-\003\003\002\024\0055!!E!cgR\024\030m\031;Gk:\034G/[8oc!9A$!\002\005\002\005]ACAA\002\021!I\030QAA\001\n\013R\bBCA\017\003\013\t\t\021\"!\002 \005)\021\r\0359msR\031\021(!\t\t\rQ\nY\0021\001$\021)\t)#!\002\002\002\023\005\025qE\001\bk:\f\007\017\0357z)\021\tI#a\f\021\t=\tYcI\005\004\003[Q!AB(qi&|g\016C\005\0022\005\r\022\021!a\001s\005\031\001\020\n\031\t\025\005U\022QAA\001\n\023\t9$A\006sK\006$'+Z:pYZ,GCAA\035!\rq\0241H\005\004\003{y$AB(cU\026\034GoB\004\002B\001A\t)a\021\002\007\025{e\tE\002 \003\0132q!a\022\001\021\003\013IEA\002F\037\032\033R!!\022\037[ABq\001HA#\t\003\ti\005\006\002\002D!1\021%!\022\005\002qB\001BVA#\003\003%\t\005\020\005\t1\006\025\023\021!C\0013\"Ia,!\022\002\002\023\005\021q\013\013\004A\006e\003\002\0033\002V\005\005\t\031\001.\t\021\031\f)%!A\005B\035D\021b\\A#\003\003%\t!a\030\025\007E\f\t\007\003\005e\003;\n\t\0211\001a\021!1\030QIA\001\n\003:\b\002C=\002F\005\005I\021\t>\t\025\005U\022QIA\001\n\023\t9\004C\004\002l\001!\t!!\034\002\025\025\024(o\034:U_.,g\016F\002\037\003_Ba\001NA5\001\004\031\003")
/*    */ public interface Tokens {
/*    */   ErrorToken$ ErrorToken();
/*    */   
/*    */   EOF$ EOF();
/*    */   
/*    */   Token errorToken(String paramString);
/*    */   
/*    */   public abstract class Token {
/*    */     public abstract String chars();
/*    */     
/*    */     public Token(Tokens $outer) {}
/*    */   }
/*    */   
/*    */   public class ErrorToken$ extends AbstractFunction1<String, ErrorToken> implements Serializable {
/*    */     public final String toString() {
/* 31 */       return "ErrorToken";
/*    */     }
/*    */     
/*    */     public Tokens.ErrorToken apply(String msg) {
/* 31 */       return new Tokens.ErrorToken(this.$outer, msg);
/*    */     }
/*    */     
/*    */     public Option<String> unapply(Tokens.ErrorToken x$0) {
/* 31 */       return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.msg());
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/* 31 */       return this.$outer.ErrorToken();
/*    */     }
/*    */     
/*    */     public ErrorToken$(Tokens $outer) {}
/*    */   }
/*    */   
/*    */   public class ErrorToken extends Token implements Product, Serializable {
/*    */     private final String msg;
/*    */     
/*    */     public String msg() {
/* 31 */       return this.msg;
/*    */     }
/*    */     
/*    */     public ErrorToken copy(String msg) {
/* 31 */       return new ErrorToken(scala$util$parsing$combinator$token$Tokens$ErrorToken$$$outer(), msg);
/*    */     }
/*    */     
/*    */     public String copy$default$1() {
/* 31 */       return msg();
/*    */     }
/*    */     
/*    */     public String productPrefix() {
/* 31 */       return "ErrorToken";
/*    */     }
/*    */     
/*    */     public int productArity() {
/* 31 */       return 1;
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/* 31 */       switch (x$1) {
/*    */         default:
/* 31 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */         case 0:
/*    */           break;
/*    */       } 
/* 31 */       return msg();
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/* 31 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/* 31 */       return x$1 instanceof ErrorToken;
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 31 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 31 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object x$1) {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: if_acmpeq -> 89
/*    */       //   5: aload_1
/*    */       //   6: instanceof scala/util/parsing/combinator/token/Tokens$ErrorToken
/*    */       //   9: ifeq -> 31
/*    */       //   12: aload_1
/*    */       //   13: checkcast scala/util/parsing/combinator/token/Tokens$ErrorToken
/*    */       //   16: invokevirtual scala$util$parsing$combinator$token$Tokens$ErrorToken$$$outer : ()Lscala/util/parsing/combinator/token/Tokens;
/*    */       //   19: aload_0
/*    */       //   20: invokevirtual scala$util$parsing$combinator$token$Tokens$ErrorToken$$$outer : ()Lscala/util/parsing/combinator/token/Tokens;
/*    */       //   23: if_acmpne -> 31
/*    */       //   26: iconst_1
/*    */       //   27: istore_2
/*    */       //   28: goto -> 33
/*    */       //   31: iconst_0
/*    */       //   32: istore_2
/*    */       //   33: iload_2
/*    */       //   34: ifeq -> 93
/*    */       //   37: aload_1
/*    */       //   38: checkcast scala/util/parsing/combinator/token/Tokens$ErrorToken
/*    */       //   41: astore #4
/*    */       //   43: aload_0
/*    */       //   44: invokevirtual msg : ()Ljava/lang/String;
/*    */       //   47: aload #4
/*    */       //   49: invokevirtual msg : ()Ljava/lang/String;
/*    */       //   52: astore_3
/*    */       //   53: dup
/*    */       //   54: ifnonnull -> 65
/*    */       //   57: pop
/*    */       //   58: aload_3
/*    */       //   59: ifnull -> 72
/*    */       //   62: goto -> 85
/*    */       //   65: aload_3
/*    */       //   66: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   69: ifeq -> 85
/*    */       //   72: aload #4
/*    */       //   74: aload_0
/*    */       //   75: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */       //   78: ifeq -> 85
/*    */       //   81: iconst_1
/*    */       //   82: goto -> 86
/*    */       //   85: iconst_0
/*    */       //   86: ifeq -> 93
/*    */       //   89: iconst_1
/*    */       //   90: goto -> 94
/*    */       //   93: iconst_0
/*    */       //   94: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #31	-> 0
/*    */       //   #236	-> 26
/*    */       //   #31	-> 33
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	95	0	this	Lscala/util/parsing/combinator/token/Tokens$ErrorToken;
/*    */       //   0	95	1	x$1	Ljava/lang/Object;
/*    */     }
/*    */     
/*    */     public ErrorToken(Tokens $outer, String msg) {
/* 31 */       super($outer);
/* 31 */       Product.class.$init$(this);
/*    */     }
/*    */     
/*    */     public String chars() {
/* 32 */       return (new StringBuilder()).append("*** error: ").append(msg()).toString();
/*    */     }
/*    */   }
/*    */   
/*    */   public class EOF$ extends Token implements Product, Serializable {
/*    */     public EOF$(Tokens $outer) {
/* 36 */       super($outer);
/* 36 */       Product.class.$init$(this);
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/* 36 */       return scala$util$parsing$combinator$token$Tokens$EOF$$$outer().EOF();
/*    */     }
/*    */     
/*    */     public String toString() {
/* 36 */       return "EOF";
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 36 */       return 68828;
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/* 36 */       return x$1 instanceof EOF$;
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/* 36 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/* 36 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */     }
/*    */     
/*    */     public int productArity() {
/* 36 */       return 0;
/*    */     }
/*    */     
/*    */     public String productPrefix() {
/* 36 */       return "EOF";
/*    */     }
/*    */     
/*    */     public String chars() {
/* 37 */       return "<eof>";
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\token\Tokens.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */