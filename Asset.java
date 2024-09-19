public interface Asset {

    String getCode();
    String getNameAsset();
    double getPriceAsset();
    String typeAsset();
}

//Ценная бумага
class Securities implements Asset {

    private String code;
    private String name;
    private double price;

    public Securities(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getNameAsset() {
        return this.name;
    }

    @Override
    public double getPriceAsset() {
        return this.price;
    }

    @Override
    public String typeAsset() {
        return "Ценная бумага";
    }
}

//Товар
class Product implements Asset {

    private String code;
    private String name;
    private double price;

    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getNameAsset() {
        return this.name;
    }

    @Override
    public double getPriceAsset() {
        return this.price;
    }

    @Override
    public String typeAsset() {
        return "Товар";
    }
}

//Валюта
class Currency implements Asset {

    private String code;
    private String name;
    private double price;

    public Currency(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getNameAsset() {
        return this.name;
    }

    @Override
    public double getPriceAsset() {
        return this.price;
    }

    @Override
    public String typeAsset() {
        return "Валюта";
    }
}
