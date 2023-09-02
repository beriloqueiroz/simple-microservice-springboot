package simple.microservice.domain.entity;

import org.junit.Assert;
import org.junit.Test;
import simple.microservice.domain.shared.DomainException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfessionalTest {

    @Test
    public void shouldBeCreateProfessional() throws DomainException {
        new Professional(
                1L,
                "96667246008",
                "123",
                "teste@gemail.com",
                "fulano",
                null
        );
    }

    @Test
    public void shouldBeNotCreateProfessionalWhenInvalidCpf() {
        try {
            new Professional(
                    1L,
                    "0101010101",
                    "123",
                    "teste@gemail.com",
                    "fulano",
                    null
            );
            Assert.fail();
        } catch (DomainException e){

        }
    }

    @Test
    public void shouldBeNotCreateProfessionalWhenInvalidName() {
        try {
            new Professional(
                    1L,
                    "96667246008",
                    "123",
                    "teste@gemail.com",
                    "",
                    null
            );
            Assert.fail();
        } catch (DomainException e){

        }
    }

    @Test
    public void shouldBePopulateClients() throws DomainException {
        Client client1 = new Client(
                1L,
                "fulano 1",
                "de tal 1",
                "teste@gmail.com",
                "96667246008",
                true,
                LocalDate.now().minusYears(30),
                1L
        );
        Client client2 = new Client(
                2L,
                "fulano 2",
                "de tal 2",
                "teste2@gmail.com",
                "96667246008",
                true,
                LocalDate.now().minusYears(30),
                1L
        );
        List<Client> clients = List.of(client1, client2);
        Professional professional = new Professional(
                1L,
                "96667246008",
                "123",
                "teste@gemail.com",
                "fulano",
                null
        );
        professional.populateClient(clients);

        assertEquals(professional.getClients().size(), 2);
        assertTrue(professional.getClients().get(0).getFirstName().equals(client1.getFirstName()));
        assertTrue(professional.getClients().get(1).getFirstName().equals(client2.getFirstName()));
    }

    @Test
    public void shouldBeAddClient() throws DomainException {
        Client client1 = new Client(
                1L,
                "fulano 1",
                "de tal 1",
                "teste@gmail.com",
                "96667246008",
                true,
                LocalDate.now().minusYears(30),
                1L
        );
        Professional professional = new Professional(
                1L,
                "96667246008",
                "123",
                "teste@gemail.com",
                "fulano",
                null
        );
        professional.addClient(client1);

        assertEquals(professional.getClients().size(), 1);
        assertTrue(professional.getClients().get(0).getFirstName().equals(client1.getFirstName()));
    }
}
