/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.DateFormatSymbols;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.UIManager;
/*     */ import org.jfree.date.SerialDate;
/*     */ 
/*     */ public class DateChooserPanel extends JPanel implements ActionListener {
/*     */   private Calendar chosenDate;
/*     */   
/*     */   private Color chosenDateButtonColor;
/*     */   
/*     */   private Color chosenMonthButtonColor;
/*     */   
/*     */   private Color chosenOtherButtonColor;
/*     */   
/*     */   private int firstDayOfWeek;
/*     */   
/* 106 */   private int yearSelectionRange = 20;
/*     */   
/* 111 */   private Font dateFont = new Font("SansSerif", 0, 10);
/*     */   
/*     */   private JComboBox monthSelector;
/*     */   
/*     */   private JComboBox yearSelector;
/*     */   
/*     */   private JButton todayButton;
/*     */   
/*     */   private JButton[] buttons;
/*     */   
/*     */   private boolean refreshing = false;
/*     */   
/*     */   private int[] WEEK_DAYS;
/*     */   
/*     */   public DateChooserPanel() {
/* 150 */     this(Calendar.getInstance(), false);
/*     */   }
/*     */   
/*     */   public DateChooserPanel(Calendar calendar, boolean controlPanel) {
/* 163 */     super(new BorderLayout());
/* 165 */     this.chosenDateButtonColor = UIManager.getColor("textHighlight");
/* 166 */     this.chosenMonthButtonColor = UIManager.getColor("control");
/* 167 */     this.chosenOtherButtonColor = UIManager.getColor("controlShadow");
/* 170 */     this.chosenDate = calendar;
/* 171 */     this.firstDayOfWeek = calendar.getFirstDayOfWeek();
/* 172 */     this.WEEK_DAYS = new int[7];
/* 173 */     for (int i = 0; i < 7; i++)
/* 174 */       this.WEEK_DAYS[i] = (this.firstDayOfWeek + i - 1) % 7 + 1; 
/* 177 */     add(constructSelectionPanel(), "North");
/* 178 */     add(getCalendarPanel(), "Center");
/* 179 */     if (controlPanel)
/* 180 */       add(constructControlPanel(), "South"); 
/* 182 */     setDate(calendar.getTime());
/*     */   }
/*     */   
/*     */   public void setDate(Date theDate) {
/* 192 */     this.chosenDate.setTime(theDate);
/* 193 */     this.monthSelector.setSelectedIndex(this.chosenDate.get(2));
/* 195 */     refreshYearSelector();
/* 196 */     refreshButtons();
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 206 */     return this.chosenDate.getTime();
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 216 */     if (e.getActionCommand().equals("monthSelectionChanged")) {
/* 217 */       JComboBox c = (JComboBox)e.getSource();
/* 223 */       int dayOfMonth = this.chosenDate.get(5);
/* 224 */       this.chosenDate.set(5, 1);
/* 225 */       this.chosenDate.set(2, c.getSelectedIndex());
/* 226 */       int maxDayOfMonth = this.chosenDate.getActualMaximum(5);
/* 228 */       this.chosenDate.set(5, Math.min(dayOfMonth, maxDayOfMonth));
/* 230 */       refreshButtons();
/* 232 */     } else if (e.getActionCommand().equals("yearSelectionChanged")) {
/* 233 */       if (!this.refreshing) {
/* 234 */         JComboBox c = (JComboBox)e.getSource();
/* 235 */         Integer y = (Integer)c.getSelectedItem();
/* 241 */         int dayOfMonth = this.chosenDate.get(5);
/* 242 */         this.chosenDate.set(5, 1);
/* 243 */         this.chosenDate.set(1, y.intValue());
/* 244 */         int maxDayOfMonth = this.chosenDate.getActualMaximum(5);
/* 246 */         this.chosenDate.set(5, Math.min(dayOfMonth, maxDayOfMonth));
/* 248 */         refreshYearSelector();
/* 249 */         refreshButtons();
/*     */       } 
/* 252 */     } else if (e.getActionCommand().equals("todayButtonClicked")) {
/* 253 */       setDate(new Date());
/* 255 */     } else if (e.getActionCommand().equals("dateButtonClicked")) {
/* 256 */       JButton b = (JButton)e.getSource();
/* 257 */       int i = Integer.parseInt(b.getName());
/* 258 */       Calendar cal = getFirstVisibleDate();
/* 259 */       cal.add(5, i);
/* 260 */       setDate(cal.getTime());
/*     */     } 
/*     */   }
/*     */   
/*     */   private JPanel getCalendarPanel() {
/* 272 */     JPanel p = new JPanel(new GridLayout(7, 7));
/* 273 */     DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
/* 274 */     String[] weekDays = dateFormatSymbols.getShortWeekdays();
/*     */     int i;
/* 276 */     for (i = 0; i < this.WEEK_DAYS.length; i++)
/* 277 */       p.add(new JLabel(weekDays[this.WEEK_DAYS[i]], 0)); 
/* 281 */     this.buttons = new JButton[42];
/* 282 */     for (i = 0; i < 42; i++) {
/* 283 */       JButton b = new JButton("");
/* 284 */       b.setMargin(new Insets(1, 1, 1, 1));
/* 285 */       b.setName(Integer.toString(i));
/* 286 */       b.setFont(this.dateFont);
/* 287 */       b.setFocusPainted(false);
/* 288 */       b.setActionCommand("dateButtonClicked");
/* 289 */       b.addActionListener(this);
/* 290 */       this.buttons[i] = b;
/* 291 */       p.add(b);
/*     */     } 
/* 293 */     return p;
/*     */   }
/*     */   
/*     */   private Color getButtonColor(Calendar theDate) {
/* 304 */     if (equalDates(theDate, this.chosenDate))
/* 305 */       return this.chosenDateButtonColor; 
/* 307 */     if (theDate.get(2) == this.chosenDate.get(2))
/* 309 */       return this.chosenMonthButtonColor; 
/* 312 */     return this.chosenOtherButtonColor;
/*     */   }
/*     */   
/*     */   private boolean equalDates(Calendar c1, Calendar c2) {
/* 324 */     if (c1.get(5) == c2.get(5) && c1.get(2) == c2.get(2) && c1.get(1) == c2.get(1))
/* 327 */       return true; 
/* 330 */     return false;
/*     */   }
/*     */   
/*     */   private Calendar getFirstVisibleDate() {
/* 341 */     Calendar c = Calendar.getInstance();
/* 342 */     c.set(this.chosenDate.get(1), this.chosenDate.get(2), 1);
/* 344 */     c.add(5, -1);
/* 345 */     while (c.get(7) != getFirstDayOfWeek())
/* 346 */       c.add(5, -1); 
/* 348 */     return c;
/*     */   }
/*     */   
/*     */   private int getFirstDayOfWeek() {
/* 358 */     return this.firstDayOfWeek;
/*     */   }
/*     */   
/*     */   private void refreshButtons() {
/* 365 */     Calendar c = getFirstVisibleDate();
/* 366 */     for (int i = 0; i < 42; i++) {
/* 367 */       JButton b = this.buttons[i];
/* 368 */       b.setText(Integer.toString(c.get(5)));
/* 369 */       b.setBackground(getButtonColor(c));
/* 370 */       c.add(5, 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void refreshYearSelector() {
/* 379 */     if (!this.refreshing) {
/* 380 */       this.refreshing = true;
/* 381 */       this.yearSelector.removeAllItems();
/* 382 */       Integer[] years = getYears(this.chosenDate.get(1));
/* 384 */       for (int i = 0; i < years.length; i++)
/* 385 */         this.yearSelector.addItem(years[i]); 
/* 387 */       this.yearSelector.setSelectedItem(new Integer(this.chosenDate.get(1)));
/* 389 */       this.refreshing = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Integer[] getYears(int chosenYear) {
/* 402 */     int size = this.yearSelectionRange * 2 + 1;
/* 403 */     int start = chosenYear - this.yearSelectionRange;
/* 405 */     Integer[] years = new Integer[size];
/* 406 */     for (int i = 0; i < size; i++)
/* 407 */       years[i] = new Integer(i + start); 
/* 409 */     return years;
/*     */   }
/*     */   
/*     */   private JPanel constructSelectionPanel() {
/* 419 */     JPanel p = new JPanel();
/* 421 */     int minMonth = this.chosenDate.getMinimum(2);
/* 422 */     int maxMonth = this.chosenDate.getMaximum(2);
/* 423 */     String[] months = new String[maxMonth - minMonth + 1];
/* 424 */     System.arraycopy(SerialDate.getMonths(), minMonth, months, 0, months.length);
/* 427 */     this.monthSelector = new JComboBox(months);
/* 428 */     this.monthSelector.addActionListener(this);
/* 429 */     this.monthSelector.setActionCommand("monthSelectionChanged");
/* 430 */     p.add(this.monthSelector);
/* 432 */     this.yearSelector = new JComboBox(getYears(0));
/* 433 */     this.yearSelector.addActionListener(this);
/* 434 */     this.yearSelector.setActionCommand("yearSelectionChanged");
/* 435 */     p.add(this.yearSelector);
/* 437 */     return p;
/*     */   }
/*     */   
/*     */   private JPanel constructControlPanel() {
/* 448 */     JPanel p = new JPanel();
/* 449 */     p.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
/* 450 */     this.todayButton = new JButton("Today");
/* 451 */     this.todayButton.addActionListener(this);
/* 452 */     this.todayButton.setActionCommand("todayButtonClicked");
/* 453 */     p.add(this.todayButton);
/* 454 */     return p;
/*     */   }
/*     */   
/*     */   public Color getChosenDateButtonColor() {
/* 464 */     return this.chosenDateButtonColor;
/*     */   }
/*     */   
/*     */   public void setChosenDateButtonColor(Color chosenDateButtonColor) {
/* 473 */     if (chosenDateButtonColor == null)
/* 474 */       throw new NullPointerException("UIColor must not be null."); 
/* 476 */     Color oldValue = this.chosenDateButtonColor;
/* 477 */     this.chosenDateButtonColor = chosenDateButtonColor;
/* 478 */     refreshButtons();
/* 479 */     firePropertyChange("chosenDateButtonColor", oldValue, chosenDateButtonColor);
/*     */   }
/*     */   
/*     */   public Color getChosenMonthButtonColor() {
/* 489 */     return this.chosenMonthButtonColor;
/*     */   }
/*     */   
/*     */   public void setChosenMonthButtonColor(Color chosenMonthButtonColor) {
/* 498 */     if (chosenMonthButtonColor == null)
/* 499 */       throw new NullPointerException("UIColor must not be null."); 
/* 501 */     Color oldValue = this.chosenMonthButtonColor;
/* 502 */     this.chosenMonthButtonColor = chosenMonthButtonColor;
/* 503 */     refreshButtons();
/* 504 */     firePropertyChange("chosenMonthButtonColor", oldValue, chosenMonthButtonColor);
/*     */   }
/*     */   
/*     */   public Color getChosenOtherButtonColor() {
/* 514 */     return this.chosenOtherButtonColor;
/*     */   }
/*     */   
/*     */   public void setChosenOtherButtonColor(Color chosenOtherButtonColor) {
/* 523 */     if (chosenOtherButtonColor == null)
/* 524 */       throw new NullPointerException("UIColor must not be null."); 
/* 526 */     Color oldValue = this.chosenOtherButtonColor;
/* 527 */     this.chosenOtherButtonColor = chosenOtherButtonColor;
/* 528 */     refreshButtons();
/* 529 */     firePropertyChange("chosenOtherButtonColor", oldValue, chosenOtherButtonColor);
/*     */   }
/*     */   
/*     */   public int getYearSelectionRange() {
/* 539 */     return this.yearSelectionRange;
/*     */   }
/*     */   
/*     */   public void setYearSelectionRange(int yearSelectionRange) {
/* 548 */     int oldYearSelectionRange = this.yearSelectionRange;
/* 549 */     this.yearSelectionRange = yearSelectionRange;
/* 550 */     refreshYearSelector();
/* 551 */     firePropertyChange("yearSelectionRange", oldYearSelectionRange, yearSelectionRange);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\DateChooserPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */