public class Main {

    public static void main(String[] args) throws InsufficientFundsException, checkAssetAmountException {
        //Создание инвесторов
        Client client = new Client(" Иванов Петр Иванович", 10000);
        Client client1 = new Client("Сидоренков Алексей Дмитриевич", 100000);

        //Создание активов
        //Ценные бумаги
        Securities stock = new Securities("AAP", "ApaApPro", 4000);

        //Товар
        Product gold = new Product("GLD", "Gold", 5600);

        //Валюта
        Currency usd = new Currency("USD", "US Dollar", 100);

        //Добавляем инвесторам активы
        client.addAssets(stock, 2);
        client.addAssets(usd, 1);
        client1.addAssets(gold, 1);

        client.getInfoAssets();
        client1.getInfoAssets();

        //Перевод актива
        Deposit.transferAssets(client, client1, stock, 2);
        Deposit.transferAssets(client1, client, gold, 1);

        client.getInfoAssets();
        client1.getInfoAssets();

        //Создание заявки (isBuy = false(продажа), isBuy = true(покупка))
        Order order = new Order(client, usd, 1, 100, false);
        Order order1 = new Order(client1, usd, 1, 100, true);
        Order order2 = new Order(client1, usd, 3, 120, true);
        Order order3 = new Order(client, gold, 4, 200, false);

        //Размещение заявки
        Order.placeOrder(order);
        Order.placeOrder(order1);
        Order.placeOrder(order2);
        Order.placeOrder(order3);

        //Выполнение заявки
        Order.executeOrder(order);

        client.getInfoAssets();
        client1.getInfoAssets();

        //Отмена заявки
        Order.cancelOrder(order2);
        Order.cancelOrder(order3);
    }
}