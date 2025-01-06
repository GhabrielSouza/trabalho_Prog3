package infraestrutura;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    private Util(){}

    public static String obterDataAtual() {
		return converterDataString(LocalDate.now(), "dd/MM/yyyy");
	}

	public static String somarDiasData(String stringData, int dias) {
        LocalDate data = converterStringData(stringData, "dd/MM/yyyy");
        LocalDate dataFinal = data.plusDays(dias);
        return converterDataString(dataFinal, "dd/MM/yyyy");
    }

	public static LocalDate converterStringData(String stringData, String formato) {
		DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern(formato);
		LocalDate data = LocalDate.parse(stringData, formatadorData);
		return (data);
	}

	public static String converterDataString(LocalDate data, String formato) {
		return (data.format(DateTimeFormatter.ofPattern(formato)));
	}
}
