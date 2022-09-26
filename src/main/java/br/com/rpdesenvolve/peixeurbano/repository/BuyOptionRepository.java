package br.com.rpdesenvolve.peixeurbano.repository;

import br.com.rpdesenvolve.peixeurbano.modelo.BuyOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyOptionRepository extends JpaRepository<BuyOption, Long> {
    Page<BuyOption> findByTitle(String title, Pageable pagination);
}
