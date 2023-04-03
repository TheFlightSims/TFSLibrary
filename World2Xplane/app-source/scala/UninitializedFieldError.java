/*    */ package scala;
/*    */ 
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005]a\001B\001\003\005\026\021q#\0268j]&$\030.\0317ju\026$g)[3mI\026\023(o\034:\013\003\r\tQa]2bY\006\034\001a\005\003\001\r9\t\002CA\004\f\035\tA\021\"D\001\003\023\tQ!!A\004qC\016\\\027mZ3\n\0051i!\001\005*v]RLW.Z#yG\026\004H/[8o\025\tQ!\001\005\002\t\037%\021\001C\001\002\b!J|G-^2u!\tA!#\003\002\024\005\ta1+\032:jC2L'0\0312mK\"AQ\003\001BK\002\023\005a#A\002ng\036,\022a\006\t\0031mq!\001C\r\n\005i\021\021A\002)sK\022,g-\003\002\035;\t11\013\036:j]\036T!A\007\002\t\021}\001!\021#Q\001\n]\tA!\\:hA!)\021\005\001C\001E\0051A(\0338jiz\"\"a\t\023\021\005!\001\001\"B\013!\001\0049\002\"B\021\001\t\0031CCA\022(\021\025AS\0051\001*\003\ry'M\033\t\003\021)J!a\013\002\003\007\005s\027\020C\004.\001\005\005I\021\001\030\002\t\r|\007/\037\013\003G=Bq!\006\027\021\002\003\007q\003C\0042\001E\005I\021\001\032\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\t1G\013\002\030i-\nQ\007\005\0027w5\tqG\003\0029s\005IQO\\2iK\016\\W\r\032\006\003u\t\t!\"\0318o_R\fG/[8o\023\tatGA\tv]\016DWmY6fIZ\013'/[1oG\026DqA\020\001\002\002\023\005s(A\007qe>$Wo\031;Qe\0264\027\016_\013\002\001B\021\021IR\007\002\005*\0211\tR\001\005Y\006twMC\001F\003\021Q\027M^1\n\005q\021\005b\002%\001\003\003%\t!S\001\raJ|G-^2u\003JLG/_\013\002\025B\021\001bS\005\003\031\n\0211!\0238u\021\035q\005!!A\005\002=\013a\002\035:pIV\034G/\0227f[\026tG\017\006\002*!\"9\021+TA\001\002\004Q\025a\001=%c!91\013AA\001\n\003\"\026a\0049s_\022,8\r^%uKJ\fGo\034:\026\003U\0032AV-*\033\0059&B\001-\003\003)\031w\016\0347fGRLwN\\\005\0035^\023\001\"\023;fe\006$xN\035\005\b9\002\t\t\021\"\001^\003!\031\027M\\#rk\006dGC\0010b!\tAq,\003\002a\005\t9!i\\8mK\006t\007bB)\\\003\003\005\r!\013\005\bG\002\t\t\021\"\021e\003!A\027m\0355D_\022,G#\001&\t\017\031\004\021\021!C!O\0061Q-];bYN$\"A\0305\t\017E+\027\021!a\001S\0359!NAA\001\022\003Y\027aF+oS:LG/[1mSj,GMR5fY\022,%O]8s!\tAANB\004\002\005\005\005\t\022A7\024\0071t\027\003\005\003pe^\031S\"\0019\013\005E\024\021a\002:v]RLW.Z\005\003gB\024\021#\0212tiJ\f7\r\036$v]\016$\030n\03482\021\025\tC\016\"\001v)\005Y\007bB<m\003\003%)\005_\001\ti>\034FO]5oOR\t\001\tC\004{Y\006\005I\021Q>\002\013\005\004\b\017\\=\025\005\rb\b\"B\013z\001\0049\002b\002@m\003\003%\ti`\001\bk:\f\007\017\0357z)\021\t\t!a\002\021\t!\t\031aF\005\004\003\013\021!AB(qi&|g\016\003\005\002\nu\f\t\0211\001$\003\rAH\005\r\005\n\003\033a\027\021!C\005\003\037\t1B]3bIJ+7o\0347wKR\021\021\021\003\t\004\003\006M\021bAA\013\005\n1qJ\0316fGR\004")
/*    */ public final class UninitializedFieldError extends RuntimeException implements Product, Serializable {
/*    */   private final String msg;
/*    */   
/*    */   public static <A> Function1<String, A> andThen(Function1<UninitializedFieldError, A> paramFunction1) {
/*    */     return UninitializedFieldError$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, UninitializedFieldError> compose(Function1<A, String> paramFunction1) {
/*    */     return UninitializedFieldError$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public String msg() {
/* 21 */     return this.msg;
/*    */   }
/*    */   
/*    */   public UninitializedFieldError copy(String msg) {
/* 21 */     return new UninitializedFieldError(msg);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 21 */     return msg();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 21 */     return "UninitializedFieldError";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 21 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 21 */     switch (x$1) {
/*    */       default:
/* 21 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 21 */     return msg();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 21 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 21 */     return x$1 instanceof UninitializedFieldError;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 21 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 67
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/UninitializedFieldError
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 71
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/UninitializedFieldError
/*    */     //   27: astore_3
/*    */     //   28: aload_0
/*    */     //   29: invokevirtual msg : ()Ljava/lang/String;
/*    */     //   32: aload_3
/*    */     //   33: invokevirtual msg : ()Ljava/lang/String;
/*    */     //   36: astore #4
/*    */     //   38: dup
/*    */     //   39: ifnonnull -> 51
/*    */     //   42: pop
/*    */     //   43: aload #4
/*    */     //   45: ifnull -> 59
/*    */     //   48: goto -> 63
/*    */     //   51: aload #4
/*    */     //   53: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   56: ifeq -> 63
/*    */     //   59: iconst_1
/*    */     //   60: goto -> 64
/*    */     //   63: iconst_0
/*    */     //   64: ifeq -> 71
/*    */     //   67: iconst_1
/*    */     //   68: goto -> 72
/*    */     //   71: iconst_0
/*    */     //   72: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #21	-> 0
/*    */     //   #236	-> 12
/*    */     //   #21	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	73	0	this	Lscala/UninitializedFieldError;
/*    */     //   0	73	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public UninitializedFieldError(String msg) {
/* 21 */     super(
/* 22 */         msg);
/*    */     Product$class.$init$(this);
/*    */   }
/*    */   
/*    */   public UninitializedFieldError(Object obj) {
/* 24 */     this((obj == null) ? "null" : obj.toString());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\UninitializedFieldError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */