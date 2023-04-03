/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.ExcludeFilter;
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
/*     */ import org.opengis.filter.spatial.Touches;
/*     */ import org.opengis.filter.spatial.Within;
/*     */ import org.opengis.filter.temporal.After;
/*     */ import org.opengis.filter.temporal.AnyInteracts;
/*     */ import org.opengis.filter.temporal.Before;
/*     */ import org.opengis.filter.temporal.Begins;
/*     */ import org.opengis.filter.temporal.BegunBy;
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
/*     */ public class OperatorNameFilterVisitor implements FilterVisitor {
/*     */   public Object visit(ExcludeFilter filter, Object extraData) {
/*  80 */     return "Exclude";
/*     */   }
/*     */   
/*     */   public Object visit(IncludeFilter filter, Object extraData) {
/*  83 */     return "Include";
/*     */   }
/*     */   
/*     */   public Object visit(And filter, Object extraData) {
/*  86 */     return "And";
/*     */   }
/*     */   
/*     */   public Object visit(Id filter, Object extraData) {
/*  89 */     return "Id";
/*     */   }
/*     */   
/*     */   public Object visit(Not filter, Object extraData) {
/*  92 */     return "Not";
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object extraData) {
/*  95 */     return "Or";
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object extraData) {
/*  98 */     return "Between";
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object extraData) {
/* 101 */     return "EqualTo";
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object extraData) {
/* 104 */     return "NotEqualTo";
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object extraData) {
/* 107 */     return "GreaterThan";
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object extraData) {
/* 110 */     return "GreaterThanOrEqualTo";
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object extraData) {
/* 113 */     return "LessThan";
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object extraData) {
/* 116 */     return "LessThanOrEqualTo";
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLike filter, Object extraData) {
/* 119 */     return "Like";
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNull filter, Object extraData) {
/* 122 */     return "Like";
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNil filter, Object extraData) {
/* 125 */     return "Nil";
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object extraData) {
/* 128 */     return "BBOX";
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object extraData) {
/* 131 */     return "Beyond";
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object extraData) {
/* 134 */     return "Contains";
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object extraData) {
/* 137 */     return "Crosses";
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object extraData) {
/* 140 */     return "Disjoint";
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object extraData) {
/* 143 */     return "DWithin";
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object extraData) {
/* 146 */     return "Equals";
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object extraData) {
/* 149 */     return "Intersects";
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object extraData) {
/* 152 */     return "Overlaps";
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object extraData) {
/* 155 */     return "Touches";
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object extraData) {
/* 158 */     return "Within";
/*     */   }
/*     */   
/*     */   public Object visitNullFilter(Object extraData) {
/* 161 */     return "null";
/*     */   }
/*     */   
/*     */   public Object visit(After after, Object extraData) {
/* 166 */     return "After";
/*     */   }
/*     */   
/*     */   public Object visit(AnyInteracts anyInteracts, Object extraData) {
/* 170 */     return "AnyInteracts";
/*     */   }
/*     */   
/*     */   public Object visit(Before before, Object extraData) {
/* 174 */     return "Before";
/*     */   }
/*     */   
/*     */   public Object visit(Begins begins, Object extraData) {
/* 178 */     return "Begins";
/*     */   }
/*     */   
/*     */   public Object visit(BegunBy begunBy, Object extraData) {
/* 182 */     return "BegunBy";
/*     */   }
/*     */   
/*     */   public Object visit(During during, Object extraData) {
/* 186 */     return "During";
/*     */   }
/*     */   
/*     */   public Object visit(EndedBy endedBy, Object extraData) {
/* 190 */     return "EndedBy";
/*     */   }
/*     */   
/*     */   public Object visit(Ends ends, Object extraData) {
/* 194 */     return "Ends";
/*     */   }
/*     */   
/*     */   public Object visit(Meets meets, Object extraData) {
/* 198 */     return "Meets";
/*     */   }
/*     */   
/*     */   public Object visit(MetBy metBy, Object extraData) {
/* 202 */     return "MetBy";
/*     */   }
/*     */   
/*     */   public Object visit(OverlappedBy overlappedBy, Object extraData) {
/* 206 */     return "OverlappedBy";
/*     */   }
/*     */   
/*     */   public Object visit(TContains contains, Object extraData) {
/* 210 */     return "TContains";
/*     */   }
/*     */   
/*     */   public Object visit(TEquals equals, Object extraData) {
/* 214 */     return "TEquals";
/*     */   }
/*     */   
/*     */   public Object visit(TOverlaps contains, Object extraData) {
/* 218 */     return "TOverlaps";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\OperatorNameFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */