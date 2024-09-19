import java.util.ArrayList;
import java.util.List;

public class Order {

    Client client;
    Asset asset;
    int amount;
    double price;
    boolean isBuy;
    OrderStatus status;
    //Список заявок
    static List<Order> orders;

    //Статус заявки
    public enum OrderStatus {
        OPEN, EXECUTED, CLOSED;
    }

    //Установить новый статус
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    //Конструктор заявки
    public Order(Client client, Asset asset, int amount, double price, boolean isBuy) {
        this.client = client;
        this.asset = asset;
        this.amount = amount;
        this.price = price;
        this.isBuy = isBuy;
        this.status = OrderStatus.OPEN;
        orders = new ArrayList<>();
    }

    public Client getClient() {
        return client;
    }

    public double getPrice() {
        return price;
    }

    public Asset getAsset() {
        return asset;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public OrderStatus getStatus() {
        return status;
    }

    //Создание заявки
    public static void placeOrder(Order order) {
        orders.add(order);
        //order.getClient().placeOrder(order);
        System.out.println("\nЗаявка размещена: " + order.getClient().getName() + " хочет " + (order.isBuy ? " купить " : " продать ") +
                order.getAsset().getNameAsset() + " за " + order.getPrice() + " в кол-ве " + order.getAmount());
    }

    //Отмена заявки
    public static void cancelOrder(Order order) {
        if (order.getStatus() == Order.OrderStatus.OPEN) {
            //order.getClient().cancelOrder(order);
            orders.remove(order);
            order.setStatus(Order.OrderStatus.CLOSED);
            System.out.println("\nЗаявка отменена: " + order.getClient().getName() + " хочет " + (order.isBuy ? " купить " : " продать ") +
                    order.getAsset().getNameAsset() + " за " + order.getPrice() + " в кол-ве " + order.getAmount());
        }
    }

    //Вывод заявок
    public void getOrders() {
        System.out.println(orders);
    }

    //Выполнение заявок
    public static void executeOrder(Order order) throws InsufficientFundsException, checkAssetAmountException {
        Client orderSender = order.getClient();
        Asset asset = order.getAsset();
        int amount = order.getAmount();
        double price = order.getPrice();
        boolean isBuy = order.isBuy();

        //Подходящая встречная заявка
        Order orderCounter = findOrder(asset, amount, price, isBuy);
        if (orderCounter == null) {
            System.out.println("\nНет подходящих заявок");
        } else if (!isBuy) {
            Client orderSender2 = orderCounter.getClient();
            Deposit.transferAssets(orderSender, orderSender2, asset, amount);
        } else {
            Client orderSender2 = orderCounter.getClient();
            Deposit.transferAssets(orderSender2, orderSender, asset, amount);
        }
        order.setStatus(OrderStatus.EXECUTED);
    }

    //Поиск подходящей заявки
    public static Order findOrder(Asset asset, double amount, double price, boolean isBuy) {
        List<Order> orderList = orders;
        for (Order openOrder: orderList ) {
            if ((openOrder.getAsset().equals(asset))
                    && (openOrder.getPrice() == price)
                    && (openOrder.getAmount() >= amount)
                    && (openOrder.isBuy() != isBuy)) {
                return openOrder;
            }
        }
        return null;
    }
}
