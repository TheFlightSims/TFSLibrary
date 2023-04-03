/*    */ package scala;
/*    */ 
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005=a\001B\001\003\001\026\021\001dU2bY\006\024VM\0327fGRLwN\\#yG\026\004H/[8o\025\005\031\021!B:dC2\f7\001A\n\005\001\031q\021\003\005\002\b\0279\021\001\"C\007\002\005%\021!BA\001\ba\006\0347.Y4f\023\taQBA\005Fq\016,\007\017^5p]*\021!B\001\t\003\021=I!\001\005\002\003\017A\023x\016Z;diB\021\001BE\005\003'\t\021AbU3sS\006d\027N_1cY\026D\001\"\006\001\003\026\004%\tAF\001\004[N<W#A\f\021\005aYbB\001\005\032\023\tQ\"!\001\004Qe\026$WMZ\005\0039u\021aa\025;sS:<'B\001\016\003\021!y\002A!E!\002\0239\022\001B7tO\002BQ!\t\001\005\002\t\na\001P5oSRtDCA\022%!\tA\001\001C\003\026A\001\007q\003C\004'\001\005\005I\021A\024\002\t\r|\007/\037\013\003G!Bq!F\023\021\002\003\007q\003C\004+\001E\005I\021A\026\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\tAF\013\002\030[-\na\006\005\0020i5\t\001G\003\0022e\005IQO\\2iK\016\\W\r\032\006\003g\t\t!\"\0318o_R\fG/[8o\023\t)\004GA\tv]\016DWmY6fIZ\013'/[1oG\026Dqa\016\001\002\002\023\005\003(A\007qe>$Wo\031;Qe\0264\027\016_\013\002sA\021!hP\007\002w)\021A(P\001\005Y\006twMC\001?\003\021Q\027M^1\n\005qY\004bB!\001\003\003%\tAQ\001\raJ|G-^2u\003JLG/_\013\002\007B\021\001\002R\005\003\013\n\0211!\0238u\021\0359\005!!A\005\002!\013a\002\035:pIV\034G/\0227f[\026tG\017\006\002J\031B\021\001BS\005\003\027\n\0211!\0218z\021\035ie)!AA\002\r\0131\001\037\0232\021\035y\005!!A\005BA\013q\002\035:pIV\034G/\023;fe\006$xN]\013\002#B\031!+V%\016\003MS!\001\026\002\002\025\r|G\016\\3di&|g.\003\002W'\nA\021\n^3sCR|'\017C\004Y\001\005\005I\021A-\002\021\r\fg.R9vC2$\"AW/\021\005!Y\026B\001/\003\005\035\021un\0347fC:Dq!T,\002\002\003\007\021\nC\004`\001\005\005I\021\t1\002\021!\f7\017[\"pI\026$\022a\021\005\bE\002\t\t\021\"\021d\003\031)\027/^1mgR\021!\f\032\005\b\033\006\f\t\0211\001J\017\0351'!!A\t\002\035\f\001dU2bY\006\024VM\0327fGRLwN\\#yG\026\004H/[8o!\tA\001NB\004\002\005\005\005\t\022A5\024\007!T\027\003\005\003l]^\031S\"\0017\013\0055\024\021a\002:v]RLW.Z\005\003_2\024\021#\0212tiJ\f7\r\036$v]\016$\030n\03482\021\025\t\003\016\"\001r)\0059\007bB:i\003\003%)\005^\001\ti>\034FO]5oOR\t\021\bC\004wQ\006\005I\021Q<\002\013\005\004\b\017\\=\025\005\rB\b\"B\013v\001\0049\002b\002>i\003\003%\ti_\001\bk:\f\007\017\0357z)\tax\020E\002\t{^I!A \002\003\r=\003H/[8o\021!\t\t!_A\001\002\004\031\023a\001=%a!I\021Q\0015\002\002\023%\021qA\001\fe\026\fGMU3t_24X\r\006\002\002\nA\031!(a\003\n\007\00551H\001\004PE*,7\r\036")
/*    */ public class ScalaReflectionException extends Exception implements Product, Serializable {
/*    */   private final String msg;
/*    */   
/*    */   public static <A> Function1<String, A> andThen(Function1<ScalaReflectionException, A> paramFunction1) {
/*    */     return ScalaReflectionException$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, ScalaReflectionException> compose(Function1<A, String> paramFunction1) {
/*    */     return ScalaReflectionException$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public String msg() {
/* 68 */     return this.msg;
/*    */   }
/*    */   
/*    */   public ScalaReflectionException copy(String msg) {
/* 68 */     return new ScalaReflectionException(msg);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 68 */     return msg();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 68 */     return "ScalaReflectionException";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 68 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 68 */     switch (x$1) {
/*    */       default:
/* 68 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 68 */     return msg();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 68 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 68 */     return x$1 instanceof ScalaReflectionException;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 68 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/ScalaReflectionException
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/ScalaReflectionException
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual msg : ()Ljava/lang/String;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual msg : ()Ljava/lang/String;
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
/*    */     //   #68	-> 0
/*    */     //   #236	-> 12
/*    */     //   #68	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	this	Lscala/ScalaReflectionException;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ScalaReflectionException(String msg) {
/* 68 */     super(msg);
/* 68 */     Product$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ScalaReflectionException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */