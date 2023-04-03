/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface ElectricPermittivity extends Quantity {
/* 48 */   public static final Unit<ElectricPermittivity> UNIT = (Unit<ElectricPermittivity>)new ProductUnit(SI.FARAD.divide(SI.METER));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\ElectricPermittivity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */