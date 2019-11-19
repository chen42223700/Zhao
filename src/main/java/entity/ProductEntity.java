package entity;

import java.io.Serializable;

public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 3512222124971218129L;
    /**
     * 生产基地
     */
    private String base;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 零件号
     */
    private String componentNum;

    /**
     * 宽度
     */
    private String width;

    /**
     * 直径
     */
    private String diameter;

    /**
     * 偏距
     */
    private String Offset;

    /**
     * 中心孔直径
     */
    private String centre;

    /**
     * 螺孔数
     */
    private String screw;

    /**
     * 分布圆
     */
    private String distribution;

    /**
     * 单轮载荷
     */
    private String singleWheelLoad;

    /**
     * 轮辋材料
     */
    private String riMaterial;

    /**
     * 轮辋厚度
     */
    private String rimthickness;

    /**
     * 轮辐材料
     */
    private String spokeMaterial;

    /**
     * 轮辐厚度
     */
    private String spokeThicknes;

    /**
     * 弯曲疲劳试验
     */
    private String bendingFatigueTest;

    /**
     * 弯曲试验结果
     */
    private String bendingTestResult;

    /**
     * 径向疲劳试验
     */
    private String radialFatigueTest;

    /**
     * 径向试验结果
     */
    private String radialTestResult;

    /**
     * 轮辐模具
     */
    private String spokeMold;

    /**
     * 轮辋模具
     */
    private String riMould;

    /**
     * 压配模具
     */
    private String pressingMold;

    /**
     * 产品数模
     */
    private String productDigitalModel;

    /**
     * 产品图纸
     */
    private String productdrawings;

    /**
     * 行号
     */
    private int rowNum;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getComponentNum() {
        return componentNum;
    }

    public void setComponentNum(String componentNum) {
        this.componentNum = componentNum;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getOffset() {
        return Offset;
    }

    public void setOffset(String offset) {
        Offset = offset;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getScrew() {
        return screw;
    }

    public void setScrew(String screw) {
        this.screw = screw;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getSingleWheelLoad() {
        return singleWheelLoad;
    }

    public void setSingleWheelLoad(String singleWheelLoad) {
        this.singleWheelLoad = singleWheelLoad;
    }

    public String getRiMaterial() {
        return riMaterial;
    }

    public void setRiMaterial(String riMaterial) {
        this.riMaterial = riMaterial;
    }

    public String getRimthickness() {
        return rimthickness;
    }

    public void setRimthickness(String rimthickness) {
        this.rimthickness = rimthickness;
    }

    public String getSpokeMaterial() {
        return spokeMaterial;
    }

    public void setSpokeMaterial(String spokeMaterial) {
        this.spokeMaterial = spokeMaterial;
    }

    public String getSpokeThicknes() {
        return spokeThicknes;
    }

    public void setSpokeThicknes(String spokeThicknes) {
        this.spokeThicknes = spokeThicknes;
    }

    public String getBendingFatigueTest() {
        return bendingFatigueTest;
    }

    public void setBendingFatigueTest(String bendingFatigueTest) {
        this.bendingFatigueTest = bendingFatigueTest;
    }

    public String getBendingTestResult() {
        return bendingTestResult;
    }

    public void setBendingTestResult(String bendingTestResult) {
        this.bendingTestResult = bendingTestResult;
    }

    public String getRadialFatigueTest() {
        return radialFatigueTest;
    }

    public void setRadialFatigueTest(String radialFatigueTest) {
        this.radialFatigueTest = radialFatigueTest;
    }

    public String getRadialTestResult() {
        return radialTestResult;
    }

    public void setRadialTestResult(String radialTestResult) {
        this.radialTestResult = radialTestResult;
    }

    public String getSpokeMold() {
        return spokeMold;
    }

    public void setSpokeMold(String spokeMold) {
        this.spokeMold = spokeMold;
    }

    public String getRiMould() {
        return riMould;
    }

    public void setRiMould(String riMould) {
        this.riMould = riMould;
    }

    public String getPressingMold() {
        return pressingMold;
    }

    public void setPressingMold(String pressingMold) {
        this.pressingMold = pressingMold;
    }

    public String getProductDigitalModel() {
        return productDigitalModel;
    }

    public void setProductDigitalModel(String productDigitalModel) {
        this.productDigitalModel = productDigitalModel;
    }

    public String getProductdrawings() {
        return productdrawings;
    }

    public void setProductdrawings(String productdrawings) {
        this.productdrawings = productdrawings;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }
}
