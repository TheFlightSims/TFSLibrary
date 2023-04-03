/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorSystem;
/*     */ import com.typesafe.config.Config;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}b\001B\001\003\001\036\021!eU5oO2,7i\0348tk6,'o\0248msVs'm\\;oI\026$W*Y5mE>D(BA\002\005\003!!\027n\0359bi\016D'\"A\003\002\t\005\\7.Y\002\001'\031\001\001B\004\n\0317A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\0275\013\027\016\0342pqRK\b/\032\t\004\037M)\022B\001\013\003\005Q\001&o\0343vG\026\034X*Z:tC\036,\027+^3vKB\021qBF\005\003/\t\021\001CT8eK6+7o]1hKF+X-^3\021\005%I\022B\001\016\013\005\035\001&o\0343vGR\004\"!\003\017\n\005uQ!\001D*fe&\fG.\033>bE2,\007\"B\020\001\t\003\001\023A\002\037j]&$h\bF\001\"!\ty\001\001C\003 \001\021\0051\005F\002\"IABQ!\n\022A\002\031\n\001b]3ui&twm\035\t\003O5r!\001K\026\016\003%R!A\013\003\002\013\005\034Go\034:\n\0051J\023aC!di>\0248+_:uK6L!AL\030\003\021M+G\017^5oONT!\001L\025\t\013E\022\003\031\001\032\002\r\r|gNZ5h!\t\031\024(D\0015\025\t\tTG\003\0027o\005AA/\0379fg\0064WMC\0019\003\r\031w.\\\005\003uQ\022aaQ8oM&<\007\"\002\037\001\t\013j\024AB2sK\006$X\rF\002?\003&\003\"aD \n\005\001\023!\001D'fgN\fw-Z)vKV,\007\"\002\"<\001\004\031\025!B8x]\026\024\bcA\005E\r&\021QI\003\002\007\037B$\030n\0348\021\005!:\025B\001%*\005!\t5\r^8s%\0264\007\"\002&<\001\004Y\025AB:zgR,W\016E\002\n\t2\003\"\001K'\n\0059K#aC!di>\0248+_:uK6Dq\001\025\001\002\002\023\005\001%\001\003d_BL\bb\002*\001\003\003%\teU\001\016aJ|G-^2u!J,g-\033=\026\003Q\003\"!\026.\016\003YS!a\026-\002\t1\fgn\032\006\0023\006!!.\031<b\023\tYfK\001\004TiJLgn\032\005\b;\002\t\t\021\"\001_\0031\001(o\0343vGR\f%/\033;z+\005y\006CA\005a\023\t\t'BA\002J]RDqa\031\001\002\002\023\005A-\001\bqe>$Wo\031;FY\026lWM\034;\025\005\025D\007CA\005g\023\t9'BA\002B]fDq!\0332\002\002\003\007q,A\002yIEBqa\033\001\002\002\023\005C.A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005i\007c\0018rK6\tqN\003\002q\025\005Q1m\0347mK\016$\030n\0348\n\005I|'\001C%uKJ\fGo\034:\t\017Q\004\021\021!C\001k\006A1-\0318FcV\fG\016\006\002wsB\021\021b^\005\003q*\021qAQ8pY\026\fg\016C\004jg\006\005\t\031A3\t\017m\004\021\021!C!y\006A\001.Y:i\007>$W\rF\001`\021\035q\b!!A\005B}\f\001\002^8TiJLgn\032\013\002)\"I\0211\001\001\002\002\023\005\023QA\001\007KF,\030\r\\:\025\007Y\f9\001\003\005j\003\003\t\t\0211\001f\017%\tYAAA\001\022\003\ti!\001\022TS:<G.Z\"p]N,X.\032:P]2LXK\0342pk:$W\rZ'bS2\024w\016\037\t\004\037\005=a\001C\001\003\003\003E\t!!\005\024\013\005=\0211C\016\021\013\005U\0211D\021\016\005\005]!bAA\r\025\0059!/\0368uS6,\027\002BA\017\003/\021\021#\0212tiJ\f7\r\036$v]\016$\030n\03481\021\035y\022q\002C\001\003C!\"!!\004\t\021y\fy!!A\005F}D\021\"a\n\002\020\005\005I\021\021\021\002\013\005\004\b\017\\=\t\025\005-\022qBA\001\n\003\013i#A\004v]\006\004\b\017\\=\025\007Y\fy\003C\005\0022\005%\022\021!a\001C\005\031\001\020\n\031\t\025\005U\022qBA\001\n\023\t9$A\006sK\006$'+Z:pYZ,GCAA\035!\r)\0261H\005\004\003{1&AB(cU\026\034G\017")
/*     */ public class SingleConsumerOnlyUnboundedMailbox implements MailboxType, ProducesMessageQueue<NodeMessageQueue>, Product, Serializable {
/*     */   public SingleConsumerOnlyUnboundedMailbox copy() {
/* 569 */     return new SingleConsumerOnlyUnboundedMailbox();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 569 */     return "SingleConsumerOnlyUnboundedMailbox";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 569 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 569 */     int i = x$1;
/* 569 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 569 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 569 */     return x$1 instanceof SingleConsumerOnlyUnboundedMailbox;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 569 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 569 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     boolean bool;
/* 569 */     Object object = x$1;
/* 569 */     if (object instanceof SingleConsumerOnlyUnboundedMailbox) {
/*     */       bool = true;
/*     */     } else {
/*     */       bool = false;
/*     */     } 
/* 569 */     return (bool && ((SingleConsumerOnlyUnboundedMailbox)x$1).canEqual(this));
/*     */   }
/*     */   
/*     */   public SingleConsumerOnlyUnboundedMailbox() {
/* 569 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public SingleConsumerOnlyUnboundedMailbox(ActorSystem.Settings settings, Config config) {
/* 571 */     this();
/*     */   }
/*     */   
/*     */   public final MessageQueue create(Option owner, Option system) {
/* 573 */     return new NodeMessageQueue();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\SingleConsumerOnlyUnboundedMailbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */