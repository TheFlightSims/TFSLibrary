/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface MagneticFieldStrength extends Quantity {
/* 45 */   public static final Unit<MagneticFieldStrength> UNIT = (Unit<MagneticFieldStrength>)new ProductUnit(SI.AMPERE.divide(SI.METER));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\MagneticFieldStrength.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */