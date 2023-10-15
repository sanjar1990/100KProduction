package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "attach")
public class AttachEntity extends StringBaseEntity {
    @Column(name = "original_name")
    private String originalName;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "extension", nullable = false)
    private String extension;

    @Column(name = "duration")
    private String duration;

    @Column(name = "prt_id")
    private UUID prtId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prt_id", insertable = false, updatable = false)
    private ProfileEntity profileEntity;
}
