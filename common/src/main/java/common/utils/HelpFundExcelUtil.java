/*
package common.utils;


import com.isoft.system.entity.ReadHelpFundExcel;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;

public class HelpFundExcelUtil {



    */
/**
     * 先创建一个实体类
     *//*

    ReadHelpFundExcel readHelpFundExcel = new ReadHelpFundExcel();

    */
/**
     * 读取Excel文件，获取信息集合
     *
     * @param mFile
     * @return
     *//*


    public Object
    getHelpFundExcelInfo(MultipartFile mFile){
        String fileName = mFile.getOriginalFilename();//获取文件名
        Object object = null;
        try {
            if(!validateExcel(fileName)){//验证文件名是否合格
                //不合格的话直接return
                return null;
            }
            boolean isExcel2003 = true;//根据文件名判断是2003版本的还是2007版本的
            if(isExcel2007(fileName)){
                isExcel2003 = false;
            }
            object= createExcel(mFile.getInputStream(), isExcel2003);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }


    */
/**
     * 判断是不是2007版本的excel
     * @param filePath
     * @return
     *//*

    public static boolean isExcel2007(String filePath){
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
    */
/**
     * 判断是不是2003版本的excel
     * @param filePath
     * @return
     *//*

    public static boolean isExcel2003(String filePath){
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public boolean validateExcel(String filePath){
        if(filePath ==null||!(isExcel2003(filePath) || isExcel2007(filePath))){
            readHelpFundExcel.setErrorMsgSheet("文件名不是excel格式");
            return false;
        }
        return true;
    }

    public Object createExcel(InputStream is , boolean isExcel2003){
        Object object = null;
        try {
            Workbook wb = null;
            if(isExcel2003){//如果是2003版本的就new一个2003的wb出来
                wb = new HSSFWorkbook(is);
            }else{
                //否则就new 一个2007版的出来
                wb = new XSSFWorkbook(is);

            }
            //再让wb去解析readExcelValue(Workbook wb)方法
            object = readExcelValue(wb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }



    */
/**
     * 读取excel信息
     *//*

    public Object readExcelValue(Workbook wb){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //先得到第一个sheet1   期初结余量
        Sheet sheet1 = wb.getSheetAt(0);
        // 期初结余量行数
        int totalRows = sheet1.getPhysicalNumberOfRows();
        readHelpFundExcel.setTotalRowsSheet(totalRows);
        //得到excel里面的列，前提是有行
        //大于1是因为我从第二行就是数据了，这个大家看情况而定
        if(totalRows >1 && sheet1.getRow(0)!=null){
            int totalCells = sheet1.getRow(0).getPhysicalNumberOfCells();
            readHelpFundExcel.setTotalCellsSheet(totalCells);
        }
        // 循环行数  期初结余量
        for (int r = 1 ; r < totalRows; r++){
            Row row = sheet1.getRow(r);
            if(row == null){
                continue;//如果行为空的话直接中断
            }
            // 循环列
            for(int c = 0; c<readHelpFundExcel.getTotalCellsSheet() ; c++) {
                Cell cell = row.getCell(c);
                // 列不等于空
                if(cell != null){
                    switch (c) {
                        case 0:
                            String year =cell.getStringCellValue();
                            break;
                        case 1:
                            String money =cell.getStringCellValue();
                            break;
                    }
                }
            }
        }

        //先得到第二个sheet2   收入
        Sheet sheet2 = wb.getSheetAt(1);
        // 收入行
        int incomeTotalRows = sheet2.getPhysicalNumberOfRows();
        int incomeTotalCells = 0; // 收入列
        if(incomeTotalRows >1 && sheet2.getRow(0)!=null){
            incomeTotalCells = sheet2.getRow(0).getPhysicalNumberOfCells();
        }
        // 循环行
        for (int r = 0; r<incomeTotalRows; r++) {
            Row row = sheet2.getRow(r);
            if(row == null){
                continue;//如果行为空的话直接中断
            }
            // 循环列
            for (int c =0; c< incomeTotalCells; c++) {
                Cell cellIncome = row.getCell(c);
                if (cellIncome !=null) {
                    switch (c) {
                        case 0:
                            String type =cellIncome.getStringCellValue();
                            break;
                        case 1:
                            String describe =cellIncome.getStringCellValue();
                            break;
                        case 2:
                            if (cellIncome.getCellType() ==0) {
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cellIncome)) {
                                    String data= sdf.format(cellIncome.getDateCellValue());
                                }
                            }
                            break;
                        case 3:
                            String money =cellIncome.getStringCellValue();
                            break;
                        case 4:
                            String remarks =cellIncome.getStringCellValue();
                            break;
                    }
                }
            }
        }

        //先得到第二个sheet3   支出(拨付)
        Sheet sheet3 = wb.getSheetAt(2);
        int expenditureTotalRows = sheet3.getPhysicalNumberOfRows();  // 支出(拨付)行
        int expenditureTotalCells = 0; // 支出(拨付)列
        if(expenditureTotalRows >1 && sheet3.getRow(0)!=null){
            expenditureTotalCells = sheet3.getRow(0).getPhysicalNumberOfCells();
        }

        for (int r = 0; r<expenditureTotalRows; r++) {
            Row row = sheet3.getRow(r);
            if(row!=null) {
                continue;//如果行为空的话直接中断
            }
            for(int c = 0;c<expenditureTotalCells; c++) {
                Cell cellExpenditure = row.getCell(c);
                if (cellExpenditure!=null) {
                    switch (c) {
                        case 0: // 申请人
                            String applicant =cellExpenditure.getStringCellValue();
                            break;
                        case 1: // 申请部门
                            String department =cellExpenditure.getStringCellValue();
                            break;
                        case 2: // 申请时间
                            if (cellExpenditure.getCellType() ==0) {
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cellExpenditure)) {
                                    String data= sdf.format(cellExpenditure.getDateCellValue());
                                }
                            }
                            break;
                        case 3: // 支出说明
                            String explain =cellExpenditure.getStringCellValue();
                            break;
                        case 4: // 金额
                            String money =cellExpenditure.getStringCellValue();
                            break;
                        case 5: // 付款方式
                            String paymentMthod  =cellExpenditure.getStringCellValue();
                            break;
                        case 6: // 备注
                            String remarks  =cellExpenditure.getStringCellValue();
                            break;

                    }
                }
            }
        }
        //先得到第四个sheet4  支出(帮困)
        Sheet sheet4 = wb.getSheetAt(3);
        int expenditureOvercomeTotalRows = sheet4.getPhysicalNumberOfRows(); // 支出(帮困)行
        int expenditureOvercomeTotalCells = 0; // 支出(帮困)列
        if(expenditureOvercomeTotalRows >1 && sheet4.getRow(0)!=null) {
            expenditureOvercomeTotalCells = sheet4.getRow(0).getPhysicalNumberOfCells();
        }
        for (int r=0; r < expenditureOvercomeTotalRows; r++) {
            Row row = sheet4.getRow(r);
            if (row!=null) {
                continue;//如果行为空的话直接中断
            }
            for (int c= 0; c<expenditureOvercomeTotalCells; c++) {
                Cell cellExpenditureOvercome = row.getCell(c);
                if (cellExpenditureOvercome !=null) {
                    switch (c) {
                        case 0: // 申请人
                            String applicant =cellExpenditureOvercome.getStringCellValue();
                            break;
                        case 1: // 申请部门
                            String department =cellExpenditureOvercome.getStringCellValue();
                            break;
                        case 2: // 申请时间
                            if (cellExpenditureOvercome.getCellType() ==0) {
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cellExpenditureOvercome)) {
                                    String data= sdf.format(cellExpenditureOvercome.getDateCellValue());
                                }
                            }
                            break;
                        case 3: // 性别
                            String gender = cellExpenditureOvercome.getStringCellValue();
                            break;
                        case 4: // 电话
                            String elephone = cellExpenditureOvercome.getStringCellValue();
                            break;
                        case 5: // 平均工资
                            String averageWage = cellExpenditureOvercome.getStringCellValue();
                            break;
                        case 6: // 家庭赡养人数
                            if (cellExpenditureOvercome.getCellType() ==0) { // 判断是否为数值
                                String number = cellExpenditureOvercome.getStringCellValue();
                            }
                            break;
                        case 7: // 家庭月收入
                            if (cellExpenditureOvercome.getCellType() ==0) { // 判断是否为数值
                                String monthlyIncome = cellExpenditureOvercome.getStringCellValue();
                            }
                            break;
                        case 8: // 致困类型
                            String typeDifficulty = cellExpenditureOvercome.getStringCellValue();
                            break;
                        case 9: // 致困原因
                            String causesDifficulty = cellExpenditureOvercome.getStringCellValue();
                            break;
                        case 10: // 帮扶类型
                            String typeAssistance = cellExpenditureOvercome.getStringCellValue();
                            break;
                        case 11: // 在职员工类型
                            String employeeType  = cellExpenditureOvercome.getStringCellValue();
                            break;
                        case 12: // 非在职员工类型
                            String isEmployeeType  = cellExpenditureOvercome.getStringCellValue();
                            break;
                        case 13: // 金额
                            String money  = cellExpenditureOvercome.getStringCellValue();
                            break;
                        case 14: // 出账日期
                            if (cellExpenditureOvercome.getCellType() ==0) {
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cellExpenditureOvercome)) {
                                    String statementDate= sdf.format(cellExpenditureOvercome.getDateCellValue());
                                }
                            }
                            break;
                    }
                }
            }
        }

        return null;
    }
}
*/
