package elementsofprogramminginterviews.arrays;

public class BuyAndSellAStockTwice {
    /**
     * The time complexity of this problem is O(n), because you iterate through the array 3 times, and the space
     * complexity is O(n) as well, due to the extra 2 arrays to store the values.
     */
    public int maxProfit2Sales(int[] stockPrices) {
        // Iterate through the array from back to front, find out the maximum you can earn if you start from that day
        int[] maxProfitCountFromBack = new int[stockPrices.length];
        maxProfitCountFromBack[stockPrices.length - 1] = 0;
        int maximumPriceSeen = stockPrices[stockPrices.length - 1];
        for (int i = stockPrices.length - 2; i >= 0; i--) {
            int stockPrice = stockPrices[i];
            int difference = maximumPriceSeen - stockPrice;
            int maxProfitSeenThusFar = maxProfitCountFromBack[i + 1];
            maxProfitCountFromBack[i] = Math.max(difference, maxProfitSeenThusFar);
            maximumPriceSeen = Math.max(maximumPriceSeen, stockPrice);
        }

        // Iterate through the array from front to back, find out the maximum you can earn if you have to sell by a
        // certain day
        int[] maxProfitCountFromFront = new int[stockPrices.length];
        maxProfitCountFromFront[0] = 0;
        int minimumPriceSeen = stockPrices[0];
        for (int i = 1; i < stockPrices.length; i++) {
            int stockPrice = stockPrices[i];
            int difference = stockPrice - minimumPriceSeen;
            int maxProfitSeenThusFar = maxProfitCountFromFront[i - 1];
            maxProfitCountFromFront[i] = Math.max(difference, maxProfitSeenThusFar);
            minimumPriceSeen = Math.min(minimumPriceSeen, stockPrice);
        }

        int maxProfit = maxProfitCountFromBack[0]; // Maximum profit you can earn if you only do 1 sale
        for (int i = 1; i < stockPrices.length - 1; i++) {
            int profit = maxProfitCountFromFront[i] + maxProfitCountFromBack[i + 1];
            maxProfit = Math.max(profit, maxProfit);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] stockPrices = new int[]{60, 22, 94, 42, 93, 33,  7,  8, 90, 57};
        BuyAndSellAStockTwice buyAndSellAStockTwice = new BuyAndSellAStockTwice();
        System.out.println(buyAndSellAStockTwice.maxProfit2Sales(stockPrices));
    }
}
