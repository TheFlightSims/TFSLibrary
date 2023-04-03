/*    */ package scala.ref;
/*    */ 
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Some;
/*    */ 
/*    */ public abstract class Reference$class {
/*    */   public static void $init$(Reference $this) {}
/*    */   
/*    */   public static String toString(Reference $this) {
/*    */     Option option1;
/*    */     Option option2;
/* 20 */     return (option2 = (Option)((option1 = $this.get()).isEmpty() ? None$.MODULE$ : new Some(option1.get().toString()))).isEmpty() ? "<deleted>" : (String)option2.get();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ref\Reference$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */