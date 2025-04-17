package com.raponis.rbank.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raponis.rbank.domain.entities.Client;
import com.raponis.rbank.infrastructure.database.repositories.MongoClientRepository;

@Service
public class ClientService implements ClientServiceInterface {

  @Autowired
  public MongoClientRepository mongoClientRepository;

  @Override
  public List<Client> findAllClients() {
    return this.mongoClientRepository.findAll();
  }

  @Override
  public Optional<Client> findClientById(String id) {
    return this.mongoClientRepository.findById(id);
  }

  @Override
  public Client registerNewClient(Client newClient) {
    Client savedClient = this.mongoClientRepository.save(newClient);
    return savedClient;
  }

  @Override
  public Client updateClientById(String id, Client clientToUpdate) {
    Optional<Client> clientExistent = this.mongoClientRepository.findById(clientToUpdate.getId());
    if (clientExistent.isPresent()) {
      clientToUpdate.setName("caralho seu delicioso rsrs");
      return this.mongoClientRepository.save(clientToUpdate);
    } else
      throw new Error("This user not exists.");
  }

  @Override
  public List<Client> deleteClientById(String id) {
    this.mongoClientRepository.deleteById(id);
    return this.findAllClients();
  }

}
