package util;

import entity.ProductEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ZhaoExcelUtil {

    private static final String PATH = "C:\\Users\\link.chen\\Desktop\\车轮数据库.xlsx";

    /**
     *
     * @param path 文件路径
     * @param width 宽度
     * @param diameter 直径
     * @param screw 偏距
     * @param distribution 螺孔数
     * @param centre 中心孔直径
     * @return
     * @throws Exception
     */
    public static List<ProductEntity> processExcelData(String path,
                                                       String width,
                                                       String diameter,
                                                       String screw,
                                                       String distribution,
                                                       String centre,
                                                       int pageNum,
                                                       int pageSize) throws Exception{
        boolean widthCondition = StringUtils.isNotBlank(width);
        boolean diameterCondition = StringUtils.isNotBlank(diameter);
        boolean screwCondition = StringUtils.isNotBlank(screw);
        boolean distributionCondition = StringUtils.isNotBlank(distribution);
        boolean centreCondition = StringUtils.isNotBlank(centre);

        double widthValue = -1;
        if (widthCondition){
            widthValue = Double.parseDouble(width);
        }
        double diameterValue = -1;
        if (diameterCondition){
            diameterValue = Double.parseDouble(diameter);
        }
        double screwValue = -1;
        if (screwCondition){
            screwValue = Double.parseDouble(diameter);
        }
        double distributionValue = -1;
        if (distributionCondition){
            distributionValue = Double.parseDouble(diameter);
        }
        double centreValue = -1;
        if (centreCondition){
            centreValue = Double.parseDouble(diameter);
        }

        //处理翻页
        int firstIndex = (pageNum - 1) * pageSize;

        List<ProductEntity> data = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(1);

            int index = 0;
            for (Row row : sheet) {
                //当查询的数据 == pageSize，则直接返回
                if (data.size() >= pageSize){
                    break;
                }
                //第一行时不用解析
                if (row.getRowNum() == 0) {
                    continue;
                }
                ProductEntity productEntity = new ProductEntity();
                productEntity.setRowNum(row.getRowNum() + 1);
                for (Cell cell : row) {

                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex){
                        case 0 : productEntity.setBase(getValueFromCell(cell));break;
                        case 1 : productEntity.setProductName(getValueFromCell(cell));break;
                        case 2 : productEntity.setComponentNum(getValueFromCell(cell));break;
                        case 3 : productEntity.setWidth(getValueFromCell(cell));break;
                        case 4 : productEntity.setDiameter(getValueFromCell(cell));break;
                        case 5 : productEntity.setOffset(getValueFromCell(cell));break;
                        case 6 : productEntity.setCentre(getValueFromCell(cell));break;
                        case 7 : productEntity.setScrew(getValueFromCell(cell));break;
                        case 8 : productEntity.setDistribution(getValueFromCell(cell));break;
                        case 9 : productEntity.setSingleWheelLoad(getValueFromCell(cell));break;
                        case 10 : productEntity.setRiMaterial(getValueFromCell(cell));break;
                        case 11 : productEntity.setRimthickness(getValueFromCell(cell));break;
                        case 12 : productEntity.setSpokeMaterial(getValueFromCell(cell));break;
                        case 13 : productEntity.setSpokeThicknes(getValueFromCell(cell));break;
                        case 14 : productEntity.setBendingFatigueTest(getValueFromCell(cell));break;
                        case 15 : productEntity.setBendingTestResult(getValueFromCell(cell));break;
                        case 16 : productEntity.setRadialFatigueTest(getValueFromCell(cell));break;
                        case 17 : productEntity.setRadialTestResult(getValueFromCell(cell));break;
                        case 18 : productEntity.setSpokeMold(getValueFromCell(cell));break;
                        case 19 : productEntity.setRiMould(getValueFromCell(cell));break;
                        case 20 : productEntity.setPressingMold(getValueFromCell(cell));break;
                        case 21 : productEntity.setProductDigitalModel(getValueFromCell(cell));break;
                        case 22 : productEntity.setProductdrawings(getValueFromCell(cell));break;
                        default : break;
                    }

                }

                //宽度
                if (widthCondition && !productEntity.getWidth().equals(String.valueOf(widthValue)) ){
                    continue;
                }

                //直径
                if (diameterCondition && !productEntity.getDiameter().equals(String.valueOf(diameterValue))){
                    continue;
                }

                //螺孔数
                if (screwCondition && !productEntity.getScrew().equals(String.valueOf(screwValue))){
                    continue;
                }
                //分布圆
                if (distributionCondition && !productEntity.getDistribution().equals(String.valueOf(distributionValue))){
                    continue;
                }

                //中心孔直径
                if (centreCondition && !productEntity.getCentre().equals(String.valueOf(centreValue))){
                    continue;
                }

                //当下标大于或等于初始行时开始放入返回list中
                if (index >= firstIndex){
                    data.add(productEntity);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private static String getValueFromCell(Cell cell){

        switch (cell.getCellType()){
            case STRING: return cell.getStringCellValue();
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return String.valueOf(cell.getArrayFormulaRange());
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());
            default: return "";
        }
    }
}
