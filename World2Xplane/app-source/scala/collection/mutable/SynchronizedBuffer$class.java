/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.script.Message;
/*     */ 
/*     */ public abstract class SynchronizedBuffer$class {
/*     */   public static void $init$(SynchronizedBuffer $this) {}
/*     */   
/*     */   public static int length(SynchronizedBuffer $this) {
/*  31 */     synchronized ($this) {
/*  32 */       int i = $this.scala$collection$mutable$SynchronizedBuffer$$super$length();
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return i;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Iterator iterator(SynchronizedBuffer $this) {
/*  35 */     synchronized ($this) {
/*  36 */       Iterator iterator = $this.scala$collection$mutable$SynchronizedBuffer$$super$iterator();
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return iterator;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object apply(SynchronizedBuffer<Object> $this, int n) {
/*  39 */     synchronized ($this) {
/*  40 */       Object object = $this.scala$collection$mutable$SynchronizedBuffer$$super$apply(n);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer<UndefinedObjectType>}, name=$this} */
/*     */       return object;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SynchronizedBuffer $plus$eq(SynchronizedBuffer<Object> $this, Object elem) {
/*  47 */     synchronized ($this) {
/*  48 */       SynchronizedBuffer synchronizedBuffer = $this.scala$collection$mutable$SynchronizedBuffer$$super$$plus$eq(elem);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer<ObjectType{java/lang/Object}>}, name=$this} */
/*     */       return synchronizedBuffer;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Buffer $plus$plus(SynchronizedBuffer $this, GenTraversableOnce xs) {
/*  57 */     synchronized ($this) {
/*  58 */       Buffer buffer = $this.scala$collection$mutable$SynchronizedBuffer$$super$$plus$plus(xs);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return buffer;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SynchronizedBuffer $plus$plus$eq(SynchronizedBuffer $this, TraversableOnce xs) {
/*  66 */     synchronized ($this) {
/*  67 */       SynchronizedBuffer synchronizedBuffer = $this.scala$collection$mutable$SynchronizedBuffer$$super$$plus$plus$eq(xs);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return synchronizedBuffer;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void append(SynchronizedBuffer $this, Seq elems) {
/*  74 */     synchronized ($this) {
/*  75 */       $this.scala$collection$mutable$SynchronizedBuffer$$super$$plus$plus$eq((TraversableOnce)elems);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void appendAll(SynchronizedBuffer $this, TraversableOnce xs) {
/*  83 */     synchronized ($this) {
/*  84 */       $this.scala$collection$mutable$SynchronizedBuffer$$super$appendAll(xs);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SynchronizedBuffer $plus$eq$colon(SynchronizedBuffer<Object> $this, Object elem) {
/*  92 */     synchronized ($this) {
/*  93 */       SynchronizedBuffer synchronizedBuffer = $this.scala$collection$mutable$SynchronizedBuffer$$super$$plus$eq$colon(elem);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer<ObjectType{java/lang/Object}>}, name=$this} */
/*     */       return synchronizedBuffer;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SynchronizedBuffer $plus$plus$eq$colon(SynchronizedBuffer $this, TraversableOnce xs) {
/* 101 */     synchronized ($this) {
/* 101 */       SynchronizedBuffer synchronizedBuffer = $this.scala$collection$mutable$SynchronizedBuffer$$super$$plus$plus$eq$colon(xs);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/* 101 */       return synchronizedBuffer;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void prepend(SynchronizedBuffer $this, Seq elems) {
/* 107 */     $this.prependAll((TraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public static void prependAll(SynchronizedBuffer $this, TraversableOnce xs) {
/* 114 */     synchronized ($this) {
/* 115 */       $this.scala$collection$mutable$SynchronizedBuffer$$super$prependAll(xs);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void insert(SynchronizedBuffer $this, int n, Seq elems) {
/* 125 */     synchronized ($this) {
/* 126 */       $this.scala$collection$mutable$SynchronizedBuffer$$super$insertAll(n, (Traversable)elems);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void insertAll(SynchronizedBuffer $this, int n, Traversable xs) {
/* 136 */     synchronized ($this) {
/* 137 */       $this.scala$collection$mutable$SynchronizedBuffer$$super$insertAll(n, xs);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void update(SynchronizedBuffer<Object> $this, int n, Object newelem) {
/* 145 */     synchronized ($this) {
/* 146 */       $this.scala$collection$mutable$SynchronizedBuffer$$super$update(n, newelem);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer<ObjectType{java/lang/Object}>}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object remove(SynchronizedBuffer<Object> $this, int n) {
/* 153 */     synchronized ($this) {
/* 154 */       Object object = $this.scala$collection$mutable$SynchronizedBuffer$$super$remove(n);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer<UndefinedObjectType>}, name=$this} */
/*     */       return object;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void clear(SynchronizedBuffer $this) {
/* 159 */     synchronized ($this) {
/* 160 */       $this.scala$collection$mutable$SynchronizedBuffer$$super$clear();
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void $less$less(SynchronizedBuffer $this, Message cmd) {
/* 163 */     synchronized ($this) {
/* 164 */       $this.scala$collection$mutable$SynchronizedBuffer$$super$$less$less(cmd);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Buffer clone(SynchronizedBuffer $this) {
/* 171 */     synchronized ($this) {
/* 172 */       Buffer buffer = $this.scala$collection$mutable$SynchronizedBuffer$$super$clone();
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return buffer;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int hashCode(SynchronizedBuffer $this) {
/* 180 */     synchronized ($this) {
/* 181 */       int i = $this.scala$collection$mutable$SynchronizedBuffer$$super$hashCode();
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/mutable/SynchronizedBuffer}, name=$this} */
/*     */       return i;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SynchronizedBuffer$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */