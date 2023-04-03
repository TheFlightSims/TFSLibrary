/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ 
/*     */ public class DefaultCompoundCS extends AbstractCS {
/*     */   private static final long serialVersionUID = -5726410275278843372L;
/*     */   
/*     */   private final CoordinateSystem[] cs;
/*     */   
/*     */   private transient List<CoordinateSystem> asList;
/*     */   
/*     */   public DefaultCompoundCS(CoordinateSystem[] cs) {
/*  70 */     super(getName(cs = clone(cs)), getAxis(cs));
/*  71 */     this.cs = cs;
/*     */   }
/*     */   
/*     */   private static CoordinateSystem[] clone(CoordinateSystem[] cs) {
/*  79 */     ensureNonNull("cs", cs);
/*  80 */     cs = (CoordinateSystem[])cs.clone();
/*  81 */     for (int i = 0; i < cs.length; i++)
/*  82 */       ensureNonNull("cs", (Object[])cs, i); 
/*  84 */     return cs;
/*     */   }
/*     */   
/*     */   private static CoordinateSystemAxis[] getAxis(CoordinateSystem[] cs) {
/*  91 */     int count = 0;
/*  92 */     for (int i = 0; i < cs.length; i++)
/*  93 */       count += cs[i].getDimension(); 
/*  95 */     CoordinateSystemAxis[] axis = new CoordinateSystemAxis[count];
/*  96 */     count = 0;
/*  97 */     for (int j = 0; j < cs.length; j++) {
/*  98 */       CoordinateSystem c = cs[j];
/*  99 */       int dim = c.getDimension();
/* 100 */       for (int k = 0; k < dim; k++)
/* 101 */         axis[count++] = c.getAxis(k); 
/*     */     } 
/* 104 */     assert count == axis.length;
/* 105 */     return axis;
/*     */   }
/*     */   
/*     */   private static String getName(CoordinateSystem[] cs) {
/* 115 */     StringBuilder buffer = new StringBuilder();
/* 116 */     for (int i = 0; i < cs.length; i++) {
/* 117 */       if (buffer.length() != 0)
/* 118 */         buffer.append(" / "); 
/* 120 */       buffer.append(cs[i].getName().getCode());
/*     */     } 
/* 122 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public synchronized List<CoordinateSystem> getCoordinateSystems() {
/* 129 */     if (this.asList == null)
/* 130 */       this.asList = Collections.unmodifiableList(Arrays.asList(this.cs)); 
/* 132 */     return this.asList;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 145 */     if (object == this)
/* 146 */       return true; 
/* 148 */     if (super.equals(object, compareMetadata)) {
/* 149 */       DefaultCompoundCS that = (DefaultCompoundCS)object;
/* 150 */       return equals((IdentifiedObject[])this.cs, (IdentifiedObject[])that.cs, compareMetadata);
/*     */     } 
/* 152 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DefaultCompoundCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */