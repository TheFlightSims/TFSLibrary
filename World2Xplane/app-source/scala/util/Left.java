/*     */ package scala.util;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%c\001B\001\003\005\036\021A\001T3gi*\0211\001B\001\005kRLGNC\001\006\003\025\0318-\0317b\007\001)2\001C\b\033'\021\001\021\002H\020\021\t)YQ\"G\007\002\005%\021AB\001\002\007\013&$\b.\032:\021\0059yA\002\001\003\007!\001!)\031A\t\003\003\005\013\"A\005\f\021\005M!R\"\001\003\n\005U!!a\002(pi\"Lgn\032\t\003']I!\001\007\003\003\007\005s\027\020\005\002\0175\02111\004\001CC\002E\021\021A\021\t\003'uI!A\b\003\003\017A\023x\016Z;diB\0211\003I\005\003C\021\021AbU3sS\006d\027N_1cY\026D\001b\t\001\003\026\004%\t\001J\001\002CV\tQ\002\003\005'\001\tE\t\025!\003\016\003\t\t\007\005C\003)\001\021\005\021&\001\004=S:LGO\020\013\003U-\002BA\003\001\0163!)1e\na\001\033!)Q\006\001C\001]\0051\021n\035'fMR,\022a\f\t\003'AJ!!\r\003\003\017\t{w\016\\3b]\")1\007\001C\001]\0059\021n\035*jO\"$\bbB\033\001\003\003%\tAN\001\005G>\004\0300F\0028uq\"\"\001O\037\021\t)\001\021h\017\t\003\035i\"Q\001\005\033C\002E\001\"A\004\037\005\013m!$\031A\t\t\017\r\"\004\023!a\001s!9q\bAI\001\n\003\001\025AD2paf$C-\0324bk2$H%M\013\004\0032kU#\001\"+\0055\0315&\001#\021\005\025SU\"\001$\013\005\035C\025!C;oG\",7m[3e\025\tIE!\001\006b]:|G/\031;j_:L!a\023$\003#Ut7\r[3dW\026$g+\031:jC:\034W\rB\003\021}\t\007\021\003B\003\034}\t\007\021\003C\004P\001\005\005I\021\t)\002\033A\024x\016Z;diB\023XMZ5y+\005\t\006C\001*X\033\005\031&B\001+V\003\021a\027M\\4\013\003Y\013AA[1wC&\021\001l\025\002\007'R\024\030N\\4\t\017i\003\021\021!C\0017\006a\001O]8ek\016$\030I]5usV\tA\f\005\002\024;&\021a\f\002\002\004\023:$\bb\0021\001\003\003%\t!Y\001\017aJ|G-^2u\0132,W.\0328u)\t1\"\rC\004d?\006\005\t\031\001/\002\007a$\023\007C\004f\001\005\005I\021\t4\002\037A\024x\016Z;di&#XM]1u_J,\022a\032\t\004Q.4R\"A5\013\005)$\021AC2pY2,7\r^5p]&\021A.\033\002\t\023R,'/\031;pe\"9a\016AA\001\n\003y\027\001C2b]\026\013X/\0317\025\005=\002\bbB2n\003\003\005\rA\006\005\be\002\t\t\021\"\021t\003!A\027m\0355D_\022,G#\001/\t\017U\004\021\021!C!m\006AAo\\*ue&tw\rF\001R\021\035A\b!!A\005Be\fa!Z9vC2\034HCA\030{\021\035\031w/!AA\002Y9q\001 \002\002\002#\005Q0\001\003MK\032$\bC\001\006\r\035\t!!!A\t\002}\034BA`A\001?A\0311#a\001\n\007\005\025AA\001\004B]f\024VM\032\005\007Qy$\t!!\003\025\003uDq!\036@\002\002\023\025c\017C\005\002\020y\f\t\021\"!\002\022\005)\021\r\0359msV1\0211CA\r\003;!B!!\006\002 A1!\002AA\f\0037\0012ADA\r\t\031\001\022Q\002b\001#A\031a\"!\b\005\rm\tiA1\001\022\021\035\031\023Q\002a\001\003/A\021\"a\t\003\003%\t)!\n\002\017Ut\027\r\0359msV1\021qEA\031\003w!B!!\013\0024A)1#a\013\0020%\031\021Q\006\003\003\r=\003H/[8o!\rq\021\021\007\003\007!\005\005\"\031A\t\t\025\005U\022\021EA\001\002\004\t9$A\002yIA\002bA\003\001\0020\005e\002c\001\b\002<\02111$!\tC\002EA\021\"a\020\003\003%I!!\021\002\027I,\027\r\032*fg>dg/\032\013\003\003\007\0022AUA#\023\r\t9e\025\002\007\037\nTWm\031;")
/*     */ public final class Left<A, B> extends Either<A, B> implements Product, Serializable {
/*     */   private final A a;
/*     */   
/*     */   public A a() {
/* 189 */     return this.a;
/*     */   }
/*     */   
/*     */   public <A, B> Left<A, B> copy(Object a) {
/* 189 */     return new Left((A)a);
/*     */   }
/*     */   
/*     */   public <A, B> A copy$default$1() {
/* 189 */     return a();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 189 */     return "Left";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 189 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 189 */     switch (x$1) {
/*     */       default:
/* 189 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 189 */     return a();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 189 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 189 */     return x$1 instanceof Left;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 189 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 189 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/* 189 */     if (this != x$1) {
/*     */       boolean bool;
/* 189 */       if (x$1 instanceof Left) {
/* 236 */         bool = true;
/*     */       } else {
/* 236 */         bool = false;
/*     */       } 
/*     */       if (bool) {
/*     */         Left left = (Left)x$1;
/*     */         Object object = left.a();
/*     */         A a;
/*     */         if ((((a = a()) == object) ? true : ((a == null) ? false : ((a instanceof Number) ? BoxesRunTime.equalsNumObject((Number)a, object) : ((a instanceof Character) ? BoxesRunTime.equalsCharObject((Character)a, object) : a.equals(object))))));
/*     */       } 
/*     */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Left(Object a) {
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public boolean isLeft() {
/*     */     return true;
/*     */   }
/*     */   
/*     */   public boolean isRight() {
/*     */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Left.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */