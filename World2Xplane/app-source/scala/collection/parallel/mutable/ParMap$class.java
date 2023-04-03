/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.generic.GenericParMapCompanion;
/*    */ import scala.collection.parallel.Combiner;
/*    */ 
/*    */ public abstract class ParMap$class {
/*    */   public static void $init$(ParMap $this) {}
/*    */   
/*    */   public static Combiner newCombiner(ParMap $this) {
/* 38 */     return ParMap$.MODULE$.newCombiner();
/*    */   }
/*    */   
/*    */   public static GenericParMapCompanion mapCompanion(ParMap $this) {
/* 40 */     return (GenericParMapCompanion)ParMap$.MODULE$;
/*    */   }
/*    */   
/*    */   public static ParMap empty(ParMap $this) {
/* 42 */     return new ParHashMap<Object, Object>();
/*    */   }
/*    */   
/*    */   public static ParMap updated(ParMap $this, Object key, Object value) {
/* 46 */     return $this.$plus(new Tuple2(key, value));
/*    */   }
/*    */   
/*    */   public static ParMap withDefault(ParMap<?, ?> $this, Function1<?, ?> d) {
/* 56 */     return new ParMap.WithDefault<Object, Object>($this, d);
/*    */   }
/*    */   
/*    */   public static ParMap withDefaultValue(ParMap<?, ?> $this, Object d) {
/* 65 */     return new ParMap.WithDefault<Object, Object>($this, (Function1<?, ?>)new ParMap$$anonfun$withDefaultValue$1($this, (ParMap<K, V>)d));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParMap$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */