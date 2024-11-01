package com.app.DeltaDrive.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record Location(Double latitude,Double longitude) {
}
