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

import com.example.allobank.allobackendtest.model.caleg;
import com.example.allobank.allobackendtest.calegRepository;

@RestController
@RequestMapping("/caleg")
public class CalegController {

    @Autowired
    CalegRepository calegRepository;

    @GetMapping("/")
    public List<Caleg> getAll() {
        return calegRepository.findAll();
    }

    @PostMapping("/")
    public caleg tambahCaleg(@Valid @RequestBody Caleg caleg) {
        return calegRepository.save(caleg);
    }

    /**
     * @param id
     * @param detailcaleg
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Caleg> updateCaleg(@PathVariable(value = "id") Long id,
            @Valid @RequestBody caleg detailcaleg) {
        Optional<Caleg> caleg = calegRepository.findById(id);
        if (caleg == null)
            return ResponseEntity.notFound().build();
        caleg.setNamaDapil(detailcaleg.getNamaDapil());
        caleg.setPartai(detailpartai.getPartai());
        caleg.setNomorUrut(detailcaleg.getNomorUrut());
        caleg.setNama(nama.getNama());
		caleg.setJenisKelamin(jenisKelamin.getJenisKelamin());
        caleg updatedCaleg = calegRepository.saveAll(caleg);
        return ResponseEntity.ok(updatedCaleg);
    }

    @DeleteMapping("/{id}")
    public String deleteCaleg(@PathVariable(value = "id") Long id) {
        Optional<Caleg> caleg = calegRepository.findById(id);
        String result = "";
        if (caleg == null) {
            result = "id " + id + " tidak ditemukan";
            return result;
        }
        result = "id " + id + " berhasil di hapus";
        calegRepository.deleteById(id);
        return result;
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Caleg>> getalegById(@PathVariable(value = "id") Long id) {
        final Optional<Caleg> caleg = calegRepository.findById(id);
        if (caleg == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(caleg);
    }

    @GetMapping("/sortcaleg")
    public List<caleg> sortcaleg(@RequestParam(value = "title") String titleBook) {
        return calegRepository.findByTitleBook(titleBook);
    }

  

}