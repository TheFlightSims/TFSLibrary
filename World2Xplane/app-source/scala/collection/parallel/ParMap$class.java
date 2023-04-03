/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Tuple2;
/*    */ import scala.collection.generic.GenericParMapCompanion;
/*    */ import scala.collection.parallel.mutable.ParHashMap;
/*    */ 
/*    */ public abstract class ParMap$class {
/*    */   public static void $init$(ParMap $this) {}
/*    */   
/*    */   public static GenericParMapCompanion mapCompanion(ParMap $this) {
/* 37 */     return (GenericParMapCompanion)ParMap$.MODULE$;
/*    */   }
/*    */   
/*    */   public static ParMap empty(ParMap $this) {
/* 41 */     return (ParMap)new ParHashMap();
/*    */   }
/*    */   
/*    */   public static String stringPrefix(ParMap $this) {
/* 43 */     return "ParMap";
/*    */   }
/*    */   
/*    */   public static ParMap updated(ParMap $this, Object key, Object value) {
/* 45 */     return $this.$plus(new Tuple2(key, value));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParMap$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */