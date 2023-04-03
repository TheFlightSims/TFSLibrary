/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import java.beans.PropertyDescriptor;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.commons.beanutils.DynaBean;
/*     */ import org.apache.commons.beanutils.DynaProperty;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ 
/*     */ public class BeanPropertySetterRule extends Rule {
/*     */   protected String propertyName;
/*     */   
/*     */   protected String bodyText;
/*     */   
/*     */   public BeanPropertySetterRule(Digester digester, String propertyName) {
/*  61 */     this(propertyName);
/*     */   }
/*     */   
/*     */   public BeanPropertySetterRule(Digester digester) {
/*  78 */     this();
/*     */   }
/*     */   
/*     */   public BeanPropertySetterRule(String propertyName) {
/* 111 */     this.propertyName = null;
/* 117 */     this.bodyText = null;
/*     */     this.propertyName = propertyName;
/*     */   }
/*     */   
/*     */   public BeanPropertySetterRule() {
/*     */     this((String)null);
/*     */   }
/*     */   
/*     */   public void body(String namespace, String name, String text) throws Exception {
/* 137 */     if (this.digester.log.isDebugEnabled())
/* 138 */       this.digester.log.debug("[BeanPropertySetterRule]{" + this.digester.match + "} Called with text '" + text + "'"); 
/* 142 */     this.bodyText = text.trim();
/*     */   }
/*     */   
/*     */   public void end(String namespace, String name) throws Exception {
/* 161 */     String property = this.propertyName;
/* 163 */     if (property == null)
/* 166 */       property = name; 
/* 170 */     Object top = this.digester.peek();
/* 173 */     if (this.digester.log.isDebugEnabled())
/* 174 */       this.digester.log.debug("[BeanPropertySetterRule]{" + this.digester.match + "} Set " + top.getClass().getName() + " property " + property + " with text " + this.bodyText); 
/* 181 */     if (top instanceof DynaBean) {
/* 182 */       DynaProperty desc = ((DynaBean)top).getDynaClass().getDynaProperty(property);
/* 184 */       if (desc == null)
/* 185 */         throw new NoSuchMethodException("Bean has no property named " + property); 
/*     */     } else {
/* 189 */       PropertyDescriptor desc = PropertyUtils.getPropertyDescriptor(top, property);
/* 191 */       if (desc == null)
/* 192 */         throw new NoSuchMethodException("Bean has no property named " + property); 
/*     */     } 
/* 198 */     BeanUtils.setProperty(top, property, this.bodyText);
/*     */   }
/*     */   
/*     */   public void finish() throws Exception {
/* 208 */     this.bodyText = null;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 218 */     StringBuffer sb = new StringBuffer("BeanPropertySetterRule[");
/* 219 */     sb.append("propertyName=");
/* 220 */     sb.append(this.propertyName);
/* 221 */     sb.append("]");
/* 222 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\BeanPropertySetterRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */