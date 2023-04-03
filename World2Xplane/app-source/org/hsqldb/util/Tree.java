package org.hsqldb.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.Vector;

class Tree extends Panel {
  private static Font fFont = new Font("Dialog", 0, 12);
  
  private static FontMetrics fMetrics = Toolkit.getDefaultToolkit().getFontMetrics(fFont);
  
  private static int iRowHeight = getMaxHeight(fMetrics);
  
  private static int iIndentWidth = 12;
  
  private int iMaxTextLength;
  
  private Dimension dMinimum;
  
  private Graphics gImage;
  
  private Image iImage;
  
  private int iWidth;
  
  private int iHeight;
  
  private int iFirstRow;
  
  private int iTreeWidth;
  
  private int iTreeHeight;
  
  private int iX;
  
  private int iY;
  
  private Vector vData = new Vector();
  
  private int iRowCount;
  
  private Scrollbar sbHoriz;
  
  private Scrollbar sbVert;
  
  private int iSbWidth;
  
  private int iSbHeight;
  
  Tree() {
    setLayout((LayoutManager)null);
    this.sbHoriz = new Scrollbar(0);
    add(this.sbHoriz);
    this.sbVert = new Scrollbar(1);
    add(this.sbVert);
  }
  
  public void setMinimumSize(Dimension paramDimension) {
    this.dMinimum = paramDimension;
  }
  
  public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
    this.iSbHeight = (this.sbHoriz.getPreferredSize()).height;
    this.iSbWidth = (this.sbVert.getPreferredSize()).width;
    this.iHeight = paramInt4 - this.iSbHeight;
    this.iWidth = paramInt3 - this.iSbWidth;
    this.sbHoriz.setBounds(0, this.iHeight, this.iWidth, this.iSbHeight);
    this.sbVert.setBounds(this.iWidth, 0, this.iSbWidth, this.iHeight);
    adjustScroll();
    this.iImage = null;
    repaint();
  }
  
  public void removeAll() {
    this.vData = new Vector();
    this.iRowCount = 0;
    adjustScroll();
    this.iMaxTextLength = 10;
    repaint();
  }
  
  public void addRow(String paramString1, String paramString2, String paramString3, int paramInt) {
    String[] arrayOfString = new String[4];
    if (paramString2 == null)
      paramString2 = ""; 
    arrayOfString[0] = paramString1;
    arrayOfString[1] = paramString2;
    arrayOfString[2] = paramString3;
    arrayOfString[3] = String.valueOf(paramInt);
    this.vData.addElement(arrayOfString);
    int i = fMetrics.stringWidth(paramString2);
    if (i > this.iMaxTextLength)
      this.iMaxTextLength = i; 
    this.iRowCount++;
  }
  
  public void addRow(String paramString1, String paramString2) {
    addRow(paramString1, paramString2, (String)null, 0);
  }
  
  public void update() {
    adjustScroll();
    repaint();
  }
  
  void adjustScroll() {
    this.iTreeHeight = iRowHeight * (this.iRowCount + 1);
    this.iTreeWidth = this.iMaxTextLength * 2;
    this.sbHoriz.setValues(this.iX, this.iWidth, 0, this.iTreeWidth);
    int i = this.iY / iRowHeight;
    int j = this.iHeight / iRowHeight;
    this.sbVert.setValues(i, j, 0, this.iRowCount + 1);
    this.iX = this.sbHoriz.getValue();
    this.iY = iRowHeight * this.sbVert.getValue();
  }
  
  public boolean handleEvent(Event paramEvent) {
    switch (paramEvent.id) {
      case 601:
      case 602:
      case 603:
      case 604:
      case 605:
        this.iX = this.sbHoriz.getValue();
        this.iY = iRowHeight * this.sbVert.getValue();
        repaint();
        return true;
    } 
    return super.handleEvent(paramEvent);
  }
  
  public void paint(Graphics paramGraphics) {
    if (paramGraphics == null || this.iWidth <= 0 || this.iHeight <= 0)
      return; 
    paramGraphics.setColor(SystemColor.control);
    paramGraphics.fillRect(this.iWidth, this.iHeight, this.iSbWidth, this.iSbHeight);
    if (this.iImage == null) {
      this.iImage = createImage(this.iWidth, this.iHeight);
      this.gImage = this.iImage.getGraphics();
      this.gImage.setFont(fFont);
    } 
    this.gImage.setColor(Color.white);
    this.gImage.fillRect(0, 0, this.iWidth, this.iHeight);
    int[] arrayOfInt = new int[100];
    String[] arrayOfString = new String[100];
    arrayOfString[0] = "";
    byte b1 = 0;
    int i = iRowHeight;
    i -= this.iY;
    boolean bool = false;
    for (byte b2 = 0; b2 < this.iRowCount; b2++) {
      String[] arrayOfString1 = this.vData.elementAt(b2);
      String str1 = arrayOfString1[0];
      String str2 = arrayOfString1[1];
      String str3 = arrayOfString1[2];
      byte b;
      for (b = b1; b && !str1.startsWith(arrayOfString[b]); b--);
      if (arrayOfString[b].length() < str1.length())
        b++; 
      if (!bool || b <= b1) {
        bool = (str3 != null && str3.equals("+")) ? true : false;
        arrayOfString[b] = str1;
        int j = iIndentWidth * b - this.iX;
        this.gImage.setColor(Color.lightGray);
        this.gImage.drawLine(j, i, j + iIndentWidth, i);
        this.gImage.drawLine(j, i, j, arrayOfInt[b]);
        arrayOfInt[b + 1] = i;
        int k = i + iRowHeight / 3;
        int m = j + iIndentWidth * 2;
        if (str3 != null) {
          arrayOfInt[b + 1] = arrayOfInt[b + 1] + 4;
          int n = Integer.parseInt(arrayOfString1[3]);
          this.gImage.setColor((n == 0) ? Color.white : new Color(n));
          this.gImage.fillRect(j + iIndentWidth - 3, i - 3, 7, 7);
          this.gImage.setColor(Color.black);
          this.gImage.drawRect(j + iIndentWidth - 4, i - 4, 8, 8);
          this.gImage.drawLine(j + iIndentWidth - 2, i, j + iIndentWidth + 2, i);
          if (str3.equals("+"))
            this.gImage.drawLine(j + iIndentWidth, i - 2, j + iIndentWidth, i + 2); 
        } else {
          m -= iIndentWidth;
        } 
        this.gImage.setColor(Color.black);
        this.gImage.drawString(str2, m, k);
        b1 = b;
        i += iRowHeight;
      } 
    } 
    paramGraphics.drawImage(this.iImage, 0, 0, this);
  }
  
  public void update(Graphics paramGraphics) {
    paint(paramGraphics);
  }
  
  public Dimension preferredSize() {
    return this.dMinimum;
  }
  
  public Dimension getPreferredSize() {
    return this.dMinimum;
  }
  
  public Dimension getMinimumSize() {
    return this.dMinimum;
  }
  
  public Dimension minimumSize() {
    return this.dMinimum;
  }
  
  public boolean mouseDown(Event paramEvent, int paramInt1, int paramInt2) {
    if (iRowHeight == 0 || paramInt1 > this.iWidth || paramInt2 > this.iHeight)
      return true; 
    paramInt2 += iRowHeight / 2;
    String[] arrayOfString = new String[100];
    arrayOfString[0] = "";
    byte b1 = 0;
    int i = iRowHeight;
    boolean bool = false;
    byte b2 = 0;
    paramInt2 += this.iY;
    while (b2 < this.iRowCount) {
      String[] arrayOfString1 = this.vData.elementAt(b2);
      String str1 = arrayOfString1[0];
      String str2 = arrayOfString1[2];
      byte b;
      for (b = b1; b && !str1.startsWith(arrayOfString[b]); b--);
      if (arrayOfString[b].length() < str1.length())
        b++; 
      if (!bool || b <= b1) {
        if (i <= paramInt2 && i + iRowHeight > paramInt2)
          break; 
        arrayOfString[b] = str1;
        bool = (str2 != null && str2.equals("+")) ? true : false;
        b1 = b;
        i += iRowHeight;
      } 
      b2++;
    } 
    if (b2 >= 0 && b2 < this.iRowCount) {
      String[] arrayOfString1 = this.vData.elementAt(b2);
      String str = arrayOfString1[2];
      if (str != null && str.equals("+")) {
        str = "-";
      } else if (str != null && str.equals("-")) {
        str = "+";
      } 
      arrayOfString1[2] = str;
      this.vData.setElementAt(arrayOfString1, b2);
      repaint();
    } 
    return true;
  }
  
  private static int getMaxHeight(FontMetrics paramFontMetrics) {
    return paramFontMetrics.getHeight() + 2;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\Tree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */