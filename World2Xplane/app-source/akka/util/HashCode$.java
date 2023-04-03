/*    */ package akka.util;
/*    */ 
/*    */ import java.lang.reflect.Array;
/*    */ import scala.Function1;
/*    */ import scala.MatchError;
/*    */ import scala.Serializable;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.IntRef;
/*    */ 
/*    */ public final class HashCode$ {
/*    */   public static final HashCode$ MODULE$;
/*    */   
/*    */   private final int SEED;
/*    */   
/*    */   private final int PRIME;
/*    */   
/*    */   private HashCode$() {
/* 25 */     MODULE$ = this;
/* 26 */     this.SEED = 23;
/* 53 */     this.PRIME = 37;
/*    */   }
/*    */   
/*    */   public int SEED() {
/*    */     return this.SEED;
/*    */   }
/*    */   
/*    */   public int hash(int seed, Object any) {
/*    */     Object object = any;
/*    */     if (object instanceof Boolean) {
/*    */       boolean bool = BoxesRunTime.unboxToBoolean(object);
/*    */       int i = hash(seed, bool);
/*    */     } else if (object instanceof Character) {
/*    */       char c = BoxesRunTime.unboxToChar(object);
/*    */       int i = hash(seed, c);
/*    */     } else if (object instanceof Short) {
/*    */       short s = BoxesRunTime.unboxToShort(object);
/*    */       int i = hash(seed, s);
/*    */     } else if (object instanceof Integer) {
/*    */       int j = BoxesRunTime.unboxToInt(object), i = hash(seed, j);
/*    */     } else if (object instanceof Long) {
/*    */       long l = BoxesRunTime.unboxToLong(object);
/*    */       int i = hash(seed, l);
/*    */     } else if (object instanceof Float) {
/*    */       float f = BoxesRunTime.unboxToFloat(object);
/*    */       int i = hash(seed, f);
/*    */     } else if (object instanceof Double) {
/*    */       double d = BoxesRunTime.unboxToDouble(object);
/*    */       int i = hash(seed, d);
/*    */     } else if (object instanceof Byte) {
/*    */       byte b = BoxesRunTime.unboxToByte(object);
/*    */       int i = hash(seed, b);
/*    */     } else {
/*    */       if (object instanceof Object) {
/*    */         Object object1 = object;
/*    */         IntRef result = new IntRef(seed);
/*    */         if (object1 == null) {
/*    */           result.elem = hash(result.elem, 0);
/*    */         } else if (isArray(object1)) {
/*    */           scala.runtime.RichInt$.MODULE$.until$extension0(scala.Predef$.MODULE$.intWrapper(0), Array.getLength(object1)).foreach$mVc$sp((Function1)new HashCode$$anonfun$hash$1(result, object1));
/*    */         } else {
/*    */           result.elem = hash(result.elem, object1.hashCode());
/*    */         } 
/*    */         return result.elem;
/*    */       } 
/*    */       throw new MatchError(object);
/*    */     } 
/*    */     return SYNTHETIC_LOCAL_VARIABLE_5;
/*    */   }
/*    */   
/*    */   public static class HashCode$$anonfun$hash$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final IntRef result$1;
/*    */     
/*    */     private final Object x10$1;
/*    */     
/*    */     public final void apply(int id) {
/*    */       apply$mcVI$sp(id);
/*    */     }
/*    */     
/*    */     public void apply$mcVI$sp(int id) {
/*    */       this.result$1.elem = HashCode$.MODULE$.hash(this.result$1.elem, Array.get(this.x10$1, id));
/*    */     }
/*    */     
/*    */     public HashCode$$anonfun$hash$1(IntRef result$1, Object x10$1) {}
/*    */   }
/*    */   
/*    */   public int hash(int seed, boolean value) {
/*    */     return firstTerm(seed) + (value ? 1 : 0);
/*    */   }
/*    */   
/*    */   public int hash(int seed, char value) {
/*    */     return firstTerm(seed) + value;
/*    */   }
/*    */   
/*    */   public int hash(int seed, int value) {
/*    */     return firstTerm(seed) + value;
/*    */   }
/*    */   
/*    */   public int hash(int seed, long value) {
/*    */     return firstTerm(seed) + (int)(value ^ value >>> 32L);
/*    */   }
/*    */   
/*    */   public int hash(int seed, float value) {
/*    */     return hash(seed, Float.floatToIntBits(value));
/*    */   }
/*    */   
/*    */   public int hash(int seed, double value) {
/*    */     return hash(seed, Double.doubleToLongBits(value));
/*    */   }
/*    */   
/*    */   private int firstTerm(int seed) {
/*    */     return PRIME() * seed;
/*    */   }
/*    */   
/*    */   private boolean isArray(Object anyRef) {
/*    */     return anyRef.getClass().isArray();
/*    */   }
/*    */   
/*    */   private int PRIME() {
/* 53 */     return this.PRIME;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\HashCode$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */