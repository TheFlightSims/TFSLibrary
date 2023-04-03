/*     */ package org.jdom;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Attribute implements Serializable, Cloneable {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: Attribute.java,v $ $Revision: 1.56 $ $Date: 2007/11/10 05:28:58 $ $Name:  $";
/*     */   
/*     */   public static final int UNDECLARED_TYPE = 0;
/*     */   
/*     */   public static final int CDATA_TYPE = 1;
/*     */   
/*     */   public static final int ID_TYPE = 2;
/*     */   
/*     */   public static final int IDREF_TYPE = 3;
/*     */   
/*     */   public static final int IDREFS_TYPE = 4;
/*     */   
/*     */   public static final int ENTITY_TYPE = 5;
/*     */   
/*     */   public static final int ENTITIES_TYPE = 6;
/*     */   
/*     */   public static final int NMTOKEN_TYPE = 7;
/*     */   
/*     */   public static final int NMTOKENS_TYPE = 8;
/*     */   
/*     */   public static final int NOTATION_TYPE = 9;
/*     */   
/*     */   public static final int ENUMERATED_TYPE = 10;
/*     */   
/*     */   protected String name;
/*     */   
/*     */   protected transient Namespace namespace;
/*     */   
/*     */   protected String value;
/*     */   
/* 179 */   protected int type = 0;
/*     */   
/*     */   protected Element parent;
/*     */   
/*     */   protected Attribute() {}
/*     */   
/*     */   public Attribute(String name, String value, Namespace namespace) {
/* 205 */     this(name, value, 0, namespace);
/*     */   }
/*     */   
/*     */   public Attribute(String name, String value, int type, Namespace namespace) {
/* 227 */     setName(name);
/* 228 */     setValue(value);
/* 229 */     setAttributeType(type);
/* 230 */     setNamespace(namespace);
/*     */   }
/*     */   
/*     */   public Attribute(String name, String value) {
/* 251 */     this(name, value, 0, Namespace.NO_NAMESPACE);
/*     */   }
/*     */   
/*     */   public Attribute(String name, String value, int type) {
/* 275 */     this(name, value, type, Namespace.NO_NAMESPACE);
/*     */   }
/*     */   
/*     */   public Element getParent() {
/* 285 */     return this.parent;
/*     */   }
/*     */   
/*     */   public Document getDocument() {
/* 296 */     Element parentElement = getParent();
/* 297 */     if (parentElement != null)
/* 298 */       return parentElement.getDocument(); 
/* 301 */     return null;
/*     */   }
/*     */   
/*     */   protected Attribute setParent(Element parent) {
/* 311 */     this.parent = parent;
/* 312 */     return this;
/*     */   }
/*     */   
/*     */   public Attribute detach() {
/* 322 */     Element parentElement = getParent();
/* 323 */     if (parentElement != null)
/* 324 */       parentElement.removeAttribute(getName(), getNamespace()); 
/* 327 */     return this;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 349 */     return this.name;
/*     */   }
/*     */   
/*     */   public Attribute setName(String name) {
/* 361 */     String reason = Verifier.checkAttributeName(name);
/* 362 */     if (reason != null)
/* 363 */       throw new IllegalNameException(name, "attribute", reason); 
/* 365 */     this.name = name;
/* 366 */     return this;
/*     */   }
/*     */   
/*     */   public String getQualifiedName() {
/* 391 */     String prefix = this.namespace.getPrefix();
/* 394 */     if (prefix == null || "".equals(prefix))
/* 395 */       return getName(); 
/* 397 */     return prefix + ':' + getName();
/*     */   }
/*     */   
/*     */   public String getNamespacePrefix() {
/* 417 */     return this.namespace.getPrefix();
/*     */   }
/*     */   
/*     */   public String getNamespaceURI() {
/* 428 */     return this.namespace.getURI();
/*     */   }
/*     */   
/*     */   public Namespace getNamespace() {
/* 438 */     return this.namespace;
/*     */   }
/*     */   
/*     */   public Attribute setNamespace(Namespace namespace) {
/* 452 */     if (namespace == null)
/* 453 */       namespace = Namespace.NO_NAMESPACE; 
/* 458 */     if (namespace != Namespace.NO_NAMESPACE && "".equals(namespace.getPrefix()))
/* 460 */       throw new IllegalNameException("", "attribute namespace", "An attribute namespace without a prefix can only be the NO_NAMESPACE namespace"); 
/* 464 */     this.namespace = namespace;
/* 465 */     return this;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 476 */     return this.value;
/*     */   }
/*     */   
/*     */   public Attribute setValue(String value) {
/* 489 */     String reason = Verifier.checkCharacterData(value);
/* 490 */     if (reason != null)
/* 491 */       throw new IllegalDataException(value, "attribute", reason); 
/* 493 */     this.value = value;
/* 494 */     return this;
/*     */   }
/*     */   
/*     */   public int getAttributeType() {
/* 504 */     return this.type;
/*     */   }
/*     */   
/*     */   public Attribute setAttributeType(int type) {
/* 516 */     if (type < 0 || type > 10)
/* 517 */       throw new IllegalDataException(String.valueOf(type), "attribute", "Illegal attribute type"); 
/* 520 */     this.type = type;
/* 521 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 532 */     return "[Attribute: " + getQualifiedName() + "=\"" + this.value + "\"" + "]";
/*     */   }
/*     */   
/*     */   public final boolean equals(Object ob) {
/* 551 */     return (ob == this);
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 560 */     return super.hashCode();
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 569 */     Attribute attribute = null;
/*     */     try {
/* 571 */       attribute = (Attribute)super.clone();
/* 573 */     } catch (CloneNotSupportedException ignore) {}
/* 581 */     attribute.parent = null;
/* 582 */     return attribute;
/*     */   }
/*     */   
/*     */   public int getIntValue() throws DataConversionException {
/*     */     try {
/* 600 */       return Integer.parseInt(this.value.trim());
/* 601 */     } catch (NumberFormatException e) {
/* 602 */       throw new DataConversionException(this.name, "int");
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getLongValue() throws DataConversionException {
/*     */     try {
/* 617 */       return Long.parseLong(this.value.trim());
/* 618 */     } catch (NumberFormatException e) {
/* 619 */       throw new DataConversionException(this.name, "long");
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getFloatValue() throws DataConversionException {
/*     */     try {
/* 635 */       return Float.valueOf(this.value.trim()).floatValue();
/* 636 */     } catch (NumberFormatException e) {
/* 637 */       throw new DataConversionException(this.name, "float");
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getDoubleValue() throws DataConversionException {
/*     */     try {
/* 653 */       return Double.valueOf(this.value.trim()).doubleValue();
/* 654 */     } catch (NumberFormatException e) {
/* 656 */       String v = this.value.trim();
/* 657 */       if ("INF".equals(v))
/* 658 */         return Double.POSITIVE_INFINITY; 
/* 660 */       if ("-INF".equals(v))
/* 661 */         return Double.NEGATIVE_INFINITY; 
/* 663 */       throw new DataConversionException(this.name, "double");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getBooleanValue() throws DataConversionException {
/* 678 */     String valueTrim = this.value.trim();
/* 679 */     if (valueTrim.equalsIgnoreCase("true") || valueTrim.equalsIgnoreCase("on") || valueTrim.equalsIgnoreCase("1") || valueTrim.equalsIgnoreCase("yes"))
/* 684 */       return true; 
/* 685 */     if (valueTrim.equalsIgnoreCase("false") || valueTrim.equalsIgnoreCase("off") || valueTrim.equalsIgnoreCase("0") || valueTrim.equalsIgnoreCase("no"))
/* 691 */       return false; 
/* 693 */     throw new DataConversionException(this.name, "boolean");
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 701 */     out.defaultWriteObject();
/* 705 */     out.writeObject(this.namespace.getPrefix());
/* 706 */     out.writeObject(this.namespace.getURI());
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 712 */     in.defaultReadObject();
/* 714 */     this.namespace = Namespace.getNamespace((String)in.readObject(), (String)in.readObject());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\Attribute.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */