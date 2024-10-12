package com.br.atletismo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("TREINADOR")
@Data
public class Treinador extends Usuario {
}
