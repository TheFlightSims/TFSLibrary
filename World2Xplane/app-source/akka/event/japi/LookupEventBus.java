/*    */ package akka.event.japi;
/*    */ 
/*    */ import akka.event.EventBus;
/*    */ import akka.event.LookupClassification;
/*    */ import akka.util.Index;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00154Q!\001\002\002\002%\021a\002T8pWV\004XI^3oi\n+8O\003\002\004\t\005!!.\0319j\025\t)a!A\003fm\026tGOC\001\b\003\021\t7n[1\004\001U!!bF\021%'\r\0011\"\005\t\003\031=i\021!\004\006\002\035\005)1oY1mC&\021\001#\004\002\007\003:L(+\0324\021\013I\031R\003I\022\016\003\tI!\001\006\002\003\021\0253XM\034;CkN\004\"AF\f\r\001\021)\001\004\001b\0013\t\tQ)\005\002\033;A\021AbG\005\00395\021qAT8uQ&tw\r\005\002\r=%\021q$\004\002\004\003:L\bC\001\f\"\t\025\021\003A1\001\032\005\005\031\006C\001\f%\t\025)\003A1\001\032\005\005\031\005\"B\024\001\t\003A\023A\002\037j]&$h\bF\001*!\025\021\002!\006\021$\021\035Y\003A1A\005\n1\n1AY;t+\005i#\003\002\030\feU2Aa\f\031\001[\taAH]3gS:,W.\0328u}!1\021\007\001Q\001\n5\nAAY;tAA\0211\007N\007\002\t%\021A\003\002\t\003gYJ!a\016\003\003)1{wn[;q\0072\f7o]5gS\016\fG/[8o\013\021Id\006A\013\003\013\0253XM\034;\006\tmr\003\001\t\002\013'V\0247o\031:jE\026\024X\001B\037/\001\r\022!b\0217bgNLg-[3s\021\025y\004A\"\005A\003\035i\027\r]*ju\026$\022!\021\t\003\031\tK!aQ\007\003\007%sG\017C\003F\001\031Ea)\001\nd_6\004\030M]3Tk\n\0348M]5cKJ\034HcA!H\023\")\001\n\022a\001A\005\t\021\rC\003K\t\002\007\001%A\001c\021\025a\005A\"\005N\003!\031G.Y:tS\032LHCA\022O\021\025)1\n1\001\026\021\025\001\006A\"\005R\003\035\001XO\0317jg\"$2AU+W!\ta1+\003\002U\033\t!QK\\5u\021\025)q\n1\001\026\021\0259v\n1\001!\003)\031XOY:de&\024WM\035\005\0063\002!\tEW\001\ngV\0247o\031:jE\026$2a\0270`!\taA,\003\002^\033\t9!i\\8mK\006t\007\"B,Y\001\004\001\003\"\0021Y\001\004\031\023A\001;p\021\025\021\007\001\"\021d\003-)hn];cg\016\024\030NY3\025\007m#W\rC\003XC\002\007\001\005C\003gC\002\0071%\001\003ge>l\007\"\0022\001\t\003BGC\001*j\021\0259v\r1\001!\021\025\001\006\001\"\021l)\t\021F\016C\003\006U\002\007Q\003")
/*    */ public abstract class LookupEventBus<E, S, C> implements EventBus<E, S, C> {
/* 47 */   private final EventBus bus = new $anon$2(this);
/*    */   
/*    */   private EventBus bus() {
/* 47 */     return this.bus;
/*    */   }
/*    */   
/*    */   public abstract int mapSize();
/*    */   
/*    */   public abstract int compareSubscribers(S paramS1, S paramS2);
/*    */   
/*    */   public abstract C classify(E paramE);
/*    */   
/*    */   public abstract void publish(E paramE, S paramS);
/*    */   
/*    */   public class $anon$2 implements EventBus, LookupClassification {
/*    */     private final Index<Object, Object> subscribers;
/*    */     
/*    */     public final Index<Object, Object> subscribers() {
/* 47 */       return this.subscribers;
/*    */     }
/*    */     
/*    */     public final void akka$event$LookupClassification$_setter_$subscribers_$eq(Index<Object, Object> x$1) {
/* 47 */       this.subscribers = x$1;
/*    */     }
/*    */     
/*    */     public boolean subscribe(Object subscriber, Object to) {
/* 47 */       return LookupClassification.class.subscribe(this, subscriber, to);
/*    */     }
/*    */     
/*    */     public boolean unsubscribe(Object subscriber, Object from) {
/* 47 */       return LookupClassification.class.unsubscribe(this, subscriber, from);
/*    */     }
/*    */     
/*    */     public void unsubscribe(Object subscriber) {
/* 47 */       LookupClassification.class.unsubscribe(this, subscriber);
/*    */     }
/*    */     
/*    */     public void publish(Object event) {
/* 47 */       LookupClassification.class.publish(this, event);
/*    */     }
/*    */     
/*    */     public $anon$2(LookupEventBus $outer) {
/* 47 */       LookupClassification.class.$init$(this);
/*    */     }
/*    */     
/*    */     public int mapSize() {
/* 52 */       return this.$outer.mapSize();
/*    */     }
/*    */     
/*    */     public int compareSubscribers(Object a, Object b) {
/* 55 */       return this.$outer.compareSubscribers(a, b);
/*    */     }
/*    */     
/*    */     public C classify(Object event) {
/* 58 */       return (C)this.$outer.classify(event);
/*    */     }
/*    */     
/*    */     public void publish(Object event, Object subscriber) {
/* 61 */       this.$outer.publish(event, subscriber);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean subscribe(Object subscriber, Object to) {
/* 84 */     return ((LookupClassification)bus()).subscribe(subscriber, to);
/*    */   }
/*    */   
/*    */   public boolean unsubscribe(Object subscriber, Object from) {
/* 85 */     return ((LookupClassification)bus()).unsubscribe(subscriber, from);
/*    */   }
/*    */   
/*    */   public void unsubscribe(Object subscriber) {
/* 86 */     ((LookupClassification)bus()).unsubscribe(subscriber);
/*    */   }
/*    */   
/*    */   public void publish(Object event) {
/* 87 */     ((LookupClassification)bus()).publish(event);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\japi\LookupEventBus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */