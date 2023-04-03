/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.opengis.util.MemberName;
/*     */ import org.opengis.util.Record;
/*     */ import org.opengis.util.RecordSchema;
/*     */ import org.opengis.util.RecordType;
/*     */ import org.opengis.util.TypeName;
/*     */ 
/*     */ public class RecordTypeImpl implements RecordType {
/*     */   private TypeName typeName;
/*     */   
/*     */   private Map<MemberName, TypeName> attributeTypes;
/*     */   
/*     */   private RecordSchema parent;
/*     */   
/*     */   private RecordTypeImpl() {}
/*     */   
/*     */   public RecordTypeImpl(RecordSchema parent, TypeName typeName, Collection<MemberName> members) {
/*  82 */     this.parent = parent;
/*  83 */     this.typeName = typeName;
/*  84 */     Map<MemberName, TypeName> attributeTypes = new HashMap<MemberName, TypeName>();
/*  85 */     for (MemberName member : members)
/*  86 */       attributeTypes.put(member, member.getAttributeType()); 
/*  88 */     this.attributeTypes = Collections.unmodifiableMap(attributeTypes);
/*     */   }
/*     */   
/*     */   public RecordTypeImpl(RecordSchema parent, TypeName typeName, Map<MemberName, TypeName> attributeTypes) {
/* 104 */     this.parent = parent;
/* 105 */     this.typeName = typeName;
/* 106 */     this.attributeTypes = Collections.unmodifiableMap(attributeTypes);
/*     */   }
/*     */   
/*     */   public TypeName getTypeName() {
/* 121 */     return this.typeName;
/*     */   }
/*     */   
/*     */   public RecordSchema getContainer() {
/* 128 */     return this.parent;
/*     */   }
/*     */   
/*     */   public Map<MemberName, TypeName> getAttributeTypes() {
/* 136 */     return this.attributeTypes;
/*     */   }
/*     */   
/*     */   public Set<MemberName> getMembers() {
/* 145 */     return getAttributeTypes().keySet();
/*     */   }
/*     */   
/*     */   public TypeName locate(MemberName memberName) {
/* 158 */     return getAttributeTypes().get(memberName);
/*     */   }
/*     */   
/*     */   public boolean isInstance(Record record) {
/* 175 */     return getMembers().equals(record.getAttributes().keySet());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\RecordTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */