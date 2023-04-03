/*    */ package scala;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.mutable.ArraySeq;
/*    */ import scala.collection.mutable.ArraySeq$;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001m2A!\001\002\001\013\t)b)\0317mE\006\0347.\021:sCf\024U/\0337eS:<'\"A\002\002\013M\034\027\r\\1\004\001M\021\001A\002\t\003\017!i\021AA\005\003\023\t\021a!\0218z%\0264\007\"B\006\001\t\003a\021A\002\037j]&$h\bF\001\016!\t9\001\001C\003\020\001\021\r\001#\001\013gC2d'-Y2l\007\006t')^5mI\032\023x.\\\013\003#)\"\"A\005\032\021\013MA\"$\013\027\016\003QQ!!\006\f\002\017\035,g.\032:jG*\021qCA\001\013G>dG.Z2uS>t\027BA\r\025\0051\031\025M\034\"vS2$gI]8na\tY\002\005E\002\b9yI!!\b\002\003\013\005\023(/Y=\021\005}\001C\002\001\003\nC9\t\t\021!A\003\002\t\0221a\030\0232#\t\031c\005\005\002\bI%\021QE\001\002\b\035>$\b.\0338h!\t9q%\003\002)\005\t\031\021I\\=\021\005}QC!B\026\017\005\004\021#!\001+\021\0075\002\024&D\001/\025\tyc#A\004nkR\f'\r\\3\n\005Er#\001C!se\006L8+Z9\t\013Mr\0019\001\033\002\0035\004\"!\016\035\017\005\0351\024BA\034\003\003\031\001&/\0323fM&\021\021H\017\002\016\tVlW._%na2L7-\033;\013\005]\022\001")
/*    */ public class FallbackArrayBuilding {
/*    */   public <T> CanBuildFrom<Object, T, ArraySeq<T>> fallbackCanBuildFrom(Predef.DummyImplicit m) {
/* 31 */     return new FallbackArrayBuilding$$anon$1(this);
/*    */   }
/*    */   
/*    */   public class FallbackArrayBuilding$$anon$1 implements CanBuildFrom<Object, T, ArraySeq<T>> {
/*    */     public FallbackArrayBuilding$$anon$1(FallbackArrayBuilding $outer) {}
/*    */     
/*    */     public Builder<T, ArraySeq<T>> apply(Object from) {
/* 32 */       return ArraySeq$.MODULE$.newBuilder();
/*    */     }
/*    */     
/*    */     public Builder<T, ArraySeq<T>> apply() {
/* 33 */       return ArraySeq$.MODULE$.newBuilder();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\FallbackArrayBuilding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */