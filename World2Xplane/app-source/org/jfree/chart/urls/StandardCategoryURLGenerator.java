/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.net.URLEncoder;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class StandardCategoryURLGenerator implements CategoryURLGenerator, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 2276668053074881909L;
/*     */   
/*  75 */   private String prefix = "index.html";
/*     */   
/*  78 */   private String seriesParameterName = "series";
/*     */   
/*  81 */   private String categoryParameterName = "category";
/*     */   
/*     */   public StandardCategoryURLGenerator() {}
/*     */   
/*     */   public StandardCategoryURLGenerator(String prefix) {
/*  96 */     if (prefix == null)
/*  97 */       throw new IllegalArgumentException("Null 'prefix' argument."); 
/*  99 */     this.prefix = prefix;
/*     */   }
/*     */   
/*     */   public StandardCategoryURLGenerator(String prefix, String seriesParameterName, String categoryParameterName) {
/* 115 */     if (prefix == null)
/* 116 */       throw new IllegalArgumentException("Null 'prefix' argument."); 
/* 118 */     if (seriesParameterName == null)
/* 119 */       throw new IllegalArgumentException("Null 'seriesParameterName' argument."); 
/* 123 */     if (categoryParameterName == null)
/* 124 */       throw new IllegalArgumentException("Null 'categoryParameterName' argument."); 
/* 128 */     this.prefix = prefix;
/* 129 */     this.seriesParameterName = seriesParameterName;
/* 130 */     this.categoryParameterName = categoryParameterName;
/*     */   }
/*     */   
/*     */   public String generateURL(CategoryDataset dataset, int series, int category) {
/* 145 */     String url = this.prefix;
/* 146 */     Comparable seriesKey = dataset.getRowKey(series);
/* 147 */     Comparable categoryKey = dataset.getColumnKey(category);
/* 148 */     boolean firstParameter = (url.indexOf("?") == -1);
/* 149 */     url = url + (firstParameter ? "?" : "&amp;");
/* 151 */     url = url + this.seriesParameterName + "=" + URLEncoder.encode(seriesKey.toString());
/* 160 */     url = url + "&amp;" + this.categoryParameterName + "=" + URLEncoder.encode(categoryKey.toString());
/* 169 */     return url;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 183 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 195 */     if (obj == this)
/* 196 */       return true; 
/* 198 */     if (!(obj instanceof StandardCategoryURLGenerator))
/* 199 */       return false; 
/* 201 */     StandardCategoryURLGenerator that = (StandardCategoryURLGenerator)obj;
/* 202 */     if (!ObjectUtilities.equal(this.prefix, that.prefix))
/* 203 */       return false; 
/* 206 */     if (!ObjectUtilities.equal(this.seriesParameterName, that.seriesParameterName))
/* 208 */       return false; 
/* 210 */     if (!ObjectUtilities.equal(this.categoryParameterName, that.categoryParameterName))
/* 212 */       return false; 
/* 214 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 224 */     int result = (this.prefix != null) ? this.prefix.hashCode() : 0;
/* 225 */     result = 29 * result + ((this.seriesParameterName != null) ? this.seriesParameterName.hashCode() : 0);
/* 228 */     result = 29 * result + ((this.categoryParameterName != null) ? this.categoryParameterName.hashCode() : 0);
/* 231 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\char\\urls\StandardCategoryURLGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */