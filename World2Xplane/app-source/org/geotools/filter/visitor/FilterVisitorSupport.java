/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.BinaryComparisonOperator;
/*     */ import org.opengis.filter.BinaryLogicOperator;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.Or;
/*     */ import org.opengis.filter.PropertyIsEqualTo;
/*     */ import org.opengis.filter.PropertyIsGreaterThan;
/*     */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLessThan;
/*     */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsNotEqualTo;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.Beyond;
/*     */ import org.opengis.filter.spatial.BinarySpatialOperator;
/*     */ import org.opengis.filter.spatial.Contains;
/*     */ import org.opengis.filter.spatial.Crosses;
/*     */ import org.opengis.filter.spatial.DWithin;
/*     */ import org.opengis.filter.spatial.Disjoint;
/*     */ import org.opengis.filter.spatial.Equals;
/*     */ import org.opengis.filter.spatial.Intersects;
/*     */ import org.opengis.filter.spatial.Overlaps;
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
/*     */ public abstract class FilterVisitorSupport implements FilterVisitor {
/*     */   public Object visit(And filter, Object extraData) {
/*  81 */     return visit((BinaryLogicOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object extraData) {
/*  85 */     return visit((BinaryLogicOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   protected abstract Object visit(BinaryLogicOperator paramBinaryLogicOperator, Object paramObject);
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object extraData) {
/*  91 */     return visit((BinaryComparisonOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object extraData) {
/*  95 */     return visit((BinaryComparisonOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object extraData) {
/*  99 */     return visit((BinaryComparisonOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object extraData) {
/* 103 */     return visit((BinaryComparisonOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object extraData) {
/* 107 */     return visit((BinaryComparisonOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object extraData) {
/* 111 */     return visit((BinaryComparisonOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   protected abstract Object visit(BinaryComparisonOperator paramBinaryComparisonOperator, Object paramObject);
/*     */   
/*     */   public Object visit(BBOX filter, Object extraData) {
/* 117 */     return visit((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object extraData) {
/* 121 */     return visit((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object extraData) {
/* 125 */     return visit((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object extraData) {
/* 129 */     return visit((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object extraData) {
/* 133 */     return visit((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object extraData) {
/* 137 */     return visit((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object extraData) {
/* 141 */     return visit((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object extraData) {
/* 145 */     return visit((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object extraData) {
/* 149 */     return visit((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object extraData) {
/* 153 */     return visit((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object extraData) {
/* 157 */     return visit((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   protected abstract Object visit(BinarySpatialOperator paramBinarySpatialOperator, Object paramObject);
/*     */   
/*     */   public Object visit(After filter, Object extraData) {
/* 163 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(AnyInteracts filter, Object extraData) {
/* 167 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Before filter, Object extraData) {
/* 171 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Begins filter, Object extraData) {
/* 175 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(BegunBy filter, Object extraData) {
/* 179 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(During filter, Object extraData) {
/* 183 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(EndedBy filter, Object extraData) {
/* 187 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Ends filter, Object extraData) {
/* 191 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Meets filter, Object extraData) {
/* 195 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(MetBy filter, Object extraData) {
/* 199 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(OverlappedBy filter, Object extraData) {
/* 203 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(TContains filter, Object extraData) {
/* 207 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(TEquals filter, Object extraData) {
/* 211 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(TOverlaps filter, Object extraData) {
/* 215 */     return visit((BinaryTemporalOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   protected abstract Object visit(BinaryTemporalOperator paramBinaryTemporalOperator, Object paramObject);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\FilterVisitorSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */