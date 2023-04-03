/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001Y4q!\001\002\021\002\007\005\021BA\005Qk\nd\027n\0355fe*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQqd\005\002\001\027A\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\t\013A\001A\021A\t\002\r\021Jg.\033;%)\005\021\002C\001\007\024\023\t!bA\001\003V]&$H!\002\f\001\005\0039\"a\001)vEF\021\001d\007\t\003\031eI!A\007\004\003\0179{G\017[5oOB\031A\004A\017\016\003\t\001\"AH\020\r\001\021)\001\005\001b\001C\t\031QI\036;\022\005a\021\003C\001\007$\023\t!cAA\002B]f,AA\n\001\001O\t\0311+\0362\021\tqASDK\005\003S\t\021!bU;cg\016\024\030NY3s!\tYS#D\001\001\013\021i\003\001\001\030\003\r\031KG\016^3s!\021aq&H\031\n\005A2!!\003$v]\016$\030n\03482!\ta!'\003\0024\r\t9!i\\8mK\006t\007bB\033\001\005\004%\tBN\001\005g\026dg-F\001+\021\031A\004\001)A\005U\005)1/\0327gA!9!\b\001b\001\n\023Y\024a\0024jYR,'o]\013\002yI\031Q(Q%\007\tyz\004\001\020\002\ryI,g-\0338f[\026tGO\020\005\007\001\002\001\013\021\002\037\002\021\031LG\016^3sg\002\002B\001\b\"E\013&\0211I\001\002\b\021\006\034\b.T1q!\tYS\005E\002\035\r\"K!a\022\002\003\007M+G\017\005\002,YA!AD\023#I\023\tY%A\001\005Nk2$\030.T1q\021\035i\005A1A\005\n9\013\021b];ta\026tG-\0323\026\003=\0032\001\b)E\023\t\t&AA\004ICND7+\032;\t\rM\003\001\025!\003P\003)\031Xo\0359f]\022,G\r\t\005\006+\002!\tAV\001\ngV\0247o\031:jE\026$\"AE,\t\013a#\006\031\001#\002\007M,(\rC\003V\001\021\005!\fF\002\0237rCQ\001W-A\002\021CQ!X-A\002!\013aAZ5mi\026\024\b\"B0\001\t\003\001\027aE:vgB,g\016Z*vEN\034'/\0339uS>tGC\001\nb\021\025Af\f1\001E\021\025\031\007\001\"\001e\003Q\t7\r^5wCR,7+\0362tGJL\007\017^5p]R\021!#\032\005\0061\n\004\r\001\022\005\006O\002!\t\001[\001\023e\026lwN^3Tk\n\0348M]5qi&|g\016\006\002\023S\")\001L\032a\001\t\")1\016\001C\001#\005\031\"/Z7pm\026\034VOY:de&\004H/[8og\")Q\016\001C\t]\0069\001/\0362mSNDGC\001\np\021\025\001H\0161\001\036\003\025)g/\0328u\021\025\021\b\001\"\021t\003\031)\027/^1mgR\021\021\007\036\005\006kF\004\rAI\001\004_\nT\007")
/*    */ public interface Publisher<Evt> {
/*    */   void scala$collection$mutable$Publisher$_setter_$self_$eq(Publisher paramPublisher);
/*    */   
/*    */   void scala$collection$mutable$Publisher$_setter_$scala$collection$mutable$Publisher$$filters_$eq(HashMap paramHashMap);
/*    */   
/*    */   void scala$collection$mutable$Publisher$_setter_$scala$collection$mutable$Publisher$$suspended_$eq(HashSet paramHashSet);
/*    */   
/*    */   Publisher self();
/*    */   
/*    */   HashMap<Subscriber<Evt, Publisher>, Set<Function1<Evt, Object>>> scala$collection$mutable$Publisher$$filters();
/*    */   
/*    */   HashSet<Subscriber<Evt, Publisher>> scala$collection$mutable$Publisher$$suspended();
/*    */   
/*    */   void subscribe(Subscriber<Evt, Publisher> paramSubscriber);
/*    */   
/*    */   void subscribe(Subscriber<Evt, Publisher> paramSubscriber, Function1<Evt, Object> paramFunction1);
/*    */   
/*    */   void suspendSubscription(Subscriber<Evt, Publisher> paramSubscriber);
/*    */   
/*    */   void activateSubscription(Subscriber<Evt, Publisher> paramSubscriber);
/*    */   
/*    */   void removeSubscription(Subscriber<Evt, Publisher> paramSubscriber);
/*    */   
/*    */   void removeSubscriptions();
/*    */   
/*    */   void publish(Evt paramEvt);
/*    */   
/*    */   boolean equals(Object paramObject);
/*    */   
/*    */   public class $anon$1 extends HashMap<Subscriber<Evt, Publisher>, Set<Function1<Evt, Object>>> implements MultiMap<Subscriber<Evt, Publisher>, Function1<Evt, Object>> {
/*    */     public Set<Function1<Evt, Object>> makeSet() {
/* 40 */       return MultiMap$class.makeSet(this);
/*    */     }
/*    */     
/*    */     public MultiMap<Subscriber<Evt, Publisher>, Function1<Evt, Object>> addBinding(Object key, Object value) {
/* 40 */       return MultiMap$class.addBinding(this, key, value);
/*    */     }
/*    */     
/*    */     public MultiMap<Subscriber<Evt, Publisher>, Function1<Evt, Object>> removeBinding(Object key, Object value) {
/* 40 */       return MultiMap$class.removeBinding(this, key, value);
/*    */     }
/*    */     
/*    */     public boolean entryExists(Object key, Function1 p) {
/* 40 */       return MultiMap$class.entryExists(this, key, p);
/*    */     }
/*    */     
/*    */     public $anon$1(Publisher $outer) {
/* 40 */       MultiMap$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class Publisher$$anonfun$subscribe$1 extends AbstractFunction1<Evt, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(Object event) {
/* 43 */       return true;
/*    */     }
/*    */     
/*    */     public Publisher$$anonfun$subscribe$1(Publisher $outer) {}
/*    */   }
/*    */   
/*    */   public class Publisher$$anonfun$publish$1 extends AbstractFunction1<Subscriber<Evt, Publisher>, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Object event$1;
/*    */     
/*    */     public Publisher$$anonfun$publish$1(Publisher $outer, Object event$1) {}
/*    */     
/*    */     public final void apply(Subscriber<Object, Publisher> sub) {
/* 52 */       if (!this.$outer.scala$collection$mutable$Publisher$$suspended().contains(sub) && (
/* 53 */         (MultiMap)this.$outer.scala$collection$mutable$Publisher$$filters()).entryExists(sub, (Function1)new Publisher$$anonfun$publish$1$$anonfun$apply$1(this)))
/* 54 */         sub.notify(this.$outer.self(), this.event$1); 
/*    */     }
/*    */     
/*    */     public class Publisher$$anonfun$publish$1$$anonfun$apply$1 extends AbstractFunction1<Function1<Evt, Object>, Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final boolean apply(Function1 p) {
/*    */         return BoxesRunTime.unboxToBoolean(p.apply(this.$outer.event$1));
/*    */       }
/*    */       
/*    */       public Publisher$$anonfun$publish$1$$anonfun$apply$1(Publisher$$anonfun$publish$1 $outer) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Publisher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */