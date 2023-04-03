/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Serializable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class WrappedArray$ {
/*     */   public static final WrappedArray$ MODULE$;
/*     */   
/*     */   private final WrappedArray.ofRef<Object> EmptyWrappedArray;
/*     */   
/*     */   private WrappedArray$() {
/*  89 */     MODULE$ = this;
/*  91 */     this.EmptyWrappedArray = new WrappedArray.ofRef(new Object[0]);
/*     */   }
/*     */   
/*     */   private WrappedArray.ofRef<Object> EmptyWrappedArray() {
/*  91 */     return this.EmptyWrappedArray;
/*     */   }
/*     */   
/*     */   public <T> WrappedArray<T> empty() {
/*  92 */     return EmptyWrappedArray();
/*     */   }
/*     */   
/*     */   public <T> WrappedArray<T> make(Object x) {
/*  99 */     if (x == null) {
/*  99 */       Object object = null;
/* 101 */     } else if (x instanceof Object[]) {
/* 101 */       Object[] arrayOfObject = (Object[])x;
/* 101 */       WrappedArray.ofRef ofRef1 = new WrappedArray.ofRef(arrayOfObject);
/* 102 */     } else if (x instanceof int[]) {
/* 102 */       int[] arrayOfInt = (int[])x;
/* 102 */       WrappedArray.ofInt ofInt = new WrappedArray.ofInt(arrayOfInt);
/* 103 */     } else if (x instanceof double[]) {
/* 103 */       double[] arrayOfDouble = (double[])x;
/* 103 */       WrappedArray.ofDouble ofDouble = new WrappedArray.ofDouble(arrayOfDouble);
/* 104 */     } else if (x instanceof long[]) {
/* 104 */       long[] arrayOfLong = (long[])x;
/* 104 */       WrappedArray.ofLong ofLong = new WrappedArray.ofLong(arrayOfLong);
/* 105 */     } else if (x instanceof float[]) {
/* 105 */       float[] arrayOfFloat = (float[])x;
/* 105 */       WrappedArray.ofFloat ofFloat = new WrappedArray.ofFloat(arrayOfFloat);
/* 106 */     } else if (x instanceof char[]) {
/* 106 */       char[] arrayOfChar = (char[])x;
/* 106 */       WrappedArray.ofChar ofChar = new WrappedArray.ofChar(arrayOfChar);
/* 107 */     } else if (x instanceof byte[]) {
/* 107 */       byte[] arrayOfByte = (byte[])x;
/* 107 */       WrappedArray.ofByte ofByte = new WrappedArray.ofByte(arrayOfByte);
/* 108 */     } else if (x instanceof short[]) {
/* 108 */       short[] arrayOfShort = (short[])x;
/* 108 */       WrappedArray.ofShort ofShort = new WrappedArray.ofShort(arrayOfShort);
/* 109 */     } else if (x instanceof boolean[]) {
/* 109 */       boolean[] arrayOfBoolean = (boolean[])x;
/* 109 */       WrappedArray.ofBoolean ofBoolean = new WrappedArray.ofBoolean(arrayOfBoolean);
/*     */     } else {
/* 110 */       if (x instanceof BoxedUnit[]) {
/* 110 */         BoxedUnit[] arrayOfBoxedUnit = (BoxedUnit[])x;
/* 110 */         return new WrappedArray.ofUnit(arrayOfBoxedUnit);
/*     */       } 
/*     */       throw new MatchError(x);
/*     */     } 
/*     */     return (WrappedArray<T>)SYNTHETIC_LOCAL_VARIABLE_12;
/*     */   }
/*     */   
/*     */   public <T> CanBuildFrom<WrappedArray<?>, T, WrappedArray<T>> canBuildFrom(ClassTag m) {
/* 114 */     return new WrappedArray$$anon$1(m);
/*     */   }
/*     */   
/*     */   public static class WrappedArray$$anon$1 implements CanBuildFrom<WrappedArray<?>, T, WrappedArray<T>> {
/*     */     private final ClassTag m$1;
/*     */     
/*     */     public WrappedArray$$anon$1(ClassTag m$1) {}
/*     */     
/*     */     public Builder<T, WrappedArray<T>> apply(WrappedArray from) {
/* 116 */       return ArrayBuilder$.MODULE$.<T>make(this.m$1).mapResult((Function1<Object, WrappedArray<T>>)new WrappedArray$$anon$1$$anonfun$apply$1(this));
/*     */     }
/*     */     
/*     */     public class WrappedArray$$anon$1$$anonfun$apply$1 extends AbstractFunction1<Object, WrappedArray<T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final WrappedArray<T> apply(Object x) {
/* 116 */         return WrappedArray$.MODULE$.make(x);
/*     */       }
/*     */       
/*     */       public WrappedArray$$anon$1$$anonfun$apply$1(WrappedArray$$anon$1 $outer) {}
/*     */     }
/*     */     
/*     */     public Builder<T, WrappedArray<T>> apply() {
/* 118 */       return ArrayBuilder$.MODULE$.<T>make(this.m$1).mapResult((Function1<Object, WrappedArray<T>>)new WrappedArray$$anon$1$$anonfun$apply$2(this));
/*     */     }
/*     */     
/*     */     public class WrappedArray$$anon$1$$anonfun$apply$2 extends AbstractFunction1<Object, WrappedArray<T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final WrappedArray<T> apply(Object x) {
/* 118 */         return WrappedArray$.MODULE$.make(x);
/*     */       }
/*     */       
/*     */       public WrappedArray$$anon$1$$anonfun$apply$2(WrappedArray$$anon$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public <A> Builder<A, IndexedSeq<A>> newBuilder() {
/* 121 */     return (Builder)new ArrayBuffer<A>();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\WrappedArray$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */