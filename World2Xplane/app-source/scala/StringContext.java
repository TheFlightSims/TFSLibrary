/*     */ package scala;
/*     */ 
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Uc\001B\001\003\001\026\021Qb\025;sS:<7i\0348uKb$(\"A\002\002\013M\034\027\r\\1\004\001M!\001A\002\006\016!\t9\001\"D\001\003\023\tI!A\001\004B]f\024VM\032\t\003\017-I!\001\004\002\003\017A\023x\016Z;diB\021qAD\005\003\037\t\021AbU3sS\006d\027N_1cY\026D\001\"\005\001\003\026\004%\tAE\001\006a\006\024Ho]\013\002'A\031q\001\006\f\n\005U\021!A\003\037sKB,\027\r^3e}A\021qC\007\b\003\017aI!!\007\002\002\rA\023X\rZ3g\023\tYBD\001\004TiJLgn\032\006\0033\tA\001B\b\001\003\022\003\006IaE\001\007a\006\024Ho\035\021\t\013\001\002A\021A\021\002\rqJg.\033;?)\t\0213\005\005\002\b\001!)\021c\ba\001'!)Q\005\001C\001M\005a1\r[3dW2+gn\032;igR\021qE\013\t\003\017!J!!\013\002\003\tUs\027\016\036\005\006W\021\002\r\001L\001\005CJ<7\017E\002.aMr!a\002\030\n\005=\022\021a\0029bG.\fw-Z\005\003cI\0221aU3r\025\ty#\001\005\002\bi%\021QG\001\002\004\003:L\b\"B\034\001\t\003A\024!A:\025\005YI\004\"B\0267\001\004Q\004cA\004\025g!)A\b\001C\001{\005\031!/Y<\025\005Yq\004\"B\026<\001\004Q\004\"\002!\001\t\003\t\025\001F:uC:$\027M\0353J]R,'\017]8mCR|'\017F\002\027\005\036CQaQ A\002\021\013q\001\035:pG\026\0348\017\005\003\b\013Z1\022B\001$\003\005%1UO\\2uS>t\027\007C\003,\001\007A\006\003\004J\001\t%\tAS\001\002MR\021ac\023\005\006W!\003\rA\017\005\b\033\002\t\t\021\"\021O\0035\001(o\0343vGR\004&/\0324jqV\tq\n\005\002Q+6\t\021K\003\002S'\006!A.\0318h\025\005!\026\001\0026bm\006L!aG)\t\017]\003\021\021!C\0011\006a\001O]8ek\016$\030I]5usV\t\021\f\005\002\b5&\0211L\001\002\004\023:$\bbB/\001\003\003%\tAX\001\017aJ|G-^2u\0132,W.\0328u)\t\031t\fC\004a9\006\005\t\031A-\002\007a$\023\007C\004c\001\005\005I\021I2\002\037A\024x\016Z;di&#XM]1u_J,\022\001\032\t\004K\"\034T\"\0014\013\005\035\024\021AC2pY2,7\r^5p]&\021\021N\032\002\t\023R,'/\031;pe\"91\016AA\001\n\003a\027\001C2b]\026\013X/\0317\025\0055\004\bCA\004o\023\ty'AA\004C_>dW-\0318\t\017\001T\027\021!a\001g!9!\017AA\001\n\003\032\030\001\0035bg\"\034u\016Z3\025\003eCq!\036\001\002\002\023\005c/\001\005u_N#(/\0338h)\005y\005b\002=\001\003\003%\t%_\001\007KF,\030\r\\:\025\0055T\bb\0021x\003\003\005\raM\004\006y\nA\t!`\001\016'R\024\030N\\4D_:$X\r\037;\021\005\035qh!B\001\003\021\003y8c\001@\007\033!1\001E C\001\003\007!\022! \004\007\003\017q\b!!\003\003-%sg/\0317jI\026\0338-\0319f\013b\034W\r\035;j_:\034B!!\002\002\fA\031Q&!\004\n\007\005=!G\001\rJY2,w-\0317Be\036,X.\0328u\013b\034W\r\035;j_:D!\"a\005\002\006\t\005\t\025!\003\027\003\r\031HO\035\005\013\003/\t)A!A!\002\023I\026aA5eq\"9\001%!\002\005\002\005mACBA\017\003C\t\031\003\005\003\002 \005\025Q\"\001@\t\017\005M\021\021\004a\001-!9\021qCA\r\001\004I\006bBA\024}\022\005\021\021F\001\riJ,\027\r^#tG\006\004Xm\035\013\004-\005-\002bBA\n\003K\001\rA\006\005\n\003_q\030\021!CA\003c\tQ!\0319qYf$2AIA\032\021\031\t\022Q\006a\001'!I\021q\007@\002\002\023\005\025\021H\001\013k:\f\007\017\0357z'\026\fH\003BA\036\003\013\002RaBA\037\003\003J1!a\020\003\005\031y\005\017^5p]B!Q-a\021\027\023\t\td\rC\005\002H\005U\022\021!a\001E\005\031\001\020\n\031\t\023\005-c0!A\005\n\0055\023a\003:fC\022\024Vm]8mm\026$\"!a\024\021\007A\013\t&C\002\002TE\023aa\0242kK\016$\b")
/*     */ public class StringContext implements Product, Serializable {
/*     */   private final Seq<String> parts;
/*     */   
/*     */   public Seq<String> parts() {
/*  51 */     return this.parts;
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  51 */     return "StringContext";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*  51 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*  51 */     switch (x$1) {
/*     */       default:
/*  51 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/*  51 */     return parts();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  51 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*  51 */     return x$1 instanceof StringContext;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  51 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  51 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 75
/*     */     //   5: aload_1
/*     */     //   6: instanceof scala/StringContext
/*     */     //   9: ifeq -> 17
/*     */     //   12: iconst_1
/*     */     //   13: istore_2
/*     */     //   14: goto -> 19
/*     */     //   17: iconst_0
/*     */     //   18: istore_2
/*     */     //   19: iload_2
/*     */     //   20: ifeq -> 79
/*     */     //   23: aload_1
/*     */     //   24: checkcast scala/StringContext
/*     */     //   27: astore #4
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual parts : ()Lscala/collection/Seq;
/*     */     //   33: aload #4
/*     */     //   35: invokevirtual parts : ()Lscala/collection/Seq;
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
/*     */     //   #51	-> 0
/*     */     //   #236	-> 12
/*     */     //   #51	-> 19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	81	0	this	Lscala/StringContext;
/*     */     //   0	81	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public StringContext(Seq<String> parts) {
/*  51 */     Product$class.$init$(this);
/*     */   }
/*     */   
/*     */   public void checkLengths(Seq args) {
/*  61 */     if (parts().length() != args.length() + 1)
/*  62 */       throw new IllegalArgumentException("wrong number of arguments for interpolated string"); 
/*     */   }
/*     */   
/*     */   public String s(Seq<Object> args) {
/*  90 */     return standardInterpolator((Function1<String, String>)new StringContext$$anonfun$s$1(this), args);
/*     */   }
/*     */   
/*     */   public class StringContext$$anonfun$s$1 extends AbstractFunction1<String, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(String str) {
/*  90 */       return StringContext$.MODULE$.treatEscapes(str);
/*     */     }
/*     */     
/*     */     public StringContext$$anonfun$s$1(StringContext $outer) {}
/*     */   }
/*     */   
/*     */   public String raw(Seq<Object> args) {
/* 114 */     return standardInterpolator((Function1<String, String>)new StringContext$$anonfun$raw$1(this), args);
/*     */   }
/*     */   
/*     */   public class StringContext$$anonfun$raw$1 extends AbstractFunction1<String, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(String x) {
/* 114 */       return Predef$.MODULE$.<String>identity(x);
/*     */     }
/*     */     
/*     */     public StringContext$$anonfun$raw$1(StringContext $outer) {}
/*     */   }
/*     */   
/*     */   public String standardInterpolator(Function1<Object, String> process, Seq<Object> args) {
/* 117 */     checkLengths(args);
/* 118 */     Iterator pi = parts().iterator();
/* 119 */     Iterator ai = args.iterator();
/* 120 */     StringBuilder bldr = new StringBuilder(process.apply(pi.next()));
/* 121 */     while (ai.hasNext()) {
/* 122 */       bldr.append(ai.next());
/* 123 */       bldr.append(process.apply(pi.next()));
/*     */     } 
/* 125 */     return bldr.toString();
/*     */   }
/*     */   
/*     */   public static String treatEscapes(String paramString) {
/*     */     return StringContext$.MODULE$.treatEscapes(paramString);
/*     */   }
/*     */   
/*     */   public static class InvalidEscapeException extends IllegalArgumentException {
/*     */     public InvalidEscapeException(String str, int idx) {
/* 175 */       super((
/* 176 */           new StringBuilder()).append("invalid escape character at index ").append(BoxesRunTime.boxToInteger(idx)).append(" in \"").append(str).append("\"").toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\StringContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */