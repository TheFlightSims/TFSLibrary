/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.filter.FunctionExpression;
/*     */ import org.geotools.filter.FunctionFactory;
/*     */ import org.geotools.filter.FunctionImpl;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ 
/*     */ public class DefaultFunctionFactory implements FunctionFactory {
/*  55 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter");
/*     */   
/*  56 */   private FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory(null);
/*     */   
/*     */   private Map<Name, FunctionDescriptor> functionCache;
/*     */   
/*     */   public List<FunctionName> getFunctionNames() {
/*  61 */     ArrayList<FunctionName> list = new ArrayList<FunctionName>(functionCache().size());
/*  62 */     for (FunctionDescriptor fd : functionCache().values())
/*  63 */       list.add(fd.name); 
/*  68 */     return list;
/*     */   }
/*     */   
/*     */   public Function function(String name, List<Expression> parameters, Literal fallback) {
/*  72 */     return function((Name)new NameImpl(name), parameters, fallback);
/*     */   }
/*     */   
/*     */   public Function function(Name name, List<Expression> parameters, Literal fallback) {
/*  78 */     Name key = functionName(name);
/*  79 */     FunctionDescriptor fd = functionCache().get(key);
/*  80 */     if (fd == null) {
/*  81 */       fd = functionCache().get(name);
/*  82 */       if (fd == null)
/*  84 */         return null; 
/*     */     } 
/*     */     try {
/*  90 */       Function newFunction = fd.newFunction(parameters, fallback);
/*  91 */       return newFunction;
/*  93 */     } catch (Exception e) {
/*  94 */       LOGGER.log(Level.FINER, "Unable to create function " + name + "Function", e);
/*  97 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Map<Name, FunctionDescriptor> functionCache() {
/* 101 */     if (this.functionCache == null)
/* 102 */       synchronized (this) {
/* 103 */         if (this.functionCache == null)
/* 104 */           this.functionCache = loadFunctions(); 
/*     */       }  
/* 108 */     return this.functionCache;
/*     */   }
/*     */   
/*     */   private FunctionName getFunctionName(Function function) {
/* 112 */     String name = function.getName();
/* 113 */     FunctionName functionName = function.getFunctionName();
/* 115 */     if (functionName == null && function instanceof org.geotools.filter.FunctionExpressionImpl)
/* 116 */       functionName = function.getFunctionName(); 
/* 118 */     if (functionName == null) {
/*     */       int argc;
/* 120 */       if (function instanceof FunctionExpression) {
/* 121 */         argc = ((FunctionExpression)function).getArgCount();
/*     */       } else {
/* 124 */         argc = function.getParameters().size();
/*     */       } 
/* 126 */       functionName = this.filterFactory.functionName(name, argc);
/* 129 */     } else if (!functionName.getName().equals(name)) {
/* 130 */       LOGGER.warning(function.getClass() + " has name conflict betwee '" + name + "' and '" + functionName.getName() + "'");
/*     */     } 
/* 133 */     return functionName;
/*     */   }
/*     */   
/*     */   private Map<Name, FunctionDescriptor> loadFunctions() {
/* 136 */     Map<Name, FunctionDescriptor> functionMap = new HashMap<Name, FunctionDescriptor>();
/* 138 */     Set<Function> functions = CommonFactoryFinder.getFunctions(null);
/* 139 */     for (Iterator<Function> i = functions.iterator(); i.hasNext(); ) {
/* 140 */       Function function = i.next();
/* 141 */       FunctionName functionName = getFunctionName(function);
/* 142 */       Name name = functionName.getFunctionName();
/* 144 */       FunctionDescriptor fd = new FunctionDescriptor(functionName, function.getClass());
/* 149 */       Name key = functionName(name);
/* 150 */       if (functionMap.containsKey(key)) {
/* 152 */         FunctionDescriptor conflict = functionMap.get(key);
/* 153 */         LOGGER.warning("Function " + key + " clash between " + conflict.clazz.getSimpleName() + " and " + function.getClass().getSimpleName());
/*     */       } 
/* 155 */       functionMap.put(key, fd);
/*     */     } 
/* 157 */     return functionMap;
/*     */   }
/*     */   
/*     */   private Name functionName(Name functionName) {
/* 161 */     String name = functionName.getLocalPart();
/* 164 */     int index = -1;
/* 166 */     if ((index = name.indexOf("Function")) != -1)
/* 167 */       name = name.substring(0, index); 
/* 171 */     name = name.toLowerCase().trim();
/* 179 */     return (Name)new NameImpl(functionName.getNamespaceURI(), functionName.getSeparator(), name);
/*     */   }
/*     */   
/*     */   static class FunctionDescriptor {
/*     */     FunctionName name;
/*     */     
/*     */     Class clazz;
/*     */     
/*     */     FunctionDescriptor(FunctionName name, Class clazz) {
/* 187 */       this.name = name;
/* 188 */       this.clazz = clazz;
/*     */     }
/*     */     
/*     */     Function newFunction(List<Expression> parameters, Literal fallback) throws Exception {
/* 193 */       if (FunctionExpression.class.isAssignableFrom(this.clazz)) {
/* 194 */         FunctionExpression function = this.clazz.newInstance();
/* 195 */         if (parameters != null)
/* 196 */           function.setParameters(parameters); 
/* 199 */         if (fallback != null && function instanceof ClassificationFunction) {
/* 200 */           ClassificationFunction classification = (ClassificationFunction)function;
/* 201 */           classification.setFallbackValue(fallback);
/*     */         } 
/* 203 */         return (Function)function;
/*     */       } 
/* 206 */       if (FunctionImpl.class.isAssignableFrom(this.clazz)) {
/* 207 */         FunctionImpl function = this.clazz.newInstance();
/* 208 */         if (parameters != null)
/* 209 */           function.setParameters(parameters); 
/* 211 */         if (fallback != null)
/* 212 */           function.setFallbackValue(fallback); 
/* 213 */         return (Function)function;
/*     */       } 
/* 217 */       Constructor<Function> constructor = this.clazz.getConstructor(new Class[] { List.class, Literal.class });
/* 218 */       return constructor.newInstance(new Object[] { parameters, fallback });
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\DefaultFunctionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */