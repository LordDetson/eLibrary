package by.babanin.service;

import by.babanin.dao.entityBuilder.PublisherBulder;
import by.babanin.dao.repository.PublisherRepository;

public class PublisherService {
    private static final PublisherRepository publisherRepository = new PublisherRepository(new PublisherBulder());

    public static PublisherRepository getPublisherRepository() {
        return publisherRepository;
    }
}
