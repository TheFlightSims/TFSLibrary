/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.UnmodifiableArrayList;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterNameException;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ 
/*     */ public class DefaultParameterDescriptorGroup extends AbstractParameterDescriptor implements ParameterDescriptorGroup {
/*     */   private static final long serialVersionUID = -4613190550542423839L;
/*     */   
/*     */   private final int maximumOccurs;
/*     */   
/*     */   private final GeneralParameterDescriptor[] parameters;
/*     */   
/*     */   private transient List<GeneralParameterDescriptor> asList;
/*     */   
/*     */   public DefaultParameterDescriptorGroup(ParameterDescriptorGroup group) {
/*  90 */     super((GeneralParameterDescriptor)group);
/*  91 */     this.maximumOccurs = group.getMaximumOccurs();
/*  92 */     List<GeneralParameterDescriptor> c = group.descriptors();
/*  93 */     this.parameters = c.<GeneralParameterDescriptor>toArray(new GeneralParameterDescriptor[c.size()]);
/*     */   }
/*     */   
/*     */   public DefaultParameterDescriptorGroup(String name, GeneralParameterDescriptor[] parameters) {
/* 106 */     this(Collections.singletonMap("name", name), parameters);
/*     */   }
/*     */   
/*     */   public DefaultParameterDescriptorGroup(Citation authority, String name, GeneralParameterDescriptor[] parameters) {
/* 123 */     this(Collections.singletonMap("name", new NamedIdentifier(authority, name)), parameters);
/*     */   }
/*     */   
/*     */   public DefaultParameterDescriptorGroup(Map<String, ?> properties, GeneralParameterDescriptor[] parameters) {
/* 137 */     this(properties, 1, 1, parameters);
/*     */   }
/*     */   
/*     */   public DefaultParameterDescriptorGroup(Map<String, ?> properties, int minimumOccurs, int maximumOccurs, GeneralParameterDescriptor[] parameters) {
/* 157 */     super(properties, minimumOccurs, maximumOccurs);
/* 158 */     this.maximumOccurs = maximumOccurs;
/* 159 */     ensureNonNull("parameters", parameters);
/* 160 */     this.parameters = new GeneralParameterDescriptor[parameters.length];
/*     */     int i;
/* 161 */     for (i = 0; i < parameters.length; i++) {
/* 162 */       this.parameters[i] = parameters[i];
/* 163 */       ensureNonNull("parameters", (Object[])parameters, i);
/*     */     } 
/* 168 */     parameters = this.parameters;
/* 169 */     for (i = 0; i < parameters.length; i++) {
/* 170 */       String name = parameters[i].getName().getCode();
/* 171 */       for (int j = 0; j < parameters.length; j++) {
/* 172 */         if (i != j && 
/* 173 */           nameMatches((IdentifiedObject)parameters[j], name))
/* 174 */           throw new InvalidParameterNameException(Errors.format(154, parameters[j].getName().getCode(), Integer.valueOf(j), name, Integer.valueOf(i)), name); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getMaximumOccurs() {
/* 189 */     return this.maximumOccurs;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup createValue() {
/* 199 */     return new ParameterGroup(this);
/*     */   }
/*     */   
/*     */   private static final class AsList extends UnmodifiableArrayList<GeneralParameterDescriptor> {
/*     */     private static final long serialVersionUID = -2116304004367396735L;
/*     */     
/*     */     private transient Set<GeneralParameterDescriptor> asSet;
/*     */     
/*     */     public AsList(GeneralParameterDescriptor[] array) {
/* 217 */       super((Object[])array);
/*     */     }
/*     */     
/*     */     public boolean contains(Object object) {
/* 223 */       if (this.asSet == null)
/* 224 */         this.asSet = new HashSet<GeneralParameterDescriptor>((Collection<? extends GeneralParameterDescriptor>)this); 
/* 226 */       return this.asSet.contains(object);
/*     */     }
/*     */   }
/*     */   
/*     */   public List<GeneralParameterDescriptor> descriptors() {
/* 235 */     if (this.asList == null)
/* 236 */       if (this.parameters == null) {
/* 237 */         this.asList = Collections.emptyList();
/*     */       } else {
/* 238 */         switch (this.parameters.length) {
/*     */           case 0:
/* 239 */             this.asList = Collections.emptyList();
/* 246 */             return this.asList;
/*     */           case 1:
/*     */             this.asList = Collections.singletonList(this.parameters[0]);
/* 246 */             return this.asList;
/*     */           case 2:
/*     */           case 3:
/*     */             this.asList = (List<GeneralParameterDescriptor>)UnmodifiableArrayList.wrap((Object[])this.parameters);
/* 246 */             return this.asList;
/*     */         } 
/*     */         this.asList = (List<GeneralParameterDescriptor>)new AsList(this.parameters);
/*     */       }  
/* 246 */     return this.asList;
/*     */   }
/*     */   
/*     */   public GeneralParameterDescriptor descriptor(String name) throws ParameterNotFoundException {
/* 258 */     ensureNonNull("name", name);
/* 259 */     name = name.trim();
/* 260 */     List<DefaultParameterDescriptorGroup> subgroups = null;
/* 261 */     List<GeneralParameterDescriptor> parameters = descriptors();
/* 262 */     while (parameters != null) {
/* 263 */       for (GeneralParameterDescriptor param : parameters) {
/* 264 */         if (param instanceof org.opengis.parameter.ParameterDescriptor) {
/* 265 */           if (nameMatches((IdentifiedObject)param, name))
/* 266 */             return param; 
/*     */           continue;
/*     */         } 
/* 268 */         if (param instanceof DefaultParameterDescriptorGroup) {
/* 269 */           if (subgroups == null)
/* 270 */             subgroups = new LinkedList<DefaultParameterDescriptorGroup>(); 
/* 272 */           assert !subgroups.contains(param) : param;
/* 273 */           subgroups.add((DefaultParameterDescriptorGroup)param);
/*     */         } 
/*     */       } 
/* 280 */       if (subgroups == null || subgroups.isEmpty())
/*     */         break; 
/* 283 */       parameters = ((DefaultParameterDescriptorGroup)subgroups.remove(0)).descriptors();
/*     */     } 
/* 285 */     throw new ParameterNotFoundException(Errors.format(99, name), name);
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 299 */     if (object == this)
/* 301 */       return true; 
/* 303 */     if (super.equals(object, compareMetadata)) {
/* 304 */       DefaultParameterDescriptorGroup that = (DefaultParameterDescriptorGroup)object;
/* 305 */       return Arrays.equals((Object[])this.parameters, (Object[])that.parameters);
/*     */     } 
/* 307 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 318 */     int code = super.hashCode();
/* 320 */     for (int i = 0; i < this.parameters.length; i++)
/* 321 */       code = code * 37 + this.parameters[i].hashCode(); 
/* 323 */     return code;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\DefaultParameterDescriptorGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */