/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface Torque extends Quantity {
/* 31 */   public static final Unit<Torque> UNIT = (Unit<Torque>)new ProductUnit(SI.NEWTON.times((Unit)SI.METRE));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\Torque.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */