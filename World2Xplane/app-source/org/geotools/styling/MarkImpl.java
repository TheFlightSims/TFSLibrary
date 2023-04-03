/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.ExternalMark;
/*     */ import org.opengis.style.Fill;
/*     */ import org.opengis.style.GraphicalSymbol;
/*     */ import org.opengis.style.Stroke;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class MarkImpl implements Mark, Cloneable {
/*  42 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.styling");
/*     */   
/*     */   private final FilterFactory filterFactory;
/*     */   
/*     */   private FillImpl fill;
/*     */   
/*     */   private StrokeImpl stroke;
/*     */   
/*     */   private ExternalMarkImpl external;
/*     */   
/*  49 */   private Expression wellKnownName = null;
/*     */   
/*     */   public MarkImpl() {
/*  55 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()), null);
/*     */   }
/*     */   
/*     */   public MarkImpl(String name) {
/*  59 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()), null);
/*  60 */     LOGGER.fine("creating " + name + " type mark");
/*  61 */     setWellKnownName(name);
/*     */   }
/*     */   
/*     */   public MarkImpl(FilterFactory filterFactory, ExternalMark external) {
/*  65 */     this.filterFactory = filterFactory;
/*  66 */     LOGGER.fine("creating defaultMark");
/*     */     try {
/*  69 */       StyleFactory sfac = new StyleFactoryImpl();
/*  70 */       this.fill = FillImpl.cast(sfac.getDefaultFill());
/*  71 */       this.stroke = StrokeImpl.cast(sfac.getDefaultStroke());
/*  73 */       this.wellKnownName = (Expression)filterFactory.literal("square");
/*  74 */     } catch (IllegalFilterException ife) {
/*  75 */       severe("<init>", "Failed to build default mark: ", (Exception)ife);
/*     */     } 
/*  77 */     this.external = ExternalMarkImpl.cast(external);
/*     */   }
/*     */   
/*     */   private static void severe(String method, String message, Exception exception) {
/*  89 */     LogRecord record = new LogRecord(Level.SEVERE, message);
/*  91 */     record.setSourceMethodName(method);
/*  92 */     record.setThrown(exception);
/*  93 */     LOGGER.log(record);
/*     */   }
/*     */   
/*     */   public FillImpl getFill() {
/* 102 */     return this.fill;
/*     */   }
/*     */   
/*     */   public StrokeImpl getStroke() {
/* 112 */     return this.stroke;
/*     */   }
/*     */   
/*     */   public Expression getWellKnownName() {
/* 124 */     return this.wellKnownName;
/*     */   }
/*     */   
/*     */   public void setFill(Fill fill) {
/* 133 */     this.fill = FillImpl.cast(fill);
/*     */   }
/*     */   
/*     */   public void setStroke(Stroke stroke) {
/* 142 */     this.stroke = StrokeImpl.cast(stroke);
/*     */   }
/*     */   
/*     */   public void setWellKnownName(Expression wellKnownName) {
/* 151 */     LOGGER.entering("DefaultMark", "setWellKnownName");
/* 152 */     this.wellKnownName = wellKnownName;
/*     */   }
/*     */   
/*     */   public void setWellKnownName(String name) {
/* 156 */     setWellKnownName((Expression)this.filterFactory.literal(name));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 161 */     return this.wellKnownName.toString();
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 165 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 169 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 184 */       MarkImpl clone = (MarkImpl)super.clone();
/* 185 */       if (this.fill != null)
/* 186 */         clone.fill = (FillImpl)this.fill.clone(); 
/* 188 */       if (this.stroke != null && this.stroke instanceof Cloneable)
/* 189 */         clone.stroke = (StrokeImpl)this.stroke.clone(); 
/* 192 */       return clone;
/* 193 */     } catch (CloneNotSupportedException e) {
/* 195 */       throw new RuntimeException("Failed to clone MarkImpl");
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 205 */     int PRIME = 1000003;
/* 206 */     int result = 0;
/* 208 */     if (this.fill != null)
/* 209 */       result = 1000003 * result + this.fill.hashCode(); 
/* 212 */     if (this.stroke != null)
/* 213 */       result = 1000003 * result + this.stroke.hashCode(); 
/* 216 */     if (this.wellKnownName != null)
/* 217 */       result = 1000003 * result + this.wellKnownName.hashCode(); 
/* 220 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object oth) {
/* 236 */     if (this == oth)
/* 237 */       return true; 
/* 240 */     if (oth == null)
/* 241 */       return false; 
/* 244 */     if (oth.getClass() != getClass())
/* 245 */       return false; 
/* 248 */     MarkImpl other = (MarkImpl)oth;
/* 251 */     if (this.wellKnownName == null) {
/* 252 */       if (other.wellKnownName != null)
/* 253 */         return false; 
/* 256 */     } else if (!this.wellKnownName.equals(other.wellKnownName)) {
/* 257 */       return false;
/*     */     } 
/* 261 */     if (this.fill == null) {
/* 262 */       if (other.fill != null)
/* 263 */         return false; 
/* 266 */     } else if (!this.fill.equals(other.fill)) {
/* 267 */       return false;
/*     */     } 
/* 271 */     if (this.stroke == null) {
/* 272 */       if (other.stroke != null)
/* 273 */         return false; 
/* 276 */     } else if (!this.stroke.equals(other.stroke)) {
/* 277 */       return false;
/*     */     } 
/* 281 */     return true;
/*     */   }
/*     */   
/*     */   public ExternalMarkImpl getExternalMark() {
/* 285 */     return this.external;
/*     */   }
/*     */   
/*     */   public void setExternalMark(ExternalMark external) {
/* 289 */     this.external = ExternalMarkImpl.cast(external);
/*     */   }
/*     */   
/*     */   static MarkImpl cast(GraphicalSymbol item) {
/* 293 */     if (item == null)
/* 294 */       return null; 
/* 296 */     if (item instanceof MarkImpl)
/* 297 */       return (MarkImpl)item; 
/* 299 */     if (item instanceof Mark) {
/* 300 */       Mark mark = (Mark)item;
/* 301 */       MarkImpl copy = new MarkImpl();
/* 302 */       copy.setStroke(mark.getStroke());
/* 303 */       copy.setWellKnownName(mark.getWellKnownName());
/* 304 */       copy.setExternalMark(mark.getExternalMark());
/* 305 */       return copy;
/*     */     } 
/* 307 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\MarkImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */