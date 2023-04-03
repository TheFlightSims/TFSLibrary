/*      */ package javax.media.jai.remote;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.List;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.ParameterList;
/*      */ import javax.media.jai.ParameterListDescriptor;
/*      */ import javax.media.jai.ParameterListDescriptorImpl;
/*      */ import javax.media.jai.ParameterListImpl;
/*      */ import javax.media.jai.util.CaselessStringKey;
/*      */ 
/*      */ public class NegotiableCapability extends ParameterListImpl implements Serializable {
/*      */   private String category;
/*      */   
/*      */   private String capabilityName;
/*      */   
/*      */   private List generators;
/*      */   
/*      */   private boolean isPreference = false;
/*      */   
/*      */   public NegotiableCapability(String category, String capabilityName, List generators, ParameterListDescriptor descriptor, boolean isPreference) {
/*  246 */     super(descriptor);
/*  248 */     if (category == null)
/*  249 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability0")); 
/*  253 */     if (capabilityName == null)
/*  254 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability1")); 
/*  258 */     ParameterListDescriptor desc = getParameterListDescriptor();
/*  259 */     int numParams = desc.getNumParameters();
/*  260 */     String[] names = desc.getParamNames();
/*  261 */     Class[] classes = desc.getParamClasses();
/*  262 */     Object[] defaults = desc.getParamDefaults();
/*  264 */     for (int i = 0; i < numParams; i++) {
/*  267 */       if (!Negotiable.class.isAssignableFrom(classes[i]))
/*  268 */         throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability4")); 
/*  272 */       if (defaults[i] == ParameterListDescriptor.NO_PARAMETER_DEFAULT)
/*  273 */         throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability5")); 
/*      */     } 
/*  278 */     this.category = category;
/*  279 */     this.capabilityName = capabilityName;
/*  280 */     this.generators = generators;
/*  281 */     this.isPreference = isPreference;
/*      */   }
/*      */   
/*      */   public String getCategory() {
/*  288 */     return this.category;
/*      */   }
/*      */   
/*      */   public String getCapabilityName() {
/*  295 */     return this.capabilityName;
/*      */   }
/*      */   
/*      */   public List getGenerators() {
/*  305 */     return this.generators;
/*      */   }
/*      */   
/*      */   public void setGenerators(List generators) {
/*  316 */     this.generators = generators;
/*      */   }
/*      */   
/*      */   public boolean isPreference() {
/*  324 */     return this.isPreference;
/*      */   }
/*      */   
/*      */   public Object getNegotiatedValue(String parameterName) {
/*  347 */     Negotiable value = (Negotiable)getObjectParameter(parameterName);
/*  348 */     if (value == null)
/*  349 */       return null; 
/*  350 */     return value.getNegotiatedValue();
/*      */   }
/*      */   
/*      */   public NegotiableCapability negotiate(NegotiableCapability capability) {
/*      */     int negStatus;
/*      */     Vector vector2, vector1, commonNames;
/*      */     Hashtable commonHash;
/*      */     Vector vector3, thisExtras, vector4, otherExtras;
/*      */     int thisExtraLength, otherExtraLength;
/*      */     Vector vector6, vector5, resultParams;
/*      */     int resultLength;
/*      */     String[] resultNames;
/*      */     int i;
/*      */     Class[] resultClasses;
/*      */     Object[] resultDefaults, resultValidValues;
/*      */     int count, j;
/*      */     ParameterListDescriptorImpl resultPLD;
/*      */     int k;
/*  431 */     if (capability == null)
/*  432 */       return null; 
/*  435 */     if (!capability.getCategory().equalsIgnoreCase(this.category) || !capability.getCapabilityName().equalsIgnoreCase(this.capabilityName))
/*  439 */       return null; 
/*  444 */     if (!areParameterListDescriptorsCompatible(capability))
/*  445 */       return null; 
/*  449 */     if (capability.isPreference() == true) {
/*  450 */       if (this.isPreference == true) {
/*  451 */         negStatus = 0;
/*      */       } else {
/*  453 */         negStatus = 1;
/*      */       } 
/*  456 */     } else if (this.isPreference == true) {
/*  457 */       negStatus = 2;
/*      */     } else {
/*  459 */       negStatus = 3;
/*      */     } 
/*  463 */     ParameterListDescriptor pld = getParameterListDescriptor();
/*  464 */     ParameterListDescriptor otherPld = capability.getParameterListDescriptor();
/*  466 */     String[] thisNames = pld.getParamNames();
/*  467 */     if (thisNames == null)
/*  468 */       thisNames = new String[0]; 
/*  469 */     String[] otherNames = otherPld.getParamNames();
/*  470 */     if (otherNames == null)
/*  471 */       otherNames = new String[0]; 
/*  472 */     Hashtable thisHash = hashNames(thisNames);
/*  473 */     Hashtable otherHash = hashNames(otherNames);
/*  475 */     Class[] thisClasses = pld.getParamClasses();
/*  476 */     Class[] otherClasses = otherPld.getParamClasses();
/*  477 */     Object[] thisDefaults = pld.getParamDefaults();
/*  478 */     Object[] otherDefaults = otherPld.getParamDefaults();
/*  480 */     NegotiableCapability result = null;
/*  483 */     ArrayList resultGenerators = new ArrayList();
/*  484 */     if (this.generators != null)
/*  485 */       resultGenerators.addAll(this.generators); 
/*  486 */     if (capability.getGenerators() != null)
/*  487 */       resultGenerators.addAll(capability.getGenerators()); 
/*  489 */     switch (negStatus) {
/*      */       case 0:
/*  493 */         vector2 = commonElements(thisHash, otherHash);
/*  494 */         commonHash = hashNames(vector2);
/*  495 */         vector3 = removeAll(thisHash, commonHash);
/*  496 */         vector4 = removeAll(otherHash, commonHash);
/*  498 */         thisExtraLength = vector3.size();
/*  499 */         otherExtraLength = vector4.size();
/*  503 */         vector6 = new Vector(vector2);
/*  504 */         vector6.addAll(vector3);
/*  505 */         vector6.addAll(vector4);
/*  506 */         resultLength = vector6.size();
/*  507 */         resultNames = new String[resultLength];
/*  508 */         for (i = 0; i < resultLength; i++)
/*  509 */           resultNames[i] = vector6.elementAt(i); 
/*  512 */         resultClasses = new Class[resultLength];
/*  513 */         resultDefaults = new Object[resultLength];
/*  514 */         resultValidValues = new Object[resultLength];
/*  517 */         for (count = 0; count < vector2.size(); count++) {
/*  518 */           String name = (String)vector2.elementAt(count);
/*  519 */           resultClasses[count] = thisClasses[getIndex(thisHash, name)];
/*  520 */           resultDefaults[count] = thisDefaults[getIndex(thisHash, name)];
/*  521 */           resultValidValues[count] = pld.getParamValueRange(name);
/*      */         } 
/*  523 */         for (j = 0; j < thisExtraLength; j++) {
/*  524 */           String name = vector3.elementAt(j);
/*  525 */           resultClasses[count + j] = thisClasses[getIndex(thisHash, name)];
/*  526 */           resultDefaults[count + j] = thisDefaults[getIndex(thisHash, name)];
/*  528 */           resultValidValues[count + j] = pld.getParamValueRange(name);
/*      */         } 
/*  530 */         count += thisExtraLength;
/*  531 */         for (j = 0; j < otherExtraLength; j++) {
/*  532 */           String name = vector4.elementAt(j);
/*  533 */           resultClasses[j + count] = otherClasses[getIndex(otherHash, name)];
/*  535 */           resultDefaults[j + count] = otherDefaults[getIndex(otherHash, name)];
/*  537 */           resultValidValues[j + count] = otherPld.getParamValueRange(name);
/*      */         } 
/*  540 */         resultPLD = new ParameterListDescriptorImpl(null, resultNames, resultClasses, resultDefaults, resultValidValues);
/*  548 */         result = new NegotiableCapability(this.category, this.capabilityName, resultGenerators, (ParameterListDescriptor)resultPLD, true);
/*  554 */         for (k = 0; k < vector2.size(); k++) {
/*  555 */           String currParam = (String)vector2.elementAt(k);
/*  556 */           Negotiable thisValue = (Negotiable)getObjectParameter(currParam);
/*  557 */           Negotiable otherValue = (Negotiable)capability.getObjectParameter(currParam);
/*  567 */           if (thisValue == null) {
/*  568 */             result.setParameter(currParam, otherValue);
/*  572 */           } else if (otherValue == null) {
/*  573 */             result.setParameter(currParam, thisValue);
/*      */           } else {
/*  581 */             Negotiable resultValue = thisValue.negotiate(otherValue);
/*  582 */             if (resultValue == null)
/*  583 */               return null; 
/*  586 */             result.setParameter(currParam, resultValue);
/*      */           } 
/*      */         } 
/*  590 */         for (k = 0; k < thisExtraLength; k++) {
/*  591 */           String currParam = vector3.elementAt(k);
/*  592 */           result.setParameter(currParam, getObjectParameter(currParam));
/*      */         } 
/*  596 */         for (k = 0; k < otherExtraLength; k++) {
/*  597 */           String currParam = vector4.elementAt(k);
/*  598 */           result.setParameter(currParam, capability.getObjectParameter(currParam));
/*      */         } 
/*      */         break;
/*      */       case 1:
/*  607 */         vector1 = commonElements(thisHash, otherHash);
/*  608 */         commonHash = hashNames(vector1);
/*  609 */         thisExtras = removeAll(thisHash, commonHash);
/*  613 */         vector5 = new Vector(vector1);
/*  614 */         vector5.addAll(thisExtras);
/*  615 */         resultLength = vector5.size();
/*  616 */         resultNames = new String[resultLength];
/*  617 */         for (k = 0; k < resultLength; k++)
/*  618 */           resultNames[k] = vector5.elementAt(k); 
/*  621 */         resultClasses = new Class[resultLength];
/*  622 */         resultDefaults = new Object[resultLength];
/*  623 */         resultValidValues = new Object[resultLength];
/*  625 */         count = 0;
/*  626 */         for (count = 0; count < vector1.size(); count++) {
/*  627 */           String name = (String)vector1.elementAt(count);
/*  628 */           resultClasses[count] = thisClasses[getIndex(thisHash, name)];
/*  629 */           resultDefaults[count] = thisDefaults[getIndex(thisHash, name)];
/*  630 */           resultValidValues[count] = pld.getParamValueRange(name);
/*      */         } 
/*  632 */         for (k = 0; k < thisExtras.size(); k++) {
/*  633 */           String name = thisExtras.elementAt(k);
/*  634 */           resultClasses[k + count] = thisClasses[getIndex(thisHash, name)];
/*  635 */           resultDefaults[k + count] = thisDefaults[getIndex(thisHash, name)];
/*  637 */           resultValidValues[k + count] = pld.getParamValueRange(name);
/*      */         } 
/*  640 */         resultPLD = new ParameterListDescriptorImpl(null, resultNames, resultClasses, resultDefaults, resultValidValues);
/*  646 */         result = new NegotiableCapability(this.category, this.capabilityName, resultGenerators, (ParameterListDescriptor)resultPLD, false);
/*  652 */         for (k = 0; k < vector1.size(); k++) {
/*  653 */           String currParam = (String)vector1.elementAt(k);
/*  654 */           Negotiable thisValue = (Negotiable)getObjectParameter(currParam);
/*  655 */           Negotiable otherValue = (Negotiable)capability.getObjectParameter(currParam);
/*  658 */           if (thisValue == null)
/*  661 */             return null; 
/*  664 */           if (otherValue == null) {
/*  669 */             result.setParameter(currParam, thisValue);
/*      */           } else {
/*  672 */             Negotiable resultValue = thisValue.negotiate(otherValue);
/*  674 */             if (resultValue == null)
/*  678 */               return null; 
/*  680 */             result.setParameter(currParam, resultValue);
/*      */           } 
/*      */         } 
/*  686 */         for (k = 0; k < thisExtras.size(); k++) {
/*  687 */           String currParam = thisExtras.elementAt(k);
/*  688 */           Negotiable resultValue = (Negotiable)getObjectParameter(currParam);
/*  689 */           if (resultValue == null)
/*  690 */             return null; 
/*  691 */           result.setParameter(currParam, resultValue);
/*      */         } 
/*      */         break;
/*      */       case 2:
/*  699 */         commonNames = commonElements(thisHash, otherHash);
/*  700 */         commonHash = hashNames(commonNames);
/*  701 */         otherExtras = removeAll(otherHash, commonHash);
/*  705 */         resultParams = new Vector(commonNames);
/*  706 */         resultParams.addAll(otherExtras);
/*  707 */         resultLength = resultParams.size();
/*  708 */         resultNames = new String[resultLength];
/*  709 */         for (k = 0; k < resultLength; k++)
/*  710 */           resultNames[k] = resultParams.elementAt(k); 
/*  713 */         resultClasses = new Class[resultLength];
/*  714 */         resultDefaults = new Object[resultLength];
/*  715 */         resultValidValues = new Object[resultLength];
/*  716 */         count = 0;
/*  717 */         for (count = 0; count < commonNames.size(); count++) {
/*  718 */           String name = (String)commonNames.elementAt(count);
/*  719 */           resultClasses[count] = thisClasses[getIndex(thisHash, name)];
/*  720 */           resultDefaults[count] = thisDefaults[getIndex(thisHash, name)];
/*  721 */           resultValidValues[count] = pld.getParamValueRange(name);
/*      */         } 
/*  724 */         for (k = 0; k < otherExtras.size(); k++) {
/*  725 */           String name = otherExtras.elementAt(k);
/*  726 */           resultClasses[k + count] = otherClasses[getIndex(otherHash, name)];
/*  728 */           resultDefaults[k + count] = otherDefaults[getIndex(otherHash, name)];
/*  730 */           resultValidValues[k + count] = otherPld.getParamValueRange(name);
/*      */         } 
/*  733 */         resultPLD = new ParameterListDescriptorImpl(null, resultNames, resultClasses, resultDefaults, resultValidValues);
/*  739 */         result = new NegotiableCapability(this.category, this.capabilityName, resultGenerators, (ParameterListDescriptor)resultPLD, false);
/*  745 */         for (k = 0; k < commonNames.size(); k++) {
/*  746 */           String currParam = (String)commonNames.elementAt(k);
/*  747 */           Negotiable thisValue = (Negotiable)getObjectParameter(currParam);
/*  748 */           Negotiable otherValue = (Negotiable)capability.getObjectParameter(currParam);
/*  753 */           if (otherValue == null)
/*  754 */             return null; 
/*  757 */           if (thisValue == null) {
/*  762 */             result.setParameter(currParam, otherValue);
/*      */           } else {
/*  765 */             Negotiable resultValue = otherValue.negotiate(thisValue);
/*  767 */             if (resultValue == null)
/*  771 */               return null; 
/*  773 */             result.setParameter(currParam, resultValue);
/*      */           } 
/*      */         } 
/*  778 */         for (k = 0; k < otherExtras.size(); k++) {
/*  779 */           String currParam = otherExtras.elementAt(k);
/*  780 */           Negotiable resultValue = (Negotiable)capability.getObjectParameter(currParam);
/*  782 */           if (resultValue == null)
/*  783 */             return null; 
/*  784 */           result.setParameter(currParam, resultValue);
/*      */         } 
/*      */         break;
/*      */       case 3:
/*  792 */         result = new NegotiableCapability(this.category, this.capabilityName, resultGenerators, pld, false);
/*  795 */         for (k = 0; k < thisNames.length; k++) {
/*  796 */           String currParam = thisNames[k];
/*  797 */           Negotiable thisValue = (Negotiable)getObjectParameter(currParam);
/*  798 */           Negotiable otherValue = (Negotiable)capability.getObjectParameter(currParam);
/*  803 */           if (thisValue == null || otherValue == null)
/*  805 */             return null; 
/*  808 */           Negotiable resultValue = thisValue.negotiate(otherValue);
/*  810 */           if (resultValue == null)
/*  814 */             return null; 
/*  816 */           result.setParameter(currParam, resultValue);
/*      */         } 
/*      */         break;
/*      */     } 
/*  823 */     return result;
/*      */   }
/*      */   
/*      */   public boolean areParameterListDescriptorsCompatible(NegotiableCapability other) {
/*  847 */     if (other == null)
/*  848 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability6")); 
/*  852 */     ParameterListDescriptor thisDesc = getParameterListDescriptor();
/*  853 */     ParameterListDescriptor otherDesc = other.getParameterListDescriptor();
/*  855 */     String[] thisNames = thisDesc.getParamNames();
/*  856 */     if (thisNames == null)
/*  857 */       thisNames = new String[0]; 
/*  858 */     String[] otherNames = otherDesc.getParamNames();
/*  859 */     if (otherNames == null)
/*  860 */       otherNames = new String[0]; 
/*  861 */     Hashtable thisHash = hashNames(thisNames);
/*  862 */     Hashtable otherHash = hashNames(otherNames);
/*  864 */     if (!this.isPreference && !other.isPreference()) {
/*  867 */       if (thisDesc.getNumParameters() != otherDesc.getNumParameters())
/*  868 */         return false; 
/*  871 */       if (!containsAll(thisHash, otherHash))
/*  872 */         return false; 
/*  874 */       Class[] arrayOfClass1 = thisDesc.getParamClasses();
/*  875 */       Class[] arrayOfClass2 = otherDesc.getParamClasses();
/*  876 */       for (int j = 0; j < thisNames.length; j++) {
/*  877 */         if (arrayOfClass1[j] != arrayOfClass2[getIndex(otherHash, thisNames[j])])
/*  879 */           return false; 
/*      */       } 
/*  882 */       return true;
/*      */     } 
/*  886 */     Vector commonNames = commonElements(thisHash, otherHash);
/*  888 */     Class[] thisParamClasses = thisDesc.getParamClasses();
/*  889 */     Class[] otherParamClasses = otherDesc.getParamClasses();
/*  891 */     for (int i = 0; i < commonNames.size(); i++) {
/*  892 */       String currName = commonNames.elementAt(i);
/*  893 */       if (thisParamClasses[getIndex(thisHash, currName)] != otherParamClasses[getIndex(otherHash, currName)])
/*  895 */         return false; 
/*      */     } 
/*  898 */     return true;
/*      */   }
/*      */   
/*      */   private boolean containsAll(Hashtable thisHash, Hashtable otherHash) {
/*  906 */     for (Enumeration i = thisHash.keys(); i.hasMoreElements(); ) {
/*  907 */       CaselessStringKey thisNameKey = i.nextElement();
/*  908 */       if (!otherHash.containsKey(thisNameKey))
/*  909 */         return false; 
/*      */     } 
/*  912 */     return true;
/*      */   }
/*      */   
/*      */   private Vector removeAll(Hashtable thisHash, Hashtable otherHash) {
/*  918 */     Vector v = new Vector();
/*  920 */     for (Enumeration i = thisHash.keys(); i.hasMoreElements(); ) {
/*  921 */       CaselessStringKey thisNameKey = i.nextElement();
/*  922 */       if (otherHash.containsKey(thisNameKey))
/*      */         continue; 
/*  925 */       v.add(thisNameKey.toString());
/*      */     } 
/*  928 */     return v;
/*      */   }
/*      */   
/*      */   private int getIndex(Hashtable h, String s) {
/*  932 */     return ((Integer)h.get(new CaselessStringKey(s))).intValue();
/*      */   }
/*      */   
/*      */   private Vector commonElements(Hashtable thisHash, Hashtable otherHash) {
/*  938 */     Vector v = new Vector();
/*  940 */     for (Enumeration i = thisHash.keys(); i.hasMoreElements(); ) {
/*  941 */       CaselessStringKey thisNameKey = i.nextElement();
/*  942 */       if (otherHash.containsKey(thisNameKey))
/*  943 */         v.add(thisNameKey.toString()); 
/*      */     } 
/*  946 */     return v;
/*      */   }
/*      */   
/*      */   private Hashtable hashNames(String[] paramNames) {
/*  951 */     Hashtable h = new Hashtable();
/*  952 */     if (paramNames != null)
/*  953 */       for (int i = 0; i < paramNames.length; i++)
/*  954 */         h.put(new CaselessStringKey(paramNames[i]), new Integer(i));  
/*  958 */     return h;
/*      */   }
/*      */   
/*      */   private Hashtable hashNames(Vector paramNames) {
/*  963 */     Hashtable h = new Hashtable();
/*  964 */     if (paramNames != null)
/*  965 */       for (int i = 0; i < paramNames.size(); i++)
/*  966 */         h.put(new CaselessStringKey(paramNames.elementAt(i)), new Integer(i));  
/*  971 */     return h;
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, byte b) {
/*  988 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability2"));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, boolean b) {
/* 1004 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability2"));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, char c) {
/* 1020 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability2"));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, short s) {
/* 1036 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability2"));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, int i) {
/* 1052 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability2"));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, long l) {
/* 1068 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability2"));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, float f) {
/* 1084 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability2"));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, double d) {
/* 1100 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability2"));
/*      */   }
/*      */   
/*      */   public ParameterList setParameter(String paramName, Object obj) {
/* 1116 */     if (obj != null && !(obj instanceof Negotiable))
/* 1117 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability2")); 
/* 1121 */     super.setParameter(paramName, obj);
/* 1122 */     return (ParameterList)this;
/*      */   }
/*      */   
/*      */   public byte getByteParameter(String paramName) {
/* 1140 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability3"));
/*      */   }
/*      */   
/*      */   public boolean getBooleanParameter(String paramName) {
/* 1155 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability3"));
/*      */   }
/*      */   
/*      */   public char getCharParameter(String paramName) {
/* 1170 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability3"));
/*      */   }
/*      */   
/*      */   public short getShortParameter(String paramName) {
/* 1185 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability3"));
/*      */   }
/*      */   
/*      */   public int getIntParameter(String paramName) {
/* 1200 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability3"));
/*      */   }
/*      */   
/*      */   public long getLongParameter(String paramName) {
/* 1215 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability3"));
/*      */   }
/*      */   
/*      */   public float getFloatParameter(String paramName) {
/* 1230 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability3"));
/*      */   }
/*      */   
/*      */   public double getDoubleParameter(String paramName) {
/* 1245 */     throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapability3"));
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\NegotiableCapability.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */