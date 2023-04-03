/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface DataRate extends Quantity {
/* 26 */   public static final Unit<DataRate> UNIT = (Unit<DataRate>)new ProductUnit(SI.BIT.divide((Unit)SI.SECOND));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\DataRate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */