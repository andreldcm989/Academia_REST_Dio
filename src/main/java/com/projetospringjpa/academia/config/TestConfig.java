package com.projetospringjpa.academia.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import com.projetospringjpa.academia.models.Aluno;
import com.projetospringjpa.academia.models.Avaliacao;
import com.projetospringjpa.academia.models.Instrutor;
import com.projetospringjpa.academia.models.Modalidade;
import com.projetospringjpa.academia.models.Turmas;
import com.projetospringjpa.academia.repositories.AlunoRepository;
import com.projetospringjpa.academia.repositories.AvaliacaoRepository;
import com.projetospringjpa.academia.repositories.InstrutorRepository;
import com.projetospringjpa.academia.repositories.ModalidadeRepository;
import com.projetospringjpa.academia.repositories.TurmasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private InstrutorRepository instrutorRepository;
    @Autowired
    private ModalidadeRepository modalidadeRepository;
    @Autowired
    private TurmasRepository turmasRepository;

    @Override
    public void run(String... args) throws Exception {
        
        Aluno a1 = new Aluno(null, "José da Silva", "00123456789", "Centro", LocalDate.of(1990, 2, 28), null, null, null);
        Aluno a2 = new Aluno(null, "Maria do Carmo","09877890023", "Pacaembu", LocalDate.of(1988, 10, 15), null, null, null);
        Aluno a3 = new Aluno(null, "Marcos Paulo", "44567877900", "Novo Mundo", LocalDate.of(1995, 12, 01), null, null, null);
        Aluno a4 = new Aluno(null, "Sonia Abreu", "67887654434", "Centro", LocalDate.of(1999, 8, 17), null, null, null);
        
        Avaliacao av1 = new Avaliacao(null, LocalDateTime.now().minusMonths(3), 96.4, 1.81, a1);
        Avaliacao av2 = new Avaliacao(null, LocalDateTime.now(), 78.2, 1.70, a2);
        Avaliacao av3 = new Avaliacao(null, LocalDateTime.now(), 92.1, 1.81, a1);
        Avaliacao av4 = new Avaliacao(null, LocalDateTime.now(), 88.2, 1.81, a3);
        alunoRepository.saveAll(Arrays.asList(a1, a2, a3, a4));
        avaliacaoRepository.saveAll(Arrays.asList(av1, av2, av3, av4));

        Instrutor i1 = new Instrutor(null, "Cristiano Silva", "99176690012", "Bacharel em Educacao Fisica", LocalDateTime.now(), null);
        Instrutor i2 = new Instrutor(null, "Leticia Alves", "66731200934", "Bacharel em Educacao Fisica", LocalDateTime.now(), null);
        Instrutor i3 = new Instrutor(null, "Sandra Lima", "56744598712", "Personal Trainner", LocalDateTime.now(), null);
        Instrutor i4 = new Instrutor(null, "Ana Fonseca", "56789012345", "Bacharel em Educacao Fisica", LocalDateTime.now(), null);
        instrutorRepository.saveAll(Arrays.asList(i1, i2, i3, i4));

        Modalidade m1 = new Modalidade(null, "Musculação", LocalDateTime.now(), null);
        Modalidade m2 = new Modalidade(null, "Natação", LocalDateTime.now(), null);
        Modalidade m3 = new Modalidade(null, "Spinning", LocalDateTime.now(), null);       
        Modalidade m4 = new Modalidade(null, "Cross Fit", LocalDateTime.now(), null);
        Modalidade m5 = new Modalidade(null, "Pilates", LocalDateTime.now(), null);
        modalidadeRepository.saveAll(Arrays.asList(m1, m2, m3, m4, m5));

        Turmas t1 = new Turmas(null, LocalTime.of(8, 0, 0), 60L, m1, i1, null, null);
        Turmas t2 = new Turmas(null, LocalTime.of(10, 0, 0), 50L, m2, null, null, null);
        Turmas t3 = new Turmas(null, LocalTime.of(9, 0, 0), 60L, m1, i2, null, null);
        Turmas t4 = new Turmas(null, LocalTime.of(9, 30, 0), 50L, m4, null, null, null);
        Turmas t5 = new Turmas(null, LocalTime.of(11, 0, 0), 50L, m5, i3, null, null);
        turmasRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));
    }
    
}
