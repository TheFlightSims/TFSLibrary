/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class ObjectCreateRule extends Rule {
/*     */   protected String attributeName;
/*     */   
/*     */   protected String className;
/*     */   
/*     */   public ObjectCreateRule(Digester digester, String className) {
/*  49 */     this(className);
/*     */   }
/*     */   
/*     */   public ObjectCreateRule(Digester digester, Class clazz) {
/*  65 */     this(clazz);
/*     */   }
/*     */   
/*     */   public ObjectCreateRule(Digester digester, String className, String attributeName) {
/*  85 */     this(className, attributeName);
/*     */   }
/*     */   
/*     */   public ObjectCreateRule(Digester digester, String attributeName, Class clazz) {
/* 106 */     this(attributeName, clazz);
/*     */   }
/*     */   
/*     */   public ObjectCreateRule(String className) {
/* 117 */     this(className, (String)null);
/*     */   }
/*     */   
/*     */   public ObjectCreateRule(Class clazz) {
/* 129 */     this(clazz.getName(), (String)null);
/*     */   }
/*     */   
/*     */   public ObjectCreateRule(String className, String attributeName) {
/* 172 */     this.attributeName = null;
/* 178 */     this.className = null;
/*     */     this.className = className;
/*     */     this.attributeName = attributeName;
/*     */   }
/*     */   
/*     */   public ObjectCreateRule(String attributeName, Class clazz) {
/*     */     this(clazz.getName(), attributeName);
/*     */   }
/*     */   
/*     */   public void begin(Attributes attributes) throws Exception {
/* 192 */     String realClassName = this.className;
/* 193 */     if (this.attributeName != null) {
/* 194 */       String value = attributes.getValue(this.attributeName);
/* 195 */       if (value != null)
/* 196 */         realClassName = value; 
/*     */     } 
/* 199 */     if (this.digester.log.isDebugEnabled())
/* 200 */       this.digester.log.debug("[ObjectCreateRule]{" + this.digester.match + "}New " + realClassName); 
/* 205 */     Class clazz = this.digester.getClassLoader().loadClass(realClassName);
/* 206 */     Object instance = clazz.newInstance();
/* 207 */     this.digester.push(instance);
/*     */   }
/*     */   
/*     */   public void end() throws Exception {
/* 217 */     Object top = this.digester.pop();
/* 218 */     if (this.digester.log.isDebugEnabled())
/* 219 */       this.digester.log.debug("[ObjectCreateRule]{" + this.digester.match + "} Pop " + top.getClass().getName()); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 231 */     StringBuffer sb = new StringBuffer("ObjectCreateRule[");
/* 232 */     sb.append("className=");
/* 233 */     sb.append(this.className);
/* 234 */     sb.append(", attributeName=");
/* 235 */     sb.append(this.attributeName);
/* 236 */     sb.append("]");
/* 237 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\ObjectCreateRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */