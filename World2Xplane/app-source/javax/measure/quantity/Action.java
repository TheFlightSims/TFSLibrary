/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface Action extends Quantity {
/* 48 */   public static final Unit<Action> UNIT = (Unit<Action>)new ProductUnit(SI.JOULE.times((Unit)SI.SECOND));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\Action.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */