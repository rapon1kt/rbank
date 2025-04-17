package com.raponis.rbank.application.services;

import java.util.List;
import java.util.Optional;

import com.raponis.rbank.domain.entities.Client;

public interface ClientServiceInterface {

  Client registerNewClient(Client newClient);

  Client updateClientById(String id, Client client);

  List<Client> deleteClientById(String id);

  List<Client> findAllClients();

  Optional<Client> findClientById(String id);

}
