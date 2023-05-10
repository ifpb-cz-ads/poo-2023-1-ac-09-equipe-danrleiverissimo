public abstract class Conta {

    private int numero;
    private Cliente titular;
    protected double saldo;

    public Conta(int numero, Cliente titular, double saldo){
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public Conta(int numero, Cliente titular){
        this.numero = numero;
        this.titular = titular;
        saldo = 0;
    }

    public Conta(int numero, String nomeTitular, String cpfTitular){
        this.numero = numero;
        this.titular = new Cliente(nomeTitular, cpfTitular);
        saldo = 0;
    }

    public Cliente getTitular(){
        return titular;
    }

    public void setTitular (Cliente titular) {
        this.titular = titular;
    }

    public void depositar(double valor){
        this.saldo = this.getSaldo() + valor;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public int getNumero() {
        return numero;
    }
        
    public abstract boolean sacar (double valor);
}