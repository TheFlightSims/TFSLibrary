/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.script.Message;
/*    */ import scala.collection.script.Script;
/*    */ import scala.runtime.IntRef;
/*    */ 
/*    */ public abstract class ObservableBuffer$class {
/*    */   public static void $init$(ObservableBuffer $this) {}
/*    */   
/*    */   public static ObservableBuffer $plus$eq(ObservableBuffer<Object> $this, Object element) {
/* 30 */     $this.scala$collection$mutable$ObservableBuffer$$super$$plus$eq(element);
/* 31 */     $this.publish((Message)new ObservableBuffer$$anon$2($this, (ObservableBuffer<A>)element));
/* 34 */     return $this;
/*    */   }
/*    */   
/*    */   public static ObservableBuffer $plus$plus$eq(ObservableBuffer<A> $this, TraversableOnce xs) {
/* 38 */     xs.foreach((Function1)new ObservableBuffer$$anonfun$$plus$plus$eq$1($this));
/* 39 */     return $this;
/*    */   }
/*    */   
/*    */   public static ObservableBuffer $plus$eq$colon(ObservableBuffer<Object> $this, Object element) {
/* 43 */     $this.scala$collection$mutable$ObservableBuffer$$super$$plus$eq$colon(element);
/* 44 */     $this.publish((Message)new ObservableBuffer$$anon$3($this, (ObservableBuffer<A>)element));
/* 47 */     return $this;
/*    */   }
/*    */   
/*    */   public static void update(ObservableBuffer<Object> $this, int n, Object newelement) {
/* 51 */     Object oldelement = $this.apply(n);
/* 52 */     $this.scala$collection$mutable$ObservableBuffer$$super$update(n, newelement);
/* 53 */     $this.publish((Message)new ObservableBuffer$$anon$4($this, oldelement, n, (ObservableBuffer<A>)newelement));
/*    */   }
/*    */   
/*    */   public static Object remove(ObservableBuffer $this, int n) {
/* 59 */     Object oldelement = $this.apply(n);
/* 60 */     $this.scala$collection$mutable$ObservableBuffer$$super$remove(n);
/* 61 */     $this.publish((Message)new ObservableBuffer$$anon$5($this, oldelement, n));
/* 64 */     return oldelement;
/*    */   }
/*    */   
/*    */   public static void clear(ObservableBuffer<A> $this) {
/* 68 */     $this.scala$collection$mutable$ObservableBuffer$$super$clear();
/* 69 */     $this.publish((Message)new ObservableBuffer$$anon$6($this));
/*    */   }
/*    */   
/*    */   public static void insertAll(ObservableBuffer<A> $this, int n, Traversable elems) {
/* 75 */     $this.scala$collection$mutable$ObservableBuffer$$super$insertAll(n, elems);
/* 76 */     IntRef curr = new IntRef(n - 1);
/* 77 */     Script msg = (Script)elems
/*    */       
/* 79 */       .foldLeft(new ObservableBuffer$$anon$1($this), (Function2)new ObservableBuffer$$anonfun$1($this, (ObservableBuffer<A>)curr));
/* 84 */     $this.publish((Message<A>)msg);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ObservableBuffer$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */