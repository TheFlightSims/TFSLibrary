/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class SetPropertiesRule extends Rule {
/*     */   private String[] attributeNames;
/*     */   
/*     */   private String[] propertyNames;
/*     */   
/*     */   public SetPropertiesRule(Digester digester) {
/*  57 */     this();
/*     */   }
/*     */   
/*     */   public SetPropertiesRule() {}
/*     */   
/*     */   public SetPropertiesRule(String attributeName, String propertyName) {
/*  82 */     this.attributeNames = new String[1];
/*  83 */     this.attributeNames[0] = attributeName;
/*  84 */     this.propertyNames = new String[1];
/*  85 */     this.propertyNames[0] = propertyName;
/*     */   }
/*     */   
/*     */   public SetPropertiesRule(String[] attributeNames, String[] propertyNames) {
/* 127 */     this.attributeNames = new String[attributeNames.length];
/*     */     int i, size;
/* 128 */     for (i = 0, size = attributeNames.length; i < size; i++)
/* 129 */       this.attributeNames[i] = attributeNames[i]; 
/* 132 */     this.propertyNames = new String[propertyNames.length];
/* 133 */     for (i = 0, size = propertyNames.length; i < size; i++)
/* 134 */       this.propertyNames[i] = propertyNames[i]; 
/*     */   }
/*     */   
/*     */   private boolean ignoreMissingProperty = true;
/*     */   
/*     */   public void begin(Attributes attributes) throws Exception {
/* 167 */     HashMap values = new HashMap();
/* 170 */     int attNamesLength = 0;
/* 171 */     if (this.attributeNames != null)
/* 172 */       attNamesLength = this.attributeNames.length; 
/* 174 */     int propNamesLength = 0;
/* 175 */     if (this.propertyNames != null)
/* 176 */       propNamesLength = this.propertyNames.length; 
/* 180 */     for (int i = 0; i < attributes.getLength(); i++) {
/* 181 */       String name = attributes.getLocalName(i);
/* 182 */       if ("".equals(name))
/* 183 */         name = attributes.getQName(i); 
/* 185 */       String value = attributes.getValue(i);
/* 188 */       for (int n = 0; n < attNamesLength; n++) {
/* 189 */         if (name.equals(this.attributeNames[n])) {
/* 190 */           if (n < propNamesLength) {
/* 192 */             name = this.propertyNames[n];
/*     */             break;
/*     */           } 
/* 197 */           name = null;
/*     */           break;
/*     */         } 
/*     */       } 
/* 203 */       if (this.digester.log.isDebugEnabled())
/* 204 */         this.digester.log.debug("[SetPropertiesRule]{" + this.digester.match + "} Setting property '" + name + "' to '" + value + "'"); 
/* 209 */       if (!this.ignoreMissingProperty && name != null) {
/* 229 */         Object object = this.digester.peek();
/* 230 */         boolean test = PropertyUtils.isWriteable(object, name);
/* 231 */         if (!test)
/* 232 */           throw new NoSuchMethodException("Property " + name + " can't be set"); 
/*     */       } 
/* 235 */       if (name != null)
/* 236 */         values.put(name, value); 
/*     */     } 
/* 241 */     Object top = this.digester.peek();
/* 242 */     if (this.digester.log.isDebugEnabled())
/* 243 */       if (top != null) {
/* 244 */         this.digester.log.debug("[SetPropertiesRule]{" + this.digester.match + "} Set " + top.getClass().getName() + " properties");
/*     */       } else {
/* 248 */         this.digester.log.debug("[SetPropertiesRule]{" + this.digester.match + "} Set NULL properties");
/*     */       }  
/* 252 */     BeanUtils.populate(top, values);
/*     */   }
/*     */   
/*     */   public void addAlias(String attributeName, String propertyName) {
/* 267 */     if (this.attributeNames == null) {
/* 269 */       this.attributeNames = new String[1];
/* 270 */       this.attributeNames[0] = attributeName;
/* 271 */       this.propertyNames = new String[1];
/* 272 */       this.propertyNames[0] = propertyName;
/*     */     } else {
/* 275 */       int length = this.attributeNames.length;
/* 276 */       String[] tempAttributes = new String[length + 1];
/* 277 */       for (int i = 0; i < length; i++)
/* 278 */         tempAttributes[i] = this.attributeNames[i]; 
/* 280 */       tempAttributes[length] = attributeName;
/* 282 */       String[] tempProperties = new String[length + 1];
/* 283 */       for (int j = 0; j < length && j < this.propertyNames.length; j++)
/* 284 */         tempProperties[j] = this.propertyNames[j]; 
/* 286 */       tempProperties[length] = propertyName;
/* 288 */       this.propertyNames = tempProperties;
/* 289 */       this.attributeNames = tempAttributes;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 299 */     StringBuffer sb = new StringBuffer("SetPropertiesRule[");
/* 300 */     sb.append("]");
/* 301 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public boolean isIgnoreMissingProperty() {
/* 315 */     return this.ignoreMissingProperty;
/*     */   }
/*     */   
/*     */   public void setIgnoreMissingProperty(boolean ignoreMissingProperty) {
/* 328 */     this.ignoreMissingProperty = ignoreMissingProperty;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\SetPropertiesRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */