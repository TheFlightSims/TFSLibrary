/*    */ package org.slf4j.impl;
/*    */ 
/*    */ import org.slf4j.IMarkerFactory;
/*    */ import org.slf4j.helpers.BasicMarkerFactory;
/*    */ import org.slf4j.spi.MarkerFactoryBinder;
/*    */ 
/*    */ public class StaticMarkerBinder implements MarkerFactoryBinder {
/* 33 */   public static final StaticMarkerBinder SINGLETON = new StaticMarkerBinder();
/*    */   
/* 35 */   final IMarkerFactory markerFactory = (IMarkerFactory)new BasicMarkerFactory();
/*    */   
/*    */   public IMarkerFactory getMarkerFactory() {
/* 45 */     return this.markerFactory;
/*    */   }
/*    */   
/*    */   public String getMarkerFactoryClassStr() {
/* 53 */     return BasicMarkerFactory.class.getName();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\slf4j\impl\StaticMarkerBinder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */