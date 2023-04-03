/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.filter.visitor.DuplicatingFilterVisitor;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.metadata.citation.OnLineResource;
/*     */ import org.opengis.style.Description;
/*     */ import org.opengis.style.GraphicLegend;
/*     */ import org.opengis.style.Rule;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.style.Symbolizer;
/*     */ import org.opengis.util.Cloneable;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class RuleImpl implements Rule, Cloneable {
/*  45 */   private List<Symbolizer> symbolizers = new ArrayList<Symbolizer>();
/*     */   
/*  47 */   private List<Graphic> legends = new ArrayList<Graphic>();
/*     */   
/*     */   private String name;
/*     */   
/*  50 */   private DescriptionImpl description = new DescriptionImpl();
/*     */   
/*  51 */   private Filter filter = null;
/*     */   
/*     */   private boolean hasElseFilter = false;
/*     */   
/*  53 */   private double maxScaleDenominator = Double.POSITIVE_INFINITY;
/*     */   
/*  54 */   private double minScaleDenominator = 0.0D;
/*     */   
/*  55 */   private OnLineResource online = null;
/*     */   
/*     */   protected RuleImpl(Symbolizer[] symbolizers) {
/*  69 */     this.symbolizers.addAll(Arrays.asList(symbolizers));
/*     */   }
/*     */   
/*     */   protected RuleImpl(Symbolizer[] symbolizers, Description desc, Graphic[] legends, String name, Filter filter, boolean isElseFilter, double maxScale, double minScale) {
/*  80 */     setSymbolizers(symbolizers);
/*  81 */     this.description.setAbstract(desc.getAbstract());
/*  82 */     this.description.setTitle(desc.getTitle());
/*  83 */     setLegendGraphic(legends);
/*  84 */     this.name = name;
/*  85 */     this.filter = filter;
/*  86 */     this.hasElseFilter = isElseFilter;
/*  87 */     this.maxScaleDenominator = maxScale;
/*  88 */     this.minScaleDenominator = minScale;
/*     */   }
/*     */   
/*     */   public RuleImpl(Rule rule) {
/*  94 */     this.symbolizers = new ArrayList<Symbolizer>();
/*  95 */     for (Symbolizer sym : rule.symbolizers()) {
/*  96 */       if (sym instanceof Symbolizer)
/*  97 */         this.symbolizers.add((Symbolizer)sym); 
/*     */     } 
/* 100 */     if (rule.getDescription() != null && rule.getDescription().getTitle() != null)
/* 101 */       this.description.setTitle(rule.getDescription().getTitle()); 
/* 103 */     if (rule.getDescription() != null && rule.getDescription().getAbstract() != null)
/* 104 */       this.description.setTitle(rule.getDescription().getAbstract()); 
/* 106 */     if (rule.getLegend() instanceof Graphic) {
/* 107 */       Graphic graphic = (Graphic)rule.getLegend();
/* 108 */       setLegendGraphic(new Graphic[] { graphic });
/*     */     } 
/* 110 */     this.name = rule.getName();
/* 111 */     this.filter = rule.getFilter();
/* 112 */     this.hasElseFilter = rule.isElseFilter();
/* 113 */     this.maxScaleDenominator = rule.getMaxScaleDenominator();
/* 114 */     this.minScaleDenominator = rule.getMinScaleDenominator();
/*     */   }
/*     */   
/*     */   public Graphic[] getLegendGraphic() {
/* 118 */     return this.legends.<Graphic>toArray(new Graphic[0]);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void addLegendGraphic(Graphic graphic) {
/* 123 */     this.legends.add(graphic);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setLegendGraphic(Graphic[] graphics) {
/* 135 */     List<Graphic> graphicList = Arrays.asList(graphics);
/* 136 */     this.legends = new ArrayList<Graphic>(graphicList);
/*     */   }
/*     */   
/*     */   public GraphicLegend getLegend() {
/* 142 */     if (this.legends.isEmpty())
/* 142 */       return null; 
/* 143 */     return this.legends.get(0);
/*     */   }
/*     */   
/*     */   public void setLegend(GraphicLegend legend) {
/* 146 */     this.legends.clear();
/* 147 */     this.legends.add((Graphic)legend);
/*     */   }
/*     */   
/*     */   public List<Symbolizer> symbolizers() {
/* 151 */     return this.symbolizers;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void addSymbolizer(Symbolizer symb) {
/* 156 */     this.symbolizers.add(symb);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setSymbolizers(Symbolizer[] syms) {
/* 161 */     List<Symbolizer> symbols = Arrays.asList(syms);
/* 162 */     this.symbolizers = new ArrayList<Symbolizer>(symbols);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Symbolizer[] getSymbolizers() {
/* 172 */     Symbolizer[] ret = new Symbolizer[this.symbolizers.size()];
/* 173 */     for (int i = 0, n = this.symbolizers.size(); i < n; i++)
/* 174 */       ret[i] = this.symbolizers.get(i); 
/* 177 */     return ret;
/*     */   }
/*     */   
/*     */   public DescriptionImpl getDescription() {
/* 181 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(Description description) {
/* 185 */     this.description = DescriptionImpl.cast(description);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 189 */     return this.name;
/*     */   }
/*     */   
/*     */   public String getAbstract() {
/* 197 */     if (this.description == null || this.description.getAbstract() == null)
/* 198 */       return null; 
/* 200 */     return this.description.getAbstract().toString();
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 204 */     this.name = name;
/*     */   }
/*     */   
/*     */   public void setAbstract(String abstractStr) {
/* 212 */     this.description.setAbstract((abstractStr != null) ? (InternationalString)new SimpleInternationalString(abstractStr) : null);
/*     */   }
/*     */   
/*     */   public String getTitle() {
/* 221 */     if (this.description == null || this.description.getTitle() == null)
/* 222 */       return null; 
/* 224 */     return this.description.getTitle().toString();
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/* 233 */     this.description.setTitle((title != null) ? (InternationalString)new SimpleInternationalString(title) : null);
/*     */   }
/*     */   
/*     */   public Filter getFilter() {
/* 237 */     return this.filter;
/*     */   }
/*     */   
/*     */   public void setFilter(Filter filter) {
/* 241 */     this.filter = filter;
/*     */   }
/*     */   
/*     */   public boolean isElseFilter() {
/* 245 */     return this.hasElseFilter;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean hasElseFilter() {
/* 253 */     return this.hasElseFilter;
/*     */   }
/*     */   
/*     */   public void setIsElseFilter(boolean flag) {
/* 257 */     this.hasElseFilter = flag;
/*     */   }
/*     */   
/*     */   public void setElseFilter(boolean defaultb) {
/* 260 */     this.hasElseFilter = defaultb;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setHasElseFilter() {
/* 268 */     this.hasElseFilter = true;
/*     */   }
/*     */   
/*     */   public double getMaxScaleDenominator() {
/* 277 */     return this.maxScaleDenominator;
/*     */   }
/*     */   
/*     */   public void setMaxScaleDenominator(double maxScaleDenominator) {
/* 286 */     this.maxScaleDenominator = maxScaleDenominator;
/*     */   }
/*     */   
/*     */   public double getMinScaleDenominator() {
/* 295 */     return this.minScaleDenominator;
/*     */   }
/*     */   
/*     */   public void setMinScaleDenominator(double minScaleDenominator) {
/* 304 */     this.minScaleDenominator = minScaleDenominator;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 308 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 312 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 322 */       RuleImpl clone = (RuleImpl)super.clone();
/* 324 */       clone.name = this.name;
/* 325 */       clone.description.setAbstract(this.description.getAbstract());
/* 326 */       clone.description.setTitle(this.description.getTitle());
/* 327 */       if (this.filter == null) {
/* 328 */         clone.filter = null;
/*     */       } else {
/* 330 */         DuplicatingFilterVisitor visitor = new DuplicatingFilterVisitor();
/* 331 */         clone.filter = (Filter)this.filter.accept((FilterVisitor)visitor, CommonFactoryFinder.getFilterFactory2(null));
/*     */       } 
/* 333 */       clone.hasElseFilter = this.hasElseFilter;
/* 334 */       clone.legends = new ArrayList<Graphic>(this.legends);
/* 336 */       clone.symbolizers = new ArrayList<Symbolizer>(this.symbolizers);
/* 338 */       clone.maxScaleDenominator = this.maxScaleDenominator;
/* 339 */       clone.minScaleDenominator = this.minScaleDenominator;
/* 341 */       return clone;
/* 342 */     } catch (CloneNotSupportedException e) {
/* 343 */       throw new RuntimeException("This will never happen", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 359 */     int PRIME = 1000003;
/* 360 */     int result = 0;
/* 361 */     result = 1000003 * result + this.symbolizers.hashCode();
/* 363 */     result = 1000003 * result + this.legends.hashCode();
/* 365 */     if (this.name != null)
/* 366 */       result = 1000003 * result + this.name.hashCode(); 
/* 369 */     if (this.description != null)
/* 370 */       result = 1000003 * result + this.description.hashCode(); 
/* 373 */     if (this.filter != null)
/* 374 */       result = 1000003 * result + this.filter.hashCode(); 
/* 377 */     result = 1000003 * result + (this.hasElseFilter ? 1 : 0);
/* 379 */     long temp = Double.doubleToLongBits(this.maxScaleDenominator);
/* 380 */     result = 1000003 * result + (int)(temp >>> 32L);
/* 381 */     result = 1000003 * result + (int)(temp & 0xFFFFFFFFFFFFFFFFL);
/* 382 */     temp = Double.doubleToLongBits(this.minScaleDenominator);
/* 383 */     result = 1000003 * result + (int)(temp >>> 32L);
/* 384 */     result = 1000003 * result + (int)(temp & 0xFFFFFFFFFFFFFFFFL);
/* 386 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object oth) {
/* 406 */     if (this == oth)
/* 407 */       return true; 
/* 410 */     if (oth instanceof RuleImpl) {
/* 411 */       RuleImpl other = (RuleImpl)oth;
/* 413 */       return (Utilities.equals(this.name, other.name) && Utilities.equals(this.description, other.description) && Utilities.equals(this.filter, other.filter) && this.hasElseFilter == other.hasElseFilter && Utilities.equals(this.legends, other.legends) && Utilities.equals(this.symbolizers, other.symbolizers) && Double.doubleToLongBits(this.maxScaleDenominator) == Double.doubleToLongBits(other.maxScaleDenominator) && Double.doubleToLongBits(this.minScaleDenominator) == Double.doubleToLongBits(other.minScaleDenominator));
/*     */     } 
/* 425 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 429 */     StringBuffer buf = new StringBuffer();
/* 430 */     buf.append("<RuleImpl");
/* 431 */     if (this.name != null) {
/* 432 */       buf.append(":");
/* 433 */       buf.append(this.name);
/*     */     } 
/* 435 */     buf.append("> ");
/* 436 */     buf.append(this.filter);
/* 437 */     if (this.symbolizers != null) {
/* 438 */       buf.append("\n");
/* 439 */       for (Symbolizer symbolizer : this.symbolizers) {
/* 440 */         buf.append("\t");
/* 441 */         buf.append(symbolizer);
/* 442 */         buf.append("\n");
/*     */       } 
/*     */     } 
/* 445 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public OnLineResource getOnlineResource() {
/* 449 */     return this.online;
/*     */   }
/*     */   
/*     */   public void setOnlineResource(OnLineResource online) {
/* 453 */     this.online = online;
/*     */   }
/*     */   
/*     */   static RuleImpl cast(Rule rule) {
/* 457 */     if (rule == null)
/* 458 */       return null; 
/* 460 */     if (rule instanceof RuleImpl)
/* 461 */       return (RuleImpl)rule; 
/* 464 */     RuleImpl copy = new RuleImpl(rule);
/* 465 */     return copy;
/*     */   }
/*     */   
/*     */   protected RuleImpl() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\RuleImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */