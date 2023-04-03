package org.jfree.chart.labels;

import java.text.AttributedString;
import org.jfree.data.general.PieDataset;

public interface PieSectionLabelGenerator {
  String generateSectionLabel(PieDataset paramPieDataset, Comparable paramComparable);
  
  AttributedString generateAttributedSectionLabel(PieDataset paramPieDataset, Comparable paramComparable);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\PieSectionLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */