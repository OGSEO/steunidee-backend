package nl.gelton.steunideebackend.controller;

import lombok.RequiredArgsConstructor;
import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.PoliticalPartyInputDto;
import nl.gelton.steunideebackend.service.interf.PoliticalPartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/political-party")
@RequiredArgsConstructor
public class PoliticalPartyController {

    private final PoliticalPartyService politicalPartyService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<Response> createPoliticalParty(@RequestBody PoliticalPartyInputDto politicalPartyInputDto) {
        return ResponseEntity.ok(politicalPartyService.createPoliticalParty(politicalPartyInputDto));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllPoliticalParties() {
        return ResponseEntity.ok(politicalPartyService.getAllPoliticalParties());
    }

    @PutMapping("/update/{politicalPartyId}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<Response> updatePoliticalParty(@PathVariable Long politicalPartyId,
                                                         @RequestBody PoliticalPartyInputDto politicalPartyInputDto) {
        return ResponseEntity.ok(politicalPartyService.updatePoliticalParty(politicalPartyId, politicalPartyInputDto));
    }

    @DeleteMapping("/delete/{politicalPartyId}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<Response> deletePoliticalParty(@PathVariable Long politicalPartyId) {
        return ResponseEntity.ok(politicalPartyService.deletePoliticalParty(politicalPartyId));
    }

    @GetMapping("/get-political-party-by-id/{politicalPartyId}")
    public ResponseEntity<Response> getPoliticalPartyById(@PathVariable Long politicalPartyId) {
        return ResponseEntity.ok(politicalPartyService.getPoliticalPartyById(politicalPartyId));
    }

}
