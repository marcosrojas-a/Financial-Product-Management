package com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Rest.Controller;

import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CrudUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractCrudController <CREATE_CMD, UPDATE_CMD, ENTITY, ID, REQUEST, RESPONSE> {

    protected final CrudUseCase<CREATE_CMD, UPDATE_CMD, ENTITY, ID> crudUseCase;
    private final Function<REQUEST, CREATE_CMD> toCreateCommand;
    private final Function<REQUEST, UPDATE_CMD> toUpdateCommand;
    private final Function<ENTITY, RESPONSE> toResponse;

    protected AbstractCrudController(CrudUseCase<CREATE_CMD, UPDATE_CMD, ENTITY, ID> crudUseCase,
                                     Function<REQUEST, CREATE_CMD> toCreateCommand,
                                     Function<REQUEST, UPDATE_CMD> toUpdateCommand,
                                     Function<ENTITY, RESPONSE> toResponse) {
        this.crudUseCase = crudUseCase;
        this.toCreateCommand = toCreateCommand;
        this.toUpdateCommand = toUpdateCommand;
        this.toResponse = toResponse;
    }

    @GetMapping
    public List<RESPONSE> getAll() {
        return crudUseCase.getAll().stream().map(toResponse).toList();
    }

    @GetMapping("/{id}")
    public RESPONSE getById(@PathVariable ID id) {
        return toResponse.apply(crudUseCase.getById(id));
    }

    @PostMapping
    public ResponseEntity<RESPONSE> create(@RequestBody REQUEST request) {
        ENTITY created = crudUseCase.create(toCreateCommand.apply(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse.apply(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RESPONSE> update(@PathVariable ID id, @RequestBody REQUEST request) {
        ENTITY updated = crudUseCase.update(id, toUpdateCommand.apply(request));
        return ResponseEntity.ok(toResponse.apply(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        crudUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
