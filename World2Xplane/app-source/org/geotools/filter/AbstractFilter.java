/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ 
/*     */ public abstract class AbstractFilter extends FilterAbstract implements Filter {
/*  37 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.core");
/*     */   
/*     */   protected short filterType;
/*     */   
/*     */   protected boolean permissiveConstruction = true;
/*     */   
/*     */   protected AbstractFilter(FilterFactory factory) {
/*  51 */     super(factory);
/*     */   }
/*     */   
/*     */   public final boolean contains(SimpleFeature feature) {
/*  68 */     return evaluate(feature);
/*     */   }
/*     */   
/*     */   protected static boolean isLogicFilter(short filterType) {
/* 105 */     LOGGER.entering("AbstractFilter", "isLogicFilter", new Short(filterType));
/* 107 */     return (filterType == 1 || filterType == 2 || filterType == 3);
/*     */   }
/*     */   
/*     */   protected static boolean isMathFilter(short filterType) {
/* 119 */     return (filterType == 15 || filterType == 16 || filterType == 17 || filterType == 18);
/*     */   }
/*     */   
/*     */   protected static boolean isCompareFilter(short filterType) {
/* 133 */     return (isMathFilter(filterType) || filterType == 14 || filterType == 19 || filterType == 23);
/*     */   }
/*     */   
/*     */   protected static boolean isGeometryFilter(short filterType) {
/* 145 */     return (filterType == 4 || filterType == 5 || filterType == 6 || filterType == 8 || filterType == 7 || filterType == 9 || filterType == 10 || filterType == 11 || filterType == 12 || filterType == 24 || filterType == 13);
/*     */   }
/*     */   
/*     */   protected static boolean isGeometryDistanceFilter(short filterType) {
/* 163 */     return (filterType == 24 || filterType == 13);
/*     */   }
/*     */   
/*     */   protected static boolean isSimpleFilter(short filterType) {
/* 175 */     return (isCompareFilter(filterType) || isGeometryFilter(filterType) || filterType == 21 || filterType == 22 || filterType == 20);
/*     */   }
/*     */   
/*     */   public short getFilterType() {
/* 189 */     return this.filterType;
/*     */   }
/*     */   
/*     */   public final void accept(FilterVisitor visitor) {
/* 205 */     accept(new FilterVisitorFilterWrapper(visitor), null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\AbstractFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */