/*     */ package org.geotools.styling;
/*     */ 
/*     */ import javax.swing.Icon;
/*     */ import org.opengis.metadata.citation.OnLineResource;
/*     */ import org.opengis.style.ExternalMark;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ 
/*     */ public class ExternalMarkImpl implements ExternalMark {
/*     */   private OnLineResource onlineResource;
/*     */   
/*     */   private Icon inlineContent;
/*     */   
/*     */   private int index;
/*     */   
/*     */   private String format;
/*     */   
/*     */   public ExternalMarkImpl() {}
/*     */   
/*     */   public ExternalMarkImpl(Icon icon) {
/*  45 */     this.inlineContent = icon;
/*  46 */     this.index = -1;
/*  47 */     this.onlineResource = null;
/*  48 */     this.format = null;
/*     */   }
/*     */   
/*     */   public ExternalMarkImpl(OnLineResource resource, String format, int markIndex) {
/*  52 */     this.inlineContent = null;
/*  53 */     this.index = markIndex;
/*  54 */     this.onlineResource = resource;
/*  55 */     this.format = format;
/*     */   }
/*     */   
/*     */   public String getFormat() {
/*  59 */     return this.format;
/*     */   }
/*     */   
/*     */   public Icon getInlineContent() {
/*  63 */     return this.inlineContent;
/*     */   }
/*     */   
/*     */   public int getMarkIndex() {
/*  67 */     return this.index;
/*     */   }
/*     */   
/*     */   public OnLineResource getOnlineResource() {
/*  71 */     return this.onlineResource;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object extraData) {
/*  75 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   public void setInlineContent(Icon inline) {
/*  79 */     this.inlineContent = inline;
/*     */   }
/*     */   
/*     */   public void getInlineContent(Icon inline) {
/*  83 */     setInlineContent(inline);
/*     */   }
/*     */   
/*     */   public void setFormat(String mimeType) {
/*  87 */     this.format = mimeType;
/*     */   }
/*     */   
/*     */   public void setMarkIndex(int markIndex) {
/*  91 */     this.index = markIndex;
/*     */   }
/*     */   
/*     */   public void setOnlineResource(OnLineResource resource) {
/*  95 */     this.onlineResource = resource;
/*     */   }
/*     */   
/*     */   static ExternalMarkImpl cast(ExternalMark mark) {
/*  98 */     if (mark == null)
/*  99 */       return null; 
/* 100 */     if (mark instanceof ExternalMarkImpl)
/* 101 */       return (ExternalMarkImpl)mark; 
/* 103 */     ExternalMarkImpl copy = new ExternalMarkImpl();
/* 104 */     copy.setFormat(mark.getFormat());
/* 105 */     copy.setMarkIndex(mark.getMarkIndex());
/* 106 */     copy.setOnlineResource(mark.getOnlineResource());
/* 107 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ExternalMarkImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */