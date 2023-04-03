/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.Or;
/*     */ 
/*     */ public abstract class LogicFilterImpl extends BinaryLogicAbstract implements LogicFilter {
/*  47 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.core");
/*     */   
/*     */   protected LogicFilterImpl(FilterFactory factory) {
/*  50 */     this(factory, new ArrayList());
/*     */   }
/*     */   
/*     */   protected LogicFilterImpl(FilterFactory factory, List children) {
/*  54 */     super(factory, children);
/*     */   }
/*     */   
/*     */   protected LogicFilterImpl(short filterType) throws IllegalFilterException {
/*  67 */     super(CommonFactoryFinder.getFilterFactory(null), new ArrayList());
/*  68 */     if (LOGGER.isLoggable(Level.FINEST))
/*  69 */       LOGGER.finest("filtertype " + filterType); 
/*  72 */     if (isLogicFilter(filterType)) {
/*  73 */       this.filterType = filterType;
/*     */     } else {
/*  75 */       throw new IllegalFilterException("Attempted to create logic filter with non-logic type.");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected LogicFilterImpl(Filter filter, short filterType) throws IllegalFilterException {
/*  92 */     super(CommonFactoryFinder.getFilterFactory(null), new ArrayList());
/*  93 */     if (isLogicFilter(filterType)) {
/*  94 */       this.filterType = filterType;
/*     */     } else {
/*  96 */       throw new IllegalFilterException("Attempted to create logic filter with non-logic type.");
/*     */     } 
/* 100 */     this.children.add(filter);
/*     */   }
/*     */   
/*     */   protected LogicFilterImpl(Filter filter1, Filter filter2, short filterType) throws IllegalFilterException {
/* 115 */     super(CommonFactoryFinder.getFilterFactory(null), new ArrayList());
/* 117 */     if (isLogicFilter(filterType)) {
/* 118 */       this.filterType = filterType;
/*     */     } else {
/* 120 */       throw new IllegalFilterException("Attempted to create logic filter with non-logic type.");
/*     */     } 
/* 125 */     this.children.add(filter1);
/* 128 */     addFilter(filter2);
/*     */   }
/*     */   
/*     */   public final void addFilter(Filter filter) throws IllegalFilterException {
/* 143 */     if (this.filterType != 3 || this.children.size() == 0) {
/* 144 */       this.children.add(filter);
/*     */     } else {
/* 146 */       throw new IllegalFilterException("Attempted to add an more than one filter to a NOT filter.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator getFilterIterator() {
/* 157 */     return this.children.iterator();
/*     */   }
/*     */   
/*     */   public Filter or(Filter filter) {
/* 176 */     if (this.filterType == 1) {
/* 177 */       if (filter instanceof Or) {
/* 178 */         Or more = (Or)filter;
/* 179 */         this.children.addAll(more.getChildren());
/*     */       } else {
/* 182 */         this.children.add(filter);
/*     */       } 
/* 184 */       return this;
/*     */     } 
/* 186 */     return super.or(filter);
/*     */   }
/*     */   
/*     */   public Filter and(Filter filter) {
/* 205 */     if (this.filterType == 2) {
/* 206 */       if (filter instanceof And) {
/* 207 */         And more = (And)filter;
/* 208 */         this.children.addAll(more.getChildren());
/*     */       } else {
/* 211 */         this.children.add(filter);
/*     */       } 
/* 213 */       return this;
/*     */     } 
/* 215 */     return super.and(filter);
/*     */   }
/*     */   
/*     */   public Filter not() {
/* 229 */     if (this.filterType == 3)
/* 230 */       return this.children.get(0); 
/* 232 */     return super.not();
/*     */   }
/*     */   
/*     */   List getSubFilters() {
/* 244 */     return this.children;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 253 */     String returnString = "[";
/* 254 */     String operator = "";
/* 255 */     Iterator<E> iterator = this.children.iterator();
/* 257 */     if (this.filterType == 1) {
/* 258 */       operator = " OR ";
/* 259 */     } else if (this.filterType == 2) {
/* 260 */       operator = " AND ";
/* 261 */     } else if (this.filterType == 3) {
/* 262 */       return "[ NOT " + iterator.next().toString() + " ]";
/*     */     } 
/* 265 */     while (iterator.hasNext()) {
/* 266 */       returnString = returnString + iterator.next().toString();
/* 268 */       if (iterator.hasNext())
/* 269 */         returnString = returnString + operator; 
/*     */     } 
/* 273 */     return returnString + "]";
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 290 */     if (obj == this)
/* 291 */       return true; 
/* 292 */     if (obj != null && obj.getClass() == getClass()) {
/* 293 */       LogicFilterImpl logFilter = (LogicFilterImpl)obj;
/* 294 */       if (LOGGER.isLoggable(Level.FINEST)) {
/* 295 */         LOGGER.finest("filter type match:" + ((logFilter.getFilterType() == this.filterType) ? 1 : 0));
/* 297 */         LOGGER.finest("same size:" + ((logFilter.getSubFilters().size() == this.children.size()) ? 1 : 0) + "; inner size: " + logFilter.getSubFilters().size() + "; outer size: " + this.children.size());
/* 301 */         LOGGER.finest("contains:" + logFilter.getSubFilters().containsAll(this.children));
/*     */       } 
/* 305 */       return (logFilter.getFilterType() == this.filterType && logFilter.getSubFilters().size() == this.children.size() && logFilter.getSubFilters().containsAll(this.children));
/*     */     } 
/* 309 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 319 */     int result = 17;
/* 320 */     result = 37 * result + this.filterType;
/* 321 */     result = 37 * result + this.children.hashCode();
/* 323 */     return result;
/*     */   }
/*     */   
/*     */   public abstract Object accept(FilterVisitor paramFilterVisitor, Object paramObject);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\LogicFilterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */