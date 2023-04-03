/*     */ package org.geotools.gml.producer;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Locale;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ 
/*     */ public class CoordinateWriter {
/*     */   private final String coordinateDelimiter;
/*     */   
/*     */   private final String tupleDelimiter;
/*     */   
/*  56 */   private final NumberFormat coordFormatter = NumberFormat.getInstance(Locale.US);
/*     */   
/*  58 */   private final AttributesImpl atts = new AttributesImpl();
/*     */   
/*  60 */   private final StringBuffer coordBuff = new StringBuffer();
/*     */   
/*  62 */   private final FieldPosition zero = new FieldPosition(0);
/*     */   
/*  64 */   private char[] buff = new char[200];
/*     */   
/*     */   private final boolean useDummyZ;
/*     */   
/*     */   private final double dummyZ;
/*     */   
/*     */   private final int D;
/*     */   
/*     */   private boolean namespaceAware = true;
/*     */   
/*  84 */   private String prefix = "gml";
/*     */   
/*  85 */   private String namespaceUri = "http://www.opengis.net/gml";
/*     */   
/*     */   private final double scale;
/*     */   
/*  96 */   private static final double DECIMAL_MIN = Math.pow(10.0D, -3.0D);
/*     */   
/* 102 */   private static final double DECIMAL_MAX = Math.pow(10.0D, 7.0D);
/*     */   
/*     */   public CoordinateWriter() {
/* 105 */     this(4);
/*     */   }
/*     */   
/*     */   public CoordinateWriter(int numDecimals, boolean isDummyZEnabled) {
/* 109 */     this(numDecimals, " ", ",", isDummyZEnabled);
/*     */   }
/*     */   
/*     */   public CoordinateWriter(int numDecimals) {
/* 113 */     this(numDecimals, false);
/*     */   }
/*     */   
/*     */   public CoordinateWriter(int numDecimals, String tupleDelim, String coordDelim) {
/* 118 */     this(numDecimals, tupleDelim, coordDelim, false);
/*     */   }
/*     */   
/*     */   public CoordinateWriter(int numDecimals, String tupleDelim, String coordDelim, boolean useDummyZ) {
/* 122 */     this(numDecimals, tupleDelim, coordDelim, useDummyZ, 0.0D);
/*     */   }
/*     */   
/*     */   public CoordinateWriter(int numDecimals, String tupleDelim, String coordDelim, boolean useDummyZ, double zValue) {
/* 125 */     this(numDecimals, tupleDelim, coordDelim, useDummyZ, 0.0D, 2);
/*     */   }
/*     */   
/*     */   public CoordinateWriter(int numDecimals, boolean useDummyZ, int dimension) {
/* 129 */     this(numDecimals, " ", ",", useDummyZ, 0.0D, dimension);
/*     */   }
/*     */   
/*     */   public CoordinateWriter(int numDecimals, String tupleDelim, String coordDelim, boolean useZ, double z, int dimension) {
/* 150 */     if (tupleDelim == null || tupleDelim.length() == 0)
/* 151 */       throw new IllegalArgumentException("Tuple delimeter cannot be null or zero length"); 
/* 153 */     if (coordDelim != null && coordDelim.length() == 0)
/* 154 */       throw new IllegalArgumentException("Coordinate delimeter cannot be null or zero length"); 
/* 156 */     this.D = dimension;
/* 158 */     this.tupleDelimiter = tupleDelim;
/* 159 */     this.coordinateDelimiter = coordDelim;
/* 161 */     this.coordFormatter.setMaximumFractionDigits(numDecimals);
/* 162 */     this.coordFormatter.setGroupingUsed(false);
/* 164 */     this.scale = Math.pow(10.0D, numDecimals);
/* 166 */     String uri = this.namespaceUri;
/* 167 */     if (!this.namespaceAware)
/* 168 */       uri = null; 
/* 171 */     this.atts.addAttribute(uri, "decimal", "decimal", "decimal", ".");
/* 172 */     this.atts.addAttribute(uri, "cs", "cs", "cs", this.coordinateDelimiter);
/* 174 */     this.atts.addAttribute(uri, "ts", "ts", "ts", this.tupleDelimiter);
/* 176 */     this.useDummyZ = useZ;
/* 177 */     this.dummyZ = z;
/*     */   }
/*     */   
/*     */   public int getNumDecimals() {
/* 181 */     return this.coordFormatter.getMaximumFractionDigits();
/*     */   }
/*     */   
/*     */   public boolean isDummyZEnabled() {
/* 185 */     return this.useDummyZ;
/*     */   }
/*     */   
/*     */   public void setNamespaceAware(boolean namespaceAware) {
/* 190 */     this.namespaceAware = namespaceAware;
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix) {
/* 194 */     this.prefix = prefix;
/*     */   }
/*     */   
/*     */   public void setNamespaceUri(String namespaceUri) {
/* 198 */     this.namespaceUri = namespaceUri;
/*     */   }
/*     */   
/*     */   public void writeCoordinates(Coordinate[] c, ContentHandler output) throws SAXException {
/* 219 */     writeCoordinates((CoordinateSequence)new CoordinateArraySequence(c), output);
/*     */   }
/*     */   
/*     */   public void writeCoordinates(CoordinateSequence c, ContentHandler output) throws SAXException {
/* 241 */     String prefix = this.prefix + ":";
/* 242 */     String namespaceUri = this.namespaceUri;
/* 244 */     if (!this.namespaceAware) {
/* 245 */       prefix = "";
/* 246 */       namespaceUri = null;
/*     */     } 
/* 249 */     output.startElement(namespaceUri, "coordinates", prefix + "coordinates", this.atts);
/* 252 */     int coordCount = c.size();
/* 254 */     int coordSeqDimension = c.getDimension();
/* 257 */     for (int i = 0, n = coordCount; i < n; i++) {
/* 258 */       double x = c.getOrdinate(i, 0);
/* 259 */       double y = c.getOrdinate(i, 1);
/* 262 */       this.coordBuff.setLength(0);
/* 265 */       formatDecimal(x, this.coordBuff);
/* 266 */       this.coordBuff.append(this.coordinateDelimiter);
/* 268 */       formatDecimal(y, this.coordBuff);
/* 270 */       if (this.D == 3 || this.useDummyZ) {
/* 271 */         double z = (this.D == 3 && coordSeqDimension > 2) ? c.getOrdinate(i, 2) : this.dummyZ;
/* 272 */         this.coordBuff.append(this.coordinateDelimiter);
/* 273 */         formatDecimal(z, this.coordBuff);
/*     */       } 
/* 277 */       if (i + 1 < coordCount)
/* 278 */         this.coordBuff.append(this.tupleDelimiter); 
/* 282 */       if (this.coordBuff.length() > this.buff.length)
/* 283 */         this.buff = new char[this.coordBuff.length()]; 
/* 286 */       this.coordBuff.getChars(0, this.coordBuff.length(), this.buff, 0);
/* 289 */       output.characters(this.buff, 0, this.coordBuff.length());
/*     */     } 
/* 291 */     output.endElement(namespaceUri, "coordinates", prefix + "coordinates");
/*     */   }
/*     */   
/*     */   private void formatDecimal(double x, StringBuffer sb) {
/* 295 */     if (Math.abs(x) >= DECIMAL_MIN && x < DECIMAL_MAX) {
/* 296 */       x = Math.floor(x * this.scale + 0.5D) / this.scale;
/* 297 */       long lx = (long)x;
/* 298 */       if (lx == x) {
/* 299 */         sb.append(lx);
/*     */       } else {
/* 301 */         sb.append(x);
/*     */       } 
/*     */     } else {
/* 303 */       this.coordFormatter.format(x, this.coordBuff, this.zero);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\producer\CoordinateWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */