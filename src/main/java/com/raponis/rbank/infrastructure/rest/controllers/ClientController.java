package com.raponis.rbank.infrastructure.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raponis.rbank.application.services.ClientService;
import com.raponis.rbank.domain.entities.Client;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

  @Autowired
  public ClientService clientService;

  @GetMapping
  public ResponseEntity<List<Client>> findAllClients() {
    return new ResponseEntity<>(this.clientService.findAllClients(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public Optional<Client> findClientById(@PathVariable("id") String id) {
    return this.clientService.findClientById(id);
  }

  @PostMapping
  public Client registerNewClient(@RequestBody Client client) {
    return this.clientService.registerNewClient(client);
  }

  @PutMapping("/{id}")
  public Client updateClientById(@PathVariable("id") String id, @RequestBody Client clientToUpdate) {
    return this.clientService.updateClientById(id, clientToUpdate);
  }

  @DeleteMapping("/{id}")
  public List<Client> deleteClientById(@PathVariable("id") String id) {
    return this.clientService.deleteClientById(id);
  }

}