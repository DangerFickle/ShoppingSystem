package domain;

/**
 * @ClassName ProductInformation
 * @Description TODO存储商品的信息
 * @Author DengChao
 * @CreatTime 2022/1/21 15:43
 * @Vertion 1.0
 */
public class ProductInformation {
    private String productName;//商品名称
    private double price;//商品价格
    private int productNum;//商品编号

    public ProductInformation(String productName, double price, int productNum) {
        this.productName = productName;
        this.price = price;
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }
}
