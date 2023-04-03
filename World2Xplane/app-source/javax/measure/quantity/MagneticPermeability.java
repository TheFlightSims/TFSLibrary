/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface MagneticPermeability extends Quantity {
/* 48 */   public static final Unit<MagneticPermeability> UNIT = (Unit<MagneticPermeability>)new ProductUnit(SI.HENRY.divide(SI.METER));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\MagneticPermeability.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */