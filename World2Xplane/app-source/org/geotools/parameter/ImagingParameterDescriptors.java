/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.media.jai.EnumeratedParameter;
/*     */ import javax.media.jai.OperationDescriptor;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.RegistryElementDescriptor;
/*     */ import javax.media.jai.util.Range;
/*     */ import org.geotools.metadata.iso.citation.CitationImpl;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.metadata.iso.citation.ContactImpl;
/*     */ import org.geotools.metadata.iso.citation.OnLineResourceImpl;
/*     */ import org.geotools.metadata.iso.citation.ResponsiblePartyImpl;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.NameFactory;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.coverage.grid.GridCoverage;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.citation.Contact;
/*     */ import org.opengis.metadata.citation.OnLineFunction;
/*     */ import org.opengis.metadata.citation.OnLineResource;
/*     */ import org.opengis.metadata.citation.ResponsibleParty;
/*     */ import org.opengis.metadata.citation.Role;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterNameException;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class ImagingParameterDescriptors extends DefaultParameterDescriptorGroup {
/*     */   private static final long serialVersionUID = 2127050865911951239L;
/*     */   
/*  89 */   private static final Object[] AUTHORITIES = new Object[] { "com.sun.media.jai", Citations.JAI, "org.geotools", Citations.GEOTOOLS, "org.jaitools.media.jai", Citations.JAI };
/*     */   
/* 102 */   public static final Map<Class<?>, Class<?>> DEFAULT_SOURCE_TYPE_MAP = Collections.singletonMap(RenderedImage.class, GridCoverage.class);
/*     */   
/*     */   protected final String registryMode;
/*     */   
/*     */   protected final RegistryElementDescriptor operation;
/*     */   
/*     */   protected final ParameterListDescriptor descriptor;
/*     */   
/*     */   public ImagingParameterDescriptors(RegistryElementDescriptor operation) {
/* 137 */     this(properties(operation), operation, DEFAULT_SOURCE_TYPE_MAP, "rendered");
/*     */   }
/*     */   
/*     */   public ImagingParameterDescriptors(RegistryElementDescriptor operation, Collection<ParameterDescriptor> extension) {
/* 162 */     this(properties(operation), operation, "rendered", DEFAULT_SOURCE_TYPE_MAP, extension);
/*     */   }
/*     */   
/*     */   public ImagingParameterDescriptors(Map<String, ?> properties, RegistryElementDescriptor operation, Map<Class<?>, Class<?>> sourceTypeMap, String registryMode) {
/* 188 */     this(properties, operation.getParameterListDescriptor(registryMode), operation, registryMode, sourceTypeMap, null);
/*     */   }
/*     */   
/*     */   public ImagingParameterDescriptors(Map<String, ?> properties, RegistryElementDescriptor operation, String registryMode, Map<Class<?>, Class<?>> sourceTypeMap, Collection<ParameterDescriptor> extension) {
/* 218 */     this(properties, operation.getParameterListDescriptor(registryMode), operation, registryMode, sourceTypeMap, extension);
/*     */   }
/*     */   
/*     */   public ImagingParameterDescriptors(Map<String, ?> properties, ParameterListDescriptor descriptor) {
/* 234 */     this(properties, descriptor, null, null, null, null);
/*     */   }
/*     */   
/*     */   private ImagingParameterDescriptors(Map<String, ?> properties, ParameterListDescriptor descriptor, RegistryElementDescriptor operation, String registryMode, Map<Class<?>, Class<?>> sourceTypeMap, Collection<ParameterDescriptor> extension) {
/* 249 */     super(properties, 1, 1, (GeneralParameterDescriptor[])asDescriptors(descriptor, operation, registryMode, sourceTypeMap, extension));
/* 250 */     this.descriptor = descriptor;
/* 251 */     this.operation = operation;
/* 252 */     this.registryMode = registryMode;
/*     */   }
/*     */   
/*     */   public static Map<String, Object> properties(RegistryElementDescriptor operation) {
/* 298 */     String name = operation.getName();
/* 299 */     Map<String, Object> properties = new HashMap<String, Object>();
/* 300 */     if (operation instanceof OperationDescriptor) {
/* 308 */       OperationDescriptor op = (OperationDescriptor)operation;
/* 309 */       ResourceBundle bundle = op.getResourceBundle(Locale.getDefault());
/* 310 */       String vendor = op.getResourceBundle(Locale.US).getString("Vendor");
/* 311 */       Citation authority = null;
/* 312 */       if (vendor != null) {
/* 313 */         vendor = vendor.trim();
/* 314 */         name = ImagingParameterDescription.trimPrefix(name, vendor);
/* 315 */         for (int i = 0; i < AUTHORITIES.length; i += 2) {
/* 316 */           if (vendor.equalsIgnoreCase((String)AUTHORITIES[i])) {
/* 317 */             authority = (Citation)AUTHORITIES[i + 1];
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 322 */       if (authority == null)
/* 327 */         throw new IllegalArgumentException(Errors.format(137, "AUTHORITIES", vendor)); 
/* 340 */       ImagingParameterDescription imagingParameterDescription = new ImagingParameterDescription(op, "Description", null);
/*     */       try {
/*     */         ResponsibleParty oldParty;
/* 342 */         URI uri = new URI(bundle.getString("DocURL"));
/* 343 */         OnLineResourceImpl resource = new OnLineResourceImpl(uri);
/* 344 */         resource.setFunction(OnLineFunction.INFORMATION);
/* 345 */         resource.setDescription((InternationalString)imagingParameterDescription);
/* 346 */         CitationImpl citation = new CitationImpl(authority);
/* 347 */         Collection<ResponsibleParty> parties = citation.getCitedResponsibleParties();
/* 350 */         Iterator<ResponsibleParty> it = parties.iterator();
/* 351 */         if (it.hasNext()) {
/* 352 */           oldParty = it.next();
/* 353 */           it.remove();
/*     */         } else {
/* 356 */           oldParty = null;
/*     */         } 
/* 359 */         ResponsiblePartyImpl party = new ResponsiblePartyImpl(oldParty);
/* 360 */         party.setRole(Role.RESOURCE_PROVIDER);
/* 361 */         party.setContactInfo((Contact)new ContactImpl((OnLineResource)resource));
/* 362 */         parties.add(party);
/* 363 */         authority = (Citation)citation.unmodifiable();
/* 364 */       } catch (URISyntaxException exception) {}
/* 375 */       GenericName alias = NameFactory.create((CharSequence[])new InternationalString[] { (InternationalString)new ImagingParameterDescription(op, "Vendor", null), (InternationalString)new ImagingParameterDescription(op, "LocalName", "Vendor") }'.');
/* 379 */       properties.put("alias", alias);
/* 380 */       properties.put("remarks", imagingParameterDescription);
/* 381 */       properties.put("version", bundle.getString("Version"));
/* 382 */       properties.put("authority", authority);
/*     */     } 
/* 384 */     properties.put("name", name);
/* 385 */     return properties;
/*     */   }
/*     */   
/*     */   private static ParameterDescriptor[] asDescriptors(ParameterListDescriptor descriptor, RegistryElementDescriptor operation, String registryMode, Map<Class<?>, Class<?>> sourceTypeMap, Collection<ParameterDescriptor> extension) {
/*     */     int numSources;
/* 403 */     ensureNonNull("descriptor", descriptor);
/* 404 */     Map<String, ParameterDescriptor> replacements = new LinkedHashMap<String, ParameterDescriptor>();
/* 406 */     if (extension != null)
/* 407 */       for (ParameterDescriptor d : extension) {
/* 408 */         String name = d.getName().getCode().trim().toLowerCase();
/* 409 */         if (replacements.put(name, d) != null)
/* 410 */           throw new InvalidParameterNameException(Errors.format(44, name), name); 
/*     */       }  
/* 422 */     int numParameters = descriptor.getNumParameters();
/* 423 */     Map<String, CharSequence> properties = new HashMap<String, CharSequence>();
/* 425 */     if (operation instanceof OperationDescriptor) {
/* 426 */       OperationDescriptor op = (OperationDescriptor)operation;
/* 427 */       String[] arrayOfString = op.getSourceNames();
/* 428 */       Class<?>[] types = op.getSourceClasses(registryMode);
/* 429 */       numSources = op.getNumSources();
/* 430 */       desc = new ParameterDescriptor[numParameters + numSources];
/* 431 */       for (int j = 0; j < numSources; j++) {
/* 432 */         Class<?> type = sourceTypeMap.get(types[j]);
/* 433 */         if (type == null)
/* 434 */           type = types[j]; 
/* 436 */         String name = arrayOfString[j];
/* 437 */         properties.clear();
/* 438 */         if (numSources == 1) {
/* 444 */           int length = name.length();
/* 445 */           if (length != 0) {
/* 446 */             char c = name.charAt(length - 1);
/* 447 */             if (c == '0' || c == '1') {
/* 448 */               properties.put("alias", name);
/* 449 */               name = name.substring(0, length - 1);
/*     */             } 
/*     */           } 
/*     */         } 
/* 453 */         properties.put("name", name);
/* 454 */         desc[j] = new DefaultParameterDescriptor(properties, type, null, null, null, null, null, true);
/*     */       } 
/*     */     } else {
/* 463 */       numSources = 0;
/* 464 */       desc = new ParameterDescriptor[numParameters];
/*     */     } 
/* 470 */     String[] names = descriptor.getParamNames();
/* 471 */     Class<?>[] classes = descriptor.getParamClasses();
/* 472 */     Object[] defaults = descriptor.getParamDefaults();
/*     */     int i;
/* 473 */     for (i = 0; i < numParameters; i++) {
/* 474 */       String name = names[i];
/* 475 */       ParameterDescriptor replacement = replacements.remove(name.trim().toLowerCase());
/* 476 */       if (replacement != null) {
/* 477 */         desc[i + numSources] = replacement;
/*     */       } else {
/*     */         Comparable<?> min, max;
/*     */         EnumeratedParameter[] validValues;
/* 480 */         Class<?> type = classes[i];
/* 481 */         Range range = descriptor.getParamValueRange(name);
/* 483 */         if (range != null) {
/* 484 */           min = range.getMinValue();
/* 485 */           max = range.getMaxValue();
/*     */         } else {
/* 487 */           min = null;
/* 488 */           max = null;
/*     */         } 
/* 491 */         if (EnumeratedParameter.class.isAssignableFrom(type)) {
/*     */           try {
/* 492 */             validValues = descriptor.getEnumeratedParameterValues(name);
/* 493 */           } catch (UnsupportedOperationException exception) {
/* 494 */             validValues = null;
/*     */           } 
/*     */         } else {
/* 496 */           validValues = null;
/*     */         } 
/* 498 */         Object defaultValue = defaults[i];
/* 499 */         if (defaultValue == ParameterListDescriptor.NO_PARAMETER_DEFAULT)
/* 500 */           defaultValue = null; 
/* 502 */         properties.clear();
/* 503 */         properties.put("name", name);
/* 504 */         if (operation instanceof OperationDescriptor) {
/* 505 */           ImagingParameterDescription remark = new ImagingParameterDescription((OperationDescriptor)operation, i);
/* 507 */           if (remark.exists())
/* 508 */             properties.put("remarks", remark); 
/*     */         } 
/* 511 */         desc[i + numSources] = new DefaultParameterDescriptor(properties, type, (Object[])validValues, defaultValue, min, max, null, true);
/*     */       } 
/*     */     } 
/* 518 */     i = desc.length;
/* 519 */     ParameterDescriptor[] desc = (ParameterDescriptor[])XArray.resize((Object[])desc, i + replacements.size());
/* 520 */     for (ParameterDescriptor d : replacements.values())
/* 521 */       desc[i++] = d; 
/* 523 */     return desc;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup createValue() {
/* 535 */     return new ImagingParameters(this);
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 548 */     if (object == this)
/* 550 */       return true; 
/* 552 */     if (super.equals(object, compareMetadata)) {
/* 553 */       ImagingParameterDescriptors that = (ImagingParameterDescriptors)object;
/* 554 */       return (Utilities.equals(this.operation, that.operation) && Utilities.equals(this.descriptor, that.descriptor));
/*     */     } 
/* 557 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 566 */     return super.hashCode() ^ this.descriptor.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\ImagingParameterDescriptors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */