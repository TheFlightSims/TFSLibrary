/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.ComplexType;
/*     */ import org.opengis.feature.type.Name;
/*     */ 
/*     */ public class Descriptors {
/*  48 */   private static final Logger LOGGER = Logging.getLogger(Descriptors.class.getPackage().getName());
/*     */   
/*     */   public static final List wrapAttributeTypes(List typeList) {
/*  59 */     List<AttributeDescriptor> descriptors = new ArrayList(typeList.size());
/*  60 */     for (Iterator<AttributeType> i = typeList.iterator(); i.hasNext(); ) {
/*  61 */       AttributeType attributeType = i.next();
/*  62 */       descriptors.add(wrapAttributeType(attributeType));
/*     */     } 
/*  64 */     return descriptors;
/*     */   }
/*     */   
/*     */   public static final AttributeDescriptor wrapAttributeType(AttributeType type) {
/*  74 */     if (type == null)
/*  75 */       return null; 
/*  77 */     return new AttributeDescriptorImpl(type, type.getName(), 1, 1, true, null);
/*     */   }
/*     */   
/*     */   public static final AttributeDescriptor find(List descriptors, Name name) {
/*  90 */     if (name == null)
/*  90 */       return null; 
/*  91 */     for (Iterator<AttributeDescriptor> i = descriptors.iterator(); i.hasNext(); ) {
/*  92 */       AttributeDescriptor attributeType = i.next();
/*  93 */       if (name.equals(attributeType.getType().getName()))
/*  94 */         return attributeType; 
/*     */     } 
/*  97 */     return null;
/*     */   }
/*     */   
/*     */   AttributeDescriptor restrict(AttributeDescriptor node, AttributeDescriptor restrict) {
/* 359 */     if (node.getType() == restrict.getType())
/* 360 */       return restrict; 
/* 362 */     for (AttributeType type = restrict.getType(); type != null; type = type.getSuper()) {
/* 363 */       if (node.getType().equals(type))
/* 364 */         return restrict; 
/*     */     } 
/* 367 */     throw new IllegalArgumentException("Cannot restrict provided schema");
/*     */   }
/*     */   
/*     */   Collection restriction(Collection schema, Collection restrict, Collection<AttributeDescriptor> restriction) {
/* 372 */     if (schema.size() != restrict.size())
/* 373 */       throw new IllegalArgumentException("You must provide an exact structure match in order to implement restriction"); 
/* 377 */     Iterator<AttributeDescriptor> i = schema.iterator();
/* 378 */     Iterator<AttributeDescriptor> j = restrict.iterator();
/* 379 */     while (i.hasNext() && j.hasNext())
/* 380 */       restriction.add(restrict(i.next(), j.next())); 
/* 383 */     return restriction;
/*     */   }
/*     */   
/*     */   public static AttributeType type(Collection schema, Name name) {
/* 398 */     AttributeDescriptor node = node(schema, name);
/* 399 */     if (node != null)
/* 400 */       return node.getType(); 
/* 401 */     return null;
/*     */   }
/*     */   
/*     */   public static AttributeType type(ComplexType schema, String name) {
/* 416 */     return type(schema, (Name)new NameImpl(name));
/*     */   }
/*     */   
/*     */   public static AttributeType type(ComplexType schema, Name name) {
/* 427 */     AttributeDescriptor node = node(schema, name);
/* 428 */     if (node != null)
/* 429 */       return node.getType(); 
/* 430 */     return null;
/*     */   }
/*     */   
/*     */   public static AttributeDescriptor node(ComplexType schema, String name) {
/* 444 */     for (Iterator<AttributeDescriptor> itr = list((AttributeType)schema).iterator(); itr.hasNext(); ) {
/* 445 */       AttributeDescriptor node = itr.next();
/* 447 */       if (node.getName() == null) {
/* 450 */         LOGGER.warning("node has no name set, try to fix! " + node);
/* 451 */         if (node.getType().getName().getLocalPart().equals(name))
/* 452 */           return node; 
/*     */         continue;
/*     */       } 
/* 456 */       if (node.getName().getLocalPart().equals(name))
/* 457 */         return node; 
/*     */     } 
/* 462 */     AttributeType superType = schema.getSuper();
/* 463 */     if (superType instanceof ComplexType)
/* 464 */       return node((ComplexType)superType, name); 
/* 466 */     return null;
/*     */   }
/*     */   
/*     */   public static AttributeDescriptor node(ComplexType schema, Name name) {
/* 491 */     return node(list((AttributeType)schema), name);
/*     */   }
/*     */   
/*     */   public static AttributeDescriptor node(Collection schema, Name name) {
/* 503 */     for (Iterator<AttributeDescriptor> itr = schema.iterator(); itr.hasNext(); ) {
/* 505 */       AttributeDescriptor node = itr.next();
/* 507 */       Name nodeName = node.getName();
/* 508 */       if (nodeName == null) {
/* 511 */         LOGGER.warning("node has no name set, try to fix! " + node);
/* 512 */         Name name2 = node.getType().getName();
/* 513 */         if (null == name.getNamespaceURI()) {
/* 514 */           if (name.getLocalPart().equals(name2.getLocalPart()))
/* 515 */             return node; 
/*     */           continue;
/*     */         } 
/* 517 */         if (name2.getNamespaceURI().equals(name.getNamespaceURI()) && name2.getLocalPart().equals(name.getLocalPart()))
/* 519 */           return node; 
/*     */         continue;
/*     */       } 
/* 523 */       if (name.getNamespaceURI() != null) {
/* 524 */         if (name.getLocalPart().equals(nodeName.getLocalPart()))
/* 525 */           return node; 
/*     */         continue;
/*     */       } 
/* 527 */       if (name.equals(nodeName))
/* 528 */         return node; 
/*     */     } 
/* 533 */     return null;
/*     */   }
/*     */   
/*     */   public static AttributeDescriptor node(ComplexType schema, AttributeType type) {
/* 549 */     for (Iterator<AttributeDescriptor> itr = list((AttributeType)schema).iterator(); itr.hasNext(); ) {
/* 550 */       AttributeDescriptor node = itr.next();
/* 551 */       if (node.getType() == type)
/* 552 */         return node; 
/*     */     } 
/* 555 */     return null;
/*     */   }
/*     */   
/*     */   public static List nodes(ComplexType schema, AttributeType type) {
/* 566 */     List<AttributeDescriptor> nodes = new ArrayList();
/* 567 */     for (Iterator<AttributeDescriptor> itr = list((AttributeType)schema).iterator(); itr.hasNext(); ) {
/* 568 */       AttributeDescriptor node = itr.next();
/* 569 */       if (node.getType().equals(type))
/* 570 */         nodes.add(node); 
/*     */     } 
/* 573 */     return nodes;
/*     */   }
/*     */   
/*     */   public static List types(AttributeType type) {
/* 587 */     List<AttributeType> types = new ArrayList();
/* 588 */     for (Iterator<AttributeDescriptor> itr = list(type).iterator(); itr.hasNext(); ) {
/* 589 */       AttributeDescriptor node = itr.next();
/* 590 */       types.add(node.getType());
/*     */     } 
/* 592 */     return types;
/*     */   }
/*     */   
/*     */   public static boolean multiple(ComplexType schema, AttributeType type) {
/* 610 */     return (maxOccurs(schema, type) > 1);
/*     */   }
/*     */   
/*     */   public static int maxOccurs(ComplexType schema, AttributeType type) {
/* 614 */     List nodes = nodes(schema, type);
/* 615 */     if (nodes.isEmpty())
/* 616 */       return 0; 
/* 618 */     int max = 0;
/* 619 */     for (Iterator<AttributeDescriptor> itr = nodes.iterator(); itr.hasNext(); ) {
/* 620 */       AttributeDescriptor node = itr.next();
/* 621 */       if (max == Integer.MAX_VALUE)
/* 622 */         return Integer.MAX_VALUE; 
/* 624 */       max += node.getMaxOccurs();
/*     */     } 
/* 626 */     return max;
/*     */   }
/*     */   
/*     */   public static List list(AttributeType type) {
/* 639 */     ArrayList list = new ArrayList();
/* 641 */     if (type instanceof ComplexType)
/* 642 */       list = new ArrayList(((ComplexType)type).getDescriptors()); 
/* 645 */     return list;
/*     */   }
/*     */   
/*     */   public static boolean isSimple(Collection schema) {
/* 671 */     for (Iterator<AttributeDescriptor> itr = schema.iterator(); itr.hasNext(); ) {
/* 672 */       AttributeDescriptor d = itr.next();
/* 673 */       if (d.getMinOccurs() != 1 || d.getMaxOccurs() != 1)
/* 674 */         return false; 
/* 676 */       if (d.getType() instanceof ComplexType)
/* 677 */         return false; 
/*     */     } 
/* 681 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\Descriptors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */