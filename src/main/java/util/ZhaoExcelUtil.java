package util;

import entity.ProductDetail;
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

    /**
     *
     * @param path 文件路径
     * @param width 宽度
     * @param diameter 直径
     * @param screw 偏距
     * @param distribution 螺孔数
     * @param centre 中心孔直径
     * @return 产品信息
     * @throws Exception 解析Excel异常
     */
    public static ProductEntity processExcelData(String path,
                                                 String width,
                                                 String diameter,
                                                 String offset,
                                                 String screw,
                                                 String distribution,
                                                 String centre,
                                                 int pageNum,
                                                 int pageSize) throws Exception{
        boolean widthCondition = StringUtils.isNotBlank(width);
        boolean diameterCondition = StringUtils.isNotBlank(diameter);
        boolean offsetCondition = StringUtils.isNotBlank(offset);
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

        double offsetValue = -1;
        if (offsetCondition){
            offsetValue = Double.parseDouble(offset);
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
        //总条数
        int total = 0;

        //处理翻页
        int firstIndex = (pageNum - 1) * pageSize;

        List<ProductDetail> data = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(1);

            for (Row row : sheet) {
                //第一行时不用解析
                if (row.getRowNum() == 0) {
                    continue;
                }
                ProductDetail productDetail = new ProductDetail();
                productDetail.setRowNum(row.getRowNum() + 1);

                row.getPhysicalNumberOfCells();

                //是否匹配
                boolean flag = false;
                for (int columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    Cell cell = row.getCell(columnIndex);

                    //获取单元内容
                    String cellStr = getValueFromCell(cell);
                    if (row.getRowNum() == 74){
                        System.out.println("7676767676767676");
                        System.out.println(cellStr);
                    }

                    //条件判断
                    //宽度
                    if (columnIndex == 3 && widthCondition && !cellStr.equals(String.valueOf(widthValue))) {
                        //不匹配
                        flag = true;
                        break;
                    }

                    //直径
                    if (columnIndex == 4 && diameterCondition && !cellStr.equals(String.valueOf(diameterValue))) {
                        //不匹配
                        flag = true;
                        break;
                    }

                    //偏距
                    if (columnIndex == 5 && offsetCondition && !cellStr.equals(String.valueOf(offsetValue))) {
                        //不匹配
                        flag = true;
                        break;
                    }

                    //中心孔直径
                    if (columnIndex == 6 && centreCondition && !cellStr.equals(String.valueOf(centreValue))) {
                        //不匹配
                        flag = true;
                        break;
                    }

                    //螺孔数
                    if (columnIndex == 7 && screwCondition && !cellStr.equals(String.valueOf(screwValue))) {
                        //不匹配
                        flag = true;
                        break;
                    }

                    //分布圆
                    if (columnIndex == 8 && distributionCondition && !cellStr.equals(String.valueOf(distributionValue))) {
                        flag = true;
                        break;
                    }

                    if (columnIndex == 5){
                        System.out.println("------------------");
                        System.out.println("1:" + cellStr);
                    }

                    switch (columnIndex) {
                        case 0:
                            productDetail.setBase(cellStr);
                            break;
                        case 1:
                            productDetail.setProductName(cellStr);
                            break;
                        case 2:
                            productDetail.setComponentNum(cellStr);
                            break;
                        case 3:
                            productDetail.setWidth(cellStr);
                            break;
                        case 4:
                            productDetail.setDiameter(cellStr);
                            break;
                        case 5:
                            productDetail.setOffset(cellStr);
                            break;
                        case 6:
                            productDetail.setCentre(cellStr);
                            break;
                        case 7:
                            productDetail.setScrew(cellStr);
                            break;
                        case 8:
                            productDetail.setDistribution(cellStr);
                            break;
                        case 9:
                            productDetail.setSingleWheelLoad(cellStr);
                            break;
                        case 10:
                            productDetail.setRiMaterial(cellStr);
                            break;
                        case 11:
                            productDetail.setRimthickness(cellStr);
                            break;
                        case 12:
                            productDetail.setSpokeMaterial(cellStr);
                            break;
                        case 13:
                            productDetail.setSpokeThicknes(cellStr);
                            break;
                        case 14:
                            productDetail.setBendingFatigueTest(cellStr);
                            break;
                        case 15:
                            productDetail.setBendingTestResult(cellStr);
                            break;
                        case 16:
                            productDetail.setRadialFatigueTest(cellStr);
                            break;
                        case 17:
                            productDetail.setRadialTestResult(cellStr);
                            break;
                        case 18:
                            productDetail.setSpokeMold(cellStr);
                            break;
                        case 19:
                            productDetail.setRiMould(cellStr);
                            break;
                        case 20:
                            productDetail.setPressingMold(cellStr);
                            break;
                        case 21:
                            productDetail.setProductDigitalModel(cellStr);
                            break;
                        case 22:
                            productDetail.setProductdrawings(cellStr);
                            break;
                        default:
                            break;
                    }
                }

                //如果不匹配，则跳过
                if (flag){
                    continue ;
                }
//                System.out.println("----------------");
                System.out.println("2:"+ productDetail.getOffset());
                //总条数+1
                total ++;

                //当下标大于或等于初始行时开始放入返回list中
                if (total > firstIndex && data.size() < pageSize){
                    data.add(productDetail);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        ProductEntity entity = new ProductEntity();
        entity.setProductDetails(data);
        entity.setPageNum(pageNum);
        entity.setTotalPage(total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        return entity;
    }

    private static String getValueFromCell(Cell cell){
        if (cell == null){
            return "";
        }

        switch (cell.getCellType()){
            case STRING: return cell.getStringCellValue();
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return String.valueOf(cell.getArrayFormulaRange());
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());
            default: return "";
        }
    }
}
