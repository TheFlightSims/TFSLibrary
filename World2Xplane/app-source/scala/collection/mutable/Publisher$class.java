/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ 
/*    */ public abstract class Publisher$class {
/*    */   public static void $init$(Publisher $this) {
/* 38 */     $this.scala$collection$mutable$Publisher$_setter_$self_$eq($this);
/* 40 */     $this.scala$collection$mutable$Publisher$_setter_$scala$collection$mutable$Publisher$$filters_$eq(new Publisher.$anon$1((Publisher$class)$this));
/* 41 */     $this.scala$collection$mutable$Publisher$_setter_$scala$collection$mutable$Publisher$$suspended_$eq(new HashSet());
/*    */   }
/*    */   
/*    */   public static void subscribe(Publisher<Evt> $this, Subscriber sub) {
/* 43 */     $this.subscribe(sub, (Function1)new Publisher$$anonfun$subscribe$1($this));
/*    */   }
/*    */   
/*    */   public static void subscribe(Publisher $this, Subscriber sub, Function1 filter) {
/* 44 */     ((MultiMap)$this.scala$collection$mutable$Publisher$$filters()).addBinding(sub, filter);
/*    */   }
/*    */   
/*    */   public static void suspendSubscription(Publisher $this, Subscriber sub) {
/* 45 */     $this.scala$collection$mutable$Publisher$$suspended().$plus$eq(sub);
/*    */   }
/*    */   
/*    */   public static void activateSubscription(Publisher $this, Subscriber sub) {
/* 46 */     $this.scala$collection$mutable$Publisher$$suspended().$minus$eq(sub);
/*    */   }
/*    */   
/*    */   public static void removeSubscription(Publisher $this, Subscriber sub) {
/* 47 */     $this.scala$collection$mutable$Publisher$$filters().$minus$eq(sub);
/*    */   }
/*    */   
/*    */   public static void removeSubscriptions(Publisher $this) {
/* 48 */     $this.scala$collection$mutable$Publisher$$filters().clear();
/*    */   }
/*    */   
/*    */   public static void publish(Publisher $this, Object event) {
/* 51 */     $this.scala$collection$mutable$Publisher$$filters().keys().foreach((Function1)new Publisher$$anonfun$publish$1($this, (Publisher<Evt>)event));
/*    */   }
/*    */   
/*    */   public static boolean equals(Publisher $this, Object obj) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: instanceof scala/collection/mutable/Publisher
/*    */     //   4: ifeq -> 89
/*    */     //   7: aload_1
/*    */     //   8: checkcast scala/collection/mutable/Publisher
/*    */     //   11: astore_3
/*    */     //   12: aload_0
/*    */     //   13: invokeinterface scala$collection$mutable$Publisher$$filters : ()Lscala/collection/mutable/HashMap;
/*    */     //   18: aload_3
/*    */     //   19: invokeinterface scala$collection$mutable$Publisher$$filters : ()Lscala/collection/mutable/HashMap;
/*    */     //   24: astore_2
/*    */     //   25: dup
/*    */     //   26: ifnonnull -> 37
/*    */     //   29: pop
/*    */     //   30: aload_2
/*    */     //   31: ifnull -> 44
/*    */     //   34: goto -> 83
/*    */     //   37: aload_2
/*    */     //   38: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   41: ifeq -> 83
/*    */     //   44: aload_0
/*    */     //   45: invokeinterface scala$collection$mutable$Publisher$$suspended : ()Lscala/collection/mutable/HashSet;
/*    */     //   50: aload_3
/*    */     //   51: invokeinterface scala$collection$mutable$Publisher$$suspended : ()Lscala/collection/mutable/HashSet;
/*    */     //   56: astore #4
/*    */     //   58: dup
/*    */     //   59: ifnonnull -> 71
/*    */     //   62: pop
/*    */     //   63: aload #4
/*    */     //   65: ifnull -> 79
/*    */     //   68: goto -> 83
/*    */     //   71: aload #4
/*    */     //   73: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   76: ifeq -> 83
/*    */     //   79: iconst_1
/*    */     //   80: goto -> 84
/*    */     //   83: iconst_0
/*    */     //   84: istore #5
/*    */     //   86: goto -> 92
/*    */     //   89: iconst_0
/*    */     //   90: istore #5
/*    */     //   92: iload #5
/*    */     //   94: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #63	-> 0
/*    */     //   #62	-> 0
/*    */     //   #64	-> 89
/*    */     //   #62	-> 92
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	95	0	$this	Lscala/collection/mutable/Publisher;
/*    */     //   0	95	1	obj	Ljava/lang/Object;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Publisher$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */