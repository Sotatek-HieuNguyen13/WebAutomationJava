package model;

public class Product {

    private String webName;
    private String prouductName;
    private double productPrice;
    private String productLink;

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getProuductName() {
        return prouductName;
    }

    public void setProuductName(String prouductName) {
        this.prouductName = prouductName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public void productInfor() {
        System.out.println("Web name: " + getWebName());
        System.out.println("Name: " + getProuductName());
        System.out.println("Price: " + Math.ceil(getProductPrice() * 100) / 100 + "$");
        System.out.println("Link: " + getProductLink());
        System.out.println("*************");
    }
}
