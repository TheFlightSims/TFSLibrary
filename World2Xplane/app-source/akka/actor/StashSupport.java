/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.DequeBasedMessageQueueSemantics;
/*     */ import akka.dispatch.Envelope;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.immutable.Vector$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001u4\001\"\001\002\021\002\007\005AA\002\002\r'R\f7\017[*vaB|'\017\036\006\003\007\021\tQ!Y2u_JT\021!B\001\005C.\\\027m\005\002\001\017A\021\001bC\007\002\023)\t!\"A\003tG\006d\027-\003\002\r\023\t1\021I\\=SK\032DQA\004\001\005\002A\ta\001J5oSR$3\001\001\013\002#A\021\001BE\005\003'%\021A!\0268ji\"1Q\003\001D\001\tY\tqaY8oi\026DH/F\001\030!\tA\022$D\001\003\023\tQ\"A\001\007BGR|'oQ8oi\026DH\017\003\004\035\001\031\005A!H\001\005g\026dg-F\001\037!\tAr$\003\002!\005\tA\021i\031;peJ+g\rC\004#\001\001\007I\021B\022\002\021QDWm\025;bg\",\022\001\n\t\004K)bS\"\001\024\013\005\035B\023!C5n[V$\030M\0317f\025\tI\023\"\001\006d_2dWm\031;j_:L!a\013\024\003\rY+7\r^8s!\ti\003'D\001/\025\tyC!\001\005eSN\004\030\r^2i\023\t\tdF\001\005F]Z,Gn\0349f\021\035\031\004\0011A\005\nQ\nA\002\0365f'R\f7\017[0%KF$\"!E\033\t\017Y\022\024\021!a\001I\005\031\001\020J\031\t\ra\002\001\025)\003%\003%!\b.Z*uCND\007\005C\003;\001\021%1(A\005bGR|'oQ3mYV\tA\b\005\002\031{%\021aH\001\002\n\003\016$xN]\"fY2Dq\001\021\001C\002\023%\021)\001\005dCB\f7-\033;z+\005\021\005C\001\005D\023\t!\025BA\002J]RDaA\022\001!\002\023\021\025!C2ba\006\034\027\016^=!\021!A\005A1A\005\002\021I\025aB7bS2\024w\016_\013\002\025B\021QfS\005\003\031:\022q\004R3rk\026\024\025m]3e\033\026\0348/Y4f#V,W/Z*f[\006tG/[2t\021\031q\005\001)A\005\025\006AQ.Y5mE>D\b\005C\003Q\001\021\005\001#A\003ti\006\034\b\016\003\004S\001\021\005AaU\001\baJ,\007/\0328e)\t\tB\013C\003V#\002\007a+\001\004pi\",'o\035\t\004K]c\023B\001-'\005\r\031V-\035\005\0075\002!\t\001\002\t\002\017Ut7\017^1tQ\")A\f\001C\001!\005QQO\\:uCND\027\t\0347\t\rq\003A\021\001\003_)\t\tr\fC\003a;\002\007\021-A\bgS2$XM\035)sK\022L7-\031;f!\021A!\rZ4\n\005\rL!!\003$v]\016$\030n\03482!\tAQ-\003\002g\023\t\031\021I\\=\021\005!A\027BA5\n\005\035\021un\0347fC:Daa\033\001\005\002\021a\027AC2mK\006\0248\013^1tQR\tQ\016E\002om2r!a\034;\017\005A\034X\"A9\013\005I|\021A\002\037s_>$h(C\001\013\023\t)\030\"A\004qC\016\\\027mZ3\n\005-:(BA;\n\021\025I\b\001\"\003{\0031)g.];fk\0264\025N]:u)\t\t2\020C\003}q\002\007A&\001\005f]Z,Gn\0349f\001")
/*     */ public interface StashSupport {
/*     */   void akka$actor$StashSupport$_setter_$akka$actor$StashSupport$$capacity_$eq(int paramInt);
/*     */   
/*     */   void akka$actor$StashSupport$_setter_$mailbox_$eq(DequeBasedMessageQueueSemantics paramDequeBasedMessageQueueSemantics);
/*     */   
/*     */   ActorContext context();
/*     */   
/*     */   ActorRef self();
/*     */   
/*     */   Vector<Envelope> akka$actor$StashSupport$$theStash();
/*     */   
/*     */   @TraitSetter
/*     */   void akka$actor$StashSupport$$theStash_$eq(Vector<Envelope> paramVector);
/*     */   
/*     */   int akka$actor$StashSupport$$capacity();
/*     */   
/*     */   DequeBasedMessageQueueSemantics mailbox();
/*     */   
/*     */   void stash();
/*     */   
/*     */   void prepend(Seq<Envelope> paramSeq);
/*     */   
/*     */   void unstash();
/*     */   
/*     */   void unstashAll();
/*     */   
/*     */   void unstashAll(Function1<Object, Object> paramFunction1);
/*     */   
/*     */   Vector<Envelope> clearStash();
/*     */   
/*     */   public class StashSupport$$anonfun$prepend$1 extends AbstractFunction2<Envelope, Vector<Envelope>, Vector<Envelope>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Vector<Envelope> apply(Envelope e, Vector s) {
/* 173 */       Envelope envelope = e;
/* 173 */       return (Vector<Envelope>)s.$plus$colon(envelope, Vector$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public StashSupport$$anonfun$prepend$1(StashSupport $outer) {}
/*     */   }
/*     */   
/*     */   public class StashSupport$$anonfun$unstashAll$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Object x$2) {
/* 201 */       return true;
/*     */     }
/*     */     
/*     */     public StashSupport$$anonfun$unstashAll$1(StashSupport $outer) {}
/*     */   }
/*     */   
/*     */   public class StashSupport$$anonfun$1 extends AbstractFunction1<Envelope, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 filterPredicate$1;
/*     */     
/*     */     public final boolean apply(Envelope envelope) {
/* 220 */       return BoxesRunTime.unboxToBoolean(this.filterPredicate$1.apply(envelope.message()));
/*     */     }
/*     */     
/*     */     public StashSupport$$anonfun$1(StashSupport $outer, Function1 filterPredicate$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\StashSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */