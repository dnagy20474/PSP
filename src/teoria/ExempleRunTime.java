package teoria;

public class ExempleRunTime {
    static void main(String[] args) throws Exception, InterruptedException {

//        Runtime run = Runtime.getRuntime();
//        Process p = run.exec("code /c start bash /k echo Hola");

        ProcessBuilder pd = new  ProcessBuilder("notepad");
        Process p = pd.start();
        int codeRetorn = p.waitFor();

        System.out.println("EL codi de retorn es " +  codeRetorn);

    }
}