/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface Luminance extends Quantity {
/* 46 */   public static final Unit<Luminance> UNIT = (Unit<Luminance>)new ProductUnit(SI.CANDELA.divide(SI.SQUARE_METRE));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\Luminance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */