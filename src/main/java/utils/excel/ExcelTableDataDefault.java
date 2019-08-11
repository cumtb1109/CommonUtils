package utils.excel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.lang.annotation.Documented;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @ClaseName ExcelTableDataDefault
 * @Description
 * @Author Monster
 * @Date 2019/6/9 13:55
 * @Version 1.0
 **/
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ExcelTableDataDefault implements ExcelTableData {

    private Class<?> aClass;
    private List<String> headerList;
    private List<byte[]> imageList;
    private String title;
    private List dataList;


    public void setHeaderList(){
        List<String> headers = new ArrayList<>();
        List<Excel> excels = new ArrayList<>();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field: fields){
            Excel excel = field.getAnnotation(Excel.class);
            if (excel.destExcelName() == title){
                excels.add(excel);
            }
        }
        // 设置header的排列顺序
        excels.sort(new Comparator<Excel>() {
            @Override
            public int compare(Excel o1, Excel o2) {
                return o1.sort() - o2.sort();
            }
        });
        // 设置header是什么
        for (Excel excel : excels){
            headerList.add(excel.name());
        }
    }

    public void setImageList(List<byte[]> imageList){
        this.imageList = imageList;
    }

    public void setDataList(List<Object> dataList){
        //TODO 如何将数据按照我们想要的样子进行排序呢？？？？？
        this.dataList = dataList;
    }

    @Override
    public Class<?> getCls() {
        return aClass;
    }

    @Override
    public List<String> getHeaderList() {
        return headerList;
    }

    @Override
    public List<byte[]> getImageList() {
        return imageList;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List getList() {
        return dataList;
    }
}
