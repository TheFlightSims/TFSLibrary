/*    */ package scala.util.parsing.combinator.testing;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055b\001B\001\003\0016\0211a\025;s\025\t\031A!A\004uKN$\030N\\4\013\005\0251\021AC2p[\nLg.\031;pe*\021q\001C\001\ba\006\0248/\0338h\025\tI!\"\001\003vi&d'\"A\006\002\013M\034\027\r\\1\004\001M!\001A\004\n\026!\ty\001#D\001\013\023\t\t\"B\001\004B]f\024VM\032\t\003\037MI!\001\006\006\003\017A\023x\016Z;diB\021qBF\005\003/)\021AbU3sS\006d\027N_1cY\026D\001\"\007\001\003\026\004%\tAG\001\002gV\t1\004\005\002\035?9\021q\"H\005\003=)\ta\001\025:fI\0264\027B\001\021\"\005\031\031FO]5oO*\021aD\003\005\tG\001\021\t\022)A\0057\005\0211\017\t\005\006K\001!\tAJ\001\007y%t\027\016\036 \025\005\035J\003C\001\025\001\033\005\021\001\"B\r%\001\004Y\002bB\026\001\003\003%\t\001L\001\005G>\004\030\020\006\002([!9\021D\013I\001\002\004Y\002bB\030\001#\003%\t\001M\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005\t$FA\0163W\005\031\004C\001\033:\033\005)$B\001\0348\003%)hn\0315fG.,GM\003\0029\025\005Q\021M\0348pi\006$\030n\0348\n\005i*$!E;oG\",7m[3e-\006\024\030.\0318dK\"9A\bAA\001\n\003j\024!\0049s_\022,8\r\036)sK\032L\0070F\001?!\tyD)D\001A\025\t\t%)\001\003mC:<'\"A\"\002\t)\fg/Y\005\003A\001CqA\022\001\002\002\023\005q)\001\007qe>$Wo\031;Be&$\0300F\001I!\ty\021*\003\002K\025\t\031\021J\034;\t\0171\003\021\021!C\001\033\006q\001O]8ek\016$X\t\\3nK:$HC\001(R!\tyq*\003\002Q\025\t\031\021I\\=\t\017I[\025\021!a\001\021\006\031\001\020J\031\t\017Q\003\021\021!C!+\006y\001O]8ek\016$\030\n^3sCR|'/F\001W!\r9&LT\007\0021*\021\021LC\001\013G>dG.Z2uS>t\027BA.Y\005!IE/\032:bi>\024\bbB/\001\003\003%\tAX\001\tG\006tW)];bYR\021qL\031\t\003\037\001L!!\031\006\003\017\t{w\016\\3b]\"9!\013XA\001\002\004q\005b\0023\001\003\003%\t%Z\001\tQ\006\034\bnQ8eKR\t\001\nC\004h\001\005\005I\021\t5\002\021Q|7\013\036:j]\036$\022A\020\005\bU\002\t\t\021\"\021l\003\031)\027/^1mgR\021q\f\034\005\b%&\f\t\0211\001OQ\021\001a.]:\021\005=y\027B\0019\013\005)!W\r\035:fG\006$X\rZ\021\002e\006QB\013[5tA\rd\027m]:!o&dG\016\t2fAI,Wn\034<fI\006\nA/\001\0043]E\002d\006M\004\bm\n\t\t\021#\001x\003\r\031FO\035\t\003Qa4q!\001\002\002\002#\005\021pE\002yuV\001Ba\037@\034O5\tAP\003\002~\025\0059!/\0368uS6,\027BA@}\005E\t%m\035;sC\016$h)\0368di&|g.\r\005\007Ka$\t!a\001\025\003]Dqa\032=\002\002\023\025\003\016C\005\002\na\f\t\021\"!\002\f\005)\021\r\0359msR\031q%!\004\t\re\t9\0011\001\034\021%\t\t\002_A\001\n\003\013\031\"A\004v]\006\004\b\017\\=\025\t\005U\0211\004\t\005\037\005]1$C\002\002\032)\021aa\0249uS>t\007\"CA\017\003\037\t\t\0211\001(\003\rAH\005\r\005\n\003CA\030\021!C\005\003G\t1B]3bIJ+7o\0347wKR\021\021Q\005\t\004\005\035\022bAA\025\001\n1qJ\0316fGRDC\001\0378rg\002")
/*    */ public class Str implements Product, Serializable {
/*    */   private final String s;
/*    */   
/*    */   public static <A> Function1<String, A> andThen(Function1<Str, A> paramFunction1) {
/*    */     return Str$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, Str> compose(Function1<A, String> paramFunction1) {
/*    */     return Str$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public String s() {
/* 13 */     return this.s;
/*    */   }
/*    */   
/*    */   public Str copy(String s) {
/* 13 */     return new Str(s);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 13 */     return s();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 13 */     return "Str";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 13 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 13 */     switch (x$1) {
/*    */       default:
/* 13 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 13 */     return s();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 13 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 13 */     return x$1 instanceof Str;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 13 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 13 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/util/parsing/combinator/testing/Str
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/util/parsing/combinator/testing/Str
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual s : ()Ljava/lang/String;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual s : ()Ljava/lang/String;
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
/*    */     //   #13	-> 0
/*    */     //   #236	-> 12
/*    */     //   #13	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	this	Lscala/util/parsing/combinator/testing/Str;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Str(String s) {
/* 13 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\testing\Str.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */