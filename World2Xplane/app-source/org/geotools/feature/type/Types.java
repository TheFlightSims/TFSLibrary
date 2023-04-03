/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import javax.xml.namespace.QName;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.feature.Attribute;
/*     */ import org.opengis.feature.ComplexAttribute;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.ComplexType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.xml.sax.helpers.NamespaceSupport;
/*     */ 
/*     */ public class Types {
/*     */   public static boolean isValid(Attribute attribute) {
/*     */     try {
/*  74 */       validate(attribute.getType(), attribute, attribute.getValue(), false);
/*  75 */       return true;
/*  77 */     } catch (IllegalAttributeException invalid) {
/*  78 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void validate(Attribute attribute, Object attributeContent) throws IllegalAttributeException {
/*  96 */     validate(attribute.getType(), attribute, attributeContent, false);
/*     */   }
/*     */   
/*     */   public static void validate(AttributeType type, Attribute attribute, Object attributeContent) throws IllegalAttributeException {
/* 108 */     validate(type, attribute, attributeContent, false);
/*     */   }
/*     */   
/*     */   protected static void validate(AttributeType type, Attribute attribute, Object attributeContent, boolean isSuper) throws IllegalAttributeException {
/* 122 */     if (type == null)
/* 123 */       throw new IllegalAttributeException("null type"); 
/* 126 */     if (attributeContent == null) {
/* 127 */       if (!attribute.isNillable())
/* 128 */         throw new IllegalAttributeException(type.getName() + " not nillable"); 
/*     */       return;
/*     */     } 
/* 133 */     if (type.isIdentified() && attribute.getIdentifier() == null)
/* 134 */       throw new NullPointerException(type.getName() + " is identified, null id not accepted"); 
/* 137 */     if (!isSuper) {
/* 145 */       Class<?> clazz = attributeContent.getClass();
/* 146 */       Class<?> binding = type.getBinding();
/* 147 */       if (binding != null && binding != clazz && !binding.isAssignableFrom(clazz))
/* 148 */         throw new IllegalAttributeException(clazz.getName() + " is not an acceptable class for " + type.getName() + " as it is not assignable from " + binding); 
/*     */     } 
/* 154 */     if (type.getRestrictions() != null)
/* 155 */       for (Filter f : type.getRestrictions()) {
/* 156 */         if (!f.evaluate(attribute))
/* 157 */           throw new IllegalAttributeException("Attribute instance (" + attribute.getIdentifier() + ")" + "fails to pass filter: " + f); 
/*     */       }  
/* 164 */     if (type.getSuper() != null)
/* 165 */       validate(type.getSuper(), attribute, attributeContent, true); 
/*     */   }
/*     */   
/*     */   public static void validate(AttributeDescriptor descriptor, Object value) throws IllegalAttributeException {
/* 175 */     if (descriptor == null)
/* 176 */       throw new NullPointerException("Attribute descriptor required for validation"); 
/* 179 */     if (value == null) {
/* 180 */       if (!descriptor.isNillable())
/* 181 */         throw new IllegalArgumentException(descriptor.getName() + " requires a non null value"); 
/*     */     } else {
/* 184 */       validate(descriptor.getType(), value, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object parse(AttributeDescriptor descriptor, Object value) throws IllegalArgumentException {
/* 200 */     if (value == null) {
/* 201 */       if (descriptor.isNillable())
/* 202 */         return descriptor.getDefaultValue(); 
/*     */     } else {
/* 206 */       Class target = descriptor.getType().getBinding();
/* 207 */       if (!target.isAssignableFrom(value.getClass())) {
/* 209 */         Object converted = Converters.convert(value, target);
/* 210 */         if (converted != null)
/* 211 */           return converted; 
/*     */       } 
/*     */     } 
/* 218 */     return value;
/*     */   }
/*     */   
/*     */   protected static void validate(AttributeType type, Object value, boolean isSuper) throws IllegalAttributeException {
/* 222 */     if (!isSuper) {
/* 229 */       Class<?> clazz = value.getClass();
/* 230 */       Class binding = type.getBinding();
/* 231 */       if (binding != null && !binding.isAssignableFrom(clazz))
/* 232 */         throw new IllegalAttributeException(clazz.getName() + " is not an acceptable class for " + type.getName() + " as it is not assignable from " + binding); 
/*     */     } 
/* 238 */     if (type.getRestrictions() != null && type.getRestrictions().size() > 0)
/* 239 */       for (Filter filter : type.getRestrictions()) {
/* 240 */         if (!filter.evaluate(value))
/* 241 */           throw new IllegalAttributeException(type.getName() + " restriction " + filter + " not met by: " + value); 
/*     */       }  
/* 247 */     if (type.getSuper() != null)
/* 248 */       validate(type.getSuper(), value, true); 
/*     */   }
/*     */   
/*     */   public static void assertNameAssignable(FeatureType expected, FeatureType actual) {
/* 284 */     String expectedName = expected.getName().getLocalPart();
/* 285 */     String actualName = actual.getName().getLocalPart();
/* 286 */     if (!expectedName.equals(actualName))
/* 287 */       throw new IllegalAttributeException("Expected '" + expectedName + "' but was supplied '" + actualName + "'."); 
/* 290 */     Set<String> names = new TreeSet<String>();
/* 291 */     for (PropertyDescriptor descriptor : actual.getDescriptors())
/* 292 */       names.add(descriptor.getName().getLocalPart()); 
/* 294 */     for (PropertyDescriptor descriptor : expected.getDescriptors()) {
/* 295 */       expectedName = descriptor.getName().getLocalPart();
/* 296 */       if (names.contains(expectedName)) {
/* 297 */         names.remove(expectedName);
/*     */         continue;
/*     */       } 
/* 300 */       throw new IllegalAttributeException("Expected to find a match for '" + expectedName + "' but was not available remaining names: " + names);
/*     */     } 
/* 303 */     if (!names.isEmpty())
/* 304 */       throw new IllegalAttributeException("Expected to find attributes '" + expectedName + "' but was not available remaining names: " + names); 
/* 308 */     for (PropertyDescriptor expectedDescriptor : expected.getDescriptors()) {
/* 309 */       expectedName = expectedDescriptor.getName().getLocalPart();
/* 310 */       PropertyDescriptor actualDescriptor = actual.getDescriptor(expectedName);
/* 312 */       Class<?> expectedBinding = expectedDescriptor.getType().getBinding();
/* 313 */       Class<?> actualBinding = actualDescriptor.getType().getBinding();
/* 314 */       if (!actualBinding.isAssignableFrom(expectedBinding))
/* 315 */         throw new IllegalArgumentException("Expected " + expectedBinding.getSimpleName() + " for " + expectedName + " but was " + actualBinding.getSimpleName()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void assertOrderAssignable(SimpleFeatureType expected, SimpleFeatureType actual) {
/* 337 */     String expectedName = expected.getName().getLocalPart();
/* 338 */     String actualName = actual.getName().getLocalPart();
/* 339 */     if (!expectedName.equals(actualName))
/* 340 */       throw new IllegalAttributeException("Expected '" + expectedName + "' but was supplied '" + actualName + "'."); 
/* 343 */     if (expected.getAttributeCount() != actual.getAttributeCount())
/* 344 */       throw new IllegalAttributeException("Expected " + expected.getAttributeCount() + " attributes, but was supplied " + actual.getAttributeCount()); 
/* 346 */     for (int i = 0; i < expected.getAttributeCount(); i++) {
/* 347 */       Class<?> expectedBinding = expected.getDescriptor(i).getType().getBinding();
/* 348 */       Class<?> actualBinding = actual.getDescriptor(i).getType().getBinding();
/* 349 */       if (!actualBinding.isAssignableFrom(expectedBinding)) {
/* 350 */         String name = expected.getDescriptor(i).getLocalName();
/* 351 */         throw new IllegalArgumentException("Expected " + expectedBinding.getSimpleName() + " for " + name + " but was " + actualBinding.getSimpleName());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Name[] names(ComplexType type) {
/* 363 */     ArrayList<Name> names = new ArrayList();
/* 364 */     for (Iterator<AttributeDescriptor> itr = type.getDescriptors().iterator(); itr.hasNext(); ) {
/* 365 */       AttributeDescriptor ad = itr.next();
/* 366 */       names.add(ad.getName());
/*     */     } 
/* 369 */     return names.<Name>toArray(new Name[names.size()]);
/*     */   }
/*     */   
/*     */   public static Name typeName(String name) {
/* 380 */     if (name == null)
/* 381 */       return null; 
/* 383 */     return (Name)new NameImpl(name);
/*     */   }
/*     */   
/*     */   public static Name typeName(String namespace, String name) {
/* 395 */     return (Name)new NameImpl(namespace, name);
/*     */   }
/*     */   
/*     */   public static Name typeName(Name name) {
/* 404 */     return (Name)new NameImpl(name.getNamespaceURI(), name.getLocalPart());
/*     */   }
/*     */   
/*     */   public static Name[] toNames(String[] names) {
/* 417 */     if (names == null)
/* 418 */       return null; 
/* 420 */     Name[] attributeNames = new Name[names.length];
/* 422 */     for (int i = 0; i < names.length; i++)
/* 423 */       attributeNames[i] = typeName(names[i]); 
/* 426 */     return attributeNames;
/*     */   }
/*     */   
/*     */   public static Name[] toTypeNames(String[] names) {
/* 439 */     if (names == null)
/* 440 */       return null; 
/* 443 */     Name[] typeNames = new Name[names.length];
/* 445 */     for (int i = 0; i < names.length; i++)
/* 446 */       typeNames[i] = typeName(names[i]); 
/* 449 */     return typeNames;
/*     */   }
/*     */   
/*     */   public static String[] fromNames(Name[] attributeNames) {
/* 457 */     if (attributeNames == null)
/* 458 */       return null; 
/* 461 */     String[] names = new String[attributeNames.length];
/* 462 */     for (int i = 0; i < attributeNames.length; i++)
/* 463 */       names[i] = attributeNames[i].getLocalPart(); 
/* 466 */     return names;
/*     */   }
/*     */   
/*     */   public static String[] fromTypeNames(Name[] typeNames) {
/* 474 */     if (typeNames == null)
/* 475 */       return null; 
/* 477 */     String[] names = new String[typeNames.length];
/* 478 */     for (int i = 0; i < typeNames.length; i++)
/* 479 */       names[i] = typeNames[i].getLocalPart(); 
/* 482 */     return names;
/*     */   }
/*     */   
/*     */   public static Name toTypeName(QName name) {
/* 486 */     if ("".equals(name.getNamespaceURI()))
/* 487 */       return typeName(name.getLocalPart()); 
/* 489 */     return typeName(name.getNamespaceURI(), name.getLocalPart());
/*     */   }
/*     */   
/*     */   public static boolean equals(Name name, QName qName) {
/* 493 */     if (name == null && qName != null)
/* 494 */       return false; 
/* 496 */     if (qName == null && name != null)
/* 497 */       return false; 
/* 499 */     if ("".equals(qName.getNamespaceURI())) {
/* 500 */       if (null != name.getNamespaceURI())
/* 501 */         return false; 
/* 503 */       return name.getLocalPart().equals(qName.getLocalPart());
/*     */     } 
/* 506 */     if (null == name.getNamespaceURI() && !"".equals(qName.getNamespaceURI()))
/* 508 */       return false; 
/* 511 */     return (name.getNamespaceURI().equals(qName.getNamespaceURI()) && name.getLocalPart().equals(qName.getLocalPart()));
/*     */   }
/*     */   
/*     */   public static Name degloseName(String prefixedName, NamespaceSupport namespaces) throws IllegalArgumentException {
/* 525 */     Name name = null;
/* 527 */     if (prefixedName == null)
/* 528 */       return null; 
/* 531 */     int prefixIdx = prefixedName.lastIndexOf(':');
/* 532 */     if (prefixIdx == -1)
/* 533 */       return typeName(prefixedName); 
/* 538 */     String nsPrefix = prefixedName.substring(0, prefixIdx);
/* 539 */     String localName = prefixedName.substring(prefixIdx + 1);
/* 540 */     String nsUri = namespaces.getURI(nsPrefix);
/* 543 */     if (nsUri == null)
/* 544 */       throw new IllegalArgumentException("No namespace set: The namespace has not been declared in the app-schema mapping file for name: " + nsPrefix + ":" + localName + " [Check the Namespaces section in the config file] "); 
/* 550 */     name = typeName(nsUri, localName);
/* 552 */     return name;
/*     */   }
/*     */   
/*     */   public static QName toQName(Name featurePath) {
/* 556 */     return toQName(featurePath, null);
/*     */   }
/*     */   
/*     */   public static QName toQName(Name featurePath, NamespaceSupport ns) {
/*     */     QName qName;
/* 560 */     if (featurePath == null)
/* 561 */       return null; 
/* 563 */     String namespace = featurePath.getNamespaceURI();
/* 564 */     String localName = featurePath.getLocalPart();
/* 566 */     if (null == namespace) {
/* 567 */       qName = new QName(localName);
/*     */     } else {
/* 569 */       if (ns != null) {
/* 570 */         String prefix = ns.getPrefix(namespace);
/* 571 */         if (prefix != null) {
/* 572 */           QName qName1 = new QName(namespace, localName, prefix);
/* 573 */           return qName1;
/*     */         } 
/*     */       } 
/* 576 */       qName = new QName(namespace, localName);
/*     */     } 
/* 578 */     return qName;
/*     */   }
/*     */   
/*     */   public static Name toName(QName name) {
/* 588 */     return toTypeName(name);
/*     */   }
/*     */   
/*     */   public static Object parse(AttributeType type, Object content) throws IllegalArgumentException {
/* 602 */     if (type instanceof AttributeTypeImpl) {
/* 603 */       AttributeTypeImpl hack = (AttributeTypeImpl)type;
/* 604 */       Object parsed = hack.parse(content);
/* 606 */       if (parsed != null)
/* 607 */         return parsed; 
/*     */     } 
/* 611 */     return content;
/*     */   }
/*     */   
/*     */   public static void validate(Attribute attribute) throws IllegalAttributeException {
/* 633 */     validate(attribute, attribute.getValue());
/*     */   }
/*     */   
/*     */   public static void validate(ComplexAttribute attribute) throws IllegalArgumentException {}
/*     */   
/*     */   public static void validate(ComplexAttribute attribute, Collection content) throws IllegalArgumentException {}
/*     */   
/*     */   protected static void validate(ComplexType type, ComplexAttribute attribute, Collection content) throws IllegalAttributeException {
/* 649 */     validate((AttributeType)type, (Attribute)attribute, content, false);
/* 651 */     if (content == null)
/*     */       return; 
/* 656 */     Collection schema = type.getDescriptors();
/* 658 */     int index = 0;
/* 659 */     for (Iterator<Attribute> itr = content.iterator(); itr.hasNext(); ) {
/* 660 */       Attribute att = itr.next();
/* 663 */       if (att == null)
/* 664 */         throw new NullPointerException("Attribute at index " + index + " is null. Attributes " + "can't be null. Do you mean Attribute.get() == null?"); 
/* 670 */       AttributeType attType = att.getType();
/* 671 */       boolean contains = false;
/* 672 */       for (Iterator<AttributeDescriptor> sitr = schema.iterator(); sitr.hasNext(); ) {
/* 673 */         AttributeDescriptor ad = sitr.next();
/* 674 */         if (ad.getType().equals(attType)) {
/* 675 */           contains = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 680 */       if (!contains)
/* 681 */         throw new IllegalArgumentException("Attribute of type " + attType.getName() + " found at index " + index + " but this type is not allowed by this descriptor"); 
/* 686 */       index++;
/*     */     } 
/* 690 */     if (type.getDescriptors().isEmpty()) {
/* 691 */       if (!content.isEmpty())
/* 692 */         throw new IllegalAttributeException(attribute.getDescriptor(), "Type indicates empty attribute collection, content does not"); 
/*     */       return;
/*     */     } 
/* 700 */     validateAll(type, attribute, content);
/* 702 */     if (type.getSuper() != null)
/* 703 */       validate((ComplexType)type.getSuper(), attribute, content); 
/*     */   }
/*     */   
/*     */   private static void validateAll(ComplexType type, ComplexAttribute att, Collection content) throws IllegalAttributeException {
/* 709 */     processAll(type.getDescriptors(), content);
/*     */   }
/*     */   
/*     */   private static void processAll(Collection all, Collection<?> content) throws IllegalAttributeException {
/* 720 */     ArrayList remaining = new ArrayList(content);
/* 721 */     for (Iterator<AttributeDescriptor> itr = all.iterator(); itr.hasNext(); ) {
/* 722 */       AttributeDescriptor ad = itr.next();
/* 724 */       int min = ad.getMinOccurs();
/* 725 */       int max = ad.getMaxOccurs();
/* 726 */       int occurences = 0;
/* 728 */       for (Iterator<Attribute> citr = remaining.iterator(); citr.hasNext(); ) {
/* 729 */         Attribute a = citr.next();
/* 730 */         if (a.getName().equals(ad.getName())) {
/* 731 */           occurences++;
/* 732 */           citr.remove();
/*     */         } 
/*     */       } 
/* 736 */       if (occurences < ad.getMinOccurs() || occurences > ad.getMaxOccurs())
/* 737 */         throw new IllegalAttributeException(ad, "Found " + occurences + " of " + ad.getName() + " when type" + "specifies between " + min + " and " + max); 
/*     */     } 
/* 742 */     if (!remaining.isEmpty())
/* 743 */       throw new IllegalAttributeException((AttributeDescriptor)remaining.iterator().next(), "Extra content found beyond the specified in the schema: " + remaining); 
/*     */   }
/*     */   
/*     */   public static boolean isSuperType(PropertyType type, PropertyType parent) {
/* 758 */     while (type.getSuper() != null) {
/* 759 */       type = type.getSuper();
/* 760 */       if (type.equals(parent))
/* 761 */         return true; 
/*     */     } 
/* 764 */     return false;
/*     */   }
/*     */   
/*     */   public static PropertyDescriptor descriptor(ComplexType type, String name) {
/* 778 */     List<PropertyDescriptor> match = descriptors(type, name);
/* 780 */     if (match.isEmpty())
/* 781 */       return null; 
/* 783 */     return match.get(0);
/*     */   }
/*     */   
/*     */   public static PropertyDescriptor descriptor(ComplexType type, String name, String namespace) {
/* 796 */     return descriptor(type, (Name)new NameImpl(namespace, name));
/*     */   }
/*     */   
/*     */   public static PropertyDescriptor descriptor(ComplexType type, Name name) {
/* 809 */     List<PropertyDescriptor> match = descriptors(type, name);
/* 811 */     if (match.isEmpty())
/* 812 */       return null; 
/* 814 */     return match.get(0);
/*     */   }
/*     */   
/*     */   public static List descriptors(ComplexType type, String name) {
/* 826 */     if (name == null)
/* 827 */       return Collections.EMPTY_LIST; 
/* 829 */     List<PropertyDescriptor> match = new ArrayList();
/* 831 */     for (Iterator<PropertyDescriptor> itr = type.getDescriptors().iterator(); itr.hasNext(); ) {
/* 832 */       PropertyDescriptor descriptor = itr.next();
/* 833 */       String localPart = descriptor.getName().getLocalPart();
/* 834 */       if (name.equals(localPart))
/* 835 */         match.add(descriptor); 
/*     */     } 
/* 841 */     if (match.size() == 0) {
/* 842 */       AttributeType superType = type.getSuper();
/* 843 */       if (superType instanceof ComplexType) {
/* 844 */         List<? extends PropertyDescriptor> superDescriptors = descriptors((ComplexType)superType, name);
/* 845 */         match.addAll(superDescriptors);
/*     */       } 
/*     */     } 
/* 848 */     return match;
/*     */   }
/*     */   
/*     */   public static List descriptors(ComplexType type, Name name) {
/* 860 */     if (name == null)
/* 861 */       return Collections.EMPTY_LIST; 
/* 863 */     List<PropertyDescriptor> match = new ArrayList();
/* 865 */     for (Iterator<PropertyDescriptor> itr = type.getDescriptors().iterator(); itr.hasNext(); ) {
/* 866 */       PropertyDescriptor descriptor = itr.next();
/* 867 */       Name descriptorName = descriptor.getName();
/* 868 */       if (name.equals(descriptorName))
/* 869 */         match.add(descriptor); 
/*     */     } 
/* 875 */     if (match.size() == 0) {
/* 876 */       AttributeType superType = type.getSuper();
/* 877 */       if (superType instanceof ComplexType) {
/* 878 */         List<? extends PropertyDescriptor> superDescriptors = descriptors((ComplexType)superType, name);
/* 879 */         match.addAll(superDescriptors);
/*     */       } 
/*     */     } 
/* 882 */     return match;
/*     */   }
/*     */   
/*     */   public static List<PropertyDescriptor> descriptors(ComplexType type) {
/* 894 */     List<PropertyDescriptor> children = new ArrayList<PropertyDescriptor>();
/* 895 */     ComplexType loopType = type;
/* 896 */     while (loopType != null) {
/* 897 */       children.addAll(loopType.getDescriptors());
/* 898 */       loopType = (loopType.getSuper() instanceof ComplexType) ? (ComplexType)loopType.getSuper() : null;
/*     */     } 
/* 901 */     return children;
/*     */   }
/*     */   
/*     */   public static PropertyDescriptor findDescriptor(ComplexType parentType, Name name) {
/* 914 */     List<PropertyDescriptor> descriptors = descriptors(parentType);
/*     */     Iterator<PropertyDescriptor> it;
/* 917 */     for (it = descriptors.iterator(); it.hasNext(); ) {
/* 918 */       PropertyDescriptor d = it.next();
/* 919 */       if (d.getName().equals(name))
/* 920 */         return d; 
/*     */     } 
/* 925 */     for (it = descriptors.iterator(); it.hasNext(); ) {
/* 926 */       List<AttributeDescriptor> substitutionGroup = (List<AttributeDescriptor>)((PropertyDescriptor)it.next()).getUserData().get("substitutionGroup");
/* 928 */       if (substitutionGroup != null) {
/* 929 */         Iterator<AttributeDescriptor> it2 = substitutionGroup.iterator();
/* 930 */         while (it2.hasNext()) {
/* 931 */           AttributeDescriptor d = it2.next();
/* 932 */           if (d.getName().equals(name))
/* 933 */             return (PropertyDescriptor)d; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 939 */     return null;
/*     */   }
/*     */   
/*     */   public static PropertyDescriptor findDescriptor(ComplexType parentType, String name) {
/* 951 */     List<PropertyDescriptor> descriptors = descriptors(parentType);
/*     */     Iterator<PropertyDescriptor> it;
/* 954 */     for (it = descriptors.iterator(); it.hasNext(); ) {
/* 955 */       PropertyDescriptor d = it.next();
/* 956 */       if (d.getName().getLocalPart().equals(name))
/* 957 */         return d; 
/*     */     } 
/* 962 */     for (it = descriptors.iterator(); it.hasNext(); ) {
/* 963 */       List<AttributeDescriptor> substitutionGroup = (List<AttributeDescriptor>)((PropertyDescriptor)it.next()).getUserData().get("substitutionGroup");
/* 965 */       if (substitutionGroup != null) {
/* 966 */         Iterator<AttributeDescriptor> it2 = substitutionGroup.iterator();
/* 967 */         while (it2.hasNext()) {
/* 968 */           AttributeDescriptor d = it2.next();
/* 969 */           if (d.getName().getLocalPart().equals(name))
/* 970 */             return (PropertyDescriptor)d; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 976 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\Types.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */