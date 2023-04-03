/*    */ package org.geotools.filter.function.string;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import org.geotools.filter.FunctionImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ 
/*    */ public class StringInFunction extends FunctionImpl {
/* 33 */   static FunctionName NAME = functionName("strIn", "result:Boolean", new String[] { "string:String", "matchCase:Boolean", "values:String:1," });
/*    */   
/*    */   public StringInFunction() {
/* 37 */     setName("strIn");
/* 38 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object object) {
/* 43 */     LinkedHashMap<String, Object> args = dispatchArguments(object);
/* 45 */     String str = (String)args.get("string");
/* 46 */     Boolean matchCase = (Boolean)args.get("matchCase");
/* 47 */     List<String> values = (List<String>)args.get("values");
/* 49 */     for (String value : values) {
/* 50 */       if (matchCase.booleanValue()) {
/* 51 */         if (str.equals(value))
/* 52 */           return Boolean.valueOf(true); 
/*    */         continue;
/*    */       } 
/* 56 */       if (str.equalsIgnoreCase(value))
/* 57 */         return Boolean.valueOf(true); 
/*    */     } 
/* 62 */     return Boolean.valueOf(false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\string\StringInFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */