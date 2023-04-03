/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface VolumetricFlowRate extends Quantity {
/* 30 */   public static final Unit<VolumetricFlowRate> UNIT = SI.METRE.pow(3).divide((Unit)SI.SECOND);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\VolumetricFlowRate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */