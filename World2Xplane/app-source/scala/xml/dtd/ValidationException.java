/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\rb\001B\001\003\001&\0211CV1mS\022\fG/[8o\013b\034W\r\035;j_:T!a\001\003\002\007\021$HM\003\002\006\r\005\031\0010\0347\013\003\035\tQa]2bY\006\034\001a\005\003\001\025YQ\002CA\006\024\035\ta\021C\004\002\016!5\taB\003\002\020\021\0051AH]8pizJ\021aB\005\003%\031\tq\001]1dW\006<W-\003\002\025+\tIQ\t_2faRLwN\034\006\003%\031\001\"a\006\r\016\003\031I!!\007\004\003\017A\023x\016Z;diB\021qcG\005\0039\031\021AbU3sS\006d\027N_1cY\026D\001B\b\001\003\026\004%\taH\001\002KV\t\001\005\005\002\"I9\021qCI\005\003G\031\ta\001\025:fI\0264\027BA\023'\005\031\031FO]5oO*\0211E\002\005\tQ\001\021\t\022)A\005A\005\021Q\r\t\005\006U\001!\taK\001\007y%t\027\016\036 \025\0051r\003CA\027\001\033\005\021\001\"\002\020*\001\004\001\003b\002\031\001\003\003%\t!M\001\005G>\004\030\020\006\002-e!9ad\fI\001\002\004\001\003b\002\033\001#\003%\t!N\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\0051$F\001\0218W\005A\004CA\035?\033\005Q$BA\036=\003%)hn\0315fG.,GM\003\002>\r\005Q\021M\0348pi\006$\030n\0348\n\005}R$!E;oG\",7m[3e-\006\024\030.\0318dK\"9\021\tAA\001\n\003\022\025!\0049s_\022,8\r\036)sK\032L\0070F\001D!\t!\025*D\001F\025\t1u)\001\003mC:<'\"\001%\002\t)\fg/Y\005\003K\025Cqa\023\001\002\002\023\005A*\001\007qe>$Wo\031;Be&$\0300F\001N!\t9b*\003\002P\r\t\031\021J\034;\t\017E\003\021\021!C\001%\006q\001O]8ek\016$X\t\\3nK:$HCA*W!\t9B+\003\002V\r\t\031\021I\\=\t\017]\003\026\021!a\001\033\006\031\001\020J\031\t\017e\003\021\021!C!5\006y\001O]8ek\016$\030\n^3sCR|'/F\001\\!\ravlU\007\002;*\021aLB\001\013G>dG.Z2uS>t\027B\0011^\005!IE/\032:bi>\024\bb\0022\001\003\003%\taY\001\tG\006tW)];bYR\021Am\032\t\003/\025L!A\032\004\003\017\t{w\016\\3b]\"9q+YA\001\002\004\031\006bB5\001\003\003%\tE[\001\tQ\006\034\bnQ8eKR\tQ\nC\004m\001\005\005I\021I7\002\r\025\fX/\0317t)\t!g\016C\004XW\006\005\t\031A*\b\017A\024\021\021!E\001c\006\031b+\0317jI\006$\030n\0348Fq\016,\007\017^5p]B\021QF\035\004\b\003\t\t\t\021#\001t'\r\021HO\007\t\005kb\004C&D\001w\025\t9h!A\004sk:$\030.\\3\n\005e4(!E!cgR\024\030m\031;Gk:\034G/[8oc!)!F\035C\001wR\t\021\017C\004~e\006\005IQ\t@\002\021Q|7\013\036:j]\036$\022a\021\005\n\003\003\021\030\021!CA\003\007\tQ!\0319qYf$2\001LA\003\021\025qr\0201\001!\021%\tIA]A\001\n\003\013Y!A\004v]\006\004\b\017\\=\025\t\0055\0211\003\t\005/\005=\001%C\002\002\022\031\021aa\0249uS>t\007\"CA\013\003\017\t\t\0211\001-\003\rAH\005\r\005\n\0033\021\030\021!C\005\0037\t1B]3bIJ+7o\0347wKR\021\021Q\004\t\004\t\006}\021bAA\021\013\n1qJ\0316fGR\004")
/*    */ public class ValidationException extends Exception implements Product, Serializable {
/*    */   private final String e;
/*    */   
/*    */   public static <A> Function1<String, A> andThen(Function1<ValidationException, A> paramFunction1) {
/*    */     return ValidationException$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, ValidationException> compose(Function1<A, String> paramFunction1) {
/*    */     return ValidationException$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public String e() {
/* 15 */     return this.e;
/*    */   }
/*    */   
/*    */   public ValidationException copy(String e) {
/* 15 */     return new ValidationException(e);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 15 */     return e();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 15 */     return "ValidationException";
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
/* 15 */     return e();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 15 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 15 */     return x$1 instanceof ValidationException;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 15 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/dtd/ValidationException
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/dtd/ValidationException
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual e : ()Ljava/lang/String;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual e : ()Ljava/lang/String;
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
/*    */     //   0	81	0	this	Lscala/xml/dtd/ValidationException;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ValidationException(String e) {
/* 15 */     super(e);
/* 15 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ValidationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */