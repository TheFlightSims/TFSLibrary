/*     */ package org.geotools.referencing.datum;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.VerticalDatum;
/*     */ import org.opengis.referencing.datum.VerticalDatumType;
/*     */ 
/*     */ public class DefaultVerticalDatum extends AbstractDatum implements VerticalDatum {
/*     */   private static final long serialVersionUID = 380347456670516572L;
/*     */   
/*  59 */   private static final VerticalDatumType[] TYPES = VerticalDatumType.values();
/*     */   
/*  65 */   private static final short[] LEGACY_CODES = new short[TYPES.length];
/*     */   
/*     */   private final VerticalDatumType type;
/*     */   
/*     */   static {
/*  67 */     LEGACY_CODES[VerticalDatumType.GEOIDAL.ordinal()] = 2005;
/*  68 */     LEGACY_CODES[VerticalDatumType.ELLIPSOIDAL.ordinal()] = 2002;
/*  69 */     LEGACY_CODES[VerticalDatumType.DEPTH.ordinal()] = 2006;
/*  70 */     LEGACY_CODES[VerticalDatumType.BAROMETRIC.ordinal()] = 2003;
/*  71 */     LEGACY_CODES[VerticalDatumType.ORTHOMETRIC.ordinal()] = 2001;
/*  72 */     LEGACY_CODES[VerticalDatumType.OTHER_SURFACE.ordinal()] = 2000;
/*     */   }
/*     */   
/*  83 */   public static final DefaultVerticalDatum GEOIDAL = new DefaultVerticalDatum(name(88), VerticalDatumType.GEOIDAL);
/*     */   
/*  91 */   public static final DefaultVerticalDatum ELLIPSOIDAL = new DefaultVerticalDatum(name(57), VerticalDatumType.ELLIPSOIDAL);
/*     */   
/*     */   public DefaultVerticalDatum(String name, VerticalDatumType type) {
/* 101 */     this(Collections.singletonMap("name", name), type);
/*     */   }
/*     */   
/*     */   public DefaultVerticalDatum(VerticalDatum datum) {
/* 116 */     super((Datum)datum);
/* 117 */     this.type = datum.getVerticalDatumType();
/*     */   }
/*     */   
/*     */   public DefaultVerticalDatum(Map<String, ?> properties, VerticalDatumType type) {
/* 128 */     super(properties);
/* 129 */     this.type = type;
/* 130 */     ensureNonNull("type", type);
/*     */   }
/*     */   
/*     */   public VerticalDatumType getVerticalDatumType() {
/* 139 */     return this.type;
/*     */   }
/*     */   
/*     */   final int getLegacyDatumType() {
/* 147 */     int ordinal = this.type.ordinal();
/* 148 */     if (ordinal >= 0 && ordinal < LEGACY_CODES.length) {
/* 149 */       assert this.type.equals(TYPES[ordinal]) : this.type;
/* 150 */       return LEGACY_CODES[ordinal];
/*     */     } 
/* 152 */     return 0;
/*     */   }
/*     */   
/*     */   public static VerticalDatumType getVerticalDatumTypeFromLegacyCode(int code) {
/* 166 */     for (int i = 0; i < LEGACY_CODES.length; i++) {
/* 167 */       if (LEGACY_CODES[i] == code)
/* 168 */         return TYPES[i]; 
/*     */     } 
/* 171 */     return null;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 184 */     if (object == this)
/* 185 */       return true; 
/* 187 */     if (super.equals(object, compareMetadata)) {
/* 188 */       DefaultVerticalDatum that = (DefaultVerticalDatum)object;
/* 189 */       return Utilities.equals(this.type, that.type);
/*     */     } 
/* 191 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 206 */     return super.hashCode() ^ this.type.hashCode();
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 219 */     super.formatWKT(formatter);
/* 220 */     return "VERT_DATUM";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\datum\DefaultVerticalDatum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */