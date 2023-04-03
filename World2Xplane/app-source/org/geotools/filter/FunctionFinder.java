/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ 
/*     */ public class FunctionFinder {
/*  53 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter");
/*     */   
/*     */   private volatile Map<Name, FunctionFactory> functionFactoryCache;
/*     */   
/*     */   public FunctionFinder(Hints hints) {}
/*     */   
/*     */   public List<FunctionName> getAllFunctionDescriptions() {
/*  66 */     Set<FunctionFactory> functionFactories = CommonFactoryFinder.getFunctionFactories(null);
/*  67 */     List<FunctionName> allFunctionDescriptions = new ArrayList<FunctionName>();
/*  69 */     for (FunctionFactory factory : functionFactories) {
/*  70 */       List<FunctionName> functionNames = factory.getFunctionNames();
/*  71 */       allFunctionDescriptions.addAll(functionNames);
/*     */     } 
/*  73 */     Collections.sort(allFunctionDescriptions, new Comparator<FunctionName>() {
/*     */           public int compare(FunctionName o1, FunctionName o2) {
/*  75 */             if (o1 == null && o2 == null)
/*  75 */               return 0; 
/*  76 */             if (o1 == null && o2 != null)
/*  76 */               return 1; 
/*  77 */             if (o1 != null && o2 == null)
/*  77 */               return -1; 
/*  79 */             return o1.getName().compareTo(o2.getName());
/*     */           }
/*     */         });
/*  82 */     return Collections.unmodifiableList(allFunctionDescriptions);
/*     */   }
/*     */   
/*     */   public FunctionName findFunctionDescription(String name) {
/*  90 */     return findFunctionDescription((Name)new NameImpl(name));
/*     */   }
/*     */   
/*     */   public FunctionName findFunctionDescription(Name name) {
/*  98 */     Set<FunctionFactory> functionFactories = CommonFactoryFinder.getFunctionFactories(null);
/*  99 */     for (FunctionFactory factory : functionFactories) {
/* 100 */       List<FunctionName> functionNames = factory.getFunctionNames();
/* 101 */       for (FunctionName description : functionNames) {
/* 102 */         if (description.getFunctionName().equals(name))
/* 103 */           return description; 
/*     */       } 
/*     */     } 
/* 107 */     return null;
/*     */   }
/*     */   
/*     */   public Function findFunction(String name) {
/* 111 */     return findFunction((Name)new NameImpl(name));
/*     */   }
/*     */   
/*     */   public Function findFunction(Name name) {
/* 115 */     return findFunction(name, (List<Expression>)null);
/*     */   }
/*     */   
/*     */   public Function findFunction(String name, List<Expression> parameters) {
/* 127 */     return findFunction(toName(name), parameters);
/*     */   }
/*     */   
/*     */   Name toName(String name) {
/* 131 */     if (name.contains(":")) {
/* 132 */       String[] split = name.split(":");
/* 133 */       return (Name)new NameImpl(split[0], ":", split[1]);
/*     */     } 
/* 135 */     if (name.contains("#")) {
/* 136 */       String[] split = name.split("#");
/* 137 */       return (Name)new NameImpl(split[0], "#", split[1]);
/*     */     } 
/* 140 */     return (Name)new NameImpl(name);
/*     */   }
/*     */   
/*     */   public Function findFunction(Name name, List<Expression> parameters) {
/* 152 */     return findFunction(name, parameters, (Literal)null);
/*     */   }
/*     */   
/*     */   public Function findFunction(String name, List<Expression> parameters, Literal fallback) {
/* 168 */     return findFunction((Name)new NameImpl(name), parameters, fallback);
/*     */   }
/*     */   
/*     */   public Function findFunction(Name name, List<Expression> parameters, Literal fallback) {
/*     */     NameImpl nameImpl;
/* 185 */     Function f = findFunctionInternal(name, parameters, fallback);
/* 187 */     if (f == null)
/* 189 */       if (name.getLocalPart().endsWith("Function")) {
/* 190 */         String local = name.getLocalPart();
/* 191 */         local = local.substring(0, local.length() - "Function".length());
/* 192 */         nameImpl = new NameImpl(name.getNamespaceURI(), name.getSeparator(), local);
/* 193 */         f = findFunctionInternal((Name)nameImpl, parameters, fallback);
/*     */       }  
/* 196 */     if (f == null && fallback != null)
/* 197 */       return new FallbackFunction((Name)nameImpl, parameters, fallback); 
/* 201 */     if (f != null)
/* 202 */       return f; 
/* 205 */     throw new RuntimeException("Unable to find function " + nameImpl);
/*     */   }
/*     */   
/*     */   Function findFunctionInternal(Name name, List<Expression> parameters, Literal fallback) {
/* 210 */     if (this.functionFactoryCache == null)
/* 211 */       synchronized (this) {
/* 212 */         if (this.functionFactoryCache == null)
/* 213 */           this.functionFactoryCache = lookupFunctions(); 
/*     */       }  
/* 218 */     if (this.functionFactoryCache.containsKey(name)) {
/* 219 */       FunctionFactory functionFactory = this.functionFactoryCache.get(name);
/* 220 */       return functionFactory.function(name, parameters, fallback);
/*     */     } 
/* 225 */     Function f = null;
/* 226 */     for (FunctionFactory ff : CommonFactoryFinder.getFunctionFactories(null)) {
/* 227 */       f = ff.function(name, parameters, fallback);
/* 228 */       if (f != null)
/* 228 */         return f; 
/*     */     } 
/* 231 */     return null;
/*     */   }
/*     */   
/*     */   private HashMap<Name, FunctionFactory> lookupFunctions() {
/* 236 */     HashMap<Name, FunctionFactory> result = new HashMap<Name, FunctionFactory>();
/* 238 */     Set<FunctionFactory> functionFactories = CommonFactoryFinder.getFunctionFactories(null);
/* 239 */     for (FunctionFactory ff : functionFactories) {
/* 240 */       for (FunctionName functionName : ff.getFunctionNames())
/* 241 */         result.put(functionName.getFunctionName(), ff); 
/*     */     } 
/* 245 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FunctionFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */