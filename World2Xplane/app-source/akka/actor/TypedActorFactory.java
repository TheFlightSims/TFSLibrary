/*    */ package akka.actor;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicReference;
/*    */ import scala.Function0;
/*    */ import scala.Serializable;
/*    */ import scala.collection.immutable.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00154q!\001\002\021\002\007\005qAA\tUsB,G-Q2u_J4\025m\031;pefT!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\0011C\001\001\t!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fM\")q\002\001C\001!\0051A%\0338ji\022\"\022!\005\t\003\023II!a\005\006\003\tUs\027\016\036\005\006+\0011\tBF\001\rC\016$xN\035$bGR|'/_\013\002/A\021\001$G\007\002\005%\021!D\001\002\020\003\016$xN\035*fM\032\0137\r^8ss\")A\004\001D\t;\005QA/\0379fI\006\033Go\034:\026\003y\001\"\001G\020\n\005\001\022!a\005+za\026$\027i\031;pe\026CH/\0328tS>t\007\"\002\022\001\t\003\031\023\001B:u_B$\"\001J\024\021\005%)\023B\001\024\013\005\035\021un\0347fC:DQ\001K\021A\002!\tQ\001\035:pqfDQA\013\001\005\002-\n!\002]8jg>t\007+\0337m)\t!C\006C\003)S\001\007\001\002C\003/\001\031\005q&\001\007jgRK\b/\0323BGR|'\017\006\002%a!)\021'\fa\001\021\005Q\001O]8ys>\023hj\034;\t\013M\002a\021\001\033\002\035\035,G/Q2u_J\024VM\032$peR\021Q\007\017\t\0031YJ!a\016\002\003\021\005\033Go\034:SK\032DQ\001\013\032A\002!AQA\017\001\005\002m\nA\002^=qK\022\f5\r^8s\037\032,2\001P L)\tiT\t\005\002?1\001A!\002!:\005\004\t%!\001*\022\005\tC\001CA\005D\023\t!%BA\004O_RD\027N\\4\t\013\031K\004\031A$\002\013A\024x\016]:\021\007aA%*\003\002J\005\tQA+\0379fIB\023x\016]:\021\005yZE!\002':\005\004i%!\001+\022\005\tk\004\"\002\036\001\t\003yUc\001)S-R\031\021k\025-\021\005y\022F!\002!O\005\004\t\005\"\002$O\001\004!\006c\001\rI+B\021aH\026\003\006\031:\023\raV\t\003\005FCQ!\027(A\002i\013AA\\1nKB\0211L\030\b\003\023qK!!\030\006\002\rA\023X\rZ3g\023\ty\006M\001\004TiJLgn\032\006\003;*AQA\017\001\005\002\t,2aY3j)\r!gm\033\t\003}\025$Q\001Q1C\002\005CQAR1A\002\035\0042\001\007%i!\tq\024\016B\003MC\n\007!.\005\002CI\")A.\031a\001k\005A\021m\031;peJ+g\r")
/*    */ public interface TypedActorFactory {
/*    */   ActorRefFactory actorFactory();
/*    */   
/*    */   TypedActorExtension typedActor();
/*    */   
/*    */   boolean stop(Object paramObject);
/*    */   
/*    */   boolean poisonPill(Object paramObject);
/*    */   
/*    */   boolean isTypedActor(Object paramObject);
/*    */   
/*    */   ActorRef getActorRefFor(Object paramObject);
/*    */   
/*    */   <R, T extends R> R typedActorOf(TypedProps<T> paramTypedProps);
/*    */   
/*    */   <R, T extends R> R typedActorOf(TypedProps<T> paramTypedProps, String paramString);
/*    */   
/*    */   <R, T extends R> R typedActorOf(TypedProps<T> paramTypedProps, ActorRef paramActorRef);
/*    */   
/*    */   public class TypedActorFactory$$anonfun$1 extends AbstractFunction0<TypedActor.TypedActor<R, T>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final AtomicReference proxyVar$1;
/*    */     
/*    */     private final Function0 c$1;
/*    */     
/*    */     private final Seq i$1;
/*    */     
/*    */     public final TypedActor.TypedActor<R, T> apply() {
/* 78 */       return new TypedActor.TypedActor<R, R>(this.proxyVar$1, this.c$1, this.i$1);
/*    */     }
/*    */     
/*    */     public TypedActorFactory$$anonfun$1(TypedActorFactory $outer, AtomicReference proxyVar$1, Function0 c$1, Seq i$1) {}
/*    */   }
/*    */   
/*    */   public class TypedActorFactory$$anonfun$typedActorOf$1 extends AbstractFunction0<ActorRef> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Props ap$1;
/*    */     
/*    */     public final ActorRef apply() {
/* 79 */       return this.$outer.actorFactory().actorOf(this.ap$1);
/*    */     }
/*    */     
/*    */     public TypedActorFactory$$anonfun$typedActorOf$1(TypedActorFactory $outer, Props ap$1) {}
/*    */   }
/*    */   
/*    */   public class TypedActorFactory$$anonfun$2 extends AbstractFunction0<TypedActor.TypedActor<R, T>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final AtomicReference proxyVar$2;
/*    */     
/*    */     private final Function0 c$2;
/*    */     
/*    */     private final Seq i$2;
/*    */     
/*    */     public final TypedActor.TypedActor<R, T> apply() {
/* 89 */       return new TypedActor.TypedActor<R, R>(this.proxyVar$2, this.c$2, this.i$2);
/*    */     }
/*    */     
/*    */     public TypedActorFactory$$anonfun$2(TypedActorFactory $outer, AtomicReference proxyVar$2, Function0 c$2, Seq i$2) {}
/*    */   }
/*    */   
/*    */   public class TypedActorFactory$$anonfun$typedActorOf$2 extends AbstractFunction0<ActorRef> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Props ap$2;
/*    */     
/*    */     private final String name$1;
/*    */     
/*    */     public final ActorRef apply() {
/* 90 */       return this.$outer.actorFactory().actorOf(this.ap$2, this.name$1);
/*    */     }
/*    */     
/*    */     public TypedActorFactory$$anonfun$typedActorOf$2(TypedActorFactory $outer, Props ap$2, String name$1) {}
/*    */   }
/*    */   
/*    */   public class TypedActorFactory$$anonfun$typedActorOf$3 extends AbstractFunction0<ActorRef> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final ActorRef actorRef$1;
/*    */     
/*    */     public final ActorRef apply() {
/* 98 */       return this.actorRef$1;
/*    */     }
/*    */     
/*    */     public TypedActorFactory$$anonfun$typedActorOf$3(TypedActorFactory $outer, ActorRef actorRef$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\TypedActorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */