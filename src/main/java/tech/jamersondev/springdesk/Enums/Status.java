package tech.jamersondev.springdesk.Enums;

public enum Status {
    ABERTO("Aberto"),
    ANDAMENTO("Em andamento"),
    FECHADO("Fechado");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
