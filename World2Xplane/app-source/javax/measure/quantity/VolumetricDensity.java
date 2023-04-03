/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface VolumetricDensity extends Quantity {
/* 28 */   public static final Unit<VolumetricDensity> UNIT = (Unit<VolumetricDensity>)new ProductUnit(SI.KILOGRAM.divide(SI.METRE.pow(3)));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\VolumetricDensity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */