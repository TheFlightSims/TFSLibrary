/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005]a\001B\001\003\001\036\021\001\"\0233f]RLg-\037\006\003\007\021\tQ!Y2u_JT\021!B\001\005C.\\\027m\001\001\024\013\001AaBE\013\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"AA\nBkR|'+Z2fSZ,G-T3tg\006<W\r\005\002\n'%\021AC\003\002\b!J|G-^2u!\tIa#\003\002\030\025\ta1+\032:jC2L'0\0312mK\"A\021\004\001BK\002\023\005!$A\005nKN\034\030mZ3JIV\t1\004\005\002\n9%\021QD\003\002\004\003:L\b\002C\020\001\005#\005\013\021B\016\002\0255,7o]1hK&#\007\005C\003\"\001\021\005!%\001\004=S:LGO\020\013\003G\021\002\"a\004\001\t\013e\001\003\031A\016\t\017\031\002\021\021!C\001O\005!1m\0349z)\t\031\003\006C\004\032KA\005\t\031A\016\t\017)\002\021\023!C\001W\005q1m\0349zI\021,g-Y;mi\022\nT#\001\027+\005mi3&\001\030\021\005=\"T\"\001\031\013\005E\022\024!C;oG\",7m[3e\025\t\031$\"\001\006b]:|G/\031;j_:L!!\016\031\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\0048\001\005\005I\021\t\035\002\033A\024x\016Z;diB\023XMZ5y+\005I\004C\001\036@\033\005Y$B\001\037>\003\021a\027M\\4\013\003y\nAA[1wC&\021\001i\017\002\007'R\024\030N\\4\t\017\t\003\021\021!C\001\007\006a\001O]8ek\016$\030I]5usV\tA\t\005\002\n\013&\021aI\003\002\004\023:$\bb\002%\001\003\003%\t!S\001\017aJ|G-^2u\0132,W.\0328u)\tY\"\nC\004L\017\006\005\t\031\001#\002\007a$\023\007C\004N\001\005\005I\021\t(\002\037A\024x\016Z;di&#XM]1u_J,\022a\024\t\004!N[R\"A)\013\005IS\021AC2pY2,7\r^5p]&\021A+\025\002\t\023R,'/\031;pe\"9a\013AA\001\n\0039\026\001C2b]\026\013X/\0317\025\005a[\006CA\005Z\023\tQ&BA\004C_>dW-\0318\t\017-+\026\021!a\0017!9Q\fAA\001\n\003r\026\001\0035bg\"\034u\016Z3\025\003\021Cq\001\031\001\002\002\023\005\023-\001\005u_N#(/\0338h)\005I\004bB2\001\003\003%\t\005Z\001\007KF,\030\r\\:\025\005a+\007bB&c\003\003\005\ra\007\025\004\001\035T\007CA\005i\023\tI'B\001\tTKJL\027\r\034,feNLwN\\+J\tz\t\021aB\004m\005\005\005\t\022A7\002\021%#WM\034;jMf\004\"a\0048\007\017\005\021\021\021!E\001_N\031a\016]\013\021\tE$8dI\007\002e*\0211OC\001\beVtG/[7f\023\t)(OA\tBEN$(/Y2u\rVt7\r^5p]FBQ!\t8\005\002]$\022!\034\005\bA:\f\t\021\"\022b\021\035Qh.!A\005\002n\fQ!\0319qYf$\"a\t?\t\013eI\b\031A\016\t\017yt\027\021!CA\0069QO\\1qa2LH\003BA\001\003\017\001B!CA\0027%\031\021Q\001\006\003\r=\003H/[8o\021!\tI!`A\001\002\004\031\023a\001=%a!I\021Q\0028\002\002\023%\021qB\001\fe\026\fGMU3t_24X\r\006\002\002\022A\031!(a\005\n\007\005U1H\001\004PE*,7\r\036")
/*    */ public class Identify implements AutoReceivedMessage, Product {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   private final Object messageId;
/*    */   
/*    */   public static <A> Function1<Object, A> andThen(Function1<Identify, A> paramFunction1) {
/*    */     return Identify$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, Identify> compose(Function1<A, Object> paramFunction1) {
/*    */     return Identify$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public Object messageId() {
/* 63 */     return this.messageId;
/*    */   }
/*    */   
/*    */   public Identify copy(Object messageId) {
/* 63 */     return new Identify(messageId);
/*    */   }
/*    */   
/*    */   public Object copy$default$1() {
/* 63 */     return messageId();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 63 */     return "Identify";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 63 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 63 */     int i = x$1;
/* 63 */     switch (i) {
/*    */       default:
/* 63 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 63 */     return messageId();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 63 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 63 */     return x$1 instanceof Identify;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 63 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 63 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/* 63 */     if (this != x$1) {
/*    */       boolean bool;
/* 63 */       Object object = x$1;
/* 63 */       if (object instanceof Identify) {
/* 63 */         bool = true;
/*    */       } else {
/* 63 */         bool = false;
/*    */       } 
/* 63 */       if (bool) {
/* 63 */         Identify identify = (Identify)x$1;
/* 63 */         if ((BoxesRunTime.equals(messageId(), identify.messageId()) && identify.canEqual(this)));
/*    */       } 
/* 63 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Identify(Object messageId) {
/* 63 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Identify.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */