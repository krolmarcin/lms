package pl.com.bottega.lms.model;

import java.io.Serializable;

public class ClientId implements Serializable {

    private Long id;

    ClientId() {
    }

    public ClientId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientId clientId = (ClientId) o;

        return id.equals(clientId.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
