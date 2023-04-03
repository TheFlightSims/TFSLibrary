/*     */ package org.geotools.styling;
/*     */ 
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.style.Description;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DescriptionImpl implements Description {
/*     */   private InternationalString title;
/*     */   
/*     */   private InternationalString description;
/*     */   
/*     */   public DescriptionImpl() {
/*  17 */     this.title = null;
/*  18 */     this.description = null;
/*     */   }
/*     */   
/*     */   public DescriptionImpl(String title, String description) {
/*  22 */     this((InternationalString)new SimpleInternationalString(title), (InternationalString)new SimpleInternationalString(description));
/*     */   }
/*     */   
/*     */   public DescriptionImpl(InternationalString title, InternationalString description) {
/*  26 */     this.title = title;
/*  27 */     this.description = description;
/*     */   }
/*     */   
/*     */   public DescriptionImpl(Description description) {
/*  35 */     this(description.getTitle(), description.getAbstract());
/*     */   }
/*     */   
/*     */   public InternationalString getTitle() {
/*  39 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(InternationalString title) {
/*  43 */     this.title = title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/*  47 */     this.title = (InternationalString)new SimpleInternationalString(title);
/*     */   }
/*     */   
/*     */   public InternationalString getAbstract() {
/*  51 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setAbstract(InternationalString description) {
/*  55 */     this.description = description;
/*     */   }
/*     */   
/*     */   public void setAbstract(String title) {
/*  59 */     this.description = (InternationalString)new SimpleInternationalString(title);
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object extraData) {
/*  63 */     return null;
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {}
/*     */   
/*     */   public int hashCode() {
/*  72 */     int prime = 31;
/*  73 */     int result = 1;
/*  74 */     result = 31 * result + ((this.description == null) ? 0 : this.description.hashCode());
/*  75 */     result = 31 * result + ((this.title == null) ? 0 : this.title.hashCode());
/*  76 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  81 */     if (this == obj)
/*  82 */       return true; 
/*  83 */     if (obj == null)
/*  84 */       return false; 
/*  85 */     if (getClass() != obj.getClass())
/*  86 */       return false; 
/*  87 */     DescriptionImpl other = (DescriptionImpl)obj;
/*  88 */     if (this.description == null) {
/*  89 */       if (other.description != null)
/*  90 */         return false; 
/*  91 */     } else if (!this.description.equals(other.description)) {
/*  92 */       return false;
/*     */     } 
/*  93 */     if (this.title == null) {
/*  94 */       if (other.title != null)
/*  95 */         return false; 
/*  96 */     } else if (!this.title.equals(other.title)) {
/*  97 */       return false;
/*     */     } 
/*  98 */     return true;
/*     */   }
/*     */   
/*     */   static DescriptionImpl cast(Description description) {
/* 107 */     if (description == null)
/* 108 */       return null; 
/* 110 */     if (description instanceof DescriptionImpl)
/* 111 */       return (DescriptionImpl)description; 
/* 114 */     DescriptionImpl copy = new DescriptionImpl();
/* 115 */     copy.setTitle(description.getTitle());
/* 116 */     copy.setAbstract(description.getAbstract());
/* 117 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\DescriptionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */