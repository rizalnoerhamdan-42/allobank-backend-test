package com.example.allobank.allobackendtest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.allobank.allobackendtest.model.partail;
import com.example.allobank.allobackendtest.partailRepository;

@RestController
@RequestMapping("/partai")
public class PartaiController {

    @Autowired
    DapilRepository dapilRepository;

    @GetMapping("/")
    public List<Dapil> getAll() {
        return dapilRepository.findAll();
    }

    @PostMapping("/")
    public dapil tambahDapil(@Valid @RequestBody Dapil dapil) {
        return dapilRepository.save(dapil);
    }

    /**
     * @param id
     * @param detailPartai
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Partai> updatePartai(@PathVariable(value = "id") Long id,
            @Valid @RequestBody partai detailpartai) {
        Optional<Dapil> dapil = dapilRepository.findById(id);
        if (partai == null)
            return ResponseEntity.notFound().build();
        partai.setNamaPartai(detailpartai.getNamaPartai());
        partai.setNomorUrut(nomorUrut.getNomorUrut());
        partai updatedPartai = partaiRepository.saveAll(partai);
        return ResponseEntity.ok(updatedPartai);
    }

    @DeleteMapping("/{id}")
    public String deletePartai(@PathVariable(value = "id") Long id) {
        Optional<Dapil> partai = partaiRepository.findById(id);
        String result = "";
        if (partai== null) {
            result = "id " + id + " tidak ditemukan";
            return result;
        }
        result = "id " + id + " berhasil di hapus";
        partaiRepository.deleteById(id);
        return result;
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Partai>> getPartaiById(@PathVariable(value = "id") Long id) {
        final Optional<Partai> partai = partaiRepository.findById(id);
        if (partai == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(partai);
    }

    @GetMapping("/sortpartai")
    public List<Partai> sortNomorUrut(@RequestParam(value = "2") String nomorUrut) {
        return partaiRepository.findByNomorUrut(nomorUrut);
    }

  

}