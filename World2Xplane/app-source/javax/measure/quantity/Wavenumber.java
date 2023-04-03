/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface Wavenumber extends Quantity {
/* 47 */   public static final Unit<Wavenumber> UNIT = (Unit<Wavenumber>)new ProductUnit(Unit.ONE.divide(SI.METER));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\Wavenumber.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */