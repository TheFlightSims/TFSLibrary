/*    */ package org.geotools.filter.function.string;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import org.geotools.filter.FunctionImpl;
/*    */ import org.geotools.util.logging.Logging;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public class ConcatenateFunction extends FunctionImpl {
/* 46 */   static final Logger LOGGER = Logging.getLogger(ConcatenateFunction.class);
/*    */   
/* 52 */   public static final FunctionName NAME = functionName("Concatenate", "result:String", new String[] { "text:String:2," });
/*    */   
/*    */   public String getName() {
/* 60 */     return NAME.getName();
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 64 */     return NAME.getArgumentCount();
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/* 68 */     StringBuffer text = new StringBuffer();
/* 69 */     for (Expression expression : getParameters()) {
/*    */       try {
/* 71 */         String str = (String)expression.evaluate(feature, String.class);
/* 72 */         if (str != null)
/* 73 */           text.append(str); 
/* 75 */       } catch (Exception couldNotCompute) {
/* 76 */         LOGGER.log(Level.FINE, "Failed to concatenate string in Concatenate function", couldNotCompute);
/*    */       } 
/*    */     } 
/* 79 */     return text.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\string\ConcatenateFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */