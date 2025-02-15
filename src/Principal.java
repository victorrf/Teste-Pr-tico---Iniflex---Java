import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 08), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 09, 02), new BigDecimal("2799.93"), "Gerente"));

        System.out.println("Todos os funcionários:");
        funcionarios.forEach(System.out::println);
        
        
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
