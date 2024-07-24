package org.hibernate.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("E")
public class ExtendingEntity extends BaseEntity {

  @OneToMany(mappedBy = "extendingEntity")
  private List<ReferencedEntity> referencedEntity;

  private String code;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public List<ReferencedEntity> getReferencedEntity() {
    return referencedEntity;
  }

  public void setReferencedEntity(List<ReferencedEntity> referencedEntity) {
    this.referencedEntity = referencedEntity;
  }

}
