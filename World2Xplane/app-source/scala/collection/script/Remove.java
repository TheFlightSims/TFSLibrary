/*    */ package scala.collection.script;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ec\001B\001\003\001&\021aAU3n_Z,'BA\002\005\003\031\0318M]5qi*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"F\n\006\001-ya$\t\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007c\001\t\022'5\t!!\003\002\023\005\t9Q*Z:tC\036,\007C\001\013\026\031\001!aA\006\001\005\006\0049\"!A!\022\005aY\002C\001\007\032\023\tQbAA\004O_RD\027N\\4\021\0051a\022BA\017\007\005\r\te.\037\t\003\031}I!\001\t\004\003\017A\023x\016Z;diB\021ABI\005\003G\031\021AbU3sS\006d\027N_1cY\026D\001\"\n\001\003\026\004%\tAJ\001\tY>\034\027\r^5p]V\tq\005\005\002\021Q%\021\021F\001\002\t\031>\034\027\r^5p]\"A1\006\001B\tB\003%q%A\005m_\016\fG/[8oA!AQ\006\001BK\002\023\005a&\001\003fY\026lW#A\n\t\021A\002!\021#Q\001\nM\tQ!\0327f[\002BQA\r\001\005\002M\na\001P5oSRtDc\001\0336mA\031\001\003A\n\t\013\025\n\004\031A\024\t\0135\n\004\031A\n\t\013I\002A\021\001\035\025\005QJ\004\"B\0278\001\004\031\002bB\036\001\003\003%\t\001P\001\005G>\004\0300\006\002>\001R\031a(\021\"\021\007A\001q\b\005\002\025\001\022)aC\017b\001/!9QE\017I\001\002\0049\003bB\027;!\003\005\ra\020\005\b\t\002\t\n\021\"\001F\0039\031w\016]=%I\0264\027-\0367uIE*\"AR)\026\003\035S#a\n%,\003%\003\"AS(\016\003-S!\001T'\002\023Ut7\r[3dW\026$'B\001(\007\003)\tgN\\8uCRLwN\\\005\003!.\023\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\t\02512I1\001\030\021\035\031\006!%A\005\002Q\013abY8qs\022\"WMZ1vYR$#'\006\002V/V\taK\013\002\024\021\022)aC\025b\001/!9\021\fAA\001\n\003R\026!\0049s_\022,8\r\036)sK\032L\0070F\001\\!\ta\026-D\001^\025\tqv,\001\003mC:<'\"\0011\002\t)\fg/Y\005\003Ev\023aa\025;sS:<\007b\0023\001\003\003%\t!Z\001\raJ|G-^2u\003JLG/_\013\002MB\021AbZ\005\003Q\032\0211!\0238u\021\035Q\007!!A\005\002-\fa\002\035:pIV\034G/\0227f[\026tG\017\006\002\034Y\"9Q.[A\001\002\0041\027a\001=%c!9q\016AA\001\n\003\002\030a\0049s_\022,8\r^%uKJ\fGo\034:\026\003E\0042A]:\034\033\005!\021B\001;\005\005!IE/\032:bi>\024\bb\002<\001\003\003%\ta^\001\tG\006tW)];bYR\021\001p\037\t\003\031eL!A\037\004\003\017\t{w\016\\3b]\"9Q.^A\001\002\004Y\002bB?\001\003\003%\tE`\001\tQ\006\034\bnQ8eKR\ta\rC\005\002\002\001\t\t\021\"\021\002\004\005AAo\\*ue&tw\rF\001\\\021%\t9\001AA\001\n\003\nI!\001\004fcV\fGn\035\013\004q\006-\001\002C7\002\006\005\005\t\031A\016\b\023\005=!!!A\t\002\005E\021A\002*f[>4X\rE\002\021\003'1\001\"\001\002\002\002#\005\021QC\n\005\003'Y\021\005C\0043\003'!\t!!\007\025\005\005E\001BCA\001\003'\t\t\021\"\022\002\004!Q\021qDA\n\003\003%\t)!\t\002\013\005\004\b\017\\=\026\t\005\r\022\021\006\013\007\003K\tY#!\f\021\tA\001\021q\005\t\004)\005%BA\002\f\002\036\t\007q\003\003\004&\003;\001\ra\n\005\b[\005u\001\031AA\024\021)\t\t$a\005\002\002\023\005\0251G\001\bk:\f\007\017\0357z+\021\t)$!\022\025\t\005]\022q\t\t\006\031\005e\022QH\005\004\003w1!AB(qi&|g\016\005\004\r\0039\0231I\005\004\003\0032!A\002+va2,'\007E\002\025\003\013\"aAFA\030\005\0049\002BCA%\003_\t\t\0211\001\002L\005\031\001\020\n\031\021\tA\001\0211\t\005\013\003\037\n\031\"!A\005\n\005E\023a\003:fC\022\024Vm]8mm\026$\"!a\025\021\007q\013)&C\002\002Xu\023aa\0242kK\016$\b")
/*    */ public class Remove<A> implements Message<A>, Product, Serializable {
/*    */   private final Location location;
/*    */   
/*    */   private final A elem;
/*    */   
/*    */   public Location location() {
/* 51 */     return this.location;
/*    */   }
/*    */   
/*    */   public A elem() {
/* 51 */     return this.elem;
/*    */   }
/*    */   
/*    */   public <A> Remove<A> copy(Location location, Object elem) {
/* 51 */     return new Remove(location, (A)elem);
/*    */   }
/*    */   
/*    */   public <A> Location copy$default$1() {
/* 51 */     return location();
/*    */   }
/*    */   
/*    */   public <A> A copy$default$2() {
/* 51 */     return elem();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 51 */     return "Remove";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 51 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 51 */     switch (x$1) {
/*    */       default:
/* 51 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 51 */     return location();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 51 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 51 */     return x$1 instanceof Remove;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 51 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 159
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/collection/script/Remove
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 163
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/collection/script/Remove
/*    */     //   27: astore #6
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual location : ()Lscala/collection/script/Location;
/*    */     //   33: aload #6
/*    */     //   35: invokevirtual location : ()Lscala/collection/script/Location;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 155
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 155
/*    */     //   58: aload_0
/*    */     //   59: invokevirtual elem : ()Ljava/lang/Object;
/*    */     //   62: aload #6
/*    */     //   64: invokevirtual elem : ()Ljava/lang/Object;
/*    */     //   67: astore #5
/*    */     //   69: dup
/*    */     //   70: astore #4
/*    */     //   72: aload #5
/*    */     //   74: if_acmpne -> 81
/*    */     //   77: iconst_1
/*    */     //   78: goto -> 139
/*    */     //   81: aload #4
/*    */     //   83: ifnonnull -> 90
/*    */     //   86: iconst_0
/*    */     //   87: goto -> 139
/*    */     //   90: aload #4
/*    */     //   92: instanceof java/lang/Number
/*    */     //   95: ifeq -> 111
/*    */     //   98: aload #4
/*    */     //   100: checkcast java/lang/Number
/*    */     //   103: aload #5
/*    */     //   105: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*    */     //   108: goto -> 139
/*    */     //   111: aload #4
/*    */     //   113: instanceof java/lang/Character
/*    */     //   116: ifeq -> 132
/*    */     //   119: aload #4
/*    */     //   121: checkcast java/lang/Character
/*    */     //   124: aload #5
/*    */     //   126: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*    */     //   129: goto -> 139
/*    */     //   132: aload #4
/*    */     //   134: aload #5
/*    */     //   136: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   139: ifeq -> 155
/*    */     //   142: aload #6
/*    */     //   144: aload_0
/*    */     //   145: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   148: ifeq -> 155
/*    */     //   151: iconst_1
/*    */     //   152: goto -> 156
/*    */     //   155: iconst_0
/*    */     //   156: ifeq -> 163
/*    */     //   159: iconst_1
/*    */     //   160: goto -> 164
/*    */     //   163: iconst_0
/*    */     //   164: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #51	-> 0
/*    */     //   #236	-> 12
/*    */     //   #51	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	165	0	this	Lscala/collection/script/Remove;
/*    */     //   0	165	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Remove(Location location, Object elem) {
/* 51 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Remove(Object elem) {
/* 52 */     this(NoLo$.MODULE$, (A)elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\Remove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */