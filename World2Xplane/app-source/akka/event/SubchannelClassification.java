/*     */ package akka.event;
/*     */ 
/*     */ import akka.util.Subclassification;
/*     */ import akka.util.SubclassifiedIndex;
/*     */ import scala.Function0;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.SetLike;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025a\001C\001\003!\003\r\ta\002?\0031M+(m\0315b]:,Gn\0217bgNLg-[2bi&|gN\003\002\004\t\005)QM^3oi*\tQ!\001\003bW.\f7\001A\n\003\001!\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007\"B\b\001\t\003\001\022A\002\023j]&$H\005F\001\022!\tI!#\003\002\024\025\t!QK\\5u\021\025)\002Ab\005\027\003E\031XOY2mCN\034\030NZ5dCRLwN\\\013\002/A\031\001dG\017\016\003eQ!A\007\003\002\tU$\030\016\\\005\0039e\021\021cU;cG2\f7o]5gS\016\fG/[8o!\tqr$D\001\001\023\t\001\023E\001\006DY\006\0348/\0334jKJL!A\t\002\003\021\0253XM\034;CkND\001\002\n\001\t\006\004%I!J\001\016gV\0247o\031:jaRLwN\\:\026\003\031\002B\001G\024\036S%\021\001&\007\002\023'V\0247\r\\1tg&4\027.\0323J]\022,\007\020\005\002\037U%\0211&\t\002\013'V\0247o\031:jE\026\024\b\002C\027\001\021\003\005\013\025\002\024\002\035M,(m]2sSB$\030n\0348tA!9q\006\001a\001\n\023\001\024!B2bG\",W#A\031\021\tI:T$O\007\002g)\021A'N\001\nS6lW\017^1cY\026T!A\016\006\002\025\r|G\016\\3di&|g.\003\0029g\t\031Q*\0319\021\007ij\024F\004\002\nw%\021AHC\001\007!J,G-\0324\n\005yz$aA*fi*\021AH\003\005\b\003\002\001\r\021\"\003C\003%\031\027m\0315f?\022*\027\017\006\002\022\007\"9A\tQA\001\002\004\t\024a\001=%c!1a\t\001Q!\nE\naaY1dQ\026\004\003FA#I!\tI\021*\003\002K\025\tAao\0347bi&dW\rC\003M\001\031EQ*\001\005dY\006\0348/\0334z)\tib\nC\003\004\027\002\007q\n\005\002\037!&\021\021+\t\002\006\013Z,g\016\036\005\006'\0021\t\002V\001\baV\024G.[:i)\r\tRK\026\005\006\007I\003\ra\024\005\006/J\003\r!K\001\013gV\0247o\031:jE\026\024\b\"B-\001\t\003Q\026!C:vEN\034'/\0332f)\rYfl\030\t\003\023qK!!\030\006\003\017\t{w\016\\3b]\")q\013\027a\001S!)\001\r\027a\001;\005\021Ao\034\005\006E\002!\taY\001\fk:\034XOY:de&\024W\rF\002\\I\026DQaV1A\002%BQAZ1A\002u\tAA\032:p[\")!\r\001C\001QR\021\021#\033\005\006/\036\004\r!\013\005\006'\002!\ta\033\013\003#1DQa\0016A\002=CQA\034\001\005\n=\fqB]3n_Z,gI]8n\007\006\034\007.\032\013\003#ADQ!]7A\002I\fqa\0315b]\036,7\017E\0023gVL!\001^\032\003\007M+\027\017\005\003\nmvI\024BA<\013\005\031!V\017\0357fe!)\021\020\001C\005u\006Q\021\r\0323U_\016\0137\r[3\025\005EY\b\"B9y\001\004\021(\003B?\000\003\0071AA \001\001y\naAH]3gS:,W.\0328u}A\031\021\021\001\001\016\003\t\0012!!\001\"\001")
/*     */ public interface SubchannelClassification {
/*     */   Subclassification<Object> subclassification();
/*     */   
/*     */   SubclassifiedIndex<Object, Object> akka$event$SubchannelClassification$$subscriptions();
/*     */   
/*     */   Map<Object, Set<Object>> akka$event$SubchannelClassification$$cache();
/*     */   
/*     */   @TraitSetter
/*     */   void akka$event$SubchannelClassification$$cache_$eq(Map<Object, Set<Object>> paramMap);
/*     */   
/*     */   Object classify(Object paramObject);
/*     */   
/*     */   void publish(Object paramObject1, Object paramObject2);
/*     */   
/*     */   boolean subscribe(Object paramObject1, Object paramObject2);
/*     */   
/*     */   boolean unsubscribe(Object paramObject1, Object paramObject2);
/*     */   
/*     */   void unsubscribe(Object paramObject);
/*     */   
/*     */   void publish(Object paramObject);
/*     */   
/*     */   public class SubchannelClassification$$anonfun$publish$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object event$1;
/*     */     
/*     */     public final void apply(Object x$1) {
/* 175 */       this.$outer.publish(this.event$1, x$1);
/*     */     }
/*     */     
/*     */     public SubchannelClassification$$anonfun$publish$1(SubchannelClassification $outer, Object event$1) {}
/*     */   }
/*     */   
/*     */   public class SubchannelClassification$$anonfun$removeFromCache$1 extends AbstractFunction2<Map<Object, Set<Object>>, Tuple2<Object, Set<Object>>, Map<Object, Set<Object>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Map<Object, Set<Object>> apply(Map x0$1, Tuple2 x1$1) {
/* 179 */       Tuple2 tuple2 = new Tuple2(x0$1, x1$1);
/* 179 */       if (tuple2 != null) {
/* 180 */         Map m = (Map)tuple2._1();
/* 180 */         Tuple2 tuple21 = (Tuple2)tuple2._2();
/*     */         if (tuple21 != null) {
/* 180 */           Object c = tuple21._1();
/* 180 */           Set cs = (Set)tuple21._2();
/* 180 */           return m.updated(c, ((Subtractable)m.getOrElse(c, (Function0)new SubchannelClassification$$anonfun$removeFromCache$1$$anonfun$apply$1(this))).$minus$minus((GenTraversableOnce)cs));
/*     */         } 
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public SubchannelClassification$$anonfun$removeFromCache$1(SubchannelClassification $outer) {}
/*     */     
/*     */     public class SubchannelClassification$$anonfun$removeFromCache$1$$anonfun$apply$1 extends AbstractFunction0<Set<Object>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Set<Object> apply() {
/* 180 */         return Predef$.MODULE$.Set().empty();
/*     */       }
/*     */       
/*     */       public SubchannelClassification$$anonfun$removeFromCache$1$$anonfun$apply$1(SubchannelClassification$$anonfun$removeFromCache$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class SubchannelClassification$$anonfun$addToCache$1 extends AbstractFunction2<Map<Object, Set<Object>>, Tuple2<Object, Set<Object>>, Map<Object, Set<Object>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Map<Object, Set<Object>> apply(Map x0$2, Tuple2 x1$2) {
/* 184 */       Tuple2 tuple2 = new Tuple2(x0$2, x1$2);
/* 184 */       if (tuple2 != null) {
/* 185 */         Map m = (Map)tuple2._1();
/* 185 */         Tuple2 tuple21 = (Tuple2)tuple2._2();
/*     */         if (tuple21 != null) {
/* 185 */           Object c = tuple21._1();
/* 185 */           Set cs = (Set)tuple21._2();
/* 185 */           return m.updated(c, ((SetLike)m.getOrElse(c, (Function0)new SubchannelClassification$$anonfun$addToCache$1$$anonfun$apply$2(this))).$plus$plus((GenTraversableOnce)cs));
/*     */         } 
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public SubchannelClassification$$anonfun$addToCache$1(SubchannelClassification $outer) {}
/*     */     
/*     */     public class SubchannelClassification$$anonfun$addToCache$1$$anonfun$apply$2 extends AbstractFunction0<Set<Object>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Set<Object> apply() {
/* 185 */         return Predef$.MODULE$.Set().empty();
/*     */       }
/*     */       
/*     */       public SubchannelClassification$$anonfun$addToCache$1$$anonfun$apply$2(SubchannelClassification$$anonfun$addToCache$1 $outer) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\SubchannelClassification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */