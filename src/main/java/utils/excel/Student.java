package utils.excel;

import java.io.PipedReader;

/**
 * @ClaseName Student
 * @Description
 * @Author Monster
 * @Date 2019/6/8 17:59
 * @Version 1.0
 **/
public class Student {
    @Excel(sort = 5,name = "学生编号",destExcelName = "test")
    private int number;
    @Excel(sort = 10,name = "学生姓名",destExcelName = "test")
    private String name;
    @Excel(sort = 15,name = "学生性别",destExcelName = "test")
    private String sex;
    @Excel(sort = 20,name = "班级名称",destExcelName = "test")
    private String className;
    @Excel(sort = 25,name = "学生身份",destExcelName = "test")
    private String status;

}
