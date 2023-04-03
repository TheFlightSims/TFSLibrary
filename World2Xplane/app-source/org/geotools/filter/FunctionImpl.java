/*     */ package org.geotools.filter;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.geotools.data.Parameter;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.filter.expression.ExpressionAbstract;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class FunctionImpl extends ExpressionAbstract implements Function {
/*     */   String name;
/*     */   
/*  57 */   List<Expression> params = Collections.emptyList();
/*     */   
/*     */   Literal fallbackValue;
/*     */   
/*     */   protected FunctionName functionName;
/*     */   
/*     */   public String getName() {
/*  78 */     return this.name;
/*     */   }
/*     */   
/*     */   public synchronized FunctionName getFunctionName() {
/*  81 */     if (this.functionName == null)
/*  82 */       this.functionName = (FunctionName)new FunctionNameImpl(this.name, getParameters().size()); 
/*  84 */     return this.functionName;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  91 */     this.name = name;
/*     */   }
/*     */   
/*     */   public List<Expression> getParameters() {
/*  98 */     return new ArrayList<Expression>(this.params);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object object) {
/* 110 */     return this.fallbackValue.evaluate(object);
/*     */   }
/*     */   
/*     */   public void setParameters(List<Expression> params) {
/* 118 */     this.params = (params == null) ? Collections.EMPTY_LIST : new ArrayList<Expression>(params);
/*     */   }
/*     */   
/*     */   public void setFallbackValue(Literal fallbackValue) {
/* 122 */     this.fallbackValue = fallbackValue;
/*     */   }
/*     */   
/*     */   public Literal getFallbackValue() {
/* 126 */     return this.fallbackValue;
/*     */   }
/*     */   
/*     */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 130 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   private void validateArguments() throws IllegalArgumentException {
/* 138 */     List<Parameter<?>> args = getFunctionName().getArguments();
/* 140 */     for (int i = 0; i < args.size(); i++) {
/* 141 */       Parameter<?> arg = args.get(i);
/* 142 */       if (arg.getMaxOccurs() == 0)
/* 143 */         throw new IllegalArgumentException(String.format("Argument %s has zero max", new Object[0])); 
/* 145 */       if (arg.getMinOccurs() != 1 || arg.getMaxOccurs() != 1)
/* 147 */         if (i != args.size() - 1)
/* 148 */           throw new IllegalArgumentException(String.format("Argument %s(%d,%d) invalid. Variable arguments must be the last argument of function.", new Object[] { arg.getName(), Integer.valueOf(arg.getMinOccurs()), Integer.valueOf(arg.getMaxOccurs()) }));  
/*     */     } 
/*     */   }
/*     */   
/*     */   protected LinkedHashMap<String, Object> dispatchArguments(Object obj) {
/* 166 */     LinkedHashMap<String, Object> prepped = new LinkedHashMap<String, Object>();
/* 168 */     List<Parameter<?>> args = getFunctionName().getArguments();
/* 169 */     List<Expression> expr = getParameters();
/* 171 */     if (expr.size() < args.size()) {
/* 172 */       Parameter<?> last = args.get(args.size() - 1);
/* 175 */       if (((Parameter)args.get(0)).getMinOccurs() != 0)
/* 176 */         throw new IllegalArgumentException(String.format("No arguments specified for arg %s, minOccurs = %d", new Object[] { last.getName().toString(), Integer.valueOf(last.getMinOccurs()) })); 
/*     */     } 
/* 180 */     for (int i = 0; i < expr.size(); i++) {
/* 181 */       Parameter<?> arg = args.get(Math.min(i, args.size() - 1));
/* 182 */       String argName = arg.getName().toString();
/* 184 */       Object o = ((Expression)expr.get(i)).evaluate(obj, arg.getType());
/* 185 */       if (o == null && (
/* 186 */         (Expression)expr.get(i)).evaluate(obj) != null)
/* 188 */         throw new IllegalArgumentException(String.format("Failure converting value for argument %s. %s could not be converted to %s", new Object[] { arg.getName(), obj.toString(), arg.getType().getName() })); 
/* 193 */       if (prepped.containsKey(argName)) {
/* 194 */         if (arg.getMaxOccurs() == 1)
/* 195 */           throw new IllegalArgumentException(String.format("Multiple values specified for argument %s  but maxOccurs = 1", new Object[] { argName })); 
/* 201 */         List<Object> l = (List)prepped.get(argName);
/* 202 */         l.add(o);
/* 206 */       } else if (arg.getMaxOccurs() < 0 || arg.getMaxOccurs() > 1) {
/* 207 */         List<Object> l = new ArrayList();
/* 208 */         l.add(o);
/* 209 */         prepped.put(argName, l);
/*     */       } else {
/* 212 */         prepped.put(argName, o);
/*     */       } 
/*     */     } 
/* 217 */     return prepped;
/*     */   }
/*     */   
/* 221 */   static FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2(null);
/*     */   
/* 226 */   static Pattern PARAM = Pattern.compile("(\\w+)(?::([\\.\\w]*)(?::(\\d*),(\\d*))?+)?+");
/*     */   
/*     */   protected static FunctionName functionName(String name, String ret, String... args) {
/* 265 */     List<Parameter<?>> list = new ArrayList<Parameter<?>>();
/* 266 */     for (String arg : args)
/* 267 */       list.add(toParameter(arg)); 
/* 270 */     return ff.functionName(name, list, toParameter(ret));
/*     */   }
/*     */   
/*     */   static Parameter toParameter(String param) throws IllegalArgumentException {
/* 274 */     Matcher m = PARAM.matcher(param);
/* 275 */     if (!m.matches())
/* 276 */       throw new IllegalArgumentException("Illegal parameter syntax: " + param); 
/* 279 */     String name = m.group(1);
/* 280 */     Class<?> type = null;
/* 281 */     int min = 1;
/* 282 */     int max = 1;
/* 284 */     String grp = m.group(2);
/* 285 */     if ("".equals(grp))
/* 286 */       grp = null; 
/* 288 */     if (grp != null)
/*     */       try {
/* 290 */         type = Class.forName(grp);
/* 291 */       } catch (ClassNotFoundException e) {
/*     */         try {
/* 294 */           type = Class.forName("java.lang." + grp);
/* 295 */         } catch (ClassNotFoundException e1) {
/*     */           try {
/* 298 */             type = Class.forName("com.vividsolutions.jts.geom." + grp);
/* 299 */           } catch (ClassNotFoundException e2) {
/* 301 */             throw (IllegalArgumentException)(new IllegalArgumentException("Unknown type: " + grp)).initCause(e);
/*     */           } 
/*     */         } 
/*     */       }  
/* 309 */     if (type == null && 
/* 310 */       "geom".equals(name))
/* 311 */       type = Geometry.class; 
/* 315 */     if (type == null)
/* 316 */       type = Object.class; 
/* 319 */     grp = m.group(3);
/* 320 */     if (grp != null)
/* 321 */       min = !"".equals(grp) ? Integer.parseInt(grp) : -1; 
/* 324 */     grp = m.group(4);
/* 325 */     if (grp != null)
/* 326 */       max = !"".equals(grp) ? Integer.parseInt(grp) : -1; 
/* 329 */     return (Parameter)new Parameter(name, type, min, max);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FunctionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */