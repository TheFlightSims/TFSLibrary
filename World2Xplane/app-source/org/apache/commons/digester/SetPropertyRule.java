/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import java.beans.PropertyDescriptor;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.commons.beanutils.DynaBean;
/*     */ import org.apache.commons.beanutils.DynaProperty;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class SetPropertyRule extends Rule {
/*     */   protected String name;
/*     */   
/*     */   protected String value;
/*     */   
/*     */   public SetPropertyRule(Digester digester, String name, String value) {
/*  58 */     this(name, value);
/*     */   }
/*     */   
/*     */   public SetPropertyRule(String name, String value) {
/*  84 */     this.name = null;
/*  90 */     this.value = null;
/*     */     this.name = name;
/*     */     this.value = value;
/*     */   }
/*     */   
/*     */   public void begin(Attributes attributes) throws Exception {
/* 106 */     if (attributes.getLength() == 0)
/*     */       return; 
/* 111 */     String actualName = null;
/* 112 */     String actualValue = null;
/* 113 */     for (int i = 0; i < attributes.getLength(); i++) {
/* 114 */       String name = attributes.getLocalName(i);
/* 115 */       if ("".equals(name))
/* 116 */         name = attributes.getQName(i); 
/* 118 */       String value = attributes.getValue(i);
/* 119 */       if (name.equals(this.name)) {
/* 120 */         actualName = value;
/* 121 */       } else if (name.equals(this.value)) {
/* 122 */         actualValue = value;
/*     */       } 
/*     */     } 
/* 127 */     Object top = this.digester.peek();
/* 130 */     if (this.digester.log.isDebugEnabled())
/* 131 */       this.digester.log.debug("[SetPropertyRule]{" + this.digester.match + "} Set " + top.getClass().getName() + " property " + actualName + " to " + actualValue); 
/* 141 */     if (top instanceof DynaBean) {
/* 142 */       DynaProperty desc = ((DynaBean)top).getDynaClass().getDynaProperty(actualName);
/* 144 */       if (desc == null)
/* 145 */         throw new NoSuchMethodException("Bean has no property named " + actualName); 
/*     */     } else {
/* 149 */       PropertyDescriptor desc = PropertyUtils.getPropertyDescriptor(top, actualName);
/* 151 */       if (desc == null)
/* 152 */         throw new NoSuchMethodException("Bean has no property named " + actualName); 
/*     */     } 
/* 158 */     BeanUtils.setProperty(top, actualName, actualValue);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 168 */     StringBuffer sb = new StringBuffer("SetPropertyRule[");
/* 169 */     sb.append("name=");
/* 170 */     sb.append(this.name);
/* 171 */     sb.append(", value=");
/* 172 */     sb.append(this.value);
/* 173 */     sb.append("]");
/* 174 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\SetPropertyRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */