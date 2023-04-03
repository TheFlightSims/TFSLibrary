/*    */ package com.world2xplane.XPlane.FacadeTools;
/*    */ 
/*    */ import com.world2xplane.Rules.GeneratorStore;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class FacadeBuilder {
/*    */   public void buildFacade(GeneratorStore config, String wallColor, String roofColor, String material, String roofMaterial, Float height, String name, Long wayId, Long relationId) throws IOException {
/* 47 */     BuildingBuilder builder = new BrickBuilder(wallColor, roofColor, wayId, relationId);
/* 48 */     if ("stone".equals("material"))
/* 49 */       builder = new StoneBuilder(wallColor, roofColor, wayId, relationId); 
/* 51 */     if ("glass".equals("material"))
/* 52 */       builder = new GlassBuilder(wallColor, roofColor, wayId, relationId); 
/* 54 */     if ("concrete".equals("material"))
/* 55 */       builder = new ConcreteBuilder(wallColor, roofColor, wayId, relationId); 
/* 57 */     if ("metal".equals("material"))
/* 58 */       builder = new MetalBuilder(wallColor, roofColor, wayId, relationId); 
/*    */     try {
/* 61 */       builder.generateFacade(config, name);
/* 62 */     } catch (IOException e) {
/* 63 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\FacadeTools\FacadeBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */