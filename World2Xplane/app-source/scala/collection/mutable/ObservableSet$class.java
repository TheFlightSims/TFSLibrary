/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.script.Message;
/*    */ 
/*    */ public abstract class ObservableSet$class {
/*    */   public static void $init$(ObservableSet $this) {}
/*    */   
/*    */   public static ObservableSet $plus$eq(ObservableSet<Object> $this, Object elem) {
/* 31 */     if (!$this.contains(elem)) {
/* 32 */       $this.scala$collection$mutable$ObservableSet$$super$$plus$eq(elem);
/* 33 */       $this.publish((Message)new ObservableSet$$anon$1($this, (ObservableSet<A>)elem));
/*    */     } 
/* 35 */     return $this;
/*    */   }
/*    */   
/*    */   public static ObservableSet $minus$eq(ObservableSet<Object> $this, Object elem) {
/* 39 */     if ($this.contains(elem)) {
/* 40 */       $this.scala$collection$mutable$ObservableSet$$super$$minus$eq(elem);
/* 41 */       $this.publish((Message)new ObservableSet$$anon$2($this, (ObservableSet<A>)elem));
/*    */     } 
/* 43 */     return $this;
/*    */   }
/*    */   
/*    */   public static void clear(ObservableSet<A> $this) {
/* 47 */     $this.scala$collection$mutable$ObservableSet$$super$clear();
/* 48 */     $this.publish((Message)new ObservableSet$$anon$3($this));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ObservableSet$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */