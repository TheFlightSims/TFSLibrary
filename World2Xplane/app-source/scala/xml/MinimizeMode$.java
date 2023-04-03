/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Enumeration;
/*    */ 
/*    */ public final class MinimizeMode$ extends Enumeration {
/*    */   public static final MinimizeMode$ MODULE$;
/*    */   
/*    */   private final Enumeration.Value Default;
/*    */   
/*    */   private final Enumeration.Value Always;
/*    */   
/*    */   private final Enumeration.Value Never;
/*    */   
/*    */   private MinimizeMode$() {
/* 32 */     MODULE$ = this;
/* 36 */     this.Default = Value();
/* 41 */     this.Always = Value();
/* 45 */     this.Never = Value();
/*    */   }
/*    */   
/*    */   public Enumeration.Value Default() {
/*    */     return this.Default;
/*    */   }
/*    */   
/*    */   public Enumeration.Value Always() {
/*    */     return this.Always;
/*    */   }
/*    */   
/*    */   public Enumeration.Value Never() {
/* 45 */     return this.Never;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\MinimizeMode$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */