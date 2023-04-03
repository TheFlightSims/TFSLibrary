/*    */ package org.geotools.referencing.factory.gridshift;
/*    */ 
/*    */ import java.net.URL;
/*    */ import org.geotools.factory.AbstractFactory;
/*    */ import org.geotools.metadata.iso.citation.Citations;
/*    */ import org.opengis.metadata.citation.Citation;
/*    */ 
/*    */ public class ClasspathGridShiftLocator extends AbstractFactory implements GridShiftLocator {
/*    */   public ClasspathGridShiftLocator() {
/* 34 */     super(50);
/*    */   }
/*    */   
/*    */   public Citation getVendor() {
/* 39 */     return Citations.GEOTOOLS;
/*    */   }
/*    */   
/*    */   public URL locateGrid(String grid) {
/* 44 */     return getClass().getResource(grid);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\gridshift\ClasspathGridShiftLocator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */