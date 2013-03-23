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
  protected static Logger log = new Logger(GenerateLevelRows.class);

  private WritableCellFormat cellFormat;
  private WritableCellFormat cellFormatBold;
  private String outputFile;

  public void setOutputFile(String outputFile) {
     this.outputFile = outputFile;
  }

  public void write() throws IOException, WriteException {
    File file = new File(outputFile);
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
    WritableFont cellFont = new WritableFont(WritableFont.ARIAL, FONT_SIZE);
    cellFormat = new WritableCellFormat(cellFont);
    cellFormat.setWrap(true);

    // header
    WritableFont headerFont =
       new WritableFont(WritableFont.ARIAL, FONT_SIZE, WritableFont.BOLD, false);
    cellFormatBold = new WritableCellFormat(headerFont);
    cellFormatBold.setWrap(true);

    CellView cv = new CellView();
    cv.setAutosize(true);
    cv.setFormat(cellFormat);
    cv.setFormat(cellFormatBold);
  }

  private void createContent(WritableSheet sheet)
          throws WriteException, RowsExceededException {

    String[] headers = {
       "Level", "Orientation", "Color", "Pattern", "Sound", "Animation"
    };
    for (int i=0; i < headers.length; i++) {
       addHeader(sheet, i, 0, headers[i]);
    }

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

  private void addHeader(WritableSheet sheet, int column, int row, String s)
          throws RowsExceededException, WriteException {
    Label label;
    label = new Label(column, row, s, cellFormatBold);
    sheet.addCell(label);
  }

  private void addNumber(WritableSheet sheet, int column, int row, Integer integer)
          throws WriteException, RowsExceededException {
    Number number = new Number(column, row, integer, cellFormat);
    sheet.addCell(number);
  }

  private void addLabel(WritableSheet sheet, int column, int row, String s)
          throws WriteException, RowsExceededException {
    Label label = new Label(column, row, s, cellFormat);
    sheet.addCell(label);
  }

  protected void complexitySelector(int complexity) {
     switch (complexity) {
        case 1:
         // 1 - randomly selects one state for all 4 aspects.

        case 2:
         // 2 - randomly selects three states for 1 aspect and one state for 3 aspect
         //     (randomly picking which aspect to bestow 3 on)
        case 3:
         // 3 - randomly selects two states for 1 aspect  and two  states for 2 aspects.
        case 4:
         // 4 - randomly selects one state for 1 aspect and three states for 3 aspects.
        case 5:
         // 5 - randomly selects three states for all 4 aspects.
        default:
          log.e("bad complexity");
     }
  }

  /**
   */
  public static void main(String[] args) throws WriteException, IOException {
    GenerateLevelRows main = new GenerateLevelRows();
    String fn = "gen/levels.xls";
    main.setOutputFile(fn);
    main.write();
    System.out.println("results are in " + fn);
  }
}


