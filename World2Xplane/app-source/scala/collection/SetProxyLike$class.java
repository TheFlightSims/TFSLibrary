/*    */ package scala.collection;
/*    */ 
/*    */ public abstract class SetProxyLike$class {
/*    */   public static void $init$(SetProxyLike $this) {}
/*    */   
/*    */   public static boolean contains(SetProxyLike $this, Object elem) {
/* 24 */     return ((SetLike)$this.self()).contains(elem);
/*    */   }
/*    */   
/*    */   public static Set $plus(SetProxyLike $this, Object elem) {
/* 25 */     return ((SetLike<Object, Set>)$this.self()).$plus(elem);
/*    */   }
/*    */   
/*    */   public static Set $minus(SetProxyLike $this, Object elem) {
/* 26 */     return ((SetLike<Object, Set>)$this.self()).$minus(elem);
/*    */   }
/*    */   
/*    */   public static boolean isEmpty(SetProxyLike $this) {
/* 27 */     return ((SetLike)$this.self()).isEmpty();
/*    */   }
/*    */   
/*    */   public static boolean apply(SetProxyLike $this, Object elem) {
/* 28 */     return ((GenSetLike)$this.self()).apply(elem);
/*    */   }
/*    */   
/*    */   public static Set intersect(SetProxyLike $this, GenSet that) {
/* 29 */     return (Set)((GenSetLike)$this.self()).intersect(that);
/*    */   }
/*    */   
/*    */   public static Set $amp(SetProxyLike $this, GenSet that) {
/* 30 */     return (Set)((GenSetLike)$this.self()).$amp(that);
/*    */   }
/*    */   
/*    */   public static Set union(SetProxyLike $this, GenSet that) {
/* 31 */     return (Set)((SetLike)$this.self()).union(that);
/*    */   }
/*    */   
/*    */   public static Set $bar(SetProxyLike $this, GenSet that) {
/* 32 */     return (Set)((GenSetLike)$this.self()).$bar(that);
/*    */   }
/*    */   
/*    */   public static Set diff(SetProxyLike $this, GenSet that) {
/* 33 */     return (Set)((SetLike)$this.self()).diff(that);
/*    */   }
/*    */   
/*    */   public static Set $amp$tilde(SetProxyLike $this, GenSet that) {
/* 34 */     return (Set)((GenSetLike)$this.self()).$amp$tilde(that);
/*    */   }
/*    */   
/*    */   public static boolean subsetOf(SetProxyLike $this, GenSet that) {
/* 35 */     return ((GenSetLike)$this.self()).subsetOf(that);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SetProxyLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */