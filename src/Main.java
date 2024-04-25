import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put("USD", 1.0); // 1 USD = 1 USD
        exchangeRates.put("OMR", 0.2646); // 1 USD = 0.2646 OMR
        exchangeRates.put("JOD", 0.708); // 1 USD = 0.708 JOD
        exchangeRates.put("GBP", 0.814); // 1 USD = 0.814 GBP
        exchangeRates.put("GIP", 0.814); // 1 USD = 0.814 GIP
        exchangeRates.put("KYD", 0.833); // 1 USD = 0.833 KYD
        exchangeRates.put("CHF", 0.984); // 1 USD = 0.984 CHF
        exchangeRates.put("EUR", 0.884); // 1 USD = 0.884 EUR
        exchangeRates.put("BRL", 0.198); // 1 USD = 0.198 BRL
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bem-vindo ao Conversor de Moedas!");

            while (true) {
                double amount;

                while (true) {
                    System.out.print("Digite o valor a ser convertido: ");
                    amount = scanner.nextDouble();
                    if (amount > 0) {
                        break;
                    }
                    System.out.println("Entrada inválida. Por favor, digite um valor positivo.");
                }

                String sourceCurrency;
                String targetCurrency;

                while (true) {
                    System.out.print("Digite a moeda de origem (USD, OMR, JOD, GBP, GIP, KYD, CHF, EUR, BRL): ");
                    sourceCurrency = scanner.next().toUpperCase();
                    if (exchangeRates.containsKey(sourceCurrency)) {
                        break;
                    }
                    System.out.println("Código de moeda inválido. Por favor, digite um código de moeda válido.");
                }

                while (true) {
                    System.out.print("Digite a moeda de destino (USD, OMR, JOD, GBP, GIP, KYD, CHF, EUR, BRL): ");
                    targetCurrency = scanner.next().toUpperCase();
                    if (exchangeRates.containsKey(targetCurrency)) {
                        break;
                    }
                    System.out.println("Código de moeda inválido. Por favor, digite um código de moeda válido.");
                }

                double convertedAmount = convert(amount, sourceCurrency, targetCurrency);

                // Formatação melhorada da saída
                System.out.printf("Valor convertido: %,.2f %s\n", convertedAmount, targetCurrency);

                System.out.print("Deseja fazer outra conversão? (S/N): ");
                String response = scanner.next().toUpperCase();
                if (!response.equals("S")) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro. Por favor, tente novamente.");
        }
    }

    private static double convert(double amount, String sourceCurrency, String targetCurrency) {
        double sourceRate = exchangeRates.get(sourceCurrency);
        double targetRate = exchangeRates.get(targetCurrency);

        // Se a moeda de origem for BRL, calculamos a conversão usando o inverso da taxa de câmbio
        if (sourceCurrency.equals("BRL")) {
            return amount / exchangeRates.get(targetCurrency);
        } else {
            return amount * targetRate / sourceRate;
        }
    }
}

