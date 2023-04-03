/*    */ package org.geotools.styling;
/*    */ 
/*    */ import org.geotools.filter.ConstantExpression;
/*    */ 
/*    */ public class OverlapBehavior extends ConstantExpression {
/*    */   public static final String AVERAGE_RESCTRICTION = "AVERAGE";
/*    */   
/*    */   public static final String RANDOM_RESCTRICTION = "RANDOM";
/*    */   
/*    */   public static final String LATEST_ON_TOP_RESCTRICTION = "LATEST_ON_TOP";
/*    */   
/*    */   public static final String EARLIEST_ON_TOP_RESCTRICTION = "EARLIEST_ON_TOP";
/*    */   
/*    */   public static final String UNSPECIFIED_RESCTRICTION = "UNSPECIFIED";
/*    */   
/* 63 */   public static final OverlapBehavior LATEST_ON_TOP = new OverlapBehavior("LATEST_ON_TOP");
/*    */   
/* 64 */   public static final OverlapBehavior EARLIEST_ON_TOP = new OverlapBehavior("EARLIEST_ON_TOP");
/*    */   
/* 65 */   public static final OverlapBehavior AVERAGE = new OverlapBehavior("AVERAGE");
/*    */   
/* 66 */   public static final OverlapBehavior RANDOM = new OverlapBehavior("RANDOM");
/*    */   
/*    */   private OverlapBehavior(String value) {
/* 69 */     super(value);
/*    */   }
/*    */   
/*    */   public void accept(StyleVisitor visitor) {
/* 73 */     visitor.visit(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\OverlapBehavior.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */