package org.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ReferencedEntity {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  private ExtendingEntity extendingEntity;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public ExtendingEntity getExtendingEntity() {
    return extendingEntity;
  }

  public void setExtendingEntity(ExtendingEntity extendingEntity) {
    this.extendingEntity = extendingEntity;
  }
}
