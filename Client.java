import java.util.ArrayList;
import java.util.List;

public class Client {

    private final String fullName;
    private double balance;
    //Список активов
    List<Asset> assets;
    //Список инвесторов
    List<Client> clients;
    //Список заявок
    static List<Order> orders;

    public Client(String fullName, double balance) {
        this.fullName = fullName;
        this.balance = balance;
        clients = new ArrayList<>();
        assets = new ArrayList<>();
        orders = new ArrayList<>();
    }

    //Добавление актива
    public void addAssets(Asset asset, int amount) {
        for (int i = 0; i < amount; i++) {
            assets.add(asset);
        }
    }

    //Информация об инвесторах
    public void getInfoClients() {
        System.out.println("\nИнформция об: " + fullName + ", Баланс: " + balance);
    }

    //Информация об активах и пользователях
    public void getInfoAssets() {
        System.out.println("\nАктивы инвестора: " + getName());
        for (Asset asset : assets) {
            System.out.println("Code: " + asset.getCode() + " Name: " + asset.getNameAsset() + " Price: " + asset.getPriceAsset()
                    + " Type: " + asset.typeAsset());
        }
    }

    public String getName() {
        return fullName;
    }

    public double getBalance() {
        return balance;
    }

    //Отправитель актива
    public void operationDepositing(double price, Asset asset, int amount) throws checkAssetAmountException {
        int amountAssetSender = 0;
        for (Asset asset1 : assets) {
            if (asset.getNameAsset() == asset1.getNameAsset()) {
                amountAssetSender++;
            }
        }
        if (amountAssetSender >= amount) {
            for (int i = 0; i < amount; i++) {
                assets.remove(asset);
            }
            balance += price;
        } else {
            throw new checkAssetAmountException("Недостаточно актива");
        }
    }

    //Получатель актива
    public void operationWriteOffs(double price, Asset asset, int amount) throws InsufficientFundsException, checkAssetAmountException {
        if (balance >= price) {
            balance -= price;
            for (int i = 0; i < amount; i++) {
                assets.add(asset);
            }
        } else {
            throw new InsufficientFundsException("Не хватает средств");
        }
    }
}







