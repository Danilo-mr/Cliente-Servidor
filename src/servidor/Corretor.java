package servidor;
//1
public class Corretor {
    private int acertos;
    private int erros;
    private char[][] GABARITO = {
            {'V', 'F', 'F', 'V'},
            {'F', 'V', 'F', 'V'},
            {'V', 'F', 'F', 'V'},
            {'V', 'V', 'V', 'V'},
            {'F', 'F', 'F', 'V'},
            {'V', 'F', 'V', 'V'},
            {'F', 'V', 'V', 'V'},
            {'F', 'F', 'F', 'V'}
    };
    public void corrigir(String resposta){

        int numeroQuestao = Integer.parseInt(resposta.substring(0, 1));
        char[] respostaEstudante = new char[4];
        respostaEstudante[0] = resposta.charAt(2);
        respostaEstudante[1] = resposta.charAt(4);
        respostaEstudante[2] = resposta.charAt(6);
        respostaEstudante[3] = resposta.charAt(8);
        for(int i=0; i<4; i++){
            if(GABARITO[numeroQuestao-1][i] == respostaEstudante[i]){
                this.acertos++;
            }else{
                this.erros++;
            }
        }
    }
    public String getResultadoEstudante(){
        return "TOTAL DE ACERTOS: " + this.acertos + "\nTOTAL DE ERROS: " + this.erros;
    }
}
