/*     */ package scala.collection.concurrent;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055f!B\001\003\001\nA!\001\005*E\007N\033v\fR3tGJL\007\017^8s\025\t\031A!\001\006d_:\034WO\035:f]RT!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b+\rIa\004K\n\005\001)q\021\003\005\002\f\0315\ta!\003\002\016\r\t1\021I\\=SK\032\004\"aC\b\n\005A1!a\002)s_\022,8\r\036\t\003\027II!a\005\004\003\031M+'/[1mSj\f'\r\\3\t\021U\001!Q3A\005\002]\t1a\0347e\007\001)\022\001\007\t\0053iar%D\001\003\023\tY\"AA\003J\035>$W\r\005\002\036=1\001A!B\020\001\005\004\001#!A&\022\005\005\"\003CA\006#\023\t\031cAA\004O_RD\027N\\4\021\005-)\023B\001\024\007\005\r\te.\037\t\003;!\"Q!\013\001C\002\001\022\021A\026\005\tW\001\021\t\022)A\0051\005!q\016\0343!\021!i\003A!f\001\n\003q\023\001D3ya\026\034G/\0323nC&tW#A\030\021\te\001DdJ\005\003c\t\021\001\"T1j]:{G-\032\005\tg\001\021\t\022)A\005_\005iQ\r\0379fGR,G-\\1j]\002B\001\"\016\001\003\026\004%\taF\001\003]ZD\001b\016\001\003\022\003\006I\001G\001\004]Z\004\003\"B\035\001\t\003Q\024A\002\037j]&$h\b\006\003<yur\004\003B\r\0019\035BQ!\006\035A\002aAQ!\f\035A\002=BQ!\016\035A\002aAq\001\021\001A\002\023\005\021)A\005d_6l\027\016\036;fIV\t!\t\005\002\f\007&\021AI\002\002\b\005>|G.Z1o\021\0351\005\0011A\005\002\035\013QbY8n[&$H/\0323`I\025\fHC\001%L!\tY\021*\003\002K\r\t!QK\\5u\021\035aU)!AA\002\t\0131\001\037\0232\021\031q\005\001)Q\005\005\006Q1m\\7nSR$X\r\032\021)\0055\003\006CA\006R\023\t\021fA\001\005w_2\fG/\0337f\021\035!\006!!A\005\002U\013AaY8qsV\031a+W.\025\t]cf\f\031\t\0053\001A&\f\005\002\0363\022)qd\025b\001AA\021Qd\027\003\006SM\023\r\001\t\005\b+M\003\n\0211\001^!\021I\"\004\027.\t\0175\032\006\023!a\001?B!\021\004\r-[\021\035)4\013%AA\002uCqA\031\001\022\002\023\0051-\001\bd_BLH\005Z3gCVdG\017J\031\026\007\021|\007/F\001fU\tAbmK\001h!\tAW.D\001j\025\tQ7.A\005v]\016DWmY6fI*\021ANB\001\013C:tw\016^1uS>t\027B\0018j\005E)hn\0315fG.,GMV1sS\006t7-\032\003\006?\005\024\r\001\t\003\006S\005\024\r\001\t\005\be\002\t\n\021\"\001t\0039\031w\016]=%I\0264\027-\0367uII*2\001\036<x+\005)(FA\030g\t\025y\022O1\001!\t\025I\023O1\001!\021\035I\b!%A\005\002i\fabY8qs\022\"WMZ1vYR$3'F\002ewr$Qa\b=C\002\001\"Q!\013=C\002\001BqA \001\002\002\023\005s0A\007qe>$Wo\031;Qe\0264\027\016_\013\003\003\003\001B!a\001\002\0165\021\021Q\001\006\005\003\017\tI!\001\003mC:<'BAA\006\003\021Q\027M^1\n\t\005=\021Q\001\002\007'R\024\030N\\4\t\023\005M\001!!A\005\002\005U\021\001\0049s_\022,8\r^!sSRLXCAA\f!\rY\021\021D\005\004\00371!aA%oi\"I\021q\004\001\002\002\023\005\021\021E\001\017aJ|G-^2u\0132,W.\0328u)\r!\0231\005\005\n\031\006u\021\021!a\001\003/A\021\"a\n\001\003\003%\t%!\013\002\037A\024x\016Z;di&#XM]1u_J,\"!a\013\021\013\0055\022q\006\023\016\003\021I1!!\r\005\005!IE/\032:bi>\024\b\"CA\033\001\005\005I\021AA\034\003!\031\027M\\#rk\006dGc\001\"\002:!AA*a\r\002\002\003\007A\005C\005\002>\001\t\t\021\"\021\002@\005A\001.Y:i\007>$W\r\006\002\002\030!I\0211\t\001\002\002\023\005\023QI\001\ti>\034FO]5oOR\021\021\021\001\005\n\003\023\002\021\021!C!\003\027\na!Z9vC2\034Hc\001\"\002N!AA*a\022\002\002\003\007Ae\002\006\002R\t\t\t\021#\001\003\003'\n\001C\025#D'N{F)Z:de&\004Ho\034:\021\007e\t)FB\005\002\005\005\005\t\022\001\002\002XM!\021Q\013\006\022\021\035I\024Q\013C\001\0037\"\"!a\025\t\025\005\r\023QKA\001\n\013\n)\005\003\006\002b\005U\023\021!CA\003G\nQ!\0319qYf,b!!\032\002l\005=D\003CA4\003c\n)(!\037\021\re\001\021\021NA7!\ri\0221\016\003\007?\005}#\031\001\021\021\007u\ty\007\002\004*\003?\022\r\001\t\005\b+\005}\003\031AA:!\031I\"$!\033\002n!9Q&a\030A\002\005]\004CB\r1\003S\ni\007C\0046\003?\002\r!a\035\t\025\005u\024QKA\001\n\003\013y(A\004v]\006\004\b\017\\=\026\r\005\005\0251SAL)\021\t\031)a'\021\013-\t))!#\n\007\005\035eA\001\004PaRLwN\034\t\n\027\005-\025qRAM\003\037K1!!$\007\005\031!V\017\0357fgA1\021DGAI\003+\0032!HAJ\t\031y\0221\020b\001AA\031Q$a&\005\r%\nYH1\001!!\031I\002'!%\002\026\"Q\021QTA>\003\003\005\r!a(\002\007a$\003\007\005\004\032\001\005E\025Q\023\005\013\003G\013)&!A\005\n\005\025\026a\003:fC\022\024Vm]8mm\026$\"!a*\021\t\005\r\021\021V\005\005\003W\013)A\001\004PE*,7\r\036")
/*     */ public class RDCSS_Descriptor<K, V> implements Product, Serializable {
/*     */   private final INode<K, V> old;
/*     */   
/*     */   private final MainNode<K, V> expectedmain;
/*     */   
/*     */   private final INode<K, V> nv;
/*     */   
/*     */   private volatile boolean committed;
/*     */   
/*     */   public INode<K, V> old() {
/* 613 */     return this.old;
/*     */   }
/*     */   
/*     */   public MainNode<K, V> expectedmain() {
/* 613 */     return this.expectedmain;
/*     */   }
/*     */   
/*     */   public INode<K, V> nv() {
/* 613 */     return this.nv;
/*     */   }
/*     */   
/*     */   public <K, V> RDCSS_Descriptor<K, V> copy(INode<K, V> old, MainNode<K, V> expectedmain, INode<K, V> nv) {
/* 613 */     return new RDCSS_Descriptor(old, expectedmain, nv);
/*     */   }
/*     */   
/*     */   public <K, V> INode<K, V> copy$default$1() {
/* 613 */     return old();
/*     */   }
/*     */   
/*     */   public <K, V> MainNode<K, V> copy$default$2() {
/* 613 */     return expectedmain();
/*     */   }
/*     */   
/*     */   public <K, V> INode<K, V> copy$default$3() {
/* 613 */     return nv();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 613 */     return "RDCSS_Descriptor";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 613 */     return 3;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 613 */     switch (x$1) {
/*     */       default:
/* 613 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 613 */     return old();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 613 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 613 */     return x$1 instanceof RDCSS_Descriptor;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 613 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 613 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 139
/*     */     //   5: aload_1
/*     */     //   6: instanceof scala/collection/concurrent/RDCSS_Descriptor
/*     */     //   9: ifeq -> 17
/*     */     //   12: iconst_1
/*     */     //   13: istore_2
/*     */     //   14: goto -> 19
/*     */     //   17: iconst_0
/*     */     //   18: istore_2
/*     */     //   19: iload_2
/*     */     //   20: ifeq -> 143
/*     */     //   23: aload_1
/*     */     //   24: checkcast scala/collection/concurrent/RDCSS_Descriptor
/*     */     //   27: astore #6
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual old : ()Lscala/collection/concurrent/INode;
/*     */     //   33: aload #6
/*     */     //   35: invokevirtual old : ()Lscala/collection/concurrent/INode;
/*     */     //   38: astore_3
/*     */     //   39: dup
/*     */     //   40: ifnonnull -> 51
/*     */     //   43: pop
/*     */     //   44: aload_3
/*     */     //   45: ifnull -> 58
/*     */     //   48: goto -> 135
/*     */     //   51: aload_3
/*     */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   55: ifeq -> 135
/*     */     //   58: aload_0
/*     */     //   59: invokevirtual expectedmain : ()Lscala/collection/concurrent/MainNode;
/*     */     //   62: aload #6
/*     */     //   64: invokevirtual expectedmain : ()Lscala/collection/concurrent/MainNode;
/*     */     //   67: astore #4
/*     */     //   69: dup
/*     */     //   70: ifnonnull -> 82
/*     */     //   73: pop
/*     */     //   74: aload #4
/*     */     //   76: ifnull -> 90
/*     */     //   79: goto -> 135
/*     */     //   82: aload #4
/*     */     //   84: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   87: ifeq -> 135
/*     */     //   90: aload_0
/*     */     //   91: invokevirtual nv : ()Lscala/collection/concurrent/INode;
/*     */     //   94: aload #6
/*     */     //   96: invokevirtual nv : ()Lscala/collection/concurrent/INode;
/*     */     //   99: astore #5
/*     */     //   101: dup
/*     */     //   102: ifnonnull -> 114
/*     */     //   105: pop
/*     */     //   106: aload #5
/*     */     //   108: ifnull -> 122
/*     */     //   111: goto -> 135
/*     */     //   114: aload #5
/*     */     //   116: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   119: ifeq -> 135
/*     */     //   122: aload #6
/*     */     //   124: aload_0
/*     */     //   125: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   128: ifeq -> 135
/*     */     //   131: iconst_1
/*     */     //   132: goto -> 136
/*     */     //   135: iconst_0
/*     */     //   136: ifeq -> 143
/*     */     //   139: iconst_1
/*     */     //   140: goto -> 144
/*     */     //   143: iconst_0
/*     */     //   144: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #613	-> 0
/*     */     //   #236	-> 12
/*     */     //   #613	-> 19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	145	0	this	Lscala/collection/concurrent/RDCSS_Descriptor;
/*     */     //   0	145	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public RDCSS_Descriptor(INode<K, V> old, MainNode<K, V> expectedmain, INode<K, V> nv) {
/* 613 */     Product.class.$init$(this);
/* 614 */     this.committed = false;
/*     */   }
/*     */   
/*     */   public boolean committed() {
/* 614 */     return this.committed;
/*     */   }
/*     */   
/*     */   public void committed_$eq(boolean x$1) {
/* 614 */     this.committed = x$1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\RDCSS_Descriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */