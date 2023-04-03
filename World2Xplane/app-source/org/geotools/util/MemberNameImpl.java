/*    */ package org.geotools.util;
/*    */ 
/*    */ import org.opengis.util.MemberName;
/*    */ import org.opengis.util.TypeName;
/*    */ 
/*    */ public class MemberNameImpl extends LocalName implements MemberName {
/*    */   private static final long serialVersionUID = 6188284973982058318L;
/*    */   
/*    */   private final TypeName typeName;
/*    */   
/*    */   public MemberNameImpl(CharSequence name, TypeName typeName) {
/* 65 */     super(name);
/* 66 */     this.typeName = typeName;
/*    */   }
/*    */   
/*    */   public TypeName getAttributeType() {
/* 76 */     return this.typeName;
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 84 */     if (super.equals(object)) {
/* 85 */       MemberNameImpl that = (MemberNameImpl)object;
/* 86 */       return Utilities.equals(this.typeName, that.typeName);
/*    */     } 
/* 88 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\MemberNameImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */