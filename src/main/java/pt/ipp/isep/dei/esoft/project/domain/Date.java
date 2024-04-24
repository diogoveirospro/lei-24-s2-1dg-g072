package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Calendar;

public class Date implements Comparable<Date> {

    /**
     * Date year
     */
    private int year;

    /**
     * Date month
     */
    private int month;

    /**
     * Date day
     */
    private int day;

    /**
     * Default year
     */
    private static final int DEFAULT_YEAR = 1;

    /**
     * Default month
     */
    private static final int DEFAULT_MONTH = 1;

    /**
     * Default day.
     */
    private static final int DEFAULT_DAY = 1;

    /**
     * Number of days of the month
     */
    private static int[] monthDay = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30,
            31, 30, 31};

    /**
     * Nomes dos meses do ano.
     */
    private static String[] monthName = {"Invalid", "January", "February",
            "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December"};

    /**
     * Creates an instance of Date receiving the year, month and day.
     *
     * @param year date year.
     * @param month date month.
     * @param day date day.
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Constrói uma instância de Data com a data por omissão.
     */
    public Date() {
        year = DEFAULT_YEAR;
        month = DEFAULT_MONTH;
        day = DEFAULT_DAY;
    }

    /**
     * Constrói uma instância de Data com as mesmas caraterísticas da data
     * recebida por parâmetro.
     *
     * @param outraData a data com as características a copiar.
     */
    public Date(Date outraData) {
        year = outraData.year;
        month = outraData.month;
        day = outraData.day;
    }

    /**
     * Devolve o ano da data.
     *
     * @return ano da data
     */
    public int getYear() {
        return year;
    }

    /**
     * Devolve o mês da data.
     *
     * @return mês da data.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Devolve o dia da data.
     *
     * @return dia da data.
     */
    public int getDay() {
        return day;
    }

    /**
     * Modifica o ano, o mês e o dia da data.
     *
     * @param ano o novo ano da data.
     * @param mes o novo mês da data.
     * @param dia o novo dia da data.
     */
    public void setDate(int ano, int mes, int dia) {
        this.year = ano;
        this.month = mes;
        this.day = dia;
    }

    /**
     * Devolve a descrição textual da data no formato: diaDaSemana, dia de mês
     * de ano.
     *
     * @return caraterísticas da data.
     */
    @Override
    public String toString() {
        return String.format("%s, %d de %s de %d", diaDaSemana(), day,
                monthName[month], year);
    }

    /**
     * Devolve a data no formato:%04d/%02d/%02d.
     *
     * @return caraterísticas da data.
     */
    public String toYearMonthDayString() {
        return String.format("%04d/%02d/%02d", year, month, day);
    }

    /**
     * Compara a data com o objeto recebido.
     *
     * @param outroObjeto o objeto a comparar com a data.
     * @return true se o objeto recebido representar uma data equivalente à
     * data. Caso contrário, retorna false.
     */
    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto) {
            return true;
        }
        if (outroObjeto == null || getClass() != outroObjeto.getClass()) {
            return false;
        }
        Date outraData = (Date) outroObjeto;
        return year == outraData.year && month == outraData.month
                && day == outraData.day;
    }

    /**
     * Compara a data com a outra data recebida por parâmetro.
     *
     * @param outraData a data a ser comparada.
     * @return o valor 0 se a outraData recebida é igual à data; o valor -1 se
     * a outraData for posterior à data; o valor 1 se a outraData for
     * anterior à data.
     */
    @Override
    public int compareTo(Date outraData) {
        return (outraData.isMaior(this)) ? -1 : (isMaior(outraData)) ? 1 : 0;
    }

    /**
     * Devolve true se a data for maior do que a data recebida por parâmetro. Se
     * a data for menor ou igual à data recebida por parâmetro, devolve false.
     *
     * @param otherDate a outra data com a qual se compara a data.
     * @return true se a data for maior do que a data recebida por parâmetro,
     * caso contrário devolve false.
     */
    public boolean isMaior(Date otherDate) {
        int totalDays = countDays();
        int totalDays1 = otherDate.countDays();

        return totalDays > totalDays1;
    }

    /**
     * Devolve a diferença em número de dias entre a data e a data recebida por
     * parâmetro.
     *
     * @param outraData a outra data com a qual se compara a data para calcular
     *                  a diferença do número de dias.
     * @return diferença em número de dias entre a data e a data recebida por
     * parâmetro.
     */
    public int diference(Date outraData) {
        int totalDias = countDays();
        int totalDias1 = outraData.countDays();

        return Math.abs(totalDias - totalDias1);
    }

    /**
     * Devolve true se o ano passado por parâmetro for bissexto. Se o ano
     * passado por parâmetro não for bissexto, devolve false.
     *
     * @param ano o ano a validar.
     * @return true se o ano passado por parâmetro for bissexto, caso contrário
     * devolve false.
     */
    public static boolean isAnoBissexto(int ano) {
        return ano % 4 == 0 && ano % 100 != 0 || ano % 400 == 0;
    }

    /**
     * Devolve a data atual do sistema.
     *
     * @return a data atual do sistema.
     */
    public static Date currentDate() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;    // january is represented by 0
        int day = today.get(Calendar.DAY_OF_MONTH);
        return new Date(year, month, day);
    }

    /**
     * Devolve o número de dias desde o dia 1/1/1 até à data.
     *
     * @return número de dias desde o dia 1/1/1 até à data.
     */
    private int countDays() {
        int totalDias = 0;

        for (int i = 1; i < year; i++) {
            totalDias += isAnoBissexto(i) ? 366 : 365;
        }
        for (int i = 1; i < month; i++) {
            totalDias += monthDay[i];
        }
        totalDias += (isAnoBissexto(year) && month > 2) ? 1 : 0;
        totalDias += day;

        return totalDias;
    }
}

