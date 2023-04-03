/*     */ package org.apache.commons.configuration.interpol;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.configuration.AbstractConfiguration;
/*     */ import org.apache.commons.configuration.ConfigurationRuntimeException;
/*     */ import org.apache.commons.jexl.Expression;
/*     */ import org.apache.commons.jexl.ExpressionFactory;
/*     */ import org.apache.commons.jexl.JexlContext;
/*     */ import org.apache.commons.jexl.JexlHelper;
/*     */ import org.apache.commons.lang.ClassUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.lang.text.StrLookup;
/*     */ import org.apache.commons.lang.text.StrSubstitutor;
/*     */ 
/*     */ public class ExprLookup extends StrLookup {
/*     */   private static final String CLASS = "Class:";
/*     */   
/*     */   private static final String DEFAULT_PREFIX = "$[";
/*     */   
/*     */   private static final String DEFAULT_SUFFIX = "]";
/*     */   
/*     */   private AbstractConfiguration configuration;
/*     */   
/*  83 */   private JexlContext context = JexlHelper.createContext();
/*     */   
/*  86 */   private String prefixMatcher = "$[";
/*     */   
/*  89 */   private String suffixMatcher = "]";
/*     */   
/*     */   public ExprLookup() {}
/*     */   
/*     */   public ExprLookup(Variables list) {
/* 105 */     setVariables(list);
/*     */   }
/*     */   
/*     */   public ExprLookup(Variables list, String prefix, String suffix) {
/* 116 */     this(list);
/* 117 */     setVariablePrefixMatcher(prefix);
/* 118 */     setVariableSuffixMatcher(suffix);
/*     */   }
/*     */   
/*     */   public void setVariablePrefixMatcher(String prefix) {
/* 128 */     this.prefixMatcher = prefix;
/*     */   }
/*     */   
/*     */   public void setVariableSuffixMatcher(String suffix) {
/* 139 */     this.suffixMatcher = suffix;
/*     */   }
/*     */   
/*     */   public void setVariables(Variables list) {
/* 148 */     Iterator iter = list.iterator();
/* 149 */     while (iter.hasNext()) {
/* 151 */       Variable var = (Variable)iter.next();
/* 152 */       this.context.getVars().put(var.getName(), var.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public Variables getVariables() {
/* 162 */     return null;
/*     */   }
/*     */   
/*     */   public void setConfiguration(AbstractConfiguration config) {
/* 171 */     this.configuration = config;
/*     */   }
/*     */   
/*     */   public String lookup(String var) {
/* 181 */     ConfigurationInterpolator interp = this.configuration.getInterpolator();
/* 182 */     StrSubstitutor subst = new StrSubstitutor(interp, this.prefixMatcher, this.suffixMatcher, '$');
/* 185 */     String result = subst.replace(var);
/*     */     try {
/* 189 */       Expression exp = ExpressionFactory.createExpression(result);
/* 190 */       result = (String)exp.evaluate(this.context);
/* 192 */     } catch (Exception e) {
/* 194 */       this.configuration.getLogger().debug("Error encountered evaluating " + result, e);
/*     */     } 
/* 197 */     return result;
/*     */   }
/*     */   
/*     */   public static class Variables extends ArrayList {
/*     */     public ExprLookup.Variable getVariable() {
/* 214 */       if (size() > 0)
/* 216 */         return (ExprLookup.Variable)get(size() - 1); 
/* 220 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Variable {
/*     */     private String key;
/*     */     
/*     */     private Object value;
/*     */     
/*     */     public Variable() {}
/*     */     
/*     */     public Variable(String name, Object value) {
/* 244 */       setName(name);
/* 245 */       setValue(value);
/*     */     }
/*     */     
/*     */     public String getName() {
/* 250 */       return this.key;
/*     */     }
/*     */     
/*     */     public void setName(String name) {
/* 255 */       this.key = name;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 260 */       return this.value;
/*     */     }
/*     */     
/*     */     public void setValue(Object value) throws ConfigurationRuntimeException {
/*     */       try {
/* 267 */         if (!(value instanceof String)) {
/* 269 */           this.value = value;
/*     */           return;
/*     */         } 
/* 272 */         String val = (String)value;
/* 273 */         String name = StringUtils.removeStartIgnoreCase(val, "Class:");
/* 274 */         Class clazz = ClassUtils.getClass(name);
/* 275 */         if (name.length() == val.length()) {
/* 277 */           this.value = clazz.newInstance();
/*     */         } else {
/* 281 */           this.value = clazz;
/*     */         } 
/* 284 */       } catch (Exception e) {
/* 286 */         throw new ConfigurationRuntimeException("Unable to create " + value, e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\interpol\ExprLookup.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */