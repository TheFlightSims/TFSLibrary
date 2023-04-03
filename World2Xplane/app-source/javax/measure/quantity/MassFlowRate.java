/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface MassFlowRate extends Quantity {
/* 29 */   public static final Unit<MassFlowRate> UNIT = SI.KILOGRAM.divide((Unit)SI.SECOND);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\MassFlowRate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */