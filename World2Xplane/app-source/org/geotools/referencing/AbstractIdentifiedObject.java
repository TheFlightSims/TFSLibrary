/*      */ package org.geotools.referencing;
/*      */ 
/*      */ import java.io.ObjectStreamException;
/*      */ import java.io.Serializable;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.LogRecord;
/*      */ import java.util.logging.Logger;
/*      */ import javax.measure.unit.SI;
/*      */ import javax.measure.unit.Unit;
/*      */ import org.geotools.metadata.iso.citation.Citations;
/*      */ import org.geotools.referencing.wkt.Formattable;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.resources.i18n.Loggings;
/*      */ import org.geotools.util.GrowableInternationalString;
/*      */ import org.geotools.util.NameFactory;
/*      */ import org.geotools.util.Utilities;
/*      */ import org.geotools.util.logging.Logging;
/*      */ import org.opengis.metadata.Identifier;
/*      */ import org.opengis.metadata.citation.Citation;
/*      */ import org.opengis.parameter.InvalidParameterValueException;
/*      */ import org.opengis.referencing.IdentifiedObject;
/*      */ import org.opengis.referencing.ReferenceIdentifier;
/*      */ import org.opengis.util.GenericName;
/*      */ import org.opengis.util.InternationalString;
/*      */ 
/*      */ public class AbstractIdentifiedObject extends Formattable implements IdentifiedObject, Serializable {
/*      */   private static final long serialVersionUID = -5173281694258483264L;
/*      */   
/*   98 */   public static final ReferenceIdentifier[] EMPTY_IDENTIFIER_ARRAY = new ReferenceIdentifier[0];
/*      */   
/*  107 */   public static final GenericName[] EMPTY_ALIAS_ARRAY = new GenericName[0];
/*      */   
/*  112 */   public static final Comparator<IdentifiedObject> NAME_COMPARATOR = new NameComparator();
/*      */   
/*      */   private static final class NameComparator implements Comparator<IdentifiedObject>, Serializable {
/*      */     private static final long serialVersionUID = -6605097017814062198L;
/*      */     
/*      */     private NameComparator() {}
/*      */     
/*      */     public int compare(IdentifiedObject o1, IdentifiedObject o2) {
/*  124 */       return AbstractIdentifiedObject.doCompare((E)o1.getName().getCode(), (E)o2.getName().getCode());
/*      */     }
/*      */     
/*      */     protected Object readResolve() throws ObjectStreamException {
/*  129 */       return AbstractIdentifiedObject.NAME_COMPARATOR;
/*      */     }
/*      */   }
/*      */   
/*  136 */   public static final Comparator<IdentifiedObject> IDENTIFIER_COMPARATOR = new IdentifierComparator();
/*      */   
/*      */   private static final class IdentifierComparator implements Comparator<IdentifiedObject>, Serializable {
/*      */     private static final long serialVersionUID = -7315726806679993522L;
/*      */     
/*      */     private IdentifierComparator() {}
/*      */     
/*      */     public int compare(IdentifiedObject o1, IdentifiedObject o2) {
/*  148 */       Collection<ReferenceIdentifier> a1 = o1.getIdentifiers();
/*  149 */       Collection<ReferenceIdentifier> a2 = o2.getIdentifiers();
/*  150 */       if (a1 == null)
/*  150 */         a1 = Collections.emptySet(); 
/*  151 */       if (a2 == null)
/*  151 */         a2 = Collections.emptySet(); 
/*  152 */       Iterator<ReferenceIdentifier> i1 = a1.iterator();
/*  153 */       Iterator<ReferenceIdentifier> i2 = a2.iterator();
/*      */       boolean n1, n2;
/*  155 */       while (((n1 = i1.hasNext()) & (n2 = i2.hasNext())) != 0) {
/*  156 */         int c = AbstractIdentifiedObject.doCompare((E)((ReferenceIdentifier)i1.next()).getCode(), (E)((ReferenceIdentifier)i2.next()).getCode());
/*  157 */         if (c != 0)
/*  158 */           return c; 
/*      */       } 
/*  161 */       if (n1)
/*  161 */         return 1; 
/*  162 */       if (n2)
/*  162 */         return -1; 
/*  163 */       return 0;
/*      */     }
/*      */     
/*      */     protected Object readResolve() throws ObjectStreamException {
/*  168 */       return AbstractIdentifiedObject.IDENTIFIER_COMPARATOR;
/*      */     }
/*      */   }
/*      */   
/*  175 */   public static final Comparator<IdentifiedObject> REMARKS_COMPARATOR = new RemarksComparator();
/*      */   
/*      */   private final ReferenceIdentifier name;
/*      */   
/*      */   private final Collection<GenericName> alias;
/*      */   
/*      */   private final Set<ReferenceIdentifier> identifiers;
/*      */   
/*      */   private final InternationalString remarks;
/*      */   
/*      */   private static final class RemarksComparator implements Comparator<IdentifiedObject>, Serializable {
/*      */     private static final long serialVersionUID = -6675419613224162715L;
/*      */     
/*      */     private RemarksComparator() {}
/*      */     
/*      */     public int compare(IdentifiedObject o1, IdentifiedObject o2) {
/*  187 */       return AbstractIdentifiedObject.doCompare((E)o1.getRemarks(), (E)o2.getRemarks());
/*      */     }
/*      */     
/*      */     protected Object readResolve() throws ObjectStreamException {
/*  192 */       return AbstractIdentifiedObject.REMARKS_COMPARATOR;
/*      */     }
/*      */   }
/*      */   
/*      */   public AbstractIdentifiedObject(IdentifiedObject object) {
/*  227 */     this.name = object.getName();
/*  228 */     this.alias = object.getAlias();
/*  229 */     this.identifiers = object.getIdentifiers();
/*  230 */     this.remarks = object.getRemarks();
/*      */   }
/*      */   
/*      */   public AbstractIdentifiedObject(Map<String, ?> properties) throws IllegalArgumentException {
/*  296 */     this(properties, null, null);
/*      */   }
/*      */   
/*      */   protected AbstractIdentifiedObject(Map<String, ?> properties, Map<String, Object> subProperties, String[] localizables) throws IllegalArgumentException {
/*  324 */     ensureNonNull("properties", properties);
/*  325 */     Object name = null;
/*  326 */     Object alias = null;
/*  327 */     Object identifiers = null;
/*  328 */     Object remarks = null;
/*  329 */     GrowableInternationalString growable = null;
/*  330 */     GrowableInternationalString[] subGrowables = null;
/*  340 */     label114: for (Map.Entry<String, ?> entry : properties.entrySet()) {
/*  341 */       String str = ((String)entry.getKey()).trim().toLowerCase();
/*  342 */       Object object = entry.getValue();
/*  347 */       switch (str.hashCode()) {
/*      */         case -1528693765:
/*  350 */           if (str.equalsIgnoreCase("anchorPoint"))
/*  350 */             str = "anchorPoint"; 
/*      */           break;
/*      */         case -1805658881:
/*  351 */           if (str.equalsIgnoreCase("bursaWolf"))
/*  351 */             str = "bursaWolf"; 
/*      */           break;
/*      */         case 109688209:
/*  352 */           if (str.equalsIgnoreCase("operationVersion"))
/*  352 */             str = "operationVersion"; 
/*      */           break;
/*      */         case 1479434472:
/*  353 */           if (str.equalsIgnoreCase("coordinateOperationAccuracy"))
/*  353 */             str = "coordinateOperationAccuracy"; 
/*      */           break;
/*      */         case 1126917133:
/*  354 */           if (str.equalsIgnoreCase("positionalAccuracy"))
/*  354 */             str = "positionalAccuracy"; 
/*      */           break;
/*      */         case 1127093059:
/*  355 */           if (str.equalsIgnoreCase("realizationEpoch"))
/*  355 */             str = "realizationEpoch"; 
/*      */           break;
/*      */         case 1790520781:
/*  356 */           if (str.equalsIgnoreCase("domainOfValidity"))
/*  356 */             str = "domainOfValidity"; 
/*      */           break;
/*      */         case -1109785975:
/*  357 */           if (str.equalsIgnoreCase("validArea"))
/*  357 */             str = "validArea"; 
/*      */           break;
/*      */         case 3373707:
/*  363 */           if (str.equals("name")) {
/*  364 */             if (object instanceof String) {
/*  365 */               name = new NamedIdentifier(properties, false);
/*  366 */               assert object.equals(((Identifier)name).getCode()) : name;
/*      */               continue;
/*      */             } 
/*  371 */             name = object;
/*      */             continue;
/*      */           } 
/*      */           break;
/*      */         case 92902992:
/*  381 */           if (str.equals("alias")) {
/*  382 */             alias = NameFactory.toArray(object);
/*      */             continue;
/*      */           } 
/*      */           break;
/*      */         case 1368189162:
/*  391 */           if (str.equals("identifiers")) {
/*  392 */             if (object != null) {
/*  393 */               if (object instanceof ReferenceIdentifier) {
/*  394 */                 identifiers = new ReferenceIdentifier[] { (ReferenceIdentifier)object };
/*      */                 continue;
/*      */               } 
/*  398 */               identifiers = object;
/*      */             } 
/*      */             continue;
/*      */           } 
/*      */           break;
/*      */         case 1091415283:
/*  409 */           if (str.equals("remarks") && 
/*  410 */             object instanceof InternationalString) {
/*  411 */             remarks = object;
/*      */             continue;
/*      */           } 
/*      */           break;
/*      */       } 
/*  423 */       if (object instanceof String) {
/*  424 */         if (growable == null)
/*  425 */           if (remarks instanceof GrowableInternationalString) {
/*  426 */             growable = (GrowableInternationalString)remarks;
/*      */           } else {
/*  428 */             growable = new GrowableInternationalString();
/*      */           }  
/*  431 */         if (growable.add("remarks", str, object.toString()))
/*      */           continue; 
/*      */       } 
/*  438 */       if (subProperties == null)
/*      */         continue; 
/*  441 */       if (localizables != null)
/*  442 */         for (int i = 0; i < localizables.length; i++) {
/*  443 */           String prefix = localizables[i];
/*  444 */           if (str.equals(prefix) && 
/*  445 */             object instanceof InternationalString)
/*      */             break; 
/*  450 */           if (object instanceof String) {
/*  451 */             if (subGrowables == null)
/*  452 */               subGrowables = new GrowableInternationalString[localizables.length]; 
/*  454 */             if (subGrowables[i] == null) {
/*  455 */               Object previous = subProperties.get(prefix);
/*  456 */               if (previous instanceof GrowableInternationalString) {
/*  457 */                 subGrowables[i] = (GrowableInternationalString)previous;
/*      */               } else {
/*  459 */                 subGrowables[i] = new GrowableInternationalString();
/*      */               } 
/*      */             } 
/*  462 */             if (subGrowables[i].add(prefix, str, object.toString()))
/*      */               continue label114; 
/*      */           } 
/*      */         }  
/*  468 */       subProperties.put(str, object);
/*      */     } 
/*  475 */     if (growable != null && !growable.getLocales().isEmpty())
/*  476 */       if (remarks == null) {
/*  477 */         remarks = growable;
/*  478 */       } else if (!growable.isSubsetOf(remarks)) {
/*  479 */         Logger logger = Logging.getLogger(AbstractIdentifiedObject.class);
/*  480 */         LogRecord record = Loggings.format(Level.WARNING, 28);
/*  481 */         record.setLoggerName(logger.getName());
/*  482 */         logger.log(record);
/*      */       }  
/*  488 */     if (subProperties != null && subGrowables != null)
/*  489 */       for (int i = 0; i < subGrowables.length; i++) {
/*  490 */         growable = subGrowables[i];
/*  491 */         if (growable != null && !growable.getLocales().isEmpty()) {
/*  492 */           String prefix = localizables[i];
/*  493 */           Object current = subProperties.get(prefix);
/*  494 */           if (current == null) {
/*  495 */             subProperties.put(prefix, growable);
/*  496 */           } else if (!growable.isSubsetOf(current)) {
/*  497 */             Logger logger = Logging.getLogger(AbstractIdentifiedObject.class);
/*  498 */             LogRecord record = Loggings.format(Level.WARNING, 28);
/*  499 */             record.setLoggerName(logger.getName());
/*  500 */             logger.log(record);
/*      */           } 
/*      */         } 
/*      */       }  
/*  510 */     String key = null;
/*  510 */     Object value = null;
/*      */     try {
/*  512 */       key = "name";
/*  513 */       this.name = (ReferenceIdentifier)(value = name);
/*  515 */       key = "alias";
/*  516 */       this.alias = asSet((GenericName[])(value = alias));
/*  518 */       key = "identifiers";
/*  519 */       this.identifiers = asSet((ReferenceIdentifier[])(value = identifiers));
/*  521 */       key = "remarks";
/*  522 */       this.remarks = (InternationalString)(value = remarks);
/*  523 */     } catch (ClassCastException exception) {
/*  524 */       InvalidParameterValueException e = new InvalidParameterValueException(Errors.format(58, key, value), key, value);
/*  526 */       e.initCause(exception);
/*  527 */       throw e;
/*      */     } 
/*  529 */     ensureNonNull("name", name);
/*  530 */     ensureNonNull("name", name.toString());
/*      */   }
/*      */   
/*      */   public ReferenceIdentifier getName() {
/*  539 */     return this.name;
/*      */   }
/*      */   
/*      */   public Collection<GenericName> getAlias() {
/*  550 */     if (this.alias == null)
/*  551 */       return Collections.emptySet(); 
/*  553 */     return this.alias;
/*      */   }
/*      */   
/*      */   public Set<ReferenceIdentifier> getIdentifiers() {
/*  565 */     if (this.identifiers == null)
/*  566 */       return Collections.emptySet(); 
/*  568 */     return this.identifiers;
/*      */   }
/*      */   
/*      */   public InternationalString getRemarks() {
/*  575 */     return this.remarks;
/*      */   }
/*      */   
/*      */   public static Map<String, ?> getProperties(IdentifiedObject info) {
/*  587 */     return new Properties(info);
/*      */   }
/*      */   
/*      */   public static Map<String, Object> getProperties(IdentifiedObject info, Citation authority) {
/*  612 */     Map<String, Object> properties = new HashMap<String, Object>(getProperties(info));
/*  613 */     properties.put("name", new NamedIdentifier(authority, info.getName().getCode()));
/*  614 */     properties.remove("identifiers");
/*  615 */     return properties;
/*      */   }
/*      */   
/*      */   public ReferenceIdentifier getIdentifier(Citation authority) {
/*  633 */     return getIdentifier0(this, authority);
/*      */   }
/*      */   
/*      */   public static ReferenceIdentifier getIdentifier(IdentifiedObject info, Citation authority) {
/*  649 */     if (info instanceof AbstractIdentifiedObject)
/*  651 */       return ((AbstractIdentifiedObject)info).getIdentifier(authority); 
/*  653 */     return getIdentifier0(info, authority);
/*      */   }
/*      */   
/*      */   private static ReferenceIdentifier getIdentifier0(IdentifiedObject info, Citation authority) {
/*  660 */     if (info == null)
/*  661 */       return null; 
/*  663 */     for (ReferenceIdentifier referenceIdentifier : info.getIdentifiers()) {
/*  664 */       if (referenceIdentifier instanceof ReferenceIdentifier) {
/*  665 */         ReferenceIdentifier identifier = referenceIdentifier;
/*  666 */         if (authority == null)
/*  667 */           return identifier; 
/*  669 */         Citation infoAuthority = identifier.getAuthority();
/*  670 */         if (infoAuthority != null && 
/*  671 */           Citations.identifierMatches(authority, infoAuthority))
/*  672 */           return identifier; 
/*      */       } 
/*      */     } 
/*  677 */     return (authority == null) ? info.getName() : null;
/*      */   }
/*      */   
/*      */   public String getName(Citation authority) {
/*  715 */     return getName0(this, authority);
/*      */   }
/*      */   
/*      */   public static String getName(IdentifiedObject info, Citation authority) {
/*  731 */     if (info instanceof AbstractIdentifiedObject)
/*  733 */       return ((AbstractIdentifiedObject)info).getName(authority); 
/*  735 */     return getName0(info, authority);
/*      */   }
/*      */   
/*      */   private static String getName0(IdentifiedObject info, Citation authority) {
/*  742 */     ReferenceIdentifier referenceIdentifier = info.getName();
/*  743 */     if (authority == null)
/*  744 */       return referenceIdentifier.getCode(); 
/*  746 */     String name = null;
/*  747 */     Citation infoAuthority = referenceIdentifier.getAuthority();
/*  748 */     if (infoAuthority != null)
/*  749 */       if (Citations.identifierMatches(authority, infoAuthority)) {
/*  750 */         name = referenceIdentifier.getCode();
/*      */       } else {
/*  752 */         for (GenericName alias : info.getAlias()) {
/*  753 */           if (alias instanceof Identifier) {
/*  754 */             Identifier identifier = (Identifier)alias;
/*  755 */             infoAuthority = identifier.getAuthority();
/*  756 */             if (infoAuthority != null && 
/*  757 */               Citations.identifierMatches(authority, infoAuthority)) {
/*  758 */               name = identifier.getCode();
/*      */               break;
/*      */             } 
/*      */             continue;
/*      */           } 
/*  763 */           GenericName scope = alias.scope().name();
/*  764 */           if (scope != null && 
/*  765 */             Citations.identifierMatches(authority, scope.toString())) {
/*  766 */             name = alias.tip().toString();
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }  
/*  774 */     return name;
/*      */   }
/*      */   
/*      */   public boolean nameMatches(String name) {
/*  792 */     return nameMatches(this, this.alias, name);
/*      */   }
/*      */   
/*      */   public static boolean nameMatches(IdentifiedObject object, String name) {
/*  807 */     if (object instanceof AbstractIdentifiedObject)
/*  808 */       return ((AbstractIdentifiedObject)object).nameMatches(name); 
/*  810 */     return nameMatches(object, object.getAlias(), name);
/*      */   }
/*      */   
/*      */   public static boolean nameMatches(IdentifiedObject o1, IdentifiedObject o2) {
/*  825 */     return (nameMatches(o1, o2.getName().getCode()) || nameMatches(o2, o1.getName().getCode()));
/*      */   }
/*      */   
/*      */   private static boolean nameMatches(IdentifiedObject object, Collection<GenericName> alias, String name) {
/*  843 */     name = name.trim();
/*  844 */     if (name.equalsIgnoreCase(object.getName().getCode().trim()))
/*  845 */       return true; 
/*  847 */     if (alias != null)
/*  848 */       for (GenericName asName : alias) {
/*  849 */         GenericName asScoped = asName.toFullyQualifiedName();
/*  850 */         if (asScoped != asName && name.equalsIgnoreCase(asScoped.toString().trim()))
/*  851 */           return true; 
/*  853 */         if (name.equalsIgnoreCase(asName.tip().toString().trim()))
/*  854 */           return true; 
/*      */       }  
/*  858 */     return false;
/*      */   }
/*      */   
/*      */   public final boolean equals(Object object) {
/*  869 */     return (object instanceof AbstractIdentifiedObject && equals((AbstractIdentifiedObject)object, true));
/*      */   }
/*      */   
/*      */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/*  899 */     if (object != null && object.getClass().equals(getClass())) {
/*  900 */       if (!compareMetadata)
/*  901 */         return true; 
/*  903 */       return (Utilities.equals(this.name, object.name) && Utilities.equals(this.alias, object.alias) && Utilities.equals(this.identifiers, object.identifiers) && Utilities.equals(this.remarks, object.remarks));
/*      */     } 
/*  908 */     return false;
/*      */   }
/*      */   
/*      */   static boolean equals(AbstractIdentifiedObject object1, AbstractIdentifiedObject object2, boolean compareMetadata) {
/*  927 */     return (object1 == object2 || (object1 != null && object1.equals(object2, compareMetadata)));
/*      */   }
/*      */   
/*      */   protected static boolean equals(IdentifiedObject object1, IdentifiedObject object2, boolean compareMetadata) {
/*  944 */     if (!(object1 instanceof AbstractIdentifiedObject))
/*  944 */       return Utilities.equals(object1, object2); 
/*  945 */     if (!(object2 instanceof AbstractIdentifiedObject))
/*  945 */       return Utilities.equals(object2, object1); 
/*  946 */     return equals((AbstractIdentifiedObject)object1, (AbstractIdentifiedObject)object2, compareMetadata);
/*      */   }
/*      */   
/*      */   protected static boolean equals(IdentifiedObject[] array1, IdentifiedObject[] array2, boolean compareMetadata) {
/*  964 */     if (array1 != array2) {
/*  965 */       if (array1 == null || array2 == null || array1.length != array2.length)
/*  966 */         return false; 
/*  968 */       for (int i = array1.length; --i >= 0;) {
/*  969 */         if (!equals(array1[i], array2[i], compareMetadata))
/*  970 */           return false; 
/*      */       } 
/*      */     } 
/*  974 */     return true;
/*      */   }
/*      */   
/*      */   protected static boolean equals(Collection<? extends IdentifiedObject> collection1, Collection<? extends IdentifiedObject> collection2, boolean compareMetadata) {
/*  993 */     if (collection1 == collection2)
/*  994 */       return true; 
/*  996 */     if (collection1 == null || collection2 == null)
/*  997 */       return false; 
/*  999 */     Iterator<? extends IdentifiedObject> it1 = collection1.iterator();
/* 1000 */     Iterator<? extends IdentifiedObject> it2 = collection2.iterator();
/* 1001 */     while (it1.hasNext()) {
/* 1002 */       if (!it2.hasNext() || !equals(it1.next(), it2.next(), compareMetadata))
/* 1003 */         return false; 
/*      */     } 
/* 1006 */     return !it2.hasNext();
/*      */   }
/*      */   
/*      */   private static <E extends Comparable<E>> int doCompare(E c1, E c2) {
/* 1014 */     if (c1 == null)
/* 1015 */       return (c2 == null) ? 0 : -1; 
/* 1017 */     if (c2 == null)
/* 1018 */       return 1; 
/* 1020 */     return c1.compareTo(c2);
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1037 */     return 0x52B12FC0 ^ getClass().hashCode();
/*      */   }
/*      */   
/*      */   protected static <E> Set<E> asSet(E[] array) {
/* 1050 */     if (array == null)
/* 1051 */       return null; 
/* 1053 */     switch (array.length) {
/*      */       case 0:
/* 1054 */         return null;
/*      */       case 1:
/* 1055 */         return Collections.singleton(array[0]);
/*      */     } 
/* 1056 */     return Collections.unmodifiableSet(new LinkedHashSet<E>(Arrays.asList(array)));
/*      */   }
/*      */   
/*      */   protected static void ensureNonNull(String name, Object object) throws InvalidParameterValueException {
/* 1071 */     if (object == null)
/* 1072 */       throw new InvalidParameterValueException(Errors.format(143, name), name, object); 
/*      */   }
/*      */   
/*      */   protected static void ensureNonNull(String name, Object[] array, int index) throws InvalidParameterValueException {
/* 1089 */     if (array[index] == null)
/* 1090 */       throw new InvalidParameterValueException(Errors.format(143, name + '[' + index + ']'), name, array); 
/*      */   }
/*      */   
/*      */   protected static void ensureTimeUnit(Unit<?> unit) throws IllegalArgumentException {
/* 1103 */     if (!SI.SECOND.isCompatible(unit))
/* 1104 */       throw new IllegalArgumentException(Errors.format(117, unit)); 
/*      */   }
/*      */   
/*      */   protected static void ensureLinearUnit(Unit<?> unit) throws IllegalArgumentException {
/* 1116 */     if (!SI.METER.isCompatible(unit))
/* 1117 */       throw new IllegalArgumentException(Errors.format(113, unit)); 
/*      */   }
/*      */   
/*      */   protected static void ensureAngularUnit(Unit<?> unit) throws IllegalArgumentException {
/* 1129 */     if (!SI.RADIAN.isCompatible(unit) && !Unit.ONE.equals(unit))
/* 1130 */       throw new IllegalArgumentException(Errors.format(107, unit)); 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\AbstractIdentifiedObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */