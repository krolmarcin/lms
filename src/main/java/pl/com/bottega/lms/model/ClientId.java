package pl.com.bottega.lms.model;

import java.io.Serializable;

public class ClientId implements Serializable {

    private Long id;

    public ClientId() {
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

        return id != null ? id.equals(clientId.id) : clientId.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
