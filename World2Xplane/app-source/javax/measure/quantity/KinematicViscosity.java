/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface KinematicViscosity extends Quantity {
/* 28 */   public static final Unit<KinematicViscosity> UNIT = (Unit<KinematicViscosity>)new ProductUnit(SI.METRE.pow(2).divide((Unit)SI.SECOND));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\KinematicViscosity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */