/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Set;
/*    */ 
/*    */ public abstract class SynchronizedMap$class {
/*    */   public static void $init$(SynchronizedMap $this) {}
/*    */   
/*    */   public static Option get(SynchronizedMap $this, Object key) {
/* 30 */     synchronized ($this) {
/* 30 */       Option option = $this.scala$collection$mutable$SynchronizedMap$$super$get(key);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 30 */       return option;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Iterator iterator(SynchronizedMap $this) {
/* 31 */     synchronized ($this) {
/* 31 */       Iterator iterator = $this.scala$collection$mutable$SynchronizedMap$$super$iterator();
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 31 */       return iterator;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static SynchronizedMap $plus$eq(SynchronizedMap $this, Tuple2 kv) {
/* 32 */     synchronized ($this) {
/* 32 */       SynchronizedMap synchronizedMap = $this.scala$collection$mutable$SynchronizedMap$$super$$plus$eq(kv);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 32 */       return synchronizedMap;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static SynchronizedMap $minus$eq(SynchronizedMap $this, Object key) {
/* 33 */     synchronized ($this) {
/* 33 */       SynchronizedMap synchronizedMap = $this.scala$collection$mutable$SynchronizedMap$$super$$minus$eq(key);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 33 */       return synchronizedMap;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int size(SynchronizedMap $this) {
/* 35 */     synchronized ($this) {
/* 35 */       int i = $this.scala$collection$mutable$SynchronizedMap$$super$size();
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 35 */       return i;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Option put(SynchronizedMap<Object, Object> $this, Object key, Object value) {
/* 36 */     synchronized ($this) {
/* 36 */       Option option = $this.scala$collection$mutable$SynchronizedMap$$super$put(key, value);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap<[ObjectType{java/lang/Object}, ObjectType{java/lang/Object}]>}, name=$this} */
/* 36 */       return option;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void update(SynchronizedMap<Object, Object> $this, Object key, Object value) {
/* 37 */     synchronized ($this) {
/* 37 */       $this.scala$collection$mutable$SynchronizedMap$$super$update(key, value);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap<[ObjectType{java/lang/Object}, ObjectType{java/lang/Object}]>}, name=$this} */
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Option remove(SynchronizedMap $this, Object key) {
/* 38 */     synchronized ($this) {
/* 38 */       Option option = $this.scala$collection$mutable$SynchronizedMap$$super$remove(key);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 38 */       return option;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void clear(SynchronizedMap $this) {
/* 39 */     synchronized ($this) {
/* 39 */       $this.scala$collection$mutable$SynchronizedMap$$super$clear();
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Object getOrElseUpdate(SynchronizedMap<Object, Object> $this, Object key, Function0<Object> default) {
/* 40 */     synchronized ($this) {
/* 40 */       Object object = $this.scala$collection$mutable$SynchronizedMap$$super$getOrElseUpdate(key, default);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap<[ObjectType{java/lang/Object}, UndefinedObjectType]>}, name=$this} */
/* 40 */       return object;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static SynchronizedMap transform(SynchronizedMap $this, Function2 f) {
/* 41 */     synchronized ($this) {
/* 41 */       SynchronizedMap synchronizedMap = $this.scala$collection$mutable$SynchronizedMap$$super$transform(f);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 41 */       return synchronizedMap;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static SynchronizedMap retain(SynchronizedMap $this, Function2 p) {
/* 42 */     synchronized ($this) {
/* 42 */       SynchronizedMap synchronizedMap = $this.scala$collection$mutable$SynchronizedMap$$super$retain(p);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 42 */       return synchronizedMap;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Iterable values(SynchronizedMap $this) {
/* 44 */     synchronized ($this) {
/* 44 */       Iterable iterable = $this.scala$collection$mutable$SynchronizedMap$$super$values();
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 44 */       return iterable;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Iterator valuesIterator(SynchronizedMap $this) {
/* 45 */     synchronized ($this) {
/* 45 */       Iterator iterator = $this.scala$collection$mutable$SynchronizedMap$$super$valuesIterator();
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 45 */       return iterator;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Map clone(SynchronizedMap $this) {
/* 46 */     synchronized ($this) {
/* 46 */       Map map = $this.scala$collection$mutable$SynchronizedMap$$super$clone();
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 46 */       return map;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void foreach(SynchronizedMap $this, Function1 f) {
/* 47 */     synchronized ($this) {
/* 47 */       $this.scala$collection$mutable$SynchronizedMap$$super$foreach(f);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Object apply(SynchronizedMap<Object, Object> $this, Object key) {
/* 48 */     synchronized ($this) {
/* 48 */       Object object = $this.scala$collection$mutable$SynchronizedMap$$super$apply(key);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap<[ObjectType{java/lang/Object}, UndefinedObjectType]>}, name=$this} */
/* 48 */       return object;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Set keySet(SynchronizedMap $this) {
/* 49 */     synchronized ($this) {
/* 49 */       Set set = $this.scala$collection$mutable$SynchronizedMap$$super$keySet();
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 49 */       return set;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Iterable keys(SynchronizedMap $this) {
/* 51 */     synchronized ($this) {
/* 51 */       Iterable iterable = $this.scala$collection$mutable$SynchronizedMap$$super$keys();
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 51 */       return iterable;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Iterator keysIterator(SynchronizedMap $this) {
/* 52 */     synchronized ($this) {
/* 52 */       Iterator iterator = $this.scala$collection$mutable$SynchronizedMap$$super$keysIterator();
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 52 */       return iterator;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean isEmpty(SynchronizedMap $this) {
/* 53 */     synchronized ($this) {
/* 53 */       boolean bool = $this.scala$collection$mutable$SynchronizedMap$$super$isEmpty();
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 53 */       return bool;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean contains(SynchronizedMap $this, Object key) {
/* 54 */     synchronized ($this) {
/* 54 */       boolean bool = $this.scala$collection$mutable$SynchronizedMap$$super$contains(key);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 54 */       return bool;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean isDefinedAt(SynchronizedMap $this, Object key) {
/* 55 */     synchronized ($this) {
/* 55 */       boolean bool = $this.scala$collection$mutable$SynchronizedMap$$super$isDefinedAt(key);
/*    */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedMap}, name=$this} */
/* 55 */       return bool;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SynchronizedMap$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */