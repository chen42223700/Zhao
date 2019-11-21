package entity;

import java.util.List;

public class ProductEntity {

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 详细数据
     */
    private List<ProductDetail> productDetails;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }
}
