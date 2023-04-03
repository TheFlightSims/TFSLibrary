/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.filter.FunctionExpressionImpl;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class EnvFunction extends FunctionExpressionImpl {
/*     */   private static class LocalLookup extends ThreadLocal<Map<String, Object>> {
/*     */     private LocalLookup() {}
/*     */     
/*     */     protected Map<String, Object> initialValue() {
/* 108 */       return new LinkedHashMap<String, Object>();
/*     */     }
/*     */     
/*     */     public Map<String, Object> getTable() {
/* 118 */       return (Map<String, Object>)get();
/*     */     }
/*     */   }
/*     */   
/* 122 */   private static final LocalLookup localLookup = new LocalLookup();
/*     */   
/* 127 */   private static ConcurrentMap<String, Object> globalLookup = new ConcurrentHashMap<String, Object>();
/*     */   
/* 130 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("env", FunctionNameImpl.parameter("value", Object.class), new Parameter[] { FunctionNameImpl.parameter("variable", String.class) });
/*     */   
/*     */   public EnvFunction() {
/* 138 */     super(NAME);
/*     */   }
/*     */   
/*     */   public static void setLocalValues(Map<String, Object> values) {
/* 149 */     Map<String, Object> table = localLookup.getTable();
/* 150 */     table.clear();
/* 152 */     if (values != null)
/* 153 */       for (Map.Entry<String, Object> e : values.entrySet())
/* 154 */         table.put(((String)e.getKey()).toUpperCase(), e.getValue());  
/*     */   }
/*     */   
/*     */   public static void clearLocalValues() {
/* 163 */     localLookup.getTable().clear();
/* 164 */     localLookup.remove();
/*     */   }
/*     */   
/*     */   public static void setGlobalValues(Map<String, Object> values) {
/* 175 */     globalLookup.clear();
/* 177 */     if (values != null)
/* 178 */       for (Map.Entry<String, Object> e : values.entrySet())
/* 179 */         globalLookup.put(((String)e.getKey()).toUpperCase(), e.getValue());  
/*     */   }
/*     */   
/*     */   public static void clearGlobalValues() {
/* 188 */     globalLookup.clear();
/*     */   }
/*     */   
/*     */   public static void setLocalValue(String name, Object value) {
/* 199 */     localLookup.getTable().put(name.toUpperCase(), value);
/*     */   }
/*     */   
/*     */   public static void setGlobalValue(String name, Object value) {
/* 210 */     globalLookup.put(name.toUpperCase(), value);
/*     */   }
/*     */   
/*     */   public int getArgCount() {
/* 219 */     return 1;
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/* 232 */     String varName = (String)getExpression(0).evaluate(feature, String.class);
/* 233 */     Object value = localLookup.getTable().get(varName.toUpperCase());
/* 236 */     if (value == null)
/* 237 */       value = globalLookup.get(varName.toUpperCase()); 
/* 241 */     if (value == null) {
/* 242 */       int paramSize = getParameters().size();
/* 243 */       if (paramSize > getArgCount())
/* 244 */         value = getExpression(paramSize - 1).evaluate(feature); 
/*     */     } 
/* 248 */     return value;
/*     */   }
/*     */   
/*     */   public void setParameters(List<?> params) {
/* 258 */     if (params == null)
/* 259 */       throw new NullPointerException("params can't be null"); 
/* 262 */     int argCount = getArgCount();
/* 263 */     int paramsSize = params.size();
/* 264 */     if (paramsSize < argCount || paramsSize > argCount + 1)
/* 265 */       throw new IllegalArgumentException(String.format("Function %s expected %d or %d arguments but got %d", new Object[] { this.name, Integer.valueOf(argCount), Integer.valueOf(argCount + 1), Integer.valueOf(paramsSize) })); 
/* 269 */     this.params = new ArrayList(params);
/*     */   }
/*     */   
/*     */   public void setFallbackValue(Literal fallback) {
/* 281 */     Logger logger = Logger.getLogger(EnvFunction.class.getName());
/* 282 */     logger.log(Level.WARNING, "The setFallbackValue is not supported by this function.Use a second argument when calling the function to provide a default value.");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\EnvFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */