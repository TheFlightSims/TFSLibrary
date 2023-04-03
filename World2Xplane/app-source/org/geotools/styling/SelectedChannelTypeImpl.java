/*     */ package org.geotools.styling;
/*     */ 
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.ContrastEnhancement;
/*     */ import org.opengis.style.SelectedChannelType;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ 
/*     */ public class SelectedChannelTypeImpl implements SelectedChannelType {
/*     */   private FilterFactory filterFactory;
/*     */   
/*     */   private ContrastEnhancement contrastEnhancement;
/*     */   
/*  37 */   private String name = "channel";
/*     */   
/*     */   public SelectedChannelTypeImpl() {
/*  41 */     this(CommonFactoryFinder.getFilterFactory(null));
/*     */   }
/*     */   
/*     */   public SelectedChannelTypeImpl(FilterFactory factory) {
/*  45 */     this.filterFactory = factory;
/*  46 */     this.contrastEnhancement = contrastEnhancement((Expression)this.filterFactory.literal(1.0D));
/*     */   }
/*     */   
/*     */   public SelectedChannelTypeImpl(FilterFactory factory, ContrastEnhancement contrast) {
/*  50 */     this.filterFactory = factory;
/*  51 */     this.contrastEnhancement = contrast;
/*     */   }
/*     */   
/*     */   public SelectedChannelTypeImpl(SelectedChannelType gray) {
/*  55 */     this.filterFactory = (FilterFactory)CommonFactoryFinder.getFilterFactory2(null);
/*  56 */     this.name = gray.getChannelName();
/*  57 */     this.contrastEnhancement = new ContrastEnhancementImpl(gray.getContrastEnhancement());
/*     */   }
/*     */   
/*     */   public String getChannelName() {
/*  61 */     return this.name;
/*     */   }
/*     */   
/*     */   public ContrastEnhancement getContrastEnhancement() {
/*  65 */     return this.contrastEnhancement;
/*     */   }
/*     */   
/*     */   public void setChannelName(String name) {
/*  69 */     this.name = name;
/*     */   }
/*     */   
/*     */   public void setContrastEnhancement(ContrastEnhancement enhancement) {
/*  73 */     this.contrastEnhancement = ContrastEnhancementImpl.cast(enhancement);
/*     */   }
/*     */   
/*     */   public void setContrastEnhancement(Expression gammaValue) {
/*  77 */     this.contrastEnhancement.setGammaValue(gammaValue);
/*     */   }
/*     */   
/*     */   protected ContrastEnhancement contrastEnhancement(Expression expr) {
/*  81 */     ContrastEnhancement enhancement = new ContrastEnhancementImpl();
/*  82 */     enhancement.setGammaValue((Expression)this.filterFactory.literal(1.0D));
/*  84 */     return enhancement;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/*  88 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/*  92 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  97 */     int PRIME = 1000003;
/*  98 */     int result = 0;
/* 100 */     if (this.name != null)
/* 101 */       result = 1000003 * result + this.name.hashCode(); 
/* 104 */     if (this.contrastEnhancement != null)
/* 105 */       result = 1000003 * result + this.contrastEnhancement.hashCode(); 
/* 108 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 113 */     if (this == obj)
/* 114 */       return true; 
/* 117 */     if (obj instanceof SelectedChannelTypeImpl) {
/* 118 */       SelectedChannelTypeImpl other = (SelectedChannelTypeImpl)obj;
/* 120 */       return (Utilities.equals(this.name, other.name) && Utilities.equals(this.contrastEnhancement, other.contrastEnhancement));
/*     */     } 
/* 124 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\SelectedChannelTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */