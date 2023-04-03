/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005=b\001B\001\003\001&\021a!\022=u\t\0264'BA\002\005\003\r!G\017\032\006\003\013\031\t1\001_7m\025\0059\021!B:dC2\f7\001A\n\005\001)q!\003\005\002\f\0315\t!!\003\002\016\005\tIQI\034;jif$UM\032\t\003\037Ai\021AB\005\003#\031\021q\001\025:pIV\034G\017\005\002\020'%\021AC\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\t-\001\021)\032!C\001/\005)Q\r\037;J\tV\t\001\004\005\002\f3%\021!D\001\002\013\013b$XM\0358bY&#\005\002\003\017\001\005#\005\013\021\002\r\002\r\025DH/\023#!\021\025q\002\001\"\001 \003\031a\024N\\5u}Q\021\001%\t\t\003\027\001AQAF\017A\002aAQa\t\001\005B\021\n1BY;jY\022\034FO]5oOR\021Q%\r\t\003M9r!a\n\027\017\005!ZS\"A\025\013\005)B\021A\002\037s_>$h(C\001\b\023\tic!A\004qC\016\\\027mZ3\n\005=\002$!D*ue&twMQ;jY\022,'O\003\002.\r!)!G\ta\001K\005\0211O\031\005\bi\001\t\t\021\"\0016\003\021\031w\016]=\025\005\0012\004b\002\f4!\003\005\r\001\007\005\bq\001\t\n\021\"\001:\0039\031w\016]=%I\0264\027-\0367uIE*\022A\017\026\0031mZ\023\001\020\t\003{\tk\021A\020\006\003\001\013\021\"\0368dQ\026\0347.\0323\013\005\0053\021AC1o]>$\030\r^5p]&\0211I\020\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007bB#\001\003\003%\tER\001\016aJ|G-^2u!J,g-\033=\026\003\035\003\"\001S'\016\003%S!AS&\002\t1\fgn\032\006\002\031\006!!.\031<b\023\tq\025J\001\004TiJLgn\032\005\b!\002\t\t\021\"\001R\0031\001(o\0343vGR\f%/\033;z+\005\021\006CA\bT\023\t!fAA\002J]RDqA\026\001\002\002\023\005q+\001\bqe>$Wo\031;FY\026lWM\034;\025\005a[\006CA\bZ\023\tQfAA\002B]fDq\001X+\002\002\003\007!+A\002yIEBqA\030\001\002\002\023\005s,A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005\001\007cA1e16\t!M\003\002d\r\005Q1m\0347mK\016$\030n\0348\n\005\025\024'\001C%uKJ\fGo\034:\t\017\035\004\021\021!C\001Q\006A1-\0318FcV\fG\016\006\002jYB\021qB[\005\003W\032\021qAQ8pY\026\fg\016C\004]M\006\005\t\031\001-\t\0179\004\021\021!C!_\006A\001.Y:i\007>$W\rF\001S\021\035\t\b!!A\005BI\f\001\002^8TiJLgn\032\013\002\017\"9A\017AA\001\n\003*\030AB3rk\006d7\017\006\002jm\"9Al]A\001\002\004Ava\002=\003\003\003E\t!_\001\007\013b$H)\0324\021\005-QhaB\001\003\003\003E\ta_\n\004ur\024\002#B?\002\002a\001S\"\001@\013\005}4\021a\002:v]RLW.Z\005\004\003\007q(!E!cgR\024\030m\031;Gk:\034G/[8oc!1aD\037C\001\003\017!\022!\037\005\bcj\f\t\021\"\022s\021%\tiA_A\001\n\003\013y!A\003baBd\027\020F\002!\003#AaAFA\006\001\004A\002\"CA\013u\006\005I\021QA\f\003\035)h.\0319qYf$B!!\007\002 A!q\"a\007\031\023\r\tiB\002\002\007\037B$\030n\0348\t\023\005\005\0221CA\001\002\004\001\023a\001=%a!I\021Q\005>\002\002\023%\021qE\001\fe\026\fGMU3t_24X\r\006\002\002*A\031\001*a\013\n\007\0055\022J\001\004PE*,7\r\036")
/*     */ public class ExtDef extends EntityDef implements Product, Serializable {
/*     */   private final ExternalID extID;
/*     */   
/*     */   public ExternalID extID() {
/* 117 */     return this.extID;
/*     */   }
/*     */   
/*     */   public ExtDef copy(ExternalID extID) {
/* 117 */     return new ExtDef(extID);
/*     */   }
/*     */   
/*     */   public ExternalID copy$default$1() {
/* 117 */     return extID();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 117 */     return "ExtDef";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 117 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 117 */     switch (x$1) {
/*     */       default:
/* 117 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 117 */     return extID();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 117 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 117 */     return x$1 instanceof ExtDef;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 117 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 117 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 75
/*     */     //   5: aload_1
/*     */     //   6: instanceof scala/xml/dtd/ExtDef
/*     */     //   9: ifeq -> 17
/*     */     //   12: iconst_1
/*     */     //   13: istore_2
/*     */     //   14: goto -> 19
/*     */     //   17: iconst_0
/*     */     //   18: istore_2
/*     */     //   19: iload_2
/*     */     //   20: ifeq -> 79
/*     */     //   23: aload_1
/*     */     //   24: checkcast scala/xml/dtd/ExtDef
/*     */     //   27: astore #4
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual extID : ()Lscala/xml/dtd/ExternalID;
/*     */     //   33: aload #4
/*     */     //   35: invokevirtual extID : ()Lscala/xml/dtd/ExternalID;
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
/*     */     //   #117	-> 0
/*     */     //   #236	-> 12
/*     */     //   #117	-> 19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	81	0	this	Lscala/xml/dtd/ExtDef;
/*     */     //   0	81	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ExtDef(ExternalID extID) {
/* 117 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public StringBuilder buildString(StringBuilder sb) {
/* 119 */     return extID().buildString(sb);
/*     */   }
/*     */   
/*     */   public static <A> Function1<ExternalID, A> andThen(Function1<ExtDef, A> paramFunction1) {
/*     */     return ExtDef$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, ExtDef> compose(Function1<A, ExternalID> paramFunction1) {
/*     */     return ExtDef$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ExtDef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */