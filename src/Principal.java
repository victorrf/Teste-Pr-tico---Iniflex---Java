import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(1990, 10, 18), new BigDecimal("2000.00"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1992, 5, 12), new BigDecimal("3000.00"), "Gerente"));
        funcionarios.add(new Funcionario("Ana", LocalDate.of(1985, 12, 25), new BigDecimal("4000.00"), "Diretor"));
        
        funcionarios.removeIf(f -> f.nome.equals("João"));
        
        System.out.println("Funcionários após remoção de João:");
        funcionarios.forEach(System.out::println);
        
        funcionarios.forEach(f -> f.aumentarSalario(new BigDecimal("0.10")));
        
        System.out.println("\nFuncionários com aumento de 10%:");
        funcionarios.forEach(System.out::println);
        
        Map<String, List<Funcionario>> agrupadosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        
        System.out.println("\nFuncionários agrupados por função:");
        agrupadosPorFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ": " + lista);
        });
        
        System.out.println("\nFuncionários que fazem aniversário nos meses 10 e 12:");
        funcionarios.stream()
                .filter(f -> f.dataNascimento.getMonthValue() == 10 || f.dataNascimento.getMonthValue() == 12)
                .forEach(System.out::println);
        
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparingInt(Funcionario::getIdade));
        System.out.println("\nFuncionário mais velho: " + maisVelho.nome + " - " + maisVelho.getIdade() + " anos");
        
        funcionarios.sort(Comparator.comparing(f -> f.nome));
        System.out.println("\nFuncionários ordenados por nome:");
        funcionarios.forEach(System.out::println);
        
        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: R$ " + String.format("%,.2f", totalSalarios).replace(',', 'X').replace('.', ',').replace('X', '.'));
        
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.nome + " - " + qtdSalariosMinimos + " salários mínimos");
        });
    }
}