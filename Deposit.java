public class Deposit {

    //Перевод активов
    public static void transferAssets(Client sender, Client recipient, Asset asset, int amount) throws InsufficientFundsException, checkAssetAmountException {
        final double commission = 0.0001; //0.01%
        double priceRecipient = amount * asset.getPriceAsset();
        double priceSender = priceRecipient - (priceRecipient * commission);

        //Выполнение операции
        sender.operationDepositing(priceSender, asset, amount);
        recipient.operationWriteOffs(priceRecipient, asset, amount);
        System.out.println("\nТранзакция выполнена: " + sender.getName() + " перевел " + " " + asset.getCode() + " "
                + asset.getNameAsset() + " в кол-ве " + amount + " шт. на счет клиента: " + recipient.getName()
                + " за " + priceRecipient);
    }
}

