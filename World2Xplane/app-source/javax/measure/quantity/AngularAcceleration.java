/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface AngularAcceleration extends Quantity {
/* 27 */   public static final Unit<AngularAcceleration> UNIT = (Unit<AngularAcceleration>)new ProductUnit(SI.RADIAN.divide(SI.SECOND.pow(2)));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\AngularAcceleration.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */