public class MaquinaBanho {

    // ===== CONSTANTES (regras do enunciado) =====
    private static final int RESERVATORIO_AGUA = 30;
    private static final int RESERVATORIO_SHAMPOO = 10;
    private static final int ABASTECIMENTO = 2;          // litros por abastecimento
    private static final int BANHO_AGUA = 10;
    private static final int BANHO_SHAMPOO = 2;
    private static final int LIMPEZA_AGUA = 3;
    private static final int LIMPEZA_SHAMPOO = 1;

    // ===== ESTADO DA MÁQUINA =====
    private int agua = 0;
    private int shampoo = 0;
    private boolean petNaMaquina = false;
    private boolean petLimpo = false;     // o pet que está dentro já tomou banho?
    private boolean maquinaSuja = false;  // precisa limpar antes de entrar outro pet?

    // ===== GETTERS =====
    public int getAgua() { return agua; }
    public int getShampoo() { return shampoo; }
    public boolean getPetNaMaquina() { return petNaMaquina; }
    public boolean isPetLimpo() { return petLimpo; }
    public boolean isMaquinaSuja() { return maquinaSuja; }

    // ===== ABASTECER =====
    public boolean abastecerAgua() {
        if (agua + ABASTECIMENTO > RESERVATORIO_AGUA) {
            return false;
        }
        agua += ABASTECIMENTO;
        return true;
    }

    public boolean abastecerShampoo() {
        if (shampoo + ABASTECIMENTO > RESERVATORIO_SHAMPOO) {
            return false;
        }
        shampoo += ABASTECIMENTO;
        return true;
    }

    // ===== PET =====
    public boolean colocarPet() {
        if (petNaMaquina || maquinaSuja) { // já tem pet OU máquina precisa de limpeza
            return false;
        }
        petNaMaquina = true;
        petLimpo = false;                  // pet novo entra sujo
        return true;
    }

    public boolean retirarPet() {
        if (!petNaMaquina) {
            return false;
        }
        if (!petLimpo) {
            maquinaSuja = true;            // saiu sem banho: suja a máquina
        }
        petNaMaquina = false;
        petLimpo = false;
        return true;
    }

    // ===== BANHO =====
    public boolean darBanho() {
        if (!petNaMaquina || petLimpo) {   // sem pet, ou pet já tomou banho
            return false;
        }
        if (agua < BANHO_AGUA || shampoo < BANHO_SHAMPOO) { // || : falta qualquer um dos dois
            return false;
        }
        agua -= BANHO_AGUA;
        shampoo -= BANHO_SHAMPOO;
        petLimpo = true;
        return true;
    }

    // ===== LIMPEZA =====
    public boolean limparMaquina() {
        if (!maquinaSuja) {         // não precisa limpar
            return false;
        }
        if (agua < LIMPEZA_AGUA || shampoo < LIMPEZA_SHAMPOO) { // sem recurso
            return false;
        }
        agua -= LIMPEZA_AGUA;
        shampoo -= LIMPEZA_SHAMPOO;
        maquinaSuja = false;
        return true;
    }
}