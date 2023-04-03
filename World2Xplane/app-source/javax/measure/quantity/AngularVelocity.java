/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface AngularVelocity extends Quantity {
/* 27 */   public static final Unit<AngularVelocity> UNIT = (Unit<AngularVelocity>)new ProductUnit(SI.RADIAN.divide((Unit)SI.SECOND));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\AngularVelocity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */