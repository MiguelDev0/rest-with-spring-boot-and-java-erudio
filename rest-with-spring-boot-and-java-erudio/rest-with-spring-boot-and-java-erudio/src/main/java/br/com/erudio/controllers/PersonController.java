package br.com.erudio.controllers;

import br.com.erudio.data.dto.v1.PersonDTO;
import br.com.erudio.data.dto.v2.PersonDTOV2;
import br.com.erudio.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {
    @Autowired
    private  final PersonServices service;

    public PersonController(PersonServices service) {
        this.service = service;
    }
    
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE })
    @Operation(summary = "Finds all People", description = "Finds all People", tags = {"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse (description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse (description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Finds a Person", description = "Finds a specific person by your id", tags = {"Person"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse (description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse (description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping(value = "/{id}",
    produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE })
    public PersonDTO findById(@PathVariable Long id) {
        var person = service.findById(id);

        return person;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_YAML_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE })
    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);
    }

    @PostMapping(value = "/v2",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_YAML_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE })
    public PersonDTOV2 create(@RequestBody PersonDTOV2 person) {
        return service.createV2(person);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_YAML_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE })
    public PersonDTO update(@RequestBody PersonDTO person) {
        return service.update(person);
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
