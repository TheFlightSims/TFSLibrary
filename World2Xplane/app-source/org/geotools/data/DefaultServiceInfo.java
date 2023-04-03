/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.net.URI;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DefaultServiceInfo implements ServiceInfo, Serializable {
/*     */   private static final long serialVersionUID = 7975308744804800859L;
/*     */   
/*     */   protected String description;
/*     */   
/*     */   protected Set<String> keywords;
/*     */   
/*     */   protected URI publisher;
/*     */   
/*     */   protected URI schema;
/*     */   
/*     */   protected String title;
/*     */   
/*     */   private URI source;
/*     */   
/*     */   public DefaultServiceInfo() {}
/*     */   
/*     */   public DefaultServiceInfo(ServiceInfo copy) {
/*  47 */     this.description = copy.getDescription();
/*  48 */     this.keywords = new HashSet<String>();
/*  49 */     if (copy.getKeywords() != null)
/*  50 */       this.keywords.addAll(copy.getKeywords()); 
/*  52 */     this.publisher = copy.getPublisher();
/*  53 */     this.schema = copy.getSchema();
/*  54 */     this.title = copy.getTitle();
/*  55 */     this.source = copy.getSource();
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  61 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/*  67 */     this.description = description;
/*     */   }
/*     */   
/*     */   public Set<String> getKeywords() {
/*  73 */     return this.keywords;
/*     */   }
/*     */   
/*     */   public void setKeywords(Set<String> keywords) {
/*  79 */     this.keywords = keywords;
/*     */   }
/*     */   
/*     */   public URI getPublisher() {
/*  85 */     return this.publisher;
/*     */   }
/*     */   
/*     */   public void setPublisher(URI publisher) {
/*  91 */     this.publisher = publisher;
/*     */   }
/*     */   
/*     */   public URI getSchema() {
/*  97 */     return this.schema;
/*     */   }
/*     */   
/*     */   public void setSchema(URI schema) {
/* 103 */     this.schema = schema;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/* 109 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/* 115 */     this.title = title;
/*     */   }
/*     */   
/*     */   public URI getSource() {
/* 122 */     return this.source;
/*     */   }
/*     */   
/*     */   public void setSource(URI source) {
/* 128 */     this.source = source;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 132 */     StringBuffer buf = new StringBuffer();
/* 133 */     buf.append("ServiceInfo ");
/* 134 */     if (this.source != null)
/* 135 */       buf.append(this.source); 
/* 137 */     if (this.title != null) {
/* 138 */       buf.append("\n title=");
/* 139 */       buf.append(this.title);
/*     */     } 
/* 141 */     if (this.publisher != null) {
/* 142 */       buf.append("\n publisher=");
/* 143 */       buf.append(this.publisher);
/*     */     } 
/* 145 */     if (this.publisher != null) {
/* 146 */       buf.append("\n schema=");
/* 147 */       buf.append(this.schema);
/*     */     } 
/* 149 */     if (this.keywords != null) {
/* 150 */       buf.append("\n keywords=");
/* 151 */       buf.append(this.keywords);
/*     */     } 
/* 153 */     if (this.description != null) {
/* 154 */       buf.append("\n description=");
/* 155 */       buf.append(this.description);
/*     */     } 
/* 157 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DefaultServiceInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */