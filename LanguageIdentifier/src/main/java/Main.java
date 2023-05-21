import java.util.Scanner;

public class Main {
    private static String text = "Karl Ludwig Schleich (ur. 19 lipca 1859 w Szczecinie, zm. 7 marca 1922 w Bad Saarow) – niemiecki chirurg, malarz, poeta. Od 1899 roku był profesorem uniwersytetu w Berlinie, a rok później został kierownikiem oddziału chirurgii w Groß-Lichterfelde. Jako pierwszy zastosował znieczulenie miejscowe w chirurgii ogólnej. Zalecał w tym celu stosowanie narkotyku – kokainy. W 1892 roku w Berlinie przedstawił wyniki swoich badań, nawołując do stosowania znieczulenia nasiękowego. Spotkał się z oburzeniem chirurgów, nazywając przeprowadzanie operacji pod całkowitą narkozą \"\"zbrodnią\"\". Mimo to opisał swoją metodę w książce Bezbolesne operacje, znieczulenie miejscowe płynami obojętnymi w 1894 roku. Na skutek dużej toksyczności metodę zaczęto stosować dopiero po otrzymaniu w 1905 roku syntetycznej nowokokainy. Był autorem esejów na temat śmierci i histerii. Schleich uznawany jest za pierwszego naukowca, który wskazał na rolę gleju w funkcji mózgu. W swoich wspomnieniach pisał, że teoria przyszła mu do głowy w trakcie wydawanego w swoim salonie wieczornego przyjęcia „około roku 1890”, gdy „genialny pianista” Stanisław Przybyszewski, przyjaciel Schleicha, grał na fortepianie.\"";
    private static String text1 = "This is english text";
    public static void main(String[] args) {
        String train = "C:\\git_send\\NAI(Artificial intelegence)\\LanguageIdentifier\\src\\main\\resources\\lang.test (1).csv";
        String test = "C:\\git_send\\NAI(Artificial intelegence)\\LanguageIdentifier\\src\\main\\resources\\lang.train (1).csv";
        DataReader dataReader = new DataReader();
        Algorithm algorithm = new Algorithm(train, test);
        algorithm.train();
        algorithm.test(dataReader.getAllEntitiesFromFile(test));
        algorithm.testWithText(text1);
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Input text to describe the language");
            System.out.println("-----------------------------------");
            System.out.println("\n");
            String c = scanner.nextLine();
            algorithm.testWithText(c);
        }
    }



}
