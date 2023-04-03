/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.MatchError;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.script.Message;
/*    */ 
/*    */ public abstract class ObservableMap$class {
/*    */   public static void $init$(ObservableMap $this) {}
/*    */   
/*    */   public static ObservableMap $plus$eq(ObservableMap $this, Tuple2 kv) {
/*    */     Object key, value;
/* 33 */     if (kv != null) {
/* 33 */       Tuple2 tuple2 = new Tuple2(kv._1(), kv._2());
/* 33 */       key = tuple2._1();
/* 33 */       value = tuple2._2();
/* 35 */       Option option = $this.get(key);
/* 36 */       if (None$.MODULE$ == null) {
/* 36 */         if (option != null)
/* 41 */           if (option instanceof Some) {
/* 41 */             Some some = (Some)option;
/* 42 */             $this.scala$collection$mutable$ObservableMap$$super$$plus$eq(kv);
/* 43 */             $this.publish((Message)new ObservableMap$$anon$2($this, key, value, (ObservableMap<A, B>)some));
/*    */             return $this;
/*    */           }  
/*    */       } else {
/*    */         if (None$.MODULE$.equals(option)) {
/*    */           $this.scala$collection$mutable$ObservableMap$$super$$plus$eq(kv);
/*    */           $this.publish((Message)new ObservableMap$$anon$1($this, key, (ObservableMap<A, B>)value));
/*    */           return $this;
/*    */         } 
/*    */         if (option instanceof Some) {
/*    */           Some some = (Some)option;
/*    */           $this.scala$collection$mutable$ObservableMap$$super$$plus$eq(kv);
/* 43 */           $this.publish((Message)new ObservableMap$$anon$2($this, key, value, (ObservableMap<A, B>)some));
/*    */           return $this;
/*    */         } 
/*    */       } 
/*    */     } else {
/*    */       throw new MatchError(kv);
/*    */     } 
/*    */     $this.scala$collection$mutable$ObservableMap$$super$$plus$eq(kv);
/*    */     $this.publish((Message)new ObservableMap$$anon$1($this, key, (ObservableMap<A, B>)value));
/*    */     return $this;
/*    */   }
/*    */   
/*    */   public static ObservableMap $minus$eq(ObservableMap $this, Object key) {
/* 51 */     Option option = $this.get(key);
/* 52 */     if (None$.MODULE$ == null) {
/* 52 */       if (option != null) {
/* 53 */         if (option instanceof Some) {
/* 53 */           Some some = (Some)option;
/* 54 */           $this.scala$collection$mutable$ObservableMap$$super$$minus$eq(key);
/* 55 */           $this.publish((Message)new ObservableMap$$anon$3($this, some, (ObservableMap<A, B>)key));
/*    */           return $this;
/*    */         } 
/*    */         throw new MatchError(option);
/*    */       } 
/*    */     } else if (!None$.MODULE$.equals(option)) {
/*    */       if (option instanceof Some) {
/*    */         Some some = (Some)option;
/*    */         $this.scala$collection$mutable$ObservableMap$$super$$minus$eq(key);
/* 55 */         $this.publish((Message)new ObservableMap$$anon$3($this, some, (ObservableMap<A, B>)key));
/*    */         return $this;
/*    */       } 
/*    */       throw new MatchError(option);
/*    */     } 
/*    */     return $this;
/*    */   }
/*    */   
/*    */   public static void clear(ObservableMap<A, B> $this) {
/* 63 */     $this.scala$collection$mutable$ObservableMap$$super$clear();
/* 64 */     $this.publish((Message)new ObservableMap$$anon$4($this));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ObservableMap$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */