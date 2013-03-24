package org.dozeneyes;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

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

import org.dozeneyes.aspect.*;

public class GenerateLevelRows {

  protected static Logger log = new Logger(GenerateLevelRows.class);

  protected static final int FONT_SIZE = 10;

  protected WritableWorkbook workbook;
  protected WritableCellFormat cellFormat;
  protected WritableCellFormat cellFormatBold;
  protected Random random = new Random();

  private int row = 1;

  public GenerateLevelRows() { }


  public void createWorkbook(String outputFile) throws IOException, WriteException {
    File file = new File(outputFile);
    WorkbookSettings wbSettings = new WorkbookSettings();
    wbSettings.setLocale(new Locale("en", "EN"));

    workbook = Workbook.createWorkbook(file, wbSettings);
    workbook.createSheet("Levels", 0);

    WritableSheet excelSheet = workbook.getSheet(0);
    createFormats(excelSheet);
  }

  public void closeWorkbook() throws IOException, WriteException {
    workbook.write();
    workbook.close();
  }

  public void write(int complexity) throws IOException, WriteException {
    WritableSheet excelSheet = workbook.getSheet(0);
    complexitySelector(excelSheet, complexity);
  }

  protected void createFormats(WritableSheet sheet) throws WriteException {

    // cell formats
    WritableFont cellFont = new WritableFont(WritableFont.ARIAL, FONT_SIZE);
    cellFormat = new WritableCellFormat(cellFont);
    cellFormat.setWrap(false);

    WritableFont headerFont =
       new WritableFont(WritableFont.ARIAL, FONT_SIZE, WritableFont.BOLD, false);
    cellFormatBold = new WritableCellFormat(headerFont);
    cellFormatBold.setWrap(false);

    // header
    String[] headers = {
       "Level", "Orientation", "Color", "Pattern", "Sound", "Animation"
    };
    for (int i=0; i < headers.length; i++) {
       sheet.getColumnView(i).setSize(256*50);
       addHeader(sheet, i, 0, headers[i]);
    }
  }


  protected void complexitySelector(WritableSheet sheet, int complexity)
     throws WriteException, RowsExceededException {

     Orientation o = Orientation.LEFT;

     Color c = Aspect.resetColor();
     Pattern p = Aspect.resetPattern();
     Sound s = Aspect.resetSound();
     Animation a = Aspect.resetAnimation();

     // first row is completely random
     addLabel(sheet, 6, row, "complexity " + complexity);
     addRow(sheet, o, c, p, s, a);

     // for rows two and three...
     for (int i=0; i < 2; i++) {

        o = (i == 0) ? Orientation.CENTER : Orientation.RIGHT;

        switch (complexity) {
        case 5:
           // 5 - randomly selects three states for all 4 aspects.
           c = Aspect.newColor();
        case 4:
           // 4 - randomly selects one state for 1 aspect and three states for 3 aspects.
           p = Aspect.newPattern();
        case 3:
           // 3 - randomly selects two states for 1 aspect  and two  states for 2 aspects.
           s = Aspect.newSound();
        case 2:
           // 2 - randomly selects three states for 1 aspect and one state for 3 aspect
           //     (randomly picking which aspect to bestow 3 on)
           a = Aspect.newAnimation();
        case 1:
           // 1 - randomly selects one state for all 4 aspects.
           break;
        default:
           log.e("bad complexity");
        }
        addRow(sheet, o, c, p, s, a);
     }
  }

  protected void addRow(WritableSheet sheet,
                        Orientation o, Color c, Pattern p, Sound s, Animation a)
     throws WriteException, RowsExceededException {

     addLabel(sheet, 0, row, "[lvl#]");
     addLabel(sheet, 1, row, o.toString());
     addLabel(sheet, 2, row, c.toString());
     addLabel(sheet, 3, row, p.toString());
     addLabel(sheet, 4, row, s.toString());
     addLabel(sheet, 5, row, a.toString());
     row++;
  }

  protected void addHeader(WritableSheet sheet, int column, int row, String s)
          throws RowsExceededException, WriteException {
    Label label;
    label = new Label(column, row, s, cellFormatBold);
    sheet.addCell(label);
  }

  protected void addNumber(WritableSheet sheet, int column, int row, Integer integer)
          throws WriteException, RowsExceededException {
    Number number = new Number(column, row, integer, cellFormat);
    sheet.addCell(number);
  }

  protected void addLabel(WritableSheet sheet, int column, int row, String s)
          throws WriteException, RowsExceededException {
    Label label = new Label(column, row, s, cellFormat);
    sheet.addCell(label);
  }


  public static void main(String[] args) throws WriteException, IOException {
    GenerateLevelRows main = new GenerateLevelRows();
    String fn = "gen/levels.xls";
    main.createWorkbook(fn);
    for (int i=0; i < args.length; i++) {
        try {
           int complexity = Integer.parseInt(args[i]);
           log.d("generate " + complexity);
           main.write(complexity);
        }
        catch (NumberFormatException nfe) {
           log.e("couldn't parse " + args[i]);
        }
    }
    main.closeWorkbook();
    log.i("results are in " + fn);
  }
}


