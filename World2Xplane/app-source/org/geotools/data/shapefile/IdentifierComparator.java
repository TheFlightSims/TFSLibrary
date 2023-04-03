/*    */ package org.geotools.data.shapefile;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.opengis.filter.identity.Identifier;
/*    */ 
/*    */ class IdentifierComparator implements Comparator<Identifier> {
/*    */   String prefix;
/*    */   
/*    */   public IdentifierComparator(String typeName) {
/* 33 */     this.prefix = typeName + ".";
/*    */   }
/*    */   
/*    */   public int compare(Identifier o1, Identifier o2) {
/* 37 */     String s1 = o1.toString();
/* 38 */     String s2 = o2.toString();
/* 39 */     if (s1.startsWith(this.prefix) && s2.startsWith(this.prefix))
/*    */       try {
/* 41 */         int i1 = Integer.parseInt(s1.substring(this.prefix.length()));
/* 42 */         int i2 = Integer.parseInt(s2.substring(this.prefix.length()));
/* 43 */         return i1 - i2;
/* 44 */       } catch (NumberFormatException e) {} 
/* 48 */     return s1.compareTo(s2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\IdentifierComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */