/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.math.Ordering;
/*     */ import scala.math.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.sys.package$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\teb\001B\001\003\t&\021AAT8eK*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQQcE\003\001\027=q\022\005\005\002\r\0335\ta!\003\002\017\r\t1\021I\\=SK\032\0042\001E\t\024\033\005\021\021B\001\n\003\005\035\te\013\024+sK\026\004\"\001F\013\r\001\021)a\003\001b\001/\t\t\021)\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\bC\001\007 \023\t\001cAA\004Qe>$Wo\031;\021\0051\021\023BA\022\007\0051\031VM]5bY&T\030M\0317f\021!)\003A!f\001\n\0031\023\001\0023bi\006,\022a\005\005\tQ\001\021\t\022)A\005'\005)A-\031;bA!A!\006\001BK\002\023\0051&\001\003mK\032$X#A\b\t\0215\002!\021#Q\001\n=\tQ\001\\3gi\002B\001b\f\001\003\026\004%\taK\001\006e&<\007\016\036\005\tc\001\021\t\022)A\005\037\0051!/[4ii\002BQa\r\001\005\002Q\na\001P5oSRtD\003B\0337oa\0022\001\005\001\024\021\025)#\0071\001\024\021\025Q#\0071\001\020\021\025y#\0071\001\020\021\035Q\004A1A\005Bm\nqAY1mC:\034W-F\001=!\taQ(\003\002?\r\t\031\021J\034;\t\r\001\003\001\025!\003=\003!\021\027\r\\1oG\026\004\003b\002\"\001\005\004%\teO\001\006I\026\004H\017\033\005\007\t\002\001\013\021\002\037\002\r\021,\007\017\0365!\021\0251\005\001\"\021H\003!IG/\032:bi>\024XC\001%O+\005I\005c\001&L\0336\tA!\003\002M\t\tA\021\n^3sCR|'\017\005\002\025\035\022)q*\022b\001!\n\t!)\005\002\0247!)!\013\001C!'\006A1m\0348uC&t7/\006\002U7R\031Q\013\027/\021\00511\026BA,\007\005\035\021un\0347fC:DQ!W)A\002i\013QA^1mk\026\004\"\001F.\005\013=\013&\031\001)\t\013u\013\006\031\0010\002\021=\024H-\032:j]\036\0042a\0302[\035\ta\001-\003\002b\r\0059\001/Y2lC\036,\027BA2e\005!y%\017Z3sS:<'BA1\007\021\0251\007\001\"\021h\003\031Ign]3siV\021\001n\033\013\004S2l\007c\001\t\022UB\021Ac\033\003\006\037\026\024\r\001\025\005\0063\026\004\rA\033\005\006;\026\004\rA\034\t\004?\nT\007\"\0029\001\t\003\n\030A\002:f[>4X-\006\002skR\031qb\035<\t\013e{\007\031\001;\021\005Q)H!B(p\005\004\001\006\"B/p\001\0049\bcA0ci\")\021\020\001C!u\006I!/Z7pm\026l\025N\\\013\004w\006\005Q#\001?\021\0131ix0a\001\n\005y4!A\002+va2,'\007E\002\025\003\003!Qa\024=C\002A\0032\001E\t\000\021\035\t9\001\001C!\003\023\t\021B]3n_Z,W*\031=\026\t\005-\021\021C\013\003\003\033\001b\001D?\002\020\005M\001c\001\013\002\022\0211q*!\002C\002A\003B\001E\t\002\020!9\021q\003\001\005B\005e\021!\003:fE\006d\027M\\2f+\021\tY\"!\b\026\003U\"aaTA\013\005\004\001\006bBA\021\001\021\005\0231E\001\rY\0264GOU8uCRLwN\\\013\005\003K\tY#\006\002\002(A!\001\003AA\025!\r!\0221\006\003\007\037\006}!\031\001)\t\017\005=\002\001\"\021\0022\005i!/[4iiJ{G/\031;j_:,B!a\r\002:U\021\021Q\007\t\005!\001\t9\004E\002\025\003s!aaTA\027\005\004\001\006bBA\037\001\021\005\023qH\001\023I>,(\r\\3MK\032$(k\034;bi&|g.\006\003\002B\005\035SCAA\"!\021\001\002!!\022\021\007Q\t9\005\002\004P\003w\021\r\001\025\005\b\003\027\002A\021IA'\003M!w.\0362mKJKw\r\033;S_R\fG/[8o+\021\ty%!\026\026\005\005E\003\003\002\t\001\003'\0022\001FA+\t\031y\025\021\nb\001!\"I\021\021\f\001\002\002\023\005\0211L\001\005G>\004\0300\006\003\002^\005\rD\003CA0\003K\n9'a\033\021\tA\001\021\021\r\t\004)\005\rDA\002\f\002X\t\007q\003C\005&\003/\002\n\0211\001\002b!I!&a\026\021\002\003\007\021\021\016\t\005!E\t\t\007C\0050\003/\002\n\0211\001\002j!I\021q\016\001\022\002\023\005\021\021O\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\021\t\031(!#\026\005\005U$fA\n\002x-\022\021\021\020\t\005\003w\n))\004\002\002~)!\021qPAA\003%)hn\0315fG.,GMC\002\002\004\032\t!\"\0318o_R\fG/[8o\023\021\t9)! \003#Ut7\r[3dW\026$g+\031:jC:\034W\r\002\004\027\003[\022\ra\006\005\n\003\033\003\021\023!C\001\003\037\013abY8qs\022\"WMZ1vYR$#'\006\003\002\022\006UUCAAJU\ry\021q\017\003\007-\005-%\031A\f\t\023\005e\005!%A\005\002\005m\025AD2paf$C-\0324bk2$HeM\013\005\003#\013i\n\002\004\027\003/\023\ra\006\005\n\003C\003\021\021!C!\003G\013Q\002\035:pIV\034G\017\025:fM&DXCAAS!\021\t9+!-\016\005\005%&\002BAV\003[\013A\001\\1oO*\021\021qV\001\005U\0064\030-\003\003\0024\006%&AB*ue&tw\r\003\005\0028\002\t\t\021\"\001<\0031\001(o\0343vGR\f%/\033;z\021%\tY\fAA\001\n\003\ti,\001\bqe>$Wo\031;FY\026lWM\034;\025\007m\ty\fC\005\002B\006e\026\021!a\001y\005\031\001\020J\031\t\023\005\025\007!!A\005B\005\035\027a\0049s_\022,8\r^%uKJ\fGo\034:\026\005\005%\007c\001&L7!I\021Q\032\001\002\002\023\005\021qZ\001\tG\006tW)];bYR\031Q+!5\t\023\005\005\0271ZA\001\002\004Y\002\"CAk\001\005\005I\021IAl\003!A\027m\0355D_\022,G#\001\037\t\023\005m\007!!A\005B\005u\027\001\003;p'R\024\030N\\4\025\005\005\025\006\"CAq\001\005\005I\021IAr\003\031)\027/^1mgR\031Q+!:\t\023\005\005\027q\\A\001\002\004Yr!CAu\005\005\005\t\022BAv\003\021qu\016Z3\021\007A\tiO\002\005\002\005\005\005\t\022BAx'\021\tioC\021\t\017M\ni\017\"\001\002tR\021\0211\036\005\013\0037\fi/!A\005F\005u\007BCA}\003[\f\t\021\"!\002|\006)\021\r\0359msV!\021Q B\002)!\tyP!\002\003\b\t-\001\003\002\t\001\005\003\0012\001\006B\002\t\0311\022q\037b\001/!9Q%a>A\002\t\005\001b\002\026\002x\002\007!\021\002\t\005!E\021\t\001C\0040\003o\004\rA!\003\t\025\t=\021Q^A\001\n\003\023\t\"A\004v]\006\004\b\017\\=\026\t\tM!1\005\013\005\005+\0219\003E\003\r\005/\021Y\"C\002\003\032\031\021aa\0249uS>t\007#\003\007\003\036\t\005\"Q\005B\023\023\r\021yB\002\002\007)V\004H.Z\032\021\007Q\021\031\003\002\004\027\005\033\021\ra\006\t\005!E\021\t\003\003\006\003*\t5\021\021!a\001\005W\t1\001\037\0231!\021\001\002A!\t\t\025\t=\022Q^A\001\n\023\021\t$A\006sK\006$'+Z:pYZ,GC\001B\032!\021\t9K!\016\n\t\t]\022\021\026\002\007\037\nTWm\031;")
/*     */ public class Node<A> implements AVLTree<A>, Product, Serializable {
/*     */   private final A data;
/*     */   
/*     */   private final AVLTree<A> left;
/*     */   
/*     */   private final AVLTree<A> right;
/*     */   
/*     */   private final int balance;
/*     */   
/*     */   private final int depth;
/*     */   
/*     */   public A data() {
/*  74 */     return this.data;
/*     */   }
/*     */   
/*     */   public AVLTree<A> left() {
/*  74 */     return this.left;
/*     */   }
/*     */   
/*     */   public AVLTree<A> right() {
/*  74 */     return this.right;
/*     */   }
/*     */   
/*     */   public <A> Node<A> copy(Object data, AVLTree<A> left, AVLTree<A> right) {
/*  74 */     return new Node((A)data, left, right);
/*     */   }
/*     */   
/*     */   public <A> A copy$default$1() {
/*  74 */     return data();
/*     */   }
/*     */   
/*     */   public <A> AVLTree<A> copy$default$2() {
/*  74 */     return left();
/*     */   }
/*     */   
/*     */   public <A> AVLTree<A> copy$default$3() {
/*  74 */     return right();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  74 */     return "Node";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*  74 */     return 3;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*  74 */     switch (x$1) {
/*     */       default:
/*  74 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*  74 */     return data();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  74 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*  74 */     return x$1 instanceof Node;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  74 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  74 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 187
/*     */     //   5: aload_1
/*     */     //   6: instanceof scala/collection/mutable/Node
/*     */     //   9: ifeq -> 17
/*     */     //   12: iconst_1
/*     */     //   13: istore_2
/*     */     //   14: goto -> 19
/*     */     //   17: iconst_0
/*     */     //   18: istore_2
/*     */     //   19: iload_2
/*     */     //   20: ifeq -> 191
/*     */     //   23: aload_1
/*     */     //   24: checkcast scala/collection/mutable/Node
/*     */     //   27: astore #7
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual data : ()Ljava/lang/Object;
/*     */     //   33: aload #7
/*     */     //   35: invokevirtual data : ()Ljava/lang/Object;
/*     */     //   38: astore #4
/*     */     //   40: dup
/*     */     //   41: astore_3
/*     */     //   42: aload #4
/*     */     //   44: if_acmpne -> 51
/*     */     //   47: iconst_1
/*     */     //   48: goto -> 103
/*     */     //   51: aload_3
/*     */     //   52: ifnonnull -> 59
/*     */     //   55: iconst_0
/*     */     //   56: goto -> 103
/*     */     //   59: aload_3
/*     */     //   60: instanceof java/lang/Number
/*     */     //   63: ifeq -> 78
/*     */     //   66: aload_3
/*     */     //   67: checkcast java/lang/Number
/*     */     //   70: aload #4
/*     */     //   72: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   75: goto -> 103
/*     */     //   78: aload_3
/*     */     //   79: instanceof java/lang/Character
/*     */     //   82: ifeq -> 97
/*     */     //   85: aload_3
/*     */     //   86: checkcast java/lang/Character
/*     */     //   89: aload #4
/*     */     //   91: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   94: goto -> 103
/*     */     //   97: aload_3
/*     */     //   98: aload #4
/*     */     //   100: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   103: ifeq -> 183
/*     */     //   106: aload_0
/*     */     //   107: invokevirtual left : ()Lscala/collection/mutable/AVLTree;
/*     */     //   110: aload #7
/*     */     //   112: invokevirtual left : ()Lscala/collection/mutable/AVLTree;
/*     */     //   115: astore #5
/*     */     //   117: dup
/*     */     //   118: ifnonnull -> 130
/*     */     //   121: pop
/*     */     //   122: aload #5
/*     */     //   124: ifnull -> 138
/*     */     //   127: goto -> 183
/*     */     //   130: aload #5
/*     */     //   132: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   135: ifeq -> 183
/*     */     //   138: aload_0
/*     */     //   139: invokevirtual right : ()Lscala/collection/mutable/AVLTree;
/*     */     //   142: aload #7
/*     */     //   144: invokevirtual right : ()Lscala/collection/mutable/AVLTree;
/*     */     //   147: astore #6
/*     */     //   149: dup
/*     */     //   150: ifnonnull -> 162
/*     */     //   153: pop
/*     */     //   154: aload #6
/*     */     //   156: ifnull -> 170
/*     */     //   159: goto -> 183
/*     */     //   162: aload #6
/*     */     //   164: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   167: ifeq -> 183
/*     */     //   170: aload #7
/*     */     //   172: aload_0
/*     */     //   173: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   176: ifeq -> 183
/*     */     //   179: iconst_1
/*     */     //   180: goto -> 184
/*     */     //   183: iconst_0
/*     */     //   184: ifeq -> 191
/*     */     //   187: iconst_1
/*     */     //   188: goto -> 192
/*     */     //   191: iconst_0
/*     */     //   192: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #74	-> 0
/*     */     //   #236	-> 12
/*     */     //   #74	-> 19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	193	0	this	Lscala/collection/mutable/Node;
/*     */     //   0	193	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Node(Object data, AVLTree<A> left, AVLTree<A> right) {
/*  74 */     AVLTree$class.$init$(this);
/*  74 */     Product.class.$init$(this);
/*  75 */     this.balance = right.depth() - left.depth();
/*  77 */     this.depth = package$.MODULE$.max(left.depth(), right.depth()) + 1;
/*     */   }
/*     */   
/*     */   public int balance() {
/*     */     return this.balance;
/*     */   }
/*     */   
/*     */   public int depth() {
/*  77 */     return this.depth;
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> iterator() {
/*  79 */     return new AVLIterator<B>(this);
/*     */   }
/*     */   
/*     */   public <B> boolean contains(Object value, Ordering ordering) {
/*  82 */     int ord = ordering.compare(value, data());
/*  83 */     return (0 == ord) ? true : (
/*     */       
/*  85 */       (ord < 0) ? 
/*  86 */       left().<Object>contains(value, ordering) : 
/*     */       
/*  88 */       right().<Object>contains(value, ordering));
/*     */   }
/*     */   
/*     */   public <B> AVLTree<B> insert(Object value, Ordering<A> ordering) {
/*  97 */     int ord = ordering.compare(value, data());
/*  98 */     if (0 == ord)
/*  99 */       throw new IllegalArgumentException(); 
/* 100 */     return (ord < 0) ? (
/* 101 */       new Node(data(), left().insert((A)value, ordering), right())).rebalance() : (
/*     */       
/* 103 */       new Node(data(), left(), right().insert((A)value, ordering))).rebalance();
/*     */   }
/*     */   
/*     */   public <B> AVLTree<A> remove(Object value, Ordering ordering) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: aload_1
/*     */     //   2: aload_0
/*     */     //   3: invokevirtual data : ()Ljava/lang/Object;
/*     */     //   6: invokeinterface compare : (Ljava/lang/Object;Ljava/lang/Object;)I
/*     */     //   11: istore #13
/*     */     //   13: iload #13
/*     */     //   15: iconst_0
/*     */     //   16: if_icmpne -> 248
/*     */     //   19: getstatic scala/collection/mutable/Leaf$.MODULE$ : Lscala/collection/mutable/Leaf$;
/*     */     //   22: aload_0
/*     */     //   23: invokevirtual left : ()Lscala/collection/mutable/AVLTree;
/*     */     //   26: astore_3
/*     */     //   27: dup
/*     */     //   28: ifnonnull -> 39
/*     */     //   31: pop
/*     */     //   32: aload_3
/*     */     //   33: ifnull -> 46
/*     */     //   36: goto -> 165
/*     */     //   39: aload_3
/*     */     //   40: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   43: ifeq -> 165
/*     */     //   46: getstatic scala/collection/mutable/Leaf$.MODULE$ : Lscala/collection/mutable/Leaf$;
/*     */     //   49: aload_0
/*     */     //   50: invokevirtual right : ()Lscala/collection/mutable/AVLTree;
/*     */     //   53: astore #4
/*     */     //   55: dup
/*     */     //   56: ifnonnull -> 68
/*     */     //   59: pop
/*     */     //   60: aload #4
/*     */     //   62: ifnull -> 76
/*     */     //   65: goto -> 82
/*     */     //   68: aload #4
/*     */     //   70: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   73: ifeq -> 82
/*     */     //   76: getstatic scala/collection/mutable/Leaf$.MODULE$ : Lscala/collection/mutable/Leaf$;
/*     */     //   79: goto -> 315
/*     */     //   82: aload_0
/*     */     //   83: invokevirtual right : ()Lscala/collection/mutable/AVLTree;
/*     */     //   86: invokeinterface removeMin : ()Lscala/Tuple2;
/*     */     //   91: astore #8
/*     */     //   93: aload #8
/*     */     //   95: ifnull -> 155
/*     */     //   98: new scala/Tuple2
/*     */     //   101: dup
/*     */     //   102: aload #8
/*     */     //   104: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   107: aload #8
/*     */     //   109: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   112: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   115: astore #5
/*     */     //   117: aload #5
/*     */     //   119: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   122: astore #6
/*     */     //   124: aload #5
/*     */     //   126: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   129: checkcast scala/collection/mutable/AVLTree
/*     */     //   132: astore #7
/*     */     //   134: new scala/collection/mutable/Node
/*     */     //   137: dup
/*     */     //   138: aload #6
/*     */     //   140: aload_0
/*     */     //   141: invokevirtual left : ()Lscala/collection/mutable/AVLTree;
/*     */     //   144: aload #7
/*     */     //   146: invokespecial <init> : (Ljava/lang/Object;Lscala/collection/mutable/AVLTree;Lscala/collection/mutable/AVLTree;)V
/*     */     //   149: invokevirtual rebalance : ()Lscala/collection/mutable/Node;
/*     */     //   152: goto -> 315
/*     */     //   155: new scala/MatchError
/*     */     //   158: dup
/*     */     //   159: aload #8
/*     */     //   161: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   164: athrow
/*     */     //   165: aload_0
/*     */     //   166: invokevirtual left : ()Lscala/collection/mutable/AVLTree;
/*     */     //   169: invokeinterface removeMax : ()Lscala/Tuple2;
/*     */     //   174: astore #12
/*     */     //   176: aload #12
/*     */     //   178: ifnull -> 238
/*     */     //   181: new scala/Tuple2
/*     */     //   184: dup
/*     */     //   185: aload #12
/*     */     //   187: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   190: aload #12
/*     */     //   192: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   195: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   198: astore #9
/*     */     //   200: aload #9
/*     */     //   202: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   205: astore #10
/*     */     //   207: aload #9
/*     */     //   209: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   212: checkcast scala/collection/mutable/AVLTree
/*     */     //   215: astore #11
/*     */     //   217: new scala/collection/mutable/Node
/*     */     //   220: dup
/*     */     //   221: aload #10
/*     */     //   223: aload #11
/*     */     //   225: aload_0
/*     */     //   226: invokevirtual right : ()Lscala/collection/mutable/AVLTree;
/*     */     //   229: invokespecial <init> : (Ljava/lang/Object;Lscala/collection/mutable/AVLTree;Lscala/collection/mutable/AVLTree;)V
/*     */     //   232: invokevirtual rebalance : ()Lscala/collection/mutable/Node;
/*     */     //   235: goto -> 315
/*     */     //   238: new scala/MatchError
/*     */     //   241: dup
/*     */     //   242: aload #12
/*     */     //   244: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   247: athrow
/*     */     //   248: iload #13
/*     */     //   250: iconst_0
/*     */     //   251: if_icmpge -> 286
/*     */     //   254: new scala/collection/mutable/Node
/*     */     //   257: dup
/*     */     //   258: aload_0
/*     */     //   259: invokevirtual data : ()Ljava/lang/Object;
/*     */     //   262: aload_0
/*     */     //   263: invokevirtual left : ()Lscala/collection/mutable/AVLTree;
/*     */     //   266: aload_1
/*     */     //   267: aload_2
/*     */     //   268: invokeinterface remove : (Ljava/lang/Object;Lscala/math/Ordering;)Lscala/collection/mutable/AVLTree;
/*     */     //   273: aload_0
/*     */     //   274: invokevirtual right : ()Lscala/collection/mutable/AVLTree;
/*     */     //   277: invokespecial <init> : (Ljava/lang/Object;Lscala/collection/mutable/AVLTree;Lscala/collection/mutable/AVLTree;)V
/*     */     //   280: invokevirtual rebalance : ()Lscala/collection/mutable/Node;
/*     */     //   283: goto -> 315
/*     */     //   286: new scala/collection/mutable/Node
/*     */     //   289: dup
/*     */     //   290: aload_0
/*     */     //   291: invokevirtual data : ()Ljava/lang/Object;
/*     */     //   294: aload_0
/*     */     //   295: invokevirtual left : ()Lscala/collection/mutable/AVLTree;
/*     */     //   298: aload_0
/*     */     //   299: invokevirtual right : ()Lscala/collection/mutable/AVLTree;
/*     */     //   302: aload_1
/*     */     //   303: aload_2
/*     */     //   304: invokeinterface remove : (Ljava/lang/Object;Lscala/math/Ordering;)Lscala/collection/mutable/AVLTree;
/*     */     //   309: invokespecial <init> : (Ljava/lang/Object;Lscala/collection/mutable/AVLTree;Lscala/collection/mutable/AVLTree;)V
/*     */     //   312: invokevirtual rebalance : ()Lscala/collection/mutable/Node;
/*     */     //   315: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #111	-> 0
/*     */     //   #112	-> 13
/*     */     //   #113	-> 19
/*     */     //   #114	-> 46
/*     */     //   #115	-> 76
/*     */     //   #117	-> 82
/*     */     //   #118	-> 134
/*     */     //   #117	-> 155
/*     */     //   #121	-> 165
/*     */     //   #122	-> 217
/*     */     //   #121	-> 238
/*     */     //   #124	-> 248
/*     */     //   #125	-> 254
/*     */     //   #127	-> 286
/*     */     //   #110	-> 315
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	316	0	this	Lscala/collection/mutable/Node;
/*     */     //   0	316	1	value	Ljava/lang/Object;
/*     */     //   0	316	2	ordering	Lscala/math/Ordering;
/*     */     //   13	303	13	ord	I
/*     */     //   124	28	6	min	Ljava/lang/Object;
/*     */     //   134	18	7	newRight	Lscala/collection/mutable/AVLTree;
/*     */     //   207	28	10	max	Ljava/lang/Object;
/*     */     //   217	18	11	newLeft	Lscala/collection/mutable/AVLTree;
/*     */   }
/*     */   
/*     */   public <B> Tuple2<B, AVLTree<B>> removeMin() {
/* 137 */     AVLTree<A> aVLTree = left();
/* 137 */     if (Leaf$.MODULE$ == null) {
/* 137 */       if (aVLTree != null)
/* 140 */         Tuple2<?, AVLTree<?>> tuple2 = left().removeMin(); 
/*     */     } else {
/*     */       if (Leaf$.MODULE$.equals(aVLTree));
/* 140 */       Tuple2<?, AVLTree<?>> tuple2 = left().removeMin();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <B> Tuple2<B, AVLTree<B>> removeMax() {
/* 151 */     AVLTree<A> aVLTree = right();
/* 151 */     if (Leaf$.MODULE$ == null) {
/* 151 */       if (aVLTree != null)
/* 154 */         Tuple2<?, AVLTree<?>> tuple2 = right().removeMax(); 
/*     */     } else {
/*     */       if (Leaf$.MODULE$.equals(aVLTree));
/* 154 */       Tuple2<?, AVLTree<?>> tuple2 = right().removeMax();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <B> Node<A> rebalance() {
/* 160 */     return (-2 == balance()) ? (
/* 161 */       (1 == left().balance()) ? 
/* 162 */       doubleRightRotation() : 
/*     */       
/* 164 */       rightRotation()) : (
/* 165 */       (2 == balance()) ? (
/* 166 */       (-1 == right().balance()) ? 
/* 167 */       doubleLeftRotation() : 
/*     */       
/* 169 */       leftRotation()) : 
/*     */       
/* 171 */       this);
/*     */   }
/*     */   
/*     */   public <B> Node<B> leftRotation() {
/* 176 */     AVLTree<A> aVLTree = right();
/* 176 */     if (Leaf$.MODULE$ == null) {
/* 176 */       if (aVLTree != null)
/* 177 */         Node r = (Node)right(); 
/*     */     } else {
/*     */       if (Leaf$.MODULE$.equals(aVLTree))
/* 179 */         throw package$.MODULE$.error("Should not happen."); 
/*     */       Node node = (Node)right();
/*     */     } 
/* 179 */     throw package$.MODULE$.error("Should not happen.");
/*     */   }
/*     */   
/*     */   public <B> Node<B> rightRotation() {
/* 183 */     AVLTree<A> aVLTree = left();
/* 183 */     if (Leaf$.MODULE$ == null) {
/* 183 */       if (aVLTree != null)
/* 184 */         Node l = (Node)left(); 
/*     */     } else {
/*     */       if (Leaf$.MODULE$.equals(aVLTree))
/* 186 */         throw package$.MODULE$.error("Should not happen."); 
/*     */       Node node = (Node)left();
/*     */     } 
/* 186 */     throw package$.MODULE$.error("Should not happen.");
/*     */   }
/*     */   
/*     */   public <B> Node<B> doubleLeftRotation() {
/* 190 */     AVLTree<A> aVLTree = right();
/* 190 */     if (Leaf$.MODULE$ == null) {
/* 190 */       if (aVLTree != null) {
/* 191 */         Node r = (Node)right();
/* 193 */         Node rightRotated = r.rightRotation();
/*     */       } 
/*     */     } else {
/*     */       if (Leaf$.MODULE$.equals(aVLTree))
/* 195 */         throw package$.MODULE$.error("Should not happen."); 
/*     */       Node node1 = (Node)right();
/*     */       Node node2 = node1.rightRotation();
/*     */     } 
/* 195 */     throw package$.MODULE$.error("Should not happen.");
/*     */   }
/*     */   
/*     */   public <B> Node<B> doubleRightRotation() {
/* 199 */     AVLTree<A> aVLTree = left();
/* 199 */     if (Leaf$.MODULE$ == null) {
/* 199 */       if (aVLTree != null) {
/* 200 */         Node l = (Node)left();
/* 202 */         Node leftRotated = l.leftRotation();
/*     */       } 
/*     */     } else {
/*     */       if (Leaf$.MODULE$.equals(aVLTree))
/* 204 */         throw package$.MODULE$.error("Should not happen."); 
/*     */       Node node1 = (Node)left();
/*     */       Node node2 = node1.leftRotation();
/*     */     } 
/* 204 */     throw package$.MODULE$.error("Should not happen.");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Node.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */