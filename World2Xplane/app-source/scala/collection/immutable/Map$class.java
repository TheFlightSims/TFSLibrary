/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ 
/*    */ public abstract class Map$class {
/*    */   public static void $init$(Map $this) {}
/*    */   
/*    */   public static Map empty(Map $this) {
/* 33 */     return Map$.MODULE$.empty();
/*    */   }
/*    */   
/*    */   public static Map toMap(Map $this, Predef$.less.colon.less ev) {
/* 35 */     return $this;
/*    */   }
/*    */   
/*    */   public static Map seq(Map $this) {
/* 37 */     return $this;
/*    */   }
/*    */   
/*    */   public static Map withDefault(Map<?, ?> $this, Function1<?, ?> d) {
/* 47 */     return new Map.WithDefault<Object, Object>($this, d);
/*    */   }
/*    */   
/*    */   public static Map withDefaultValue(Map<?, ?> $this, Object d) {
/* 57 */     return new Map.WithDefault<Object, Object>($this, (Function1<?, ?>)new Map$$anonfun$withDefaultValue$1($this, (Map<A, B>)d));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Map$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */