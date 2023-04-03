/*     */ package org.geotools.filter;
/*     */ 
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.ExcludeFilter;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.IncludeFilter;
/*     */ import org.opengis.filter.Not;
/*     */ import org.opengis.filter.Or;
/*     */ import org.opengis.filter.PropertyIsBetween;
/*     */ import org.opengis.filter.PropertyIsEqualTo;
/*     */ import org.opengis.filter.PropertyIsGreaterThan;
/*     */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLessThan;
/*     */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLike;
/*     */ import org.opengis.filter.PropertyIsNil;
/*     */ import org.opengis.filter.PropertyIsNotEqualTo;
/*     */ import org.opengis.filter.PropertyIsNull;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.Beyond;
/*     */ import org.opengis.filter.spatial.Contains;
/*     */ import org.opengis.filter.spatial.Crosses;
/*     */ import org.opengis.filter.spatial.DWithin;
/*     */ import org.opengis.filter.spatial.Disjoint;
/*     */ import org.opengis.filter.spatial.Equals;
/*     */ import org.opengis.filter.spatial.Intersects;
/*     */ import org.opengis.filter.spatial.Overlaps;
/*     */ import org.opengis.filter.spatial.SpatialOperator;
/*     */ import org.opengis.filter.spatial.Touches;
/*     */ import org.opengis.filter.spatial.Within;
/*     */ import org.opengis.filter.temporal.After;
/*     */ import org.opengis.filter.temporal.AnyInteracts;
/*     */ import org.opengis.filter.temporal.Before;
/*     */ import org.opengis.filter.temporal.Begins;
/*     */ import org.opengis.filter.temporal.BegunBy;
/*     */ import org.opengis.filter.temporal.BinaryTemporalOperator;
/*     */ import org.opengis.filter.temporal.During;
/*     */ import org.opengis.filter.temporal.EndedBy;
/*     */ import org.opengis.filter.temporal.Ends;
/*     */ import org.opengis.filter.temporal.Meets;
/*     */ import org.opengis.filter.temporal.MetBy;
/*     */ import org.opengis.filter.temporal.OverlappedBy;
/*     */ import org.opengis.filter.temporal.TContains;
/*     */ import org.opengis.filter.temporal.TEquals;
/*     */ import org.opengis.filter.temporal.TOverlaps;
/*     */ 
/*     */ public class FilterVisitorFilterWrapper implements FilterVisitor {
/*     */   FilterVisitor delegate;
/*     */   
/*     */   public FilterVisitorFilterWrapper(FilterVisitor delegate) {
/*  80 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */   protected void visitLogicFilter(Filter filter) {
/*  84 */     if (filter instanceof LogicFilter)
/*  85 */       this.delegate.visit((LogicFilter)filter); 
/*     */   }
/*     */   
/*     */   protected void visitCompareFilter(Filter filter) {
/*  90 */     if (filter instanceof BetweenFilter) {
/*  91 */       this.delegate.visit((BetweenFilter)filter);
/*     */       return;
/*     */     } 
/*  95 */     if (filter instanceof NullFilter) {
/*  96 */       this.delegate.visit((NullFilter)filter);
/*     */       return;
/*     */     } 
/* 100 */     if (filter instanceof LikeFilter)
/* 101 */       this.delegate.visit((LikeFilter)filter); 
/* 105 */     if (filter instanceof CompareFilter)
/* 106 */       this.delegate.visit((CompareFilter)filter); 
/*     */   }
/*     */   
/*     */   protected void visitGeometryFilter(SpatialOperator filter) {
/* 111 */     if (filter instanceof GeometryFilter)
/* 112 */       this.delegate.visit((GeometryFilter)filter); 
/*     */   }
/*     */   
/*     */   public Object visit(And filter, Object extraData) {
/* 117 */     visitLogicFilter((Filter)filter);
/* 118 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Id filter, Object extraData) {
/* 122 */     if (filter instanceof FidFilter)
/* 123 */       this.delegate.visit((FidFilter)filter); 
/* 126 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visitNullFilter(Object extraData) {
/* 130 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(IncludeFilter filter, Object extraData) {
/* 133 */     if (this.delegate instanceof FilterVisitor2)
/* 134 */       ((FilterVisitor2)this.delegate).visit(filter); 
/* 135 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(ExcludeFilter filter, Object extraData) {
/* 138 */     if (this.delegate instanceof FilterVisitor2)
/* 139 */       ((FilterVisitor2)this.delegate).visit(filter); 
/* 140 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Not filter, Object extraData) {
/* 143 */     visitLogicFilter((Filter)filter);
/* 144 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object extraData) {
/* 148 */     visitLogicFilter((Filter)filter);
/* 149 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object extraData) {
/* 153 */     visitCompareFilter((Filter)filter);
/* 154 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object extraData) {
/* 158 */     visitCompareFilter((Filter)filter);
/* 159 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object extraData) {
/* 163 */     visitCompareFilter((Filter)filter);
/* 164 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object extraData) {
/* 168 */     visitCompareFilter((Filter)filter);
/* 169 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object extraData) {
/* 173 */     visitCompareFilter((Filter)filter);
/* 174 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object extraData) {
/* 178 */     visitCompareFilter((Filter)filter);
/* 179 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object extraData) {
/* 183 */     visitCompareFilter((Filter)filter);
/* 184 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLike filter, Object extraData) {
/* 188 */     visitCompareFilter((Filter)filter);
/* 189 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNull filter, Object extraData) {
/* 193 */     visitCompareFilter((Filter)filter);
/* 194 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNil filter, Object extraData) {
/* 198 */     visitCompareFilter((Filter)filter);
/* 199 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object extraData) {
/* 203 */     visitGeometryFilter((SpatialOperator)filter);
/* 204 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object extraData) {
/* 208 */     visitGeometryFilter((SpatialOperator)filter);
/* 209 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object extraData) {
/* 213 */     visitGeometryFilter((SpatialOperator)filter);
/* 214 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object extraData) {
/* 218 */     visitGeometryFilter((SpatialOperator)filter);
/* 219 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object extraData) {
/* 223 */     visitGeometryFilter((SpatialOperator)filter);
/* 224 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object extraData) {
/* 228 */     visitGeometryFilter((SpatialOperator)filter);
/* 229 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object extraData) {
/* 233 */     visitGeometryFilter((SpatialOperator)filter);
/* 234 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object extraData) {
/* 238 */     visitGeometryFilter((SpatialOperator)filter);
/* 239 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object extraData) {
/* 243 */     visitGeometryFilter((SpatialOperator)filter);
/* 244 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object extraData) {
/* 248 */     visitGeometryFilter((SpatialOperator)filter);
/* 249 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object extraData) {
/* 253 */     visitGeometryFilter((SpatialOperator)filter);
/* 254 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(After after, Object extraData) {
/* 258 */     return visitTemporalFilter((BinaryTemporalOperator)after);
/*     */   }
/*     */   
/*     */   public Object visit(AnyInteracts anyInteracts, Object extraData) {
/* 262 */     return visitTemporalFilter((BinaryTemporalOperator)anyInteracts);
/*     */   }
/*     */   
/*     */   public Object visit(Before before, Object extraData) {
/* 266 */     return visitTemporalFilter((BinaryTemporalOperator)before);
/*     */   }
/*     */   
/*     */   public Object visit(Begins begins, Object extraData) {
/* 270 */     return visitTemporalFilter((BinaryTemporalOperator)begins);
/*     */   }
/*     */   
/*     */   public Object visit(BegunBy begunBy, Object extraData) {
/* 274 */     return visitTemporalFilter((BinaryTemporalOperator)begunBy);
/*     */   }
/*     */   
/*     */   public Object visit(During during, Object extraData) {
/* 278 */     return visitTemporalFilter((BinaryTemporalOperator)during);
/*     */   }
/*     */   
/*     */   public Object visit(EndedBy endedBy, Object extraData) {
/* 282 */     return visitTemporalFilter((BinaryTemporalOperator)endedBy);
/*     */   }
/*     */   
/*     */   public Object visit(Ends ends, Object extraData) {
/* 286 */     return visitTemporalFilter((BinaryTemporalOperator)ends);
/*     */   }
/*     */   
/*     */   public Object visit(Meets meets, Object extraData) {
/* 290 */     return visitTemporalFilter((BinaryTemporalOperator)meets);
/*     */   }
/*     */   
/*     */   public Object visit(MetBy metBy, Object extraData) {
/* 294 */     return visitTemporalFilter((BinaryTemporalOperator)metBy);
/*     */   }
/*     */   
/*     */   public Object visit(OverlappedBy overlappedBy, Object extraData) {
/* 298 */     return visitTemporalFilter((BinaryTemporalOperator)overlappedBy);
/*     */   }
/*     */   
/*     */   public Object visit(TContains contains, Object extraData) {
/* 302 */     return visitTemporalFilter((BinaryTemporalOperator)contains);
/*     */   }
/*     */   
/*     */   public Object visit(TEquals equals, Object extraData) {
/* 306 */     return visitTemporalFilter((BinaryTemporalOperator)equals);
/*     */   }
/*     */   
/*     */   public Object visit(TOverlaps contains, Object extraData) {
/* 310 */     return visitTemporalFilter((BinaryTemporalOperator)contains);
/*     */   }
/*     */   
/*     */   protected Object visitTemporalFilter(BinaryTemporalOperator filter) {
/* 314 */     throw new UnsupportedOperationException("Temporal filters not supported");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterVisitorFilterWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */