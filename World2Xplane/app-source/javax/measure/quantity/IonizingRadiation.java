/*    */ package javax.measure.quantity;
/*    */ 
/*    */ import javax.measure.unit.ProductUnit;
/*    */ import javax.measure.unit.SI;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ public interface IonizingRadiation extends Quantity {
/* 48 */   public static final Unit<IonizingRadiation> UNIT = (Unit<IonizingRadiation>)new ProductUnit(SI.COULOMB.divide((Unit)SI.KILOGRAM));
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\quantity\IonizingRadiation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */