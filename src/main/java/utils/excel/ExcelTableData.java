package utils.excel;

import java.util.List;

public interface ExcelTableData {
    /**
     * 获取所需要配置的类
     * @return
     */
    Class<?> getCls();

    /**
     * 获取Excel表格头部
     * @return
     */
    List<String> getHeaderList();

    /**
     * 获取图片列表
     * @return
     */
    List<byte[]> getImageList();

    /**
     * 获取Excel标题
     * @return
     */
    String getTitle();

    /**
     * 获取数据
     * @return
     */
    List getList();

}
