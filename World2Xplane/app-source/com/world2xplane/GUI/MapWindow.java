/*    */ package com.world2xplane.GUI;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class MapWindow extends JPanel {
/*    */   public MapWindow() {
/* 32 */     setPreferredSize(new Dimension(420, 420));
/*    */   }
/*    */   
/*    */   public void paintComponent(Graphics g) {
/* 39 */     super.paintComponent(g);
/* 41 */     g.drawRect(10, 10, 240, 240);
/* 43 */     g.fillRoundRect(50, 50, 100, 100, 80, 80);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\GUI\MapWindow.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */