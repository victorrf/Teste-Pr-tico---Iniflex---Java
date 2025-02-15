import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void aumentarSalario(BigDecimal percentual) {
        this.salario = this.salario.add(this.salario.multiply(percentual));
    }

    public String getFuncao() {
        return funcao;
    }

    public int getIdade() {
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return nome + " - " + dataNascimento.format(formatter) + " - " +
                "R$ " + String.format("%,.2f", salario).replace(',', 'X').replace('.', ',').replace('X', '.') +
                " - " + funcao;
    }
}
