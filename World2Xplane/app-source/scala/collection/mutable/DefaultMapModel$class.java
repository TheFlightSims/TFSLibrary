/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ 
/*    */ public abstract class DefaultMapModel$class {
/*    */   public static void $init$(DefaultMapModel $this) {}
/*    */   
/*    */   public static Option get(DefaultMapModel $this, Object key) {
/* 30 */     DefaultEntry e = $this.findEntry(key);
/* 31 */     return (e == null) ? (Option)None$.MODULE$ : 
/* 32 */       (Option)new Some(e.value());
/*    */   }
/*    */   
/*    */   public static Option put(DefaultMapModel $this, Object key, Object value) {
/* 36 */     DefaultEntry e = $this.findEntry(key);
/* 37 */     $this.addEntry(new DefaultEntry<Object, Object>(key, value));
/* 38 */     Object v = e.value();
/* 38 */     e.value_$eq(value);
/* 38 */     return (e == null) ? (Option)None$.MODULE$ : (Option)new Some(v);
/*    */   }
/*    */   
/*    */   public static DefaultMapModel $plus$eq(DefaultMapModel<Object, Object> $this, Tuple2 kv) {
/* 41 */     $this.put(kv._1(), kv._2());
/* 41 */     return $this;
/*    */   }
/*    */   
/*    */   public static Iterator iterator(DefaultMapModel<A, B> $this) {
/* 43 */     return $this.entries().map((Function1)new DefaultMapModel$$anonfun$iterator$1($this));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\DefaultMapModel$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */