/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface DynamicViscosity extends Quantity {
/* 29 */   public static final Unit<DynamicViscosity> UNIT = (Unit<DynamicViscosity>)new ProductUnit(SI.PASCAL.times((Unit)SI.SECOND));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\DynamicViscosity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */