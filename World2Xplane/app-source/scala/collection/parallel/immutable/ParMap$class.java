/*    */ package scala.collection.parallel.immutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.generic.GenericParMapCompanion;
/*    */ 
/*    */ public abstract class ParMap$class {
/*    */   public static void $init$(ParMap $this) {}
/*    */   
/*    */   public static GenericParMapCompanion mapCompanion(ParMap $this) {
/* 39 */     return (GenericParMapCompanion)ParMap$.MODULE$;
/*    */   }
/*    */   
/*    */   public static ParMap empty(ParMap $this) {
/* 41 */     return new ParHashMap<Object, Object>();
/*    */   }
/*    */   
/*    */   public static String stringPrefix(ParMap $this) {
/* 43 */     return "ParMap";
/*    */   }
/*    */   
/*    */   public static ParMap toMap(ParMap $this, Predef$.less.colon.less ev) {
/* 45 */     return $this;
/*    */   }
/*    */   
/*    */   public static ParMap updated(ParMap $this, Object key, Object value) {
/* 47 */     return $this.$plus(new Tuple2(key, value));
/*    */   }
/*    */   
/*    */   public static ParMap withDefault(ParMap<?, ?> $this, Function1<?, ?> d) {
/* 59 */     return new ParMap.WithDefault<Object, Object>($this, d);
/*    */   }
/*    */   
/*    */   public static ParMap withDefaultValue(ParMap<?, ?> $this, Object d) {
/* 68 */     return new ParMap.WithDefault<Object, Object>($this, (Function1<?, ?>)new ParMap$$anonfun$withDefaultValue$1($this, (ParMap<K, V>)d));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParMap$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */