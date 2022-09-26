package br.com.rpdesenvolve.peixeurbano.controller;

import br.com.rpdesenvolve.peixeurbano.controller.dto.DealDTO;
import br.com.rpdesenvolve.peixeurbano.controller.dto.DetailDealDTO;
import br.com.rpdesenvolve.peixeurbano.controller.form.DealForm;
import br.com.rpdesenvolve.peixeurbano.controller.form.UpdateDealForm;
import br.com.rpdesenvolve.peixeurbano.controller.form.UpdateTotalSold;
import br.com.rpdesenvolve.peixeurbano.modelo.Deal;
import br.com.rpdesenvolve.peixeurbano.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deal")
public class DealController {

    @Autowired
    private DealRepository dealRepository;

    @GetMapping
    @Cacheable(value = "dealList")
    public List<DealDTO> list() {
        List<Deal> deals = dealRepository.findAll();
        Date today = new Date();
        deals.removeIf(deal -> (deal.getPublishDate().getDate() > today.getDate()) &&
                                (deal.getEndDate().getDate() < today.getDate()));

        return DealDTO.convert(deals);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "dealList", allEntries = true)
    public ResponseEntity<DealDTO> register(@RequestBody @Valid DealForm dealForm, UriComponentsBuilder uriComponentsBuilder) {
        Deal deal = dealForm.convert();

        URI uri = uriComponentsBuilder.path("/deal/{id}").buildAndExpand(deal.getId()).toUri();
        deal.setUrl(uri.toString());

        dealRepository.save(deal);

        return ResponseEntity.created(uri).body(new DealDTO(deal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailDealDTO> detail(@PathVariable Long id) {
        Optional<Deal> deal = dealRepository.findById(id);
        if (deal.isPresent()) {
            return ResponseEntity.ok(new DetailDealDTO(deal.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "dealList", allEntries = true)
    public ResponseEntity<DetailDealDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateDealForm updateDealForm) {
        Optional<Deal> optional = dealRepository.findById(id);
        if (optional.isPresent()) {
            Deal deal = updateDealForm.update(id, dealRepository);

            return ResponseEntity.ok(new DetailDealDTO(deal));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "dealList", allEntries = true)
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Deal> deal = dealRepository.findById(id);
        if (deal.isPresent()) {
            dealRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/totalSold/{id}")
    @Transactional
    @CacheEvict(value = "dealList", allEntries = true)
    public ResponseEntity<DetailDealDTO> updateTotalSold(@PathVariable Long id, @RequestBody @Valid UpdateTotalSold updateTotalSold) {
        Optional<Deal> optional = dealRepository.findById(id);
        if (optional.isPresent()) {
            Deal deal = updateTotalSold.update(id, dealRepository);

            return ResponseEntity.ok(new DetailDealDTO(deal));
        }

        return ResponseEntity.notFound().build();
    }
}
