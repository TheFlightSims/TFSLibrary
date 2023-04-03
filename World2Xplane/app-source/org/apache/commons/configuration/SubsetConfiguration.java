/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ import org.apache.commons.collections.iterators.TransformIterator;
/*     */ import org.apache.commons.configuration.interpol.ConfigurationInterpolator;
/*     */ 
/*     */ public class SubsetConfiguration extends AbstractConfiguration {
/*     */   protected Configuration parent;
/*     */   
/*     */   protected String prefix;
/*     */   
/*     */   protected String delimiter;
/*     */   
/*     */   public SubsetConfiguration(Configuration parent, String prefix) {
/*  56 */     this.parent = parent;
/*  57 */     this.prefix = prefix;
/*     */   }
/*     */   
/*     */   public SubsetConfiguration(Configuration parent, String prefix, String delimiter) {
/*  69 */     this.parent = parent;
/*  70 */     this.prefix = prefix;
/*  71 */     this.delimiter = delimiter;
/*     */   }
/*     */   
/*     */   protected String getParentKey(String key) {
/*  83 */     if ("".equals(key) || key == null)
/*  85 */       return this.prefix; 
/*  89 */     return (this.delimiter == null) ? (this.prefix + key) : (this.prefix + this.delimiter + key);
/*     */   }
/*     */   
/*     */   protected String getChildKey(String key) {
/* 102 */     if (!key.startsWith(this.prefix))
/* 104 */       throw new IllegalArgumentException("The parent key '" + key + "' is not in the subset."); 
/* 108 */     String modifiedKey = null;
/* 109 */     if (key.length() == this.prefix.length()) {
/* 111 */       modifiedKey = "";
/*     */     } else {
/* 115 */       int i = this.prefix.length() + ((this.delimiter != null) ? this.delimiter.length() : 0);
/* 116 */       modifiedKey = key.substring(i);
/*     */     } 
/* 119 */     return modifiedKey;
/*     */   }
/*     */   
/*     */   public Configuration getParent() {
/* 130 */     return this.parent;
/*     */   }
/*     */   
/*     */   public String getPrefix() {
/* 140 */     return this.prefix;
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix) {
/* 150 */     this.prefix = prefix;
/*     */   }
/*     */   
/*     */   public Configuration subset(String prefix) {
/* 155 */     return this.parent.subset(getParentKey(prefix));
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 160 */     return !getKeys().hasNext();
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/* 165 */     return this.parent.containsKey(getParentKey(key));
/*     */   }
/*     */   
/*     */   public void addPropertyDirect(String key, Object value) {
/* 170 */     this.parent.addProperty(getParentKey(key), value);
/*     */   }
/*     */   
/*     */   protected void clearPropertyDirect(String key) {
/* 175 */     this.parent.clearProperty(getParentKey(key));
/*     */   }
/*     */   
/*     */   public Object getProperty(String key) {
/* 180 */     return this.parent.getProperty(getParentKey(key));
/*     */   }
/*     */   
/*     */   public Iterator getKeys(String prefix) {
/* 185 */     return (Iterator)new TransformIterator(this.parent.getKeys(getParentKey(prefix)), new Transformer() {
/*     */           private final SubsetConfiguration this$0;
/*     */           
/*     */           public Object transform(Object obj) {
/* 189 */             return SubsetConfiguration.this.getChildKey((String)obj);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Iterator getKeys() {
/* 196 */     return (Iterator)new TransformIterator(this.parent.getKeys(this.prefix), new Transformer() {
/*     */           private final SubsetConfiguration this$0;
/*     */           
/*     */           public Object transform(Object obj) {
/* 200 */             return SubsetConfiguration.this.getChildKey((String)obj);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   protected Object interpolate(Object base) {
/* 207 */     if (this.delimiter == null && "".equals(this.prefix))
/* 209 */       return super.interpolate(base); 
/* 213 */     SubsetConfiguration config = new SubsetConfiguration(this.parent, "");
/* 214 */     ConfigurationInterpolator interpolator = config.getInterpolator();
/* 215 */     getInterpolator().registerLocalLookups(interpolator);
/* 216 */     if (this.parent instanceof AbstractConfiguration)
/* 218 */       interpolator.setParentInterpolator(((AbstractConfiguration)this.parent).getInterpolator()); 
/* 220 */     return config.interpolate(base);
/*     */   }
/*     */   
/*     */   protected String interpolate(String base) {
/* 226 */     return super.interpolate(base);
/*     */   }
/*     */   
/*     */   public void setThrowExceptionOnMissing(boolean throwExceptionOnMissing) {
/* 236 */     if (this.parent instanceof AbstractConfiguration) {
/* 238 */       ((AbstractConfiguration)this.parent).setThrowExceptionOnMissing(throwExceptionOnMissing);
/*     */     } else {
/* 242 */       super.setThrowExceptionOnMissing(throwExceptionOnMissing);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isThrowExceptionOnMissing() {
/* 253 */     if (this.parent instanceof AbstractConfiguration)
/* 255 */       return ((AbstractConfiguration)this.parent).isThrowExceptionOnMissing(); 
/* 259 */     return super.isThrowExceptionOnMissing();
/*     */   }
/*     */   
/*     */   public char getListDelimiter() {
/* 272 */     return (this.parent instanceof AbstractConfiguration) ? ((AbstractConfiguration)this.parent).getListDelimiter() : super.getListDelimiter();
/*     */   }
/*     */   
/*     */   public void setListDelimiter(char delim) {
/* 286 */     if (this.parent instanceof AbstractConfiguration) {
/* 288 */       ((AbstractConfiguration)this.parent).setListDelimiter(delim);
/*     */     } else {
/* 292 */       super.setListDelimiter(delim);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isDelimiterParsingDisabled() {
/* 307 */     return (this.parent instanceof AbstractConfiguration) ? ((AbstractConfiguration)this.parent).isDelimiterParsingDisabled() : super.isDelimiterParsingDisabled();
/*     */   }
/*     */   
/*     */   public void setDelimiterParsingDisabled(boolean delimiterParsingDisabled) {
/* 322 */     if (this.parent instanceof AbstractConfiguration) {
/* 324 */       ((AbstractConfiguration)this.parent).setDelimiterParsingDisabled(delimiterParsingDisabled);
/*     */     } else {
/* 329 */       super.setDelimiterParsingDisabled(delimiterParsingDisabled);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\SubsetConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */