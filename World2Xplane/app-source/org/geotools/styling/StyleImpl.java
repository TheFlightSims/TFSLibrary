/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.style.Description;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.style.Symbolizer;
/*     */ import org.opengis.util.Cloneable;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class StyleImpl implements Style, Cloneable {
/*  41 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.styling");
/*     */   
/*  44 */   private List<FeatureTypeStyle> featureTypeStyles = new ArrayList<FeatureTypeStyle>();
/*     */   
/*  45 */   private DescriptionImpl description = new DescriptionImpl();
/*     */   
/*  46 */   private String name = "Default Styler";
/*     */   
/*     */   private boolean defaultB = false;
/*     */   
/*     */   private Symbolizer defaultSymbolizer;
/*     */   
/*     */   public DescriptionImpl getDescription() {
/*  58 */     return this.description;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getAbstract() {
/*  62 */     if (this.description == null || this.description.getAbstract() == null)
/*  63 */       return null; 
/*  65 */     return this.description.getAbstract().toString();
/*     */   }
/*     */   
/*     */   public FeatureTypeStyle[] getFeatureTypeStyles() {
/*     */     FeatureTypeStyle[] arrayOfFeatureTypeStyle;
/*  69 */     FeatureTypeStyleImpl[] arrayOfFeatureTypeStyleImpl = { new FeatureTypeStyleImpl() };
/*  71 */     if (this.featureTypeStyles != null && this.featureTypeStyles.size() != 0) {
/*  72 */       if (LOGGER.isLoggable(Level.FINE))
/*  73 */         LOGGER.fine("number of fts set " + this.featureTypeStyles.size()); 
/*  75 */       arrayOfFeatureTypeStyle = this.featureTypeStyles.<FeatureTypeStyle>toArray(new FeatureTypeStyle[0]);
/*     */     } 
/*  80 */     return arrayOfFeatureTypeStyle;
/*     */   }
/*     */   
/*     */   public List<FeatureTypeStyle> featureTypeStyles() {
/*  84 */     return this.featureTypeStyles;
/*     */   }
/*     */   
/*     */   public Symbolizer getDefaultSpecification() {
/*  88 */     return this.defaultSymbolizer;
/*     */   }
/*     */   
/*     */   public void setDefaultSpecification(Symbolizer defaultSymbolizer) {
/*  91 */     this.defaultSymbolizer = defaultSymbolizer;
/*     */   }
/*     */   
/*     */   public void setFeatureTypeStyles(FeatureTypeStyle[] styles) {
/*  95 */     List<FeatureTypeStyle> newStyles = Arrays.asList(styles);
/*  97 */     this.featureTypeStyles.clear();
/*  98 */     this.featureTypeStyles.addAll(newStyles);
/* 100 */     LOGGER.fine("StyleImpl added " + this.featureTypeStyles.size() + " feature types");
/*     */   }
/*     */   
/*     */   public void addFeatureTypeStyle(FeatureTypeStyle type) {
/* 105 */     this.featureTypeStyles.add(type);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 109 */     return this.name;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getTitle() {
/* 113 */     if (this.description == null || this.description.getTitle() == null)
/* 114 */       return null; 
/* 116 */     return this.description.getTitle().toString();
/*     */   }
/*     */   
/*     */   public boolean isDefault() {
/* 120 */     return this.defaultB;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setAbstract(String abstractStr) {
/* 125 */     if (this.description == null)
/* 126 */       this.description = new DescriptionImpl(); 
/* 128 */     this.description.setAbstract((abstractStr == null) ? null : (InternationalString)new SimpleInternationalString(abstractStr));
/*     */   }
/*     */   
/*     */   public void setDefault(boolean isDefault) {
/* 132 */     this.defaultB = isDefault;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 136 */     this.name = name;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setTitle(String title) {
/* 140 */     if (this.description == null)
/* 141 */       this.description = new DescriptionImpl(); 
/* 143 */     this.description.setTitle((title == null) ? null : (InternationalString)new SimpleInternationalString(title));
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 147 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     Style clone;
/*     */     try {
/* 163 */       clone = (Style)super.clone();
/* 164 */     } catch (CloneNotSupportedException e) {
/* 165 */       throw new RuntimeException(e);
/*     */     } 
/* 168 */     FeatureTypeStyle[] ftsArray = new FeatureTypeStyle[this.featureTypeStyles.size()];
/* 171 */     for (int i = 0; i < ftsArray.length; i++) {
/* 172 */       FeatureTypeStyle fts = this.featureTypeStyles.get(i);
/* 173 */       ftsArray[i] = (FeatureTypeStyle)((Cloneable)fts).clone();
/*     */     } 
/* 176 */     clone.setFeatureTypeStyles(ftsArray);
/* 178 */     return clone;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 187 */     int PRIME = 1000003;
/* 188 */     int result = 0;
/* 190 */     if (this.featureTypeStyles != null)
/* 191 */       result = 1000003 * result + this.featureTypeStyles.hashCode(); 
/* 194 */     if (this.description != null)
/* 195 */       result = 1000003 * result + this.description.hashCode(); 
/* 198 */     if (this.name != null)
/* 199 */       result = 1000003 * result + this.name.hashCode(); 
/* 202 */     result = 1000003 * result + (this.defaultB ? 1 : 0);
/* 204 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object oth) {
/* 220 */     if (this == oth)
/* 221 */       return true; 
/* 224 */     if (oth instanceof StyleImpl) {
/* 225 */       StyleImpl other = (StyleImpl)oth;
/* 227 */       return (Utilities.equals(this.name, other.name) && Utilities.equals(this.description, other.description) && Utilities.equals(this.featureTypeStyles, other.featureTypeStyles));
/*     */     } 
/* 232 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 235 */     StringBuffer buf = new StringBuffer();
/* 236 */     buf.append("StyleImpl");
/* 237 */     buf.append("[");
/* 238 */     if (this.name != null) {
/* 239 */       buf.append(" name=");
/* 240 */       buf.append(this.name);
/*     */     } else {
/* 243 */       buf.append(" UNNAMED");
/*     */     } 
/* 245 */     if (this.defaultB)
/* 246 */       buf.append(", DEFAULT"); 
/* 252 */     buf.append("]");
/* 253 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object extraData) {
/* 257 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   public void setDescription(Description description) {
/* 261 */     this.description = DescriptionImpl.cast(description);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StyleImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */