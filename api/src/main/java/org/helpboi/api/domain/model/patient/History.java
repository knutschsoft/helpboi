package org.helpboi.api.domain.model.patient;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Patient patient;
    
    @Min(1L)
    @NotNull
    @Column(name = "creator_id")
    private Long creatorId;

    @NotNull
    @Column(name = "created_at")
    private ZonedDateTime createdAt;
    
    @NotNull
    @Column(name = "modified_at")
    private ZonedDateTime modifiedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private HistoryType type;

    @Lob
    @NotNull
    @NotBlank
    private String content;

    public History() {
    }

    public History(
            Long id,
            Patient patient,
            Long creatorId,
            HistoryType type,
            String content
    ) {
        Objects.requireNonNull(patient);
        Objects.requireNonNull(creatorId);
        Objects.requireNonNull(type);
        Objects.requireNonNull(content);

        this.id = id;
        this.patient = patient;
        this.creatorId = creatorId;
        this.type = type;
        this.content = content;

        this.createdAt = ZonedDateTime.now();
        this.modifiedAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    public HistoryType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        History history = (History) o;
        return Objects.equals(id, history.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", patient=" + patient +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}
