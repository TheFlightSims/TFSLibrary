/*     */ package org.geotools.styling;
/*     */ 
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.Fill;
/*     */ import org.opengis.style.Font;
/*     */ import org.opengis.style.Halo;
/*     */ import org.opengis.style.LabelPlacement;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.style.Symbolizer;
/*     */ import org.opengis.style.TextSymbolizer;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class TextSymbolizerImpl extends AbstractSymbolizer implements TextSymbolizer2, Cloneable {
/*     */   private Font font;
/*     */   
/*     */   private final FilterFactory filterFactory;
/*     */   
/*     */   private FillImpl fill;
/*     */   
/*     */   private HaloImpl halo;
/*     */   
/*     */   private LabelPlacement placement;
/*     */   
/*  52 */   private Expression label = null;
/*     */   
/*  53 */   private Graphic graphic = null;
/*     */   
/*  54 */   private Expression priority = null;
/*     */   
/*  55 */   private Expression abxtract = null;
/*     */   
/*  56 */   private Expression description = null;
/*     */   
/*  57 */   private OtherText otherText = null;
/*     */   
/*     */   protected TextSymbolizerImpl() {
/*  60 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   protected TextSymbolizerImpl(FilterFactory factory) {
/*  67 */     this(factory, (Description)null, (String)null, (Unit<Length>)null);
/*     */   }
/*     */   
/*     */   protected TextSymbolizerImpl(FilterFactory factory, Description desc, String name, Unit<Length> uom) {
/*  71 */     super(name, desc, (Expression)null, uom);
/*  72 */     this.filterFactory = factory;
/*  73 */     this.fill = new FillImpl(factory);
/*  74 */     this.fill.setColor((Expression)this.filterFactory.literal("#000000"));
/*  75 */     this.halo = null;
/*  76 */     this.placement = new PointPlacementImpl();
/*     */   }
/*     */   
/*     */   public FillImpl getFill() {
/*  86 */     return this.fill;
/*     */   }
/*     */   
/*     */   public void setFill(Fill fill) {
/*  95 */     if (this.fill == fill)
/*     */       return; 
/*  98 */     this.fill = FillImpl.cast(fill);
/*     */   }
/*     */   
/*     */   public Font getFont() {
/* 102 */     return this.font;
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/* 106 */     if (this.font == font)
/*     */       return; 
/* 109 */     this.font = FontImpl.cast(font);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Font[] getFonts() {
/* 120 */     if (this.font == null)
/* 121 */       return new Font[0]; 
/* 123 */     return new Font[] { this.font };
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void addFont(Font font) {
/* 134 */     this.font = font;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setFonts(Font[] fonts) {
/* 146 */     if (fonts != null && fonts.length > 0) {
/* 147 */       this.font = fonts[0];
/*     */     } else {
/* 149 */       this.font = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public HaloImpl getHalo() {
/* 159 */     return this.halo;
/*     */   }
/*     */   
/*     */   public void setHalo(Halo halo) {
/* 168 */     if (this.halo == halo)
/*     */       return; 
/* 171 */     this.halo = HaloImpl.cast(halo);
/*     */   }
/*     */   
/*     */   public Expression getLabel() {
/* 180 */     return this.label;
/*     */   }
/*     */   
/*     */   public void setLabel(Expression label) {
/* 189 */     this.label = label;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public LabelPlacement getPlacement() {
/* 200 */     return getLabelPlacement();
/*     */   }
/*     */   
/*     */   public void setPlacement(LabelPlacement labelPlacement) {
/* 210 */     setLabelPlacement(labelPlacement);
/*     */   }
/*     */   
/*     */   public LabelPlacement getLabelPlacement() {
/* 221 */     return this.placement;
/*     */   }
/*     */   
/*     */   public void setLabelPlacement(LabelPlacement labelPlacement) {
/* 231 */     if (this.placement == labelPlacement)
/*     */       return; 
/* 234 */     if (labelPlacement instanceof LinePlacement) {
/* 235 */       this.placement = LinePlacementImpl.cast(labelPlacement);
/*     */     } else {
/* 238 */       this.placement = PointPlacementImpl.cast(labelPlacement);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 248 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 252 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 265 */       return super.clone();
/* 266 */     } catch (CloneNotSupportedException e) {
/* 267 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setPriority(Expression priority) {
/* 272 */     if (this.priority == priority)
/*     */       return; 
/* 275 */     this.priority = priority;
/*     */   }
/*     */   
/*     */   public Expression getPriority() {
/* 279 */     return this.priority;
/*     */   }
/*     */   
/*     */   public void addToOptions(String key, String value) {
/* 283 */     getOptions().put(key, value.trim());
/*     */   }
/*     */   
/*     */   public String getOption(String key) {
/* 287 */     if (this.options == null)
/* 288 */       return null; 
/* 291 */     return this.options.get(key);
/*     */   }
/*     */   
/*     */   public Graphic getGraphic() {
/* 295 */     return this.graphic;
/*     */   }
/*     */   
/*     */   public void setGraphic(Graphic graphic) {
/* 299 */     if (this.graphic == graphic)
/*     */       return; 
/* 302 */     this.graphic = graphic;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 306 */     StringBuffer buf = new StringBuffer();
/* 307 */     buf.append("<TextSymbolizerImp property=");
/* 308 */     buf.append(getGeometryPropertyName());
/* 309 */     buf.append(" label=");
/* 310 */     buf.append(this.label);
/* 311 */     buf.append(">");
/* 312 */     buf.append(this.font);
/* 313 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public Expression getSnippet() {
/* 317 */     return this.abxtract;
/*     */   }
/*     */   
/*     */   public void setSnippet(Expression abxtract) {
/* 321 */     this.abxtract = abxtract;
/*     */   }
/*     */   
/*     */   public Expression getFeatureDescription() {
/* 325 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setFeatureDescription(Expression description) {
/* 329 */     this.description = description;
/*     */   }
/*     */   
/*     */   public OtherText getOtherText() {
/* 333 */     return this.otherText;
/*     */   }
/*     */   
/*     */   public void setOtherText(OtherText otherText) {
/* 337 */     this.otherText = otherText;
/*     */   }
/*     */   
/*     */   static TextSymbolizerImpl cast(Symbolizer symbolizer) {
/* 341 */     if (symbolizer == null)
/* 342 */       return null; 
/* 344 */     if (symbolizer instanceof TextSymbolizerImpl)
/* 345 */       return (TextSymbolizerImpl)symbolizer; 
/* 348 */     TextSymbolizer textSymbolizer = (TextSymbolizer)symbolizer;
/* 349 */     TextSymbolizerImpl copy = new TextSymbolizerImpl();
/* 350 */     copy.setDescription(textSymbolizer.getDescription());
/* 351 */     copy.setFill(textSymbolizer.getFill());
/* 352 */     copy.setFont(textSymbolizer.getFont());
/* 353 */     copy.setGeometryPropertyName(textSymbolizer.getGeometryPropertyName());
/* 354 */     copy.setHalo(textSymbolizer.getHalo());
/* 355 */     copy.setLabel(textSymbolizer.getLabel());
/* 356 */     copy.setLabelPlacement(textSymbolizer.getLabelPlacement());
/* 357 */     copy.setName(textSymbolizer.getName());
/* 358 */     copy.setUnitOfMeasure(textSymbolizer.getUnitOfMeasure());
/* 360 */     return copy;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 366 */     int prime = 31;
/* 367 */     int result = super.hashCode();
/* 368 */     result = 31 * result + ((this.abxtract == null) ? 0 : this.abxtract.hashCode());
/* 369 */     result = 31 * result + ((this.description == null) ? 0 : this.description.hashCode());
/* 370 */     result = 31 * result + ((this.fill == null) ? 0 : this.fill.hashCode());
/* 371 */     result = 31 * result + ((this.filterFactory == null) ? 0 : this.filterFactory.hashCode());
/* 372 */     result = 31 * result + ((this.font == null) ? 0 : this.font.hashCode());
/* 373 */     result = 31 * result + ((this.graphic == null) ? 0 : this.graphic.hashCode());
/* 374 */     result = 31 * result + ((this.halo == null) ? 0 : this.halo.hashCode());
/* 375 */     result = 31 * result + ((this.label == null) ? 0 : this.label.hashCode());
/* 376 */     result = 31 * result + ((this.otherText == null) ? 0 : this.otherText.hashCode());
/* 377 */     result = 31 * result + ((this.placement == null) ? 0 : this.placement.hashCode());
/* 378 */     result = 31 * result + ((this.priority == null) ? 0 : this.priority.hashCode());
/* 379 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 384 */     if (this == obj)
/* 385 */       return true; 
/* 386 */     if (!super.equals(obj))
/* 387 */       return false; 
/* 388 */     if (getClass() != obj.getClass())
/* 389 */       return false; 
/* 390 */     TextSymbolizerImpl other = (TextSymbolizerImpl)obj;
/* 391 */     if (this.abxtract == null) {
/* 392 */       if (other.abxtract != null)
/* 393 */         return false; 
/* 394 */     } else if (!this.abxtract.equals(other.abxtract)) {
/* 395 */       return false;
/*     */     } 
/* 396 */     if (this.description == null) {
/* 397 */       if (other.description != null)
/* 398 */         return false; 
/* 399 */     } else if (!this.description.equals(other.description)) {
/* 400 */       return false;
/*     */     } 
/* 401 */     if (this.fill == null) {
/* 402 */       if (other.fill != null)
/* 403 */         return false; 
/* 404 */     } else if (!this.fill.equals(other.fill)) {
/* 405 */       return false;
/*     */     } 
/* 406 */     if (this.filterFactory == null) {
/* 407 */       if (other.filterFactory != null)
/* 408 */         return false; 
/* 409 */     } else if (!this.filterFactory.equals(other.filterFactory)) {
/* 410 */       return false;
/*     */     } 
/* 411 */     if (this.font == null) {
/* 412 */       if (other.font != null)
/* 413 */         return false; 
/* 414 */     } else if (!this.font.equals(other.font)) {
/* 415 */       return false;
/*     */     } 
/* 416 */     if (this.graphic == null) {
/* 417 */       if (other.graphic != null)
/* 418 */         return false; 
/* 419 */     } else if (!this.graphic.equals(other.graphic)) {
/* 420 */       return false;
/*     */     } 
/* 421 */     if (this.halo == null) {
/* 422 */       if (other.halo != null)
/* 423 */         return false; 
/* 424 */     } else if (!this.halo.equals(other.halo)) {
/* 425 */       return false;
/*     */     } 
/* 426 */     if (this.label == null) {
/* 427 */       if (other.label != null)
/* 428 */         return false; 
/* 429 */     } else if (!this.label.equals(other.label)) {
/* 430 */       return false;
/*     */     } 
/* 431 */     if (this.otherText == null) {
/* 432 */       if (other.otherText != null)
/* 433 */         return false; 
/* 434 */     } else if (!this.otherText.equals(other.otherText)) {
/* 435 */       return false;
/*     */     } 
/* 436 */     if (this.placement == null) {
/* 437 */       if (other.placement != null)
/* 438 */         return false; 
/* 439 */     } else if (!this.placement.equals(other.placement)) {
/* 440 */       return false;
/*     */     } 
/* 441 */     if (this.priority == null) {
/* 442 */       if (other.priority != null)
/* 443 */         return false; 
/* 444 */     } else if (!this.priority.equals(other.priority)) {
/* 445 */       return false;
/*     */     } 
/* 446 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\TextSymbolizerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */