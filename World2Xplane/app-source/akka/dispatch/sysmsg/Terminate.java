/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001M4Q!\001\002A\r!\021\021\002V3s[&t\027\r^3\013\005\r!\021AB:zg6\034xM\003\002\006\r\005AA-[:qCR\034\007NC\001\b\003\021\t7n[1\024\013\001Iqb\005\f\021\005)iQ\"A\006\013\0031\tQa]2bY\006L!AD\006\003\r\005s\027PU3g!\t\001\022#D\001\003\023\t\021\"AA\007TsN$X-\\'fgN\fw-\032\t\003\025QI!!F\006\003\017A\023x\016Z;diB\021!bF\005\0031-\021AbU3sS\006d\027N_1cY\026DQA\007\001\005\002q\ta\001P5oSRt4\001\001\013\002;A\021\001\003\001\005\b?\001\t\t\021\"\001\035\003\021\031w\016]=\t\017\005\002\021\021!C!E\005i\001O]8ek\016$\bK]3gSb,\022a\t\t\003I%j\021!\n\006\003M\035\nA\001\\1oO*\t\001&\001\003kCZ\f\027B\001\026&\005\031\031FO]5oO\"9A\006AA\001\n\003i\023\001\0049s_\022,8\r^!sSRLX#\001\030\021\005)y\023B\001\031\f\005\rIe\016\036\005\be\001\t\t\021\"\0014\0039\001(o\0343vGR,E.Z7f]R$\"\001N\034\021\005))\024B\001\034\f\005\r\te.\037\005\bqE\n\t\0211\001/\003\rAH%\r\005\bu\001\t\t\021\"\021<\003=\001(o\0343vGRLE/\032:bi>\024X#\001\037\021\007u\002E'D\001?\025\ty4\"\001\006d_2dWm\031;j_:L!!\021 \003\021%#XM]1u_JDqa\021\001\002\002\023\005A)\001\005dC:,\025/^1m)\t)\005\n\005\002\013\r&\021qi\003\002\b\005>|G.Z1o\021\035A$)!AA\002QBqA\023\001\002\002\023\0053*\001\005iCND7i\0343f)\005q\003bB'\001\003\003%\tET\001\ti>\034FO]5oOR\t1\005C\004Q\001\005\005I\021I)\002\r\025\fX/\0317t)\t)%\013C\0049\037\006\005\t\031\001\033)\007\001!v\013\005\002\013+&\021ak\003\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!A\004\t3\n\t\t\021#\001\0075\006IA+\032:nS:\fG/\032\t\003!m3\001\"\001\002\002\002#\005a\001X\n\0047v3\002c\0010b;5\tqL\003\002a\027\0059!/\0368uS6,\027B\0012`\005E\t%m\035;sC\016$h)\0368di&|g\016\r\005\0065m#\t\001\032\013\0025\"9QjWA\001\n\013r\005bB4\\\003\003%\t\tH\001\006CB\004H.\037\005\bSn\013\t\021\"!k\003\035)h.\0319qYf$\"!R6\t\0171D\027\021!a\001;\005\031\001\020\n\031\t\0179\\\026\021!C\005_\006Y!/Z1e%\026\034x\016\034<f)\005\001\bC\001\023r\023\t\021XE\001\004PE*,7\r\036")
/*     */ public class Terminate implements SystemMessage, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private transient SystemMessage next;
/*     */   
/*     */   public SystemMessage next() {
/* 230 */     return this.next;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void next_$eq(SystemMessage x$1) {
/* 230 */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public void unlink() {
/* 230 */     SystemMessage$class.unlink(this);
/*     */   }
/*     */   
/*     */   public boolean unlinked() {
/* 230 */     return SystemMessage$class.unlinked(this);
/*     */   }
/*     */   
/*     */   public Terminate copy() {
/* 230 */     return new Terminate();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 230 */     return "Terminate";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 230 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 230 */     int i = x$1;
/* 230 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 230 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 230 */     return x$1 instanceof Terminate;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 230 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 230 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     boolean bool;
/* 230 */     Object object = x$1;
/* 230 */     if (object instanceof Terminate) {
/*     */       bool = true;
/*     */     } else {
/*     */       bool = false;
/*     */     } 
/* 230 */     return (bool && ((Terminate)x$1).canEqual(this));
/*     */   }
/*     */   
/*     */   public Terminate() {
/* 230 */     SystemMessage$class.$init$(this);
/* 230 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Terminate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */