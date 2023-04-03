/*    */ package scala;
/*    */ 
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ef\001B\001\003\001\026\021a\001V;qY\026$$\"A\002\002\013M\034\027\r\\1\004\001U)a\001\005\016\036AM)\001aB\006#KA\021\001\"C\007\002\005%\021!B\001\002\007\003:L(+\0324\021\r!aa\"\007\017 \023\ti!A\001\005Qe>$Wo\031;5!\ty\001\003\004\001\005\rE\001AQ1\001\023\005\t!\026'\005\002\024-A\021\001\002F\005\003+\t\021qAT8uQ&tw\r\005\002\t/%\021\001D\001\002\004\003:L\bCA\b\033\t\031Y\002\001\"b\001%\t\021AK\r\t\003\037u!aA\b\001\005\006\004\021\"A\001+4!\ty\001\005\002\004\"\001\021\025\rA\005\002\003)R\002\"\001C\022\n\005\021\022!a\002)s_\022,8\r\036\t\003\021\031J!a\n\002\003\031M+'/[1mSj\f'\r\\3\t\021%\002!Q3A\005\002)\n!aX\031\026\0039A\001\002\f\001\003\022\003\006IAD\001\004?F\002\003\002\003\030\001\005+\007I\021A\030\002\005}\023T#A\r\t\021E\002!\021#Q\001\ne\t1a\030\032!\021!\031\004A!f\001\n\003!\024AA04+\005a\002\002\003\034\001\005#\005\013\021\002\017\002\007}\033\004\005\003\0059\001\tU\r\021\"\001:\003\tyF'F\001 \021!Y\004A!E!\002\023y\022aA05A!)Q\b\001C\001}\0051A(\0338jiz\"Ra\020!B\005\016\003b\001\003\001\0173qy\002\"B\025=\001\004q\001\"\002\030=\001\004I\002\"B\032=\001\004a\002\"\002\035=\001\004y\002\"B#\001\t\0032\025\001\003;p'R\024\030N\\4\025\003\035\003\"\001S'\016\003%S!AS&\002\t1\fgn\032\006\002\031\006!!.\031<b\023\tq\025J\001\004TiJLgn\032\005\b!\002\t\t\021\"\001R\003\021\031w\016]=\026\013I+v+W.\025\013McVLX0\021\r!\001AK\026-[!\tyQ\013B\003\022\037\n\007!\003\005\002\020/\022)1d\024b\001%A\021q\"\027\003\006==\023\rA\005\t\003\037m#Q!I(C\002IAq!K(\021\002\003\007A\013C\004/\037B\005\t\031\001,\t\017Mz\005\023!a\0011\"9\001h\024I\001\002\004Q\006bB1\001#\003%\tAY\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\025\031gn\0349r+\005!'F\001\bfW\0051\007CA4m\033\005A'BA5k\003%)hn\0315fG.,GM\003\002l\005\005Q\021M\0348pi\006$\030n\0348\n\0055D'!E;oG\",7m[3e-\006\024\030.\0318dK\022)\021\003\031b\001%\021)1\004\031b\001%\021)a\004\031b\001%\021)\021\005\031b\001%!91\017AI\001\n\003!\030AD2paf$C-\0324bk2$HEM\013\006k^D\030P_\013\002m*\022\021$\032\003\006#I\024\rA\005\003\0067I\024\rA\005\003\006=I\024\rA\005\003\006CI\024\rA\005\005\by\002\t\n\021\"\001~\0039\031w\016]=%I\0264\027-\0367uIM*\022B`A\001\003\007\t)!a\002\026\003}T#\001H3\005\013EY(\031\001\n\005\013mY(\031\001\n\005\013yY(\031\001\n\005\013\005Z(\031\001\n\t\023\005-\001!%A\005\002\0055\021AD2paf$C-\0324bk2$H\005N\013\013\003\037\t\031\"!\006\002\030\005eQCAA\tU\tyR\r\002\004\022\003\023\021\rA\005\003\0077\005%!\031\001\n\005\ry\tIA1\001\023\t\031\t\023\021\002b\001%!I\021Q\004\001\002\002\023\005\023qD\001\016aJ|G-^2u!J,g-\033=\026\003\035C\021\"a\t\001\003\003%\t%!\n\002\037A\024x\016Z;di&#XM]1u_J,\"!a\n\021\013\005%\022q\006\f\016\005\005-\"bAA\027\005\005Q1m\0347mK\016$\030n\0348\n\t\005E\0221\006\002\t\023R,'/\031;pe\"I\021Q\007\001\002\002\023\005\021qG\001\tG\006tW)];bYR!\021\021HA !\rA\0211H\005\004\003{\021!a\002\"p_2,\027M\034\005\n\003\003\n\031$!AA\002Y\t1\001\037\0232\021%\t)\005AA\001\n\003\n9%\001\005iCND7i\0343f)\t\tI\005E\002\t\003\027J1!!\024\003\005\rIe\016\036\005\n\003#\002\021\021!C!\003'\na!Z9vC2\034H\003BA\035\003+B\021\"!\021\002P\005\005\t\031\001\f\b\023\005e#!!A\t\002\005m\023A\002+va2,G\007E\002\t\003;2\001\"\001\002\002\002#\005\021qL\n\005\003;:Q\005C\004>\003;\"\t!a\031\025\005\005m\003\002C#\002^\005\005IQ\t$\t\025\005%\024QLA\001\n\003\013Y'A\003baBd\0270\006\006\002n\005M\024qOA>\003\"\"\"a\034\002\002\006\r\025QQAD!)A\001!!\035\002v\005e\024Q\020\t\004\037\005MDAB\t\002h\t\007!\003E\002\020\003o\"aaGA4\005\004\021\002cA\b\002|\0211a$a\032C\002I\0012aDA@\t\031\t\023q\rb\001%!9\021&a\032A\002\005E\004b\002\030\002h\001\007\021Q\017\005\bg\005\035\004\031AA=\021\035A\024q\ra\001\003{B!\"a#\002^\005\005I\021QAG\003\035)h.\0319qYf,\"\"a$\002\034\006}\0251UAT)\021\t\t*!+\021\013!\t\031*a&\n\007\005U%A\001\004PaRLwN\034\t\013\021\001\tI*!(\002\"\006\025\006cA\b\002\034\0221\021#!#C\002I\0012aDAP\t\031Y\022\021\022b\001%A\031q\"a)\005\ry\tII1\001\023!\ry\021q\025\003\007C\005%%\031\001\n\t\025\005-\026\021RA\001\002\004\t9*A\002yIAB!\"a,\002^\005\005I\021BAY\003-\021X-\0313SKN|GN^3\025\005\005M\006c\001%\0026&\031\021qW%\003\r=\023'.Z2u\001")
/*    */ public class Tuple4<T1, T2, T3, T4> implements Product4<T1, T2, T3, T4>, Product, Serializable {
/*    */   private final T1 _1;
/*    */   
/*    */   private final T2 _2;
/*    */   
/*    */   private final T3 _3;
/*    */   
/*    */   private final T4 _4;
/*    */   
/*    */   public int productArity() {
/* 21 */     return Product4$class.productArity(this);
/*    */   }
/*    */   
/*    */   public Object productElement(int n) throws IndexOutOfBoundsException {
/* 21 */     return Product4$class.productElement(this, n);
/*    */   }
/*    */   
/*    */   public T1 _1() {
/* 21 */     return this._1;
/*    */   }
/*    */   
/*    */   public T2 _2() {
/* 21 */     return this._2;
/*    */   }
/*    */   
/*    */   public T3 _3() {
/* 21 */     return this._3;
/*    */   }
/*    */   
/*    */   public T4 _4() {
/* 21 */     return this._4;
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> copy(Object _1, Object _2, Object _3, Object _4) {
/* 21 */     return new Tuple4((T1)_1, (T2)_2, (T3)_3, (T4)_4);
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4> T1 copy$default$1() {
/* 21 */     return _1();
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4> T2 copy$default$2() {
/* 21 */     return _2();
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4> T3 copy$default$3() {
/* 21 */     return _3();
/*    */   }
/*    */   
/*    */   public <T1, T2, T3, T4> T4 copy$default$4() {
/* 21 */     return _4();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 21 */     return "Tuple4";
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 21 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 21 */     return x$1 instanceof Tuple4;
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
/*    */     //   2: if_acmpeq -> 375
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/Tuple4
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 379
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/Tuple4
/*    */     //   27: astore #11
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   33: aload #11
/*    */     //   35: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   38: astore #4
/*    */     //   40: dup
/*    */     //   41: astore_3
/*    */     //   42: aload #4
/*    */     //   44: if_acmpne -> 51
/*    */     //   47: iconst_1
/*    */     //   48: goto -> 103
/*    */     //   51: aload_3
/*    */     //   52: ifnonnull -> 59
/*    */     //   55: iconst_0
/*    */     //   56: goto -> 103
/*    */     //   59: aload_3
/*    */     //   60: instanceof java/lang/Number
/*    */     //   63: ifeq -> 78
/*    */     //   66: aload_3
/*    */     //   67: checkcast java/lang/Number
/*    */     //   70: aload #4
/*    */     //   72: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*    */     //   75: goto -> 103
/*    */     //   78: aload_3
/*    */     //   79: instanceof java/lang/Character
/*    */     //   82: ifeq -> 97
/*    */     //   85: aload_3
/*    */     //   86: checkcast java/lang/Character
/*    */     //   89: aload #4
/*    */     //   91: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*    */     //   94: goto -> 103
/*    */     //   97: aload_3
/*    */     //   98: aload #4
/*    */     //   100: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   103: ifeq -> 371
/*    */     //   106: aload_0
/*    */     //   107: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   110: aload #11
/*    */     //   112: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   115: astore #6
/*    */     //   117: dup
/*    */     //   118: astore #5
/*    */     //   120: aload #6
/*    */     //   122: if_acmpne -> 129
/*    */     //   125: iconst_1
/*    */     //   126: goto -> 187
/*    */     //   129: aload #5
/*    */     //   131: ifnonnull -> 138
/*    */     //   134: iconst_0
/*    */     //   135: goto -> 187
/*    */     //   138: aload #5
/*    */     //   140: instanceof java/lang/Number
/*    */     //   143: ifeq -> 159
/*    */     //   146: aload #5
/*    */     //   148: checkcast java/lang/Number
/*    */     //   151: aload #6
/*    */     //   153: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*    */     //   156: goto -> 187
/*    */     //   159: aload #5
/*    */     //   161: instanceof java/lang/Character
/*    */     //   164: ifeq -> 180
/*    */     //   167: aload #5
/*    */     //   169: checkcast java/lang/Character
/*    */     //   172: aload #6
/*    */     //   174: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*    */     //   177: goto -> 187
/*    */     //   180: aload #5
/*    */     //   182: aload #6
/*    */     //   184: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   187: ifeq -> 371
/*    */     //   190: aload_0
/*    */     //   191: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   194: aload #11
/*    */     //   196: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   199: astore #8
/*    */     //   201: dup
/*    */     //   202: astore #7
/*    */     //   204: aload #8
/*    */     //   206: if_acmpne -> 213
/*    */     //   209: iconst_1
/*    */     //   210: goto -> 271
/*    */     //   213: aload #7
/*    */     //   215: ifnonnull -> 222
/*    */     //   218: iconst_0
/*    */     //   219: goto -> 271
/*    */     //   222: aload #7
/*    */     //   224: instanceof java/lang/Number
/*    */     //   227: ifeq -> 243
/*    */     //   230: aload #7
/*    */     //   232: checkcast java/lang/Number
/*    */     //   235: aload #8
/*    */     //   237: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*    */     //   240: goto -> 271
/*    */     //   243: aload #7
/*    */     //   245: instanceof java/lang/Character
/*    */     //   248: ifeq -> 264
/*    */     //   251: aload #7
/*    */     //   253: checkcast java/lang/Character
/*    */     //   256: aload #8
/*    */     //   258: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*    */     //   261: goto -> 271
/*    */     //   264: aload #7
/*    */     //   266: aload #8
/*    */     //   268: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   271: ifeq -> 371
/*    */     //   274: aload_0
/*    */     //   275: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   278: aload #11
/*    */     //   280: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   283: astore #10
/*    */     //   285: dup
/*    */     //   286: astore #9
/*    */     //   288: aload #10
/*    */     //   290: if_acmpne -> 297
/*    */     //   293: iconst_1
/*    */     //   294: goto -> 355
/*    */     //   297: aload #9
/*    */     //   299: ifnonnull -> 306
/*    */     //   302: iconst_0
/*    */     //   303: goto -> 355
/*    */     //   306: aload #9
/*    */     //   308: instanceof java/lang/Number
/*    */     //   311: ifeq -> 327
/*    */     //   314: aload #9
/*    */     //   316: checkcast java/lang/Number
/*    */     //   319: aload #10
/*    */     //   321: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*    */     //   324: goto -> 355
/*    */     //   327: aload #9
/*    */     //   329: instanceof java/lang/Character
/*    */     //   332: ifeq -> 348
/*    */     //   335: aload #9
/*    */     //   337: checkcast java/lang/Character
/*    */     //   340: aload #10
/*    */     //   342: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*    */     //   345: goto -> 355
/*    */     //   348: aload #9
/*    */     //   350: aload #10
/*    */     //   352: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   355: ifeq -> 371
/*    */     //   358: aload #11
/*    */     //   360: aload_0
/*    */     //   361: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   364: ifeq -> 371
/*    */     //   367: iconst_1
/*    */     //   368: goto -> 372
/*    */     //   371: iconst_0
/*    */     //   372: ifeq -> 379
/*    */     //   375: iconst_1
/*    */     //   376: goto -> 380
/*    */     //   379: iconst_0
/*    */     //   380: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #21	-> 0
/*    */     //   #236	-> 12
/*    */     //   #21	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	381	0	this	Lscala/Tuple4;
/*    */     //   0	381	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Tuple4(Object _1, Object _2, Object _3, Object _4) {
/* 21 */     Product$class.$init$(this);
/* 21 */     Product4$class.$init$(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 24 */     return (new StringBuilder()).append("(").append(_1()).append(",").append(_2()).append(",").append(_3()).append(",").append(_4()).append(")").toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */