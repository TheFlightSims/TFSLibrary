/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public final class BoxedUnit implements Serializable {
/*    */   private static final long serialVersionUID = 8405543498931817370L;
/*    */   
/* 17 */   public static final BoxedUnit UNIT = new BoxedUnit();
/*    */   
/* 19 */   public static final Class<Void> TYPE = (Class)void.class;
/*    */   
/*    */   private Object readResolve() {
/* 21 */     return UNIT;
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 26 */     return (this == other);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 30 */     return 0;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 34 */     return "()";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\BoxedUnit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */