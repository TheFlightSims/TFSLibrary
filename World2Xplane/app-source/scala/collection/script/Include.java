/*    */ package scala.collection.script;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ec\001B\001\003\001&\021q!\0238dYV$WM\003\002\004\t\00511o\031:jaRT!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)\"AC\013\024\013\001YqBH\021\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rE\002\021#Mi\021AA\005\003%\t\021q!T3tg\006<W\r\005\002\025+1\001AA\002\f\001\t\013\007qCA\001B#\tA2\004\005\002\r3%\021!D\002\002\b\035>$\b.\0338h!\taA$\003\002\036\r\t\031\021I\\=\021\0051y\022B\001\021\007\005\035\001&o\0343vGR\004\"\001\004\022\n\005\r2!\001D*fe&\fG.\033>bE2,\007\002C\023\001\005+\007I\021\001\024\002\0211|7-\031;j_:,\022a\n\t\003!!J!!\013\002\003\0211{7-\031;j_:D\001b\013\001\003\022\003\006IaJ\001\nY>\034\027\r^5p]\002B\001\"\f\001\003\026\004%\tAL\001\005K2,W.F\001\024\021!\001\004A!E!\002\023\031\022!B3mK6\004\003\"\002\032\001\t\003\031\024A\002\037j]&$h\bF\0025kY\0022\001\005\001\024\021\025)\023\0071\001(\021\025i\023\0071\001\024\021\025\021\004\001\"\0019)\t!\024\bC\003.o\001\0071\003C\004<\001\005\005I\021\001\037\002\t\r|\007/_\013\003{\001#2AP!C!\r\001\002a\020\t\003)\001#QA\006\036C\002]Aq!\n\036\021\002\003\007q\005C\004.uA\005\t\031A \t\017\021\003\021\023!C\001\013\006q1m\0349zI\021,g-Y;mi\022\nTC\001$R+\0059%FA\024IW\005I\005C\001&P\033\005Y%B\001'N\003%)hn\0315fG.,GM\003\002O\r\005Q\021M\0348pi\006$\030n\0348\n\005A[%!E;oG\",7m[3e-\006\024\030.\0318dK\022)ac\021b\001/!91\013AI\001\n\003!\026AD2paf$C-\0324bk2$HEM\013\003+^+\022A\026\026\003'!#QA\006*C\002]Aq!\027\001\002\002\023\005#,A\007qe>$Wo\031;Qe\0264\027\016_\013\0027B\021A,Y\007\002;*\021alX\001\005Y\006twMC\001a\003\021Q\027M^1\n\005\tl&AB*ue&tw\rC\004e\001\005\005I\021A3\002\031A\024x\016Z;di\006\023\030\016^=\026\003\031\004\"\001D4\n\005!4!aA%oi\"9!\016AA\001\n\003Y\027A\0049s_\022,8\r^#mK6,g\016\036\013\00371Dq!\\5\002\002\003\007a-A\002yIEBqa\034\001\002\002\023\005\003/A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005\t\bc\001:t75\tA!\003\002u\t\tA\021\n^3sCR|'\017C\004w\001\005\005I\021A<\002\021\r\fg.R9vC2$\"\001_>\021\0051I\030B\001>\007\005\035\021un\0347fC:Dq!\\;\002\002\003\0071\004C\004~\001\005\005I\021\t@\002\021!\f7\017[\"pI\026$\022A\032\005\n\003\003\001\021\021!C!\003\007\t\001\002^8TiJLgn\032\013\0027\"I\021q\001\001\002\002\023\005\023\021B\001\007KF,\030\r\\:\025\007a\fY\001\003\005n\003\013\t\t\0211\001\034\017%\tyAAA\001\022\003\t\t\"A\004J]\016dW\017Z3\021\007A\t\031B\002\005\002\005\005\005\t\022AA\013'\021\t\031bC\021\t\017I\n\031\002\"\001\002\032Q\021\021\021\003\005\013\003\003\t\031\"!A\005F\005\r\001BCA\020\003'\t\t\021\"!\002\"\005)\021\r\0359msV!\0211EA\025)\031\t)#a\013\002.A!\001\003AA\024!\r!\022\021\006\003\007-\005u!\031A\f\t\r\025\ni\0021\001(\021\035i\023Q\004a\001\003OA!\"!\r\002\024\005\005I\021QA\032\003\035)h.\0319qYf,B!!\016\002FQ!\021qGA$!\025a\021\021HA\037\023\r\tYD\002\002\007\037B$\030n\0348\021\r1\tydJA\"\023\r\t\tE\002\002\007)V\004H.\032\032\021\007Q\t)\005\002\004\027\003_\021\ra\006\005\013\003\023\ny#!AA\002\005-\023a\001=%aA!\001\003AA\"\021)\ty%a\005\002\002\023%\021\021K\001\fe\026\fGMU3t_24X\r\006\002\002TA\031A,!\026\n\007\005]SL\001\004PE*,7\r\036")
/*    */ public class Include<A> implements Message<A>, Product, Serializable {
/*    */   private final Location location;
/*    */   
/*    */   private final A elem;
/*    */   
/*    */   public Location location() {
/* 31 */     return this.location;
/*    */   }
/*    */   
/*    */   public A elem() {
/* 31 */     return this.elem;
/*    */   }
/*    */   
/*    */   public <A> Include<A> copy(Location location, Object elem) {
/* 31 */     return new Include(location, (A)elem);
/*    */   }
/*    */   
/*    */   public <A> Location copy$default$1() {
/* 31 */     return location();
/*    */   }
/*    */   
/*    */   public <A> A copy$default$2() {
/* 31 */     return elem();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 31 */     return "Include";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 31 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 31 */     switch (x$1) {
/*    */       default:
/* 31 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 31 */     return location();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 31 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 31 */     return x$1 instanceof Include;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 31 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 31 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 159
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/collection/script/Include
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 163
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/collection/script/Include
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
/*    */     //   #31	-> 0
/*    */     //   #236	-> 12
/*    */     //   #31	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	165	0	this	Lscala/collection/script/Include;
/*    */     //   0	165	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Include(Location location, Object elem) {
/* 31 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Include(Object elem) {
/* 32 */     this(NoLo$.MODULE$, (A)elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\Include.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */