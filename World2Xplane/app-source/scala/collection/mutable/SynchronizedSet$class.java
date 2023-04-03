/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.script.Message;
/*     */ 
/*     */ public abstract class SynchronizedSet$class {
/*     */   public static void $init$(SynchronizedSet $this) {}
/*     */   
/*     */   public static int size(SynchronizedSet $this) {
/*  29 */     synchronized ($this) {
/*  30 */       int i = $this.scala$collection$mutable$SynchronizedSet$$super$size();
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return i;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isEmpty(SynchronizedSet $this) {
/*  33 */     synchronized ($this) {
/*  34 */       boolean bool = $this.scala$collection$mutable$SynchronizedSet$$super$isEmpty();
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return bool;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean contains(SynchronizedSet<Object> $this, Object elem) {
/*  37 */     synchronized ($this) {
/*  38 */       boolean bool = $this.scala$collection$mutable$SynchronizedSet$$super$contains(elem);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet<ObjectType{java/lang/Object}>}, name=$this} */
/*     */       return bool;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SynchronizedSet $plus$eq(SynchronizedSet<Object> $this, Object elem) {
/*  41 */     synchronized ($this) {
/*  42 */       SynchronizedSet synchronizedSet = $this.scala$collection$mutable$SynchronizedSet$$super$$plus$eq(elem);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet<ObjectType{java/lang/Object}>}, name=$this} */
/*     */       return synchronizedSet;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SynchronizedSet $plus$plus$eq(SynchronizedSet $this, TraversableOnce xs) {
/*  45 */     synchronized ($this) {
/*  46 */       SynchronizedSet synchronizedSet = $this.scala$collection$mutable$SynchronizedSet$$super$$plus$plus$eq(xs);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return synchronizedSet;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SynchronizedSet $minus$eq(SynchronizedSet<Object> $this, Object elem) {
/*  49 */     synchronized ($this) {
/*  50 */       SynchronizedSet synchronizedSet = $this.scala$collection$mutable$SynchronizedSet$$super$$minus$eq(elem);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet<ObjectType{java/lang/Object}>}, name=$this} */
/*     */       return synchronizedSet;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SynchronizedSet $minus$minus$eq(SynchronizedSet $this, TraversableOnce xs) {
/*  53 */     synchronized ($this) {
/*  54 */       SynchronizedSet synchronizedSet = $this.scala$collection$mutable$SynchronizedSet$$super$$minus$minus$eq(xs);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return synchronizedSet;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void update(SynchronizedSet<Object> $this, Object elem, boolean included) {
/*  57 */     synchronized ($this) {
/*  58 */       $this.scala$collection$mutable$SynchronizedSet$$super$update(elem, included);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet<ObjectType{java/lang/Object}>}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean add(SynchronizedSet<Object> $this, Object elem) {
/*  61 */     synchronized ($this) {
/*  62 */       boolean bool = $this.scala$collection$mutable$SynchronizedSet$$super$add(elem);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet<ObjectType{java/lang/Object}>}, name=$this} */
/*     */       return bool;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean remove(SynchronizedSet<Object> $this, Object elem) {
/*  65 */     synchronized ($this) {
/*  66 */       boolean bool = $this.scala$collection$mutable$SynchronizedSet$$super$remove(elem);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet<ObjectType{java/lang/Object}>}, name=$this} */
/*     */       return bool;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Set intersect(SynchronizedSet $this, GenSet that) {
/*  69 */     synchronized ($this) {
/*  70 */       Set set = $this.scala$collection$mutable$SynchronizedSet$$super$intersect(that);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return set;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void clear(SynchronizedSet $this) {
/*  73 */     synchronized ($this) {
/*  74 */       $this.scala$collection$mutable$SynchronizedSet$$super$clear();
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean subsetOf(SynchronizedSet $this, GenSet that) {
/*  77 */     synchronized ($this) {
/*  78 */       boolean bool = $this.scala$collection$mutable$SynchronizedSet$$super$subsetOf(that);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return bool;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void foreach(SynchronizedSet $this, Function1 f) {
/*  81 */     synchronized ($this) {
/*  82 */       $this.scala$collection$mutable$SynchronizedSet$$super$foreach(f);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void retain(SynchronizedSet $this, Function1 p) {
/*  85 */     synchronized ($this) {
/*  86 */       $this.scala$collection$mutable$SynchronizedSet$$super$retain(p);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static List toList(SynchronizedSet $this) {
/*  89 */     synchronized ($this) {
/*  90 */       List list = $this.scala$collection$mutable$SynchronizedSet$$super$toList();
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return list;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String toString(SynchronizedSet $this) {
/*  93 */     synchronized ($this) {
/*  94 */       String str = $this.scala$collection$mutable$SynchronizedSet$$super$toString();
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return str;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void $less$less(SynchronizedSet $this, Message cmd) {
/*  97 */     synchronized ($this) {
/*  98 */       $this.scala$collection$mutable$SynchronizedSet$$super$$less$less(cmd);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Set clone(SynchronizedSet $this) {
/* 101 */     synchronized ($this) {
/* 102 */       Set set = $this.scala$collection$mutable$SynchronizedSet$$super$clone();
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedSet}, name=$this} */
/*     */       return set;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SynchronizedSet$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */