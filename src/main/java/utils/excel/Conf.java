package utils.excel;

import java.time.LocalDate;
import java.util.List;

public interface Conf {
    /**
     * 获取相关的Table数据列表
     * @return
     */
    List<ExcelTableData>  getTableDataList();

    /**
     * 获取导出方式
     */
    ExportTagEnum getExportTag();
    /**
     * 获取导出文件的路径
     * @return
     */
    String getFilePath();
    /**
     * 获取时间
     */
    LocalDate getDate();
    /**
     * 文件名车给
     */
    String getSheetName();

    /**
     * 输出Excel文件
     */
    void export();

}
