/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001!4A!\001\002\001\023\t9\001*[:u_JL(BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)2AC\020\026'\025\0011\"\t\023(!\raQbD\007\002\005%\021aB\001\002\021\003\n\034HO]1di&#XM]1cY\026\004B\001E\t\024=5\ta!\003\002\023\r\t1A+\0369mKJ\002\"\001F\013\r\001\021)a\003\001b\001/\t\031\001+\0362\022\005aY\002C\001\t\032\023\tQbAA\004O_RD\027N\\4\021\005Aa\022BA\017\007\005\r\te.\037\t\003)}!Q\001\t\001C\002]\0211!\022<u!\021a!EH\n\n\005\r\022!AC*vEN\034'/\0332feB\031A\"J\b\n\005\031\022!\001C%uKJ\f'\r\\3\021\005AA\023BA\025\007\0051\031VM]5bY&T\030M\0317f\021\025Y\003\001\"\001-\003\031a\024N\\5u}Q\tQ\006\005\003\r\001y\031\002bB\030\001\005\004%\t\002M\001\004Y><W#A\031\021\0071\021t\"\003\0024\005\t)\021+^3vK\"1Q\007\001Q\001\nE\nA\001\\8hA!9q\007\001b\001\n\003A\024AC7bq\"K7\017^8ssV\t\021\b\005\002\021u%\0211H\002\002\004\023:$\bBB\037\001A\003%\021(A\006nCbD\025n\035;pef\004\003\"B \001\t\003\001\025A\0028pi&4\027\020F\002B\t\032\003\"\001\005\"\n\005\r3!\001B+oSRDQ!\022 A\002M\t1\001];c\021\0259e\b1\001\037\003\025)g/\0328u\021\025I\005\001\"\0219\003\021\031\030N_3\t\013-\003A\021\001'\002\021%$XM]1u_J,\022!\024\t\004\035>{Q\"\001\003\n\005A#!\001C%uKJ\fGo\034:\t\013I\003A\021A*\002\r\0254XM\034;t+\005!\006c\001(P=!)a\013\001C\001/\006)1\r\\3beR\t\021\tC\003Z\001\021\005#,\001\004fcV\fGn\035\013\0037z\003\"\001\005/\n\005u3!a\002\"p_2,\027M\034\005\006?b\003\raG\001\004_\nT\007\"B1\001\t\003\022\027\001\0035bg\"\034u\016Z3\025\003eB3\001\0013h!\t\001R-\003\002g\r\t\0012+\032:jC24VM]:j_:,\026\n\022\020\t\021:|\006\0033\013\033Z\004")
/*    */ public class History<Evt, Pub> extends AbstractIterable<Tuple2<Pub, Evt>> implements Subscriber<Evt, Pub>, Iterable<Tuple2<Pub, Evt>>, Serializable {
/*    */   public static final long serialVersionUID = 5219213543849892588L;
/*    */   
/* 34 */   private final Queue<Tuple2<Pub, Evt>> log = new Queue<Tuple2<Pub, Evt>>();
/*    */   
/*    */   public Queue<Tuple2<Pub, Evt>> log() {
/* 34 */     return this.log;
/*    */   }
/*    */   
/* 35 */   private final int maxHistory = 1000;
/*    */   
/*    */   public int maxHistory() {
/* 35 */     return this.maxHistory;
/*    */   }
/*    */   
/*    */   public void notify(Object pub, Object event) {
/* 43 */     (log().length() >= maxHistory()) ? 
/* 44 */       log().dequeue() : BoxedUnit.UNIT;
/* 46 */     (new Tuple2[1])[0] = new Tuple2(pub, event);
/* 46 */     log().enqueue(Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]));
/*    */   }
/*    */   
/*    */   public int size() {
/* 49 */     return log().length();
/*    */   }
/*    */   
/*    */   public Iterator<Tuple2<Pub, Evt>> iterator() {
/* 50 */     return log().iterator();
/*    */   }
/*    */   
/*    */   public Iterator<Evt> events() {
/* 51 */     return log().iterator().map((Function1)new History$$anonfun$events$1(this));
/*    */   }
/*    */   
/*    */   public class History$$anonfun$events$1 extends AbstractFunction1<Tuple2<Pub, Evt>, Evt> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Evt apply(Tuple2 x$1) {
/* 51 */       return (Evt)x$1._2();
/*    */     }
/*    */     
/*    */     public History$$anonfun$events$1(History $outer) {}
/*    */   }
/*    */   
/*    */   public void clear() {
/* 53 */     log().clear();
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/*    */     boolean bool;
/* 59 */     if (obj instanceof History) {
/* 59 */       History history = (History)obj;
/* 59 */       bool = log().equals(history.log());
/*    */     } else {
/* 61 */       bool = false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 63 */     return log().hashCode();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\History.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */