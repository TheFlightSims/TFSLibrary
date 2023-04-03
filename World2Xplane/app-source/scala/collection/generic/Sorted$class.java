/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Some;
/*    */ import scala.collection.Iterator;
/*    */ 
/*    */ public abstract class Sorted$class {
/*    */   public static void $init$(Sorted $this) {}
/*    */   
/*    */   public static int compare(Sorted $this, Object k0, Object k1) {
/* 33 */     return $this.ordering().compare(k0, k1);
/*    */   }
/*    */   
/*    */   public static Sorted from(Sorted $this, Object from) {
/* 53 */     return (Sorted)$this.rangeImpl((Option)new Some(from), (Option)None$.MODULE$);
/*    */   }
/*    */   
/*    */   public static Sorted until(Sorted $this, Object until) {
/* 59 */     return (Sorted)$this.rangeImpl((Option)None$.MODULE$, (Option)new Some(until));
/*    */   }
/*    */   
/*    */   public static Sorted range(Sorted $this, Object from, Object until) {
/* 66 */     return (Sorted)$this.rangeImpl((Option)new Some(from), (Option)new Some(until));
/*    */   }
/*    */   
/*    */   public static Sorted to(Sorted<Object, Sorted> $this, Object to) {
/* 72 */     Iterator i = $this.keySet().from(to).iterator();
/* 73 */     if (i.isEmpty())
/* 73 */       return (Sorted)$this.repr(); 
/* 74 */     Object next = i.next();
/* 75 */     return ($this.compare(next, to) == 0) ? (
/* 76 */       i.isEmpty() ? (Sorted)$this.repr() : 
/* 77 */       $this.until(i.next())) : 
/*    */       
/* 79 */       $this.until(next);
/*    */   }
/*    */   
/*    */   public static boolean hasAll(Sorted $this, Iterator j) {
/* 83 */     Iterator i = $this.keySet().iterator();
/* 84 */     if (i.isEmpty())
/* 84 */       return j.isEmpty(); 
/* 86 */     Object in = i.next();
/* 87 */     label21: while (j.hasNext()) {
/* 88 */       Object jn = j.next();
/*    */       while (true) {
/* 89 */         int n = $this.compare(jn, in);
/* 92 */         if (n < 0)
/* 92 */           return false; 
/* 93 */         if (i.hasNext()) {
/*    */         
/*    */         } else {
/* 93 */           return false;
/*    */         } 
/* 94 */         if (((n == 0) ? Character.MIN_VALUE : "JD-Core does not support Kotlin") != null) {
/* 95 */           in = i.next();
/*    */           continue;
/*    */         } 
/*    */         continue label21;
/*    */       } 
/*    */     } 
/* 97 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\Sorted$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */