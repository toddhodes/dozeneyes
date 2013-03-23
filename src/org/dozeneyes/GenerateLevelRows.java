package org.dozeneyes;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class GenerateLevelRows {

  protected static final int FONT_SIZE = 14;

  private WritableCellFormat timesBold;
  private WritableCellFormat times;
  private String inputFile;

  public void setOutputFile(String inputFile) {
     this.inputFile = inputFile;
  }

  public void write() throws IOException, WriteException {
    File file = new File(inputFile);
    WorkbookSettings wbSettings = new WorkbookSettings();

    wbSettings.setLocale(new Locale("en", "EN"));

    WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
    workbook.createSheet("Levels", 0);
    WritableSheet excelSheet = workbook.getSheet(0);
    createLabel(excelSheet);
    createContent(excelSheet);

    workbook.write();
    workbook.close();
  }

  private void createLabel(WritableSheet sheet)
      throws WriteException {

    // cell format
    WritableFont times10pt = new WritableFont(WritableFont.ARIAL, FONT_SIZE);
    times = new WritableCellFormat(times10pt);
    times.setWrap(true);

    // header
    WritableFont headerFont =
       new WritableFont(WritableFont.ARIAL, FONT_SIZE, WritableFont.BOLD, false);
    timesBold = new WritableCellFormat(headerFont);
    timesBold.setWrap(true);

    CellView cv = new CellView();
    cv.setFormat(times);
    cv.setFormat(timesBold);
    cv.setAutosize(true);

    // headers
    String[] headers = {
       "Level", "Orientation", "Color", "Pattern", "Sound", "Animation"
    };
    for (int i=0; i < headers.length; i++) {
       addCaption(sheet, i, 0, headers[i]);
    }

  }

  private void createContent(WritableSheet sheet) throws WriteException,
      RowsExceededException {
    for (int i = 1; i < 10; i++) {
      addNumber(sheet, 0, i, i + 10);
      addNumber(sheet, 1, i, i * i);
    }
    // calculate the sum of it
    StringBuffer buf = new StringBuffer();
    buf.append("SUM(A2:A10)");
    Formula f = new Formula(0, 10, buf.toString());
    sheet.addCell(f);
    buf = new StringBuffer();
    buf.append("SUM(B2:B10)");
    f = new Formula(1, 10, buf.toString());
    sheet.addCell(f);

    for (int i = 12; i < 20; i++) {
      addLabel(sheet, 0, i, "Boring text " + i);
      addLabel(sheet, 1, i, "Another text");
    }
  }

  private void addCaption(WritableSheet sheet, int column, int row, String s)
      throws RowsExceededException, WriteException {
    Label label;
    label = new Label(column, row, s, timesBold);
    sheet.addCell(label);
  }

  private void addNumber(WritableSheet sheet, int column, int row,
      Integer integer) throws WriteException, RowsExceededException {
    Number number;
    number = new Number(column, row, integer, times);
    sheet.addCell(number);
  }

  private void addLabel(WritableSheet sheet, int column, int row, String s)
      throws WriteException, RowsExceededException {
    Label label;
    label = new Label(column, row, s, times);
    sheet.addCell(label);
  }

  public static void main(String[] args) throws WriteException, IOException {
    GenerateLevelRows main = new GenerateLevelRows();
    String fn = "gen/levels.xls";
    main.setOutputFile(fn);
    main.write();
    System.out.println("results are in " + fn);
  }
}


