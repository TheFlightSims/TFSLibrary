/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.cs.DefaultCompoundCS;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.UnmodifiableArrayList;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.CheckedCollection;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.CompoundCRS;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.SingleCRS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ 
/*     */ public class DefaultCompoundCRS extends AbstractCRS implements CompoundCRS {
/*     */   private static final long serialVersionUID = -2656710314586929286L;
/*     */   
/*     */   private final List<? extends CoordinateReferenceSystem> crs;
/*     */   
/*     */   private transient List<SingleCRS> singles;
/*     */   
/*     */   public DefaultCompoundCRS(CompoundCRS crs) {
/*  90 */     super((CoordinateReferenceSystem)crs);
/*  91 */     if (crs instanceof DefaultCompoundCRS) {
/*  92 */       DefaultCompoundCRS that = (DefaultCompoundCRS)crs;
/*  93 */       this.crs = that.crs;
/*  94 */       this.singles = that.singles;
/*     */     } else {
/*  96 */       this.crs = copy(crs.getCoordinateReferenceSystems());
/*     */     } 
/*     */   }
/*     */   
/*     */   public DefaultCompoundCRS(String name, CoordinateReferenceSystem head, CoordinateReferenceSystem tail) {
/* 111 */     this(name, new CoordinateReferenceSystem[] { head, tail });
/*     */   }
/*     */   
/*     */   public DefaultCompoundCRS(String name, CoordinateReferenceSystem head, CoordinateReferenceSystem middle, CoordinateReferenceSystem tail) {
/* 127 */     this(name, new CoordinateReferenceSystem[] { head, middle, tail });
/*     */   }
/*     */   
/*     */   public DefaultCompoundCRS(String name, CoordinateReferenceSystem[] crs) {
/* 137 */     this(Collections.singletonMap("name", name), crs);
/*     */   }
/*     */   
/*     */   public DefaultCompoundCRS(Map<String, ?> properties, CoordinateReferenceSystem[] crs) {
/* 149 */     super(properties, createCoordinateSystem(crs));
/* 150 */     this.crs = copy(Arrays.asList(crs));
/*     */   }
/*     */   
/*     */   private static CoordinateSystem createCoordinateSystem(CoordinateReferenceSystem[] crs) {
/* 159 */     ensureNonNull("crs", crs);
/* 160 */     if (crs.length < 2)
/* 161 */       throw new IllegalArgumentException(Errors.format(99, "crs[" + crs.length + ']')); 
/* 164 */     CoordinateSystem[] cs = new CoordinateSystem[crs.length];
/* 165 */     for (int i = 0; i < crs.length; i++) {
/* 166 */       ensureNonNull("crs", (Object[])crs, i);
/* 167 */       cs[i] = crs[i].getCoordinateSystem();
/*     */     } 
/* 169 */     return (CoordinateSystem)new DefaultCompoundCS(cs);
/*     */   }
/*     */   
/*     */   private List<? extends CoordinateReferenceSystem> copy(List<? extends CoordinateReferenceSystem> crs) {
/*     */     List<SingleCRS> list;
/*     */     UnmodifiableArrayList unmodifiableArrayList;
/* 184 */     if (computeSingleCRS(crs)) {
/* 185 */       list = this.singles;
/*     */     } else {
/* 187 */       unmodifiableArrayList = UnmodifiableArrayList.wrap(list.toArray((Object[])new CoordinateReferenceSystem[list.size()]));
/*     */     } 
/* 189 */     return (List<? extends CoordinateReferenceSystem>)unmodifiableArrayList;
/*     */   }
/*     */   
/*     */   public List<CoordinateReferenceSystem> getCoordinateReferenceSystems() {
/* 199 */     return (List)this.crs;
/*     */   }
/*     */   
/*     */   public List<SingleCRS> getSingleCRS() {
/* 210 */     return this.singles;
/*     */   }
/*     */   
/*     */   public static List<SingleCRS> getSingleCRS(CoordinateReferenceSystem crs) {
/*     */     List<SingleCRS> singles;
/* 223 */     if (crs instanceof DefaultCompoundCRS) {
/* 224 */       singles = ((DefaultCompoundCRS)crs).getSingleCRS();
/* 225 */     } else if (crs instanceof CompoundCRS) {
/* 226 */       List<CoordinateReferenceSystem> elements = ((CompoundCRS)crs).getCoordinateReferenceSystems();
/* 228 */       singles = new ArrayList<SingleCRS>(elements.size());
/* 229 */       getSingleCRS(elements, singles);
/*     */     } else {
/* 231 */       singles = Collections.singletonList((SingleCRS)crs);
/*     */     } 
/* 233 */     return singles;
/*     */   }
/*     */   
/*     */   private static boolean getSingleCRS(List<? extends CoordinateReferenceSystem> source, List<SingleCRS> target) {
/* 244 */     boolean identical = true;
/* 245 */     for (CoordinateReferenceSystem candidate : source) {
/* 246 */       if (candidate instanceof CompoundCRS) {
/* 247 */         getSingleCRS(((CompoundCRS)candidate).getCoordinateReferenceSystems(), target);
/* 248 */         identical = false;
/*     */         continue;
/*     */       } 
/* 250 */       target.add((SingleCRS)candidate);
/*     */     } 
/* 253 */     return identical;
/*     */   }
/*     */   
/*     */   private boolean computeSingleCRS(List<? extends CoordinateReferenceSystem> crs) {
/* 261 */     this.singles = new ArrayList<SingleCRS>(crs.size());
/* 262 */     boolean identical = getSingleCRS(crs, this.singles);
/* 263 */     this.singles = (List<SingleCRS>)UnmodifiableArrayList.wrap(this.singles.toArray((Object[])new SingleCRS[this.singles.size()]));
/* 264 */     return identical;
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 272 */     in.defaultReadObject();
/* 273 */     if (this.crs instanceof CheckedCollection) {
/* 274 */       Class<?> type = ((CheckedCollection)this.crs).getElementType();
/* 275 */       if (SingleCRS.class.isAssignableFrom(type)) {
/* 276 */         this.singles = (List)this.crs;
/*     */         return;
/*     */       } 
/*     */     } 
/* 280 */     computeSingleCRS(this.crs);
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 293 */     if (object == this)
/* 294 */       return true; 
/* 296 */     if (super.equals(object, compareMetadata)) {
/* 297 */       DefaultCompoundCRS that = (DefaultCompoundCRS)object;
/* 298 */       return equals(this.crs, that.crs, compareMetadata);
/*     */     } 
/* 300 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 312 */     return this.crs.hashCode() ^ 0x73EE077A;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 325 */     for (CoordinateReferenceSystem element : this.crs)
/* 326 */       formatter.append((IdentifiedObject)element); 
/* 328 */     return "COMPD_CS";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\DefaultCompoundCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */