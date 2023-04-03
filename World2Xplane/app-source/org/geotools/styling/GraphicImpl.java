/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.filter.ConstantExpression;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.AnchorPoint;
/*     */ import org.opengis.style.Displacement;
/*     */ import org.opengis.style.Graphic;
/*     */ import org.opengis.style.GraphicalSymbol;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class GraphicImpl implements Graphic, Cloneable {
/*  50 */   private final List<GraphicalSymbol> graphics = new ArrayList<GraphicalSymbol>();
/*     */   
/*     */   private AnchorPointImpl anchor;
/*     */   
/*     */   private Expression gap;
/*     */   
/*     */   private Expression initialGap;
/*     */   
/*     */   private FilterFactory filterFactory;
/*     */   
/*  56 */   private Expression rotation = null;
/*     */   
/*  57 */   private Expression size = null;
/*     */   
/*  58 */   private DisplacementImpl displacement = null;
/*     */   
/*  59 */   private Expression opacity = null;
/*     */   
/*     */   protected GraphicImpl() {
/*  65 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   public GraphicImpl(FilterFactory factory) {
/*  69 */     this(factory, null, null, null);
/*     */   }
/*     */   
/*     */   public GraphicImpl(FilterFactory factory, AnchorPoint anchor, Expression gap, Expression initialGap) {
/*  73 */     this.filterFactory = factory;
/*  74 */     this.anchor = AnchorPointImpl.cast(anchor);
/*  76 */     if (gap == null) {
/*  76 */       this.gap = (Expression)ConstantExpression.constant(0);
/*     */     } else {
/*  77 */       this.gap = gap;
/*     */     } 
/*  78 */     if (initialGap == null) {
/*  78 */       this.initialGap = (Expression)ConstantExpression.constant(0);
/*     */     } else {
/*  79 */       this.initialGap = initialGap;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setFilterFactory(FilterFactory factory) {
/*  85 */     this.filterFactory = factory;
/*     */   }
/*     */   
/*     */   public List<GraphicalSymbol> graphicalSymbols() {
/*  89 */     return this.graphics;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ExternalGraphic[] getExternalGraphics() {
/* 104 */     Collection<ExternalGraphic> exts = new ArrayList<ExternalGraphic>();
/* 106 */     for (GraphicalSymbol s : this.graphics) {
/* 107 */       if (s instanceof ExternalGraphic)
/* 108 */         exts.add((ExternalGraphic)s); 
/*     */     } 
/* 112 */     return exts.<ExternalGraphic>toArray(new ExternalGraphic[0]);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setExternalGraphics(ExternalGraphic[] externalGraphics) {
/* 117 */     Collection<GraphicalSymbol> currExternalGraphics = new ArrayList<GraphicalSymbol>();
/* 118 */     for (GraphicalSymbol s : this.graphics) {
/* 119 */       if (s instanceof ExternalGraphic)
/* 120 */         currExternalGraphics.add(s); 
/*     */     } 
/* 123 */     this.graphics.removeAll(currExternalGraphics);
/* 125 */     for (ExternalGraphic g : externalGraphics)
/* 126 */       this.graphics.add(g); 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void addExternalGraphic(ExternalGraphic externalGraphic) {
/* 132 */     this.graphics.add(externalGraphic);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Mark[] getMarks() {
/* 146 */     Collection<Mark> exts = new ArrayList<Mark>();
/* 148 */     for (GraphicalSymbol s : this.graphics) {
/* 149 */       if (s instanceof Mark)
/* 150 */         exts.add((Mark)s); 
/*     */     } 
/* 154 */     return exts.<Mark>toArray(new Mark[0]);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setMarks(Mark[] marks) {
/* 159 */     Collection<GraphicalSymbol> currMarks = new ArrayList<GraphicalSymbol>();
/* 160 */     for (GraphicalSymbol s : this.graphics) {
/* 161 */       if (s instanceof Mark)
/* 162 */         currMarks.add(s); 
/*     */     } 
/* 165 */     this.graphics.removeAll(currMarks);
/* 167 */     for (Mark g : marks)
/* 168 */       this.graphics.add(g); 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void addMark(Mark mark) {
/* 174 */     this.graphics.add(mark);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Symbol[] getSymbols() {
/* 196 */     ArrayList<Symbol> symbols = new ArrayList<Symbol>();
/* 197 */     for (GraphicalSymbol graphic : this.graphics) {
/* 198 */       if (graphic instanceof Symbol)
/* 199 */         symbols.add((Symbol)graphic); 
/*     */     } 
/* 202 */     return symbols.<Symbol>toArray(new Symbol[0]);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setSymbols(Symbol[] symbols) {
/* 211 */     this.graphics.clear();
/* 213 */     for (Symbol g : symbols)
/* 214 */       this.graphics.add(g); 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void addSymbol(Symbol symbol) {
/* 220 */     this.graphics.add(symbol);
/*     */   }
/*     */   
/*     */   public AnchorPointImpl getAnchorPoint() {
/* 224 */     return this.anchor;
/*     */   }
/*     */   
/*     */   public void setAnchorPoint(AnchorPoint anchor) {
/* 228 */     this.anchor = AnchorPointImpl.cast(anchor);
/*     */   }
/*     */   
/*     */   public void setAnchorPoint(AnchorPoint anchorPoint) {
/* 232 */     this.anchor = AnchorPointImpl.cast(anchorPoint);
/*     */   }
/*     */   
/*     */   public Expression getOpacity() {
/* 247 */     return this.opacity;
/*     */   }
/*     */   
/*     */   public Expression getRotation() {
/* 260 */     return this.rotation;
/*     */   }
/*     */   
/*     */   public Expression getSize() {
/* 280 */     return this.size;
/*     */   }
/*     */   
/*     */   public DisplacementImpl getDisplacement() {
/* 284 */     return this.displacement;
/*     */   }
/*     */   
/*     */   public Expression getInitialGap() {
/* 288 */     return this.initialGap;
/*     */   }
/*     */   
/*     */   public void setInitialGap(Expression initialGap) {
/* 292 */     this.initialGap = initialGap;
/*     */   }
/*     */   
/*     */   public Expression getGap() {
/* 296 */     return this.gap;
/*     */   }
/*     */   
/*     */   public void setGap(Expression gap) {
/* 300 */     this.gap = gap;
/*     */   }
/*     */   
/*     */   public void setDisplacement(Displacement offset) {
/* 304 */     this.displacement = DisplacementImpl.cast(offset);
/*     */   }
/*     */   
/*     */   public void setOpacity(Expression opacity) {
/* 308 */     this.opacity = opacity;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setOpacity(double opacity) {
/* 313 */     setOpacity((Expression)this.filterFactory.literal(opacity));
/*     */   }
/*     */   
/*     */   public void setRotation(Expression rotation) {
/* 322 */     this.rotation = rotation;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setRotation(double rotation) {
/* 327 */     setRotation((Expression)this.filterFactory.literal(rotation));
/*     */   }
/*     */   
/*     */   public void setSize(Expression size) {
/* 336 */     this.size = size;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setSize(int size) {
/* 341 */     setSize((Expression)this.filterFactory.literal(size));
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 345 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 349 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     GraphicImpl clone;
/*     */     try {
/* 364 */       clone = (GraphicImpl)super.clone();
/* 365 */       clone.graphics.clear();
/* 366 */       clone.graphics.addAll(this.graphics);
/* 368 */     } catch (CloneNotSupportedException e) {
/* 369 */       throw new RuntimeException(e);
/*     */     } 
/* 372 */     return clone;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 381 */     int PRIME = 1000003;
/* 382 */     int result = 0;
/* 384 */     if (this.graphics != null)
/* 385 */       result = 1000003 * result + this.graphics.hashCode(); 
/* 388 */     if (this.rotation != null)
/* 389 */       result = 1000003 * result + this.rotation.hashCode(); 
/* 392 */     if (this.size != null)
/* 393 */       result = 1000003 * result + this.size.hashCode(); 
/* 396 */     if (this.opacity != null)
/* 397 */       result = 1000003 * result + this.opacity.hashCode(); 
/* 408 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object oth) {
/* 425 */     if (this == oth)
/* 426 */       return true; 
/* 429 */     if (oth instanceof GraphicImpl) {
/* 430 */       GraphicImpl other = (GraphicImpl)oth;
/* 432 */       return (Utilities.equals(this.size, other.size) && Utilities.equals(this.rotation, other.rotation) && Utilities.equals(this.opacity, other.opacity) && Arrays.equals((Object[])getMarks(), (Object[])other.getMarks()) && Arrays.equals((Object[])getExternalGraphics(), (Object[])other.getExternalGraphics()) && Arrays.equals((Object[])getSymbols(), (Object[])other.getSymbols()));
/*     */     } 
/* 452 */     return false;
/*     */   }
/*     */   
/*     */   static GraphicImpl cast(Graphic graphic) {
/* 456 */     if (graphic == null)
/* 457 */       return null; 
/* 459 */     if (graphic instanceof GraphicImpl)
/* 460 */       return (GraphicImpl)graphic; 
/* 463 */     GraphicImpl copy = new GraphicImpl();
/* 464 */     copy.setAnchorPoint(graphic.getAnchorPoint());
/* 465 */     copy.setDisplacement(graphic.getDisplacement());
/* 466 */     if (graphic.graphicalSymbols() != null)
/* 467 */       for (GraphicalSymbol item : graphic.graphicalSymbols()) {
/* 468 */         if (item instanceof org.opengis.style.ExternalGraphic) {
/* 469 */           copy.graphicalSymbols().add(ExternalGraphicImpl.cast(item));
/*     */           continue;
/*     */         } 
/* 471 */         if (item instanceof org.opengis.style.Mark)
/* 472 */           copy.graphicalSymbols().add(MarkImpl.cast(item)); 
/*     */       }  
/* 476 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\GraphicImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */