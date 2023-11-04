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

import com.example.allobank.allobackendtest.model.dapil;
import com.example.allobank.allobackendtest.dapilRepository;

@RestController
@RequestMapping("/dapil")
public class DapilController {

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
     * @param detailDapil
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Dapil> updateDapil(@PathVariable(value = "id") Long id,
            @Valid @RequestBody dapil detaildapil) {
        Optional<Dapil> dapil = dapilRepository.findById(id);
        if (dapil == null)
            return ResponseEntity.notFound().build();
        dapil.setNamaDapil(detaildapil.getNamaDapil());
        dapil.setProvinsi(detaildapil.getProvinsi());
        dapil.setWilayahDapilList(detaildapil.getWilayahDapilList());
        dapil.setJumlahKursi(jumlahKursi.getNamaPeminjam());
        dapil updatedDapil = dapilRepository.saveAll(dapil);
        return ResponseEntity.ok(updatedDapil);
    }

    @DeleteMapping("/{id}")
    public String deleteDapil(@PathVariable(value = "id") Long id) {
        Optional<Dapil> dapil = dapilRepository.findById(id);
        String result = "";
        if (dapil== null) {
            result = "id " + id + " tidak ditemukan";
            return result;
        }
        result = "id " + id + " berhasil di hapus";
        dapilRepository.deleteById(id);
        return result;
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Dapil>> getDapilById(@PathVariable(value = "id") Long id) {
        final Optional<Dapil> dapil = dapilRepository.findById(id);
        if (dapil == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(dapil);
    }

    @GetMapping("/sortdapil")
    public List<Dapil> sortNamaDapil(@RequestParam(value = "namaDapil") String namaDapil) {
        return dapilRepository.findByNamaDapil(nameDapil);
    }

  

}