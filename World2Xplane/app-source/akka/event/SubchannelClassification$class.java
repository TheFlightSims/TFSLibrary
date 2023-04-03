/*     */ package akka.event;
/*     */ 
/*     */ import akka.util.SubclassifiedIndex;
/*     */ import scala.Function2;
/*     */ import scala.Predef$;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class SubchannelClassification$class {
/*     */   public static SubclassifiedIndex akka$event$SubchannelClassification$$subscriptions(SubchannelClassification $this) {
/* 131 */     return new SubclassifiedIndex($this.subclassification());
/*     */   }
/*     */   
/*     */   public static void $init$(SubchannelClassification $this) {
/* 134 */     $this.akka$event$SubchannelClassification$$cache_$eq(Predef$.MODULE$.Map().empty());
/*     */   }
/*     */   
/*     */   public static boolean subscribe(SubchannelClassification $this, Object subscriber, Object to) {
/* 146 */     synchronized ($this.akka$event$SubchannelClassification$$subscriptions()) {
/* 147 */       Seq diff = $this.akka$event$SubchannelClassification$$subscriptions().addValue(to, subscriber);
/* 148 */       addToCache($this, diff);
/* 149 */       Boolean bool = BoxesRunTime.boxToBoolean(diff.nonEmpty());
/*     */       return BoxesRunTime.unboxToBoolean(bool);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean unsubscribe(SubchannelClassification $this, Object subscriber, Object from) {
/* 152 */     synchronized ($this.akka$event$SubchannelClassification$$subscriptions()) {
/* 153 */       Seq diff = $this.akka$event$SubchannelClassification$$subscriptions().removeValue(from, subscriber);
/* 156 */       $this.akka$event$SubchannelClassification$$cache_$eq($this.akka$event$SubchannelClassification$$cache().$plus$plus((GenTraversableOnce)diff));
/* 157 */       Boolean bool = BoxesRunTime.boxToBoolean(diff.nonEmpty());
/*     */       return BoxesRunTime.unboxToBoolean(bool);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void unsubscribe(SubchannelClassification $this, Object subscriber) {
/* 160 */     synchronized ($this.akka$event$SubchannelClassification$$subscriptions()) {
/* 161 */       removeFromCache($this, $this.akka$event$SubchannelClassification$$subscriptions().removeValue(subscriber));
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void publish(SubchannelClassification $this, Object event) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: invokeinterface classify : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   7: astore_2
/*     */     //   8: aload_0
/*     */     //   9: invokeinterface akka$event$SubchannelClassification$$cache : ()Lscala/collection/immutable/Map;
/*     */     //   14: aload_2
/*     */     //   15: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   20: ifeq -> 41
/*     */     //   23: aload_0
/*     */     //   24: invokeinterface akka$event$SubchannelClassification$$cache : ()Lscala/collection/immutable/Map;
/*     */     //   29: aload_2
/*     */     //   30: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   35: checkcast scala/collection/immutable/Set
/*     */     //   38: goto -> 117
/*     */     //   41: aload_0
/*     */     //   42: invokeinterface akka$event$SubchannelClassification$$subscriptions : ()Lakka/util/SubclassifiedIndex;
/*     */     //   47: dup
/*     */     //   48: astore #4
/*     */     //   50: monitorenter
/*     */     //   51: aload_0
/*     */     //   52: invokeinterface akka$event$SubchannelClassification$$cache : ()Lscala/collection/immutable/Map;
/*     */     //   57: aload_2
/*     */     //   58: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   63: ifeq -> 81
/*     */     //   66: aload_0
/*     */     //   67: invokeinterface akka$event$SubchannelClassification$$cache : ()Lscala/collection/immutable/Map;
/*     */     //   72: aload_2
/*     */     //   73: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   78: goto -> 107
/*     */     //   81: aload_0
/*     */     //   82: aload_0
/*     */     //   83: invokeinterface akka$event$SubchannelClassification$$subscriptions : ()Lakka/util/SubclassifiedIndex;
/*     */     //   88: aload_2
/*     */     //   89: invokevirtual addKey : (Ljava/lang/Object;)Lscala/collection/immutable/Seq;
/*     */     //   92: invokestatic addToCache : (Lakka/event/SubchannelClassification;Lscala/collection/immutable/Seq;)V
/*     */     //   95: aload_0
/*     */     //   96: invokeinterface akka$event$SubchannelClassification$$cache : ()Lscala/collection/immutable/Map;
/*     */     //   101: aload_2
/*     */     //   102: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   107: astore #5
/*     */     //   109: aload #4
/*     */     //   111: monitorexit
/*     */     //   112: aload #5
/*     */     //   114: checkcast scala/collection/immutable/Set
/*     */     //   117: astore_3
/*     */     //   118: aload_3
/*     */     //   119: new akka/event/SubchannelClassification$$anonfun$publish$1
/*     */     //   122: dup
/*     */     //   123: aload_0
/*     */     //   124: aload_1
/*     */     //   125: invokespecial <init> : (Lakka/event/SubchannelClassification;Ljava/lang/Object;)V
/*     */     //   128: invokeinterface foreach : (Lscala/Function1;)V
/*     */     //   133: return
/*     */     //   134: aload #4
/*     */     //   136: monitorexit
/*     */     //   137: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #165	-> 0
/*     */     //   #167	-> 8
/*     */     //   #168	-> 41
/*     */     //   #169	-> 51
/*     */     //   #171	-> 81
/*     */     //   #172	-> 95
/*     */     //   #168	-> 111
/*     */     //   #166	-> 117
/*     */     //   #175	-> 118
/*     */     //   #168	-> 134
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	138	0	$this	Lakka/event/SubchannelClassification;
/*     */     //   0	138	1	event	Ljava/lang/Object;
/*     */     //   8	125	2	c	Ljava/lang/Object;
/*     */     //   118	15	3	recv	Lscala/collection/immutable/Set;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   51	112	134	finally
/*     */   }
/*     */   
/*     */   private static void removeFromCache(SubchannelClassification $this, Seq changes) {
/* 179 */     Map<Object, Set<Object>> map = $this.akka$event$SubchannelClassification$$cache();
/* 179 */     $this.akka$event$SubchannelClassification$$cache_$eq((Map<Object, Set<Object>>)changes.$div$colon(map, (Function2)new SubchannelClassification$$anonfun$removeFromCache$1($this)));
/*     */   }
/*     */   
/*     */   private static void addToCache(SubchannelClassification $this, Seq changes) {
/* 184 */     Map<Object, Set<Object>> map = $this.akka$event$SubchannelClassification$$cache();
/* 184 */     $this.akka$event$SubchannelClassification$$cache_$eq((Map<Object, Set<Object>>)changes.$div$colon(map, (Function2)new SubchannelClassification$$anonfun$addToCache$1($this)));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\SubchannelClassification$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */