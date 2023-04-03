/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.referencing.operation.DefaultMathTransformFactory;
/*     */ import org.geotools.referencing.operation.DefaultOperation;
/*     */ import org.geotools.referencing.operation.DefaultOperationMethod;
/*     */ import org.geotools.referencing.operation.transform.AbstractMathTransform;
/*     */ import org.geotools.referencing.operation.transform.AffineTransform2D;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class PropertyCoordinateOperationAuthorityFactory extends DirectAuthorityFactory implements CoordinateOperationAuthorityFactory {
/*     */   private final Citation authority;
/*     */   
/* 104 */   private final Properties definitions = new Properties();
/*     */   
/* 110 */   private final Set<String> codes = Collections.unmodifiableSet(this.definitions.keySet());
/*     */   
/*     */   public PropertyCoordinateOperationAuthorityFactory(ReferencingFactoryContainer factories, Citation authority, URL definitions) throws IOException {
/* 129 */     super(factories, 11);
/* 132 */     this.authority = authority;
/* 133 */     ensureNonNull("authority", authority);
/* 136 */     InputStream in = definitions.openStream();
/* 137 */     this.definitions.load(in);
/* 138 */     in.close();
/*     */   }
/*     */   
/*     */   public CoordinateOperation createCoordinateOperation(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 152 */     String[] crsPair = trimAuthority(code).split(",");
/* 153 */     if (crsPair.length == 2) {
/* 154 */       Set<CoordinateOperation> coordopset = createFromCoordinateReferenceSystemCodes(trimAuthority(crsPair[0]), trimAuthority(crsPair[1]));
/* 156 */       if (!coordopset.isEmpty())
/* 157 */         return coordopset.iterator().next(); 
/*     */     } 
/* 160 */     return null;
/*     */   }
/*     */   
/*     */   public Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String sourceCRS, String targetCRS) throws NoSuchAuthorityCodeException, FactoryException {
/* 181 */     Set<CoordinateOperation> coordops = new HashSet<CoordinateOperation>(1);
/* 183 */     CoordinateOperation coordop = createFromCoordinateReferenceSystemCodes(sourceCRS, targetCRS, false);
/* 185 */     if (coordop == null)
/* 187 */       coordop = createFromCoordinateReferenceSystemCodes(targetCRS, sourceCRS, true); 
/* 189 */     if (coordop != null)
/* 191 */       coordops.add(coordop); 
/* 193 */     return coordops;
/*     */   }
/*     */   
/*     */   CoordinateOperation createFromCoordinateReferenceSystemCodes(String sourceCRS, String targetCRS, boolean inverse) throws NoSuchAuthorityCodeException, FactoryException {
/*     */     String str1;
/* 217 */     sourceCRS = trimAuthority(sourceCRS);
/* 218 */     targetCRS = trimAuthority(targetCRS);
/* 219 */     String id = sourceCRS + "," + targetCRS;
/* 220 */     String WKT = this.definitions.getProperty(id);
/* 221 */     if (WKT == null)
/* 223 */       return null; 
/* 227 */     MathTransform mt = null;
/*     */     try {
/* 229 */       mt = this.factories.getMathTransformFactory().createFromWKT(WKT);
/* 230 */     } catch (FactoryException e) {
/* 232 */       LOGGER.warning("Error creating transformation: " + WKT);
/* 233 */       return null;
/*     */     } 
/* 237 */     String s = ((Identifier)this.authority.getIdentifiers().iterator().next()).getCode();
/* 238 */     CoordinateReferenceSystem source = CRS.decode(s + ":" + sourceCRS);
/* 239 */     CoordinateReferenceSystem target = CRS.decode(s + ":" + targetCRS);
/* 244 */     DefaultMathTransformFactory mtf = (DefaultMathTransformFactory)this.factories.getMathTransformFactory();
/* 246 */     MathTransform mt2 = mtf.createBaseToDerived(source, mt, target.getCoordinateSystem());
/*     */     try {
/* 251 */       if (mt instanceof AbstractMathTransform) {
/* 252 */         str1 = ((AbstractMathTransform)mt).getParameterValues().getDescriptor().getName().getCode();
/* 253 */       } else if (mt instanceof AffineTransform2D) {
/* 254 */         str1 = ((AffineTransform2D)mt).getParameterValues().getDescriptor().getName().getCode();
/*     */       } else {
/* 256 */         str1 = mt.getClass().getSimpleName();
/*     */       } 
/* 258 */     } catch (NullPointerException e) {
/* 259 */       str1 = mt.getClass().getSimpleName();
/*     */     } 
/* 261 */     Map<String, String> props = new HashMap<String, String>();
/* 262 */     props.put("name", str1);
/* 265 */     DefaultOperationMethod defaultOperationMethod = new DefaultOperationMethod(props, mt2.getSourceDimensions(), mt2.getTargetDimensions(), null);
/* 269 */     CoordinateOperation coordop = null;
/* 270 */     if (!inverse) {
/* 271 */       props.put("name", sourceCRS + " ⇨ " + targetCRS);
/* 272 */       coordop = DefaultOperation.create(props, source, target, mt2, (OperationMethod)defaultOperationMethod, CoordinateOperation.class);
/*     */     } else {
/*     */       try {
/* 276 */         props.put("name", targetCRS + " ⇨ " + sourceCRS);
/* 277 */         coordop = DefaultOperation.create(props, target, source, mt2.inverse(), (OperationMethod)defaultOperationMethod, CoordinateOperation.class);
/* 279 */       } catch (NoninvertibleTransformException e) {
/* 280 */         return null;
/*     */       } 
/*     */     } 
/* 283 */     return coordop;
/*     */   }
/*     */   
/*     */   public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) {
/* 297 */     if (type == null || type.isAssignableFrom(CoordinateOperation.class))
/* 298 */       return this.codes; 
/* 300 */     return Collections.emptySet();
/*     */   }
/*     */   
/*     */   public InternationalString getDescriptionText(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 317 */     String wkt = this.definitions.getProperty(trimAuthority(code));
/* 319 */     if (wkt == null)
/* 320 */       throw noSuchAuthorityCode(IdentifiedObject.class, code); 
/* 324 */     int start = wkt.indexOf('"');
/* 325 */     if (start >= 0) {
/* 326 */       int end = wkt.indexOf('"', ++start);
/* 327 */       if (end >= 0)
/* 328 */         return (InternationalString)new SimpleInternationalString(wkt.substring(start, end).trim()); 
/*     */     } 
/* 331 */     return null;
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 340 */     return this.authority;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\PropertyCoordinateOperationAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */