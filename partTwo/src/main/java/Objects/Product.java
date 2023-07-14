package Objects;

public class Product {

    private String productid;
    private String name;
    private String imageFile;
    private Double price;

    public Product(String productid, String name, String imageFile, Double price){
        this.productid = productid;
        this.name = name;
        this.imageFile = imageFile;
        this.price = price;
    }

    public String getProductId() {
        return productid;
    }

    public String getName() {
        return name;
    }

    public String getImageFile() {
        return imageFile;
    }

    public Double getPrice() {
        return price;
    }
}
