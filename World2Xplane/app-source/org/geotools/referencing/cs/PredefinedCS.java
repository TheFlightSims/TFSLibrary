/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.cs.AffineCS;
/*     */ import org.opengis.referencing.cs.CartesianCS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.CylindricalCS;
/*     */ import org.opengis.referencing.cs.EllipsoidalCS;
/*     */ import org.opengis.referencing.cs.LinearCS;
/*     */ import org.opengis.referencing.cs.PolarCS;
/*     */ import org.opengis.referencing.cs.SphericalCS;
/*     */ import org.opengis.referencing.cs.TimeCS;
/*     */ import org.opengis.referencing.cs.UserDefinedCS;
/*     */ import org.opengis.referencing.cs.VerticalCS;
/*     */ 
/*     */ final class PredefinedCS implements Comparator<CoordinateSystem> {
/*     */   private static Comparator<CoordinateSystem> csComparator;
/*     */   
/*  52 */   private final Class<? extends CoordinateSystem>[] types = new Class[] { CartesianCS.class, AffineCS.class, EllipsoidalCS.class, SphericalCS.class, CylindricalCS.class, PolarCS.class, VerticalCS.class, TimeCS.class, LinearCS.class, UserDefinedCS.class };
/*     */   
/*     */   public int compare(CoordinateSystem object1, CoordinateSystem object2) {
/*  77 */     Class<? extends CoordinateSystem> type1 = (Class)object1.getClass();
/*  78 */     Class<? extends CoordinateSystem> type2 = (Class)object2.getClass();
/*  79 */     for (int i = 0; i < this.types.length; i++) {
/*  80 */       Class<?> type = this.types[i];
/*  81 */       boolean a1 = type.isAssignableFrom(type1);
/*  82 */       boolean a2 = type.isAssignableFrom(type2);
/*  83 */       if (a1)
/*  83 */         return a2 ? 0 : -1; 
/*  84 */       if (a2)
/*  84 */         return a1 ? 0 : 1; 
/*     */     } 
/*  86 */     return 0;
/*     */   }
/*     */   
/*     */   static CoordinateSystem standard(CoordinateSystem cs) throws IllegalArgumentException {
/*  93 */     int dimension = cs.getDimension();
/*  94 */     if (cs instanceof CartesianCS)
/*  95 */       switch (dimension) {
/*     */         case 2:
/*  97 */           if (DefaultCartesianCS.PROJECTED.axisColinearWith(cs))
/*  98 */             return DefaultCartesianCS.PROJECTED; 
/* 100 */           if (DefaultCartesianCS.GRID.axisColinearWith(cs))
/* 101 */             return DefaultCartesianCS.GRID; 
/* 103 */           if (DefaultCartesianCS.GENERIC_2D.directionColinearWith(cs))
/* 104 */             return DefaultCartesianCS.GENERIC_2D; 
/* 106 */           return (CoordinateSystem)rightHanded((AffineCS)cs);
/*     */         case 3:
/* 109 */           if (DefaultCartesianCS.GEOCENTRIC.axisColinearWith(cs))
/* 110 */             return DefaultCartesianCS.GEOCENTRIC; 
/* 112 */           if (DefaultCartesianCS.GENERIC_3D.directionColinearWith(cs))
/* 113 */             return DefaultCartesianCS.GENERIC_3D; 
/* 115 */           return (CoordinateSystem)rightHanded((AffineCS)cs);
/*     */       }  
/* 119 */     if (cs instanceof AffineCS)
/* 120 */       return (CoordinateSystem)rightHanded((AffineCS)cs); 
/* 122 */     if (cs instanceof EllipsoidalCS)
/* 123 */       switch (dimension) {
/*     */         case 2:
/* 124 */           return DefaultEllipsoidalCS.GEODETIC_2D;
/*     */         case 3:
/* 125 */           return DefaultEllipsoidalCS.GEODETIC_3D;
/*     */       }  
/* 128 */     if (cs instanceof SphericalCS)
/* 129 */       switch (dimension) {
/*     */         case 3:
/* 130 */           return DefaultSphericalCS.GEOCENTRIC;
/*     */       }  
/* 133 */     if (cs instanceof VerticalCS)
/* 134 */       switch (dimension) {
/*     */         case 1:
/* 136 */           return DefaultVerticalCS.ELLIPSOIDAL_HEIGHT;
/*     */       }  
/* 140 */     if (cs instanceof TimeCS)
/* 141 */       switch (dimension) {
/*     */         case 1:
/* 142 */           return DefaultTimeCS.DAYS;
/*     */       }  
/* 145 */     if (cs instanceof DefaultCompoundCS) {
/* 146 */       List<CoordinateSystem> components = ((DefaultCompoundCS)cs).getCoordinateSystems();
/* 147 */       CoordinateSystem[] user = new CoordinateSystem[components.size()];
/* 148 */       CoordinateSystem[] std = new CoordinateSystem[user.length];
/* 149 */       for (int i = 0; i < std.length; i++)
/* 150 */         std[i] = standard(user[i] = components.get(i)); 
/* 152 */       if (csComparator == null)
/* 153 */         csComparator = new PredefinedCS(); 
/* 155 */       Arrays.sort(std, csComparator);
/* 156 */       return Arrays.equals((Object[])user, (Object[])std) ? cs : new DefaultCompoundCS(std);
/*     */     } 
/* 158 */     throw new IllegalArgumentException(Errors.format(196, cs.getName().getCode()));
/*     */   }
/*     */   
/*     */   private static AffineCS rightHanded(AffineCS cs) {
/*     */     int j;
/* 168 */     boolean changed = false;
/* 169 */     int dimension = cs.getDimension();
/* 170 */     CoordinateSystemAxis[] axis = new CoordinateSystemAxis[dimension];
/* 171 */     for (int i = 0; i < dimension; i++) {
/* 178 */       CoordinateSystemAxis axe = axis[i] = cs.getAxis(i);
/* 179 */       DefaultCoordinateSystemAxis standard = DefaultCoordinateSystemAxis.getPredefined(axe);
/* 180 */       if (standard != null)
/* 181 */         axe = standard; 
/* 189 */       Unit<?> unit = axe.getUnit();
/* 190 */       if (!Unit.ONE.equals(unit) && !SI.METER.equals(unit)) {
/* 191 */         if (!(axe instanceof DefaultCoordinateSystemAxis))
/* 192 */           axe = new DefaultCoordinateSystemAxis(axe); 
/* 194 */         axe = ((DefaultCoordinateSystemAxis)axe).usingUnit(SI.METER);
/*     */       } 
/* 196 */       j = changed | ((axe != axis[i]) ? 1 : 0);
/* 197 */       axis[i] = axe;
/*     */     } 
/* 203 */     boolean bool1 = j | ComparableAxisWrapper.sort(axis);
/* 204 */     if (!bool1)
/* 205 */       return cs; 
/* 207 */     Map<String, ?> properties = DefaultAffineCS.getProperties((IdentifiedObject)cs, null);
/* 208 */     if (cs instanceof CartesianCS)
/* 209 */       return new DefaultCartesianCS(properties, axis); 
/* 211 */     return new DefaultAffineCS(properties, axis);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\PredefinedCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */