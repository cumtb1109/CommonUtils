package utils.excel;


import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import sun.jvm.hotspot.tools.ObjectHistogram;
import sun.nio.ch.ThreadPool;
import utils.entity.EntityUtils;

import java.io.DataInput;
import java.io.PipedReader;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClaseName ExcelUtils
 * @Description  针对Excel做出的一些工具方法，生成一个Excel
 * @Author Monster
 * @Date 2019/6/7 16:57
 * @Version 1.0
 **/
@Data
public class ExcelUtils {
    /**
     * 新建Excel的时间
     */
    private LocalDate localDate;

    private Conf conf;

    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    // 输出到磁盘
    public void export(){
        this.conf.export();
    }

    public HSSFWorkbook createExcel(){
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        List<ExcelTableData> data = conf.getTableDataList();
        for (ExcelTableData tmpData: data) {
            Sheet sheet = hssfWorkbook.createSheet();
            setSheetAllData(sheet, tmpData);
        }
        return hssfWorkbook;
    }

    /**
     * 设置表格的所有数据，包括表格头部，表格数据
     * @param sheet
     * @param data
     */
    public void setSheetAllData(Sheet sheet, ExcelTableData data){
        List<Object[]> list = setAnnotationList(data);
        setSheetHeader(sheet, list);
        setSheetData(sheet, list, data);
    }


    /**
     * 根据传入的数据初始化注解列表内容
     *
     * @param data
     * @return
     */
    private List<Object[]> setAnnotationList(ExcelTableData data){
        List<Object[]> annotationList = new ArrayList<>();
        Field[] fields = data.getCls().getDeclaredFields();
        for (Field field : fields){
            Excel excel = field.getAnnotation(Excel.class);
            if (excel.destExcelName() == data.getTitle()){
                annotationList.add(new Object[]{field, excel});
            }
        }
        annotationList.sort(new CompareWithExcelSort());
        return annotationList;
    }


    /**
     * 设置这个sheet表格的头部
     * @param sheet
     * @param annotationList   注解列表
     */
    private void setSheetHeader(Sheet sheet, List<Object[]> annotationList){
        Row row = sheet.createRow(0);
        Iterator iterator = annotationList.iterator();
        int i = 0;
        while (iterator.hasNext()){
            Object[] fieldAndAnnotation = (Object[]) iterator.next();
            Excel titleCell = (Excel)fieldAndAnnotation[0];
            row.createCell(i).setCellValue(titleCell.name());
            i++;
        }
    }

    //TODO 设置这个sheet表格的数据部分
    private void setSheetData(Sheet sheet, List<Object[]> annotationList, ExcelTableData data){
        int rowNumber = 1;
        List dataList = data.getList();
        Iterator dataIterator = dataList.iterator();
        Iterator annotationIterator = annotationList.iterator();
        for (Object object: dataList) {
            Row row = sheet.createRow(rowNumber);
            while (annotationIterator.hasNext()) {
                // 写入单元格
                int colNumber = 1;
                Object[] objects = (Object[]) annotationIterator.next();
                if (objects.length >= 2){
                    Field field = (Field) objects[0];
                    //Excel excel = (Excel) objects[1];
                    //将获得的内容转化为String类型插入到单元格中
                    String value = EntityUtils.getValueFromField(object, field).toString();
                    row.createCell(colNumber).setCellValue(value);
                }
            }
            rowNumber ++;
        }
    }


//    private void reflactData(ExcelTableData data){
//        // 获取类
//        Class clazz = data.getCls();
//        // 获取属性
//        Field[] fields = clazz.getFields();
//        List<String> dataHeader = new ArrayList<>();
//        // 遍历每一个属性
//        for (Field field: fields){
//            Excel excel = field.getAnnotation(Excel.class);
//            data
//        }
//    }



    private class CompareWithExcelSort implements Comparator<Object[]>{
        @Override
        public int compare(Object[] o1, Object[] o2) {
            if (o1.length > 1 && o2.length > 1){
                Excel excel1 = (Excel) o1[0];
                Excel excel2 = (Excel) o2[0];
                return excel1.sort() - excel2.sort();
            }
            return 0;
        }
    }

}
